package entities;

import enums.EstadoEnum;
import utils.Validador;

import java.util.ArrayList;
import java.util.Objects;

public class Cenario {
    private String descricao;
    private EstadoEnum estado;
    private int numeracao;
    private ArrayList<Aposta> apostas;

    /**
     * Cria um objeto Cenario.
     * @param descricao A descrição não pode ser vazia ou nula.
     */
    public Cenario(String descricao, int numeracao){
        Validador.validaNaoNulo(descricao, "Erro no cadastro de cenario: Descricao nao pode ser nula");
        Validador.validaStringNaoVazia(descricao, "Erro no cadastro de cenario: Descricao nao pode ser vazia");
        Validador.validaNumeroPositivo(numeracao, "Erro no cadastro de cenario: Numeracao nao pode ser inferior ou igual a 0", false);

        this.descricao = descricao;
        this.numeracao = numeracao;
        this.estado = EstadoEnum.NAO_FINALIZADO;
        this.apostas = new ArrayList<>();
    }

    /**
     * Verifica se dois objetos Cenários são iguais, através do atributo numeracao.
     * @param objeto Objeto a ser analizado.
     * @return Retorna true caso possuam a mesma numeração.
     */
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null || getClass() != objeto.getClass()) return false;
        Cenario cenario = (Cenario) objeto;
        return numeracao == cenario.numeracao;
    }

    /**
     * Gera o hash através do atributo numeracao.
     * @return Hash referente a numeracao.
     */
    @Override
    public int hashCode() {
        return Objects.hash(numeracao);
    }

    /**
     * Formatação textual do cenário.
     * @return String formatada: DESCRICAO - ESTADO
     */
    public String toString(){
        return this.numeracao + " - " + this.descricao + " - " + this.estado.getEstado();
    }

    /**
     * Cadastra uma aposta no cenário.
     * @param apostador Nome do apostador
     * @param valor Valor da Aposta.
     * @param previsao Previsão da aposta.
     */
    public void cadastraAposta(String apostador, int valor, String previsao) {
        this.apostas.add(new Aposta(apostador, valor, previsao));
    }

    /**
     * Retorna o total das apostas cadastradas em um cenário.
     * @return Total de apostas feitas.
     */
    public int obtemTotalApostas() {
        return this.apostas.size();
    }
}
