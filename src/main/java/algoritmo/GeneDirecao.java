package algoritmo;

import java.util.Random;

/**
 *
 * @author Wesley
 */
public enum GeneDirecao implements Gene{
  CIMA("CIMA"),
  ESQUERDA("ESQUERDA"),
  BAIXO("BAIXO"),
  DIREITA("DIREITA");
  
  private static final int quantidade = 4;
  private String direcao;
  private GeneDirecao(String direcao){
    this.direcao = direcao;
  }
  
  public static Gene getRandomGene(){
    Random random = new Random();
    int selecao = random.nextInt(quantidade);
    return values()[selecao];
  }
}