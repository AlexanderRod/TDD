/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdd;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Rodrigo Calderon
 */
public class CarritoComprasTest {

    @Test
    public void agregarProductosCarrito() throws Exception {
        CarritoCompras carrito = new CarritoCompras();
        carrito.agregar(new Producto(new BigDecimal("20.00")));
        carrito.agregar(new Producto(new BigDecimal("20.00")));
        assertEquals(2, carrito.tamaño());
    }
    
    @Test
    public void calcularTotalProductosCarrito() throws Exception {
        CarritoCompras carrito = new CarritoCompras();
        carrito.agregar(new Producto(new BigDecimal("19.99")));
        carrito.agregar(new Producto(new BigDecimal("20.00")));
        assertEquals(new BigDecimal("39.99"),carrito.total());
    }
    
    @Test
    public void denegarPrecioNegativosProductos() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CarritoCompras carrito = new CarritoCompras();
            carrito.agregar(new Producto(new BigDecimal("-10.00")));
        });
    }
    
    @Test
    public void aceptarPrecioCero() throws Exception {
        CarritoCompras carrito = new CarritoCompras();
        carrito.agregar(new Producto(new BigDecimal("0.00")));
        assertEquals(new BigDecimal("0.00"), carrito.total());
    }
    
    @Test
    public void agregarImpuestoVentas() throws Exception {
        CarritoCompras carrito = new CarritoCompras();
        carrito.agregar(new Producto(new BigDecimal("20.00")));
        carrito.agregar(new Producto(new BigDecimal("20.00")));
        assertEquals(new BigDecimal("47.2000"), carrito.totalImpuesto());
    }
    
    @Test
    public void diferentesTiposProductos() throws Exception {
        CarritoCompras carrito = new CarritoCompras();
        carrito.agregar(new Libro(new BigDecimal("20.00")));
        carrito.agregar(new Video(new BigDecimal("20.99")));
        assertEquals(new BigDecimal("40.99"), carrito.total());
    } 
    
    @Test
    public void agregarPromociones() throws Exception {
        CarritoCompras carrito = new CarritoCompras();
        carrito.agregar(new Libro(new BigDecimal("20.00")));
        carrito.agregar(new Video(new BigDecimal("20.00")));
        carrito.establecerPromocion(new Promocion() {
            public BigDecimal applyTo(Producto producto){
                if(producto instanceof Video) {
                    return new BigDecimal("19.00");
                }
                return producto.obtenerPrecio();
            }
        });
        assertEquals(new BigDecimal("39.00"), carrito.total());
    }
    
}
