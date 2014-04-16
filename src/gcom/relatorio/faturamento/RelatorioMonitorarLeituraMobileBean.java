package gcom.relatorio.faturamento;

import gcom.micromedicao.bean.MonitorarLeituraMobilePopupHelper;
import gcom.relatorio.RelatorioBean;

public class RelatorioMonitorarLeituraMobileBean implements RelatorioBean {

	private String inscricao;
	private String idImovel;
	private String sequencialRota;
	private String leituraAnterior;
	private String leituraAtual;
	private String idAnormalidade;
	private String dtLeitura;
	private String dtRecebimento;
	private String icEmissaoConta;	
	
	public RelatorioMonitorarLeituraMobileBean( MonitorarLeituraMobilePopupHelper helper ){
		this.inscricao = helper.getInscricao();
		this.idImovel = helper.getIdImovel();
		this.sequencialRota = helper.getSequencialRota();
		this.leituraAnterior = helper.getLeituraAnterior();
		this.leituraAtual = helper.getLeituraAtual();
		this.idAnormalidade = helper.getIdAnormalidade();
		this.dtLeitura = helper.getDtLeitura();
		this.dtRecebimento = helper.getDtRecebimento();
		this.icEmissaoConta = helper.getIcEmissaoConta();
	}
	
	public String getDtLeitura() {
		return dtLeitura;
	}
	public void setDtLeitura(String dtLeitura) {
		this.dtLeitura = dtLeitura;
	}
	public String getDtRecebimento() {
		return dtRecebimento;
	}
	public void setDtRecebimento(String dtRecebimento) {
		this.dtRecebimento = dtRecebimento;
	}
	public String getIcEmissaoConta() {
		return icEmissaoConta;
	}
	public void setIcEmissaoConta(String icEmissaoConta) {
		this.icEmissaoConta = icEmissaoConta;
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
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
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
	public String getSequencialRota() {
		return sequencialRota;
	}
	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}
	
}
