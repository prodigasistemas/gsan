package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.action.ActionForm;

public class EmitirOrdemFiscalizacaoForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String matriculaImovel;
	private String inscricaoImovel;
	private String enderecoImovel;
	private String tipo;
	private String tipoHidden;
	private String indicadorPermitirEmitir;
	private String indicadorPermitirGerarOs;
	private String dataEmissao;
	private String idPerfilImovel;
	private String descricaoPerfilImovel;
	private String endereco;
	private String ultimaAlteracao;
	private String faturamentoGrupo;
	private String situacaoLigacaoAgua;
	private String consumoMedioAgua;
	private String dataCorte;
	private String dataSupressaoParcial;
	private String dataSupressaoTotal;
	private String situacaoLigacaoEsgoto;
	private String volumeFixoEsgoto;
	private String ocorrencia;
	private String valorServicos;
	private String valorDebitosAteDataVencimento;
	private String nomeCliente;
	private String cpfCnpj;
	private String rg;
	private String ddd;
	private String numeroTelefone;
	private String ramal;
	private String tipoTelefone;
	private Integer qtdeEconResidencial;
	private Integer qtdeEconComercial;
	private Integer qtdeEconIndustrial;
	private Integer qtdeEconPublica;
	private Integer qtdeEconTotal;
	private String ordemServico;
	private String uf;
	private String situacaoAguaDebitos;
	private String situacaoEsgotoDebitos;

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getIdPerfilImovel() {
		return idPerfilImovel;
	}

	public void setIdPerfilImovel(String idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	public void setFaturamentoGrupo(String faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}

	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	public String getConsumoMedioAgua() {
		return consumoMedioAgua;
	}

	public void setConsumoMedioAgua(String consumoMedioAgua) {
		this.consumoMedioAgua = consumoMedioAgua;
	}

	public String getDataCorte() {
		return dataCorte;
	}

	public void setDataCorte(String dataCorte) {
		this.dataCorte = dataCorte;
	}

	public String getDataSupressaoParcial() {
		return dataSupressaoParcial;
	}

	public void setDataSupressaoParcial(String dataSupressaoParcial) {
		this.dataSupressaoParcial = dataSupressaoParcial;
	}

	public String getDataSupressaoTotal() {
		return dataSupressaoTotal;
	}

	public void setDataSupressaoTotal(String dataSupressaoTotal) {
		this.dataSupressaoTotal = dataSupressaoTotal;
	}

	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public String getVolumeFixoEsgoto() {
		return volumeFixoEsgoto;
	}

	public void setVolumeFixoEsgoto(String volumeFixoEsgoto) {
		this.volumeFixoEsgoto = volumeFixoEsgoto;
	}

	public String getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public String getValorServicos() {
		return valorServicos;
	}

	public void setValorServicos(String valorServicos) {
		this.valorServicos = valorServicos;
	}

	public String getValorDebitosAteDataVencimento() {
		return valorDebitosAteDataVencimento;
	}

	public void setValorDebitosAteDataVencimento(
			String valorDebitosAteDataVencimento) {
		this.valorDebitosAteDataVencimento = valorDebitosAteDataVencimento;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public String getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public String getDescricaoPerfilImovel() {
		return descricaoPerfilImovel;
	}

	public void setDescricaoPerfilImovel(String descricaoPerfilImovel) {
		this.descricaoPerfilImovel = descricaoPerfilImovel;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public Integer getQtdeEconResidencial() {
		return qtdeEconResidencial;
	}

	public void setQtdeEconResidencial(Integer qtdeEconResidencial) {
		this.qtdeEconResidencial = qtdeEconResidencial;
	}

	public Integer getQtdeEconComercial() {
		return qtdeEconComercial;
	}

	public void setQtdeEconComercial(Integer qtdeEconComercial) {
		this.qtdeEconComercial = qtdeEconComercial;
	}

	public Integer getQtdeEconIndustrial() {
		return qtdeEconIndustrial;
	}

	public void setQtdeEconIndustrial(Integer qtdeEconIndustrial) {
		this.qtdeEconIndustrial = qtdeEconIndustrial;
	}

	public Integer getQtdeEconPublica() {
		return qtdeEconPublica;
	}

	public void setQtdeEconPublica(Integer qtdeEconPublica) {
		this.qtdeEconPublica = qtdeEconPublica;
	}

	public Integer getQtdeEconTotal() {
		return qtdeEconTotal;
	}

	public void setQtdeEconTotal(Integer qtdeEconTotal) {
		this.qtdeEconTotal = qtdeEconTotal;
	}

	public String getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(String ordemServico) {
		this.ordemServico = ordemServico;
	}

	public String getIndicadorPermitirEmitir() {
		return indicadorPermitirEmitir;
	}

	public void setIndicadorPermitirEmitir(String indicadorPermitirEmitir) {
		this.indicadorPermitirEmitir = indicadorPermitirEmitir;
	}

	public String getIndicadorPermitirGerarOs() {
		return indicadorPermitirGerarOs;
	}

	public void setIndicadorPermitirGerarOs(String indicadorPermitirGerarOs) {
		this.indicadorPermitirGerarOs = indicadorPermitirGerarOs;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoHidden() {
		return tipoHidden;
	}

	public void setTipoHidden(String tipoHidden) {
		this.tipoHidden = tipoHidden;
	}

	public String getSituacaoAguaDebitos() {
		return situacaoAguaDebitos;
	}

	public void setSituacaoAguaDebitos(String situacaoAguaDebitos) {
		this.situacaoAguaDebitos = situacaoAguaDebitos;
	}

	public String getSituacaoEsgotoDebitos() {
		return situacaoEsgotoDebitos;
	}

	public void setSituacaoEsgotoDebitos(String situacaoEsgotoDebitos) {
		this.situacaoEsgotoDebitos = situacaoEsgotoDebitos;
	}


}
