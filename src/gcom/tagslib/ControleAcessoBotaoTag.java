package gcom.tagslib;

import gcom.fachada.Fachada;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.struts.Globals;
import org.apache.struts.util.RequestUtils;

/**
 * Classe responsável por construir um botão habilitado caso o usuário logado tenha 
 * acesso a operação requerida ou um botão desabilitado caso contrário. 
 *
 * @author Pedro Alexandre
 * @date 20/07/2006
 */
public class ControleAcessoBotaoTag extends SimpleTagSupport {
	
	//Nome do botão que vai ser criado, essa propriedade é requerida
	private String name;
	
	//Descrição que vai aparecer no botão que vai ser criado, essa propriedade é requerida
	private String value;

	//Url da operação para ser acessada, essa propriedade é requerida
	private String url;
	
	//Metódo javascript que vai ser chamado no click do botão, essa propriedade é requerida
	private String onclick;
	
	//Essas propriedades não são requeridas
	private String align;
	private String tabindex;
    private String style;
	
	
	/**
	 * Metódo chamado para construir o componente da tag lib
	 *
	 * @author Administrador
	 * @date 20/07/2006
	 *
	 * @throws JspException
	 * @throws IOException
	 */
	public void doTag() throws JspException, IOException{
		
		//Cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Cria a variável que vai conter a string que cria o botão na jsp
		String botaoAcesso = null;
		
		//Cria a variável contendo a parte inicial para todo o botão
		//String inicioBotao = "<input type=\"" + "button\" " +  " class=\"" + "bottonRightCol\" " + " style=\""+"width: 70px;\" ";
		String inicioBotao = "<input type=\"" + "button\" " +  " class=\"" + "bottonRightCol\" " ;
		
		//Cria a variável contendo a parte final para todo o botão
		String fimBotao = "/>";
		
		//Caso tenha informado a propriedade "tabindex" no componente 
		//adicona a propriedade ao botão na parte final
		if(tabindex != null && !tabindex.trim().equals("")){
			fimBotao = "tabindex=\"" + tabindex + "\" " + fimBotao;
		}

		//Caso tenha informado a propriedade "align" no componente 
		//adicona a propriedade ao botão na parte final
		if(align != null && !align.trim().equals("")){
			fimBotao = "align=\"" + align + "\" " + fimBotao;
		}

        //Caso tenha informado a propriedade "style" no componente 
        //adicona a propriedade ao botão na parte final
        if(style != null && !style.trim().equals("")){
            fimBotao = "style=\"" + style + "\" " + fimBotao;
        }
        
		//Variável contendo o valor para desabilitar o botão caso 
		//o usuário não tenha acesso a operação
		String desabilitado = "disabled";
		
		//Recupera o jsp context
		JspContext jspContext = getJspContext();
		
		//Recupera o usuário logado da sessão
		Usuario usuarioLogado =(Usuario) jspContext.getAttribute("usuarioLogado",PageContext.SESSION_SCOPE);
		
		//Recupera a coleção de grupos ao qual o usário pertence
		Collection<Grupo> colecaoGruposUsuario =(Collection<Grupo>) jspContext.getAttribute("colecaoGruposUsuario",PageContext.SESSION_SCOPE);
		
		//Chama o metódo da fachda para verificar se o usuário tem acesso a operação
		boolean permitirAcesso = fachada.verificarAcessoPermitidoOperacao(usuarioLogado,url,colecaoGruposUsuario);

	    if(Util.verificarNaoVazio(this.value)){
	    	
	    	String msg = RequestUtils.message((PageContext)jspContext, Globals.MESSAGES_KEY, Globals.LOCALE_KEY, this.value);
	    	if(Util.verificarNaoVazio(msg) && !(msg.startsWith("???") && msg.endsWith("???"))){
	    		this.value = msg;
	    	}
	    }

		/*
		 * Caso o usuário tenha acesso a operação indicada pela url
		 * constroi o botão habilitado
		 * Caso o usuário não tenha acesso a operação
		 * constroi o botão desabilitado
		 */
		if(permitirAcesso){
			botaoAcesso =inicioBotao + "name=\""+name +"\""+ " value=\""+ value +"\""+ " onclick=\"" + onclick+"\" " + fimBotao;
		}else{
			botaoAcesso =inicioBotao + "name=\""+name +"\""+ " value=\""+ value +"\""+ " " + desabilitado+" " + fimBotao;
		}

		//Escreve o botão na jsp
		jspContext.getOut().print(botaoAcesso);
	}

	/**
	 * @param align O align a ser setado.
	 */
	public void setAlign(String align) {
		this.align = align;
	}

	/**
	 * @param name O name a ser setado.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param onclick O onclick a ser setado.
	 */
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	/**
	 * @param tabindex O tabindex a ser setado.
	 */
	public void setTabindex(String tabindex) {
		this.tabindex = tabindex;
	}

	/**
	 * @param url O url a ser setado.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param value O value a ser setado.
	 */
	public void setValue(String value) {
		this.value = value;
	}

    public String getAlign() {
        return align;
    }
    
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
