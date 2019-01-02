package ConsoleInterface;

import java.util.Scanner;

import ShopAdvizor.*;

public class MenuFactory {
	public static ShopAdvizor shopAdvizor = new ShopAdvizor();
	public static Scanner scanner = new Scanner(System.in);
	public static Menu menu = authMenu();
	
	public static Menu authMenu() {
		return new Menu( new String[] { "Sign in", "Sign up", "Continue as guest" }, 
						 new String[] { "login", "insertUser", "guest" },
						 new String[][] { {"username", "password" }, {"username", "password"}, {} });
	}
	
	public static Menu mainMenu() {
		return new Menu( new String[] { "User menu", "Product menu", "Brand menu", "Retailer menu" },
						 new String[] { "user_menu", "product_menu", "brand_menu", "retailer_menu" },
						 new String[][] { {}, {}, {}, {} });
	}
	
	public static Menu userMenu() {
		return new Menu( new String[] { "Sign out", "My Reviews" }, 
				 new String[] { "logout", "user_reviews" },
				 new String[][] { {}, {} });
	}
	
	public static Menu productMenu() {
		return new Menu( new String[] { "Search" }, 
				 new String[] { "search_product" },
				 new String[][] { {"product name", "brand name"} });
	}
	
	public static Menu brandMenu() {
		return null;
	}
	
	public static Menu retailerMenu() {
		return null;
	}
	
	public static void call(String callback, String[] args) {
		Menu next_menu = menu;
		switch (callback) {
		case "login": {
			shopAdvizor.login(args[0], args[1]);
			next_menu = mainMenu();
			break;
		}
		case "insertUser": {
			shopAdvizor.insertUser(new User(args[0], args[1]));
			shopAdvizor.login(args[0], args[1]);
			next_menu = mainMenu();
			break;
		}
		case "guest": {
			next_menu = mainMenu();
			break;
		}
		case "user_menu": {
			next_menu = userMenu();
			break;
		}
		case "product_menu": {
			next_menu = productMenu();
			break;
		}
		case "brand_menu": {
			next_menu = brandMenu();
			break;
		}
		case "retailer_menu": {
			next_menu = retailerMenu();
			break;
		}
		case "logout": {
			shopAdvizor.logout();
			next_menu = authMenu();
			break;
		}
		case "user_reviews": {
			PrintFactory.printUserReviews(shopAdvizor.getUserReviews());
			break;
		}
		case "search_product": {
			Brand brand = shopAdvizor.getBrand(args[1]);
			Product product = shopAdvizor.getProduct(args[0], brand);
			PrintFactory.printProduct(product);
			System.out.println("Average Rating: " + shopAdvizor.getProductAvgRating(args[0], args[1]) + "/5");
			PrintFactory.printLowestPrice(shopAdvizor.getLowestPriceRetailer(product));
			PrintFactory.printProductReviews(shopAdvizor.getProductReviews(args[0], args[1]));
			System.out.println();
			break;
		}
		default: break;
		}
		menu = next_menu;
	}
	
	public static void run() {
		while(true) {
			menu.run();
		}
	}
	
	public static void printError(String msg) {
		System.out.println("Error: " + msg);
	}
	
	public static void populate() {
		shopAdvizor.insertUser(new User("admin", "admin", Quotes.AdminQuote.getInstance()));
		shopAdvizor.insertUser(new User("user1", "pass1"));
		shopAdvizor.login("admin", "admin");
		String[] brands = new String[] { "Oreo", "Chip Mix" };
		for(String b : brands) {
			Brand brand = new Brand(b);
			shopAdvizor.insertBrand(brand);
			shopAdvizor.insertProduct(new Product("Bolachas", "Bolachas de chocolate", brand));
		}
		shopAdvizor.insertRetailer(new Retailer("Pingo Doce"));
		shopAdvizor.insertProductToRetailer("Pingo Doce", "Bolachas", "Oreo", 20, 5.0);
		shopAdvizor.logout();
		shopAdvizor.login("user1", "pass1");
		shopAdvizor.insertReview("Bolachas", "Oreo", 5, "Muito boas!");
		shopAdvizor.logout();
	}
	
	public static void main(String[] args) {
		MenuFactory.populate();
		MenuFactory.run();
	}
}
