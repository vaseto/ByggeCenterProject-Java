/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUILayer;

import ControlLayer.ContractorCtrl;
import ControlLayer.CustomerCtrl;
import ControlLayer.EmployeeCtrl;
import ControlLayer.ItemsReorderCtrl;
import ControlLayer.MachineCtrl;
import ControlLayer.ProductCtrl;
import ControlLayer.ProductSaleCtrl;
import ModelLayer.Contractor;
import ModelLayer.Customer;
import ModelLayer.Employee;
import ModelLayer.Machine;
import ModelLayer.Product;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author viva
 */
public class SeeAll extends javax.swing.JFrame {
    private Contractor contractor;
    private EmployeeCtrl empCtrl;
    private CustomerCtrl custCtrl;
    private ContractorCtrl contCtrl;
    private Registration register;
    private ProductCtrl prdCtrl;
    private MachineCtrl machCtrl;
    private String representative;
    private EmployeeUI empUI;
    private String difference;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    private String id;

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }

    /**
     * Creates new form SeeAll
     */
    public SeeAll() {
        initComponents();
        empUI = EmployeeUI.getInstance();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        list1 = new java.awt.List();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        list1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                list1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(list1, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void list1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_list1ActionPerformed
        register = new Registration();
        String player = list1.getSelectedItem();
        String s = list1.getSelectedItem();
        switch (representative) {
            case "employees":
                player = player.substring(player.indexOf("Id"), player.indexOf("| bankAcc"));
                player = player.substring(player.indexOf(" ")).trim();
                break;
            case "customers":
                player = player.substring(player.indexOf("Id"), player.indexOf("| Group"));
                player = player.substring(player.indexOf(" ")).trim();
                break;
            case "contractors":
                player = player.substring(player.indexOf("Id"), player.lastIndexOf(" "));
                player = player.substring(player.indexOf(" ")).trim();
                break;
            case "products":
            case "machines":
            case "Products for reordering":
                player = player.substring(player.indexOf("barcode"), player.indexOf("| price"));
                player = player.substring(player.indexOf(" ")).trim();
                break;
            case "orders":
                player = player.substring(player.indexOf("ordered :"),player.indexOf("Quantity"));
                player = player.substring(player.indexOf(" "));
               try{
                   s = s.substring(s.indexOf("Quantity reordered "),s.lastIndexOf(" "));
               }catch(Exception e){
                   System.out.println(s);
               }
               
              break;
                
        }
        if (difference != null && difference.equals("diff")){
            this.setVisible(false);
            empUI.setVisible(false);
            empUI.setCust(custCtrl.findCustomer(player));
            renewEmpUI();
        } else if (difference == null) {
            register.fillFieldsWhenFound(representative, player);
            register.changeName();
            this.setVisible(false);
         }else if(difference.equals("cont")){
            empUI.setCont(contCtrl.findContractor(player));
        } else {
            this.setVisible(false);
            empUI.setVisible(false);
            empUI.getBarcodeField().setText(player);
            renewEmpUI();
        }
        if(representative.equals("orders")){
            ItemsReorderCtrl itRC = new ItemsReorderCtrl();
            ProductCtrl prCtrl = new ProductCtrl();
            
            try{
                itRC.addProductToStock(prCtrl.findProductByName(player),Integer.parseInt(s));
                JOptionPane.showMessageDialog(null,"Order accepted. Products added to stock");
                contractor.getRequestForReorder().remove(prCtrl.getProductByName(player),s);
            }catch(Exception e){
            }
            
        }
        this.setVisible(false);
    }//GEN-LAST:event_list1ActionPerformed

    private void renewEmpUI() {
        empUI.setSize(this.getSize());
        empUI.repaint();
        empUI.setVisible(true);
    }

    public String addToList(String str) {
        switch (str) {
            case "employees":
                empCtrl = new EmployeeCtrl();
                for (Employee emp : empCtrl.getEmployees()) {
                    list1.add(emp.toString());
                }
                break;
            case "customers":
                custCtrl = new CustomerCtrl();
                for (Customer cust : custCtrl.getCustomers()) {
                    list1.add(cust.toString());
                }
                break;
            case "contractors":
                contCtrl = new ContractorCtrl();
                for (Contractor cont : contCtrl.getContractors()) {
                    list1.add(cont.toString());
                }
                break;
            case "products":
                prdCtrl = new ProductCtrl();
                for (Product prd : prdCtrl.getProducts()) {
                    list1.add(prd.toString());
                }
                break;
            case "machines":
                machCtrl = new MachineCtrl();
                for (Machine mach : machCtrl.getMachines()) {
                    list1.add(mach.toString());
                }
                break;
            case "Products for reordering":
                prdCtrl = new ProductCtrl();
                for(Product pr : prdCtrl.getProducts()){
                    if(pr.getQuantity()< pr.getMinimum()){
                        list1.add(pr.toString());
                    }
                }
                break;
            case "orders":
                 contCtrl = new ContractorCtrl();
                 Contractor cont = contCtrl.findContractor(id);
                 contractor = cont;
               for(Map.Entry<Product,Integer> entry : cont.getRequestForReorder().entrySet()){
                   list1.add("Product ordered : " + entry.getKey().getName() + " Quantity ordered " + entry.getValue()+ " ");
                }   
               break;
        }
        representative = str;
        return str;
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
            java.util.logging.Logger.getLogger(SeeAll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeeAll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeeAll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeeAll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SeeAll().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private java.awt.List list1;
    // End of variables declaration//GEN-END:variables
}