package Interface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MM1KParameter extends Stage {


    private Stage stage;//window

    private TextField lambdaField;
    private TextField muField;
    private TextField sField;

    private Label lambdaLabel;
    private Label muLabel;
    private Label sLabel;

    private Button validateButton;

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

        setLambda();
        setMu();
        setValidateButton();
        GridPane root = new GridPane();

        root.addRow(1,lambdaLabel,lambdaField);
        root.addRow(2,muLabel,muField);
        root.addRow(3, validateButton);
        // Set the horizontal spacing to 15px
        root.setHgap(15);
        // Set the vertical spacing to 25px
        root.setVgap(25);

        root.setStyle("-fx-padding: 15;" );

        Scene scene = new Scene(root);

        stage.setScene(scene);
    }

    private void setLambda(){

        lambdaLabel = new Label("lambda");

        lambdaField = new TextField("1");
        lambdaField.minWidth(10);
        lambdaField.setPrefWidth(100);
    }

    private void setMu(){
        muLabel = new Label("mu");

        muField = new TextField("1");
        muField.minWidth(10);
        muField.setPrefWidth(100);
    }

    private void setS(){

        sLabel = new Label("S");

        sField = new TextField("2");
        sField.minWidth(10);
        sField.setPrefWidth(100);

    }

    private void setValidateButton(){

        validateButton = new Button("Valider");
        validateButton.setMinWidth(10);
        validateButton.setPrefWidth(100);
        validateButton.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                MM1KResult p = new MM1KResult();
                p.init();
                stage.close();
            }
        });
    }

}
