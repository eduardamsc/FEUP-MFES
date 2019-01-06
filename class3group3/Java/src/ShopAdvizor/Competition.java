package ShopAdvizor;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Competition extends Activity {
  public VDMSet m_competitors;
  public User m_winner;

  public void cg_init_Competition_1(
      final String title, final String description, final Number prize, final Brand brand) {

    m_competitors = SetUtil.set();
    m_winner = null;
    cg_init_Activity_1(title, description, prize, brand);
  }

  public Competition(
      final String title, final String description, final Number prize, final Brand brand) {

    cg_init_Competition_1(title, description, prize, brand);
  }

  public void addCompetitor(final User user) {

    m_competitors = SetUtil.union(Utils.copy(m_competitors), SetUtil.set(user));
  }

  public void chooseWinner(final User user) {

    m_winner = user;
  }

  public Competition() {}

  public String toString() {

    return "Competition{"
        + "m_competitors := "
        + Utils.toString(m_competitors)
        + ", m_winner := "
        + Utils.toString(m_winner)
        + "}";
  }
}
