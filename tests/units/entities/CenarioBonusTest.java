package units.entities;

import entities.Cenario;
import entities.CenarioBonus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste de Cenario.
 * @author Júlia Fernandes Alves (julia.alves@ccc.ufcg.edu.br)
 */
public class CenarioBonusTest {
    private CenarioBonus cenario;

    /**
     * Cria um objeto CenarioBonus para servir de base para os testes unitários.
     */
    @Before
    public void criaCenario(){
        this.cenario = new CenarioBonus(1, "O resultado do dado será maior que três", 1000);
    }

    /**
     * Testa a criação do objeto CenarioBonus.
     */
    @Test
    public void testCriaCenario(){
        new CenarioBonus(1, "A Alemanha vai ser goleada pelo Brasil.", 100);
    }

    /**
     * Testa se gera uma exceção caso descrição seja nula.
     */
    @Test (expected = NullPointerException.class)
    public void testCenarioDescricaoNula(){
        new CenarioBonus(1, null, 100);
    }

    /**
     * Testa se gera uma exceção caso descrição seja vazia.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioDescricaoVazia(){
        new CenarioBonus(1, "", 100);
    }

    /**
     * Testa se gera uma exceção caso descrição tenha somente espaços em brancos.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioDescricaoComEspacoEmBranco(){
        new CenarioBonus(1, "     ", 100);
    }

    /**
     * Testa se gera uma exceção caso a identificador seja um número negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioIdNegativo(){
        new CenarioBonus(-1, "Popoyer vai salvar a Olívia Palito.", 100);
    }

    /**
     * Testa se gera uma exceção caso a identificador seja igual a zero.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioIdentificadorIgualAZero(){
        new CenarioBonus(0, "Maurício será promovido.", 100);
    }

    /**
     * Testa se gera uma exceção caso o bonus seja um número negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioBonusNegativo(){
        new CenarioBonus(1, "Popoyer vai salvar a Olívia Palito.", -100);
    }

    /**
     * Testa se gera uma exceção caso a bônus seja igual a zero.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioBonusIgualAZero(){
        new CenarioBonus(1, "Maurício será promovido.", 0);
    }

    /**
     * Testa o método toString do CenarioBonus.
     * Formato textual: Numeracao - Descricao - Estado - Bonus
     */
    @Test
    public void testToString(){
        Assert.assertEquals("1 - O resultado do dado será maior que três - Nao finalizado - R$ 10,00", this.cenario.toString());
    }

    /**
     * Testa o calculo do rateio do cenário de bônus para as apostas ganhadoras. O valor do rateio remove o valor destinado ao caixa.
     */
    @Test
    public void testCalculaRateio(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "VAI ACONTECER");
        this.cenario.adicionaAposta("Maria da Sorte", 1000, "VAI ACONTECER", 100);
        this.cenario.adicionaAposta("Ana da Sorte", 2000, "VAI ACONTECER", 0.1);
        this.cenario.finaliza(false);
        Assert.assertEquals(4600, this.cenario.calculaRateio(0.1));
    }
}
