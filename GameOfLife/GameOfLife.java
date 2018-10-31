//	Herman Berlemann
//	csc 160
//	Feb 12 2006
//	Program 3 GameOfLive.java


public class GameOfLife{ 
	private long steps;
	private String[][] oldScreen;
	private String[][] newScreen;
	private boolean stopLife;
	
	public GameOfLife(String[][] grid){
		setOldScreen(grid);
		setNewScreen(grid);
		setStopLife(false);
		steps = 0;
		Life();
	}
	
	public void StopLife(boolean stop) {
		setStopLife(stop);
	}
	
	private void setStopLife(boolean stop) {
		stopLife = stop;
	}
	private void setNewScreen(String[][] newScreens) {
		newScreen = new String[newScreens.length][newScreens.length];
		for(int row = 0; row < newScreens.length; row++){
			for(int col = 0; col < newScreens[row].length; col++){
				newScreen[row][col]= newScreens[row][col];
			}
		}
	}
	
	private void setOldScreen(String[][] oldScreens) {
		oldScreen = oldScreens;
	}

	private void Life(){
		//System.out.println("Starting Life Step:" + steps);
		while(!stopLife){
			for (int row = 1; row < oldScreen.length-1 ; row++){
				//System.out.println("Row = " + row);
				for(int col=1; col < oldScreen.length-1; col++){
					//System.out.println("col = " + col);
					//If the cell is alive then 
					if(oldScreen[row][col].equals("X")){
						//System.out.println(oldScreen[row][col]);
						Survival(row,col);
					}else{
						//System.out.println(oldScreen[row][col]);
						Birth(row,col);
					}
				}
			}

			printNew();
			//printOld();
		//printOld();
			steps++;
			setOldScreen(newScreen);
			 try{
				 Thread.sleep(1000);
				 }catch(InterruptedException i){} 
		}
		System.exit(0);
	}
	//Give birth to a new cell
	private void Birth(int field, int column){
		//System.out.println("Entered Birth");
		
		//field = DecodeLowerBounds(field);
		//field = DecodeUpperBounds(field);
		
		//column = DecodeLowerBounds(column);
		//column = DecodeUpperBounds(column);

		int rowBounds = field + 1;
		int colBounds = column + 1; 

		int counter = 0;
		
		//System.out.println("rowBounds " + rowBounds);
		//System.out.println("colBounds " + colBounds);
		
		for (int row = field - 1; row <= rowBounds; row++){
			//System.out.println("Row " + row);
			for(int col = column - 1; col <= colBounds; col++){
				if(oldScreen[row][col].equals("X")){
					counter++;
				}
			}
		//	System.out.println();
		}
		//System.out.println("Birth Counter " + counter);
		if(counter == 3){
			newScreen[field][column] = "X";
		}else{
			newScreen[field][column] = ".";
		}
		//System.out.println("Leaving Birth");
	}
	//Will the cell live. Survivor in java land
	private void Survival(int field, int column){
		//field = DecodeLowerBounds(field);
		//field = DecodeUpperBounds(field);
		
		//column = DecodeLowerBounds(column);
		//column = DecodeUpperBounds(column);
		
		
		int rowBounds = field + 1;
		int colBounds = column + 1;
		int counter = 0;
		
		for (int row = field - 1; row <= rowBounds; row++){
			for(int col = column - 1; col <= colBounds; col++){
				//If the cell is alive then 
				if(oldScreen[row][col].equals("X")){
					counter++;
				}
			}
		}
		//System.out.println("Survival Counter" + counter);
		if((counter == 3) || (counter == 4)){
			newScreen[field][column] = "X";
		}else{
			newScreen[field][column] = ".";
		}
	}
	
	
	
	private int DecodeLowerBounds(int value){
		
		//System.out.println("Decodeing Lower" + value);
		
		int decodedValue = value;
		
		if ((value == 0) || (value == 1))
			decodedValue = 1;
		
		return decodedValue;
	}
	
	
	private int DecodeUpperBounds(int value){
		
		//System.out.println("Decodeing Upper" + value);
		
		int decodedValue = value;
		
		if (value > (oldScreen.length - 2))
			decodedValue = oldScreen.length - 2;
		
		return decodedValue;
	}
	
	
	private void printNew(){
		System.out.println("Step " + steps);
		
		for (int row = 0; row < newScreen.length; row++){
			String line = "";
			for(int col=0; col < newScreen[row].length; col++){
				line = line + newScreen[row][col];
			}
			System.out.println(line);
		}
		
		if(steps == 25)
				setStopLife(true);
	}
private void printOld(){
	System.out.println("Old ");
		for (int row = 0; row < oldScreen.length; row++){
			String line = "";
			for(int col=0; col < oldScreen[row].length; col++){
				line = line + oldScreen[row][col];
			}
			System.out.println(line);
		}
		if(steps == 2)
				setStopLife(true);
	}
}
