/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectodesupermercado.Vista;

import proyectodesupermercado.Vista.interfaces.ContentChanger;

import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
/**
 * @author cheerio-pixel
 */
public class AppFrame extends javax.swing.JFrame implements ContentChanger {

    private JPanel currentPanel;
    private ActionListener currentGoBackAction;
    /**
     * Creates new form AppFrame
     */
    public AppFrame() {
        initComponents();
        setIconImage(getLogo());
    }
     public Image getLogo() {
         return Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("logo.png"));
      
      }
    @Override
    public void setContent(JPanel panel, String goBackText, ActionListener goBackAction) {
        if (currentPanel != null) {
            bodyPanel.remove(currentPanel);
        }
        bodyPanel.add(panel);
        currentPanel = panel;

        goBackLabel.setText(goBackText);

        if (currentGoBackAction != null) {
            goBackButton.removeActionListener(currentGoBackAction);
        }
        goBackButton.addActionListener(goBackAction);
        currentGoBackAction = goBackAction;

        revalidate();
        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bodyPanel = new javax.swing.JPanel();
        goBackButton = new javax.swing.JButton();
        goBackLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bodyPanel.setLayout(new java.awt.BorderLayout());

        goBackButton.setText("⮌");
        goBackButton.setFocusPainted(false);
        goBackButton.setFocusable(false);

        goBackLabel.setText("TEMPLATE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(goBackButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(goBackLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goBackButton)
                    .addComponent(goBackLabel))
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(bodyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton goBackButton;
    private javax.swing.JLabel goBackLabel;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
