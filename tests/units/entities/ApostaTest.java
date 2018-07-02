package units.entities;

import entities.Aposta;
import enums.PrevisaoEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * Classe de teste de Aposta.
 * @author Júlia Fernandes Alves (julia.alves@ccc.ufcg.edu.br)
 */
public class ApostaTest {
    private Aposta aposta;

    /**
     * Cria um objeto Aposta para servir de base para os testes unitários.
     */
    @Before
    public void criaAposta(){
        this.aposta = new Aposta(1,"José da Sorte", 1000, "N VAI ACONTECER");
    }

    /**
     * Testa a criação do objeto Aposta.
     */
    @Test
    public void testAposta(){
        new Aposta(1, "José da Sorte", 1000, "N VAI ACONTECER");
    }

    /**
     * Testa o método que retorna o valor da aposta.
     */
    @Test
    public void testGetValor(){
        Assert.assertEquals(1000, this.aposta.getValor());
    }

    /**
     * Testa o método que retorna a previsão da aposta.
     */
    @Test
    public void testGetPrevisao(){
        Assert.assertEquals(PrevisaoEnum.NAO_VAI_ACONTECER, this.aposta.getPrevisao());
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador seja vazio.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testApostaApostadorVazio(){
        new Aposta(1, "", 1000, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador esteja preenchido com caracteres em branco.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testApostaApostadorEspacoEmBranco(){
        new Aposta(1, " ", 1000, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador seja nulo.
     */
    @Test (expected = NullPointerException.class)
    public void testApostaApostadorNulo(){
        new Aposta(1,null, 1000, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso o valor seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testApostaValorNegativo(){
        new Aposta(1,"Jose da Sorte", -1, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso o valor seja igual 0.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testApostaValorZero(){
        new Aposta(1,"Jose da Sorte", 0, "N VAI ACONTECER");
    }

    /**
     * Testa se gera uma exceção caso a previsão seja vazia.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testApostaPrevisaoVazio(){
        new Aposta(1,"Jose da Sorte", 1000, "");
    }

    /**
     * Testa se gera uma exceção caso a previsão esteja preenchido com caracteres em branco.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testApostaPrevisaoEmBranco(){
        new Aposta(1,"Jose da Sorte", 1000, "  ");
    }

    /**
     * Testa se gera uma exceção caso a previsão seja nula.
     */
    @Test (expected = NullPointerException.class)
    public void testApostaPrevisaoNulo(){
        new Aposta(1,"Jose da Sorte", 1000, null);
    }

    /**
     * Testa se gera uma exceção caso a previsão seja inválida.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testApostaPrevisaoInvalida(){
        new Aposta(1,"Jose da Sorte", 1000, "NAO EXISTE ESSA PREVISAO");
    }

    /**
     * Testa o método toString da Aposta. Formato: Apostador - Valor (em reais) - Previsão
     */
    @Test
    public void testToString(){
        Assert.assertEquals("José da Sorte - R$10,00 - N VAI ACONTECER", this.aposta.toString());
    }
}
