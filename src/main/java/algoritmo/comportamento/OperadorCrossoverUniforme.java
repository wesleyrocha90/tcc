package algoritmo.comportamento;

import algoritmo.Individuo;
import algoritmo.IndividuoSimples;

/**
 *
 * @author Wesley
 */
public class OperadorCrossoverUniforme implements OperadorCrossover {

  @Override
  public Individuo aplicarOperador(Individuo individuo1, Individuo individuo2) {
    Individuo novoIndividuo = new IndividuoSimples(individuo1.getCromossomos().size(), false);
    for (int i = 0; i < individuo1.getCromossomos().size(); i++) {
      if (individuo1.getCromossomos().get(i).getGene().equals(individuo2.getCromossomos().get(i).getGene())) {
        novoIndividuo.getCromossomos().add(individuo1.getCromossomos().get(i));
      } else {
        if (Math.random() < 0.5) {
          novoIndividuo.getCromossomos().add(individuo1.getCromossomos().get(i));
        } else {
          novoIndividuo.getCromossomos().add(individuo2.getCromossomos().get(i));
        }
      }
    }
    return null;
  }
}
