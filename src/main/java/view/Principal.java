package view;

import algoritmo.Individuo;
import algoritmo.Populacao;
import algoritmo.PopulacaoSimples;
import com.jidesoft.swing.JideTabbedPane;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;
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

  private LabirintoPanel labirintoPanel;
  private JideTabbedPane tabbedPane;
  private JPanel treePanel;
  private JTree tree;

  private final int MAXIMO_HORIZONTAL = 50;
  private final int MAXIMO_VERTICAL = 15;
  private int tamanhoHorizontal = 10;
  private int tamanhoVertical = 10;
  private boolean inicioFimNasBordas = true;
  private Labirinto labirinto;
  
  private JTextField textTamanhoPopulacao;
  private JTextField textTamanhoIndividuo;
  private Populacao populacao;
  
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
    
    //<editor-fold defaultstate="collapsed" desc="Criação da banda Labirinto">
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // inicio do codigo pra criar todas as bandas e seus componentes
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // criando a banda
    JRibbonBand bandLabirinto = new JRibbonBand("Labirinto", getResizableIconFromResource("maze_128.png"));

    // criando os botoes de comando
    JCommandButton buttonGerar = new JCommandButton("Gerar Labirinto", getResizableIconFromResource("maze_128.png"));
    buttonGerar.addActionListener(new GerarLabirinto());
    
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
    
    // seta as formatos em que os botoes serao exibidos
    buttonGerar.setDisplayState(CommandButtonDisplayState.BIG);

    // inserindo os botoes de comando na banda
    bandLabirinto.addCommandButton(buttonGerar, RibbonElementPriority.TOP);
    
    bandLabirinto.startGroup("Início e Fim");
    bandLabirinto.addRibbonComponent(componentNasBordas);
    bandLabirinto.addRibbonComponent(componentAleatorio);
    bandLabirinto.startGroup("Tamanho");
    bandLabirinto.addRibbonComponent(spinnerTamanhoH);
    bandLabirinto.addRibbonComponent(spinnerTamanhoV);

    // definindo as politicas de redimensionamento da banda
    bandLabirinto.setResizePolicies((List) Arrays.asList(
            new CoreRibbonResizePolicies.None(bandLabirinto.getControlPanel())));
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Criação da banda Algoritmo">
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // inicio do codigo pra criar todas as bandas e seus componentes
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // criando a banda
    JRibbonBand bandAlgoritmo = new JRibbonBand("Algoritmo", getResizableIconFromResource("maze_128.png"));

    // criando os botoes de comando
    JCommandButton buttonGerarPopulacao = new JCommandButton("Executar Algoritmo", getResizableIconFromResource("maze_128.png"));
    buttonGerarPopulacao.addActionListener(new ExecutarAlgoritmo());
    
    textTamanhoPopulacao = new JTextField("10", 5);
    textTamanhoIndividuo = new JTextField("10", 5);
    JRibbonComponent componentTamanhoPopulacao = new JRibbonComponent(null, "Tamanho População", textTamanhoPopulacao);
    JRibbonComponent componentTamanhoIndividuo = new JRibbonComponent(null, "Tamanho Indivíduo", textTamanhoIndividuo);

    // seta as formatos em que os botoes serao exibidos
    buttonGerarPopulacao.setDisplayState(CommandButtonDisplayState.BIG);

    // inserindo os botoes de comando na banda
    bandAlgoritmo.addCommandButton(buttonGerarPopulacao, RibbonElementPriority.TOP);
    
    bandAlgoritmo.startGroup("Tamanhos");
    bandAlgoritmo.addRibbonComponent(componentTamanhoPopulacao);
    bandAlgoritmo.addRibbonComponent(componentTamanhoIndividuo);

    // definindo as politicas de redimensionamento da banda
    bandAlgoritmo.setResizePolicies((List) Arrays.asList(
            new CoreRibbonResizePolicies.None(bandAlgoritmo.getControlPanel())));
    //</editor-fold>
    //======================================================================================================================================================
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Criacao das tasks e insercao de todas as suas bandas
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // criando a task e inserindo as bandas nela
    RibbonTask taskPrincipal = new RibbonTask("Principal", bandLabirinto, bandAlgoritmo);
    //======================================================================================================================================================

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Insercao de todas as tasks no frame, atentar que a ordem de insercao sera a ordem de exibicao
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // inserindo a task no frame, pra ser exibido na janela
    this.getRibbon().addTask(taskPrincipal);
    //======================================================================================================================================================

    tabbedPane = new JideTabbedPane();
    tabbedPane.setShowCloseButtonOnTab(true);
    buttonGerar.doActionClick();
    
    treePanel = new JPanel(new GridLayout(1, 1, 0, 0));
    
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabbedPane, treePanel);
    splitPane.setDividerLocation(0.75);
    splitPane.setResizeWeight(0.75);
    
    this.add(splitPane);
    this.setVisible(true);
  }

  private class GerarLabirinto implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
//      populacao = new PopulacaoSimples();
      labirinto = new Labirinto(tamanhoHorizontal, tamanhoVertical, inicioFimNasBordas);
      labirintoPanel = new LabirintoPanel(labirinto);
      if (tabbedPane.getTabCount() > 0) {
        tabbedPane.remove(tabbedPane.getSelectedIndex());
      }
      tabbedPane.add("Labirinto", labirintoPanel);
    }
  }
  
  private class ExecutarAlgoritmo implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      try{
        int tamanhoPopulacao = Integer.parseInt(textTamanhoPopulacao.getText());
        int tamanhoIndividuo = Integer.parseInt(textTamanhoIndividuo.getText());
        
        populacao = new PopulacaoSimples(tamanhoPopulacao, tamanhoIndividuo);
        populacao.inicializaPopulacao();
        
        imprimePopulacao();
      } catch(NumberFormatException exception){
        JOptionPane dialog = new JOptionPane("Tamanho Incorreto", JOptionPane.ERROR_MESSAGE, JOptionPane.OK_OPTION);
        System.out.println(exception);
      }
    }
  }
  
  private void imprimePopulacao(){
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Geração 1");
    for (Individuo individuo : populacao.getIndividuos()) {
      DefaultMutableTreeNode leaf = new DefaultMutableTreeNode(individuo.toString());
      root.add(leaf);
    }
    tree = new JTree(root);
    treePanel.add(tree);
  }
}