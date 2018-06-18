package controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SistemaControllerTest {
    private String descricao;
    private SistemaController sistemaController;

    /**
     * Cria um objeto SistemaController para servir de base para os testes unitários.
     */
    @Before
    public void criaSistemaController(){
        this.descricao = "O Brasil vai ser hexacampeão";
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
        int cenario = this.sistemaController.cadastraCenario("O Brasil var ser hexa");
        Assert.assertEquals("O Brasil vai ser hexa - Não finalizado", this.sistemaController.consultaCenario(cenario));
    }

    /**
     * Testa se gera uma exceção caso descrição seja vazia.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConsulaCenarioInexistente(){
        this.sistemaController.consultaCenario(1);
    }
}
