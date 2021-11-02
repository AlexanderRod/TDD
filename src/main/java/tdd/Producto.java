/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdd;

import java.math.BigDecimal;

/**
 *
 * @author Rodrigo Calderon
 */
public class Producto {
    
    private BigDecimal precio;
    
    public Producto(BigDecimal precio){
        this.precio = precio;
    }
    
    public BigDecimal obtenerPrecio(){
        return precio;
    }
    
}
