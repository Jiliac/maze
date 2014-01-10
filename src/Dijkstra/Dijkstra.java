package Dijkstra;

import java.util.ArrayList;



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
			for (VertexInterface y : this.removeNonPreviousVertex(asi,pivot)){
				// le poids de l'arc p(pivot,y) est forcement 1 puisaue le test de parente a deja ete fait
				int futurPoidsY = pi.getPoids(y) + 1;
				if(futurPoidsY < pi.getPoids(y)){
					pi.setPoids(y, futurPoidsY);
					//instruction du pere de y... machin...
				}
				pivot = asi.getMin(gi);
				asi.add(pivot);
			}
		}

		return asi;
	}
	
	private ArrayList<VertexInterface> removeNonPreviousVertex(AsetInterface asi, VertexInterface pivot){
		ArrayList<VertexInterface> retour = new ArrayList<VertexInterface>() , nonASetCollection = asi.removeAlVi(this.gi);
		for(VertexInterface vi : nonASetCollection){
			if(pivot.isPrevious(vi))
				retour.add(vi);
		}
		return retour;
	}
}
