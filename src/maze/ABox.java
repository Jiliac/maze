package maze;

public class ABox extends EBox {

	public ABox(int posX, int posY) {
		super(posX, posY);
	}

	public boolean getPExit(MBox mBox) {
		return false;
	}

	public char getType() {
		return 'A';
	}
}