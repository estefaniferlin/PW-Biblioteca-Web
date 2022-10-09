package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Categoria;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author estef
 */
@Stateful
public class CategoriaDAO<TIPO> extends DAOGenerico<Categoria> implements Serializable{
    
    public CategoriaDAO(){
        super(); // chamo o construtor do pai DAOGenerico
        classePersistente = Categoria.class;
    }
    
}
