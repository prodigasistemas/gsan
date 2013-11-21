package gcom.relatorio.cadastro.imovel;

import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoCobradoHistorico;

import java.util.Collection;

public class RelatorioDebitoCobradoContaHelper {

	private String idImovel;
	
	private String situacaoConta;
	
	private String situacaoLigacaoAgua;
	
	private String mesAnoConta;
	
	private String situacaoLigacaoEsgoto;
	
	private Collection<DebitoCobrado> colecaoDebitosCobrados;
	
	private Collection<DebitoCobradoHistorico> colecaoDebitosCobradosHistorico;

	
	public RelatorioDebitoCobradoContaHelper() {
		
	}

	public Collection<DebitoCobrado> getColecaoDebitosCobrados() {
		return colecaoDebitosCobrados;
	}

	public void setColecaoDebitosCobrados(
			Collection<DebitoCobrado> colecaoDebitosCobrados) {
		this.colecaoDebitosCobrados = colecaoDebitosCobrados;
	}

	public Collection<DebitoCobradoHistorico> getColecaoDebitosCobradosHistorico() {
		return colecaoDebitosCobradosHistorico;
	}

	public void setColecaoDebitosCobradosHistorico(
			Collection<DebitoCobradoHistorico> colecaoDebitosCobradosHistorico) {
		this.colecaoDebitosCobradosHistorico = colecaoDebitosCobradosHistorico;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getMesAnoConta() {
		return mesAnoConta;
	}

	public void setMesAnoConta(String mesAnoConta) {
		this.mesAnoConta = mesAnoConta;
	}

	public String getSituacaoConta() {
		return situacaoConta;
	}

	public void setSituacaoConta(String situacaoConta) {
		this.situacaoConta = situacaoConta;
	}

	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}
	
	
	
	
}
