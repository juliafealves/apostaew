package entities;

/**
 * Responsável por definir os métodos e atributos de um seguro do tipo taxa.
 */
public class SeguroTaxa extends Seguro {

    /**
     * Taxa da aposta.
     */
    private double taxa;

    /**
     * Inicializa o seguro do tipo taxa.
     *
     * @param valor Valor da aposta.
     * @param taxa Taxa da aposta.
     */
    public SeguroTaxa(int valor, double taxa) {
        this.valor = valor;
        this.taxa = taxa;
    }

    /**
     * Calcula o valor do seguro.
     *
     * @return Valor do seguro a ser descontado do caixa.
     */
    @Override
    public int calculaValor() {
        return (int)(this.valor * this.taxa);
    }

    /**
     * Formatação textual da segura.
     *
     * @return Formatação textual.
     */
    @Override
    public String toString() {
        return " - ASSEGURADA (TAXA) - " + (int) (this.taxa * 100) + "%";
    }
}
