package gcom.atendimentopublico.ordemservico.bean;

import gcom.cadastro.imovel.Imovel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EmitirOrdemDeFiscalizacaoHelper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Imovel imovel;
	private Date dataEmissao;
	private String numeroInscricao;
	private String matriculaImovel;
	private String descricaPerfilImovel;
	private String descricaoEndereco;
	private String bairro;
	private String municipio;
	private String unidadeFederacao;
	private String cep;
	private Integer qtdEconomiasRes;
	private Integer qtdEconomiasCom;
	private Integer qtdEconomiasInd;
	private Integer qtdEconomiasPub;
	private Integer qtdEconomiasTot;
	private String ultimaAlteracao;
	private Integer idGrupoFaturamento;
	private String descricaoSituacaoAgua;
	private Integer cosumoMedio;
	private String dataCorte;
	private String dataSupressaoParcial;
	private String dataSupressaoTotal;
	private String descricaoSituacaoEsgoto;
	private Integer volumeFixoEsgoto;
	private String descricaoOcorrencia;
	private BigDecimal valorServicosAtualizacoes;
	private BigDecimal valorDebito;
	private String nomeCliente;
	private String cpfCnpj;
	private String rg;
	private String numeroTelefonico;
	private String ramal;
	private String tipoTelefonico;
	private Integer idLocalidade;
	private Integer idSetorComercial;
	private Integer codigoSetorComercial;
	private Integer idQuadra;
	private Integer numeroQuadra;
	private Integer numeroLote;
	private Integer numeroSubLote;

	public EmitirOrdemDeFiscalizacaoHelper() {

	}


	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getNumeroInscricao() {
		return numeroInscricao;
	}

	public void setNumeroInscricao(String numeroInscricao) {
		this.numeroInscricao = numeroInscricao;
	}
	
	public String getMatriculaFormatada() {

		String matriculaImovelFormatada = "";
		
		String matriculaImovel = this.matriculaImovel;
		
		matriculaImovelFormatada = "" + matriculaImovel;
		
		int quantidadeCaracteresImovel = matriculaImovel.length();
			matriculaImovelFormatada = matriculaImovelFormatada.substring(0,
					quantidadeCaracteresImovel - 1)
					+ "."
					+ matriculaImovelFormatada.substring(
							quantidadeCaracteresImovel - 1,
							quantidadeCaracteresImovel);

		return matriculaImovelFormatada;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getDescricaPerfilImovel() {
		return descricaPerfilImovel;
	}

	public void setDescricaPerfilImovel(String descricaPerfilImovel) {
		this.descricaPerfilImovel = descricaPerfilImovel;
	}

	public String getDescricaoEndereco() {
		return descricaoEndereco;
	}

	public void setDescricaoEndereco(String descricaoEndereco) {
		this.descricaoEndereco = descricaoEndereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getUnidadeFederacao() {
		return unidadeFederacao;
	}

	public void setUnidadeFederacao(String unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getQtdEconomiasRes() {
		return qtdEconomiasRes;
	}

	public void setQtdEconomiasRes(Integer qtdEconomiasRes) {
		this.qtdEconomiasRes = qtdEconomiasRes;
	}

	public Integer getQtdEconomiasCom() {
		return qtdEconomiasCom;
	}

	public void setQtdEconomiasCom(Integer qtdEconomiasCom) {
		this.qtdEconomiasCom = qtdEconomiasCom;
	}

	public Integer getQtdEconomiasInd() {
		return qtdEconomiasInd;
	}

	public void setQtdEconomiasInd(Integer qtdEconomiasInd) {
		this.qtdEconomiasInd = qtdEconomiasInd;
	}

	public Integer getQtdEconomiasPub() {
		return qtdEconomiasPub;
	}

	public void setQtdEconomiasPub(Integer qtdEconomiasPub) {
		this.qtdEconomiasPub = qtdEconomiasPub;
	}

	public Integer getQtdEconomiasTot() {
		return qtdEconomiasTot;
	}

	public void setQtdEconomiasTot(Integer qtdEconomiasTot) {
		this.qtdEconomiasTot = qtdEconomiasTot;
	}

	public Integer getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(Integer idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public String getDescricaoSituacaoAgua() {
		return descricaoSituacaoAgua;
	}

	public void setDescricaoSituacaoAgua(String descricaoSituacaoAgua) {
		this.descricaoSituacaoAgua = descricaoSituacaoAgua;
	}

	public Integer getCosumoMedio() {
		return cosumoMedio;
	}

	public void setCosumoMedio(Integer cosumoMedio) {
		this.cosumoMedio = cosumoMedio;
	}

	public String getDescricaoSituacaoEsgoto() {
		return descricaoSituacaoEsgoto;
	}

	public void setDescricaoSituacaoEsgoto(String descricaoSituacaoEsgoto) {
		this.descricaoSituacaoEsgoto = descricaoSituacaoEsgoto;
	}

	public Integer getVolumeFixoEsgoto() {
		return volumeFixoEsgoto;
	}

	public void setVolumeFixoEsgoto(Integer volumeFixoEsgoto) {
		this.volumeFixoEsgoto = volumeFixoEsgoto;
	}

	public String getDescricaoOcorrencia() {
		return descricaoOcorrencia;
	}

	public void setDescricaoOcorrencia(String descricaoOcorrencia) {
		this.descricaoOcorrencia = descricaoOcorrencia;
	}

	public BigDecimal getValorServicosAtualizacoes() {
		return valorServicosAtualizacoes;
	}

	public void setValorServicosAtualizacoes(BigDecimal valorServicosAtualizacoes) {
		this.valorServicosAtualizacoes = valorServicosAtualizacoes;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
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

	public String getNumeroTelefonico() {
		return numeroTelefonico;
	}

	public void setNumeroTelefonico(String numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public String getTipoTelefonico() {
		return tipoTelefonico;
	}

	public void setTipoTelefonico(String tipoTelefonico) {
		this.tipoTelefonico = tipoTelefonico;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Integer getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(Integer numeroLote) {
		this.numeroLote = numeroLote;
	}

	public Integer getNumeroSubLote() {
		return numeroSubLote;
	}

	public void setNumeroSubLote(Integer numeroSubLote) {
		this.numeroSubLote = numeroSubLote;
	}

	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
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


}
