import java.util.Scanner;
public class Battleship {
	
	public static void main(String[] args) {
		System.out.println("Welcome to Battleship!");
		
		//create two 2d arrays of player's boards and fill all elements with - because empty
		char[][] p1 = new char[5][5];
		char[][] p2 = new char[5][5];
		//create two 2d arrays of player's view of opposing boards and fill all elements with - because empty
		char[][] p1p2 = new char[5][5];
		char[][] p2p1 = new char[5][5];
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				p1[i][j] = '-';
				p2[i][j] = '-';
				p1p2[i][j]='-';
				p2p1[i][j]='-';
			}
		}
		
		//Player 1 ships
		System.out.println("PLAYER 1, ENTER YOUR SHIPS' COORDINATES.");
		p1 = enterShips(p1);
		printBattleShip(p1);
		print100Lines();
		
		//Player 2 ships
		System.out.println("PLAYER 2, ENTER YOUR SHIPS' COORDINATES.");
		p2 = enterShips(p2);
		printBattleShip(p2);
		print100Lines();

		
		int[] location = new int[2];
		int p1hits = 0;
		int p2hits = 0;
		
		while(true) {
			location = enterShip(1,p1p2);
			if(p2[location[0]][location[1]]=='@') {
				System.out.println("PLAYER 1 HIT PLAYER 2’s SHIP!");
				p1p2[location[0]][location[1]] = 'X';
				p2[location[0]][location[1]] = 'X';
				p1hits++;
				if(p1hits==5) {
					System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!");
					break;
				}
			}
			else {
				System.out.println("PLAYER 1 MISSED!");
				p1p2[location[0]][location[1]] = 'O';
				p2[location[0]][location[1]] = 'O';
			}
			printBattleShip(p1p2);
			
			location = enterShip(2,p2p1);
			if(p1[location[0]][location[1]]=='@') {
				System.out.println("PLAYER 2 HIT PLAYER 1’s SHIP!");
				p2p1[location[0]][location[1]] = 'X';
				p1[location[0]][location[1]] = 'X';
				p2hits++;
				if(p2hits==5) {
					System.out.println("PLAYER 2 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!");
					break;
				}
			}
			else {
				System.out.println("PLAYER 2 MISSED!");
				p2p1[location[0]][location[1]] = 'O';
				p1[location[0]][location[1]] = 'O';
			}
			printBattleShip(p2p1);
		}
		System.out.println("Final boards:");
		printBattleShip(p1);
		System.out.println("");
		printBattleShip(p2);
		
		System.out.println("GAME OVER");
	}
	
	private static void print100Lines() {
		for (int i = 0; i<100; i++) System.out.println("-");
	}
	
	private static char[][] enterShips(char[][] p){
		Scanner in  = new Scanner(System.in);
		for(int i = 0; i<5; i++) {
			while(true) {
				System.out.println("Enter ship "+(i+1)+" location:");
				int x = in.nextInt();
				int y = in.nextInt();
				if(x>=5 || x <0 || y>=5 || y<0) {
					System.out.println("Invalid coordinates. Choose different coordinates.");
					continue;
				}
				else if(p[x][y]=='@') {
					System.out.println("You already have a ship there. Choose different coordinates.");
					continue;
				}
				else{
					p[x][y] = '@';
					break;
				}
			}
		}
		return p;
	}
	
	private static int[] enterShip(int a, char[][] p1p2){
		int[] location = new int[2];
		Scanner in  = new Scanner(System.in);
		while(true) {
			System.out.println("Player "+(a)+", enter hit row/column:");
			location[0] = in.nextInt();
			location[1] = in.nextInt();
			if(location[0]>=5 || location[0] <0 || location[1]>=5 || location[1]<0) {
				System.out.println("Invalid coordinates. Choose different coordinates.");
				continue;
			}
			else if(p1p2[location[0]][location[1]]!='-') {
				System.out.println("You already fired on this spot. Choose different coordinates.");
				continue;
			}
			else break;
		}
		return location;
	}

	// Use this method to print game boards to the console.
	private static void printBattleShip(char[][] player) {
		System.out.print("  ");
		for (int row = -1; row < 5; row++) {
			if (row > -1) {
				System.out.print(row + " ");
			}
			for (int column = 0; column < 5; column++) {
				if (row == -1) {
					System.out.print(column + " ");
				} else {
					System.out.print(player[row][column] + " ");
				}
			}
			System.out.println("");
		}
	}
}
