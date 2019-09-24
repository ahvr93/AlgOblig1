
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    int n;
    int vinkel;
    int grein;
    int random;
    int stammeH�yde;
    Pane pane = new Pane();
     
    public void paint() {
        printTree(n, stammeH�yde, 0, 600, 780);    
    }
  
    public void printTree(int n, double lengde, double vinkel, double x, double y) {
        double xmove = (double)(Math.cos(Math.toRadians(vinkel+90))*lengde);
        double ymove = (double)(Math.sin(Math.toRadians(vinkel-90))*lengde);
        Line line = new Line();
        if (n==1) {
        	line.setStartX(x);
        	line.setStartY(y);
        	line.setEndX(x+xmove+random());
        	line.setEndY(y+ymove+random());
        	pane.getChildren().add(line);
        		
        }else if(lengde>=2 && n!=1) {
        	printTree(n-1, lengde-grein, (vinkel+this.vinkel)+random(), x+xmove, y+ymove); // Vinkel p� greiner
            printTree(n-1, lengde-grein, (vinkel-this.vinkel)+random(), x+xmove, y+ymove); // Vinkel p� greiner
        	line.setStartX(x);
        	line.setStartY(y);
        	line.setEndX(x+xmove);
        	line.setEndY(y+ymove);
        	pane.getChildren().add(line);
        }
    }
	private double random() {
		double start = random;
		double end = -random;
		double random = new Random().nextDouble();
		double result = start + (random * (end - start));
		return result;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane pane = new BorderPane();
		VBox meny = new VBox();
		meny.setPadding(new Insets(20,0,0,10));
		pane.setLeft(meny);
		
		Scene scene = new Scene(pane, 200, 400);
		primaryStage.setTitle("Tegn tre");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		
		// Knapper
		Button ok = new Button("Vis tre");
		ok.setMinWidth(100);
		ok.setMaxWidth(150);
		Button kalkulerN = new Button("Kalkuler h�yde");
		kalkulerN.setMinWidth(100);
		kalkulerN.setMaxWidth(150);
		
		
		// Tekstfelt
		ChoiceBox<Integer> niv�Cb = new ChoiceBox<Integer>();
		niv�Cb.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
		niv�Cb.setValue(10);
		niv�Cb.setMinWidth(100);
		
		ChoiceBox<Integer> vinkelCb = new ChoiceBox<Integer>();
		vinkelCb.getItems().addAll(0,10,20,30,40,50,60,70,80,90,100);
		vinkelCb.setValue(30);
		vinkelCb.setMinWidth(100);
		
		ChoiceBox<Integer> stammeH�ydeCb = new ChoiceBox<Integer>();
		stammeH�ydeCb.getItems().addAll(30,40,50,60,70,80,90,100);
		stammeH�ydeCb.setValue(100);
		stammeH�ydeCb.setMinWidth(100);
		
		ChoiceBox<Integer> greinCb = new ChoiceBox<Integer>();
		greinCb.getItems().addAll(5, 10, 20, 30, 40, 50);
		greinCb.setValue(10);
		greinCb.setMinWidth(100);
		
		ChoiceBox<Integer> randomCb = new ChoiceBox<Integer>();
		randomCb.getItems().addAll(0, 10, 20, 30, 40);
		randomCb.setValue(0);
		randomCb.setMinWidth(100);
		
		Label niv�Lb = new Label("Niv�: ");
		Label vinkelLb = new Label("Vinkel: ");
		Label stammeLb = new Label("Lengde p� neste grein: " + "\n" + "Eg. 20 = 20px mindre enn forrige");
		Label randomLb = new Label("Tilfeldighet :" + "\n" + "Eg. 0 = lavest");
		Label stammeH�ydeLb = new Label("H�yde p� stamme: ");
		TextField status = new TextField("");
		Label kalk = new Label("Kalkuler h�yde f�r print?");
		Label print = new Label("Print tre");
		Label kalk2 = new Label("Kalkulasjon");
		status.setEditable(false);
		meny.getChildren().addAll(niv�Lb, niv�Cb, vinkelLb, vinkelCb,stammeH�ydeLb,
				stammeH�ydeCb, stammeLb, greinCb, randomLb, randomCb,kalk,
				kalkulerN, kalk2 ,status, print, ok);
		
		ok.setOnMouseClicked(e -> {
				n = niv�Cb.getValue();
				vinkel = vinkelCb.getValue();
				grein = greinCb.getValue();
				random = randomCb.getValue();
				stammeH�yde = stammeH�ydeCb.getValue();
				primaryStage.hide();
				tegneBrett();
		});	
		
		kalkulerN.setOnMouseClicked(e -> {
			int stamme = stammeH�ydeCb.getValue();
			int lengde = greinCb.getValue();
			int n = niv�Cb.getValue();
			if(stamme-(lengde*n)>=0) {
				status.setText("Kalkulert antall h�yde: " + n);
			}else {
				status.setText("Mindre enn planlagt");	
			}
	
		});	
	}
	private void tegneBrett() {
		Scene scene = new Scene(pane, 1200, 800);
		Stage subStage = new Stage();
		subStage.setTitle("Tegnebrett");
		subStage.setScene(scene);
		subStage.show();
		paint();
	
	}
}
