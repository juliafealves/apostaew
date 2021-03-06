package entities;

import enums.PrevisaoEnum;
import utils.Validador;

import java.util.Objects;

/**
 * Classe responsável por representar uma aposta.
 */
public class Aposta {

    /**
     * Identificador único.
     */
    private int id;

    /**
     * Valor da aposta.
     */
    protected int valor;

    /**
     * Nome do apostador
     */
    private String apostador;

    /**
     * Previsão da aposta: N VAI ACONTECER ou VAI ACONTECER
     */
    private PrevisaoEnum previsao;

    /**
     * Inicializa uma aposta.
     *
     * @param apostador Nome do apostador.
     * @param valor Valor da aposta.
     * @param previsao Previsão da Aposta: N VAI ACONTECER ou VAI ACONTECER
     */
    public Aposta(int id, String apostador, int valor, String previsao) {
        this.valida(id, apostador, valor, previsao);
        this.id = id;
        this.apostador = apostador;
        this.valor = valor;
        this.previsao = previsao.equalsIgnoreCase(PrevisaoEnum.VAI_ACONTECER.toString()) ? PrevisaoEnum.VAI_ACONTECER : PrevisaoEnum.NAO_VAI_ACONTECER;
    }

    /**
     * Verifica se dois objetos Apostas são iguais, através do atributo id.
     *
     * @param objeto Objeto a ser analizado.
     * @return Retorna true caso possuam a mesma id.
     */
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null || getClass() != objeto.getClass()) return false;
        Aposta aposta = (Aposta) objeto;
        return this.id == aposta.id;
    }
    /**
     * Retorna a previsão da aposta.
     * @return Retorna a previsão da aposta.
     */
    public PrevisaoEnum getPrevisao() {
        return previsao;
    }

    /**
     * Retorna a previsão perdedora do cenário.
     *
     * @param ocorreu Ocorreu o evento do cenário da aposta.
     * @return Retorna o tipo de previsão perdedora.
     */
    public static PrevisaoEnum getPrevisaoPerdedora(boolean ocorreu){
        if(ocorreu)
            return PrevisaoEnum.NAO_VAI_ACONTECER;

        return PrevisaoEnum.VAI_ACONTECER;
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
     * Gera o hash através do atributo id.
     *
     * @return Hash referente o id.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Retorna a representação textual das informações de aposta.
     *
     * @return Formatação textual: Apostador - R$ Valor em reais - Previsão.
     */
    @Override
    public String toString() {
        return this.apostador + " - R$" + String.format("%.2f", this.valor / 100.0) + " - " + this.previsao;
    }

    /**
     * Realiza as validações de aposta.
     *
     * @param id Identificador não poderá ser menor ou igual a 0.
     * @param apostador Nome do apostador não poderá ser nulo ou vazio.
     * @param valor Valor da aposta não poderá ser menor ou igual a 0.
     * @param previsao Previsão da Aposta só poderá ser N VAI ACONTECER ou VAI ACONTECER, não podendo ser nula ou vazia.
     */
    private void valida(int id, String apostador, int valor, String previsao){
        Validador.validaNumeroPositivo(id, "Id nao pode ser menor ou igual a zero", false);
        Validador.validaNaoNulo(apostador, "Apostador nao pode ser vazio ou nulo");
        Validador.validaStringNaoVazia(apostador, "Apostador nao pode ser vazio ou nulo");
        Validador.validaNumeroPositivo(valor, "Valor nao pode ser menor ou igual a zero", false);
        Validador.validaNaoNulo(previsao, "Previsao nao pode ser vazia ou nula");
        Validador.validaStringNaoVazia(previsao, "Previsao nao pode ser vazia ou nula");
        String[] previsoes = { PrevisaoEnum.NAO_VAI_ACONTECER.toString(), PrevisaoEnum.VAI_ACONTECER.toString() };
        Validador.validaStringIguais(previsao, previsoes, "Previsao invalida");
    }
}
