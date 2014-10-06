package main;

import algoritmo.Individuo;
import algoritmo.Populacao;
import algoritmo.PopulacaoSimples;
import java.util.ArrayList;
import maze.Caminho;
import maze.Celula;
import maze.Labirinto;
import view.LabirintoPanel;

public class Maestro {

  private Labirinto labirinto;
  private LabirintoPanel labirintoPanel;
  private int maximoGeracoes;
  private int quantidadeEleitos;
  private ArrayList<Populacao> geracoes;
  private Populacao populacao;

  public Maestro() {
    geracoes = new ArrayList<>();
  }
  
  public void criarLabirinto(int tamanhoHorizontal, int tamanhoVertical, boolean inicioFimNasBordas) {
    labirinto = new Labirinto(tamanhoHorizontal, tamanhoVertical, inicioFimNasBordas);
  }

  public Labirinto getLabirinto() {
    return labirinto;
  }

  public void criarLabirintoPanel() {
    if (labirinto != null) {
      labirintoPanel = new LabirintoPanel(labirinto);
    }
  }

  public LabirintoPanel getLabirintoPanel() {
    return labirintoPanel;
  }
  
  public void setMaximoGeracoes(int maximoGeracoes){
    this.maximoGeracoes = maximoGeracoes;
  }
  
  public void setQuantidadeEleitos(int quantidadeEleitos){
    this.quantidadeEleitos = quantidadeEleitos;
    populacao.setQuantidadeEleitos(quantidadeEleitos);
  }

  public void criarPopulacao(int tamanhoPopulacao, int tamanhoIndividuo) {
    populacao = new PopulacaoSimples(tamanhoPopulacao, tamanhoIndividuo);
  }

  public void executarAlgoritmo() {
    populacao.inicializaPopulacao();
    avaliarPopulacao(populacao);
    geracoes.add(populacao);
    
    while(geracoes.size() < maximoGeracoes){
      Populacao novaPopulacao = populacao.aplicarOperador();
      avaliarPopulacao(novaPopulacao);
      geracoes.add(novaPopulacao);
    }
  }
  
  private void avaliarPopulacao(Populacao populacao){
    Celula celulaFimLabirinto = labirinto.getCelulaFim();
    for (Individuo individuo : populacao.getIndividuos()) {
      Caminho caminho = new Caminho(individuo);
      labirinto.aplicaCaminho(caminho);
      Celula celulaFimCaminho = labirinto.getCelulaFimCaminho();
      individuo.setAvalicao(labirinto.getDistancia(celulaFimCaminho, celulaFimLabirinto));
    }
    populacao.ordenaInversoPorAvaliacao();
  }

  public ArrayList<Populacao> getGeracoes() {
    return geracoes;
  }

  public void aplicaCaminhoLabirinto(Caminho caminho) {
    labirinto.aplicaCaminho(caminho);
  }

  public void pinturaInicialLabirintoPanel() {
    labirintoPanel.pintutaInicial();
  }

  public void repintaLabirintoPanel() {
    labirintoPanel.repinta();
  }

  public void limparGeracoes() {
    geracoes = new ArrayList<>();
  }
}
