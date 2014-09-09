package view;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

/**
 *
 * @author Wesley
 */
public class CelulaPanel extends JPanel {

  private Color background = Color.white;
  private Color borda = Color.black;
  private Color inicio = Color.green;
  private Color fim = Color.yellow;
  private int emcima, direita, embaixo, esquerda;
  private int thickness = 5;
  
  public CelulaPanel(int tipoCelula, boolean paredeEmcima, boolean paredeDireita, boolean paredeEmbaixo, boolean paredeEsquerda) {
    if(tipoCelula == -1){
      this.setBackground(inicio);
    }else if(tipoCelula == 0){
      this.setBackground(background);
    }else if(tipoCelula == 1){
      this.setBackground(fim);
    }
    this.emcima = (paredeEmcima) ? thickness : 0;
    this.direita = (paredeDireita) ? thickness : 0;
    this.embaixo = (paredeEmbaixo) ? thickness : 0;
    this.esquerda = (paredeEsquerda) ? thickness : 0;
    this.setBorder(new MatteBorder(emcima, esquerda, embaixo, direita, borda));
  }
}
