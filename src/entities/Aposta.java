package entities;

import enums.PrevisaoEnum;
import utils.Validador;

/**
 * Classe responsável por representar uma aposta.
 */
public class Aposta {
    private PrevisaoEnum previsao;
    private int valor;
    private String apostador;

    /**
     * Inicializa uma aposta.
     *
     * @param apostador Nome do apostador.
     * @param valor Valor da aposta.
     * @param previsao Previsão da Aposta: N VAI ACONTECER ou VAI ACONTECER
     */
    public Aposta(String apostador, int valor, String previsao) {
        this.validaAposta(apostador, valor, previsao);
        this.apostador = apostador;
        this.valor = valor;
        this.previsao = previsao.equalsIgnoreCase(PrevisaoEnum.VAI_ACONTECER.toString()) ? PrevisaoEnum.VAI_ACONTECER : PrevisaoEnum.NAO_VAI_ACONTECER;
    }

    /**
     * Retorna o valor da aposta.
     *
     * @return Valor da Aposta.
     */
    public int getValor() {
        return valor;
    }

    /**
     * Retorna a previsão da aposta.
     * @return
     */
    public PrevisaoEnum getPrevisao() {
        return previsao;
    }

    /**
     * Retorna a representação textual das informações de Aposta.
     * @return Formatação textual: Apostador - R$ Valor em reais - Previsão.
     */
    @Override
    public String toString() {
        return this.apostador + " - R$" + String.format("%.2f", this.valor / 100.0) + " - " + this.previsao;
    }

    /**
     * Realiza as validações de aposta.
     *
     * @param apostador Nome do apostador.
     * @param valor Valor da aposta.
     * @param previsao Previsão da Aposta: N VAI ACONTECER ou VAI ACONTECER
     */
    private void validaAposta(String apostador, int valor, String previsao){
        Validador.validaNaoNulo(apostador, "Apostador nao pode ser vazio ou nulo");
        Validador.validaStringNaoVazia(apostador, "Apostador nao pode ser vazio ou nulo");
        Validador.validaNumeroPositivo(valor, "Valor nao pode ser menor ou igual a zero", false);
        Validador.validaNaoNulo(previsao, "Previsao nao pode ser vazia ou nula");
        Validador.validaStringNaoVazia(previsao, "Previsao nao pode ser vazia ou nula");
        String[] previsoes = { PrevisaoEnum.NAO_VAI_ACONTECER.toString(), PrevisaoEnum.VAI_ACONTECER.toString() };
        Validador.validaStringIguais(previsao, previsoes, "Previsao invalida");
    }
}
