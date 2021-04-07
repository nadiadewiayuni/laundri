/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class db_koneksi {
    Connection koneksi=null;
    public static Connection koneksiDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi =  DriverManager.getConnection("jdbc:mysql://localhost/laundry_db","root","");
            return koneksi;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
