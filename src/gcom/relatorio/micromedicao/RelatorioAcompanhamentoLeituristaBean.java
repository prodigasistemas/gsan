package gcom.relatorio.micromedicao;

import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

import java.math.BigDecimal;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioAcompanhamentoLeituristaBean implements RelatorioBean {
	
	// Controle
	
	private Integer idRota;
	
	private String imprimirCabecalhoAnormalidades;
	
	private String imprimirCabecalhoHorario;
	
	// Cabeçalho 
	private String referencia;

	private String idLocalidade;

	private String nomeLocalidade;

	private String grupoFaturamento;

	private String codigoRota;

	private String tipoLeitura;

	private String nomeLeiturista;

	// Resumo Leitura
	private BigDecimal totalLeiturasAgua;

	private BigDecimal totalLeiturasPoco;

	private BigDecimal totalLeiturasAguaExecutadaAbsoluto;

	private BigDecimal totalLeiturasPocoExecutadaAbsoluto;

	private BigDecimal totalLeiturasAguaExecutadaPercentual;

	private BigDecimal totalLeiturasPocoExecutadaPercentual;

	private String dataHoraPrimeiraLeitura;

	private String dataHoraUltimaLeitura;

	private String tempoMedioLeitura;
	
	private String horarioPresumidoIntervalo;

	// Resumo Anormalidade
	private JRBeanCollectionDataSource collectionAnormalidades;

	// Resumo Faturamento
	private BigDecimal faturadoMediaAbsoluto;

	private BigDecimal faturadoMediaPercentual;

	private BigDecimal faturadoMinimoAbsoluto;

	private BigDecimal faturadoMinimoPercentual;

	private BigDecimal consumoTotalMedido;

	private BigDecimal consumoTotalFaturado;
	
	private BigDecimal consumoTotalFaturadoPercentual;

	private BigDecimal consumoMedioMedido;

	private BigDecimal consumoMedioFaturado;

	// Horarios Individuais
	private JRBeanCollectionDataSource horariosIndividuais;

	
	
	public String getCodigoRota() {
		return codigoRota;
	}



	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}







	public BigDecimal getConsumoMedioFaturado() {
		return consumoMedioFaturado;
	}



	public void setConsumoMedioFaturado(BigDecimal consumoMedioFaturado) {
		this.consumoMedioFaturado = consumoMedioFaturado;
	}



	public BigDecimal getConsumoMedioMedido() {
		return consumoMedioMedido;
	}



	public void setConsumoMedioMedido(BigDecimal consumoMedioMedido) {
		this.consumoMedioMedido = consumoMedioMedido;
	}



	public BigDecimal getConsumoTotalFaturado() {
		return consumoTotalFaturado;
	}



	public void setConsumoTotalFaturado(BigDecimal consumoTotalFaturado) {
		this.consumoTotalFaturado = consumoTotalFaturado;
	}



	public BigDecimal getConsumoTotalMedido() {
		return consumoTotalMedido;
	}



	public void setConsumoTotalMedido(BigDecimal consumoTotalMedido) {
		this.consumoTotalMedido = consumoTotalMedido;
	}



	public String getDataHoraPrimeiraLeitura() {
		return dataHoraPrimeiraLeitura;
	}



	public void setDataHoraPrimeiraLeitura(String dataHoraPrimeiraLeitura) {
		this.dataHoraPrimeiraLeitura = dataHoraPrimeiraLeitura;
	}



	public String getDataHoraUltimaLeitura() {
		return dataHoraUltimaLeitura;
	}



	public void setDataHoraUltimaLeitura(String dataHoraUltimaLeitura) {
		this.dataHoraUltimaLeitura = dataHoraUltimaLeitura;
	}



	public BigDecimal getFaturadoMediaAbsoluto() {
		return faturadoMediaAbsoluto;
	}



	public void setFaturadoMediaAbsoluto(BigDecimal faturadoMediaAbsoluto) {
		this.faturadoMediaAbsoluto = faturadoMediaAbsoluto;
	}



	public BigDecimal getFaturadoMediaPercentual() {
		return faturadoMediaPercentual;
	}



	public void setFaturadoMediaPercentual(BigDecimal faturadoMediaPercentual) {
		this.faturadoMediaPercentual = faturadoMediaPercentual;
	}



	public BigDecimal getFaturadoMinimoAbsoluto() {
		return faturadoMinimoAbsoluto;
	}



	public void setFaturadoMinimoAbsoluto(BigDecimal faturadoMinimoAbsoluto) {
		this.faturadoMinimoAbsoluto = faturadoMinimoAbsoluto;
	}



	public BigDecimal getFaturadoMinimoPercentual() {
		return faturadoMinimoPercentual;
	}



	public void setFaturadoMinimoPercentual(BigDecimal faturadoMinimoPercentual) {
		this.faturadoMinimoPercentual = faturadoMinimoPercentual;
	}



	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}



	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}



	public JRBeanCollectionDataSource getCollectionAnormalidades() {
		return collectionAnormalidades;
	}



	public void setCollectionAnormalidades(
			JRBeanCollectionDataSource collectionAnormalidades) {
		this.collectionAnormalidades = collectionAnormalidades;
	}



	public JRBeanCollectionDataSource getHorariosIndividuais() {
		return horariosIndividuais;
	}



	public void setHorariosIndividuais(
			JRBeanCollectionDataSource horariosIndividuais) {
		this.horariosIndividuais = horariosIndividuais;
	}



	public String getIdLocalidade() {
		return idLocalidade;
	}



	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
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



	public String getReferencia() {
		return referencia;
	}



	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}



	public String getTempoMedioLeitura() {
		return tempoMedioLeitura;
	}



	public void setTempoMedioLeitura(String tempoMedioLeitura) {
		this.tempoMedioLeitura = tempoMedioLeitura;
	}



	public String getTipoLeitura() {
		return tipoLeitura;
	}



	public void setTipoLeitura(String tipoLeitura) {
		this.tipoLeitura = tipoLeitura;
	}



	public BigDecimal getTotalLeiturasAgua() {
		return totalLeiturasAgua;
	}



	public void setTotalLeiturasAgua(BigDecimal totalLeiturasAgua) {
		this.totalLeiturasAgua = totalLeiturasAgua;
	}



	public BigDecimal getTotalLeiturasAguaExecutadaAbsoluto() {
		return totalLeiturasAguaExecutadaAbsoluto;
	}



	public void setTotalLeiturasAguaExecutadaAbsoluto(
			BigDecimal totalLeiturasAguaExecutadaAbsoluto) {
		this.totalLeiturasAguaExecutadaAbsoluto = totalLeiturasAguaExecutadaAbsoluto;
	}



	public BigDecimal getTotalLeiturasAguaExecutadaPercentual() {
		return totalLeiturasAguaExecutadaPercentual;
	}



	public void setTotalLeiturasAguaExecutadaPercentual(
			BigDecimal totalLeiturasAguaExecutadaPercentual) {
		this.totalLeiturasAguaExecutadaPercentual = totalLeiturasAguaExecutadaPercentual;
	}



	public BigDecimal getTotalLeiturasPoco() {
		return totalLeiturasPoco;
	}



	public void setTotalLeiturasPoco(BigDecimal totalLeiturasPoco) {
		this.totalLeiturasPoco = totalLeiturasPoco;
	}



	public BigDecimal getTotalLeiturasPocoExecutadaAbsoluto() {
		return totalLeiturasPocoExecutadaAbsoluto;
	}



	public void setTotalLeiturasPocoExecutadaAbsoluto(
			BigDecimal totalLeiturasPocoExecutadaAbsoluto) {
		this.totalLeiturasPocoExecutadaAbsoluto = totalLeiturasPocoExecutadaAbsoluto;
	}



	public BigDecimal getTotalLeiturasPocoExecutadaPercentual() {
		return totalLeiturasPocoExecutadaPercentual;
	}



	public void setTotalLeiturasPocoExecutadaPercentual(
			BigDecimal totalLeiturasPocoExecutadaPercentual) {
		this.totalLeiturasPocoExecutadaPercentual = totalLeiturasPocoExecutadaPercentual;
	}



	public RelatorioAcompanhamentoLeituristaBean(RelatorioAcompanhamentoLeituristaHelper helper){

		this.referencia = Util.formatarAnoMesParaMesAno(helper.getMesAno());
		this.idLocalidade = helper.getIdLocalidade();
		this.nomeLocalidade = helper.getNomeLocalidade();
		this.grupoFaturamento = helper.getIdGrupoFaturamento();
		this.codigoRota = helper.getCodigoRota();
		this.tipoLeitura = helper.getTipoLeitura();
		this.nomeLeiturista = helper.getNomeLeiturista();
		this.totalLeiturasAgua = helper.getTotalLeiturasAgua();
		this.totalLeiturasPoco = helper.getTotalLeiturasPoco();
		this.totalLeiturasAguaExecutadaAbsoluto = helper.getTotalLeiturasAguaExecutadaAbsoluto();
		this.totalLeiturasPocoExecutadaAbsoluto = helper.getTotalLeiturasPocoExecutadaAbsoluto();
		this.totalLeiturasAguaExecutadaPercentual = helper.getTotalLeiturasAguaExecutadaPercentual();
		this.totalLeiturasPocoExecutadaPercentual = helper.getTotalLeiturasPocoExecutadaPercentual();
		this.dataHoraPrimeiraLeitura = helper.getDataHoraPrimeiraLeitura();
		this.dataHoraUltimaLeitura = helper.getDataHoraUltimaLeitura();
		this.tempoMedioLeitura = helper.getTempoMedioLeitura();
		this.collectionAnormalidades = new JRBeanCollectionDataSource(helper.getCollectionAnormalidades());
		this.faturadoMediaAbsoluto = helper.getFaturadoMediaAbsoluto();
		this.faturadoMediaPercentual = helper.getFaturadoMediaPercentual();
		this.faturadoMinimoAbsoluto = helper.getFaturadoMinimoAbsoluto();
		this.faturadoMinimoPercentual = helper.getFaturadoMinimoPercentual();
		this.consumoTotalMedido = helper.getConsumoTotalMedido();
		this.consumoTotalFaturado = helper.getConsumoTotalFaturado();
		this.consumoMedioMedido = helper.getConsumoMedioMedido();
		this.consumoMedioFaturado = helper.getConsumoMedioFaturado();
		this.horariosIndividuais = new JRBeanCollectionDataSource(helper.getHorariosIndividuais());
		this.idRota = helper.getIdRota();
		this.consumoTotalFaturadoPercentual = helper.getConsumoTotalFaturadoPercentual();
		this.horarioPresumidoIntervalo = helper.getHorarioPresumidoIntervalo();
		this.imprimirCabecalhoAnormalidades = helper.getImprimirCabecalhoAnormalidades();
		this.imprimirCabecalhoHorario = helper.getImprimirCabecalhoHorario();
	}

	public Integer getIdRota() {
		return idRota;
	}



	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}



	public BigDecimal getConsumoTotalFaturadoPercentual() {
		return consumoTotalFaturadoPercentual;
	}



	public void setConsumoTotalFaturadoPercentual(
			BigDecimal consumoTotalFaturadoPercentual) {
		this.consumoTotalFaturadoPercentual = consumoTotalFaturadoPercentual;
	}



	public String getHorarioPresumidoIntervalo() {
		return horarioPresumidoIntervalo;
	}



	public void setHorarioPresumidoIntervalo(String horarioPresumidoIntervalo) {
		this.horarioPresumidoIntervalo = horarioPresumidoIntervalo;
	}



	public String getImprimirCabecalhoAnormalidades() {
		return imprimirCabecalhoAnormalidades;
	}



	public void setImprimirCabecalhoAnormalidades(
			String imprimirCabecalhoAnormalidades) {
		this.imprimirCabecalhoAnormalidades = imprimirCabecalhoAnormalidades;
	}



	public String getImprimirCabecalhoHorario() {
		return imprimirCabecalhoHorario;
	}



	public void setImprimirCabecalhoHorario(String imprimirCabecalhoHorario) {
		this.imprimirCabecalhoHorario = imprimirCabecalhoHorario;
	}


}
