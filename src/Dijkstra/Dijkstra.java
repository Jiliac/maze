package Dijkstra;

import java.util.ArrayList;
import maze.*;

public class Dijkstra {

	private GraphInterface gi;

	public Dijkstra(GraphInterface gi) {
		this.gi = gi;
	}

	public ArrayList<VertexInterface> shortestPath() {

		gi.setPrevious();
		ArrayList<Triple> unreached = new ArrayList<Triple>();

		Triple r = null;
		Triple god = new Triple(null, 10000, null);

		for (VertexInterface vi : this.gi.getGraph()) {
			if (vi.isDeparture() == false) {
				unreached.add(new Triple(vi, (int) Integer.MAX_VALUE / 2, god));
			} else {
				r = new Triple(vi, 0, null);
			}
		}

		Triple pivot = r;

		boolean done_win = false;
		boolean done_lose = false;
		int size = -1;
		int count = 0;

		while (!done_win && !done_lose) {

			int lengh = unreached.size();

			for (int iterator = 0; iterator < lengh; iterator++) {

				Triple x = unreached.get(iterator);

				if (x.isPrevious(pivot) && unreached.contains(x)
						&& !x.isFather(pivot) && x.isCrossable()
						&& !x.equal(pivot)) {

					int ancien = x.getPoids();
					int nouveau = pivot.getPoids() + 1;

					if (nouveau < ancien) {
						x.setPoids(nouveau);
						x.setFather(pivot);
					}
				}
			}

			// setMin()
			Triple min = new Triple(null, (int) Integer.MAX_VALUE / 2, null);
			for (int iterator = 0; iterator < lengh; iterator++) {
				Triple y = unreached.get(iterator);
				if (min.getPoids() > y.getPoids()) {
					min = y;
				}
			}
			
			// Cas particulier: coince ou chemin trouve
			if (min.getPoids() + 10 > (int) Integer.MAX_VALUE / 2) {
				System.out
						.println("Je suis coince a tout jamais dans ce labyrinthe la!");
				done_lose = true;
			} else {
				pivot = min;
				unreached.remove(pivot);
				if (pivot.getVi().isArrival()) {
					System.out.println("J'ai trouve la sortie!\nle chemin est:\n");
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

		
		// on cree la liste a retourner
		ArrayList<VertexInterface> chemin = new ArrayList<VertexInterface>();
		if (!done_lose && done_win) {
			VertexInterface[] tab = new VertexInterface[pivot.getPoids() + 1];

			Triple z = pivot;
			for (int p = pivot.getPoids(); p >= 0; p += -1) {
				tab[p] = z.getVi();
				z = z.getFather();
			}

			for (int i = 0; i < tab.length; i++) {
				chemin.add(tab[i]);
				System.out.println("Passons par ("
						+ Integer.toString(tab[i].getPosX()) + ", "
						+ Integer.toString(tab[i].getPosY()) + ")");
			}
			System.out.println("Je suis libre!\n\n**************************\n");
		}
		return chemin;
	}

}
