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
						 new String[][] { {"username", "password" }, {"username", "password"}, {} },
						 null);
	}
	
	public static Menu mainMenu() {
		return new Menu( new String[] { "User menu", "Product menu", "Brand menu", "Retailer menu", "Competition menu" },
						 new String[] { "user_menu", "product_menu", "brand_menu", "retailer_menu", "competition_menu" },
						 new String[][] { {}, {}, {}, {}, {} },
						 null);
	}
	
	public static Menu userMenu() {
		return new Menu( new String[] { "Sign out", "My Reviews", "My Competitions", "Back" }, 
				 new String[] { "logout", "user_reviews", "user_competitions", "back" },
				 new String[][] { {}, {}, {}, {} },
				 mainMenu());
	}
	
	public static Menu productMenu() {
		return new Menu( new String[] { "Search", "List products", "Insert product", "Review product", "Remove review", "Back" }, 
				 new String[] { "search_product", "list_products", "insert_product", "insert_review", "remove_review", "back" },
				 new String[][] { {"product name", "brand name"}, {}, {"product name", "product description", "brand name"}, {"product name", "brand name", "rating", "feeback"}, {"product name", "brand name"}, {} },
				 mainMenu());
	}
	
	public static Menu brandMenu() {
		return new Menu( new String[] { "Search", "List brands", "Insert brand", "Back" }, 
				 new String[] { "search_brands", "list_brands", "insert_brand", "back" },
				 new String[][] { {"brand name"}, {}, {"brand name"}, {} },
				 mainMenu());
	}
	
	public static Menu retailerMenu() {
		return new Menu( new String[] { "Search", "List retailers", "Insert retailer", "Insert product on Retailer", "Back" }, 
				 new String[] { "search_retailers", "list_retailers", "insert_retailer", "insert_product_retailer", "back" },
				 new String[][] { {"retailer name"}, {}, {"retailer name"}, {"retailer name", "product name", "brand name", "stock", "price"}, {} },
				 mainMenu());
	}
	
	public static Menu competitionMenu() {
		return new Menu( new String[] { "Search", "List competitions", "Create competition", "Compete", "Start competition", "End competition", "Choose winner", "Back" }, 
				 new String[] { "search_competitions", "list_competitions", "insert_competitions", "add_competitor", "start_competition", "end_competition", "choose_winner", "back" },
				 new String[][] { {"competition title"}, {}, {"competition title", "competition description", "competition prize", "competition brand"}, {"competition title"}, {"competition title"}, {"competition title"}, {"competition title", "competition winner username"}, {}},
				 mainMenu());
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
		case "back": {
			next_menu = menu.m_back;
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
		case "competition_menu": {
			next_menu = competitionMenu();
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
		case "user_competitions": {
			PrintFactory.printUserCompetitions(shopAdvizor.getUserCompetitions());
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
			PrintFactory.printBrand(brand, shopAdvizor);
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
		case "search_retailers": {
			Retailer retailer = shopAdvizor.getRetailer(args[0]);
			PrintFactory.printRetailer(retailer);
			break;
		}
		case "list_retailers": {
			for(Object obj : shopAdvizor.m_retailers) {
				Retailer retailer = (Retailer)obj;
				System.out.println("Retailer name: " + retailer.m_name);
			}
			break;
		}
		case "insert_retailer": {
			Retailer retailer = new Retailer(args[0]);
			shopAdvizor.insertRetailer(retailer);
			break;
		}
		case "insert_product_retailer": {
			shopAdvizor.insertProductToRetailer(args[0], args[1], args[2], Integer.parseInt(args[3]), Double.parseDouble(args[4]));
			break;
		}
		case "search_competitions": {
			Competition competition = (Competition)shopAdvizor.getActivity(args[0]);
			PrintFactory.printCompetition(competition);
			break;
		}
		case "list_competitions": {
			PrintFactory.printCompetitions(shopAdvizor.m_activities);
			break;
		}
		case "insert_competitions": {
			Brand brand = shopAdvizor.getBrand(args[3]);
			Competition competition = new Competition(args[0], args[1], Double.parseDouble(args[2]), brand);
			shopAdvizor.insertActivity(competition);
			break;
		}
		case "add_competitor": {
			shopAdvizor.addCompetitor(args[0]);
			break;
		}
		case "start_competition": {
			shopAdvizor.startActivity(args[0]);
			break;
		}
		case "end_competition": {
			shopAdvizor.endActivity(args[0]);
			break;
		}
		case "choose_winner": {
			shopAdvizor.chooseWinner(args[0], args[1]);
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
