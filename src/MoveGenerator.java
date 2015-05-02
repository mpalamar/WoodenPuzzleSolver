import java.util.*;

public class MoveGenerator {
	
	private char [][] board;
	private char [][] currentConfig;
	private char [] config = {'C', 'A','D', 'B', 'E', 'D', 'A', 'E', 'D', 'A', 'D', 'A'};
	private final char [][] initialConfigBoard = generateGrid(config);
	private boolean flag = false;
	private Queue<possibleMove> queue;
	private String initialConfig = "AADCBDEDEAAD";
	
//	public static void main(String [] args){
//		board = initialConfigBoard;
//		currentConfig = initialConfigBoard;
//		
//		queue = new LinkedList<possibleMove>();
//		
//		displayBoard(currentConfig);
//		int numb = generateNumberMovesPossible();
//		System.out.println(numb);
//		System.out.println("displaying queue..:");
//		displayQueue();
//		System.out.println();
//		displayMoveOptions();
//	}
	
	public MoveGenerator(String initialConfig) {
		currentConfig = generateGrid(initialConfig.toCharArray());
		board = initialConfigBoard;
		currentConfig = initialConfigBoard;
//		
		queue = new LinkedList<possibleMove>();
		
		displayBoard(currentConfig);
		int numb = generateNumberMovesPossible();
		System.out.println(numb);
		System.out.println("displaying queue..:");
		displayQueue();
		System.out.println();
		displayMoveOptions();
		
	}

	
	
	public class possibleMove{
		int xLocation;
		int yLocation;
		String direction;
		boolean isDouble;
		boolean isStack;
		
		public possibleMove(int x, int y, String dir, boolean dub, boolean stack) {
			xLocation = x;
			yLocation = y;
			direction = dir;
			isDouble = dub;
			isStack = stack;
		}
	}
	
	public int generateNumberMovesPossible(){
		char [][] a = currentConfig;
		int [] eLocations = new int[4];
		int eLocationCounter = 0;
		int numberMoves = 0;
		
		for(int i = 1; i < 5; i++) {
			for(int j = 1; j < 6; j++) {
				if(a[i][j] == 'E') {
	
					if(getAdjacentELocation(i,j).equals("BELOW")) {
							if(getHeight(a[i][j-1]) == 2 && a[i][j-1] == a[i+1][j-1]) {
								if(getHeight(a[i][j-1]) == 2 && getWidth(a[i][j-1]) ==2) {
									numberMoves++;
									queue.add(new possibleMove(i,j-1,"RIGHT",(getWidth(a[i][j-1]) == 2),(getHeight(a[j][j-1]) == 2)));
								}
								else{
								numberMoves++;
								System.out.println("// stack below left of E:" + a[i][j-1]);
								queue.add(new possibleMove(i,j-1,"RIGHT", (getWidth(a[i][j-1]) == 2),(getHeight(a[i][j-1]) == 2)));
								}
								
							}
							if(getHeight(a[i][j+1]) == 2 && a[i][j+1] == a[i+1][j+1]) {
								numberMoves++;
								System.out.println("// stack below right of E:" + a[i+1][j+1]);
								queue.add(new possibleMove(i,j+1,"LEFT",(getWidth(a[i+1][j+1]) == 2),(getHeight(a[i+1][j+1]) == 2)));
							}

							
					}

					if(getAdjacentELocation(i,j).equals("RIGHT")) {
						if(getWidth(a[i-1][j]) == 2 && a[i-1][j] == a[i-1][j+1]) {
							numberMoves++;
							System.out.println("// stack below right of E:" + a[i-1][j+1]);
						}
						if(getWidth(a[i+1][j]) == 2 && a[i+1][j] == a[i+1][j+1]) {
							numberMoves++;
							System.out.println("// stack above right of E:" + a[i+1][j+1]);
						}
					}

					
					if(a[i-1][j] != 'E' && getWidth(a[i-1][j]) == 1 ) { 
						numberMoves++;
						System.out.println("// block above E:" + a[i-1][j]);
						queue.add(new possibleMove(i-1,j,"DOWN",(getWidth(a[i-1][j]) == 2),(getHeight(a[i-1][j]) == 2)));
					}
 
					if(a[i+1][j] != 'E' && getWidth(a[i+1][j]) == 1 ) { 
						numberMoves++;
						System.out.println("// block below E:" + a[i+1][j]);

						queue.add(new possibleMove(i+1,j,"UP",(getWidth(a[i+1][j]) == 2),(getHeight(a[i+1][j]) == 2)));
						
					}
					
					if(a[i][j-1] != 'E' && getHeight(a[i][j-1]) == 1) {
						numberMoves++;
						System.out.println("// block left of E:" + a[i][j-1]);
						queue.add(new possibleMove(i,j-1,"RIGHT",(getWidth(a[i][j-1]) == 2),(getHeight(a[i][j-1]) == 2)));
					}
					if(a[i][j+1] != 'E' && getHeight(a[i][j+1]) == 1) {
						numberMoves++;
						System.out.println( "// block right of E:" + a[i][j+1]);
						queue.add(new possibleMove(i,j+1,"LEFT",(getWidth(a[i][j+1]) == 2),(getHeight(a[i][j+1]) == 2)));
					}
				}
				
			}
		}			
		return numberMoves;
	}
	
	public char[][] generateGrid(char[] z) {
		char[][] layout = new char[10][10];
		
		int x = 1;
		int y = 1;
		
		for(int i = 0; i < z.length; i++) {
			for(int j = 0; j < getWidth(z[i]); j++) {
				if(layout[x][y] == '\0') {
					if(getHeight(z[i]) == 1) {
						layout[x][y] = z[i];
					}
					else {
						layout[x][y] = z[i];
						layout[x+1][y] = z[i];
					}
				
				y++;
				if(y == 6) { x++; y = 1; }
			}
			else{
				while(layout[x][y] != '\0') {
					y++;
					if(y == 6) { x++; y = 1; }
				}
				
				if(getHeight(z[i]) == 1) {
					layout[x][y] = z[i];
				}
				else {
					layout[x][y] = z[i];
					layout[x+1][y] = z[i];
				}
			}
		}
	}
		return layout;		
}
	public String generateConfigString(char[][] a){
		String config = "";
		
		for(int i = 1; i < 5; i++) {
			for(int j = 1; j < 6; j++) {
				if(a[i][j] == 'A'){
					j++;
				}
				if(a[i][j] == 'C'){
					if(a[i-1][j] == 'C') {
						j += 2;
					}
					else
						j++;
				}
				if(a[i][j] == 'B' && a[i-1][j] == 'B') {
					j++;
				}
				config += a[i][j];
			}
		}
		return config;
	}
	
	
	public String getAdjacentELocation(int x, int y) {
		if(board[x+1][y] == 'E')
			return "BELOW";
		else if(board[x][y-1] == 'E')
			return "LEFT";
		else if(board[x][y+1] == 'E')
			return "RIGHT";
		else
			return "NAH";
	}

//	public static int countPossibleMoves(char[] x){
//		int moveCount = 0;
//		for(int i = 0; i < x.length; i++) {
//		}		
//		return moveCount;
//	}
	
	public char[][] movePiece(int x, int y, char[][] c, String dir){
		int directionX;
		int directionY;
		
		if(dir.equals("UP")) { directionX = -1; directionY = 0; }
		else if(dir.equals("DOWN")) { directionX = 1; directionY = 0; }
		else if(dir.equals("LEFT"))	{ directionX = 0; directionY = -1; }
		else { directionX = 0; directionY = 1; } // Right by default
		
		char[][] tempConfig = c;
		
		char temp = 'Z';
		temp  = tempConfig[x][y];
		tempConfig[x][y] = tempConfig[x + directionX][y + directionY] ;
		tempConfig[x + directionX][y + directionY] = temp;
		return tempConfig;
	}
	
//	public static char[][] movePieceDownAt(int x, int y,char [][] c){
//		char[][] tempConfig = c;
//		
//		char temp = 'Z';
//		temp  = tempConfig[x][y];
//		tempConfig[x][y] = tempConfig[x+1][y] ;
//		tempConfig[x+1][y] = temp;
//		return tempConfig;
//	}
//	
//	public static char[][] movePieceUpAt(int x, int y, char [][] c){
//		char[][] tempConfig = c;
//		
//		char temp = 'Z';
//		temp  = tempConfig[x][y];
//		tempConfig[x][y] = tempConfig[x-1][y] ;
//		tempConfig[x-1][y] = temp;
//		return tempConfig;
//	}
//	public static char[][] movePieceLeftAt(int x, int y, char [][] c){
//		char[][] tempConfig = c;
//		
//		char temp = 'Z';
//		temp  = tempConfig[x][y];
//		tempConfig[x][y] = tempConfig[x][y-1] ;
//		tempConfig[x][y-1] = temp;
//		return tempConfig;
//	}
//	
//	public static char[][] movePieceRightAt(int x, int y, char [][] c){
//		char[][] tempConfig = c;
//
//		char temp = 'Z';
//		temp  = tempConfig[x][y];
//		tempConfig[x][y] = tempConfig[x][y+1] ;
//		tempConfig[x][y+1] = temp;
//		return tempConfig;
//	}
	
	public int getCharPosition(char x) {
		for(int i = 0; i < initialConfig.length(); i++)
			if(initialConfig.charAt(i) == x)
				return i;
		return -1;
	}
	
	public int getWidth(char x){
		if(x == 'A')
			return 2;
		else if(x == 'B')
			return 1;
		else if(x == 'C')
			return 2;
		else if(x == 'D')
			return 1;
		else if(x == 'E')
			return 1;
		else 
			return -1;
	}
	
	public int getHeight(char x){
		if(x == 'A')
			return 1;
		else if(x == 'B')
			return 2;
		else if(x == 'C')
			return 2;
		else if(x == 'D')
			return 1;
		else if(x == 'E')
			return 1;
		else 
			return -1;
	}
	
	public char[][] copyOf(char[][] original) {
		    int length = original.length;
		    char[][] target = new char[length][original[0].length];
		    for (int i = 0; i < length; i++) {
		        System.arraycopy(original[i], 0, target[i], 0, original[i].length);
		    }
		    return target;
	}
	
	public void swap(int x, int y) {
		char[] c = initialConfig.toCharArray();

		char temp = c[x];
		c[x] = c[y];
		c[y] = temp;
		String swappedString = new String(c);
	}
	
	
	/******** Display ****/
	public void displayMoveOptions() {
		char[][] c = copyOf(initialConfigBoard);
		
		for(possibleMove x : queue) {
			
			c = copyOf(initialConfigBoard);
			
//			if(x.direction.equals("RIGHT")) {
				movePiece(x.xLocation, x.yLocation,c,x.direction);
				
				if(x.isDouble && x.isStack) {
					movePiece(x.xLocation, x.yLocation - 1,c, x.direction);
					movePiece(x.xLocation + 1, x.yLocation, c,x.direction);
					movePiece(x.xLocation + 1, x.yLocation - 1, c,x.direction);
				}
				else if(x.isDouble) {
					movePiece(x.xLocation, x.yLocation - 1,c,x.direction);
				}
				else if(x.isStack) {
					movePiece(x.xLocation + 1, x.yLocation,c,x.direction);
				}
//			}
//			else if(x.direction.equals("LEFT")) {
//				movePieceLeftAt(x.xLocation, x.yLocation,c);
//				
//				if(x.isDouble && x.isStack) {
//					movePieceLeftAt(x.xLocation, x.yLocation - 1,c);
//					movePieceLeftAt(x.xLocation + 1, x.yLocation, c);
//					movePieceLeftAt(x.xLocation + 1, x.yLocation - 1, c);
//				}
//				else if(x.isDouble) {
//					movePieceLeftAt(x.xLocation, x.yLocation+1,c);
//				}
//				else if(x.isStack) {
//					movePieceLeftAt(x.xLocation + 1, x.yLocation,c);
//				}
//			}
//			else if(x.direction.equals("UP")) {
//				movePieceUpAt(x.xLocation, x.yLocation,c);
//				
//				if(x.isDouble && x.isStack) {
//					movePieceUpAt(x.xLocation, x.yLocation - 1,c);
//					movePieceUpAt(x.xLocation + 1, x.yLocation, c);
//					movePieceUpAt(x.xLocation + 1, x.yLocation - 1, c);
//				}
//				else if(x.isDouble) {
//					movePieceUpAt(x.xLocation, x.yLocation - 1,c);
//				}
//				else if(x.isStack) {
//					movePieceUpAt(x.xLocation + 1, x.yLocation,c);
//				}
//			}
//			else {
//				movePieceDownAt(x.xLocation, x.yLocation,c);
//			
//				if(x.isDouble && x.isStack) {
//					movePieceDownAt(x.xLocation, x.yLocation - 1,c);
//					movePieceDownAt(x.xLocation + 1, x.yLocation, c);
//					movePieceDownAt(x.xLocation + 1, x.yLocation - 1, c);
//				}
//				else if(x.isDouble) {
//					movePieceDownAt(x.xLocation, x.yLocation - 1,c);
//				}
//				else if(x.isStack) {
//					movePieceDownAt(x.xLocation + 1, x.yLocation,c);
//				}
//			}
			displayBoard(c);
			System.out.println("Config string representation: " + generateConfigString(c));
			System.out.println();
			}
	}
	
	public void displayBoard(char[][] x) {		
		for(int z = 1; z < 5; z++) {
			for(int j = 1; j < 6; j++) {
				System.out.print(x[z][j] + " ");
			}
			System.out.println("");
		}	
	}
	
	public void displayQueue(){
		for(possibleMove x : queue){
			System.out.println("Move piece at (" + x.xLocation + ", " + x.yLocation + ") " + x.direction);
		}		
	}

}
