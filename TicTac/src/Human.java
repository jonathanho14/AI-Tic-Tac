import java.util.List;
import java.util.Scanner;

public class Human extends Player{
	
	public Human(int n){
		name = n;
	}


	@Override
	public Pair move(Board board, int subBoard) {
		
		Scanner reader = new Scanner(System.in);
		List<Position> moves = board.getEmptyPositions(subBoard);
		
		
		if(subBoard == -1){
			
			System.out.println("Enter a SubBoard (0 - 8): ");
			int x = reader.nextInt();
			System.out.println("Enter position (0 - 8): ");
			int y = reader.nextInt();
			while(!moves.contains(new Position(x,y))){
				//if move is not legal
				System.out.println("Please enter a legal move");
				System.out.println("Enter a SubBoard (0 - 8): ");
				x = reader.nextInt();
				System.out.println("Enter position (0 - 8): ");
				y = reader.nextInt();
			}
			reader.close();
			
			board.performMove(name, new Position((x % 3) * 3 + y % 3, x / 3 * 3 + y / 3));
			
			if(board.checkBox(y) != -1){
				return new Pair(board,-1);
			}
			
			
			return new Pair(board,y);
			
		}else{
			System.out.println("SubBoard: " + subBoard);
			System.out.println("Enter position (0 - 8): ");
			int x = reader.nextInt();
			Position temp = new Position((subBoard % 3) * 3 + x % 3, subBoard / 3 * 3 + x / 3);
			
			while(!moves.contains(temp)){
				System.out.println("Enter position (0 - 8): ");
				x = reader.nextInt();
				temp = new Position((subBoard % 3) * 3 + x % 3, subBoard / 3 * 3 + x / 3);
			}
			reader.close();
			
			board.performMove(name,  temp);
			
			if(board.checkBox(x) != -1){
				return new Pair(board,-1);
			}
			return new Pair(board,x);
		}
		
	}
	
	
}
