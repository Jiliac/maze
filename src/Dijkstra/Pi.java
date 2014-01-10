package Dijkstra;

import java.util.ArrayList;

public class Pi implements PiInterface {
	private ArrayList<Triple> alc;

	public Pi() {
		alc = new ArrayList<Triple>();
	}

	public void setPoids(VertexInterface vi, int poids) {
		/*
		 * deux cas a distinguer 1) le cas ou on initialise vi 2) le cas ou vi a
		 * deja un poids
		 */
		if (this.isIn(vi) == null) {
			Triple t = new Triple(vi, poids, null);
			alc.add(t);
		} else {
			isIn(vi).setPoids(poids);

		}

	}

	public int getPoids(VertexInterface vi) {
		int retour = Integer.MAX_VALUE;
		for (Triple t : alc) {
			if (t.getVi().equal(vi))
				retour = t.getPoids();
		}
		return retour;
	}

	private Triple isIn(VertexInterface vi) {
		Triple match = null;
		for (Triple t : alc) {
			if (t.getVi().equal(vi))
				match = t;
		}
		return match;
	}
}
