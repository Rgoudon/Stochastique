package Interface;

import javafx.beans.property.FloatProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MMS;

import java.util.List;

public class MMSParameter extends Stage{

    private Stage stage;//window

    private TitledPane paramPane;
    private TitledPane resultPane;
    private TitledPane graphsPane;

    private TextField lambdaField;
    private TextField muField;
    private TextField sField;

    private Label lambdaLabel;
    private Label muLabel;
    private Label sLabel;

    private Label resultatLabel;

    private Label nbClientSysLabel;
    private Label nbClientFileLabel;
    private Label tmpsAttSysLabel;
    private Label tmpsAttFileLabel;

    private Label nbClientSysField = new Label();
    private Label nbClientFileField = new Label();
    private Label tmpsAttSysField = new Label();
    private Label tmpsAttFileField = new Label();

    private BarChart customerProbabilitiesChart;

    private MMS mms = new MMS(2);

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
        setS();
        setFixedLabel();
        setValidateButton();
        GridPane root = new GridPane();

        initNbCustomerProbabilitiesChart();

        root.add(initTitledPaneParametres(), 0, 0, 2, 4);
        root.add(initTitledPaneResultats(), 0, 4, 4, 3);
        root.add(initTitledPaneGraphiques(), 0, 7, 4, 3);

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

    private void setFixedLabel(){
        resultatLabel = new Label("Résultat des calculs");
        tmpsAttSysLabel = new Label("Attente moyenne dans le système");
        tmpsAttFileLabel = new Label("Attente moyenne dans la file");
        nbClientFileLabel = new Label("Nombre moyen de client dans la file");
        nbClientSysLabel = new Label("Nombre moyen de client dans le système");

    }

    private void setResultatLabel(float attenteFile , float attenteSys , float clientFile, float clientSys) {

        tmpsAttSysField = new Label(Float.toString(attenteSys));
        tmpsAttFileField = new Label(Float.toString(attenteFile));
        nbClientFileField = new Label(Float.toString(clientFile));
        nbClientSysField = new Label(Float.toString(clientSys));
    }

    private TitledPane initTitledPaneParametres() {
        // TitledPane "Paramètres"
        paramPane = new TitledPane();
        paramPane.setCollapsible(false);
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
        labelsColumn.getChildren().add(sLabel);
        // Column 2
        VBox fieldsColumn = new VBox(10);
        fieldsColumn.getChildren().add(lambdaField);
        fieldsColumn.getChildren().add(muField);
        fieldsColumn.getChildren().add(sField);
        // Horizontal box that contains the columns
        HBox paramContent = new HBox(20);
        paramContent.getChildren().add(labelsColumn);
        paramContent.getChildren().add(fieldsColumn);
        paramContent.getChildren().add(validateButton);
        paramPane.setContent(paramContent);
        return paramPane;
    }

    private TitledPane initTitledPaneResultats() {
        // TitledPane "Résultats"
        resultPane = new TitledPane();
        resultPane.setCollapsible(false);
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
        return resultPane;
    }

    public TitledPane initTitledPaneGraphiques() {
        // TitledPane "Graphiques"
        graphsPane = new TitledPane();
        graphsPane.setCollapsible(false);
        graphsPane.setText("Graphiques");
        // Content for TitledPane "Graphiques"
        // Horizontal box that contains the graphs
        HBox graphsContent = new HBox(20);

        graphsContent.getChildren().add(customerProbabilitiesChart);
        //graphsContent.getChildren().add();
        graphsPane.setContent(graphsContent);

        return graphsPane;
    }

    private void initNbCustomerProbabilitiesChart() {
        //Defining X axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Nombre de clients");
        xAxis.setAutoRanging(true);
        //Defining y axis
        NumberAxis yAxis = new NumberAxis(0, 100, 10);
        yAxis.setLabel("% de d'avoir X clients");
        yAxis.setAutoRanging(true);
        customerProbabilitiesChart = new BarChart(xAxis, yAxis);
    }

    private void setDataCustomerProbCharts(List<FloatProperty> customerProbabilities) {
        // Define data
        XYChart.Series series = new XYChart.Series();
        for(int i = 0; i<customerProbabilities.size(); i++) {
            series.getData().add(new XYChart.Data<String, Float>(String.valueOf(i), customerProbabilities.get(i).getValue()*100));
        }
        customerProbabilitiesChart.getData().clear();
        //Setting the data to Line chart
        customerProbabilitiesChart.getData().add(series);
    }

    private void bindResultatLabel(MMS mms) {
        System.out.println("mm1.getMeanTimeInSystem().getValue() = " + mms.getMeanTimeInSystem().getValue());
        tmpsAttSysField.textProperty().bind(mms.getMeanTimeInSystem().asString());
        tmpsAttFileField.textProperty().bind(mms.getMeanTimeInQueue().asString());
        nbClientFileField.textProperty().bind(mms.getNbCustInQueue().asString());
        nbClientSysField.textProperty().bind(mms.getNbCustInSystem().asString());
    }
    private void updateResults(){

        GridPane root = new GridPane();

        root.addRow(1, lambdaLabel,lambdaField);
        root.addRow(2, muLabel,muField);
        root.addRow(3,sLabel,sField);
        root.addRow(4, new Label(""), validateButton);
        root.addRow(5, resultatLabel);
        root.addRow(6, nbClientSysLabel,nbClientSysField, nbClientFileLabel, nbClientFileField);
        root.addRow(7, tmpsAttSysLabel, tmpsAttSysField, tmpsAttFileLabel, tmpsAttFileField);

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
                mms.getLambda().setValue(Float.parseFloat(lambdaField.textProperty().getValue()));
                mms.getMu().setValue(Float.parseFloat(muField.textProperty().getValue()));
                mms.getNbServer().setValue(Integer.parseInt(sField.textProperty().getValue()));
                try {
                    mms.computeRho();
                }
                catch (ArithmeticException ae) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Oups!");
                    alert.setContentText(ae.getMessage());
                    alert.showAndWait();
                    return;
                }
                mms.computeR(10);
                mms.computeNbCustomerProbabilities();
                mms.computeMeanTimeInSystem();
                mms.computeMeanTimeInQueue();
                mms.computeNbCustomerInQueue();
                mms.computeNbCustomerInSystem();
                setDataCustomerProbCharts(mms.getProbabilityOfStates());
                bindResultatLabel(mms);
            }
        });
    }
}
