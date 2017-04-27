package projectpokemon.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Battle {
	
	private double[][] typeChart = new double[17][17];
	
	public void loadTypeChart(){
        try {
        	File a = new File(Battle.class.getResource("../data/pokemon_types.txt").getFile());
			Scanner scanner = new Scanner(a).useLocale(Locale.US);
			for(int i = 0; i < 17; i++){
				for(int j = 0; j < 17; j++){
					typeChart[i][j] = scanner.nextDouble();
				}
			}
		}
        catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        
	}
	
	public void printTypeChart(){
		for(int i = 0; i < 17; i++){
			for(int j = 0; j < 17; j++){
				System.out.print(typeChart[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	public short attackPokemon(Pokemon attacker, Pokemon defender, short movIndex)
	{
		double damage;
		boolean isCritical = false;
		boolean moveHit = true;
		Move chosenMove = attacker.getMoveSet().get(movIndex);
		if(chosenMove.getCanMiss())
			moveHit = moveHits(attacker, defender, chosenMove);
		
		if(!moveHit)
			return 0;
		
		double modifier = calculateModifier(attacker, defender, chosenMove, isCritical);
		
		if(chosenMove.isSpecial()){
			damage = ((2.0 * attacker.getLevel() / 5.0 + 2.0) * chosenMove.getPower() * attacker.getSPATK() / defender.getSPDEF(isCritical) / 50.0 + 2.0) * modifier;
		}
		else{
			damage = ((2.0 * attacker.getLevel() / 5.0 + 2.0) * chosenMove.getPower() * attacker.getATK() / defender.getDEF(isCritical) / 50.0 + 2.0) * modifier;
		}
		
		
		return (short) Math.floor(damage);
	}
	
	public void modifyStatStages(Pokemon attacker, Pokemon defender, Move chosenMove)
	{
		
	}
	
	public boolean moveHits(Pokemon attacker, Pokemon defender, Move chosenMove)
	{
		Random r = new Random();
		double hitChance;
		
		hitChance = chosenMove.getAccuracy() * attacker.getStatStages().getAccuracyModifier()/defender.getStatStages().getEvasionModifier();
		
		if(r.nextDouble() < hitChance)
		{
			return true;
		}
		return false;
	}
	
	public double calculateModifier(Pokemon attacker, Pokemon defender, Move chosenMove, boolean isCritical){
		Random r = new Random();
		double modifier = 1;
		if(attacker.getType1() == chosenMove.getType()){
			modifier *= 1.5;
		}
		else if(attacker.hasTwoTypes() && attacker.getType2() == chosenMove.getType()){
			modifier *= 1.5;
		}
		modifier *= 1 - r.nextInt(16)/100.0;
		
		double critChance = 0.0625;
		if(chosenMove.getHighCritChance())
		{
			critChance *= 2.0;
		}
		if(r.nextDouble() < critChance){
			modifier *= 2.0;
			isCritical = true;
		}
		
		return modifier;
	}
}
