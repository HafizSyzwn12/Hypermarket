package main;
import java.io.*;
import java.util.*;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainUI extends javax.swing.JFrame {

    /**
     * Creates new form HomeUI
     */
    public MainUI() {
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private CounterChoose counterchoose;

    //TEMPORARY LIST WITHOUT COUNTER NUMBER, WILL ASSIGN TO COUNTER LIST QUEUE
    private ArrayList<CustomerInformation> customerList = new ArrayList();
    private ArrayList<String> listCustID = new ArrayList<String>();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MAIN");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("HyperMarket");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(688, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setText("WELCOME TO HYPERMARKET");

        jButton1.setBackground(new java.awt.Color(153, 204, 255));
        jButton1.setText("START");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(193, 193, 193))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(256, 256, 256))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121)
                .addComponent(jLabel7)
                .addGap(38, 38, 38)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    //GET ONLY SELECTED ITEM BY CUSTOMER ID TO ADD TO COUNTER
    public List filteritemdatacust(String custID) {
        Predicate<CustomerInformation> itemSelectCondition = itemsCond -> itemsCond.getCustID().equalsIgnoreCase(custID);
        List itemsCustomer = customerList.stream().filter(itemSelectCondition).collect(Collectors.toList());
        return itemsCustomer;
    }
    
    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        jButton1.setEnabled(false);
        //1. READ TEXTFILE AND ADD TO TEMPORARY ARRAY
        try {
            BufferedReader br = new BufferedReader(new FileReader("cashierapp.txt"));
            String line = br.readLine();

            String customerID = "";
            String customerIC = "";
            String customerName = "";
            String currentCustID = "";

            while (line != null) {
                //READ DATA EACH LINE
                StringTokenizer st = new StringTokenizer(line, ",");
                customerID = st.nextToken();
                customerIC = st.nextToken();
                customerName = st.nextToken();
                String itemID = st.nextToken();
                String itemName = st.nextToken();
                double itemPrice = Double.parseDouble(st.nextToken());
                String datePurchase = st.nextToken();
                if (!customerID.equalsIgnoreCase(currentCustID)) {
                    listCustID.add(customerID);
                    currentCustID = customerID;
                }

                customerList.add(new CustomerInformation(customerID, customerIC, customerName, null, itemID, itemName, itemPrice, datePurchase));

                line = br.readLine();
            }
            br.close();

            //ADD TO NEW QUEUE
            int counterswitching = 1;
            for (int i = 0; i < listCustID.size(); i++) {
                List filtereditemcustomer = filteritemdatacust(listCustID.get(i));

                if (filtereditemcustomer.size() <= 5) {
                    if (counterswitching == 1) {
                        for (int j = 0; j < filtereditemcustomer.size(); j++) {
                            CustomerInformation itemdata = (CustomerInformation) filtereditemcustomer.get(j);
                            String custID = itemdata.getCustID();
                            String custIC = itemdata.getCustIC();
                            String custName = itemdata.getCustName();
                            String itemID = itemdata.getItemID();
                            String itemName = itemdata.getItemName();
                            Double itemPrice = itemdata.getitemPrice();
                            String datePurchased = itemdata.getDatePurchase();

                            main.Main.getCounter1().add(new CustomerInformation(custID, custIC, custName, "counter1", itemID, itemName, itemPrice, datePurchased));
                        }
                        counterswitching = 2;
                    } else if (counterswitching == 2) {
                        for (int j = 0; j < filtereditemcustomer.size(); j++) {
                            CustomerInformation itemdata = (CustomerInformation) filtereditemcustomer.get(j);
                            String custID = itemdata.getCustID();
                            String custIC = itemdata.getCustIC();
                            String custName = itemdata.getCustName();
                            String itemID = itemdata.getItemID();
                            String itemName = itemdata.getItemName();
                            Double itemPrice = itemdata.getitemPrice();
                            String datePurchased = itemdata.getDatePurchase();

                            main.Main.getCounter2().add(new CustomerInformation(custID, custIC, custName, "counter2", itemID, itemName, itemPrice, datePurchased));

                        }
                        counterswitching = 1;
                    }
                } else {
                    for (int j = 0; j < filtereditemcustomer.size(); j++) {
                        CustomerInformation itemdata = (CustomerInformation) filtereditemcustomer.get(j);
                        String custID = itemdata.getCustID();
                        String custIC = itemdata.getCustIC();
                        String custName = itemdata.getCustName();
                        String itemID = itemdata.getItemID();
                        String itemName = itemdata.getItemName();
                        Double itemPrice = itemdata.getitemPrice();
                        String datePurchased = itemdata.getDatePurchase();

                        main.Main.getCounter3().add(new CustomerInformation(custID, custIC, custName, "counter3", itemID, itemName, itemPrice, datePurchased));

                    }
                }
            }

            counterchoose = new CounterChoose();
            counterchoose.setVisible(true);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton1MouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
