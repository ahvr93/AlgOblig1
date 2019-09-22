
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}
    
    Pane pane = new Pane();
     
    public void paint() {
<<<<<<< refs/remotes/Oblig/master
        printTree(5,100, 0, 600, 550);    
=======
        printTree(6,100, 0, 400, 550);    
>>>>>>> tre
    }
  
    public void printTree(int n, double lengde, double vinkel, double x, double y) {
        double xmove = (double)(Math.cos(Math.toRadians(vinkel+90))*lengde);
        double ymove = (double)(Math.sin(Math.toRadians(vinkel-90))*lengde);
        Line line = new Line();
        if (n==1) {
        	line.setStartX(x);
        	line.setStartY(y);
        	line.setEndX(x+xmove);
        	line.setEndY(y+ymove);
        	pane.getChildren().add(line);
        }else if(lengde>=30) {
        	printTree(n-1, lengde-10, vinkel+30, x+xmove, y+ymove);
        	printTree(n-1, lengde-10, vinkel-30, x+xmove, y+ymove);
        	line.setStartX(x);
        	line.setStartY(y);
        	line.setEndX(x+xmove);
        	line.setEndY(y+ymove);
        	pane.getChildren().add(line);
        }
    }
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(pane, 700, 600);
		primaryStage.setTitle("printTree");
		primaryStage.setScene(scene);
		primaryStage.show();
		paint();
		
	}
}
