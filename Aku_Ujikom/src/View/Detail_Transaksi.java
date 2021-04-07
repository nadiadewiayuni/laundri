/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author lenovo
 */
public class Detail_Transaksi extends javax.swing.JFrame {
Connection conn;
    Statement stm;
    ResultSet rs;
    /**
     * Creates new form Detail_Transaksi
     */
    public Detail_Transaksi() {
        initComponents();
    siapIsi(false);
        tombolNormal();
        tampildaftardetail();
        id_transaksi();
        id_paket();
    }
 public Connection setKoneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/laundry_db","root","");
            stm=conn.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Koneksi Gagal :" +e);
        }
       return conn; 
    }
    
    private void auto_number(){
        try {
            setKoneksi();
            String sql="select right (id,2)+1 from tb_detail_transaksi ";
            ResultSet rs=stm.executeQuery(sql);
            if(rs.next()){
                rs.last();
                String no=rs.getString(1);
                while (no.length()<3){
                    no="0"+no;
                    Id_Detail.setText("1"+no);}
                }
            else
            {
                Id_Detail.setText("1001"); 
        }
        } catch (Exception e) 
        {
        } 
    }
    
    private void id_transaksi(){
        String sql = "SELECT * FROM tb_transaksi";
        try{
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery(sql);
            while(rs.next()){
                Id_Transaksi.addItem(rs.getString("id_transaksi"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void id_paket(){
        String sql = "SELECT * FROM tb_paket";
        try{
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery(sql);
            while(rs.next()){
                Id_paket.addItem(rs.getString("id_paket"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void siapIsi(boolean a){
        Id_Detail.setEnabled(a);
        Id_Transaksi.setEnabled(a);
        Id_paket.setEnabled(a);
        Quantity.setEnabled(a);
        Keterangan.setEnabled(a);
    }
    
    private void tombolNormal(){
        tambah.setEnabled(true);
        simpan.setEnabled(false);
        hapus.setEnabled(false);
        edit.setEnabled(false);
        cari.setEnabled(true);
        
    }
    
    private void bersih(){
        Id_Detail.setText("");
        Id_Transaksi.setSelectedItem("");     
        Id_paket.setSelectedItem("");
        Quantity.setText("");
        Keterangan.setText("");
    }
    
    private void simpan(){
        try{
            setKoneksi();
            String sql="insert into tb_detail_transaksi values('"+Id_Detail.getText()
                    +"','"+Id_Transaksi.getSelectedItem()
                    +"','"+Id_paket.getSelectedItem()
                    +"','"+Quantity.getText()
                    +"','"+Keterangan.getText() +"')";
            stm.executeUpdate(sql); 
            JOptionPane.showMessageDialog(null,"SIMPAN DATA DETAIL TRANSAKSI BERHASIL :)");
            }
            catch (Exception e) {
        }
        tampildaftardetail();
       
    }
    
    
    
    private void perbarui(){
        try{
            setKoneksi();
            String sql="update tb_detail_transaksi set "+"',id_transaksi='"+Id_Transaksi.getSelectedItem()
                    +"',id_paket='"+Id_paket.getSelectedItem()
                    + "qty='"+Quantity.getText()
                    +"',keterangan='"+Keterangan.getText()
                    +"' where id='"+Id_Detail.getText()+"'";
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"EDIT DATA DETAIL TRANSAKSI BERHASIL","",JOptionPane.INFORMATION_MESSAGE);
        } 
        catch(Exception e){
        }
        tampildaftardetail();
        
    }
    
    private void hapus(){
        try{
            String sql="delete from tb_detail_transaksi where id='"+ Id_Detail.getText() +"'";
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "DATA DETAIL TRANSAKSI TELAH DIHAPUS");
            }
            catch (Exception e) {
            }
        tampildaftardetail();
    }
    
    public void tampildaftardetail(){
        Object header[]={"Id Detail","Id Transaksi","Id Paket","Quantity","Keterangan"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tb_detail.setModel(data);
        setKoneksi();
        String sql="select*from tb_detail_transaksi";
        try {
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
                String kolom4=rs.getString(4);
                String kolom5=rs.getString(5);
                
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Id_Detail = new javax.swing.JTextField();
        Quantity = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Keterangan = new javax.swing.JTextArea();
        Id_Transaksi = new javax.swing.JComboBox<>();
        Id_paket = new javax.swing.JComboBox<>();
        tambah = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        cari = new javax.swing.JButton();
        pencarian = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_detail = new javax.swing.JTable();
        kembali = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Id Detail Transaksi ");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Id Transaksi");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Id Paket");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Quantity");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Keterangan");

        Id_Detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Id_DetailActionPerformed(evt);
            }
        });

        Quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuantityActionPerformed(evt);
            }
        });

        Keterangan.setColumns(20);
        Keterangan.setRows(5);
        jScrollPane1.setViewportView(Keterangan);

        Id_Transaksi.setBackground(new java.awt.Color(255, 204, 204));
        Id_Transaksi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Id_Transaksi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- PILIH -" }));

        Id_paket.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Id_paket.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- PILIH -" }));

        tambah.setBackground(new java.awt.Color(102, 102, 102));
        tambah.setText("Tambah");
        tambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        simpan.setBackground(new java.awt.Color(102, 102, 102));
        simpan.setText("Simpan");
        simpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        edit.setBackground(new java.awt.Color(102, 102, 102));
        edit.setText("Edit");
        edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        hapus.setBackground(new java.awt.Color(102, 102, 102));
        hapus.setText("Hapus");
        hapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        cari.setBackground(new java.awt.Color(102, 102, 102));
        cari.setText("Cari");
        cari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        pencarian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pencarianKeyPressed(evt);
            }
        });

        tb_detail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tb_detail);

        kembali.setBackground(new java.awt.Color(102, 102, 102));
        kembali.setText("Kembali");
        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Quantity)
                    .addComponent(Id_Detail)
                    .addComponent(Id_Transaksi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Id_paket, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(91, 91, 91))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(tambah)
                        .addGap(79, 79, 79)
                        .addComponent(simpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(hapus)
                        .addGap(71, 71, 71)
                        .addComponent(kembali))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(38, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(pencarian)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Id_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Id_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Id_paket, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Id_DetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Id_DetailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Id_DetailActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here:
        if(tambah.getText().equalsIgnoreCase("tambah")){
            tambah.setText("Refresh");
            bersih();
            siapIsi(true);
            auto_number();

            Id_Detail.setEnabled(true);
            Quantity.setEnabled(true);
            tambah.setEnabled(true);
            simpan.setEnabled(true);
            hapus.setEnabled(false);
            edit.setEnabled(false);
            cari.setEnabled(false);

        } else{
            tambah.setText("Tambah");
            bersih();
            siapIsi(false);
            tombolNormal();
            tampildaftardetail();
        }
    }//GEN-LAST:event_tambahActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
        if(Id_Detail.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Id Detail Harus di Isi");
        }if(Id_Transaksi.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Id Transaksi Harus di Pilih");
        }if(Id_paket.getSelectedIndex()== 0){
            JOptionPane.showMessageDialog(null, "Id Paket di Pilih");
        }if(Quantity.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Quantity Harus di Isi");
        }if(Keterangan.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Keterangan Harus di Isi");
        }else{
            if(tambah.getText().equalsIgnoreCase("Refresh")){
                if(tambah.getText().equalsIgnoreCase("Refresh")){
                    simpan();
                } else{
                    JOptionPane.showMessageDialog(null, "SIMPAN DATA GAGAL, PERIKSA KEMBALI :( ","",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if(edit.getText().equalsIgnoreCase("batal")){
                if(edit.getText().equalsIgnoreCase("batal")){
                    perbarui();
                } else{
                    JOptionPane.showMessageDialog(null, "EDIT DATA GAGAL, PERIKSA KEMBALI :( ","",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            bersih();
            siapIsi(false);
            tambah.setText("Tambah");
            edit.setText("Edit");
            tombolNormal();
        }
    }//GEN-LAST:event_simpanActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        if(edit.getText().equalsIgnoreCase("edit")){
            edit.setText("Batal");
            siapIsi(true);
            Id_Detail.setEnabled(false);
            tambah.setEnabled(false);
            simpan.setEnabled(true);
            hapus.setEnabled(false);
            edit.setEnabled(true);
            cari.setEnabled(false);
        } else{
            edit.setText("Edit");
            bersih();
            siapIsi(false);
            tombolNormal();
        }
    }//GEN-LAST:event_editActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
        int pesan=JOptionPane.showConfirmDialog(null, "YAKIN DATA AKAN DIHAPUS ?","Konfirmasi",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(pesan==JOptionPane.YES_OPTION){
            if(pesan==JOptionPane.YES_OPTION){
                hapus();
                bersih();
                siapIsi(false);
                tombolNormal();
            } else{
                JOptionPane.showMessageDialog(null, "HAPUS DATA GAGAL :(");
            }
        }
    }//GEN-LAST:event_hapusActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
        Object header[]={"Id Detail Transaksi", "Id Transaksi", "Id Paket", "Quantity", "Keterangan"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tb_detail.setModel(data);
        String sql="Select * from tb_detail_transaksi where id like '%" + pencarian.getText() + "%'" + "or id_transaksi like '%" +pencarian.getText()+"%'";
        try {
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
                String kolom4=rs.getString(4);
                String kolom5=rs.getString(5);

                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5};
                data.addRow(kolom);
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_cariActionPerformed

    private void pencarianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pencarianKeyPressed
        // TODO add your handling code here:
        Object header[]={"Id Detail Transaksi", "Id Transaksi", "Id Paket", "Quantity", "Keterangan"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tb_detail.setModel(data);
        String sql="Select * from tb_detail_transaksi where id like '%" + pencarian.getText() + "%'" + "or id_transaksi like '%" +pencarian.getText()+"%'";
        try {
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
                String kolom4=rs.getString(4);
                String kolom5=rs.getString(5);

                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5};
                data.addRow(kolom);
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_pencarianKeyPressed

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        // TODO add your handling code here:
        new home_admin().setVisible(true);
        dispose();
    }//GEN-LAST:event_kembaliActionPerformed

    private void QuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QuantityActionPerformed

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
            java.util.logging.Logger.getLogger(Detail_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Detail_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Detail_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Detail_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Detail_Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Id_Detail;
    private javax.swing.JComboBox<String> Id_Transaksi;
    private javax.swing.JComboBox<String> Id_paket;
    private javax.swing.JTextArea Keterangan;
    private javax.swing.JTextField Quantity;
    private javax.swing.JButton cari;
    private javax.swing.JButton edit;
    private javax.swing.JButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton kembali;
    private javax.swing.JTextField pencarian;
    private javax.swing.JButton simpan;
    private javax.swing.JButton tambah;
    private javax.swing.JTable tb_detail;
    // End of variables declaration//GEN-END:variables
}
