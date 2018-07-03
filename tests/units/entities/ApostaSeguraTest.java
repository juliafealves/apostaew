package units.entities;

import entities.ApostaSegura;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste de Aposta.
 * @author Júlia Fernandes Alves (julia.alves@ccc.ufcg.edu.br)
 */
public class ApostaSeguraTest {
    private ApostaSegura apostaSeguraValor;
    private ApostaSegura apostaSeguraTaxa;

    /**
     * Cria um objeto ApostaSegura para servir de base para os testes unitários.
     */
    @Before
    public void criaAposta(){
        this.apostaSeguraValor = new ApostaSegura(1,"José da Sorte", 1000, "N VAI ACONTECER", 100);
        this.apostaSeguraTaxa = new ApostaSegura(1,"José da Sorte", 1000, "N VAI ACONTECER", 0.1);
    }

    /**
     * Testa a criação do objeto Aposta.
     */
    @Test
    public void testApostaSeguraSeguraValor(){
        new ApostaSegura(1, "José da Sorte", 1000, "N VAI ACONTECER", 100);
        new ApostaSegura(1, "José da Sorte", 1000, "N VAI ACONTECER", 0.1);
    }

    /**
     * Testa a modificação do tipo de seguro.
     */
    @Test
    public void testModificaSeguroValor(){
        this.apostaSeguraTaxa.modificaSeguro(1000);
        Assert.assertEquals("José da Sorte - R$10,00 - N VAI ACONTECER - ASSEGURADA (VALOR) - R$ 10,00", this.apostaSeguraTaxa.toString());
    }

    /**
     * Testa se gera uma exceção caso o valor assegurado seja negativo ao alterar o tipo de seguro.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testModificaSeguroValorNegativo(){
        this.apostaSeguraTaxa.modificaSeguro(-1000);
    }

    /**
     * Testa se gera uma exceção caso o valor assegurado seja zero ao alterar o tipo de seguro.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testModificaSeguroValorZero(){
        this.apostaSeguraTaxa.modificaSeguro(0);
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador seja vazio.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testApostaSeguraApostadorVazio(){
        new ApostaSegura(1, "", 1000, "N VAI ACONTECER", 1000);
        new ApostaSegura(1, "", 1000, "N VAI ACONTECER", 0.1);
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador esteja preenchido com caracteres em branco.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testApostaSeguraApostadorEspacoEmBranco(){
        new ApostaSegura(1, " ", 1000, "N VAI ACONTECER", 100);
        new ApostaSegura(1, " ", 1000, "N VAI ACONTECER", 0.1);
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador seja nulo.
     */
    @Test (expected = NullPointerException.class)
    public void testApostaSeguraApostadorNulo(){
        new ApostaSegura(1,null, 1000, "N VAI ACONTECER", 100);
        new ApostaSegura(1,null, 1000, "N VAI ACONTECER", 0.1);
    }

    /**
     * Testa se gera uma exceção caso o valor seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testApostaSeguraValorNegativo(){
        new ApostaSegura(1,"Jose da Sorte", -1, "N VAI ACONTECER", 1000);
        new ApostaSegura(1,"Jose da Sorte", -1, "N VAI ACONTECER", 0.1);
    }

    /**
     * Testa se gera uma exceção caso o valor seja igual 0.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testApostaSeguraValorZero(){
        new ApostaSegura(1,"Jose da Sorte", 0, "N VAI ACONTECER", 1000);
        new ApostaSegura(1,"Jose da Sorte", 0, "N VAI ACONTECER", 0.1);
    }

    /**
     * Testa se gera uma exceção caso a previsão seja vazia.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testApostaSeguraPrevisaoVazio(){
        new ApostaSegura(1,"Jose da Sorte", 1000, "", 1000);
        new ApostaSegura(1,"Jose da Sorte", 1000, "", 0.1);
    }

    /**
     * Testa se gera uma exceção caso a previsão esteja preenchido com caracteres em branco.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testApostaSeguraPrevisaoEmBranco(){
        new ApostaSegura(1,"Jose da Sorte", 1000, "  ", 1000);
        new ApostaSegura(1,"Jose da Sorte", 1000, "  ", 0.1);
    }

    /**
     * Testa se gera uma exceção caso a previsão seja nula.
     */
    @Test (expected = NullPointerException.class)
    public void testApostaSeguraPrevisaoNulo(){
        new ApostaSegura(1,"Jose da Sorte", 1000, null, 100);
        new ApostaSegura(1,"Jose da Sorte", 1000, null, 0.1);
    }

    /**
     * Testa se gera uma exceção caso a previsão seja inválida.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testApostaSeguraPrevisaoInvalida(){
        new ApostaSegura(1,"Jose da Sorte", 1000, "NAO EXISTE ESSA PREVISAO", 100);
        new ApostaSegura(1,"Jose da Sorte", 1000, "NAO EXISTE ESSA PREVISAO", 0.1);
    }

    /**
     * Testa se gera uma exceção caso o valor assegurado seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testApostaSeguraValorAsseguradoNegativo(){
        new ApostaSegura(1,"Jose da Sorte", 1000, "N VAI ACONTECER", -1);
    }

    /**
     * Testa se gera uma exceção caso o valor assegurado seja igual 0.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testApostaSeguraValorAsseguradoZero(){
        new ApostaSegura(1,"Jose da Sorte", 1000, "N VAI ACONTECER", 0);
    }

    /**
     * Testa se gera uma exceção caso a taxa seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testApostaSeguraTaxaNegativo(){
        new ApostaSegura(1,"Jose da Sorte", 1000, "N VAI ACONTECER", -1.0);
    }

    /**
     * Testa se gera uma exceção caso a taxa seja igual 0.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testApostaSeguraTaxaZero(){
        new ApostaSegura(1,"Jose da Sorte", 1000, "N VAI ACONTECER", 0.0);
    }

    /**
     * Testa o método toString da Aposta. Formato: Apostador - Valor (em reais) - Previsão
     */
    @Test
    public void testToString(){
        Assert.assertEquals("José da Sorte - R$10,00 - N VAI ACONTECER - ASSEGURADA (VALOR) - R$ 1,00", this.apostaSeguraValor.toString());
        Assert.assertEquals("José da Sorte - R$10,00 - N VAI ACONTECER - ASSEGURADA (TAXA) - 10%", this.apostaSeguraTaxa.toString());
    }
}
