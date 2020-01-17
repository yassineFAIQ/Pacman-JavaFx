package Pacman;
import javafx.scene.control.Label;
public class GameOver extends Label {
	private static final String font="";
	private int score ;
	
	
	public GameOver() {
		
		score = 0;
		setText("Game Over !!");
	//	setFont(80);
		setTranslateX(100);
		setTranslateY(100);
	}
	
	void scoreUpdate() {
		score++;
		setText("Score : " + score);
	}
}
