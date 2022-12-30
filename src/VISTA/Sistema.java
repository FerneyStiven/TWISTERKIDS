/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VISTA;

import MODELO.Cliente;
import MODELO.ClienteDAO;
import MODELO.Detalle;
import MODELO.Eventos;
import MODELO.Producto;
import MODELO.ProductoDAO;
import MODELO.Provedor;
import MODELO.ProvedorDAO;
import MODELO.Venta;
import MODELO.VentaDAO;
import Reportes.Excel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Sistema extends javax.swing.JFrame {
    
    Cliente cl = new Cliente();
    ClienteDAO client = new ClienteDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();
    Eventos EVENT = new Eventos();
    Provedor pr = new Provedor();
    ProvedorDAO prDao = new ProvedorDAO();
    Producto pro = new Producto();
    ProductoDAO proDao = new ProductoDAO();
    Venta v = new Venta();
    VentaDAO vDao = new VentaDAO();
    Detalle de = new Detalle();
    int Totalpagar = 0;
    int Totalven=0;
    int Totalcambio = 0;
    int Dinerorec = 0;

    int item;

    public Sistema() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtidcliente.setVisible(false);
        txtIdProveedor.setVisible(false);
        txtIdProd.setVisible(false);
        txtidventa.setVisible(false);
        AutoCompleteDecorator.decorate(cbxProveedorProd);
        proDao.ConsulatarProvedor(cbxProveedorProd);

    }

    public void ListarCliente() {
        List<Cliente> Listarcl = client.Listarcliente();
        modelo = (DefaultTableModel) TableCliente.getModel();
        LimpirarTable();
        Object[] ob = new Object[5];
        for (int i = 0; i < Listarcl.size(); i++) {
            ob[0] = Listarcl.get(i).getId();
            ob[1] = Listarcl.get(i).getDocumento();
            ob[2] = Listarcl.get(i).getNombre();
            ob[3] = Listarcl.get(i).getCorreo();
            ob[4] = Listarcl.get(i).getTelefono();
            modelo.addRow(ob);

        }
        TableCliente.setModel(modelo);

    }

    public void ListarDetalle() {
       SimpleDateFormat dFormat=new SimpleDateFormat("yyyy-MM-dd");
       String date =dFormat.format(jdateinicial.getDate());
       
        List<Detalle> ListarDe = vDao.ListarVentas(date);
        modelo = (DefaultTableModel) Tablevenf.getModel();
        LimpirarTable();
        Object[] ob = new Object[10];
        for (int i = 0; i < ListarDe.size(); i++) {
            ob[0] = ListarDe.get(i).getId();
            ob[1] = ListarDe.get(i).getCodigopro();
            ob[2] = ListarDe.get(i).getMarca();
            ob[3] = ListarDe.get(i).getTall();
            ob[4] = ListarDe.get(i).getCantidad();
            ob[5] = ListarDe.get(i).getPrecio();
            ob[6] = ListarDe.get(i).getValor();
            ob[7] = ListarDe.get(i).getTotal();
            ob[8] = ListarDe.get(i).getId_venta();
            ob[9] = ListarDe.get(i).getFecha();
            modelo.addRow(ob);

        }
        Tablevenf.setModel(modelo);

    }

    public void ListarProvedor() {
        List<Provedor> Listarpr = prDao.ListarProvedor();
        modelo = (DefaultTableModel) Tableprovedor.getModel();
        LimpirarTable();
        Object[] ob = new Object[5];
        for (int i = 0; i < Listarpr.size(); i++) {
            ob[0] = Listarpr.get(i).getId();
            ob[1] = Listarpr.get(i).getDocumento();
            ob[2] = Listarpr.get(i).getNombre();
            ob[3] = Listarpr.get(i).getTelefono();
            ob[4] = Listarpr.get(i).getCorreo();
            modelo.addRow(ob);

        }
        Tableprovedor.setModel(modelo);

    }

    public void ListarProducto() {

        List<Producto> ListarPro = proDao.ListarProducto();
        modelo = (DefaultTableModel) TableProducto.getModel();
        LimpirarTable();
        Object[] ob = new Object[8];
        for (int i = 0; i < ListarPro.size(); i++) {
            ob[0] = ListarPro.get(i).getId();
            ob[1] = ListarPro.get(i).getCodigo();
            ob[2] = ListarPro.get(i).getMarca();
            ob[3] = ListarPro.get(i).getTalla();
            ob[4] = ListarPro.get(i).getDescripcion();
            ob[5] = ListarPro.get(i).getCantidad();
            ob[6] = ListarPro.get(i).getPrecio();
            ob[7] = ListarPro.get(i).getProvedor();
            modelo.addRow(ob);

        }
        TableProducto.setModel(modelo);

    }

    public void ListarBusqueda() {
        List<Producto> ListarPro = proDao.BuscarProducto();
        modelo = (DefaultTableModel) TableProducto.getModel();
        LimpirarTable();
        //hola
        Object[] ob = new Object[8];
        for (int i = 0; i < ListarPro.size(); i++) {
            ob[0] = ListarPro.get(i).getId();
            ob[1] = ListarPro.get(i).getCodigo();
            ob[2] = ListarPro.get(i).getMarca();
            ob[3] = ListarPro.get(i).getTalla();
            ob[4] = ListarPro.get(i).getDescripcion();
            ob[5] = ListarPro.get(i).getCantidad();
            ob[6] = ListarPro.get(i).getPrecio();
            ob[7] = ListarPro.get(i).getProvedor();
            modelo.addRow(ob);

        }
        TableProducto.setModel(modelo);

    }

    public void LimpirarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnventanue = new javax.swing.JButton();
        btncliente = new javax.swing.JButton();
        btnprodu = new javax.swing.JButton();
        btnventas = new javax.swing.JButton();
        btnprovedor = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btncaja = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCodigoVenta = new javax.swing.JTextField();
        txtMarcaVenta = new javax.swing.JTextField();
        txtDescripcionVenta = new javax.swing.JTextField();
        txtCantidadVenta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableVenta = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnimprim = new javax.swing.JButton();
        txtDocumentoVenta = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtNombreVenta = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        LabelTotalVenta = new javax.swing.JLabel();
        btnsalir1 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        txtstock = new javax.swing.JTextField();
        txtvalor = new javax.swing.JTextField();
        txttallaven = new javax.swing.JTextField();
        txtteleven = new javax.swing.JTextField();
        txtcorreov = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtvendor = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtidventa = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtRecibe = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtcambio = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtIdentificacionCliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        txtCorreoCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        btnGuardarCliente = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableCliente = new javax.swing.JTable();
        txtidcliente = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtDocumentoProveedor = new javax.swing.JTextField();
        txtNombreProveedor = new javax.swing.JTextField();
        txtTelefonoProveedor = new javax.swing.JTextField();
        txtCorreoProveedor = new javax.swing.JTextField();
        btnGuardarProveedor = new javax.swing.JButton();
        btnEditarProveedor = new javax.swing.JButton();
        btnEliminarProveedor = new javax.swing.JButton();
        txtIdProveedor = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        Tableprovedor = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtCodigoProd = new javax.swing.JTextField();
        txtMarcaProd = new javax.swing.JTextField();
        txtTallaProd = new javax.swing.JTextField();
        txtDescripcionProd = new javax.swing.JTextField();
        txtCantidaProd = new javax.swing.JTextField();
        txtPrecioProd = new javax.swing.JTextField();
        cbxProveedorProd = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableProducto = new javax.swing.JTable();
        btnGuardarProd = new javax.swing.JButton();
        btnEditarProd = new javax.swing.JButton();
        btnEliminarProd = new javax.swing.JButton();
        btnNuevoProd = new javax.swing.JButton();
        txtIdProd = new javax.swing.JTextField();
        btnexel = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Tablevenf = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        labelVent = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        btnbuscarv = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jdateinicial = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));

        btnventanue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Nventa.png"))); // NOI18N
        btnventanue.setText("Nueva venta");
        btnventanue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnventanueActionPerformed(evt);
            }
        });

        btncliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Clientes.png"))); // NOI18N
        btncliente.setText("Clientes");
        btncliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclienteActionPerformed(evt);
            }
        });

        btnprodu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/producto.png"))); // NOI18N
        btnprodu.setText("Productos");
        btnprodu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproduActionPerformed(evt);
            }
        });

        btnventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/compras.png"))); // NOI18N
        btnventas.setText("ventas");
        btnventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnventasActionPerformed(evt);
            }
        });

        btnprovedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/proveedor.png"))); // NOI18N
        btnprovedor.setText("provedor");
        btnprovedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprovedorActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/lg1.png"))); // NOI18N

        btncaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/caja.png"))); // NOI18N
        btncaja.setText("caja");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btncaja, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnventas, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btncliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnventanue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnprodu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnprovedor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnventanue)
                .addGap(29, 29, 29)
                .addComponent(btncliente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnprovedor, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnprodu, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnventas, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btncaja, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 710));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/encabezado.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 1000, 10));

        jLabel3.setText("REFERENCIA");

        jLabel4.setText("MARCA");

        jLabel5.setText("TALLA");

        jLabel6.setText("DESCRIPCION");

        jLabel7.setText("UNIDADES");

        txtCodigoVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCodigoVentaMouseClicked(evt);
            }
        });
        txtCodigoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoVentaActionPerformed(evt);
            }
        });
        txtCodigoVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoVentaKeyTyped(evt);
            }
        });

        txtDescripcionVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionVentaActionPerformed(evt);
            }
        });

        txtCantidadVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadVentaActionPerformed(evt);
            }
        });
        txtCantidadVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadVentaKeyTyped(evt);
            }
        });

        tableVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "REFERNCIA", "MARCA", "TALLA", "DESCRIPCION", "UNIDADES", "PRECIO", "VALOR", "TOTAL"
            }
        ));
        tableVenta.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tableVentaAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tableVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableVentaMouseClicked(evt);
            }
        });
        tableVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableVentaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableVenta);
        if (tableVenta.getColumnModel().getColumnCount() > 0) {
            tableVenta.getColumnModel().getColumn(4).setPreferredWidth(12);
        }

        jLabel8.setText("PRECIO");

        txtPrecioVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioVentaActionPerformed(evt);
            }
        });
        txtPrecioVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioVentaKeyTyped(evt);
            }
        });

        jLabel9.setText("IDENTIFICACIÓN");

        btnimprim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/print.png"))); // NOI18N
        btnimprim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimActionPerformed(evt);
            }
        });

        txtDocumentoVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDocumentoVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDocumentoVentaKeyTyped(evt);
            }
        });

        jLabel26.setText("NOMBRE");

        txtNombreVenta.setEditable(false);

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/money.png"))); // NOI18N
        jLabel27.setText("TOTAL A PAGAR");

        LabelTotalVenta.setText("*********************");

        btnsalir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/eliminar.png"))); // NOI18N
        btnsalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir1ActionPerformed(evt);
            }
        });

        jLabel36.setText("CANTIDAD");

        txtstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstockActionPerformed(evt);
            }
        });
        txtstock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtstockKeyPressed(evt);
            }
        });

        txttallaven.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttallavenKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttallavenKeyTyped(evt);
            }
        });

        jLabel38.setText("VALOR");

        txtvendor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtvendorKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtvendorKeyTyped(evt);
            }
        });

        jLabel39.setText("VENDEDOR");

        txtidventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidventaActionPerformed(evt);
            }
        });

        jLabel40.setText("RECIBE");

        txtRecibe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRecibeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRecibeKeyTyped(evt);
            }
        });

        jLabel41.setText("CAMBIO");

        txtcambio.setText("*********************");

        jLabel43.setText("TELEFONO");

        jLabel44.setText("CORREO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodigoVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMarcaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttallaven, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescripcionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtvalor, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtidventa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addComponent(btnsalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(84, 84, 84))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtDocumentoVenta)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                                        .addGap(37, 37, 37)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNombreVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jLabel39))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtteleven, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel44)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtvendor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtcorreov, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnimprim)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(jLabel41))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(LabelTotalVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtcambio, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(87, 87, 87))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(127, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel36)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel38)
                            .addComponent(txtidventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMarcaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescripcionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttallaven, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtvalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnsalir1)
                        .addGap(354, 354, 354)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel43))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtteleven, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNombreVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDocumentoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(LabelTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel44)
                                .addComponent(jLabel39)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcorreov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcambio)
                            .addComponent(txtvendor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnimprim))))
                .addGap(51, 51, 51))
        );

        jTabbedPane1.addTab("tab1", jPanel2);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Identificación:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Nombre:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("correo");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Teléfono:");

        txtIdentificacionCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdentificacionClienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdentificacionClienteKeyTyped(evt);
            }
        });

        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyTyped(evt);
            }
        });

        txtCorreoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCorreoClienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoClienteKeyTyped(evt);
            }
        });

        txtTelefonoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefonoClienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoClienteKeyTyped(evt);
            }
        });

        btnGuardarCliente.setText("GUARDAR");
        btnGuardarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarClienteActionPerformed(evt);
            }
        });

        btnEditarCliente.setText("EDITAR");
        btnEditarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });

        btnEliminarCliente.setText("ELIMINAR");
        btnEliminarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        TableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "DOCUMENTO", "NOMBRE", "CORREO", "TELEFONO"
            }
        ));
        TableCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableClienteMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TableCliente);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdentificacionCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(txtNombreCliente)
                            .addComponent(txtCorreoCliente)
                            .addComponent(txtTelefonoCliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtidcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEliminarCliente)
                            .addComponent(btnGuardarCliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditarCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtIdentificacionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtidcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtCorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditarCliente)
                            .addComponent(btnGuardarCliente))
                        .addGap(32, 32, 32)
                        .addComponent(btnEliminarCliente)))
                .addContainerGap(248, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel3);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Documento");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Nombre:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Teléfono:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("correo");

        txtDocumentoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDocumentoProveedorActionPerformed(evt);
            }
        });
        txtDocumentoProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDocumentoProveedorKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDocumentoProveedorKeyTyped(evt);
            }
        });

        txtNombreProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreProveedorKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreProveedorKeyTyped(evt);
            }
        });

        txtTelefonoProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefonoProveedorKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoProveedorKeyTyped(evt);
            }
        });

        txtCorreoProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCorreoProveedorKeyPressed(evt);
            }
        });

        btnGuardarProveedor.setText("GUARDAR");
        btnGuardarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProveedorActionPerformed(evt);
            }
        });

        btnEditarProveedor.setText("EDITAR");
        btnEditarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProveedorActionPerformed(evt);
            }
        });

        btnEliminarProveedor.setText("ELIMINAR");
        btnEliminarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedorActionPerformed(evt);
            }
        });

        Tableprovedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Documento", "Nombre", "Telefono", "correo"
            }
        ));
        Tableprovedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableprovedorMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(Tableprovedor);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel10)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEditarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                .addComponent(txtNombreProveedor, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDocumentoProveedor, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCorreoProveedor))))
                    .addComponent(btnGuardarProveedor)
                    .addComponent(btnEliminarProveedor)
                    .addComponent(txtIdProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtDocumentoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtCorreoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarProveedor)
                            .addComponent(btnEditarProveedor))
                        .addGap(31, 31, 31)
                        .addComponent(btnEliminarProveedor))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(250, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanel4);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("REFERNCIA");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("MARCA");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setText("TALLA");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("DESCRIPCIÓN");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("CANTIDAD");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setText("PRECIO");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setText("PROVEEDOR");

        txtCodigoProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoProdKeyPressed(evt);
            }
        });

        txtMarcaProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMarcaProdKeyPressed(evt);
            }
        });

        txtTallaProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTallaProdKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTallaProdKeyTyped(evt);
            }
        });

        txtDescripcionProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionProdKeyPressed(evt);
            }
        });

        txtCantidaProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidaProdKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidaProdKeyTyped(evt);
            }
        });

        txtPrecioProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioProdKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioProdKeyTyped(evt);
            }
        });

        cbxProveedorProd.setEditable(true);
        cbxProveedorProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxProveedorProdActionPerformed(evt);
            }
        });

        TableProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "REFERENCIA", "MARCA", "TALLA", "DESCRIPCIÓN", "CANTIDAD", "PRECIO", "PROVEEDOR"
            }
        ));
        TableProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableProductoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TableProducto);
        if (TableProducto.getColumnModel().getColumnCount() > 0) {
            TableProducto.getColumnModel().getColumn(0).setPreferredWidth(1);
            TableProducto.getColumnModel().getColumn(1).setPreferredWidth(50);
            TableProducto.getColumnModel().getColumn(2).setPreferredWidth(100);
            TableProducto.getColumnModel().getColumn(3).setPreferredWidth(10);
            TableProducto.getColumnModel().getColumn(7).setPreferredWidth(60);
        }

        btnGuardarProd.setText("GUARDAR");
        btnGuardarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProdActionPerformed(evt);
            }
        });

        btnEditarProd.setText("EDITAR");
        btnEditarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProdActionPerformed(evt);
            }
        });

        btnEliminarProd.setText("ELIMINAR");
        btnEliminarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProdActionPerformed(evt);
            }
        });

        btnNuevoProd.setText("buscar");
        btnNuevoProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProdActionPerformed(evt);
            }
        });

        btnexel.setText("EXEL");
        btnexel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(cbxProveedorProd, 0, 96, Short.MAX_VALUE)
                        .addGap(801, 801, 801))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(46, 46, 46)
                                .addComponent(txtPrecioProd))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel21)
                                            .addComponent(jLabel20)))
                                    .addComponent(jLabel19))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMarcaProd)
                                    .addComponent(txtTallaProd)
                                    .addComponent(txtCodigoProd)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel23))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCantidaProd)
                                    .addComponent(txtDescripcionProd))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnexel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardarProd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarProd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNuevoProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditarProd, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtMarcaProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtTallaProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDescripcionProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidaProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(txtPrecioProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(cbxProveedorProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarProd)
                    .addComponent(btnEditarProd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarProd)
                    .addComponent(btnNuevoProd))
                .addGap(18, 18, 18)
                .addComponent(btnexel)
                .addContainerGap(158, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab4", jPanel5);

        Tablevenf.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Referencia", "marca", "Talla", "cantidad", "Precio", "Valor", "Total", "num_venta", "Fecha"
            }
        ));
        jScrollPane5.setViewportView(Tablevenf);
        if (Tablevenf.getColumnModel().getColumnCount() > 0) {
            Tablevenf.getColumnModel().getColumn(0).setPreferredWidth(15);
            Tablevenf.getColumnModel().getColumn(3).setPreferredWidth(15);
            Tablevenf.getColumnModel().getColumn(8).setPreferredWidth(30);
        }

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setText("fecha inicial");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("VENTAS");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel31.setText("GASTOS");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "tipo de pago", "descripcion", "cantidad", "fecha"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel32.setText("total ventas");

        jLabel33.setText("total gastos");

        labelVent.setText("**********************");

        jLabel35.setText("**********************");

        btnbuscarv.setText("BUSCAR");
        btnbuscarv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarvActionPerformed(evt);
            }
        });

        jButton6.setText("Excel");

        jLabel28.setText("valor total en caja");

        jLabel37.setText("*******************************");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(422, 422, 422)
                        .addComponent(jLabel28)
                        .addGap(61, 61, 61)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelVent, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jdateinicial, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)
                                .addComponent(btnbuscarv, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(130, 130, 130)
                                .addComponent(jButton6))
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(148, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jdateinicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnbuscarv)
                        .addComponent(jButton6)))
                .addGap(18, 18, 18)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(labelVent))
                .addGap(4, 4, 4)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel35))
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("tab5", jPanel6);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 1000, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnventanueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnventanueActionPerformed

        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_btnventanueActionPerformed

    private void txtCodigoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoVentaActionPerformed

    }//GEN-LAST:event_txtCodigoVentaActionPerformed

    private void txtDescripcionVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionVentaActionPerformed

    private void txtPrecioVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioVentaActionPerformed

    private void tableVentaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tableVentaAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tableVentaAncestorAdded

    private void btnbuscarvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarvActionPerformed
        
        ListarDetalle();
        totalVent();
    }//GEN-LAST:event_btnbuscarvActionPerformed

    private void btnGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarClienteActionPerformed
        if (!"".equals(txtIdentificacionCliente.getText()) && !"".equals(txtNombreCliente.getText()) && !"".equals(txtCorreoCliente.getText()) && !"".equals(txtTelefonoCliente.getText())) {
            cl.setDocumento(txtIdentificacionCliente.getText());
            cl.setNombre(txtNombreCliente.getText());
            cl.setCorreo(txtCorreoCliente.getText());
            cl.setTelefono(txtTelefonoCliente.getText());
            client.RegistrarCliente(cl);
            LimpirarTable();
            LimpiarCliente();
            ListarCliente();
            JOptionPane.showMessageDialog(null, "Cliente Registrado");
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_btnGuardarClienteActionPerformed

    private void btnclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclienteActionPerformed
        LimpirarTable();
        ListarCliente();
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnclienteActionPerformed

    private void TableClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableClienteMouseClicked
        int fila = TableCliente.rowAtPoint(evt.getPoint());
        txtidcliente.setText(TableCliente.getValueAt(fila, 0).toString());
        txtIdentificacionCliente.setText(TableCliente.getValueAt(fila, 1).toString());
        txtNombreCliente.setText(TableCliente.getValueAt(fila, 2).toString());
        txtCorreoCliente.setText(TableCliente.getValueAt(fila, 3).toString());
        txtTelefonoCliente.setText(TableCliente.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_TableClienteMouseClicked

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        if (!"".equals(txtidcliente.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el Cliente?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtidcliente.getText());
                client.EliminarCliente(id);
                JOptionPane.showMessageDialog(null, "cleinte eliminado");
                LimpirarTable();
                LimpiarCliente();
                ListarCliente();
            }
        } else {
        }

    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed
        if ("".equals(txtidcliente.getText())) {
            JOptionPane.showMessageDialog(null, "Selecione una fila");
        } else {
            if (!"".equals(txtIdentificacionCliente.getText()) && !"".equals(txtNombreCliente.getText()) && !"".equals(txtTelefonoCliente.getText()) && !"".equals(txtCorreoCliente.getText())) {
                cl.setDocumento(txtIdentificacionCliente.getText());
                cl.setNombre(txtNombreCliente.getText());
                cl.setCorreo(txtCorreoCliente.getText());
                cl.setTelefono(txtTelefonoCliente.getText());
                cl.setId(Integer.parseInt(txtidcliente.getText()));
                client.ModificarCliente(cl);
                JOptionPane.showMessageDialog(null, "cliente editado");
                LimpirarTable();
                LimpiarCliente();
                ListarCliente();
            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan Vacios");
            }

        }
    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void btnGuardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProveedorActionPerformed
        if (!"".equals(txtDocumentoProveedor.getText()) && !"".equals(txtNombreProveedor.getText()) && !"".equals(txtTelefonoProveedor.getText()) && !"".equals(txtCorreoProveedor.getText())) {
            pr.setDocumento(txtDocumentoProveedor.getText());
            pr.setNombre(txtNombreProveedor.getText());
            pr.setTelefono(txtTelefonoProveedor.getText());
            pr.setCorreo(txtCorreoProveedor.getText());
            prDao.RegistrarProvedor(pr);
            JOptionPane.showMessageDialog(null, "Provedor guardado");
            LimpirarTable();
            ListarProvedor();
            LimpiarProvedor();
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_btnGuardarProveedorActionPerformed

    private void txtDocumentoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocumentoProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocumentoProveedorActionPerformed

    private void btnprovedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprovedorActionPerformed
        LimpirarTable();
        ListarProvedor();
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_btnprovedorActionPerformed

    private void TableprovedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableprovedorMouseClicked
        // TODO add your handling code here:
        int fila = Tableprovedor.rowAtPoint(evt.getPoint());
        txtIdProveedor.setText(Tableprovedor.getValueAt(fila, 0).toString());
        txtDocumentoProveedor.setText(Tableprovedor.getValueAt(fila, 1).toString());
        txtNombreProveedor.setText(Tableprovedor.getValueAt(fila, 2).toString());
        txtTelefonoProveedor.setText(Tableprovedor.getValueAt(fila, 3).toString());
        txtCorreoProveedor.setText(Tableprovedor.getValueAt(fila, 4).toString());


    }//GEN-LAST:event_TableprovedorMouseClicked

    private void btnEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedorActionPerformed
        if (!"".equals(txtIdProveedor.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdProveedor.getText());
                prDao.EliminarProvedor(id);
                JOptionPane.showMessageDialog(null, "provedor eliminado");
                LimpirarTable();
                ListarProvedor();
                LimpiarProvedor();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione una fila para eliminar");
        }
    }//GEN-LAST:event_btnEliminarProveedorActionPerformed

    private void btnEditarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProveedorActionPerformed
        if ("".equals(txtIdProveedor.getText())) {
            JOptionPane.showMessageDialog(null, "selecione una fila");
        } else {
            if (!"".equals(txtDocumentoProveedor.getText()) && !"".equals(txtNombreProveedor.getText()) && !"".equals(txtTelefonoProveedor.getText()) && !"".equals(txtCorreoProveedor.getText())) {
                pr.setDocumento(txtDocumentoProveedor.getText());
                pr.setNombre(txtNombreProveedor.getText());
                pr.setTelefono(txtTelefonoProveedor.getText());
                pr.setCorreo(txtCorreoProveedor.getText());
                pr.setId(Integer.parseInt(txtIdProveedor.getText()));
                prDao.ModificarProvedor(pr);
                JOptionPane.showMessageDialog(null, "provedor editado");
                LimpirarTable();
                ListarProvedor();
                LimpiarProvedor();
            } else {
                JOptionPane.showMessageDialog(null, "Tiene espacios vacios");
            }
        }
    }//GEN-LAST:event_btnEditarProveedorActionPerformed

    private void btnGuardarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProdActionPerformed

        if (!"".equals(txtCodigoProd.getText()) && !"".equals(txtMarcaProd.getText()) && !"".equals(txtTallaProd.getText()) && !"".equals(txtDescripcionProd.getText()) && !"".equals(txtCantidaProd.getText()) && !"".equals(txtPrecioProd.getText()) && !"".equals(cbxProveedorProd.getSelectedItem())) {
            pro.setCodigo(txtCodigoProd.getText());
            pro.setMarca(txtMarcaProd.getText());
            pro.setTalla(Integer.parseInt(txtTallaProd.getText()));
            pro.setDescripcion(txtDescripcionProd.getText());
            pro.setCantidad(Integer.parseInt(txtCantidaProd.getText()));
            pro.setPrecio(Integer.parseInt(txtPrecioProd.getText()));
            pro.setProvedor(cbxProveedorProd.getSelectedItem().toString());
            proDao.RegistarProductos(pro);
            JOptionPane.showMessageDialog(null, "Producto registrado");
            ListarProducto();
        } else {
            JOptionPane.showMessageDialog(null, "los campos estan vacios");
        }
    }//GEN-LAST:event_btnGuardarProdActionPerformed

    private void cbxProveedorProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxProveedorProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxProveedorProdActionPerformed

    private void btnproduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproduActionPerformed
        LimpirarTable();
        ListarProducto();
        jTabbedPane1.setSelectedIndex(3);


    }//GEN-LAST:event_btnproduActionPerformed

    private void TableProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableProductoMouseClicked
        // TODO add your handling code here:
        int fila = TableProducto.rowAtPoint(evt.getPoint());
        txtIdProd.setText(TableProducto.getValueAt(fila, 0).toString());
        txtCodigoProd.setText(TableProducto.getValueAt(fila, 1).toString());
        txtMarcaProd.setText(TableProducto.getValueAt(fila, 2).toString());
        txtTallaProd.setText(TableProducto.getValueAt(fila, 3).toString());
        txtDescripcionProd.setText(TableProducto.getValueAt(fila, 4).toString());
        txtCantidaProd.setText(TableProducto.getValueAt(fila, 5).toString());
        txtPrecioProd.setText(TableProducto.getValueAt(fila, 6).toString());
        cbxProveedorProd.setSelectedItem(TableProducto.getValueAt(fila, 7).toString());

    }//GEN-LAST:event_TableProductoMouseClicked

    private void btnEliminarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProdActionPerformed
        if (!"".equals(txtIdProd.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdProd.getText());
                proDao.EliminarProducto(id);
                JOptionPane.showMessageDialog(null, "producto eliminado eliminado");
                LimpirarTable();
                LimpiarProducto();
                ListarProducto();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione una fila para eliminar");
        }
    }//GEN-LAST:event_btnEliminarProdActionPerformed

    private void btnEditarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProdActionPerformed
        if ("".equals(txtIdProd.getText())) {
            JOptionPane.showMessageDialog(null, "selecione una fila");
        } else {
            if (!"".equals(txtCodigoProd.getText()) && !"".equals(txtMarcaProd.getText()) && !"".equals(txtTallaProd.getText()) && !"".equals(txtDescripcionProd.getText()) && !"".equals(txtCantidaProd.getText()) && !"".equals(txtPrecioProd.getText()) && !"".equals(cbxProveedorProd.getSelectedItem())) {
                pro.setCodigo(txtCodigoProd.getText());
                pro.setMarca(txtMarcaProd.getText());
                pro.setTalla(Integer.parseInt(txtTallaProd.getText()));
                pro.setDescripcion(txtDescripcionProd.getText());
                pro.setCantidad(Integer.parseInt(txtCantidaProd.getText()));
                pro.setPrecio(Integer.parseInt(txtPrecioProd.getText()));
                pro.setProvedor(cbxProveedorProd.getSelectedItem().toString());
                pro.setId(Integer.parseInt(txtIdProd.getText()));
                proDao.ModificarProducto(pro);
                JOptionPane.showMessageDialog(null, "Producto editado");
                LimpirarTable();
                ListarProducto();
                LimpiarProducto();
            }
        }
    }//GEN-LAST:event_btnEditarProdActionPerformed

    private void btnNuevoProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProdActionPerformed

        ListarBusqueda();


    }//GEN-LAST:event_btnNuevoProdActionPerformed

    private void btnexelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexelActionPerformed
        Excel.reporte();
    }//GEN-LAST:event_btnexelActionPerformed

    private void txtCodigoVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCodigoVenta.getText())) {
                String cod = txtCodigoVenta.getText();
                pro = proDao.BuscarPro(cod);
                if (pro.getMarca() != null) {
                    txtMarcaVenta.setText("" + pro.getMarca());
                    txtDescripcionVenta.setText("" + pro.getDescripcion());
                    txtstock.setText("" + pro.getCantidad());
                    txtPrecioVenta.setText("" + pro.getPrecio());
                    txtvalor.setText("" + pro.getPrecio());
                    txttallaven.requestFocus();
                } else {
                    txtMarcaVenta.setText("");
                    txtDescripcionVenta.setText("");
                    txtPrecioVenta.setText("");
                    txtvalor.setText("");
                    txtstock.setText("");
                    txtCodigoVenta.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el Codigo del producto");
                txtCodigoVenta.requestFocus();
            }

        }
    }//GEN-LAST:event_txtCodigoVentaKeyPressed

    private void txtCantidadVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCantidadVenta.getText())) {
                String cod = txtCodigoVenta.getText();
                String marca = txtMarcaVenta.getText();
                String talla = txttallaven.getText();
                String descripcion = txtDescripcionVenta.getText();
                int cant = Integer.parseInt(txtCantidadVenta.getText());
                int precio = Integer.parseInt(txtPrecioVenta.getText());
                int valor = Integer.parseInt(txtvalor.getText());
                int total = cant * precio;
                int stock = Integer.parseInt(txtstock.getText());
                if (stock >= cant) {
                    item = item + 1;
                    tmp = (DefaultTableModel) tableVenta.getModel();
                    for (int i = 0; i < tableVenta.getRowCount(); i++) {
                        if (tableVenta.getValueAt(i, 0).equals(txtCodigoVenta.getText()) && tableVenta.getValueAt(i, 2).equals(txttallaven.getText())) {
                            JOptionPane.showMessageDialog(null, "El producto ya esta registrado");
                            return;
                        }

                    }
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(cod);
                    lista.add(marca);
                    lista.add(talla);
                    lista.add(descripcion);
                    lista.add(cant);
                    lista.add(precio);
                    lista.add(valor);
                    lista.add(total);
                    Object[] o = new Object[8];
                    o[0] = lista.get(1);
                    o[1] = lista.get(2);
                    o[2] = lista.get(3);
                    o[3] = lista.get(4);
                    o[4] = lista.get(5);
                    o[5] = lista.get(6);
                    o[6] = lista.get(7);
                    o[7] = lista.get(8);
                    tmp.addRow(o);
                    tableVenta.setModel(tmp);
                    totaPagar();
                    LimpiarVenta();

                    txtCodigoVenta.requestFocus();

                } else {
                    JOptionPane.showMessageDialog(null, "EL NUMERO DE UNIDADES SUPERA LAS PERMITIDAS");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese cantidad");

            }
        }
    }//GEN-LAST:event_txtCantidadVentaKeyPressed

    private void txtPrecioVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtPrecioVenta.getText())) {

                txtCantidadVenta.requestFocus();

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una talla");
                txtPrecioVenta.requestFocus();
            }
        }

    }//GEN-LAST:event_txtPrecioVentaKeyPressed

    private void txttallavenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttallavenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txttallaven.getText())) {
                int val = Integer.parseInt(txttallaven.getText());
                if (val >= 21 && val <= 35) {
                    txtPrecioVenta.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese una talla valida");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una talla");
                txttallaven.requestFocus();
            }
        }
    }//GEN-LAST:event_txttallavenKeyPressed

    private void btnsalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir1ActionPerformed
        modelo = (DefaultTableModel) tableVenta.getModel();
        modelo.removeRow(tableVenta.getSelectedRow());
        totaPagar();
        txtCodigoVenta.requestFocus();
    }//GEN-LAST:event_btnsalir1ActionPerformed

    private void txtDocumentoVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtDocumentoVenta.getText())) {
                String documento = txtDocumentoVenta.getText();
                cl = client.BuscarCliente(documento);
                if (cl.getNombre() != null) {
                    txtNombreVenta.setText("" + cl.getNombre());
                    txtteleven.setText("" + cl.getTelefono());
                    txtcorreov.setText("" + cl.getCorreo());
                    txtvendor.requestFocus();
                } else {
                    txtDocumentoVenta.setText("");
                    JOptionPane.showMessageDialog(null, "El cliente no existe");
                }
            }
        }
    }//GEN-LAST:event_txtDocumentoVentaKeyPressed

    private void txtCantidadVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadVentaActionPerformed

    private void tableVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableVentaMouseClicked
        int fila = TableCliente.rowAtPoint(evt.getPoint());
        txtCodigoVenta.setText(tableVenta.getValueAt(fila, 0).toString());
        txtMarcaVenta.setText(tableVenta.getValueAt(fila, 1).toString());
        txttallaven.setText(tableVenta.getValueAt(fila, 2).toString());
        txtDescripcionVenta.setText(tableVenta.getValueAt(fila, 3).toString());
        txtPrecioVenta.setText(tableVenta.getValueAt(fila, 5).toString());
        txtCantidadVenta.setText(tableVenta.getValueAt(fila, 4).toString());
        modelo = (DefaultTableModel) tableVenta.getModel();
        modelo.removeRow(tableVenta.getSelectedRow());
        totaPagar();
        txtCodigoVenta.requestFocus();


    }//GEN-LAST:event_tableVentaMouseClicked

    private void txtstockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstockActionPerformed

    private void txtCodigoVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCodigoVentaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoVentaMouseClicked

    private void tableVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableVentaKeyPressed

    }//GEN-LAST:event_tableVentaKeyPressed

    private void btnimprimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimActionPerformed
        if (tableVenta.getRowCount() > 0) {
            if (!"".equals(txtDocumentoVenta.getText())) {
                if (!"".equals(txtvendor.getText())) {
                    if (!"".equals(txtRecibe.getText())) {
                        RestrarVenta();
                        RegistrarDetalle();
                        Actualizarcantidad();
                        pdf();
                        LimpiarTableVenta();
                        LimpiarClienteVenta();
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese el monto recibido");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese un vendedor");

                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un usuario valido");

            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay productos registrados");
        }

    }//GEN-LAST:event_btnimprimActionPerformed

    private void txtstockKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstockKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstockKeyPressed

    private void txtidventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidventaActionPerformed

    private void txtRecibeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRecibeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtRecibe.getText())) {
                totalCambio();
            } else {
                JOptionPane.showMessageDialog(null, "El de recibido no puede estar vacio");
            }

        }
    }//GEN-LAST:event_txtRecibeKeyPressed

    private void txtvendorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtvendorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtvendor.getText())) {
                txtRecibe.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Digite un vendedor");
            }

        }
    }//GEN-LAST:event_txtvendorKeyPressed

    private void txtCodigoVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVentaKeyTyped
        //
    }//GEN-LAST:event_txtCodigoVentaKeyTyped

    private void txttallavenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttallavenKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txttallavenKeyTyped

    private void txtCantidadVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVentaKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtCantidadVentaKeyTyped

    private void txtDocumentoVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoVentaKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtDocumentoVentaKeyTyped

    private void txtvendorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtvendorKeyTyped
        EVENT.textKeyPress(evt);
    }//GEN-LAST:event_txtvendorKeyTyped

    private void txtRecibeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRecibeKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtRecibeKeyTyped

    private void txtIdentificacionClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionClienteKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtIdentificacionClienteKeyTyped

    private void txtCorreoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoClienteKeyTyped

    }//GEN-LAST:event_txtCorreoClienteKeyTyped

    private void txtNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyTyped
        EVENT.textKeyPress(evt);
    }//GEN-LAST:event_txtNombreClienteKeyTyped

    private void txtDocumentoProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoProveedorKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtDocumentoProveedorKeyTyped

    private void txtTelefonoProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoProveedorKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTelefonoProveedorKeyTyped

    private void txtNombreProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProveedorKeyTyped
        EVENT.textKeyPress(evt);
    }//GEN-LAST:event_txtNombreProveedorKeyTyped

    private void txtTallaProdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProdKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTallaProdKeyTyped

    private void txtCantidaProdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidaProdKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtCantidaProdKeyTyped

    private void txtPrecioProdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioProdKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtPrecioProdKeyTyped

    private void txtPrecioVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtPrecioVentaKeyTyped

    private void txtIdentificacionClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtIdentificacionCliente.getText())) {
                txtNombreCliente.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese la identificacion del cliente");
                txtIdentificacionCliente.requestFocus();
            }
        }

    }//GEN-LAST:event_txtIdentificacionClienteKeyPressed

    private void txtNombreClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtNombreCliente.getText())) {
                txtCorreoCliente.requestFocus();
            } else {

                JOptionPane.showMessageDialog(null, "Ingrese el Nombre del cliente");
                txtIdentificacionCliente.requestFocus();
            }
        }
    }//GEN-LAST:event_txtNombreClienteKeyPressed

    private void txtCorreoClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCorreoCliente.getText())) {
                txtTelefonoCliente.requestFocus();
            } else {

                JOptionPane.showMessageDialog(null, "Ingrese el correo del cliente");
                txtCorreoCliente.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCorreoClienteKeyPressed

    private void txtTelefonoClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if ("".equals(txtTelefonoCliente.getText())) {
                JOptionPane.showMessageDialog(null, "Ingrese el telefono del cliente");
                txtTelefonoCliente.requestFocus();
            }

        }
    }//GEN-LAST:event_txtTelefonoClienteKeyPressed

    private void txtTelefonoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoClienteKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTelefonoClienteKeyTyped

    private void txtDocumentoProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoProveedorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtDocumentoProveedor.getText())) {
                txtNombreProveedor.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese la identificacion del proveedor");
                txtDocumentoProveedor.requestFocus();
            }
        }
    }//GEN-LAST:event_txtDocumentoProveedorKeyPressed

    private void txtNombreProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProveedorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtNombreProveedor.getText())) {
                txtTelefonoProveedor.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el Nombre del proveedor");
                txtNombreProveedor.requestFocus();
            }
        }
    }//GEN-LAST:event_txtNombreProveedorKeyPressed

    private void txtCorreoProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoProveedorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if ("".equals(txtCorreoProveedor.getText())) {
                JOptionPane.showMessageDialog(null, "Ingrese el correo del provedor");
                txtCorreoProveedor.requestFocus();
            }

        }
    }//GEN-LAST:event_txtCorreoProveedorKeyPressed

    private void txtTelefonoProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoProveedorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtTelefonoProveedor.getText())) {
                txtCorreoProveedor.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el Telefono del proveedor");
                txtTelefonoProveedor.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTelefonoProveedorKeyPressed

    private void txtCodigoProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCodigoProd.getText())) {
                txtMarcaProd.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Referencia");
                txtCodigoProd.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCodigoProdKeyPressed

    private void txtMarcaProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaProdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtMarcaProd.getText())) {
                txtTallaProd.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Marca ");
                txtMarcaProd.requestFocus();
            }
        }
    }//GEN-LAST:event_txtMarcaProdKeyPressed

    private void txtTallaProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtTallaProd.getText())) {
                txtDescripcionProd.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Talla ");
                txtTallaProd.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTallaProdKeyPressed

    private void txtDescripcionProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionProdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtDescripcionProd.getText())) {
                txtCantidaProd.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Descripcion");
                txtDescripcionProd.requestFocus();
            }
        }
    }//GEN-LAST:event_txtDescripcionProdKeyPressed

    private void txtCantidaProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidaProdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCantidaProd.getText())) {
                txtPrecioProd.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Cantidad");
                txtCantidaProd.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCantidaProdKeyPressed

    private void txtPrecioProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioProdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if ("".equals(txtPrecioProd.getText())) {
                JOptionPane.showMessageDialog(null, "Ingrese Un Precio");
                txtPrecioProd.requestFocus();
            }

        }
    }//GEN-LAST:event_txtPrecioProdKeyPressed

    private void btnventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnventasActionPerformed
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_btnventasActionPerformed

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
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sistema().setVisible(true);
            }
        });
    }

    private void LimpiarClienteVenta() {
        txtDocumentoVenta.setText("");
        txtNombreVenta.setText("");
        txtNombreVenta.setText("");
        txtcorreov.setText("");
        txtteleven.setText("");
        txtvendor.setText("");
        LabelTotalVenta.setText("***********");
    }

    private void LimpiarCliente() {
        txtidcliente.setText("");
        txtIdentificacionCliente.setText("");
        txtNombreCliente.setText("");
        txtCorreoCliente.setText("");
        txtTelefonoCliente.setText("");
    }

    private void LimpiarVenta() {
        txtCodigoVenta.setText("");
        txtMarcaVenta.setText("");
        txttallaven.setText("");
        txtDescripcionVenta.setText("");
        txtPrecioVenta.setText("");
        txtCantidadVenta.setText("");
        txtstock.setText("");
        txtvalor.setText("");
        txtidventa.setText("");
    }

    private void LimpiarProvedor() {
        txtIdProveedor.setText("");
        txtDocumentoProveedor.setText("");
        txtNombreProveedor.setText("");
        txtCorreoProveedor.setText("");
        txtTelefonoProveedor.setText("");
    }

    private void LimpiarProducto() {
        txtIdProd.setText("");
        txtCodigoProd.setText("");
        txtMarcaProd.setText("");
        txtTallaProd.setText("");
        txtDescripcionProd.setText("");
        txtCantidaProd.setText("");
        txtPrecioProd.setText("");
        cbxProveedorProd.setSelectedItem(null);
    }

    private void totalCambio() {
        Totalcambio = 0;
        int rec = Integer.parseInt(txtRecibe.getText());
        int val = Integer.parseInt(LabelTotalVenta.getText());
        int valor = rec - val;
        txtcambio.setText("" + valor);

    }

    private void totaPagar() {
        Totalpagar = 0;
        int numfila = tableVenta.getRowCount();
        for (int i = 0; i < numfila; i++) {
            int calcular = Integer.parseInt(String.valueOf(tableVenta.getModel().getValueAt(i, 7)));
            Totalpagar = Totalpagar + calcular;
        }
        LabelTotalVenta.setText("" + Totalpagar);
    }
       private void totalVent() {
        Totalven= 0;
        int numfila = Tablevenf.getRowCount();
        for (int i = 0; i < numfila; i++) {
            int calcular = Integer.parseInt(String.valueOf(Tablevenf.getModel().getValueAt(i, 7)));
            Totalven = Totalven + calcular;
        }
        labelVent.setText("$ " + Totalven);
    }

    private void RestrarVenta() {

        String cliente = txtNombreVenta.getText();
        String vendedor = txtvendor.getText();
        int monto = Totalpagar;
        v.setCliente(cliente);
        v.setVendedor(vendedor);
        v.setTotal(monto);
        vDao.Registrarven(v);

    }

    private void RegistrarDetalle() {
        int id = vDao.IdVenta();
        for (int i = 0; i < tableVenta.getRowCount(); i++) {
            String cod = tableVenta.getValueAt(i, 0).toString();
            String marca = tableVenta.getValueAt(i, 1).toString();
            int talla = Integer.parseInt(tableVenta.getValueAt(i, 2).toString());
            int cant = Integer.parseInt(tableVenta.getValueAt(i, 4).toString());
            int pre = Integer.parseInt(tableVenta.getValueAt(i, 5).toString());
            int val = Integer.parseInt(tableVenta.getValueAt(i, 6).toString());
            int tota = Integer.parseInt(tableVenta.getValueAt(i, 7).toString());
            de.setCodigopro(cod);
            de.setMarca(marca);
            de.setTall(talla);
            de.setCantidad(cant);
            de.setPrecio(pre);
            de.setValor(val);
            de.setTotal(tota);
            de.setId_venta(id);
            vDao.RegistrarDetalle(de);

        }

    }

    private void Actualizarcantidad() {
        for (int i = 0; i < tableVenta.getRowCount(); i++) {
            String cod = tableVenta.getValueAt(i, 0).toString();
            int talla = Integer.parseInt(tableVenta.getValueAt(i, 2).toString());
            int cant = Integer.parseInt(tableVenta.getValueAt(i, 4).toString());
            pro = proDao.BuscarPro(cod);
            int CantidadActual = pro.getCantidad() - cant;
            vDao.ActualizarCantidad(CantidadActual, cod, talla);
        }
    }

    private void LimpiarTableVenta() {
        tmp = (DefaultTableModel) tableVenta.getModel();
        int fila = tableVenta.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);
        }

    }

    private void pdf() {
        try {
            int id = vDao.IdVenta();
            FileOutputStream archivo;
            String fileName = "productos";
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/venta" + id + ".pdf");
            //File file = new File("src/PDF/venta"+id+".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance("src/img/lg1.png");

            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);
            Date date = new Date();
            fecha.add("Factura: " + id + "\n" + "Fecha: " + new SimpleDateFormat("dd-mm-yyyy").format(date) + "\n\n");

            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            Encabezado.addCell(img);

            String ruc = "91.529.252-2";
            String nombre = "Twister Kids";
            String Telefono = "3045945677";
            String Direcion = "Calle 37 #16-04";
            String Razon = "centro comercial ";

            Encabezado.addCell("");
            Encabezado.addCell("Ruc: " + ruc + "\nNombre :" + nombre + "\nTelefono: " + Telefono + "\nDireccion: " + Direcion + "\n       " + Razon);
            Encabezado.addCell(fecha);
            doc.add(Encabezado);

            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("Datos del cliente\n\n");
            doc.add(cli);

            PdfPTable tableclient = new PdfPTable(4);
            tableclient.setWidthPercentage(100);
            tableclient.getDefaultCell().setBorder(0);
            float[] Columnaclient = new float[]{30f, 50f, 40f, 50f};
            tableclient.setWidths(Columnaclient);
            tableclient.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cli1 = new PdfPCell(new Phrase("Documento", negrita));
            PdfPCell cli2 = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell cli3 = new PdfPCell(new Phrase("Telefono", negrita));
            PdfPCell cli4 = new PdfPCell(new Phrase("Correo", negrita));
            cli1.setBorder(0);
            cli2.setBorder(0);
            cli3.setBorder(0);
            cli4.setBorder(0);
            tableclient.addCell(cli1);
            tableclient.addCell(cli2);
            tableclient.addCell(cli3);
            tableclient.addCell(cli4);
            tableclient.addCell(txtDocumentoVenta.getText());
            tableclient.addCell(txtNombreVenta.getText());
            tableclient.addCell(txtteleven.getText());
            tableclient.addCell(txtcorreov.getText() + "\n\n");

            doc.add(tableclient);

            //productos
            PdfPTable tableproducto = new PdfPTable(5);
            tableproducto.setWidthPercentage(100);
            tableproducto.getDefaultCell().setBorder(0);
            float[] Columnaprod = new float[]{40f, 40f, 40f, 40f, 40f};
            tableproducto.setWidths(Columnaprod);
            tableproducto.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell pro1 = new PdfPCell(new Phrase("Referencia", negrita));
            PdfPCell pro2 = new PdfPCell(new Phrase("Marca", negrita));
            PdfPCell pro3 = new PdfPCell(new Phrase("Unidades", negrita));
            PdfPCell pro4 = new PdfPCell(new Phrase("Precio U.", negrita));
            PdfPCell pro5 = new PdfPCell(new Phrase("Precio T.", negrita));
            pro1.setBorder(0);
            pro2.setBorder(0);
            pro3.setBorder(0);
            pro4.setBorder(0);
            pro5.setBorder(0);
            pro1.setBackgroundColor(BaseColor.GREEN);
            pro2.setBackgroundColor(BaseColor.GREEN);
            pro3.setBackgroundColor(BaseColor.GREEN);
            pro4.setBackgroundColor(BaseColor.GREEN);
            pro5.setBackgroundColor(BaseColor.GREEN);

            tableproducto.addCell(pro1);
            tableproducto.addCell(pro2);
            tableproducto.addCell(pro3);
            tableproducto.addCell(pro4);
            tableproducto.addCell(pro5);
            for (int i = 0; i < tableVenta.getRowCount(); i++) {
                String Ref = tableVenta.getValueAt(i, 0).toString();
                String Mar = tableVenta.getValueAt(i, 1).toString();
                String Uni = tableVenta.getValueAt(i, 4).toString();
                String PreU = tableVenta.getValueAt(i, 5).toString();
                String PreT = tableVenta.getValueAt(i, 7).toString();
                tableproducto.addCell(Ref);
                tableproducto.addCell(Mar);
                tableproducto.addCell(Uni);
                tableproducto.addCell(PreU);
                tableproducto.addCell(PreT);

            }

            doc.add(tableproducto);

            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            String totalp = LabelTotalVenta.getText();
            info.add("Total a pagar          $ " + totalp);
            info.add("\n\n");
            info.setAlignment(Element.ALIGN_LEFT);
            doc.add(info);

            Paragraph rec = new Paragraph();
            rec.add(Chunk.NEWLINE);
            String dinerorec = txtRecibe.getText();
            rec.add("Monto                    $ " + dinerorec);
            info.add("\n\n");
            rec.setAlignment(Element.ALIGN_LEFT);
            doc.add(rec);

            Paragraph camb = new Paragraph();
            camb.add(Chunk.NEWLINE);
            String totalcam = txtcambio.getText();
            camb.add("Total De cambio     $ " + totalcam);
            info.add("\n\n");
            camb.setAlignment(Element.ALIGN_LEFT);
            doc.add(camb);

            Paragraph mes = new Paragraph();
            mes.add(Chunk.NEWLINE);
            mes.add("Gracias por su compra");
            mes.add("\n\n");
            mes.setAlignment(Element.ALIGN_CENTER);
            doc.add(mes);

            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);

        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelTotalVenta;
    private javax.swing.JTable TableCliente;
    private javax.swing.JTable TableProducto;
    private javax.swing.JTable Tableprovedor;
    private javax.swing.JTable Tablevenf;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnEditarProd;
    private javax.swing.JButton btnEditarProveedor;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarProd;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnGuardarCliente;
    private javax.swing.JButton btnGuardarProd;
    private javax.swing.JButton btnGuardarProveedor;
    private javax.swing.JButton btnNuevoProd;
    private javax.swing.JButton btnbuscarv;
    private javax.swing.JButton btncaja;
    private javax.swing.JButton btncliente;
    private javax.swing.JButton btnexel;
    private javax.swing.JButton btnimprim;
    private javax.swing.JButton btnprodu;
    private javax.swing.JButton btnprovedor;
    private javax.swing.JButton btnsalir1;
    private javax.swing.JButton btnventanue;
    private javax.swing.JButton btnventas;
    private javax.swing.JComboBox<String> cbxProveedorProd;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private com.toedter.calendar.JDateChooser jdateinicial;
    private javax.swing.JLabel labelVent;
    private javax.swing.JTable tableVenta;
    private javax.swing.JTextField txtCantidaProd;
    private javax.swing.JTextField txtCantidadVenta;
    private javax.swing.JTextField txtCodigoProd;
    private javax.swing.JTextField txtCodigoVenta;
    private javax.swing.JTextField txtCorreoCliente;
    private javax.swing.JTextField txtCorreoProveedor;
    private javax.swing.JTextField txtDescripcionProd;
    private javax.swing.JTextField txtDescripcionVenta;
    private javax.swing.JTextField txtDocumentoProveedor;
    private javax.swing.JTextField txtDocumentoVenta;
    private javax.swing.JTextField txtIdProd;
    private javax.swing.JTextField txtIdProveedor;
    private javax.swing.JTextField txtIdentificacionCliente;
    private javax.swing.JTextField txtMarcaProd;
    private javax.swing.JTextField txtMarcaVenta;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtNombreVenta;
    private javax.swing.JTextField txtPrecioProd;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtRecibe;
    private javax.swing.JTextField txtTallaProd;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtTelefonoProveedor;
    private javax.swing.JLabel txtcambio;
    private javax.swing.JTextField txtcorreov;
    private javax.swing.JTextField txtidcliente;
    private javax.swing.JTextField txtidventa;
    private javax.swing.JTextField txtstock;
    private javax.swing.JTextField txttallaven;
    private javax.swing.JTextField txtteleven;
    private javax.swing.JTextField txtvalor;
    private javax.swing.JTextField txtvendor;
    // End of variables declaration//GEN-END:variables

}
