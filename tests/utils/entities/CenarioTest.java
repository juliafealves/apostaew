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
        Assert.assertEquals("1 - O Brasil vai ser hexa. - Não finalizado", cenario.toString());
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
     * Testa se gera uma exceção caso a numeração seja um número negativo.
     */
    @Test (expected = NumberFormatException.class)
    public void testCenarioNumeracaoNegativa(){
        new Cenario("O Brasil vai ser hexa.", -1);
    }

    /**
     * Testa se gera uma exceção caso a numeração seja igual a zero.
     */
    @Test (expected = NumberFormatException.class)
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
        Assert.assertEquals("1 - O Brasil vai ser hexa. - Não finalizado", this.cenario.toString());
    }
}
