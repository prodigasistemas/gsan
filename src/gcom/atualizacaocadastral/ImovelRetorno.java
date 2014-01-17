package gcom.atualizacaocadastral;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.cadastro.imovel.IImovel;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;

import java.math.BigDecimal;
import java.util.Date;

public class ImovelRetorno implements IImovel{

	private Integer id;
	private short subLote;
	private String numeroImovel;
	private String complementoEndereco;
	private Short numeroPontosUtilizacao;
	private Short numeroMorador;
	private BigDecimal numeroIptu;
	private String coordenadaX;
	private String coordenadaY;
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
	private HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico;
	private LigacaoAguaSituacao ligacaoAguaSituacao;
	private LogradouroCep logradouroCep;
	private String numeroMedidorEnergia;
	private String informacoesComplementares;
	private FonteAbastecimento fonteAbastecimento;
	private LigacaoAgua ligacaoAgua;
	private Date ultimaAlteracao;
	private Integer idImovel;
	private int numeroQuadra;
	private short lote;
	private Integer numeroSequencialRota;
    private Integer idCapacidadeHidrometro;
    private Integer idMarcaHidrometro;
    private Integer idProtecaoHidrometro;
    private String numeroHidrometro;
    private String nomeEntrevistado;
	private Integer idMunicipio;
	private String nomeMunicipio;
	private String dsUFSiglaMunicipio;
	

	public ImovelRetorno () {
		
	}
	
	public ImovelRetorno (IImovel imovelAtualizacaoCadastral) {
		this.coordenadaX = imovelAtualizacaoCadastral.getCoordenadaX();
		this.coordenadaY = imovelAtualizacaoCadastral.getCoordenadaY();
		this.fonteAbastecimento = imovelAtualizacaoCadastral.getFonteAbastecimento();
		this.hidrometroInstalacaoHistorico = imovelAtualizacaoCadastral.getHidrometroInstalacaoHistorico();
		this.idCapacidadeHidrometro = imovelAtualizacaoCadastral.getIdCapacidadeHidrometro();
		this.idMarcaHidrometro = imovelAtualizacaoCadastral.getIdMarcaHidrometro();
		this.idProtecaoHidrometro = imovelAtualizacaoCadastral.getIdProtecaoHidrometro();
		this.informacoesComplementares = imovelAtualizacaoCadastral.getInformacoesComplementares();
		this.ligacaoAgua = imovelAtualizacaoCadastral.getLigacaoAgua();
		this.ligacaoAguaSituacao = imovelAtualizacaoCadastral.getLigacaoAguaSituacao();
		this.ligacaoEsgotoSituacao = imovelAtualizacaoCadastral.getLigacaoEsgotoSituacao();
		this.lote = imovelAtualizacaoCadastral.getLote();
		this.nomeEntrevistado = imovelAtualizacaoCadastral.getNomeEntrevistado();
		this.numeroHidrometro = imovelAtualizacaoCadastral.getNumeroHidrometro();
		this.numeroImovel = imovelAtualizacaoCadastral.getNumeroImovel();
		this.numeroIptu = imovelAtualizacaoCadastral.getNumeroIptu();
		this.numeroMedidorEnergia = imovelAtualizacaoCadastral.getNumeroMedidorEnergia();
		this.numeroMorador = imovelAtualizacaoCadastral.getNumeroMorador();
		this.numeroPontosUtilizacao = imovelAtualizacaoCadastral.getNumeroPontosUtilizacao();
		this.numeroQuadra = imovelAtualizacaoCadastral.getNumeroQuadra();
		this.subLote = imovelAtualizacaoCadastral.getSubLote();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public short getSubLote() {
		return subLote;
	}

	public void setSubLote(short subLote) {
		this.subLote = subLote;
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

	public BigDecimal getNumeroIptu() {
		return numeroIptu;
	}

	public void setNumeroIptu(BigDecimal numeroIptu) {
		this.numeroIptu = numeroIptu;
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

	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public HidrometroInstalacaoHistorico getHidrometroInstalacaoHistorico() {
		return hidrometroInstalacaoHistorico;
	}

	public void setHidrometroInstalacaoHistorico(HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico) {
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
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

	public String getNumeroMedidorEnergia() {
		return numeroMedidorEnergia;
	}

	public void setNumeroMedidorEnergia(String numeroMedidorEnergia) {
		this.numeroMedidorEnergia = numeroMedidorEnergia;
	}

	public String getInformacoesComplementares() {
		return informacoesComplementares;
	}

	public void setInformacoesComplementares(String informacoesComplementares) {
		this.informacoesComplementares = informacoesComplementares;
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

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public int getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public short getLote() {
		return lote;
	}

	public void setLote(short lote) {
		this.lote = lote;
	}

	public Integer getNumeroSequencialRota() {
		return numeroSequencialRota;
	}

	public void setNumeroSequencialRota(Integer numeroSequencialRota) {
		this.numeroSequencialRota = numeroSequencialRota;
	}

	public Integer getIdCapacidadeHidrometro() {
		return idCapacidadeHidrometro;
	}

	public void setIdCapacidadeHidrometro(Integer idCapacidadeHidrometro) {
		this.idCapacidadeHidrometro = idCapacidadeHidrometro;
	}

	public Integer getIdMarcaHidrometro() {
		return idMarcaHidrometro;
	}

	public void setIdMarcaHidrometro(Integer idMarcaHidrometro) {
		this.idMarcaHidrometro = idMarcaHidrometro;
	}

	public Integer getIdProtecaoHidrometro() {
		return idProtecaoHidrometro;
	}

	public void setIdProtecaoHidrometro(Integer idProtecaoHidrometro) {
		this.idProtecaoHidrometro = idProtecaoHidrometro;
	}

	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public String getNomeEntrevistado() {
		return nomeEntrevistado;
	}

	public void setNomeEntrevistado(String nomeEntrevistado) {
		this.nomeEntrevistado = nomeEntrevistado;
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

	public String getDsUFSiglaMunicipio() {
		return dsUFSiglaMunicipio;
	}

	public void setDsUFSiglaMunicipio(String dsUFSiglaMunicipio) {
		this.dsUFSiglaMunicipio = dsUFSiglaMunicipio;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	
}
