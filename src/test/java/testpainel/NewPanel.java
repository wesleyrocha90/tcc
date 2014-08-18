package testpainel;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Wesley
 */
public class NewPanel extends JPanel{
  public NewPanel(){
    setBorder(new LineBorder(Color.yellow, 5));
    setBackground(Color.red);
  }
}
