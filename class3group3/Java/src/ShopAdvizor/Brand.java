package ShopAdvizor;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Brand {
  public String m_name;

  public void cg_init_Brand_1(final String name) {

    m_name = name;
    return;
  }

  public Brand(final String name) {

    cg_init_Brand_1(name);
  }

  public Brand() {}

  public String toString() {

    return "Brand{" + "m_name := " + Utils.toString(m_name) + "}";
  }
}
