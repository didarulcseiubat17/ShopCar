/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.controller;

import shopcar.entities.Cliente;
import shopcar.entities.Veiculo;

/**
 *
 * @author info1
 */
public interface IVendeCarro 
{
    public void Vender(Veiculo veiculo, Cliente cliente);
}
