package utils;

import controllers.SistemaController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SistemaControllerTest {
    private SistemaController sistemaController;

    /**
     * Cria um objeto SistemaController para servir de base para os testes unitários.
     */
    @Before
    public void criaSistemaController(){
        this.sistemaController = new SistemaController();
    }

    /**
     * Testa o cadastra Cenario, deve retorna um valor númerico único.
     */
    @Test
    public void testCadastraCenario(){
        Assert.assertEquals(1, this.sistemaController.cadastraCenario("O Brasil vai ser hexa!"));
    }

    /**
     * Testa se gera uma exceção caso descrição seja vazia.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraCenarioDescricaoVazia(){
        this.sistemaController.cadastraCenario("");
    }

    /**
     * Testa se gera uma exceção caso descrição seja vazia.
     */
    @Test (expected = NullPointerException.class)
    public void testCadastraCenarioDescricaoNula(){
        this.sistemaController.cadastraCenario(null);
    }

    /**
     * Testa a consulta do cenário pela numeração em forma textual.
     */
    @Test
    public void testConsulaCenario(){
        int cenario = this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        Assert.assertEquals("1 - O Brasil vai ser hexa - Nao finalizado", this.sistemaController.consultaCenario(cenario));
    }

    /**
     * Testa se gera uma exceção caso numeração do cenário seja negativo.
     */
    @Test (expected = NumberFormatException.class)
    public void testConsulaCenarioNegativo(){
        this.sistemaController.consultaCenario(-1);
    }

    /**
     * Testa se gera uma exceção caso o número do Cenário não foi cadastrado ainda.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testConsulaCenarioInexistente(){
        this.sistemaController.consultaCenario(1);
    }
}
