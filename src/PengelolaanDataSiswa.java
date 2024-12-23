import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public class PengelolaanDataSiswa {

    private JFrame frame;
    public JTable table;
    private DefaultTableModel tableModel;
    public JTextField NISNField;
    public JTextField nameField;
    public JTextField ageField;
    private JLabel imageLabel;
    private File currentImage;

    public PengelolaanDataSiswa() {

        frame = new JFrame("Aplikasi Pengelolaan Data Siswa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(new Color(245, 245, 245));


        tableModel = new DefaultTableModel(new String[]{"NISN", "Nama", "Umur", "Gambar"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));


        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(54, 57, 63));
        JLabel titleLabel = new JLabel("Pengelolaan Data Siswa", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        headerPanel.add(titleLabel, BorderLayout.CENTER);


        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(new Color(245, 245, 245));


        NISNField = new JTextField();
        NISNField.setPreferredSize(new Dimension(200, 30));
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 30));
        ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(200, 30));

        imageLabel = new JLabel("Tidak ada gambar", SwingConstants.CENTER);
        imageLabel.setOpaque(true);
        imageLabel.setBackground(Color.LIGHT_GRAY);
        imageLabel.setPreferredSize(new Dimension(150, 150));
        JButton browseButton = new JButton("Unggah Image");

        browseButton.addActionListener(e -> chooseImage());

        formPanel.add(new JLabel("NISN :", SwingConstants.RIGHT));
        formPanel.add(NISNField);
        formPanel.add(new JLabel("Nama :", SwingConstants.RIGHT));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Umur :", SwingConstants.RIGHT));
        formPanel.add(ageField);
        formPanel.add(new JLabel("Gambar :", SwingConstants.RIGHT));
        formPanel.add(browseButton);
        formPanel.add(new JLabel("Lihat :", SwingConstants.RIGHT));
        formPanel.add(imageLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(54, 57, 63));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton addButton = new JButton("Tambah");
        JButton updateButton = new JButton("Perbarui");
        JButton deleteButton = new JButton("Hapus");
        JButton saveButton = new JButton("Simpan Ke File");

        addButton.setBackground(new Color(46, 204, 113));
        addButton.setForeground(Color.WHITE);
        updateButton.setBackground(new Color(52, 152, 219));
        updateButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(231, 76, 60));
        deleteButton.setForeground(Color.WHITE);
        saveButton.setBackground(new Color(241, 196, 15));
        saveButton.setForeground(Color.WHITE);

        addButton.addActionListener(e -> addStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        saveButton.addActionListener(e -> saveToFile());

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);

        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(formPanel, BorderLayout.WEST);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Gambar", "jpg", "png", "jpeg"));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            currentImage = fileChooser.getSelectedFile();
            try {
                Image img = ImageIO.read(currentImage).getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
                imageLabel.setText(null);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "gagal memuat gambar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void addStudent() {
        try {
            String id = NISNField.getText().trim();
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();


            if (id.isEmpty() || name.isEmpty() || ageText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Semua Kolom harus diisi", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int age = Integer.parseInt(ageText);
            String imagePath = currentImage != null ? currentImage.getAbsolutePath() : "Tidak ada gambar";

            tableModel.addRow(new Object[]{id, name, age, imagePath});
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "umur harus berupa angka valid!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "terjadi kesalahan : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateStudent() {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                throw new Exception("tidak ada baris yang dipilih");
            }

            String id = NISNField.getText().trim();
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();

            // Check for empty fields
            if (id.isEmpty() || name.isEmpty() || ageText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "semua kolom harus diisi", "Input Error", JOptionPane.ERROR_MESSAGE);
                return; // Stop further processing
            }

            int age = Integer.parseInt(ageText);

            String imagePath = currentImage != null ? currentImage.getAbsolutePath() : tableModel.getValueAt(selectedRow, 3).toString();

            tableModel.setValueAt(id, selectedRow, 0);
            tableModel.setValueAt(name, selectedRow, 1);
            tableModel.setValueAt(age, selectedRow, 2);
            tableModel.setValueAt(imagePath, selectedRow, 3);
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "umur harus berupa angka valid!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "terjadi kesalahan : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void deleteStudent() {
        try {
            int selectedRow = table.getSelectedRow( );
            if (selectedRow == -1) {
                throw new Exception("tidak ada baris yang dipilih");
            }
            tableModel.removeRow(selectedRow);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "terjadi kesalahan : " + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveToFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text Files", "txt"));
        int result = fileChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("ID\tName\tAge\tImage\n");
                // Write each row data into file, including the image path
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    writer.write(tableModel.getValueAt(i, 0) + "\t" +
                            tableModel.getValueAt(i, 1) + "\t" +
                            tableModel.getValueAt(i, 2) + "\t" +
                            tableModel.getValueAt(i, 3) + "\n");
                }
                JOptionPane.showMessageDialog(frame, "Data berhasil disimpan!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Gagal untuk menyimpan file : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void clearFields() {
        NISNField.setText("");
        nameField.setText("");
        ageField.setText("");
        imageLabel.setIcon(null);
        imageLabel.setText("Tidak ada gambar");
        currentImage = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PengelolaanDataSiswa::new);
    }
}



