/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Class.Offre;
import ClassDAO.OffreDAO;
import java.awt.CardLayout;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alexis
 */
public class NewAnnonceForm extends javax.swing.JPanel {

    private MaFenetrePrincipale mafenetreprincipal;
    public static List<Offre> offres;
    
    public NewAnnonceForm(MaFenetrePrincipale mafenetreprincipal) {
        this.mafenetreprincipal = mafenetreprincipal;
        initComponents(); 
        ListerNewAnnonce();
                
    }
     
    /**
     * Creates new form NewAnnonceForm
     */
    public NewAnnonceForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_offre = new javax.swing.JTable();
        jButton_Details = new javax.swing.JButton();
        jButton_retour = new javax.swing.JButton();

        jTable_offre.setForeground(new java.awt.Color(51, 51, 51));
        jTable_offre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identifiant ", "Intitulé", "Référence", "Date", "Durée", "Date de début", "Localisation", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable_offre);

        jButton_Details.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Details.setForeground(new java.awt.Color(51, 51, 51));
        jButton_Details.setText("Details de l'annonce");
        jButton_Details.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DetailsActionPerformed(evt);
            }
        });

        jButton_retour.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_retour.setForeground(new java.awt.Color(51, 51, 51));
        jButton_retour.setText("Retour");
        jButton_retour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_retourActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_retour, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jButton_Details, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Details, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_retour, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_DetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DetailsActionPerformed
      
        int index = jTable_offre.getSelectedRow();
        Offre off = offres.get(index);
        
        CardLayout c1 = (CardLayout) MaFenetrePrincipale.Panel_principal.getLayout();
        c1.show(MaFenetrePrincipale.Panel_principal, "validationCard");
        
        Component[] com = MaFenetrePrincipale.Panel_principal.getComponents();
        for (Component c : com) {
            if(c instanceof ValidationForm) {
                ValidationForm valid = (ValidationForm)c;
                valid.test(off);
            }
        }
    }//GEN-LAST:event_jButton_DetailsActionPerformed

    public static void ListerNewAnnonce(){
        
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1/megacasting";
        String utilisateur = "root";
        String password = "";         
        
        int i = 0;
        Connection cnx = null;

       try {
            cnx = DriverManager.getConnection(url, utilisateur, password);
            
            System.out.println("Connexion réussi !"); 
            
             
            DefaultTableModel model = (DefaultTableModel) jTable_offre.getModel();
            model.setRowCount(0);
            OffreDAO offreDAO = new OffreDAO();
            offres = OffreDAO.ListerOffreNonValide(cnx);

            for (Offre o : offres){
                model.addRow(new Object[] {
                    o.getId_offre(),
                    o.getInt_offre(),
                    o.getRef_offre(),
                    o.getDate_offre(),
                    o.getDuree_offre(),
                    o.getDate_deb_offre(),
                    o.getLoc_offre(),
                    o.getDesc_offre(),
                    o.getAnnonceur().getId_anonceur(),
                    o.getContrat().getId_contrat(),
                    o.getMetier().getId_metier(),
                    o.getDomaine().getId_domaine(),
                    o.getMedia().getId_media()
               
                });

            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{

            if(cnx != null){
                try {
                    cnx.close();
                } catch (SQLException ex) {
                }
            }
        }

        System.out.println("Connexion fermé !");

    }
    
    private void jButton_retourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_retourActionPerformed
        CardLayout c1 = (CardLayout) MaFenetrePrincipale.Panel_principal.getLayout();
        c1.show(MaFenetrePrincipale.Panel_principal, "redirectionCard");
    }//GEN-LAST:event_jButton_retourActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Details;
    private javax.swing.JButton jButton_retour;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable_offre;
    // End of variables declaration//GEN-END:variables
}