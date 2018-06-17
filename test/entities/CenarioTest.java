package entities;

import org.junit.Assert;
import org.junit.Test;

public class CenarioTest {

    /**
     * Testa a criação do objeto Cenário.
     */
    @Test
    public void testCriaCenario(){
        new Cenario("O Brasil vai ser hexa!");
    }

    /**
     * Testa se o objeto Cenário inicializa com estado NAO FINALIZADO.
     */
    @Test
    public void testCenarioIniciaNaoFinalizado(){
        Cenario cenario = new Cenario("O Brasil vai ser hexa.");
        Assert.assertEquals("O Brasil vai ser hexa. - Não finalizado", cenario.toString());
    }

    /**
     * Testa se gera uma exceção caso descrição seja nula.
     */
    @Test (expected = NullPointerException.class)
    public void testCenarioDescricaoNula(){
        new Cenario(null);
    }

    /**
     * Testa se gera uma exceção caso descrição seja vazia.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioDescricaoVazia(){
        new Cenario("");
    }
}
