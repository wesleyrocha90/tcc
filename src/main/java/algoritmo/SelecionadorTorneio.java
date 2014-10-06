package algoritmo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Wesley
 */
public class SelecionadorTorneio implements Selecionador{
  private int numeroParticipantes;

  public int getNumeroParticipantes() {
    return numeroParticipantes;
  }

  public void setNumeroParticipantes(int numeroParticipantes) {
    this.numeroParticipantes = numeroParticipantes;
  }
  
  @Override
  public List<? extends Individuo> selecionar(List<? extends Individuo> populacao, int quantidadeEleitos) {
    for (int i = 0; i < quantidadeEleitos; i++) {
      List<Individuo> participantes = new ArrayList<>();
      for (int j = 0; j < numeroParticipantes; j++) {
        int selecao = new Random().nextInt(populacao.size());
        participantes.add(populacao.get(selecao));
      }
      
    }
    return null;
  }
}