package Pacman;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class StageLabel extends Label{
	private static final String font="";
	private String stage ;
	
	
	public StageLabel() {
		
		
		this.getStylesheets().addAll("css/style.css");
		this.getStyleClass().add("labelText");
		
		setTranslateX(1360);
		setTranslateY(13);
	
	}
	void stageUpdate() {
		stage = "Stage 1";
		setText(stage);
	
		setFont(new Font("Arial", 30));
	//	FontWeight.BOLD();
	}
	
	void stageUpdate2() {
		
		stage="Stage 2";
		setText(stage);
	}
	
   void stageUpdate3() {
		
		stage="Stage 3";
		setText(stage);
	}
	
	
}
