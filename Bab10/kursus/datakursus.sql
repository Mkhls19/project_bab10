-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 18 Des 2023 pada 08.57
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `datakursus`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `data1`
--

CREATE TABLE `data1` (
  `Id` int(255) NOT NULL,
  `jenis_kursus` varchar(30) NOT NULL,
  `Nama_peserta` varchar(36) NOT NULL,
  `Lama_Kursus` int(10) NOT NULL,
  `Harga_Kursus` int(10) NOT NULL,
  `Terbayar` int(10) NOT NULL,
  `Biaya_admin` int(10) NOT NULL,
  `ket` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data1`
--

INSERT INTO `data1` (`Id`, `jenis_kursus`, `Nama_peserta`, `Lama_Kursus`, `Harga_Kursus`, `Terbayar`, `Biaya_admin`, `ket`) VALUES
(2, 'B.inggris', 'Muhlis', 5, 190000, 250000, 19000, 'Belum Lunas');

-- --------------------------------------------------------

--
-- Struktur dari tabel `datapeserta`
--

CREATE TABLE `datapeserta` (
  `Id` int(255) NOT NULL,
  `Nama_peserta` varchar(30) NOT NULL,
  `jenis_kursus` varchar(30) NOT NULL,
  `alamat` varchar(30) NOT NULL,
  `no_tlp` int(11) NOT NULL,
  `email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `datapeserta`
--

INSERT INTO `datapeserta` (`Id`, `Nama_peserta`, `jenis_kursus`, `alamat`, `no_tlp`, `email`) VALUES
(1, 'Nama Peserta', 'B.inggris', 'Bow', 9862323, 'oiyw@col.con');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `data1`
--
ALTER TABLE `data1`
  ADD PRIMARY KEY (`Id`);

--
-- Indeks untuk tabel `datapeserta`
--
ALTER TABLE `datapeserta`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `data1`
--
ALTER TABLE `data1`
  MODIFY `Id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `datapeserta`
--
ALTER TABLE `datapeserta`
  MODIFY `Id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
