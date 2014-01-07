import fr.enst.inf103.ui.MazeViewController;
import fr.enst.inf103.ui.MazeWindow;
import maze.MazeController;

public class Test {
	public static void main(String[] arg0) {
		MazeViewController mazeController = new MazeController("maze.txt");
		MazeWindow mazeWindow = new MazeWindow("My awesome labyrinth", mazeController) ;
	}
}
