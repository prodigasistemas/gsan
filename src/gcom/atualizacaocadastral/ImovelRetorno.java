package gcom.atualizacaocadastral;

import java.math.BigDecimal;
import java.util.Date;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.cadastro.imovel.IImovel;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroProtecao;

public class ImovelRetorno implements IImovel{

	private Integer id;
	private Integer idImovel;
	private Integer tipoOperacao;
	
	private Integer idLocalidade;
	private int codigoSetorComercial;
	private int numeroQuadra;
	private Integer idRota;
	
	private Integer codigoMunicipio;
	
	private String numeroIptu;
	private String numeroMedidorEnergia;
	
	private Short numeroPontosUtilizacao;
	private Short numeroMorador;
	
	private Integer idLogradouroTipo;
	private String descricaoLogradouro;
	private String numeroImovel;
	private String complementoEndereco;
	private String nomeBairro;
	private Integer codigoCep;
	private String nomeMunicipio;
	private Integer idLogradouro;
	
	private FonteAbastecimento fonteAbastecimento;
	
	private String coordenadaX;
	private String coordenadaY;
	
	private LigacaoAguaSituacao ligacaoAguaSituacao;
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
	private RamalLocalInstalacao ramalLocalInstalacao;
	

	private String numeroHidrometro;
	private HidrometroMarca hidrometroMarca;
	private HidrometroCapacidade hidrometroCapacidade;
	private HidrometroProtecao hidrometroProtecao;
	
	private String informacoesComplementares;
    private String tipoEntrevistado;
    
	private Date ultimaAlteracao;
	
    private Short classeSocial;
    
    private Short quantidadeAnimaisDomesticos;
    
	private BigDecimal areaConstruida;

	private BigDecimal volumePiscina;
    
    private BigDecimal volumeCisterna;
    
    private BigDecimal volumeCaixaDagua;
    
    private Short tipoUso;
    
    private Short acessoHidrometro;
    
    private Integer quantidadeEconomiasSocial;     
    
    private Integer quantidadeEconomiasOutra;
    
    private Short percentualAbastecimento;

	public ImovelRetorno () {
	}
	
	public ImovelRetorno(Integer id) {
		this.id = id;
	}

	public ImovelRetorno(IImovel imovelAtualizacaoCadastral) {
		this.idImovel = imovelAtualizacaoCadastral.getIdImovel();;
		this.tipoOperacao = imovelAtualizacaoCadastral.getTipoOperacao();
		this.idLocalidade = imovelAtualizacaoCadastral.getIdLocalidade();
		this.codigoSetorComercial = imovelAtualizacaoCadastral.getCodigoSetorComercial();
		this.numeroQuadra = imovelAtualizacaoCadastral.getNumeroQuadra();
		this.idRota = imovelAtualizacaoCadastral.getIdRota();
		this.codigoMunicipio = imovelAtualizacaoCadastral.getCodigoMunicipio();
		this.numeroIptu = imovelAtualizacaoCadastral.getNumeroIptu();
		this.numeroMedidorEnergia = imovelAtualizacaoCadastral.getNumeroMedidorEnergia();
		this.numeroPontosUtilizacao = imovelAtualizacaoCadastral.getNumeroPontosUtilizacao();
		this.numeroMorador = imovelAtualizacaoCadastral.getNumeroMorador();
		this.idLogradouroTipo = imovelAtualizacaoCadastral.getLogradouroTipo();
		this.descricaoLogradouro = imovelAtualizacaoCadastral.getDescricaoLogradouro();
		this.numeroImovel = imovelAtualizacaoCadastral.getNumeroImovel();
		this.complementoEndereco = imovelAtualizacaoCadastral.getComplementoEndereco();
		this.nomeBairro = imovelAtualizacaoCadastral.getNomeBairro();
		this.codigoCep = imovelAtualizacaoCadastral.getCodigoCep();
		this.nomeMunicipio = imovelAtualizacaoCadastral.getNomeMunicipio();
		this.idLogradouro = Integer.parseInt(imovelAtualizacaoCadastral.getCodigoLogradouro()); 
		this.fonteAbastecimento = new FonteAbastecimento(imovelAtualizacaoCadastral.getIdFonteAbastecimento());
		this.coordenadaX = imovelAtualizacaoCadastral.getCoordenadaX();
		this.coordenadaY = imovelAtualizacaoCadastral.getCoordenadaY();
		this.ligacaoAguaSituacao = new LigacaoAguaSituacao(imovelAtualizacaoCadastral.getIdLigacaoAguaSituacao());
		this.ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao(imovelAtualizacaoCadastral.getIdLigacaoEsgotoSituacao());
		this.ramalLocalInstalacao = new RamalLocalInstalacao(imovelAtualizacaoCadastral.getIdLocalInstalacaoRamal());
		this.numeroHidrometro = imovelAtualizacaoCadastral.getNumeroHidrometro();
		this.hidrometroMarca = new HidrometroMarca(imovelAtualizacaoCadastral.getIdMarcaHidrometro());
		this.hidrometroCapacidade = new HidrometroCapacidade(imovelAtualizacaoCadastral.getIdCapacidadeHidrometro());
		this.hidrometroProtecao = new HidrometroProtecao(imovelAtualizacaoCadastral.getIdProtecaoHidrometro());
		this.informacoesComplementares = imovelAtualizacaoCadastral.getInformacoesComplementares();
		this.tipoEntrevistado = imovelAtualizacaoCadastral.getTipoEntrevistado();
		this.classeSocial                 = imovelAtualizacaoCadastral.getClasseSocial();
		this.quantidadeAnimaisDomesticos  = imovelAtualizacaoCadastral.getQuantidadeAnimaisDomesticos();
		this.areaConstruida               = imovelAtualizacaoCadastral.getAreaConstruida();
		this.volumePiscina                = imovelAtualizacaoCadastral.getVolumePiscina();
		this.volumeCisterna               = imovelAtualizacaoCadastral.getVolumeCisterna();
		this.volumeCaixaDagua             = imovelAtualizacaoCadastral.getVolumeCaixaDagua();
		this.tipoUso                      = imovelAtualizacaoCadastral.getTipoUso();
		this.acessoHidrometro             = imovelAtualizacaoCadastral.getAcessoHidrometro();
		this.quantidadeEconomiasSocial    = imovelAtualizacaoCadastral.getQuantidadeEconomiasSocial();
		this.quantidadeEconomiasOutra     = imovelAtualizacaoCadastral.getQuantidadeEconomiasOutra();
		this.percentualAbastecimento      = imovelAtualizacaoCadastral.getPercentualAbastecimento();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(Integer tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public int getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(int codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public int getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Integer getIdRota() {
		return idRota;
	}

	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}

	public Integer getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(Integer codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public String getNumeroIptu() {
		return numeroIptu;
	}

	public void setNumeroIptu(String numeroIptu) {
		this.numeroIptu = numeroIptu;
	}

	public String getNumeroMedidorEnergia() {
		return numeroMedidorEnergia;
	}

	public void setNumeroMedidorEnergia(String numeroMedidorEnergia) {
		this.numeroMedidorEnergia = numeroMedidorEnergia;
	}

	public Short getNumeroPontosUtilizacao() {
		return numeroPontosUtilizacao;
	}

	public void setNumeroPontosUtilizacao(Short numeroPontosUtilizacao) {
		this.numeroPontosUtilizacao = numeroPontosUtilizacao;
	}

	public Short getNumeroMorador() {
		return numeroMorador;
	}

	public void setNumeroMorador(Short numeroMorador) {
		this.numeroMorador = numeroMorador;
	}

	public Integer getIdLogradouroTipo() {
		return idLogradouroTipo;
	}

	public void setIdLogradouroTipo(Integer idLogradouroTipo) {
		this.idLogradouroTipo = idLogradouroTipo;
	}

	public String getDescricaoLogradouro() {
		return descricaoLogradouro;
	}

	public void setDescricaoLogradouro(String descricaoLogradouro) {
		this.descricaoLogradouro = descricaoLogradouro;
	}

	public String getNumeroImovel() {
		return numeroImovel;
	}

	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}

	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public Integer getCodigoCep() {
		return codigoCep;
	}

	public void setCodigoCep(Integer codigoCep) {
		this.codigoCep = codigoCep;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public Integer getIdLogradouro() {
		return idLogradouro;
	}

	public void setIdLogradouro(Integer idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	public FonteAbastecimento getFonteAbastecimento() {
		return fonteAbastecimento;
	}

	public void setFonteAbastecimento(FonteAbastecimento fonteAbastecimento) {
		this.fonteAbastecimento = fonteAbastecimento;
	}

	public String getCoordenadaX() {
		return coordenadaX;
	}

	public void setCoordenadaX(String coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	public String getCoordenadaY() {
		return coordenadaY;
	}

	public void setCoordenadaY(String coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public RamalLocalInstalacao getRamalLocalInstalacao() {
		return ramalLocalInstalacao;
	}

	public void setRamalLocalInstalacao(RamalLocalInstalacao ramalLocalInstalacao) {
		this.ramalLocalInstalacao = ramalLocalInstalacao;
	}

	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public HidrometroProtecao getHidrometroProtecao() {
		return hidrometroProtecao;
	}

	public void setHidrometroProtecao(HidrometroProtecao hidrometroProtecao) {
		this.hidrometroProtecao = hidrometroProtecao;
	}

	public String getInformacoesComplementares() {
		return informacoesComplementares;
	}

	public void setInformacoesComplementares(String informacoesComplementares) {
		this.informacoesComplementares = informacoesComplementares;
	}

	public String getTipoEntrevistado() {
		return tipoEntrevistado;
	}

	public void setTipoEntrevistado(String tipoEntrevistado) {
		this.tipoEntrevistado = tipoEntrevistado;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getCodigoLogradouro() {
		return null;
	}

	public Integer getIdFonteAbastecimento() {
		return null;
	}

	public Integer getIdLigacaoAguaSituacao() {
		return null;
	}

	public Integer getLogradouroTipo() {
		return null;
	}

	public boolean isImovelNovo() {
		return false;
	}

	public void setCodigoLogradouro(String codigoLogradouro) {
	}

	public void setIdFonteAbastecimento(Integer idFonteAbastecimento) {
	}

	public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao) {
	}

	public void setLogradouroTipo(Integer logradouroTipo) {
	}

	public Integer getIdLigacaoEsgotoSituacao() {
		return null;
	}

	public void setIdLigacaoEsgotoSituacao(Integer idLigacaoEsgotoSituacao) {
	}

	public Integer getIdLocalInstalacaoRamal() {
		return null;
	}

	public void setIdLocalInstalacaoRamal(Integer idRamalLocalInstalacao) {
	}

	public Integer getIdProtecaoHidrometro() {
		return null;
	}

	public void setIdProtecaoHidrometro(Integer idProtecaoHidrometro) {
	}

	public HidrometroCapacidade getHidrometroCapacidade() {
		return hidrometroCapacidade;
	}

	public HidrometroMarca getHidrometroMarca() {
		return hidrometroMarca;
	}

	public Integer getIdCapacidadeHidrometro() {
		return null;
	}

	public Integer getIdMarcaHidrometro() {
		return null;
	}

	public void setHidrometroCapacidade(HidrometroCapacidade hidrometroCapacidade) {
		this.hidrometroCapacidade = hidrometroCapacidade;
	}

	public void setHidrometroMarca(HidrometroMarca hidrometroMarca) {
		this.hidrometroMarca = hidrometroMarca;
	}

	public void setIdCapacidadeHidrometro(Integer idCapacidadeHidrometro) {
	}

	public void setIdMarcaHidrometro(Integer idMarcaHidrometro) {
	}

    public Short getClasseSocial() {
        return classeSocial;
    }

    public void setClasseSocial(Short classeSocial) {
        this.classeSocial = classeSocial;
    }

    public Short getQuantidadeAnimaisDomesticos() {
        return quantidadeAnimaisDomesticos;
    }

    public void setQuantidadeAnimaisDomesticos(Short quantidadeAnimaisDomesticos) {
        this.quantidadeAnimaisDomesticos = quantidadeAnimaisDomesticos;
    }

    public BigDecimal getAreaConstruida() {
    	return areaConstruida != null && areaConstruida.compareTo(BigDecimal.ZERO) <= 0 ? null : areaConstruida;
	}

	public void setAreaConstruida(BigDecimal areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	public BigDecimal getVolumePiscina() {
		return volumePiscina != null && volumePiscina.compareTo(BigDecimal.ZERO) <= 0 ? null : volumePiscina;
	}

	public void setVolumePiscina(BigDecimal volumePiscina) {
		this.volumePiscina = volumePiscina;
	}

	public BigDecimal getVolumeCisterna() {
        return volumeCisterna != null && volumeCisterna.compareTo(BigDecimal.ZERO) <= 0 ? null : volumeCisterna;
    }

    public void setVolumeCisterna(BigDecimal volumeCisterna) {
        this.volumeCisterna = volumeCisterna;
    }

    public BigDecimal getVolumeCaixaDagua() {
    	return volumeCaixaDagua != null && volumeCaixaDagua.compareTo(BigDecimal.ZERO) <= 0 ? null : volumeCaixaDagua;
    }

    public void setVolumeCaixaDagua(BigDecimal volumeCaixaDagua) {
        this.volumeCaixaDagua = volumeCaixaDagua;
    }

    public Short getTipoUso() {
        return tipoUso;
    }

    public void setTipoUso(Short tipoUso) {
        this.tipoUso = tipoUso;
    }

    public Short getAcessoHidrometro() {
        return acessoHidrometro;
    }

    public void setAcessoHidrometro(Short acessoHidrometro) {
        this.acessoHidrometro = acessoHidrometro;
    }

    public Integer getQuantidadeEconomiasSocial() {
        return quantidadeEconomiasSocial;
    }

    public void setQuantidadeEconomiasSocial(Integer quantidadeEconomiasSocial) {
        this.quantidadeEconomiasSocial = quantidadeEconomiasSocial;
    }

    public Integer getQuantidadeEconomiasOutra() {
        return quantidadeEconomiasOutra;
    }

    public void setQuantidadeEconomiasOutra(Integer quantidadeEconomiasOutra) {
        this.quantidadeEconomiasOutra = quantidadeEconomiasOutra;
    }

	public Short getPercentualAbastecimento() {
		return percentualAbastecimento;
	}

	public void setPercentualAbastecimento(Short percentualAbastecimento) {
		this.percentualAbastecimento = percentualAbastecimento;
	}
}
