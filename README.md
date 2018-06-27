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

### 2. Cadastrar e Listar Cenários
Cada cenário apresenta uma descrição de uma possível situação futura que poderá (ou não) acontecer. Os cenários iniciam como não finalizados e passam a ser ditos como finalizados quando são encerrados.


Ao encerrar um cenário, deve ser informado ao sistema se o mesmo ocorreu ou não. Todo cenário é identificado por uma numeração inteira. O primeiro cenário recebe a numeração 1, o segundo cenário criado recebe a numeração 2 e assim sucessivamente.


Cada cenário tem uma representação textual no formato `NUMERAÇÃO - DESCRIÇÃO - ESTADO`, onde ESTADO pode ser `Finalizado (ocorreu)`, `Finalizado (n ocorreu)` ou `Não finalizado`. Exemplos:

```
“1 - A maioria irá tirar mais do que 7 na prova! - Não finalizado”
“2 - O professor irá para a aula sobre GRASP com um café! - Finalizado (ocorreu)”
```

A Facade deve ter métodos para:

Cadastrar cenários e retornar a numeração do cenário cadastrado
```
int cadastrarCenario(String descricao)
```
Retornar a representação textual de um cenário (a partir da numeração)
```
String exibirCenario(int cenario)
```
Retornar a representação textual de todos os cenários cadastrados no sistema (um por linha, em ordem de cadastro)
```
String exibirCenarios()
```

### 3. Cadastrar e Listar Apostas
É possível fazer apostas para diferentes cenários. Todas as apostas são diferentes entre si e tem como atributos: nome do apostador, valor da aposta e a previsão (`VAI ACONTECER` ou `N VAI ACONTECER`).

No cenário de exemplo (`A maioria irá tirar mais do que 7 na prova!`) é possível cadastrar uma aposta feita por Matheus, valendo 10000 centavos concordando com o cenário. É importante observar que é possível ter duas apostas diferentes com o mesmo nome do apostador, valor e tipo. Um mesmo apostador pode fazer diferentes apostas, inclusive de tipos diferentes.


Cada aposta é representada textualmente pelo formato `NOME - VALOR - PREVISÃO`. Seria um exemplo de representação:


`Matheus Gaudencio - R$100,00 - VAI ACONTECER`


A Facade deve ter métodos para:

Cadastrar a aposta com nome do apostador, quantia apostada e previsão em um cenário através do número do cenário.
```
void cadastrarAposta(int cenario, String apostador, int valor, String previsao)
```
Retornar o valor total das apostas feitas em um cenário.
```
int valorTotalDeApostas(int cenario)
```
Retornar o número de apostas feitas em um cenário.
```
int totalDeApostas(int cenario)
```
Gerar a representação textual das apostas de um cenário (uma aposta por linha).
```
String exibeApostas(int cenario)
```