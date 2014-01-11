package maze;

public class EBox extends MBox {
	
	private char type;

	public EBox(int posX, int posY) {
		super(posX, posY);
		type='E';
	}

	public char getType() {
		return this.type;
	}	
}