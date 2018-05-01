import java.util.List;

public class MonteCarlo extends Player{
	
	private int runs;
	
	public MonteCarlo(int name, int runs){
		this.name = name;
		this.runs = runs;
	}
	
	@Override
	public Pair move(Board board, int subBoard) {

		List<Position> moves = board.getEmptyPositions(subBoard);
		
		int max = Integer.MIN_VALUE;
		Position bestMove = moves.get(0);
		Board temp;
		
		for(Position move: moves){
			temp = new Board(board);
			temp.performMove(name, move);
			
			Environment e;
			int[] result = new int[3];
			for(int i =0; i < runs; i++){
				
				e = new Environment(temp);
				if(name == 1){
					e.p2 = new Minimax(2,1);
					e.turn = false;
				}else{
					e.p1 = new Minimax(1,1);
				}
				int res = e.play();
				result[res] += 1;
			}
			
			if(max < result[name]){
				max = result[name];
				bestMove = move;
			}
			
			
		}
		
		
		board.performMove(name, bestMove);
		

		if(board.checkBox(bestMove.getX() % 3 + bestMove.getY() % 3 * 3) != -1){
			return new Pair(board, -1);
		}
		
		
		
		return new Pair(board, bestMove.getX() % 3 + bestMove.getY() % 3 * 3);
	}

}
