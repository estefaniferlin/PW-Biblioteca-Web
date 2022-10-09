/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author estef
 */
public class Util {
    
    // método para tratar erros
    public static String getMensagemErro(Exception e){
        while(e.getCause() != null){
            e = (Exception) e.getCause();
        }
        String retorno = e.getMessage();
        if(retorno.contains("viola restriçã de chave estrangeira")){
            retorno = "Registro não pode ser removido por possuir referências " +
                     "em outras partes do sistema";
        }
        return retorno;
    }
    
    // metodos para gerar mensagens do JSF
    // para informação
    public static void mensagemInformacao(String textoMensagem){
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, textoMensagem, "");
        contexto.addMessage(null, msg);
    }
    
    // para mensagem de erro
    public static void mensagemErro(String textoMensagem){
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, textoMensagem, "");
        contexto.addMessage(null, msg);
    }
}
