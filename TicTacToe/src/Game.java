import java.util.Scanner;

public class Game {
	
	/*
	Created by Lukas Rose 06/2018
	*/

	public static void main(String[] args) {
		
		System.out.println("Welcome to a Game of TicTacToe.");
		
		String [][] visualBoard = {
				{"   ", "|", "   ", "|", "   "},
				{"---", "|", "---", "|", "---"},
				{"   ", "|", "   ", "|", "   "},
				{"---", "|", "---", "|", "---"},
				{"   ", "|", "   ", "|", "   "}};
		
		int [][] valueBoard = new int[3][3]; //1 -> X,2 -> O
		boolean end = false;
		
		printBoard(visualBoard);
		
		int turnCounter = 1; 
		
		while(end == false) {
			turn(visualBoard, valueBoard, turnCounter);
			
			if (checkWin(valueBoard) == 1) {
				System.out.println("Game Over\nPlayer X won the Game.");
				end = true;
				break;
			}
			else if (checkWin(valueBoard) == 2) {
				System.out.println("Game Over\nPlayer O won the Game.");
				end = true;
				break;
			}
			
			if(turnCounter == 9) {
				System.out.println("Game Over\nThe Game has ended in a Draw.");
				break;
			}
			turnCounter++;
		}
	}
	
	public static void printBoard(String[][] visualBoard) {
		for(String[] row : visualBoard) {
			for(String col : row) {
				System.out.print(col);
			}
			System.out.println();
		}
	}
	
	public static int checkWin(int[][] valueBoard) {
		int n = 3; //board cube size
		
		for(int player = 1; player <= 2; player++) {
			for(int x = 0; x < n; x++) {
				
				for(int i = 0; i < n; i++) {//check for horizontal line win
					if(valueBoard[x][i] != player) break;
					if(i == n-1)return player;
				}
				
				for(int i = 0; i < n; i++) {//check for vertical line win
					if(valueBoard[i][x] != player) break;
					if(i == n-1)return player;
				}
				
				for(int i = 0; i < n; i++) {//check for diagonal line win
					if(valueBoard[i][i] != player) break;
					if(i == n-1)return player;
				}
				
				for(int i = 0; i < n; i++) {//check for anti-diagonal line win
					if(valueBoard[i][(n-1)-i] != player) break;
					if(i == n-1)return player;
				}
				
			}
		}
			
		return 0;
	}
	
	public static void turn(String[][] visualBoard, int[][] valueBoard, int turn) {
		Scanner scan = new Scanner(System.in);
		
		boolean error = true;
		
		int row = 0, column = 0;
		char player = '?';
		int value;
		
		if (turn % 2 != 0) {
			System.out.println("It is X's turn.\nSelect your placement (row,column)(1-3):");
			player = 'X';	
			value = 1;
		}
		else {
			System.out.println("It is O's turn.\nSelect your placement (row,column)(1-3):");
			player = 'O';
			value = 2;
		}
		
		while (error == true) {
			row = scan.nextInt();
			column = scan.nextInt();
			if (1 <= row && row <= 3 && 1 <= column && column <= 3) {
				if (valueBoard[row-1][column-1] == 0) {
					error = false;
				}
				else System.out.println("Error: That Space is Taken.");	
			}	
			else System.out.println("Error: Out of Bounds Space.");
		}
		valueBoard[row-1][column-1] = value;
		visualBoard[(row-1)*2][(column-1)*2] = " "+player+" ";
		printBoard(visualBoard);
		
		
		System.out.println("An "+player+" got placed at ("+row+","+column+")");
	}

}
