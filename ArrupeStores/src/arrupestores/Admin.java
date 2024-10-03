package arrupestores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Admin extends javax.swing.JFrame {
       public Admin() {
        initComponents();
        jTabbedPane1.addTab("Editar", jPanel5);
        jTabbedPane1.setEnabledAt(jTabbedPane1.indexOfTab("Editar"), false); // Ocultar el tab de Editar

        jTabbedPane1.addTab("Eliminar", jPanel6);
        jTabbedPane1.setEnabledAt(jTabbedPane1.indexOfTab("Eliminar"), false); // Ocultar el tab de Eliminar

        // Cargar datos de usuarios al iniciar la ventana
                // Cargar datos de usuarios al iniciar la ventana
             jTabbedPane1.addTab("Devoluciones", jPanel7);
        jTabbedPane1.setEnabledAt(jTabbedPane1.indexOfTab("Devoluciones"), false);
        
        
             jTabbedPane1.addTab("Devoluciones", jPanel8);
        jTabbedPane1.setEnabledAt(jTabbedPane1.indexOfTab("Devoluciones"), false);

        // Cargar datos al iniciar la ventana
        cargarUsuarios();
        cargarOrdenes();
        cargarProductos();
        cargarDevoluciones();

    }
    private void cargarUsuarios() {
        String consulta = "SELECT u.UsuarioID, u.Nombre, u.Apellido, u.Email, c.NombreCargo AS Cargo "
                        + "FROM Usuarios u "
                        + "INNER JOIN Cargos c ON u.CargoID = c.CargoID";

        try (Connection conn = ConexionSQLServer.conectar();
             PreparedStatement stmt = conn.prepareStatement(consulta);
             ResultSet rs = stmt.executeQuery()) {

            DefaultTableModel model = new DefaultTableModel();
            jTable2.setModel(model);

            model.addColumn("ID");
            model.addColumn("Nombre");
            model.addColumn("Apellido");
            model.addColumn("Email");
            model.addColumn("Cargo");

            while (rs.next()) {
                int id = rs.getInt("UsuarioID");
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellido");
                String email = rs.getString("Email");
                String cargo = rs.getString("Cargo");

                model.addRow(new Object[]{id, nombre, apellido, email, cargo});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar usuarios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
private void cargarOrdenes() {
    String consulta = "SELECT p.PedidoID, u.Nombre AS Usuario, p.FechaPedido, p.Estado, p.Total "
                    + "FROM Pedidos p "
                    + "INNER JOIN Usuarios u ON p.UsuarioID = u.UsuarioID";

    try (Connection conn = ConexionSQLServer.conectar();
         PreparedStatement stmt = conn.prepareStatement(consulta);
         ResultSet rs = stmt.executeQuery()) {

        DefaultTableModel model = new DefaultTableModel();
        jTable4.setModel(model); // Asignar el modelo a jTable3

        model.addColumn("ID");
        model.addColumn("Usuario");
        model.addColumn("Fecha");
        model.addColumn("Estado");
        model.addColumn("Total");

        while (rs.next()) {
            int id = rs.getInt("PedidoID");
            String usuario = rs.getString("Usuario");
            String fecha = rs.getString("FechaPedido");
            String estado = rs.getString("Estado");
            double total = rs.getDouble("Total");

            model.addRow(new Object[]{id, usuario, fecha, estado, total});
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar órdenes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
private void cargarProductos() {
    String consulta = "SELECT ProductoID, Nombre, Precio FROM Productos";

    try (Connection conn = ConexionSQLServer.conectar();
         PreparedStatement stmt = conn.prepareStatement(consulta);
         ResultSet rs = stmt.executeQuery()) {

        DefaultTableModel model = new DefaultTableModel();
        jTable1.setModel(model);

        model.addColumn("ID Producto");
        model.addColumn("Nombre");
        model.addColumn("Precio");

        while (rs.next()) {
            int idProducto = rs.getInt("ProductoID");
            String nombre = rs.getString("Nombre");
            double precio = rs.getDouble("Precio");

            model.addRow(new Object[]{idProducto, nombre, precio});
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void cargarDatosUsuario(int idUsuario) {
        String consulta = "SELECT Nombre, Apellido, Email FROM Usuarios WHERE UsuarioID = ?";

        try (Connection conn = ConexionSQLServer.conectar();
             PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellido");
                String email = rs.getString("Email");

                txtNombre.setText(nombre);
                txtApellido.setText(apellido);
                txtEmail.setText(email);

            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el usuario con ID: " + idUsuario, "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos del usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
private void actualizarUsuario(int idUsuario, String nombre, String apellido, String email) {
    String consulta = "UPDATE Usuarios SET Nombre = ?, Apellido = ?, Email = ? WHERE UsuarioID = ?";

    try (Connection conn = ConexionSQLServer.conectar();
         PreparedStatement stmt = conn.prepareStatement(consulta)) {

        stmt.setString(1, nombre);
        stmt.setString(2, apellido);
        stmt.setString(3, email);
        stmt.setInt(4, idUsuario);

        int filasAfectadas = stmt.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.");
            
            // Cambiar a la pestaña de Usuarios después de actualizar
            jTabbedPane1.setSelectedIndex(jTabbedPane1.indexOfTab("Usuarios"));
            
            cargarUsuarios(); // Actualiza la tabla jTable2 después de la modificación
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al actualizar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    private void eliminarUsuario(int idUsuario) {
        String consulta = "DELETE FROM Usuarios WHERE UsuarioID = ?";

        try (Connection conn = ConexionSQLServer.conectar();
             PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setInt(1, idUsuario);
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
                cargarUsuarios(); // Llama al método cargarUsuarios() para actualizar la tabla jTable2
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void agregarProducto(String nombre, String descripcion, double precio, int stock, int categoriaID, int proveedorID) {
    String consulta = "INSERT INTO Productos (Nombre, Descripción, Precio, Stock, CategoriaID, ProveedorID) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection conn = ConexionSQLServer.conectar();
         PreparedStatement stmt = conn.prepareStatement(consulta)) {

        stmt.setString(1, nombre);
        stmt.setString(2, descripcion);
        stmt.setDouble(3, precio);
        stmt.setInt(4, stock);
        stmt.setInt(5, categoriaID);
        stmt.setInt(6, proveedorID);

        int filasAfectadas = stmt.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(this, "Producto agregado correctamente.");
            cargarProductos(); // Actualiza la tabla de productos en la pestaña "Productos"
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo agregar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al agregar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    private void cargarDevoluciones() {
    String consulta = "SELECT * FROM Devoluciones"; // Ajusta la consulta SQL según tu estructura de base de datos

    try (Connection conn = ConexionSQLServer.conectar();
         PreparedStatement stmt = conn.prepareStatement(consulta);
         ResultSet rs = stmt.executeQuery()) {

        DefaultTableModel model = new DefaultTableModel();
        jTable5.setModel(model);

        model.addColumn("ID Devolución");
        model.addColumn("Fecha");
        model.addColumn("Producto");
        model.addColumn("Motivo");

        while (rs.next()) {
            int idDevolucion = rs.getInt("DevolucionID");
            String fecha = rs.getString("Fecha");
            String producto = rs.getString("Producto");
            String motivo = rs.getString("Motivo");

            model.addRow(new Object[]{idDevolucion, fecha, producto, motivo});
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar devoluciones: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        Editar = new javax.swing.JButton();
        Eliminar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        Devoluciones = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Agregar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        Actualizar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        usuarios = new javax.swing.JButton();
        Ordenes = new javax.swing.JButton();
        Productos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        Editar.setText("Editar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });

        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(Editar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Eliminar)
                .addGap(147, 147, 147))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Editar)
                    .addComponent(Eliminar))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Usuarios", jPanel3);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable4);

        Devoluciones.setText("Devoluciones");
        Devoluciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DevolucionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(Devoluciones)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Devoluciones)
                .addGap(0, 62, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ordenes", jPanel4);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 260));

        Agregar.setText("Agregar");
        Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarActionPerformed(evt);
            }
        });
        jPanel2.add(Agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, -1, -1));

        jTabbedPane1.addTab("Productos", jPanel2);

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        Actualizar.setText("Actualizar");
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(252, 252, 252)
                            .addComponent(Actualizar))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(247, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addComponent(Actualizar)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab4", jPanel5);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab5", jPanel6);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab6", jPanel7);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 407, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab7", jPanel8);

        usuarios.setText("Usuarios");
        usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuariosActionPerformed(evt);
            }
        });

        Ordenes.setText("Ordenes");
        Ordenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrdenesActionPerformed(evt);
            }
        });

        Productos.setText("Productos");
        Productos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Productos)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Ordenes)
                        .addComponent(usuarios)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(usuarios)
                .addGap(34, 34, 34)
                .addComponent(Productos)
                .addGap(34, 34, 34)
                .addComponent(Ordenes)
                .addContainerGap(269, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductosActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_ProductosActionPerformed

    private void usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuariosActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_usuariosActionPerformed

    private void OrdenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrdenesActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_OrdenesActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
         int filaSeleccionada = jTable2.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para editar.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int idUsuario = (int) jTable2.getValueAt(filaSeleccionada, 0);
            cargarDatosUsuario(idUsuario);
            jTabbedPane1.setSelectedIndex(jTabbedPane1.indexOfTab("Editar"));
        }
        
        
    }//GEN-LAST:event_EditarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = jTable2.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int idUsuario = (int) jTable2.getValueAt(filaSeleccionada, 0);
            eliminarUsuario(idUsuario);
        }
        
        
    }//GEN-LAST:event_EliminarActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        // TODO add your handling code here:
        String nombre = txtNombre.getText().trim();
    String apellido = txtApellido.getText().trim();
    String email = txtEmail.getText().trim();

    // Validar que los campos no estén vacíos
    if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Obtener la fila seleccionada en la tabla de usuarios
    int filaSeleccionada = jTable2.getSelectedRow();

    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione un usuario para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Obtener el ID del usuario desde la tabla
    int idUsuario = (int) jTable2.getValueAt(filaSeleccionada, 0);

    // Llamar al método para actualizar el usuario en la base de datos
    actualizarUsuario(idUsuario, nombre, apellido, email);

    // Limpiar los campos después de la actualización (opcional)
    txtNombre.setText("");
    txtApellido.setText("");
    txtEmail.setText("");
    }//GEN-LAST:event_ActualizarActionPerformed

    private void DevolucionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DevolucionesActionPerformed
     jTabbedPane1.setEnabledAt(jTabbedPane1.indexOfTab("Devoluciones"), true);
        jTabbedPane1.setSelectedIndex(jTabbedPane1.indexOfTab("Devoluciones"));

        // Cargar los datos de Devoluciones al mostrarse la pestaña
        cargarDevoluciones();
    }//GEN-LAST:event_DevolucionesActionPerformed

    private void AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setEnabledAt(jTabbedPane1.indexOfTab("Agregar"), true);
         jTabbedPane1.setSelectedIndex(jTabbedPane1.indexOfTab("Agregar"));
    }//GEN-LAST:event_AgregarActionPerformed
    
    
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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JButton Agregar;
    private javax.swing.JButton Devoluciones;
    private javax.swing.JButton Editar;
    private javax.swing.JButton Eliminar;
    private javax.swing.JButton Ordenes;
    private javax.swing.JButton Productos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JButton usuarios;
    // End of variables declaration//GEN-END:variables
}
