package gcom.micromedicao.bean;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.seguranca.acesso.usuario.Usuario;

import java.math.BigDecimal;

/**
 * 
 * Classe de apoio ao 
 * [UC-1127] - Gerar RA e OS para Anormalidade Consumo
 * 
 * @author Bruno Barros
 * @date 16/02/2011
 *
 */
public class GerarRAOSAnormalidadeConsumoHelper {
	
	private Integer idImovel;	
	private Integer idImovelPerfil;
	private Integer idPrincipalCategoria;
	private Integer idConsumoAnormalidade;
	private Integer idSolicitacaoTipoEspecificacaoMes1;
	private Integer idSolicitacaoTipoEspecificacaoMes2;
	private Integer idSolicitacaoTipoEspecificacaoMes3;
	private String  descricaoAnormalidade;
	private UnidadeOrganizacional unidadeOrganizacional;
	private Integer idLocalidadeImovel;
	private Integer idSolicitacaoTipoGrupo;
	private String dataPrevistaRA;
	private Integer numeroDiasPrazoAtendimentoRA;
	private Integer idSolicitacaoTipoEspecificacao;
	private String observacaoRA;
	private Integer idSolicitacaoTipo;
	private Integer idSetorComercial;
	private Integer idQuadra;
	private Usuario admin;	
	private Integer idServicoTipo;
	private BigDecimal coordenadaX;
	private BigDecimal coordenadaY;
	
	public Integer getIdServicoTipo() {
		return idServicoTipo;
	}


	public void setIdServicoTipo(Integer idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}


	public String getObservacaoRA() {
		return observacaoRA;
	}


	public void setObservacaoRA(String observacaoRA) {
		this.observacaoRA = observacaoRA;
	}


	public Integer getIdSolicitacaoTipoEspecificacao() {
		return idSolicitacaoTipoEspecificacao;
	}


	public void setIdSolicitacaoTipoEspecificacao(
			Integer idSolicitacaoTipoEspecificacao) {
		this.idSolicitacaoTipoEspecificacao = idSolicitacaoTipoEspecificacao;
	}


	public Integer getNumeroDiasPrazoAtendimentoRA() {
		return numeroDiasPrazoAtendimentoRA;
	}


	public void setNumeroDiasPrazoAtendimentoRA(Integer numeroDiasPrazoAtendimentoRA) {
		this.numeroDiasPrazoAtendimentoRA = numeroDiasPrazoAtendimentoRA;
	}


	public String getDataPrevistaRA() {
		return dataPrevistaRA;
	}


	public void setDataPrevistaRA(String dataPrevistaRA) {
		this.dataPrevistaRA = dataPrevistaRA;
	}


	public Integer getIdSolicitacaoTipoGrupo() {
		return idSolicitacaoTipoGrupo;
	}


	public void setIdSolicitacaoTipo(Integer idSolicitacaoTipo) {
		this.idSolicitacaoTipo = idSolicitacaoTipo;
	}


	public Integer getIdLocalidadeImovel() {
		return idLocalidadeImovel;
	}


	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}


	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}


	public String getDescricaoAnormalidade() {
		return descricaoAnormalidade;
	}


	public void setDescricaoAnormalidade(String descricaoAnormalidade) {
		this.descricaoAnormalidade = descricaoAnormalidade;
	}


	public Integer getIdConsumoAnormalidade() {
		return idConsumoAnormalidade;
	}


	public Integer getIdPrincipalCategoria() {
		return idPrincipalCategoria;
	}


	public void setIdPrincipalCategoria(Integer idPrincipalCategoria) {
		this.idPrincipalCategoria = idPrincipalCategoria;
	}


	/**
	 * 
	 * Construtor que ira monta o helper para facilitar
	 * o desenvolvimento
	 * 
	 * @param dados - Sequencia para criação:
	 * 
	 * 	[0] - imov_id
	 *  [1] - iper_id
	 *  [2] - csaa_id
	 *  [3] - loca_id
	 *  [4] - stcm_id
	 *  [5] - qdra_id
	 *  [6] - IMOV_NNCOORDENADAY
	 *  [7] - IMOV_NNCOORDENADAX
	 * 
	 */
	public GerarRAOSAnormalidadeConsumoHelper( Object[] dados ){
		this.idImovel = (Integer) dados[0];
		this.idImovelPerfil = (Integer) dados[1];
		this.idConsumoAnormalidade = ( Integer ) dados[2];
		this.idLocalidadeImovel = ( Integer ) dados[3];
		this.idSetorComercial = ( Integer ) dados[4];
		this.idQuadra = ( Integer ) dados[5];
		this.coordenadaY = ( BigDecimal ) dados[6];
		this.coordenadaX = ( BigDecimal ) dados[7];
	}


	public Integer getIdImovel() {
		return idImovel;
	}


	public Integer getIdImovelPerfil() {
		return idImovelPerfil;
	}


	public Integer getIdSolicitacaoTipoEspecificacaoMes1() {
		return idSolicitacaoTipoEspecificacaoMes1;
	}


	public void setIdSolicitacaoTipoEspecificacaoMes1(
			Integer idSolicitacaoTipoEspecificacaoMes1) {
		this.idSolicitacaoTipoEspecificacaoMes1 = idSolicitacaoTipoEspecificacaoMes1;
	}


	public Integer getIdSolicitacaoTipoEspecificacaoMes2() {
		return idSolicitacaoTipoEspecificacaoMes2;
	}


	public void setIdSolicitacaoTipoEspecificacaoMes2(
			Integer idSolicitacaoTipoEspecificacaoMes2) {
		this.idSolicitacaoTipoEspecificacaoMes2 = idSolicitacaoTipoEspecificacaoMes2;
	}


	public Integer getIdSolicitacaoTipoEspecificacaoMes3() {
		return idSolicitacaoTipoEspecificacaoMes3;
	}


	public void setIdSolicitacaoTipoEspecificacaoMes3(
			Integer idSolicitacaoTipoEspecificacaoMes3) {
		this.idSolicitacaoTipoEspecificacaoMes3 = idSolicitacaoTipoEspecificacaoMes3;
	}


	public Integer getIdQuadra() {
		return idQuadra;
	}


	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}


	public Integer getIdSolicitacaoTipo() {
		return idSolicitacaoTipo;
	}


	public Usuario getAdmin() {
		return admin;
	}


	public void setAdmin(Usuario admin) {
		this.admin = admin;
	}


	public BigDecimal getCoordenadaX() {
		return coordenadaX;
	}

	public BigDecimal getCoordenadaY() {
		return coordenadaY;
	}


	public void setIdSolicitacaoTipoGrupo(Integer idSolicitacaoTipoGrupo) {
		this.idSolicitacaoTipoGrupo = idSolicitacaoTipoGrupo;
	}	
}
