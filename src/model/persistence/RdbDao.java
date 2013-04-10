package model.persistence;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

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
public class RdbDao extends InventoryDao implements Observer {
	private static final String dbFile = "inventory.sqlite";

	// Used when persisting to DB
	private Map<Object, Integer> referenceToId;

	// Used when loading from DB
	private Map<Integer, Product> productIdToReference;
	private Map<Integer, Item> itemIdToReference;
	private Map<Integer, ProductContainer> productContainerIdToReference;
	private Map<Integer, ProductQuantity> productQuantityIdToReference;
	private Map<Integer, Unit> unitIdToReference;
	private Map<String, Integer> unitToId;

	public RdbDao() {
		referenceToId = new HashMap<Object, Integer>();

		productIdToReference = new HashMap<Integer, Product>();
		itemIdToReference = new HashMap<Integer, Item>();
		productContainerIdToReference = new HashMap<Integer, ProductContainer>();
		productQuantityIdToReference = new HashMap<Integer, ProductQuantity>();
		unitIdToReference = new HashMap<Integer, Unit>();

		int index = 0;
		unitToId = new HashMap<String, Integer>();
		unitToId.put(Unit.COUNT.toDBString(), index++);
		unitToId.put(Unit.POUNDS.toDBString(), index++);
		unitToId.put(Unit.OUNCES.toDBString(), index++);
		unitToId.put(Unit.GRAMS.toDBString(), index++);
		unitToId.put(Unit.KILOGRAMS.toDBString(), index++);
		unitToId.put(Unit.GALLONS.toDBString(), index++);
		unitToId.put(Unit.QUARTS.toDBString(), index++);
		unitToId.put(Unit.PINTS.toDBString(), index++);
		unitToId.put(Unit.FLUID_OUNCES.toDBString(), index++);
		unitToId.put(Unit.LITERS.toDBString(), index++);
	}

	@Override
	public void applicationClose(HomeInventoryTracker hit) {
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

			Map<Integer, Set<ProductContainer>> productToContainer = new HashMap<Integer, Set<ProductContainer>>();
			results = statement.executeQuery("SELECT * FROM Product_has_ProductContainer");
			while (results.next()) {
				Integer productId = results.getInt("Product_id");
				Integer containerId = results.getInt("ProductContainer_id");

				ProductContainer container = productContainerIdToReference.get(containerId);

				Set<ProductContainer> containers = productToContainer.get(productId);
				if (containers == null)
					containers = new HashSet<ProductContainer>();

				containers.add(container);
				productToContainer.put(productId, containers);
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

				Set<ProductContainer> containers = productToContainer.get(id);

				Product p = null;
				for (ProductContainer pc : containers) {
					if (p == null) {
						p = new Product(barcode, description, creationDate, shelfLife, tms,
								quantity, pc, hit.getProductManager());
					} else {
						pc.add(p);
						p.addContainer(pc);
					}
				}

				productIdToReference.put(id, p);
				referenceToId.put(p, id);
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

			hit.getItemManager().addObserver(this);
			hit.getProductContainerManager().addObserver(this);
			hit.getProductManager().addObserver(this);

			return hit;
		} catch (Exception e) {
			if (f != null) {
				f.delete();
			}
			e.printStackTrace();

			HomeInventoryTracker h = new HomeInventoryTracker();

			h.getItemManager().addObserver(this);
			h.getProductContainerManager().addObserver(this);
			h.getProductManager().addObserver(this);

			return h;
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
			if (obj instanceof Item)
				updateItem((Item) obj); // don't delete the item, just update the row.

			if (obj instanceof Product)
				deleteProduct((Product) obj);

			if (obj instanceof ProductContainer)
				deleteProductContainer((ProductContainer) obj);
			break;
		case EDIT:
			if (obj instanceof Item)
				updateItem((Item) obj);
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

		Set<String> units = unitToId.keySet();
		for (String unitString : units) {
			statement.executeUpdate("INSERT INTO Unit VALUES(" + unitToId.get(unitString)
					+ ",\"" + unitString + "\")");
		}

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

	private void deleteProduct(Product p) {

	}

	private void deleteProductContainer(ProductContainer pc) {

	}

	private void insertItem(Item i) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO Item VALUES(null,?,?,?,?,?,?)");

			Integer productId = referenceToId.get(i.getProduct());
			Integer containerId = referenceToId.get(i.getContainer());

			statement.setLong(1, i.getEntryDate().getTime());
			if (i.getExitTime() != null) {
				statement.setLong(2, i.getExitTime().getTime());
			} else {
				statement.setNull(2, java.sql.Types.DATE);
			}
			statement.setString(3, i.getBarcode());
			if (i.getExpirationDate() != null) {
				statement.setLong(4, i.getExpirationDate().getTime());
			} else {
				statement.setNull(4, java.sql.Types.DATE);
			}
			statement.setInt(5, productId);
			statement.setInt(6, containerId);

			statement.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void insertProduct(Product p) {
		long creationDate = p.getCreationDate().getTime();
		String barcode = p.getBarcode();
		String description = p.getDescription();
		ProductQuantity pq = p.getProductQuantity();
		int shelfLife = p.getShelfLife();
		int tms = p.getThreeMonthSupply();

		insertProductQuantity(pq);

		int pqId = referenceToId.get(pq);

		String insertStatement = "INSERT INTO Product VALUES(null, " + creationDate + ","
				+ "\"" + barcode + "\"," + "\"" + description + "\"," + pqId + "," + shelfLife
				+ "," + tms + ")";

		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			Statement statement = conn.createStatement();
			statement.executeUpdate(insertStatement);
			ResultSet set = statement.getGeneratedKeys();
			int key = -1;
			while (set.next())
				key = set.getInt(1);
			referenceToId.put(p, key);

			Set<ProductContainer> containers = p.getProductContainers();
			for (ProductContainer pc : containers) {
				Integer containerId = referenceToId.get(pc);
				insertStatement = "INSERT INTO Product_has_ProductContainer VALUES(" + key
						+ "," + containerId + ")";
				statement.executeUpdate(insertStatement);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertProductContainer(ProductContainer pc) {
		String name = pc.getName();
		String insertStatement = "INSERT INTO ProductContainer VALUES(null, \"" + name + "\")";
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			Statement statement = conn.createStatement();
			statement.executeUpdate(insertStatement);
			ResultSet set = statement.getGeneratedKeys();
			int key = -1;
			while (set.next())
				key = set.getInt(1);
			if (pc instanceof StorageUnit) {
				insertStatement = "INSERT INTO StorageUnit VALUES (" + key + ")";
				statement.executeUpdate(insertStatement);
				referenceToId.put(pc, key);
			} else if (pc instanceof ProductGroup) {
				insertProductQuantity(((ProductGroup) pc).getThreeMonthSupply());

				Integer parentId = referenceToId.get(((ProductGroup) pc).getContainer());
				insertStatement = "INSERT INTO ProductGroup VALUES(" + key + ", "
						+ referenceToId.get(((ProductGroup) pc).getThreeMonthSupply()) + ", "
						+ parentId + ")";
				statement.executeUpdate(insertStatement);
				referenceToId.put(pc, key);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertProductQuantity(ProductQuantity pq) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			Statement statement = conn.createStatement();
			String insertStatement = "INSERT INTO ProductQuantity VALUES(null, "
					+ pq.getQuantity() + ", " + unitToId.get(pq.getUnits().toDBString()) + ")";
			statement.executeUpdate(insertStatement);
			ResultSet set = statement.getGeneratedKeys();
			int key = -1;
			while (set.next())
				key = set.getInt(1);

			referenceToId.put(pq, key);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateItem(Item i) {
		Integer itemId = referenceToId.get(i);
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			PreparedStatement statement;
			if (i.getContainer() != null) { // moved containers
				statement = connection
						.prepareStatement("UPDATE Item SET entryDate=?, exitTime=?,"
								+ "barcode=?, expirationDate=?, Product_id=?, ProductContainer_id=?"
								+ "WHERE Item_id=?");

				Integer containerId = referenceToId.get(i.getContainer());
				statement.setInt(6, containerId);
				statement.setInt(7, itemId);
			} else { // removed -- update everything but leave the reference to container
				statement = connection
						.prepareStatement("UPDATE Item SET entryDate=?, exitTime=?,"
								+ "barcode=?, expirationDate=?, Product_id=?"
								+ "WHERE Item_id=?");
				statement.setInt(6, itemId);
			}

			Integer productId = referenceToId.get(i.getProduct());

			statement.setLong(1, i.getEntryDate().getTime());
			if (i.getExitTime() != null) {
				statement.setLong(2, i.getExitTime().getTime());
			} else {
				statement.setNull(2, java.sql.Types.DATE);
			}
			statement.setString(3, i.getBarcode());
			if (i.getExpirationDate() != null) {
				statement.setLong(4, i.getExpirationDate().getTime());
			} else {
				statement.setNull(4, java.sql.Types.DATE);
			}
			statement.setInt(5, productId);

			statement.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
