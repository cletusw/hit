package model.persistence;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import model.Action;
import model.Action.ActionType;
import model.Barcode;
import model.HomeInventoryTracker;
import model.Item;
import model.Product;
import model.ProductContainer;
import model.ProductGroup;
import model.ProductQuantity;
import model.StorageUnit;
import model.Unit;
import model.report.ExpiredItemsReport;
import model.report.NMonthSupplyReport;
import model.report.NoticesReport;
import model.report.ProductStatisticsReport;
import model.report.RemovedItemsReport;

/**
 * Observes the model and persists all changes made to it an a local MySQL database
 * 
 * @author Matthew
 * @version 1.0 -- Snell CS 340 Phase 4.0
 * 
 */
public class RdbDao extends InventoryDao {
	private static final String dbFile = "inventory.sqlite";

	// Used when persisting to DB
	private Map<Object, Integer> referenceToId;

	// Used when loading from DB
	private Map<Integer, Product> productIdToReference;
	private Map<Integer, Item> itemIdToReference;
	private Map<Integer, ProductContainer> productContainerIdToReference;
	private Map<Integer, ProductQuantity> productQuantityIdToReference;
	private Map<Integer, Unit> unitIdToReference;

	public RdbDao() {
		referenceToId = new HashMap<Object, Integer>();

		productIdToReference = new HashMap<Integer, Product>();
		itemIdToReference = new HashMap<Integer, Item>();
		productContainerIdToReference = new HashMap<Integer, ProductContainer>();
		productQuantityIdToReference = new HashMap<Integer, ProductQuantity>();
		unitIdToReference = new HashMap<Integer, Unit>();
	}

	@Override
	public void applicationClose(HomeInventoryTracker hit) {
		// Do nothing
	}

	@Override
	public HomeInventoryTracker loadHomeInventoryTracker() {
		File f = null;
		HomeInventoryTracker hit = new HomeInventoryTracker();

		try {
			Class.forName("org.sqlite.JDBC");

			f = new File(dbFile);
			if (!f.exists()) {
				createSchema();
			}

			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM ProductContainer "
					+ "INNER JOIN StorageUnit "
					+ "ON ProductContainer.ProductContainer_id=StorageUnit.StorageUnit_id");
			while (results.next()) {
				Integer id = results.getInt("ProductContainer_id");
				String name = results.getString("name");

				StorageUnit su = new StorageUnit(name, hit.getProductContainerManager());

				productContainerIdToReference.put(id, su);
				referenceToId.put(su, id);
			}

			results = statement.executeQuery("SELECT * FROM Unit");
			while (results.next()) {
				String unitName = results.getString("Unit_name");
				Integer unitId = results.getInt("Unit_id");
				Unit u = Unit.valueOf(unitName);
				unitIdToReference.put(unitId, u);
			}

			results = statement.executeQuery("SELECT * FROM ProductQuantity");
			while (results.next()) {
				Integer pqId = results.getInt("ProductQuantity_id");
				float q = results.getFloat("quantity");
				Integer unitId = results.getInt("Unit_id");

				Unit u = unitIdToReference.get(unitId);
				ProductQuantity quantity = new ProductQuantity(q, u);
				referenceToId.put(quantity, pqId);
				productQuantityIdToReference.put(pqId, quantity);
			}

			results = statement.executeQuery("SELECT * FROM ProductContainer "
					+ "INNER JOIN ProductGroup "
					+ "ON ProductContainer.ProductContainer_id = "
					+ "ProductGroup.ProductGroup_id");
			while (results.next()) {
				Integer id = results.getInt("ProductGroup_id");
				String name = results.getString("name");
				Integer threeMonthSupplyId = results.getInt("ProductQuantity_id");
				ProductQuantity threeMonthSupply = productQuantityIdToReference
						.get(threeMonthSupplyId);
				Integer parentId = results.getInt("parent");
				ProductContainer parent = productContainerIdToReference.get(parentId);

				ProductGroup pg = new ProductGroup(name, threeMonthSupply,
						threeMonthSupply.getUnits(), parent, hit.getProductContainerManager());

				productContainerIdToReference.put(id, pg);
				referenceToId.put(pg, id);
			}

			results = statement.executeQuery("SELECT * FROM Product");
			while (results.next()) {
				Integer id = results.getInt("Product_id");
				Date creationDate = results.getDate("creationDate");
				String barcode = results.getString("barcode");
				String description = results.getString("description");
				Integer quantityId = results.getInt("ProductQuantity_id");
				Integer shelfLife = results.getInt("shelfLife");
				Integer tms = results.getInt("threeMonthSupply");
				ProductQuantity quantity = productQuantityIdToReference.get(quantityId);

				Product p = new Product(barcode, description, creationDate, shelfLife, tms,
						quantity, hit.getProductManager());

				productIdToReference.put(id, p);
				referenceToId.put(p, id);
			}

			results = statement.executeQuery("SELECT * FROM Product_has_ProductContainer");
			while (results.next()) {
				Integer productId = results.getInt("Product_id");
				Integer containerId = results.getInt("ProductContainer_id");

				Product product = productIdToReference.get(productId);
				ProductContainer container = productContainerIdToReference.get(containerId);

				product.addContainer(container);
				container.add(product);
			}

			results = statement.executeQuery("SELECT * FROM Item");
			while (results.next()) {
				Integer itemId = results.getInt("Item_id");
				Date entryDate = results.getDate("entryDate");
				Date exitTime = results.getDate("exitTime");
				String barcode = results.getString("barcode");
				Date expirationDate = results.getDate("expirationDate");
				Integer productId = results.getInt("Product_id");
				Integer containerId = results.getInt("ProductContainer_id");
				Product product = productIdToReference.get(productId);
				ProductContainer container = productContainerIdToReference.get(containerId);
				Barcode code = new Barcode(barcode);

				Item i = new Item(code, product, container, entryDate, hit.getItemManager());
				i.setExpirationDate(expirationDate, this);

				if (exitTime != null) {
					container.remove(i, hit.getItemManager());
					i.setExitDate(exitTime, this);
				}

				itemIdToReference.put(itemId, i);
				referenceToId.put(i, itemId);
			}

			results = statement.executeQuery("SELECT * FROM Report");
			while (results.next()) {
				Integer reportId = results.getInt("Report_id");
				Date runtime = results.getDate("Report_runtime");

				switch (reportId) {
				case 0: // ExpiredItemsReport
					ExpiredItemsReport eir = new ExpiredItemsReport(
							hit.getProductContainerManager());
					eir.setLastRuntime(runtime, this);
					hit.getReportManager().setExpiredItemsReport(eir, this);
					break;
				case 1:// NMonthSupplyReport
					NMonthSupplyReport nmsr = new NMonthSupplyReport(hit.getProductManager(),
							hit.getProductContainerManager());
					nmsr.setLastRuntime(runtime, this);
					hit.getReportManager().setNMonthSupplyReport(nmsr, this);
					break;

				case 2:// NoticesReport
					NoticesReport nr = new NoticesReport(hit.getProductContainerManager());
					nr.setLastRuntime(runtime, this);
					hit.getReportManager().setNoticesReport(nr, this);

					break;
				case 3:// ProductStatisticsReport
					ProductStatisticsReport psr = new ProductStatisticsReport(
							hit.getItemManager(), hit.getProductManager());
					psr.setLastRuntime(runtime, this);
					hit.getReportManager().setProductStatiscticsReport(psr, this);
					break;

				case 4: // RemovedItemsReport
					RemovedItemsReport rir = new RemovedItemsReport(hit.getItemManager());
					rir.setLastRuntime(runtime, this);
					hit.getReportManager().setRemovedItemsReport(rir, this);
					break;

				default:
					throw new Exception("Unknown report id: " + reportId);
				}
			}

			return hit;
		} catch (Exception e) {
			if (f != null) {
				f.delete();
			}
			e.printStackTrace();
			return new HomeInventoryTracker();
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Action action = (Action) arg1;
		ActionType type = action.getAction();
		Object obj = action.getObject();

		switch (type) {
		case CREATE:
			if (obj instanceof Item)
				insertItem((Item) obj);

			if (obj instanceof Product)
				insertProduct((Product) obj);

			if (obj instanceof ProductContainer)
				insertProductContainer((ProductContainer) obj);

			break;
		case DELETE:
			break;
		case EDIT:
			break;
		case INVISIBLE_EDIT:
			break;
		case MOVE:
			break;
		}

	}

	private void createSchema() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);

		Statement statement = connection.createStatement();
		statement.setQueryTimeout(30);

		statement.executeUpdate("DROP TABLE IF EXISTS `Unit`");
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS `Unit` (`Unit_id` INTEGER "
				+ "NOT NULL PRIMARY KEY AUTOINCREMENT , `Unit_name` VARCHAR(45) NULL )");

		statement.executeUpdate("DROP TABLE IF EXISTS `ProductQuantity`");
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS `ProductQuantity` ("
				+ "`ProductQuantity_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,"
				+ "`quantity` FLOAT NULL ,`Unit_id` INTEGER NULL ,"
				+ "FOREIGN KEY (`Unit_id` )" + "REFERENCES `Unit` (`Unit_id` ) )");

		statement.executeUpdate("DROP TABLE IF EXISTS `Product`");
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS `Product` ("
				+ "  `Product_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,"
				+ "  `creationDate` DATE NULL ," + "  `barcode` VARCHAR(15) NULL ,"
				+ "  `description` TEXT NULL ," + "  `ProductQuantity_id` INTEGER NULL ,"
				+ "  `shelfLife` INTEGER NULL ," + "  `threeMonthSupply` INTEGER NULL ,"
				+ "    FOREIGN KEY (`ProductQuantity_id` )"
				+ "    REFERENCES `ProductQuantity` (`ProductQuantity_id` ) )");

		statement.executeUpdate("DROP TABLE IF EXISTS `ProductContainer`");
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS `ProductContainer` ("
				+ "  `ProductContainer_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,"
				+ "  `name` TEXT NULL )");

		statement.executeUpdate("DROP TABLE IF EXISTS `Product_has_ProductContainer`");
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS `Product_has_ProductContainer` ("
				+ "  `Product_id` INTEGER NOT NULL ,"
				+ "  `ProductContainer_id` INTEGER NOT NULL ,"
				+ "  PRIMARY KEY (`Product_id`, `ProductContainer_id`) ,"
				+ "    FOREIGN KEY (`Product_id` )"
				+ "    REFERENCES `Product` (`Product_id` ),"
				+ "    FOREIGN KEY (`ProductContainer_id` )"
				+ "    REFERENCES `ProductContainer` (`ProductContainer_id` ) )");

		statement.executeUpdate("DROP TABLE IF EXISTS `Item`");

		statement.executeUpdate("CREATE TABLE IF NOT EXISTS `Item` ("
				+ "  `Item_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,"
				+ "  `entryDate` DATE NULL ," + "  `exitTime` DATETIME NULL ,"
				+ "  `barcode` VARCHAR(45) NULL ," + "  `expirationDate` DATE NULL ,"
				+ "  `Product_id` INTEGER NULL ,"
				+ "  `ProductContainer_id` INTEGER NOT NULL ,"
				+ "    FOREIGN KEY (`Product_id` )"
				+ "    REFERENCES `Product` (`Product_id` ),"
				+ "    FOREIGN KEY (`ProductContainer_id` )"
				+ "    REFERENCES `ProductContainer` (`ProductContainer_id` ) )");

		statement.executeUpdate("DROP TABLE IF EXISTS `ProductGroup`");
		statement.executeUpdate("CREATE  TABLE IF NOT EXISTS `ProductGroup` ("
				+ "  `ProductGroup_id` INTEGER NOT NULL PRIMARY KEY ,"
				+ "  `ProductQuantity_id` INTEGER NULL ," + "  `parent` INTEGER NOT NULL ,"
				+ "    FOREIGN KEY (`ProductQuantity_id` )"
				+ "    REFERENCES `ProductQuantity` (`ProductQuantity_id` ),"
				+ "    FOREIGN KEY (`parent` )"
				+ "    REFERENCES `ProductContainer` (`ProductContainer_id` ),"
				+ "    FOREIGN KEY (`ProductGroup_id` )"
				+ "    REFERENCES `ProductContainer` (`ProductContainer_id` ) )");

		statement.executeUpdate("DROP TABLE IF EXISTS `StorageUnit`");
		statement.executeUpdate("CREATE  TABLE IF NOT EXISTS `StorageUnit` ("
				+ "  `StorageUnit_id` INTEGER NOT NULL PRIMARY KEY ,"
				+ "    FOREIGN KEY (`StorageUnit_id` )"
				+ "    REFERENCES `ProductContainer` (`ProductContainer_id` ) )");

		statement.executeUpdate("DROP TABLE IF EXISTS `Report`");
		statement.executeUpdate("CREATE  TABLE IF NOT EXISTS `Report` ("
				+ "  `Report_id` INTEGER NOT NULL PRIMARY KEY ,"
				+ "  `Report_runtime` DATETIME NULL )");

		connection.close();
	}

	private void insertItem(Item i) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			Statement statement = connection.createStatement();

			Long exitTime = 0l;
			if (i.getExitTime() != null)
				exitTime = i.getExitTime().getTime();
			else
				exitTime = null;

			Integer productId = referenceToId.get(i.getProduct());
			Integer containerId = referenceToId.get(i.getContainer());

			String stmt = "INSERT INTO Item VALUES(null, " + i.getEntryDate().getTime() + ", "
					+ exitTime + ", " + i.getBarcode() + ", "
					+ i.getExpirationDate().getTime() + ", " + productId + ", " + containerId;

			statement.executeUpdate(stmt);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void insertProduct(Product p) {

	}

	private void insertProductContainer(ProductContainer pc) {

	}
}
