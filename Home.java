/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ucmerced_timetables;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author Jonathan
 */
public class Home extends javax.swing.JFrame {
    /*
     * This must be in the begining of all of your pages
     *!!!!look under contactActionPerformed to look how to use buttons!!!!!!
     */
    Connection conn = null;//allows us to connect to the database
    ResultSet rs = null;
    PreparedStatement ps = null;
    int userId;

    public Home(int userId) {//this stuff runs when the page loads
        this.userId = userId;
        initComponents();
        conn = connect.connect();
        Update_table();//updates the table to get all the favorites
        Update_Name();//changes the name on the page to the users name
        
    }
    public void Getting_userName(int userId){
        this.userId = userId;
    }
    private void Update_Name(){//this is changing the text field to the user's name
        try{
            String sql = "SELECT user_name FROM user WHERE user_id = " +userId+";";//sql statement
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                String i = rs.getString("user_name");
                name_field.setText(i);//setting the name_field to the user name, will change the text of that field
            }
        }
        catch(Exception e){//if the sql stament is wrong or errors it will throw exception
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();//always close these after every sql statment to prevent locks
                ps.close();
            }
            catch(Exception e){//if cannot close will throw exception
                JOptionPane.showMessageDialog(null, e);
            }
        }
            
    }
    
    private void Update_table(){//this is how you update a table
       try{
        
        String sql = "SELECT service_name,time_opens,time_close,time_week,time_day FROM ( SELECT fav_serviceid FROM fav WHERE fav_userid = "+ userId+ ") AS favorite,services, times WHERE favorite.fav_serviceid = time_serviceid  AND service_id = favorite.fav_serviceid  AND service_id = time_serviceid;";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        Fav_Table.setModel(DbUtils.resultSetToTableModel(rs));
       
       
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }finally {
            try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } } 
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
        Fav_Table = new javax.swing.JTable();
        name_field = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        buildings_list = new javax.swing.JComboBox();
        settings = new javax.swing.JButton();
        search_box = new javax.swing.JButton();
        log_out = new javax.swing.JButton();
        contact = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Fav_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Building", "Opening", "Closing", "Time of Day", "Day of Week"
            }
        ));
        jScrollPane1.setViewportView(Fav_Table);

        name_field.setText("jLabel2");

        jLabel1.setText("Welcome:");

        buildings_list.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        settings.setText("Settings");
        settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsActionPerformed(evt);
            }
        });

        search_box.setText("Search");
        search_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_boxActionPerformed(evt);
            }
        });

        log_out.setText("Log Out");
        log_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                log_outActionPerformed(evt);
            }
        });

        contact.setText("Contact Us");
        contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        jLabel2.setText("Home");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buildings_list, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(search_box)
                        .addGap(253, 253, 253))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(settings)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(contact)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(name_field)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(log_out)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(name_field)
                        .addComponent(log_out)
                        .addComponent(contact)
                        .addComponent(settings))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buildings_list, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_box))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
 * these next private voids are the actions that are performed when
 * user pushes a button
 */
    private void settingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsActionPerformed
        try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } 
        //buy x = new buy(Email);
        //x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_settingsActionPerformed

    private void contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactActionPerformed
        try{//this is to make sure before you move to next page, you let go of all resources
                conn.close();
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } 
         Contact_Us x = new Contact_Us(userId);//x is now a object of type of the page you want to go to
        x.setVisible(true);//sets the next page visibility to true
        this.dispose();//this makes it so the current page closes when you switch to the next page
    }//GEN-LAST:event_contactActionPerformed

    private void log_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_log_outActionPerformed
        try{
                conn.close();
                rs.close(); 
                ps.close(); }
        catch(Exception e) { } 
        Login x = new Login();
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_log_outActionPerformed

    private void search_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_boxActionPerformed
        Object selectedItemObj = buildings_list.getSelectedItem();
        if(selectedItemObj != null){
            String selectedItem = selectedItemObj.toString();
        }
        try{
            conn.close();
            rs.close();
            ps.close();
        }
        catch(Exception e){}
        //what ever its called
        //x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_search_boxActionPerformed
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Home().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Fav_Table;
    private javax.swing.JComboBox buildings_list;
    private javax.swing.JButton contact;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton log_out;
    private javax.swing.JLabel name_field;
    private javax.swing.JButton search_box;
    private javax.swing.JButton settings;
    // End of variables declaration//GEN-END:variables
}
