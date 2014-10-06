package algoritmo;

/**
 *
 * @author Wesley
 */
public class OperadorMutacaoSimples implements OperadorMutacao{

  @Override
  public void mutar(Individuo individuo, double taxaMutacao) {
    for (int i = 0; i < individuo.getCromossomos().size(); i++) {
      if (Math.random() < taxaMutacao){
        individuo.getCromossomos().set(i, GeneDirecao.getRandomGene());
      }
    }
  }
}
