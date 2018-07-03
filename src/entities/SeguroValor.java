package entities;

/**
 * Responsável por definir os métodos e atributos de um seguro do tipo valor.
 */
public class SeguroValor extends Seguro {

    /**
     * Inicializa o seguro do tipo valor.
     *
     * @param valor Valor assegurado da aposta.
     */
    public SeguroValor(int valor){
        this.valor = valor;
    }

    /**
     * Calcula o valor do seguro.
     *
     * @return Valor do seguro a ser descontado do caixa.
     */
    @Override
    public int calculaValor() {
        return this.valor;
    }

    /**
     * Formatação textual da segura.
     *
     * @return Formatação textual.
     */
    @Override
    public String toString() {
        return " - ASSEGURADA (VALOR) - R$ " + String.format("%.2f", this.valor / 100.0);
    }
}
