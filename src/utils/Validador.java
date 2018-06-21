package utils;

import java.util.ArrayList;

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
     * Verifica se existe um elemento na coleção a partir do índice.
     * @param colecao
     * @param indice
     * @param campo
     * @return
     */
    public boolean validaElementoInexistente(ArrayList<Object> colecao, int indice, String campo) {
        try{
            colecao.get(indice);
            return true;
        } catch (IndexOutOfBoundsException exception){
            throw new IndexOutOfBoundsException(String.format("%s não existe na coleção.", campo));
        }
    }
}
