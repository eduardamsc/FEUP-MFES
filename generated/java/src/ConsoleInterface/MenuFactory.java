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
		return new Menu( new String[] { "Search", "List products", "Insert product", "Review product", "Remove review" }, 
				 new String[] { "search_product", "list_products", "insert_product", "insert_review", "remove_review" },
				 new String[][] { {"product name", "brand name"}, {}, {"product name", "product description", "brand name"}, {"product name", "brand name", "rating", "feeback"}, {"product name", "brand name"} });
	}
	
	public static Menu brandMenu() {
		return new Menu( new String[] { "Search", "List brands", "Insert brand" }, 
				 new String[] { "search_brands", "list_brands", "insert_brand" },
				 new String[][] { {"brand name"}, {}, {"brand name"} });
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
		case "insert_product": {
			Brand brand = shopAdvizor.getBrand(args[2]);
			Product product = new Product(args[0], args[1], brand);
			shopAdvizor.insertProduct(product);
			break;
		}
		case "insert_review": {
			shopAdvizor.insertReview(args[0], args[1], Double.parseDouble(args[2]), args[3]);
			break;
		}
		case "remove_review": {
			shopAdvizor.removeReview(args[0], args[1]);
			break;
		}
		case "list_products": {
			for(Object obj : shopAdvizor.m_products) {
				Product product = (Product)obj;
				PrintFactory.printProduct(product);
				System.out.println();
			}
			break;
		}
		case "search_brands": {
			Brand brand = shopAdvizor.getBrand(args[0]);
			System.out.println("Brand name: " + brand.m_name);
			boolean initial_print = false;
			for(Object obj : shopAdvizor.m_products) {
				Product product = (Product)obj;
				if(product.m_brand.m_name == brand.m_name) {
					if(!initial_print) {
						System.out.println("Products:");
						initial_print = true;
					}
					System.out.println("Product name: " + product.m_name);
				}
			}
			if(!initial_print)
				System.out.println("Brand " + brand.m_name + " has no products!");
			System.out.println();
			break;
		}
		case "list_brands": {
			for(Object obj : shopAdvizor.m_brands) {
				Brand brand = (Brand)obj;
				System.out.println("Brand name: " + brand.m_name);
			}
			break;
		}
		case "insert_brand": {
			Brand brand = new Brand(args[0]);
			shopAdvizor.insertBrand(brand);
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
