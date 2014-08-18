package maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Wesley
 */
public class Labirinto {

  private final Double taxaVerdade = 0.25;
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
        linhaMatriz.add(new Celula("0", i, j, true, true, true, true));
      }
      this.matriz.add(linhaMatriz);
    }
    
    this.gerarCaminho();
    this.setInicioEFim();
  }
  
  private void gerarCaminho(){
    ArrayList<Ligacao> listaLigacoes = new ArrayList<>();
    
    Celula celula = this.getCelulaEm(0, 0);
    celula.setRotulo("1");
    listaLigacoes.addAll(this.getLigacoesCelula(celula));
    
    while( !listaLigacoes.isEmpty() ){
      Collections.shuffle(listaLigacoes);
      Ligacao ligacao = listaLigacoes.remove(0);
      
      Celula celulaDestino = ligacao.getCelulaDestino();
      
      if( !"1".equals(celulaDestino.getRotulo()) ){
        celulaDestino.setRotulo("1");
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
    this.getCelulaEm(inicioLinha, inicioColuna).setRotulo("O");
    this.getCelulaEm(fimLinha, fimColuna).setRotulo("X");
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

  private Celula getCelulaEm(int linha, int coluna) {
    return this.matriz.get(linha).get(coluna);
  }

  public int getTamanhoHorizontal() {
    return tamanhoHorizontal;
  }

  public int getTamanhoVertical() {
    return tamanhoVertical;
  }

  @Override
  public String toString() {
    StringBuilder retorno = new StringBuilder();
    for (int i = 0; i < this.tamanhoHorizontal; i++) {
      retorno.append(" _");
    }
    for (int i = 0; i < this.tamanhoVertical; i++) {
      retorno.append("\n");
      for (int j = 0; j < this.tamanhoHorizontal; j++) {
        Celula celula = this.getCelulaEm(i, j);
        if(celula.hasParedeEsquerda()){
          retorno.append("|");
        }else{
          retorno.append(" ");
        }
        if (celula.hasParedeEmbaixo()) {
          retorno.append("_");
        }else{
          retorno.append(" ");
        }
        
        if(j == this.tamanhoHorizontal - 1){
          retorno.append("|");
        }
      }
    }
    return retorno.toString();
  }
  
  public String toString2() {
    String vazado = "\u2591";
    String solido = "\u2593";
    
    StringBuilder retorno = new StringBuilder();
    for (int i = 0; i < this.tamanhoVertical; i++) {
      for (int j = 0; j < this.tamanhoHorizontal; j++) {
        Celula celula = this.getCelulaEm(i, j);
        if(celula.hasParedeEmcima()){
          retorno.append(solido).append(solido).append(solido);
        }else{
          retorno.append(solido).append(vazado).append(solido);
        }
      }
      retorno.append("\n");
      for (int j = 0; j < this.tamanhoHorizontal; j++) {
        Celula celula = this.getCelulaEm(i, j);
        if(celula.hasParedeEsquerda()){
          retorno.append(solido);
        }else{
          retorno.append(vazado);
        }
        
        if("O".equals(celula.getRotulo()) || "X".equals(celula.getRotulo())){
          retorno.append(celula.getRotulo());
        }else{
          retorno.append(vazado);
        }
        
        if (celula.hasParedeDireita()) {
          retorno.append(solido);
        }else{
          retorno.append(vazado);
        }
      }
      retorno.append("\n");
      for (int j = 0; j < this.tamanhoHorizontal; j++) {
        Celula celula = this.getCelulaEm(i, j);
        if(celula.hasParedeEmbaixo()){
          retorno.append(solido).append(solido).append(solido);
        }else{
          retorno.append(solido).append(vazado).append(solido);
        }
      }
      retorno.append("\n");
    }
    return retorno.toString();
  }
  
  public String toString3() {
    String vazado = "\u2591";
    String solido = "\u2593";

    StringBuilder retorno = new StringBuilder();
    
    for (int i = 0; i < this.tamanhoHorizontal; i++) {
      retorno.append(solido).append(solido);
    }
    retorno.append(solido);
    for (int i = 0; i < this.tamanhoVertical; i++) {
      retorno.append("\n");
      for (int j = 0; j < this.tamanhoHorizontal; j++) {
        Celula celula = this.getCelulaEm(i, j);
        if (celula.hasParedeEsquerda()) {
          retorno.append(solido);
        } else {
          retorno.append(vazado);
        }
        
        if ("O".equals(celula.getRotulo()) || "X".equals(celula.getRotulo())) {
          retorno.append(celula.getRotulo());
        } else {
          retorno.append(vazado);
        }
        
        if (j == this.tamanhoHorizontal - 1) {
          retorno.append(solido);
        }
      }
      retorno.append("\n");
      for (int j = 0; j < this.tamanhoHorizontal; j++) {
        Celula celula = this.getCelulaEm(i, j);
        if (celula.hasParedeEmbaixo()) {
          retorno.append(solido).append(solido);
        } else {
          retorno.append(solido).append(vazado);
        }
      }
      retorno.append(solido);
    }
    
    return retorno.toString();
  }

}
