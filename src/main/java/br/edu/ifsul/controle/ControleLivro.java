package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AutorDAO;
import br.edu.ifsul.dao.CategoriaDAO;
import br.edu.ifsul.dao.LivroDAO;
import br.edu.ifsul.modelo.Autor;
import br.edu.ifsul.modelo.Categoria;
import br.edu.ifsul.modelo.Livro;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author estef
 */
@Named(value = "controleLivro")
@ViewScoped
public class ControleLivro implements Serializable{
    
    @EJB
    private LivroDAO<Livro> dao;
    private Livro objeto;
    @EJB
    private AutorDAO<Autor> daoAutor;
    @EJB
    private CategoriaDAO<Categoria> daoCategoria;
    
    public ControleLivro(){
        
    }
    
    public String listar(){ // redireciona para a página que tem a manutenção
        return "/privado/livro/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Livro();
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
            System.out.println("removeu");
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch(Exception e){
           Util.mensagemInformacao("Erro ao remover objeto " + Util.getMensagemErro(e)); 
        }
    }
    
    public void salvar(){
        
        try{
            if(objeto.getId() == null){ // quer dizer que é novo
                dao.persist(objeto); // ai ele é persistido
                System.out.println("persistiu");
            } else{
                dao.merge(objeto);
                System.out.println("alterou");
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        }
        catch(Exception e){
            Util.mensagemInformacao("Erro ao salvar objeto " + Util.getMensagemErro(e)); 
        }
    }

    public LivroDAO<Livro> getDao() {
        return dao;
    }

    public void setDao(LivroDAO<Livro> dao) {
        this.dao = dao;
    }

    public Livro getObjeto() {
        return objeto;
    }

    public void setObjeto(Livro objeto) {
        this.objeto = objeto;
    }

    public AutorDAO<Autor> getDaoAutor() {
        return daoAutor;
    }

    public void setDaoAutor(AutorDAO<Autor> daoAutor) {
        this.daoAutor = daoAutor;
    }

    public CategoriaDAO<Categoria> getDaoCategoria() {
        return daoCategoria;
    }

    public void setDaoCategoria(CategoriaDAO<Categoria> daoCategoria) {
        this.daoCategoria = daoCategoria;
    }

}
