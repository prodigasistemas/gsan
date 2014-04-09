package gcom.gui.cadastro.imovel;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Roberta Costa
 * @created 22 de Dezembro de 2005
 */
public class ImovelDoacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idImovelDoacao;

	private String idImovel;

	private String idEntidadeBeneficente;

	private String valorDoacao;

	private String dataAdesao;

	private String dataCancelamento;

	private String idUsuarioAdesao;

	private String idUsuarioCancelamento;

	private String dataHoraUltimaAlteracao;
	
	private String inscricaoImovel;
	
	private String quantidadeParcela;
	
	private String quantidadeParcelaMaxima;

	/**
	 * @return Returns the idImovelDoacao.
	 */
	public String getIdImovelDoacao() {
		return idImovelDoacao;
	}

	/**
	 * @param consumoAlto
	 *            the idImovelDoacao to set.
	 */
	public void setIdImovelDoacao(String idImovelDoacao) {
		this.idImovelDoacao = idImovelDoacao;
	}

	/**
	 * @return Returns the idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}

	/**
	 * @param consumoEstouro
	 *            the idImovel to set.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Returns the idEntidadeBeneficente.
	 */
	public String getIdEntidadeBeneficente() {
		return idEntidadeBeneficente;
	}

	/**
	 * @param consumoMinimo
	 *            the idEntidadeBeneficente to set.
	 */
	public void setIdEntidadeBeneficente(String idEntidadeBeneficente) {
		this.idEntidadeBeneficente = idEntidadeBeneficente;
	}

	/**
	 * @return Returns the valorDoacao.
	 */
	public String getValorDoacao() {
		return valorDoacao;
	}

	/**
	 * @param descricao
	 *            the valorDoacao to set.
	 */
	public void setValorDoacao(String valorDoacao) {
		this.valorDoacao = valorDoacao;
	}

	/**
	 * @return Returns the dataAdesao.
	 */
	public String getDataAdesao() {
		return dataAdesao;
	}

	/**
	 * @param descricaoAbreviada
	 *            the dataAdesao to set.
	 */
	public void setDataAdesao(String dataAdesao) {
		this.dataAdesao = dataAdesao;
	}

	/**
	 * @return Returns the dataCancelamento.
	 */
	public String getDataCancelamento() {
		return dataCancelamento;
	}

	/**
	 * @param idCategoria
	 *            the dataCancelamento to set.
	 */
	public void setDataCancelamento(String dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	/**
	 * @return Returns the idUsuarioAdesao.
	 */
	public String getIdUsuarioAdesao() {
		return idUsuarioAdesao;
	}

	/**
	 * @param indicadorUso
	 *            the idUsuarioAdesao to set.
	 */
	public void setIdUsuarioAdesao(String idUsuarioAdesao) {
		this.idUsuarioAdesao = idUsuarioAdesao;
	}

	/**
	 * @return Returns the idUsuarioCancelamento.
	 */
	public String getIdUsuarioCancelamento() {
		return idUsuarioCancelamento;
	}

	/**
	 * @param mediaBaixoConsumo
	 *            the idUsuarioCancelamento to set.
	 */
	public void setIdUsuarioCancelamento(String idUsuarioCancelamento) {
		this.idUsuarioCancelamento = idUsuarioCancelamento;
	}

	/**
	 * @return Returns the dataHoraUltimaAlteracao.
	 */
	public String getDataHoraUltimaAlteracao() {
		return dataHoraUltimaAlteracao;
	}

	/**
	 * @param porcentagemMediaBaixoConsumo
	 *            the dataHoraUltimaAlteracao to set.
	 */
	public void setDataHoraUltimaAlteracao(String dataHoraUltimaAlteracao) {
		this.dataHoraUltimaAlteracao = dataHoraUltimaAlteracao;
	}
	
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Returns the quantidadeParcela.
	 */
	public String getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(String quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}

	
	
	/**
	 * @return Returns the quantidadeParcelaMaxima.
	 */
	public String getQuantidadeParcelaMaxima() {
		return quantidadeParcelaMaxima;
	}

	/**
	 * @param quantidadeParcelaMaxima The quantidadeParcelaMaxima to set.
	 */
	public void setQuantidadeParcelaMaxima(String quantidadeParcelaMaxima) {
		this.quantidadeParcelaMaxima = quantidadeParcelaMaxima;
	}

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 */
	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		idImovelDoacao = null;
		idImovel = null;
		idEntidadeBeneficente = null;
		valorDoacao = null;
		dataAdesao = null;
		dataCancelamento = null;
		idUsuarioAdesao = null;
		idUsuarioCancelamento = null;
		dataHoraUltimaAlteracao = null;
		inscricaoImovel = null;
		quantidadeParcela = null;
		quantidadeParcelaMaxima = null;
	}

}
