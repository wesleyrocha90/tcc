package algoritmo.comportamento;

import algoritmo.Individuo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe que implementa o método de seleção por torneio. Consiste em selecionar uma quantidade
 * de indivíduo que farão parte do torneio, o torneio consiste apenas em escolher dentre os 
 * participantes o que tem a melhor avaliação, esse vencedor é adicionado na lista de eleitos,
 * quando a quantidade de eleitos for atingida o método termina retornando a lista de eleitos
 * 
 * @author Wesley
 */
public class SelecionadorTorneio implements Selecionador{
  /**
   * A quantidade de indivíduos que participarão do torneio
   */
  private int numeroParticipantes = 3;

  public int getNumeroParticipantes() {
    return numeroParticipantes;
  }

  public void setNumeroParticipantes(int numeroParticipantes) {
    this.numeroParticipantes = numeroParticipantes;
  }
  
  @Override
  public List<? extends Individuo> selecionar(List<? extends Individuo> populacao, int quantidadeEleitos) {
    List<Individuo> eleitos = new ArrayList<>();
    for (int i = 0; i < quantidadeEleitos; i++) {
      List<Individuo> participantes = new ArrayList<>();
      for (int j = 0; j < numeroParticipantes; j++) {
        int selecao = new Random().nextInt(populacao.size());
        participantes.add(populacao.get(selecao));
      }
      Individuo melhor = null;
      for (Individuo participante : participantes) {
        if (melhor != null){
          if (participante.getAvaliacao() > melhor.getAvaliacao()){
            melhor = participante;
          }
        } else {
          melhor = participante;
        }
      }
      eleitos.add(melhor);
    }
    return null;
  }
}