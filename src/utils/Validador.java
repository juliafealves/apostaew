package utils;

public class Validador {
    protected boolean validaNaoNulo(Object objeto, String descricao){
        String mensagem = String.format("%s não poderá ser nulo.", descricao);

        if(objeto == null)
            throw new NullPointerException(mensagem);

        return true;
    }

    protected boolean validaNaoVazio(String campo, String descricao){
        String mensagem = String.format("%s não poderá ser nulo.", descricao);

        if(campo.isEmpty())
            throw new IllegalArgumentException(mensagem);

        return true;
    }
}
