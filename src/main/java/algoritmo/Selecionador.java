package algoritmo;

import java.util.List;

/**
 * Interface que define um método para se efetuar a seleção dos indivíduos
 * 
 * @author Wesley
 */
public interface Selecionador {
  /**
   * Método responsável por implementar o algoritmo de seleção dos melhores indivíduos
   * @param populacao A população de indivíduos de onde os melhores serão selecionados
   * @param quantidadeEleitos A quantidade de indivíduos que devem ser selecionados
   * @return Uma coleção com os indivíduos que foram selecionados pelo método
   */
  public List<? extends Individuo> selecionar(List<? extends Individuo> populacao, int quantidadeEleitos);
}
