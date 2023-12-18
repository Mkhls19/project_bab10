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

public class GUI_DataPeserta extends javax.swing.JFrame {

    /**
     * Creates new form GUI_DataPeserta
     */
    public GUI_DataPeserta() {
        initComponents();
        tampil();
        tampilKursus();
    }
    public Connection conn;
    
    public void clear(){
            txt_alamat.setText("");
            txt_email.setText("");
            cmb_jenisP.setSelectedItem(0);
            cmb_nama.setSelectedItem("");
            txt_noTelp.setText("");
    }
     // Membuat variabel baru yang digunakan untuk menyimpan data yang akan ditampilkan dalam textfield dan buttongrup saat item dipilih
    String Jenis_kursus, nama_P1, alamat1, email1, notlp1;
    
    void itempilih() {
        // Membuat method itempilih() yang digunakan untuk menetapkan nilai textfield dan memilih buttongrup berdasarkan nilai variabel yang telah disimpan sebelumnya
        cmb_jenisP.setSelectedItem(Jenis_kursus);
        cmb_nama.setSelectedItem(nama_P1);
        txt_alamat.setText(alamat1);
        txt_email.setText(email1);
        txt_noTelp.setText(notlp1);
    }
    
    public void koneksi() throws SQLException {
        try {
            conn = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/datakursus?user=root&password=");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_Kursus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(GUI_DataPeserta.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(GUI_DataPeserta.class.getName()).log(Level.SEVERE, null, es);
        }
    }
    
    public void tampil() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("Nama Peserta");
        tabelhead.addColumn("jenis kursus");
        tabelhead.addColumn("alamat");
        tabelhead.addColumn("no tlp");
        tabelhead.addColumn("email");
        try {
            koneksi();
            String sql = "SELECT * FROM datapeserta";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString(2), res.getString(3), res.getString(4),
                    res.getString(5), res.getString(6)});
            }
            Tabel_data.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }
    
    public void refresh() {
    // Membuat instance baru dari GUI_DataLembur dan menampilkannya
        new GUI_DataPeserta().setVisible(true);
        this.setVisible(false);
    }
    
    public void insert() {
        // Mendapatkan nilai-nilai dari textfield dan buttongrup
        String nama_P = (String) cmb_nama.getSelectedItem();
        String jenis_k = (String) cmb_jenisP.getSelectedItem();
        String alamat = txt_alamat.getText();
        String no_tlp = txt_noTelp.getText();
        String email = txt_email.getText();
        try {
            koneksi();
            // Membuat statement untuk koneksi database
            Statement statement = conn.createStatement();
            // Menambahkan data baru ke tabel tb_datalembur
            statement.executeUpdate("INSERT INTO datapeserta(Nama_peserta, jenis_kursus, alamat, no_tlp, email)"
                    + "VALUES('" + nama_P + "','" + jenis_k + "','" + alamat + "','" + no_tlp + "','" + email + "')");
            statement.close();
            // Menampilkan pesan sukses setelah menambahkan data
            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input!");
    }
    refresh();
}
    public void tampilKursus() {
        try {
            koneksi();
            String sql = "SELECT Nama_peserta FROM data1 order by Nama_peserta asc";
            Statement stt = conn.createStatement();
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {
                Object[] ob = new Object[3];
                ob[0] = res.getString(1);
                cmb_nama.addItem((String) ob[0]);
            }
            res.close();
            stt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void batal() {
        cmb_nama.setSelectedIndex(0);
        cmb_jenisP.setSelectedIndex(0);
        txt_alamat.setText("");
        txt_noTelp.setText("");
        txt_email.setText("");
    }
    
    //masukkan method delete()
    public void delete() {
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                String sql = "DELETE FROM datakursus WHERE Nama_peserta='" + cmb_nama.getSelectedItem();
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                batal();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus");
            }
        }
        refresh();
    }
    
    public void cari() {
        try {
            try ( Statement statement = conn.createStatement()) {
                String sql = "SELECT * FROM datakursus WHERE `Nama_peserta` LIKE '%" + cmb_nama.getSelectedItem() + "%'";
                ResultSet rs = statement.executeQuery(sql);
                //menampilkan data dari sql query
                if (rs.next()) // .next() = scanner method
                {
                    cmb_nama.setSelectedItem(rs.getString(2));
                    cmb_jenisP.setSelectedItem(rs.getString(3));
                    txt_alamat.setText(rs.getString(4));
                    txt_noTelp.setText(rs.getString(5));
                    txt_email.setText(rs.getString(6));

                } else {
                    JOptionPane.showMessageDialog(null, "Data yang Anda cari tidak ada");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error." + ex);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_alamat = new javax.swing.JTextField();
        txt_noTelp = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabel_data = new javax.swing.JTable();
        btn_Batal = new javax.swing.JButton();
        btn_Close = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_cari = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        cmb_jenisP = new javax.swing.JComboBox<>();
        cmb_nama = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("DATA PESERTA");

        jLabel3.setText("Jenis Kursus");

        jLabel4.setText("Alamat");

        jLabel5.setText("Nomor Telepon");

        jLabel6.setText("Email");

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

        Tabel_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nama Peserta", "Jenis Kursus", "Alamat", "No Telepon", "Email"
            }
        ));
        Tabel_data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabel_dataMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Tabel_data);

        btn_Batal.setText("Batal");
        btn_Batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BatalActionPerformed(evt);
            }
        });

        btn_Close.setText("Close");
        btn_Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CloseActionPerformed(evt);
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

        cmb_jenisP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "B.inggris", "Komputer", "Public Speaking" }));

        cmb_nama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nama Peserta" }));

        jButton1.setText("Nama Peserta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_cari))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jButton1))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_email, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_noTelp, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_alamat, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmb_jenisP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmb_nama, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_simpan)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_ubah)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_hapus)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_Batal)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_Close))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cari)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmb_jenisP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_noTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpan)
                    .addComponent(btn_hapus)
                    .addComponent(btn_Batal)
                    .addComponent(btn_Close)
                    .addComponent(btn_ubah))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        insert();
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
       delete();
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_BatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BatalActionPerformed
        // TODO add your handling code here:
        batal();
    }//GEN-LAST:event_btn_BatalActionPerformed

    private void btn_CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CloseActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_CloseActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_btn_cariActionPerformed

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ubahActionPerformed

    private void Tabel_dataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabel_dataMouseClicked
        // TODO add your handling code here:
        int tabel = Tabel_data.getSelectedRow();
        Jenis_kursus = Tabel_data.getValueAt(tabel, 0).toString();
        nama_P1 = Tabel_data.getValueAt(tabel, 1).toString();
        alamat1 = Tabel_data.getValueAt(tabel, 2).toString();
        notlp1 = Tabel_data.getValueAt(tabel, 3).toString();
        email1 = Tabel_data.getValueAt(tabel, 4).toString();
        itempilih();
    }//GEN-LAST:event_Tabel_dataMouseClicked

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
            java.util.logging.Logger.getLogger(GUI_DataPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_DataPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_DataPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_DataPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_DataPeserta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabel_data;
    private javax.swing.JButton btn_Batal;
    private javax.swing.JButton btn_Close;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JComboBox<String> cmb_jenisP;
    private javax.swing.JComboBox<String> cmb_nama;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_noTelp;
    // End of variables declaration//GEN-END:variables
}
