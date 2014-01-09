package Dijkstra;

import java.util.ArrayList;

public class Pi implements PiInterface {
	private ArrayList<Couple> alc;

	public Pi() {
		alc = new ArrayList<Couple>();
	}

	public void setPoids(VertexInterface vi, int poids) {
		/*
		 * deux cas a distinguer 1) le cas ou on initialise vi 2) le cas ou vi a
		 * deja un poids
		 */
		if (this.isIn(vi) == null) {
			Couple c = new Couple(vi, poids);
			alc.add(c);
		} else {
			isIn(vi).setPoids(poids);

		}

	}

	public int getPoids(VertexInterface vi) {
		int retour = Integer.MAX_VALUE;
		for (Couple c : alc) {
			if (c.equal(vi))
				retour = c.getPoids();
		}
		return retour;
	}

	private Couple isIn(VertexInterface vi) {
		Couple match = null;
		for (Couple c : alc) {
			if (c.equal(vi))
				match = c;
		}
		return match;
	}
}
