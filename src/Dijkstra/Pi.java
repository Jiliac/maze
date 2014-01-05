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
		if (!this.isIn(vi)) {
			Couple c = new Couple(vi, poids);
			alc.add(c);
		} else {
			for (Couple c : alc) {
				if (c.equal(vi)) {
					// je fais deux fois la meme chose il y avait surement une
					// facon de faire quelaue chose de mieux...
					
					/*  <YM> oui, initialiser tous les vertex dès le début avec un poids infini  </YM> */
					
					c.setPoids(poids);
				}
			}
		}

	}
	
	public int getPoids(VertexInterface vi){
		int retour = Integer.MAX_VALUE;
		for(Couple c : alc){
			if(c.equal(vi))
				retour = c.getPoids();
		}
		return retour;
	}

	private boolean isIn(VertexInterface vi) {
		boolean retour = false;
		for (Couple c : alc) {
			if (c.equal(vi))
				retour = true;
		}
		return retour;
	}
}
