package algoritmo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Wesley
 */
public class SelecionadorSimples implements Selecionador {

  @Override
  public List<? extends Individuo> selecionar(List<? extends Individuo> populacao, int quantidadeEleitos) {
    List<Individuo> eleitos = new ArrayList<>();
    for (Individuo individuo : populacao) {
      eleitos.add(individuo);
    }
    return eleitos;
  }
}
