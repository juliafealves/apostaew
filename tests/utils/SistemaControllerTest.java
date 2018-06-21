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
        this.sistemaController = new SistemaController(1000, 0.1);
    }

    /**
     * Testa a inicialização do sistema.
     */
    @Test
    public void testInicializaSistema(){
       new SistemaController(1000, 0.1);
    }

    /**
     * Testa se gera uma exceção caso caixa seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testInicializaSistemaCaixaNegativo(){
        new SistemaController(-1, 0.1);
    }

    /**
     * Testa se gera uma exceção caso taxa seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testInicializaSistemaTaxaNegativa(){
        new SistemaController(1000, -0.1);
    }

    /**
     * Testa o retorno do caixa do sistema.
     */
    @Test
    public void testGetCaixa(){
        Assert.assertEquals(1000, this.sistemaController.getCaixa());
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
     * Testa se gera uma exceção caso descrição seja nula.
     */
    @Test (expected = NullPointerException.class)
    public void testCadastraCenarioDescricaoNula(){
        this.sistemaController.cadastraCenario(null);
    }

    /**
     * Testa a consulta do cenário pela numeração em forma textual.
     */
    @Test
    public void testConsultaCenario(){
        int cenario = this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        Assert.assertEquals("1 - O Brasil vai ser hexa - Nao finalizado", this.sistemaController.consultaCenario(cenario));
    }

    /**
     * Testa se gera uma exceção caso numeração do cenário seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConsultaCenarioNegativo(){
        this.sistemaController.consultaCenario(-1);
    }

    /**
     * Testa se gera uma exceção caso o número do Cenário não foi cadastrado ainda.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testConsultaCenarioInexistente(){
        this.sistemaController.consultaCenario(1);
    }

    /**
     * Testa se gera uma exceção caso o número do Cenário acima do tamanho da coleção.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testConsultaApostaCenarioAcimaDoLimite(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.consultaCenario(2);
    }

    /**
     * Testa a listagem de cenários cadastrados.
     */
    @Test
    public void testListaCenarios(){
        this.sistemaController.cadastraCenario("Cenario1");
        this.sistemaController.cadastraCenario("Cenario2");
        String cenarios = "1 - Cenario1 - Nao finalizado" + System.lineSeparator() +
                "2 - Cenario2 - Nao finalizado" + System.lineSeparator();
        Assert.assertEquals(cenarios, this.sistemaController.listaCenarios());
    }

    /**
     * Testa a criação do objeto Aposta.
     */
    @Test
    public void testCadastraAposta(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER");
        Assert.assertEquals(1, this.sistemaController.obtemTotalApostas(1));
    }

    /**
     * Testa se gera uma exceção caso numeração do cenário seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaCenarioNegativo(){
        this.sistemaController.cadastraAposta(-1,"", 1000, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso o número do Cenário não foi cadastrado ainda.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testCadastraApostaCenarioInexistente(){
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso o número do Cenário acima do tamanho da coleção.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void estCadastraApostaCenarioAcimaDoLimite(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(2,"Jose da Sorte", 1000, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador seja vazio.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaApostadorVazio(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"", 1000, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador esteja preenchido com caracteres em branco.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaApostadorEmBranco(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1," ", 1000, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador seja nulo.
     */
    @Test (expected = NullPointerException.class)
    public void testCadastraApostaApostadorNulo(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,null, 1000, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso o valor seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaValorNegativo(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", -1, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso o valor seja igual 0.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaValorZero(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 0, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso a previsão seja vazia.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaPrevisaoVazio(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "");
    }

    /**
     * Testa se gera uma exceção caso a previsão esteja preenchido com caracteres em branco.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaPrevisaoEmBranco(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "  ");
    }

    /**
     * Testa se gera uma exceção caso a previsão seja nula.
     */
    @Test (expected = NullPointerException.class)
    public void testCadastraApostaPrevisaoNulo(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, null);
    }

    /**
     * Testa se gera uma exceção caso a previsão seja inválida.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaPrevisaoInvalida(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "NAO EXITE ESSA PREVISAO");
    }

    /**
     * Testa o retorno do total de apostas feitas.
     */
    @Test
    public void testObtemTotalApostas(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1, "Jose da Sorte", 1000, "N VAI ACONTECER");
        Assert.assertEquals(1, this.sistemaController.obtemTotalApostas(1));
    }

    /**
     * Testa se gera uma exceção caso numeração do cenário seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testObtemTotalApostasCenarioNegativo(){
        this.sistemaController.obtemTotalApostas(-1);
    }

    /**
     * Testa se gera uma exceção caso o número do Cenário não foi cadastrado ainda.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testObtemTotalApostasCenarioInexistente(){
        this.sistemaController.obtemTotalApostas(1);
    }

    /**
     * Testa se gera uma exceção caso o número do Cenário acima do tamanho da coleção.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testObtemTotalApostasCenarioAcimaDoLimite(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.obtemTotalApostas(2);
    }

    /**
     * Testa o retorno do valor total de apostas feitas.
     */
    @Test
    public void testObtemValorTotalApostas(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(1, "Jose da Sorte", 1000, "N VAI ACONTECER");
        Assert.assertEquals(2000, this.sistemaController.obtemValorTotalApostas(1));
    }

    /**
     * Testa se gera uma exceção caso numeração do cenário seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testObtemValorTotalApostasCenarioNegativo(){
        this.sistemaController.obtemValorTotalApostas(-1);
    }

    /**
     * Testa se gera uma exceção caso o número do Cenário não foi cadastrado ainda.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testObtemValorTotalApostasCenarioInexistente(){
        this.sistemaController.obtemValorTotalApostas(1);
    }

    /**
     * Testa se gera uma exceção caso o número do Cenário acima do tamanho da coleção.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testObtemValorTotalApostasCenarioAcimaDoLimite(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.obtemValorTotalApostas(2);
    }

    /**
     * Testa o lista apostas.
     * Formato: Apostador - Valor (em reais) - Previsão
     */
    @Test
    public void testListaApostas(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(1,"Maria da Sorte", 199, "VAI ACONTECER");
        String apostas = "Jose da Sorte - R$10,00 - N VAI ACONTECER" + System.lineSeparator() +
                "Maria da Sorte - R$1,99 - VAI ACONTECER" + System.lineSeparator();
        Assert.assertEquals(apostas, this.sistemaController.listaApostas(1));
    }

    /**
     * Testa se gera uma exceção caso numeração do cenário seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testListaApostasCenarioNegativo(){
        this.sistemaController.listaApostas(-1);
    }

    /**
     * Testa se gera uma exceção caso o número do Cenário não foi cadastrado ainda.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testListaApostasCenarioInexistente(){
        this.sistemaController.listaApostas(1);
    }

    /**
     * Testa se gera uma exceção caso o número do Cenário acima do tamanho da coleção.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testListaApostasCenarioAcimaDoLimite(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.listaApostas(2);
    }
}
