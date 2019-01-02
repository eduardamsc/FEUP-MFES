package ShopAdvizor;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ShopAdvizor {
  public VDMSet m_products;
  public VDMSet m_users;
  public VDMSet m_retailers;
  public VDMSet m_brands;
  public VDMSet m_activities;
  private User m_user;

  public void cg_init_ShopAdvizor_1() {

    m_products = SetUtil.set();
    m_users = SetUtil.set();
    m_retailers = SetUtil.set();
    m_brands = SetUtil.set();
    m_activities = SetUtil.set();
    m_user = null;
    return;
  }

  public ShopAdvizor() {

    cg_init_ShopAdvizor_1();
  }

  public Brand getBrand(final String name) {

    Brand brand = null;
    Long exists1Counter_1 = 0L;
    VDMSet set_1 = Utils.copy(m_brands);
    for (Iterator iterator_1 = set_1.iterator();
        iterator_1.hasNext() && (exists1Counter_1.longValue() < 2L);
        ) {
      Brand b = ((Brand) iterator_1.next());
      if (Utils.equals(b.m_name, name)) {
        exists1Counter_1++;
      }
    }
    if (Utils.equals(exists1Counter_1, 1L)) {
      Brand iotaExp_1 = null;
      Long iotaCounter_1 = 0L;
      VDMSet set_2 = Utils.copy(m_brands);
      for (Iterator iterator_2 = set_2.iterator(); iterator_2.hasNext(); ) {
        Brand b = ((Brand) iterator_2.next());
        if (Utils.equals(b.m_name, name)) {
          iotaCounter_1++;
          if (iotaCounter_1.longValue() > 1L) {
            throw new RuntimeException("Iota selects more than one result");
          } else {
            iotaExp_1 = b;
          }
        }
      }
      if (Utils.equals(iotaCounter_1, 0L)) {
        throw new RuntimeException("Iota selects more than one result");
      }

      brand = iotaExp_1;

      return brand;

    } else {
      return null;
    }
  }

  public Retailer getRetailer(final String name) {

    Retailer retailer = null;
    Long exists1Counter_2 = 0L;
    VDMSet set_3 = Utils.copy(m_retailers);
    for (Iterator iterator_3 = set_3.iterator();
        iterator_3.hasNext() && (exists1Counter_2.longValue() < 2L);
        ) {
      Retailer r = ((Retailer) iterator_3.next());
      if (Utils.equals(r.m_name, name)) {
        exists1Counter_2++;
      }
    }
    if (Utils.equals(exists1Counter_2, 1L)) {
      Retailer iotaExp_2 = null;
      Long iotaCounter_2 = 0L;
      VDMSet set_4 = Utils.copy(m_retailers);
      for (Iterator iterator_4 = set_4.iterator(); iterator_4.hasNext(); ) {
        Retailer r = ((Retailer) iterator_4.next());
        if (Utils.equals(r.m_name, name)) {
          iotaCounter_2++;
          if (iotaCounter_2.longValue() > 1L) {
            throw new RuntimeException("Iota selects more than one result");
          } else {
            iotaExp_2 = r;
          }
        }
      }
      if (Utils.equals(iotaCounter_2, 0L)) {
        throw new RuntimeException("Iota selects more than one result");
      }

      retailer = iotaExp_2;

      return retailer;

    } else {
      return null;
    }
  }

  public User getUser(final String name) {

    User user = null;
    Long exists1Counter_3 = 0L;
    VDMSet set_5 = Utils.copy(m_users);
    for (Iterator iterator_5 = set_5.iterator();
        iterator_5.hasNext() && (exists1Counter_3.longValue() < 2L);
        ) {
      User u = ((User) iterator_5.next());
      if (Utils.equals(u.m_username, name)) {
        exists1Counter_3++;
      }
    }
    if (Utils.equals(exists1Counter_3, 1L)) {
      User iotaExp_3 = null;
      Long iotaCounter_3 = 0L;
      VDMSet set_6 = Utils.copy(m_users);
      for (Iterator iterator_6 = set_6.iterator(); iterator_6.hasNext(); ) {
        User u = ((User) iterator_6.next());
        if (Utils.equals(u.m_username, name)) {
          iotaCounter_3++;
          if (iotaCounter_3.longValue() > 1L) {
            throw new RuntimeException("Iota selects more than one result");
          } else {
            iotaExp_3 = u;
          }
        }
      }
      if (Utils.equals(iotaCounter_3, 0L)) {
        throw new RuntimeException("Iota selects more than one result");
      }

      user = iotaExp_3;

      return user;

    } else {
      return null;
    }
  }

  public Activity getActivity(final String title) {

    Activity activity = null;
    Long exists1Counter_4 = 0L;
    VDMSet set_7 = Utils.copy(m_activities);
    for (Iterator iterator_7 = set_7.iterator();
        iterator_7.hasNext() && (exists1Counter_4.longValue() < 2L);
        ) {
      Activity a = ((Activity) iterator_7.next());
      if (Utils.equals(a.m_title, title)) {
        exists1Counter_4++;
      }
    }
    if (Utils.equals(exists1Counter_4, 1L)) {
      Activity iotaExp_4 = null;
      Long iotaCounter_4 = 0L;
      VDMSet set_8 = Utils.copy(m_activities);
      for (Iterator iterator_8 = set_8.iterator(); iterator_8.hasNext(); ) {
        Activity a = ((Activity) iterator_8.next());
        if (Utils.equals(a.m_title, title)) {
          iotaCounter_4++;
          if (iotaCounter_4.longValue() > 1L) {
            throw new RuntimeException("Iota selects more than one result");
          } else {
            iotaExp_4 = a;
          }
        }
      }
      if (Utils.equals(iotaCounter_4, 0L)) {
        throw new RuntimeException("Iota selects more than one result");
      }

      activity = iotaExp_4;

      return activity;

    } else {
      return null;
    }
  }

  public Product getProduct(final String name, final Brand brand) {

    Product product = null;
    Long exists1Counter_5 = 0L;
    VDMSet set_9 = Utils.copy(m_products);
    for (Iterator iterator_9 = set_9.iterator();
        iterator_9.hasNext() && (exists1Counter_5.longValue() < 2L);
        ) {
      Product p = ((Product) iterator_9.next());
      Boolean andResult_9 = false;

      if (Utils.equals(p.m_name, name)) {
        if (Utils.equals(p.m_brand, brand)) {
          andResult_9 = true;
        }
      }

      if (andResult_9) {
        exists1Counter_5++;
      }
    }
    if (Utils.equals(exists1Counter_5, 1L)) {
      Product iotaExp_5 = null;
      Long iotaCounter_5 = 0L;
      VDMSet set_10 = Utils.copy(m_products);
      for (Iterator iterator_10 = set_10.iterator(); iterator_10.hasNext(); ) {
        Product p = ((Product) iterator_10.next());
        Boolean andResult_10 = false;

        if (Utils.equals(p.m_name, name)) {
          if (Utils.equals(p.m_brand, brand)) {
            andResult_10 = true;
          }
        }

        if (andResult_10) {
          iotaCounter_5++;
          if (iotaCounter_5.longValue() > 1L) {
            throw new RuntimeException("Iota selects more than one result");
          } else {
            iotaExp_5 = p;
          }
        }
      }
      if (Utils.equals(iotaCounter_5, 0L)) {
        throw new RuntimeException("Iota selects more than one result");
      }

      product = iotaExp_5;

      return product;

    } else {
      return null;
    }
  }

  public Number getProductAvgRating(final String product_name, final String brand_name) {

    Brand brand = getBrand(brand_name);
    Product product = null;
    if (Utils.equals(brand, null)) {
      return null;
    }

    product = getProduct(product_name, brand);
    if (Utils.equals(product, null)) {
      return null;

    } else {
      return product.getAvgRating();
    }
  }

  public Boolean insertReview(
      final String product_name,
      final String brand_name,
      final Number rating,
      final String feedback) {

    Brand brand = getBrand(brand_name);
    Product product = null;
    requireUserToBe(Quotes.NormalQuote.getInstance());
    if (Utils.equals(brand, null)) {
      return false;
    }

    product = getProduct(product_name, brand);
    if (Utils.equals(product, null)) {
      return false;

    } else {
      product.insertReview(m_user, new Product.Review(rating, feedback));
      return true;
    }
  }

  public VDMMap getProductReviews(final String product_name, final String brand_name) {

    Brand brand = getBrand(brand_name);
    Product product = null;
    if (Utils.equals(brand, null)) {
      return null;
    }

    product = getProduct(product_name, brand);
    if (Utils.equals(product, null)) {
      return null;

    } else {
      return product.m_reviews;
    }
  }

  public VDMSet getUserReviews(final String username) {

    User user = getUser(username);
    VDMSet reviews = null;
    if (Utils.equals(user, null)) {
      return null;

    } else {
      VDMSet setCompResult_1 = SetUtil.set();
      VDMSet set_11 = Utils.copy(m_products);
      for (Iterator iterator_11 = set_11.iterator(); iterator_11.hasNext(); ) {
        Product r = ((Product) iterator_11.next());
        VDMSet setCompResult_2 = SetUtil.set();
        VDMSet set_12 = MapUtil.dom(r.m_reviews);
        for (Iterator iterator_12 = set_12.iterator(); iterator_12.hasNext(); ) {
          User u = ((User) iterator_12.next());
          if (Utils.equals(u, user)) {
            setCompResult_2.add(
                Tuple.mk_(r, Utils.copy(((Product.Review) Utils.get(r.m_reviews, user)))));
          }
        }
        setCompResult_1.add(Utils.copy(setCompResult_2));
      }
      reviews = SetUtil.dunion(Utils.copy(setCompResult_1));

      return Utils.copy(reviews);
    }
  }

  public Tuple getLowestPriceRetailer(final Product product) {

    Tuple retailer = null;
    VDMSet retailers = null;
    if (!(SetUtil.inSet(product, m_products))) {
      return null;

    } else {
      VDMSet setCompResult_3 = SetUtil.set();
      VDMSet set_13 = Utils.copy(m_retailers);
      for (Iterator iterator_13 = set_13.iterator(); iterator_13.hasNext(); ) {
        Retailer r = ((Retailer) iterator_13.next());
        if (SetUtil.inSet(product, MapUtil.dom(r.m_items))) {
          setCompResult_3.add(
              Tuple.mk_(r, ((Retailer.ItemInfo) Utils.get(r.m_items, product)).price));
        }
      }
      retailers = Utils.copy(setCompResult_3);

      Boolean existsExpResult_1 = false;
      VDMSet set_14 = Utils.copy(retailers);
      for (Iterator iterator_14 = set_14.iterator();
          iterator_14.hasNext() && !(existsExpResult_1);
          ) {
        Tuple r1 = ((Tuple) iterator_14.next());
        Boolean forAllExpResult_1 = true;
        VDMSet set_15 = Utils.copy(retailers);
        for (Iterator iterator_15 = set_15.iterator();
            iterator_15.hasNext() && forAllExpResult_1;
            ) {
          Tuple r2 = ((Tuple) iterator_15.next());
          forAllExpResult_1 =
              ((Number) r1.get(1)).doubleValue() <= ((Number) r2.get(1)).doubleValue();
        }
        existsExpResult_1 = forAllExpResult_1;
      }
      if (existsExpResult_1) {
        Tuple iotaExp_6 = null;
        Long iotaCounter_6 = 0L;
        VDMSet set_16 = Utils.copy(retailers);
        for (Iterator iterator_16 = set_16.iterator(); iterator_16.hasNext(); ) {
          Tuple r1 = ((Tuple) iterator_16.next());
          Boolean forAllExpResult_2 = true;
          VDMSet set_17 = Utils.copy(retailers);
          for (Iterator iterator_17 = set_17.iterator();
              iterator_17.hasNext() && forAllExpResult_2;
              ) {
            Tuple r2 = ((Tuple) iterator_17.next());
            forAllExpResult_2 =
                ((Number) r1.get(1)).doubleValue() <= ((Number) r2.get(1)).doubleValue();
          }
          if (forAllExpResult_2) {
            iotaCounter_6++;
            if (iotaCounter_6.longValue() > 1L) {
              throw new RuntimeException("Iota selects more than one result");
            } else {
              iotaExp_6 = Utils.copy(r1);
            }
          }
        }
        if (Utils.equals(iotaCounter_6, 0L)) {
          throw new RuntimeException("Iota selects more than one result");
        }

        retailer = Utils.copy(iotaExp_6);

        return Utils.copy(retailer);

      } else {
        return null;
      }
    }
  }

  public void insertProduct(final Product product) {

    requireUserToBe(Quotes.BrandQuote.getInstance());
    requireSameUsername(product.m_brand.m_name);
    m_products = SetUtil.union(Utils.copy(m_products), SetUtil.set(product));
  }

  public void insertUser(final User user) {

    m_users = SetUtil.union(Utils.copy(m_users), SetUtil.set(user));
  }

  public void insertRetailer(final Retailer retailer) {

    requireUserToBe(Quotes.RetailerQuote.getInstance());
    requireSameUsername(retailer.m_name);
    m_retailers = SetUtil.union(Utils.copy(m_retailers), SetUtil.set(retailer));
  }

  public void insertActivity(final Activity activity) {

    requireUserToBe(Quotes.BrandQuote.getInstance());
    requireSameUsername(activity.m_brand.m_name);
    m_activities = SetUtil.union(Utils.copy(m_activities), SetUtil.set(activity));
  }

  public void insertBrand(final Brand brand) {

    requireUserToBe(Quotes.BrandQuote.getInstance());
    requireSameUsername(brand.m_name);
    m_brands = SetUtil.union(Utils.copy(m_brands), SetUtil.set(brand));
  }

  public VDMMap insertProductToRetailer(
      final String retailer_name,
      final String product_name,
      final String brand_name,
      final Number stock,
      final Number price) {

    Brand brand = getBrand(brand_name);
    Retailer retailer = getRetailer(retailer_name);
    Product product = null;
    requireUserToBe(Quotes.RetailerQuote.getInstance());
    if (Utils.equals(brand, null)) {
      return null;
    }

    product = getProduct(product_name, brand);
    Boolean orResult_1 = false;

    if (Utils.equals(product, null)) {
      orResult_1 = true;
    } else {
      orResult_1 = Utils.equals(retailer, null);
    }

    if (orResult_1) {
      return null;

    } else {
      requireSameUsername(retailer.m_name);
      retailer.insertProduct(product, new Retailer.ItemInfo(stock, price));
      return retailer.m_items;
    }
  }

  public User login(final String username, final String password) {

    Long exists1Counter_6 = 0L;
    VDMSet set_18 = Utils.copy(m_users);
    for (Iterator iterator_18 = set_18.iterator();
        iterator_18.hasNext() && (exists1Counter_6.longValue() < 2L);
        ) {
      User u = ((User) iterator_18.next());
      Boolean andResult_14 = false;

      if (Utils.equals(u.m_username, username)) {
        if (u.passwordMatches(password)) {
          andResult_14 = true;
        }
      }

      if (andResult_14) {
        exists1Counter_6++;
      }
    }
    if (Utils.equals(exists1Counter_6, 1L)) {
      User iotaExp_7 = null;
      Long iotaCounter_7 = 0L;
      VDMSet set_19 = Utils.copy(m_users);
      for (Iterator iterator_19 = set_19.iterator(); iterator_19.hasNext(); ) {
        User u = ((User) iterator_19.next());
        Boolean andResult_15 = false;

        if (Utils.equals(u.m_username, username)) {
          if (u.passwordMatches(password)) {
            andResult_15 = true;
          }
        }

        if (andResult_15) {
          iotaCounter_7++;
          if (iotaCounter_7.longValue() > 1L) {
            throw new RuntimeException("Iota selects more than one result");
          } else {
            iotaExp_7 = u;
          }
        }
      }
      if (Utils.equals(iotaCounter_7, 0L)) {
        throw new RuntimeException("Iota selects more than one result");
      }

      m_user = iotaExp_7;

      return m_user;

    } else {
      return null;
    }
  }

  public void logout() {

    m_user = null;
  }

  private void requireUserToBe(final Object type) {

    return;
  }

  private void requireSameUsername(final String name) {

    return;
  }

  public void startActivity(final String title) {

    Activity activity = getActivity(title);
    requireUserToBe(Quotes.BrandQuote.getInstance());
    if (!(Utils.equals(activity, null))) {
      requireSameUsername(activity.m_brand.m_name);
      activity.startActivity();
    }
  }

  public void endActivity(final String title) {

    Activity activity = getActivity(title);
    requireUserToBe(Quotes.BrandQuote.getInstance());
    if (!(Utils.equals(activity, null))) {
      requireSameUsername(activity.m_brand.m_name);
      activity.endActivity();
    }
  }

  public void addCompetitor(final String title) {

    Activity activity = getActivity(title);
    Competition competition = null;
    requireUserToBe(Quotes.NormalQuote.getInstance());
    Boolean andResult_18 = false;

    if (!(Utils.equals(activity, null))) {
      if (activity instanceof Competition) {
        andResult_18 = true;
      }
    }

    if (andResult_18) {
      competition = ((Competition) activity);
      competition.addCompetitor(m_user);
    }
  }

  public void chooseWinner(final String title, final String username) {

    Activity activity = getActivity(title);
    Competition competition = null;
    User user = getUser(username);
    requireUserToBe(Quotes.BrandQuote.getInstance());
    Boolean andResult_19 = false;

    if (!(Utils.equals(user, null))) {
      Boolean andResult_20 = false;

      if (!(Utils.equals(activity, null))) {
        if (activity instanceof Competition) {
          andResult_20 = true;
        }
      }

      if (andResult_20) {
        andResult_19 = true;
      }
    }

    if (andResult_19) {
      requireSameUsername(activity.m_brand.m_name);
      competition = ((Competition) activity);
      competition.chooseWinner(user);
    }
  }

  public String toString() {

    return "ShopAdvizor{"
        + "m_products := "
        + Utils.toString(m_products)
        + ", m_users := "
        + Utils.toString(m_users)
        + ", m_retailers := "
        + Utils.toString(m_retailers)
        + ", m_brands := "
        + Utils.toString(m_brands)
        + ", m_activities := "
        + Utils.toString(m_activities)
        + ", m_user := "
        + Utils.toString(m_user)
        + "}";
  }
}
