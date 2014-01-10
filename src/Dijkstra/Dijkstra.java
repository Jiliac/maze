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

		int n = gi.getGraph().size();
		for (int j = 1; j < n; j++) {
			
		}

		return null;
	}
}
