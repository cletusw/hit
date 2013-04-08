package model.productIdentification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Initializes and maintains ProductIdentificationPlugins.
 * 
 * @author Matthew
 * @version 1.0 -- Snell CS 340 Phase 4.0
 */
public class ProductIdentificationPluginManager {
	public static void printClasspath() {

		// Get the System Classloader
		ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();

		// Get the URLs
		URL[] urls = ((URLClassLoader) sysClassLoader).getURLs();

		for (int i = 0; i < urls.length; i++) {
			System.err.println(urls[i].getFile());
		}
	}

	private final String configFileName = "plugins.cfg";

	private ProductIdentificationPluginWrapper root;

	/**
	 * Uses the ProductIdentificationPlugin to determine the desription of the product
	 * 
	 * @param productBarcode
	 * @return the description for the product, or null if not found
	 * 
	 * @pre product != null
	 * @post true
	 */
	public String getDescriptionForProduct(String productBarcode) {
		return root.getDescriptionForProduct(productBarcode);
	}

	/**
	 * Loads all plugins based on config file
	 */
	public void loadPlugins() {
		try {
			FileReader fileReader = new FileReader(new File(configFileName));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			// printClasspath();

			ProductIdentificationPluginWrapper previousWrapper = null;

			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				Class c = null;
				try {
					c = Class.forName(line);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
				ProductIdentificationPlugin plugin = null;
				try {
					plugin = (ProductIdentificationPlugin) c.newInstance();
				} catch (InstantiationException e) {
					System.out.println("Unable to instantiate " + c.getCanonicalName());
					e.printStackTrace();
					continue;
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
				ProductIdentificationPluginWrapper currentWrapper = new ProductIdentificationPluginWrapper(
						plugin);
				System.out.println("Successfully loaded plugin: " + line);
				// System.out.println(plugin.getDescriptionForProduct("A Product!"));
				if (root == null) {
					root = currentWrapper;
				} else {
					previousWrapper.setSuccessor(currentWrapper);
				}
				previousWrapper = currentWrapper;
			}
			bufferedReader.close();
		} catch (IOException e) {
			System.err.println("Cannot open plugin configuration file: .\\" + configFileName);
		}
	}
}
