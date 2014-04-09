package gcom.gui.util.log;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirLogActionForm extends ValidatorActionForm {
    
    private static final long serialVersionUID = 1L;
    
    private String arquivo;
    private String textoErro;
    
    private String inicio;
    private String anterior;
    private String proximo;
    private String ultima;
    
    private String paginaAtual;

    public String getPaginaAtual() {
		return paginaAtual;
	}


	public void setPaginaAtual(String paginaAtual) {
		this.paginaAtual = paginaAtual;
	}


	public String getTextoErro() {
		return textoErro;
	}


	public void setTextoErro(String textoErro) {
		this.textoErro = textoErro;
	}


	public String getArquivo() {
		return arquivo;
	}


	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
	

	/**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        
    	
        this.textoErro = null;
        this.anterior = null;
        this.inicio = null;
        this.proximo = null;

    }


	public String getAnterior() {
		return anterior;
	}


	public void setAnterior(String anterior) {
		this.anterior = anterior;
	}


	public String getInicio() {
		return inicio;
	}


	public void setInicio(String inicio) {
		this.inicio = inicio;
	}


	public String getProximo() {
		return proximo;
	}


	public void setProximo(String proximo) {
		this.proximo = proximo;
	}


	public String getUltima() {
		return ultima;
	}


	public void setUltima(String ultima) {
		this.ultima = ultima;
	}
}
