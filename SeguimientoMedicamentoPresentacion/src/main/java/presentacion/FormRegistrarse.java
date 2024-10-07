/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import casoDeUsoRegistrarse.CasoDeUsoRegistrarse;
import casoDeUsoRegistrarse.CasoDeUsoRegistrarseException;
import casoDeUsoRegistrarse.ICasoDeUsoRegistrarse;
import dto.UsuarioDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Laboratorios
 */
public class FormRegistrarse extends javax.swing.JFrame {

    /**
     * Creates new form FormRegistrarse
     */
    public FormRegistrarse() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panFondo = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        labelSubTitulo = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        labelContraseña = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JTextField();
        btnIniciarSesion = new javax.swing.JButton();
        labelLeyendaIniciar = new javax.swing.JLabel();
        btnRegistrarse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panFondo.setBackground(new java.awt.Color(204, 255, 255));

        labelTitulo.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        labelTitulo.setText("Increible Sistema de Toma de Medicamentos");

        labelSubTitulo.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        labelSubTitulo.setText("Registrarse");

        labelUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        labelUsuario.setText("Usuario:");

        txtUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        labelContraseña.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        labelContraseña.setText("Contraseña:");

        txtContraseña.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        btnIniciarSesion.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnIniciarSesion.setText("Iniciar Sesion");
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        labelLeyendaIniciar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        labelLeyendaIniciar.setText("Si ya tienes una cuenta puedes:");

        btnRegistrarse.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnRegistrarse.setText("Confirmar");
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panFondoLayout = new javax.swing.GroupLayout(panFondo);
        panFondo.setLayout(panFondoLayout);
        panFondoLayout.setHorizontalGroup(
            panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panFondoLayout.createSequentialGroup()
                .addGroup(panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panFondoLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTitulo)
                            .addComponent(labelSubTitulo)))
                    .addGroup(panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panFondoLayout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addGroup(panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtContraseña, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panFondoLayout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addGroup(panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelUsuario)
                                .addComponent(labelContraseña)
                                .addGroup(panFondoLayout.createSequentialGroup()
                                    .addGap(68, 68, 68)
                                    .addComponent(labelLeyendaIniciar))))))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panFondoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnIniciarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(138, 138, 138))
        );
        panFondoLayout.setVerticalGroup(
            panFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panFondoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelSubTitulo)
                .addGap(29, 29, 29)
                .addComponent(labelUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelContraseña)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnRegistrarse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLeyendaIniciar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIniciarSesion)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        FormIniciarSesion formIniciarSesion = new FormIniciarSesion();
        formIniciarSesion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarseActionPerformed
        if (txtUsuario.getText().isEmpty() || txtContraseña.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "¡Todos los campos deben estar llenos!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            UsuarioDTO usuarioDTO = new UsuarioDTO(
                    txtUsuario.getText(),
                    txtContraseña.getText()
            );
            ICasoDeUsoRegistrarse casoDeUsoRegistrarse = new CasoDeUsoRegistrarse();
            
            try {
                if(casoDeUsoRegistrarse.registrarse(usuarioDTO)){
                    JOptionPane.showMessageDialog(null, "Te registraste correctamente. Inicia Sesion!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    
                    FormIniciarSesion formIniciarSesion = new FormIniciarSesion();
                    formIniciarSesion.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Datos incorrectos. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (CasoDeUsoRegistrarseException ex) {
                Logger.getLogger(FormRegistrarse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnRegistrarseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnRegistrarse;
    private javax.swing.JLabel labelContraseña;
    private javax.swing.JLabel labelLeyendaIniciar;
    private javax.swing.JLabel labelSubTitulo;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPanel panFondo;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
