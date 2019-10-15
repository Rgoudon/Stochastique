package Interface;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MMSResult extends Stage {

    private Stage stage;//window

    private TableView resultArray;

    private TableColumn lambdaCol;
    private TableColumn muCol;
    private TableColumn roCol;
    private TableColumn durationCol;

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

        BorderPane root = new BorderPane();
        setTable();
        root.setTop(new Label("Résultat"));
        root.setCenter(resultArray);

        root.setStyle("-fx-padding: 15;" );

        Scene scene = new Scene(root);

        stage.setScene(scene);
    }

    private void setTable(){

        resultArray = new TableView();
        lambdaCol = new TableColumn("Lambda");
        muCol = new TableColumn("Mu");
        roCol = new TableColumn("Ro");
        durationCol = new TableColumn("Durée");

        lambdaCol.setCellValueFactory(new PropertyValueFactory("lambda"));
        muCol.setCellValueFactory(new PropertyValueFactory("mu"));
        roCol.setCellValueFactory(new PropertyValueFactory("ro"));
        durationCol.setCellValueFactory(new PropertyValueFactory("duree"));


        resultArray.getColumns().addAll(lambdaCol,muCol,roCol, durationCol);

    }
}
