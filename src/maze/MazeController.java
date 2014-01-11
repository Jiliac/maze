package maze;

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
		for (VertexInterface vi : chemin)
			if (vi == null) {
				System.out.println("Null " + compteur + " fois.");
				compteur++;
			}

			else {
				vi.setInShortestPath(true);
			}
	}

	public MazeViewSource openMaze(String fileName) {
		maze = new Maze(fileName);
		return maze;
	}

	public void saveMazeAs(String fileName) {
		maze.save(fileName);
	}

	public MazeViewSource getMazeViewSource() {
		return this.maze;
	}

	public MazeViewSource newMaze() {
		maze = new Maze();

		return maze;
		// ATTENTION la j'ouvre a partir du fichier texte deja creer
		// on pourrait personnaliser la hauteur largeur etc...
	}

	// ******** constructeur ***********

	Maze maze;

	// ATTENTION la j'ouvre a partir du fichier texte deja creer
	// on pourrait personnaliser la hauteur largeur etc...
	public MazeController() {
		maze = new Maze();

	}

	public MazeController(String fileName) {
		maze = new Maze(fileName);
	}

}
