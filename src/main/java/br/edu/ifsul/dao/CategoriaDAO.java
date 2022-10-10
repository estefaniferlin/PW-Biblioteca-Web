package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
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
