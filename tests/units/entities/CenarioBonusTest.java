package units.entities;

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
        this.cenario = new CenarioBonus("O resultado do dado será maior que três", 1, 1000);
    }

    /**
     * Testa a criação do objeto CenarioBonus.
     */
    @Test
    public void testCriaCenario(){
        new CenarioBonus("A Alemanha vai ser goleada pelo Brasil.", 1, 100);
    }

    /**
     * Testa se gera uma exceção caso o bonus seja um número negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioBonusNegativo(){
        new CenarioBonus("Popoyer vai salvar a Olívia Palito.", 1, -100);
    }

    /**
     * Testa se gera uma exceção caso a bônus seja igual a zero.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioBonusIgualAZero(){
        new CenarioBonus("Maurício será promovido.", 1, 0);
    }

    /**
     * Testa o método toString do CenarioBonus.
     * Formato textual: Numeracao - Descricao - Estado - Bonus
     */
    @Test
    public void testToString(){
        Assert.assertEquals("1 - O resultado do dado será maior que três - Nao finalizado - R$ 10,00", this.cenario.toString());
    }

//    /**
//     * Testa o calculo do rateio do cenário de bônus para as apostas ganhadoras. O valor do rateio remove o valor destinado ao caixa.
//     */
//    @Test
//    public void testCalculaRateio(){
//        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
//        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
//        this.cenario.adicionaAposta("Jose da Sorte", 1000, "VAI ACONTECER");
//        this.cenario.finaliza(true);
//        Assert.assertEquals(2800, this.cenario.calculaRateio(0.1));
//    }
}
