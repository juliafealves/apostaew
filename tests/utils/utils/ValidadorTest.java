package utils.utils;

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
}
