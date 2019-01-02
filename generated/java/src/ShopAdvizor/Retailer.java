package ShopAdvizor;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Retailer {
  public String m_name;
  public VDMMap m_items = MapUtil.map();

  public void cg_init_Retailer_1(final String name) {

    m_name = name;
    return;
  }

  public Retailer(final String name) {

    cg_init_Retailer_1(name);
  }

  public void insertProduct(final Product product, final ItemInfo info) {

    m_items =
        MapUtil.override(Utils.copy(m_items), MapUtil.map(new Maplet(product, Utils.copy(info))));
  }

  public Retailer() {}

  public String toString() {

    return "Retailer{"
        + "m_name := "
        + Utils.toString(m_name)
        + ", m_items := "
        + Utils.toString(m_items)
        + "}";
  }

  public static class ItemInfo implements Record {
    public Number stock;
    public Number price;

    public ItemInfo(final Number _stock, final Number _price) {

      stock = _stock;
      price = _price;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof ItemInfo)) {
        return false;
      }

      ItemInfo other = ((ItemInfo) obj);

      return (Utils.equals(stock, other.stock)) && (Utils.equals(price, other.price));
    }

    public int hashCode() {

      return Utils.hashCode(stock, price);
    }

    public ItemInfo copy() {

      return new ItemInfo(stock, price);
    }

    public String toString() {

      return "mk_Retailer`ItemInfo" + Utils.formatFields(stock, price);
    }
  }
}
