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
public class Orders extends javax.swing.JFrame {

    /**
     * Creates new form Orders
     */
    public Orders() {
        initComponents();
    }
    
    public Orders(String ID) {
        initComponents();
        
        uid=ID;
        ArrayList<List2> listt=SPlist2();
        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        Object[] row = new Object[6];
        if(listt.size()!=0)
        {
            for(int i=listt.size()-1;i>-1;i--)
           {
               row[0]=i+1;
               row[1]=listt.get(i).getsName();
               row[2]=listt.get(i).getSpName();
               row[3]=listt.get(i).getsRate();
               row[4]=listt.get(i).getDateTime();
               row[5]=listt.get(i).getOid();
               model.addRow(row);
           }  
        }
         
         
         
        ArrayList<List3> listt1=SPlist3();
        DefaultTableModel model1=(DefaultTableModel)jTable2.getModel();
        Object[] row1 = new Object[7];
        if(listt1.size()!=0)
        {
         for(int i=listt1.size()-1;i>-1;i--)
        {
            row1[0]=i+1;
            row1[1]=listt1.get(i).getCarBrand();
            row1[2]=listt1.get(i).getCarModel();
            row1[3]=listt1.get(i).getRentPrice();
            row1[4]=listt1.get(i).getSpName();
            row1[5]=listt1.get(i).getDatenTime();
            row1[6]=listt1.get(i).getOid();
            model1.addRow(row1);
        }
        }
    }
    
    
    public ArrayList<List2> SPlist2()
     {
           ArrayList<List2> splist2=new ArrayList<>();
           
            try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=PROJECT;user=sa;password=123456";
            Connection con = DriverManager.getConnection(url);
            String sql = "Select ServiceName,ServiceRate,ServiceProvider.FirstName+' '+ServiceProvider.LastName as spName,DateAndTime,PendingOrderTable.OrderId from PendingOrderTable inner join\n" +
                         " CarService on PendingOrderTable.CarServiceId=CarService.CarServiceId inner join ServiceTable on  ServiceTable.CarServiceId=CarService.CarServiceId \n" +
                         "inner join ServiceProvider on ServiceProvider.ServiceProviderId=CarService.ServiceProviderId where UserId=? order by OrderId";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, uid);
            ResultSet rs = pst.executeQuery();
            List2 list2;
            while(rs.next()){
                list2=new List2(rs.getString("ServiceName"),rs.getString("ServiceRate"),rs.getString("spName"),rs.getString("DateAndTime"),rs.getString("OrderId"));
                splist2.add(list2);
            }
            
            
            String sql1 = "Select sum(amount) as sum,count(OrderId) as tor from PendingOrderTable inner join\n" +
                         " CarService on PendingOrderTable.CarServiceId=CarService.CarServiceId inner join ServiceTable on  ServiceTable.CarServiceId=CarService.CarServiceId \n" +
                         "inner join ServiceProvider on ServiceProvider.ServiceProviderId=CarService.ServiceProviderId where UserId=?";
            pst = con.prepareStatement(sql1);
            pst.setString(1, uid);
            ResultSet rs1 = pst.executeQuery();
            if(rs1.next())
            {
                totalOrder1.setText(rs1.getString("tor"));
                totalAmount1.setText(rs1.getString("sum"));
                
            }
                
            
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
           
           
           return splist2;
     }
    
    
    
    
    
    public ArrayList<List3> SPlist3()
     {
           ArrayList<List3> splist3=new ArrayList<>();
           
            try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=PROJECT;user=sa;password=123456";
            Connection con = DriverManager.getConnection(url);
            String sql = "Select CarBrand,CarModel,RentPrice,ServiceProvider.FirstName+' '+ServiceProvider.LastName as spName,DateAndTime,PendingOrderTable.OrderId from PendingOrderTable \n" +
                            "inner join CarRent on PendingOrderTable.CarRentId=CarRent.CarRentId\n" +
                            "inner join Car on Car.CarRentId=CarRent.CarRentId\n" +
                            "inner join ServiceProvider on ServiceProvider.ServiceProviderId=CarRent.ServiceProviderId where UserId=? order by OrderId";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, uid);
            ResultSet rs = pst.executeQuery();
            List3 list3;
            while(rs.next()){
                list3=new List3(rs.getString("CarBrand"),rs.getString("CarModel"),rs.getString("RentPrice"),rs.getString("spName"),rs.getString("DateAndTime"),rs.getString("OrderId"));
                splist3.add(list3);
            }
            
            String sql1 = "Select sum(amount) as sum,count(OrderId) as oid from PendingOrderTable \n" +
                            "inner join CarRent on PendingOrderTable.CarRentId=CarRent.CarRentId\n" +
                            "inner join Car on Car.CarRentId=CarRent.CarRentId\n" +
                            "inner join ServiceProvider on ServiceProvider.ServiceProviderId=CarRent.ServiceProviderId where UserId=?";
            pst = con.prepareStatement(sql1);
            pst.setString(1, uid);
            ResultSet rs1 = pst.executeQuery();
            if(rs1.next())
            {
                totalOrder2.setText(rs1.getString("oid"));
                totalAmount2.setText(rs1.getString("sum"));
                
            }
                
            
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
           
           
           return splist3;
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        totalOrder1 = new javax.swing.JLabel();
        totalAmount1 = new javax.swing.JLabel();
        totalOrder2 = new javax.swing.JLabel();
        totalAmount2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 100));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 1200));
        jPanel1.setVerifyInputWhenFocusTarget(false);

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("CAR SERVICE");

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBackground(new java.awt.Color(245, 250, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order No.", "Service Name", "Service Provider Name", "Amount charged", "Date and time", "OrderId"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(30);
        jTable1.setRowMargin(8);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(5).setMinWidth(0);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("CAR RENT");

        jTable2.setBackground(new java.awt.Color(245, 250, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order No.", "Car Brand", "Car Model", "Rent Price", "Renter Name", "Date and Time", "OrderId"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setRowHeight(30);
        jTable2.setRowMargin(8);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(6).setMinWidth(0);
            jTable2.getColumnModel().getColumn(6).setPreferredWidth(0);
            jTable2.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        totalOrder1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        totalOrder1.setForeground(new java.awt.Color(0, 0, 102));
        totalOrder1.setText("totalOrder");

        totalAmount1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        totalAmount1.setForeground(new java.awt.Color(0, 0, 102));
        totalAmount1.setText("totalAmount");

        totalOrder2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        totalOrder2.setForeground(new java.awt.Color(0, 0, 102));
        totalOrder2.setText("jLabel5");

        totalAmount2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        totalAmount2.setForeground(new java.awt.Color(0, 0, 102));
        totalAmount2.setText("jLabel6");

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setText("Total Order Pending:");

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setText("Total Amount :");

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setText("Total Order Pending    :");

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 102));
        jLabel8.setText("Total Amount :");

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
        jButton4.setText("PENDING ORDERS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(40, 40, 40));
        jButton5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(240, 240, 240));
        jButton5.setText("RECEIVED ORDERS");
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
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(514, 514, 514)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(totalAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalOrder1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(480, 480, 480))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(totalOrder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(totalAmount2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(448, 448, 448))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(302, 302, 302))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalOrder1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalOrder2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalAmount2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1250, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        UserAccount s = new UserAccount(uid);
        s.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        UserHome s = new UserHome(uid);
        s.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        Orders2 s = new Orders2(uid);
        s.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        
        int index =jTable2.getSelectedRow();
            TableModel model=jTable2.getModel();
            String id=model.getValueAt(index,6).toString();


            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Are sure you want cancel the orderd?", "Title on Box", dialogButton);
            if(dialogResult == 0) {
             // System.out.println("Yes option");
             try{


                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    String url="jdbc:sqlserver://localhost:1433;databaseName=PROJECT;user=sa;password=123456";
                    Connection con = DriverManager.getConnection(url);
                    PreparedStatement pst ;
                    String sql = "delete from [PROJECT].[dbo].[PendingOrderTable] where OrderId=?";
                    pst = con.prepareStatement(sql);
                    pst.setInt(1, Integer.parseInt(id));
                    pst.executeQuery();

                    con.close();

                }
                catch(Exception e){
                   // JOptionPane.showMessageDialog(null, e);
                   setVisible(false);
                   Orders s = new Orders(uid);
                   s.setVisible(true);
                }

            } else {
              System.out.println("No Option");
            }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int index =jTable1.getSelectedRow();
            TableModel model=jTable1.getModel();
            String id=model.getValueAt(index,5).toString();
            
            

            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Are sure you want cancel the orderd?", "Title on Box", dialogButton);
            if(dialogResult == 0) {
             // System.out.println("Yes option");
             try{


                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    String url="jdbc:sqlserver://localhost:1433;databaseName=PROJECT;user=sa;password=123456";
                    Connection con = DriverManager.getConnection(url);
                    PreparedStatement pst ;
                    String sql = "delete from [PROJECT].[dbo].[PendingOrderTable] where OrderId=?";
                    pst = con.prepareStatement(sql);
                    pst.setInt(1, Integer.parseInt(id));
                    pst.executeQuery();

                    con.close();

                }
                catch(Exception e){
                   // JOptionPane.showMessageDialog(null, e);
                   setVisible(false);
                   Orders s = new Orders(uid);
                   s.setVisible(true);
                }

            } else {
              System.out.println("No Option");
            }
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */
    String uid;
    String to1,to2,tam1,tam2;
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
            java.util.logging.Logger.getLogger(Orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Orders().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel totalAmount1;
    private javax.swing.JLabel totalAmount2;
    private javax.swing.JLabel totalOrder1;
    private javax.swing.JLabel totalOrder2;
    // End of variables declaration//GEN-END:variables
}
