package entities;

import utils.Validador;

/**
 * Classe responsável por representar uma aposta segura.
 */
public class ApostaSegura extends Aposta {

    /**
     * Tipo de seguro da aposta.
     */
    Seguro seguro;

    /**
     * Inicializa uma aposta segura por valor.
     *
     * @param id Identificador da aposta segura.
     * @param apostador Nome do apostador.
     * @param valor Valor da aposta.
     * @param previsao Previsão da Aposta: N VAI ACONTECER ou VAI ACONTECER
     * @param valorAssegurado Valor assegurado da aposta.
     */
    public ApostaSegura(int id, String apostador, int valor, String previsao, int valorAssegurado) {
        super(id, apostador, valor, previsao);
        this.valida(valorAssegurado);
        this.seguro = new SeguroValor(valorAssegurado);
    }

    /**
     * Inicializa uma aposta segura por taxa.
     *
     * @param id Identificador da aposta segura.
     * @param apostador Nome do apostador.
     * @param valor Valor da aposta.
     * @param previsao Previsão da Aposta: N VAI ACONTECER ou VAI ACONTECER
     * @param taxa Taxa da aposta.
     */
    public ApostaSegura(int id, String apostador, int valor, String previsao, double taxa) {
        super(id, apostador, valor, previsao);
        this.valida(taxa);
        this.seguro = new SeguroTaxa(valor, taxa);
    }

    /**
     * Retorna a representação textual das informações de aposta assegurada.
     *
     * @return Formatação textual: Apostador - R$ Valor em reais - Previsão - Tipo do Seguro.
     */
    @Override
    public String toString() {
        return super.toString() + this.seguro.toString();
    }

    /**
     * Valida os dados específicos da aposta assegurada por valor.
     *
     * @param valorAssegurado Valor assegurado não poderá ser menor ou igual a 0.
     */
    private void valida(int valorAssegurado) {
        Validador.validaNumeroPositivo(valorAssegurado, "Valor assegurado nao pode ser inferior ou igual a 0", false);
    }

    /**
     * Valida os dados específicos da aposta assegurada por taxa.
     *
     * @param taxa Taxa não poderá ser menor ou igual a 0.
     */
    private void valida(double taxa) {
        Validador.validaNumeroPositivo(taxa, "Taxa nao pode ser inferior ou igual a 0", false);
    }
}
