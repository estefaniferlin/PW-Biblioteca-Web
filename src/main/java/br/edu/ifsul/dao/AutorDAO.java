package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Autor;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author estef
 */
@Stateful
public class AutorDAO<TIPO> extends DAOGenerico<Autor> implements Serializable{
    
    public AutorDAO(){
        super(); // chamo o construtor do pai DAOGenerico
        classePersistente = Autor.class;
    }
    
}
