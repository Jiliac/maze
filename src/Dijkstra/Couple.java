package Dijkstra;

public class Couple {
	private VertexInterface vi;
	private int poids;

	public Couple(VertexInterface vi, int poids) {
		this.vi = vi;
		this.poids = poids;
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}

	public boolean equal(VertexInterface x) {
		boolean retour = false;
		if (x.getPosX() == vi.getPosX() && x.getPosY() == vi.getPosY())
			retour = true;
		return retour;
	}
}
