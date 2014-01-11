package maze;

import Dijkstra.ASet;
import Dijkstra.VertexInterface;
import fr.enst.inf103.ui.MazeViewController;
import fr.enst.inf103.ui.MazeViewSource;
import java.util.ArrayList;

public class MazeController implements MazeViewController {

	// ********* implementation de MazeViewController ********
	// ASet shortestPath;
	public void calculateShortestPath() {
		int compteur = 0;
		ArrayList<VertexInterface> chemin = maze.getShortestPath();
		if (chemin != null) {
			for (VertexInterface vi : chemin)
				if (vi == null) {
					System.out.println("Null " + compteur + " fois.");
					compteur++;
				}

				else {
					vi.setInShortestPath(true);
				}
		}
		// ca marche si on utilise ca qu'une seule fois mais ca ne marcherait
		// pas si on doit changer les cases...

	}

	public MazeViewSource openMaze(String fileName) {
		try {
			maze = new Maze(fileName);
		} catch (MazeException e) {
			e.printStackTrace();
		}
		return maze;
	}

	public void saveMazeAs(String fileName) {
		maze.save(fileName);
	}

	public MazeViewSource getMazeViewSource() {
		return this.maze;
	}

	public MazeViewSource newMaze() {
		try {
			maze = new Maze();
		} catch (MazeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maze;
		// ATTENTION la j'ouvre a partir du fichier texte deja creer
		// on pourrait personnaliser la hauteur largeur etc...
	}

	// ******** constructeur ***********

	Maze maze;

	// ATTENTION la j'ouvre a partir du fichier texte deja creer
	// on pourrait personnaliser la hauteur largeur etc...
	public MazeController() {
		try {
			maze = new Maze();
		} catch (MazeException e) {
			e.printStackTrace();
		}
	}

	public MazeController(String fileName) {
		try {
			maze = new Maze(fileName);
		} catch (MazeException e) {
			e.printStackTrace();
		}
	}

}
