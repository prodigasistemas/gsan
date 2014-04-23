package gcom.cadastro.imovel.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * @author Hibernate CodeGenerator
 * @created 1 de Junho de 2004
 */
public class ImovelRelatorioHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String[] subcategoriaQtdEconomia;

	private Collection subcategorias;

	private Collection clienteUsuarioResponsavel;

	// private Collection<ClienteImovel> clientesImovel;

	private Integer matriculaImovel;

	private Integer quantidadeEconomia;

	private Integer matriculaImovelPrincipal;

	private Integer idGerenciaRegional;

	private String descricaoGerenciaRegional;

	private Integer idLocalidade;

	private String descricaoLocalidade;

	private Integer codigoSetorComercial;

	private String descricaoSetorComercial;

	private Integer idMunicipio;

	private String nomeMunicipio;

	private String tipoLogradouro;

	private String tituloLogradouro;
	
	private String idLogradouro;

	private String nomeLogradouro;

	private Integer cepCodigo;

	private String cepFormatado;

	private String cepLogradouro;

	private String cepBairro;

	private String cepMunicipio;

	private String cepTipoLogradouro;

	private String cepSigla;

	private Integer idBairro;

	private String nomeBairro;

	private Integer numeroQuadra;

	private String descricaoQuadra;

	private short numeroLote;

	private short numeroSubLote;

	private short indicadorImovelCondominio;

	private String indicadorImovelCondominioDescricao;

	private Integer matriculaImovelCondominio;

	private String perfilImovel;

	private String ligacaoAguaSituacao;

	private String ligacaoEsgotoSituacao;

	private String tipoPavimentoRua;

	private String tipoPavimentoCalcada;

	private String tipoDespejo;

	private BigDecimal volumeReservatorioSuperior;

	private BigDecimal volumeReservatorioInferior;

	private BigDecimal volumePiscina;

	private Integer consumoMedioImovel;

	private Short areaConstruida;
	
	private BigDecimal areaConstruidaImovel;

	private short numeroMoradores;

	private short numeroPontosUtilzacao;

	private String descricaoTipoPoco;

	private Date dataLigacaoAgua;

	private String diametroLigacaoAgua;

	private String materialLigacaoAgua;

	private Integer consumoMinimoFixadoAgua;

	private Date dataLigacaoEsgoto;

	private String diametroLigacaoEsgoto;

	private String materialLigacaoEsgoto;

	private Integer consumoMinimoFixadoLigacaoEsgoto;

	private BigDecimal percentualAguaConsumidaColetada;

	private BigDecimal percentual;

	private short consumoMinimoFixadoEsgoto;

	private String numeroHidrometroAgua;

	private short anoFabricaocaHidrometroAgua;

	private String capacidadeHidrometroAgua;

	private String marcaHidrometroAgua;

	private String diametroHidrometroAgua;

	private String tipoHidrometroAgua;

	private Date dataInstalacaoHidrometroAgua;

	private String localInstalacaoHidrometroAgua;

	private String tipoProtecaoHidrometroAgua;

	private short indicadorExistenciaCavaleteAgua;

	private String indicadorExistenciaCavaleteAguaDescricao;

	private String numeroHidrometroPoco;

	private short anoFabricacaoHidrometroPoco;

	private String capacidadeHidrometroPoco;

	private String marcaHidrometroPoco;

	private String diametroHidrometroPoco;

	private String tipoHidrometroPoco;

	private Date dataInstalacaoHidrometroPoco;

	private String localInstalacaoHidrometroPoco;

	private String tipoProtecaoHidrometroPoco;

	private short indicadorExistenciaCavaletePoco;

	private String indicadorExistenciaCavaletePocoDescricao;

	private Integer clienteUsuarioId;

	private String clienteUsuarioNome;

	private Integer clienteId;

	private String clienteNome;

	private Integer clienteResponsavelId;

	private String clienteResponsavelNome;

	private String clienteRelacaoTipo;

	private Date dataImplantacao;

	private Date dataExclusao;

	private String motivoExclusao;

	private Date ultimoRecadastramento;

	private Short numeroRecadastramento;

	private Long numeroCartaoTarifaSocial;

	private String tipoCartaoTarifaSocial;

	private Date validadeCartao;

	private Short numeroMesesAdesao;

	private Integer consumoCelpe;

	private BigDecimal valorRendaFamiliar;

	private String rendaTipo;

	private BigDecimal numeroIptu;

	private Long numeroCelpe;

	private Collection tarifasEconomias;

	private String clienteCpf;

	private Collection clientes;

	private String clienteRg;

	private Date clienteDataEmissaoOrgaoRg;

	private String clienteEmissaoOrgaoRg;

	private String clienteUf;

	private String inscricaoImovel;

	private String enderecoReferencia;

	private String numeroImovel;

	private String complementoImovel;

	private String unidadeFederacao;

	private Integer areaConstruidaMenorFaixa;

	private Integer areaConstruidaMaiorFaixa;

	private BigDecimal volumeReservatorioSuperiorMenorFaixa;

	private BigDecimal volumeReservatorioSuperiorMaiorFaixa;

	private BigDecimal volumeReservatorioInferiorMenorFaixa;

	private BigDecimal volumeReservatorioInferiorMaiorFaixa;

	private BigDecimal volumePiscinaMenorFaixa;

	private BigDecimal volumePiscinaMaiorFaixa;
	
	private Integer idUnidadeNegocio;
	
	private String nomeUnidadeNegocio;
	
	private String jardim;
	
	private String endereco;
	
	private Short codigoRota;
	
	private Integer numeroSequencialRota;
	
	private Integer indicadorExistenciaHidrometro;
	
	private String descricaoAbreviadaCategoria;
	
	private String tipoFaturamento;
	
	private String fone;
	
	private Short testadaLote;
	
	
	public String getTipoFaturamento() {
		return tipoFaturamento;
	}

	public void setTipoFaturamento(String tipoFaturamento) {
		this.tipoFaturamento = tipoFaturamento;
	}

	public String getComplementoImovel() {
		return complementoImovel;
	}

	public void setComplementoImovel(String complementoImovel) {
		this.complementoImovel = complementoImovel;
	}

	public String getNumeroImovel() {
		return numeroImovel;
	}

	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}

	public String getEnderecoReferencia() {
		return enderecoReferencia;
	}

	public void setEnderecoReferencia(String enderecoReferencia) {
		this.enderecoReferencia = enderecoReferencia;
	}

	public Date getClienteDataEmissaoOrgaoRg() {
		return clienteDataEmissaoOrgaoRg;
	}

	public void setClienteDataEmissaoOrgaoRg(Date clienteDataEmissaoOrgaoRg) {
		this.clienteDataEmissaoOrgaoRg = clienteDataEmissaoOrgaoRg;
	}

	public String getClienteEmissaoOrgaoRg() {
		return clienteEmissaoOrgaoRg;
	}

	public void setClienteEmissaoOrgaoRg(String clienteEmissaoOrgaoRg) {
		this.clienteEmissaoOrgaoRg = clienteEmissaoOrgaoRg;
	}

	public String getClienteRg() {
		return clienteRg;
	}

	public void setClienteRg(String clienteRg) {
		this.clienteRg = clienteRg;
	}

	public String getClienteUf() {
		return clienteUf;
	}

	public void setClienteUf(String clienteUf) {
		this.clienteUf = clienteUf;
	}

	public Collection getClientes() {
		return clientes;
	}

	public void setClientes(Collection clientes) {
		this.clientes = clientes;
	}

	public String getClienteCpf() {
		return clienteCpf;
	}

	public void setClienteCpf(String clienteCpf) {
		this.clienteCpf = clienteCpf;
	}

	public Collection getTarifasEconomias() {
		return tarifasEconomias;
	}

	public void setTarifasEconomias(Collection tarifasEconomias) {
		this.tarifasEconomias = tarifasEconomias;
	}

	public Integer getConsumoCelpe() {
		return consumoCelpe;
	}

	public void setConsumoCelpe(Integer consumoCelpe) {
		this.consumoCelpe = consumoCelpe;
	}

	public Long getNumeroCartaoTarifaSocial() {
		return numeroCartaoTarifaSocial;
	}

	public void setNumeroCartaoTarifaSocial(Long numeroCartaoTarifaSocial) {
		this.numeroCartaoTarifaSocial = numeroCartaoTarifaSocial;
	}

	public Long getNumeroCelpe() {
		return numeroCelpe;
	}

	public void setNumeroCelpe(Long numeroCelpe) {
		this.numeroCelpe = numeroCelpe;
	}

	public BigDecimal getNumeroIptu() {
		return numeroIptu;
	}

	public void setNumeroIptu(BigDecimal numeroIptu) {
		this.numeroIptu = numeroIptu;
	}

	public Short getNumeroMesesAdesao() {
		return numeroMesesAdesao;
	}

	public void setNumeroMesesAdesao(Short numeroMesesAdesao) {
		this.numeroMesesAdesao = numeroMesesAdesao;
	}

	public String getRendaTipo() {
		return rendaTipo;
	}

	public void setRendaTipo(String rendaTipo) {
		this.rendaTipo = rendaTipo;
	}

	public String getTipoCartaoTarifaSocial() {
		return tipoCartaoTarifaSocial;
	}

	public void setTipoCartaoTarifaSocial(String tipoCartaoTarifaSocial) {
		this.tipoCartaoTarifaSocial = tipoCartaoTarifaSocial;
	}

	public Date getValidadeCartao() {
		return validadeCartao;
	}

	public void setValidadeCartao(Date validadeCartao) {
		this.validadeCartao = validadeCartao;
	}

	public BigDecimal getValorRendaFamiliar() {
		return valorRendaFamiliar;
	}

	public void setValorRendaFamiliar(BigDecimal valorRendaFamiliar) {
		this.valorRendaFamiliar = valorRendaFamiliar;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public Date getDataImplantacao() {
		return dataImplantacao;
	}

	public void setDataImplantacao(Date dataImplantacao) {
		this.dataImplantacao = dataImplantacao;
	}

	public String getMotivoExclusao() {
		return motivoExclusao;
	}

	public void setMotivoExclusao(String motivoExclusao) {
		this.motivoExclusao = motivoExclusao;
	}

	public Short getNumeroRecadastramento() {
		return numeroRecadastramento;
	}

	public void setNumeroRecadastramento(Short numeroRecadastramento) {
		this.numeroRecadastramento = numeroRecadastramento;
	}

	public Date getUltimoRecadastramento() {
		return ultimoRecadastramento;
	}

	public void setUltimoRecadastramento(Date ultimoRecadastramento) {
		this.ultimoRecadastramento = ultimoRecadastramento;
	}

	public Integer getClienteResponsavelId() {
		return clienteResponsavelId;
	}

	public void setClienteResponsavelId(Integer clienteResponsavelId) {
		this.clienteResponsavelId = clienteResponsavelId;
	}

	public String getClienteResponsavelNome() {
		return clienteResponsavelNome;
	}

	public void setClienteResponsavelNome(String clienteResponsavelNome) {
		this.clienteResponsavelNome = clienteResponsavelNome;
	}

	public Integer getClienteUsuarioId() {
		return clienteUsuarioId;
	}

	public void setClienteUsuarioId(Integer clienteUsuarioId) {
		this.clienteUsuarioId = clienteUsuarioId;
	}

	public String getClienteUsuarioNome() {
		return clienteUsuarioNome;
	}

	public void setClienteUsuarioNome(String clienteUsuarioNome) {
		this.clienteUsuarioNome = clienteUsuarioNome;
	}

	public String getClienteRelacaoTipo() {
		return clienteRelacaoTipo;
	}

	public void setClienteRelacaoTipo(String clienteRelacaoTipo) {
		this.clienteRelacaoTipo = clienteRelacaoTipo;
	}

	public ImovelRelatorioHelper() {
	}

	/*
	 * public boolean equals(Object other) { if ((this == other)) { return true; }
	 * if (!(other instanceof ImovelMicromedicao)) { return false; }
	 * ImovelMicromedicao castOther = (ImovelMicromedicao) other;
	 * 
	 * return (this.getImovel().getId().equals(castOther.getImovel().getId())); }
	 */

	public short getAnoFabricacaoHidrometroPoco() {
		return anoFabricacaoHidrometroPoco;
	}

	public void setAnoFabricacaoHidrometroPoco(short anoFabricacaoHidrometroPoco) {
		this.anoFabricacaoHidrometroPoco = anoFabricacaoHidrometroPoco;
	}

	public short getAnoFabricaocaHidrometroAgua() {
		return anoFabricaocaHidrometroAgua;
	}

	public void setAnoFabricaocaHidrometroAgua(short anoFabricaocaHidrometroAgua) {
		this.anoFabricaocaHidrometroAgua = anoFabricaocaHidrometroAgua;
	}

	public Short getAreaConstruida() {
		return areaConstruida;
	}

	public void setAreaConstruida(Short areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	public String getCapacidadeHidrometroAgua() {
		return capacidadeHidrometroAgua;
	}

	public void setCapacidadeHidrometroAgua(String capacidadeHidrometroAgua) {
		this.capacidadeHidrometroAgua = capacidadeHidrometroAgua;
	}

	public String getCapacidadeHidrometroPoco() {
		return capacidadeHidrometroPoco;
	}

	public void setCapacidadeHidrometroPoco(String capacidadeHidrometroPoco) {
		this.capacidadeHidrometroPoco = capacidadeHidrometroPoco;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getConsumoMedioImovel() {
		return consumoMedioImovel;
	}

	public void setConsumoMedioImovel(Integer consumoMedioImovel) {
		this.consumoMedioImovel = consumoMedioImovel;
	}

	public Integer getConsumoMinimoFixadoAgua() {
		return consumoMinimoFixadoAgua;
	}

	public void setConsumoMinimoFixadoAgua(Integer consumoMinimoFixadoAgua) {
		this.consumoMinimoFixadoAgua = consumoMinimoFixadoAgua;
	}

	public short getConsumoMinimoFixadoEsgoto() {
		return consumoMinimoFixadoEsgoto;
	}

	public void setConsumoMinimoFixadoEsgoto(short consumoMinimoFixadoEsgoto) {
		this.consumoMinimoFixadoEsgoto = consumoMinimoFixadoEsgoto;
	}

	public Integer getConsumoMinimoFixadoLigacaoEsgoto() {
		return consumoMinimoFixadoLigacaoEsgoto;
	}

	public void setConsumoMinimoFixadoLigacaoEsgoto(
			Integer consumoMinimoFixadoLigacaoEsgoto) {
		this.consumoMinimoFixadoLigacaoEsgoto = consumoMinimoFixadoLigacaoEsgoto;
	}

	public Date getDataInstalacaoHidrometroAgua() {
		return dataInstalacaoHidrometroAgua;
	}

	public void setDataInstalacaoHidrometroAgua(
			Date dataInstalacaoHidrometroAgua) {
		this.dataInstalacaoHidrometroAgua = dataInstalacaoHidrometroAgua;
	}

	public Date getDataInstalacaoHidrometroPoco() {
		return dataInstalacaoHidrometroPoco;
	}

	public void setDataInstalacaoHidrometroPoco(
			Date dataInstalacaoHidrometroPoco) {
		this.dataInstalacaoHidrometroPoco = dataInstalacaoHidrometroPoco;
	}

	public Date getDataLigacaoAgua() {
		return dataLigacaoAgua;
	}

	public void setDataLigacaoAgua(Date dataLigacaoAgua) {
		this.dataLigacaoAgua = dataLigacaoAgua;
	}

	public Date getDataLigacaoEsgoto() {
		return dataLigacaoEsgoto;
	}

	public void setDataLigacaoEsgoto(Date dataLigacaoEsgoto) {
		this.dataLigacaoEsgoto = dataLigacaoEsgoto;
	}

	public String getDescricaoGerenciaRegional() {
		return descricaoGerenciaRegional;
	}

	public void setDescricaoGerenciaRegional(String descricaoGerenciaRegional) {
		this.descricaoGerenciaRegional = descricaoGerenciaRegional;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getDescricaoQuadra() {
		return descricaoQuadra;
	}

	public void setDescricaoQuadra(String descricaoQuadra) {
		this.descricaoQuadra = descricaoQuadra;
	}

	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}

	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}

	public String getDescricaoTipoPoco() {
		return descricaoTipoPoco;
	}

	public void setDescricaoTipoPoco(String descricaoTipoPoco) {
		this.descricaoTipoPoco = descricaoTipoPoco;
	}

	public String getDiametroHidrometroAgua() {
		return diametroHidrometroAgua;
	}

	public void setDiametroHidrometroAgua(String diametroHidrometroAgua) {
		this.diametroHidrometroAgua = diametroHidrometroAgua;
	}

	public String getDiametroHidrometroPoco() {
		return diametroHidrometroPoco;
	}

	public void setDiametroHidrometroPoco(String diametroHidrometroPoco) {
		this.diametroHidrometroPoco = diametroHidrometroPoco;
	}

	public String getDiametroLigacaoAgua() {
		return diametroLigacaoAgua;
	}

	public String getLocalInstalacaoHidrometroPoco() {
		return localInstalacaoHidrometroPoco;
	}

	public void setLocalInstalacaoHidrometroPoco(
			String localInstalacaoHidrometroPoco) {
		this.localInstalacaoHidrometroPoco = localInstalacaoHidrometroPoco;
	}

	public void setDiametroLigacaoAgua(String diametroLigacaoAgua) {
		this.diametroLigacaoAgua = diametroLigacaoAgua;
	}

	public String getDiametroLigacaoEsgoto() {
		return diametroLigacaoEsgoto;
	}

	public void setDiametroLigacaoEsgoto(String diametroLigacaoEsgoto) {
		this.diametroLigacaoEsgoto = diametroLigacaoEsgoto;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public short getIndicadorExistenciaCavaleteAgua() {
		return indicadorExistenciaCavaleteAgua;
	}

	public void setIndicadorExistenciaCavaleteAgua(
			short indicadorExistenciaCavaleteAgua) {
		this.indicadorExistenciaCavaleteAgua = indicadorExistenciaCavaleteAgua;
	}

	public short getIndicadorExistenciaCavaletePoco() {
		return indicadorExistenciaCavaletePoco;
	}

	public void setIndicadorExistenciaCavaletePoco(
			short indicadorExistenciaCavaletePoco) {
		this.indicadorExistenciaCavaletePoco = indicadorExistenciaCavaletePoco;
	}

	public short getIndicadorImovelCondominio() {
		return indicadorImovelCondominio;
	}

	public void setIndicadorImovelCondominio(short indicadorImovelCondominio) {
		this.indicadorImovelCondominio = indicadorImovelCondominio;
	}

	public String getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(String ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public String getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(String ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public String getLocalInstalacaoHidrometroAgua() {
		return localInstalacaoHidrometroAgua;
	}

	public void setLocalInstalacaoHidrometroAgua(
			String localInstalacaoHidrometroAgua) {
		this.localInstalacaoHidrometroAgua = localInstalacaoHidrometroAgua;
	}

	public String getLocalIstalacaoHidrometroPoco() {
		return localInstalacaoHidrometroPoco;
	}

	public void setLocalIstalacaoHidrometroPoco(
			String localIstalacaoHidrometroPoco) {
		this.localInstalacaoHidrometroPoco = localIstalacaoHidrometroPoco;
	}

	public String getMarcaHidrometroAgua() {
		return marcaHidrometroAgua;
	}

	public void setMarcaHidrometroAgua(String marcaHidrometroAgua) {
		this.marcaHidrometroAgua = marcaHidrometroAgua;
	}

	public String getMarcaHidrometroPoco() {
		return marcaHidrometroPoco;
	}

	public void setMarcaHidrometroPoco(String marcaHidrometroPoco) {
		this.marcaHidrometroPoco = marcaHidrometroPoco;
	}

	public String getMaterialLigacaoAgua() {
		return materialLigacaoAgua;
	}

	public void setMaterialLigacaoAgua(String materialLigacaoAgua) {
		this.materialLigacaoAgua = materialLigacaoAgua;
	}

	public String getMaterialLigacaoEsgoto() {
		return materialLigacaoEsgoto;
	}

	public void setMaterialLigacaoEsgoto(String materialLigacaoEsgoto) {
		this.materialLigacaoEsgoto = materialLigacaoEsgoto;
	}

	public Integer getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(Integer matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public Integer getQuantidadeEconomia() {
		return quantidadeEconomia;
	}

	public void setQuantidadeEconomia(Integer quantidadeEconomia) {
		this.quantidadeEconomia = quantidadeEconomia;
	}

	public Integer getMatriculaImovelCondominio() {
		return matriculaImovelCondominio;
	}

	public void setMatriculaImovelCondominio(Integer matriculaImovelCondominio) {
		this.matriculaImovelCondominio = matriculaImovelCondominio;
	}

	public String getNumeroHidrometroAgua() {
		return numeroHidrometroAgua;
	}

	public void setNumeroHidrometroAgua(String numeroHidrometroAgua) {
		this.numeroHidrometroAgua = numeroHidrometroAgua;
	}

	public String getNumeroHidrometroPoco() {
		return numeroHidrometroPoco;
	}

	public void setNumeroHidrometroPoco(String numeroHidrometroPoco) {
		this.numeroHidrometroPoco = numeroHidrometroPoco;
	}

	public short getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(short numeroLote) {
		this.numeroLote = numeroLote;
	}

	public short getNumeroMoradores() {
		return numeroMoradores;
	}

	public void setNumeroMoradores(short numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}

	public short getNumeroPontosUtilzacao() {
		return numeroPontosUtilzacao;
	}

	public void setNumeroPontosUtilzacao(short numeroPontosUtilzacao) {
		this.numeroPontosUtilzacao = numeroPontosUtilzacao;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public short getNumeroSubLote() {
		return numeroSubLote;
	}

	public void setNumeroSubLote(short numeroSubLote) {
		this.numeroSubLote = numeroSubLote;
	}

	public BigDecimal getPercentual() {
		return percentual;
	}

	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}

	public BigDecimal getPercentualAguaConsumidaColetada() {
		return percentualAguaConsumidaColetada;
	}

	public void setPercentualAguaConsumidaColetada(
			BigDecimal percentualAguaConsumidaColetada) {
		this.percentualAguaConsumidaColetada = percentualAguaConsumidaColetada;
	}

	public String getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public String getTipoDespejo() {
		return tipoDespejo;
	}

	public void setTipoDespejo(String tipoDespejo) {
		this.tipoDespejo = tipoDespejo;
	}

	public String getTipoHidrometroAgua() {
		return tipoHidrometroAgua;
	}

	public void setTipoHidrometroAgua(String tipoHidrometroAgua) {
		this.tipoHidrometroAgua = tipoHidrometroAgua;
	}

	public String getTipoHidrometroPoco() {
		return tipoHidrometroPoco;
	}

	public void setTipoHidrometroPoco(String tipoHidrometroPoco) {
		this.tipoHidrometroPoco = tipoHidrometroPoco;
	}

	public String getTipoPavimentoCalcada() {
		return tipoPavimentoCalcada;
	}

	public void setTipoPavimentoCalcada(String tipoPavimentoCalcada) {
		this.tipoPavimentoCalcada = tipoPavimentoCalcada;
	}

	public String getTipoPavimentoRua() {
		return tipoPavimentoRua;
	}

	public void setTipoPavimentoRua(String tipoPavimentoRua) {
		this.tipoPavimentoRua = tipoPavimentoRua;
	}

	public String getTipoProtecaoHidrometroAgua() {
		return tipoProtecaoHidrometroAgua;
	}

	public void setTipoProtecaoHidrometroAgua(String tipoProtecaoHidrometroAgua) {
		this.tipoProtecaoHidrometroAgua = tipoProtecaoHidrometroAgua;
	}

	public String getTipoProtecaoHidrometroPoco() {
		return tipoProtecaoHidrometroPoco;
	}

	public void setTipoProtecaoHidrometroPoco(String tipoProtecaoHidrometroPoco) {
		this.tipoProtecaoHidrometroPoco = tipoProtecaoHidrometroPoco;
	}

	public BigDecimal getVolumePiscina() {
		return volumePiscina;
	}

	public void setVolumePiscina(BigDecimal volumePiscina) {
		this.volumePiscina = volumePiscina;
	}

	public BigDecimal getVolumeReservatorioInferior() {
		return volumeReservatorioInferior;
	}

	public void setVolumeReservatorioInferior(
			BigDecimal volumeReservatorioInferior) {
		this.volumeReservatorioInferior = volumeReservatorioInferior;
	}

	public BigDecimal getVolumeReservatorioSuperior() {
		return volumeReservatorioSuperior;
	}

	public void setVolumeReservatorioSuperior(
			BigDecimal volumeReservatorioSuperior) {
		this.volumeReservatorioSuperior = volumeReservatorioSuperior;
	}

	public Integer getMatriculaImovelPrincipal() {
		return matriculaImovelPrincipal;
	}

	public void setMatriculaImovelPrincipal(Integer matriculaImovelPrincipal) {
		this.matriculaImovelPrincipal = matriculaImovelPrincipal;
	}

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public Integer getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(Integer idBairro) {
		this.idBairro = idBairro;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getTituloLogradouro() {
		return tituloLogradouro;
	}

	public void setTituloLogradouro(String tituloLogradouro) {
		this.tituloLogradouro = tituloLogradouro;
	}

	public String getCepBairro() {
		return cepBairro;
	}

	public void setCepBairro(String cepBairro) {
		this.cepBairro = cepBairro;
	}

	public Integer getCepCodigo() {
		return cepCodigo;
	}

	public void setCepCodigo(Integer cepCodigo) {
		this.cepCodigo = cepCodigo;
	}

	public String getCepLogradouro() {
		return cepLogradouro;
	}

	public void setCepLogradouro(String cepLogradouro) {
		this.cepLogradouro = cepLogradouro;
	}

	public String getCepMunicipio() {
		return cepMunicipio;
	}

	public void setCepMunicipio(String cepMunicipio) {
		this.cepMunicipio = cepMunicipio;
	}

	public String getCepSigla() {
		return cepSigla;
	}

	public void setCepSigla(String cepSigla) {
		this.cepSigla = cepSigla;
	}

	public String getCepTipoLogradouro() {
		return cepTipoLogradouro;
	}

	public void setCepTipoLogradouro(String cepTipoLogradouro) {
		this.cepTipoLogradouro = cepTipoLogradouro;
	}

	public String[] getSubcategoriaQtdEconomia() {
		return subcategoriaQtdEconomia;
	}

	public void setSubcategoriaQtdEconomia(String[] subcategoriaQtdEconomia) {
		this.subcategoriaQtdEconomia = subcategoriaQtdEconomia;
	}

	public String getEnderecoFormatado() {

		String retorno = "";
		if (this.nomeLogradouro != null
				&& !this.nomeLogradouro.trim().equalsIgnoreCase("")) {
			if (this.tipoLogradouro != null
					&& !this.tipoLogradouro.trim().equalsIgnoreCase("")) {
				retorno = this.tipoLogradouro.trim();
			}
			if (this.tituloLogradouro != null
					&& !this.tituloLogradouro.trim().equalsIgnoreCase("")) {
				retorno = retorno + " " + this.tituloLogradouro.trim();
			}
			retorno = retorno + " " + this.nomeLogradouro.trim();

			if (this.enderecoReferencia != null
					&& !this.enderecoReferencia.trim().equalsIgnoreCase("")) {
				retorno = retorno + " - " + this.enderecoReferencia.trim();
			}

			if (this.numeroImovel != null
					&& !this.numeroImovel.trim().equalsIgnoreCase("")) {
				retorno = retorno + " - " + this.numeroImovel.trim();
			}

			if (this.complementoImovel != null
					&& !this.complementoImovel.trim().equalsIgnoreCase("")) {
				retorno = retorno + " - " + this.complementoImovel.trim();
			}

			if (this.nomeBairro != null
					&& !this.nomeBairro.trim().equalsIgnoreCase("")) {
				retorno = retorno + " - " + this.nomeBairro;
			}
			if (this.nomeMunicipio != null
					&& !this.nomeMunicipio.trim().equalsIgnoreCase("")) {
				retorno = retorno + " - " + this.nomeMunicipio;
			}
			if (this.unidadeFederacao != null
					&& !this.unidadeFederacao.trim().equalsIgnoreCase("")) {
				retorno = retorno + " - " + this.unidadeFederacao;
			}

			if (this.cepFormatado != null
					&& !this.cepFormatado.toString().trim().equals("")) {
				retorno = retorno + " - " + this.cepFormatado;
			}

		} else {
			if (this.cepTipoLogradouro != null
					&& !this.cepLogradouro.trim().equalsIgnoreCase("")) {
				retorno = retorno + this.cepLogradouro + " - ";
			}
			if (this.cepLogradouro != null
					&& !this.cepLogradouro.trim().equalsIgnoreCase("")) {
				retorno = retorno + this.cepLogradouro + " - ";
			}
			if (this.cepBairro != null
					&& !this.cepBairro.trim().equalsIgnoreCase("")) {
				retorno = retorno + this.cepBairro + " - ";
			}
			if (this.cepMunicipio != null
					&& !this.cepMunicipio.trim().equalsIgnoreCase("")) {
				retorno = retorno + this.cepMunicipio + " - ";
			}
			if (this.cepSigla != null
					&& !this.cepSigla.trim().equalsIgnoreCase("")) {
				retorno = retorno + this.cepSigla + " - ";
			}
		}

		return retorno;
	}

	public Collection getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(Collection subcategorias) {
		this.subcategorias = subcategorias;
	}

	public Collection getClienteUsuarioResponsavel() {
		return clienteUsuarioResponsavel;
	}

	public void setClienteUsuarioResponsavel(
			Collection clienteUsuarioResponsavel) {
		this.clienteUsuarioResponsavel = clienteUsuarioResponsavel;
	}

	public String getIndicadorImovelCondominioDescricao() {
		return indicadorImovelCondominioDescricao;
	}

	public void setIndicadorImovelCondominioDescricao(
			String indicadorImovelCondominioDescricao) {
		this.indicadorImovelCondominioDescricao = indicadorImovelCondominioDescricao;
	}

	public String getIndicadorExistenciaCavaleteAguaDescricao() {
		return indicadorExistenciaCavaleteAguaDescricao;
	}

	public void setIndicadorExistenciaCavaleteAguaDescricao(
			String indicadorExistenciaCavaleteAguaDescricao) {
		this.indicadorExistenciaCavaleteAguaDescricao = indicadorExistenciaCavaleteAguaDescricao;
	}

	public String getIndicadorExistenciaCavaletePocoDescricao() {
		return indicadorExistenciaCavaletePocoDescricao;
	}

	public void setIndicadorExistenciaCavaletePocoDescricao(
			String indicadorExistenciaCavaletePocoDescricao) {
		this.indicadorExistenciaCavaletePocoDescricao = indicadorExistenciaCavaletePocoDescricao;
	}

	public String getInscricaoFormatada() {
		String retorno = "";

		retorno = this.idLocalidade + "." + this.codigoSetorComercial + "."
				+ this.numeroQuadra + "." + this.numeroLote + "."
				+ this.numeroSubLote;

		return retorno;
	}

	/**
	 * Retorna o valor de cpfFormatado
	 * 
	 * @return O valor de cpfFormatado
	 */
	public String getCpfFormatado() {
		String cpfFormatado = "" + this.clienteCpf;

		if (cpfFormatado.length() == 11) {

			cpfFormatado = cpfFormatado.substring(0, 3) + "."
					+ cpfFormatado.substring(3, 6) + "."
					+ cpfFormatado.substring(6, 9) + "-"
					+ cpfFormatado.substring(9, 11);
		}
		return cpfFormatado;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getUnidadeFederacao() {
		return unidadeFederacao;
	}

	public void setUnidadeFederacao(String unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}

	public String getCepFormatado() {
		return cepFormatado;
	}

	public void setCepFormatado(String cepFormatado) {
		this.cepFormatado = cepFormatado;
	}

	public Integer getAreaConstruidaMaiorFaixa() {
		return areaConstruidaMaiorFaixa;
	}

	public void setAreaConstruidaMaiorFaixa(Integer areaConstruidaMaiorFaixa) {
		this.areaConstruidaMaiorFaixa = areaConstruidaMaiorFaixa;
	}

	public Integer getAreaConstruidaMenorFaixa() {
		return areaConstruidaMenorFaixa;
	}

	public void setAreaConstruidaMenorFaixa(Integer areaConstruidaMenorFaixa) {
		this.areaConstruidaMenorFaixa = areaConstruidaMenorFaixa;
	}

	public BigDecimal getVolumePiscinaMaiorFaixa() {
		return volumePiscinaMaiorFaixa;
	}

	public void setVolumePiscinaMaiorFaixa(BigDecimal volumePiscinaMaiorFaixa) {
		this.volumePiscinaMaiorFaixa = volumePiscinaMaiorFaixa;
	}

	public BigDecimal getVolumePiscinaMenorFaixa() {
		return volumePiscinaMenorFaixa;
	}

	public void setVolumePiscinaMenorFaixa(BigDecimal volumePiscinaMenorFaixa) {
		this.volumePiscinaMenorFaixa = volumePiscinaMenorFaixa;
	}

	public BigDecimal getVolumeReservatorioInferiorMaiorFaixa() {
		return volumeReservatorioInferiorMaiorFaixa;
	}

	public void setVolumeReservatorioInferiorMaiorFaixa(
			BigDecimal volumeReservatorioInferiorMaiorFaixa) {
		this.volumeReservatorioInferiorMaiorFaixa = volumeReservatorioInferiorMaiorFaixa;
	}

	public BigDecimal getVolumeReservatorioInferiorMenorFaixa() {
		return volumeReservatorioInferiorMenorFaixa;
	}

	public void setVolumeReservatorioInferiorMenorFaixa(
			BigDecimal volumeReservatorioInferiorMenorFaixa) {
		this.volumeReservatorioInferiorMenorFaixa = volumeReservatorioInferiorMenorFaixa;
	}

	public BigDecimal getVolumeReservatorioSuperiorMaiorFaixa() {
		return volumeReservatorioSuperiorMaiorFaixa;
	}

	public void setVolumeReservatorioSuperiorMaiorFaixa(
			BigDecimal volumeReservatorioSuperiorMaiorFaixa) {
		this.volumeReservatorioSuperiorMaiorFaixa = volumeReservatorioSuperiorMaiorFaixa;
	}

	public BigDecimal getVolumeReservatorioSuperiorMenorFaixa() {
		return volumeReservatorioSuperiorMenorFaixa;
	}

	public void setVolumeReservatorioSuperiorMenorFaixa(
			BigDecimal volumeReservatorioSuperiorMenorFaixa) {
		this.volumeReservatorioSuperiorMenorFaixa = volumeReservatorioSuperiorMenorFaixa;
	}

	public BigDecimal getAreaConstruidaImovel() {
		return areaConstruidaImovel;
	}

	public void setAreaConstruidaImovel(BigDecimal areaConstruidaImovel) {
		this.areaConstruidaImovel = areaConstruidaImovel;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getJardim() {
		return jardim;
	}

	public void setJardim(String jardim) {
		this.jardim = jardim;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo codigoRota.
	 */
	public Short getCodigoRota() {
		return codigoRota;
	}

	/**
	 * @param codigoRota O codigoRota a ser setado.
	 */
	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	/**
	 * @return Retorna o campo numeroSequencialRota.
	 */
	public Integer getNumeroSequencialRota() {
		return numeroSequencialRota;
	}

	/**
	 * @param numeroSequencialRota O numeroSequencialRota a ser setado.
	 */
	public void setNumeroSequencialRota(Integer numeroSequencialRota) {
		this.numeroSequencialRota = numeroSequencialRota;
	}

	public Integer getIndicadorExistenciaHidrometro() {
		return indicadorExistenciaHidrometro;
	}

	public void setIndicadorExistenciaHidrometro(
			Integer indicadorExistenciaHidrometro) {
		this.indicadorExistenciaHidrometro = indicadorExistenciaHidrometro;
	}

	public String getDescricaoAbreviadaCategoria() {
		return descricaoAbreviadaCategoria;
	}

	public void setDescricaoAbreviadaCategoria(String descricaoAbreviadaCategoria) {
		this.descricaoAbreviadaCategoria = descricaoAbreviadaCategoria;
	}

	public String getIdLogradouro() {
		return idLogradouro;
	}

	public void setIdLogradouro(String idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	/**
	 * @return Retorna o campo testadaLote.
	 */
	public Short getTestadaLote() {
		return testadaLote;
	}

	/**
	 * @param testadaLote O testadaLote a ser setado.
	 */
	public void setTestadaLote(Short testadaLote) {
		this.testadaLote = testadaLote;
	}

	
}
