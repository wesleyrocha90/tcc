package algoritmo;

import java.util.Random;

/**
 *
 * @author Wesley
 */
public class OperadorCrossoverUmPonto implements OperadorGenetico{

  @Override
  public Individuo aplicarOperador(Individuo individuo1, Individuo individuo2) {
    int pontoCorte = new Random().nextInt(individuo1.getCromossomos().size());
    Individuo novoIndividuo = new IndividuoSimples(individuo1.getCromossomos().size(), false);
    for (int i = 0; i < pontoCorte; i++) {
      novoIndividuo.getCromossomos().add(individuo1.getCromossomos().get(i));
    }
    for (int i = pontoCorte; i < individuo2.getCromossomos().size(); i++) {
      novoIndividuo.getCromossomos().add(individuo2.getCromossomos().get(i));
    }
    return novoIndividuo;
  }
}
