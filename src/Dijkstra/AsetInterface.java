package Dijkstra;

import java.util.ArrayList;

public interface AsetInterface {
	public boolean isInAset(VertexInterface vi);
	public void add(VertexInterface vi);
	public VertexInterface getMin(GraphInterface gi);
	public int size();
	public ArrayList<VertexInterface> removeAlVi(GraphInterface gi);
}
