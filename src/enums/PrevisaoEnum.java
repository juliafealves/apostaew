package enums;

/**
 * Classe responsável por representar os diferentes tipos de previsão de uma aposta.
 */
public enum PrevisaoEnum {
    NAO_VAI_ACONTECER("N VAI ACONTECER"),
    VAI_ACONTECER("VAI ACONTECER");

    private String previsao;

    /**
     * Construtor privado para instanciar as previsões do Enum.
     *
     * @param previsao Previsão das apostas.
     */
    PrevisaoEnum(String previsao){
        this.previsao = previsao;
    }

    /**
     * Retorna o valor da previsão.
     *
     * @return Retorna o valor em String do previsão.
     */
    public String toString(){
        return this.previsao;
    }
}
