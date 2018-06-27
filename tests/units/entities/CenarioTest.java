package units.entities;

import entities.Cenario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste de Cenario.
 * @author Júlia Fernandes Alves (julia.alves@ccc.ufcg.edu.br)
 */
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
        new Cenario("A Alemanha vai ser goleada pelo Brasil.", 1);
    }

    /**
     * Testa se o objeto Cenario inicializa com estado NAO FINALIZADO.
     */
    @Test
    public void testCenarioIniciaNaoFinalizado(){
        Cenario cenario = new Cenario("Joao Milionário vai ganhar na mega-sena.", 1);
        Assert.assertEquals("1 - Joao Milionário vai ganhar na mega-sena. - Nao finalizado", cenario.toString());
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
     * Testa se gera uma exceção caso descrição tenha somente espaços em brancos.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioDescricaoComEspacoEmBranco(){
        new Cenario("     ", 1);
    }

    /**
     * Testa se gera uma exceção caso a numeração seja um número negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioNumeracaoNegativa(){
        new Cenario("Popoyer vai salvar a Olívia Palito.", -1);
    }

    /**
     * Testa se gera uma exceção caso a numeração seja igual a zero.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioNumeracaoIgualAZero(){
        new Cenario("Maurício será promovido.", 0);
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
     * Testa se o hashCode de objetos iguais são iguais.
     */
    @Test
    public void testHashCode(){
        Cenario cenario = new Cenario("O Brasil vai ser hexa.", 1);
        Assert.assertEquals(this.cenario.hashCode(), cenario.hashCode());
    }

    /**
     * Testa o método toString do Cenario.
     * Formato textual: Numeracao - Descricao - Estado
     */
    @Test
    public void testToString(){
        Assert.assertEquals("1 - O Brasil vai ser hexa. - Nao finalizado", this.cenario.toString());
    }

    /**
     * Testa a criação do objeto Aposta.
     */
    @Test
    public void testAdicionaAposta(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        Assert.assertEquals(1, this.cenario.obtemTotalApostas());
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador seja vazio.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAdicionaApostaApostadorVazio(){
        this.cenario.adicionaAposta("", 1000, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador esteja preenchido com caracteres em branco.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAdicionaApostaApostadorEmBranco(){
        this.cenario.adicionaAposta(" ", 1000, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador seja nulo.
     */
    @Test (expected = NullPointerException.class)
    public void testAdicionaApostaApostadorNulo(){
        this.cenario.adicionaAposta(null, 1000, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso o valor seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAdicionaApostaValorNegativo(){
        this.cenario.adicionaAposta("Jose da Sorte", -1, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso o valor seja igual 0.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAdicionaApostaValorZero(){
        this.cenario.adicionaAposta("Jose da Sorte", 0, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso a previsão seja vazia.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAdicionaApostaPrevisaoVazio(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "");
    }

    /**
     * Testa se gera uma exceção caso a previsão esteja preenchido com caracteres em branco.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAdicionaApostaPrevisaoEmBranco(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "  ");
    }

    /**
     * Testa se gera uma exceção caso a previsão seja nula.
     */
    @Test (expected = NullPointerException.class)
    public void testAdicionaApostaPrevisaoNulo(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, null);
    }

    /**
     * Testa se gera uma exceção caso a previsão seja inválida.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAdicionaApostaPrevisaoInvalida(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "NAO EXISTE ESSA PREVISAO");
    }

    /**
     * Testa o retorno do valor total de apostas feitas.
     */
    @Test
    public void testObtemValorTotalApostas(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        Assert.assertEquals(2000, this.cenario.obtemValorTotalApostas());
    }

    /**
     * Testa o retorno do total de apostas feitas.
     */
    @Test
    public void testObtemTotalApostas(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        Assert.assertEquals(1, this.cenario.obtemTotalApostas());
    }

    /**
     * Testa a listagem de apostas do cenário. Formato: Apostador - Valor (em reais) - Previsão
     */
    @Test
    public void testListaApostas(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        this.cenario.adicionaAposta("Maria da Sorte", 199, "VAI ACONTECER");
        String apostas = "Jose da Sorte - R$10,00 - N VAI ACONTECER" + System.lineSeparator() +
                "Maria da Sorte - R$1,99 - VAI ACONTECER" + System.lineSeparator();
        Assert.assertEquals(apostas, this.cenario.listaApostas());
    }
}
