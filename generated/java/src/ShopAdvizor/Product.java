package ShopAdvizor;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Product {
  public static final VDMSet unitValues = SetUtil.set("Kg", "g", "l", "ml", "%", "units");
  public Number m_id;
  private static Number m_next_id = 1L;
  public String m_name;
  public String m_description;
  public Brand m_brand;
  public VDMMap m_ingredients;
  public VDMMap m_reviews;

  public void cg_init_Product_1(final String name, final String description, final Brand brand) {

    m_id = Product.m_next_id;
    m_next_id = Product.m_next_id.longValue() + 1L;
    m_name = name;
    m_description = description;
    m_brand = brand;
    m_ingredients = MapUtil.map();
    m_reviews = MapUtil.map();
    return;
  }

  public Product(final String name, final String description, final Brand brand) {

    cg_init_Product_1(name, description, brand);
  }

  public Number getAvgRating() {

    Number sum = 0L;
    Number num_reviews = MapUtil.rng(Utils.copy(m_reviews)).size();
    if (Utils.equals(num_reviews, 0L)) {
      return null;
    }

    for (Iterator iterator_20 = MapUtil.rng(Utils.copy(m_reviews)).iterator();
        iterator_20.hasNext();
        ) {
      Review review = (Review) iterator_20.next();
      sum = sum.longValue() + review.rating.longValue();
    }
    return Utils.divide((1.0 * sum.longValue()), num_reviews.longValue());
  }

  public void insertReview(final User user, final Review review) {

    m_reviews =
        MapUtil.override(Utils.copy(m_reviews), MapUtil.map(new Maplet(user, Utils.copy(review))));
  }

  public void removeReview(final User user) {

    m_reviews = MapUtil.domResBy(SetUtil.set(user), Utils.copy(m_reviews));
  }

  public void insertIngredient(final String ingredient, final Composition composition) {

    m_ingredients =
        MapUtil.override(
            Utils.copy(m_ingredients),
            MapUtil.map(new Maplet(ingredient, Utils.copy(composition))));
  }

  public Product() {}

  public String toString() {

    return "Product{"
        + "unitValues = "
        + Utils.toString(unitValues)
        + ", m_id := "
        + Utils.toString(m_id)
        + ", m_next_id := "
        + Utils.toString(m_next_id)
        + ", m_name := "
        + Utils.toString(m_name)
        + ", m_description := "
        + Utils.toString(m_description)
        + ", m_brand := "
        + Utils.toString(m_brand)
        + ", m_ingredients := "
        + Utils.toString(m_ingredients)
        + ", m_reviews := "
        + Utils.toString(m_reviews)
        + "}";
  }

  public static class Composition implements Record {
    public Number quantity;
    public String unit;

    public Composition(final Number _quantity, final String _unit) {

      quantity = _quantity;
      unit = _unit != null ? _unit : null;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Composition)) {
        return false;
      }

      Composition other = ((Composition) obj);

      return (Utils.equals(quantity, other.quantity)) && (Utils.equals(unit, other.unit));
    }

    public int hashCode() {

      return Utils.hashCode(quantity, unit);
    }

    public Composition copy() {

      return new Composition(quantity, unit);
    }

    public String toString() {

      return "mk_Product`Composition" + Utils.formatFields(quantity, unit);
    }
  }

  public static Boolean inv_Composition(final Composition c) {

    return SetUtil.inSet(c.unit, Product.unitValues);
  }

  public static class Review implements Record {
    public Number rating;
    public String feedback;

    public Review(final Number _rating, final String _feedback) {

      rating = _rating;
      feedback = _feedback != null ? _feedback : null;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Review)) {
        return false;
      }

      Review other = ((Review) obj);

      return (Utils.equals(rating, other.rating)) && (Utils.equals(feedback, other.feedback));
    }

    public int hashCode() {

      return Utils.hashCode(rating, feedback);
    }

    public Review copy() {

      return new Review(rating, feedback);
    }

    public String toString() {

      return "mk_Product`Review" + Utils.formatFields(rating, feedback);
    }
  }

  public static Boolean inv_Review(final Review review) {

    Boolean andResult_7 = false;

    if (review.rating.longValue() >= 1L) {
      if (review.rating.longValue() <= 5L) {
        andResult_7 = true;
      }
    }

    return andResult_7;
  }
}
