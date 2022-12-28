
package MODELO;


public class Venta {
       private int id;
    private String cliente;
    private String vendedor;
    private String codigo;
    private String marca;
    private int talla;
    private String descripcion;
    private int cantidad;
    private int precio;
    private int valor;
    private int total;
    private int totalp;

    public Venta() {
    }

    public Venta(int id, String cliente, String vendedor, String codigo, String marca, int talla, String descripcion, int cantidad, int precio, int valor, int total, int totalp) {
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.codigo = codigo;
        this.marca = marca;
        this.talla = talla;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.valor = valor;
        this.total = total;
        this.totalp = totalp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalp() {
        return totalp;
    }

    public void setTotalp(int totalp) {
        this.totalp = totalp;
    }
    
    

}
