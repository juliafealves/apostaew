package units.utils;

import org.junit.Assert;
import org.junit.Test;
import utils.Validador;

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
     * Testa se valida um número inteiro positivo com o zero.
     */
    @Test
    public void testValidaNumeroPositivoComZero(){
        Assert.assertTrue(Validador.validaNumeroPositivo(0, "Mensagem", true));
    }

    /**
     * Testa se é lançada exceção caso envie valide um número zero quando não é permitido.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testValidaNumeroPositivoSemZeroNumeroSendoZero(){
        Assert.assertTrue(Validador.validaNumeroPositivo(0, "Mensagem", false));
    }

    /**
     * Testa se é lançada exceção caso envie um número negativo quando não é permitido.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testValidaNumeroPositivoComNumeroNegativo(){
        Assert.assertTrue(Validador.validaNumeroPositivo(-1, "Mensagem", false));
    }

    /**
     * Testa se é o índice atende aos limites da Collection.
     */
    @Test
    public void testValidaIndiceColecao(){
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
     * Testa se é lançada exceção caso índice seja igual o tamanho da Collection.
     * Lembrando que o indice inicia de 0, portanto, não índice e tamanho da coleção nunca será igual.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testValidaIndiceColecaoIndiceIgualTamanho(){
        Validador.validaIndiceColecao(1, 1, "Mensagem");
    }

    /**
     * Testa se é lançada exceção caso índice esteja fora dos limites do tamanho da Collection.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testValidaIndiceColecaoSemElementosAdicionados(){
        Validador.validaIndiceColecao(1, 0, "Mensagem");
    }

    /**
     * Testa se existem strings iguais na coleção.
     */
    @Test
    public void testValidaStringIguaisColecao(){
        String[] strings = {"String Tal", "OUTRA STRING"};
        Assert.assertTrue(Validador.validaStringIguais("String Tal", strings, "Mensagem"));
    }

    /**
     * Testa se existem strings iguais na coleção mesmo com "cases" diferentes.
     */
    @Test
    public void testValidaStringIguaisColecaoCases(){
        String[] strings = {"String Tal", "OUTRA STRING"};
        Assert.assertTrue(Validador.validaStringIguais("STRING tal", strings, "Mensagem"));
    }

    /**
     * Testa se é lançada exceção caso busque uma string inexistente na lista de strings.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testValidaStringIguaisColecaoStringInexistente(){
        String[] strings = {"String Tal", "OUTRA STRING"};
        Assert.assertTrue(Validador.validaStringIguais("Uma terceira string", strings, "Mensagem"));
    }
}
