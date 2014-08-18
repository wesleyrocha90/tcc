package maze;

/**
 *
 * @author Wesley
 */
public class Celula {

  private String rotulo;
  private int posicaoLinha;
  private int posicaoColuna;
  private boolean paredeEmcima;
  private boolean paredeEsquerda;
  private boolean paredeEmbaixo;
  private boolean paredeDireita;

  public Celula(String rotulo, int posicaoLinha, int posicaoColuna, boolean paredeEmcima, boolean paredeEsquerda, boolean paredeEmbaixo, boolean paredeDireita) {
    this.rotulo = rotulo;
    this.posicaoLinha = posicaoLinha;
    this.posicaoColuna = posicaoColuna;
    this.paredeEmcima = paredeEmcima;
    this.paredeEsquerda = paredeEsquerda;
    this.paredeEmbaixo = paredeEmbaixo;
    this.paredeDireita = paredeDireita;
  }

  public String getRotulo() {
    return rotulo;
  }

  public void setRotulo(String rotulo) {
    this.rotulo = rotulo;
  }

  public int getPosicaoLinha() {
    return posicaoLinha;
  }

  public int getPosicaoColuna() {
    return posicaoColuna;
  }

  public void setParedeEmcima(boolean paredeEmcima) {
    this.paredeEmcima = paredeEmcima;
  }

  public void setParedeEsquerda(boolean paredeEsquerda) {
    this.paredeEsquerda = paredeEsquerda;
  }

  public void setParedeEmbaixo(boolean paredeEmbaixo) {
    this.paredeEmbaixo = paredeEmbaixo;
  }

  public void setParedeDireita(boolean paredeDireita) {
    this.paredeDireita = paredeDireita;
  }

  public boolean hasParedeEmcima() {
    return this.paredeEmcima;
  }

  public boolean hasParedeEsquerda() {
    return this.paredeEsquerda;
  }

  public boolean hasParedeEmbaixo() {
    return this.paredeEmbaixo;
  }

  public boolean hasParedeDireita() {
    return this.paredeDireita;
  }

  @Override
  public String toString() {
    return "Cell{" + rotulo + ", [top=" + paredeEmcima + ", left=" + paredeEsquerda + ", bottom=" + paredeEmbaixo + ", right=" + paredeDireita + "]}";
  }
}
