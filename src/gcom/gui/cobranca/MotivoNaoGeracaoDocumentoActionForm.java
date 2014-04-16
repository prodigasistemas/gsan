package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * @author Anderson Italo
 * @date 20/11/2009
 *
 */
public class MotivoNaoGeracaoDocumentoActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String tipoComando;
	private String anoMesReferencia;
	private String idCobrancaGrupo;
	private String idCobrancaAcao;
	private String idCobrancaAtividade;
	private String matriculaImovel;
	private String descricaoMotivo;
	private String idCobrancaAcaoAtividadeComando;
	private String indicadorTipoPesquisa;
	private String indicadorTipoRelatorio;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String idSetorComercial;
	private String descricaoSetorComercial;
	private String idQuadra;
	private String descricaoQuadra;
	private String gerenciaRegional;
	private String unidadeNegocio;
	
	
	public String getIdCobrancaAcaoAtividadeComando() {
		return idCobrancaAcaoAtividadeComando;
	}

	public void setIdCobrancaAcaoAtividadeComando(
			String idCobrancaAcaoAtividadeComando) {
		this.idCobrancaAcaoAtividadeComando = idCobrancaAcaoAtividadeComando;
	}

	public String getTipoComando() {
		return tipoComando;
	}

	public void setTipoComando(String tipoComando) {
		this.tipoComando = tipoComando;
	}

	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public String getDescricaoMotivo() {
		return descricaoMotivo;
	}

	public void setDescricaoMotivo(String descricaoMotivo) {
		this.descricaoMotivo = descricaoMotivo;
	}

	public String getIdCobrancaAcao() {
		return idCobrancaAcao;
	}

	public void setIdCobrancaAcao(String idCobrancaAcao) {
		this.idCobrancaAcao = idCobrancaAcao;
	}

	public String getIdCobrancaAtividade() {
		return idCobrancaAtividade;
	}

	public void setIdCobrancaAtividade(String idCobrancaAtividade) {
		this.idCobrancaAtividade = idCobrancaAtividade;
	}

	public String getIdCobrancaGrupo() {
		return idCobrancaGrupo;
	}

	public void setIdCobrancaGrupo(String idCobrancaGrupo) {
		this.idCobrancaGrupo = idCobrancaGrupo;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getIndicadorTipoPesquisa() {
		return indicadorTipoPesquisa;
	}

	public void setIndicadorTipoPesquisa(String indicadorTipoPesquisa) {
		this.indicadorTipoPesquisa = indicadorTipoPesquisa;
	}

	public String getIndicadorTipoRelatorio() {
		return indicadorTipoRelatorio;
	}

	public void setIndicadorTipoRelatorio(String indicadorTipoRelatorio) {
		this.indicadorTipoRelatorio = indicadorTipoRelatorio;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}

	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}

	public String getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(String idQuadra) {
		this.idQuadra = idQuadra;
	}

	public String getDescricaoQuadra() {
		return descricaoQuadra;
	}

	public void setDescricaoQuadra(String descricaoQuadra) {
		this.descricaoQuadra = descricaoQuadra;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

}
