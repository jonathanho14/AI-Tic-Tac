
public abstract class Player {
	protected int name;
	
	public int getName(){
		return name;
	}

	
	abstract public Pair move(Board board, int subBoard);
	
}
