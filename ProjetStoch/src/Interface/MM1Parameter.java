package Interface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.MM1;

public class MM1Parameter extends Stage {

    private Stage stage;//window

    private TextField lambdaField;
    private TextField muField;
    private TextField uTimeField;

    private Label lambdaLabel;
    private Label muLabel;
    private Label uTimeLabel;

    private Label resultatLabel;

    private Label nbClientSysLabel;
    private Label nbClientFileLabel;
    private Label tmpsAttSysLabel;
    private Label tmpsAttFileLabel;

    private Label nbClientSys;
    private Label nbClientFile;
    private Label tmpsAttSys;
    private Label tmpsAttFile;

    private MM1 formules;

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
        stage.setWidth(800);
        stage.setTitle("Calculateur file d'attente");

        setLambda();
        setMu();
        setValidateButton();
        setFixedLabel();
        GridPane root = new GridPane();

        root.addRow(1, lambdaLabel,lambdaField);
        root.addRow(2, muLabel,muField);
        root.addRow(3, new Label(""), validateButton);
        root.addRow(4, resultatLabel);
        root.addRow(5, nbClientSysLabel,new Label(""), nbClientFileLabel);
        root.addRow(6, tmpsAttSysLabel,new Label(""), tmpsAttFileLabel);

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

    private void setFixedLabel(){
        resultatLabel = new Label("Résultat des calculs");
        tmpsAttSysLabel = new Label("Attente moyenne dans le système");
        tmpsAttFileLabel = new Label("Attente moyenne dans la file");
        nbClientFileLabel = new Label("Nombre moyen de client dans la file");
        nbClientSysLabel = new Label("Nombre moyen de client dans le système");

    }

    private void setResultatLabel(float attenteFile , float attenteSys , float clientFile, float clientSys) {
        tmpsAttSys = new Label("Attente moyenne dans le système");
        tmpsAttFile = new Label("Attente moyenne dans la file");
        nbClientFile = new Label("Nombre moyen de client dans la file");
        nbClientSys = new Label("Nombre moyen de client dans le système");
    }

    private void setValidateButton(){

        validateButton = new Button("Valider");
        validateButton.setMinWidth(10);
        validateButton.setPrefWidth(100);
        validateButton.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

            }
        });
    }
}