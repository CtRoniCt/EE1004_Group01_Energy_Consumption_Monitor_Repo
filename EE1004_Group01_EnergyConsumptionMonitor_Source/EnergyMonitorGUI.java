package e_Save;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EnergyMonitorGUI extends JFrame {

    private Household household;
    private JTextField nameField, rateField, hoursField, dateField;
    private JComboBox<String> typeBox;
    private JButton addButton, reportButton, clearButton;
    private JLabel statusLabel, totalKwhLabel, totalCostLabel;
    private DefaultTableModel model;
    private JTable table;

    public EnergyMonitorGUI() {
        super("Energy Consumption Monitor");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.DARK_GRAY);

        // Title
        JLabel title = new JLabel("Energy Consumption Monitor", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        mainPanel.add(title, BorderLayout.NORTH);

        // Left panel - Input
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.GRAY);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        leftPanel.add(createLabel("Owner Name:"));
        nameField = new JTextField(15);
        leftPanel.add(nameField);
        leftPanel.add(Box.createVerticalStrut(5));

        leftPanel.add(createLabel("Electricity Rate (TL/kWh):"));
        rateField = new JTextField(15);
        leftPanel.add(rateField);
        leftPanel.add(Box.createVerticalStrut(10));

        leftPanel.add(createLabel("Appliance Type:"));
        typeBox = new JComboBox<>(new String[]{"Refrigerator", "WashingMachine", "AirConditioner"});
        leftPanel.add(typeBox);
        leftPanel.add(Box.createVerticalStrut(5));

        leftPanel.add(createLabel("Hours Used:"));
        hoursField = new JTextField(15);
        leftPanel.add(hoursField);
        leftPanel.add(Box.createVerticalStrut(5));

        leftPanel.add(createLabel("Date (yyyy-mm-dd):"));
        dateField = new JTextField(15);
        leftPanel.add(dateField);
        leftPanel.add(Box.createVerticalStrut(10));

        addButton = new JButton("Add Appliance");
        reportButton = new JButton("Generate Report");
        clearButton = new JButton("Clear All");
        reportButton.setEnabled(false);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addAppliance();
            }
        });

        reportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateReport();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearAll();
            }
        });

        leftPanel.add(addButton);
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(reportButton);
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(clearButton);
        leftPanel.add(Box.createVerticalStrut(10));

        statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.WHITE);
        leftPanel.add(statusLabel);

        // Right panel - Table
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.DARK_GRAY);

        String[] columns = {"#", "Type", "Hours", "Date", "Energy (kWh)"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setBackground(Color.LIGHT_GRAY);
        table.setRowHeight(20);

        JScrollPane scrollPane = new JScrollPane(table);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        // Summary panel
        JPanel summaryPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        summaryPanel.setBackground(Color.DARK_GRAY);
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JPanel kwhPanel = new JPanel();
        kwhPanel.setBackground(Color.GRAY);
        kwhPanel.add(new JLabel("Total Energy:"));
        totalKwhLabel = new JLabel("0.00 kWh");
        totalKwhLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalKwhLabel.setForeground(Color.BLUE);
        kwhPanel.add(totalKwhLabel);
        summaryPanel.add(kwhPanel);

        JPanel costPanel = new JPanel();
        costPanel.setBackground(Color.GRAY);
        costPanel.add(new JLabel("Total Cost:"));
        totalCostLabel = new JLabel("0.00 TL");
        totalCostLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalCostLabel.setForeground(Color.GREEN);
        costPanel.add(totalCostLabel);
        summaryPanel.add(costPanel);

        rightPanel.add(summaryPanel, BorderLayout.SOUTH);

        // Add panels to main
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        // Status bar
        JPanel statusBar = new JPanel();
        statusBar.setBackground(Color.BLACK);
        JLabel statusText = new JLabel("EE1004 - Java Programming Project");
        statusText.setForeground(Color.GRAY);
        statusBar.add(statusText);
        mainPanel.add(statusBar, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        return label;
    }

    private void addAppliance() {
        if (household == null) {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                statusLabel.setText("Please enter owner name!");
                statusLabel.setForeground(Color.RED);
                return;
            }
            try {
                double rate = Double.parseDouble(rateField.getText().trim());
                if (rate < 0) {
                    statusLabel.setText("Rate cannot be negative!");
                    statusLabel.setForeground(Color.RED);
                    return;
                }
                household = new Household(name, rate);
                nameField.setEditable(false);
                rateField.setEditable(false);
            } catch (NumberFormatException ex) {
                statusLabel.setText("Invalid rate!");
                statusLabel.setForeground(Color.RED);
                return;
            }
        }

        double hours;
        try {
            hours = Double.parseDouble(hoursField.getText().trim());
            if (hours < 0) {
                statusLabel.setText("Hours cannot be negative!");
                statusLabel.setForeground(Color.RED);
                return;
            }
        } catch (NumberFormatException ex) {
            statusLabel.setText("Invalid hours!");
            statusLabel.setForeground(Color.RED);
            return;
        }

        LocalDate date;
        try {
            date = LocalDate.parse(dateField.getText().trim());
        } catch (DateTimeParseException ex) {
            statusLabel.setText("Invalid date! Use yyyy-mm-dd");
            statusLabel.setForeground(Color.RED);
            return;
        }

        String type = (String) typeBox.getSelectedItem();
        Appliance app;
        if (type.equals("Refrigerator")) {
            app = new Refrigerator(hours, date);
        } else if (type.equals("WashingMachine")) {
            app = new WashingMachine(hours, date);
        } else {
            app = new AirConditioner(hours, date);
        }

        household.addAppliance(app);
        model.addRow(new Object[]{
            model.getRowCount() + 1,
            type,
            String.format("%.1f", hours),
            date.toString(),
            String.format("%.2f", app.getEnergyConsumed())
        });

        totalKwhLabel.setText(String.format("%.2f kWh", household.totalEnergyConsumed()));
        totalCostLabel.setText(String.format("%.2f TL", household.totalCost()));

        hoursField.setText("");
        dateField.setText("");
        statusLabel.setText(type + " added!");
        statusLabel.setForeground(Color.GREEN);
        reportButton.setEnabled(true);
    }

    private void generateReport() {
        if (household == null) {
            statusLabel.setText("Add appliance first!");
            statusLabel.setForeground(Color.RED);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Energy Report for ").append(household.getOwnerName()).append("\n\n");

        for (int i = 0; i < model.getRowCount(); i++) {
            sb.append(" - [").append(model.getValueAt(i, 1)).append("] used ")
              .append(model.getValueAt(i, 2)).append(" hours on ")
              .append(model.getValueAt(i, 3)).append(", consumed ")
              .append(model.getValueAt(i, 4)).append(" kWh.\n");
        }

        sb.append("\nTotal Energy: ").append(String.format("%.2f", household.totalEnergyConsumed())).append(" kWh");
        sb.append("\nTotal Cost: ").append(String.format("%.2f", household.totalCost())).append(" TL");

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setBackground(Color.LIGHT_GRAY);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, scrollPane, "Report", JOptionPane.PLAIN_MESSAGE);
    }

    private void clearAll() {
        int result = JOptionPane.showConfirmDialog(this,
            "Clear all?",
            "Confirm", JOptionPane.YES_NO_OPTION);
        if (result != JOptionPane.YES_OPTION) return;

        household = null;
        model.setRowCount(0);
        totalKwhLabel.setText("0.00 kWh");
        totalCostLabel.setText("0.00 TL");
        nameField.setEditable(true);
        rateField.setEditable(true);
        nameField.setText("");
        rateField.setText("");
        hoursField.setText("");
        dateField.setText("");
        reportButton.setEnabled(false);
        statusLabel.setText("Cleared!");
        statusLabel.setForeground(Color.WHITE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EnergyMonitorGUI();
            }
        });
    }
}
