package maze;

import java.util.ArrayList;

import Dijkstra.VertexInterface;

public abstract class MBox implements VertexInterface {

	private int posX;
	private int posY;

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
		if (this.posX == x.getPosX() && this.posY == x.getPosY())
			return true;
		else
			return false;
	}

	public boolean isArrival() {
		if (this.getType() == 'A')
			return true;
		else
			return false;
	}
	
	public boolean isDeparture() {
		if (this.getType() == 'D')
			return true;
		else
			return false;
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
	
	// ************* implementation necessaire au shortestPath ****************
	
	private boolean isInShortestPath = false;

	public boolean isInShortestPath() {
		return isInShortestPath;
	}

	public void setInShortestPath(boolean isInShortestPath) {
		this.isInShortestPath = isInShortestPath;
	}
}
