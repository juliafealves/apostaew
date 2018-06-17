package enums;

public enum EstadoEnum {
    NAO_FINALIZADO("Não finalizado"),
    FINALIZADO_NAO_OCORREU("Finalizado (não ocorreu)"),
    FINALIZADO_OCORREU("Finalizado (ocorreu)");

    private String estado;

    EstadoEnum(String estado){
        this.estado = estado;
    }

    /**
     * Retorna o valor do estado.
     * @return Retorna o valor em String do estado.
     */
    public String getEstado(){
        return this.estado;
    }
}
