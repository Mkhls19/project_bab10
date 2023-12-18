/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugas1.kursus;

/**
 *
 * @author acer
 */
public class CLI_detailkursus {
    public static void main(String[] args) {
        // Membuat objek DataPeserta
        DataPeserta dataPeserta = new DataPeserta();
        dataPeserta.dataJkursus("B.inggris");
        dataPeserta.setdataNamapes("Muhlisin");
        dataPeserta.dataBkb(100000);
        dataPeserta.dataPaket(3);
        dataPeserta.dataBiayaadm(25000);
        //dataPeserta.setJadwal("Senin, 10:00"); // Set jadwal untuk DataPeserta
        
        System.out.println("          Data Kursus               ");
        System.out.println("------------------------------------");
        System.out.println("Jenis Kursus          : " + dataPeserta.cetakJkursus());
        System.out.println("Nama peserta          : " + dataPeserta.getcetakNamapes());
        System.out.println("Biaya Kursus Perbulan : " + dataPeserta.cetakBkb());
        System.out.println("Lama Kursus           : " + dataPeserta.cetakPaket());
        System.out.println("Biaya Administrasi    : " + dataPeserta.cetakBiayaadm());
        dataPeserta.hitungTotalBiaya();
        System.out.println("Total Biaya           : " + dataPeserta.cetakTotalb());
        //System.out.println("Jadwal Kursus         : " + dataPeserta.getJadwal());
        //System.out.println("Ketersediaan Kursus   : " + (dataPeserta.isAvailable() ? "Tersedia" : "Tidak tersedia"));
    }
}

