package maze;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.FileOutputStream;
import java.nio.BufferOverflowException;
import java.nio.CharBuffer;
import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;
import fr.enst.inf103.ui.MazeView;
import fr.enst.inf103.ui.MazeViewSource;
import Dijkstra.Dijkstra;
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
		if (c == 'E' && box.isInShortestPath())
			str = "*";

		return str;
	}

	public void setSymbolForBox(int row, int column, String str) {
		char c = str.charAt(0);

		switch (c) {
		case 'A':
			this.setVI(row, column, new ABox(row, column));
			break;

		case 'E':
			this.setVI(row, column, new EBox(row, column));
			break;

		case 'W':
			this.setVI(row, column, new WBox(row, column));
			break;

		case 'D':
			this.setVI(row, column, new DBox(row, column));
			break;
		}

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

	public Maze() {
		try {
			this.load("maze.txt");
		} catch (MazeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.setPrevious();
	}

	public Maze(String fileName) {
		try {
			this.load(fileName);
		} catch (MazeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.setPrevious();
	}

	// ************* constructeur a partir d'un fichier texte **********

	public void load(String fileName) throws MazeException {
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
		// this.setPrevious();
	}

	// - - - - - - - - - auxiliaire - - - - - - - - -

	private MBox createBox(int posX, int posY, char c) throws MazeException {

		switch (c) {
		case 'W':
			return new WBox(posX, posY);

		case 'A': {
			if (grid.indexOf(new ABox(0, 0)) != -1)
				throw new MazeException("Deja une case d'arrivee!");
			else
				return new ABox(posX, posY);
		}

		case 'D': {
			if (grid.indexOf(new DBox(0, 0)) != -1)
				throw new MazeException("Deja une case de depart!");
			else
				return new DBox(posX, posY);
		}

		case 'E':
			return new EBox(posX, posY);

		default:
			throw new MazeException(" Caractere non recevable: " + c);

		}

	}

	// ******* sauvegarder dans un fichier texte *********

	// Necessite d'avoir comme nom de fichier "exemple.txt" et pas "exemple".

	public void save(String fileName) {
		try {
			FileOutputStream fis = new FileOutputStream("./" + fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Le fichier existe deja ou ne peut etre ecrit.");
		}

		try {
			CharBuffer cb = CharBuffer.allocate(1);
			this.setBorne();
			for (int i = 0; i < maxX; i++) {
				for (int j = 0; j < maxY; j++) {
					cb.put(((MBox) getVI(i, j)).getType());
				}
				cb.put('/');
			}
			cb.put(';');

		} catch (BufferOverflowException e) {
			e.printStackTrace();
		} catch (ReadOnlyBufferException e) {
			e.printStackTrace();
		}

	}

	public void save() {
		this.save("maze.txt");
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

		for (int posX = 0; posX <= maxX; posX++) {
			for (int posY = 0; posY <= maxY; posY++) {
				this.addFils(posX, posY);
			}
		}
	}

	private void addFils(int posX, int posY) {
		VertexInterface box = this.getVI(posX, posY);

		box.addFils(this.getVI(posX, (posY - 1 < 0) ? 0 : (posY - 1)));
		box.addFils(this.getVI(posX, (posY + 1 > maxY) ? maxY : posY + 1));
		box.addFils(this.getVI((posX - 1 < 0) ? 0 : (posX - 1), posY));
		box.addFils(this.getVI((posX + 1 > maxX) ? maxX : (posX + 1), posY));
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

	public ArrayList<VertexInterface> getGraph() {
		return grid;
	}

	// ******** getters et setters *********

	public ArrayList<VertexInterface> getShortestPath() {
		Dijkstra d = new Dijkstra(this);
		ArrayList<VertexInterface> chemin = d.shortestPath();
		return chemin;
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
		int indexOfBox = -1;

		for (VertexInterface vi : grid) {
			if (vi.equal(box))
				indexOfBox = grid.indexOf(vi);
		}
		if (indexOfBox == -1)
			System.out.println("Ces coordonnées n'existent pas");
		else {
			grid.remove(indexOfBox);
			grid.add(x);
			this.addFils(x.getPosX(), x.getPosY());
		}
	}
}
