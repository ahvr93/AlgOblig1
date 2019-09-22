
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}
    int n = 0;
    double vinkel;
    Pane pane = new Pane();
     
    public void paint() {
        printTree(n, 100, 0, 400, 550);    
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
        	printTree(n-1, lengde-10, vinkel+this.vinkel, x+xmove, y+ymove); // Vinkel på greiner
        	printTree(n-1, lengde-10, vinkel-this.vinkel, x+xmove, y+ymove); // Vinkel på greiner
        	line.setStartX(x);
        	line.setStartY(y);
        	line.setEndX(x+xmove);
        	line.setEndY(y+ymove);
        	pane.getChildren().add(line);
        }
    }
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane pane = new BorderPane();
		VBox meny = new VBox();
		meny.setPadding(new Insets(20,0,0,100));
		pane.setLeft(meny);
		
		Scene scene = new Scene(pane, 300, 200);
		primaryStage.setTitle("Tegn tre");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		
		// Knapp
		Button ok = new Button("Vis tre");
		ok.setMinWidth(100);
		ok.setMaxWidth(150);
		
		
		// Tekstfelt
		TextField nivåTxt = new TextField("");
		TextField vinkelTxt = new TextField("");
		Label nivåLb = new Label("Nivå: ");
		Label vinkelLb = new Label("Vinkel: ");
		TextField status = new TextField("");
		Label blank = new Label("");
		Label blank2 = new Label("");
		status.setEditable(false);
		meny.getChildren().addAll(nivåLb, nivåTxt, vinkelLb, vinkelTxt, blank, ok, blank2, status);
		
		ok.setOnMouseClicked(e -> {
			if(nivåTxt.getText().equals("") || vinkelTxt.getText().equals("")) {
				status.setText("Fyll ut alle felter");
			}else {
				n = Integer.parseInt(nivåTxt.getText());
				vinkel = Double.parseDouble(vinkelTxt.getText());
				tegneBrett();
			}
		});	
	}
	
	private void tegneBrett() {
		Stage subStage = new Stage();
		subStage.setTitle("Tegnebrett");
		Scene scene = new Scene(pane, 700, 600);
		subStage.setScene(scene);
		subStage.show();
		paint();
		
	}
}
