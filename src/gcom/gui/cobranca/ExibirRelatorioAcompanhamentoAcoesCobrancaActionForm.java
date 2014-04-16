package gcom.gui.cobranca;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form responsável pelas propiedades do caso de uso de Relatorio de Acompanhamento das Acoes de Cobranca
 * 
 * @author Genival Barbosa
 * @date 15/07/09
 */
public class ExibirRelatorioAcompanhamentoAcoesCobrancaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idCobrancaAcao;
	
	private String dataInicial;
	
	private String dataFinal;
	
	private String chkEstado;
	
	private String chkGerencia;
	
	private String idGerenciaRegional;
	
	private String chkUnidade;
	
	private String idUnidadeNegocio;
	
	private String chkLocalidade;
	
	private String idLocalidade;
	
	private String idEmpresa;
	


	/**
	 * @return Retorna o campo idCobrancaAcao.
	 */
	public String getIdCobrancaAcao() {
		return idCobrancaAcao;
	}

	/**
	 * @param idCobrancaAcao O idCobrancaAcao a ser setado.
	 */
	public void setIdCobrancaAcao(String idCobrancaAcao) {
		this.idCobrancaAcao = idCobrancaAcao;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getChkEstado() {
		return chkEstado;
	}

	public void setChkEstado(String chkEstado) {
		this.chkEstado = chkEstado;
	}

	public String getChkGerencia() {
		return chkGerencia;
	}

	public void setChkGerencia(String chkGerencia) {
		this.chkGerencia = chkGerencia;
	}

	public String getChkLocalidade() {
		return chkLocalidade;
	}

	public void setChkLocalidade(String chkLocalidade) {
		this.chkLocalidade = chkLocalidade;
	}

	public String getChkUnidade() {
		return chkUnidade;
	}

	public void setChkUnidade(String chkUnidade) {
		this.chkUnidade = chkUnidade;
	}
	
	
}
