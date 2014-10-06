package algoritmo.comportamento;

import algoritmo.Individuo;
import algoritmo.IndividuoSimples;
import java.util.Random;

/**
 *
 * @author Wesley
 */
public class OperadorCrossoverDoisPontos implements OperadorCrossover{
  
  @Override
  public Individuo aplicarOperador(Individuo individuo1, Individuo individuo2) {
    int pontoCorte1 = new Random().nextInt(individuo1.getCromossomos().size());
    int pontoCorte2 = new Random().nextInt(individuo1.getCromossomos().size());
    if (pontoCorte2 < pontoCorte1){
      int aux = pontoCorte1;
      pontoCorte1 = pontoCorte2;
      pontoCorte2 = aux;
    }
    Individuo novoIndividuo = new IndividuoSimples(individuo1.getCromossomos().size(), false);
    for (int i = 0; i < pontoCorte1; i++) {
      novoIndividuo.getCromossomos().add(individuo1.getCromossomos().get(i));
    }
    for (int i = pontoCorte1; i < pontoCorte2; i++) {
      novoIndividuo.getCromossomos().add(individuo2.getCromossomos().get(i));
    }
    for (int i = pontoCorte2; i < individuo2.getCromossomos().size(); i++) {
      novoIndividuo.getCromossomos().add(individuo2.getCromossomos().get(i));
    }
    return novoIndividuo;
  }
}
