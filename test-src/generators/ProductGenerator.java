package generators;

import java.util.Date;

import model.Product;
import model.ProductManager;
import builder.model.ProductBuilder;

public class ProductGenerator {
	private final ProductManager manager;

	public ProductGenerator(ProductManager productManager) {
		manager = productManager;
	}

	public Product createProductInDateRange(int minMonths, int maxMonths) {
		Date now = new Date();
		long millisNow = now.getTime();
		long millisLatest = millisNow;
		Date latestDate = new Date(millisLatest);
		int currentMonth = now.getMonth();
		int currentYear = now.getYear();
		for (int i = 0; i < minMonths; i++) {
			currentMonth--;
			if (currentMonth == 0) {
				currentMonth = 12;
				currentYear--;
			}
		}
		latestDate.setMonth(currentMonth);
		latestDate.setYear(currentYear);
		millisLatest = latestDate.getTime();
		for (int i = minMonths; i < maxMonths; i++) {
			currentMonth--;
			if (currentMonth == 0) {
				currentMonth = 12;
				currentYear--;
			}
		}
		Date earliestDate = now;
		earliestDate.setMonth(currentMonth);
		earliestDate.setYear(currentYear);
		long millisEarliest = earliestDate.getTime();
		Product product = new ProductBuilder()
				.creationDate(
						new Date((long) (Math.random() * (millisLatest - millisEarliest))
								+ millisEarliest)).productManager(manager).build();
		return product;
	}
}
