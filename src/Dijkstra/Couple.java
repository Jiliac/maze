package Dijkstra;

public class Couple {
	private VertexInterface vi;
	private int poids;
	private VertexInterface pere;

	public Couple(VertexInterface vi, int poids, VertexInterface pere) {
		this.vi = vi;
		this.poids = poids;
		this.pere=pere;
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}
	
	public void setPere(VertexInterface pere){
		this.pere=pere;
	}
	
	public VertexInterface getPere(){
		return this.pere;
	}

	public boolean equal(VertexInterface x) {
		boolean retour = false;
		if (x.getPosX() == vi.getPosX() && x.getPosY() == vi.getPosY())
			retour = true;
		return retour;
	}
}
