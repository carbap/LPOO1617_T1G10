# LPOO1617_T1G10

**Setup e instalação**

Para ambiente de desenvolvimento será necessário o Eclipse Neon 3 com Graddle, bastando escolher a opção de importar um Graddle Project. Posteriormente será necessário editar as "Run Configurations" tanto do projeto em si, como dos testes unitários. Para tal, no menu edição de "Run Configurations" do Eclipse, selecionar a "tab" "Arguments" e ao fundo em "Working Directory" selecionar "Other". De seguida clicar em "Workspace" e selecionar a pasta "assets" dentro da pasta "Project Pokemon-core". Aplicar este procedimento a ambos os "Run"s.
Para correr o jogo basta incluir o ficheiro pokemon.jar na pasta assets do projeto e executá-lo. Poderá ser necessário correr
o jogo a partir da linha de comandos, usando "java -jar pokemon.jar".

---------------------------------------------------

**UserManual**

O menu inicial contém os 3 botões principais, o botão de exit, o slider do volume
e ainda a equipa atual de pokémons, que ao iniciar o jogo será uma equipa pré-definida.

![Alt text](/mainMenu.PNG?raw=true "Main Menu")

O menu Edit Party permitirá escolher e customizar essa mesma equipa, que será posteriormente usada para
defrontar uma série de 4 adversários. 
O primeiro botão drop-down (a contar de cima) contém a lista de pokémons disponíveis.
Os restantes 4 botões correspondem aos ataques do pokémon selecionado. Por omissão, o pokémon selecionado é
o que se encontra mais à esquerda. Para selecionar um pokémon basta carregar sobre o seu ícone.
Assim, após selecionar o pokémon pretendido, é possível substituí-lo por outro a partir da lista de pokémons
ou então alterar o seu conjunto de ataques. 
A informação de cada move (ataque) é mostrada junto aos botões correspondentes.
Quando as escolhas tiverem sido feitas, o botão Main Menu guarda as alterações e volta ao menu principal. Neste
menu a equipa mostrada estará já atualizada.

![Alt text](/editParty.PNG?raw=true "Edit Party")

Carregando em Battle AI o jogo direciona-nos para um ecrã de preparação de batalha, em que é mostrada a equipa do 
adversário. A partir daqui, é possível dar início à batalha ou voltar ao menu principal.

![Alt text](/battleAI.PNG?raw=true "Ecrã de preparação de batalha")

Durante a batalha é possível escolher um dos quatro ataques do nosso pokémon ou então trocá-lo por outro, através do
botão switch. É também possível desistir da batalha, voltando ao menu principal. O sprite do nosso pokémon, a sua barra
de vida e nível estão representados em baixo, estando os do adversário em cima. De notar que o adversário tenderá a fazer boas jogadas,
quer seja escolhendo o melhor ataque ou trocar em alturas apropriadas.

![Alt text](/battle.PNG?raw=true "Ecrã de batalha")

O menu de troca pode ser acedido de duas formas: através do botão Switch ou quando o pokémon que estava em batalha morre (troca forçada).
Os pokémons à direita, com as respetivas barras de vida, são as opções existentes para substituir o pokémon representado mais
à esquerda, que está atualmente em batalha. Só é possível escolher um pokémon que ainda tenha HP restante, sendo que os botões
dos pokémons sem vida estarão desativados.
Caso a troca não tenha sido seja forçada, é possível cancelá-la através do botão Cancel em cima, caso contrário é necessário escolher
um pokémon para prosseguir a batalha.

![Alt text](/switch.PNG?raw=true "Ecrã de batalha")

Caso o jogador vença a batalha, derrotando todos os pokémos do oponente, aparecerá o ecrã de apresentação do novo adversário. Caso perca o jogo retornará ao menu principal.
Antes de enfrentar um novo adversário a vida dos pokémons será completamente restaurada.
Se o jogador conseguir derrotar a série de 4 treinadores será mostrado um ecrã de vitória.

O botão update do menu principal permite atualizar a lista de pokémons e moves disponíveis, descarregando a informação necessária bem como
os sprites dos novos pokémons através de URLs diretos para estes ficheiros.
O update demora cerca de 1 a 3 segundos (a música de fundo pára durante o processo) e posteriormente estarão disponíveis para escolha os
novos pokémons no menu Edit Party. Não é necessário reiniciar o jogo para que as mudanças tenham efeito, mas caso se reinicie o jogo continuará atualizado.

---------------------------------------------------

**Design Patterns**

Fizemos uso de pelo menos dois design patterns, sendo eles os padrões Singleton e Factory.
  
Analisando o package graphics, para garantir que existe apenas uma instância da classe Engine a que todos os objetos podem aceder, utilizámos o padrão Singleton. Deve existir apenas um objeto desta classe durante toda a execução do jogo pois só é necessário
haver um objeto que trate de gerir os sound effects, músicas de fundo e volume do jogo.

A classe Engine possui ainda duas váriaveis privadas, uma da classe Trainer que representa o jogador em si (utilizador) e outra de InfoBattleAI, que contém informações sobre as equipas dos adversários e um índice que indica o adversário atual. No desenvolvimento do projeto, começámos por considerar que estas classes usariam também o padrão Singleton, isto porque existe um único jogador e as informações sobre os adversários são sempre as mesmas, necessitanto apenas de um objeto. No entanto, apercebemo-nos das complicações que essa opção traria ao efetuar testes unitários à lógica. Assim, decicimos associar estas classes ao Engine como variáveis privadas mas com
construtores públicos. Esta decisão permitiu criar vários objectos Trainer e InfoBattleAI para os testes unitários mas ao mesmo tempo
ter apenas, na prática, 2 objetos durante a execução do jogo.
  Também a classe ScreenFactory implementa o padrão de desenho Singleton, sendo a sua função definir o screen atual do jogo.

Em relação ao package logic, a classe MoveFactory faz uso, simultaneamente, dos design patterns Singleton e Factory.
Esta solução foi adotada pois permite ter uma única "fábrica" de moves, contendo um ArrayList com todos os moves
já carregados, que é acessível de forma global. Desta forma não é preciso estar sempre a criar novos Move, o que evita ter vários objetos de igual conteúdo em memória, sendo o mesmo objeto partilhado por vários pokémons. Assim torna-se mais eficiente as classes Battle e Pokemon, por exemplo, acederem aos moves que necessitam através da instância de MoveFactory.

---------------------------------------------------

**Diagram UML**

![Alt text](/UMLDiagram.PNG?raw=true "Ecrã de batalha")

---------------------------------------------------

**Observações finais**

Durante o desenvolvimento deste projeto tivemos que tomar decisões face aos desafios que foram surgindo,
tendo sempre em conta os objetivos com que nos tinhamos comprometido.

Perante certos aspetos que tínhamos previsto implementar, na tentativa de recriar o jogo original o mais fielmente possível,
chegámos à conclusão que alguns destes não seriam suficientemente relevantes para o resultado final e para a experiência do jogador.
Referimo-nos, por exemplo, a alguns atributos da classe Pokémon (as variáveis EV e IV passam a ser comuns a todos os stats)
e Move (não existência da variável que indica se um ataque nunca falha). Também não incluímos, no ecrã de preparação para a 
próxima batalha, a suposta mensagem do treinador adversário.

Para além destes pequenos ajustes que não consideramos ter muita influência, houve certamente decisões mais importantes que tivemos
de tomar. Estava inicialmente previsto existir um modo online que permitisse que dois jogadores batalhassem um contra o outro.
No entanto, surgiu a ideia de ter uma funcionalidade de atualização do jogo, descarregando novos pokémons e moves a partir de ficheiros
na Internet. Sendo certo que, comparativiamentee à opção de multiplayer, esta funcionalidade nos parecia mais simples de implementar, 
continuava a fazer sentido no contexto do jogo e a equandrar-se no objetivo do Networking, decidimos avançar para esta ideia.

Relativamente às maiores dificuldades que encontrámos neste projeto, podemos destacar alguns problemas técnicos que de certa forma
atrasaram os progressos no desenvolvimento. Tivemos frequentemente problemas com o ficheiro uiksin.json, problemas ao importar projetos
do libgdx para o eclipse e ao exportar, no fim, o jogo para um formato executável, por não sabermos onde incluir os ficheiros dos assets e 
termos de alterar no código o caminho da localizacao dos mesmos.


