package ShopAdvizor;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  public String m_username;
  public Object m_type;
  private String m_password;

  public void cg_init_User_2(final String username, final String password, final Object type) {

    m_username = username;
    m_password = password;
    m_type = type;
    return;
  }

  public void cg_init_User_1(final String username, final String password) {

    m_username = username;
    m_password = password;
    m_type = Quotes.NormalQuote.getInstance();
    return;
  }

  public User(final String username, final String password) {

    cg_init_User_1(username, password);
  }

  public User(final String username, final String password, final Object type) {

    cg_init_User_2(username, password, type);
  }

  public Boolean passwordMatches(final String password) {

    return Utils.equals(m_password, password);
  }

  public User() {}

  public String toString() {

    return "User{"
        + "m_username := "
        + Utils.toString(m_username)
        + ", m_type := "
        + Utils.toString(m_type)
        + ", m_password := "
        + Utils.toString(m_password)
        + "}";
  }
}
