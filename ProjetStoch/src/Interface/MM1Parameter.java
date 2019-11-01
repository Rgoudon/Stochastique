package Interface;

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

    private Label nbClientSys;
    private Label nbClientFile;
    private Label tmpsAttSys;
    private Label tmpsAttFile;

    private MM1 formules = new MM1();

    private Button validateButton;

    public void init(){

        stage = this;
        setResultatLabel(0,0,0,0);
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
        systemFieldsColumn.getChildren().add(nbClientSys);
        systemFieldsColumn.getChildren().add(tmpsAttSys);
        // Column 3
        VBox queueLabelsColumn = new VBox(10);
        queueLabelsColumn.getChildren().add(nbClientFileLabel);
        queueLabelsColumn.getChildren().add(tmpsAttFileLabel);
        // Column 4
        VBox queueFieldsColumn = new VBox(10);
        queueFieldsColumn.getChildren().add(nbClientFile);
        queueFieldsColumn.getChildren().add(tmpsAttFile);
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
        tmpsAttSysLabel = new Label("Attente moyenne dans le système : ");
        tmpsAttFileLabel = new Label("Attente moyenne dans la file : ");
        nbClientFileLabel = new Label("Nombre moyen de client dans la file : ");
        nbClientSysLabel = new Label("Nombre moyen de client dans le système : ");

    }

    private void setResultatLabel(float attenteFile , float attenteSys , float clientFile, float clientSys) {

        tmpsAttSys = new Label(Float.toString(attenteSys));
        tmpsAttFile = new Label(Float.toString(attenteFile));
        nbClientFile = new Label(Float.toString(clientFile));
        nbClientSys = new Label(Float.toString(clientSys));
    }

    private void updateResults(){

        GridPane root = new GridPane();

        root.addRow(1, lambdaLabel,lambdaField);
        root.addRow(2, muLabel,muField);
        root.addRow(3, new Label(""), validateButton);
        root.addRow(4, resultatLabel);
        root.addRow(5, nbClientSysLabel,nbClientSys, nbClientFileLabel,nbClientFile);
        root.addRow(6, tmpsAttSysLabel,tmpsAttSys, tmpsAttFileLabel,tmpsAttFile);

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

                formules.setLambda(Float.parseFloat(lambdaField.getText()));
                formules.setMu(Float.parseFloat(muField.getText()));
                formules.computeRho();
                System.out.println("lamb " + Float.parseFloat(lambdaField.getText()));
                System.out.println("mu " + Float.parseFloat(muField.getText()));
                System.out.println("Rho = " + formules.getRho());



                System.out.println("tmps moyen systeme :" + formules.computeMeanTimeInSystem() + " \n"+
                                "tmps moyen file :" + formules.computeMeanTimeInQueue()+ " \n" +
                        "nb client file :" + formules.computeNbCustomerInQueue() + "\n" +
                        "nb client sys :" + formules.computeNbCustomerInSystem());


                setResultatLabel(formules.computeMeanTimeInSystem(),formules.computeMeanTimeInQueue()
                        ,formules.computeNbCustomerInSystem(),formules.computeNbCustomerInQueue());
                updateResults();

            }
        });
    }
}
