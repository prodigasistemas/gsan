package gcom.cobranca;

import java.util.Date;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.micromedicao.Rota;

/**
 * 
 * Classe Básica 
 *
 * @author Thiago Toscano
 * @date 15/05/2006
 */
public class ResumoCobrancaSituacaoEspecial {

	private Integer id;
	private Integer codigoSetorComercial;
	private Integer numeroQuadra;
	private Integer anoMesInicioSituacaoEspecial;
	private Integer anoMesFinalSituacaoEspecial;
	private Integer quantidadeImovel;
	private Date ultimaAlteracao;

	private GerenciaRegional gerenciaRegional;
	private Localidade localidade;
	private SetorComercial setorComercial;
	private Rota rota;
	private Quadra quadra;
	private ImovelPerfil imovelPerfil;
	private LigacaoAguaSituacao ligacaoAguaSituacao;
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
	private Categoria categoria;
	private EsferaPoder esferaPoder;
	private CobrancaSituacaoTipo cobrancaSituacaoTipo;
	private CobrancaSituacaoMotivo cobrancaSituacaoMotivo;

	public ResumoCobrancaSituacaoEspecial (Integer codigoSetorComercial, Integer numeroQuadra, Integer anoMesInicioSituacaoEspecial, Integer anoMesFinalSituacaoEspecial, Integer quantidadeImovel, GerenciaRegional gerenciaRegional, Localidade localidade, SetorComercial setorComercial, Rota rota, Quadra quadra, ImovelPerfil imovelPerfil, LigacaoAguaSituacao ligacaoAguaSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Categoria categoria, EsferaPoder esferaPoder, CobrancaSituacaoTipo cobrancaSituacaoTipo, CobrancaSituacaoMotivo cobrancaSituacaoMotivo) {
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.anoMesInicioSituacaoEspecial = anoMesInicioSituacaoEspecial;
		this.anoMesFinalSituacaoEspecial = anoMesFinalSituacaoEspecial;
		this.quantidadeImovel = quantidadeImovel;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.rota = rota;
		this.quadra = quadra;
		this.imovelPerfil = imovelPerfil;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.categoria = categoria;
		this.esferaPoder = esferaPoder;
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
		this.cobrancaSituacaoMotivo = cobrancaSituacaoMotivo;
	}

	public ResumoCobrancaSituacaoEspecial (Integer codigoSetorComercial, Integer numeroQuadra, Integer anoMesInicioSituacaoEspecial, Integer anoMesFinalSituacaoEspecial, Integer quantidadeImovel, Date ultimaAlteracao, GerenciaRegional gerenciaRegional, Localidade localidade, SetorComercial setorComercial, Rota rota, Quadra quadra, ImovelPerfil imovelPerfil, LigacaoAguaSituacao ligacaoAguaSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Categoria categoria, EsferaPoder esferaPoder, CobrancaSituacaoTipo cobrancaSituacaoTipo, CobrancaSituacaoMotivo cobrancaSituacaoMotivo) {
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.anoMesInicioSituacaoEspecial = anoMesInicioSituacaoEspecial;
		this.anoMesFinalSituacaoEspecial = anoMesFinalSituacaoEspecial;
		this.quantidadeImovel = quantidadeImovel;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.rota = rota;
		this.quadra = quadra;
		this.imovelPerfil = imovelPerfil;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.categoria = categoria;
		this.esferaPoder = esferaPoder;
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
		this.cobrancaSituacaoMotivo = cobrancaSituacaoMotivo;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ResumoCobrancaSituacaoEspecial (Integer id, Integer codigoSetorComercial, Integer numeroQuadra, Integer anoMesInicioSituacaoEspecial, Integer anoMesFinalSituacaoEspecial, Integer quantidadeImovel, GerenciaRegional gerenciaRegional, Localidade localidade, SetorComercial setorComercial, Rota rota, Quadra quadra, ImovelPerfil imovelPerfil, LigacaoAguaSituacao ligacaoAguaSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Categoria categoria, EsferaPoder esferaPoder, CobrancaSituacaoTipo cobrancaSituacaoTipo, CobrancaSituacaoMotivo cobrancaSituacaoMotivo) {
		this.id = id;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.anoMesInicioSituacaoEspecial = anoMesInicioSituacaoEspecial;
		this.anoMesFinalSituacaoEspecial = anoMesFinalSituacaoEspecial;
		this.quantidadeImovel = quantidadeImovel;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.rota = rota;
		this.quadra = quadra;
		this.imovelPerfil = imovelPerfil;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.categoria = categoria;
		this.esferaPoder = esferaPoder;
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
		this.cobrancaSituacaoMotivo = cobrancaSituacaoMotivo;
	}

	public ResumoCobrancaSituacaoEspecial() {

	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo anoMesFinalSituacaoEspecial.
	 */
	public Integer getAnoMesFinalSituacaoEspecial() {
		return anoMesFinalSituacaoEspecial;
	}
	/**
	 * @param anoMesFinalSituacaoEspecial O anoMesFinalSituacaoEspecial a ser setado.
	 */
	public void setAnoMesFinalSituacaoEspecial(Integer anoMesFinalSituacaoEspecial) {
		this.anoMesFinalSituacaoEspecial = anoMesFinalSituacaoEspecial;
	}
	/**
	 * @return Retorna o campo anoMesInicioSituacaoEspecial.
	 */
	public Integer getAnoMesInicioSituacaoEspecial() {
		return anoMesInicioSituacaoEspecial;
	}
	/**
	 * @param anoMesInicioSituacaoEspecial O anoMesInicioSituacaoEspecial a ser setado.
	 */
	public void setAnoMesInicioSituacaoEspecial(Integer anoMesInicioSituacaoEspecial) {
		this.anoMesInicioSituacaoEspecial = anoMesInicioSituacaoEspecial;
	}
	/**
	 * @return Retorna o campo categoria.
	 */
	public Categoria getCategoria() {
		return categoria;
	}
	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	/**
	 * @return Retorna o campo esferaPoder.
	 */
	public EsferaPoder getEsferaPoder() {
		return esferaPoder;
	}
	/**
	 * @param esferaPoder O esferaPoder a ser setado.
	 */
	public void setEsferaPoder(EsferaPoder esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	/**
	 * @return Retorna o campo cobrancaSituacaoMotivo.
	 */
	public CobrancaSituacaoMotivo getCobrancaSituacaoMotivo() {
		return cobrancaSituacaoMotivo;
	}

	/**
	 * @param cobrancaSituacaoMotivo O cobrancaSituacaoMotivo a ser setado.
	 */
	public void setCobrancaSituacaoMotivo(
			CobrancaSituacaoMotivo cobrancaSituacaoMotivo) {
		this.cobrancaSituacaoMotivo = cobrancaSituacaoMotivo;
	}

	/**
	 * @return Retorna o campo cobrancaSituacaoTipo.
	 */
	public CobrancaSituacaoTipo getCobrancaSituacaoTipo() {
		return cobrancaSituacaoTipo;
	}

	/**
	 * @param cobrancaSituacaoTipo O cobrancaSituacaoTipo a ser setado.
	 */
	public void setCobrancaSituacaoTipo(CobrancaSituacaoTipo cobrancaSituacaoTipo) {
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
	}

	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}
	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	/**
	 * @return Retorna o campo ligacaoAguaSituacao.
	 */
	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}
	/**
	 * @param ligacaoAguaSituacao O ligacaoAguaSituacao a ser setado.
	 */
	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}
	/**
	 * @return Retorna o campo ligacaoEsgotoSituacao.
	 */
	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}
	/**
	 * @param ligacaoEsgotoSituacao O ligacaoEsgotoSituacao a ser setado.
	 */
	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}
	/**
	 * @return Retorna o campo localidade.
	 */
	public Localidade getLocalidade() {
		return localidade;
	}
	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}
	/**
	 * @return Retorna o campo numeroQuadra.
	 */
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}
	/**
	 * @param numeroQuadra O numeroQuadra a ser setado.
	 */
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	/**
	 * @return Retorna o campo imovelPerfil.
	 */
	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	/**
	 * @param imovelPerfil O imovelPerfil a ser setado.
	 */
	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	/**
	 * @return Retorna o campo quadra.
	 */
	public Quadra getQuadra() {
		return quadra;
	}
	/**
	 * @param quadra O quadra a ser setado.
	 */
	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}
	/**
	 * @return Retorna o campo quantidadeImovel.
	 */
	public Integer getQuantidadeImovel() {
		return quantidadeImovel;
	}
	/**
	 * @param quantidadeImovel O quantidadeImovel a ser setado.
	 */
	public void setQuantidadeImovel(Integer quantidadeImovel) {
		this.quantidadeImovel = quantidadeImovel;
	}
	/**
	 * @return Retorna o campo rota.
	 */
	public Rota getRota() {
		return rota;
	}
	/**
	 * @param rota O rota a ser setado.
	 */
	public void setRota(Rota rota) {
		this.rota = rota;
	}
	/**
	 * @return Retorna o campo setorComercial.
	 */
	public SetorComercial getSetorComercial() {
		return setorComercial;
	}
	/**
	 * @param setorComercial O setorComercial a ser setado.
	 */
	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

}
