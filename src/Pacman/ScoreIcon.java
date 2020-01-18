package Pacman;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
public class ScoreIcon extends Label {
	private static final String font="";

	
	public ScoreIcon() {
		Image image = null;
		try {
			image = new Image(new FileInputStream("Pacman-JavaFx-master/src/photosJeu/score.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		    setGraphic(new ImageView(image));
		
		this.getStylesheets().addAll("css/style.css");
		this.getStyleClass().add("labelText");
		
		setText("");

		setTranslateX(10);
		setTranslateY(10);
	
	}
	

}
