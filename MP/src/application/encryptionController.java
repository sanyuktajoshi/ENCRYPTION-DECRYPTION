package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
//import javafx.scene.layout.TilePane;
//import javafx.stage.Stage;

public class encryptionController implements Initializable{
	@FXML 
	public ComboBox<String> combobox;
	ObservableList<String> list = FXCollections.observableArrayList("Encrypt File", "Decrypt File");
	@FXML
	public Label mylabel;
	@FXML
	public Label l;


		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			combobox.setItems(list);
			
		}
		public void comboChanged(ActionEvent event) throws Exception{
			mylabel.setText(combobox.getValue());
			String message=combobox.getValue();
			if(message=="Encrypt File") {
				Stage primaryStage= new Stage();
				Parent root= FXMLLoader.load(getClass().getResource("/application/enc.fxml"));
				Scene scene = new Scene(root,500,500);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			}
			else if(message=="Decrypt File") {
				Stage primaryStage= new Stage();
				Parent root= FXMLLoader.load(getClass().getResource("/application/dec.fxml"));
				Scene scene = new Scene(root,500,500);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			}
			
		}
		
	    
}

