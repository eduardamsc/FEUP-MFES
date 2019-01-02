package ConsoleInterface;

import ShopAdvizor.*;
import org.overture.codegen.runtime.*;

public class PrintFactory {
	public static void printUserReviews(VDMSet reviews) {
		for(Object obj : reviews) {
			Tuple tuple = (Tuple)obj;
			Product product = (Product)tuple.get(0);
			Product.Review review = (Product.Review)tuple.get(1);
			System.out.println("Product: " + product.m_name);
			System.out.println("Brand: " + product.m_brand.m_name);
			System.out.println("Rating: " + review.rating + "/5");
			System.out.println("Feedback: " + review.feedback);
			System.out.println();
		}
	}
}
