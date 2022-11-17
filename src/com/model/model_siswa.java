package com.model;
import com.controller.controller;
import com.controller.controller_siswa;
import com.view.view1;
import koneksi.java;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author astse
 */
public class model_siswa extends controller_siswa{
    String jk;

    @Override
    public void Simpan(form_siswa siswa) throws SQLException {
        if (siswa.rbLaki.isSelected()) {
            jk = "Laki-laki";
        } else {
            jk = "Perempuan";
        }
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "Insert into siswa Values(?,?,?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, siswa.txtNIS.getText());
            prepare.setString(2, siswa.txtNama.getText());
            prepare.setString(3, jk);
            prepare.setString(4, (String) siswa.cbJurusan.getSelectedItem());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diSimpan");
            prepare.close();
            Baru(siswa);
            
        } catch (Exception e) {
            System.out.println(e);
        } finally{
            Tampil(siswa);
            siswa.setLebarKolom();
        }
    }

    @Override
    public void Ubah(form_siswa siswa) throws SQLException {
        if (siswa.rbLaki.isSelected()) {
            jk = "Laki-laki";
        } else {
            jk = "Perempuan";
        }
            try {
                Connection con = koneksi.getKoneksi();
                String sql = "UPDATE siswa SET nama=?, jenis_kelamin=?, " + "jurusan=? WHERE NIS=?";
                PreparedStatement prepare = con.prepareStatement(sql);

                prepare.setString(4, siswa.txtNIS.getText());
                prepare.setString(1, siswa.txtNama.getText());
                prepare.setString(2, jk);
                prepare.setString(3, (String) siswa.cbJurusan.getSelectedItem());
                prepare.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil diubah");
                prepare.close();
            } catch (Exception e) {
                System.out.println(e);
            } finally{
                Tampil(siswa);
                siswa.setLebarKolom();
                Baru(siswa);
            }
    }

    @Override
    public void Hapus(form_siswa siswa) throws SQLException {
        
        String sql = "DELETE FROM siswa WHERE NIS=?";
//        String resetno = "ALTER TABLE siswa DROP NIS";
//        String consecutivenumbers = "ALTER TABLE siswa ADD NIS INT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST";
        try{
            Connection con = koneksi.getKoneksi();
            PreparedStatement prepare = con.prepareStatement(sql);
//            con.createStatement().execute(resetno);
//            con.createStatement().execute(consecutivenumbers);
            
            prepare.setString(1, siswa.txtNIS.getText());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            prepare.close();
        } catch (Exception e){
            System.out.println(e);
        } finally{
            Tampil(siswa);
            siswa.setLebarKolom();
            Baru(siswa);
        }
    }

    @Override
    public void Tampil(form_siswa siswa) throws SQLException {
        siswa.tblmodel.getDataVector().removeAllElements();
        siswa.tblmodel.fireTableDataChanged();;
            try {
            Connection con = koneksi.getKoneksi();
            Statement stt = con.createStatement();
            // Query menampilkan semua data pada tabel siswa
            // dengan urutan NIS dari kecil ke besar
            String sql…