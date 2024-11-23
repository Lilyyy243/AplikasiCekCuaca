/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.ImageIcon;
import org.json.JSONObject;

/**
 *
 * @author Lila
 */
public class AplikasiCekCuacaSederhana extends javax.swing.JFrame {
    private static final String API_KEY = "306b1529af945758f30a9cbf66026aef";
    private static final String ICONS_PATH = "/icons/";
    private static final int ICON_SIZE = 100; // Add this constant
    private static final String CSV_FILE = "weather_data.csv";
    private javax.swing.table.DefaultTableModel tableModel;
    private java.util.List<String> favoriteCities;
    private java.io.File favoritesFile;

    /**
     * Creates new form AplikasiCekCuacaSederhana
     */
    public AplikasiCekCuacaSederhana() {
        favoriteCities = new java.util.ArrayList<>();
        favoritesFile = new java.io.File("favorite_cities.txt");
        initComponents();
        setupComponents();
        loadFavorites();
        setupTableAndButtons(); // Add this line
        initializeLabel(); // Add this line
    }

    private void setupComponents() {
        setupSimpanButton();
        setupFavoritComboBox();
        setupKeluarButton();
    }

    private void setupSimpanButton() {
        simpanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFavorite();
            }
        });
    }

    private void setupFavoritComboBox() {
        favoritComboBox.removeAllItems();
        favoritComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                    String selectedCity = (String) favoritComboBox.getSelectedItem();
                    if (selectedCity != null) {
                        kotaTextField.setText(selectedCity);
                        checkWeather(selectedCity);
                    }
                }
            }
        });
    }

    private void setupKeluarButton() {
        keluarButton.addActionListener(e -> System.exit(0));
    }

    private void loadFavorites() {
        try {
            favoritComboBox.removeAllItems();
            if (favoritesFile.exists()) {
                java.util.Scanner scanner = new java.util.Scanner(favoritesFile);
                while (scanner.hasNextLine()) {
                    String city = scanner.nextLine().trim();
                    if (!city.isEmpty()) {
                        favoriteCities.add(city);
                        favoritComboBox.addItem(city);
                    }
                }
                scanner.close();
            }
        } catch (Exception e) {
            System.err.println("Error loading favorites: " + e.getMessage());
        }
    }

    private void saveFavorite() {
        try {
            String city = kotaTextField.getText().trim();
            if (city.isEmpty()) {
                city = kotaComboBox.getSelectedItem().toString();
            }

            if (!favoriteCities.contains(city)) {
                favoriteCities.add(city);
                favoritComboBox.addItem(city);

                // Save to file
                java.io.PrintWriter writer = new java.io.PrintWriter(
                        new java.io.FileWriter(favoritesFile, true));
                writer.println(city);
                writer.close();

                javax.swing.JOptionPane.showMessageDialog(this,
                        "Kota " + city + " berhasil ditambahkan ke favorit!");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Kota sudah ada di daftar favorit!");
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Error menyimpan favorit: " + e.getMessage());
        }
    }

    private void setupTableAndButtons() {
        // Setup table
        String[] columns = {"Kota", "Cuaca", "Waktu"};
        tableModel = new javax.swing.table.DefaultTableModel(columns, 0);
        jTable1.setModel(tableModel);
        
        // Setup button handlers
        btnSimpan.addActionListener(e -> saveWeatherData());
        btnMuat1.addActionListener(e -> loadWeatherData());
    }
    
    private void saveWeatherData() {
        try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(CSV_FILE))) {
            // Write header
            writer.println("Kota,Cuaca,Waktu");
            
            // Write data
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String line = String.format("%s,%s,%s",
                    tableModel.getValueAt(i, 0),
                    tableModel.getValueAt(i, 1),
                    tableModel.getValueAt(i, 2));
                writer.println(line);
            }
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Data cuaca berhasil disimpan ke " + CSV_FILE);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Error menyimpan data: " + e.getMessage());
        }
    }
    
    private void loadWeatherData() {
        try {
            java.io.File file = new java.io.File(CSV_FILE);
            if (!file.exists()) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "File data belum ada");
                return;
            }
            
            // Clear existing data
            tableModel.setRowCount(0);
            
            // Read CSV file
            java.io.BufferedReader reader = new java.io.BufferedReader(
                new java.io.FileReader(file));
            
            // Skip header
            String line = reader.readLine();
            
            // Read data
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    tableModel.addRow(data);
                }
            }
            reader.close();
            
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Data cuaca berhasil dimuat");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Error memuat data: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        kotaComboBox = new javax.swing.JComboBox<>();
        cekButton = new javax.swing.JButton();
        keluarButton = new javax.swing.JButton();
        kotaTextField = new javax.swing.JTextField();
        kotaLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pilihkotaLabel = new javax.swing.JLabel();
        simpanButton = new javax.swing.JButton();
        favoritComboBox = new javax.swing.JComboBox<>();
        favoritLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnSimpan = new javax.swing.JButton();
        btnMuat1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aplikasi Cek Cuaca Sederhana", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        kotaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Banjarmasin", "Jakarta", "Surabaya", "Makassar", "Pontianak", "Samarinda", " ", " " }));
        kotaComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                kotaComboBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 9, 0);
        jPanel1.add(kotaComboBox, gridBagConstraints);

        cekButton.setText("Cek");
        cekButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 9, 8);
        jPanel1.add(cekButton, gridBagConstraints);

        keluarButton.setText("Keluar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 9, 8);
        jPanel1.add(keluarButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 9, 0);
        jPanel1.add(kotaTextField, gridBagConstraints);

        kotaLabel.setText("Masukkan Nama Kota");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 11, 9, 0);
        jPanel1.add(kotaLabel, gridBagConstraints);

        jLabel1.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 4;
        jPanel1.add(jLabel1, gridBagConstraints);

        pilihkotaLabel.setText("Pilih Kota");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanel1.add(pilihkotaLabel, gridBagConstraints);

        simpanButton.setText("Simpan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 8);
        jPanel1.add(simpanButton, gridBagConstraints);

        favoritComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 0);
        jPanel1.add(favoritComboBox, gridBagConstraints);

        favoritLabel.setText("Pilih Kota Favorit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 9, 0);
        jPanel1.add(favoritLabel, gridBagConstraints);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        btnSimpan.setText("Simpan Data");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(btnSimpan, gridBagConstraints);

        btnMuat1.setText("Muat Data");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(btnMuat1, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kotaComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String selectedCity = (String) kotaComboBox.getSelectedItem();
            if (selectedCity != null && !selectedCity.trim().isEmpty()) {
                kotaTextField.setText(selectedCity);
                checkWeather(selectedCity);
            }
        }
    }

    private void cekButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekButtonActionPerformed
        String kota = kotaTextField.getText().isEmpty() ? 
                     kotaComboBox.getSelectedItem().toString() : 
                     kotaTextField.getText();
        checkWeather(kota);
    }//GEN-LAST:event_cekButtonActionPerformed

    private void checkWeather(String city) {
        try {
            String urlString = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", 
                                          city, API_KEY);
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            String weather = jsonResponse.getJSONArray("weather")
                                      .getJSONObject(0)
                                      .getString("main");

            String currentTime = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                .format(new java.util.Date());
            
            tableModel.addRow(new Object[]{
                city,
                weather,
                currentTime
            });
            
            displayWeatherIcon(weather);

        } catch (Exception e) {
            jLabel1.setIcon(null);
            jLabel1.setText("Error: " + e.getMessage());
        }
    }

    private void displayWeatherIcon(String weather) {
        String iconName;
        String weatherDescription = weather.toLowerCase();
        
        // Match exact weather conditions with available icons
        if (weatherDescription.contains("clear") || weatherDescription.equals("sun")) {
            iconName = "sunny.png";
        } else if (weatherDescription.contains("rain") && weatherDescription.contains("light")) {
            iconName = "light rain.png";
        } else if (weatherDescription.contains("rain")) {
            iconName = "rain.png";
        } else if (weatherDescription.equals("clouds")) {
            iconName = "cloudy.png";
        } else if (weatherDescription.contains("cloud")) {
            iconName = "partly-cloudy.png";
        } else if (weatherDescription.contains("storm")) {
            iconName = "strom.png";
        } else if (weatherDescription.contains("snow")) {
            iconName = "snow.png";
        } else if (weatherDescription.contains("mist") || weatherDescription.contains("haze")) {
            iconName = "fog.png";
        } else {
            iconName = "default.png";
        }

        try {
            java.awt.Image img = new javax.swing.ImageIcon(getClass().getResource(ICONS_PATH + iconName))
                    .getImage()
                    .getScaledInstance(ICON_SIZE, ICON_SIZE, java.awt.Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img);
            jLabel1.setIcon(icon);
            jLabel1.setText(weather);
        } catch (Exception e) {
            jLabel1.setIcon(null);
            jLabel1.setText("Cannot load icon: " + iconName);
        }
    }

    private void initializeLabel() {
        try {
            String defaultIcon = "default.png";
            java.awt.Image img = new javax.swing.ImageIcon(getClass().getResource(ICONS_PATH + defaultIcon))
                    .getImage()
                    .getScaledInstance(ICON_SIZE, ICON_SIZE, java.awt.Image.SCALE_SMOOTH);
            jLabel1.setIcon(new ImageIcon(img));
            jLabel1.setText("Silahkan pilih kota");
        } catch (Exception e) {
            jLabel1.setIcon(null);
            jLabel1.setText("Silahkan pilih kota");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AplikasiCekCuacaSederhana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AplikasiCekCuacaSederhana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AplikasiCekCuacaSederhana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AplikasiCekCuacaSederhana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AplikasiCekCuacaSederhana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMuat1;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton cekButton;
    private javax.swing.JComboBox<String> favoritComboBox;
    private javax.swing.JLabel favoritLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton keluarButton;
    private javax.swing.JComboBox<String> kotaComboBox;
    private javax.swing.JLabel kotaLabel;
    private javax.swing.JTextField kotaTextField;
    private javax.swing.JLabel pilihkotaLabel;
    private javax.swing.JButton simpanButton;
    // End of variables declaration//GEN-END:variables
}
