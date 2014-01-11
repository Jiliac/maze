package maze;

public class EBox extends MBox {
	
	private char type;

	public EBox(int posX, int posY) {
		super(posX, posY);
		type='E';
	}

	public boolean getPEntry(MBox mBox) {
		return true;
	}

	public boolean getPExit(MBox mBox) {
		return true;
	}

	public char getType() {
		return this.type;
	}
	
	public void setType(){
		type='*';
	}
	
}