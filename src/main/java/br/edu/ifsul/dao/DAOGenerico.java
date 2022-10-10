/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author estef
 */
public class DAOGenerico<TIPO> implements Serializable{
    
    private List<TIPO> listaObjetos;
    private List<TIPO> listaTodos;
    @PersistenceContext(unitName = "PW-Biblioteca-WebPU")
    protected EntityManager em;
    protected Class classePersistente;
    protected String filtro = "";
    protected List<Ordem> listaOrdem = new ArrayList<>();
    protected Ordem ordemAtual;
    protected ConverterOrdem converterOrdem;
    protected Integer maximoObjetos = 5; // valor padrao para quantidade de dados por página
    protected Integer posicaoAtual = 0;
    protected Integer totalObjetos = 0;
    
    public DAOGenerico(){
        
    }

    public List<TIPO> getListaObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        String where = "";
        filtro = filtro.replaceAll("[';-]", "");
        if(filtro.length() > 0){ // se filtro foi informado
            switch(ordemAtual.getOperador()){
                case "=" :
                    if(ordemAtual.getAtributo().equals("id")){
                        try{
                            Integer.parseInt(filtro);
                        }catch(Exception e){
                            filtro = "0";
                        }
                    }
                    where += " where " + ordemAtual.getAtributo() + " = '" + filtro + "' ";
                    break;
                case "like" :
                    where += " where upper(" + ordemAtual.getAtributo() + ") like '" + filtro.toUpperCase() + "%' ";
                    break;
            }
        }
        jpql += where;
        jpql += " order by " + ordemAtual.getAtributo();
        totalObjetos = em.createQuery(jpql).getResultList().size();
        return em.createQuery(jpql).setFirstResult(posicaoAtual).setMaxResults(maximoObjetos).getResultList();
    }
    
    // ir para a primeira página
    public void primeiro(){
        posicaoAtual = 0;
    }
    
    // ir para a página anterior
    public void anterior(){
        posicaoAtual -= maximoObjetos;
        if(posicaoAtual < 0){
            posicaoAtual = 0; // para nao voltar a uma página que não existe pois estamos na primeira
        }
    }
    
    // ir para a próxima página
    public void proximo(){   
        if(posicaoAtual + maximoObjetos < totalObjetos){
            posicaoAtual += maximoObjetos;
        }
    }
    
    public void ultimo(){
        int resto = totalObjetos % maximoObjetos;
        if(resto > 0){
            posicaoAtual = totalObjetos - resto;
        } else{
            posicaoAtual = totalObjetos - maximoObjetos;
        }
    }
    
    // para exibir mensagem de navegação (posição do usuário dentro das páginas)
    public String getMensagemNavegacao(){
        int ate = posicaoAtual + maximoObjetos;
        if(ate > totalObjetos){
            ate = totalObjetos;
        }
        if(totalObjetos > 0){ // se tem algo na consulta
            return "Listando de " + (posicaoAtual + 1) + " até " + ate + " de " + totalObjetos + " registros";
        } else{
            return "Nenhum registro encontrado";
        }
    }

    public List<TIPO> getListaTodos() {
        String jpql = "from " + classePersistente.getSimpleName() + " order by " + ordemAtual.getAtributo();
        return em.createQuery(jpql).getResultList();
    }
    
    // métodos para persistência dos dados
    public void persist(TIPO obj) throws Exception{
        System.out.print(obj);
        em.persist(obj);
    }
    
    public void merge(TIPO obj) throws Exception{
        em.merge(obj);
    }
    
    public void remover(TIPO obj) throws Exception{
        obj = em.merge(obj); // une o obj que veio pelo parametro para dps remover
        em.remove(obj);
    }
    
    public TIPO getObjectByID(Object id) throws Exception { // retorna o tipo a partir do id
        return (TIPO) em.find(classePersistente, id);
    }
    
    
    public void setListaObjetos(List<TIPO> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public void setListaTodos(List<TIPO> listaTodos) {
        this.listaTodos = listaTodos;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Class getClassePersistente() {
        return classePersistente;
    }

    public void setClassePersistente(Class classePersistente) {
        this.classePersistente = classePersistente;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<Ordem> getListaOrdem() {
        return listaOrdem;
    }

    public void setListaOrdem(List<Ordem> listaOrdem) {
        this.listaOrdem = listaOrdem;
    }

    public Ordem getOrdemAtual() {
        return ordemAtual;
    }

    public void setOrdemAtual(Ordem ordemAtual) {
        this.ordemAtual = ordemAtual;
    }

    public ConverterOrdem getConverterOrdem() {
        return converterOrdem;
    }

    public void setConverterOrdem(ConverterOrdem converterOrdem) {
        this.converterOrdem = converterOrdem;
    }

    public Integer getMaximoObjetos() {
        return maximoObjetos;
    }

    public void setMaximoObjetos(Integer maximoObjetos) {
        this.maximoObjetos = maximoObjetos;
    }

    public Integer getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(Integer posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public Integer getTotalObjetos() {
        return totalObjetos;
    }

    public void setTotalObjetos(Integer totalObjetos) {
        this.totalObjetos = totalObjetos;
    }
    
}
