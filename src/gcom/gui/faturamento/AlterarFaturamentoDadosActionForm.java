package gcom.gui.faturamento;

import org.apache.struts.action.ActionForm;


public class AlterarFaturamentoDadosActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idLeiturista; 

	private String idSetorComercial; 

	private String idSituacaoLeituraAtual; 

	private String consumoInformado; 

	private String consumoMedido; 

	private String dataLeituraAnterior; 

	private String dataLeituraAtual; 
	
	private String enderecoFormatado;
	
	private String idImovel; 

	private String idLocalidade; 

	private String idImovelSelecionado; 

	private String indicadorConfirmacao; 

	private String leituraAnterior; 

	private String leituraAtual; 

	private String nomeAnormalidade; 

	private String nomeLeiturista; 

	private String nomeLocalidade; 

	private String nomeSetorComercial; 

	private String nomeSituacaoLeituraAtual; 

	private String tipoMedicao; 

	private String tipoMedicaoSelecionada; 
	
	private String idAnormalidade;
	
	private String inscricaoImovel;
	
	private String nomeUsuario;
	
	private String ligacaoAguaSituacao;
	
	private String ligacaoEsgotoSituacao;

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getConsumoInformado() {
		return consumoInformado;
	}

	public void setConsumoInformado(String consumoInformado) {
		this.consumoInformado = consumoInformado;
	}

	public String getConsumoMedido() {
		return consumoMedido;
	}

	public void setConsumoMedido(String consumoMedido) {
		this.consumoMedido = consumoMedido;
	}

	public String getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}

	public void setDataLeituraAnterior(String dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}

	public String getDataLeituraAtual() {
		return dataLeituraAtual;
	}

	public void setDataLeituraAtual(String dataLeituraAtual) {
		this.dataLeituraAtual = dataLeituraAtual;
	}

	public String getEnderecoFormatado() {
		return enderecoFormatado;
	}

	public void setEnderecoFormatado(String enderecoFormatado) {
		this.enderecoFormatado = enderecoFormatado;
	}

	public String getIdAnormalidade() {
		return idAnormalidade;
	}

	public void setIdAnormalidade(String idAnormalidade) {
		this.idAnormalidade = idAnormalidade;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdImovelSelecionado() {
		return idImovelSelecionado;
	}

	public void setIdImovelSelecionado(String idImovelSelecionado) {
		this.idImovelSelecionado = idImovelSelecionado;
	}

	public String getIdLeiturista() {
		return idLeiturista;
	}

	public void setIdLeiturista(String idLeiturista) {
		this.idLeiturista = idLeiturista;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getIdSituacaoLeituraAtual() {
		return idSituacaoLeituraAtual;
	}

	public void setIdSituacaoLeituraAtual(String idSituacaoLeituraAtual) {
		this.idSituacaoLeituraAtual = idSituacaoLeituraAtual;
	}

	public String getIndicadorConfirmacao() {
		return indicadorConfirmacao;
	}

	public void setIndicadorConfirmacao(String indicadorConfirmacao) {
		this.indicadorConfirmacao = indicadorConfirmacao;
	}

	public String getLeituraAnterior() {
		return leituraAnterior;
	}

	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public String getLeituraAtual() {
		return leituraAtual;
	}

	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	public String getNomeAnormalidade() {
		return nomeAnormalidade;
	}

	public void setNomeAnormalidade(String nomeAnormalidade) {
		this.nomeAnormalidade = nomeAnormalidade;
	}

	public String getNomeLeiturista() {
		return nomeLeiturista;
	}

	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}

	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}

	public String getNomeSituacaoLeituraAtual() {
		return nomeSituacaoLeituraAtual;
	}

	public void setNomeSituacaoLeituraAtual(String nomeSituacaoLeituraAtual) {
		this.nomeSituacaoLeituraAtual = nomeSituacaoLeituraAtual;
	}

	public String getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	public String getTipoMedicaoSelecionada() {
		return tipoMedicaoSelecionada;
	}

	public void setTipoMedicaoSelecionada(String tipoMedicaoSelecionada) {
		this.tipoMedicaoSelecionada = tipoMedicaoSelecionada;
	}

	/**
	 * @return Retorna o campo ligacaoAguaSituacao.
	 */
	public String getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	/**
	 * @param ligacaoAguaSituacao O ligacaoAguaSituacao a ser setado.
	 */
	public void setLigacaoAguaSituacao(String ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	/**
	 * @return Retorna o campo ligacaoEsgotoSituacao.
	 */
	public String getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	/**
	 * @param ligacaoEsgotoSituacao O ligacaoEsgotoSituacao a ser setado.
	 */
	public void setLigacaoEsgotoSituacao(String ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	/**
	 * @return Retorna o campo nomeUsuario.
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/**
	 * @param nomeUsuario O nomeUsuario a ser setado.
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	} 
	

}

	
