/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Categoria;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author estef
 */
@Named(value = "converterCategoria")
@RequestScoped
public class ConverterCategoria implements Serializable, Converter{

    @PersistenceContext(unitName = "PW-Biblioteca-WebPU")
    protected EntityManager em;
    
    // converte da tela para o objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        // testar se deu algum problema na tela
        if(string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Categoria.class, Integer.parseInt(string));
    }

    // converte do objeto para a tela (string)
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
         if(t == null){
             return null;
         }
         Categoria obj = (Categoria) t;
         return obj.getId().toString();
    }
    
}
