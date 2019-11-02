package Interface;

import javafx.beans.property.FloatProperty;
import javafx.collections.FXCollections;
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
import model.MM1K;

import java.util.List;

public class MM1KParameter extends Stage {


    private Stage stage;//window

    private TitledPane paramPane;
    private TitledPane resultPane;
    private TitledPane graphsPane;

    private TextField lambdaField;
    private TextField muField;
    private TextField kField;
    private ComboBox timeUnitCombo;

    private ToggleGroup radioButtonGroup;
    private RadioButton nbCustomerProbRadioButton;
    private RadioButton timeSpentProbRadioButton;

    private Label lambdaLabel;
    private Label muLabel;
    private Label kLabel;
    private Label timeUnitLabel;

    private Label resultatLabel;

    private Label nbClientSysLabel;
    private Label nbClientFileLabel;
    private Label tmpsAttSysLabel;
    private Label tmpsAttFileLabel;

    private Label nbClientSysField = new Label();
    private Label nbClientFileField = new Label();
    private Label tmpsAttSysField = new Label();
    private Label tmpsAttFileField = new Label();

    private BarChart customerProbabilitiesChart ;

    private MM1K mm1k = new MM1K(1);

    private Button validateButton;

    public void init(){

        stage = this;
        //setResultatLabel(0,0,0,0);
        setWindow();
        stage.show();
    }

    private void setWindow(){

        stage.setX(200);
        stage.setY(200);
        stage.setHeight(800);
        stage.setWidth(580);
        stage.setTitle("Calculateur file d'attente | File d'attente à serveur unique avec limite de clients finie");

        initLambda();
        initMu();
        initK();
        initTimeUnits();
        initFixedLabel();
        initRadioButtons();
        setValidateButton();
        GridPane root = new GridPane();

        initNbCustomerProbabilitiesChart();

        root.add(initTitledPaneParametres(), 0, 0, 4, 4);
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

    private void initLambda(){

        lambdaLabel = new Label("Nombre de clients entrant par unité de temps (λ)");

        lambdaField = new TextField("1");
        lambdaField.minWidth(10);
        lambdaField.setPrefWidth(100);
    }

    private void initMu(){
        muLabel = new Label("Nombre de clients sortant par unité de temps (μ)");

        muField = new TextField("1");
        muField.minWidth(10);
        muField.setPrefWidth(100);
    }

    private void initTimeUnits() {
        timeUnitLabel = new Label("Unité de temps");
        String timeUnits[] = { "Milliseconde", "Seconde", "Minute", "Heure"};
        timeUnitCombo = new ComboBox(FXCollections.observableArrayList(timeUnits));
    }

    private void initK(){

        kLabel = new Label("Nombre maximum de clients");

        kField = new TextField("2");
        kField.minWidth(10);
        kField.setPrefWidth(100);

    }

    private void initFixedLabel(){
        resultatLabel = new Label("Résultat des calculs");
        tmpsAttSysLabel = new Label("Attente moyenne dans le système : ");
        tmpsAttFileLabel = new Label("Attente moyenne dans la file : ");
        nbClientFileLabel = new Label("Nombre moyen de client dans la file : ");
        nbClientSysLabel = new Label("Nombre moyen de client dans le système : ");

    }

    public TitledPane initTitledPaneParametres() {
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
        VBox labelsColumn = new VBox(19);
        labelsColumn.getChildren().add(lambdaLabel);
        labelsColumn.getChildren().add(muLabel);
        labelsColumn.getChildren().add(kLabel);
        labelsColumn.getChildren().add(timeUnitLabel);
        // Column 2
        VBox fieldsColumn = new VBox(10);
        fieldsColumn.getChildren().add(lambdaField);
        fieldsColumn.getChildren().add(muField);
        fieldsColumn.getChildren().add(kField);
        fieldsColumn.getChildren().add(timeUnitCombo);
        // Horizontal box that contains the columns
        HBox paramContent = new HBox(20);
        paramContent.getChildren().add(labelsColumn);
        paramContent.getChildren().add(fieldsColumn);
        paramContent.getChildren().add(validateButton);
        paramPane.setContent(paramContent);

        return paramPane;
    }

    public TitledPane initTitledPaneResultats() {
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
        // Vertical box for the radio buttons
        VBox radioButtonBox = new VBox(10);
        radioButtonBox.getChildren().addAll(new Label("Choix du type de graphique :"), nbCustomerProbRadioButton, timeSpentProbRadioButton);
        // Horizontal box that contains the graphs
        HBox graphsContent = new HBox(20);
        graphsContent.getChildren().addAll(customerProbabilitiesChart, radioButtonBox);
        //graphsContent.getChildren().add();
        graphsPane.setContent(graphsContent);

        return graphsPane;
    }

    private void initRadioButtons() {
        radioButtonGroup = new ToggleGroup();
        nbCustomerProbRadioButton = new RadioButton("Probabilité des quantités de clients");
        nbCustomerProbRadioButton.setSelected(true);
        nbCustomerProbRadioButton.setToggleGroup(radioButtonGroup);
        timeSpentProbRadioButton = new RadioButton("Probabilité d'attente");
        timeSpentProbRadioButton.setToggleGroup(radioButtonGroup);
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

    private void setDataCharts(String title, List<FloatProperty> data, int xLowerBound) {
        // Define data
        XYChart.Series series = new XYChart.Series();
        series.setName(title);
        for (int i = 0; i < data.size(); i++) {
            series.getData().add(new XYChart.Data<String, Float>(String.valueOf(i+xLowerBound), data.get(i).getValue() * 100));
        }
        customerProbabilitiesChart.getData().clear();
        //Setting the data to Line chart
        customerProbabilitiesChart.getData().add(series);
    }

    private void bindResultatLabel(MM1K mm1k) {
        tmpsAttSysField.textProperty().bind(mm1k.getMeanTimeInSystem());
        tmpsAttFileField.textProperty().bind(mm1k.getMeanTimeInQueue());
        nbClientFileField.textProperty().bind(mm1k.getNbCustInQueue().asString());
        nbClientSysField.textProperty().bind(mm1k.getNbCustInSystem().asString());
    }

    private void setValidateButton(){

        validateButton = new Button("Valider");
        validateButton.setMinWidth(10);
        validateButton.setPrefWidth(100);
        validateButton.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                mm1k.getLambda().setValue(Float.parseFloat(lambdaField.textProperty().getValue()));
                mm1k.getMu().setValue(Float.parseFloat(muField.textProperty().getValue()));
                mm1k.getMaxCust().setValue(Integer.parseInt(kField.textProperty().getValue()));
                mm1k.getTimeUnit().setValue(timeUnitCombo.getValue().toString());
                mm1k.computeRho();
                mm1k.computeMeanTimeInSystem();
                mm1k.computeMeanTimeInQueue();
                mm1k.computeNbCustomerInQueue();
                mm1k.computeNbCustomerInSystem();
                mm1k.computeNbCustomerProbabilities();
                mm1k.computeWaitingTimeProbabilities();
                if (nbCustomerProbRadioButton.isSelected()) {
                    setDataCharts("Probabilités qu'il y ait X clients dans le système", mm1k.getProbabilityOfStates(), 0);
                }
                else if (timeSpentProbRadioButton.isSelected()) {
                    setDataCharts("Probabilités d'attendre plus de X heures", mm1k.getWaitingTimeProbabilities(), 1);
                }
                bindResultatLabel(mm1k);

            }
        });
    }

}
