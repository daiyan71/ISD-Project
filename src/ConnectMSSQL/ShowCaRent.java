/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectMSSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author daiya
 */
public class ShowCaRent extends javax.swing.JFrame {

    /**
     * Creates new form ShowCaRent
     */
     public ShowCaRent() {
        initComponents();
     }
    public ShowCaRent(String userId) {
        initComponents();
        uid=userId;
        
        
        
        try{

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url="jdbc:sqlserver://localhost:1433;databaseName=PROJECT;user=sa;password=123456";
        Connection con = DriverManager.getConnection(url);
        String sql="Select max(RentPrice) as max,count(CarId) as count from Car";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        if(rs.next())
        {
            //max=rs.getString("max");
            count=rs.getString("count");
        }
        
        //high.setText(max);
        countLabel.setText(count);
        low.setText("0");
        con.close();
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        

        
        System.out.println("in show");
        ArrayList<List> listt=SPlist("ALL");
        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        Object[] row = new Object[5];
         for(int i=0;i<listt.size();i++)
        {
            row[0]=listt.get(i).getId();
            row[1]=listt.get(i).getBrand();
            row[2]=listt.get(i).getModel();
            row[3]=listt.get(i).getType();
            row[4]=listt.get(i).getPrice();
            model.addRow(row);
        }
    }
     public ArrayList<List> SPlist(String query)
     {
           ArrayList<List> splist=new ArrayList<>();
           
            try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=PROJECT;user=sa;password=123456";
            Connection con = DriverManager.getConnection(url);
            String sql,sql1;
            String h,l;
            if(high.getText().toString().isEmpty())
            {
                h=""+Integer.MAX_VALUE;
            }
            else{
                h=high.getText().toString();
            }
            if(low.getText().toString().isEmpty())
            {
                l="0";
                
            }
            else{
                l=low.getText().toString();
            }
            if(query.equals("ALL")==true)
            {
                 sql = "Select CarId,CarBrand,CarModel,CarType,RentPrice from Car inner join CarRent on Car.CarRentId=CarRent.CarRentId where Availability='YES' and RentPrice between "+l+" and "+h+" ";
                 sql1 = "Select count(CarId) as count from Car inner join CarRent on Car.CarRentId=CarRent.CarRentId where Availability='YES' and RentPrice between "+l+" and "+h+" ";
            }
             else{
              sql = "Select CarId,CarBrand,CarModel,CarType,RentPrice from Car inner join CarRent on Car.CarRentId=CarRent.CarRentId where Availability='Yes' and CarType ='"+query+"' and (RentPrice between "+l+" and "+h+")";
              sql1 = "Select count(CarId) as count from Car inner join CarRent on Car.CarRentId=CarRent.CarRentId where Availability='Yes' and CarType ='"+query+"' and (RentPrice between "+l+" and "+h+")";
            }
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            List list;
            while(rs.next()){
                list=new List(rs.getString("CarId"),rs.getString("CarBrand"),rs.getString("CarModel"),rs.getString("CarType"),rs.getString("RentPrice"));
                splist.add(list);
            }
            
            
            pst = con.prepareStatement(sql1);
            ResultSet rs1 = pst.executeQuery();
            
            if(rs1.next())
            {
                countLabel.setText(rs1.getString("count"));
            }
                
            
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
           
           
           return splist;
     }
     public ArrayList<List> SPlist1(String query)
     {
           ArrayList<List> splist=new ArrayList<>();
           
            try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=PROJECT;user=sa;password=123456";
            Connection con = DriverManager.getConnection(url);
            String sql,sql1;
            String h,l;
            if(high.getText().toString().isEmpty())
            {
                h=""+Integer.MAX_VALUE;
            }
            else{
                h=high.getText().toString();
            }
            if(low.getText().toString().isEmpty())
            {
                l="0";
                
            }
            else{
                l=low.getText().toString();
            }
            if(carType.getSelectedItem().equals("ALL")==true)
            {
                 sql = "Select CarId,CarBrand,CarModel,CarType,RentPrice from Car inner join CarRent on Car.CarRentId=CarRent.CarRentId where Availability='YES' and (CarBrand like '%"+query+"%' or CarModel like '%"+query+"%') and RentPrice between "+l+" and "+h+" ";
                 sql1 = "Select count(CarId) as count from Car inner join CarRent on Car.CarRentId=CarRent.CarRentId where Availability='YES' and (CarBrand like '%"+query+"%' or CarModel like '%"+query+"%') and RentPrice between "+l+" and "+h+" ";
            }
             else{
              sql = "Select CarId,CarBrand,CarModel,CarType,RentPrice from Car inner join CarRent on Car.CarRentId=CarRent.CarRentId where Availability='Yes' and (CarBrand like '%"+query+"%' or CarModel like '%"+query+"%') and CarType ='"+carType.getSelectedItem()+"' and (RentPrice between "+l+" and "+h+")";
              sql1 = "Select count(CarId) as count from Car inner join CarRent on Car.CarRentId=CarRent.CarRentId where Availability='Yes' and (CarBrand like '%"+query+"%' or CarModel like '%"+query+"%') and CarType ='"+carType.getSelectedItem()+"' and (RentPrice between "+l+" and "+h+")";
            }
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            List list;
            while(rs.next()){
                list=new List(rs.getString("CarId"),rs.getString("CarBrand"),rs.getString("CarModel"),rs.getString("CarType"),rs.getString("RentPrice"));
                splist.add(list);
            }
            pst = con.prepareStatement(sql1);
            ResultSet rs1 = pst.executeQuery();
            
            if(rs1.next())
            {
                countLabel.setText(rs1.getString("count"));
            }    
            
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
           
           
           return splist;
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        carType = new javax.swing.JComboBox<>();
        low = new javax.swing.JTextField();
        high = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        searchText = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        goButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        countLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 750));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 700));

        jTable1.setBackground(new java.awt.Color(245, 250, 255));
        jTable1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTable1.setForeground(new java.awt.Color(0, 51, 51));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Car Brand", "Car Model", "Car Type", "Rent Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setRowHeight(40);
        jTable1.setRowMargin(10);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        carType.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        carType.setForeground(new java.awt.Color(245, 250, 255));
        carType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL", "4 seat car", "7 seat car", "10 seat car" }));
        carType.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        carType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carTypeActionPerformed(evt);
            }
        });

        low.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        low.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lowActionPerformed(evt);
            }
        });

        high.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        high.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                highActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Highest Price");

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("Lowest Price");

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("Select Car Type");

        searchText.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N

        searchButton.setBackground(new java.awt.Color(179, 255, 217));
        searchButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        searchButton.setText("SEARCH");
        searchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        goButton.setBackground(new java.awt.Color(179, 255, 217));
        goButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        goButton.setText("GO");
        goButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 102));
        jLabel4.setText("Total result found:");

        countLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        countLabel.setForeground(new java.awt.Color(0, 0, 102));
        countLabel.setText("count");

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setText("Search by brand or model name");

        jButton2.setBackground(new java.awt.Color(40, 40, 40));
        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(240, 240, 240));
        jButton2.setText("HOME");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(40, 40, 40));
        jButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(240, 240, 240));
        jButton3.setText("ACCOUNT");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(40, 40, 40));
        jButton4.setText("CAR RENT");

        jButton5.setBackground(new java.awt.Color(40, 40, 40));
        jButton5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(240, 240, 240));
        jButton5.setText("CAR SERVICE");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(carType, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(high, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(countLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(goButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchButton))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(458, 458, 458)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(low, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(carType, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(low, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(high, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(goButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(countLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 973, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int index =jTable1.getSelectedRow();
        TableModel model=jTable1.getModel();
        String id=model.getValueAt(index,0).toString();
        
        setVisible(false);
        CarRentDetails s = new CarRentDetails(id,uid);
        s.setVisible(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void carTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carTypeActionPerformed
        // TODO add your handling code here:
        ArrayList<List> listt=SPlist(carType.getSelectedItem().toString());
        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
         for(int i=0;i<listt.size();i++)
        {
            row[0]=listt.get(i).getId();
            row[1]=listt.get(i).getBrand();
            row[2]=listt.get(i).getModel();
            row[3]=listt.get(i).getType();
            row[4]=listt.get(i).getPrice();
            model.addRow(row);
        }
        
    }//GEN-LAST:event_carTypeActionPerformed

    private void highActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_highActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_highActionPerformed

    private void lowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lowActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lowActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        if(!searchText.getText().isEmpty())
        {
        ArrayList<List> listt=SPlist1(searchText.getText());
        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
         for(int i=0;i<listt.size();i++)
        {
            row[0]=listt.get(i).getId();
            row[1]=listt.get(i).getBrand();
            row[2]=listt.get(i).getModel();
            row[3]=listt.get(i).getType();
            row[4]=listt.get(i).getPrice();
            model.addRow(row);
        }
        }
        
    }//GEN-LAST:event_searchButtonActionPerformed

    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goButtonActionPerformed
        // TODO add your handling code here:
        ArrayList<List> listt=SPlist(carType.getSelectedItem().toString());
        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
         for(int i=0;i<listt.size();i++)
        {
            row[0]=listt.get(i).getId();
            row[1]=listt.get(i).getBrand();
            row[2]=listt.get(i).getModel();
            row[3]=listt.get(i).getType();
            row[4]=listt.get(i).getPrice();
            model.addRow(row);
        }
    }//GEN-LAST:event_goButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        UserHome s = new UserHome(uid);
        s.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        UserAccount s = new UserAccount(uid);
        s.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        ShowCarService s = new ShowCarService(uid);
        s.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    String uid;
    String max,count;
    
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
            java.util.logging.Logger.getLogger(ShowCaRent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowCaRent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowCaRent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowCaRent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowCaRent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> carType;
    private javax.swing.JLabel countLabel;
    private javax.swing.JButton goButton;
    private javax.swing.JTextField high;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField low;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchText;
    // End of variables declaration//GEN-END:variables
}
