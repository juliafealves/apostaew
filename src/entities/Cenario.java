package entities;

import enums.EstadoEnum;
import enums.PrevisaoEnum;
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
        return this.numeracao + " - " + this.descricao + " - " + this.estado;
    }

    /**
     * Adiciona uma aposta no cenário.
     *
     * @param apostador Nome do apostador
     * @param valor Valor da Aposta.
     * @param previsao Previsão da aposta.
     */
    public void adicionaAposta(String apostador, int valor, String previsao) {
        this.apostas.add(new Aposta(apostador, valor, previsao));
    }

    /**
     * Finaliza um cenário e calcula os valores das apostas.
     * @param ocorreu
     * @return
     */
    public int finaliza(boolean ocorreu) {
        if (ocorreu)
            this.estado = EstadoEnum.FINALIZADO_OCORREU;
        else
            this.estado = EstadoEnum.FINALIZADO_NAO_OCORREU;

        return this.calculaApostas();
    }

    /**
     * Verifica se um cenário foi finalizado.
     * @return
     */
    public boolean finalizado(){
        return this.estado.toString().equals(EstadoEnum.FINALIZADO_OCORREU) ||
                this.estado.toString().equals(EstadoEnum.FINALIZADO_NAO_OCORREU);
    }

    /**
     * Calcula os valores das apostas de acordo com o resultado da previsão.
     * @return Total de apostas realizadas.
     */
    public int calculaApostas() {
        if(this.finalizado()) {
            if (this.estado.equals(EstadoEnum.FINALIZADO_OCORREU))
                return apostas.stream().filter(aposta -> !aposta.getPrevisao().equals(PrevisaoEnum.VAI_ACONTECER)).mapToInt(Aposta::getValor).sum();

            return apostas.stream().filter(aposta -> !aposta.getPrevisao().equals(PrevisaoEnum.NAO_VAI_ACONTECER)).mapToInt(Aposta::getValor).sum();
        }

        return 0;
    }

    /**
     * Retorna o total de apostas cadastradas em um cenário.
     *
     * @return Total de apostas feitas.
     */
    public int obtemTotalApostas() {
        return this.apostas.size();
    }

    /**
     * Retorna a soma do valor total das apostas.
     *
     * @return Total das apostas realizadas.
     */
    public int obtemValorTotalApostas(){
        return apostas.stream().mapToInt(Aposta::getValor).sum();
    }

    /**
     * Listas as apostas de um cenário.
     *
     * @return Representação textual: Apostador - Valor - Previsão.
     */
    public String listaApostas(){
        StringBuilder apostas = new StringBuilder();

        for(Aposta aposta : this.apostas)
            apostas.append(aposta).append(System.lineSeparator());

        return apostas.toString();
    }

    public int calculaRateioApostas(double taxa) {
        if(this.finalizado()) {
            if (this.estado.equals(EstadoEnum.FINALIZADO_OCORREU))
                return (int) (apostas.stream().filter(aposta -> !aposta.getPrevisao().equals(PrevisaoEnum.NAO_VAI_ACONTECER)).mapToInt(Aposta::getValor).sum() * taxa);

            return (int) (apostas.stream().filter(aposta -> !aposta.getPrevisao().equals(PrevisaoEnum.VAI_ACONTECER)).mapToInt(Aposta::getValor).sum() * taxa);
        }

        return 0;
    }
}
