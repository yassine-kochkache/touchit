/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import models.Chauffeur;
import utils.DataSource;


/**
 *
 * @author yassi
 */
public class ChauffeurServices implements IChauffeurServices{
    
 Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void AjouterChauffeur(Chauffeur c) {
        try {
             String req = "INSERT INTO chauffeur (nom,prenom ,sexe, numtel ) VALUES ('" + c.getNom() + "','" + c.getPrenom() + "','" + c.getSexe() + "','" + c.getNumtel() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("chauffeur ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    @Override
          public void SupprimerChauffeur(String nom){
       
//           try {
//            PreparedStatement statement = cnx.prepareStatement(
//    "DELETE FROM trip WHERE offre = ?"
//);
//   statement.setString(1, p.getOffre());
//    statement.executeUpdate();
////            
//          
//            System.out.println("Trip supprimé !");
//        } catch (SQLException ex) {
//           System.out.println(ex.getMessage());
// //      }
       String requete = "delete from chauffeur where nom=?";
        try {
          PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1,nom);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           Logger.getLogger(ChauffeurServices.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
    }
    

@Override
   public void ModifierChauffeur(Chauffeur c, String nom) {
        	 
		 try{
            
        String req2 = "UPDATE `chauffeur` SET `prenom`=?,`sexe`=?,`numtel`=? WHERE nom=? ";
                PreparedStatement st = cnx.prepareStatement(req2);
		
           
            st.setString(1, c.getPrenom());
            st.setString(2,c.getSexe());
             st.setString(3,c.getNumtel());
            st.setString(4,nom);
                st.executeUpdate();
                
                 System.out.println("Covoiturage mis à jour avec succès !");
                 System.out.println(c.toString());
        }catch (SQLException ex) {
            System.out.println(c.toString());
            System.out.println("erreur lors de la modification " + ex.getMessage());
            Logger.getLogger(ChauffeurServices.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
//        try {
//            String req = "UPDATE trip SET  ville_dest =? img=? ,description=? ,periode=? WHERE offre=?" ;
//           PreparedStatement pst = cnx.prepareStatement(req);
////           
//                       pst.setString(1, p.getVille_dest());
//
//            pst.setString(2, p.getImg());
//            pst.setString(3,p.getDescription());
//             pst.setString(4, p.getOffre());
//                       pst.setString(5, p.getPeriode());
//
//            pst.executeUpdate();
//            System.out.println("Trip modifié !");
//        } catch (SQLException ex) {
//           System.out.println(ex.getMessage());
//        }
//String sql = "UPDATE trip SET `ville_dest`=?,`img`=?,`description`=? ,`periode`=?  WHERE offre=" + p.getOffre();
//        PreparedStatement ste;
//        try {
//            ste = cnx.prepareStatement(sql);
//                        ste.setInt(1,p.getId_trip());
//
//            ste.setString(2,p.getVille_dest());
//            ste.setString(3, p.getImg());
//            ste.setString(4, p.getDescription());
//
//            ste.setString(5, p.getPeriode());
//
//
//            ste.executeUpdate();
//            int rowsUpdated = ste.executeUpdate();
//            if (rowsUpdated > 0) {
//                System.out.println("La modification de la classe a été éffectuée avec succès ");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TripServices.class.getName()).log(Level.SEVERE, null, ex);
//        }

    
        
    

    @Override
    public List<Chauffeur> afficher() {
        List<Chauffeur> list = new ArrayList<>();
        
        try {
            String req = "SELECT nom , prenom, sexe, numtel FROM chauffeur";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new Chauffeur( rs.getString("nom"),rs.getString("prenom"),rs.getString("sexe"),rs.getString("numtel")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
 

//    public void rechercher(String index){
//List<Trip> result = afficher().stream().filter(line -> index.equals(line.getOffre())).collect(Collectors.toList());
//                    System.out.println("----------");
//                    result.forEach(System.out::println);
////
//}
//   public void TrierParId (){
// TripServices sa = new TripServices();
//List<Trip> TrierParId = sa.afficher().stream().sorted(Comparator.comparing(Trip::getId_trip)).collect(Collectors.toList());
////                            TrierParId.forEach(System.out::println);
//}
@Override
   public int modifier (Chauffeur c){
String sq13="UPDATE `chauffeur` SET `nom`=?,`prenom`=?,`sexe`=?,`numtel`=? WHERE nom =?";
            
        try {
            PreparedStatement pst = cnx.prepareStatement(sq13);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getPrenom());
                        pst.setString(3, c.getSexe());

            pst.setString(4, c.getNumtel());
                        pst.setString(5, c.getNom());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ChauffeurServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
   }

}
