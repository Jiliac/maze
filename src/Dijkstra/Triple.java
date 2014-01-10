package Dijkstra;

public class Triple {
	private VertexInterface vi;
	private int poids;
	private Triple father;
	
	public Triple(){
		this.vi=null;
		this.poids=((int) Integer.MAX_VALUE/2);
		this.father=null;
	}

	public Triple(VertexInterface vi, int poids, Triple father) {
		this.vi = vi;
		this.poids = poids;
		this.father=father;
	}
	
	public VertexInterface getVi(){
		return this.vi;
	}
	
	public void setVi(VertexInterface vi){
		this.vi=vi;
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}
	
	public void setFather(Triple father){
		this.father=father;
	}
	
	public Triple getFather(){
		return this.father;
	}

	public boolean equal(VertexInterface x) {
		boolean retour = false;
		if (x.getPosX() == vi.getPosX() && x.getPosY() == vi.getPosY())
			retour = true;
		return retour;
	}
	
	public boolean equal(Triple t){
		return this.getVi().equal(t.getVi());
	}
}
