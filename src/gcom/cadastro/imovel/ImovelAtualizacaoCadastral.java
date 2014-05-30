package gcom.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


@ControleAlteracao()
public class ImovelAtualizacaoCadastral extends ObjetoTransacao implements IImovel {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL = 1502;
	
	private Integer id;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idImovel;
    
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String numeroImovel;
    
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idLocalidade;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private int codigoSetorComercial;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private int numeroQuadra;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private short lote;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private short subLote;
	
    private Integer numeroSequencialRota;
	
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idLogradouroTipo;
	
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String dsLogradouroTipo;
	
    private Integer idLogradouroTitulo;
	
    private String dsLogradouroTitulo;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idLogradouro;
	
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String descricaoLogradouro;
	
    private Integer idBairro;
	
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String nomeBairro;
	
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer codigoCep;

    @ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String complementoEndereco;
	
    private Integer idEnderecoReferencia;

    private Integer idDespejo;

    private BigDecimal volumeReservatorioSuperior;

    private BigDecimal volumeReservatorioInferior;

    private Integer idPavimentoCalcada;

    private Integer idPavimentoRua;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idFonteAbastecimento;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idLigacaoAguaSituacao;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idLigacaoEsgotoSituacao;

    private Integer idImovelPerfil;

    private BigDecimal volumePiscina;

    private Integer idPocoTipo;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Short numeroPontosUtilizacao;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Short numeroMorador;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String numeroIptu;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Long numeroContratoEnergia;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String coordenadaX;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String coordenadaY;

    private Date ultimaAlteracao;

    private BigDecimal areaConstruida;

    private Short indicadorJardim;

    private Integer numeroLeituraInstalacaoHidrometro;

    @ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idCapacidadeHidrometro;

    @ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idMarcaHidrometro;

    private Integer idLocalInstalacaoHidrometro;

    @ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idProtecaoHidrometro;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String numeroHidrometro;

	private Short indicadorCavalete;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String numeroMedidorEnergia;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idCadastroOcorrencia;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String descricaoOutrasInformacoes;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String tipoEntrevistado;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
	private Integer idLocalInstalacaoRamal;
	
	private Integer codigoImovelPrincipal;
    
    private Integer idSituacaoAtualizacaoCadastral;
    
    private Integer idEmpresa;

	private Integer idMunicipio;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
	private String nomeMunicipio;
	
	private Integer idUinidadeFederacao;
	
	private String dsUFSiglaMunicipio;
	
	private Integer idArquivoTexto;
	
	private Integer indicadorAtualizado;
	
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
	
	private LigacaoAguaSituacao ligacaoAguaSituacao;
	
	private LogradouroCep logradouroCep;
	
	private FonteAbastecimento fonteAbastecimento;
	
	private LigacaoAgua ligacaoAgua;
	
	private Integer tipoOperacao;
	
	private Integer codigoMunicipio;
	
	private Integer idRota;
	
	public ImovelAtualizacaoCadastral(Integer id, Integer idImovel, Integer idLocalidade,
			int codigoSetorComercial, int numeroQuadra, short lote,
			short subLote, Integer numeroSequencialRota,
			Integer idLogradouroTipo, String dsLogradouroTipo,
			Integer idLogradouroTitulo, String dsLogradouroTitulo,
			Integer idLogradouro, String descricaoLogradouro, Integer idBairro,
			String nomeBairro, Integer codigoCep, String numeroImovel,
			String complementoEndereco, Integer idEnderecoReferencia,
			Integer idDespejo, BigDecimal volumeReservatorioSuperior,
			BigDecimal volumeReservatorioInferior, Integer idPavimentoCalcada,
			Integer idPavimentoRua, Integer idFonteAbastecimento,
			Integer idLigacaoAguaSituacao, Integer idLigacaoEsgotoSituacao,
			Integer idImovelPerfil, BigDecimal volumePiscina,
			Integer idPocoTipo, Short numeroPontosUtilizacao,
			Short numeroMorador, String numeroIptu,
			Long numeroContratoEnergia, String coordenadaX,
			String coordenadaY, Date ultimaAlteracao,
			BigDecimal areaConstruida, Short indicadorJardim,
			Integer numeroLeituraInstalacaoHidrometro,
			Integer idCapacidadeHidrometro, Integer idMarcaHidrometro,
			Integer idLocalInstalacaoHidrometro, Integer idProtecaoHidrometro,
			String numeroHidrometro, Short indicadorCavalete,
			String numeroMedidorEnergia, Integer idCadastroOcorrencia,
			String descricaoOutrasInformacoes, String tipoEntrevistado,
			Integer codigoImovelPrincipal,
			Integer idSituacaoAtualizacaoCadastral, Integer idEmpresa,
			Integer idMunicipio, Integer codigoMunicipio, String nomeMunicipio,
			Integer idUinidadeFederacao, String dsUFSiglaMunicipio,
			Integer idArquivoTexto, Integer indicadorAtualizado,
			Integer tipoOperacao) {
		this.id = id;
		this.idImovel = idImovel;
		this.idLocalidade = idLocalidade;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.lote = lote;
		this.subLote = subLote;
		this.numeroSequencialRota = numeroSequencialRota;
		this.idLogradouroTipo = idLogradouroTipo;
		this.dsLogradouroTipo = dsLogradouroTipo;
		this.idLogradouroTitulo = idLogradouroTitulo;
		this.dsLogradouroTitulo = dsLogradouroTitulo;
		this.idLogradouro = idLogradouro;
		this.descricaoLogradouro = descricaoLogradouro;
		this.idBairro = idBairro;
		this.nomeBairro = nomeBairro;
		this.codigoCep = codigoCep;
		this.numeroImovel = numeroImovel;
		this.complementoEndereco = complementoEndereco;
		this.idEnderecoReferencia = idEnderecoReferencia;
		this.idDespejo = idDespejo;
		this.volumeReservatorioSuperior = volumeReservatorioSuperior;
		this.volumeReservatorioInferior = volumeReservatorioInferior;
		this.idPavimentoCalcada = idPavimentoCalcada;
		this.idPavimentoRua = idPavimentoRua;
		this.idFonteAbastecimento = idFonteAbastecimento;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idImovelPerfil = idImovelPerfil;
		this.volumePiscina = volumePiscina;
		this.idPocoTipo = idPocoTipo;
		this.numeroPontosUtilizacao = numeroPontosUtilizacao;
		this.numeroMorador = numeroMorador;
		this.numeroIptu = numeroIptu;
		this.numeroContratoEnergia = numeroContratoEnergia;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
		this.ultimaAlteracao = ultimaAlteracao;
		this.areaConstruida = areaConstruida;
		this.indicadorJardim = indicadorJardim;
		this.numeroLeituraInstalacaoHidrometro = numeroLeituraInstalacaoHidrometro;
		this.idCapacidadeHidrometro = idCapacidadeHidrometro;
		this.idMarcaHidrometro = idMarcaHidrometro;
		this.idLocalInstalacaoHidrometro = idLocalInstalacaoHidrometro;
		this.idProtecaoHidrometro = idProtecaoHidrometro;
		this.numeroHidrometro = numeroHidrometro;
		this.indicadorCavalete = indicadorCavalete;
		this.numeroMedidorEnergia = numeroMedidorEnergia;
		this.idCadastroOcorrencia = idCadastroOcorrencia;
		this.descricaoOutrasInformacoes = descricaoOutrasInformacoes;
		this.tipoEntrevistado = tipoEntrevistado;
		this.codigoImovelPrincipal = codigoImovelPrincipal;
		this.idSituacaoAtualizacaoCadastral = idSituacaoAtualizacaoCadastral;
		this.idEmpresa = idEmpresa;
		this.idMunicipio = idMunicipio;
		this.codigoMunicipio = codigoMunicipio;
		this.nomeMunicipio = nomeMunicipio;
		this.idUinidadeFederacao = idUinidadeFederacao;
		this.dsUFSiglaMunicipio = dsUFSiglaMunicipio;
		this.idArquivoTexto = idArquivoTexto;
		this.indicadorAtualizado = indicadorAtualizado;
		this.tipoOperacao = tipoOperacao;
	}

	public ImovelAtualizacaoCadastral(){    	
    }
	
	public ImovelAtualizacaoCadastral(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdImovel() {
        return this.idImovel;
    }

    public void setIdImovel(Integer idImovel) {
        this.idImovel = idImovel;
    }
    
    public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public short getLote() {
        return this.lote;
    }

    public void setLote(short lote) {
        this.lote = lote;
    }

    public short getSubLote() {
        return this.subLote;
    }

    public void setSubLote(short subLote) {
        this.subLote = subLote;
    }

    public String getNumeroImovel() {
        return this.numeroImovel;
    }

    public void setNumeroImovel(String numeroImovel) {
        this.numeroImovel = numeroImovel;
    }

    public String getComplementoEndereco() {
        return this.complementoEndereco;
    }

    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
    }

    public Integer getIdDespejo() {
        return this.idDespejo;
    }

    public void setIdDespejo(Integer idDespejo) {
        this.idDespejo = idDespejo;
    }

    public BigDecimal getVolumeReservatorioSuperior() {
        return this.volumeReservatorioSuperior;
    }

    public void setVolumeReservatorioSuperior(BigDecimal volumeReservatorioSuperior) {
        this.volumeReservatorioSuperior = volumeReservatorioSuperior;
    }

    public BigDecimal getVolumeReservatorioInferior() {
        return this.volumeReservatorioInferior;
    }

    public void setVolumeReservatorioInferior(BigDecimal volumeReservatorioInferior) {
        this.volumeReservatorioInferior = volumeReservatorioInferior;
    }

    public Integer getIdPavimentoCalcada() {
        return this.idPavimentoCalcada;
    }

    public void setIdPavimentoCalcada(Integer idPavimentoCalcada) {
        this.idPavimentoCalcada = idPavimentoCalcada;
    }

    public Integer getIdPavimentoRua() {
        return this.idPavimentoRua;
    }

    public void setIdPavimentoRua(Integer idPavimentoRua) {
        this.idPavimentoRua = idPavimentoRua;
    }

    public Integer getIdFonteAbastecimento() {
        return this.idFonteAbastecimento;
    }

    public void setIdFonteAbastecimento(Integer idFonteAbastecimento) {
        this.idFonteAbastecimento = idFonteAbastecimento;
    }

    public Integer getIdLigacaoAguaSituacao() {
        return this.idLigacaoAguaSituacao;
    }

    public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao) {
        this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
    }

    public Integer getIdLigacaoEsgotoSituacao() {
        return this.idLigacaoEsgotoSituacao;
    }

    public void setIdLigacaoEsgotoSituacao(Integer idLigacaoEsgotoSituacao) {
        this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
    }

    public Integer getIdImovelPerfil() {
        return this.idImovelPerfil;
    }

    public void setIdImovelPerfil(Integer idImovelPerfil) {
        this.idImovelPerfil = idImovelPerfil;
    }

    public BigDecimal getVolumePiscina() {
        return this.volumePiscina;
    }

    public void setVolumePiscina(BigDecimal volumePiscina) {
        this.volumePiscina = volumePiscina;
    }
    
    public Integer getIdPocoTipo() {
		return idPocoTipo;
	}

	public void setIdPocoTipo(Integer idPocoTipo) {
		this.idPocoTipo = idPocoTipo;
	}

	public Short getNumeroPontosUtilizacao() {
        return this.numeroPontosUtilizacao;
    }

    public void setNumeroPontosUtilizacao(Short numeroPontosUtilizacao) {
        this.numeroPontosUtilizacao = numeroPontosUtilizacao;
    }

    public Short getNumeroMorador() {
        return this.numeroMorador;
    }

    public void setNumeroMorador(Short numeroMorador) {
        this.numeroMorador = numeroMorador;
    }

    public String getNumeroIptu() {
        return this.numeroIptu;
    }

    public void setNumeroIptu(String numeroIptu) {
        this.numeroIptu = numeroIptu;
    }

    public Long getNumeroContratoEnergia() {
        return this.numeroContratoEnergia;
    }

    public void setNumeroContratoEnergia(Long numeroContratoEnergia) {
        this.numeroContratoEnergia = numeroContratoEnergia;
    }

    public String getCoordenadaX() {
        return this.coordenadaX;
    }

    public void setCoordenadaX(String coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public String getCoordenadaY() {
        return this.coordenadaY;
    }

    public void setCoordenadaY(String coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }


    public Integer getIdEnderecoReferencia() {
		return idEnderecoReferencia;
	}

	public void setIdEnderecoReferencia(Integer idEnderecoReferencia) {
		this.idEnderecoReferencia = idEnderecoReferencia;
	}

	public Integer getIdLogradouro() {
        return this.idLogradouro;
    }

    public void setIdLogradouro(Integer idLogradouro) {
        this.idLogradouro = idLogradouro;
    }

    public BigDecimal getAreaConstruida() {
        return this.areaConstruida;
    }

    public void setAreaConstruida(BigDecimal areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    public Integer getIdBairro() {
        return this.idBairro;
    }

    public void setIdBairro(Integer idBairro) {
        this.idBairro = idBairro;
    }

    public Short getIndicadorJardim() {
        return this.indicadorJardim;
    }

    public void setIndicadorJardim(Short indicadorJardim) {
        this.indicadorJardim = indicadorJardim;
    }

    public Integer getNumeroSequencialRota() {
        return this.numeroSequencialRota;
    }

    public void setNumeroSequencialRota(Integer numeroSequencialRota) {
        this.numeroSequencialRota = numeroSequencialRota;
    }

    public int getCodigoSetorComercial() {
        return this.codigoSetorComercial;
    }

    public void setCodigoSetorComercial(int codigoSetorComercial) {
        this.codigoSetorComercial = codigoSetorComercial;
    }

    public int getNumeroQuadra() {
        return this.numeroQuadra;
    }

    public void setNumeroQuadra(int numeroQuadra) {
        this.numeroQuadra = numeroQuadra;
    }

    public String getDescricaoLogradouro() {
        return this.descricaoLogradouro;
    }

    public void setDescricaoLogradouro(String descricaoLogradouro) {
        this.descricaoLogradouro = descricaoLogradouro;
    }

    public Integer getCodigoCep() {
        return this.codigoCep;
    }

    public void setCodigoCep(Integer codigoCep) {
        this.codigoCep = codigoCep;
    }

    public String getNomeBairro() {
        return this.nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    public Integer getNumeroLeituraInstalacaoHidrometro() {
        return this.numeroLeituraInstalacaoHidrometro;
    }

    public void setNumeroLeituraInstalacaoHidrometro(Integer numeroLeituraInstalacaoHidrometro) {
        this.numeroLeituraInstalacaoHidrometro = numeroLeituraInstalacaoHidrometro;
    }

    public Integer getIdCapacidadeHidrometro() {
        return this.idCapacidadeHidrometro;
    }

    public void setIdCapacidadeHidrometro(Integer idCapacidadeHidrometro) {
        this.idCapacidadeHidrometro = idCapacidadeHidrometro;
    }

    public Integer getIdMarcaHidrometro() {
        return this.idMarcaHidrometro;
    }

    public void setIdMarcaHidrometro(Integer idMarcaHidrometro) {
        this.idMarcaHidrometro = idMarcaHidrometro;
    }

    public Integer getIdLocalInstalacaoHidrometro() {
        return this.idLocalInstalacaoHidrometro;
    }

    public void setIdLocalInstalacaoHidrometro(Integer idLocalInstalacaoHidrometro) {
        this.idLocalInstalacaoHidrometro = idLocalInstalacaoHidrometro;
    }

    public Integer getIdProtecaoHidrometro() {
        return this.idProtecaoHidrometro;
    }

    public void setIdProtecaoHidrometro(Integer idProtecaoHidrometro) {
        this.idProtecaoHidrometro = idProtecaoHidrometro;
    }

    public String getNumeroHidrometro() {
        return this.numeroHidrometro;
    }

    public void setNumeroHidrometro(String numeroHidrometro) {
        this.numeroHidrometro = numeroHidrometro;
    }

	public Short getIndicadorCavalete() {
		return indicadorCavalete;
	}

	public void setIndicadorCavalete(Short indicadorCavalete) {
		this.indicadorCavalete = indicadorCavalete;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("idImovel", getIdImovel())
            .toString();
    }
	
    public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdSituacaoAtualizacaoCadastral() {
		return idSituacaoAtualizacaoCadastral;
	}

	public void setIdSituacaoAtualizacaoCadastral(
			Integer idSituacaoAtualizacaoCadastral) {
		this.idSituacaoAtualizacaoCadastral = idSituacaoAtualizacaoCadastral;
	}
	
    public String getDsLogradouroTipo() {
		return dsLogradouroTipo;
	}

	public void setDsLogradouroTipo(String dsLogradouroTipo) {
		this.dsLogradouroTipo = dsLogradouroTipo;
	}

	public String getDsLogradouroTitulo() {
		return dsLogradouroTitulo;
	}

	public void setDsLogradouroTitulo(String dsLogradouroTitulo) {
		this.dsLogradouroTitulo = dsLogradouroTitulo;
	}

	public Integer getIdLogradouroTipo() {
		return idLogradouroTipo;
	}

	public void setIdLogradouroTipo(Integer idLogradouroTipo) {
		this.idLogradouroTipo = idLogradouroTipo;
	}

	public Integer getIdLogradouroTitulo() {
		return idLogradouroTitulo;
	}

	public void setIdLogradouroTitulo(Integer idLogradouroTitulo) {
		this.idLogradouroTitulo = idLogradouroTitulo;
	}

	
	/*
	 * Retorna a inscrição do imóvel.
	 */
	public String getInscricaoFormatada() {
		String inscricao = null;

		String zeroUm = "0";
		String zeroDois = "00";
		String zeroTres = "000";

		String localidade, setorComercial, quadra, lote, subLote;

		localidade = String.valueOf(this.getIdLocalidade().intValue());
		setorComercial = String.valueOf(this.getCodigoSetorComercial());
		quadra = String.valueOf(this.getNumeroQuadra());
		lote = String.valueOf(this.getLote());
		subLote = String.valueOf(this.getSubLote());

		if (String.valueOf(this.getIdLocalidade().intValue()).length() < 3
				&& String.valueOf(this.getIdLocalidade().intValue())
						.length() > 1) {
			localidade = zeroUm + this.getIdLocalidade().intValue();
		} else if (String.valueOf(this.getIdLocalidade().intValue())
				.length() < 3) {
			localidade = zeroDois + this.getIdLocalidade().intValue();
		}

		if (String.valueOf(this.getCodigoSetorComercial()).length() < 3
				&& String.valueOf(this.getCodigoSetorComercial())
						.length() > 1) {
			setorComercial = zeroUm + this.getCodigoSetorComercial();
		} else if (String.valueOf(this.getCodigoSetorComercial())
				.length() < 3) {
			setorComercial = zeroDois + this.getCodigoSetorComercial();
		}

		if (String.valueOf(this.getNumeroQuadra()).length() < 3
				&& String.valueOf(this.getNumeroQuadra()).length() > 1) {
			quadra = zeroUm + this.getNumeroQuadra();
		} else if (String.valueOf(this.getNumeroQuadra()).length() < 3) {
			quadra = zeroDois + this.getNumeroQuadra();
		}

		if (String.valueOf(this.getLote()).length() < 4
				&& String.valueOf(this.getLote()).length() > 2) {
			lote = zeroUm + this.getLote();
		} else if (String.valueOf(this.getLote()).length() < 3
				&& String.valueOf(this.getLote()).length() > 1) {
			lote = zeroDois + this.getLote();
		} else if (String.valueOf(this.getLote()).length() < 2) {
			lote = zeroTres + this.getLote();
		}

		if (String.valueOf(this.getSubLote()).length() < 3
				&& String.valueOf(this.getSubLote()).length() > 1) {
			subLote = zeroUm + this.getSubLote();
		} else if (String.valueOf(this.getSubLote()).length() < 3) {
			subLote = zeroDois + this.getSubLote();
		}

		inscricao = localidade + "." + setorComercial + "." + quadra + "."
				+ lote + "." + subLote;

		return inscricao;
	}

	/**
     *  Descrição do método>>
     * 
     * @param other
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof ImovelAtualizacaoCadastral)) {
            return false;
        }
        ImovelAtualizacaoCadastral castOther = (ImovelAtualizacaoCadastral) other;

        return new EqualsBuilder().append(this.getIdImovel(), castOther.getIdImovel())
                .isEquals();
    }

	@Override
	public Filtro retornaFiltro() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroTabela.ID, this.getIdImovel()));
		return filtro;

	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		return null;
	}



	public Integer getCodigoImovelPrincipal() {
		return codigoImovelPrincipal;
	}



	public void setCodigoImovelPrincipal(Integer codigoImovelPrincipal) {
		this.codigoImovelPrincipal = codigoImovelPrincipal;
	}

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public Integer getIdUinidadeFederacao() {
		return idUinidadeFederacao;
	}

	public void setIdUinidadeFederacao(Integer idUinidadeFederacao) {
		this.idUinidadeFederacao = idUinidadeFederacao;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getDsUFSiglaMunicipio() {
		return dsUFSiglaMunicipio;
	}

	public void setDsUFSiglaMunicipio(String dsUFSiglaMunicipio) {
		this.dsUFSiglaMunicipio = dsUFSiglaMunicipio;
	}

	public String getDescricaoOutrasInformacoes() {
		return descricaoOutrasInformacoes;
	}

	public void setDescricaoOutrasInformacoes(String descricaoOutrasInformacoes) {
		this.descricaoOutrasInformacoes = descricaoOutrasInformacoes;
	}

	public Integer getIdCadastroOcorrencia() {
		return idCadastroOcorrencia;
	}

	public void setIdCadastroOcorrencia(Integer idCadastroOcorrencia) {
		this.idCadastroOcorrencia = idCadastroOcorrencia;
	}

	public String getTipoEntrevistado() {
		return tipoEntrevistado;
	}

	public void setTipoEntrevistado(String tipoEntrevistado) {
		this.tipoEntrevistado = tipoEntrevistado;
	}

	public String getNumeroMedidorEnergia() {
		return numeroMedidorEnergia;
	}

	public void setNumeroMedidorEnergia(String numeroMedidorEnergia) {
		this.numeroMedidorEnergia = numeroMedidorEnergia;
	}

	public Integer getIdLocalInstalacaoRamal() {
		return idLocalInstalacaoRamal;
	}

	public void setIdLocalInstalacaoRamal(Integer idLocalInstalacaoRamal) {
		this.idLocalInstalacaoRamal = idLocalInstalacaoRamal;
	}

	public Integer getIdArquivoTexto() {
		return idArquivoTexto;
	}

	public void setIdArquivoTexto(Integer idArquivoTexto) {
		this.idArquivoTexto = idArquivoTexto;
	}

	public Integer getIndicadorAtualizado() {
		return indicadorAtualizado;
	}

	public void setIndicadorAtualizado(Integer indicadorAtualizado) {
		this.indicadorAtualizado = indicadorAtualizado;
	}

	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public LogradouroCep getLogradouroCep() {
		return logradouroCep;
	}

	public void setLogradouroCep(LogradouroCep logradouroCep) {
		this.logradouroCep = logradouroCep;
	}

	public FonteAbastecimento getFonteAbastecimento() {
		return fonteAbastecimento;
	}

	public void setFonteAbastecimento(FonteAbastecimento fonteAbastecimento) {
		this.fonteAbastecimento = fonteAbastecimento;
	}

	public LigacaoAgua getLigacaoAgua() {
		return ligacaoAgua;
	}

	public void setLigacaoAgua(LigacaoAgua ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}

	public String getInformacoesComplementares() {
		return descricaoOutrasInformacoes;
	}

	public void setInformacoesComplementares(String informacoesComplementares) {
		this.descricaoOutrasInformacoes = informacoesComplementares;
	}

	public HidrometroProtecao getHidrometroProtecao() {
		return null;
	}

	public void setHidrometroProtecao(HidrometroProtecao hidrometroProtecao) {
	}

	public Integer getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(Integer tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public Integer getCodigoMunicipio() {
		return this.codigoMunicipio;
	}

	public void setCodigoMunicipio(Integer codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public Integer getLogradouroTipo() {
		return idLogradouroTipo;
	}

	public void setLogradouroTipo(Integer logradouroTipo) {
		this.idLogradouroTipo = logradouroTipo;
	}

	public String getCodigoLogradouro() {
		return String.valueOf(idLogradouro);
	}

	public void setCodigoLogradouro(String codigoLogradouro) {
		this.idLogradouro = Integer.valueOf(codigoLogradouro);
	}
	
	public boolean isImovelNovo() {
		return this.idImovel.equals(null);
	}

	public Integer getIdRota() {
		return idRota;
	}

	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}

	public HidrometroCapacidade getHidrometroCapacidade() {
		return null;
	}

	public HidrometroMarca getHidrometroMarca() {
		return null;
	}

	public void setHidrometroCapacidade(HidrometroCapacidade hidrometroCapacidade) {
	}

	public void setHidrometroMarca(HidrometroMarca hidrometroMarca) {
	}
}
