/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pizzaria;

import javax.swing.JOptionPane;

public class Pizzaria extends javax.swing.JFrame {

    public Pizzaria() {
        initComponents();
        refreshTable();
    }

    public void refreshTable() {
        try (DB db = new DB("bancodados.db")) {
            db.query("SELECT * FROM pizzas");
            String cols[] = { "ID", "PIZZA", "INGREDIENTES"};
            String fields[] = { "ID", "nome", "ingredientes"};
            TableRender.render(table, cols, fields, db);
        } catch (Exception e) {
            e.printStackTrace();
            // Trate a exceção apropriadamente, como logar ou exibir uma mensagem de erro
        }
    }

    private void initComponents() {
        // ... (o restante do seu código gerado automaticamente)

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table);

        jButton1.setText("NOVO PEDIDO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("REMOVER");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("ALTERAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Buscar por nome");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(51, 51, 51)
                                                .addComponent(jButton4))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(52, 52, 52)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jButton1)
                                                        .addComponent(jButton2)
                                                        .addComponent(jButton3))))
                                .addContainerGap(128, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(46, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton4))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton1)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton2)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton3))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14))
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        PizzasCad cadastro = new PizzasCad(this, -1);
        cadastro.setVisible(true);
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        int linha = table.getSelectedRow();
        System.out.println("Linha selecionada: " + linha);
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada. Selecione uma linha!");
            return;
        }
        String codigo = String.valueOf(table.getValueAt(linha, 0));
        System.out.println("Código: " + codigo);
        PizzasCad cadastro = new PizzasCad(this, Integer.parseInt(codigo));
        cadastro.setVisible(true);
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        int linha = table.getSelectedRow();
        System.out.println("Linha selecionada: " + linha);
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada. Selecione uma linha!");
            return;
        }
        String ID = String.valueOf(table.getValueAt(linha, 0));
        DB db = new DB("bancodados.db");
        String query = "DELETE FROM pizzas WHERE ID=" + ID;
        db.execQuery(query);
        refreshTable();
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        String nomeBuscar = txtBusca.getText();
        System.out.println("Texto da busca: " + nomeBuscar);
        DB db = new DB("bancodados.db");
        db.query("SELECT * FROM pizzas WHERE nome LIKE '%" + nomeBuscar + "%'");
        String cols[] = { "ID", "PIZZA", "INGREDIENTES"};
        String fields[] = { "ID", "nome", "ingredientes"};
        TableRender.render(table, cols, fields, db);
        db.closeConnection();
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration
}