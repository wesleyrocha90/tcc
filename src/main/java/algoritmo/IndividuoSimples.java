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