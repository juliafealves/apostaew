package units.controllers;

import controllers.SistemaController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste de SistemaController.
 * @author Júlia Fernandes Alves (julia.alves@ccc.ufcg.edu.br)
 */
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
     * Testa o retorno do valor atual do caixa no sistema.
     */
    @Test
    public void testGetCaixa(){
        Assert.assertEquals(1000, this.sistemaController.getCaixa());
    }

    /**
     * Testa o retorno do valor atual do caixa no sistema após cadastrar um cenário bônus.
     */
    @Test
    public void testGetCaixaAposCenarioBonus(){
        this.sistemaController.cadastraCenario("O dado vai dar ímpar", 100);
        Assert.assertEquals(900, this.sistemaController.getCaixa());
    }

    /**
     * Testa o cadastra Cenario, deve retorna um valor númerico único.
     */
    @Test
    public void testCadastraCenario(){
        Assert.assertEquals(1, this.sistemaController.cadastraCenario("O Brasil vai ser hexa!"));
    }

    /**
     * Testa o cadastra cenario bônus, deve retorna um valor númerico único.
     */
    @Test
    public void testCadastraCenarioBonus(){
        Assert.assertEquals(1, this.sistemaController.cadastraCenario("O Brasil vai ser hexa!", 100));
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
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraCenarioBonusDescricaoVazia(){
        this.sistemaController.cadastraCenario("", 100);
    }

    /**
     * Testa se gera uma exceção caso descrição seja apenas constituída de espaços vazios.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraCenarioDescricaoComEspacoEmBranco(){
        this.sistemaController.cadastraCenario("   ");
    }

    /**
     * Testa se gera uma exceção caso descrição seja apenas constituída de espaços vazios.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraCenarioBonusDescricaoComEspacoEmBranco(){
        this.sistemaController.cadastraCenario("   ", 100);
    }

    /**
     * Testa se gera uma exceção caso descrição seja nula.
     */
    @Test (expected = NullPointerException.class)
    public void testCadastraCenarioDescricaoNula(){
        this.sistemaController.cadastraCenario(null);
    }

    /**
     * Testa se gera uma exceção caso descrição seja nula.
     */
    @Test (expected = NullPointerException.class)
    public void testCadastraCenarioBonusDescricaoNula(){
        this.sistemaController.cadastraCenario(null, 100);
    }

    /**
     * Testa se gera uma exceção caso bônus seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraCenarioBonusNegativo(){
        this.sistemaController.cadastraCenario("O dado vai dar par", -100);
    }

    /**
     * Testa se gera uma exceção caso bônus seja igual a zero.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraCenarioBonusIgualZero(){
        this.sistemaController.cadastraCenario("O dado vai dar par", 0);
    }

    /**
     * Testa a consulta do cenário de aposta pela numeração em formato textual.
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
     * Testa se gera uma exceção caso o número do cenário não foi cadastrado ainda.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testConsultaCenarioInexistente(){
        this.sistemaController.consultaCenario(1);
    }

    /**
     * Testa se gera uma exceção caso o número do cenário acima do tamanho da coleção.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testConsultaApostaCenarioForaDoLimite(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.consultaCenario(2);
    }

    /**
     * Testa a listagem de cenários de aposta cadastrados.
     */
    @Test
    public void testListaCenarios(){
        this.sistemaController.cadastraCenario("O Google vai contratar todos os alunos da UFCG.");
        this.sistemaController.cadastraCenario("O Facebook vai ser comprado pelo Google.");
        String cenarios = "1 - O Google vai contratar todos os alunos da UFCG. - Nao finalizado" + System.lineSeparator() +
                "2 - O Facebook vai ser comprado pelo Google. - Nao finalizado";
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
     * Testa a criação do objeto ApostaSegura por valor.
     */
    @Test
    public void testCadastraApostaSeguraValor(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER", 1000, 100);
        Assert.assertEquals(1, this.sistemaController.obtemTotalApostas(1));
    }

    /**
     * Testa a criação do objeto ApostaSegura por taxa.
     */
    @Test
    public void testCadastraApostaSeguraTaxa(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER", 0.1, 100);
        Assert.assertEquals(1, this.sistemaController.obtemTotalApostas(1));
    }

    /**
     * Testa se gera uma exceção caso numeração do cenário seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaCenarioNegativo(){
        this.sistemaController.cadastraAposta(-1,"Maria da Sorte", 1000, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(-1,"Maria da Sorte", 1000, "N VAI ACONTECER", 1000, 100);
        this.sistemaController.cadastraAposta(-1,"Maria da Sorte", 1000, "N VAI ACONTECER", 0.1, 100);
    }

    /**
     * Testa se gera uma exceção caso o número do cenário não foi cadastrado ainda.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testCadastraApostaCenarioInexistente(){
        this.sistemaController.cadastraCenario("Cenario Teste");
        this.sistemaController.cadastraAposta(2,"Jose da Sorte", 1000, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(2,"Jose da Sorte", 1000, "N VAI ACONTECER", 100, 100);
        this.sistemaController.cadastraAposta(2,"Jose da Sorte", 1000, "N VAI ACONTECER", 0.1, 100);
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador seja vazio.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaApostadorVazio(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"", 1000, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(1,"", 1000, "N VAI ACONTECER", 100, 100);
        this.sistemaController.cadastraAposta(1,"", 1000, "N VAI ACONTECER", 0.1, 100);
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador esteja preenchido com caracteres em branco.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaApostadorEmBranco(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1," ", 1000, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(1," ", 1000, "N VAI ACONTECER", 100, 100);
        this.sistemaController.cadastraAposta(1," ", 1000, "N VAI ACONTECER", 0.1, 100);
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador seja nulo.
     */
    @Test (expected = NullPointerException.class)
    public void testCadastraApostaApostadorNulo(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,null, 1000, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(1,null, 1000, "N VAI ACONTECER", 100, 100);
        this.sistemaController.cadastraAposta(1,null, 1000, "N VAI ACONTECER", 0.1, 100);
    }

    /**
     * Testa se gera uma exceção caso o valor seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaValorNegativo(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", -1, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", -1, "N VAI ACONTECER", 100, 100);
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", -1, "N VAI ACONTECER", 0.1, 100);
    }

    /**
     * Testa se gera uma exceção caso o valor seja igual 0.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaValorZero(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 0, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 0, "N VAI ACONTECER", 100, 100);
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 0, "N VAI ACONTECER", 0.1, 100);
    }

    /**
     * Testa se gera uma exceção caso a previsão seja vazia.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaPrevisaoVazio(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "", 100, 100);
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "", 0.1, 100);
    }

    /**
     * Testa se gera uma exceção caso a previsão esteja preenchido com caracteres em branco.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaPrevisaoEmBranco(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "  ");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "  ", 100, 100);
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "  ", 0.1, 100);
    }

    /**
     * Testa se gera uma exceção caso a previsão seja nula.
     */
    @Test (expected = NullPointerException.class)
    public void testCadastraApostaPrevisaoNulo(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, null);
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, null, 100, 100);
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, null, 0.1, 100);
    }

    /**
     * Testa se gera uma exceção caso a previsão seja inválida.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaPrevisaoInvalida(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "NAO EXISTE ESSA PREVISAO");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "NAO EXISTE ESSA PREVISAO", 100, 100);
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "NAO EXISTE ESSA PREVISAO", 0.1, 100);
    }

    /**
     * Testa se gera uma exceção caso o valor assegurado seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaValorAsseguradoNegativo(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER", -1, 100);
    }

    /**
     * Testa se gera uma exceção caso o valor assegurado seja igual 0.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaValorAsseguradoZero(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER", 0, 100);
    }

    /**
     * Testa se gera uma exceção caso a taxa seja igual 0.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaTaxaZero(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER", 0.0, 100);
    }

    /**
     * Testa se gera uma exceção caso a taxa seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaTaxaNegativa(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER", -1.0, 100);
    }

    /**
     * Testa se gera uma exceção caso o custo seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaCustoNegativo(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER", 1000, -1);
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER", 0.1, -1);
    }

    /**
     * Testa se gera uma exceção caso o custo seja igual 0.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaCustoZero(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER", 1000, 0);
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER", 0.1, 0);
    }

    /**
     * Testa o retorno do total de apostas feitas.
     */
    @Test
    public void testObtemTotalApostas(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(1,"Ana da Sorte", 1000, "N VAI ACONTECER", 0.1, 100);
        this.sistemaController.cadastraAposta(1,"Maria da Sorte", 1000, "N VAI ACONTECER", 1000, 100);
        Assert.assertEquals(3, this.sistemaController.obtemTotalApostas(1));
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
        this.sistemaController.cadastraCenario("Cenário teste");
        this.sistemaController.obtemTotalApostas(2);
    }

    /**
     * Testa o retorno do valor total de apostas feitas.
     */
    @Test
    public void testObtemValorTotalApostas(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(1, "Maria da Sorte", 2000, "N VAI ACONTECER", 100, 100);
        this.sistemaController.cadastraAposta(1, "Ana da Sorte", 3000, "N VAI ACONTECER", 0.1, 100);
        Assert.assertEquals(6000, this.sistemaController.obtemValorTotalApostas(1));
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
        this.sistemaController.cadastraCenario("Cenario Teste");
        this.sistemaController.obtemValorTotalApostas(2);
    }

    /**
     * Testa o lista apostas. Formato: Apostador - Valor (em reais) - Previsão
     */
    @Test
    public void testListaApostas(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(1,"Maria da Sorte", 2000, "VAI ACONTECER", 0.2, 100);
        this.sistemaController.cadastraAposta(1,"Ana da Sorte", 3000, "VAI ACONTECER", 1000, 100);
        String apostas = "Jose da Sorte - R$10,00 - N VAI ACONTECER" + System.lineSeparator() +
                "Maria da Sorte - R$20,00 - VAI ACONTECER - ASSEGURADA (TAXA) - 20%" + System.lineSeparator() +
                "Ana da Sorte - R$30,00 - VAI ACONTECER - ASSEGURADA (VALOR) - R$ 10,00" + System.lineSeparator();
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
     * Testa se gera uma exceção caso o número do cenário não foi cadastrado ainda.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testListaApostasCenarioInexistente(){
        this.sistemaController.cadastraCenario("Cenario teste");
        this.sistemaController.listaApostas(2);
    }

    /**
     * Testa a finalização do cenário quando ocorre.
     */
    @Test
    public void testFinalizaCenarioOcorreu(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(1,"Ana da Sorte", 1000, "N VAI ACONTECER", 0.1, 100);
        this.sistemaController.cadastraAposta(1,"Maria da Sorte", 1000, "N VAI ACONTECER", 1000, 100);
        this.sistemaController.finalizaCenario(1, true);
        Assert.assertEquals("1 - O Brasil vai ser hexa - Finalizado (ocorreu)", this.sistemaController.consultaCenario(1));
    }

    /**
     * Testa a finalização do cenário quando ocorre.
     * @todo cadastra aposta segura.
     */
    @Test
    public void testFinalizaCenarioNaoOcorreu(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(1,"Ana da Sorte", 1000, "N VAI ACONTECER", 0.1, 100);
        this.sistemaController.cadastraAposta(1,"Maria da Sorte", 1000, "N VAI ACONTECER", 1000, 100);
        this.sistemaController.finalizaCenario(1, false);
        Assert.assertEquals("1 - O Brasil vai ser hexa - Finalizado (n ocorreu)", this.sistemaController.consultaCenario(1));
    }

    /**
     * Testa se gera uma exceção caso numeração do cenário seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testFinalizaCenarioCenarioNegativo(){
        this.sistemaController.finalizaCenario(-1, true);
    }

    /**
     * Testa se gera uma exceção caso o número do cenário não foi cadastrado ainda.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testFinalizaCenarioCenarioInexistente(){
        this.sistemaController.cadastraCenario("Cenario teste");
        this.sistemaController.finalizaCenario(2, true);
    }

    /**
     * Testa a finalização do cenário quando ocorre.
     */
    @Test
    public void testFinalizaCenarioCaixaQuandoOcorreu(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(1,"Ana da Sorte", 1000, "N VAI ACONTECER", 0.1, 100);
        this.sistemaController.cadastraAposta(1,"Maria da Sorte", 1000, "N VAI ACONTECER", 1000, 100);
        this.sistemaController.finalizaCenario(1, true);
        Assert.assertEquals(400, this.sistemaController.getCaixa());
    }

    /**
     * Testa a finalização do cenário quando ocorre.
     */
    @Test
    public void testFinalizaCenarioCaixaQuandoNaoOcorreu(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(1,"Ana da Sorte", 1000, "N VAI ACONTECER", 0.1, 100);
        this.sistemaController.cadastraAposta(1,"Maria da Sorte", 1000, "N VAI ACONTECER", 1000, 100);
        this.sistemaController.finalizaCenario(1, false);
        Assert.assertEquals(1200, this.sistemaController.getCaixa());
    }

    /**
     * Testa o calculo das apostas perdedoras destinadas ao caixa.
     */
    @Test
    public void testCalculaCaixaCenario(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(1,"Ana da Sorte", 1000, "N VAI ACONTECER", 0.1, 100);
        this.sistemaController.cadastraAposta(1,"Maria da Sorte", 1000, "N VAI ACONTECER", 1000, 100);
        this.sistemaController.finalizaCenario(1, true);
        Assert.assertEquals(300, this.sistemaController.calculaCaixaCenario(1));
    }

    /**
     * Testa se gera uma exceção caso numeração do cenário seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCalculaCaixaCenarioCenarioNegativo(){
        this.sistemaController.calculaCaixaCenario(-1);
    }

    /**
     * Testa se gera uma exceção caso o número do cenário acima do tamanho da coleção.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testCalculaCaixaCenarioCenarioInexistente(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.calculaCaixaCenario(2);
    }

    /**
     * Testa a modificação do seguro de taxa para valor.
     */
    @Test
    public void testModificaSeguroValor(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        int id = this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER", 0.1, 100);
        Assert.assertEquals(1, this.sistemaController.modificaAposta(1, id, 1000));
    }

    /**
     * Testa a modificação do seguro de valor para taxa.
     */
    @Test
    public void testModificaSeguroTaxa(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        int id = this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER", 1000, 100);
        Assert.assertEquals(1, this.sistemaController.modificaAposta(1, id, 0.1));
    }

    /**
     * Testa se gera uma exceção caso numeração do cenário seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testModificaSeguroCenarioNegativo(){
        this.sistemaController.modificaAposta(-1,1, 1000);
        this.sistemaController.modificaAposta(-1,1, 0.1);
    }

    /**
     * Testa se gera uma exceção caso o número do cenário acima do tamanho da coleção.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testModificaSeguroCenarioInexistente(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.modificaAposta(2, 1, 1000);
        this.sistemaController.modificaAposta(2, 1, 0.1);
    }

    /**
     * Testa se gera uma exceção caso identificador da aposta seja negativo.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testModificaSeguroIdentificadorApostaNegativo(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.modificaAposta(1,-1, 1000);
        this.sistemaController.modificaAposta(1,-1, 0.1);
    }

    /**
     * Testa se gera uma exceção caso o identificador da aposta acima do tamanho da coleção.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testModificaSeguroIdentificadorApostaInexistente(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.modificaAposta(1, 2, 1000);
        this.sistemaController.modificaAposta(1, 2, 0.2);
    }

    /**
     * Testa se gera uma exceção caso identificador da aposta seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testModificaSeguroNegativo(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        int id = this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER", 0.1, 100);
        this.sistemaController.modificaAposta(1, id, -100);

        int id2 = this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER", 1000, 100);
        this.sistemaController.modificaAposta(1, id2, -0.4);
    }

    /**
     * Testa se gera uma exceção caso o identificador da aposta acima do tamanho da coleção.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testModificaSeguroZero(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        int id = this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER", 0.1, 100);
        this.sistemaController.modificaAposta(1, id, 0);

        int id2 = this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER", 1000, 100);
        this.sistemaController.modificaAposta(1, id2, 0);
    }

    /**
     * Testa se gera uma exceção caso tente modificar o tipo de seguro já finalizado.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testModificaSeguroValorFinalizado(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        int id = this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER", 0.1, 100);
        this.sistemaController.finalizaCenario(1, true);
        this.sistemaController.modificaAposta(1, id, 1000);
    }

    /**
     * Testa se gera uma exceção caso tente modificar o tipo de seguro já finalizado.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testModificaSeguroTaxaFinalizado(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        int id = this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER", 1000, 100);
        this.sistemaController.finalizaCenario(1, true);
        this.sistemaController.modificaAposta(1, id, 0.1);
    }

    /**
     * Testa o calculo das apostas perdedoras a ser destinada para rateio dos ganhadores.
     */
    @Test
    public void testCalculaRateioCenario(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(1,"Ana da Sorte", 1000, "N VAI ACONTECER", 0.1, 100);
        this.sistemaController.cadastraAposta(1,"Maria da Sorte", 1000, "N VAI ACONTECER", 1000, 100);
        this.sistemaController.finalizaCenario(1, true);
        Assert.assertEquals(2700, this.sistemaController.calculaCaixaRateioCenario(1));
    }

    /**
     * Testa o calculo das apostas perdedoras destinadas ao caixa do cenário bônus.
     */
    @Test
    public void testCalculaRateioCenarioBonus(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa", 100);
        this.sistemaController.cadastraAposta(1,"Jose da Sorte", 1000, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(1,"Ana da Sorte", 1000, "N VAI ACONTECER", 0.1, 100);
        this.sistemaController.cadastraAposta(1,"Maria da Sorte", 1000, "N VAI ACONTECER", 1000, 100);
        this.sistemaController.finalizaCenario(1, true);
        Assert.assertEquals(2800, this.sistemaController.calculaCaixaRateioCenario(1));
    }

    /**
     * Testa se gera uma exceção caso numeração do cenário seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCalculaRateioCenarioCenarioNegativo(){
        this.sistemaController.calculaCaixaRateioCenario(-1);
    }

    /**
     * Testa se gera uma exceção caso o número do cenário acima do tamanho da coleção.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testCalculaRateioCenarioCenarioInexistente(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.calculaCaixaRateioCenario(2);
    }

    /**
     * Testa a ordenação por identificador do consulta cenário.
     */
    @Test
    public void testConsultaCenarioOrdenadoPorId(){
        this.sistemaController.cadastraCenario("Primeiro cenário", 100);
        this.sistemaController.cadastraCenario("Segundo cenário");
        this.sistemaController.cadastraCenario("Terceiro cenário");
        Assert.assertEquals("1 - Primeiro cenário - Nao finalizado - R$ 1,00", this.sistemaController.consultaCenarioOrdenado(1));
    }

    /**
     * Testa a ordenação por descrição do consulta cenário.
     */
    @Test
    public void testConsultaCenarioOrdenadoPorDescricao(){
        this.sistemaController.cadastraCenario("C cenário", 100);
        this.sistemaController.cadastraCenario("A cenário");
        this.sistemaController.cadastraCenario("B cenário");
        this.sistemaController.configuraOrdenacao("nome");
        Assert.assertEquals("2 - A cenário - Nao finalizado", this.sistemaController.consultaCenarioOrdenado(1));
    }

    /**
     * Testa a ordenação por descrição do consulta cenário em caso de empate.
     */
    @Test
    public void testConsultaCenarioOrdenadoPorDescricaoEmpate(){
        this.sistemaController.cadastraCenario("A cenário", 100);
        this.sistemaController.cadastraCenario("A cenário");
        this.sistemaController.cadastraCenario("A cenário");
        this.sistemaController.configuraOrdenacao("nome");
        Assert.assertEquals("1 - A cenário - Nao finalizado - R$ 1,00", this.sistemaController.consultaCenarioOrdenado(1));
    }

    /**
     * Testa a ordenação por total de apostas do consulta cenário.
     */
    @Test
    public void testConsultaCenarioOrdenadoPorApostas(){
        this.sistemaController.cadastraCenario("Primeiro cenário", 100);
        this.sistemaController.cadastraAposta(1, "Clara da Sorte", 100, "N VAI ACONTECER", 100, 100);
        this.sistemaController.cadastraCenario("Segundo cenário");
        this.sistemaController.cadastraAposta(2, "Joao da Sorte", 100, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(2, "Maria da Sorte", 100, "N VAI ACONTECER");
        this.sistemaController.cadastraCenario("Terceiro cenário");
        this.sistemaController.cadastraAposta(3, "Ana da Sorte", 100, "N VAI ACONTECER", 0.1, 100);
        this.sistemaController.configuraOrdenacao("apostas");
        Assert.assertEquals("2 - Segundo cenário - Nao finalizado", this.sistemaController.consultaCenarioOrdenado(1));
    }

    /**
     * Testa a ordenação por total de apostas do consulta cenário com empate.
     */
    @Test
    public void testConsultaCenarioOrdenadoPorApostasEmpate(){
        this.sistemaController.cadastraCenario("Primeiro cenário", 1000);
        this.sistemaController.cadastraAposta(1, "Clara da Sorte", 100, "N VAI ACONTECER", 100, 100);
        this.sistemaController.cadastraAposta(1, "Clara da Sorte", 100, "N VAI ACONTECER", 100, 100);
        this.sistemaController.cadastraCenario("Segundo cenário");
        this.sistemaController.cadastraAposta(2, "Joao da Sorte", 100, "N VAI ACONTECER");
        this.sistemaController.cadastraAposta(2, "Maria da Sorte", 100, "N VAI ACONTECER");
        this.sistemaController.cadastraCenario("Terceiro cenário");
        this.sistemaController.cadastraAposta(3, "Ana da Sorte", 100, "N VAI ACONTECER", 0.1, 100);
        this.sistemaController.cadastraAposta(3, "Ana da Sorte", 100, "N VAI ACONTECER", 0.1, 100);
        this.sistemaController.configuraOrdenacao("apostas");
        Assert.assertEquals("1 - Primeiro cenário - Nao finalizado - R$ 10,00", this.sistemaController.consultaCenarioOrdenado(1));
    }

    /**
     * Testa se gera uma exceção caso numeração do cenário seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConsultaCenarioOrdenadoCenarioNegativo(){
        this.sistemaController.consultaCenarioOrdenado(-1);
    }

    /**
     * Testa se gera uma exceção caso o número do cenário acima do tamanho da coleção.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testConsultaCenarioOrdenadoCenarioInexistente(){
        this.sistemaController.cadastraCenario("O Brasil vai ser hexa");
        this.sistemaController.consultaCenarioOrdenado(2);
    }

    /**
     * Testa se gera uma exceção caso a ordem seja vazia.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConfiguraOrdenacaoOrdemVazia(){
        this.sistemaController.configuraOrdenacao("");
    }

    /**
     * Testa se gera uma exceção caso a ordem esteja preenchido com caracteres em branco.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConfiguraOrdenacaoOrdemEmBranco(){
        this.sistemaController.configuraOrdenacao(" ");
    }

    /**
     * Testa se gera uma exceção caso a ordem seja nula.
     */
    @Test (expected = NullPointerException.class)
    public void testConfiguraOrdenacaoOrdemNulo(){
        this.sistemaController.configuraOrdenacao(null);
    }

    /**
     * Testa se gera uma exceção caso a ordem seja inválida.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConfiguraOrdenacaoOrdemInvalida(){
        this.sistemaController.configuraOrdenacao("valor");
    }
}
