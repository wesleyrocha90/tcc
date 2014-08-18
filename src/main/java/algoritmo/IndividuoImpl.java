package algoritmo;

import java.util.ArrayList;

/**
 *
 * @author Wesley
 */
public class IndividuoImpl implements Individuo{
  private ArrayList<Gene> cromossomo;
  private int avaliacao;
  private int tamanhoCromossomo;
  
  @Override
  public int getAvaliacao(){
    return this.avaliacao;
  }
  
  public IndividuoImpl(int tamanhoCromossomo){
    this.tamanhoCromossomo = tamanhoCromossomo;
    cromossomo = new ArrayList<>(tamanhoCromossomo);
    for (int i = 0; i < tamanhoCromossomo; i++) {
      cromossomo.add(GeneDirecao.getRandomGene());
    }
    avaliacao = 0;
  }
  
  @Override
  public void avaliaIndividuo(){
    int nCima, nEsquerda, nBaixo, nDireita;
    nCima = nEsquerda = nBaixo = nDireita = 0;
    for (Gene gene : cromossomo) {
      switch(gene.toString()){
        case "CIMA":
          nCima++;
          break;
        case "ESQUERDA":
          nEsquerda++;
          break;
        case "BAIXO":
          nBaixo++;
          break;
        case "DIREITA":
          nDireita++;
          break;
      }
    }
    this.avaliacao = ( nCima * 4 + nEsquerda * 3 + nBaixo * 2 + nDireita * 1 );
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
}