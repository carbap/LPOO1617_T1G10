# LPOO1617_T1G10

**Descrição da GUI**:

A Graphical User Interface terá um menu principal em que o utilizador escolhe se quer jogar contra AI, online ou se deseja editar a party atual de pokémons, que também é apresentada neste ecrã principal. O menu possui ainda um botão para encerrar o jogo.

Se o utilizador escolher jogar contra AI é lhe mostrado um ecrã de apresentação ao próximo adversário, com uma imagem do mesmo, a sua equipa e uma mensagem. Este ecrã é apresentado sempre que o utilizador se prepara para defrontar um novo adversário. Depois deste ecrã, o jogo transita para o ecrã de batalha.

Se o utilizador escolher jogar online é mostrado um ecrã em que pode escolher ser o Host ou juntar-se a outra partida. Quando se escolhe ser Host o jogo fica à espera que outro jogador se junte à partida. A qualquer momento o Host pode cancelar a espera ao carregar num botão. Além dos elementos mencionados, são mostradas ainda as informações necessárias para que outro jogador se possa conectar à partida, caso o utilizador deseje ser o Host. Se escolher juntar-se a uma partida, é pedido que insira as informações para se conectar ao Host. Quando é estabelecida uma ligação entre dois jogadores o jogo transita para o ecrã de batalha.

O ecrã de batalha mostrará o pokémon atual do utilizador e do adversário bem como as suas barras de vida, níveis e nome. Por baixo das barras de vida, será apresentado o número de pokémons de cada treinador que estão aptos a lutar. No seu turno, o utilizador poderá optar por usar um dos quatro ataques do seu pokémon ou trocá-lo por outro. Durante as jogadas de cada treinador são apresentadas informações sobre o que foi feito, tais como se houve trocas, quais foram os ataques usados, a eficácia dos mesmos e se foram critical hits.
O ecrã de troca de pokémon será acedido através do botão switch. Quando a batalha termina o jogo pode transitar para o ecrã de apresentação do adversário (caso a batalha seja AI, o utilizador tenha ganho e ainda haja adversários restantes) ou para o menu principal.

O ecrã de troca apresentará todos os pokémons da party com as suas informações básicas como o nome, imagem, nível, vida e tipo(s). Os pokémons que não estão aptos a lutar (cuja vida é igual a zero) não poderão ser selecionados e o seu botão terá cor vermelha para diferenciá-los dos restantes, cujos botões serão azuis. Depois de efetuada a troca ou de esta ser cancelada ao carregar no botão respetivo, o jogo regressa ao ecrã de batalha.

O ecrã de edição da party do utilizador, acedido através do menu principal, apresenta a party atual, os stats e o moveset do pokémon atualmente selecionado. Este ecrã permitirá trocar o pokémon selecionado por outro escolhido de uma lista drop-down. Ao selecionar um dos moves do pokémon será apresentada uma nova janela com os moves que o podem susbtituir. Quando terminada a edição o jogo regressa ao ecrã do menu principal.

![Alt text](/mainMenu.png?raw=true "Main Menu")
![Alt text](/battleAI.png?raw=true "Battle AI")
![Alt text](/battleOnline.png?raw=true "Battle Online")
![Alt text](/battle.png?raw=true "Battle")
![Alt text](/switch.png?raw=true "Switch Pokémon")
![Alt text](/editParty.png?raw=true "Edit Party")

--------------------------------------------------

**Casos a testar**:

Criação de objectos Pokémon e Move através dos ficheiros respetivos.<br />
Fórmulas de cálculo dos stats dos pokémons.<br />
Substituição de moves dos pokémons.<br />
Obtenção e alteração dos valores e modifiers da classe StatStages.<br />
Dano esperado, dentro dos intervalos possíveis, devido ao fator random, hit chance e modificadores de dano.<br />
Eficácia dos moves atendendo ao seu tipo e ao do alvo.<br />
Chance de critical hit, tendo em conta que certos moves têm maior critical chance.<br />
Adição, remoção, substituição e reordenação de pokémons na party.<br />
Escolha por parte da AI de moves que maximizem o dano causado/ chance de atingir o adversário.<br />
AI trocar de pokémon quando se encontra numa situação desfavorável (HP muito baixo).<br />
AI troca, se possível, para um pokémon que tenha vantagem sobre o adversário.<br />
AI prioriza a melhor jogada, mas não a efetua sempre (tem pequeno fator de aleatoriedade).<br />

--------------------------------------------------

**Descrição das classes**:

![Alt text](/uml.png?raw=true "Diagrama de Classes")

__Battle__:<br />

double[][] typeChart;<br />
Trainer player;<br />
Move chosenMove;<br />
Move enemyChosenMove;<br />
bool playerSwitch;<br />
short playerSwitchIndex;<br />
bool enemySwitch;<br />
short enemySwitchIndex;<br />

Descrição:<br />
Responsável por calcular quem ataca primeiro, se ataque atinge o adversário e os efeitos causados
pelo ataque, tais como o dano e mudanças de stats. Também se encarrega de trocar de pokémons quando o treinador assim desejar.

__BattleAI__:<br />

TrainerNPC opponent;<br />

Descrição:<br />
Deriva da classe Battle. É responsável por determinar a jogada do adversário face à situação em que se encontra (algoritmo de inteligência artifical).

__BattleOnline__:<br />

Trainer onlineOpponent;<br />
ObjectOutputStream output;<br />
ObjectInputStream input;<br />
ServerSocket server;<br />
Socket connection;<br />

Descrição:<br />
Deriva da classe Battle. É responsável pela ligação entre dois jogadores através de sockets, permitindo uma batalha multijogador.

__Move__:<br />

short type;<br />
boolean special;<br />
String name;<br />
short accuracy;<br />
short power;<br />
short id;<br />
StatStages selfStatStages;<br />
StatStages enemyStatStages;<br />
boolean highCritChance;<br />
boolean canMiss;<br />

Descrição:<br />
Contém a informação sobre um ataque, nomeadamente o nome, id, tipo, power, precisão, modificadores de stats para o utilizador e para o alvo e flags que indicam se o ataque pode falhar e/ou se têm uma probabilidade elevada de critical hit.

__Pokemon__:<br />

String name;<br />
short id;<br />
short level;<br />
short type1;<br />
short type2;<br />
boolean twoTypes;<br />
StatStages statStages;<br />
ArrayList<Move> moveSet;<br />
short damageTaken;<br />
short evHP, evATK, evDEF, evSPATK, evSPDEF, evSPD;<br />
short ivHP, ivATK, ivDEF, ivSPATK, ivSPDEF, ivSPD;<br />
short baseHP, baseATK, baseDEF, baseSPATK, baseSPDEF, baseSPD;<br />

Descrição:<br />
Contém as informações sobre um pokémon, tais como o nome, id, nível, tipo(s), modificadores de stats, moveset, dano recebido, EVs, IVs e base stats. A partir dos base stats, EVs, IVs e os StatStages são calculados os stats efetivos do Pokémon.

__StatStages__:<br />

short atk;<br />
short def;<br />
short spatk;<br />
short spdef;<br />
short spd;<br />
short accuracy;<br />
short evasion;<br />

Descrição:<br />
Classe que contém o estado de um stat e, conforme estes estados, calcula as percentagens de moficação dos respetivos stats.

__Trainer__:<br />

ArrayList<Pokemon> party;<br />

Descrição:<br />
Contém a party (conjunto de pokémons) de um treinador e possui métodos para adicionar, remover, substituir e reordenar pokémons da party.

__TrainerNPC__:<br />

String introMessage;<br />

Descrição:<br />
Deriva da classe Trainer e possui uma mensagem que é mostrada durante a apresentação do NPC adversário.

--------------------------------------------------

**Design Patterns**:

Planeamos utilizar o design pattern State e Factory. 
O padrão State servirá para modelar a navegação nos vários menus e também para distinguir as fases de uma batalha tais como a escolha de ataque, troca de pokémons, primeiro turno de ataque, apresentação de texto entre outros.
O padrão Factory será utilizado para guardar apenas uma instância dos sprites, evitando assim o loading excessivo de imagens para memória, melhorando o desempenho e diminuindo o uso de memória.

--------------------------------------------------

**Navegação nos screens do jogo**:

![Alt text](/menuStages.png?raw=true "Estados do jogo")





