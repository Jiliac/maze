package Dijkstra;

import java.util.ArrayList;

import maze.*;

public class Dijkstra {

	private GraphInterface gi;
	private PiInterface pi;
	private AsetInterface asi;

	public Dijkstra(GraphInterface gi, PiInterface pi, AsetInterface asi) {
		this.gi = gi;
		this.pi = pi;
		this.asi = asi;
	}

	public AsetInterface shortestPath() {
		gi.setPrevious();

		ArrayList<VertexInterface> viUnreached = new ArrayList<VertexInterface>();
		// tous les sommets sont non-atteints:

		for (VertexInterface vi : this.gi.getGraph()) {
			viUnreached.add(vi);
		}

		VertexInterface r, pivot;
		for (VertexInterface x : gi.getGraph()) {
			pi.setPoids(x, (int) Integer.MAX_VALUE / 2);
			// je divise par 2 parce que Integer.MAX_VALUE+1=Integer.MIN_VALUE
		}
		r = gi.getDeparture();
		pivot = r;
		pi.setPoids(r, 0);
		asi.add(r);

		// Penser a mettre r et le pivot inShortestPath

		boolean done_win = false;
		boolean done_lose = false;
		int size = -1;
		int count = 0;

		while (done_win == false && done_lose == false) {

			// le pivot est un sommet atteint
			viUnreached.remove(pivot);

			// taille de l'ensemble des sommets non-atteints
			int lengh = viUnreached.size();

			for (int iterator = 0; iterator < lengh; iterator++) {

				// x sommet non-atteint
				VertexInterface x = viUnreached.get(iterator);

				System.out.println("test01");
				System.out.println(gi.isPrevious(pivot, x));

				if (/* !asi.isInAset(x) && */gi.isPrevious(pivot, x)) {
					System.out.println("test02");

					// x est a proximite du pivot et on actualise son poids

					int poidsPivot = pi.getPoids(pivot);
					int poidsX = pi.getPoids(x);
					int poidsArc = gi.getPoids(pivot, x);

					if (poidsPivot + poidsArc < poidsX) {
						pi.setPoids(x, poidsPivot + poidsArc);
						System.out.println("Nouveau poids du sommet (X="
								+ Integer.toString(x.getPosX()) + " et Y="
								+ Integer.toString(x.getPosY()) + ") est de "
								+ Integer.toString(poidsX));

					}

					// on cherche un nouveau pivot
					pivot = asi.getMin(gi);
					asi.add(pivot);
					System.out.println("Pivot d'ordonne "
							+ Integer.toString(((MBox) pivot).getPosX())
							+ " et d'abscisse "
							+ Integer.toString(((MBox) pivot).getPosX()));

					// on regarde si on est sorti
					if (pivot.isArrival()) {
						System.out.println("J'ai trouve la sortie!");
						done_win = true;
					}
				}
			}

			lengh = viUnreached.size();

			// on regarde si on est perdu
			if (asi.size() != size) {
				size = asi.size();
			} else {
				count++;
			}
			if (count == 2) {
				System.out
						.println("Je suis coince a tout jamais dans ce labyrinthe la!");
				done_lose = true;
			}
		}
		return asi;
	}
	
	public ASetInterface court(){
		
	
		gi.setPrevious();
		
		VertexInterface r, pivot;
		
		for (VertexInterface x : gi.getGraph()) {
			pi.setPoids(x, (int) Integer.MAX_VALUE / 2);
		}
		
		r = gi.getDeparture();
		pivot = r;
		pi.setPoids(r, 0);
		asi.add(r);

		boolean done_win = false;
		boolean done_lose = false;
		int size = -1;
		int count = 0;
		
		ArrayList<VertexInterface> unreached = new ArrayList<VertexInterface>();

		for (VertexInterface vi : this.gi.getGraph()) {
			unreached.add(vi);
		}
		
		while (done_win == false && done_lose == false) {
			
		int lengh = asi.size();
			
			for (int iterator = 0; iterator < lengh; iterator++) {
				
				
			}
		
	}
	}
	
	
}
