package utils;

import java.util.ArrayList;

public class Validador {
    protected boolean validaNaoNulo(Object objeto, String descricao){
        String mensagem = String.format("%s não poderá ser nulo.", descricao);

        if(objeto == null)
            throw new NullPointerException(mensagem);

        return true;
    }

    protected boolean validaNaoVazio(String valor, String campo){
        String mensagem = String.format("%s não poderá ser nulo.", campo);

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
