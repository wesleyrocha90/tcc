package algoritmo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wesley
 */
public class PopulacaoSimples implements Populacao {
  private int contadorGeracao;
  private int tamanhoIndividuo;
  private int tamanhoPopulacao;
  private ArrayList<Individuo> populacao;
  
  private int melhorAvaliacao;
  private final int melhorAvaliacaoPossivel = 60;
  
  public PopulacaoSimples(int tamanhoPopulacao, int tamanhoIndividuo){
    this.contadorGeracao = 0;
    this.tamanhoIndividuo = tamanhoIndividuo;
    this.tamanhoPopulacao = tamanhoPopulacao;
  }
  
  public void inicializaPopulacao(){
    populacao = new ArrayList<>();
    for (int i = 0; i < tamanhoPopulacao; i++) {
      populacao.add(new IndividuoSimples(tamanhoIndividuo));
    }
  }
  
  private void avaliaPopulacao(){
    for (Individuo individuo : populacao) {
      individuo.avaliaIndividuo();
    }
    this.ordenaPorAvaliacao();
  }
  
  private void ordenaPorAvaliacao(){
    for(int i = tamanhoPopulacao - 1; i >= 1; i--){
      for (int j = 0; j < i; j++) {
        if (populacao.get(j).getAvaliacao() < populacao.get(j+1).getAvaliacao()) {
          Individuo auxiliar = populacao.get(j);
          populacao.set(j, populacao.get(j+1));
          populacao.set(j+1, auxiliar);
        }
      }
    }
    melhorAvaliacao = populacao.get(0).getAvaliacao();
  }
  
  public void aplicarOperador(){
    
  }
  
  public void algoritmo(){
    this.inicializaPopulacao();
    this.avaliaPopulacao();
//    while(melhorAvaliacao < melhorAvaliacaoPossivel){
//      System.out.println("Geracao = " + contadorGeracao++);
//      this.aplicarOperador();
//      this.avaliaPopulacao();
//    }
  }
  
  @Override
  public String toString(){
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < tamanhoPopulacao; i++) {
      s.append(populacao.get(i).getAvaliacao()).append("\t").append(populacao.get(i)).append("\n");
    }
    return s.toString();
  }

  @Override
  public List<Individuo> getIndividuos() {
    return populacao;
  }
}
