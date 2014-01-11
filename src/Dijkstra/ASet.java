package Dijkstra;

import java.util.ArrayList;

import maze.EBox;

public class ASet implements AsetInterface {
	private ArrayList<VertexInterface> alVi;
	private PiInterface pi;

	public ASet(PiInterface pi) {
		alVi = new ArrayList<VertexInterface>();
		this.pi = pi;
	}

	public void add(VertexInterface vi) {
		alVi.add(vi);
	}

	public ArrayList<VertexInterface> getSommet() {
		return this.alVi;
	}

	public int size() {
		return alVi.size();
	}

}
