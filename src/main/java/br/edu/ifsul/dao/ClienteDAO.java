package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
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
        // definir as ordens possíveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like")); 
        listaOrdem.add(new Ordem("cpf", "CPF", "like")); 
        listaOrdem.add(new Ordem("telefone", "Telefone", "like"));
        listaOrdem.add(new Ordem("email", "E-mail", "like"));
        listaOrdem.add(new Ordem("dtCadastro", "Data de Cadastro", "like")); 
        // definir a ordem inicial
        ordemAtual = listaOrdem.get(1);
        //inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
}
