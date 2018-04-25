import java.util.List;

public class Minimax extends Player{

	private int level;
	
	public Minimax(int n, int l){
		name = n;
		level = l;
	}
	
	@Override
	public Pair move(Board board, int subBoard) {

		List<Position> moves = board.getEmptyPositions(subBoard);
		
		if(moves.size() == 0){
			System.out.println("No possible moves");
			return new Pair(board, -1);
		}
		
		double max = Integer.MIN_VALUE;
		Position bestMove = moves.get(0);
		Board temp;
		
		for(Position move: moves){
			temp = new Board(board);
			temp.performMove(name, move);
			
			List<Position> nextMoves = temp.getEmptyPositions(move.getX() % 3 + move.getY() % 3 * 3);
			
			if(nextMoves.size() == 0){
				max = Integer.MAX_VALUE;
				bestMove = move;
			}
			
			double min = Integer.MAX_VALUE;
			
			for(Position m: nextMoves){
				if(H(temp,m) < min){
					min = H(temp, m);
				}
			}
			
			if(min > max){
				bestMove = move;
				max = min;
			}
			
			
		}
		
		System.out.println(H(board, bestMove));
		
		board.performMove(name, bestMove);
		

		if(board.checkBox(bestMove.getX() % 3 + bestMove.getY() % 3 * 3) != -1){
			return new Pair(board, -1);
		}
		
		
		
		return new Pair(board, bestMove.getX() % 3 + bestMove.getY() % 3 * 3);
	}
	
	
	private double H(Board b, Position p){
		
		b.performMove(name, p);
		
		double sum = 0;
		double count;
		
		
		//check vertical
		for(int i = 0; i < 3; i ++){
			count = 0;
			for(int j = 0; j<3; j++){
				if(b.checkBox(new Position(i,j)) != name && b.checkBox(new Position(i,j)) != -1){
					count = Integer.MIN_VALUE;
				}else if(b.checkBox(new Position(i,j)) == name){
					count += 24;
				}else{
					count+=subH(b, new Position(i,j));
				}
			}
			if(count > 0){
				sum += 0.415509 * Math.exp(1.35173 * count / 8);
			}
		}
		
		//check vertical
		for(int j = 0; j < 3; j ++){
			count = 0;
			for(int i = 0; i<3; i++){
				if(b.checkBox(new Position(i,j)) != name && b.checkBox(new Position(i,j)) != -1){
					count = Integer.MIN_VALUE;
				}else if(b.checkBox(new Position(i,j)) == name){
					count += 24;
				}else{
					count+=subH(b, new Position(i,j));
				}
			}
			if(count > 0){
				sum += 0.415509 * Math.exp(1.35173 * count / 24);
			}
		}
		
		//check diagonal down
		count = 0;
		for(int i = 0; i<3; i++){
			if(b.checkBox(new Position(i,i)) != name && b.checkBox(new Position(i,i)) != -1){
				count = Integer.MIN_VALUE;
			}else{
				count+=subH(b, new Position(i,i));
			}
		}
		if(count > 0){
			sum += 0.415509 * Math.exp(1.35173 * count / 8);
		}

		
		//check diagonal up
		count = 0;
		for(int i = 0; i<3; i++){
			if(b.checkBox(new Position(i,2 - i)) != name && b.checkBox(new Position(i,2 - i)) != -1){
				count = Integer.MIN_VALUE;
			}else{
				count+=subH(b, new Position(i,2 - i));
			}
		}
		if(count > 0){
			sum += 0.415509 * Math.exp(1.35173 * count / 8);
		}

		return sum;
		
	}
	
	private int subH(Board b, Position sub){
		
		int x = sub.getX() * 3;
		int y = sub.getY() * 3;
		
		int sum = 0;
		int count;
		
		//check vertical
		for(int i = 0; i < 3; i ++){
			count = 0;
			for(int j = 0; j<3; j++){
				if(b.getBoard()[x + i][y + j] != name && b.getBoard()[x + i][y + j] != 0){
					count = -5;
				}else if(b.getBoard()[x + i][y + j] == name){
					count++;
				}
			}
			if(count == 1){
				sum+= 2;
			}
			if(count == 2){
				sum += 6;
			}
			if(count == 3){
				return 24;
			}
		}
		
		//check horizontal
		for(int j = 0; j < 3; j ++){
			count = 0;
			for(int i = 0; i<3; i++){
				if(b.getBoard()[x + i][y + j] != name && b.getBoard()[x + i][y + j] != 0){
					count = -5;
				}else if(b.getBoard()[x + i][y + j] == name){
					count++;
				}
			}
			if(count == 1){
				sum+= 2;
			}
			if(count == 2){
				sum += 6;
			}
			if(count == 3){
				return 24;
			}
		}
		
		//check diagonal down
		for(int i = 0; i < 3; i++){
			count = 0;
			if(b.getBoard()[x + i][y + i] != name && b.getBoard()[x + i][y + i] != 0){
				count = -5;
			}else if(b.getBoard()[x + i][y + i] == name){
				count++;
			}
			if(count == 1){
				sum+= 2;
			}
			if(count == 2){
				sum += 6;
			}
			if(count == 3){
				return 24;
			}
		}
		
		//check diagonal up
		for(int i = 0; i < 3; i++){
			count = 0;
			if(b.getBoard()[x + i][y + 2 - i] != name && b.getBoard()[x + i][y + 2 - i] != 0){
				count = -5;
			}else if(b.getBoard()[x + i][y + 2 - i] == name){
				count++;
			}
			if(count == 1){
				sum+= 2;
			}
			if(count == 2){
				sum += 6;
			}
			if(count == 3){
				return 24;
			}
		}
		
		return Math.min(sum, 24);
		
	}
	

}
