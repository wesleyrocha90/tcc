package main;

import algoritmo.IndividuoSimples;
import maze.Caminho;
import maze.Labirinto;
import org.junit.Test;

/**
 *
 * @author Wesley
 */
public class MainTest {

  @Test
  public void criacaoDeUmaPopulacao() {
    
    Caminho caminho = new Caminho(new IndividuoSimples(10));
    Labirinto labirinto = new Labirinto(5, 5);
    labirinto.aplicaCaminho(caminho);
    
  }
}
