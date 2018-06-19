package utils;

import org.junit.Assert;
import org.junit.Test;

public class ValidadorTest {

    public void testValidaNuloNaoNulo(){
        Assert.assertTrue(Validador.validaNaoNulo("Nao Nulo", "Não Nulo."));
    }
}
