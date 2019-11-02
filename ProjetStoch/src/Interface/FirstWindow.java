package Interface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FirstWindow extends Stage {

    private Stage stage;//window

    private Label title = new Label("Calculateur de files d'attente");

    private Button MM1;
    private Button MMS;
    private Button MM1K;


    public void init(){

        stage = this;
        setWindow();
        stage.show();
    }

    private void setWindow(){

        stage.setX(200);
        stage.setY(200);
        stage.setHeight(320);
        stage.setWidth(450);
        stage.setTitle("Calculateur de files d'attente");

        setMM1Button();
        setMM1KButton();
        setMMSButton();

        title.setStyle("-fx-font-weight: bold");
        title.setStyle("-fx-font-size: 32");

        GridPane root = new GridPane();
        root.addRow(1,title);
        root.addRow(2,MM1);
        root.addRow(3,MMS);
        root.addRow(4,MM1K);

        // Set the horizontal spacing to 15px
        root.setHgap(15);
        // Set the vertical spacing to 25px
        root.setVgap(25);

        root.setStyle("-fx-padding: 15;" );

        Scene scene = new Scene(root);

        stage.setScene(scene);
    }

    private void setMM1Button(){

        MM1 = new Button("File d'attente à serveur unique sans limite de clients (MM1)");
        MM1.minWidth(10);
        MM1.setPrefWidth(450);

        MM1.setOnAction( new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                MM1Parameter p = new MM1Parameter();
                p.init();
                stage.close();
                }
            }
        );
    }

    private void setMMSButton(){
        MMS = new Button("File d'attente à plusieurs serveurs (MMS)");
        MMS.minWidth(10);
        MMS.setPrefWidth(450);

        MMS.setOnAction( new EventHandler<ActionEvent>() {
                             @Override public void handle(ActionEvent e) {
                                 MMSParameter p = new MMSParameter();
                                 p.init();
                                 stage.close();
                             }
                         }
        );
    }

    private void setMM1KButton(){

        MM1K = new Button("File d'attente à serveur unique avec limite de clients finie (MM1K)");
        MM1K.minWidth(10);
        MM1K.setPrefWidth(450);

        MM1K.setOnAction( new EventHandler<ActionEvent>() {
                             @Override public void handle(ActionEvent e) {
                                 MM1KParameter p = new MM1KParameter();
                                 p.init();
                                 stage.close();
                             }
                         }
        );

    }

}
