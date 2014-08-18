package testpainel;

import javax.swing.JFrame;

/**
 *
 * @author Wesley
 */
public class NewFrame extends JFrame{
  public NewFrame(){
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(true);
    setSize(300, 300);
    
    add(new NewPanel());
  }
}
