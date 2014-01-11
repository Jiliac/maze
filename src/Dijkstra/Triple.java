package Dijkstra;

public class Triple {
	/********** constructeurs **************/
	
	private VertexInterface vi;
	private int poids;
	private Triple father;

	public Triple() {
		this.vi = null;
		this.poids = ((int) Integer.MAX_VALUE / 2);
		this.father = null;
	}

	public Triple(VertexInterface vi, int poids, Triple father) {
		this.vi = vi;
		this.poids = poids;
		this.father = father;
	}
	
	/********** methode de test ***************/
	
	public boolean equal(Triple t) {
		boolean retour = false;
		if (this.vi == null) {
			retour = false;
		}

		else {
			if (t.getVi().getPosX() == this.vi.getPosX()
					&& t.getVi().getPosY() == this.vi.getPosY()) {
				retour = true;
			}
		}
		return retour;
	}
	
	public boolean isPrevious(Triple pivot){
		return vi.isPrevious(pivot.getVi());
	}
	
	public boolean isFather(Triple pivot){
		return father.equal(pivot);
	}
	
	public boolean isCrossable(){
		return vi.isCrossable();
	}

	/*********** getters et setters ***********/
	
	public VertexInterface getVi() {
		return this.vi;
	}

	public void setVi(VertexInterface vi) {
		this.vi = vi;
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}

	public void setFather(Triple father) {
		this.father = father;
	}

	public Triple getFather() {
		return this.father;
	}
}
