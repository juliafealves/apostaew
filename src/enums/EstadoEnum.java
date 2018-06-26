package enums;

/**
 * Classe responsável por representar os diferentes tipos de estados de um cenário de aposta.
 */
public enum EstadoEnum {
    NAO_FINALIZADO("Nao finalizado"),
    FINALIZADO_NAO_OCORREU("Finalizado (nao ocorreu)"),
    FINALIZADO_OCORREU("Finalizado (ocorreu)");

    private String estado;

    /**
     * Construtor privado para instaciar os estados do Enum.
     * @param estado Estado das apostas.
     */
    EstadoEnum(String estado){
        this.estado = estado;
    }

    /**
     * Retorna o valor do estado.
     * @return Retorna o valor em String do estado.
     */
    public String toString(){
        return this.estado;
    }
}
