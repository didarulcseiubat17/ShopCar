/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.repository;

import java.util.List;
import javax.inject.Inject;
import shopcar.entities.Modelo;
import shopcar.repository.JpaDAO;

/**
 *
 * @author Aluno
 */
public class ModeloDAO
{
    @Inject private JpaDAO<Modelo> daoModelo;
    
    public ModeloDAO()  { }
    
    public List<Modelo> listAllModelosByMarca(Object value)
    {
        List<Modelo> resultList = daoModelo.getByRestriction
        (Modelo.MODELOS_BY_MARCA, "marca" , value);
        
        return resultList;
    }
}
