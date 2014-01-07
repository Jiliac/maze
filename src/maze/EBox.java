package maze;

public class EBox extends MBox {

	public EBox(int posX, int posY) {
		super(posX, posY);
	}

	public boolean getPEntry(MBox mBox) {
		return true;
	}

	public boolean getPExit(MBox mBox) {
		return true;
	}

	public char getType() {
		return 'E';
	}
}