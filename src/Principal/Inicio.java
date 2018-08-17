package Principal;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Oscar
 */
public class Inicio extends javax.swing.JFrame {

    public String desicion = ""; //Para saber si se va a guardar un registro nuevo o existente
    public int anterior = 0;
    private final String bd = "ferre_cali";
    private final String user = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/" + bd;
    PreparedStatement ps;
    ResultSet rs;
    public static float total = 0;
    public static float subtotal = 0;
    private Connection con = null;

    public Connection getConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 000 " + ex.getMessage());
        }
        return con;
    }

    // ----------------- TRAER DATOS DE TABLAS ----------------------
    public void ConsultaEmpleado() {
        try {

            con = getConexion();
            ps = con.prepareStatement("SELECT * FROM empleado");
            rs = ps.executeQuery();

            if (rs.next()) {
                jtf_id_empleado.setText(rs.getString("id_empleado"));
                jtf_folio_empleado.setText(rs.getString("folio_empleado"));
                jtf_nombre_empleado.setText(rs.getString("nombre"));
                jtf_ape_pat_empleado.setText(rs.getString("ape_pat"));
                jtf_ape_mat_empleado.setText(rs.getString("ape_mat"));
                jtf_calle_empleado.setText(rs.getString("calle"));
                jtf_numero_empleado.setText(rs.getString("numero"));
                jtf_colonia_empleado.setText(rs.getString("colonia"));
                jtf_telefono_empleado.setText(rs.getString("telefono"));
                jtf_edad_empleado.setText(rs.getString("edad"));
            } else {
                JOptionPane.showMessageDialog(null, "Tabla de Empleados Vacía");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Empleado 006 " + ex.getMessage());
        }
    }

    public void ConsultaProveedor() {
        try {

            con = getConexion();
            ps = con.prepareStatement("SELECT * FROM proveedor");
            rs = ps.executeQuery();

            if (rs.next()) {
                jtf_id_proveedor.setText(rs.getString("id_proveedor"));
                jtf_folio_proveedor.setText(rs.getString("folio_proveedor"));
                jtf_nombre_proveedor.setText(rs.getString("nombre_empresa"));
                jtf_calle_proveedor.setText(rs.getString("calle"));
                jtf_numero_proveedor.setText(rs.getString("numero"));
                jtf_colonia_proveedor.setText(rs.getString("colonia"));
                jtf_telefono_proveedor.setText(rs.getString("telefono"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe el empleado con ese id");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Empleado 007 " + ex.getMessage());
        }
    }

    public void ConsultaProducto() {
        try {

            con = getConexion();
            ps = con.prepareStatement("SELECT * FROM productos INNER JOIN proveedor ON productos.id_proveedor=proveedor.id_proveedor");
            rs = ps.executeQuery();

            if (rs.next()) {
                jtf_id_producto.setText(rs.getString("id_producto"));
                jtf_folio_producto.setText(rs.getString("folio_producto"));
                jtf_nombre_producto.setText(rs.getString("nombre"));
                jtf_existencia_producto.setText(rs.getString("existencia"));
                jtf_existencia_um_producto.setText(rs.getString("existencia_um"));
                jtf_precio_producto.setText(rs.getString("precio_unitario"));
                jtf_marca_producto.setText(rs.getString("marca"));
                jtf_fecha_ingreso_producto.setText(rs.getString("fecha_ingreso"));
                jtf_descripcion_producto.setText(rs.getString("descripcion"));
                jtf_categoria_producto.setText(rs.getString("categoria"));
                jtf_imagen_producto.setText(rs.getString("imagen"));
                jtf_id_proveedor_producto.setText(rs.getString("id_proveedor"));
            } else {
                JOptionPane.showMessageDialog(null, "No existen valores en la Tabla");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error PRODUCTO 001 " + ex.getMessage());
        }
    }

    // ----------------- DATOS DE EMPLEADOS -------------------------
    public void tablaempleado() {
        try {
            Connection con = null;

            con = getConexion();
            ps = con.prepareStatement("SELECT * FROM empleado");
            rs = ps.executeQuery();

            DefaultTableModel modelo = new DefaultTableModel();

            modelo.addColumn("Id Empleado");
            modelo.addColumn("Folio");
            modelo.addColumn("Nombre");
            modelo.addColumn("Ape Pat");
            modelo.addColumn("Ape Mat");
            modelo.addColumn("Calle");
            modelo.addColumn("Numero");
            modelo.addColumn("Colonia");
            modelo.addColumn("Telefono");
            modelo.addColumn("Edad");

            jtb_info_empleado.setModel(modelo);

            String[] datos = new String[10];

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);

                modelo.addRow(datos);
            }
            jtb_info_empleado.setModel(modelo);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error Empleado 001 " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Empleado 001 " + ex.getMessage());
        }
    }

    public void VerDatosEmpleado(boolean ver) {
        jtf_id_empleado.setEnabled(ver);
        jtf_folio_empleado.setEnabled(ver);
        jtf_nombre_empleado.setEnabled(ver);
        jtf_ape_pat_empleado.setEnabled(ver);
        jtf_ape_mat_empleado.setEnabled(ver);
        jtf_calle_empleado.setEnabled(ver);
        jtf_numero_empleado.setEnabled(ver);
        jtf_colonia_empleado.setEnabled(ver);
        jtf_telefono_empleado.setEnabled(ver);
        jtf_edad_empleado.setEnabled(ver);
    }

    // ----------------- DATOS DE PROVEEDOR -------------------------
    public void tablaproveedor() {
        try {
            Connection con = null;

            con = getConexion();
            ps = con.prepareStatement("SELECT * FROM proveedor");
            rs = ps.executeQuery();
            DefaultTableModel modelo = new DefaultTableModel();

            modelo.addColumn("Id Proveedor");
            modelo.addColumn("Folio");
            modelo.addColumn("Nombre");
            modelo.addColumn("Calle");
            modelo.addColumn("Numero");
            modelo.addColumn("Colonia");
            modelo.addColumn("Telefono");

            jtb_infoproveedor.setModel(modelo);

            String[] datos = new String[10];

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);

                modelo.addRow(datos);
            }
            jtb_infoproveedor.setModel(modelo);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error Proveedor 002 " + ex.getMessage());
        }
    }

    public void VerDatosProveedor(boolean ver) {
        jtf_id_proveedor.setEnabled(ver);
        jtf_folio_proveedor.setEnabled(ver);
        jtf_nombre_proveedor.setEnabled(ver);
        jtf_calle_proveedor.setEnabled(ver);
        jtf_numero_proveedor.setEnabled(ver);
        jtf_colonia_proveedor.setEnabled(ver);
        jtf_telefono_proveedor.setEnabled(ver);
    }

    // ----------------- DATOS DE PRODUCTO -------------------------
    public void tablaproducto() {
        try {
            Connection con = null;

            con = getConexion();
            ps = con.prepareStatement("SELECT * FROM productos INNER JOIN proveedor ON productos.id_proveedor=proveedor.id_proveedor");
            rs = ps.executeQuery();

            DefaultTableModel modelo = new DefaultTableModel();

            modelo.addColumn("ID");
            modelo.addColumn("Folio");
            modelo.addColumn("Nombre");
            modelo.addColumn("Existencia");
            modelo.addColumn("Exist UM");
            modelo.addColumn("Precio Unitario");
            modelo.addColumn("Marca");
            modelo.addColumn("Fecha");
            modelo.addColumn("Descripcion");
            modelo.addColumn("Categoria");
            modelo.addColumn("IMG");
            modelo.addColumn("TAM IMG");
            modelo.addColumn("ID Proveedor");

            jtb_info_producto.setModel(modelo);

            String[] datos = new String[13];

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                datos[11] = rs.getString(12);
                datos[12] = rs.getString(13);

                modelo.addRow(datos);
            }
            jtb_info_producto.setModel(modelo);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error Producto 002 " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Empleado 002 " + ex.getMessage());
        }
    }

    public void VerDatosProducto(boolean ver) {
        jtf_id_producto.setEnabled(ver);
        jtf_folio_producto.setEnabled(ver);
        jtf_nombre_producto.setEnabled(ver);
        jtf_existencia_producto.setEnabled(ver);
        jtf_existencia_um_producto.setEnabled(ver);
        jtf_precio_producto.setEnabled(ver);
        jtf_marca_producto.setEnabled(ver);
        jtf_fecha_ingreso_producto.setEnabled(ver);
        jtf_descripcion_producto.setEnabled(ver);
        jtf_categoria_producto.setEnabled(ver);
        jtf_imagen_producto.setEnabled(ver);
        jtf_id_proveedor_producto.setEnabled(ver);
    }

    public Inicio(int a) {
        initComponents();
        this.setLocationRelativeTo(null);//centra la aplicacion
        Conexion con = new Conexion();
        inicio();
        VerDatosEmpleado(false);
        VerDatosProveedor(false);
        VerDatosProducto(false);
        jtf_id_empleado.setVisible(false);
        jtf_id_proveedor.setVisible(false);
        jtf_id_producto.setVisible(false);
        jtb_info_empleado.setEnabled(true);
        jtb_infoproveedor.setEnabled(true);
        jtb_info_producto.setEnabled(true);

        // DESHABILITO BOTONES
        jb_guardar_empleado.setEnabled(false);
        jb_cancelar_empleado.setEnabled(false);
        jb_guardar_proveedor.setEnabled(false);
        jb_cancelar_proveedor.setEnabled(false);
        jb_guardar_producto.setEnabled(false);
        jb_cancelar_producto.setEnabled(false);
        jb_seleccionar_producto.setEnabled(false);

        // MUESTRO TABLAS Y CONTENIDO DE ELLAS
        jtb_info_empleado.setVisible(true);
        tablaempleado();

        jtb_infoproveedor.setVisible(true);
        tablaproveedor();

        jtb_info_producto.setVisible(true);
        tablaproducto();
        //DESHABILITAR JTFS DE VENTAS 
        deshabilitar();
        //
        jtf_total_ventas.setEnabled(false);
        
        if(a == 1){
            
        }else if(a==2){
            jb_productos.setEnabled(false);
            jb_proveedores.setEnabled(false);
            jb_empleados.setEnabled(false);
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

        jb_nuevo7 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jl_nombre_producto11 = new javax.swing.JLabel();
        jtf_nombre_producto3 = new javax.swing.JTextField();
        jl_nombre_producto13 = new javax.swing.JLabel();
        jtf_nombre_producto5 = new javax.swing.JTextField();
        jp_contenedor = new javax.swing.JPanel();
        jp_menu = new javax.swing.JPanel();
        jb_productos = new javax.swing.JButton();
        jb_home = new javax.swing.JButton();
        jb_ventas = new javax.swing.JButton();
        jb_proveedores = new javax.swing.JButton();
        lj_logo = new javax.swing.JLabel();
        jb_empleados = new javax.swing.JButton();
        jp_modulos = new javax.swing.JPanel();
        jp_home = new javax.swing.JPanel();
        jl_cali_logo = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jp_productos = new javax.swing.JPanel();
        jl_nombre_producto = new javax.swing.JLabel();
        jl_marca_producto = new javax.swing.JLabel();
        jl_precio_producto = new javax.swing.JLabel();
        jl_categoria_producto = new javax.swing.JLabel();
        jtf_nombre_producto = new javax.swing.JTextField();
        jtf_precio_producto = new javax.swing.JTextField();
        jtf_marca_producto = new javax.swing.JTextField();
        jtf_categoria_producto = new javax.swing.JTextField();
        jb_nuevo_producto = new javax.swing.JButton();
        jb_editar_producto = new javax.swing.JButton();
        jb_guardar_producto = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtb_info_producto = new javax.swing.JTable();
        jl_id_producto = new javax.swing.JLabel();
        jtf_id_producto = new javax.swing.JTextField();
        jl_folio_producto = new javax.swing.JLabel();
        jtf_folio_producto = new javax.swing.JTextField();
        jl_existencia_producto = new javax.swing.JLabel();
        jtf_existencia_producto = new javax.swing.JTextField();
        jl_existencia_um_producto = new javax.swing.JLabel();
        jtf_existencia_um_producto = new javax.swing.JTextField();
        jl_fecha_ingreso_producto = new javax.swing.JLabel();
        jtf_fecha_ingreso_producto = new javax.swing.JTextField();
        jl_descripcion_producto = new javax.swing.JLabel();
        jtf_descripcion_producto = new javax.swing.JTextField();
        jl_id_proveedor_producto = new javax.swing.JLabel();
        jtf_id_proveedor_producto = new javax.swing.JTextField();
        jb_buscar_producto = new javax.swing.JButton();
        jtf_buscar_producto = new javax.swing.JTextField();
        jl_imagen_producto = new javax.swing.JLabel();
        jtf_imagen_producto = new javax.swing.JTextField();
        jb_eliminar_producto = new javax.swing.JButton();
        jb_cancelar_producto = new javax.swing.JButton();
        jb_seleccionar_producto = new javax.swing.JButton();
        jl_foto_producto = new javax.swing.JLabel();
        jp_ventas = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jl_nombre_producto1 = new javax.swing.JLabel();
        jtf_cambio_ventas = new javax.swing.JTextField();
        jl_nombre_producto2 = new javax.swing.JLabel();
        js_cantidad_ventas = new javax.swing.JSpinner();
        jl_nombre_producto3 = new javax.swing.JLabel();
        jtf_buscar_ventas = new javax.swing.JTextField();
        jl_nombre_producto4 = new javax.swing.JLabel();
        jl_nombre_producto5 = new javax.swing.JLabel();
        jl_nombre_producto6 = new javax.swing.JLabel();
        jtf_pu_ventas = new javax.swing.JTextField();
        jtf_efectivo_ventas = new javax.swing.JTextField();
        jtf_total_ventas = new javax.swing.JTextField();
        jl_nombre_producto7 = new javax.swing.JLabel();
        jtf_hora_ventas = new javax.swing.JTextField();
        jl_nombre_producto8 = new javax.swing.JLabel();
        jtf_fecha_ventas = new javax.swing.JTextField();
        jb_realizar_ventas = new javax.swing.JButton();
        jb_agregar_ventas = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jt_busqueda_ventas = new javax.swing.JTable();
        jb_nuevo6 = new javax.swing.JButton();
        jl_nombre_producto9 = new javax.swing.JLabel();
        jtf_descripcion_ventas = new javax.swing.JTextField();
        jl_nombre_producto10 = new javax.swing.JLabel();
        jtf_nombre_ventas = new javax.swing.JTextField();
        jb_eliminar_ventas = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_productos = new javax.swing.JTable();
        jp_proveedores = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jl_folio_proveedor = new javax.swing.JLabel();
        jtf_folio_proveedor = new javax.swing.JTextField();
        jl_nombre_proveedor = new javax.swing.JLabel();
        jtf_nombre_proveedor = new javax.swing.JTextField();
        jl_calle_proveedor = new javax.swing.JLabel();
        jtf_calle_proveedor = new javax.swing.JTextField();
        jl_numero_proveedor = new javax.swing.JLabel();
        jtf_numero_proveedor = new javax.swing.JTextField();
        jl_colonia_proveedor = new javax.swing.JLabel();
        jtf_colonia_proveedor = new javax.swing.JTextField();
        jl_telefono_proveedor = new javax.swing.JLabel();
        jtf_telefono_proveedor = new javax.swing.JTextField();
        jl_id_proveedor = new javax.swing.JLabel();
        jtf_id_proveedor = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtb_infoproveedor = new javax.swing.JTable();
        jb_buscar_proveedor = new javax.swing.JButton();
        jtf_buscar_proveedor = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jb_nuevo_proveedor = new javax.swing.JButton();
        jb_editar_proveedor = new javax.swing.JButton();
        jb_eliminar_proveedor = new javax.swing.JButton();
        jb_guardar_proveedor = new javax.swing.JButton();
        jb_cancelar_proveedor = new javax.swing.JButton();
        jp_empleados = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jl_id_empleado = new javax.swing.JLabel();
        jtf_id_empleado = new javax.swing.JTextField();
        jl_folio_empleado = new javax.swing.JLabel();
        jtf_folio_empleado = new javax.swing.JTextField();
        jl_nombre_empleado = new javax.swing.JLabel();
        jl_ape_pat_empleado = new javax.swing.JLabel();
        jl_ape_mat_empleado = new javax.swing.JLabel();
        jl_calle_empleado = new javax.swing.JLabel();
        jl_numero_empleado = new javax.swing.JLabel();
        jl_colonia_empleado = new javax.swing.JLabel();
        jl_telefono_empleado = new javax.swing.JLabel();
        jl_edad_empleado = new javax.swing.JLabel();
        jtf_nombre_empleado = new javax.swing.JTextField();
        jtf_ape_pat_empleado = new javax.swing.JTextField();
        jtf_ape_mat_empleado = new javax.swing.JTextField();
        jtf_calle_empleado = new javax.swing.JTextField();
        jtf_numero_empleado = new javax.swing.JTextField();
        jtf_colonia_empleado = new javax.swing.JTextField();
        jtf_telefono_empleado = new javax.swing.JTextField();
        jtf_edad_empleado = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtb_info_empleado = new javax.swing.JTable();
        jb_buscar_empleado = new javax.swing.JButton();
        jtf_buscar_empleado = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jb_nuevo_empleado = new javax.swing.JButton();
        jb_editar_empleado = new javax.swing.JButton();
        jb_eliminar_empleado = new javax.swing.JButton();
        jb_guardar_empleado = new javax.swing.JButton();
        jb_cancelar_empleado = new javax.swing.JButton();

        jb_nuevo7.setBackground(new java.awt.Color(255, 102, 0));
        jb_nuevo7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_nuevo7.setForeground(new java.awt.Color(255, 255, 255));
        jb_nuevo7.setText("NUEVO");
        jb_nuevo7.setContentAreaFilled(false);
        jb_nuevo7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_nuevo7.setOpaque(true);

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
        jScrollPane5.setViewportView(jTable1);

        jl_nombre_producto11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_nombre_producto11.setForeground(new java.awt.Color(255, 255, 255));
        jl_nombre_producto11.setText("EXISTENCIA:");

        jl_nombre_producto13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_nombre_producto13.setForeground(new java.awt.Color(255, 255, 255));
        jl_nombre_producto13.setText("EXISTENCIA UM:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jp_contenedor.setBackground(new java.awt.Color(51, 51, 51));

        jp_menu.setBackground(new java.awt.Color(51, 51, 51));
        jp_menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jb_productos.setBackground(new java.awt.Color(51, 51, 51));
        jb_productos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jb_productos.setForeground(new java.awt.Color(255, 255, 255));
        jb_productos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tienda.png"))); // NOI18N
        jb_productos.setText("PRODUCTOS");
        jb_productos.setBorder(null);
        jb_productos.setContentAreaFilled(false);
        jb_productos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_productos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_productosActionPerformed(evt);
            }
        });
        jp_menu.add(jb_productos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        jb_home.setBackground(new java.awt.Color(51, 51, 51));
        jb_home.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jb_home.setForeground(new java.awt.Color(255, 255, 255));
        jb_home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/casa.png"))); // NOI18N
        jb_home.setText("HOME");
        jb_home.setBorder(null);
        jb_home.setContentAreaFilled(false);
        jb_home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_homeActionPerformed(evt);
            }
        });
        jp_menu.add(jb_home, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jb_ventas.setBackground(new java.awt.Color(51, 51, 51));
        jb_ventas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jb_ventas.setForeground(new java.awt.Color(255, 255, 255));
        jb_ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/carrito-de-la-compra.png"))); // NOI18N
        jb_ventas.setText("VENTAS");
        jb_ventas.setBorder(null);
        jb_ventas.setContentAreaFilled(false);
        jb_ventas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_ventasActionPerformed(evt);
            }
        });
        jp_menu.add(jb_ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        jb_proveedores.setBackground(new java.awt.Color(51, 51, 51));
        jb_proveedores.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jb_proveedores.setForeground(new java.awt.Color(255, 255, 255));
        jb_proveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/gerente (1).png"))); // NOI18N
        jb_proveedores.setText("PROVEEDORES");
        jb_proveedores.setBorder(null);
        jb_proveedores.setContentAreaFilled(false);
        jb_proveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_proveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_proveedoresActionPerformed(evt);
            }
        });
        jp_menu.add(jb_proveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));

        lj_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo_cali_opt.png"))); // NOI18N
        jp_menu.add(lj_logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jb_empleados.setBackground(new java.awt.Color(51, 51, 51));
        jb_empleados.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jb_empleados.setForeground(new java.awt.Color(255, 255, 255));
        jb_empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/multitud-de-usuarios (1).png"))); // NOI18N
        jb_empleados.setText("EMPLEADOS");
        jb_empleados.setBorder(null);
        jb_empleados.setContentAreaFilled(false);
        jb_empleados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_empleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_empleadosActionPerformed(evt);
            }
        });
        jp_menu.add(jb_empleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, -1));

        jp_modulos.setBackground(new java.awt.Color(204, 102, 0));
        jp_modulos.setPreferredSize(new java.awt.Dimension(1200, 800));
        jp_modulos.setLayout(new java.awt.CardLayout());

        jp_home.setBackground(new java.awt.Color(102, 102, 102));

        jl_cali_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo_cali.png"))); // NOI18N

        jPanel7.setBackground(new java.awt.Color(231, 92, 0));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 33)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("HOME");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jp_homeLayout = new javax.swing.GroupLayout(jp_home);
        jp_home.setLayout(jp_homeLayout);
        jp_homeLayout.setHorizontalGroup(
            jp_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_homeLayout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(jl_cali_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(233, 237, Short.MAX_VALUE))
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jp_homeLayout.setVerticalGroup(
            jp_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_homeLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(199, 199, 199)
                .addComponent(jl_cali_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jp_modulos.add(jp_home, "card2");

        jp_productos.setBackground(new java.awt.Color(102, 102, 102));

        jl_nombre_producto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_nombre_producto.setForeground(new java.awt.Color(255, 255, 255));
        jl_nombre_producto.setText("NOMBRE:");

        jl_marca_producto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_marca_producto.setForeground(new java.awt.Color(255, 255, 255));
        jl_marca_producto.setText("MARCA:");

        jl_precio_producto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_precio_producto.setForeground(new java.awt.Color(255, 255, 255));
        jl_precio_producto.setText("PRECIO:");

        jl_categoria_producto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_categoria_producto.setForeground(new java.awt.Color(255, 255, 255));
        jl_categoria_producto.setText("CATEGORIA");

        jtf_precio_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_precio_productoKeyTyped(evt);
            }
        });

        jb_nuevo_producto.setBackground(new java.awt.Color(255, 102, 0));
        jb_nuevo_producto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_nuevo_producto.setForeground(new java.awt.Color(255, 255, 255));
        jb_nuevo_producto.setText("NUEVO");
        jb_nuevo_producto.setContentAreaFilled(false);
        jb_nuevo_producto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_nuevo_producto.setOpaque(true);
        jb_nuevo_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_nuevo_productoActionPerformed(evt);
            }
        });

        jb_editar_producto.setBackground(new java.awt.Color(255, 102, 0));
        jb_editar_producto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_editar_producto.setForeground(new java.awt.Color(255, 255, 255));
        jb_editar_producto.setText("EDITAR");
        jb_editar_producto.setContentAreaFilled(false);
        jb_editar_producto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_editar_producto.setOpaque(true);
        jb_editar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_editar_productoActionPerformed(evt);
            }
        });

        jb_guardar_producto.setBackground(new java.awt.Color(255, 102, 0));
        jb_guardar_producto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_guardar_producto.setForeground(new java.awt.Color(255, 255, 255));
        jb_guardar_producto.setText("GUARDAR");
        jb_guardar_producto.setContentAreaFilled(false);
        jb_guardar_producto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_guardar_producto.setOpaque(true);
        jb_guardar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_guardar_productoActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(231, 92, 0));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 33)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("PRODUCTOS");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtb_info_producto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtb_info_producto.setSelectionBackground(new java.awt.Color(255, 102, 0));
        jtb_info_producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtb_info_productoMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jtb_info_producto);

        jl_id_producto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_id_producto.setForeground(new java.awt.Color(255, 255, 255));
        jl_id_producto.setText("ID:");

        jtf_id_producto.setEnabled(false);

        jl_folio_producto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_folio_producto.setForeground(new java.awt.Color(255, 255, 255));
        jl_folio_producto.setText("FOLIO:");

        jl_existencia_producto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_existencia_producto.setForeground(new java.awt.Color(255, 255, 255));
        jl_existencia_producto.setText("EXISTENCIA:");

        jtf_existencia_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_existencia_productoKeyTyped(evt);
            }
        });

        jl_existencia_um_producto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_existencia_um_producto.setForeground(new java.awt.Color(255, 255, 255));
        jl_existencia_um_producto.setText("EXISTENCIA UM:");

        jl_fecha_ingreso_producto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_fecha_ingreso_producto.setForeground(new java.awt.Color(255, 255, 255));
        jl_fecha_ingreso_producto.setText("FECHA:");

        jl_descripcion_producto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_descripcion_producto.setForeground(new java.awt.Color(255, 255, 255));
        jl_descripcion_producto.setText("DESCRIPCIÓN:");

        jl_id_proveedor_producto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_id_proveedor_producto.setForeground(new java.awt.Color(255, 255, 255));
        jl_id_proveedor_producto.setText("ID PROVEEDOR:");

        jtf_id_proveedor_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_id_proveedor_productoKeyTyped(evt);
            }
        });

        jb_buscar_producto.setBackground(new java.awt.Color(255, 102, 0));
        jb_buscar_producto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_buscar_producto.setForeground(new java.awt.Color(255, 255, 255));
        jb_buscar_producto.setText("BUSCAR");
        jb_buscar_producto.setContentAreaFilled(false);
        jb_buscar_producto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_buscar_producto.setOpaque(true);
        jb_buscar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_buscar_productoActionPerformed(evt);
            }
        });

        jtf_buscar_producto.setEnabled(false);
        jtf_buscar_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtf_buscar_productoKeyPressed(evt);
            }
        });

        jl_imagen_producto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_imagen_producto.setForeground(new java.awt.Color(255, 255, 255));
        jl_imagen_producto.setText("IMAGEN:");

        jb_eliminar_producto.setBackground(new java.awt.Color(255, 102, 0));
        jb_eliminar_producto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_eliminar_producto.setForeground(new java.awt.Color(255, 255, 255));
        jb_eliminar_producto.setText("ELIMINAR");
        jb_eliminar_producto.setContentAreaFilled(false);
        jb_eliminar_producto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_eliminar_producto.setOpaque(true);
        jb_eliminar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_eliminar_productoActionPerformed(evt);
            }
        });

        jb_cancelar_producto.setBackground(new java.awt.Color(255, 102, 0));
        jb_cancelar_producto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_cancelar_producto.setForeground(new java.awt.Color(255, 255, 255));
        jb_cancelar_producto.setText("CANCELAR");
        jb_cancelar_producto.setContentAreaFilled(false);
        jb_cancelar_producto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_cancelar_producto.setOpaque(true);
        jb_cancelar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_cancelar_productoActionPerformed(evt);
            }
        });

        jb_seleccionar_producto.setText("Seleccionar");
        jb_seleccionar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_seleccionar_productoActionPerformed(evt);
            }
        });

        jl_foto_producto.setBackground(new java.awt.Color(102, 102, 102));
        jl_foto_producto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jp_productosLayout = new javax.swing.GroupLayout(jp_productos);
        jp_productos.setLayout(jp_productosLayout);
        jp_productosLayout.setHorizontalGroup(
            jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jp_productosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jb_buscar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_buscar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jp_productosLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jp_productosLayout.createSequentialGroup()
                        .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jl_existencia_um_producto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jl_existencia_producto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jl_precio_producto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jl_marca_producto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jl_fecha_ingreso_producto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jl_descripcion_producto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jl_categoria_producto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jl_id_proveedor_producto, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtf_precio_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_marca_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_fecha_ingreso_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_descripcion_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_categoria_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_id_proveedor_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_existencia_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_existencia_um_producto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jp_productosLayout.createSequentialGroup()
                        .addComponent(jl_nombre_producto)
                        .addGap(18, 18, 18)
                        .addComponent(jtf_nombre_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_productosLayout.createSequentialGroup()
                            .addComponent(jl_id_producto)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtf_id_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_productosLayout.createSequentialGroup()
                            .addComponent(jl_folio_producto)
                            .addGap(18, 18, 18)
                            .addComponent(jtf_folio_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(34, 34, 34)
                .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jp_productosLayout.createSequentialGroup()
                        .addComponent(jl_imagen_producto)
                        .addGap(36, 36, 36)
                        .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jb_editar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jb_nuevo_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jb_eliminar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jb_guardar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jb_cancelar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jp_productosLayout.createSequentialGroup()
                                .addComponent(jtf_imagen_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jb_seleccionar_producto))))
                    .addGroup(jp_productosLayout.createSequentialGroup()
                        .addComponent(jl_foto_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp_productosLayout.setVerticalGroup(
            jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_productosLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jp_productosLayout.createSequentialGroup()
                        .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtf_id_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_id_producto))
                        .addGap(18, 18, 18)
                        .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtf_folio_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_folio_producto))
                        .addGap(18, 18, 18)
                        .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtf_nombre_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_nombre_producto))
                        .addGap(20, 20, 20)
                        .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtf_existencia_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_existencia_producto))
                        .addGap(18, 18, 18)
                        .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_precio_producto)
                            .addComponent(jtf_precio_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_existencia_um_producto)
                            .addComponent(jtf_existencia_um_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtf_marca_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_marca_producto))
                        .addGap(18, 18, 18)
                        .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtf_fecha_ingreso_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_fecha_ingreso_producto))
                        .addGap(18, 18, 18)
                        .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtf_descripcion_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_descripcion_producto))
                        .addGap(18, 18, 18)
                        .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtf_categoria_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_categoria_producto)))
                    .addGroup(jp_productosLayout.createSequentialGroup()
                        .addComponent(jb_nuevo_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jb_editar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jb_eliminar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jb_guardar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jb_cancelar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jl_foto_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_id_proveedor_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_id_proveedor_producto)
                    .addComponent(jl_imagen_producto)
                    .addComponent(jtf_imagen_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_seleccionar_producto))
                .addGap(57, 57, 57)
                .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jp_productosLayout.createSequentialGroup()
                        .addComponent(jb_buscar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtf_buscar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jp_modulos.add(jp_productos, "card2");

        jp_ventas.setBackground(new java.awt.Color(102, 102, 102));

        jPanel5.setBackground(new java.awt.Color(231, 92, 0));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 33)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("VENTAS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jl_nombre_producto1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_nombre_producto1.setForeground(new java.awt.Color(255, 255, 255));
        jl_nombre_producto1.setText("CAMBIO    $");

        jl_nombre_producto2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_nombre_producto2.setForeground(new java.awt.Color(255, 255, 255));
        jl_nombre_producto2.setText("BUSCAR");

        js_cantidad_ventas.setModel(new javax.swing.SpinnerNumberModel(1, 1, 40, 1));
        js_cantidad_ventas.setToolTipText("");

        jl_nombre_producto3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_nombre_producto3.setForeground(new java.awt.Color(255, 255, 255));
        jl_nombre_producto3.setText("CANTIDAD");

        jtf_buscar_ventas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtf_buscar_ventasKeyPressed(evt);
            }
        });

        jl_nombre_producto4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_nombre_producto4.setForeground(new java.awt.Color(255, 255, 255));
        jl_nombre_producto4.setText("PRECIO UNITARIO  $");

        jl_nombre_producto5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_nombre_producto5.setForeground(new java.awt.Color(255, 255, 255));
        jl_nombre_producto5.setText("TOTAL   $");

        jl_nombre_producto6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_nombre_producto6.setForeground(new java.awt.Color(255, 255, 255));
        jl_nombre_producto6.setText("EFECTIVO   $");

        jtf_efectivo_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_efectivo_ventasActionPerformed(evt);
            }
        });

        jl_nombre_producto7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_nombre_producto7.setForeground(new java.awt.Color(255, 255, 255));
        jl_nombre_producto7.setText("FECHA");

        jl_nombre_producto8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_nombre_producto8.setForeground(new java.awt.Color(255, 255, 255));
        jl_nombre_producto8.setText("HORA");

        jtf_fecha_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_fecha_ventasActionPerformed(evt);
            }
        });

        jb_realizar_ventas.setBackground(new java.awt.Color(255, 102, 0));
        jb_realizar_ventas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_realizar_ventas.setForeground(new java.awt.Color(255, 255, 255));
        jb_realizar_ventas.setText("FINALIZAR VENTA");
        jb_realizar_ventas.setContentAreaFilled(false);
        jb_realizar_ventas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_realizar_ventas.setOpaque(true);
        jb_realizar_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_realizar_ventasActionPerformed(evt);
            }
        });

        jb_agregar_ventas.setBackground(new java.awt.Color(255, 102, 0));
        jb_agregar_ventas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_agregar_ventas.setForeground(new java.awt.Color(255, 255, 255));
        jb_agregar_ventas.setText("AGREGAR");
        jb_agregar_ventas.setContentAreaFilled(false);
        jb_agregar_ventas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_agregar_ventas.setOpaque(true);
        jb_agregar_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_agregar_ventasActionPerformed(evt);
            }
        });

        jt_busqueda_ventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Descripcion", "Precio", "Num de piezas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jt_busqueda_ventas.setSelectionBackground(new java.awt.Color(255, 102, 0));
        jScrollPane8.setViewportView(jt_busqueda_ventas);

        jb_nuevo6.setBackground(new java.awt.Color(255, 102, 0));
        jb_nuevo6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_nuevo6.setForeground(new java.awt.Color(255, 255, 255));
        jb_nuevo6.setText("CORTE DE CAJA");
        jb_nuevo6.setContentAreaFilled(false);
        jb_nuevo6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_nuevo6.setOpaque(true);
        jb_nuevo6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_nuevo6ActionPerformed(evt);
            }
        });

        jl_nombre_producto9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_nombre_producto9.setForeground(new java.awt.Color(255, 255, 255));
        jl_nombre_producto9.setText("DESCRIPCION");

        jtf_descripcion_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_descripcion_ventasActionPerformed(evt);
            }
        });

        jl_nombre_producto10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_nombre_producto10.setForeground(new java.awt.Color(255, 255, 255));
        jl_nombre_producto10.setText("NOMBRE");

        jb_eliminar_ventas.setBackground(new java.awt.Color(255, 102, 0));
        jb_eliminar_ventas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_eliminar_ventas.setForeground(new java.awt.Color(255, 255, 255));
        jb_eliminar_ventas.setText("ELIMINAR");
        jb_eliminar_ventas.setContentAreaFilled(false);
        jb_eliminar_ventas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_eliminar_ventas.setOpaque(true);
        jb_eliminar_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_eliminar_ventasActionPerformed(evt);
            }
        });

        jt_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jt_productos.setSelectionBackground(new java.awt.Color(255, 102, 0));
        jt_productos.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jt_productosAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jt_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_productosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jt_productos);

        javax.swing.GroupLayout jp_ventasLayout = new javax.swing.GroupLayout(jp_ventas);
        jp_ventas.setLayout(jp_ventasLayout);
        jp_ventasLayout.setHorizontalGroup(
            jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_ventasLayout.createSequentialGroup()
                .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_ventasLayout.createSequentialGroup()
                        .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_ventasLayout.createSequentialGroup()
                                .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jp_ventasLayout.createSequentialGroup()
                                        .addGap(110, 110, 110)
                                        .addComponent(jl_nombre_producto2))
                                    .addGroup(jp_ventasLayout.createSequentialGroup()
                                        .addGap(92, 92, 92)
                                        .addComponent(jl_nombre_producto3)))
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_ventasLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jl_nombre_producto4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jl_nombre_producto10, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtf_buscar_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(js_cantidad_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_nombre_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_pu_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(93, 93, 93)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jb_nuevo6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_ventasLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jl_nombre_producto9)
                        .addGap(12, 12, 12)
                        .addComponent(jtf_descripcion_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_ventasLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jb_agregar_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jb_eliminar_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jb_realizar_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jp_ventasLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jp_ventasLayout.createSequentialGroup()
                                .addComponent(jl_nombre_producto8)
                                .addGap(18, 18, 18)
                                .addComponent(jtf_hora_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(201, 201, 201)
                                .addComponent(jl_nombre_producto1))
                            .addGroup(jp_ventasLayout.createSequentialGroup()
                                .addComponent(jl_nombre_producto7)
                                .addGap(12, 12, 12)
                                .addComponent(jtf_fecha_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jl_nombre_producto6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_ventasLayout.createSequentialGroup()
                                .addComponent(jtf_efectivo_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(jl_nombre_producto5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtf_total_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtf_cambio_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jp_ventasLayout.setVerticalGroup(
            jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_ventasLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_nuevo6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jp_ventasLayout.createSequentialGroup()
                        .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_ventasLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jtf_buscar_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(js_cantidad_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jp_ventasLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jl_nombre_producto2)
                                .addGap(21, 21, 21)
                                .addComponent(jl_nombre_producto3)))
                        .addGap(13, 13, 13)
                        .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_nombre_producto10)
                            .addComponent(jtf_nombre_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtf_pu_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_nombre_producto4))))
                .addGap(20, 20, 20)
                .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_ventasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jl_nombre_producto9))
                    .addComponent(jtf_descripcion_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jp_ventasLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jb_agregar_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jb_eliminar_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jb_realizar_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(80, 80, 80)
                .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jl_nombre_producto7)
                    .addComponent(jtf_fecha_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jl_nombre_producto5)
                        .addComponent(jtf_total_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_ventasLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_nombre_producto6)
                            .addComponent(jtf_efectivo_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5)
                .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jl_nombre_producto8)
                    .addComponent(jtf_hora_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jp_ventasLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_nombre_producto1)
                            .addComponent(jtf_cambio_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jp_modulos.add(jp_ventas, "card2");

        jp_proveedores.setBackground(new java.awt.Color(102, 102, 102));

        jPanel4.setBackground(new java.awt.Color(231, 92, 0));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 33)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PROVEEDORES");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jl_folio_proveedor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jl_folio_proveedor.setForeground(new java.awt.Color(255, 255, 255));
        jl_folio_proveedor.setText("FOLIO:");

        jtf_folio_proveedor.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jtf_folio_proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_folio_proveedorKeyTyped(evt);
            }
        });

        jl_nombre_proveedor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jl_nombre_proveedor.setForeground(new java.awt.Color(255, 255, 255));
        jl_nombre_proveedor.setText("NOMBRE:");

        jtf_nombre_proveedor.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jl_calle_proveedor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jl_calle_proveedor.setForeground(new java.awt.Color(255, 255, 255));
        jl_calle_proveedor.setText("CALLE:");

        jtf_calle_proveedor.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jl_numero_proveedor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jl_numero_proveedor.setForeground(new java.awt.Color(255, 255, 255));
        jl_numero_proveedor.setText("NÚMERO:");

        jtf_numero_proveedor.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jtf_numero_proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_numero_proveedorKeyTyped(evt);
            }
        });

        jl_colonia_proveedor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jl_colonia_proveedor.setForeground(new java.awt.Color(255, 255, 255));
        jl_colonia_proveedor.setText("COLONIA:");

        jtf_colonia_proveedor.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jl_telefono_proveedor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jl_telefono_proveedor.setForeground(new java.awt.Color(255, 255, 255));
        jl_telefono_proveedor.setText("TELÉFONO:");

        jtf_telefono_proveedor.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jtf_telefono_proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_telefono_proveedorKeyTyped(evt);
            }
        });

        jl_id_proveedor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jl_id_proveedor.setForeground(new java.awt.Color(255, 255, 255));
        jl_id_proveedor.setText("ID PROVEEDOR:");

        jtf_id_proveedor.setEditable(false);
        jtf_id_proveedor.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jtf_id_proveedor.setEnabled(false);

        jtb_infoproveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtb_infoproveedor.setSelectionBackground(new java.awt.Color(255, 102, 0));
        jtb_infoproveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtb_infoproveedorMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jtb_infoproveedor);

        jb_buscar_proveedor.setBackground(new java.awt.Color(255, 102, 0));
        jb_buscar_proveedor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_buscar_proveedor.setForeground(new java.awt.Color(255, 255, 255));
        jb_buscar_proveedor.setText("BUSCAR");
        jb_buscar_proveedor.setContentAreaFilled(false);
        jb_buscar_proveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_buscar_proveedor.setOpaque(true);
        jb_buscar_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_buscar_proveedorActionPerformed(evt);
            }
        });

        jtf_buscar_proveedor.setEnabled(false);
        jtf_buscar_proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtf_buscar_proveedorKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jb_nuevo_proveedor.setBackground(new java.awt.Color(255, 102, 0));
        jb_nuevo_proveedor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_nuevo_proveedor.setForeground(new java.awt.Color(255, 255, 255));
        jb_nuevo_proveedor.setText("NUEVO");
        jb_nuevo_proveedor.setContentAreaFilled(false);
        jb_nuevo_proveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_nuevo_proveedor.setOpaque(true);
        jb_nuevo_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_nuevo_proveedorActionPerformed(evt);
            }
        });

        jb_editar_proveedor.setBackground(new java.awt.Color(255, 102, 0));
        jb_editar_proveedor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_editar_proveedor.setForeground(new java.awt.Color(255, 255, 255));
        jb_editar_proveedor.setText("EDITAR");
        jb_editar_proveedor.setContentAreaFilled(false);
        jb_editar_proveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_editar_proveedor.setOpaque(true);
        jb_editar_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_editar_proveedorActionPerformed(evt);
            }
        });

        jb_eliminar_proveedor.setBackground(new java.awt.Color(255, 102, 0));
        jb_eliminar_proveedor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_eliminar_proveedor.setForeground(new java.awt.Color(255, 255, 255));
        jb_eliminar_proveedor.setText("ELIMINAR");
        jb_eliminar_proveedor.setContentAreaFilled(false);
        jb_eliminar_proveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_eliminar_proveedor.setOpaque(true);
        jb_eliminar_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_eliminar_proveedorActionPerformed(evt);
            }
        });

        jb_guardar_proveedor.setBackground(new java.awt.Color(255, 102, 0));
        jb_guardar_proveedor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_guardar_proveedor.setForeground(new java.awt.Color(255, 255, 255));
        jb_guardar_proveedor.setText("GUARDAR");
        jb_guardar_proveedor.setContentAreaFilled(false);
        jb_guardar_proveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_guardar_proveedor.setOpaque(true);
        jb_guardar_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_guardar_proveedorActionPerformed(evt);
            }
        });

        jb_cancelar_proveedor.setBackground(new java.awt.Color(255, 102, 0));
        jb_cancelar_proveedor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_cancelar_proveedor.setForeground(new java.awt.Color(255, 255, 255));
        jb_cancelar_proveedor.setText("CANCELAR");
        jb_cancelar_proveedor.setContentAreaFilled(false);
        jb_cancelar_proveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_cancelar_proveedor.setOpaque(true);
        jb_cancelar_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_cancelar_proveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jb_cancelar_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_guardar_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_eliminar_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_editar_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_nuevo_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jb_nuevo_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_editar_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_eliminar_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_guardar_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_cancelar_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jp_proveedoresLayout = new javax.swing.GroupLayout(jp_proveedores);
        jp_proveedores.setLayout(jp_proveedoresLayout);
        jp_proveedoresLayout.setHorizontalGroup(
            jp_proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jp_proveedoresLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_buscar_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtf_buscar_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 222, Short.MAX_VALUE))
            .addGroup(jp_proveedoresLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jp_proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_proveedoresLayout.createSequentialGroup()
                        .addComponent(jl_id_proveedor)
                        .addGap(38, 38, 38)
                        .addComponent(jtf_id_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_proveedoresLayout.createSequentialGroup()
                        .addGroup(jp_proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jl_telefono_proveedor)
                            .addComponent(jl_colonia_proveedor)
                            .addComponent(jl_numero_proveedor)
                            .addComponent(jl_calle_proveedor)
                            .addComponent(jl_nombre_proveedor)
                            .addComponent(jl_folio_proveedor))
                        .addGap(69, 69, 69)
                        .addGroup(jp_proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtf_nombre_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_folio_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_calle_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_numero_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_colonia_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_telefono_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158))
        );
        jp_proveedoresLayout.setVerticalGroup(
            jp_proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_proveedoresLayout.createSequentialGroup()
                .addGroup(jp_proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_proveedoresLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addGroup(jp_proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_id_proveedor)
                            .addComponent(jtf_id_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jp_proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_folio_proveedor)
                            .addComponent(jtf_folio_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jp_proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_nombre_proveedor)
                            .addComponent(jtf_nombre_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jp_proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_calle_proveedor)
                            .addComponent(jtf_calle_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jp_proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_numero_proveedor)
                            .addComponent(jtf_numero_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jp_proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_colonia_proveedor)
                            .addComponent(jtf_colonia_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jp_proveedoresLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jp_proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_telefono_proveedor)
                    .addComponent(jtf_telefono_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jp_proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jp_proveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jtf_buscar_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jb_buscar_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(73, 73, 73))
        );

        jp_modulos.add(jp_proveedores, "card6");

        jp_empleados.setBackground(new java.awt.Color(102, 102, 102));

        jPanel8.setBackground(new java.awt.Color(231, 92, 0));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 33)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("EMPLEADOS");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        jl_id_empleado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_id_empleado.setForeground(new java.awt.Color(255, 255, 255));
        jl_id_empleado.setText("ID EMPLEADO:");

        jtf_id_empleado.setEditable(false);
        jtf_id_empleado.setEnabled(false);

        jl_folio_empleado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_folio_empleado.setForeground(new java.awt.Color(255, 255, 255));
        jl_folio_empleado.setText("FOLIO:");

        jl_nombre_empleado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_nombre_empleado.setForeground(new java.awt.Color(255, 255, 255));
        jl_nombre_empleado.setText("NOMBRE:");

        jl_ape_pat_empleado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_ape_pat_empleado.setForeground(new java.awt.Color(255, 255, 255));
        jl_ape_pat_empleado.setText("APELLIDO PATERNO:");

        jl_ape_mat_empleado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_ape_mat_empleado.setForeground(new java.awt.Color(255, 255, 255));
        jl_ape_mat_empleado.setText("APELLIDO MATERNO:");

        jl_calle_empleado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_calle_empleado.setForeground(new java.awt.Color(255, 255, 255));
        jl_calle_empleado.setText("CALLE:");

        jl_numero_empleado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_numero_empleado.setForeground(new java.awt.Color(255, 255, 255));
        jl_numero_empleado.setText("NUMERO:");

        jl_colonia_empleado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_colonia_empleado.setForeground(new java.awt.Color(255, 255, 255));
        jl_colonia_empleado.setText("COLONIA:");

        jl_telefono_empleado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_telefono_empleado.setForeground(new java.awt.Color(255, 255, 255));
        jl_telefono_empleado.setText("TELEFONO:");

        jl_edad_empleado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_edad_empleado.setForeground(new java.awt.Color(255, 255, 255));
        jl_edad_empleado.setText("EDAD:");

        jtf_numero_empleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_numero_empleadoKeyTyped(evt);
            }
        });

        jtf_telefono_empleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_telefono_empleadoKeyTyped(evt);
            }
        });

        jtf_edad_empleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_edad_empleadoKeyTyped(evt);
            }
        });

        jtb_info_empleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtb_info_empleado.setSelectionBackground(new java.awt.Color(255, 102, 0));
        jtb_info_empleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtb_info_empleadoMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jtb_info_empleado);

        jb_buscar_empleado.setBackground(new java.awt.Color(255, 102, 0));
        jb_buscar_empleado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_buscar_empleado.setForeground(new java.awt.Color(255, 255, 255));
        jb_buscar_empleado.setText("BUSCAR");
        jb_buscar_empleado.setContentAreaFilled(false);
        jb_buscar_empleado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_buscar_empleado.setOpaque(true);
        jb_buscar_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_buscar_empleadoActionPerformed(evt);
            }
        });

        jtf_buscar_empleado.setEditable(false);
        jtf_buscar_empleado.setEnabled(false);
        jtf_buscar_empleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtf_buscar_empleadoKeyPressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        jb_nuevo_empleado.setBackground(new java.awt.Color(255, 102, 0));
        jb_nuevo_empleado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_nuevo_empleado.setForeground(new java.awt.Color(255, 255, 255));
        jb_nuevo_empleado.setText("NUEVO");
        jb_nuevo_empleado.setContentAreaFilled(false);
        jb_nuevo_empleado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_nuevo_empleado.setOpaque(true);
        jb_nuevo_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_nuevo_empleadoActionPerformed(evt);
            }
        });

        jb_editar_empleado.setBackground(new java.awt.Color(255, 102, 0));
        jb_editar_empleado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_editar_empleado.setForeground(new java.awt.Color(255, 255, 255));
        jb_editar_empleado.setText("EDITAR");
        jb_editar_empleado.setContentAreaFilled(false);
        jb_editar_empleado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_editar_empleado.setOpaque(true);
        jb_editar_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_editar_empleadoActionPerformed(evt);
            }
        });

        jb_eliminar_empleado.setBackground(new java.awt.Color(255, 102, 0));
        jb_eliminar_empleado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_eliminar_empleado.setForeground(new java.awt.Color(255, 255, 255));
        jb_eliminar_empleado.setText("ELIMINAR");
        jb_eliminar_empleado.setContentAreaFilled(false);
        jb_eliminar_empleado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_eliminar_empleado.setOpaque(true);
        jb_eliminar_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_eliminar_empleadoActionPerformed(evt);
            }
        });

        jb_guardar_empleado.setBackground(new java.awt.Color(255, 102, 0));
        jb_guardar_empleado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_guardar_empleado.setForeground(new java.awt.Color(255, 255, 255));
        jb_guardar_empleado.setText("GUARDAR");
        jb_guardar_empleado.setContentAreaFilled(false);
        jb_guardar_empleado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_guardar_empleado.setOpaque(true);
        jb_guardar_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_guardar_empleadoActionPerformed(evt);
            }
        });

        jb_cancelar_empleado.setBackground(new java.awt.Color(255, 102, 0));
        jb_cancelar_empleado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jb_cancelar_empleado.setForeground(new java.awt.Color(255, 255, 255));
        jb_cancelar_empleado.setText("CANCELAR");
        jb_cancelar_empleado.setContentAreaFilled(false);
        jb_cancelar_empleado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_cancelar_empleado.setOpaque(true);
        jb_cancelar_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_cancelar_empleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jb_nuevo_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_editar_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_eliminar_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_guardar_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_cancelar_empleado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jb_nuevo_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_editar_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_eliminar_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_guardar_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_cancelar_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jl_id_empleado)
                            .addComponent(jl_folio_empleado)
                            .addComponent(jl_nombre_empleado)
                            .addComponent(jl_ape_pat_empleado)
                            .addComponent(jl_ape_mat_empleado)
                            .addComponent(jl_calle_empleado)
                            .addComponent(jl_numero_empleado)
                            .addComponent(jl_colonia_empleado)
                            .addComponent(jl_telefono_empleado)
                            .addComponent(jl_edad_empleado))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtf_edad_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_telefono_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_colonia_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtf_numero_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtf_calle_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtf_ape_mat_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtf_ape_pat_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtf_id_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtf_folio_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtf_nombre_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb_buscar_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtf_buscar_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(jl_id_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(jtf_id_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGap(18, 18, 18)
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(jl_folio_empleado)
                                                                            .addComponent(jtf_folio_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(jl_nombre_empleado))
                                                                    .addComponent(jtf_nombre_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jl_ape_pat_empleado))
                                                            .addComponent(jtf_ape_pat_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jl_ape_mat_empleado))
                                                    .addComponent(jtf_ape_mat_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jl_calle_empleado))
                                            .addComponent(jtf_calle_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jl_numero_empleado))
                                    .addComponent(jtf_numero_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jl_colonia_empleado))
                            .addComponent(jtf_colonia_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jl_telefono_empleado))
                    .addComponent(jtf_telefono_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_edad_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_edad_empleado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jb_buscar_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtf_buscar_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jp_empleadosLayout = new javax.swing.GroupLayout(jp_empleados);
        jp_empleados.setLayout(jp_empleadosLayout);
        jp_empleadosLayout.setHorizontalGroup(
            jp_empleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jp_empleadosLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jp_empleadosLayout.setVerticalGroup(
            jp_empleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_empleadosLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jp_modulos.add(jp_empleados, "card6");

        javax.swing.GroupLayout jp_contenedorLayout = new javax.swing.GroupLayout(jp_contenedor);
        jp_contenedor.setLayout(jp_contenedorLayout);
        jp_contenedorLayout.setHorizontalGroup(
            jp_contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_contenedorLayout.createSequentialGroup()
                .addComponent(jp_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jp_modulos, javax.swing.GroupLayout.DEFAULT_SIZE, 1047, Short.MAX_VALUE))
        );
        jp_contenedorLayout.setVerticalGroup(
            jp_contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jp_modulos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void deshabilitajtfVentas(Boolean x){
        jtf_pu_ventas.setEnabled(x);
        jtf_descripcion_ventas.setEnabled(x);
        jtf_total_ventas.setEnabled(x);
        jtf_nombre_ventas.setEnabled(x);
        
        
    }

    private void jb_productosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_productosActionPerformed
        jp_modulos.removeAll();
        jp_modulos.repaint();
        jp_modulos.revalidate();

        jp_modulos.add(jp_productos);
        jp_modulos.repaint();
        jp_modulos.revalidate();
        ConsultaProducto();

    }//GEN-LAST:event_jb_productosActionPerformed

    private void jb_homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_homeActionPerformed
        jp_modulos.removeAll();
        jp_modulos.repaint();
        jp_modulos.revalidate();

        jp_modulos.add(jp_home);
        jp_modulos.repaint();
        jp_modulos.revalidate();
    }//GEN-LAST:event_jb_homeActionPerformed

    private void jb_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_ventasActionPerformed
        jp_modulos.removeAll();
        jp_modulos.repaint();
        jp_modulos.revalidate();

        jp_modulos.add(jp_ventas);
        jp_modulos.repaint();
        jp_modulos.revalidate();
    }//GEN-LAST:event_jb_ventasActionPerformed

    private void jb_proveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_proveedoresActionPerformed
        jp_modulos.removeAll();
        jp_modulos.repaint();
        jp_modulos.revalidate();

        jp_modulos.add(jp_proveedores);
        jp_modulos.repaint();
        jp_modulos.revalidate();
        ConsultaProveedor();
    }//GEN-LAST:event_jb_proveedoresActionPerformed

    private void jb_empleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_empleadosActionPerformed
        jp_modulos.removeAll();
        jp_modulos.repaint();
        jp_modulos.revalidate();

        jp_modulos.add(jp_empleados);
        jp_modulos.repaint();
        jp_modulos.revalidate();
        ConsultaEmpleado();
    }//GEN-LAST:event_jb_empleadosActionPerformed

    private void jb_guardar_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_guardar_empleadoActionPerformed
        if (desicion == "nuevo") {
            Connection con = null;
            try {
                con = getConexion();
                ps = con.prepareStatement("INSERT INTO empleado (folio_empleado,nombre,ape_pat,ape_mat,calle,numero,colonia,telefono,edad) VALUES (?,?,?,?,?,?,?,?,?)");
                ps.setString(1, jtf_folio_empleado.getText());
                ps.setString(2, jtf_nombre_empleado.getText());
                ps.setString(3, jtf_ape_pat_empleado.getText());
                ps.setString(4, jtf_ape_mat_empleado.getText());
                ps.setString(5, jtf_calle_empleado.getText());
                ps.setString(6, jtf_numero_empleado.getText());
                ps.setString(7, jtf_colonia_empleado.getText());
                ps.setString(8, jtf_telefono_empleado.getText());
                ps.setString(9, jtf_edad_empleado.getText());

                int res = ps.executeUpdate();

                VerDatosEmpleado(false);
                jtb_info_empleado.setVisible(true);
                jtb_info_empleado.setEnabled(true);
                tablaempleado();
                jb_nuevo_empleado.setEnabled(true);
                jb_editar_empleado.setEnabled(true);
                jb_eliminar_empleado.setEnabled(true);
                jb_guardar_empleado.setEnabled(false);
                jb_cancelar_empleado.setEnabled(false);

                if (res > 0) {
                    JOptionPane.showMessageDialog(this, "Datos de Empleado Guardados Correctamente!!");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al ingresar Datos de Empleado");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Empleado 003 " + ex.getMessage());
            }
        } else if (desicion == "editar") {
            Connection con = null;
            try {

                con = getConexion();
                ps = con.prepareStatement("UPDATE empleado SET folio_empleado=?,nombre=?,ape_pat=?,ape_mat=?,calle=?,numero=?,colonia=?,telefono=?,edad=? WHERE id_empleado=?");
                ps.setString(1, jtf_folio_empleado.getText());
                ps.setString(2, jtf_nombre_empleado.getText());
                ps.setString(3, jtf_ape_pat_empleado.getText());
                ps.setString(4, jtf_ape_mat_empleado.getText());
                ps.setString(5, jtf_calle_empleado.getText());
                ps.setString(6, jtf_numero_empleado.getText());
                ps.setString(7, jtf_colonia_empleado.getText());
                ps.setString(8, jtf_telefono_empleado.getText());
                ps.setString(9, jtf_edad_empleado.getText());
                ps.setString(10, jtf_id_empleado.getText());

                int res = ps.executeUpdate();
                VerDatosEmpleado(false);
                jtb_info_empleado.setVisible(true);
                jtb_info_empleado.setEnabled(true);
                tablaempleado();
                jb_nuevo_empleado.setEnabled(true);
                jb_editar_empleado.setEnabled(true);
                jb_eliminar_empleado.setEnabled(true);
                jb_guardar_empleado.setEnabled(false);
                jb_cancelar_empleado.setEnabled(false);

                if (res > 0) {
                    JOptionPane.showMessageDialog(this, "Datos de Empleado Actualizados Correctamente!!");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar Datos de Empleado");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Empleado 004 " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jb_guardar_empleadoActionPerformed

    private void jb_buscar_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_buscar_empleadoActionPerformed
        jtf_buscar_empleado.setEditable(true);
        jtf_buscar_empleado.setEnabled(true);

    }//GEN-LAST:event_jb_buscar_empleadoActionPerformed

    private void jb_nuevo_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_nuevo_empleadoActionPerformed
        desicion = "nuevo";
        VerDatosEmpleado(true);
        jtb_info_empleado.setEnabled(false);
        jtf_id_empleado.setEnabled(false);
        jtf_folio_empleado.setText("");
        jtf_nombre_empleado.setText("");
        jtf_ape_pat_empleado.setText("");
        jtf_ape_mat_empleado.setText("");
        jtf_calle_empleado.setText("");
        jtf_numero_empleado.setText("");
        jtf_colonia_empleado.setText("");
        jtf_telefono_empleado.setText("");
        jtf_edad_empleado.setText("");
        jb_nuevo_empleado.setEnabled(false);
        jb_editar_empleado.setEnabled(false);
        jb_eliminar_empleado.setEnabled(false);
        jb_guardar_empleado.setEnabled(true);
        jb_cancelar_empleado.setEnabled(true);
    }//GEN-LAST:event_jb_nuevo_empleadoActionPerformed

    private void jb_editar_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_editar_empleadoActionPerformed
        desicion = "editar";
        VerDatosEmpleado(true);
        jtb_info_empleado.setEnabled(false);
        jtf_id_empleado.setEnabled(false);
        jb_nuevo_empleado.setEnabled(false);
        jb_editar_empleado.setEnabled(false);
        jb_eliminar_empleado.setEnabled(false);
        jb_guardar_empleado.setEnabled(true);
        jb_cancelar_empleado.setEnabled(true);

    }//GEN-LAST:event_jb_editar_empleadoActionPerformed

    private void jb_eliminar_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_eliminar_empleadoActionPerformed
        Connection con = null;
        try {

            con = getConexion();
            ps = con.prepareStatement("DELETE FROM empleado WHERE id_empleado=?");
            ps.setInt(1, Integer.parseInt(jtf_id_empleado.getText()));
            jb_nuevo_empleado.setEnabled(true);
            jb_editar_empleado.setEnabled(true);
            jb_eliminar_empleado.setEnabled(true);
            jb_guardar_empleado.setEnabled(false);
            jb_cancelar_empleado.setEnabled(false);

            int res = ps.executeUpdate();
            jtb_info_empleado.setVisible(true);
            jtb_info_empleado.setEnabled(true);
            tablaempleado();
            ConsultaEmpleado();

            if (res > 0) {
                JOptionPane.showMessageDialog(this, "Datos de Empleado Eliminados Correctamente!!");
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar los Datos de Empleado");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Empleado 005 " + ex.getMessage());
        }
    }//GEN-LAST:event_jb_eliminar_empleadoActionPerformed

    private void jb_cancelar_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_cancelar_empleadoActionPerformed
        VerDatosEmpleado(false);
        ConsultaEmpleado();
        jtb_info_empleado.setEnabled(true);
        jb_nuevo_empleado.setEnabled(true);
        jb_editar_empleado.setEnabled(true);
        jb_eliminar_empleado.setEnabled(true);
        jb_guardar_empleado.setEnabled(false);
        jb_cancelar_empleado.setEnabled(false);
    }//GEN-LAST:event_jb_cancelar_empleadoActionPerformed

    private void jtf_buscar_empleadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_buscar_empleadoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Connection con = null;
            try {

                con = getConexion();
                ps = con.prepareStatement("SELECT * FROM empleado WHERE folio_empleado=?");
                ps.setString(1, jtf_buscar_empleado.getText());
                rs = ps.executeQuery();

                if (rs.next()) {
                    jtf_id_empleado.setText(rs.getString("id_empleado"));
                    jtf_folio_empleado.setText(rs.getString("folio_empleado"));
                    jtf_nombre_empleado.setText(rs.getString("nombre"));
                    jtf_ape_pat_empleado.setText(rs.getString("ape_pat"));
                    jtf_ape_mat_empleado.setText(rs.getString("ape_mat"));
                    jtf_calle_empleado.setText(rs.getString("calle"));
                    jtf_numero_empleado.setText(rs.getString("numero"));
                    jtf_colonia_empleado.setText(rs.getString("colonia"));
                    jtf_telefono_empleado.setText(rs.getString("telefono"));
                    jtf_edad_empleado.setText(rs.getString("edad"));
                } else {
                    JOptionPane.showMessageDialog(null, "No existe el empleado con ese id");
                }
                JOptionPane.showMessageDialog(this, "Busqueda Exitosa!!");
                jtf_buscar_empleado.setEnabled(false);
                jtf_buscar_empleado.setText("");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Empleado 006 " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jtf_buscar_empleadoKeyPressed

    private void jtb_info_empleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtb_info_empleadoMouseClicked
        Connection con = null;
        try {

            con = getConexion();
            ps = con.prepareStatement("SELECT * FROM empleado WHERE id_empleado=?");
            ps.setString(1, jtb_info_empleado.getValueAt(jtb_info_empleado.getSelectedRow(), 0).toString());
            rs = ps.executeQuery();

            if (rs.next()) {
                jtf_id_empleado.setText(rs.getString("id_empleado"));
                jtf_folio_empleado.setText(rs.getString("folio_empleado"));
                jtf_nombre_empleado.setText(rs.getString("nombre"));
                jtf_ape_pat_empleado.setText(rs.getString("ape_pat"));
                jtf_ape_mat_empleado.setText(rs.getString("ape_mat"));
                jtf_calle_empleado.setText(rs.getString("calle"));
                jtf_numero_empleado.setText(rs.getString("numero"));
                jtf_colonia_empleado.setText(rs.getString("colonia"));
                jtf_telefono_empleado.setText(rs.getString("telefono"));
                jtf_edad_empleado.setText(rs.getString("edad"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe el empleado con ese id");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Empleado 007 " + ex.getMessage());
        }
    }//GEN-LAST:event_jtb_info_empleadoMouseClicked

    private void jb_nuevo_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_nuevo_proveedorActionPerformed
        desicion = "nuevo";
        VerDatosProveedor(true);
        jtb_infoproveedor.setEnabled(false);
        jtf_id_proveedor.setEnabled(false);
        jtf_folio_proveedor.setText("");
        jtf_nombre_proveedor.setText("");
        jtf_calle_proveedor.setText("");
        jtf_numero_proveedor.setText("");
        jtf_colonia_proveedor.setText("");
        jtf_telefono_proveedor.setText("");
        jb_nuevo_proveedor.setEnabled(false);
        jb_editar_proveedor.setEnabled(false);
        jb_eliminar_proveedor.setEnabled(false);
        jb_guardar_proveedor.setEnabled(true);
        jb_cancelar_proveedor.setEnabled(true);
    }//GEN-LAST:event_jb_nuevo_proveedorActionPerformed

    private void jb_editar_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_editar_proveedorActionPerformed
        desicion = "editar";
        VerDatosProveedor(true);
        jtb_infoproveedor.setEnabled(false);
        jtf_id_proveedor.setEnabled(false);
        jb_nuevo_proveedor.setEnabled(false);
        jb_editar_proveedor.setEnabled(false);
        jb_eliminar_proveedor.setEnabled(false);
        jb_guardar_proveedor.setEnabled(true);
        jb_cancelar_proveedor.setEnabled(true);
    }//GEN-LAST:event_jb_editar_proveedorActionPerformed

    private void jb_cancelar_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_cancelar_proveedorActionPerformed
        VerDatosProveedor(false);
        ConsultaProveedor();
        jtb_infoproveedor.setEnabled(true);
        jb_nuevo_proveedor.setEnabled(true);
        jb_editar_proveedor.setEnabled(true);
        jb_eliminar_proveedor.setEnabled(true);
        jb_guardar_proveedor.setEnabled(false);
        jb_cancelar_proveedor.setEnabled(false);
    }//GEN-LAST:event_jb_cancelar_proveedorActionPerformed

    private void jb_guardar_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_guardar_proveedorActionPerformed
        if (desicion == "nuevo") {
            Connection con = null;
            try {
                con = getConexion();
                ps = con.prepareStatement("INSERT INTO proveedor (folio_proveedor,nombre_empresa,calle,numero,colonia,telefono) VALUES (?,?,?,?,?,?)");
                ps.setString(1, jtf_folio_proveedor.getText());
                ps.setString(2, jtf_nombre_proveedor.getText());
                ps.setString(3, jtf_calle_proveedor.getText());
                ps.setString(4, jtf_numero_proveedor.getText());
                ps.setString(5, jtf_colonia_proveedor.getText());
                ps.setString(6, jtf_telefono_proveedor.getText());

                int res = ps.executeUpdate();

                VerDatosProveedor(false);
                jtb_infoproveedor.setVisible(true);
                jtb_infoproveedor.setEnabled(true);
                tablaproveedor();
                jb_nuevo_proveedor.setEnabled(true);
                jb_editar_proveedor.setEnabled(true);
                jb_eliminar_proveedor.setEnabled(true);
                jb_guardar_proveedor.setEnabled(false);
                jb_cancelar_proveedor.setEnabled(false);

                if (res > 0) {
                    JOptionPane.showMessageDialog(this, "Datos de Proveedor Guardados Correctamente!!");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al ingresar Datos de Proveedor");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Proveedor 003 " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Campos Vacios");
            }
        } else if (desicion == "editar") {
            Connection con = null;
            try {
                con = getConexion();
                ps = con.prepareStatement("UPDATE proveedor SET folio_proveedor=?,nombre_empresa=?,calle=?,numero=?,colonia=?,telefono=? WHERE id_proveedor=?");
                ps.setString(1, jtf_folio_proveedor.getText());
                ps.setString(2, jtf_nombre_proveedor.getText());
                ps.setString(3, jtf_calle_proveedor.getText());
                ps.setString(4, jtf_numero_proveedor.getText());
                ps.setString(5, jtf_colonia_proveedor.getText());
                ps.setString(6, jtf_telefono_proveedor.getText());
                ps.setString(7, jtf_id_proveedor.getText());

                int res = ps.executeUpdate();

                VerDatosProveedor(false);
                jtb_infoproveedor.setVisible(true);
                jtb_infoproveedor.setEnabled(true);
                tablaproveedor();
                jb_nuevo_proveedor.setEnabled(true);
                jb_editar_proveedor.setEnabled(true);
                jb_eliminar_proveedor.setEnabled(true);
                jb_guardar_proveedor.setEnabled(false);
                jb_cancelar_proveedor.setEnabled(false);

                if (res > 0) {
                    JOptionPane.showMessageDialog(this, "Datos de Proveedor Actualizados Correctamente!!");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al Actualizar Datos de Proveedor");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Proveedor 004 " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Campos Vacios");
            }
        }
    }//GEN-LAST:event_jb_guardar_proveedorActionPerformed

    private void jb_eliminar_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_eliminar_proveedorActionPerformed
        Connection con = null;
        try {

            con = getConexion();
            ps = con.prepareStatement("DELETE FROM proveedor WHERE id_proveedor=?");
            ps.setInt(1, Integer.parseInt(jtf_id_proveedor.getText()));
            jb_nuevo_proveedor.setEnabled(true);
            jb_editar_proveedor.setEnabled(true);
            jb_eliminar_proveedor.setEnabled(true);
            jb_guardar_proveedor.setEnabled(false);
            jb_cancelar_proveedor.setEnabled(false);

            int res = ps.executeUpdate();
            jtb_infoproveedor.setVisible(true);
            jtb_infoproveedor.setEnabled(true);
            tablaproveedor();
            ConsultaProveedor();

            if (res > 0) {
                JOptionPane.showMessageDialog(this, "Datos de Proveedor Eliminados Correctamente!!");
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar los Datos de Proveedor");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Proveedor 005 " + ex.getMessage());
        }
    }//GEN-LAST:event_jb_eliminar_proveedorActionPerformed

    private void jb_buscar_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_buscar_proveedorActionPerformed
        jtf_buscar_proveedor.setEditable(true);
        jtf_buscar_proveedor.setEnabled(true);
    }//GEN-LAST:event_jb_buscar_proveedorActionPerformed

    private void jtf_buscar_proveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_buscar_proveedorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Connection con = null;
            try {

                con = getConexion();
                ps = con.prepareStatement("SELECT * FROM proveedor WHERE folio_proveedor=?");
                ps.setString(1, jtf_buscar_proveedor.getText());
                rs = ps.executeQuery();

                if (rs.next()) {
                    jtf_id_proveedor.setText(rs.getString("id_proveedor"));
                    jtf_folio_proveedor.setText(rs.getString("folio_proveedor"));
                    jtf_nombre_proveedor.setText(rs.getString("nombre_empresa"));
                    jtf_calle_proveedor.setText(rs.getString("calle"));
                    jtf_numero_proveedor.setText(rs.getString("numero"));
                    jtf_colonia_proveedor.setText(rs.getString("colonia"));
                    jtf_telefono_proveedor.setText(rs.getString("telefono"));
                } else {
                    JOptionPane.showMessageDialog(null, "No existe el empleado con ese id");
                }
                JOptionPane.showMessageDialog(this, "Busqueda Exitosa!!");
                jtf_buscar_proveedor.setEnabled(false);
                jtf_buscar_proveedor.setText("");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Empleado 006 " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jtf_buscar_proveedorKeyPressed

    private void jtb_infoproveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtb_infoproveedorMouseClicked
        Connection con = null;
        try {

            con = getConexion();
            ps = con.prepareStatement("SELECT * FROM proveedor WHERE id_proveedor=?");
            ps.setString(1, jtb_infoproveedor.getValueAt(jtb_infoproveedor.getSelectedRow(), 0).toString());
            rs = ps.executeQuery();

            if (rs.next()) {
                jtf_id_proveedor.setText(rs.getString("id_proveedor"));
                jtf_folio_proveedor.setText(rs.getString("folio_proveedor"));
                jtf_nombre_proveedor.setText(rs.getString("nombre_empresa"));
                jtf_calle_proveedor.setText(rs.getString("calle"));
                jtf_numero_proveedor.setText(rs.getString("numero"));
                jtf_colonia_proveedor.setText(rs.getString("colonia"));
                jtf_telefono_proveedor.setText(rs.getString("telefono"));

            } else {
                JOptionPane.showMessageDialog(null, "No existe el empleado con ese id");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Empleado 007 " + ex.getMessage());
        }
    }//GEN-LAST:event_jtb_infoproveedorMouseClicked

    private void jb_nuevo_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_nuevo_productoActionPerformed
        desicion = "nuevo";
        VerDatosProducto(true);
        jtb_info_producto.setEnabled(false);
        jb_seleccionar_producto.setEnabled(true);
        jtf_id_producto.setEnabled(false);
        jtf_folio_producto.setText("");
        jtf_nombre_producto.setText("");
        jtf_existencia_producto.setText("");
        jtf_existencia_um_producto.setText("");
        jtf_precio_producto.setText("");
        jtf_marca_producto.setText("");
        jtf_fecha_ingreso_producto.setEditable(false);
        jtf_fecha_ingreso_producto.setText("Fecha Agregada por defecto!!");
        jtf_descripcion_producto.setText("");
        jtf_categoria_producto.setText("");
        jtf_imagen_producto.setText("");
        jtf_id_proveedor_producto.setText("");
        jb_nuevo_producto.setEnabled(false);
        jb_editar_producto.setEnabled(false);
        jb_eliminar_producto.setEnabled(false);
        jb_guardar_producto.setEnabled(true);
        jb_cancelar_producto.setEnabled(true);
    }//GEN-LAST:event_jb_nuevo_productoActionPerformed

    private void jb_editar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_editar_productoActionPerformed
        desicion = "editar";
        VerDatosProducto(true);
        jtb_info_producto.setEnabled(false);
        jb_seleccionar_producto.setEnabled(true);
        jtf_id_producto.setEnabled(false);
        jtf_fecha_ingreso_producto.setEnabled(false);
        jb_nuevo_producto.setEnabled(false);
        jb_editar_producto.setEnabled(false);
        jb_eliminar_producto.setEnabled(false);
        jb_guardar_producto.setEnabled(true);
        jb_cancelar_producto.setEnabled(true);
    }//GEN-LAST:event_jb_editar_productoActionPerformed

    private void jb_cancelar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_cancelar_productoActionPerformed
        VerDatosProducto(false);
        ConsultaProducto();
        jtb_info_producto.setEnabled(true);
        jb_seleccionar_producto.setEnabled(false);
        jb_nuevo_producto.setEnabled(true);
        jb_editar_producto.setEnabled(true);
        jb_eliminar_producto.setEnabled(true);
        jb_guardar_producto.setEnabled(false);
        jb_cancelar_producto.setEnabled(false);
    }//GEN-LAST:event_jb_cancelar_productoActionPerformed

    private void jb_guardar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_guardar_productoActionPerformed
        if (desicion == "nuevo") {
            Connection con = null;
            try {
                con = getConexion();
                FileInputStream archivofoto;
                ps = con.prepareStatement("INSERT INTO productos (folio_producto, nombre, existencia, existencia_um, precio_unitario, marca, descripcion, categoria, imagen, tam_img, id_proveedor) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, jtf_folio_producto.getText());
                ps.setString(2, jtf_nombre_producto.getText());
                ps.setFloat(3, Float.parseFloat(jtf_existencia_producto.getText()));
                ps.setString(4, jtf_existencia_um_producto.getText());
                ps.setFloat(5, Float.parseFloat(jtf_precio_producto.getText()));
                ps.setString(6, jtf_marca_producto.getText());
                ps.setString(7, jtf_descripcion_producto.getText());
                ps.setString(8, jtf_categoria_producto.getText());
                ps.setString(9, jtf_imagen_producto.getText());
                archivofoto = new FileInputStream(jtf_imagen_producto.getText());
                ps.setBinaryStream(10, archivofoto);
                ps.setInt(11, Integer.parseInt(jtf_id_proveedor_producto.getText()));

                int res = ps.executeUpdate();

                VerDatosProducto(false);
                jtb_info_producto.setEnabled(true);
                jtb_info_producto.setVisible(true);
                tablaproducto();
                jb_seleccionar_producto.setEnabled(false);
                jb_nuevo_producto.setEnabled(true);
                jb_editar_producto.setEnabled(true);
                jb_eliminar_producto.setEnabled(true);
                jb_guardar_producto.setEnabled(false);
                jb_cancelar_producto.setEnabled(false);

                if (res > 0) {
                    JOptionPane.showMessageDialog(this, "Datos de Productos actualizados Correctamente!!");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar Datos de Productos");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Producto 004 " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Campos Vacios" + ex.getMessage());
            }
        } else if (desicion == "editar") {
            Connection con = null;
            try {
                con = getConexion();
                FileInputStream archivofoto;
                ps = con.prepareStatement("UPDATE productos SET folio_producto=?,nombre=?,existencia=?,existencia_um=?,precio_unitario=?,marca=?,descripcion=?,categoria=?,imagen=?, tam_img=?, id_proveedor=? WHERE id_producto=?");
                ps.setString(1, jtf_folio_producto.getText());
                ps.setString(2, jtf_nombre_producto.getText());
                ps.setFloat(3, Float.parseFloat(jtf_existencia_producto.getText()));
                ps.setString(4, jtf_existencia_um_producto.getText());
                ps.setFloat(5, Float.parseFloat(jtf_precio_producto.getText()));
                ps.setString(6, jtf_marca_producto.getText());
                ps.setString(7, jtf_descripcion_producto.getText());
                ps.setString(8, jtf_categoria_producto.getText());
                ps.setString(9, jtf_imagen_producto.getText());
                archivofoto = new FileInputStream(jtf_imagen_producto.getText());
                ps.setBinaryStream(10, archivofoto);
                ps.setInt(11, Integer.parseInt(jtf_id_proveedor_producto.getText()));
                ps.setInt(12, Integer.parseInt(jtf_id_producto.getText()));

                int res = ps.executeUpdate();

                VerDatosProducto(false);
                jtb_info_producto.setVisible(true);
                jtb_info_producto.setEnabled(true);
                tablaproducto();
                jb_seleccionar_producto.setEnabled(false);
                jb_nuevo_producto.setEnabled(true);
                jb_editar_producto.setEnabled(true);
                jb_eliminar_producto.setEnabled(true);
                jb_guardar_producto.setEnabled(false);
                jb_cancelar_producto.setEnabled(false);

                if (res > 0) {
                    JOptionPane.showMessageDialog(this, "Datos de Productos actualizados Correctamente!!");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar Datos de Productos");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Producto 004 " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Campos Vacios" + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jb_guardar_productoActionPerformed

    private void jb_eliminar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_eliminar_productoActionPerformed

        Connection con = null;
        try {
            con = getConexion();
            ps = con.prepareStatement("DELETE FROM productos WHERE id_producto=?");
            ps.setInt(1, Integer.parseInt(jtf_id_producto.getText()));

            int res = ps.executeUpdate();

            VerDatosProducto(false);
            jtb_info_producto.setVisible(true);
            jtb_info_producto.setEnabled(true);
            tablaproducto();
            ConsultaProducto();
            jtb_info_empleado.setEnabled(true);
            jb_nuevo_producto.setEnabled(true);
            jb_editar_producto.setEnabled(true);
            jb_eliminar_producto.setEnabled(true);
            jb_guardar_producto.setEnabled(false);
            jb_cancelar_producto.setEnabled(false);

            if (res > 0) {
                JOptionPane.showMessageDialog(this, "Datos de Productos Eliminados Correctamente!!");
            } else {
                JOptionPane.showMessageDialog(this, "Error al Eliminar Datos de Productos");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Producto 004 " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Campos Vacios" + ex.getMessage());
        }
    }//GEN-LAST:event_jb_eliminar_productoActionPerformed

    private void jb_buscar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_buscar_productoActionPerformed
        jtf_buscar_producto.setEditable(true);
        jtf_buscar_producto.setEnabled(true);
    }//GEN-LAST:event_jb_buscar_productoActionPerformed

    private void jtf_buscar_productoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_buscar_productoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Connection con = null;
            try {

                con = getConexion();
                ps = con.prepareStatement("SELECT * FROM productos WHERE folio_producto=?");
                ps.setString(1, jtf_buscar_producto.getText());
                rs = ps.executeQuery();

                if (rs.next()) {
                    jtf_id_producto.setText(rs.getString("id_producto"));
                    jtf_folio_producto.setText(rs.getString("folio_producto"));
                    jtf_nombre_producto.setText(rs.getString("nombre"));
                    jtf_existencia_producto.setText(rs.getString("existencia"));
                    jtf_existencia_um_producto.setText(rs.getString("existencia_um"));
                    jtf_precio_producto.setText(rs.getString("precio_unitario"));
                    jtf_marca_producto.setText(rs.getString("marca"));
                    jtf_fecha_ingreso_producto.setText(rs.getString("fecha_ingreso"));
                    jtf_descripcion_producto.setText(rs.getString("descripcion"));
                    jtf_categoria_producto.setText(rs.getString("categoria"));
                    jtf_imagen_producto.setText(rs.getString("imagen"));
                    jtf_id_proveedor_producto.setText(rs.getString("id_proveedor"));
                } else {
                    JOptionPane.showMessageDialog(null, "No existe el empleado con ese id");
                }
                JOptionPane.showMessageDialog(this, "Busqueda Exitosa!!");
                jtf_buscar_producto.setEnabled(false);
                jtf_buscar_producto.setText("");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Empleado 006 " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jtf_buscar_productoKeyPressed

    private void jtb_info_productoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtb_info_productoMouseClicked
        Connection con = null;
        try {

            con = getConexion();
            ps = con.prepareStatement("SELECT * FROM productos WHERE id_producto=?");
            ps.setString(1, jtb_info_producto.getValueAt(jtb_info_producto.getSelectedRow(), 0).toString());
            rs = ps.executeQuery();

            if (rs.next()) {
                jtf_id_producto.setText(rs.getString("id_producto"));
                jtf_folio_producto.setText(rs.getString("folio_producto"));
                jtf_nombre_producto.setText(rs.getString("nombre"));
                jtf_existencia_producto.setText(rs.getString("existencia"));
                jtf_existencia_um_producto.setText(rs.getString("existencia_um"));
                jtf_precio_producto.setText(rs.getString("precio_unitario"));
                jtf_marca_producto.setText(rs.getString("marca"));
                jtf_fecha_ingreso_producto.setText(rs.getString("fecha_ingreso"));
                jtf_descripcion_producto.setText(rs.getString("descripcion"));
                jtf_categoria_producto.setText(rs.getString("categoria"));
                jtf_imagen_producto.setText(rs.getString("imagen"));
                jtf_id_proveedor_producto.setText(rs.getString("id_proveedor"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe el empleado con ese id");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Empleado 007 " + ex.getMessage());
        }
    }//GEN-LAST:event_jtb_info_productoMouseClicked

    private void jb_seleccionar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_seleccionar_productoActionPerformed
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos JPEG (*.JPG;*.JPEG)*", "jpg", "png", "jpeg");
        JFileChooser archivo = new JFileChooser();//Todo lo que contiene Chooser viaja a traves de archivo
        archivo.addChoosableFileFilter(filtro);
        archivo.setDialogTitle("Abrir Archivo");
        File ruta = new File("C:/Users/USUARIO/PICTURES");
        archivo.setCurrentDirectory(ruta);
        int ventana = archivo.showOpenDialog(this);//abre una ventana de dialogo 

        if (ventana == JFileChooser.APPROVE_OPTION) {//Verifica si se selecciono un archivo
            File file = archivo.getSelectedFile();
            jtf_imagen_producto.setText(String.valueOf(file));//imprimie la ruta del archivo seleccionado
            Image foto = getToolkit().getImage(jtf_imagen_producto.getText());
            foto = foto.getScaledInstance(150, 285, Image.SCALE_DEFAULT);//Tamaño de la foto
            jl_foto_producto.setIcon(new ImageIcon(foto));
        }
    }//GEN-LAST:event_jb_seleccionar_productoActionPerformed

    private void jtf_existencia_productoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_existencia_productoKeyTyped
        char car = evt.getKeyChar(); // extraemos el dato que el usuario teclea y se guarda en evt
        if ((car < '0' || car > '9') && (car > '.')) {
            evt.consume(); // detecta un caracter y no deja escribirlo
        }
    }//GEN-LAST:event_jtf_existencia_productoKeyTyped

    private void jtf_precio_productoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_precio_productoKeyTyped
        char car = evt.getKeyChar(); // extraemos el dato que el usuario teclea y se guarda en evt
        if ((car < '0' || car > '9') && (car > '.')) {
            evt.consume(); // detecta un caracter y no deja escribirlo
        }
    }//GEN-LAST:event_jtf_precio_productoKeyTyped

    private void jtf_id_proveedor_productoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_id_proveedor_productoKeyTyped
        char car = evt.getKeyChar(); // extraemos el dato que el usuario teclea y se guarda en evt
        if ((car < '0' || car > '9') && (car > '.')) {
            evt.consume(); // detecta un caracter y no deja escribirlo
        }
    }//GEN-LAST:event_jtf_id_proveedor_productoKeyTyped

    private void jtf_numero_proveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_numero_proveedorKeyTyped
        char car = evt.getKeyChar(); // extraemos el dato que el usuario teclea y se guarda en evt
        if ((car < '0' || car > '9') && (car > '.')) {
            evt.consume();
        } // detecta un caracter y no deja escribirlo
    }//GEN-LAST:event_jtf_numero_proveedorKeyTyped

    private void jtf_telefono_proveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_telefono_proveedorKeyTyped
        char car = evt.getKeyChar(); // extraemos el dato que el usuario teclea y se guarda en evt
        if ((car < '0' || car > '9') && (car > '.')) {
            evt.consume(); // detecta un caracter y no deja escribirlo
        }
    }//GEN-LAST:event_jtf_telefono_proveedorKeyTyped

    private void jtf_numero_empleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_numero_empleadoKeyTyped
        char car = evt.getKeyChar(); // extraemos el dato que el usuario teclea y se guarda en evt
        if ((car < '0' || car > '9') && (car > '.')) {
            evt.consume();
        } // detecta un caracter y no deja escribirlo
    }//GEN-LAST:event_jtf_numero_empleadoKeyTyped

    private void jtf_telefono_empleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_telefono_empleadoKeyTyped
        char car = evt.getKeyChar(); // extraemos el dato que el usuario teclea y se guarda en evt
        if ((car < '0' || car > '9') && (car > '.')) {
            evt.consume(); // detecta un caracter y no deja escribirlo
        }
    }//GEN-LAST:event_jtf_telefono_empleadoKeyTyped

    private void jtf_edad_empleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_edad_empleadoKeyTyped
        char car = evt.getKeyChar(); // extraemos el dato que el usuario teclea y se guarda en evt
        if ((car < '0' || car > '9') && (car > '.')) {
            evt.consume(); // detecta un caracter y no deja escribirlo
        }
    }//GEN-LAST:event_jtf_edad_empleadoKeyTyped

    private void jtf_folio_proveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_folio_proveedorKeyTyped
        char car = evt.getKeyChar(); // extraemos el dato que el usuario teclea y se guarda en evt
        if ((car < '0' || car > '9') && (car > '.')) {
            evt.consume(); // detecta un caracter y no deja escribirlo
        }
    }//GEN-LAST:event_jtf_folio_proveedorKeyTyped

    private void jtf_buscar_ventasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_buscar_ventasKeyPressed
            try {
                con = getConexion();
                Statement stmt = con.createStatement();
                String sql = "SELECT id_producto,nombre, precio_unitario, descripcion FROM productos WHERE nombre  LIKE '"+jtf_buscar_ventas.getText()+"%'";
                System.out.println(sql);
                ResultSet rs = stmt.executeQuery(sql);
                
                //ps.setString(1, jtf_buscar_ventas.getText());
                //rs = ps.executeQuery();
                String Titulo[]={"id","nombre","precio","descripcion"};
                String registros[]=new String[4];
                DefaultTableModel modelo= new DefaultTableModel(null,Titulo);
                rs.next();
                //if (rs.next()){
                    
                    while(rs.next()) {
                        
                        registros[0]=rs.getString(1);
                        registros[1]=rs.getString(2);
                        registros[2]=rs.getString(3);
                        registros[3]=rs.getString(4);
                        modelo.addRow(registros);
                    }
                    jt_productos.setModel(modelo);

                //} else {
                    //JOptionPane.showMessageDialog(null, "No existe el producto");
                //}

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error 001 " + ex.getMessage());
            }
        
    }//GEN-LAST:event_jtf_buscar_ventasKeyPressed
    // fecha y hora para laventa
    public void fechaHora(){
        
        Calendar cal=Calendar.getInstance(); 

        String fecha=cal.get(cal.DATE)+"-"+cal.get(cal.MONTH)+"-"+cal.get(cal.YEAR);
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 

        jtf_fecha_ventas.setText(fecha);
        jtf_hora_ventas.setText(hora);

        jtf_fecha_ventas.setEnabled(false);
        jtf_hora_ventas.setEnabled(false);      
       
}
    
  
    
    
    
    
    private void jtf_efectivo_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_efectivo_ventasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_efectivo_ventasActionPerformed

    private void jtf_fecha_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_fecha_ventasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_fecha_ventasActionPerformed

    private void jb_realizar_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_realizar_ventasActionPerformed
        String c_efectivo = jtf_efectivo_ventas.getText();
        float cambio = 0.0f;
        
        if(c_efectivo == ""){
            JOptionPane.showMessageDialog(this, "Complete el campo efectivo antes de continuar");
        } else {
            try {
                float efectivo = Float.parseFloat(jtf_efectivo_ventas.getText());
                if (efectivo < total) {
                    JOptionPane.showMessageDialog(this, "Efectivo insuficiente");
                } else {
                    fechaHora();
                    cambio = efectivo - total;
                    jtf_cambio_ventas.setText(String.valueOf(cambio));
                    jtf_cambio_ventas.setEnabled(false);
                }
            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Complete el campo efectivo para finalizar la venta");
            }
             
        }
        
        
    }//GEN-LAST:event_jb_realizar_ventasActionPerformed

    private void jb_agregar_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_agregar_ventasActionPerformed
        try{
            String nombre = jtf_nombre_ventas.getText();
            String descripcion = jtf_descripcion_ventas.getText();
            String precios = jtf_pu_ventas.getText();
            String piezas = Integer.toString((int) js_cantidad_ventas.getValue());
          
        
            String [] fila = {nombre,descripcion, precios, piezas};
            DefaultTableModel modelo = (DefaultTableModel)jt_busqueda_ventas.getModel();
        
            modelo.addRow(fila);
            
            float precio = Float.parseFloat(precios);
            float pieza = Float.parseFloat(piezas);
            
            subtotal = (precio * pieza);
            total = total + subtotal;
            
            this.jtf_total_ventas.setText("$"+String.valueOf(total));
            
            
            
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "error al agregar producto");
        }
        
    }//GEN-LAST:event_jb_agregar_ventasActionPerformed

    private void jb_nuevo6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_nuevo6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jb_nuevo6ActionPerformed

    private void jb_eliminar_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_eliminar_ventasActionPerformed
        
        if(jt_busqueda_ventas.getSelectedRow() != -1){
            DefaultTableModel model1 = (DefaultTableModel) jt_busqueda_ventas.getModel();
            
 
            //aca capturo el primer dato de la celda seleccionada 

            String precios =String.valueOf(model1.getValueAt(jt_busqueda_ventas.getSelectedRow(),2));
            String piezas =String.valueOf(model1.getValueAt(jt_busqueda_ventas.getSelectedRow(),3));
            
           
            //int a = jt_busqueda_ventas.getSelectedRow();
        
            //String precios = String.valueOf(jt_productos.getValueAt(a, 2));
            //String piezas = String.valueOf(jt_productos.getValueAt(a, 3));
            
            float precio = Float.parseFloat(precios);
            float pieza = Float.parseFloat(piezas); 
            
            JOptionPane.showMessageDialog(this, "¡Producto Eliminado!");
            
            subtotal = precio * pieza;
            total = total - subtotal;
            
            jtf_total_ventas.setText(String.valueOf(total));
            
            //JOptionPane.showMessageDialog(this, model1.getDataVector().get(jt_busqueda_ventas.getSelectedRow());
            model1.removeRow(jt_busqueda_ventas.getSelectedRow());
            
        }
    }//GEN-LAST:event_jb_eliminar_ventasActionPerformed

    private void jtf_descripcion_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_descripcion_ventasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_descripcion_ventasActionPerformed
    
    public void deshabilitar(){
        jtf_nombre_ventas.setEnabled(false);
        jtf_pu_ventas.setEnabled(false);
        jtf_descripcion_ventas.setEnabled(false);
    }
    private void jt_productosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_productosMouseClicked
        int seleccion = jt_productos.getSelectedRow(); //envia datos a los jtf de la fila seleccionada de la tabla jt_productos
        
        jtf_nombre_ventas.setText(String.valueOf(jt_productos.getValueAt(seleccion, 1)));
        jtf_pu_ventas.setText(String.valueOf(jt_productos.getValueAt(seleccion, 2)));
        jtf_descripcion_ventas.setText(String.valueOf(jt_productos.getValueAt(seleccion, 3)));
    }//GEN-LAST:event_jt_productosMouseClicked

    private void jt_productosAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jt_productosAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_productosAncestorAdded

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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio(0).setVisible(true);
                
            }
        });
    }
    
    
    public void inicio(){
        Connection con = null;
        try {

            con = getConexion();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_producto,nombre, precio_unitario, descripcion FROM productos");
            //ps.setString(1, jtf_buscar_ventas.getText());
            //rs = ps.executeQuery()
            String Titulo[]={"id","nombre","precio","descripcion"};
            String registros[]=new String[4];
            DefaultTableModel modelo= new DefaultTableModel(null,Titulo);
 
            while(rs.next()) {
                registros[0]=rs.getString(1);
                registros[1]=rs.getString(2);
                registros[2]=rs.getString(3);
                registros[3]=rs.getString(4);
                modelo.addRow(registros);
            }
            jt_productos.setModel(modelo);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error emple 001 " + ex.getMessage());
        }
    
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jb_agregar_ventas;
    private javax.swing.JButton jb_buscar_empleado;
    private javax.swing.JButton jb_buscar_producto;
    private javax.swing.JButton jb_buscar_proveedor;
    private javax.swing.JButton jb_cancelar_empleado;
    private javax.swing.JButton jb_cancelar_producto;
    private javax.swing.JButton jb_cancelar_proveedor;
    private javax.swing.JButton jb_editar_empleado;
    private javax.swing.JButton jb_editar_producto;
    private javax.swing.JButton jb_editar_proveedor;
    private javax.swing.JButton jb_eliminar_empleado;
    private javax.swing.JButton jb_eliminar_producto;
    private javax.swing.JButton jb_eliminar_proveedor;
    private javax.swing.JButton jb_eliminar_ventas;
    private javax.swing.JButton jb_empleados;
    private javax.swing.JButton jb_guardar_empleado;
    private javax.swing.JButton jb_guardar_producto;
    private javax.swing.JButton jb_guardar_proveedor;
    private javax.swing.JButton jb_home;
    private javax.swing.JButton jb_nuevo6;
    private javax.swing.JButton jb_nuevo7;
    private javax.swing.JButton jb_nuevo_empleado;
    private javax.swing.JButton jb_nuevo_producto;
    private javax.swing.JButton jb_nuevo_proveedor;
    private javax.swing.JButton jb_productos;
    private javax.swing.JButton jb_proveedores;
    private javax.swing.JButton jb_realizar_ventas;
    private javax.swing.JButton jb_seleccionar_producto;
    private javax.swing.JButton jb_ventas;
    private javax.swing.JLabel jl_ape_mat_empleado;
    private javax.swing.JLabel jl_ape_pat_empleado;
    private javax.swing.JLabel jl_cali_logo;
    private javax.swing.JLabel jl_calle_empleado;
    private javax.swing.JLabel jl_calle_proveedor;
    private javax.swing.JLabel jl_categoria_producto;
    private javax.swing.JLabel jl_colonia_empleado;
    private javax.swing.JLabel jl_colonia_proveedor;
    private javax.swing.JLabel jl_descripcion_producto;
    private javax.swing.JLabel jl_edad_empleado;
    private javax.swing.JLabel jl_existencia_producto;
    private javax.swing.JLabel jl_existencia_um_producto;
    private javax.swing.JLabel jl_fecha_ingreso_producto;
    private javax.swing.JLabel jl_folio_empleado;
    private javax.swing.JLabel jl_folio_producto;
    private javax.swing.JLabel jl_folio_proveedor;
    private javax.swing.JLabel jl_foto_producto;
    private javax.swing.JLabel jl_id_empleado;
    private javax.swing.JLabel jl_id_producto;
    private javax.swing.JLabel jl_id_proveedor;
    private javax.swing.JLabel jl_id_proveedor_producto;
    private javax.swing.JLabel jl_imagen_producto;
    private javax.swing.JLabel jl_marca_producto;
    private javax.swing.JLabel jl_nombre_empleado;
    private javax.swing.JLabel jl_nombre_producto;
    private javax.swing.JLabel jl_nombre_producto1;
    private javax.swing.JLabel jl_nombre_producto10;
    private javax.swing.JLabel jl_nombre_producto11;
    private javax.swing.JLabel jl_nombre_producto13;
    private javax.swing.JLabel jl_nombre_producto2;
    private javax.swing.JLabel jl_nombre_producto3;
    private javax.swing.JLabel jl_nombre_producto4;
    private javax.swing.JLabel jl_nombre_producto5;
    private javax.swing.JLabel jl_nombre_producto6;
    private javax.swing.JLabel jl_nombre_producto7;
    private javax.swing.JLabel jl_nombre_producto8;
    private javax.swing.JLabel jl_nombre_producto9;
    private javax.swing.JLabel jl_nombre_proveedor;
    private javax.swing.JLabel jl_numero_empleado;
    private javax.swing.JLabel jl_numero_proveedor;
    private javax.swing.JLabel jl_precio_producto;
    private javax.swing.JLabel jl_telefono_empleado;
    private javax.swing.JLabel jl_telefono_proveedor;
    private javax.swing.JPanel jp_contenedor;
    private javax.swing.JPanel jp_empleados;
    private javax.swing.JPanel jp_home;
    private javax.swing.JPanel jp_menu;
    private javax.swing.JPanel jp_modulos;
    private javax.swing.JPanel jp_productos;
    private javax.swing.JPanel jp_proveedores;
    private javax.swing.JPanel jp_ventas;
    private javax.swing.JSpinner js_cantidad_ventas;
    private javax.swing.JTable jt_busqueda_ventas;
    private javax.swing.JTable jt_productos;
    private javax.swing.JTable jtb_info_empleado;
    private javax.swing.JTable jtb_info_producto;
    private javax.swing.JTable jtb_infoproveedor;
    private javax.swing.JTextField jtf_ape_mat_empleado;
    private javax.swing.JTextField jtf_ape_pat_empleado;
    private javax.swing.JTextField jtf_buscar_empleado;
    private javax.swing.JTextField jtf_buscar_producto;
    private javax.swing.JTextField jtf_buscar_proveedor;
    private javax.swing.JTextField jtf_buscar_ventas;
    private javax.swing.JTextField jtf_calle_empleado;
    private javax.swing.JTextField jtf_calle_proveedor;
    private javax.swing.JTextField jtf_cambio_ventas;
    private javax.swing.JTextField jtf_categoria_producto;
    private javax.swing.JTextField jtf_colonia_empleado;
    private javax.swing.JTextField jtf_colonia_proveedor;
    private javax.swing.JTextField jtf_descripcion_producto;
    private javax.swing.JTextField jtf_descripcion_ventas;
    private javax.swing.JTextField jtf_edad_empleado;
    private javax.swing.JTextField jtf_efectivo_ventas;
    private javax.swing.JTextField jtf_existencia_producto;
    private javax.swing.JTextField jtf_existencia_um_producto;
    private javax.swing.JTextField jtf_fecha_ingreso_producto;
    private javax.swing.JTextField jtf_fecha_ventas;
    private javax.swing.JTextField jtf_folio_empleado;
    private javax.swing.JTextField jtf_folio_producto;
    private javax.swing.JTextField jtf_folio_proveedor;
    private javax.swing.JTextField jtf_hora_ventas;
    private javax.swing.JTextField jtf_id_empleado;
    private javax.swing.JTextField jtf_id_producto;
    private javax.swing.JTextField jtf_id_proveedor;
    private javax.swing.JTextField jtf_id_proveedor_producto;
    private javax.swing.JTextField jtf_imagen_producto;
    private javax.swing.JTextField jtf_marca_producto;
    private javax.swing.JTextField jtf_nombre_empleado;
    private javax.swing.JTextField jtf_nombre_producto;
    private javax.swing.JTextField jtf_nombre_producto3;
    private javax.swing.JTextField jtf_nombre_producto5;
    private javax.swing.JTextField jtf_nombre_proveedor;
    private javax.swing.JTextField jtf_nombre_ventas;
    private javax.swing.JTextField jtf_numero_empleado;
    private javax.swing.JTextField jtf_numero_proveedor;
    private javax.swing.JTextField jtf_precio_producto;
    private javax.swing.JTextField jtf_pu_ventas;
    private javax.swing.JTextField jtf_telefono_empleado;
    private javax.swing.JTextField jtf_telefono_proveedor;
    private javax.swing.JTextField jtf_total_ventas;
    private javax.swing.JLabel lj_logo;
    // End of variables declaration//GEN-END:variables
}
