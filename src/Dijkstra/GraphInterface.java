package Dijkstra;

import java.util.ArrayList;

public interface GraphInterface {	
	
	public void setPrevious();
	
	public boolean isPrevious(VertexInterface pere, VertexInterface fils);

	public ArrayList<VertexInterface> getGraph();
}
