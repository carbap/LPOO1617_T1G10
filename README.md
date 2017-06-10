# LPOO1617_T1G10

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
