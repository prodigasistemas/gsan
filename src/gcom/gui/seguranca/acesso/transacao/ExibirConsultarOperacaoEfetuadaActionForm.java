package gcom.gui.seguranca.acesso.transacao;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarOperacaoEfetuadaActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String nomeOperacao = "";
	private String nomeUsuario = "";
	private String acaoUsuario = "";
//	private String  = "";
	private String nomeCampoTabela = "";

	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		// TODO Auto-generated method stub
		super.reset(arg0, arg1);
		
		nomeOperacao = "";
		nomeUsuario = "";
		acaoUsuario = "";
		nomeCampoTabela = "";
	}

	public String getAcaoUsuario() {
		return acaoUsuario;
	}

	public void setAcaoUsuario(String acaoUsuario) {
		this.acaoUsuario = acaoUsuario;
	}

	public String getNomeCampoTabela() {
		return nomeCampoTabela;
	}

	public void setNomeCampoTabela(String nomeCampoTabela) {
		this.nomeCampoTabela = nomeCampoTabela;
	}

	public String getNomeOperacao() {
		return nomeOperacao;
	}

	public void setNomeOperacao(String nomeOperacao) {
		this.nomeOperacao = nomeOperacao;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	
	

}
