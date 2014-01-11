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

	public AsetInterface shortestPath2() {
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

	public ArrayList<VertexInterface> shortestPath() {

		gi.setPrevious();

		ArrayList<Triple> unreached = new ArrayList<Triple>();

		Triple r = null;
		Triple god = new Triple(null, 10000, null);

		for (VertexInterface vi : this.gi.getGraph()) {
			if (vi.isDeparture()==false) {
				unreached.add(new Triple(vi, (int) Integer.MAX_VALUE / 2, god));
				System.out.println("Initialisation du sommet non-atteint: ("
						+ Integer.toString(vi.getPosX()) + ", "
						+ Integer.toString(vi.getPosY()) + ")");
			} else{
				r = new Triple(vi, 0, null);
				System.out.println("J'ai un depart : ("
						+ Integer.toString(vi.getPosX()) + ", "
						+ Integer.toString(vi.getPosY()) + ")");
			}
		}

		Triple pivot = r;

		boolean done_win = false;
		boolean done_lose = false;
		int size = -1;
		int count = 0;

		while (done_win == false && done_lose == false) {

			int lengh = unreached.size();

			for (int iterator = 0; iterator < lengh; iterator++) {

				Triple x = unreached.get(iterator);

				if (x.getVi().isPrevious(pivot.getVi())
						&& unreached.contains(x) && x.getFather().equal(pivot)==false
						&& x.getVi().isCrossable() && x.equal(pivot)==false) {

					int ancien = x.getPoids();
					int nouveau = pivot.getPoids() + 1;

					if (nouveau < ancien) {
						x.setPoids(nouveau);
						x.setFather(pivot);
						/*
						System.out.println("Le nouveau poids du sommet ("
								+ Integer.toString(x.getVi().getPosX()) + ", "
								+ Integer.toString(x.getVi().getPosY())
								+ ") est " + Integer.toString(nouveau));
						*/
					}
				}

			}

			Triple min = new Triple(null, (int) Integer.MAX_VALUE / 2, null);

			for (int iterator = 0; iterator < lengh; iterator++) {

				Triple y = unreached.get(iterator);

				if (min.getPoids() > y.getPoids()) {
					min = y;
				}
			}
			if (min.getPoids() + 10 > (int) Integer.MAX_VALUE / 2) {
				System.out
						.println("Je suis coince a tout jamais dans ce labyrinthe la!");
				done_lose = true;
			} else {
				pivot = min;
				unreached.remove(pivot);
				if (pivot.getVi().isArrival()) {
					System.out.println("J'ai trouve la sortie!");
					done_win = true;
				}
			}
			if (unreached.size() != size) {
				size = unreached.size();
			} else {
				count++;
			}
			if (count == 2) {
				System.out
						.println("Je suis coince a tout jamais dans ce labyrinthe la!");
				done_lose = true;
			}
		}

		ArrayList<VertexInterface> chemin = new ArrayList<VertexInterface>();

		if (done_lose == false && done_win == true) {
			VertexInterface[] tab = new VertexInterface[pivot.getPoids() + 1];

			Triple z = pivot;
			for (int p = pivot.getPoids(); p >= 0; p = p - 1) {
				tab[p] = z.getVi();
				// chemin.add(p, z.getVi());
				z = z.getFather();
			}

			for (int i = 0; i < tab.length; i++) {
				chemin.add(tab[i]);
				System.out.println("Passons par ("
						+ Integer.toString(tab[i].getPosX()) + ", "
						+ Integer.toString(tab[i].getPosY())
						+ ")");
			}
			System.out.println("Je suis libre!");
		} else {
			chemin = null;
		}
		return chemin;
	}
}
