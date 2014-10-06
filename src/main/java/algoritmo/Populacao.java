package algoritmo;

import java.util.List;

/**
 *
 * @author Wesley
 */
public interface Populacao {

  public void inicializaPopulacao();

  public Populacao aplicarOperador();

  public void ordenaPorAvaliacao();

  public void ordenaInversoPorAvaliacao();

  public List<Individuo> getIndividuos();
  
  public void setQuantidadeEleitos(int quantidadeIndividuosEleitos);
}
