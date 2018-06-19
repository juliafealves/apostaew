package entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CenarioTest {
    private Cenario cenario;

    /**
     * Cria um objeto Cenario para servir de base para os testes unitários.
     */
    @Before
    public void criaCenario(){
        this.cenario = new Cenario("O Brasil vai ser hexa.", 1);
    }

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

    /**
     * Testa a método toString do Cenario.
     */
    @Test
    public void testToString(){
        Assert.assertEquals("1 - O Brasil vai ser hexa. - Não finalizado", this.cenario.toString());
    }
}
