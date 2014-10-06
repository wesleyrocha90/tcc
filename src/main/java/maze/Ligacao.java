package maze;

/**
 *
 * @author Wesley
 */
public class Ligacao {

  enum Direcao {

    Cima, Esquerda, Baixo, Direita
  };
  private Celula celulaOrigem;
  private Celula celulaDestino;
  private Direcao direcao;

  public Ligacao(Celula celulaOrigem, Celula celulaDestino, Direcao direcao) {
    this.celulaOrigem = celulaOrigem;
    this.celulaDestino = celulaDestino;
    this.direcao = direcao;
  }

  public Celula getCelulaDestino() {
    return celulaDestino;
  }

  public void setPassagem() {
    switch (this.direcao) {
      case Cima:
        celulaOrigem.setParedeEmcima(false);
        celulaDestino.setParedeEmbaixo(false);
        break;
      case Esquerda:
        celulaOrigem.setParedeEsquerda(false);
        celulaDestino.setParedeDireita(false);
        break;
      case Baixo:
        celulaOrigem.setParedeEmbaixo(false);
        celulaDestino.setParedeEmcima(false);
        break;
      case Direita:
        celulaOrigem.setParedeDireita(false);
        celulaDestino.setParedeEsquerda(false);
        break;
    }
  }
}
