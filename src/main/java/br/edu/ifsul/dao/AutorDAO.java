package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
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
        //definir as ordens possíveis
        listaOrdem.add(new Ordem("id", "ID", "=")); // id, label, operador
        listaOrdem.add(new Ordem("nome", "Nome", "like"));       
        //definir a ordem inicial
        ordemAtual = listaOrdem.get(1);
        //inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
}
