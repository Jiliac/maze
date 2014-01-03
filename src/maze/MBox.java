package maze;

import java.util.ArrayList;

import Dijkstra.VertexInterface;

public abstract class MBox implements VertexInterface {

	private final int posX;
	private final int posY;

	// ****** constructeurs ***********

	protected MBox(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	// ********** definition des box *************

	public abstract boolean getPEntry(MBox mBox);

	public abstract boolean getPExit(MBox mBox);

	public abstract char getType();

	// ********** les box sont des vertex **********

	public boolean equal(VertexInterface x) {
		boolean retour;
		if (this.posX == x.getPosX() && this.posY == x.getPosY())
			retour = true;
		else
			retour = false;
		return retour;
	}

	public boolean isArrival() {
		boolean retour;
		if (this.getType() == 'A')
			retour = true;
		else
			retour = false;
		return retour;
	}

	// ********** gestion de parente ********

	private ArrayList<VertexInterface> alFils = new ArrayList<VertexInterface>();

	public void addFils(VertexInterface vi) {
		alFils.add(vi);
	}

	public boolean isPrevious(VertexInterface vi) {
		boolean retour = false;
		for (VertexInterface fils : alFils) {
			if (fils.equal(vi))
				retour = true;
		}
		return retour;
	}

	// ********** getters **********

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
}
