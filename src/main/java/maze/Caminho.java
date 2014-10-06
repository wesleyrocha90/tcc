package maze;

import algoritmo.Gene;
import algoritmo.Individuo;
import java.util.ArrayList;

/**
 *
 * @author Wesley
 */
public class Caminho {

  private Individuo individuo;

  public Caminho(Individuo individuo) {
    this.individuo = individuo;
  }

  public ArrayList<Gene> getDirecoes() {
    return this.individuo.getCromossomos();
  }
}
