
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}
    double x4 = 200.0, y4 = 400.0;
    double x = 200.0, y = 300.0;
//   int level = 0;
//   double x1 = 160.0, y1 = (280.0 - 280*Math.sqrt(3.0)/2);
    
    Pane pane = new Pane();
     
//    public void init() {
//        String levelStr = JOptionPane
//                .showInputDialog("Enter the depth of recursion: ");
//        level = Integer.parseInt(levelStr);
//    }
  
    public void paint() {
        vonKoch(1, x, y, x4, y4);    
    }
  
    public void vonKoch(int n, double x0, double y0, double lengde, double vinkel) {
        double deltaX, deltaY, x1, y1, x2, y2, x3, y3;
        Line line = new Line();
        if (n == 1) {
        	line.setStartX(x0);
        	line.setStartY(y0);
        	line.setEndX(lengde);
        	line.setEndY(vinkel);
        	pane.getChildren().add(line);
        }
        else {
            deltaX = lengde - x0;
            deltaY = vinkel - y0;
            x1 = x0 + (deltaX / 3);
            y1 = y0 + deltaY / 3;
            x2 = ((x0 + lengde) / 2 + (Math.sqrt(3.0) / 6) * (y0 - vinkel));
            y2 = ((y0 + vinkel) / 2 + (Math.sqrt(3.0) / 6) * (lengde - x0));
            x3 = x0 + deltaX * 2 / 3;
            y3 = y0 + deltaY * 2 / 3;
            vonKoch(n-1, x0, y0, x1, y1);
            vonKoch(n-1, x1, y1, x2, y2);
            vonKoch(n-1, x2, y2, x3, y3);
            vonKoch(n-1, x3, y3, lengde, vinkel);
        }
    }
	@Override
	public void start(Stage primaryStage) throws Exception {
		paint();
		Scene scene = new Scene(pane, 400, 600);
		primaryStage.setTitle("VonKoch");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
