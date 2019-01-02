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
	
	public static void main(String[] args) {
		MenuFactory.run();
	}
}
