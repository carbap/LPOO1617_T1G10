package projectpokemon.main;

import projectpokemon.logic.*;

public class Main {

	public static void main(String[] args) {
		Battle a = new Battle();
		a.loadTypeChart();
		a.printTypeChart();
	}

}
