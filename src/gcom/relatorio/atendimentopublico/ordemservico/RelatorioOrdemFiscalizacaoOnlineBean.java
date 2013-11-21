package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.relatorio.RelatorioBean;

public class RelatorioOrdemFiscalizacaoOnlineBean implements RelatorioBean {
	
	private String matriculaImovel;
	private String inscricaoImovel;
	private String enderecoImovel;
	private String tipoMedicao;
	private String dataEmissao;
	private String idPerfilImovel;
	private String descricaoPerfilImovel;
	private String endereco;
	private String ultimaAlteracao;
	private String faturamentoGrupo;
	private String qtdeEconResidencial;
	private String qtdeEconComercial;
	private String qtdeEconIndustrial;
	private String qtdeEconPublica;
	private String qtdeEconTotal;
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
	private String origem;
	private String ordemServico;
	private String data;
	private String uf;
	
	
	
	public RelatorioOrdemFiscalizacaoOnlineBean(String matriculaImovel,
			String inscricaoImovel, String enderecoImovel,
			String dataEmissao,
			String descricaoPerfilImovel,
			String ultimaAlteracao, String faturamentoGrupo,
			String qtdeEconResidencial, String qtdeEconComercial,
			String qtdeEconIndustrial, String qtdeEconPublica,
			String qtdeEconTotal, String situacaoLigacaoAgua,
			String consumoMedioAgua, String dataCorte,
			String dataSupressaoParcial, String dataSupressaoTotal,
			String situacaoLigacaoEsgoto, String volumeFixoEsgoto,
			String ocorrencia, String valorServicos,
			String valorDebitosAteDataVencimento, String nomeCliente,
			String cpfCnpj, String rg, String ddd, String numeroTelefone,
			String ramal, String tipoTelefone,
			String origem, String ordemServico,String data,
			String uf) {
		this.matriculaImovel = matriculaImovel;
		this.inscricaoImovel = inscricaoImovel;
		this.enderecoImovel = enderecoImovel;
		this.dataEmissao = dataEmissao;
		this.descricaoPerfilImovel = descricaoPerfilImovel;
		this.ultimaAlteracao = ultimaAlteracao;
		this.faturamentoGrupo = faturamentoGrupo;
		this.qtdeEconResidencial = qtdeEconResidencial;
		this.qtdeEconComercial = qtdeEconComercial;
		this.qtdeEconIndustrial = qtdeEconIndustrial;
		this.qtdeEconPublica = qtdeEconPublica;
		this.qtdeEconTotal = qtdeEconTotal;
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
		this.consumoMedioAgua = consumoMedioAgua;
		this.dataCorte = dataCorte;
		this.dataSupressaoParcial = dataSupressaoParcial;
		this.dataSupressaoTotal = dataSupressaoTotal;
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
		this.volumeFixoEsgoto = volumeFixoEsgoto;
		this.ocorrencia = ocorrencia;
		this.valorServicos = valorServicos;
		this.valorDebitosAteDataVencimento = valorDebitosAteDataVencimento;
		this.nomeCliente = nomeCliente;
		this.cpfCnpj = cpfCnpj;
		this.rg = rg;
		this.ddd = ddd;
		this.numeroTelefone = numeroTelefone;
		this.ramal = ramal;
		this.tipoTelefone = tipoTelefone;
		this.origem = origem;
		this.ordemServico = ordemServico;
		this.data = data;
		this.uf = uf;
	}

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
	public String getTipoMedicao() {
		return tipoMedicao;
	}
	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
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
	public String getDescricaoPerfilImovel() {
		return descricaoPerfilImovel;
	}
	public void setDescricaoPerfilImovel(String descricaoPerfilImovel) {
		this.descricaoPerfilImovel = descricaoPerfilImovel;
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
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getQtdeEconResidencial() {
		return qtdeEconResidencial;
	}
	public void setQtdeEconResidencial(String qtdeEconResidencial) {
		this.qtdeEconResidencial = qtdeEconResidencial;
	}
	public String getQtdeEconComercial() {
		return qtdeEconComercial;
	}
	public void setQtdeEconComercial(String qtdeEconComercial) {
		this.qtdeEconComercial = qtdeEconComercial;
	}
	public String getQtdeEconIndustrial() {
		return qtdeEconIndustrial;
	}
	public void setQtdeEconIndustrial(String qtdeEconIndustrial) {
		this.qtdeEconIndustrial = qtdeEconIndustrial;
	}
	public String getQtdeEconPublica() {
		return qtdeEconPublica;
	}
	public void setQtdeEconPublica(String qtdeEconPublica) {
		this.qtdeEconPublica = qtdeEconPublica;
	}
	public String getQtdeEconTotal() {
		return qtdeEconTotal;
	}
	public void setQtdeEconTotal(String qtdeEconTotal) {
		this.qtdeEconTotal = qtdeEconTotal;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(String ordemServico) {
		this.ordemServico = ordemServico;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	
}
