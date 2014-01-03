package Dijkstra;

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
		r = gi.getDeparture();
		pivot = r;
		pi.setPoids(r, 0);
		asi.add(r);
		for (VertexInterface x : gi.getGraph()) {
			pi.setPoids(x, (int) Integer.MAX_VALUE / 2);
			 //je divise par 2 parce que Integer.MAX_VALUE+1=Integer.MIN_VALUE
		}

		for (VertexInterface x : gi.getGraph()) {
			if (!asi.isInAset(x) && gi.isPrevious(pivot, x) && !x.isArrival()) {
				if (pi.getPoids(pivot) + gi.getPoids(pivot, x) < pi.getPoids(x))
					pi.setPoids(x, pi.getPoids(pivot) + gi.getPoids(pivot, x));
				pivot = asi.getMin();
				asi.add(pivot);
			}
		}

		return asi;
	}
}
