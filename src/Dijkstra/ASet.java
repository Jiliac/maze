package Dijkstra;

import java.util.ArrayList;

import maze.EBox;

public class ASet implements AsetInterface {
	private ArrayList<VertexInterface> alVi;
	private PiInterface pi;

	public ASet(PiInterface pi) {
		alVi = new ArrayList<VertexInterface>();
		this.pi = pi;
	}
	
	// *************** formation de liste ***********************

	public ArrayList<VertexInterface> removeAlVi(GraphInterface gi){
		ArrayList<VertexInterface> retour = gi.getGraph();
		for (VertexInterface vi : this.alVi){
			retour.remove(vi);
		}
		return retour;
	}
	
	// methode inutile mtn, non?
	public boolean isInAset(VertexInterface vi) {
		boolean retour = false;
		for (VertexInterface x : alVi) {
			if (x.getPosX() == vi.getPosX() && x.getPosY() == vi.getPosY())
				retour = true;
		}
		return retour;
	}

	// *************** recherche dans la liste ***************
	
	public VertexInterface getMin(GraphInterface gi) {
		VertexInterface retour = new EBox(-1, -1);
		pi.setPoids(retour, Integer.MAX_VALUE);

		ArrayList<VertexInterface> listParcourir = this.removeAlVi(gi);

		for (VertexInterface vi : listParcourir) {
			if (pi.getPoids(vi) < pi.getPoids(retour)) {
				retour = vi;
			}
		}
		return retour;
	}
	
	// ************** methodes simples **************

	public void add(VertexInterface vi) {
		alVi.add(vi);
	}

	public ArrayList<VertexInterface> getSommet() {
		return this.alVi;
	}

	public int size() {
		return alVi.size();
	}

}
