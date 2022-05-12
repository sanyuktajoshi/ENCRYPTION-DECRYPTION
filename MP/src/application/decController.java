package application;


import endec.passwordgen;
import endec.decryptImage;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
//import javafx.scene.control.Button;
//import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//import javafx.scene.layout.TilePane;
//import javafx.stage.Stage;

public class decController {
	//@FXML 
	//public ComboBox<String>
	//ObservableList<String> LIST = FXCollections.observableArrayList("AES", "CUSTOM ALGORITHM");
	//@FXML
	//public Label selectedAlgo;
	@FXML
	public Label file;
	
	@FXML
	private PasswordField passwordField;
	
	
	FileChooser fc = new FileChooser();
	public File selectedFile;
	//public String message= endec.passwordgen.generateValidPassword();
		
	public void saveFile(ActionEvent event) {
		
		//File selectedFile= fc.showOpenDialog(null);
		selectedFile= fc.showOpenDialog(null);
		if(selectedFile != null) {
			file.setText("You chose: "+selectedFile.getAbsolutePath());
		}
		else {
			file.setText(null);
		}
		String fileName =selectedFile.getPath();
		String ext=fileName.substring(fileName.length()-3);
		System.out.print("ext:"+ext);
		System.out.println("\nExtension:"+ext+"\n");
		String extension = ext.toString();
		if(!"png".equalsIgnoreCase(extension)&&!"jpg".equalsIgnoreCase(extension)) {
			System.out.println("Running");
			passwordField.setDisable(true);
		}
		}
	

		/*public void comboAlgo(ActionEvent event) throws Exception{
			selectedAlgo.setText("You chose: "+algolist.getValue());
			//System.out.println(algolist.getValue());
			//String message=combobox.getValue();
			
		}*/
		//@Override
		/*public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			algolist.setItems(LIST);
		
		}*/
		
		public void decryptBtn(ActionEvent event) throws Exception {
			
			String fileName =selectedFile.getPath();
			String message = passwordField.getText();
			System.out.print(message);
			String ext=fileName.substring(fileName.length()-3);
			System.out.print("ext:"+ext);
			System.out.println("\nExtension:"+ext+"\n");
			/*if(ext=="enc") {
				
			}*/
			String extension = ext.toString();
			if(!"png".equalsIgnoreCase(extension)&&!"jpg".equalsIgnoreCase(extension)) {
			endec.decryptFile de = endec.decryptFile.getDecrypter(true);
			de.decrypt(fileName, "C://Users//Sanyukta//OneDrive - somaiya.edu//SEMESTER - 4/MP/decrypted");
			System.out.println("Decrypted!");
			}
			else {
				System.out.println("Running");
				endec.decryptImage.Decrypt(fileName, message);
			}
			
		}
}
