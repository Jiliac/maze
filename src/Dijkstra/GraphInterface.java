package Dijkstra;

import java.util.ArrayList;

public interface GraphInterface {	
	
	public void setPrevious();
	
	public boolean isPrevious(VertexInterface pere, VertexInterface fils);

	public VertexInterface getDeparture();

	public ArrayList<VertexInterface> getGraph();

	public int getPoids(VertexInterface x, VertexInterface y);
}
