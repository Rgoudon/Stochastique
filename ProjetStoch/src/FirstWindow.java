import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FirstWindow extends Stage {

    private Stage stage;//window

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
        stage.setHeight(500);
        stage.setWidth(500);
        stage.setTitle("Calculateur file d'attente");

        setMM1Button();
        setMM1KButton();
        setMMSButton();

        GridPane root = new GridPane();
        root.addRow(1,MM1);
        root.addRow(2,MMS);
        root.addRow(3,MM1K);

        // Set the horizontal spacing to 15px
        root.setHgap(15);
        // Set the vertical spacing to 25px
        root.setVgap(25);

        root.setStyle("-fx-padding: 15;" );

        Scene scene = new Scene(root);

        stage.setScene(scene);
    }

    private void setMM1Button(){

        MM1 = new Button("File avec 1 serveur (MM1)");
        MM1.minWidth(10);
        MM1.setPrefWidth(450);
    }

    private void setMMSButton(){
        MMS = new Button("File avec plusieurs serveurs (MMS)");
        MMS.minWidth(10);
        MMS.setPrefWidth(450);
    }

    private void setMM1KButton(){

        MM1K = new Button("File  avec 1 serveur, clients limité dans le système (MM1K)");
        MM1K.minWidth(10);
        MM1K.setPrefWidth(450);

    }

}
