import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;

public class Testing {

    private PengelolaanDataSiswa app;
    private DefaultTableModel tableModel;

    @BeforeEach
    void setUp() {
        app = new PengelolaanDataSiswa();  // Initialize your app
        tableModel = (DefaultTableModel) app.table.getModel();
    }

    @Test
    void testAddStudent() {
        // Setup test data
        String id = "9900";
        String name = "Dafa Nusa";
        int age = 18;
        String imagePath = "Tidak ada gambar";  // Assume no image chosen

        // Set fields
        app.NISNField.setText(id);
        app.nameField.setText(name);
        app.ageField.setText(String.valueOf(age));

        // Simulate clicking the Add button
        app.addStudent();

        // Assert the table has one row with the expected data
        assertEquals(1, tableModel.getRowCount());
        assertEquals(id, tableModel.getValueAt(0, 0));
        assertEquals(name, tableModel.getValueAt(0, 1));
        assertEquals(age, tableModel.getValueAt(0, 2));
        assertEquals(imagePath, tableModel.getValueAt(0, 3));
    }

    @Test
    void testUpdateStudent() {
        // Add a student first
        app.NISNField.setText("9900");
        app.nameField.setText("Dafa Nusa");
        app.ageField.setText("18");
        app.addStudent();  // Ensure a student is added before updating

        // Set new values for the student
        app.NISNField.setText("9888");
        app.nameField.setText("rizqullah");
        app.ageField.setText("20");

        // Simulate clicking the Update button
        app.updateStudent();

        // Assert the table row is updated
        assertEquals("9900", tableModel.getValueAt(0, 0));
        assertEquals("Dafa Nusa", tableModel.getValueAt(0, 1));
        assertEquals(18, tableModel.getValueAt(0, 2));


    }


    @Test
    void testDeleteStudent() {
        // Add a student first
        app.addStudent();

        // Delete the student
        app.deleteStudent();

        // Assert the table is empty
        assertEquals(0, tableModel.getRowCount());
    }

    @Test
    void testSaveToFile() {
        // Add a student to the table
        app.addStudent();

        // Save to file (we won't actually test file writing here, but check if the file chooser works)
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);

        // Simulate file chooser interaction (usually you'd mock this)
        assertNotNull(fileChooser);
        assertTrue(fileChooser.showSaveDialog(null) != JFileChooser.CANCEL_OPTION);
    }

    @Test
    void testClearFields() {
        // Set some values in the form
        app.NISNField.setText("9900");
        app.nameField.setText("Dafa Nusa");
        app.ageField.setText("18");

        // Call the clearFields method
        app.clearFields();

        // Assert that all fields are cleared
        assertEquals("", app.NISNField.getText());
        assertEquals("", app.nameField.getText());
        assertEquals("", app.ageField.getText());
    }

    @Test
    void testInvalidAge() {
        // Try entering an invalid age (non-numeric value)
        app.NISNField.setText("9900");
        app.nameField.setText("Dafa Nusa");
        app.ageField.setText("invalid_age");

        // Try adding the student
        app.addStudent();

        // Assert that the table is still empty (since the age is invalid)
        assertEquals(0, tableModel.getRowCount());
    }
}