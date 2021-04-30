
package element2;

/**
 *
 * @author rabiul
 */

import java.io.*;
import javafx.geometry.Insets;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.text.Text;


public class Element2 extends Application {

   private Scanner fileScanner;
   Button saveBtn, recordsBtn, formBtn, viewRecordBtn;
   TextField typeField,hallNameField, nameField, genderField, courseRoleField, yearSalaryField;
   Label typeLabelRecord, hallNameLabelRecord, nameLabelRecord, genderLabelRecord,courseLabelRecord,yearLabelRecord;
   
   Scene formScene, recordsScene;
   Stage window;
   
   
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        
        this.initialize();
        
        
        //First Scene
        
        saveBtn= new Button();
        saveBtn.setText("Save Record");
        saveBtn.setOnAction(e -> this.addRecord());
        
        
        recordsBtn= new Button();
        recordsBtn.setText("Go to Records");
        recordsBtn.setOnAction(e->window.setScene(recordsScene)); //CHanges the scene being displayed to the records scene
        
        
        
        Text typeLabel = new Text("Person Type( Student/Employee)");
        typeField= new TextField();
        
        Text hallNameLabel = new Text("Name of assigned Hall");
        hallNameField= new TextField();
        
        Text nameLabel = new Text("Occupant Name");
        nameField= new TextField();
        
        Text genderLabel = new Text("Occupant Gender");
        genderField= new TextField();
        
        Text courseLabel = new Text("Course(student) or Role(Employee)");
        courseRoleField= new TextField();
        
        Text yearLabel = new Text("Year of study(student) or Salary(Employee)");
        yearSalaryField= new TextField();
        
        //Vertically layered items
        VBox formLayout = new VBox(10);
        formLayout.setPadding(new Insets(10, 50, 50, 50));
        formLayout.getChildren().addAll(typeLabel, typeField, hallNameLabel, hallNameField,nameLabel,nameField,genderLabel, genderField, courseLabel, courseRoleField, yearLabel, yearSalaryField, saveBtn,recordsBtn);
        
        formScene = new Scene(formLayout, 680, 450);
        
        
        
        
        
        //Set up the second scene
        
        formBtn= new Button();
        formBtn.setText("Go to Form");
        formBtn.setOnAction(e->window.setScene(formScene));//CHanges the scene being displayed to the form scene
        
        typeLabelRecord = new Label("Occupant Type: ");
        hallNameLabelRecord = new Label("Hall Name: ");
        nameLabelRecord = new Label("Name: ");
        genderLabelRecord = new Label("Gender: ");
        courseLabelRecord = new Label("Course: ");
        yearLabelRecord = new Label("Year: ");
        
        
        viewRecordBtn = new Button();
        viewRecordBtn.setText("View record");
        viewRecordBtn.setOnAction(e-> this.fetchRecords());
        
       
        VBox recordsLayout = new VBox(20);
        recordsLayout.setPadding(new Insets(10, 50, 50, 50));
        recordsLayout.getChildren().addAll(typeLabelRecord, hallNameLabelRecord, nameLabelRecord, genderLabelRecord, courseLabelRecord, yearLabelRecord, viewRecordBtn, formBtn);
        recordsScene = new Scene(recordsLayout, 680, 450);
                
                
 
        
        window.setScene(formScene);
        window.setTitle("Element 2 OOP");
        window.show();
        
    }
    
    
    public void fetchRecords(){
         try{
               String[] s;
               //Read the contents of the text file that have been stored in the fileScanner line by line
                if(fileScanner.hasNextLine()){
                 
                    s = fileScanner.nextLine().split(",");
                    
                    hallNameLabelRecord.setText("Hall name: "+s[0]);
                    typeLabelRecord.setText("Occupant Type: "+s[1]);
                    nameLabelRecord.setText("Name: "+s[2]);
                    genderLabelRecord.setText("Gender: "+s[3]);
                    courseLabelRecord.setText("Course/Role: "+s[4]);
                    yearLabelRecord.setText("Year/Salary: "+s[5]);
                }else{
                    //Re instantiate the file scanner so that it can be read from the beginning once the end of the file has been reached
                    File file = new File("/Users/mkamau/desktop/records.txt");
                    fileScanner = new Scanner(file);
                    
                    //Display an alert to inform the user that the end of the file has been reached
                    
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("You have exhausted all the records!");
                    alert.showAndWait();
                }
                
         } catch(Exception e){
             //Throw an exception when the file cannot be read
             System.out.println("could not be able to read the file");
         }
    }
    
    
    public void addRecord(){
        try{
            //Instantiate the file writer and set it to append
            FileWriter writer = new FileWriter("/Users/mkamau/desktop/records.txt", true);
            
            if(getData(typeField).equalsIgnoreCase("student")){
                //Create a student object and save the record
                 Student student = new Student(getData(nameField),getData(genderField),getData(courseRoleField),getData(yearSalaryField));
                 Hall hall = new Hall(getData(hallNameField),student);
                 writer.write( hall.getName()+","+hall.getType()+","+hall.getStudent().getName()+","+ hall.getStudent().getGender()+","+ hall.getStudent().getCourse() + "," + hall.getStudent().getYear()+"\n");
                 
            }else if (getData(typeField).equalsIgnoreCase("employee")){
                if(Integer.valueOf(getData(yearSalaryField)) > 0){
                    //Create an Employee object and save the record
                     Employee employee = new Employee(getData(nameField),getData(genderField),getData(courseRoleField),Integer.valueOf(getData(yearSalaryField)));
                     System.out.println("could not be able to read the file"+ Integer.valueOf(getData(yearSalaryField)));
                     Hall hall = new Hall(getData(hallNameField),employee);
                     writer.write( hall.getName()+","+hall.getType()+","+hall.getEmployee().getName()+","+ hall.getEmployee().getGender()+","+ hall.getEmployee().getRole() + "," + hall.getEmployee().getSalary()+"\n");
                }else{
                     Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please input a valid integer Salary for the employee!");
                    alert.showAndWait();
                }
                  
                
            }
            else{
                //Alert the user to type the correct occupant type
                Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please input a valid Occupant type(Student/Employee)!");
                    alert.showAndWait();
            }
        
           
            
            //After saving the record, update the filescanner with the new data
            File file = new File("/Users/mkamau/desktop/records.txt");
            fileScanner = new Scanner(file);
      
            writer.close();
            
            
         } catch(Exception e){
             System.out.println("could not be able to write into the file"+ e);
         }
    }
    
    
    public String getData(TextField input){
        //Extract text from TextField
        return input.getText();   
    }
    
    
    public void initialize(){
            try{                  
           //Initialize the file scanner with the saved records
            File file = new File("/Users/mkamau/desktop/records.txt");
            fileScanner = new Scanner(file);
           
            
         } catch(Exception e){
             System.out.println("could not be able to write into the file");
         }
    }
   
    
}
