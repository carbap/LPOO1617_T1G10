# LPOO1617_T1G10

Descrição da GUI:

A Graphical User Interface terá um menu principal em que o utilizador escolhe se quer jogar contra AI, online ou se deseja editar a party atual de pokémons, que também é apresentada neste ecrã principal. O menu possui ainda um botão para encerrar o jogo.

Se o utilizador escolher jogar contra AI é lhe mostrado um ecrã de apresentação ao próximo adversário, com uma imagem do mesmo, a sua equipa e uma mensagem. Este ecrã é apresentado sempre que o utilizador se prepara para defrontar um novo adversário. Depois deste ecrâ, o jogo transita para o ecrã de batalha.

Se o utilizador escolher jogar online é mostrado um ecrã em que pode escolher ser o Host ou se quer juntar a outra partida. Quando se escolher ser Host o jogo fica à espera que outro jogador se junte à partida. A qualquer momento o Host pode cancelar a espera ao carregar num botão. Além dos elementos mencionados, são mostradas ainda as informações necessárias para que outro jogador se possa conectar à partida, caso o utilizador deseje ser o Host. Se escolher juntar-se a uma partida, é pedido que insira as informações para se conectar ao Host. Quando é estabelecida uma ligação entre dois jogadores o jogo transita para o ecrâ de batalha.

O ecrã de batalha mostrará o pokémon atual do utilizador e do adversário bem como as suas barras de vida, níveis e nome. Por baixo das barras de vida, será apresentado o número de pokémons de cada treinador que estão aptos a lutar. No seu turno, o utilizador poderá optar por usar um dos quatro ataques do seu pokémon ou trocá-lo por outro. Durante as jogadas de cada treinador são apresentadas informações sobre o que foi feito, tais como se houve trocas, quais foram os ataques usados, a eficácia dos mesmos e se foram critical hits.
O ecrã de troca de pokémon será acedido através do botão switch. Quando a batalha termina o jogo pode transitar para o ecrã de apresentação do adversário (caso a batalha seja AI, o utilizador tenha ganho e ainda haja adversários restantes) ou para o menu principal.

O ecrã de troca apresentará todos os pokémons da party com as suas informações básicas como o nome, imagem, nível, vida e tipo(s). Os pokémons que não estão aptos a lutar (cuja vida é igual a zero) não poderão ser selecionados e o seu botão terá cor vermelha para diferenciá-los dos restantes, cujos botões serão azuis. Depois de efetuada a troca ou de esta ser cancelada ao carregar no botão respetivo, o jogo regressa ao ecrã de batalha.

O ecrã de edição da party do utilizador, acedido através do menu principal, apresenta a party atual, os stats e o moveset do pokémon atualmente selecionado. Este ecrã permitirá trocar o pokémon selecionado por outro escolhido de uma lista drop-down. Ao selecionar um dos moves do pokémon será apresentada uma nova janela com os moves que o podem susbtituir. Quando terminada a edição o jogo regressa ao ecrã do menu principal.





Casos a testar:

Criação de objectos Pokémon e Move através dos ficheiros respetivos.
Fórmulas de cálculo dos stats dos pokémons.
Substituição de moves dos pokémons.
Obtenção e alteração dos valores e modifiers da classe StatStages.
Dano esperado, dentro dos intervalos possíveis, devido ao fator random, hit chance e modificadores de dano.
Eficácia dos moves atendendo ao seu tipo e ao do alvo.
Chance de critical hit, tendo em conta que certos moves têm maior critical chance.
Adição, remoção, substituição e reordenação de pokémons na party.
Escolha por parte da AI de moves que maximizem o dano causado/ chance de atingir o adversário.
AI trocar de pokémon quando se encontra numa situação desfavorável (HP muito baixo).
AI troca, se possível, para um pokémon que tenha vantagem sobre o adversário.
AI prioriza a melhor jogada, mas não a efetua sempre (tem pequeno fator de aleatoriedade).






