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
import java.util.List;
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
import models.Chauffeur;
import services.ChauffeurServices;
import utils.DataSource;


/**
 *
 * @author yassi
 */
public class FXMLDocumentController implements Initializable {
    public static int  nh = 0 ,nf=0 , ne = 0 ;  
            ObservableList<String> list = FXCollections.observableArrayList("Homme","Femme","Enfant");

    
    @FXML
    private Label label;
    @FXML
    private TableView<Chauffeur> tablechauff;
    @FXML
    private TableColumn<Chauffeur, String> noma;
    @FXML
    private TableColumn<Chauffeur, String> prenoma;
    @FXML
    private TableColumn<Chauffeur, String> sexea;
    @FXML
    private TableColumn<Chauffeur, String> numtela;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfsexe;
    @FXML
    private TextField tfnumtel;
        private Chauffeur c; 
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnretour;
    @FXML
    private Button btnclose;

    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    //ObservableList<Chauffeur>  list =  FXCollections.observableArrayList();
      int  index= -1; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<Chauffeur>  list =  FXCollections.observableArrayList();
          try { 
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT nom , prenom, sexe, numtel FROM chauffeur");
            while(rs.next())
            {
                list.add(new Chauffeur(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

       noma.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getNom());
        });
    //image_colonne.setCellValueFactory(new PropertyValueFactory<>("image"));

    prenoma.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getPrenom());
        });
    sexea.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getSexe());
        });
         numtela.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getNumtel());
        });
  
        // TODO
 tablechauff.setItems(list);
  tablechauff.refresh();
    }    
        // TODO
       

    @FXML
    private void ajouterchauffeur(ActionEvent event) {
                if(verifUserChamps() ){ 
                        if ( controlSaisie()){



         ChauffeurServices ts = new ChauffeurServices();
       ts.AjouterChauffeur(new Chauffeur(tfnom.getText(),tfprenom.getText(),tfsexe.getText(),tfnumtel.getText()));

       tablechauff.refresh();

       
                }}
    }

    @FXML
    private void supprimerchauffeur(ActionEvent event) throws SQLException {

                  ChauffeurServices chauffeur = new ChauffeurServices();
        Chauffeur ls = new Chauffeur();
        ls = tablechauff.getSelectionModel().getSelectedItem();
                

        chauffeur.SupprimerChauffeur(ls.getNom());
//         TripServices ts = new TripServices();
//
//        ts.SupprimerTrip(new Trip(tfoffre.getText()));
//        // JOptionPane.showMessageDialog(null, "Article supprimé !");
//        tabtrip.getItems().removeAll(tabtrip.getSelectionModel().getSelectedItem());
//          tabtrip.refresh();

    }
    

    @FXML
    private void modifierchauffeur(ActionEvent event) {
        String nom=tfnom.getText();
        String prenom=tfprenom.getText();
        String sexe=tfsexe.getText();

        String numtel=tfnumtel.getText();
        ChauffeurServices sp = new ChauffeurServices();
        Chauffeur c = new Chauffeur();
        c.setNom(nom);
 c.setPrenom(prenom);
        c.setSexe(sexe);

        c.setNumtel(numtel);
                c.setNom(nom);

        
        sp.modifier(c);
    }

    @FXML
    private void refresh(ActionEvent event) {
        ObservableList<Chauffeur>  list =  FXCollections.observableArrayList();
          try { 
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT nom , prenom, sexe, numtel FROM chauffeur");
            while(rs.next())
            {
                list.add(new Chauffeur(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

       noma.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getNom());
        });

      
   prenoma.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getPrenom());
        });

    sexea.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getSexe());
        });
    numtela.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getNumtel());
        });
         
        // TODO
 tablechauff.setItems(list);
  tablechauff.refresh();
    }

    @FXML
    private void Selected(MouseEvent event) {
         index=tablechauff.getSelectionModel().getSelectedIndex();
        if (index<= -1)
        {return; } 
        tfnom.setText(noma.getCellData(index).toString());
                tfprenom.setText(prenoma.getCellData(index).toString());
                tfsexe.setText(sexea.getCellData(index).toString());
                tfnumtel.setText(numtela.getCellData(index).toString());
    }

    @FXML
    private void retour(ActionEvent event) throws IOException 
    {
        
              Parent root = FXMLLoader.load(getClass().getResource("/projetpidev4/GestionLocation.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
    public boolean verifUserChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

   
       
        tfnom.setStyle(styledefault);
        tfprenom.setStyle(styledefault);
        tfsexe.setStyle(styledefault);
        tfnumtel.setStyle(styledefault);
     
       
 

        if (tfnom.getText().equals("")) {
            tfnom.setStyle(style);
            verif = 1;
        }
       
        if ( tfprenom.getText().equals("")) {
             tfprenom.setStyle(style);
            verif = 1;
        }
         
        if (tfsexe.getText().equals("")) {
            tfsexe.setStyle(style);
            verif = 1;
        }
       
        if (tfnumtel.getText().equals("")) {
            tfnumtel.setStyle(style);
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
         

        if(checkIfStringContainsNumber(tfnom.getText())){
            alert.setContentText("Le nom ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
                if(checkIfStringContainsNumber(tfprenom.getText())){
            alert.setContentText("Le prenom ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
                  if(checkIfStringContainsNumber(tfsexe.getText())){
            alert.setContentText("Le prenom ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
                  if(checkIfStringContainsNumber2(tfnumtel.getText())){
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

    @FXML
    private void calcul(ActionEvent event) throws IOException{
        ChauffeurServices ser= new ChauffeurServices();
        
        List<Chauffeur> li =ser.afficher(); 
        int i = 0; 
        
        for ( i=0 ; i<li.size();i++){
        if (li.get(i).getNom().equals("Homme"))
        
        {nh=nh+1;}  ;
        if (li.get(i).getNom().equals("Femme")){nf=nf+1 ; } 
        if (li.get(i).getNom().equals("Enfant")){ne=ne+1 ; }  }
        
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("Clientstat.fxml"));
         Parent root = loader.load();
        tfnom.getScene().setRoot(root);
        
        
        
        
        
                
        
  }

    @FXML
    private void closeapp(ActionEvent event) {
        Stage stage = (Stage) btnclose.getScene().getWindow();
        stage.close();
    }
    
    
    

}
