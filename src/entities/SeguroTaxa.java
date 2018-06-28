package entities;

public class SeguroTaxa extends Seguro {

    private double taxa;

    public SeguroTaxa(int valor, double taxa) {
        this.valor = valor;
        this.taxa = taxa;
    }

    /**
     * Retorna o valor do seguro.
     *
     * @return
     */
    @Override
    public int calculaValor() {
        return (int)(this.valor * this.taxa);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return " - ASSEGURADA (TAXA) - " + (int) (this.taxa * 100) + "%";
    }
}
