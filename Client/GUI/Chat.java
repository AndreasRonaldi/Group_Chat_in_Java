package Client.GUI;

import java.awt.Graphics;

import Client.Client;
import Client.ClientServer;

public class Chat extends javax.swing.JFrame {
        // Variables declaration - do not modify
        private javax.swing.JButton jButton1;
        private javax.swing.JButton jButton2;
        private javax.swing.JButton jButton3;
        private javax.swing.JButton jButton4;
        public javax.swing.JLabel jLabel1;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JScrollPane jScrollPane1;
        public javax.swing.JTextArea jTextArea1;
        private javax.swing.JTextField jTextField1;
        // End of variables declaration

        public Chat() {
                initComponents();
        }

        private void initComponents() {

                jPanel2 = new javax.swing.JPanel();
                jTextField1 = new javax.swing.JTextField();
                jButton1 = new javax.swing.JButton();
                jButton2 = new javax.swing.JButton();
                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jButton3 = new javax.swing.JButton();
                jButton4 = new javax.swing.JButton();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTextArea1 = new javax.swing.JTextArea();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setBackground(new java.awt.Color(238, 238, 238));

                jPanel2.setBackground(new java.awt.Color(104, 109, 118));

                jTextField1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                jTextField1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                handleSendTextField(evt);
                        }
                });

                jButton1.setText("Send");
                jButton1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                handleSendMessage(evt);
                        }
                });

                jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("../Image/mdi--paperclip (1).png"))); // NOI18N
                jButton2.setToolTipText("");
                jButton2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                handleSendFile(evt);
                        }
                });

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jTextField1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                300,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jButton1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                75,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jButton2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                75,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap()));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jTextField1)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jButton1,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                28,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jButton2)
                                                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                                .addContainerGap()));

                jPanel1.setBackground(new java.awt.Color(104, 109, 118));

                jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setText("Group Name");

                jButton3.setText("...");
                jButton3.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                handleOpenMemberList(evt);
                        }
                });

                jButton4.setText("<");
                jButton4.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                handleExitGroup(evt);
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(24, 24, 24)
                                                                .addComponent(jButton4)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel1)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jButton3)
                                                                .addGap(16, 16, 16)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(16, 16, 16)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel1)
                                                                                .addComponent(jButton3)
                                                                                .addComponent(jButton4))
                                                                .addContainerGap(18, Short.MAX_VALUE)));

                jTextArea1.setColumns(20);
                jTextArea1.setRows(5);
                jTextArea1.setEditable(false);
                jScrollPane1.setViewportView(jTextArea1);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jScrollPane1)
                                                                .addContainerGap()));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                452, Short.MAX_VALUE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));

                pack();
        }// </editor-fold>

        private void handleOpenMemberList(java.awt.event.ActionEvent evt) {
                ClientServer.handleOpenMember();
        }

        private void handleSendFile(java.awt.event.ActionEvent evt) {

        }

        private void handleSendMessage(java.awt.event.ActionEvent evt) {
                handleSend();
        }

        private void handleSendTextField(java.awt.event.ActionEvent evt) {
                handleSend();
        }

        private void handleSend() {
                String input = jTextField1.getText();
                if (!input.trim().isEmpty()) {
                        ClientServer.handleSendMsg(input.trim());
                        jTextField1.setText("");
                }
        }

        private void handleExitGroup(java.awt.event.ActionEvent evt) {
                ClientServer.handleExitGroup();
        }

        public void displayMessage(String msg) {
                jTextArea1.append(msg + "\n");
        }
}
