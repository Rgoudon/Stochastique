import Interface.FirstWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"))
/*      primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();*/

        FirstWindow mainWind = new FirstWindow();
        mainWind.init();


    }


    public static void main(String[] args) {

        launch(args);
    }
}
