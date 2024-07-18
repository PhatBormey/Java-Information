import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Information extends JFrame {
    private JTextField idField, lastNameField, firstNameField, phoneField;
    private JButton previousButton, nextButton;
    private ResultSet customers;
    private int currentRow;

    public Information() {
        setTitle("Customer");
        setSize(300, 200);
        setLayout(new GridLayout(5, 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        idField = new JTextField();
        lastNameField = new JTextField();
        firstNameField = new JTextField();
        phoneField = new JTextField();
        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");

        // Add components to frame
        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Last Name:"));
        add(lastNameField);
        add(new JLabel("First Name:"));
        add(firstNameField);
        add(new JLabel("Phone:"));
        add(phoneField);
        add(previousButton);
        add(nextButton);

        // Disable editing of text fields
        idField.setEditable(false);
        lastNameField.setEditable(false);
        firstNameField.setEditable(false);
        phoneField.setEditable(false);

        // Load customers from database
        DatabaseConnector db = new DatabaseConnector();
        customers = db.getCustomers();
        try {
            if (customers.next()) {
                displayCustomer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add action listeners
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (customers.previous()) {
                        displayCustomer();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (customers.next()) {
                        displayCustomer();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        setVisible(true);
    }

    private void displayCustomer() {
        try {
            idField.setText(customers.getString("customer_id"));
            lastNameField.setText(customers.getString("customer_last_name"));
            firstNameField.setText(customers.getString("customer_first_name"));
            phoneField.setText(customers.getString("customer_phone"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Information();
    }
}
