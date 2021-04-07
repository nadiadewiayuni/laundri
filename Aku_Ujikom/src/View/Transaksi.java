/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author lenovo
 */
public class Transaksi extends javax.swing.JFrame {
Connection conn;
    Statement stm;
    ResultSet rs;
    /**
     * Creates new form Transaksi
     */
    public Transaksi() {
        initComponents();
    siapIsi(false);
        tombolNormal();
        tampildaftartransaksi();
        id_outlet();
        id_member();
        id_user();
        nama_paket();
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
            String sql="select right (id_transaksi,2)+1 from tb_transaksi ";
            ResultSet rs=stm.executeQuery(sql);
            if(rs.next()){
                rs.last();
                String no=rs.getString(1);
                while (no.length()<3){
                    no="0"+no;
                    idtra.setText("KTR"+no);}
                }
            else
            {
                idtra.setText("KTR001"); 
        }
        } catch (Exception e) 
        {
        } 
    }
    
    private void id_outlet(){
        String sql = "SELECT * FROM tb_outlet";
        try{
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery(sql);
            while(rs.next()){
                idout.addItem(rs.getString("id_outlet"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void id_member(){
        String sql = "SELECT * FROM tb_member";
        try{
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery(sql);
            while(rs.next()){
                idmem.addItem(rs.getString("id_member"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void id_user(){
        String sql = "SELECT * FROM tb_user";
        try{
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery(sql);
            while(rs.next()){
                Id_user.addItem(rs.getString("id_user"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void nama_paket(){
        String sql = "SELECT * FROM tb_paket";
        try{
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery(sql);
            while(rs.next()){
                nama_paket.addItem(rs.getString("nama_paket"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void siapIsi(boolean a){
        idtra.setEnabled(a);
        idout.setEnabled(a);
        koin.setEnabled(a);
        idmem.setEnabled(a);
        tag.setEnabled(a);
        batas.setEnabled(a);
        tglbayar.setEnabled(a);
        jComboBox1.setEnabled(a);
        tambahan.setEnabled(a);
        dis.setEnabled(a);
        pajak.setEnabled(a);
        status.setEnabled(a);
        dibayar.setEnabled(a);
        Id_user.setEnabled(a);
        nama_paket.setEnabled(a);
        quantity.setEnabled(a);
        harga.setEnabled(a);
        total_bayar.setEnabled(a);
        nominal_bayar.setEnabled(a);
        kembaliab.setEnabled(a);
    }
    
    private void tombolNormal(){
        tambah.setEnabled(true);
        simpan.setEnabled(false);
        hapus.setEnabled(false);
        edit.setEnabled(false);
        cari.setEnabled(true);
    }
   
    private void bersih(){
        idtra.setText("");
        idout.setSelectedItem("- PILIH -");
        koin.setText("");
        idmem.setSelectedItem("- PILIH -");
        jComboBox1.setSelectedItem("- PILIH -");
        tambahan.setText("");
        dis.setText("");
        pajak.setText("");
        status.setSelectedItem("- PILIH -");
        dibayar.setSelectedItem("- PILIH -");
        Id_user.setSelectedItem("- PILIH -");
        quantity.setText("");
        nama_paket.setSelectedItem("- PILIH -");
        harga.setText("");
        total_bayar.setText("");
        nominal_bayar.setText("");
        kembaliab.setText("");
        idtra.requestFocus();
    }
    
    private void simpan(){
        SimpleDateFormat date;
            date = new SimpleDateFormat("yyyy-MM-dd");
            String tgl = date.format(tag.getDate());
            String batas_waktu = date.format(batas.getDate());
            String tgl_bayar = date.format(tglbayar.getDate());
        try{
            setKoneksi();
            String sql="insert into tb_transaksi values('"+idtra.getText()
                    +"','"+idout.getSelectedItem()
                    +"','"+koin.getText()
                    +"','"+idmem.getSelectedItem()
                    +"','"+tgl
                    +"','"+batas_waktu
                    +"','"+tgl_bayar
                    +"','"+tambahan.getText()
                    +"','"+dis.getText()
                    +"','"+pajak.getText()
                    +"','"+status.getSelectedItem()
                    +"','"+dibayar.getSelectedItem()
                    +"','"+Id_user.getSelectedItem() +"')";
            stm.executeUpdate(sql); 
            JOptionPane.showMessageDialog(null,"SIMPAN DATA TRANSAKSI BERHASIL :)");
            
            struk.append("\n BUKTI PEMBAYARAN TRANSAKSI HOMEYLAUNDRY \n\n " +
          "ID Transaksi \t\t: " + idtra.getText() 
        + "\n ID Outlet \t\t: " + idout.getSelectedItem()
        + "\n Kode Invoice \t\t: " + koin.getText()
        + "\n ID Member \t\t: " + idmem.getSelectedItem()
        + "\n ==========================================================="
        + "\n Tanggal Laundry \t: " + tag.getDate()
        + "\n Batas Waktu \t\t: " + batas.getDate()
        + "\n Tanggal Pembayaran \t: " + tglbayar.getDate()
        + "\n Metode Pengambilan \t: " + jComboBox1.getSelectedItem()
        + "\n Biaya Tambahan \t: " + tambahan.getText()
        + "\n ==========================================================="
        + "\n Status \t\t: " + status.getSelectedItem()
        + "\n ID User \t\t: " + Id_user.getSelectedItem()
        + "\n Pajak \t\t: " + pajak.getText()
        + "\n Quantity \t\t: " + quantity.getText()
        + "\n Diskon \t\t: " + dis.getText() +"%"
        + "\n ==========================================================="
        + "\n Nama Paket \t\t: " + nama_paket.getSelectedItem()
        + "\n Total Bayar \t\t: " + total_bayar.getText()
        + "\n Nominal Bayar \t\t: " + nominal_bayar.getText()
        + "\n Kembali \t\t: " + kembaliab.getText()
        + "\n ==========================================================="
        + "\n ====== Perlihatkan struk ini saat pengambilan barang ======"
        + "\n =========================================================== \n"
        + "\n == TERIMA KASIH SUDAH MENGGUNAKAN HOMEYLAUNDRY == \n"
        );
            
            }
            catch (Exception e) {
        }
        tampildaftartransaksi();
       
    }
    
    private void perbarui(){
        try{
            setKoneksi();
            String sql="update tb_transaksi set "+"',id_outlet='"+idout.getSelectedItem()
                    +"',kode_invoice='"+koin.getText()
                    +"',id_member='"+idmem.getSelectedItem()
                    +"',tgl='"+tag.getDate()
                    +"',batas_waktu='"+batas.getDate()
                    +"',tgl_bayar='"+tglbayar.getDate()
                    +"',biaya_tambahan='"+tambahan.getText()
                    +"',diskon='"+dis.getText()
                    +"',pajak='"+pajak.getText()
                    +"',status='"+status.getSelectedItem()
                    +"',dibayar='"+Id_user.getSelectedItem()
                    +"',id_user='"+idmem.getSelectedItem()
                    +"' where id_transaksi='"+idtra.getText()+"'";
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"EDIT DATA TRANSAKSI BERHASIL","",JOptionPane.INFORMATION_MESSAGE);
        } 
        catch(Exception e){
        }
        tampildaftartransaksi();
        
    }
    
    private void hapus(){
        try{
            String sql="delete from tb_transaksi where id_transaksi='"+ idtra.getText() +"'";
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "DATA TRANSAKSI TELAH DIHAPUS");
            }
            catch (Exception e) {
            }
        tampildaftartransaksi();
    }
    
    public void tampildaftartransaksi(){
        Object header[]={"Id Transaksi", "Id Outlet", "Kode Invoice", "Id Member", "Tanggal", "Batas Waktu", "Tanggal Bayar", "Biaya Tambahan", "Diskon", "Pajak", "Status", "Dibayar", "Id User"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tbtran.setModel(data);
        setKoneksi();
        String sql="select*from tb_transaksi";
        try {
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
                String kolom4=rs.getString(4);
                String kolom5=rs.getString(5);
                String kolom6=rs.getString(6);
                String kolom7=rs.getString(7);
                String kolom8=rs.getString(8);
                String kolom9=rs.getString(9);
                String kolom10=rs.getString(10);
                String kolom11=rs.getString(11);
                String kolom12=rs.getString(12);
                String kolom13=rs.getString(13);

                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7,kolom8,kolom9,kolom10,kolom11,kolom12,kolom13};
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
        idtra = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        idout = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        koin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        idmem = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tambahan = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        kembaliab = new javax.swing.JTextField();
        nominal_bayar = new javax.swing.JTextField();
        total_bayar = new javax.swing.JTextField();
        harga = new javax.swing.JTextField();
        nama_paket = new javax.swing.JComboBox<>();
        Id_user = new javax.swing.JComboBox<>();
        dibayar = new javax.swing.JComboBox<>();
        status = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        pajak = new javax.swing.JTextField();
        quantity = new javax.swing.JTextField();
        dis = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbtran = new javax.swing.JTable();
        pencarian = new javax.swing.JTextField();
        cari = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        struk = new javax.swing.JTextArea();
        hitung = new javax.swing.JButton();
        print = new javax.swing.JButton();
        tambah = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        tag = new com.toedter.calendar.JDateChooser();
        batas = new com.toedter.calendar.JDateChooser();
        tglbayar = new com.toedter.calendar.JDateChooser();
        kembali = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Id Transaksi");

        idtra.setPreferredSize(new java.awt.Dimension(10, 20));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Id Outlet");

        idout.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- PILIH -" }));
        idout.setPreferredSize(new java.awt.Dimension(10, 20));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Kode Invoice");

        koin.setPreferredSize(new java.awt.Dimension(10, 20));
        koin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                koinActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Id Member");

        idmem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- PILIH -" }));
        idmem.setPreferredSize(new java.awt.Dimension(10, 20));
        idmem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idmemActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Tanggal");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Batas Waktu");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Tanggal Bayar");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setText("Metode Pengambilan");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Biaya Tambahan");

        tambahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahanActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- PILIH -", "Antar", "Ambil Sendiri" }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(10, 20));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Status");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("Dibayar");

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Id User");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setText("Nama Paket");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Harga");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Total Bayar");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("Nominal Bayar");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("Kembalian");

        kembaliab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliabActionPerformed(evt);
            }
        });

        nominal_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nominal_bayarActionPerformed(evt);
            }
        });
        nominal_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nominal_bayarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nominal_bayarKeyReleased(evt);
            }
        });

        total_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_bayarActionPerformed(evt);
            }
        });

        harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaActionPerformed(evt);
            }
        });

        nama_paket.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- PILIH -" }));
        nama_paket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_paketActionPerformed(evt);
            }
        });

        Id_user.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- PILIH -" }));

        dibayar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- PILIH -", "Dibayar", "Belum Dibayar" }));
        dibayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dibayarActionPerformed(evt);
            }
        });

        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- PILIH -", "Baru", "Proses", "Selesai", "Diambil" }));
        status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("Pajak");

        pajak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pajakActionPerformed(evt);
            }
        });

        quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                quantityKeyReleased(evt);
            }
        });

        dis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setText("Quantity");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Diskon");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel22.setText("%");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel21.setText("Kg");

        tbtran.setModel(new javax.swing.table.DefaultTableModel(
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
        tbtran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbtranMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbtran);

        pencarian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pencarianKeyPressed(evt);
            }
        });

        cari.setBackground(new java.awt.Color(102, 102, 102));
        cari.setText("Cari");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        struk.setColumns(20);
        struk.setRows(5);
        jScrollPane2.setViewportView(struk);

        hitung.setBackground(new java.awt.Color(102, 102, 102));
        hitung.setText("Hitung");
        hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitungActionPerformed(evt);
            }
        });

        print.setBackground(new java.awt.Color(102, 102, 102));
        print.setText("Print");
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        tambah.setBackground(new java.awt.Color(102, 102, 102));
        tambah.setText("Tambah");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        simpan.setBackground(new java.awt.Color(102, 102, 102));
        simpan.setText("Simpan");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        edit.setBackground(new java.awt.Color(102, 102, 102));
        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        hapus.setBackground(new java.awt.Color(102, 102, 102));
        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        tag.setPreferredSize(new java.awt.Dimension(10, 20));

        tglbayar.setPreferredSize(new java.awt.Dimension(10, 20));

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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(64, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel3))
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(20, 20, 20)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(idout, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(tambahan, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(batas, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(tag, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(idmem, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(tglbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(1, 1, 1)
                                                        .addComponent(koin, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addComponent(idtra, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(5, 5, 5)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel14)
                                                            .addComponent(jLabel17)
                                                            .addComponent(jLabel18)
                                                            .addComponent(jLabel10)
                                                            .addComponent(jLabel20)
                                                            .addComponent(jLabel9)
                                                            .addComponent(jLabel15)))
                                                    .addComponent(jLabel19))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                            .addGap(41, 41, 41)
                                                            .addComponent(nama_paket, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(harga, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(total_bayar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(nominal_bayar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(kembaliab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(pajak, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(41, 41, 41)
                                                        .addComponent(dis, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel13)
                                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(72, 72, 72)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(dibayar, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(Id_user, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(quantity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel21)
                                            .addComponent(jLabel22)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44)
                                        .addComponent(kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(hitung, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(pencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2)
                        .addGap(20, 20, 20))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(idtra, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dibayar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(idout, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(Id_user, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(koin, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nama_paket, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idmem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tag, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(total_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tglbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel16)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(nominal_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel17))
                                        .addGap(18, 18, 18)
                                        .addComponent(kembaliab, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(pajak, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tambahan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(hitung, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(dis, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(batas, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 25, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
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

    private void koinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_koinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_koinActionPerformed

    private void idmemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idmemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idmemActionPerformed

    private void tambahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tambahanActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        if(jComboBox1.getSelectedItem().equals("Pilih")){
            JOptionPane.showMessageDialog(null, "Pilih Metode Pengambilan");
        }else if(jComboBox1.getSelectedItem().equals("Antar")){
            tambahan.setText("5000");
        }else{
            tambahan.setText("0");
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void kembaliabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kembaliabActionPerformed

    private void nominal_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nominal_bayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nominal_bayarActionPerformed

    private void nominal_bayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nominal_bayarKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_nominal_bayarKeyPressed

    private void nominal_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nominal_bayarKeyReleased
        // TODO add your handling code here:
        String totalbayar = total_bayar.getText();
        String cash = nominal_bayar.getText();

        float totalbayarr = Float.valueOf(totalbayar);
        float bayarr = Float.valueOf(cash);

        float kembali = bayarr-totalbayarr;
        kembaliab.setText(Float.toString(kembali));
    }//GEN-LAST:event_nominal_bayarKeyReleased

    private void hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaActionPerformed

    private void nama_paketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama_paketActionPerformed
        // TODO add your handling code here:
        try{
            setKoneksi();
            stm=conn.createStatement();
            String sql = "select * from tb_paket where nama_paket='"+nama_paket.getSelectedItem()+"'";
            rs=stm.executeQuery(sql);
            while(rs.next()){
                harga.setText(rs.getString("harga"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_nama_paketActionPerformed

    private void pajakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pajakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pajakActionPerformed

    private void quantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityKeyReleased
        // TODO add your handling code here:
        String Quantity = quantity.getText();
        String Harga = harga.getText();
        float berat = Float.valueOf(Quantity);
        float perkilo = Float.valueOf(Harga);
        String discount;

        float bayar = berat*perkilo;
        if(bayar>20000 && bayar<50000){
            discount = "5";
            dis.setText(""+discount);
        }else if(bayar>50000 && bayar<100000){
            discount = "10";
            dis.setText(""+discount);
        }else if(bayar>100000){
            discount = "10";
            dis.setText(""+discount);
        }else{
            dis.setText("0");
        }
    }//GEN-LAST:event_quantityKeyReleased

    private void tbtranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbtranMouseClicked
        // TODO add your handling code here:
        int row = tbtran.getSelectedRow();
        idtra.setText((String)tbtran.getValueAt(row, 0));
        idout.setSelectedItem((String)tbtran.getValueAt(row, 1));
        koin.setText((String)tbtran.getValueAt(row, 2));
        idmem.setSelectedItem((String)tbtran.getValueAt(row, 3));
        tag.setDateFormatString((String)tbtran.getValueAt(row, 4));
        batas.setDateFormatString((String)tbtran.getValueAt(row, 5));
        tglbayar.setDateFormatString((String)tbtran.getValueAt(row, 6));
        tambahan.setText((String)tbtran.getValueAt(row, 7));
        dis.setText((String)tbtran.getValueAt(row, 8));
        pajak.setText((String)tbtran.getValueAt(row, 9));
        status.setSelectedItem((String)tbtran.getValueAt(row, 10));
        dibayar.setSelectedItem((String)tbtran.getValueAt(row, 11));
        Id_user.setSelectedItem((String)tbtran.getValueAt(row, 12));
        hapus.setEnabled(true);
        edit.setEnabled(true);
    }//GEN-LAST:event_tbtranMouseClicked

    private void pencarianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pencarianKeyPressed
        // TODO add your handling code here:
        Object header[]={"Id Transaksi", "Id Outlet", "Kode Invoice", "Id Member", "Tanggal", "Batas Waktu", "Tanggal Bayar", "Biaya Tambahan", "Diskon", "Pajak", "Status", "Dibayar", "Id User"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tbtran.setModel(data);
        String sql="Select * from tb_transaksi where id_transaksi like '%" + pencarian.getText() + "%'" + "or kode_invoice like '%" +pencarian.getText()+"%'";
        try {
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
                String kolom4=rs.getString(4);
                String kolom5=rs.getString(5);
                String kolom6=rs.getString(6);
                String kolom7=rs.getString(7);
                String kolom8=rs.getString(8);
                String kolom9=rs.getString(9);
                String kolom10=rs.getString(10);
                String kolom11=rs.getString(11);
                String kolom12=rs.getString(12);
                String kolom13=rs.getString(13);

                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7,kolom8,kolom9,kolom10,kolom11,kolom12,kolom13};
                data.addRow(kolom);
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_pencarianKeyPressed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
        Object header[]={"Id Transaksi", "Id Outlet", "Kode Invoice", "Id Member", "Tanggal", "Batas Waktu", "Tanggal Bayar", "Biaya Tambahan", "Diskon", "Pajak", "Status", "Dibayar", "Id User"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tbtran.setModel(data);
        String sql="Select * from tb_transaksi where id_transaksi like '%" + pencarian.getText() + "%'" + "or kode_invoice like '%" +pencarian.getText()+"%'";
        try {
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
                String kolom4=rs.getString(4);
                String kolom5=rs.getString(5);
                String kolom6=rs.getString(6);
                String kolom7=rs.getString(7);
                String kolom8=rs.getString(8);
                String kolom9=rs.getString(9);
                String kolom10=rs.getString(10);
                String kolom11=rs.getString(11);
                String kolom12=rs.getString(12);
                String kolom13=rs.getString(13);

                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7,kolom8,kolom9,kolom10,kolom11,kolom12,kolom13};
                data.addRow(kolom);
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_cariActionPerformed

    private void hitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitungActionPerformed
        // TODO add your handling code here:
        String d = dis.getText();
        String pj = pajak.getText();
        String har = harga.getText();
        String bt = tambahan.getText();

        float di = Float.valueOf(d);
        float pjk = Float.valueOf(pj);
        float hrg = Float.valueOf(har);
        float btm = Float.valueOf(bt);

        float disk = di/100*hrg;
        float total = (hrg-(btm+disk))+pjk;
        total_bayar.setText(Float.toString(total));
    }//GEN-LAST:event_hitungActionPerformed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        // TODO add your handling code here:
        try{
            struk.print();
        }catch(PrinterException ex){
            Logger.getLogger(home_admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here:
        if(tambah.getText().equalsIgnoreCase("tambah")){
            tambah.setText("Refresh");
            bersih();
            siapIsi(true);
            auto_number();

            idtra.setEnabled(true);
            idout.setEnabled(true);
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
            tampildaftartransaksi();
        }
    }//GEN-LAST:event_tambahActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
        if(idtra.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Id Transaksi Harus di Isi");
        }if(idout.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Id Outlet Harus di Pilih");
        }if(koin.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Kode Invoice Harus di Isi");
        }if(idmem.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Id Member Harus di Pilih");
        }if(tag == null){
            JOptionPane.showMessageDialog(null, "Tanggal Harus di Pilih");
        }if(batas == null){
            JOptionPane.showMessageDialog(null, "Batas Waktu Harus di Pilih");
        }if(tglbayar == null){
            JOptionPane.showMessageDialog(null, "Tanggal Bayar Harus di Pilih");
        }if(tambahan.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Biaya Tambahan Harus di Isi");
        }if(dis.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Diskon Harus di Isi");
        }if(pajak.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Pajak Harus di Isi");
        }if(status.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Status Harus di Pilih");
        }if(dibayar.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Dibayar Harus di Pilih");
        }if(Id_user.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Id User Harus di Pilih");
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
            idtra.setEnabled(false);
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

    private void disActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_disActionPerformed

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        // TODO add your handling code here:
        new home_admin().setVisible(true);
        dispose();
    }//GEN-LAST:event_kembaliActionPerformed

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusActionPerformed

    private void dibayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dibayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dibayarActionPerformed

    private void total_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_bayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total_bayarActionPerformed

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
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Id_user;
    private com.toedter.calendar.JDateChooser batas;
    private javax.swing.JButton cari;
    private javax.swing.JComboBox<String> dibayar;
    private javax.swing.JTextField dis;
    private javax.swing.JButton edit;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField harga;
    private javax.swing.JButton hitung;
    private javax.swing.JComboBox<String> idmem;
    private javax.swing.JComboBox<String> idout;
    private javax.swing.JTextField idtra;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton kembali;
    private javax.swing.JTextField kembaliab;
    private javax.swing.JTextField koin;
    private javax.swing.JComboBox<String> nama_paket;
    private javax.swing.JTextField nominal_bayar;
    private javax.swing.JTextField pajak;
    private javax.swing.JTextField pencarian;
    private javax.swing.JButton print;
    private javax.swing.JTextField quantity;
    private javax.swing.JButton simpan;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JTextArea struk;
    private com.toedter.calendar.JDateChooser tag;
    private javax.swing.JButton tambah;
    private javax.swing.JTextField tambahan;
    private javax.swing.JTable tbtran;
    private com.toedter.calendar.JDateChooser tglbayar;
    private javax.swing.JTextField total_bayar;
    // End of variables declaration//GEN-END:variables
}
