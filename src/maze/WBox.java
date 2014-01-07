package maze;

public class WBox extends MBox {

	public WBox(int posX, int posY) {
		super(posX, posY);
	}

	public boolean getPEntry(MBox mBox) {
		return false;
	}

	public boolean getPExit(MBox mBox) {
		return false;
	}

	public char getType() {
		return 'W';
	}
}