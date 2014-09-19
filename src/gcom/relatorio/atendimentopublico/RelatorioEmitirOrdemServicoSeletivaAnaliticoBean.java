package gcom.relatorio.atendimentopublico;

import java.util.Collection;

import gcom.relatorio.RelatorioBean;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioEmitirOrdemServicoSeletivaAnaliticoBean implements
		RelatorioBean {

	private JRBeanCollectionDataSource colecaoCategorias;
	private String matricula;
	private String inscricao;
	private String cliente;
	private String endereco;
	private String localidade;
	private String unidadeResponsavel;
	private String numeroHidrometro;
	private String consumoMedio;
	private String consumo1;
	private String mesAno1;
	private String consumo2;
	private String mesAno2;
	private String consumo3;
	private String mesAno3;
	private String consumo4;
	private String mesAno4;
	private String consumo5;
	private String mesAno5;
	private String consumo6;
	private String mesAno6;
	private String saldoTotal;
	private String faturasVencidas;
	private String faturasAVencer;
	private String numeroOs;

	public RelatorioEmitirOrdemServicoSeletivaAnaliticoBean(String matricula,
			String inscricao, String cliente, String endereco,
			String localidade, String unidadeResponsavel,
			String numeroHidrometro,String consumoMedio, String consumo1, String mesAno1,
			String consumo2, String mesAno2, String consumo3, String mesAno3,
			String consumo4, String mesAno4, String consumo5, String mesAno5,
			String consumo6, String mesAno6, String saldoTotal,
			String faturasVencidas, String faturasAVencer,
			Collection colecaoCategorias) {
		
		this.matricula = matricula;
		this.inscricao = inscricao;
		this.cliente = cliente;
		this.endereco = endereco;
		this.localidade = localidade;
		this.unidadeResponsavel = unidadeResponsavel;
		this.numeroHidrometro = numeroHidrometro;
		this.consumoMedio = consumoMedio;
		this.consumo1 = consumo1;
		this.mesAno1 = mesAno1;
		this.consumo2 = consumo2;
		this.mesAno2 = mesAno2;
		this.consumo3 = consumo3;
		this.mesAno3 = mesAno3;
		this.consumo4 = consumo4;
		this.mesAno4 = mesAno4;
		this.consumo5 = consumo5;
		this.mesAno5 = mesAno5;
		this.consumo6 = consumo6;
		this.mesAno6 = mesAno6;
		this.saldoTotal = saldoTotal;
		this.faturasVencidas = faturasVencidas;
		this.faturasAVencer = faturasAVencer;
		this.colecaoCategorias = new JRBeanCollectionDataSource(colecaoCategorias);
	}

	public RelatorioEmitirOrdemServicoSeletivaAnaliticoBean() {
		
	}


	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUnidadeResponsavel() {
		return unidadeResponsavel;
	}

	public void setUnidadeResponsavel(String unidadeResponsavel) {
		this.unidadeResponsavel = unidadeResponsavel;
	}

	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public String getConsumo1() {
		return consumo1;
	}

	public void setConsumo1(String consumo1) {
		this.consumo1 = consumo1;
	}

	public String getMesAno1() {
		return mesAno1;
	}

	public void setMesAno1(String mesAno1) {
		this.mesAno1 = mesAno1;
	}

	public String getConsumo2() {
		return consumo2;
	}

	public void setConsumo2(String consumo2) {
		this.consumo2 = consumo2;
	}

	public String getMesAno2() {
		return mesAno2;
	}

	public void setMesAno2(String mesAno2) {
		this.mesAno2 = mesAno2;
	}

	public String getConsumo3() {
		return consumo3;
	}

	public void setConsumo3(String consumo3) {
		this.consumo3 = consumo3;
	}

	public String getMesAno3() {
		return mesAno3;
	}

	public void setMesAno3(String mesAno3) {
		this.mesAno3 = mesAno3;
	}

	public String getConsumo4() {
		return consumo4;
	}

	public void setConsumo4(String consumo4) {
		this.consumo4 = consumo4;
	}

	public String getMesAno4() {
		return mesAno4;
	}

	public void setMesAno4(String mesAno4) {
		this.mesAno4 = mesAno4;
	}

	public String getConsumo5() {
		return consumo5;
	}

	public void setConsumo5(String consumo5) {
		this.consumo5 = consumo5;
	}

	public String getMesAno5() {
		return mesAno5;
	}

	public void setMesAno5(String mesAno5) {
		this.mesAno5 = mesAno5;
	}

	public String getConsumo6() {
		return consumo6;
	}

	public void setConsumo6(String consumo6) {
		this.consumo6 = consumo6;
	}

	public String getMesAno6() {
		return mesAno6;
	}

	public void setMesAno6(String mesAno6) {
		this.mesAno6 = mesAno6;
	}

	public String getSaldoTotal() {
		return saldoTotal;
	}

	public void setSaldoTotal(String saldoTotal) {
		this.saldoTotal = saldoTotal;
	}

	public String getFaturasVencidas() {
		return faturasVencidas;
	}

	public void setFaturasVencidas(String faturasVencidas) {
		this.faturasVencidas = faturasVencidas;
	}

	public String getFaturasAVencer() {
		return faturasAVencer;
	}

	public void setFaturasAVencer(String faturasAVencer) {
		this.faturasAVencer = faturasAVencer;
	}

	public String getConsumoMedio() {
		return consumoMedio;
	}

	public void setConsumoMedio(String consumoMedio) {
		this.consumoMedio = consumoMedio;
	}

	public JRBeanCollectionDataSource getColecaoCategorias() {
		return colecaoCategorias;
	}

	public void setColecaoCategorias(JRBeanCollectionDataSource colecaoCategorias) {
		this.colecaoCategorias = colecaoCategorias;
	}

	public String getNumeroOs() {
		return numeroOs;
	}

	public void setNumeroOs(String numeroOs) {
		this.numeroOs = numeroOs;
	}

}
