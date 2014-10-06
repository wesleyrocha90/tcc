package algoritmo;

import java.util.ArrayList;

/**
 *
 * @author Wesley
 */
public interface Individuo {

  public double getAvaliacao();

  public void setAvalicao(double i);

  public ArrayList<Gene> getCromossomos();
}
