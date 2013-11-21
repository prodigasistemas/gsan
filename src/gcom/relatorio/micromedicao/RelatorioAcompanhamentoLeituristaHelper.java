package gcom.relatorio.micromedicao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class RelatorioAcompanhamentoLeituristaHelper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Controle
	private String imprimirCabecalhoAnormalidades;
	
	private String imprimirCabecalhoHorario;
	
	private Integer idRota;
	
	private String indicadorHorario;
	
	// Filtro
	private Integer mesAno;

	private String idGrupoFaturamento;

	private String idEmpresa;

	private String idLeiturista;
	
	private Collection rotas;
	
	private Integer idRotaFluxo;
	

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
	private Collection collectionAnormalidades;

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
	private Collection horariosIndividuais;

	public String getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Collection getCollectionAnormalidades() {
		return collectionAnormalidades;
	}

	public void setCollectionAnormalidades(Collection collectionAnormalidades) {
		this.collectionAnormalidades = collectionAnormalidades;
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

	public Collection getHorariosIndividuais() {
		return horariosIndividuais;
	}

	public void setHorariosIndividuais(Collection horariosIndividuais) {
		this.horariosIndividuais = horariosIndividuais;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
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

	public Integer getMesAno() {
		return mesAno;
	}

	public void setMesAno(Integer mesAno) {
		this.mesAno = mesAno;
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



	public Collection getRotas() {
		return rotas;
	}

	public void setRotas(Collection rotas) {
		this.rotas = rotas;
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

	public Integer getIdRotaFluxo() {
		return idRotaFluxo;
	}

	public void setIdRotaFluxo(Integer idRotaFluxo) {
		this.idRotaFluxo = idRotaFluxo;
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

	public String getIndicadorHorario() {
		return indicadorHorario;
	}

	public void setIndicadorHorario(String indicadorHorario) {
		this.indicadorHorario = indicadorHorario;
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
