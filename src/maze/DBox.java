package maze;

public class DBox extends EBox {

	public DBox(int posX, int posY) {
		super(posX, posY);
	}

	public boolean getPEntry(MBox mBox) {
		return false;
	}

	public char getType() {
		return 'D';
	}

}