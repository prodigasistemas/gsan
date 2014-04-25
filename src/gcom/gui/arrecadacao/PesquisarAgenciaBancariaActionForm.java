package gcom.gui.arrecadacao;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0804] Pesquisar Agencia Bancaria
 * 
 * @date 26/05/2008
 * @author Vinicius Medeiros
 */

public class PesquisarAgenciaBancariaActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String codigo;

	private String nomeAgencia;

	private String banco;

	private String bancoID;

	private String nomeBanco;

	private String indicadorUso;

	private String tipoPesquisa;

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getBancoID() {
		return bancoID;
	}

	public void setBancoID(String bancoID) {
		this.bancoID = bancoID;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getNomeAgencia() {
		return nomeAgencia;
	}

	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}

}
