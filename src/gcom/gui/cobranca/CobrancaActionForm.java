package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

public class CobrancaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idGrupoCobranca;
	
	private String descricaoGrupoCobranca;
	
	private String mesAno;
	
	private String[] idRegistrosRemocao;
	
	private String idCobrancaGrupoCronogramaMes;
	
	private String idCobrancaAcao;
	
	private String indicadorAtualizar;
	
	private String dataInicio;
	
	private String idGrupoCobrancaFiltro;
	private String mesAnoFiltro;

	private Integer quantidadeMaximaDocumentos;
	
	public Integer getQuantidadeMaximaDocumentos() {
		return quantidadeMaximaDocumentos;
	}

	public void setQuantidadeMaximaDocumentos(Integer quantidadeMaximaDocumentos) {
		this.quantidadeMaximaDocumentos = quantidadeMaximaDocumentos;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	public String getIdCobrancaAcao() {
		return idCobrancaAcao;
	}

	public void setIdCobrancaAcao(String idCobrancaAcao) {
		this.idCobrancaAcao = idCobrancaAcao;
	}

	public String getIdCobrancaGrupoCronogramaMes() {
		return idCobrancaGrupoCronogramaMes;
	}

	public void setIdCobrancaGrupoCronogramaMes(String idCobrancaGrupoCronogramaMes) {
		this.idCobrancaGrupoCronogramaMes = idCobrancaGrupoCronogramaMes;
	}

	public String getIdGrupoCobranca() {
		return idGrupoCobranca;
	}

	public void setIdGrupoCobranca(String idGrupoCobranca) {
		this.idGrupoCobranca = idGrupoCobranca;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getDescricaoGrupoCobranca() {
		return descricaoGrupoCobranca;
	}

	public void setDescricaoGrupoCobranca(String descricaoGrupoCobranca) {
		this.descricaoGrupoCobranca = descricaoGrupoCobranca;
	}

	public String[] getIdRegistrosRemocao() {
		return idRegistrosRemocao;
	}

	public void setIdRegistrosRemocao(String[] idRegistrosRemocao) {
		this.idRegistrosRemocao = idRegistrosRemocao;
	}

	public String getIdGrupoCobrancaFiltro() {
		return idGrupoCobrancaFiltro;
	}

	public void setIdGrupoCobrancaFiltro(String idGrupoCobrancaFiltro) {
		this.idGrupoCobrancaFiltro = idGrupoCobrancaFiltro;
	}

	public String getMesAnoFiltro() {
		return mesAnoFiltro;
	}

	public void setMesAnoFiltro(String mesAnoFiltro) {
		this.mesAnoFiltro = mesAnoFiltro;
	}
	
	

}
