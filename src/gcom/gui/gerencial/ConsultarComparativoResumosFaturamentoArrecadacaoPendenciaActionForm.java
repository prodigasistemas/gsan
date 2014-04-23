package gcom.gui.gerencial;

import org.apache.struts.action.ActionForm;

/**
 * Classe responsável por ajudar o caso de uso [UC0343] Resumo de Anormalidades 
 *
 * @author Pedro Alexandre
 * @date 07/06/2006
 */
public class ConsultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private Integer idImovel;
	private Integer idGerenciaRegional;
	private Integer idLocalidade;
	private Integer idSetorComercial;
	private Integer idRota;
	private Integer idQuadra;
	private Integer codigoSetorComercial;
	private Integer numeroQuadra;
	private Integer idPerfilImovel;
	private Integer idSituacaoLigacaoAgua;
	private Integer idSituacaoLigacaoEsgoto;
	private Integer idCategoria;
	private Integer idEsfera;
	private Integer idLeituraAnormalidadeFaturada;
	private Integer idLigacaoAgua;
	private Integer idMedicaoTipo;
	private Integer idFaturamentoGrupo;

	
	private Integer quantidadeContasFaturamento;
	private String valorFaturamento;
	private Integer quantidadeContasArrecadacao;
	private String valorArrecadacao;
	private String quantidadeContasPercentual;
	private String valorPercentual;
	private Integer quantidadeContasPendencia;
	private String valorPendencia;
	private String quantidadeContasNumeroVezesFaturamento;
	private String valorNumeroVezesFaturamento;
	
	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	/**
	 * @return Retorna o campo idCategoria.
	 */
	public Integer getIdCategoria() {
		return idCategoria;
	}
	/**
	 * @param idCategoria O idCategoria a ser setado.
	 */
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	/**
	 * @return Retorna o campo idEsfera.
	 */
	public Integer getIdEsfera() {
		return idEsfera;
	}
	/**
	 * @param idEsfera O idEsfera a ser setado.
	 */
	public void setIdEsfera(Integer idEsfera) {
		this.idEsfera = idEsfera;
	}
	/**
	 * @return Retorna o campo idFaturamentoGrupo.
	 */
	public Integer getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}
	/**
	 * @param idFaturamentoGrupo O idFaturamentoGrupo a ser setado.
	 */
	public void setIdFaturamentoGrupo(Integer idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}
	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	/**
	 * @return Retorna o campo idImovel.
	 */
	public Integer getIdImovel() {
		return idImovel;
	}
	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	/**
	 * @return Retorna o campo idLeituraAnormalidadeFaturada.
	 */
	public Integer getIdLeituraAnormalidadeFaturada() {
		return idLeituraAnormalidadeFaturada;
	}
	/**
	 * @param idLeituraAnormalidadeFaturada O idLeituraAnormalidadeFaturada a ser setado.
	 */
	public void setIdLeituraAnormalidadeFaturada(
			Integer idLeituraAnormalidadeFaturada) {
		this.idLeituraAnormalidadeFaturada = idLeituraAnormalidadeFaturada;
	}
	/**
	 * @return Retorna o campo idLigacaoAgua.
	 */
	public Integer getIdLigacaoAgua() {
		return idLigacaoAgua;
	}
	/**
	 * @param idLigacaoAgua O idLigacaoAgua a ser setado.
	 */
	public void setIdLigacaoAgua(Integer idLigacaoAgua) {
		this.idLigacaoAgua = idLigacaoAgua;
	}
	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	/**
	 * @return Retorna o campo idMedicaoTipo.
	 */
	public Integer getIdMedicaoTipo() {
		return idMedicaoTipo;
	}
	/**
	 * @param idMedicaoTipo O idMedicaoTipo a ser setado.
	 */
	public void setIdMedicaoTipo(Integer idMedicaoTipo) {
		this.idMedicaoTipo = idMedicaoTipo;
	}
	/**
	 * @return Retorna o campo idPerfilImovel.
	 */
	public Integer getIdPerfilImovel() {
		return idPerfilImovel;
	}
	/**
	 * @param idPerfilImovel O idPerfilImovel a ser setado.
	 */
	public void setIdPerfilImovel(Integer idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}
	/**
	 * @return Retorna o campo idQuadra.
	 */
	public Integer getIdQuadra() {
		return idQuadra;
	}
	/**
	 * @param idQuadra O idQuadra a ser setado.
	 */
	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}
	/**
	 * @return Retorna o campo idRota.
	 */
	public Integer getIdRota() {
		return idRota;
	}
	/**
	 * @param idRota O idRota a ser setado.
	 */
	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}
	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}
	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	/**
	 * @return Retorna o campo idSituacaoLigacaoAgua.
	 */
	public Integer getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}
	/**
	 * @param idSituacaoLigacaoAgua O idSituacaoLigacaoAgua a ser setado.
	 */
	public void setIdSituacaoLigacaoAgua(Integer idSituacaoLigacaoAgua) {
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
	}
	/**
	 * @return Retorna o campo idSituacaoLigacaoEsgoto.
	 */
	public Integer getIdSituacaoLigacaoEsgoto() {
		return idSituacaoLigacaoEsgoto;
	}
	/**
	 * @param idSituacaoLigacaoEsgoto O idSituacaoLigacaoEsgoto a ser setado.
	 */
	public void setIdSituacaoLigacaoEsgoto(Integer idSituacaoLigacaoEsgoto) {
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
	}
	/**
	 * @return Retorna o campo numeroQuadra.
	 */
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}
	/**
	 * @param numeroQuadra O numeroQuadra a ser setado.
	 */
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}
	/**
	 * @return Retorna o campo quantidadeContasArrecadacao.
	 */
	public Integer getQuantidadeContasArrecadacao() {
		return quantidadeContasArrecadacao;
	}
	/**
	 * @param quantidadeContasArrecadacao O quantidadeContasArrecadacao a ser setado.
	 */
	public void setQuantidadeContasArrecadacao(Integer quantidadeContasArrecadacao) {
		this.quantidadeContasArrecadacao = quantidadeContasArrecadacao;
	}
	/**
	 * @return Retorna o campo quantidadeContasFaturamento.
	 */
	public Integer getQuantidadeContasFaturamento() {
		return quantidadeContasFaturamento;
	}
	/**
	 * @param quantidadeContasFaturamento O quantidadeContasFaturamento a ser setado.
	 */
	public void setQuantidadeContasFaturamento(Integer quantidadeContasFaturamento) {
		this.quantidadeContasFaturamento = quantidadeContasFaturamento;
	}
	/**
	 * @return Retorna o campo quantidadeContasNumeroVezesFaturamento.
	 */
	public String getQuantidadeContasNumeroVezesFaturamento() {
		return quantidadeContasNumeroVezesFaturamento;
	}
	/**
	 * @param quantidadeContasNumeroVezesFaturamento O quantidadeContasNumeroVezesFaturamento a ser setado.
	 */
	public void setQuantidadeContasNumeroVezesFaturamento(
			String quantidadeContasNumeroVezesFaturamento) {
		this.quantidadeContasNumeroVezesFaturamento = quantidadeContasNumeroVezesFaturamento;
	}
	/**
	 * @return Retorna o campo quantidadeContasPendencia.
	 */
	public Integer getQuantidadeContasPendencia() {
		return quantidadeContasPendencia;
	}
	/**
	 * @param quantidadeContasPendencia O quantidadeContasPendencia a ser setado.
	 */
	public void setQuantidadeContasPendencia(Integer quantidadeContasPendencia) {
		this.quantidadeContasPendencia = quantidadeContasPendencia;
	}
	/**
	 * @return Retorna o campo quantidadeContasPercentual.
	 */
	public String getQuantidadeContasPercentual() {
		return quantidadeContasPercentual;
	}
	/**
	 * @param quantidadeContasPercentual O quantidadeContasPercentual a ser setado.
	 */
	public void setQuantidadeContasPercentual(String quantidadeContasPercentual) {
		this.quantidadeContasPercentual = quantidadeContasPercentual;
	}
	/**
	 * @return Retorna o campo valorArrecadacao.
	 */
	public String getValorArrecadacao() {
		return valorArrecadacao;
	}
	/**
	 * @param valorArrecadacao O valorArrecadacao a ser setado.
	 */
	public void setValorArrecadacao(String valorArrecadacao) {
		this.valorArrecadacao = valorArrecadacao;
	}
	/**
	 * @return Retorna o campo valorFaturamento.
	 */
	public String getValorFaturamento() {
		return valorFaturamento;
	}
	/**
	 * @param valorFaturamento O valorFaturamento a ser setado.
	 */
	public void setValorFaturamento(String valorFaturamento) {
		this.valorFaturamento = valorFaturamento;
	}
	/**
	 * @return Retorna o campo valorNumeroVezesFaturamento.
	 */
	public String getValorNumeroVezesFaturamento() {
		return valorNumeroVezesFaturamento;
	}
	/**
	 * @param valorNumeroVezesFaturamento O valorNumeroVezesFaturamento a ser setado.
	 */
	public void setValorNumeroVezesFaturamento(String valorNumeroVezesFaturamento) {
		this.valorNumeroVezesFaturamento = valorNumeroVezesFaturamento;
	}
	/**
	 * @return Retorna o campo valorPendencia.
	 */
	public String getValorPendencia() {
		return valorPendencia;
	}
	/**
	 * @param valorPendencia O valorPendencia a ser setado.
	 */
	public void setValorPendencia(String valorPendencia) {
		this.valorPendencia = valorPendencia;
	}
	/**
	 * @return Retorna o campo valorPercentual.
	 */
	public String getValorPercentual() {
		return valorPercentual;
	}
	/**
	 * @param valorPercentual O valorPercentual a ser setado.
	 */
	public void setValorPercentual(String valorPercentual) {
		this.valorPercentual = valorPercentual;
	}
	


}
