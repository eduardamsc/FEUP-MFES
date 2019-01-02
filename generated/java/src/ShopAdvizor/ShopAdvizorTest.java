package ShopAdvizor;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ShopAdvizorTest {
  private static final User admin =
      new User("admin", "admin", Quotes.AdminQuote.getInstance());

  private void assertTrue(final Boolean cond) {

    return;
  }

  public void testAddProductToRetailer() {

    ShopAdvizor shopAdvizor = new ShopAdvizor();
    Brand brand = new Brand("Chip Mix");
    Product product = new Product("Bolachas", "Bolachas de Chocolate", brand);
    Retailer.ItemInfo info = new Retailer.ItemInfo(20L, 2.1);
    Retailer retailer = new Retailer("Pingo Doce");
    User user =
        new User("Pingo Doce", "pingo_doce", Quotes.RetailerQuote.getInstance());
    shopAdvizor.insertUser(ShopAdvizorTest.admin);
    assertTrue(Utils.equals(shopAdvizor.login("admin", "admin"), ShopAdvizorTest.admin));
    shopAdvizor.insertUser(user);
    shopAdvizor.insertBrand(brand);
    shopAdvizor.insertProduct(product);
    shopAdvizor.insertRetailer(retailer);
    shopAdvizor.logout();
    assertTrue(Utils.equals(shopAdvizor.login("Pingo Doce", "pingo_doce"), user));
    assertTrue(
        Utils.equals(
            shopAdvizor.insertProductToRetailer(
                "Pingo Azedo", "Bolachas", "Chip Mix", info.stock, info.price),
            null));
    assertTrue(
        Utils.equals(
            shopAdvizor.insertProductToRetailer(
                "Pingo Doce", "Bolachas", "Oreo", info.stock, info.price),
            null));
    assertTrue(
        Utils.equals(
            shopAdvizor.insertProductToRetailer(
                "Pingo Doce", "Detergente da Roupa", "Chip Mix", info.stock, info.price),
            null));
    assertTrue(
        Utils.equals(
            shopAdvizor.insertProductToRetailer(
                "Pingo Doce", "Bolachas", "Chip Mix", info.stock, info.price),
            MapUtil.map(new Maplet(product, Utils.copy(info)))));
  }

  public void testAverageReviewRating() {

    ShopAdvizor shopAdvizor = new ShopAdvizor();
    Brand brand = new Brand("Chip Mix");
    Product product = new Product("Bolachas", "Bolachas de Chocolate", brand);
    User u1 = new User("u1", "p1");
    User u2 = new User("u2", "p2");
    shopAdvizor.insertUser(ShopAdvizorTest.admin);
    shopAdvizor.insertUser(u1);
    shopAdvizor.insertUser(u2);
    assertTrue(Utils.equals(shopAdvizor.login("admin", "admin"), ShopAdvizorTest.admin));
    assertTrue(Utils.equals(shopAdvizor.getProductAvgRating("Bolachas", "Chip Mix"), null));
    shopAdvizor.insertBrand(brand);
    assertTrue(Utils.equals(shopAdvizor.getProductAvgRating("Bolachas", "Chip Mix"), null));
    shopAdvizor.insertProduct(product);
    shopAdvizor.logout();
    assertTrue(Utils.equals(shopAdvizor.getProductAvgRating("Bolachas", "Chip Mix"), null));
    assertTrue(Utils.equals(shopAdvizor.login("u1", "p1"), u1));
    assertTrue(Utils.equals(shopAdvizor.insertReview("Bolachas", "Oreos", 1L, "mau"), false));
    assertTrue(Utils.equals(shopAdvizor.insertReview("Bolachas", "Chip Mix", 1L, "mau"), true));
    assertTrue(Utils.equals(shopAdvizor.getProductAvgRating("Bolachas", "Chip Mix"), 1L));
    shopAdvizor.logout();
    assertTrue(Utils.equals(shopAdvizor.login("u2", "p2"), u2));
    assertTrue(Utils.equals(shopAdvizor.insertReview("Ketchup", "Chip Mix", 1L, "mau"), false));
    assertTrue(Utils.equals(shopAdvizor.insertReview("Bolachas", "Chip Mix", 4L, "bom"), true));
    assertTrue(Utils.equals(shopAdvizor.getProductAvgRating("Bolachas", "Chip Mix"), 2.5));
    assertTrue(Utils.equals(shopAdvizor.removeReview("Bolachas", "Oreos"), false));
    assertTrue(Utils.equals(shopAdvizor.removeReview("Ketchup", "Chip Mix"), false));
    assertTrue(Utils.equals(shopAdvizor.removeReview("Bolachas", "Chip Mix"), true));
    assertTrue(Utils.equals(shopAdvizor.getProductAvgRating("Bolachas", "Chip Mix"), 1L));
  }

  public void testReviews() {

    ShopAdvizor shopAdvizor = new ShopAdvizor();
    Brand b1 = new Brand("b1");
    Brand b2 = new Brand("b2");
    Product p1 = new Product("p1", "pd1", b1);
    Product p2 = new Product("p2", "pd2", b2);
    User u1 = new User("n1", "np1");
    User u2 = new User("n2", "np2");
    shopAdvizor.insertUser(ShopAdvizorTest.admin);
    assertTrue(Utils.equals(shopAdvizor.login("admin", "admin"), ShopAdvizorTest.admin));
    shopAdvizor.insertBrand(b1);
    shopAdvizor.insertBrand(b2);
    shopAdvizor.insertProduct(p1);
    shopAdvizor.insertProduct(p2);
    shopAdvizor.insertUser(u1);
    shopAdvizor.insertUser(u2);
    assertTrue(Utils.equals(shopAdvizor.getUserReviews("n3"), null));
    assertTrue(Utils.empty(shopAdvizor.getUserReviews("n1")));
    assertTrue(Utils.equals(shopAdvizor.getProductReviews("p3", "b1"), null));
    assertTrue(Utils.equals(shopAdvizor.getProductReviews("p1", "b3"), null));
    assertTrue(Utils.empty(shopAdvizor.getProductReviews("p1", "b1")));
    p1.insertReview(u1, new Product.Review(1L, "fb1"));
    assertTrue(
        Utils.equals(
            shopAdvizor.getUserReviews("n1"),
            SetUtil.set(Tuple.mk_(p1, new Product.Review(1L, "fb1")))));
    assertTrue(
        Utils.equals(
            shopAdvizor.getProductReviews("p1", "b1"),
            MapUtil.map(new Maplet(u1, new Product.Review(1L, "fb1")))));
    p1.insertReview(u2, new Product.Review(2L, "fb2"));
    assertTrue(
        Utils.equals(
            shopAdvizor.getUserReviews("n2"),
            SetUtil.set(Tuple.mk_(p1, new Product.Review(2L, "fb2")))));
    assertTrue(
        Utils.equals(
            shopAdvizor.getProductReviews("p1", "b1"),
            MapUtil.map(
                new Maplet(u1, new Product.Review(1L, "fb1")),
                new Maplet(u2, new Product.Review(2L, "fb2")))));
    p2.insertReview(u1, new Product.Review(5L, "fb1"));
    assertTrue(
        Utils.equals(
            shopAdvizor.getUserReviews("n1"),
            SetUtil.set(
                Tuple.mk_(p1, new Product.Review(1L, "fb1")),
                Tuple.mk_(p2, new Product.Review(5L, "fb1")))));
    assertTrue(
        Utils.equals(
            shopAdvizor.getProductReviews("p2", "b2"),
            MapUtil.map(new Maplet(u1, new Product.Review(5L, "fb1")))));
    p2.removeReview(u1);
    assertTrue(Utils.empty(shopAdvizor.getProductReviews("p2", "b2")));
    shopAdvizor.logout();
    assertTrue(Utils.equals(shopAdvizor.login("n1", "np1"), u1));
    assertTrue(Utils.equals(shopAdvizor.getUserReviews(), shopAdvizor.getUserReviews("n1")));
  }

  public void testLogin() {

    ShopAdvizor shopAdvizor = new ShopAdvizor();
    User u1 = new User("user", "pass");
    shopAdvizor.insertUser(u1);
    assertTrue(Utils.equals(shopAdvizor.login("user", "123"), null));
    assertTrue(Utils.equals(shopAdvizor.login("123", "pass"), null));
    assertTrue(Utils.equals(shopAdvizor.login("user", "pass"), u1));
    shopAdvizor.logout();
  }

  public void testLowestPrice() {

    ShopAdvizor shopAdvizor = new ShopAdvizor();
    Brand b1 = new Brand("b1");
    Brand b2 = new Brand("b2");
    Product p1 = new Product("p1", "pd1", b1);
    Product p2 = new Product("p2", "pd2", b1);
    Product p3 = new Product("p1", "pd3", b2);
    Product p4 = new Product("p2", "pd4", b2);
    Retailer r1 = new Retailer("r1");
    Retailer r2 = new Retailer("r2");
    shopAdvizor.insertUser(ShopAdvizorTest.admin);
    assertTrue(Utils.equals(shopAdvizor.login("admin", "admin"), ShopAdvizorTest.admin));
    shopAdvizor.insertBrand(b1);
    shopAdvizor.insertBrand(b2);
    shopAdvizor.insertProduct(p1);
    shopAdvizor.insertProduct(p2);
    shopAdvizor.insertProduct(p3);
    shopAdvizor.insertRetailer(r1);
    shopAdvizor.insertRetailer(r2);
    r1.insertProduct(p1, new Retailer.ItemInfo(25L, 2.5));
    r1.insertProduct(p2, new Retailer.ItemInfo(30L, 1.5));
    r2.insertProduct(p1, new Retailer.ItemInfo(25L, 2.0));
    assertTrue(Utils.equals(shopAdvizor.getLowestPriceRetailer(p1), Tuple.mk_(r2, 2.0)));
    assertTrue(Utils.equals(shopAdvizor.getLowestPriceRetailer(p2), Tuple.mk_(r1, 1.5)));
    assertTrue(Utils.equals(shopAdvizor.getLowestPriceRetailer(p3), null));
    assertTrue(Utils.equals(shopAdvizor.getLowestPriceRetailer(p4), null));
  }

  public void testCompetition() {

    ShopAdvizor shopAdvizor = new ShopAdvizor();
    User u1 = new User("u1", "p1");
    User u2 = new User("u2", "p2");
    User u3 = new User("u3", "p3");
    User bu = new User("Oreo", "oreo_pass", Quotes.BrandQuote.getInstance());
    Brand b = new Brand("Oreo");
    Brand b2 = new Brand("ChipMix");
    Competition c =
        new Competition("Competitive eating", "Eat as much cookies as possible", 20.0, b);
    Competition c2 =
        new Competition("Biggest buyer", "Buy the biggest ammount of cookies", 100.0, b2);
    assertTrue(Utils.equals(shopAdvizor.getActivity("Competitive eating"), null));
    shopAdvizor.insertUser(ShopAdvizorTest.admin);
    shopAdvizor.insertUser(bu);
    assertTrue(Utils.equals(shopAdvizor.login("admin", "admin"), ShopAdvizorTest.admin));
    shopAdvizor.insertBrand(b);
    shopAdvizor.insertBrand(b2);
    shopAdvizor.insertUser(u1);
    shopAdvizor.insertUser(u2);
    shopAdvizor.insertUser(u3);
    shopAdvizor.insertActivity(c);
    shopAdvizor.insertActivity(c2);
    shopAdvizor.logout();
    assertTrue(Utils.equals(shopAdvizor.login("u1", "p1"), u1));
    shopAdvizor.addCompetitor("Competitive eating");
    shopAdvizor.logout();
    assertTrue(Utils.equals(shopAdvizor.login("u2", "p2"), u2));
    shopAdvizor.addCompetitor("Competitive eating");
    shopAdvizor.logout();
    assertTrue(Utils.equals(shopAdvizor.login("u3", "p3"), u3));
    shopAdvizor.addCompetitor("Competitive eating");
    assertTrue(Utils.equals(c.m_competitors, SetUtil.set(u1, u2, u3)));
    shopAdvizor.logout();
    assertTrue(Utils.equals(shopAdvizor.login("Oreo", "oreo_pass"), bu));
    shopAdvizor.startActivity("Competitive eating");
    shopAdvizor.endActivity("Competitive eating");
    shopAdvizor.chooseWinner("Competitive eating", "u1");
    assertTrue(Utils.equals(c.m_winner, u1));
  }

  public void testIngredients() {

    ShopAdvizor shopAdvizor = new ShopAdvizor();
    Brand b = new Brand("Oreo");
    Product p = new Product("Cookies", "Chocolate cookies", b);
    p.insertIngredient("milk", new Product.Composition(20.0, "ml"));
    p.insertIngredient("chocolate", new Product.Composition(50.0, "g"));
    p.insertIngredient("sugar", new Product.Composition(5.0, "g"));
    shopAdvizor.insertUser(ShopAdvizorTest.admin);
    assertTrue(Utils.equals(shopAdvizor.login("admin", "admin"), ShopAdvizorTest.admin));
    shopAdvizor.insertBrand(b);
    shopAdvizor.insertProduct(p);
  }

  public static void main() {

    ShopAdvizorTest test = new ShopAdvizorTest();
    test.testAddProductToRetailer();
    test.testAverageReviewRating();
    test.testReviews();
    test.testLogin();
    test.testLowestPrice();
    test.testCompetition();
    test.testIngredients();
  }

  public ShopAdvizorTest() {}

  public String toString() {

    return "ShopAdvizorTest{" + "admin = " + Utils.toString(admin) + "}";
  }
}
