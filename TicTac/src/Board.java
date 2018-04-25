import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Board {


    public static final int IN_PROGRESS = -1;
    public static final int DRAW = 0;
	
	private int[][] board;
	private int moves;
	
	public int checkWin(){
		
		for(int i = 0; i < 3; i++){
			
			if(checkBox(new Position(i,0)) != 0 && checkBox(new Position(i,0)) == checkBox(new Position(i,1))
					&& checkBox(new Position(i,0)) == checkBox(new Position(i,2))){
				return checkBox(new Position(i,0));
			}
			
			if(checkBox(new Position(0,i)) != 0 && checkBox(new Position(0,i)) == checkBox(new Position(1,i)) 
					&& checkBox(new Position(0,i)) == checkBox(new Position(2,i))){
				return checkBox(new Position(0,i));
			}
			
		}
		
		if((checkBox(new Position(1,1)) != 0) && ((checkBox(new Position(0,0)) == checkBox(new Position(1,1))
				&& checkBox(new Position(0,0)) == checkBox(new Position(2,2))) ||
				(checkBox(new Position(2,0)) == checkBox(new Position(1,1)) 
						&& checkBox(new Position(2,0)) == checkBox(new Position(0,2))))){
			return checkBox(new Position(1,1));
		}
		
        if (getEmptyPositions(-1).size() > 0)
            return IN_PROGRESS;
        else
            return DRAW;
        
	}

	public int checkBox(int subBoard){
		return checkBox(new Position(subBoard % 3, subBoard / 3));
	}
	
	public int checkBox(Position p){
		int x = p.getX();
		int y = p.getY();
		
		for(int i = 0; i < 3; i++){
			
			if(board[3 * x][3 * y + i] != 0){
				if(board[3 * x][3 * y + i] == board[3 * x + 1][3 * y + i] && 
						board[3 * x + 1][3 * y + i] == board[3 * x + 2][3 * y + i]){
					return board[3 * x][3 * y + i];
				}
			}
			
			if(board[3 * x + i][3 * y] != 0){
				if(board[3 * x + i][3 * y] == board[3 * x + i][3 * y + 1] && 
						board[3 * x + i][3 * y] == board[3 * x + i][3 * y + 2]){
					return board[3 * x + i][3 * y];
				}
			}
			
		}
		
		if(board[3 * x + 1][3 * y + 1] != 0){
			if((board[3 * x][3 * y] == board[3 * x + 1][3 * y + 1] && 
					board[3 * x][3 * y] == board[3 * x + 2][3 * y + 2]) || 
					(board[3 * x + 2][3 * y] == board[3 * x + 1][3 * y + 1] && 
							board[3 * x + 2][3 * y] == board[3 * x][3 * y + 2])){
				return board[3 * x + 1][3 * y + 1];
			}
		}
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(board[3 * x + i][3 * y + j] == 0){
					return IN_PROGRESS;
				}
			}
		}
		
		return DRAW;
	}
	
	public Board(){
		board = new int[9][9];
		moves = 0;
	}
	
	public Board(Board b){
		
        int boardLength = b.getBoard().length;
        this.board = new int[boardLength][boardLength];
        int[][] boardValues = b.getBoard();
        int n = boardValues.length;
        
        for (int i = 0; i < n; i++) {
            int m = boardValues[i].length;
            for (int j = 0; j < m; j++) {
                this.board[i][j] = boardValues[i][j];
            }
        }
	}
	
	public int[][] getBoard(){
		return board;
	}
	
	public void performMove(int player, Position p){
		
		moves++;
		board[p.getX()][p.getY()] = player;
		
	}
	
	public List<Position> getEmptyPositions(int subBoard){
		
		int size = board.length;
		
		List<Position> emptyPositions = new ArrayList<>();
		
		
		if(subBoard == -1){
	        for (int i = 0; i < size; i++) {
	            for (int j = 0; j < size; j++) {
	                if (board[i][j] == 0 && checkBox(new Position(i/3,j/3)) == IN_PROGRESS){
	                    emptyPositions.add(new Position(i, j));
	                }
	            }
	        }
		}else{
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					if(board[i + ((subBoard % 3) * 3)][j + ((subBoard / 3) * 3)] == 0){
						emptyPositions.add(new Position(i + ((subBoard % 3) * 3), j + ((subBoard / 3) * 3)));
					}
				}
			}
		}
        
		return emptyPositions;
		
	}
	

	public String toString(){
		String s = "-----------------------\n \n";
		
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				s += board[j][i];
				if(j % 3 == 2){
					s += " ";
				}
				if(j == 8){
					s += "\n";
					if(i % 3 == 2){
						s += "\n";
					}
				}
				
			}
		}
		
		return s;
		
	}

	
}
