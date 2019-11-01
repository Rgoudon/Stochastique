package Interface;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MM1;

public class MM1Parameter extends Stage {

    private Stage stage;//window

    private TitledPane paramPane;
    private TitledPane resultPane;

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

    private Label nbClientSysField = new Label();
    private Label nbClientFileField = new Label();
    private Label tmpsAttSysField = new Label();
    private Label tmpsAttFileField = new Label();

    private MM1 mm1 = new MM1();

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

        initLambda();
        initMu();
        setValidateButton();
        initFixedLabel();

        GridPane root = new GridPane();

        // TitledPane "Paramètres"
        paramPane = new TitledPane();
        paramPane.setText("Paramètres");
        // Content for TitledPane "Paramètres"
        // This pane has 2 columns like this:
        // -----------------
        // |  Label  | Field |
        // |  Label  | Field |
        // |  Button |
        // -----------------
        // This way, we make sure that everything is perfectly aligned
        // Column 1
        VBox labelsColumn = new VBox(10);
        labelsColumn.getChildren().add(lambdaLabel);
        labelsColumn.getChildren().add(muLabel);
        // Column 2
        VBox fieldsColumn = new VBox(10);
        fieldsColumn.getChildren().add(lambdaField);
        fieldsColumn.getChildren().add(muField);
        // Horizontal box that contains the columns
        HBox paramContent = new HBox(20);
        paramContent.getChildren().add(labelsColumn);
        paramContent.getChildren().add(fieldsColumn);
        paramContent.getChildren().add(validateButton);
        paramPane.setContent(paramContent);

        // TitledPane "Résultats"
        resultPane = new TitledPane();
        resultPane.setText("Résultats");
        // Content for TitledPane "Résultats"
        // This pane has 4 columns like this:
        // ---------------------------------
        // | Label | Field | Label | Field |
        // | Label | Field | Label | Field |
        // ---------------------------------
        // This way, we make sure that everything is perfectly aligned
        // Column 1
        VBox systemLabelsColumn = new VBox(10);
        systemLabelsColumn.getChildren().add(nbClientSysLabel);
        systemLabelsColumn.getChildren().add(tmpsAttSysLabel);
        // Column 2
        VBox systemFieldsColumn = new VBox(10);
        systemFieldsColumn.getChildren().add(nbClientSysField);
        systemFieldsColumn.getChildren().add(tmpsAttSysField);
        // Column 3
        VBox queueLabelsColumn = new VBox(10);
        queueLabelsColumn.getChildren().add(nbClientFileLabel);
        queueLabelsColumn.getChildren().add(tmpsAttFileLabel);
        // Column 4
        VBox queueFieldsColumn = new VBox(10);
        queueFieldsColumn.getChildren().add(nbClientFileField);
        queueFieldsColumn.getChildren().add(tmpsAttFileField);
        // Horizontal box that contains the columns
        HBox resultContent = new HBox(20);
        resultContent.getChildren().add(systemLabelsColumn);
        resultContent.getChildren().add(systemFieldsColumn);
        resultContent.getChildren().add(queueLabelsColumn);
        resultContent.getChildren().add(queueFieldsColumn);
        resultPane.setContent(resultContent);

        root.add(paramPane, 0, 0, 2, 4);
        root.add(resultPane, 0, 4, 4, 3);

        // Set the horizontal spacing to 15px
        root.setHgap(15);
        // Set the vertical spacing to 25px
        root.setVgap(25);

        root.setStyle("-fx-padding: 15;" );

        Scene scene = new Scene(root);

        stage.setScene(scene);
    }

    private void initLambda(){

        lambdaLabel = new Label("lambda");

        lambdaField = new TextField("1");
        lambdaField.minWidth(10);
        lambdaField.setPrefWidth(100);

        // Set binding to the value of MM1 class

    }

    private void initMu(){
        muLabel = new Label("mu");

        muField = new TextField("1");
        muField.minWidth(10);
        muField.setPrefWidth(100);
    }

    private void initFixedLabel(){
        resultatLabel = new Label("Résultat des calculs");
        tmpsAttSysLabel = new Label("Attente moyenne dans le système : ");
        tmpsAttFileLabel = new Label("Attente moyenne dans la file : ");
        nbClientFileLabel = new Label("Nombre moyen de client dans la file : ");
        nbClientSysLabel = new Label("Nombre moyen de client dans le système : ");

    }

    private void bindResultatLabel(MM1 mm1) {
        System.out.println("mm1.getMeanTimeInSystem().getValue() = " + mm1.getMeanTimeInSystem().getValue());
        tmpsAttSysField.textProperty().bind(mm1.getMeanTimeInSystem().asString());
        tmpsAttFileField.textProperty().bind(mm1.getMeanTimeInQueue().asString());
        nbClientFileField.textProperty().bind(mm1.getNbCustInQueue().asString());
        nbClientSysField.textProperty().bind(mm1.getNbCustInSystem().asString());
    }

    private void updateResults(){

        GridPane root = new GridPane();

        root.addRow(1, lambdaLabel,lambdaField);
        root.addRow(2, muLabel,muField);
        root.addRow(3, new Label(""), validateButton);
        root.addRow(4, resultatLabel);
        root.addRow(5, nbClientSysLabel, nbClientSysField, nbClientFileLabel, nbClientFileField);
        root.addRow(6, tmpsAttSysLabel, tmpsAttSysField, tmpsAttFileLabel, tmpsAttFileField);

        // Set the horizontal spacing to 15px
        root.setHgap(15);
        // Set the vertical spacing to 25px
        root.setVgap(25);

        root.setStyle("-fx-padding: 15;" );

        Scene scene = new Scene(root);

        stage.setScene(scene);
    }

    private void setValidateButton(){

        validateButton = new Button("Valider");
        validateButton.setMinWidth(10);
        validateButton.setPrefWidth(100);
        validateButton.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                mm1.getLambda().setValue(Float.parseFloat(lambdaField.textProperty().getValue()));
                mm1.getMu().setValue(Float.parseFloat(muField.textProperty().getValue()));
                mm1.computeRho();
                mm1.computeMeanTimeInSystem();
                mm1.computeMeanTimeInQueue();
                mm1.computeNbCustomerInQueue();
                mm1.computeNbCustomerInSystem();
                bindResultatLabel(mm1);

            }
        });
    }
}
