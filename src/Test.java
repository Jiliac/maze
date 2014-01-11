import maze.Maze;
import maze.MazeController;
import fr.enst.inf103.ui.MazeViewController;
import fr.enst.inf103.ui.MazeWindow;

public class Test {

	public static void main(String[] args) {

		// demarrage d'un labyrinthe
		MazeViewController mazeController = new MazeController("maze.txt");
		MazeWindow mazeWindow = new MazeWindow("My awesome maze",
				mazeController);
		
	}
}
