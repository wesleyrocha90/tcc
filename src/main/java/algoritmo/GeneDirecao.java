package algoritmo;

import java.util.Random;

/**
 *
 * @author Wesley
 */
public enum GeneDirecao implements Gene {

  CIMA("CIMA"),
  ESQUERDA("ESQUERDA"),
  BAIXO("BAIXO"),
  DIREITA("DIREITA");
  private final String upwardsThickArrow = "\u21E7";
  private final String leftwardsThickArrow = "\u21E6";
  private final String downwardsThickArrow = "\u21E9";
  private final String rightwardsThickArrow = "\u21E8";
  private static final int quantidade = 4;
  private String direcao;

  private GeneDirecao(String direcao) {
    this.direcao = direcao;
  }

  public static Gene getRandomGene() {
    Random random = new Random();
    int selecao = random.nextInt(quantidade);
    return values()[selecao];
  }

  @Override
  public String getGene() {
    return this.name();
  }

  @Override
  public String toString() {
    String retorno = "";
    switch (this.direcao) {
      case "CIMA":
        retorno = upwardsThickArrow;
        break;
      case "ESQUERDA":
        retorno = leftwardsThickArrow;
        break;
      case "BAIXO":
        retorno = downwardsThickArrow;
        break;
      case "DIREITA":
        retorno = rightwardsThickArrow;
        break;
    }
    return retorno;
  }
}