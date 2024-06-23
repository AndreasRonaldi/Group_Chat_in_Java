package Client.GUI;

import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;

import Client.Client;
import Client.ClientServer;

public class DetailOwner extends javax.swing.JFrame {
        public DetailOwner() {
                initComponents();
        }

        private void initComponents() {

                jScrollPane2 = new javax.swing.JScrollPane();
                jList1 = new javax.swing.JList<>();
                jPanel2 = new javax.swing.JPanel();
                kickBtn = new javax.swing.JButton();
                delBtn = new javax.swing.JButton();
                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                backBtn = new javax.swing.JButton();

                // setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jList1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jList1.setModel(new javax.swing.AbstractListModel<String>() {
                        String[] strings = {};

                        public int getSize() {
                                return strings.length;
                        }

                        public String getElementAt(int i) {
                                return strings[i];
                        }
                });
                jScrollPane2.setViewportView(jList1);

                jPanel2.setBackground(new java.awt.Color(104, 109, 118));

                kickBtn.setText("Kick");
                kickBtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                kickBtnActionPerformed(evt);
                        }
                });

                delBtn.setText("Delete Group");
                delBtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                delBtnActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(kickBtn,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                186,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(delBtn,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                186,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(kickBtn,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                28,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(delBtn,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap()));

                jPanel1.setBackground(new java.awt.Color(104, 109, 118));

                jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setText("Group Name");

                backBtn.setText("<");
                backBtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                exitBtnActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(24, 24, 24)
                                                                .addComponent(backBtn)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel1)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(16, 16, 16)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel1)
                                                                                .addComponent(backBtn))
                                                                .addContainerGap(18, Short.MAX_VALUE)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jScrollPane2)
                                                                .addContainerGap())
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane2,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                487, Short.MAX_VALUE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {
                Client.closeDetailOwner();
        }

        private void delBtnActionPerformed(java.awt.event.ActionEvent evt) {
                ClientServer.handleRemoveRoom();
                Client.closeDetailOwner();
        }

        private void kickBtnActionPerformed(java.awt.event.ActionEvent evt) {
                String user = jList1.getSelectedValue();
                if (user == null)
                        return;

                System.out.println("> Kick user: " + user);
                ClientServer.handleKickUser(user);
        }

        public void handleChangeModelList(String users) {
                if (users.length() == 2)
                        return;

                List<String> list = Arrays.asList(users.substring(1, users.length() - 1).split(", "));
                DefaultListModel<String> res = new DefaultListModel<>();

                for (String user : list)
                        res.addElement(user);

                jList1.setModel(res);
        }

        // Variables declaration - do not modify
        private javax.swing.JButton backBtn;
        private javax.swing.JButton delBtn;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JList<String> jList1;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JButton kickBtn;
        // End of variables declaration
}
