package maze;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.BufferOverflowException;
import java.nio.CharBuffer;
import java.nio.ReadOnlyBufferException;
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
		return false;
	}

	public boolean handleKey(KeyEvent arg0, MazeView arg1) {
		return false;
	}

	public String getSymbolForBox(int row, int column) {
		MBox box = (MBox) this.getVI(row, column);
		char c = box.getType();
		String str = "" + c;

		// pour le shortestPath
		if (str == "E" && box.isInShortestPath())
			str = "*";

		return str;
	}

	public void setSymbolForBox(int row, int column, String str) {
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

	private ArrayList<VertexInterface> grid;

	public Maze(ArrayList<VertexInterface> grid) {
		this.grid = grid;
		this.setPrevious();
	}

	public Maze() throws MazeException {
		this.load2("maze.txt");
		this.setPrevious();
	}

	public Maze(String fileName) throws MazeException {
		this.load2(fileName);
		this.setPrevious();
	}

	// ************* constructeur a partir d'un fichier texte **********

	/*public void load(String fileName) throws MazeException {
		grid = new ArrayList<VertexInterface>();
		Reader r;
		try {
			// mise en place du reader
			r = new FileReader("./" + fileName);
			BufferedReader br = new BufferedReader(r);
			try {
				// on initialise
				char c = (char) br.read();
				int y = 0;

				// on construit le labyrinthe
				while (c != ';') {
					int x = 0;
					while (c != '/' && c != ';') {
						System.out.println(c + " x= " + x + " y= " + y + '.');
						grid.add(this.createBox(x, y, c));
						c = (char) br.read();
						x++;
					}
					if (c != ';')
						c = (char) br.read();
					y++;
				}

				// on ferme
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// on contruit les relations de parente
		this.setPrevious();
	}*/

	public void load() throws MazeException {
		this.load2("maze.txt");
	}
		
	public void load2(String fileName) throws MazeException {
		try (FileInputStream fis = new FileInputStream("./" + fileName);) {
			CharBuffer cb = CharBuffer.allocate(1);
			this.setBorne();
			int i=0;
			int j=0;
			char c='c';
			while(c!=';'){
				c='c';
				while(c!='/' && c!=';'){
					grid.add(this.createBox(i, j, c=cb.get()));
					i++;
				}
				j++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out
					.println("Le fichier est introuvable.");
		} catch (BufferOverflowException e) {
			e.printStackTrace();
		} catch (ReadOnlyBufferException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// - - - - - - - - - auxiliaire - - - - - - - - -

	private MBox createBox(int posX, int posY, char c) throws MazeException {
		MBox retour = new EBox(posX, posY);
		if (c == 'W')
			retour = new WBox(posX, posY);
		else if (c == 'E') { //variable deja initialise
		} else if (c == 'A') {
			if (grid.indexOf(new ABox(0, 0)) != -1)
				throw new MazeException("Deja une case d'arrivee!");
			else
				retour = new ABox(posX, posY);
		} else if (c == 'D') {
			if (grid.indexOf(new DBox(0, 0)) != -1)
				throw new MazeException("Deja une case de depart!");
			else
				retour = new DBox(posX, posY);
		} else
			throw new MazeException(" Caractere non recevable: " + c);

		return retour;
	}

	// ******* sauvegarder dans un fichier texte *********

	// Necessite d'avoir comme nom de fichier "exemple.txt" et pas "exemple".

	public void save(String fileName) {
		try (FileOutputStream fis = new FileOutputStream("./" + fileName);) {
			CharBuffer cb = CharBuffer.allocate(1);
			this.setBorne();
			for (int i = 0; i < maxX; i++) {
				for (int j = 0; j < maxY; j++) {
					cb.put(((MBox) getVI(i, j)).getType());
				}
				cb.put('/');
			}
			cb.put(';');

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out
					.println("Le fichier existe deja ou ne peut etre ecrit.");
		} catch (BufferOverflowException e) {
			e.printStackTrace();
		} catch (ReadOnlyBufferException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void save() {
		this.save("maze_backup.txt");
	}

	// ************ gestion de parente ***************

	private int maxX, maxY;

	private void setBorne() {
		maxX = 0;
		maxY = 0;

		for (VertexInterface vi : grid) {
			if (vi.getPosX() > maxX)
				maxX = vi.getPosX();
			if (vi.getPosY() > maxY)
				maxY = vi.getPosY();
		}
	}

	public void setPrevious() {
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
		if (fils instanceof EBox && pere instanceof EBox) {
			if (pere.isPrevious(fils))
				return true;
			else if (fils.isPrevious(pere))
				return true;
			else
				return false;
		} else
			return false;
	}

	// ******** methodes simples de GraphInterface ********

	public VertexInterface getDeparture() {
		MBox retour = null;
		for (VertexInterface vi : grid) {
			MBox box = (MBox) vi;
			if (box.getType() == 'D')
				retour = box;
		}

		return retour;
	}

	public ArrayList<VertexInterface> getGraph() {
		return grid;
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
		VertexInterface retour = new EBox(posX, posY);

		EBox box = new EBox(posX, posY);
		for (VertexInterface vi : grid) {
			if (vi.equal(box))
				retour = vi;
		}
		return retour;
	}

	private void setVI(int posX, int posY, VertexInterface x) {
		EBox box = new EBox(posX, posY);
		for (VertexInterface vi : grid) {
			if (vi.equal(box))
				vi = x;
		}
	}
}
