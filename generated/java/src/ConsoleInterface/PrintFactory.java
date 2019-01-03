package ConsoleInterface;

import ShopAdvizor.*;
import org.overture.codegen.runtime.*;
import java.util.*;

public class PrintFactory {
	public static void printUserReviews(VDMSet reviews) {
		for(Object obj : reviews) {
			Tuple tuple = (Tuple)obj;
			Product product = (Product)tuple.get(0);
			Product.Review review = (Product.Review)tuple.get(1);
			printProduct(product);
			System.out.println("Rating: " + review.rating + "/5");
			System.out.println("Feedback: " + review.feedback);
			System.out.println();
		}
	}
	
	public static void printProduct(Product product) {
		System.out.println("Product: " + product.m_name);
		System.out.println("Brand: " + product.m_brand.m_name);
	}
	
	public static void printLowestPrice(Tuple tuple) {
		if (tuple == null) {
			System.out.println("No retailer sells this product!");
			return;
		}
		Retailer retailer = (Retailer)tuple.get(0);
		Double price = (Double)tuple.get(1);
		System.out.println("Lowest price: " + price + "€ at " + retailer.m_name);
	}
	
	public static void printProductReviews(VDMMap reviews) {
		for(Object obj : reviews.entrySet()) {
			Map.Entry<User, Product.Review> entry = (Map.Entry<User, Product.Review>)obj;
			User user = entry.getKey();
			Product.Review review = entry.getValue();
			System.out.println();
			System.out.println("User: " + user.m_username);
			System.out.println("Rating: " + review.rating);
			System.out.println("Feedback: " + review.feedback);
		}
	}

}
