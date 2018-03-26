
public class SubBoard {
	private int size = 0;
	private int result = -1;
	private int[] board = new int[9];
	
	private int checkWin(){
		
		
		for(int i = 0; i < 3; i++){
			
			if(board[i * 3] != 0 && board[i * 3] == board[i * 3 + 1] && board[i * 3 + 1] == board[i * 3 + 2]){
				return board[i * 3];
			}
			
			if(board[i] != 0 && board[i] == board[i + 3] && board[i + 3] == board[i + 6]){
				return board[i];
			}
			
		}
		
		if((board[4] != 0 && board[0] == board[4] && board[4] == board[8]) ||
				board[4] != 0 && board[2] == board[4] && board[4] == board[6]){
			return board[4];
		}
		
		if(size == 9){
			return 0;
		}
		
		return -1;
		
	}
	
	public SubBoard(){
		for(int i = 0; i < 9; i++){
			board[i] = 0;
		}
	}
	
	public int[] getBoard(){
		return board;
	}
	
	public int result(){
		
		return result;
	}
	
	public boolean move(int index, int player){
		
		if(result > -1){
			return false;
		}
		
		if(board[index] > 0){
			return false;
		}
		
		size++;
		board[index] = player;
		
		result = checkWin();
		
		return true;
		
	}
	
	public String toString(){
		
		String s = "";
		
		for(int i:board){
			if(i % 3 == 2){
				s += i + "\n";
			}else{
				s += i + ", ";
			}
		}
		return s;
	}
}
