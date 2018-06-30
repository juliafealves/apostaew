package utils;

/**
 * Classe genérica para validação de dados.
 */
public class Validador {

    /**
     * Valida se um objeto é não nulo.
     *
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
        if(valor.trim().isEmpty())
            throw new IllegalArgumentException(mensagem);

        return true;
    }

    /**
     * Valida se um número inteiro é positivo, incluindo ou não o zero.
     *
     * @param numero Número a ser validado.
     * @param mensagem Mensagem personalizada.
     * @param inclueZero Caso inclua o 0 como número válido (true), caso contrário false.
     * @return Retorna true caso o número é positivo (incluindo zero ou não).
     */
    public static boolean validaNumeroPositivo(int numero, String mensagem, boolean inclueZero){
        if ((numero <= 0 && !inclueZero) || (numero < 0 && inclueZero))
            throw new IllegalArgumentException(mensagem);

        return true;
    }

    /**
     * Valida se um número de ponto flutuante é positivo, incluindo ou não o zero.
     *
     * @param numero Número a ser validado.
     * @param mensagem Mensagem personalizada.
     * @param inclueZero Caso inclua o 0 como número válido (true), caso contrário false.
     * @return Retorna true caso o número é positivo (incluindo zero ou não).
     */
    public static boolean validaNumeroPositivo(double numero, String mensagem, boolean inclueZero){
        if ((numero <= 0 && !inclueZero) || (numero < 0 && inclueZero))
            throw new IllegalArgumentException(mensagem);

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

    /**
     * Verifica se duas strings são iguais dentro de um conjunto de strings.
     *
     * @param string Primeira string.
     * @param outrasStrings Conjunto de strings para comparar.
     * @param mensagem Mensagem personalizada.
     * @return Retorna true alguma das strings sejam iguais a string.
     */
    public static boolean validaStringIguais(String string, String[] outrasStrings, String mensagem) {
        for(String outraString : outrasStrings)
            if (string.equalsIgnoreCase(outraString)) return true;

        throw new IllegalArgumentException(mensagem);
    }
}
