package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CategoriaDAO;
import br.edu.ifsul.modelo.Categoria;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author estef
 */
@Named(value = "controleCategoria")
@ViewScoped
public class ControleCategoria implements Serializable{
    
    @EJB
    private CategoriaDAO<Categoria> dao;
    private Categoria objeto;
    
    public ControleCategoria(){
        
    }
    
    public String listar(){ // redireciona para a página que tem a manutenção
        return "/privado/categoria/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Categoria();
    }
    
    public void alterar(Object id){
        try{
            objeto = dao.getObjectByID(id);
        } catch(Exception e){
            Util.mensagemErro("Erro ao recuperar objeto " + Util.getMensagemErro(e)); 
        }
    }
    
    public void remover(Object id){
        try{
            objeto = dao.getObjectByID(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch(Exception e){
           Util.mensagemInformacao("Erro ao remover objeto " + Util.getMensagemErro(e)); 
        }
    }
    
    public void salvar(){
        try{
            if(objeto.getId() == null){ // quer dizer que é novo
                dao.persist(objeto); // ai ele é persistido
            } else{
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        }
        catch(Exception e){
            Util.mensagemInformacao("Erro ao salvar objeto " + Util.getMensagemErro(e)); 
        }
    }

    public CategoriaDAO<Categoria> getDao() {
        return dao;
    }

    public void setDao(CategoriaDAO<Categoria> dao) {
        this.dao = dao;
    }

    public Categoria getObjeto() {
        return objeto;
    }

    public void setObjeto(Categoria objeto) {
        this.objeto = objeto;
    }

}
