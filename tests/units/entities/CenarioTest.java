package units.entities;

import entities.Cenario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste de Cenario.
 * @author Júlia Fernandes Alves (julia.alves@ccc.ufcg.edu.br)
 */
public class CenarioTest {
    private Cenario cenario;

    /**
     * Cria um objeto Cenario para servir de base para os testes unitários.
     */
    @Before
    public void criaCenario(){
        this.cenario = new Cenario(1, "O Brasil vai ser hexa.");
    }

    /**
     * Testa a criação do objeto Cenário.
     */
    @Test
    public void testCriaCenario(){
        new Cenario(1, "A Alemanha vai ser eliminada da Copa 2018.");
    }

    /**
     * Testa se o objeto Cenario inicializa com estado NAO FINALIZADO.
     */
    @Test
    public void testCenarioIniciaNaoFinalizado(){
        Cenario cenario = new Cenario(1, "Joao Milionário vai ganhar na mega-sena.");
        Assert.assertEquals("1 - Joao Milionário vai ganhar na mega-sena. - Nao finalizado", cenario.toString());
    }

    /**
     * Testa se gera uma exceção caso descrição seja nula.
     */
    @Test (expected = NullPointerException.class)
    public void testCenarioDescricaoNula(){
        new Cenario(1, null);
    }

    /**
     * Testa se gera uma exceção caso descrição seja vazia.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioDescricaoVazia(){
        new Cenario(1, "");
    }

    /**
     * Testa se gera uma exceção caso descrição tenha somente espaços em brancos.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioDescricaoComEspacoEmBranco(){
        new Cenario(1, "     ");
    }

    /**
     * Testa se gera uma exceção caso a identificador seja um número negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioIdNegativo(){
        new Cenario(-1, "Popoyer vai salvar a Olívia Palito.");
    }

    /**
     * Testa se gera uma exceção caso a identificador seja igual a zero.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCenarioIdentificadorIgualAZero(){
        new Cenario(0, "Maurício será promovido.");
    }

    /**
     * Testa se dois objetos são iguais. Dois objetos são iguais caso tenha o mesmo identificador.
     */
    @Test
    public void testEquals(){
        Cenario cenario = new Cenario(1, "O Brasil vai ser hexa.");
        Assert.assertTrue(this.cenario.equals(cenario));
    }

    /**
     * Testa se dois objetos são diferentes. Dois objetos são diferentes caso tenha identificador distintos.
     */
    @Test
    public void testEqualsDiferentes(){
        Cenario cenario = new Cenario(2, "O Brasil vai ser hexa.");
        Assert.assertFalse(this.cenario.equals(cenario));
    }

    /**
     * Testa se o hashCode de objetos iguais são iguais.
     */
    @Test
    public void testHashCode(){
        Cenario cenario = new Cenario(1, "O Brasil vai ser hexa.");
        Assert.assertEquals(this.cenario.hashCode(), cenario.hashCode());
    }

    /**
     * Testa o método toString do Cenario.
     * Formato textual: Numeracao - Descricao - Estado
     */
    @Test
    public void testToString(){
        Assert.assertEquals("1 - O Brasil vai ser hexa. - Nao finalizado", this.cenario.toString());
    }

    /**
     * Testa o adiciona aposta.
     */
    @Test
    public void testAdicionaAposta(){
        int id = this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        Assert.assertEquals(1, id);
    }

    /**
     * Testa o adiciona aposta segura por valor.
     */
    @Test
    public void testAdicionaApostaSeguraValor(){
        int id = this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER", 1000);
        Assert.assertEquals(1, id);
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador seja vazio.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAdicionaApostaApostadorVazio(){
        this.cenario.adicionaAposta("", 1000, "N VAI ACONTECER");
        this.cenario.adicionaAposta("", 1000, "N VAI ACONTECER", 1000);
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador esteja preenchido com caracteres em branco.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAdicionaApostaApostadorEmBranco(){
        this.cenario.adicionaAposta(" ", 1000, "N VAI ACONTECER");
        this.cenario.adicionaAposta(" ", 1000, "N VAI ACONTECER", 1000);
    }

    /**
     * Testa se gera uma exceção caso o nome do apostador seja nulo.
     */
    @Test (expected = NullPointerException.class)
    public void testAdicionaApostaApostadorNulo(){
        this.cenario.adicionaAposta(null, 1000, "N VAI ACONTECER");
        this.cenario.adicionaAposta(null, 1000, "N VAI ACONTECER", 1000);
    }

    /**
     * Testa se gera uma exceção caso o valor seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAdicionaApostaValorNegativo(){
        this.cenario.adicionaAposta("Jose da Sorte", -1, "N VAI ACONTECER");
        this.cenario.adicionaAposta("Jose da Sorte", -1, "N VAI ACONTECER", 1000);
    }

    /**
     * Testa se gera uma exceção caso o valor seja igual 0.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAdicionaApostaValorZero(){
        this.cenario.adicionaAposta("Jose da Sorte", 0, "N VAI ACONTECER");
        this.cenario.adicionaAposta("Jose da Sorte", 0, "N VAI ACONTECER", 1000);
    }

    /**
     * Testa se gera uma exceção caso a previsão seja vazia.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAdicionaApostaPrevisaoVazio(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "");
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "", 1000);
    }

    /**
     * Testa se gera uma exceção caso a previsão esteja preenchido com caracteres em branco.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAdicionaApostaPrevisaoEmBranco(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "  ");
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "  ", 1000);
    }

    /**
     * Testa se gera uma exceção caso a previsão seja nula.
     */
    @Test (expected = NullPointerException.class)
    public void testAdicionaApostaPrevisaoNulo(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, null);
        this.cenario.adicionaAposta("Jose da Sorte", 1000, null, 1000);
    }

    /**
     * Testa se gera uma exceção caso a previsão seja inválida.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAdicionaApostaPrevisaoInvalida(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "NAO EXISTE ESSA PREVISAO");
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "NAO EXISTE ESSA PREVISAO", 1000);
    }

    /**
     * Testa se gera uma exceção caso o valor seja negativo.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAdicionaApostaValorAsseguradoNegativo(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER", -1);
    }

    /**
     * Testa se gera uma exceção caso o valor seja igual 0.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAdicionaApostaValorAsseguradoZero(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER", 0);
    }

    /**
     * Testa o retorno do valor total de apostas feitas.
     * @todo calcular quando adiciona aposta segura.
     */
    @Test
    public void testObtemValorTotalApostas(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        this.cenario.adicionaAposta("Maria da Sorte", 2000, "N VAI ACONTECER", 1000);
        this.cenario.adicionaAposta("Maria da Sorte", 2000, "N VAI ACONTECER", 1000);
        Assert.assertEquals(6000, this.cenario.obtemValorTotalApostas());
    }

    /**
     * Testa o retorno do total de apostas feitas.
     */
    @Test
    public void testObtemTotalApostas(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER", 1000);
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER", 1000);
        Assert.assertEquals(4, this.cenario.obtemTotalApostas());
    }

    /**
     * Testa a listagem de apostas do cenário. Formato: Apostador - Valor (em reais) - Previsão
     * @todo adicionar aposta segura.
     */
    @Test
    public void testListaApostas(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        this.cenario.adicionaAposta("Maria da Sorte", 199, "VAI ACONTECER");
        this.cenario.adicionaAposta("Ana da Sorte", 2000, "VAI ACONTECER", 1000);
        String apostas = "Jose da Sorte - R$10,00 - N VAI ACONTECER" + System.lineSeparator() +
                "Maria da Sorte - R$1,99 - VAI ACONTECER" + System.lineSeparator() +
                "Ana da Sorte - R$20,00 - VAI ACONTECER - ASSEGURADA (VALOR) - R$ 10,00" + System.lineSeparator();
        Assert.assertEquals(apostas, this.cenario.listaApostas());
    }

    /**
     * Testa a verificação se um cenário de aposta foi finalizado com ocorreu.
     */
    @Test
    public void testFinalizado(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        this.cenario.finaliza(true);
        Assert.assertTrue(this.cenario.finalizado());
    }

    /**
     * Testa a verificação se um cenário de aposta foi finalizado com não ocorreu.
     */
    @Test
    public void testFinalizadoNaoOcorreu(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        this.cenario.finaliza(false);
        Assert.assertTrue(this.cenario.finalizado());
    }

    /**
     * Testa a verificação se um cenário não encerrado, está não finalizado.
     */
    @Test
    public void testFinalizadoCenarioNaoFinalizado(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        Assert.assertFalse(this.cenario.finalizado());
    }

    /**
     * Testa a finalização do cenário de aposta quando ocorreu.
     */
    @Test
    public void testFinalizaOcorreu(){
        this.cenario.finaliza(true);
        Assert.assertEquals("1 - O Brasil vai ser hexa. - Finalizado (ocorreu)", this.cenario.toString());
    }

    /**
     * Testa a finalização do cenário de aposta quando não ocorreu.
     */
    @Test
    public void testFinalizaNaoOcorreu(){
        this.cenario.finaliza(false);
        Assert.assertEquals("1 - O Brasil vai ser hexa. - Finalizado (nao ocorreu)", this.cenario.toString());
    }

    /**
     * Testa o calculo o valor total de apostas perdedoras.
     * @todo adiciona aposta segurada.
     */
    @Test
    public void testCalculaApostasOcorreu(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "VAI ACONTECER");
        this.cenario.finaliza(true);
        Assert.assertEquals(2000, this.cenario.calculaApostas());
    }

    /**
     * Testa o calculo o valor total de apostas perdedoras.
     * @todo adiciona aposta segurada.
     */
    @Test
    public void testCalculaApostasNaoOcorreu(){
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
        this.cenario.adicionaAposta("Jose da Sorte", 1000, "VAI ACONTECER");
        this.cenario.finaliza(false);
        Assert.assertEquals(1000, this.cenario.calculaApostas());
    }
//
//    /**
//     * Testa o calculo do rateio para as apostas ganhadoras. O valor do rateio remove o valor destinado ao caixa.
//     */
//    @Test
//    public void testCalculaRateio(){
//        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
//        this.cenario.adicionaAposta("Jose da Sorte", 1000, "N VAI ACONTECER");
//        this.cenario.adicionaAposta("Jose da Sorte", 1000, "VAI ACONTECER");
//        this.cenario.finaliza(true);
//        Assert.assertEquals(1800, this.cenario.calculaRateio(0.1));
//    }
}
