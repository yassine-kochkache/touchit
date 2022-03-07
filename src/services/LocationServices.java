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
import models.Location;
import utils.DataSource;


/**
 *
 * @author yassi
 */
public class LocationServices implements ILocationServices{
    
 Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void AjouterLocation(Location c) {
        try {
             String req = "INSERT INTO location (model,prix ,dateloc, duree ) VALUES ('" + c.getModel() + "','" + c.getPrix() + "','" + c.getDateloc() + "','" + c.getDuree() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("location ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    @Override
          public void SupprimerLocation(String model){
       
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
       String requete = "delete from location where model=?";
        try {
          PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1,model);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           Logger.getLogger(LocationServices.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
    }
    

@Override
   public void ModifierLocation(Location c, String model) {
        	 
		 try{
            
        String req2 = "UPDATE `location` SET `prix`=?,`dateloc`=?,`duree`=? WHERE model=? ";
                PreparedStatement st = cnx.prepareStatement(req2);
		
           
            st.setString(1, c.getPrix());
            st.setString(2,c.getDateloc());
             st.setString(3,c.getDuree());
            st.setString(4,model);
                st.executeUpdate();
                
                 System.out.println("location mis à jour avec succès !");
                 System.out.println(c.toString());
        }catch (SQLException ex) {
            System.out.println(c.toString());
            System.out.println("erreur lors de la modification " + ex.getMessage());
            Logger.getLogger(LocationServices.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<Location> afficher() {
        List<Location> list = new ArrayList<>();
        
        try {
            String req = "SELECT model , prix, dateloc, duree FROM location";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new Location( rs.getString("model"),rs.getString("prix"),rs.getString("dateloc"),rs.getString("duree")));
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
   public int modifierL (Location c){
String sq13="UPDATE `location` SET `model`=?,`prix`=?,`dateloc`=?,`duree`=? WHERE model =?";
            
        try {
            PreparedStatement pst = cnx.prepareStatement(sq13);
            pst.setString(1, c.getModel());
            pst.setString(2, c.getPrix());
                        pst.setString(3, c.getDateloc());

            pst.setString(4, c.getDuree());
                        pst.setString(5, c.getModel());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(LocationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
   }

}
