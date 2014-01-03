package maze;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;

import fr.enst.inf103.ui.MazeView;
import fr.enst.inf103.ui.MazeViewSource;
import Dijkstra.ASet;
import Dijkstra.Dijkstra;
import Dijkstra.Pi;
import Dijkstra.VertexInterface;
import Dijkstra.GraphInterface;

public class Maze implements GraphInterface, MazeViewSource {

	// ************** implementation de MazeViewSource *************

	public boolean handleClick(MouseEvent arg0, MazeView arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean handleKey(KeyEvent arg0, MazeView arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	public String getSymbolForBox(int row, int column) {
		MBox box = (MBox) this.getVI(row, column);
		char c = box.getType();
		String str = "" + c;
		return str;
	}

	public void setSymbolForBox(int row, int column, String str) {
		MBox box = (MBox) this.getVI(row, column);
		if (str == "A")
			this.setVI(row, column, new ABox(row, column));
		else if (str == "E")
			this.setVI(row, column, new EBox(row, column));
		else if (str == "W")
			this.setVI(row, column, new WBox(row, column));
		else if (str == "D")
			this.setVI(row, column, new DBox(row, column));
	}

	public boolean drawMaze(Graphics g, MazeView mazeView) {
		return false;
	}

	public int getWidth() {
		this.setBorne();
		return this.maxX + 1;
	}

	public int getHeight() {
		this.setBorne();
		return this.maxY + 1;
	}

	// ************* constructreurs ***************

	private ArrayList<VertexInterface> alVi;

	public Maze(ArrayList<VertexInterface> alVi) {
		this.alVi = alVi;
		this.setPrevious();
	}

	public Maze() {
		this.load();
		this.setPrevious();
	}
	
	public Maze(String fileName) {
		this.load(fileName);
		this.setPrevious();
	}

	// ************* constructeur a partir d'un fichier texte **********

	public void load(String fileName) {
		alVi = new ArrayList<VertexInterface>();
		Reader r;
		try {
			// mise en place du reader
			r = new FileReader(fileName);
			BufferedReader br = new BufferedReader(r);
			try {
				// on initialise
				char c = (char) br.read();
				int y = 0;

				// on construit le labyrinthe
				while (c != 'f') {
					int x = 0;
					while (c != '/' && c != 'f') {
						System.out.println(c + " x= " + x + " y= " + y + '.');
						alVi.add(this.createBox(x, y, c));
						c = (char) br.read();
						x++;
					}
					if (c != 'f')
						c = (char) br.read();
					y++;
				}

				// on ferme
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// on contruit les relations de parente
		this.setPrevious();
	}
	
	public void load(){
		this.load("maze.txt");
	}

	// - - - - - - - - - auxiliaire - - - - - - - - -

	private MBox createBox(int posX, int posY, char c) {
		MBox retour;
		if (c == 'W')
			retour = new WBox(posX, posY);
		else if (c == 'E')
			retour = new EBox(posX, posY);
		else if (c == 'A')
			retour = new ABox(posX, posY);
		else if (c == 'D')
			retour = new DBox(posX, posY);
		else {
			retour = null;
			System.out.println("erreur de caractere" + (int) c);
		}
		return retour;
	}

	// ******* sauvegarder dans un fichier texte *********

	public void save(String fileName) {
		try {
			PrintWriter p = new PrintWriter(fileName);
			BufferedWriter bw = new BufferedWriter(p);
			// IL FAUT RESET LE FICHIER TXT

			// A FAIRE !!!!!!!!!!!!!!!!!!!!!!!!!!

			try {

				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void save(){
		this.save("maze.txt");
	}

	// ************ gestion de parente ***************

	private int maxX, maxY;

	private void setBorne() {
		maxX = 0;
		maxY = 0;

		for (VertexInterface vi : alVi) {
			if (vi.getPosX() > maxX)
				maxX = vi.getPosX();
			if (vi.getPosY() > maxY)
				maxY = vi.getPosY();
		}
	}

	private void setPrevious() {
		this.setBorne();

		for (int posX = 1; posX < maxX; posX++) {
			for (int posY = 1; posY < maxY; posY++) {
				VertexInterface box = this.getVI(posX, posY);

				box.addFils(this.getVI(posX - 1, posY - 1));
				box.addFils(this.getVI(posX + 1, posY - 1));
				box.addFils(this.getVI(posX - 1, posY + 1));
				box.addFils(this.getVI(posX + 1, posY + 1));
			}
		}
	}

	public boolean isPrevious(VertexInterface pere, VertexInterface fils) {
		boolean retour;
		if (pere.isPrevious(fils))
			retour = true;
		else if (fils.isPrevious(pere))
			retour = true;
		else
			retour = false;
		return retour;
	}

	// ******** methodes simples de GraphInterface ********

	public VertexInterface getDeparture() {
		MBox retour = null;
		for (VertexInterface vi : alVi) {
			MBox box = (MBox) vi;
			if (box.getType() == 'D')
				retour = box;
		}

		return retour;
	}

	public ArrayList<VertexInterface> getGraph() {
		return alVi;
	}

	public int getPoids(VertexInterface x, VertexInterface y) {
		int retour = Integer.MAX_VALUE / 2;
		if (this.isPrevious(x, y))
			retour = 1;
		return retour;
	}

	// ******** getter *********

	public ASet getShortestPath() {
		Pi pi = new Pi();
		Dijkstra d = new Dijkstra(this, pi, new ASet(pi));
		ASet retour = (ASet) d.shortestPath();
		return retour;
	}

	private VertexInterface getVI(int posX, int posY) {
		VertexInterface retour = null;

		EBox box = new EBox(posX, posY);
		for (VertexInterface vi : alVi) {
			if (vi.equal(box))
				retour = vi;
		}
		return retour;
	}

	private void setVI(int posX, int posY, VertexInterface x) {
		EBox box = new EBox(posX, posY);
		for (VertexInterface vi : alVi) {
			if (vi.equal(box))
				vi = x;
		}
	}
}
