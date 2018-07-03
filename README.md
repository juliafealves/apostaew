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

### 4. Fechar Apostas (Concretizar Cenário)
Em determinado momento o sistema deve encerrar as apostas e fechar o cenário. O sistema deve ter uma operação em que finaliza o cenário indicando se o mesmo ocorreu, ou não. Quando um cenário acontece (cenário ocorreu), as apostas que previram que o cenário `VAI ACONTECER` são consideradas vencedoras e as demais perdedoras. Quando o cenário não acontece (não ocorreu) as apostas que previram que o cenário `N VAI ACONTECER` são consideradas vencedoras e as demais perdedoras.

Quando o sistema concretiza o cenário, o valor de todas as apostas perdedoras é somado. Desse valor, parte é retirado (de acordo com a taxa definida na inicialização do sistema) e acrescentado ao caixa do sistema. Esse valor deve ser sempre inteiro e arredondado para baixo. O resto do dinheiro será usado para ser  rateado igualmente entre os apostadores vencedores.

Considere o exemplo de 6 apostas do cenário discutido anteriormente:
```
“Francisco Cisco - R$200,00 - N VAI ACONTECER”
“Anonimo - R$1,99 - N VAI ACONTECER”
“Matheus Gaudencio - R$100,00 - VAI ACONTECER”
“Livia Maria - R$300,00 - VAI ACONTECER”
“Raquel Lopes- R$200,00 - VAI ACONTECER”
“Matheus Gaudencio - R$100,00 - VAI ACONTECER”
```

Se o cenário se concretizar, as apostas de Matheus, Lívia e Raquel são vencedoras. As apostas de Francisco e Anonimo são perdedoras. O sistema então soma R$200,00 com R$1,99 e multiplica pela taxa inicial definida no sistema (por exemplo, 0.01). Isto dá um total de R$2,0199, ou 201 centavos (arredondando para baixo). O sistema acrescenta então 201 centavos ao seu caixa e marca o cenário como encerrado. O sistema deve armazenar também qual o bolo a ser rateado entre os vencedores. No exemplo acima, R$ 199,98 devem ser distribuídos entre os vencedores.

A Facade deve ter métodos para:

Encerrar um cenário (indicando se ocorreu ou não).
```
void fecharAposta(int cenario, boolean ocorreu)
```
Retornar o valor total de um cenário encerrado que será destinado ao caixa
```
int getCaixaCenario(int cenario)
```
Retornar o valor total de um cenário encerrado que será destinado a distribuição entre as apostas vencedoras
```
int getTotalRateioCenario(int cenario)
```

### 5. Cadastrar Cenário Bônus
Alguns cenários precisam dar incentivos aos apostadores. Os cenários que não parecem muito óbvios ou que são puramente aleatórios (ex.: O resultado do dado será maior que três) precisam de um incentivo para que as pessoas apostem. Uma maneira de incentivar apostas em tais cenários é oferecer um bônus aos vencedores.

Seu sistema deve permitir cadastrar agora um novo tipo de cenário que ofereça bônus. Em tais cenários, o bônus é um valor que é imediatamente retirado do caixa do sistema e colocado no cenário quando o mesmo é criado. Esse valor passa a fazer parte da quantidade em dinheiro a ser distribuída entre os vencedores (ou seja, passa a ser parte do valor retornado no getTotalRateioCenario).

A representação de tais cenários será na forma: `NUMERAÇÃO - DESCRIÇÃO - ESTADO - BÔNUS`. Exemplo: `1 - O resultado do dado será maior que três - Não finalizado - R$ 100,00`


A Facade deve ter métodos para:

Cadastrar cenários com bônus
```
int cadastrarCenario(String descricao, int bonus)
```

### 6. Cadastrar Aposta Assegurada

Um seguro é uma proteção dada ao apostador para cobrir parte de suas perdas. Existem 2 tipos de seguro: por valor e por taxa. Sendo assim, é possível existir:
```
Aposta sem seguro
Aposta assegurada por valor (com seguro por valor)
Aposta assegurada por taxa (com seguro por taxa)
```

Todo seguro possui um custo e o que difere nos dois tipos existentes é a representação do montante assegurado. No seguro por valor, é guardado o valor assegurado, e no seguro por taxa, é guardado a taxa assegurada. Ao criar uma aposta assegurada, além dos atributos padrões da aposta, deve ser passado o tipo do seguro (VALOR ou TAXA), o próprio valor ou taxa a ser assegurado e o custo do seguro. Todos os seguros são bancados e recebidos pelo caixa do sistema. Assim, ao criar um aposta assegurada, o custo do seguro é repassado ao caixa do sistema.


Na finalização da aposta, se a mesma for decretada vencedora nada de diferente acontece. Se, no entanto, a aposta é considerada perdedora, a parte que cabe ao sistema é retirada (taxa definida na inicialização vezes o valor da aposta) e colocada em caixa. Em seguida, o valor assegurado (ou a taxa assegurada vezes o valor da aposta) é retirado do caixa. Este dinheiro será utilizado para pagar o seguro.


Num sistema de taxa 1% onde é feita uma aposta no valor de R$ 1000,00, com seguro por valor (valor assegurado de R$ 200,00 e de custo de seguro de R$ 50,00), as seguintes ações acontecerão:

Na criação da aposta, o caixa do sistema receberá R$ 50,00

No final da aposta...

...se a mesma for vencedora, nada é alterado no sistema

...se a mesma tiver perdido:
R$ 10,00 são colocados no caixa (0.01 * R$ 1000,00)
R$ 200,00 são retirados do caixa
(ou seja, no final o caixa sofre um decréscimo de R$ 190,00)


Na seguro por taxa, o valor assegurado é o valor da aposta vezes a taxa assegurada. Por exemplo, em uma aposta de R$1000,00, e com seguro de 30%, o valor assegurado nesse seguro é de R$300,00.

Uma aposta com seguro apresenta uma representação única no formato: `NOME - VALOR - PREVISÃO - ASSEGURADA (TIPO) - VALOR`. São exemplos de apostas asseguradas:
```
“Matheus Gaudencio - R$100,00 - VAI ACONTECER - ASSEGURADA (VALOR) - R$ 200,00”
“Matheus Gaudencio - R$100,00 - VAI ACONTECER - ASSEGURADA (TAXA) - 5%”
```

A qualquer momento antes da finalização de um cenário, uma aposta assegurada por valor pode virar uma aposta assegurada por taxa e vice-versa. Não há custo para realizar a conversão. Toda aposta assegurada é identificada unicamente no cenário por um número inteiro. Você é livre para gerar essa identificação.


A Facade deve ter métodos para:

Cadastrar apostas assegurados
```
int cadastrarApostaSeguraValor(int cenario, String apostador, int valor, String previsao, int valor, int custo)
int cadastrarApostaSeguraTaxa(int cenario, String apostador, int valor, String previsao, double taxa, int custo)
int alterarSeguroValor(int cenario, int apostaAssegurada, int valor)
int alterarSeguroTaxa(int cenario, int apostaAssegurada, double taxa)
```

### 7. Listar Cenários Ordenados

É importante que sistemas de cadastro de entidades sejam capazes de retornar seus elementos em determinada ordem. Para o sistema de apostas, não seria diferente. Por exemplo, a ordem alfabética permite que o usuário rapidamente ache um cenário ao lembrar do começo da descrição. A ordem de número de apostas ajuda a identificar quais os cenários de maior interesse por parte dos apostadores. Por fim, a própria ordem em que os elementos foram cadastrados permite recuperar, rapidamente, qual cenário está associado com qual identificação.


Seu sistema deve agora permitir trabalhar com elementos ordenados por essas três características:

- **Cadastro** A ordem natural de cadastro dos cenários (ordenados pela identificação)
- **Nome** Ordenação baseando-se na descrição do cenário ( A < Z )
- **Apostas** Ordenação baseando-se no número total de apostas (mais apostas primeiro)

Tanto a ordenação por nome ou por apostas podem fazer com que cenários ocupem a mesma posição (nos casos em que os nomes são iguais ou que há o mesmo número de apostas). Nessas situações, o critério de desempate é a identificação do cenário - ou seja - cenários com menor identificação aparecem primeiro na ordem dos cenários.


A Facade deve ter métodos para:

Alterar a ordem em que os cenários serão exibidos.
```
void alterarOrdem(String ordem)
```
Retornar a representação textual de um cenário na ordenação definida anteriormente.
```
String exibirCenarioOrdenado(int cenario)
```