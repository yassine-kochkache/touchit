/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidev4;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Location;
import services.LocationServices;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author yassi
 */
public class GestionLocationController implements Initializable {
@FXML
    private TableView<Location> tableloc;

    @FXML
    private TableColumn<Location, String> modela;
    @FXML
    private TableColumn<Location, String> prixa;
    @FXML
    private TableColumn<Location, String> dateloca;
    @FXML
    private TableColumn<Location, String> dureea;
    @FXML
    private TextField tflmodel;
    @FXML
    private TextField tflprix;
    @FXML
    private TextField tfldateloc;
    @FXML
    private TextField tflduree;
            private Location c; 
            
            ObservableList<Location>  list =  FXCollections.observableArrayList();
      int  index= -1; 
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<Location>  list =  FXCollections.observableArrayList();
          try { 
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT model , prix, dateloc, duree FROM location");
            while(rs.next())
            {
                list.add(new Location(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }

       modela.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getModel());
        });
    //image_colonne.setCellValueFactory(new PropertyValueFactory<>("image"));

    prixa.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getPrix());
        });
    dateloca.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getDateloc());
        });
         dureea.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getDuree());
        });
  
        // TODO
 tableloc.setItems(list);
  tableloc.refresh();
        // TODO
    }    

    @FXML
    private void ajouterloc(ActionEvent event) {
         if(verifUserChamps() ){ 
                        if ( controlSaisie()){
         LocationServices ts = new LocationServices();
       ts.AjouterLocation(new Location(tflmodel.getText(),tflprix.getText(),tfldateloc.getText(),tflduree.getText()));

       tableloc.refresh();
    }
         }}
    @FXML
    private void refresh(ActionEvent event) {
        ObservableList<Location>  list =  FXCollections.observableArrayList();
          try { 
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT model , prix, dateloc, duree FROM location");
            while(rs.next())
            {
                list.add(new Location(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }

       modela.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getModel());
        });

      
   prixa.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getPrix());
        });

    dateloca.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getDateloc());
        });
    dureea.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getDuree());
        });
         
        // TODO
 tableloc.setItems(list);
  tableloc.refresh();
    }

    @FXML
    private void selectedl(MouseEvent event) {
         index=tableloc.getSelectionModel().getSelectedIndex();
        if (index<= -1)
        {return; } 
        tflmodel.setText(modela.getCellData(index).toString());
                tflprix.setText(prixa.getCellData(index).toString());
                tfldateloc.setText(dateloca.getCellData(index).toString());
                tflduree.setText(dureea.getCellData(index).toString());
    }

    @FXML
    private void supprimerloc(ActionEvent event) {
            LocationServices location = new LocationServices();
        Location ls = new Location();
        ls = tableloc.getSelectionModel().getSelectedItem();
                

        location.SupprimerLocation(ls.getModel());
    }

    @FXML
    private void modifierloc(ActionEvent event) {
         String model=tflmodel.getText();
        String prix=tflprix.getText();
        String dateloc=tfldateloc.getText();

        String duree=tflduree.getText();
        LocationServices sp = new LocationServices();
        Location c = new Location();
        c.setModel(model);
 c.setPrix(prix);
        c.setDateloc(dateloc);

        c.setDuree(duree);
                c.setModel(model);

        
        sp.modifierL(c);
    }

    @FXML
    private void retour(ActionEvent event)throws IOException 
    {
        
              Parent root = FXMLLoader.load(getClass().getResource("/projetpidev4/FXMLDocument.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
     public boolean verifUserChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

   
       
        tflmodel.setStyle(styledefault);
        tflprix.setStyle(styledefault);
        tfldateloc.setStyle(styledefault);
        tflduree.setStyle(styledefault);
     
       
 

        if (tflmodel.getText().equals("")) {
            tflmodel.setStyle(style);
            verif = 1;
        }
       
        if ( tflprix.getText().equals("")) {
             tflprix.setStyle(style);
            verif = 1;
        }
         
        if (tfldateloc.getText().equals("")) {
            tfldateloc.setStyle(style);
            verif = 1;
        }
       
        if (tflduree.getText().equals("")) {
            tflduree.setStyle(style);
            verif = 1;
        }
       
        if (verif == 0) {
            return true;
        }
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Verifier les champs");
        al.setHeaderText(null);
        al.show() ; 
        
        return false;
    }
    public boolean controlSaisie(){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

        if(checkIfStringContainsNumber(tflmodel.getText())){
            alert.setContentText("Le nom ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
                if(checkIfStringContainsNumber2(tflprix.getText())){
            alert.setContentText("Le prenom ne doit pas contenir des caracteres");
            alert.showAndWait();
            return false;
        }
                  if(checkIfStringContainsNumber(tfldateloc.getText())){
            alert.setContentText("Le prenom ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
                  if(checkIfStringContainsNumber2(tflduree.getText())){
            alert.setContentText("Le prenom ne doit pas contenir des caracteres");
            alert.showAndWait();
            return false;
        }
             

        
        return true;
    }
    
    public boolean checkIfNumber(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

       return true;
    }
    
    public boolean checkIfStringContainsNumber(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("0") || str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4") || str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") || str.contains("9")){
                return true;
            }
        }
        return false;
    }
    public boolean checkIfStringContainsNumber2(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("a") || str.contains("b") || str.contains("c") || str.contains("d") || str.contains("e") || str.contains("f") || str.contains("g") || str.contains("h") || str.contains("i") || str.contains("j")|| str.contains("k")|| str.contains("l")|| str.contains("m")|| str.contains("n")|| str.contains("o")|| str.contains("p")|| str.contains("q")|| str.contains("r")|| str.contains("s")|| str.contains("t")|| str.contains("u")|| str.contains("v")|| str.contains("w")|| str.contains("y")|| str.contains("z")){
                return true;
            }
        }
        return false;
    }
    
    
    
}
