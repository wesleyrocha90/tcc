package algoritmo.comportamento;

import algoritmo.Individuo;

/**
 *
 * @author Wesley
 */
public interface OperadorCrossover {
  public Individuo aplicarOperador(Individuo individuo1, Individuo individuo2);
}
