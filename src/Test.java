import maze.MazeController;
import fr.enst.inf103.ui.MazeViewController;
import fr.enst.inf103.ui.MazeWindow;


public class Test {

	public static void main(String[] args) {
	System.out.println("test118");
	MazeViewController mazeController = new MazeController("maze.txt");
	MazeWindow mazeWindow = new MazeWindow("My awesome labyrinth", mazeController) ;
	}

}
