package maze;
import java.lang.Exception;

public class MazeException extends Exception{
	
	public MazeException(String s){
		System.out.println("Erreur:"+s);
	}

}
