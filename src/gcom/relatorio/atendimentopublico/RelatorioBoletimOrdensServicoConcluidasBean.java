package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0766] - Relatorio Boletim de Ordens de Servico Concluidas
 * 
 * @author Ivan Sérgio
 * @date 07/05/2008
 * 
 */
public class RelatorioBoletimOrdensServicoConcluidasBean implements RelatorioBean {

	private String referenciaEncerramentoMes;
	private String referenciaEncerramentoAno;
	private String situacao;
	private String idLocalidade;
	private String nomeLocalidade;
	private String idLocalInstalacao;
	private String descricaoLocalInstalacao;
	private String nomeAbreviadoFirma;
	private String inscricao;
	private String idOrdemServico;
	private String tipoServico;
	private String trocaProtecao;
	private String trocaRegistro;
	private String dataGeracaoOrdemServico;
	private String dataEncerramentoOrdemServico;
	private String dataFiscalizacao1;
	private String dataFiscalizacao2;
	private String dataFiscalizacao3;
	private String paga;
	private String idImovel;
	private String codigoSetorComercial;
	private String idSetorComercial;
	
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public RelatorioBoletimOrdensServicoConcluidasBean () {}

	public String getDataEncerramentoOrdemServico() {
		return dataEncerramentoOrdemServico;
	}

	public void setDataEncerramentoOrdemServico(String dataEncerramentoOrdemServico) {
		this.dataEncerramentoOrdemServico = dataEncerramentoOrdemServico;
	}

	public String getDataFiscalizacao1() {
		return dataFiscalizacao1;
	}

	public void setDataFiscalizacao1(String dataFiscalizacao1) {
		this.dataFiscalizacao1 = dataFiscalizacao1;
	}

	public String getDataFiscalizacao2() {
		return dataFiscalizacao2;
	}

	public void setDataFiscalizacao2(String dataFiscalizacao2) {
		this.dataFiscalizacao2 = dataFiscalizacao2;
	}

	public String getDataFiscalizacao3() {
		return dataFiscalizacao3;
	}

	public void setDataFiscalizacao3(String dataFiscalizacao3) {
		this.dataFiscalizacao3 = dataFiscalizacao3;
	}

	public String getDataGeracaoOrdemServico() {
		return dataGeracaoOrdemServico;
	}

	public void setDataGeracaoOrdemServico(String dataGeracaoOrdemServico) {
		this.dataGeracaoOrdemServico = dataGeracaoOrdemServico;
	}

	public String getDescricaoLocalInstalacao() {
		return descricaoLocalInstalacao;
	}

	public void setDescricaoLocalInstalacao(String descricaoLocalInstalacao) {
		this.descricaoLocalInstalacao = descricaoLocalInstalacao;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdLocalInstalacao() {
		return idLocalInstalacao;
	}

	public void setIdLocalInstalacao(String idLocalInstalacao) {
		this.idLocalInstalacao = idLocalInstalacao;
	}

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getNomeAbreviadoFirma() {
		return nomeAbreviadoFirma;
	}

	public void setNomeAbreviadoFirma(String nomeAbreviadoFirma) {
		this.nomeAbreviadoFirma = nomeAbreviadoFirma;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getPaga() {
		return paga;
	}

	public void setPaga(String paga) {
		this.paga = paga;
	}

	public String getReferenciaEncerramentoAno() {
		return referenciaEncerramentoAno;
	}

	public void setReferenciaEncerramentoAno(String referenciaEncerramentoAno) {
		this.referenciaEncerramentoAno = referenciaEncerramentoAno;
	}

	public String getReferenciaEncerramentoMes() {
		return referenciaEncerramentoMes;
	}

	public void setReferenciaEncerramentoMes(String referenciaEncerramentoMes) {
		this.referenciaEncerramentoMes = referenciaEncerramentoMes;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getTrocaProtecao() {
		return trocaProtecao;
	}

	public void setTrocaProtecao(String trocaProtecao) {
		this.trocaProtecao = trocaProtecao;
	}

	public String getTrocaRegistro() {
		return trocaRegistro;
	}

	public void setTrocaRegistro(String trocaRegistro) {
		this.trocaRegistro = trocaRegistro;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
}
