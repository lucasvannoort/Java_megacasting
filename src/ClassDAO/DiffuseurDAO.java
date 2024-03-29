/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassDAO;

import Class.Diffuseur;
import Class.Information;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author
 * Alexis
 */
public class DiffuseurDAO {
    public static void CreerDiffuseur(Connection cnx, Diffuseur dif) throws Exception{
            Diffuseur d = TrouverDiffuseurNom(cnx, dif.getNom_diffuseur());
        if(d != null){
            throw new Exception(dif.getNom_diffuseur() + " existe déjà !");
        }
        
        InformationDAO.CreerInformation(cnx, dif.getInformation());
        
        Statement stmt = null;
        
        try {
            stmt = cnx.createStatement();
            stmt.executeUpdate("INSERT INTO diffuseur (nom_diffuseur, id_information) "
                    + "VALUES ('" + dif.getNom_diffuseur() + "'"                 
                    + ", " + dif.getInformation().getId_information()
                    + ")");
            ResultSet rs = stmt.executeQuery("SELECT MAX(id_diffuseur) FROM diffuseur");
            if (rs.next()){
                long id = rs.getLong(1);
                dif.setId_diffuseur((int) id);
            }
        } catch (Exception ex) {
         ex.printStackTrace();
        } finally {
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                }
            }
        }
                 
           
    }
    
    public static void ModifierDiffuseur(Connection cnx, Diffuseur dif) throws SQLException{
         
        Statement stmt = null;
        
         try {
             stmt =  cnx.createStatement();
             stmt.executeUpdate("UPDATE diffuseur"
                    + " SET nom_diffuseur ='" + dif.getNom_diffuseur()+ "'"
                    + " WHERE id_diffuseur =" + dif.getId_diffuseur() + ";");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }finally{
             if (stmt != null){
                 stmt.close();
             }
         }
    }
    
    static  public void SupprimerDiffuseur(Connection cnx, Diffuseur dif) throws SQLException{
         Statement stmt = null;
        
         try {
             stmt =  cnx.createStatement();
             
             stmt.executeUpdate("DELETE FROM information"
                    + " WHERE id_information = " + dif.getInformation().getId_information() + ";");
             stmt.executeUpdate("DELETE FROM diffuseur"
                    + " WHERE id_diffuseur = " + dif.getId_diffuseur());
         } catch (SQLException ex) {
             ex.printStackTrace();
         }finally {
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                }
            }
        }
    }
    
    
     public static ArrayList<Diffuseur> ListerDiffuseur(Connection cnx){
        ArrayList<Diffuseur> liste = new ArrayList<>(); 
        Statement stmt = null;
        
         try {
             stmt =  cnx.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id_diffuseur, nom_diffuseur, id_information"
                    + " FROM diffuseur");
            
           while(rs.next()){
               long id_diffuseur = rs.getInt(1);
               String nom_diffuseur = rs.getString(2);                 
               long id_information = rs.getInt(3);
               
               Information inf = InformationDAO.TrouverInformationId(cnx, id_information);
               
               Diffuseur dif = new Diffuseur(nom_diffuseur, inf);         
               dif.setId_diffuseur((int)id_diffuseur);
               
               liste.add(dif);
               
               
               
           }
         } catch (Exception e) {
             e.printStackTrace();
         }finally {
            
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                }
            }                  
        }
        return liste;
     
       
    }

    public static Diffuseur TrouverDiffuseurId(Connection cnx, long id_diffuseur) {
        Diffuseur dif = null;
        Statement stmt = null;
        
        try {
            
            stmt =  cnx.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_diffuseur, nom_diffuseur, id_information"
                    + " FROM diffuseur"
                   + " WHERE id_diffuseur ='" + id_diffuseur + "'");
            
         if (rs.next()){
             
               long id_information = rs.getLong(3);                           
               String nom_diffuseur = rs.getString(2);
                
               
               Information inf = InformationDAO.TrouverInformationId(cnx, id_information);
               
               dif = new Diffuseur(nom_diffuseur, inf);
               dif.setId_diffuseur(id_diffuseur);
               }
            
         
              
        } catch (Exception e) {
            
        }finally {
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                }
            }
        }
         
        return dif;
             
           }
    public static Diffuseur TrouverDiffuseurNom(Connection cnx, String nom_diffuseur) {
        Diffuseur dif = null;
        Statement stmt = null;
        
        try {
            
            stmt =  cnx.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_diffuseur, nom_diffuseur, id_information"
                    + " FROM diffuseur"
                   + " WHERE nom_diffuseur ='" + nom_diffuseur + "'");
            
         if (rs.next()){
             
               long id_information = rs.getLong(3);                           
               long id_diffuseur = rs.getLong(1);
                
               
               Information inf = InformationDAO.TrouverInformationId(cnx, id_information);
               
               dif = new Diffuseur(nom_diffuseur, inf);
               dif.setId_diffuseur((int)id_diffuseur);
               
               
               }
            
         
              
        } catch (Exception e) {
            
        }finally {
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                }
            }
        }
         
        return dif;
             
           }
    
}
