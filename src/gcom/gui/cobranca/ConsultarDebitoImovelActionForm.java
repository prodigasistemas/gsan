package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

public class ConsultarDebitoImovelActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoImovel;
	
	private String tipoRelacao;

	private String referenciaInicial;

	private String referenciaFinal;

	private String dataVencimentoInicial;

	private String dataVencimentoFinal;
	
	private String ligacaoAgua;
	
	private String ligacaoEsgoto;
	
	private String maticula;
	
	private String inscricao;
	
	private String nomeCliente;
	
	private String tipoRelacaoCliente;
	
	private String cpf;
	
	private String cnpj;
	
	private String refInicial;
	
	private String refFinal;
	
	private String dtInicial;
	
	private String dtFinal;
	
	private String ligacaoAguaId;
	private String ligacaoEsgotoId;
	private String indicadorEmissaoExtratoNaConsulta;
	
	private String[] contasSelecionadas = {};
	
	private String[] debitosSelecionados = {};
	
	private String[] creditosSelecionados = {};
	
	private String[] guiasSelecionadas = {};
	
	public String[] getContasSelecionadas() {
		return contasSelecionadas;
	}

	public void setContasSelecionadas(String[] contasSelecionadas) {
		this.contasSelecionadas = contasSelecionadas;
	}

	public String[] getDebitosSelecionados() {
		return debitosSelecionados;
	}

	public void setDebitosSelecionados(String[] debitosSelecionados) {
		this.debitosSelecionados = debitosSelecionados;
	}

	public String[] getCreditosSelecionados() {
		return creditosSelecionados;
	}

	public void setCreditosSelecionados(String[] creditosSelecionados) {
		this.creditosSelecionados = creditosSelecionados;
	}

	public String[] getGuiasSelecionadas() {
		return guiasSelecionadas;
	}

	public void setGuiasSelecionadas(String[] guiasSelecionadas) {
		this.guiasSelecionadas = guiasSelecionadas;
	}

	public String getIndicadorEmissaoExtratoNaConsulta() {
		return indicadorEmissaoExtratoNaConsulta;
	}

	public void setIndicadorEmissaoExtratoNaConsulta(
			String indicadorEmissaoExtratoNaConsulta) {
		this.indicadorEmissaoExtratoNaConsulta = indicadorEmissaoExtratoNaConsulta;
	}

	public String getCodigoImovel() {
		return codigoImovel;
	}

	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	public String getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}

	public void setDataVencimentoFinal(String dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}

	public String getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}

	public void setDataVencimentoInicial(String dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}

	public String getReferenciaFinal() {
		return referenciaFinal;
	}

	public void setReferenciaFinal(String referenciaFinal) {
		this.referenciaFinal = referenciaFinal;
	}

	public String getReferenciaInicial() {
		return referenciaInicial;
	}

	public void setReferenciaInicial(String referenciaInicial) {
		this.referenciaInicial = referenciaInicial;
	}

	public String getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getLigacaoAgua() {
		return ligacaoAgua;
	}

	public void setLigacaoAgua(String ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}

	public String getLigacaoEsgoto() {
		return ligacaoEsgoto;
	}

	public void setLigacaoEsgoto(String ligacaoEsgoto) {
		this.ligacaoEsgoto = ligacaoEsgoto;
	}

	public String getMaticula() {
		return maticula;
	}

	public void setMaticula(String maticula) {
		this.maticula = maticula;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getTipoRelacaoCliente() {
		return tipoRelacaoCliente;
	}

	public void setTipoRelacaoCliente(String tipoRelacaoCliente) {
		this.tipoRelacaoCliente = tipoRelacaoCliente;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(String dtFinal) {
		this.dtFinal = dtFinal;
	}

	public String getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(String dtInicial) {
		this.dtInicial = dtInicial;
	}

	public String getRefFinal() {
		return refFinal;
	}

	public void setRefFinal(String refFinal) {
		this.refFinal = refFinal;
	}

	public String getRefInicial() {
		return refInicial;
	}

	public void setRefInicial(String refInicial) {
		this.refInicial = refInicial;
	}

	public String getLigacaoAguaId() {
		return ligacaoAguaId;
	}

	public void setLigacaoAguaId(String ligacaoAguaId) {
		this.ligacaoAguaId = ligacaoAguaId;
	}

	public String getLigacaoEsgotoId() {
		return ligacaoEsgotoId;
	}

	public void setLigacaoEsgotoId(String ligacaoEsgotoId) {
		this.ligacaoEsgotoId = ligacaoEsgotoId;
	}

}
