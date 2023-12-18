/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugas1.kursus;

/**
 *
 * @author acer
 */
public abstract class Kursus{
    //Abstract Method 
    public abstract String Ringkasan();
    abstract public String keterangan();
    
    //atribut Dari kursus
    protected String nama_peserta, jenis_kursus,ket;
    protected int biaya_adm, biayakurbulan, totalbiaya,paket_kursus,harga;
    
    //method setter
    public void setdataNamapes(String Namapes){
        this.nama_peserta = Namapes;
    }
    public void dataJkursus(String Jeniskursus){
        this.jenis_kursus = Jeniskursus;
    }
    public void dataBkb(int Bkb){
        this.biayakurbulan = Bkb;
    }
    public void dataPaket(int Paket){
        this.paket_kursus = Paket;
    }
    public void dataBiayaadm(int Biayaadm){
        this.biaya_adm = Biayaadm;
    }
    public void dataTotalb(int Totalb){
        this.totalbiaya = Totalb;
    }
    public void dataHarga(int harga){
        this.harga = harga;
    }
    
    //method getter
    public String getcetakNamapes (){
        return nama_peserta;
    }
    public String cetakJkursus (){
        return jenis_kursus;
    }
    public int cetakBkb (){
        return biayakurbulan;
    }
    public int cetakPaket (){
        return paket_kursus;
    }
    public int cetakBiayaadm (){
        return biaya_adm;
    }
    public int cetakTotalb (){
        return totalbiaya;
    }
    public int cetakHarga (){
        return harga;
    }
    
    // Method untuk menghitung total biaya kursus
    public int hitungTotalBiaya() {
        // Perhitungan total biaya
        return totalbiaya = biaya_adm + (biayakurbulan * paket_kursus);//total biaya diambil dari cetakTotalb
    }
    public int total(){
        return harga*paket_kursus;
    }
    
}



