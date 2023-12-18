/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugas1.kursus;

/**
 *
 * @author acer
 */
public class DataSkor extends DataPeserta {
    // Atribut tambahan untuk DataSkor
    private int skorUjian;
    private String Keterangan;
    
    //private String jadwalKursusDataSkor;

    // Method setter untuk skor ujian
    public void setSkorUjian(int Skor) {
        this.skorUjian = Skor;
    }
    
    // OVERLOADING
    // Method overloading untuk setHasil dengan parameter String
    public String getHasil() {
         return Keterangan;
    }

    // Method overloading untuk setHasil dengan parameter int
    public String getHasil(int nilai) {
        if (nilai >= 70) {
            Keterangan = "Lulus";
        } else {
            Keterangan = "Tidak Lulus";
        }
        return Keterangan;
    }

    // Method getter untuk skor ujian
    public int getSkor() {
        return skorUjian;
    }
    
    //overriding method
    @Override
    public int cetakTotalb (){
        return paket_kursus = 3;
    }
    
    @Override
    public String Ringkasan() {
        // Mengembalikan ringkasan tentang skor dan keterangan dari ujian
        return getHasil(skorUjian);
    }
    
}

