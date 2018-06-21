package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Validador {

    /**
     * Valida se um objeto é não nulo.
     * @param objeto Objeto que não deve ser nulo.
     * @param mensagem Mensagem gerada na exceção.
     * @return Returna true caso o objeto seja não nulo.
     */
    public static boolean validaNaoNulo(Object objeto, String mensagem){
        if(objeto == null)
            throw new NullPointerException(mensagem);

        return true;
    }

    /**
     * Valida se uma String não é vazia.
     * @param valor String que não deve ser nulo.
     * @param mensagem Mensagem gerada na exceção.
     * @return Returna true caso o objeto seja não nulo.
     */
    public static boolean validaStringNaoVazia(String valor, String mensagem){
        if(valor.isEmpty())
            throw new IllegalArgumentException(mensagem);

        return true;
    }

    /**
     * Valida um numero positivo.
     * @param numero Numero a ser validado.
     * @param mensagem Mensagem personalizada.
     * @param inclueZero Caso inclua o 0 como número válido (true), caso contrário false.
     * @return Retorna true caso o número é positivo (incluindo zero ou não).
     */
    public static boolean validaNumeroPositivo(int numero, String mensagem, boolean inclueZero){
        if ((numero <= 0 && !inclueZero) || (numero < 0 && inclueZero))
            throw new NumberFormatException(mensagem);

        return true;
    }

    /**
     * Verifica o índice da coleção é válido.
     *
     * @param indice Indice do ArrayList
     * @param mensagem Mensagem personalizada.
     * @return Retorna true caso o índice seja maior ou igual a 0, ou menor que o tamanho.
     */
    public static boolean validaIndiceColecao(int indice, int tamanho, String mensagem) {
        if (indice >= tamanho || indice < 0)
            throw new IndexOutOfBoundsException(mensagem);

        return true;
    }
}
