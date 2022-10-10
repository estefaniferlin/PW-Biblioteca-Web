package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Livro;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author estef
 */
@Stateful
public class LivroDAO<TIPO> extends DAOGenerico<Livro> implements Serializable{
    
    public LivroDAO(){
        super(); // chamo o construtor do pai DAOGenerico
        classePersistente = Livro.class;
        // definir as ordens possíveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("titulo", "Título", "like"));
        listaOrdem.add(new Ordem("quantidade", "Quantidade", "="));
        listaOrdem.add(new Ordem("autor.nome", "Autor", "like"));
        listaOrdem.add(new Ordem("categoria.nome", "Categoria", "like"));
        // definir a ordem inicial
        ordemAtual = listaOrdem.get(1);
        //inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
}
