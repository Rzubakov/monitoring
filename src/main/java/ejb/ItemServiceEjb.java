/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entitys.Item;
import entitys.User;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import jpa.ItemServiceJpa;

/**
 *
 * @author Rzubakov
 */
@Stateless
public class ItemServiceEjb {
    
    public ItemServiceEjb(){
    }
    
    @EJB
    private ItemServiceJpa itemServiceJpa;
    
    public List<Item> getByUser(User user){
        return itemServiceJpa.getByUser(user);
    } 
    public Item getItem(Long id){
        return itemServiceJpa.get(id);
    }    
}

