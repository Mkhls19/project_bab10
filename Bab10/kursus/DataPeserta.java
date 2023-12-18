/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugas1.kursus;

/**
 *
 * @author acer
 */
public class DataPeserta extends Kursus implements Progres{
    // Atribut tambahan untuk DataPeserta
    private String alamat, nomor_telepon, email;
    
    
    // method setter
    public void setdataAlamat(String alamat) {
        this.alamat = alamat;
    }
    public void setdataNomor(String nomor){
        this.nomor_telepon = nomor;
    }
    public void setdataEmail(String Email){
        this.email = Email;
    }
    
    //methode getter
    public String getcetakAlamat() {
        return alamat;
    } 
    public String getcetakNomor(){
        return nomor_telepon;
    }
    public String getcetakEmail(){
        return email;
    }
    
    //@Override
    public String Ringkasan(){
        return null;
    }
    @Override
    public String keterangan(){
        if(total() <= biayakurbulan*paket_kursus){
            ket =  "lunas";
        }else{
            ket = "Belum Lunas";
        }
        return ket;
    }
    @Override
    public String Progress(){
        if(getcetakNamapes().equals("Muhlisin")){
            if(cetakJkursus().equals("B.inggris")){
                ket = "80%";
            }else if(cetakJkursus().equals("Komputer")){
                ket = "7%";
            }else if(cetakJkursus().equals("Public Speaking")){
                ket = "6%";
            }else{
                ket = "Data Kosong";
            }
        }else if (getcetakNamapes().equals("Whildan")){
            if(cetakJkursus().equals("B.inggris")){
                ket = "80%";
            }else if(cetakJkursus().equals("Komputer")){
                ket = "6%";
            }else if(cetakJkursus().equals("Public Speaking")){
                ket = "10%";
            }else{
                ket = "Data Kosong";
            }
        }else if (getcetakNamapes().equals("Fajrul")){
            if(cetakJkursus().equals("B.inggris")){
                ket = "80%";
            }else if(cetakJkursus().equals("Komputer")){
                ket = "23%";
            }else if(cetakJkursus().equals("Public Speaking")){
                ket = "34%";
            }else{
                ket = "Data Kosong";
            }
        }else{
            ket = "Murid Illegal";
        }
            return ket;
    }
}

