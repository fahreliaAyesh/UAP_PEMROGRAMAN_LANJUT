# Aplikasi Manajemen Data Siswa

Ini adalah aplikasi Java sederhana untuk mengelola data siswa, termasuk ID, nama, usia, dan gambar opsional. Aplikasi ini menyediakan antarmuka grafis (GUI) untuk menambah, memperbarui, menghapus, dan menyimpan data siswa ke dalam file teks.

## Fitur

- *Tambah Siswa*: Masukkan detail siswa dan gambar (opsional) dan tambahkan ke tabel.
- *Perbarui Siswa*: Pilih siswa dari tabel dan perbarui detail mereka, termasuk gambar.
- *Hapus Siswa*: Hapus siswa dari daftar.
- *Simpan ke File*: Simpan daftar siswa saat ini ke file teks.
- *Unggah Gambar*: Telusuri dan pilih gambar untuk dikaitkan dengan siswa.

## Persyaratan

- Java 8 atau lebih baru
- Java Swing (termasuk dalam JDK)

## Memulai

Untuk menjalankan aplikasi ini, ikuti langkah-langkah berikut:

1. Clone atau unduh repositori ke mesin lokal Anda.
2. Kompilasi kode Java menggunakan IDE Java atau alat baris perintah (misalnya, javac StudentManagementApp.java).
3. Jalankan aplikasi dengan perintah java StudentManagementApp.

## Penggunaan

Setelah aplikasi dijalankan, sebuah jendela akan terbuka dengan komponen-komponen berikut:

- *Tabel Siswa*: Tabel yang menampilkan semua siswa yang ditambahkan dengan kolom untuk ID, Nama, Usia, dan Gambar.
- *Panel Formulir*: Formulir untuk memasukkan data siswa (ID, Nama, Usia) dan memilih gambar.
- *Tombol*:
    - *Tambah*: Menambahkan siswa ke tabel.
    - *Perbarui*: Memperbarui informasi siswa yang dipilih dalam tabel.
    - *Hapus*: Menghapus siswa yang dipilih dari tabel.
    - *Simpan ke File*: Menyimpan data siswa ke file teks.

### Menambahkan Siswa
1. Isi ID, nama, dan usia siswa di kolom yang sesuai.
2. Pilih gambar (opsional) dengan mengklik tombol "Browse Image".
3. Klik "Add" untuk menambahkan siswa ke tabel.

### Memperbarui Siswa
1. Pilih siswa dari tabel.
2. Ubah informasi siswa (ID, Nama, Usia, atau Gambar).
3. Klik "Update" untuk menyimpan perubahan.

### Menghapus Siswa
1. Pilih siswa dari tabel.
2. Klik "Delete" untuk menghapus siswa yang dipilih dari tabel.

### Menyimpan Data ke File
1. Klik "Save to File" untuk membuka pemilih file.
2. Pilih lokasi untuk menyimpan file dan masukkan nama file.
3. Data akan disimpan dalam format file teks dengan pemisah tab.

## Penjelasan Kode

### Komponen Utama

1. *JTable*: Menampilkan data siswa dengan kolom ID, Nama, Usia, dan Gambar.
2. *JTextField*: Digunakan untuk kolom input seperti ID siswa, nama, dan usia.
3. *JButton*: Digunakan untuk memicu tindakan seperti menambah, memperbarui, menghapus, dan menyimpan data.
4. *JFileChooser*: Membantu pengguna untuk menelusuri dan memilih file gambar yang akan dikaitkan dengan siswa.
5. *BufferedWriter*: Digunakan untuk menulis data siswa ke file saat menyimpan.

### Metode

- *addStudent()*: Menambahkan siswa baru ke tabel.
- *updateStudent()*: Memperbarui data siswa yang ada di tabel.
- *deleteStudent()*: Menghapus siswa yang dipilih dari tabel.
- *saveToFile()*: Menyimpan data siswa ke file teks.
- *chooseImage()*: Membuka pemilih file untuk memilih gambar.