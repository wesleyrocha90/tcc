package algoritmo.comportamento;

import algoritmo.Individuo;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que implementa o método de seleção simples, onde apenas os indivíduos 
 * com as melhores avaliação são escolhidos, sem a utilização de nenhum critério
 * além da nota da avaliação
 * 
 * @author Wesley
 */
public class SelecionadorSimples implements Selecionador {

  @Override
  public List<? extends Individuo> selecionar(List<? extends Individuo> populacao, int quantidadeEleitos) {
    List<Individuo> eleitos = new ArrayList<>();
    for (int i = 0; i < quantidadeEleitos; i++) {
      eleitos.add(populacao.get(i));
    }
    return eleitos;
  }
}
