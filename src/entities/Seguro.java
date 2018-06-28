package entities;

public abstract class Seguro {
    protected int valor;

    /**
     * Calcula o valor do seguro.
     * @return Valor do seguro a ser descontado do caixa.
     */
    public abstract int calculaValor();

    /**
     * Formatação textual da apostaSegura.
     * @return
     */
    public abstract String toString();
}
