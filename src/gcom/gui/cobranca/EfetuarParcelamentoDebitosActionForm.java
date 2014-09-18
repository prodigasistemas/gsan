package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Roberta Costa
 * @created 24 de Janeiro de 2006
 */
public class EfetuarParcelamentoDebitosActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String matriculaImovel;

	private String inscricaoImovel;
	
	private String nomeCliente;
	
	private String situacaoAgua;

	private String situacaoAguaId;
	
	private String situacaoEsgoto;

	private String situacaoEsgotoId;

	private String perfilImovel;
	
	private String descricaoPerfilImovel;

	private String cpfCnpj;

	private String endereco;

	private String dataParcelamento;
	
	private String inicioIntervaloParcelamento;

	private String fimIntervaloParcelamento;
	
	private String resolucaoDiretoria;
	
	private String numeroParcelamento;
	
	private String numeroReparcelamento;
	
	private String numeroReparcelamentoConsecutivos;
	
	private String valorTotalContaValores;
	
	private String valorGuiasPagamento;
	
	private String valorTotalDescontos;
	
	private String valorDebitoTotalAtualizado;
	
	private String indicadorRestabelecimento;
	
	private String valorMinimoPrestacao;
	
	private String indicadorConfirmacaoParcelamento;
	
	private String idClienteParcelamento;
	
	private String nomeClienteParcelamento;

	/**
	 * @return Retorna o campo indicadorRestabelecimento.
	 */
	public String getIndicadorRestabelecimento() {
		return indicadorRestabelecimento;
	}

	/**
	 * @param indicadorRestabelecimento O indicadorRestabelecimento a ser setado.
	 */
	public void setIndicadorRestabelecimento(String indicadorRestabelecimento) {
		this.indicadorRestabelecimento = indicadorRestabelecimento;
	}

	/**
	 * @return Returns the inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	/**
	 * @param inscricaoImovel The inscricaoImovel to set.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Returns the nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente The nomeCliente to set.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Returns the situacaoAgua.
	 */
	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	/**
	 * @param situacaoAgua The situacaoAgua to set.
	 */
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	/**
	 * @return Returns the situacaoEsgoto.
	 */
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	/**
	 * @param situacaoEsgoto The situacaoEsgoto to set.
	 */
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	/**
	 * @return Returns the matriculaImovel.
	 */
	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	/**
	 * @param matriculaImovel The matriculaImovel to set.
	 */
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	/**
	 * @return Returns the cpfCnpj.
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * @param cpfCnpj The cpfCnpj to set.
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * @return Returns the endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco The endereco to set.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return Returns the dataParcelamento.
	 */
	public String getDataParcelamento() {
		return dataParcelamento;
	}

	/**
	 * @param dataParcelamento The dataParcelamento to set.
	 */
	public void setDataParcelamento(String dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}

	/**
	 * @return Returns the inicioIntervaloParcelamento.
	 */
	public String getInicioIntervaloParcelamento() {
		return inicioIntervaloParcelamento;
	}

	/**
	 * @param inicioIntervaloParcelamento The inicioIntervaloParcelamento to set.
	 */
	public void setInicioIntervaloParcelamento(String inicioIntervaloParcelamento) {
		this.inicioIntervaloParcelamento = inicioIntervaloParcelamento;
	}

	/**
	 * @return Returns the fimIntervaloParcelamento.
	 */
	public String getFimIntervaloParcelamento() {
		return fimIntervaloParcelamento;
	}

	/**
	 * @param fimIntervaloParcelamento The fimIntervaloParcelamento to set.
	 */
	public void setFimIntervaloParcelamento(String fimIntervaloParcelamento) {
		this.fimIntervaloParcelamento = fimIntervaloParcelamento;
	}

	/**
	 * @return Returns the resolucaoDiretoria.
	 */
	public String getResolucaoDiretoria() {
		return resolucaoDiretoria;
	}

	/**
	 * @param resolucaoDiretoria The resolucaoDiretoria to set.
	 */
	public void setResolucaoDiretoria(String resolucaoDiretoria) {
		this.resolucaoDiretoria = resolucaoDiretoria;
	}
	/**
	 * @return Returns the numeroParcelamento.
	 */
	public String getNumeroParcelamento() {
		return numeroParcelamento;
	}

	/**
	 * @param numeroParcelamento The numeroParcelamento to set.
	 */
	public void setNumeroParcelamento(String numeroParcelamento) {
		this.numeroParcelamento = numeroParcelamento;
	}

	/**
	 * @return Returns the numeroReparcelamento.
	 */
	public String getNumeroReparcelamento() {
		return numeroReparcelamento;
	}

	/**
	 * @param numeroReparcelamento The numeroReparcelamento to set.
	 */
	public void setNumeroReparcelamento(String numeroReparcelamento) {
		this.numeroReparcelamento = numeroReparcelamento;
	}

	/**
	 * @return Returns the numeroReparcelamentoConsecutivos.
	 */
	public String getNumeroReparcelamentoConsecutivos() {
		return numeroReparcelamentoConsecutivos;
	}

	/**
	 * @param numeroReparcelamentoConsecutivos The numeroReparcelamentoConsecutivos to set.
	 */
	public void setNumeroReparcelamentoConsecutivos(
			String numeroReparcelamentoConsecutivos) {
		this.numeroReparcelamentoConsecutivos = numeroReparcelamentoConsecutivos;
	}

	/**
	 * @return Returns the valorTotalContaValores.
	 */
	public String getValorTotalContaValores() {
		return valorTotalContaValores;
	}

	/**
	 * @param valorTotalContaValores The valorTotalContaValores to set.
	 */
	public void setValorTotalContaValores(String valorTotalContaValores) {
		this.valorTotalContaValores = valorTotalContaValores;
	}

	/**
	 * @return Returns the valorGuiasPagamento.
	 */
	public String getValorGuiasPagamento() {
		return valorGuiasPagamento;
	}

	/**
	 * @param valorGuiasPagamento The valorGuiasPagamento to set.
	 */
	public void setValorGuiasPagamento(String valorGuiasPagamento) {
		this.valorGuiasPagamento = valorGuiasPagamento;
	}

	/**
	 * @return Retorna o campo situacaoAguaId.
	 */
	public String getSituacaoAguaId() {
		return situacaoAguaId;
	}

	/**
	 * @param situacaoAguaId O situacaoAguaId a ser setado.
	 */
	public void setSituacaoAguaId(String situacaoAguaId) {
		this.situacaoAguaId = situacaoAguaId;
	}

	/**
	 * @return Retorna o campo situacaoEsgotoId.
	 */
	public String getSituacaoEsgotoId() {
		return situacaoEsgotoId;
	}

	/**
	 * @param situacaoEsgotoId O situacaoEsgotoId a ser setado.
	 */
	public void setSituacaoEsgotoId(String situacaoEsgotoId) {
		this.situacaoEsgotoId = situacaoEsgotoId;
	}

	/**
	 * @return Retorna o campo perfilImovel.
	 */
	public String getPerfilImovel() {
		return perfilImovel;
	}

	/**
	 * @param perfilImovel O perfilImovel a ser setado.
	 */
	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public String getDescricaoPerfilImovel() {
		return descricaoPerfilImovel;
	}

	public void setDescricaoPerfilImovel(String descricaoPerfilImovel) {
		this.descricaoPerfilImovel = descricaoPerfilImovel;
	}
	
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		
		super.reset(arg0, arg1);
		
		this.matriculaImovel = "";
		this.inscricaoImovel = "";
		this.nomeCliente = "";
		this.situacaoAgua = "";
		this.situacaoAguaId = "";
		this.situacaoEsgoto = "";
		this.situacaoEsgotoId = "";
		this.perfilImovel = "";
		this.descricaoPerfilImovel = "";
		this.cpfCnpj = "";
		this.endereco = "";
		this.dataParcelamento = "";
		this.inicioIntervaloParcelamento = "";
		this.fimIntervaloParcelamento = "";
		this.resolucaoDiretoria = "";
		this.numeroParcelamento = "";
		this.numeroReparcelamento = "";
		this.numeroReparcelamentoConsecutivos = "";
		this.valorTotalContaValores = "";
		this.valorGuiasPagamento = "";
	}

	/**
	 * @return Retorna o campo valorTotalDescontos.
	 */
	public String getValorTotalDescontos() {
		return valorTotalDescontos;
	}

	/**
	 * @param valorTotalDescontos O valorTotalDescontos a ser setado.
	 */
	public void setValorTotalDescontos(String valorTotalDescontos) {
		this.valorTotalDescontos = valorTotalDescontos;
	}

	/**
	 * @return Retorna o campo valorDebitoTotalAtualizado.
	 */
	public String getValorDebitoTotalAtualizado() {
		return valorDebitoTotalAtualizado;
	}

	/**
	 * @param valorDebitoTotalAtualizado O valorDebitoTotalAtualizado a ser setado.
	 */
	public void setValorDebitoTotalAtualizado(String valorDebitoTotalAtualizado) {
		this.valorDebitoTotalAtualizado = valorDebitoTotalAtualizado;
	}

	public String getValorMinimoPrestacao() {
		return valorMinimoPrestacao;
	}

	public void setValorMinimoPrestacao(String valorMinimoPrestacao) {
		this.valorMinimoPrestacao = valorMinimoPrestacao;
	}
	
	public String getIndicadorConfirmacaoParcelamento() {
		return indicadorConfirmacaoParcelamento;
	}

	public void setIndicadorConfirmacaoParcelamento(
			String indicadorConfirmacaoParcelamento) {
		this.indicadorConfirmacaoParcelamento = indicadorConfirmacaoParcelamento;
	}

	public String getIdClienteParcelamento() {
		return idClienteParcelamento;
	}

	public void setIdClienteParcelamento(String idClienteParcelamento) {
		this.idClienteParcelamento = idClienteParcelamento;
	}

	public String getNomeClienteParcelamento() {
		return nomeClienteParcelamento;
	}

	public void setNomeClienteParcelamento(String nomeClienteParcelamento) {
		this.nomeClienteParcelamento = nomeClienteParcelamento;
	}

	
}
