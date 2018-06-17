package entities;

import enums.EstadoEnum;

public class Cenario {
    private String descricao;
    private EstadoEnum estado;

    /**
     * Cria um objeto Cenario.
     * @param descricao A descrição não pode ser vázia ou nula.
     */
    public Cenario(String descricao){
        if(descricao == null)
            throw new NullPointerException("Descrição não deve ser nula.");

        if(descricao.isEmpty())
            throw new IllegalArgumentException("Descrição não deve ser vazia.");

        this.descricao = descricao;
        this.estado = EstadoEnum.NAO_FINALIZADO;
    }

    /**
     * Formatação textual do cenário.
     * @return String formatada: DESCRICAO - ESTADO
     */
    public String toString(){
        return this.descricao + " - " + this.estado.getEstado();
    }
}
