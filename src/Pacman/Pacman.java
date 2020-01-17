package Pacman;

/*
PacmanFX.java Copyright (c) Kari Laitinen

http://www.naturalprogramming.com/

2014-12-16 File created.
2014-12-29 Last modification.

This program shows a Pacman on the screen. The Pacman closes and opens
its mouth constantly.

This program demonstrates
   - the use of the Arc class
   - the use of classes Timeline, KeyFrame, and KeyValue to control animation

*/


import javafx.animation.* ;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;  // Arc, Circle, etc.
import javafx.stage.Stage;


public class Pacman extends Application
{
public void start( Stage stage )
{
   Group group_for_pacman = new Group() ;

   stage.setTitle( "PacmanFX.java" ) ;

   Scene scene = new Scene( group_for_pacman, 600, 500 ) ;

   scene.setFill( Color.LIGHTYELLOW ) ;

   // The center point of the Pacman is ( 300, 250 ) and ( width x height )
   // of its bounding rectangle is ( 128 x 96 ).
   // Initially the mouth of the Pacman is fully open.

   Arc pacman_shape = new Arc( 300, 250, // center point
                               64, 48,   // width and height radiuses
                               45,       // start angle in degrees
                               270 ) ;   // length angle in degrees

   pacman_shape.setType( ArcType.ROUND ) ;
   pacman_shape.setFill( Color.FIREBRICK ) ; // Red/brown color.

   group_for_pacman.getChildren().add( pacman_shape ) ;
        
   stage.setScene( scene ) ;
   stage.show() ;

   Timeline timeline = new Timeline() ;

   timeline.setCycleCount( Animation.INDEFINITE ) ;
   timeline.setAutoReverse( true ) ;

   // The following KeyValue object specifies that the start angle of the
   // pacman shape will diminish to zero from its initial value 45.

   KeyValue start_angle_value = new KeyValue( pacman_shape.startAngleProperty(),
                                              0 ) ;

   // The following object rules that the length angle property will grow
   // to 360 from its initial value 270.

   KeyValue length_value = new KeyValue( pacman_shape.lengthProperty(), 360 ) ;


   // The following KeyFrame object specifies that during 1000 milliseconds
   // two values change simultaneusly as specified by the KeyValue objects.
   // As the Timeline is set to AutoReverse mode, the same KeyFrame also
   // opens the mouth after it has been closed.

   KeyFrame closing_of_mouth = new KeyFrame( Duration.millis( 1000 ),
                                             start_angle_value,
                                             length_value ) ;

   timeline.getKeyFrames().add( closing_of_mouth ) ;

   timeline.play() ;
}

public static void main( String[] command_line_parameters )
{
   launch( command_line_parameters ) ;
}
}


/*  NOTES:



The following were useful pages when I was developing this program:

https://docs.oracle.com/javase/8/javafx/api/javafx/animation/Timeline.html

https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/Arc.html
*/