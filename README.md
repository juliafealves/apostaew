# Apostaew
:moneybag: Atividade: Sistema de Apostas (Lab. 5) - Laboratório de Programação II - UFCG

## Requisitos:

### Sistema de Apostas
No projeto atual você deve implementar um sistema de apostas que possui um caixa inicial. Neste programa, são cadastrados cenários que podem acontecer, ou não. Por exemplo, pode-se imaginar o cenário em que “mais da metade da turma irá pagar física por média”. Para cada cenário, é possível fazer apostas com o nome do apostador, quantia de aposta (R$) e uma previsão (o cenário vai acontecer ou o cenário não vai acontecer).

Em determinado momento, o sistema concretiza o cenário (dizendo se ocorreu ou não) tornando as apostas vencedoras e perdedoras (se elas acertaram ou não a previsão que fizeram). Este sistema contará ainda com: i) apostas especiais, em que um prêmio especial é oferecido pelo próprio sistema de apostas, e; ii) por apostas seguradas, que protege o apostador em determinadas situações. Além disso o sistema deve imprimir os cenários ordenados por diferentes critérios.

Para cada aposta perdedora, o sistema retira uma parte da quantia apostada para o seu próprio caixa (o resto é distribuído para os vencedores da aposta). O sistema também irá retirar dinheiro desse caixa para colocar nas apostas prêmios (quando essas forem cadastradas). É importante sempre ter controle desse valor em caixa.

### 1. Inicializar Sistema
Durante a inicialização do sistema, deve-se receber um valor indicando a quantidade atual de dinheiro (em centavos) que o sistema possui e a porcentagem que deve ser retirada de cada aposta perdedora para ir ao caixa do sistema (o resto do valor é usado para premiar as apostas vencedoras).

O valor de porcentagem não será alterado ao longo do programa. Deve ser possível conseguir recuperar o caixa atual do sistema em qualquer momento.

A Facade deve oferecer métodos para:

Inicializar o sistema com um valor e taxa (exemplo: 100000 e 0.01)
```
void inicializa(int caixa, double taxa)
```
Recuperar o valor em caixa do sistema (em centavos).
```
int getCaixa()
```