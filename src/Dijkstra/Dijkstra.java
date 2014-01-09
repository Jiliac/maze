package Dijkstra;

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
		VertexInterface r, pivot;
		for (VertexInterface x : gi.getGraph()) {
			pi.setPoids(x, (int) Integer.MAX_VALUE / 2);
			// je divise par 2 parce que Integer.MAX_VALUE+1=Integer.MIN_VALUE
		}
		r = gi.getDeparture();
		pivot = r;
		pi.setPoids(r, 0);
		asi.add(r);

		gi.setPrevious();

		boolean done_win = false;
		boolean done_lose = false;
		int size=-1;
		int count=0;

		while (done_win == false && done_lose == false) {
			
			for (VertexInterface x : gi.getGraph()) {
				System.out.println("test01");
				System.out.println(gi.isPrevious(pivot, x));
				if (!asi.isInAset(x) && gi.isPrevious(pivot, x)) {
					System.out.println("test02");

					if (pi.getPoids(pivot) + gi.getPoids(pivot, x) < pi
							.getPoids(x)) {
						System.out.println(Integer.toString(pi.getPoids(pivot) + gi.getPoids(pivot, x)));
						pi.setPoids(x,
								pi.getPoids(pivot) + gi.getPoids(pivot, x));

					}
					pivot = asi.getMin();
					asi.add(pivot);
					System.out.println("Sommet d'ordonnee "
							+ Integer.toString(((MBox) pivot).getPosX())
							+ " et d'abscisse "
							+ Integer.toString(((MBox) pivot).getPosX()));
					if (pivot.isArrival()) {
						System.out.println("J'ai trouve la sortie!");
						done_win = true;
					}
				}
			}
			
			if(asi.size()!=size){
				size=asi.size();			
			}
			else{
				count++;
			}
			if(count==2){
				System.out.println("Je suis coince a tout jamais dans ce labyrinthe la!");
				done_lose=true;
			}
		}
		return asi;
	}
}
