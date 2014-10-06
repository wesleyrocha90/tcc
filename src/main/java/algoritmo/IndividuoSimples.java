package algoritmo;

import java.util.ArrayList;

/**
 *
 * @author Wesley
 */
public class IndividuoSimples implements Individuo {

  private ArrayList<Gene> cromossomo;
  private double avaliacao;
  private int tamanhoCromossomo;

  @Override
  public double getAvaliacao() {
    return this.avaliacao;
  }

  @Override
  public void setAvalicao(double avaliacao) {
    this.avaliacao = avaliacao;
  }
  
  /**
   * Cria um individuo simples com o tamanho especificado
   * @param tamanhoCromossomo a quantidade de genes que o indivíduo deverá ter
   * @param inicializa se o indivíduo deve ser inicializado com seus genes gerados aleatoriamente
   */
  public IndividuoSimples(int tamanhoCromossomo, boolean inicializa) {
    this.tamanhoCromossomo = tamanhoCromossomo;
    cromossomo = new ArrayList<>(tamanhoCromossomo);
    if (inicializa){
      for (int i = 0; i < tamanhoCromossomo; i++) {
        cromossomo.add(GeneDirecao.getRandomGene());
      }
    }
    avaliacao = 0;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("Individuo [ ");
    for (Gene gene : cromossomo) {
      s.append(gene.toString()).append(" ");
    }
    s.append("]-[");
    s.append(avaliacao);
    s.append("]");
    return s.toString();
  }

  @Override
  public ArrayList<Gene> getCromossomos() {
    return this.cromossomo;
  }
}