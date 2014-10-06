package algoritmo.comportamento;

import algoritmo.Individuo;

public interface OperadorMutacao {
  public void mutar(Individuo individuo, double taxaMutacao);
}
