/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tugas1.kursus;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GUI_Kursus extends javax.swing.JFrame {

    /**
     * Creates new form GUI_Kursus
     */
    public GUI_Kursus() {
        initComponents();
        tampil();
    }
    public void batal(){
        txtBiayaadm.setText("");
        txtByr.setText("");
        txtNamap.setText("");
        txtPaketK.setText("");
        cmbJen.setSelectedItem(0);
    }
    
    // Variabel conn digunakan untuk menyimpan koneksi ke database
    public Connection conn;
    
     // Membuat variabel baru yang digunakan untuk menyimpan data yang akan ditampilkan dalam textfield dan buttongrup saat item dipilih
    String Jenis_kursus, nama_P1, terbayar, paket1, biayaadm1;
    
    void itempilih() {
        // Membuat method itempilih() yang digunakan untuk menetapkan nilai textfield dan memilih buttongrup berdasarkan nilai variabel yang telah disimpan sebelumnya
        cmbJen.setSelectedItem(Jenis_kursus);
        txtNamap.setText(nama_P1);
        txtByr.setText(terbayar);
        txtPaketK.setText(paket1);
        txtBiayaadm.setText(biayaadm1);
    }
    
    public void koneksi() throws SQLException {
        try {
            conn = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/datakursus?user=root&password=");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_Kursus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(GUI_Kursus.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(GUI_Kursus.class.getName()).log(Level.SEVERE, null, es);
        }
    }
    
    public void tampil() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("jenis kursus");
        tabelhead.addColumn("Nama Peserta");
        tabelhead.addColumn("Lama Kursus");
        tabelhead.addColumn("Harga Kursus");
        tabelhead.addColumn("Terbayar");
        tabelhead.addColumn("Biaya Admin");
        tabelhead.addColumn("Ket");
        try {
            koneksi();
            String sql = "SELECT * FROM data1";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString(2), res.getString(3), res.getString(4),
                    res.getString(5), res.getString(6), res.getString(7),
                    res.getString(8)});
            }
            tabel_info.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }
    
    public void refresh() {
    // Membuat instance baru dari GUI_DataLembur dan menampilkannya
        new GUI_Kursus().setVisible(true);
        this.setVisible(false);
    }
    
    public void insert() {
        // Mendapatkan nilai-nilai dari textfield dan buttongrup
        String jenis_k = cmbJen.getSelectedItem().toString();
        String nama_P = txtNamap.getText();
        int terbayar = Integer.parseInt(txtByr.getText());
        String Sterbayar = Integer.toString(terbayar);
        String paket_k = txtPaketK.getText();
        String biaya_Adm = txtBiayaadm.getText();
        int harga = 0;
        if(cmbJen.getSelectedIndex() == 0){
            harga = 250000;
        }else if(cmbJen.getSelectedIndex()== 1){
            harga = 300000;
        }else if(cmbJen.getSelectedIndex()== 2){
            harga = 500000;
        }
        String Sharga = Integer.toString(harga);
        String ket = "";
        if(terbayar < harga){
            ket = "Belum Lunas";
        }else{
            ket = "Lunas";
        }
        try {
            koneksi();
            // Membuat statement untuk koneksi database
            Statement statement = conn.createStatement();
            // Menambahkan data baru ke tabel tb_datalembur
            statement.executeUpdate("INSERT INTO data1(jenis_kursus, Nama_peserta, Lama_Kursus, Harga_Kursus, Terbayar, Biaya_admin, ket)"
                    + "VALUES('" + jenis_k + "','" + nama_P + "','" + paket_k + "','" + Sterbayar + "','" + Sharga + "','" + biaya_Adm + "','" + ket + "')");
//                    ,'" + gaji_lembur + "', '" + alasan_lembur + "', '" + total_gaji + "'
            statement.close();
            // Menampilkan pesan sukses setelah menambahkan data
            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input!");
    }
    refresh();
}
    
    public void update() {
        // Mendapatkan nilai-nilai dari textfield dan buttongrup
        String jenis_k = (String) cmbJen.getSelectedItem();
        String nama_P = txtNamap.getText();
        int terbayar = Integer.parseInt(txtByr.getText());
        String Sterbayar = String.valueOf(terbayar);
        String paket_k = txtPaketK.getText();
        String biaya_Adm = txtBiayaadm.getText();
        int harga = 0;
        if(cmbJen.getSelectedIndex() == 0){
            harga = 250000;
        }else if(cmbJen.getSelectedIndex()== 1){
            harga = 300000;
        }else if(cmbJen.getSelectedIndex()== 2){
            harga = 500000;
        }
        String Sharga = String.valueOf(harga);
        String ket = "";
        if(terbayar < harga){
            ket = "Belum Lunas";
        }else{
            ket = "Lunas";
        }
        String jk_lama = Jenis_kursus;
        String namak_lama = nama_P1;
        try {
            // Membuat statement untuk koneksi database
            Statement statement = conn.createStatement();
            // Menjalankan query UPDATE untuk memperbarui data dalam tabel tb_datalembur
            statement.executeUpdate("UPDATE data1 SET jenis_kursus='" + jenis_k + "'," + "Nama_peserta='" + nama_P + "'" + "',Lama_Kursus='" + paket_k
                    + ",Terbayar='" + Sterbayar + "',Biaya_admin='" + biaya_Adm + "',ket='" + ket + "' WHERE jenis_kursus ='" + jk_lama + "' AND Nama_peserta='" + namak_lama + "'");
            statement.close();
            conn.close();
            // Menampilkan pesan sukses setelah memperbarui data
            JOptionPane.showMessageDialog(null, "Update Data Lembur!");
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        refresh();
    }
    public void delete() {
        // Menampilkan dialog konfirmasi untuk menghapus data
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                // Query DELETE untuk menghapus data dari tabel tb_datalembur
                String sql = "DELETE FROM data1 WHERE Nama_peserta='" + txtNamap.getText() + "' AND Lama_Kursus='" + txtPaketK.getText() + "'";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
                
                // Menampilkan pesan sukses setelah menghapus data
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                batal();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal di hapus");
            }
        }
        refresh();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNamap = new javax.swing.JTextField();
        txtByr = new javax.swing.JTextField();
        txtPaketK = new javax.swing.JTextField();
        txtBiayaadm = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cmbJen = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_info = new javax.swing.JTable();
        btn_simpan = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_btl = new javax.swing.JButton();
        btn_close = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("DATA KURSUS");

        jLabel2.setText("Jenis Kursus");

        jLabel3.setText("Nama peserta");

        jLabel4.setText("Terbayar");

        jLabel5.setText("Paket Kursus");

        jLabel6.setText("Biaya Administrasi");

        txtNamap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamapActionPerformed(evt);
            }
        });

        txtPaketK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaketKActionPerformed(evt);
            }
        });

        jLabel7.setText("bulan");

        cmbJen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "B.inggris", "Komputer", "Public Speaking" }));

        tabel_info.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Jenis Kursus", "Nama", "Lama Kursus", "Harga Kursus", "Terbayar", "Biaya admin", "Ket"
            }
        ));
        tabel_info.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_infoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_info);

        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_btl.setText("Batal");
        btn_btl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_btlActionPerformed(evt);
            }
        });

        btn_close.setText("Close");
        btn_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_closeActionPerformed(evt);
            }
        });

        btn_ubah.setText("Ubah");
        btn_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahActionPerformed(evt);
            }
        });

        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        jButton1.setText("Form Data Peserta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(397, 397, 397)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_cari))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtByr, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNamap, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtPaketK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel7))
                                .addComponent(cmbJen, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtBiayaadm, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_simpan)
                                .addGap(18, 18, 18)
                                .addComponent(btn_ubah)
                                .addGap(18, 18, 18)
                                .addComponent(btn_hapus)
                                .addGap(18, 18, 18)
                                .addComponent(btn_btl)
                                .addGap(18, 18, 18)
                                .addComponent(btn_close)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cmbJen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNamap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtByr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtPaketK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtBiayaadm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpan)
                    .addComponent(btn_hapus)
                    .addComponent(btn_btl)
                    .addComponent(btn_close)
                    .addComponent(btn_ubah)
                    .addComponent(jButton1))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamapActionPerformed

    private void txtPaketKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaketKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaketKActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        insert();
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        delete();
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_btlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_btlActionPerformed
        // TODO add your handling code here:
        batal();
    }//GEN-LAST:event_btn_btlActionPerformed

    private void btn_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_closeActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_closeActionPerformed

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btn_ubahActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cariActionPerformed

    private void tabel_infoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_infoMouseClicked
        // TODO add your handling code here:
        int tabel = tabel_info.getSelectedRow();
        Jenis_kursus = tabel_info.getValueAt(tabel, 0).toString();
        nama_P1 = tabel_info.getValueAt(tabel, 1).toString();
        paket1 = tabel_info.getValueAt(tabel, 2).toString();
        terbayar = tabel_info.getValueAt(tabel, 3).toString();
        biayaadm1 = tabel_info.getValueAt(tabel, 4).toString();
        itempilih();
    }//GEN-LAST:event_tabel_infoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new GUI_DataPeserta().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(GUI_Kursus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Kursus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Kursus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Kursus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Kursus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_btl;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_close;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JComboBox<String> cmbJen;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabel_info;
    private javax.swing.JTextField txtBiayaadm;
    private javax.swing.JTextField txtByr;
    private javax.swing.JTextField txtNamap;
    private javax.swing.JTextField txtPaketK;
    private javax.swing.JTextField txt_cari;
    // End of variables declaration//GEN-END:variables
}

