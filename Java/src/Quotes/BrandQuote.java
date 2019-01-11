package Quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class BrandQuote {
  private static int hc = 0;
  private static BrandQuote instance = null;

  public BrandQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static BrandQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new BrandQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof BrandQuote;
  }

  public String toString() {

    return "<Brand>";
  }
}
