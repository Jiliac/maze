package Dijkstra;

public interface VertexInterface {
	public int getPosX();

	public int getPosY();

	public boolean equal(VertexInterface vi);

	public boolean isArrival();

	public void addFils(VertexInterface vi);

	public boolean isPrevious(VertexInterface fils);

	// ************* implementation necessaire au shortestPath ****************

	public boolean isInShortestPath();

	public void setInShortestPath(boolean isInShortestPath);
}
