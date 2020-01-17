package Pacman;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
public class Score extends Label {
	private static final String font="";
	private int score ;
	
	
	public Score() {
		
		
		this.getStylesheets().addAll("css/style.css");
		this.getStyleClass().add("labelText");
		score = 0;
		setText(""+score);
	
		setFont(new Font("Arial", 30));
	//	FontWeight.BOLD();
		setTranslateX(50);
		setTranslateY(15);
	
	}
	
	void scoreUpdate() {
		score++;
		setText("" + score);
	}
	void scoreInit() {
		score = 0 ;
	}
	void scoreFianl(int score) {
		this.getStylesheets().addAll("css/style.css");
		this.getStyleClass().add("labelScore");
		setText("Your score is :"+ score);
		setTranslateX(850);
		setTranslateY(250);
	}
	
	void scoreF(int i) {
		this.getStylesheets().addAll("css/style.css");
		this.getStyleClass().add("labelScore");
		setText("Congratulations ! \n"+ "Your score is :"+ i);
		//setText(");
		setTranslateX(850);
		setTranslateY(250);
	}

	public int getScore() {
		
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
