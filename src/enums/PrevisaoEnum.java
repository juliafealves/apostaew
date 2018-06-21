package enums;

public enum PrevisaoEnum {
    NAO_VAI_ACONTECER("N VAI ACONTECER"),
    VAI_ACONTECER("VAI ACONTECER");

    private String previsao;

    PrevisaoEnum(String previsao){
        this.previsao = previsao;
    }

    /**
     * Retorna o valor da previsão.
     * @return Retorna o valor em String do previsão.
     */
    public String getPrevisao(){
        return this.previsao;
    }
}
