package view;

import com.jidesoft.swing.JideTabbedPane;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import maze.Labirinto;
import org.pushingpixels.flamingo.api.common.CommandButtonDisplayState;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonComponent;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;

/**
 *
 * @author Wesley
 */
public class Principal extends JRibbonFrame {

  private final int MAXIMO_HORIZONTAL = 50;
  private final int MAXIMO_VERTICAL = 15;
  private int tamanhoHorizontal = 10;
  private int tamanhoVertical = 10;
  private boolean inicioFimNasBordas = true;
  private Labirinto maze;
  private JideTabbedPane tabbedPane;

  public static ResizableIcon getResizableIconFromResource(String resource) {
    String resourcePath = "" + resource;
    return ImageWrapperResizableIcon.getIcon(Principal.class.getClassLoader().getResource(resourcePath), new Dimension(48, 48));
  }

  public Principal() {
    this.setDefaultCloseOperation(JRibbonFrame.EXIT_ON_CLOSE);
    this.setExtendedState(JRibbonFrame.MAXIMIZED_BOTH);
    this.setMinimumSize(new Dimension(700, 700));

    this.getRibbon().setApplicationMenu(new RibbonApplicationMenu());
    this.setApplicationIcon(getResizableIconFromResource("maze-cube.png"));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // inicio do codigo pra criar todas as bandas e seus componentes
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // criando a banda
    JRibbonBand bandPrincipal = new JRibbonBand("Principal", getResizableIconFromResource("maze_128.png"));

    // criando os botoes de comando
    JCommandButton buttonGerar = new JCommandButton("Gerar Labirinto", getResizableIconFromResource("maze_128.png"));
    buttonGerar.addActionListener(new GerarMazeListener());

    // seta as formatos em que os botoes serao exibidos
    buttonGerar.setDisplayState(CommandButtonDisplayState.BIG);

    // inserindo os botoes de comando na banda
    bandPrincipal.addCommandButton(buttonGerar, RibbonElementPriority.TOP);

    // definindo as politicas de redimensionamento da banda
    bandPrincipal.setResizePolicies((List) Arrays.asList(
            new CoreRibbonResizePolicies.None(bandPrincipal.getControlPanel())));

    // criando a banda
    JRibbonBand bandConfiguracao = new JRibbonBand("Configurações", getResizableIconFromResource("maze_128.png"));

    // criando os botoes de comando
    ButtonGroup radioGroup = new ButtonGroup();
    JRadioButton radioNasBordas = new JRadioButton("", true);
    radioNasBordas.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED){
          inicioFimNasBordas = true;
        }
      }
    });
    JRadioButton radioAleatorio = new JRadioButton("", false);
    radioAleatorio.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED){
          inicioFimNasBordas = false;
        }
      }
    });
    radioGroup.add(radioNasBordas);
    radioGroup.add(radioAleatorio);
    JRibbonComponent componentNasBordas = new JRibbonComponent(null, "Nas Bordas", radioNasBordas);
    JRibbonComponent componentAleatorio = new JRibbonComponent(null, "Aleatório", radioAleatorio);
    
    
    final JSpinner spinnerH = new JSpinner();
    spinnerH.setModel(new SpinnerNumberModel(10, 5, this.MAXIMO_HORIZONTAL, 1));
    spinnerH.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        tamanhoHorizontal = (int)spinnerH.getValue();
      }
    });
    final JSpinner spinnerV = new JSpinner();
    spinnerV.setModel(new SpinnerNumberModel(10, 5, this.MAXIMO_VERTICAL, 1));
    spinnerV.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        tamanhoVertical = (int)spinnerV.getValue();
      }
    });
    JRibbonComponent spinnerTamanhoH = new JRibbonComponent(getResizableIconFromResource("resize_horizontal.png"), "Horizontal", spinnerH);
    JRibbonComponent spinnerTamanhoV = new JRibbonComponent(getResizableIconFromResource("resize_vertical.png"), "Vertical", spinnerV);

    // inserindo os botoes de comando na banda
    bandConfiguracao.startGroup("Início e Fim");
    bandConfiguracao.addRibbonComponent(componentNasBordas);
    bandConfiguracao.addRibbonComponent(componentAleatorio);
    bandConfiguracao.startGroup("Tamanho");
    bandConfiguracao.addRibbonComponent(spinnerTamanhoH);
    bandConfiguracao.addRibbonComponent(spinnerTamanhoV);

    // definindo as politicas de redimensionamento da banda
    bandConfiguracao.setResizePolicies((List) Arrays.asList(
            new CoreRibbonResizePolicies.None(bandConfiguracao.getControlPanel())));

    //======================================================================================================================================================
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Criacao das tasks e insercao de todas as suas bandas
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // criando a task e inserindo as bandas nela
    RibbonTask taskPrincipal = new RibbonTask("Principal", bandPrincipal, bandConfiguracao);
    //======================================================================================================================================================

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Insercao de todas as tasks no frame, atentar que a ordem de insercao sera a ordem de exibicao
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // inserindo a task no frame, pra ser exibido na janela
    this.getRibbon().addTask(taskPrincipal);
    //======================================================================================================================================================

    tabbedPane = new JideTabbedPane();
    tabbedPane.setShowCloseButtonOnTab(true);

    this.add(tabbedPane);

    this.setVisible(true);
  }

  private class GerarMazeListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      maze = new Labirinto(tamanhoHorizontal, tamanhoVertical, inicioFimNasBordas);
      if (tabbedPane.getTabCount() > 0) {
        tabbedPane.remove(tabbedPane.getSelectedIndex());
      }
      JTextPane text = new JTextPane();
      text.setFont(new Font("Consolas", Font.PLAIN, 20));
      text.setText(maze.toString3());

      tabbedPane.add("Labirinto", text);
    }
  }
  
  // O menu Ribbon é composto por várias RibbonTask
  // A RibbonTask é cada uma das abas do Ribbon
  // É na RibbonTask que se coloca uma Band
  // Existem dois componentes que são Band: JRibbonBand e JFlowRibbonBand
  // A diferença entre os dois está na forma de disposição dos componentes
}