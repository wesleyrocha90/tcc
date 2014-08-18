package main;

import algoritmo.PopulacaoImpl;
import org.junit.Test;

/**
 *
 * @author Wesley
 */
public class MainTest {

  @Test
  public void criacaoDeUmaPopulacao() {
    
    PopulacaoImpl p = new PopulacaoImpl(10, 10);
    p.algoritmo();    
    System.out.println(p.toString());
    
  }
}
