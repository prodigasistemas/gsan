/**
 * 
 */
package gcom.tagslib;

import gcom.util.Util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.struts.Globals;
import org.apache.struts.util.RequestUtils;

/**
 *
 *
 * @author Marlon Patrick
 * @since 13/10/2009
 */
public class MensagensInternacionalizadasTag extends SimpleTagSupport{
	
	private static final long serialVersionUID = 1L;

    private String bundle = Globals.MESSAGES_KEY;

    private String key = null;
    
    private String arg0 = null;
    
    private String arg1 = null;

    private String arg2 = null;

    private String arg3 = null;

    private String arg4 = null;

//    private String localeKey = Globals.LOCALE_KEY;

    public String getBundle() {
        return (this.bundle);
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    public String getKey() {
        return (this.key);
    }

    public void setKey(String key) {
        this.key = key;
    }
    
    public String getArg0() {
		return arg0;
	}

	public void setArg0(String parametro0) {
		this.arg0 = parametro0;
	}

	public String getArg1() {
		return arg1;
	}

	public void setArg1(String parametro1) {
		this.arg1 = parametro1;
	}

	public String getArg2() {
		return arg2;
	}

	public void setArg2(String parametro2) {
		this.arg2 = parametro2;
	}

	public String getArg3() {
		return arg3;
	}

	public void setArg3(String parametro3) {
		this.arg3 = parametro3;
	}

	public String getArg4() {
		return arg4;
	}

	public void setArg4(String parametro4) {
		this.arg4 = parametro4;
	}

	public void doTag() throws JspException,IOException {
        PageContext pageContext = (PageContext)getJspContext();
        
        if(Util.verificarNaoVazio(this.arg0)){
	    	String msg = RequestUtils.message(pageContext, bundle, Globals.LOCALE_KEY, this.arg0);
	    	if(isMsgExistente(msg)){
	        	this.arg0 = msg;
	    	}
        }

        if(Util.verificarNaoVazio(this.arg1)){
	    	String msg = RequestUtils.message(pageContext, bundle, Globals.LOCALE_KEY, this.arg1);
	    	if(isMsgExistente(msg)){
	        	this.arg1 = msg;
	    	}
        }

        if(Util.verificarNaoVazio(this.arg2)){
	    	String msg = RequestUtils.message(pageContext, bundle, Globals.LOCALE_KEY, this.arg2);
	    	if(isMsgExistente(msg)){
	        	this.arg2 = msg;
	    	}
        }

        if(Util.verificarNaoVazio(this.arg3)){
	    	String msg = RequestUtils.message(pageContext, bundle, Globals.LOCALE_KEY, this.arg3);
	    	if(isMsgExistente(msg)){
	        	this.arg3 = msg;
	    	}
        }

        if(Util.verificarNaoVazio(this.arg4)){
	    	String msg = RequestUtils.message(pageContext, bundle, Globals.LOCALE_KEY, this.arg4);
	    	if(isMsgExistente(msg)){
	        	this.arg4 = msg;
	    	}
        }

		Object[] parametros = new Object[] { this.arg0, this.arg1, this.arg2, this.arg3, this.arg4};

        String message = RequestUtils.message(pageContext, bundle, Globals.LOCALE_KEY, key, parametros);
		        
        if ( !Util.verificarNaoVazio(message)) {
        	throw new JspException("Chave " + this.key + " não encontrada.");
        }

        getJspContext().getOut().print(message);

    }

	/**
	 * Se a mensagem passada como parâmetro existir no properties retorna true.
	 *
	 *@since 26/10/2009
	 *@author Marlon Patrick
	 */
	private boolean isMsgExistente(String msg) {
		return Util.verificarNaoVazio(msg) && !(msg.startsWith("???") && msg.endsWith("???"));
	}	
}
