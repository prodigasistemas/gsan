package gcom.gui.cobranca.spcserasa;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

public class GerarMovimentoExclusaoNegativacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String opcao;
	private String[] negativadores;
	private Collection colecaoNegativadores;

	private String codigoMovimento;
	private String descricaoMovimento;
	private Collection colecaoMovimento;

	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);
		opcao = "";
		negativadores = new String[0];
		colecaoNegativadores = new ArrayList();

		codigoMovimento = "";
		descricaoMovimento = "";
		colecaoMovimento = new ArrayList();
	}

	public Collection getCollMovimento() {
		return colecaoMovimento;
	}

	public void setCollMovimento(Collection collMovimento) {
		this.colecaoMovimento = collMovimento;
	}

	public String getCodigoMovimento() {
		return codigoMovimento;
	}

	public void setCodigoMovimento(String codigoMovimento) {
		this.codigoMovimento = codigoMovimento;
	}

	public Collection getCollNegativadores() {
		return colecaoNegativadores;
	}

	public void setCollNegativadores(Collection collNegativadores) {
		this.colecaoNegativadores = collNegativadores;
	}

	public String getDescricaoMovimento() {
		return descricaoMovimento;
	}

	public void setDescricaoMovimento(String descricaoMovimento) {
		this.descricaoMovimento = descricaoMovimento;
	}

	public String[] getNegativadores() {
		return negativadores;
	}

	public void setNegativadores(String[] negativadores) {
		this.negativadores = negativadores;
	}

	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}
}
