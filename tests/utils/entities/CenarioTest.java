package utils.entities;

import entities.Cenario;
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
        new Cenario("O Brasil vai ser hexa!", 1);
    }

    /**
     * Testa se o objeto Cenário inicializa com estado NAO FINALIZADO.
     */
    @Test
    public void testCenarioIniciaNaoFinalizado(){
        Cenario cenario = new Cenario("O Brasil vai ser hexa.", 1);
        Assert.assertEquals("1 - O Brasil vai ser hexa. - Nao finalizado", cenario.toString());
    }

    /**
     * Testa se gera uma exceção caso descrição seja nula.
     */
    @Test (expected = NullPointerException.class)
    public void testCenarioDescricaoNula(){
        new Cenario(null, 1);
    }

    /**
     * Testa se gera uma exceção caso descrição seja vazia.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioDescricaoVazia(){
        new Cenario("", 1);
    }

    /**
     * Testa se gera uma exceção caso descrição seja vazia.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioDescricaoEspacoEmBranco(){
        new Cenario("  ", 1);
    }

    /**
     * Testa se gera uma exceção caso a numeração seja um número negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioNumeracaoNegativa(){
        new Cenario("O Brasil vai ser hexa.", -1);
    }

    /**
     * Testa se gera uma exceção caso a numeração seja igual a zero.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioNumeracaoIgualAZero(){
        new Cenario("O Brasil vai ser hexa.", 0);
    }

    /**
     * Testa se dois objetos são iguais. Dois objetos são iguais caso tenha a mesma numeração.
     */
    @Test
    public void testEquals(){
        Cenario cenario = new Cenario("O Brasil vai ser hexa.", 1);
        Assert.assertTrue(this.cenario.equals(cenario));
    }

    /**
     * Testa se dois objetos são diferentes. Dois objetos são diferentes caso tenha numeração distintas.
     */
    @Test
    public void testEqualsDiferentes(){
        Cenario cenario = new Cenario("O Brasil vai ser hexa.", 2);
        Assert.assertFalse(this.cenario.equals(cenario));
    }

    /**
     * Testa se o hashCode de objetos iguais é possuem o mesmo hashCode.
     */
    @Test
    public void testHashCode(){
        Cenario cenario = new Cenario("O Brasil vai ser hexa.", 1);
        Assert.assertEquals(this.cenario.hashCode(), cenario.hashCode());
    }

    /**
     * Testa o método toString do Cenario.
     * Formato: Numeracao - Descricao - Estado
     */
    @Test
    public void testToString(){
        Assert.assertEquals("1 - O Brasil vai ser hexa. - Nao finalizado", this.cenario.toString());
    }

    /**
     * Testa a criação do objeto Aposta.
     */
    @Test
    public void testCadastraAposta(){
        this.cenario.cadastraAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        Assert.assertEquals(1, this.cenario.obtemTotalApostas());
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador seja vazio.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaApostadorVazio(){
        this.cenario.cadastraAposta("", 1000, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador esteja preenchido com caracteres em branco.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaApostadorEmBranco(){
        this.cenario.cadastraAposta(" ", 1000, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador seja nulo.
     */
    @Test (expected = NullPointerException.class)
    public void testCadastraApostaApostadorNulo(){
        this.cenario.cadastraAposta(null, 1000, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso o valor seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaValorNegativo(){
        this.cenario.cadastraAposta("Jose da Sorte", -1, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso o valor seja igual 0.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaValorZero(){
        this.cenario.cadastraAposta("Jose da Sorte", 0, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso a previsão seja vazia.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaPrevisaoVazio(){
        this.cenario.cadastraAposta("Jose da Sorte", 1000, "");
    }

    /**
     * Testa se gera uma exceção caso a previsão esteja preenchido com caracteres em branco.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaPrevisaoEmBranco(){
        this.cenario.cadastraAposta("Jose da Sorte", 1000, "  ");
    }

    /**
     * Testa se gera uma exceção caso a previsão seja nula.
     */
    @Test (expected = NullPointerException.class)
    public void testCadastraApostaPrevisaoNulo(){
        this.cenario.cadastraAposta("Jose da Sorte", 1000, null);
    }

    /**
     * Testa se gera uma exceção caso a previsão seja inválida.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCadastraApostaPrevisaoInvalida(){
        this.cenario.cadastraAposta("Jose da Sorte", 1000, "NAO EXITE ESSA PREVISAO");
    }

    /**
     * Testa o retorno do total de apostas feitas.
     */
    @Test
    public void testObtemTotalApostas(){
        this.cenario.cadastraAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        Assert.assertEquals(1, this.cenario.obtemTotalApostas());
    }

    /**
     * Testa o retorno do valor total de apostas feitas.
     */
    @Test
    public void testObtemValorTotalApostas(){
        this.cenario.cadastraAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        this.cenario.cadastraAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        Assert.assertEquals(2000, this.cenario.obtemValorTotalApostas());
    }

    /**
     * Testa o lista apostas.
     * Formato: Apostador - Valor (em reais) - Previsão
     */
    @Test
    public void testListaApostas(){
        this.cenario.cadastraAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        this.cenario.cadastraAposta("Maria da Sorte", 199, "VAI ACONTECER");
        String apostas = "Jose da Sorte - R$10,00 - N VAI ACONTECER" + System.lineSeparator() +
                "Maria da Sorte - R$1,99 - VAI ACONTECER" + System.lineSeparator();
        Assert.assertEquals(apostas, this.cenario.listaApostas());
    }
}
