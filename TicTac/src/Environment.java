
public class Environment {
	
	private Board board;
	private Player p1;
	private Player p2;
	private boolean turn;
	private int subBoard;
	
	

	
	public Environment(){
		
		board = new Board();
		p1 = new AI(1);
		p2 = new AI(2);
		turn = true;
		subBoard = -1;
		
	}
	
	public Board getBoard(){
		return board;
	}
	
	public void play(){
		
		int temp;
		MonteCarloTreeSearch mcts = new MonteCarloTreeSearch();
		Pair p;
		
		while((temp = board.checkWin()) < 0){
			
			if(turn){
				System.out.println(board.toString());
				p = p1.move(board, subBoard);
				board = p.board;
				subBoard = p.y;
				turn = false;
			}else{
				System.out.println(board.toString());
				p = p2.move(board, subBoard);
				board = p.board;
				subBoard = p.y;
				turn = true;
			}
		}
		System.out.println(board.toString());
		if(temp == 0){
			System.out.println("It's a tie");
		}else{
			System.out.println("Player " + temp + " wins!");
		}
	}
	
	public static void main(String[] args){
		Environment e = new Environment();
		e.play();
	}
	
	
}
