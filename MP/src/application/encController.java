package application;

import endec.passwordgen;

import endec.encryptImage;

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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
//import javafx.scene.control.Button;
//import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//import javafx.scene.layout.TilePane;
//import javafx.stage.Stage;

public class encController implements Initializable{
	@FXML 
	public ComboBox<String> algolist;
	ObservableList<String> LIST = FXCollections.observableArrayList("GENERAL ENC ALGORITHM", "CUSTOM IMAGE MANIPLUATION ALGORITHM");
	
	/*@FXML 
	public ComboBox<String> algolist2;
	ObservableList<String> LIST2 = FXCollections.observableArrayList("AES");*/
	
	@FXML
	public Label selectedAlgo;
	@FXML
	public Label file;
	
	@FXML
	private PasswordField passwordPasswordField;
	
	@FXML
	public Label pwd;
	
	@FXML
	Button b=new Button("Generate Password");
	
	FileChooser fc = new FileChooser();
	File selectedFile;
	int flag=0;
	
	public String message= endec.passwordgen.generateValidPassword();
		
	public void saveFile(ActionEvent event1) {
		
		//File selectedFile= fc.showOpenDialog(null);
		selectedFile= fc.showOpenDialog(null);
		if(selectedFile != null) {
			file.setText("You chose: "+selectedFile.getAbsolutePath());
		}
		else {
			file.setText(null);
		}
		String fileName =selectedFile.getPath();
		String ext = fileName.substring(fileName.length()-3);
		System.out.println("\nExtension:"+ext+"\n");
		String ext1="png";
		String ext2="jpg";
		String extension = ext.toString();
		if(!"png".equalsIgnoreCase(extension)&&!"jpg".equalsIgnoreCase(extension)) {
			algolist.setDisable(true);
			passwordPasswordField.setDisable(true);
		}
		else {
			algolist.setDisable(false);
			passwordPasswordField.setDisable(false);
		}
	}

		public void comboAlgo(ActionEvent event2) throws Exception{
				selectedAlgo.setText("You chose: "+algolist.getValue());
			//System.out.println(algolist.getValue());
			String message=algolist.getValue();
			if(message=="GENERAL ENC ALGORITHM") {
				flag=1;
			}
			else if(message=="CUSTOM IMAGE MANIPLUATION ALGORITHM") {
				flag=2;
			}
			
		}
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			algolist.setItems(LIST);
			//algolist2.setItems(LIST2);
		
		}
		
		public void encryptBtn(ActionEvent event3) throws Exception {
			
			String fileName =selectedFile.getPath();
			//endec.encryptImage.Encrypt(fileName, message);
			//endec.encryptFile en = endec.encryptFile.getEncrypter(true);
			//en.encrypt(fileName, "C://Users//Sanyukta//OneDrive - somaiya.edu//SEMESTER - 4/MP/encrypted");
			String ext=fileName.substring(fileName.length()-3);
			
			System.out.print("ext:"+ext);
			System.out.println("\nExtension:"+ext+"\n");
			String ext1="png";
			String ext2="jpg";
			String extension = ext.toString();
			/*if(extension!="png"||extension!="jpg") {
				System.out.println("This is running");
				endec.encryptFile en = endec.encryptFile.getEncrypter(true);
				en.encrypt(fileName, "C://Users//Sanyukta//OneDrive - somaiya.edu//SEMESTER - 4/MP/encrypted");
			}*/
			/*else if((ext=="png"||ext=="jpg") && flag==2) {
			System.out.println("Running");
			endec.encryptImage.Encrypt(fileName, message);
			}
			else if((ext=="png"||ext=="jpg")&& flag==1) {
				endec.encryptFile en = endec.encryptFile.getEncrypter(true);
				en.encrypt(fileName, "C://Users//Sanyukta//OneDrive - somaiya.edu//SEMESTER - 4/MP/encrypted");
			}*/
			if(!"png".equalsIgnoreCase(extension)&&!"jpg".equalsIgnoreCase(extension)){
				
				System.out.println("This is running");
				endec.encryptFile en = endec.encryptFile.getEncrypter(true);
				en.encrypt(fileName, "C://Users//Sanyukta//OneDrive - somaiya.edu//SEMESTER - 4/MP/encrypted");
			}
			else {
				if(flag==1) {
					System.out.println("\nFlag:"+flag);
					endec.encryptFile en = endec.encryptFile.getEncrypter(true);
					en.encrypt(fileName, "C://Users//Sanyukta//OneDrive - somaiya.edu//SEMESTER - 4/MP/encrypted");
				}
				else if(flag==2) {
					System.out.println("\nFlag:"+flag);
					endec.encryptImage.Encrypt(fileName, message);
				}
			}
			
			
		}
		public void generatePwd(ActionEvent event) {
			
			//String message= endec.passwordgen.generateValidPassword();
			final Clipboard clipboard = Clipboard.getSystemClipboard(); 
			final ClipboardContent content = new ClipboardContent(); 
			content.putString(message); 
			content.putHtml(message); 
			clipboard.setContent(content); 
			pwd.setText("Password generated and saved to clipboard!");
	}
		//b.setOnAction(event);
}


		
	    

