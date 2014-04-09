package gcom.gui.faturamento.autoinfracao;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0857] Gerar Relatório de Arrecadação das Multas de Autos de Infração
 * @author Rafael Corrêa
 * @since 08/09/2008
 */
public class GerarRelatorioAutoInfracaoActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idUnidadeNegocio;
	
	private String idFuncionario;
	
	private String nomeFuncionario;
	
	private String dataPagamentoInicio;
	
	private String dataPagamentoFim;
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}
	
	/**
	 * @return Retorna o campo dataPagamentoFim.
	 */
	public String getDataPagamentoFim() {
		return dataPagamentoFim;
	}

	/**
	 * @param dataPagamentoFim O dataPagamentoFim a ser setado.
	 */
	public void setDataPagamentoFim(String dataPagamentoFim) {
		this.dataPagamentoFim = dataPagamentoFim;
	}

	/**
	 * @return Retorna o campo dataPagamentoInicio.
	 */
	public String getDataPagamentoInicio() {
		return dataPagamentoInicio;
	}

	/**
	 * @param dataPagamentoInicio O dataPagamentoInicio a ser setado.
	 */
	public void setDataPagamentoInicio(String dataPagamentoInicio) {
		this.dataPagamentoInicio = dataPagamentoInicio;
	}

	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}


	/**
	 * @param idUnidadeNegocio O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo idFuncionario.
	 */
	public String getIdFuncionario() {
		return idFuncionario;
	}

	/**
	 * @param idFuncionario O idFuncionario a ser setado.
	 */
	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	/**
	 * @return Retorna o campo nomeFuncionario.
	 */
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	/**
	 * @param nomeFuncionario O nomeFuncionario a ser setado.
	 */
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}


}
