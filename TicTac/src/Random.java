import java.util.List;

public class Random extends Player{
	
	public Random(int n){
		name = n;
	}

	@Override
	public Pair move(Board board, int subBoard) {
		
		List<Position> moves = board.getEmptyPositions(subBoard);
		
		if(subBoard == -1){
			
			
			//If any subBoard, pick random move and move
			Position p = moves.get((int)(Math.random() * moves.size()));
			
			board.performMove(name,p);
			
			if(board.checkBox((p.getY() % 3 * 3) + (p.getX() % 3)) != -1){
				return new Pair(board,-1);
			}
			
			return new Pair(board,(p.getY() % 3 * 3) + (p.getX() % 3));
			
		}else{
			int x = (int)(Math.random() * 9);
			Position temp = new Position((subBoard % 3) * 3 + x % 3, subBoard / 3 * 3 + x / 3);
			
			while(!moves.contains(temp)){
				x = (int)(Math.random() * 9);
				temp = new Position((subBoard % 3) * 3 + x % 3, subBoard / 3 * 3 + x / 3);
			}
			
			board.performMove(name, temp);
			
			if(board.checkBox(x) != -1){
				return new Pair(board, -1);
			}
			
			
			return new Pair(board, x);
		}
		
	}
}
