package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Cliente;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author estef
 */
@Stateful
public class ClienteDAO<TIPO> extends DAOGenerico<Cliente> implements Serializable{
    
    public ClienteDAO(){
        super(); // chamo o construtor do pai DAOGenerico
        classePersistente = Cliente.class;
    }
    
}
