import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

class Car {
    private String licensePlate;
    private long entryTime;

    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
        this.entryTime = System.currentTimeMillis();
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public long getEntryTime() {
        return entryTime;
    }
}

class ParkingSpot {
    private int spotNumber;
    private Car parkedCar;

    public ParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.parkedCar = null;
    }

    public boolean isAvailable() {
        return parkedCar == null;
    }

    public void parkCar(Car car) {
        this.parkedCar = car;
    }

    public Car removeCar() {
        Car car = parkedCar;
        parkedCar = null;
        return car;
    }

    public Car getParkedCar() {
        return parkedCar;
    }

    public int getSpotNumber() {
        return spotNumber;
    }
}

public class SmartParkingGUI extends JFrame {
    private static final int TOTAL_SPOTS = 5;
    private List<ParkingSpot> spots;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField plateField;
    private JTextArea resultArea;

    public SmartParkingGUI() {
        spots = new ArrayList<>();
        for (int i = 1; i <= TOTAL_SPOTS; i++) {
            spots.add(new ParkingSpot(i));
        }

        setTitle("Smart Parking System");
        setSize(650, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JLabel title = new JLabel("Smart Parking System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setOpaque(true);
        title.setBackground(new Color(44, 62, 80));
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(100, 50));
        add(title, BorderLayout.NORTH);

        // Table
        String[] columnNames = {"Spot #", "Status", "License Plate"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel) {
            // Color rows
            public Component prepareRenderer(TableCellRenderer r, int row, int col) {
                Component c = super.prepareRenderer(r, row, col);
                String status = (String) getValueAt(row, 1);
                if ("Available".equals(status)) {
                    c.setBackground(new Color(198, 239, 206));
                } else {
                    c.setBackground(new Color(255, 199, 206));
                }
                c.setFont(new Font("SansSerif", Font.PLAIN, 14));
                return c;
            }
        };
        table.setRowHeight(25);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setFillsViewportHeight(true);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Control panel
        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBackground(new Color(236, 240, 241));

        JPanel topControls = new JPanel();
        topControls.setBackground(new Color(236, 240, 241));
        plateField = new JTextField(10);
        JButton parkBtn = new JButton("Park Car");
        JButton removeBtn = new JButton("Remove Car");

        styleButton(parkBtn, new Color(46, 204, 113));
        styleButton(removeBtn, new Color(231, 76, 60));

        topControls.add(new JLabel("License Plate:"));
        topControls.add(plateField);
        topControls.add(parkBtn);
        topControls.add(removeBtn);

        resultArea = new JTextArea(3, 40);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        resultArea.setBackground(new Color(250, 250, 210));
        resultArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        resultArea.setLineWrap(true);

        controlPanel.add(topControls, BorderLayout.NORTH);
        controlPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        add(controlPanel, BorderLayout.SOUTH);

        // Actions
        parkBtn.addActionListener(e -> {
            String plate = plateField.getText().trim();
            if (!plate.isEmpty()) {
                parkCar(plate);
                plateField.setText("");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a license plate.");
            }
        });

        removeBtn.addActionListener(e -> {
            String plate = plateField.getText().trim();
            if (!plate.isEmpty()) {
                removeCar(plate);
                plateField.setText("");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a license plate.");
            }
        });

        refreshTable();
        setVisible(true);
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setFocusPainted(false);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable()) {
                tableModel.addRow(new Object[]{spot.getSpotNumber(), "Available", ""});
            } else {
                tableModel.addRow(new Object[]{
                        spot.getSpotNumber(),
                        "Occupied",
                        spot.getParkedCar().getLicensePlate()
                });
            }
        }
    }

    private void parkCar(String licensePlate) {
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable()) {
                Car car = new Car(licensePlate);
                spot.parkCar(car);
                resultArea.setText("✅ Car parked at spot " + spot.getSpotNumber());
                return;
            }
        }
        resultArea.setText("❌ No available spots.");
    }

    private void removeCar(String licensePlate) {
        for (ParkingSpot spot : spots) {
            Car car = spot.getParkedCar();
            if (car != null && car.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                long duration = (System.currentTimeMillis() - car.getEntryTime()) / 1000;
                double fee = calculateFee(duration);
                spot.removeCar();
                resultArea.setText("🚗 Car removed from spot " + spot.getSpotNumber()
                        + "\n⏱ Duration: " + duration + " seconds"
                        + "\n💵 Fee: $" + fee);
                return;
            }
        }
        resultArea.setText("⚠️ Car not found.");
    }

    private double calculateFee(long durationInSeconds) {
        double ratePerSecond = 0.005;
        return Math.round(durationInSeconds * ratePerSecond * 100.0) / 100.0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SmartParkingGUI::new);
    }
}