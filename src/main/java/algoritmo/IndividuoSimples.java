package algoritmo;

import java.util.ArrayList;

/**
 *
 * @author Wesley
 */
public class IndividuoSimples implements Individuo{
  private ArrayList<Gene> cromossomo;
  private int avaliacao;
  private int tamanhoCromossomo;
  
  @Override
  public int getAvaliacao(){
    return this.avaliacao;
  }
  
  public IndividuoSimples(int tamanhoCromossomo){
    this.tamanhoCromossomo = tamanhoCromossomo;
    cromossomo = new ArrayList<>(tamanhoCromossomo);
    for (int i = 0; i < tamanhoCromossomo; i++) {
      cromossomo.add(GeneDirecao.getRandomGene());
    }
    avaliacao = 0;
  }
  
  @Override
  public void avaliaIndividuo(){
    // coisa pra levar em conta na hora de avaliar o individuo
    // 1 - quantas vezes ele executa um movimento possivel - ganha nota positiva
    // 2 - quantas vezes ele se movimenta pra uma posicao impossivel de chegar - ganha nota negativa
    // 3 - quanto mais posicoes absolutas ele se distancia da posicao inicial - ganha nota positiva
    // 4 - quanto mais posicoes absolutas ele se aproxima da posicao final - ganha nota positiva
  }
  
  @Override
  public String toString(){
    StringBuilder s = new StringBuilder();
    s.append("Individuo [ ");
    for (Gene gene : cromossomo) {
      s.append(gene.toString()).append(" ");
    }
    s.append("]");
    return s.toString();
  }
  
  @Override
  public ArrayList<Gene> getCromossomos() {
    return this.cromossomo;
  }
}