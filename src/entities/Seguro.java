package entities;

/**
 * Responsável por definir os métodos e atributos de um seguro.
 */
public abstract class Seguro {
    /**
     * Valor assegurado.
     */
    protected int valor;

    /**
     * Calcula o valor do seguro.
     *
     * @return Valor do seguro a ser descontado do caixa.
     */
    public abstract int calculaValor();

    /**
     * Formatação textual da segura.
     *
     * @return Formatação textual.
     */
    public abstract String toString();
}
