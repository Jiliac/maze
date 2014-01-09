package Dijkstra;

public interface AsetInterface {
	public boolean isInAset(VertexInterface vi);
	public void add(VertexInterface vi);
	public VertexInterface getMin();
	public int size();
}
