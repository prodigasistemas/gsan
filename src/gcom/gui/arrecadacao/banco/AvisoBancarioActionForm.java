package gcom.gui.arrecadacao.banco;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter aviso bancario 
 *
 * @author thiago
 *
 * @date 10/03/2006
 */
public class AvisoBancarioActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String acao = "";
	private String idAvisoBancario = "";
	private String posicao = "";
	private String idContaBancaria = "";
	private String tipoAcesso = "";
	private String acerto = "";
	private String dataAcerto = "";
	private String valorAcerto = "";
	private String idTipoDeducao = "";
	private String valorDeducao = "";
	private String valorDeducoes = "";
	private String valorAviso = "";
	private String idFormaArrecadacao = "";

	private String numeroDocumento = "";
	private String dataRealizacao = "";
	private String valorArrecadacao = "";
	private String valorDevolucao = "";
	private String dataLancamento= "";
	
	/**
	 * Método que limpa os atributos 
	 *
	 * @author Administrador
	 * @date 10/03/2006
	 *
	 * @param arg0 mapeamento
	 * @param arg1 request
	 */
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		
		super.reset(arg0, arg1);

		this.acao = ""; 
		this.idAvisoBancario = "";
		this.posicao = "";
		this.idContaBancaria = "";
		this.tipoAcesso = "";
		this.acerto = "";
		this.dataAcerto = "";
		this.valorAcerto = "";
		this.idTipoDeducao = "";
		this.valorDeducao = "";
		this.valorDeducoes = "";
		this.valorAviso = "";
		
		this.numeroDocumento = "";
		this.dataRealizacao = "";
		this.valorArrecadacao = "";
		this.valorDevolucao = "";
		this.dataLancamento= "";
	}

	
	
	public String getDataLancamento() {
		return dataLancamento;
	}



	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}



	public String getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(String dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getValorArrecadacao() {
		return valorArrecadacao;
	}

	public void setValorArrecadacao(String valorArrecadacao) {
		this.valorArrecadacao = valorArrecadacao;
	}

	public String getValorDevolucao() {
		return valorDevolucao;
	}

	public void setValorDevolucao(String valorDevolucao) {
		this.valorDevolucao = valorDevolucao;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getDataAcerto() {
		return dataAcerto;
	}

	public void setDataAcerto(String dataAcerto) {
		this.dataAcerto = dataAcerto;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public String getIdAvisoBancario() {
		return idAvisoBancario;
	}

	public void setIdAvisoBancario(String idAvisoBancario) {
		this.idAvisoBancario = idAvisoBancario;
	}

	public String getIdContaBancaria() {
		return idContaBancaria;
	}

	public void setIdContaBancaria(String idContaBancaria) {
		this.idContaBancaria = idContaBancaria;
	}

	public String getIdTipoDeducao() {
		return idTipoDeducao;
	}

	public void setIdTipoDeducao(String idTipoDeducao) {
		this.idTipoDeducao = idTipoDeducao;
	}

	public String getTipoAcesso() {
		return tipoAcesso;
	}

	public void setTipoAcesso(String tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}

	public String getValorAcerto() {
		return valorAcerto;
	}

	public void setValorAcerto(String valorAcerto) {
		this.valorAcerto = valorAcerto;
	}

	public String getValorDeducao() {
		return valorDeducao;
	}

	public void setValorDeducao(String valorDeducao) {
		this.valorDeducao = valorDeducao;
	}

	/**
	 * @return Retorna o campo valorAviso.
	 */
	public String getValorAviso() {
		return valorAviso;
	}

	/**
	 * @param valorAviso O valorAviso a ser setado.
	 */
	public void setValorAviso(String valorAviso) {
		this.valorAviso = valorAviso;
	}

	/**
	 * @return Retorna o campo valorDeducoes.
	 */
	public String getValorDeducoes() {
		return valorDeducoes;
	}

	/**
	 * @param valorDeducoes O valorDeducoes a ser setado.
	 */
	public void setValorDeducoes(String valorDeducoes) {
		this.valorDeducoes = valorDeducoes;
	}

	/**
	 * @return Retorna o campo acerto.
	 */
	public String getAcerto() {
		return acerto;
	}

	/**
	 * @param acerto O acerto a ser setado.
	 */
	public void setAcerto(String acerto) {
		this.acerto = acerto;
	}



	public String getIdFormaArrecadacao() {
		return idFormaArrecadacao;
	}



	public void setIdFormaArrecadacao(String idFormaArrecadacao) {
		this.idFormaArrecadacao = idFormaArrecadacao;
	}

}
