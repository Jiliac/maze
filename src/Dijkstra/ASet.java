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

	public boolean isInAset(VertexInterface vi) {
		boolean retour = false;
		for (VertexInterface x : alVi) {
			if (x.getPosX() == vi.getPosX() && x.getPosY() == vi.getPosY())
				retour = true;
		}
		return retour;
	}

	public VertexInterface getMin() {
		VertexInterface retour = new EBox(-1, -1);
		pi.setPoids(retour, Integer.MAX_VALUE);
		for (VertexInterface vi : alVi) {
			if (pi.getPoids(vi) < pi.getPoids(retour)) {
				retour = vi;
			}
		}
		return retour;
	}

	public void add(VertexInterface vi) {
		alVi.add(vi);
	}
	
	public ArrayList<VertexInterface> getSommet(){
		return this.alVi;
	}
}
