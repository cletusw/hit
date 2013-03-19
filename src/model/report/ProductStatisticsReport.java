package model.report;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import model.ItemManager;
import model.Product;
import model.ProductManager;
import model.report.builder.ReportBuilder;

@SuppressWarnings("serial")
public class ProductStatisticsReport extends Report {
	private ItemManager itemManager;
	private ProductManager productManager;
	private List<String> headers = Arrays.asList("Description", "Barcode", "Size",
			"3-Month Supply", "Supply: Cur/Avg", "Supply: Min/Max", "Supply Used/Added",
			"Shelf Life", "Used Age: Avg/Max", "Cur Age: Avg/Max");

	/**
	 * Set up an empty ProductStatisticsReport.
	 * 
	 * @param itemManager
	 *            Manager to use for gathering removed Item data
	 * @param productManager
	 *            Manager to use for gathering Product data
	 * 
	 * @pre true
	 * @post true
	 */
	public ProductStatisticsReport(ItemManager itemManager, ProductManager productManager) {
		super();
		this.itemManager = itemManager;
		this.productManager = productManager;
		reportName = "product-statistics";
	}

	/**
	 * Construct a completed ProductStatisticsReport where the reporting period is the number
	 * of months specified
	 * 
	 * @param builder
	 *            ReportBuilder to use
	 * @param months
	 *            Reporting Period
	 * 
	 * @pre true
	 * @post (new Date()).getTime() - getLastRunTime().getTime() < 1000
	 */
	public void construct(ReportBuilder builder, int months) {
		updateLastRunTime();
		builder.addDocumentTitle("Product Report (" + months + " Months)");
		builder.startTable(headers);

		Map<String, Product> products = new TreeMap<String, Product>();

		for (Product product : productManager.getProducts()) {
			products.put(product.getDescription(), product);
		}

		for (Product product : products.values()) {
			List<String> row = new ArrayList<String>();
			row.add(product.getDescription());
			row.add(product.getBarcode());
			row.add(product.getSize().toString());
			row.add(Integer.toString(product.getThreeMonthSupply()));
			row.add("SUPPLY");
			row.add("SUPPLY");
			row.add("SUPPLY");
			row.add(product.getShelfLife() + " months");
			row.add("Used Age");
			row.add("Cur Age");
			builder.addTableRow(row);
		}
		try {
			File file = builder.print(getFileName());
			java.awt.Desktop.getDesktop().open(file);
		} catch (IOException e) {
			System.out.println("Not able to open!! " + getFileName());
		}
	}
}
