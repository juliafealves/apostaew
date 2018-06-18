package utils;

import org.junit.Test;

import java.util.ArrayList;

public class ValidadorTest {
    private Validador validador;

    public void criaValidador(){
        this.validador = new Validador();
    }

    @Test
    public void testElementoInexistente(){
        this.validador.validaElementoInexistente(new ArrayList<>(), 1);
    }
}
