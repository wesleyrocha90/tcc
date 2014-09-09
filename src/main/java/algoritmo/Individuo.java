package algoritmo;

import java.util.ArrayList;

/**
 *
 * @author Wesley
 */
public interface Individuo {
  public int getAvaliacao();
  public void avaliaIndividuo();
  public ArrayList<Gene> getCromossomos();
}
