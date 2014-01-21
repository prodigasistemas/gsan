package gcom.atualizacaocadastral;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.cadastro.imovel.IImovel;
import gcom.micromedicao.hidrometro.HidrometroProtecao;

import java.math.BigDecimal;
import java.util.Date;

public class ImovelRetorno implements IImovel{

	private Integer id;
	private String numeroImovel;
	private String complementoEndereco;
	private Short numeroPontosUtilizacao;
	private Short numeroMorador;
	private BigDecimal numeroIptu;
	private String coordenadaX;
	private String coordenadaY;
	private String numeroMedidorEnergia;
	private String informacoesComplementares;
	private Integer idImovel;
    private String numeroHidrometro;
    private String nomeEntrevistado;
    private LigacaoAguaSituacao ligacaoAguaSituacao;
    private FonteAbastecimento fonteAbastecimento;
    private HidrometroProtecao hidrometroProtecao;
    
	private Integer idLigacaoAguaSituacao;
	private Integer idFonteAbastecimento;
	private Integer idProtecaoHidrometro;
	
	private Date ultimaAlteracao;
	

	public ImovelRetorno () {
		
	}
	
	public ImovelRetorno (IImovel imovelAtualizacaoCadastral) {
		this.numeroImovel = imovelAtualizacaoCadastral.getNumeroImovel();
		this.complementoEndereco = imovelAtualizacaoCadastral.getComplementoEndereco();
		this.numeroPontosUtilizacao = imovelAtualizacaoCadastral.getNumeroPontosUtilizacao();
		this.numeroMorador = imovelAtualizacaoCadastral.getNumeroMorador();
		this.numeroIptu = imovelAtualizacaoCadastral.getNumeroIptu();
		this.coordenadaX = imovelAtualizacaoCadastral.getCoordenadaX();
		this.coordenadaY = imovelAtualizacaoCadastral.getCoordenadaY();
		this.numeroMedidorEnergia = imovelAtualizacaoCadastral.getNumeroMedidorEnergia();
		this.informacoesComplementares = imovelAtualizacaoCadastral.getInformacoesComplementares();
		this.idImovel = imovelAtualizacaoCadastral.getIdImovel();
		this.numeroHidrometro = imovelAtualizacaoCadastral.getNumeroHidrometro();
		this.idProtecaoHidrometro = imovelAtualizacaoCadastral.getIdProtecaoHidrometro();
		this.nomeEntrevistado = imovelAtualizacaoCadastral.getNomeEntrevistado();
		this.ligacaoAguaSituacao = new LigacaoAguaSituacao(imovelAtualizacaoCadastral.getIdLigacaoAguaSituacao());
		this.fonteAbastecimento = new FonteAbastecimento(imovelAtualizacaoCadastral.getIdFonteAbastecimento());
		this.hidrometroProtecao = new HidrometroProtecao(imovelAtualizacaoCadastral.getIdProtecaoHidrometro());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
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

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
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

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdLigacaoAguaSituacao() {
		return idLigacaoAguaSituacao;
	}

	public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao) {
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
	}

	public Integer getIdFonteAbastecimento() {
		return idFonteAbastecimento;
	}

	public void setIdFonteAbastecimento(Integer idFonteAbastecimento) {
		this.idFonteAbastecimento = id;
	}

	public HidrometroProtecao getHidrometroProtecao() {
		return hidrometroProtecao;
	}

	public void setHidrometroProtecao(HidrometroProtecao hidrometroProtecao) {
		this.hidrometroProtecao = hidrometroProtecao;
	}

}
