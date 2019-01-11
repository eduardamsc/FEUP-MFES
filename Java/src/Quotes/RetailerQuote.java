package Quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class RetailerQuote {
  private static int hc = 0;
  private static RetailerQuote instance = null;

  public RetailerQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static RetailerQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new RetailerQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof RetailerQuote;
  }

  public String toString() {

    return "<Retailer>";
  }
}
