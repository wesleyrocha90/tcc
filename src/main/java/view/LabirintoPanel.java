package view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import maze.Labirinto;

/**
 *
 * @author Wesley
 */
public class LabirintoPanel extends JPanel {

  private final List<List<CelulaPanel>> matriz;
  private final int linhas;
  private final int colunas;
  private int hAtual = 0;
  private int vAtual = 0;
  
  public LabirintoPanel(Labirinto labirinto){
    this.linhas = labirinto.getTamanhoHorizontal();
    this.colunas = labirinto.getTamanhoVertical();
    
    this.setLayout(new GridLayout(linhas, colunas));
    
    this.matriz = new ArrayList<>(linhas);
    for (int i = 0; i < linhas; i++) {
      List<CelulaPanel> linha = new ArrayList<>(colunas);
      for (int j = 0; j < colunas; j++) {
        CelulaPanel painel = new CelulaPanel( Integer.parseInt(labirinto.getCelulaEm(i, j).getRotulo()),
                labirinto.getCelulaEm(i, j).hasParedeEmcima(),
                labirinto.getCelulaEm(i, j).hasParedeDireita(),
                labirinto.getCelulaEm(i, j).hasParedeEmbaixo(),
                labirinto.getCelulaEm(i, j).hasParedeEsquerda());
        this.add(painel);
        linha.add(painel);
      }
      this.matriz.add(linha);
    }
  }
}
