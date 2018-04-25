
public class Environment {
	
	private Board board;
	private Player p1;
	private Player p2;
	private boolean turn;
	private int subBoard;
	
	

	
	public Environment(){
		
		board = new Board();
		p1 = new Random(1);
		p2 = new Random(2);
		turn = true;
		subBoard = -1;
		
	}
	
	public Environment(Player p1, Player p2){
		
		board = new Board();
		this.p1 = p1;
		this.p2 = p2;
		turn = true;
		subBoard = -1;		
		
		
	}
	
	public Board getBoard(){
		return board;
	}
	
	public int play(int randPlays){
		
		
		Player p1t = p1;
		Player p2t = p2;
		
		p1 = new Random(p1.name);
		p2 = new Random(p2.name);
		int temp;
		Pair p;
		int count = 0;
		while((temp = board.checkWin()) < 0){
			if(count == randPlays){
				p1 = p1t;
				p2 = p2t;
			}
			if(turn){
				//System.out.println(board.toString());
				p = p1.move(board, subBoard);
				board = p.board;
				subBoard = p.y;
				turn = false;
			}else{
				//System.out.println(board.toString());
				p = p2.move(board, subBoard);
				board = p.board;
				subBoard = p.y;
				turn = true;
			}
			count++;
		}
		//System.out.println(board.toString());
		if(temp == 0){
			System.out.println("It's a tie");
			return 0;
		}else{
			System.out.println("Player " + temp + " wins!");
			return temp;
		}
	}
	
	public int play(){
		
		int temp;
		Pair p;
		while((temp = board.checkWin()) < 0){
			if(turn){
				//System.out.println(board.toString());
				p = p1.move(board, subBoard);
				board = p.board;
				subBoard = p.y;
				turn = false;
			}else{
				//System.out.println(board.toString());
				p = p2.move(board, subBoard);
				board = p.board;
				subBoard = p.y;
				turn = true;
			}
		}
		//System.out.println(board.toString());
		if(temp == 0){
			System.out.println("It's a tie");
			return 0;
		}else{
			System.out.println("Player " + temp + " wins!");
			return temp;
		}
	}
	
	public static void main(String[] args){
		Environment e = new Environment();
		
		int[] result = new int[3];
		Player p1 = new Random(1);
		Player p2 = new Minimax(2,1);
		for(int i =0; i < 1000; i++){
			if(i % 2 == 0){
				e = new Environment();
			}else{
				e = new Environment();
			}
			int temp = e.play();
			result[temp] += 1;
		}
		
		System.out.println(result[0] + ", " + result[1] + ", " + result[2]);
	}
	
	
}
