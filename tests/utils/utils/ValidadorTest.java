package utils.utils;

import org.junit.Assert;
import org.junit.Test;
import utils.Validador;

import java.util.ArrayList;
import java.util.List;

public class ValidadorTest {

    /**
     * Testa se valida dados não nulos, e retorna true.
     */
    @Test
    public void testValidaNaoNulo(){
        Assert.assertTrue(Validador.validaNaoNulo("Nao Nulo", "Mensagem"));
    }

    /**
     * Testa se é lançada exceção caso envie um dado nulo.
     */
    @Test (expected = NullPointerException.class)
    public void testValidaNaoNuloComObjetoNulo(){
        Validador.validaNaoNulo(null, "Mensagem");
    }

    /**
     * Testa se valida dados não nulos, e retorna true.
     */
    @Test
    public void testValidaStringNaoVazia(){
        Assert.assertTrue(Validador.validaStringNaoVazia("Nao Vazio", "Mensagem"));
    }

    /**
     * Testa se valida um número positivo sem o zero.
     */
    @Test
    public void testValidaNumeroPositivoSemZero(){
        Assert.assertTrue(Validador.validaNumeroPositivo(1, "Mensagem", false));
    }

    /**
     * Testa se valida um número positivo sem o zero.
     */
    @Test
    public void testValidaNumeroPositivoComZero(){
        Assert.assertTrue(Validador.validaNumeroPositivo(0, "Mensagem", true));
    }

    /**
     * Testa se é lançada exceção caso envie um número zero quando não é permitido.
     */
    @Test (expected = NumberFormatException.class)
    public void testValidaNumeroPositivoSemZeroNumeroSendoZero(){
        Assert.assertTrue(Validador.validaNumeroPositivo(0, "Mensagem", false));
    }

    /**
     * Testa se é lançada exceção caso envie um numero negativo quando não é permitido.
     */
    @Test (expected = NumberFormatException.class)
    public void testValidaNumeroPositivoComNumeroNegativo(){
        Assert.assertTrue(Validador.validaNumeroPositivo(-1, "Mensagem", false));
    }

    /**
     * Testa se é o índice atende aos limites da Collection.
     */
    @Test
    public void testValidaIndiceColecao(){
        ArrayList<String> colecao = new ArrayList<>();
        colecao.add("Teste");
        Assert.assertTrue(Validador.validaIndiceColecao(0, 1, "Mensagem"));
    }

    /**
     * Testa se é lançada exceção caso índice esteja fora dos limites do tamanho da Collection.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testValidaIndiceColecaoIndiceAcimaDoTamanho(){
        Validador.validaIndiceColecao(2, 1, "Mensagem");
    }

    /**
     * Testa se é lançada exceção caso índice esteja fora dos limites do tamanho da Collection.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testValidaIndiceColecaoSemElementosAdicionados(){
        Validador.validaIndiceColecao(1, 0, "Mensagem");
    }
}
