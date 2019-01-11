package ShopAdvizor;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Activity {
  public String m_title;
  public String m_description;
  public Number m_prize;
  public Brand m_brand;
  public Boolean m_has_started = false;
  public Boolean m_has_ended = false;

  public void cg_init_Activity_1(
      final String title, final String description, final Number prize, final Brand brand) {

    m_title = title;
    m_description = description;
    m_prize = prize;
    m_brand = brand;
    m_has_started = false;
    m_has_ended = false;
    return;
  }

  public Activity(
      final String title, final String description, final Number prize, final Brand brand) {

    cg_init_Activity_1(title, description, prize, brand);
  }

  public void startActivity() {

    m_has_started = true;
  }

  public void endActivity() {

    m_has_ended = true;
  }

  public Activity() {}

  public String toString() {

    return "Activity{"
        + "m_title := "
        + Utils.toString(m_title)
        + ", m_description := "
        + Utils.toString(m_description)
        + ", m_prize := "
        + Utils.toString(m_prize)
        + ", m_brand := "
        + Utils.toString(m_brand)
        + ", m_has_started := "
        + Utils.toString(m_has_started)
        + ", m_has_ended := "
        + Utils.toString(m_has_ended)
        + "}";
  }
}
