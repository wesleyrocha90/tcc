package maze;

import algoritmo.Gene;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Wesley
 */
public class Labirinto {

  private int tamanhoHorizontal;
  private int tamanhoVertical;
  private boolean inicioFimNasBordas;
  private ArrayList<ArrayList<Celula>> matriz;

  public Labirinto(int tamanhoHorizontal, int tamanhoVertical) {
    this.tamanhoHorizontal = tamanhoHorizontal;
    this.tamanhoVertical = tamanhoVertical;

    this.gerarLabirinto();
  }
  
  public Labirinto(int tamanhoHorizontal, int tamanhoVertical, boolean inicioFimNasBordas) {
    this.tamanhoHorizontal = tamanhoHorizontal;
    this.tamanhoVertical = tamanhoVertical;
    this.inicioFimNasBordas = inicioFimNasBordas;
    
    this.gerarLabirinto();
  }

  private void gerarLabirinto() {
    this.matriz = new ArrayList<>();
    ArrayList<Celula> linhaMatriz;
    for (int i = 0; i < this.tamanhoVertical; i++) {
      linhaMatriz = new ArrayList<>();
      for (int j = 0; j < this.tamanhoHorizontal; j++) {
        linhaMatriz.add(new Celula("X", i, j, true, true, true, true));
      }
      this.matriz.add(linhaMatriz);
    }
    
    this.gerarCaminho();
    this.setInicioEFim();
  }
  
  private void gerarCaminho(){
    ArrayList<Ligacao> listaLigacoes = new ArrayList<>();
    
    Celula celula = this.getCelulaEm(0, 0);
    celula.setRotulo("0");
    listaLigacoes.addAll(this.getLigacoesCelula(celula));
    
    while( !listaLigacoes.isEmpty() ){
      Collections.shuffle(listaLigacoes);
      Ligacao ligacao = listaLigacoes.remove(0);
      
      Celula celulaDestino = ligacao.getCelulaDestino();
      
      if( !"0".equals(celulaDestino.getRotulo()) ){
        celulaDestino.setRotulo("0");
        ligacao.setPassagem();
        listaLigacoes.addAll(this.getLigacoesCelula(celulaDestino));
      }
      
    }
  }
  
  private void setInicioEFim(){
    Random random = new Random();
    int inicioLinha;
    int inicioColuna;
    int fimLinha;
    int fimColuna;
    if(inicioFimNasBordas){
      do{
        inicioLinha = Math.abs(random.nextInt()) % this.tamanhoVertical;
        inicioColuna = Math.abs(random.nextInt()) % this.tamanhoHorizontal;
      }while(inicioLinha != 0 && inicioColuna != 0);
      do{
        fimLinha = Math.abs(random.nextInt()) % this.tamanhoVertical;
        fimColuna = Math.abs(random.nextInt()) % this.tamanhoHorizontal;
      }while((fimLinha != 0 && fimColuna != 0) || (fimLinha == inicioLinha && fimColuna == inicioColuna));
    }else{
      inicioLinha = Math.abs(random.nextInt()) % this.tamanhoVertical;
      inicioColuna = Math.abs(random.nextInt()) % this.tamanhoHorizontal;
      do{
        fimLinha = Math.abs(random.nextInt()) % this.tamanhoVertical;
        fimColuna = Math.abs(random.nextInt()) % this.tamanhoHorizontal;
      }while(fimLinha == inicioLinha && fimColuna == inicioColuna);
    }
    this.getCelulaEm(inicioLinha, inicioColuna).setRotulo("-1");
    this.getCelulaEm(fimLinha, fimColuna).setRotulo("1");
  }
  
  private ArrayList<Ligacao> getLigacoesCelula(Celula celula){
    ArrayList<Ligacao> retorno = new ArrayList<>();
    Celula celulaAux;
    int linha = celula.getPosicaoLinha();
    int coluna = celula.getPosicaoColuna();
    if( (celulaAux = this.getCelulaEmcimaDe(linha, coluna)) != null){
      retorno.add(new Ligacao(celula, celulaAux, Ligacao.Direcao.Cima));
    }
    if( (celulaAux = this.getCelulaEsquerdaDe(linha, coluna)) != null){
      retorno.add(new Ligacao(celula, celulaAux, Ligacao.Direcao.Esquerda));
    }
    if( (celulaAux = this.getCelulaEmbaixoDe(linha, coluna)) != null){
      retorno.add(new Ligacao(celula, celulaAux, Ligacao.Direcao.Baixo));
    }
    if( (celulaAux = this.getCelulaDireitaDe(linha, coluna)) != null){
      retorno.add(new Ligacao(celula, celulaAux, Ligacao.Direcao.Direita));
    }
    return retorno;
  }

  private Celula getCelulaEmcimaDe(int linha, int coluna) {
    if (linha == 0) {
      return null;
    } else {
      return this.getCelulaEm(linha - 1, coluna);
    }
  }

  private Celula getCelulaEmbaixoDe(int linha, int coluna) {
    if (linha == this.tamanhoVertical - 1) {
      return null;
    } else {
      return this.getCelulaEm(linha + 1, coluna);
    }
  }

  private Celula getCelulaEsquerdaDe(int linha, int coluna) {
    if (coluna == 0) {
      return null;
    } else {
      return this.getCelulaEm(linha, coluna - 1);
    }
  }

  private Celula getCelulaDireitaDe(int linha, int coluna) {
    if (coluna == this.tamanhoHorizontal - 1) {
      return null;
    } else {
      return this.getCelulaEm(linha, coluna + 1);
    }
  }

  public Celula getCelulaEm(int linha, int coluna) {
    return this.matriz.get(linha).get(coluna);
  }

  public int getTamanhoHorizontal() {
    return tamanhoHorizontal;
  }

  public int getTamanhoVertical() {
    return tamanhoVertical;
  }
  
  public void aplicaCaminho(Caminho caminho){
    ArrayList<Gene> direcoes = caminho.getDirecoes();
    for (Gene gene : direcoes) {
      System.out.print(gene.toString() + " - ");
    }
  }
}
