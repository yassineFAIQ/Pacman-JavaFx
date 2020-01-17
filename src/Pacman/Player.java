package Pacman;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;

public class Player extends graphicObject {
	
	public Player(Zone zone) {
		
		
		
		Arc pacman_shape = new Arc( 200, 120, // center point
                40, 40,   // width and height radiuses
                30,       // start angle in degrees
                300 ) ;   // length angle in degrees
		
pacman_shape.setType( ArcType.ROUND ) ;
pacman_shape.setFill( Color.FIREBRICK ) ; // Red/brown color.
//container.getChildren().add( pacman_shape ) ;


Timeline timeline = new Timeline() ;
timeline.setCycleCount( Animation.INDEFINITE ) ;
timeline.setAutoReverse( true ) ;

KeyValue start_angle_value = new KeyValue( pacman_shape.startAngleProperty(),
                               0 ) ;
KeyValue length_value = new KeyValue( pacman_shape.lengthProperty(), 360 ) ;
KeyFrame closing_of_mouth = new KeyFrame( Duration.millis( 1000 ),
                              start_angle_value,
                              length_value ) ;
timeline.getKeyFrames().add( closing_of_mouth ) ;
timeline.play() ;
		
		

		corps=pacman_shape;
	//corps.setX(0);
	//	((ImageView)corps).setY(0);
		
		//doit appartenir zone
		double x=zone.getX1()+(zone.getX2()-30-zone.getX1())*Math.random();
		double y=zone.getY1()+(zone.getY2()-50-zone.getY1())*Math.random();
		corps.setTranslateX(x);
		corps.setTranslateY(y);
		
		
		

		
	}
	public void rotateUp() {
		corps.setRotate(270);
	}
	public void rotateDown() {
		corps.setRotate(90);
	}
	public void rotateRight() {
		corps.setRotate(0);
	}
	public void rotateLeft() {
		corps.setRotate(180);
	}
	public double getRotate() {
		return corps.getRotate();
	}
	
	
}
