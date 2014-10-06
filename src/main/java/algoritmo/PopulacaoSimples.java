package algoritmo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Wesley
 */
public class PopulacaoSimples implements Populacao {

  
  private int tamanhoIndividuo;
  private int tamanhoPopulacao;
  private int quantidadeIndividuosContinuam;
  private ArrayList<Individuo> populacao;
//  private int melhorAvaliacao;

  public PopulacaoSimples(int tamanhoPopulacao, int tamanhoIndividuo) {
    this.tamanhoIndividuo = tamanhoIndividuo;
    this.tamanhoPopulacao = tamanhoPopulacao;
  }

  private PopulacaoSimples(ArrayList<Individuo> populacao) {
    if(populacao != null && populacao.size() > 0){
      this.tamanhoIndividuo = populacao.get(0).getCromossomos().size();
      this.tamanhoPopulacao = populacao.size();
      this.populacao = new ArrayList<>();
      for (Individuo individuo : populacao) {
        this.populacao.add(individuo);
      }
    }
  }

  @Override
  public void setQuantidadeEleitos(int quantidadeIndividuosEleitos) {
    this.quantidadeIndividuosContinuam = quantidadeIndividuosEleitos;
  }

  @Override
  public void inicializaPopulacao() {
    populacao = new ArrayList<>();
    for (int i = 0; i < tamanhoPopulacao; i++) {
      populacao.add(new IndividuoSimples(tamanhoIndividuo, true));
    }
  }

  @Override
  public void ordenaPorAvaliacao() {
    for (int i = tamanhoPopulacao - 1; i >= 1; i--) {
      for (int j = 0; j < i; j++) {
        if (populacao.get(j).getAvaliacao() < populacao.get(j + 1).getAvaliacao()) {
          Individuo auxiliar = populacao.get(j);
          populacao.set(j, populacao.get(j + 1));
          populacao.set(j + 1, auxiliar);
        }
      }
    }
  }

  @Override
  public void ordenaInversoPorAvaliacao() {
    for (int i = tamanhoPopulacao - 1; i >= 1; i--) {
      for (int j = 0; j < i; j++) {
        if (populacao.get(j).getAvaliacao() > populacao.get(j + 1).getAvaliacao()) {
          Individuo auxiliar = populacao.get(j);
          populacao.set(j, populacao.get(j + 1));
          populacao.set(j + 1, auxiliar);
        }
      }
    }
  }

  @Override
  public Populacao aplicarOperador() {
    //nova populacao
    ArrayList<Individuo> novaPopulacao = new ArrayList<>(tamanhoPopulacao);

    //selecionar os melhores individuos
    //e mante-los na nova populacao
    List<Individuo> melhores = new ArrayList<>();
    melhores.addAll(this.selecionarMelhores());
    novaPopulacao.addAll(melhores);
    
    //aplica o operador genetico nos melhores individuos para gerar novos individuos ate
    //que a nova populacao tenha o mesmo tamanho da antiga
    while (novaPopulacao.size() < populacao.size()) {
      for (int i = 0; i < melhores.size(); i++) {
        for (int j = 0; j < melhores.size(); j++) {
          if (i == j) {
            continue;
          }
          Individuo novo = operadorCrossoverUmPonto(melhores.get(i), melhores.get(j));
          novaPopulacao.add(novo);
          if (novaPopulacao.size() == populacao.size()) {
            break;
          }
        }
        if (novaPopulacao.size() == populacao.size()) {
          break;
        }
      }
    }

    //a nova populacao substitui a antiga
    return new PopulacaoSimples(novaPopulacao);
  }
  
  private Collection<? extends Individuo> selecionarMelhores() {
    Collection<Individuo> melhores = new ArrayList<>();
    for (int i = 0; i < quantidadeIndividuosContinuam; i++) {
      melhores.add(populacao.get(i));
    }
    return melhores;
  }

  public Individuo operadorCrossoverUmPonto(Individuo individuo1, Individuo individuo2) {
    int pontoCorte = new Random().nextInt(individuo1.getCromossomos().size());
//    int pontoCorte = individuo1.getCromossomos().size() / 2;
    Individuo novoIndividuo = new IndividuoSimples(tamanhoIndividuo, false);
    for (int i = 0; i < pontoCorte; i++) {
      novoIndividuo.getCromossomos().add(individuo1.getCromossomos().get(i));
    }
    for (int i = pontoCorte; i < individuo2.getCromossomos().size(); i++) {
      novoIndividuo.getCromossomos().add(individuo2.getCromossomos().get(i));
    }
    return novoIndividuo;
  }

  @Override
  public String toString() {
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
