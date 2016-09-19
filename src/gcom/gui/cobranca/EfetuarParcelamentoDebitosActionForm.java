package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

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

	public String getIndicadorRestabelecimento() {
		return indicadorRestabelecimento;
	}

	public void setIndicadorRestabelecimento(String indicadorRestabelecimento) {
		this.indicadorRestabelecimento = indicadorRestabelecimento;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDataParcelamento() {
		return dataParcelamento;
	}

	public void setDataParcelamento(String dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}

	public String getInicioIntervaloParcelamento() {
		return inicioIntervaloParcelamento;
	}

	public void setInicioIntervaloParcelamento(String inicioIntervaloParcelamento) {
		this.inicioIntervaloParcelamento = inicioIntervaloParcelamento;
	}

	public String getFimIntervaloParcelamento() {
		return fimIntervaloParcelamento;
	}

	public void setFimIntervaloParcelamento(String fimIntervaloParcelamento) {
		this.fimIntervaloParcelamento = fimIntervaloParcelamento;
	}

	public String getResolucaoDiretoria() {
		return resolucaoDiretoria;
	}

	public void setResolucaoDiretoria(String resolucaoDiretoria) {
		this.resolucaoDiretoria = resolucaoDiretoria;
	}

	public String getNumeroParcelamento() {
		return numeroParcelamento;
	}

	public void setNumeroParcelamento(String numeroParcelamento) {
		this.numeroParcelamento = numeroParcelamento;
	}

	public String getNumeroReparcelamento() {
		return numeroReparcelamento;
	}

	public void setNumeroReparcelamento(String numeroReparcelamento) {
		this.numeroReparcelamento = numeroReparcelamento;
	}

	public String getNumeroReparcelamentoConsecutivos() {
		return numeroReparcelamentoConsecutivos;
	}

	public void setNumeroReparcelamentoConsecutivos(String numeroReparcelamentoConsecutivos) {
		this.numeroReparcelamentoConsecutivos = numeroReparcelamentoConsecutivos;
	}

	public String getValorTotalContaValores() {
		return valorTotalContaValores;
	}

	public void setValorTotalContaValores(String valorTotalContaValores) {
		this.valorTotalContaValores = valorTotalContaValores;
	}

	public String getValorGuiasPagamento() {
		return valorGuiasPagamento;
	}

	public void setValorGuiasPagamento(String valorGuiasPagamento) {
		this.valorGuiasPagamento = valorGuiasPagamento;
	}

	public String getSituacaoAguaId() {
		return situacaoAguaId;
	}

	public void setSituacaoAguaId(String situacaoAguaId) {
		this.situacaoAguaId = situacaoAguaId;
	}

	public String getSituacaoEsgotoId() {
		return situacaoEsgotoId;
	}

	public void setSituacaoEsgotoId(String situacaoEsgotoId) {
		this.situacaoEsgotoId = situacaoEsgotoId;
	}

	public String getPerfilImovel() {
		return perfilImovel;
	}

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

	public String getValorTotalDescontos() {
		return valorTotalDescontos;
	}

	public void setValorTotalDescontos(String valorTotalDescontos) {
		this.valorTotalDescontos = valorTotalDescontos;
	}

	public String getValorDebitoTotalAtualizado() {
		return valorDebitoTotalAtualizado;
	}

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

	public void setIndicadorConfirmacaoParcelamento(String indicadorConfirmacaoParcelamento) {
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
