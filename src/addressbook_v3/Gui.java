package addressbook_v3;
// V2

/**********************************************

Workshop #3

Course:<JAC444> - Semester 4

Last Name:<Osorio>

First Name:<Eduardo>

ID:<033751140>

Section:<SCD>

This assignment represents my own work in accordance with Seneca Academic Policy.

Signature Eduardo Osorio

Date:<March 21 11:59, 2018>

**********************************************/

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Gui extends Application implements EventHandler<ActionEvent>{

	// Array Lists
	private ArrayList<String> names;
	private ArrayList<String> streets;
	private ArrayList<String> cities;
	private ArrayList<String> states;
	private ArrayList<String> zips;
	private ArrayList<TextField> textFields;
	private ArrayList<Button> buttons;
	private ArrayList<Label> labels;
	// Finals
	// Text Fields
	private final int TF_NAME = 0;
	private final int TF_STREET = 1;
	private final int TF_CITY = 2;
	private final int TF_STATE = 3;
	private final int TF_ZIP = 4;
	// Labels
	private final int L_NAME = 0;
	private final int L_STREET = 1;
	private final int L_CITY = 2;
	private final int L_STATE = 3;
	private final int L_ZIP = 4;
	// Buttons
	private final int BTN_ADD = 0;
	private final int BTN_FIRST = 1;
	private final int BTN_NEXT = 2;
	private final int BTN_PREVIOUS = 3;
	private final int BTN_LAST = 4;
	private final int BTN_UPDATE = 5;
	// Other
	RandomAccessFile ra;
	final int NUM_OF_BYTES = 96;
	private int currentPos;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ra = new RandomAccessFile("src/address.dat", "rw");
		zips = new ArrayList<>();
		streets = new ArrayList<>();
		cities = new ArrayList<>();
		states = new ArrayList<>();
		names = new ArrayList<>();
		labels = new ArrayList<>();
		textFields = new ArrayList<>();
		buttons = new ArrayList<>();
		currentPos = -1;
		loadData();
		// Making the pane
		GridPane gp = new GridPane();
		// Sets the padding of the pane from the window
		gp.setPadding(new Insets(10,10,10,10));
		gp.setHgap(8);
		gp.setVgap(8);
		
		// Making all the labels
		labels.add(new Label("Name"));
		labels.add(new Label("Street"));
		labels.add(new Label("City"));
		labels.add(new Label("State"));
		labels.add(new Label("Zip"));
		
		// Setting all the TextFields
		textFields.add(new TextField());
		textFields.add(new TextField());
		textFields.add(new TextField());
		textFields.add(new TextField());
		textFields.add(new TextField());
		
		// Setting the lengths of the text fields
		textFields.get(TF_NAME).setPrefColumnCount(30);
		textFields.get(TF_STREET).setPrefColumnCount(30);
		textFields.get(TF_CITY).setPrefColumnCount(8);
		textFields.get(TF_STATE).setPrefColumnCount(2);
		textFields.get(TF_ZIP).setPrefColumnCount(6);
		
		// Setting the fonts/font sizes of all the labels
		labels.forEach(labels->new Font("Arial",18));
		
		buttons.add(new Button("Add"));
		buttons.add(new Button("First"));
		buttons.add(new Button("Next"));
		buttons.add(new Button("Previous"));
		buttons.add(new Button("Last"));
		buttons.add(new Button("Update"));
		
		// Button Handling
		buttons.forEach(button->{
			button.setOnAction(this);
		});
		
		// Text Handling
		textFields.get(TF_NAME).lengthProperty().addListener(new ChangeListener<Number>() {
			int max = 32;
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				if (textFields.get(TF_NAME).getText().length() > max) {
	                String s = textFields.get(TF_NAME).getText().substring(0, max);
	                textFields.get(TF_NAME).setText(s);
	            }
			}
		});
		textFields.get(TF_STREET).lengthProperty().addListener(new ChangeListener<Number>() {
			int max = 32;
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				if (textFields.get(TF_STREET).getText().length() > max) {
	                String s = textFields.get(TF_STREET).getText().substring(0, max);
	                textFields.get(TF_STREET).setText(s);
	            }
			}
		});
		textFields.get(TF_CITY).lengthProperty().addListener(new ChangeListener<Number>() {
			int max = 20;
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				if (textFields.get(TF_CITY).getText().length() > max) {
	                String s = textFields.get(TF_CITY).getText().substring(0, max);
	                textFields.get(TF_CITY).setText(s);
	            }
			}
		});
		textFields.get(TF_STATE).lengthProperty().addListener(new ChangeListener<Number>() {
			int max = 2;
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				if (textFields.get(TF_STATE).getText().length() > max) {
	                String s = textFields.get(TF_STATE).getText().substring(0, max);
	                textFields.get(TF_STATE).setText(s);
	            }
			}
		});
		textFields.get(TF_ZIP).lengthProperty().addListener(new ChangeListener<Number>() {
			int max = 5;
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				if (textFields.get(TF_ZIP).getText().length() > max) {
	                String s = textFields.get(TF_ZIP).getText().substring(0, max);
	                textFields.get(TF_ZIP).setText(s);
	            }
			}
		});

		// First Row
		gp.add(labels.get(L_NAME), 0, 0);
		gp.add(textFields.get(TF_NAME), 1, 0, 10, 1);
		// Second Row
		gp.add(labels.get(L_STREET), 0, 1);
		gp.add(textFields.get(L_STREET), 1, 1, 10, 1);
		// Second Row
		gp.add(labels.get(L_CITY), 0, 2);
		gp.add(textFields.get(L_CITY), 1, 2, 3, 1);
		gp.add(labels.get(L_STATE), 4, 2, 1, 1);
		gp.add(textFields.get(L_STATE), 5, 2, 2, 1);
		gp.add(labels.get(L_ZIP), 7, 2, 1, 1);
		gp.add(textFields.get(L_ZIP), 8, 2, 2, 1);
		// Add the buttons on the 4th Row
		FlowPane fp = new FlowPane();
		
		fp.getChildren().addAll(buttons.get(BTN_ADD),buttons.get(BTN_FIRST),buttons.get(BTN_NEXT),
								buttons.get(BTN_PREVIOUS),buttons.get(BTN_LAST),buttons.get(BTN_UPDATE));
		
		fp.setAlignment(Pos.CENTER);
		gp.add(fp, 0,3,11,1);
		
		Scene scene = new Scene(gp, 470, 154);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Address Book");
		primaryStage.show();
	}

	// BUTTON HANDLING
	@Override
	public void handle(ActionEvent event) {
		try{
			
			if(event.getSource() == buttons.get(BTN_ADD)) {
				
				// BUTTON ADD
				removeRedBorder();
				if(isEmpty()) {
					return;
				}else {
					String name = textFields.get(TF_NAME).getText();
					// Checks if the name is already in the file
					if(names.contains(name.toUpperCase())) {
						errorBox(TF_NAME, "Already have the name");
						return;
					}
					String street = textFields.get(TF_STREET).getText();
					String city = textFields.get(TF_CITY).getText();
					String state = textFields.get(TF_STATE).getText();
					String zip = textFields.get(TF_ZIP).getText();
					addAddress(name, street, city, state, zip);
					
					ra = new RandomAccessFile("src/address.dat", "rw");
					write();
					currentPos = 0;
				}
				// END OF BUTTON ADD
				
			}else if(event.getSource() == buttons.get(BTN_FIRST)) {
					removeRedBorder();
					currentPos = 0;
					findText();
				}else if(event.getSource() == buttons.get(BTN_NEXT)) {
					removeRedBorder();
					currentPos++;
					if(currentPos >= this.names.size()) {
						currentPos = 0;
					}
					findText();
					
				}else if(event.getSource() == buttons.get(BTN_PREVIOUS)) {
					removeRedBorder();
					if(currentPos <= 0) {
						currentPos = this.names.size();
					}
					currentPos--;
					findText();
					
				}else if(event.getSource() == buttons.get(BTN_LAST)) {
					removeRedBorder();
					currentPos = this.names.size() - 1;
					findText();
				}else if(event.getSource() == buttons.get(BTN_UPDATE)) {
					removeRedBorder();
					
					if(isEmpty()) {
						return;
					}else if(names.contains(textFields.get(TF_NAME).getText().trim().toUpperCase())){
						
						currentPos = names.indexOf(textFields.get(TF_NAME).getText().trim().toUpperCase());
						streets.set (currentPos, textFields.get(TF_STREET).getText().trim().toUpperCase());
						states.set  (currentPos, textFields.get(TF_STATE).getText().trim().toUpperCase());
						zips.set    (currentPos, textFields.get(TF_ZIP).getText().trim().toUpperCase());
						cities.set  (currentPos, textFields.get(TF_CITY).getText().trim().toUpperCase());
						
						ra = new RandomAccessFile("src/address.dat", "rw");
						write();
						
					}else{
						errorBox(TF_NAME, "Name does not exist in the file");
					}
				}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}
	// Writes to the file
	public void write() {
		char[] addresses = newAddresses().toCharArray();
		for(int i = 0; i < newAddresses().length(); i++) {
			try {
				ra.write(addresses[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Format the new address to be written
	public String newAddresses() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < this.names.size(); i++) {
			sb.append(names.get(i)+":"+streets.get(i)+":"+cities.get(i)+":"+states.get(i)+":"+zips.get(i)+";");
		}
		return sb.toString();
	}
	
	// Remove the red border
	public void removeRedBorder() {
		textFields.forEach(tf->{
			tf.setStyle("-fx-border-color: none ; -fx-border-width: 0px ;");
		});
	}
	
	// Checks if the text field is empty
	public boolean isEmpty() {
		if(textFields.get(TF_NAME).getText().isEmpty()        ||
				textFields.get(TF_STREET).getText().isEmpty() || 
				textFields.get(TF_CITY).getText().isEmpty()   || 
				textFields.get(TF_STATE).getText().isEmpty()  || 
				textFields.get(TF_ZIP).getText().isEmpty()) {
			textFields.forEach(tf->{
				if(tf.getText().isEmpty()) {
					errorBox(textFields.indexOf(tf), "Its Empty");
				}
			});
			return true;
		}
		return false;
	}
	// Creates an error box
	public void errorBox(int tf, String statement) {
		textFields.get(tf).setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
		textFields.get(tf).setText("");
		textFields.get(tf).setPromptText(statement);
	}

	// Find the next address based on the position
	public void findText() {
		String[] s = getCurrentPosition();
		textFields.get(TF_NAME).setText(s[TF_NAME]);
		textFields.get(TF_STREET).setText(s[TF_STREET]);
		textFields.get(TF_CITY).setText(s[TF_CITY]);
		textFields.get(TF_STATE).setText(s[TF_STATE]);
		textFields.get(TF_ZIP).setText(s[TF_ZIP]);
	}
	
	// gets the current position
	public String[] getCurrentPosition() {
		String s[] = {names.get(currentPos), streets.get(currentPos), cities.get(currentPos), 
					  states.get(currentPos), zips.get(currentPos)};
		return s;
	}
	// add the address to lists
	public void addAddress(String name, String street, String city, String state, String zip) {
		names.add(name.trim().toUpperCase());
		streets.add(street.trim().toUpperCase()); 
		cities.add(city.trim().toUpperCase());
		states.add(state.trim().toUpperCase());
		zips.add(zip.trim().toUpperCase());
	}
	
	// Loads the data from address.dat to the arraylists
	public void loadData() {
		try {
			ra.seek(0);
			char endOfString = 'a';
			boolean end = false;
			String name = "";
			String street = "";
			String city = "";
			String state = "";
			String zip = "";
			int addressField = 0;
			while(!end) {
				if(ra.getFilePointer() == ra.length()) {
					end = true;
				}
				endOfString = (char)ra.read();
				if(endOfString == ';') {
					addAddress(name, street, city, state, zip);
					name = "";street = "";city = "";state = "";zip = "";
					addressField = 0;
				}else if(endOfString == ':') {
					addressField++;
				}else if(addressField == 0) {
					name += endOfString;
				}else if(addressField == 1) {
					street += endOfString;	
				}else if(addressField == 2) {
					city += endOfString;	
				}else if(addressField == 3) {
					state += endOfString;	
				}else if(addressField == 4) {
					zip += endOfString;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
