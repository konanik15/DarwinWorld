package classes;

import java.util.Arrays;
import java.util.Random;

public class AnimalGenes {
	private int[] genes;
	private static int sizeOfGenotype = 32;
	private static int amountOfPossibleGenes = 8;
	
	public AnimalGenes() {
        genes = new int[sizeOfGenotype];
        createRandomGenes();
       // checkForAllOfTheGenes();
    }
	 
	public AnimalGenes(AnimalGenes father,AnimalGenes mother) {
		
		genes = new int[sizeOfGenotype];
		
		
		int firstSeparatorNumber = (int) ((Math.random() * ( 29 - 0 )) + 0);
		int secondSeparatorNumber = (int) ((Math.random() * ( 31 - firstSeparatorNumber  )) + firstSeparatorNumber  );
		
		for(int i = 0; i<firstSeparatorNumber; i++) {
			genes[i] = father.genes[i];
		}
		
		for(int i = firstSeparatorNumber ;i < secondSeparatorNumber; i++ ) {
				genes[i] = father.genes[i];
		}
		for(int i = secondSeparatorNumber; i <sizeOfGenotype ;i++ ) {
			genes[i] = mother.genes[i];
		} 
		
		//checkForAllOfTheGenes();
		Arrays.sort(genes);
	}

	private void createRandomGenes() {
		Random random = new Random();
		for(int i = 0; i < sizeOfGenotype; i++) {
			genes[i] = (int) random.nextInt(amountOfPossibleGenes);
		}
		 Arrays.sort(genes);
	//	System.out.println("Genom : " + Arrays.toString(genes));
	}

	@SuppressWarnings("unused")
	private void checkForAllOfTheGenes() {
		
		
		boolean everyGeneInGenotype[] = new boolean [amountOfPossibleGenes];
		boolean condition = false;
		
		do {
			
			for( int i = 0; i < sizeOfGenotype; i++) {
				everyGeneInGenotype[this.genes[i]] = true;          
            }
			
			 for (int i = 0; i < amountOfPossibleGenes; i++) {
	                if (!everyGeneInGenotype[i]) {                     
	                    condition = true;
	                }
	        }
			 
			if(condition) {
				for(int i = 0; i < amountOfPossibleGenes; i++) {
					 if (!everyGeneInGenotype[i]) {
	                        Random random = new Random();              
	                        genes[random.nextInt(amountOfPossibleGenes)] = i;     
	                 }
				}
			} 
		
		}
		while(condition);
	
		
	    }
	
	public int getDirectionValue() {
	    int rand = (int) (Math.random() * (sizeOfGenotype));
	    return genes[rand];
	
	}
	
	
}
