package Client.GUI;

import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.*;

import Client.Client;
import Client.ClientServer;

import java.awt.*;
import java.awt.event.*;

public class Signup extends javax.swing.JFrame {

        // Variables declaration - do not modify
        private javax.swing.JButton signupBtn;
        private javax.swing.JButton loginBtn;
        private javax.swing.JLabel title;
        private javax.swing.JLabel usernameLabel;
        private javax.swing.JLabel passwordLabel;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel background;
        private javax.swing.JPasswordField passwordField;
        private javax.swing.JTextField usernameField;
        // End of variables declaration

        public Signup() {
                initComponents();
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                background = new javax.swing.JPanel();
                title = new javax.swing.JLabel();
                usernameLabel = new javax.swing.JLabel();
                usernameField = new javax.swing.JTextField();
                passwordLabel = new javax.swing.JLabel();
                passwordField = new javax.swing.JPasswordField();
                signupBtn = new javax.swing.JButton();
                loginBtn = new javax.swing.JButton();

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 400, Short.MAX_VALUE));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 146, Short.MAX_VALUE));

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setBackground(new java.awt.Color(255, 255, 255));

                background.setBackground(new java.awt.Color(255, 255, 255));
                background.setPreferredSize(new java.awt.Dimension(500, 500));

                title.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
                title.setText("Sign Up");
                title.setToolTipText("");

                usernameLabel.setLabelFor(usernameField);
                usernameLabel.setText("Username");

                passwordLabel.setLabelFor(passwordField);
                passwordLabel.setText("Password");

                signupBtn.setText("Sign Up");
                signupBtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                handleSignup(evt);
                        }
                });

                loginBtn.setText("Login");
                loginBtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                handleChangePageLogin(evt);
                        }
                });

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(background);
                background.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(193, 193, 193)
                                                                .addComponent(title)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(90, Short.MAX_VALUE)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(usernameLabel)
                                                                                .addComponent(passwordLabel))
                                                                .addGap(28, 28, 28)
                                                                .addGroup(jPanel2Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(signupBtn,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                100,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                43,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(loginBtn,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                100,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(passwordField)
                                                                                .addComponent(usernameField))
                                                                .addGap(86, 86, 86)));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(46, 46, 46)
                                                                .addComponent(title)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.CENTER)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(usernameLabel)
                                                                                                .addGap(29, 29, 29)
                                                                                                .addComponent(passwordLabel))
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(usernameField,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(23, 23, 23)
                                                                                                .addComponent(passwordField,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(29, 29, 29)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(signupBtn)
                                                                                .addComponent(loginBtn))
                                                                .addContainerGap(45, Short.MAX_VALUE)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, 270,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));

                pack();
        }

        private void handleSignup(java.awt.event.ActionEvent evt) {
                String inputUsername = usernameField.getText();
                String inputPassword = String.valueOf(passwordField.getPassword());

                Boolean output = ClientServer.handleSignup(inputUsername, inputPassword);
                if (output) {
                        Client.openDashboard();
                        this.dispose();
                } else {
                        showMessageDialog(null, "Username Taken!");
                        return;
                }
        }

        private void handleChangePageLogin(java.awt.event.ActionEvent evt) {
                Login form = new Login();
                form.setVisible(true);
                form.pack();
                form.setLocationRelativeTo(null);

                this.dispose();
        }
}