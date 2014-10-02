package gcom.faturamento.conta;

import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.faturamento.FaturamentoGrupo;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

/**
 * básica 
 *
 * @author Thiago Toscano 
 * @date 02/05/2006
 */
public class ContaMensagem extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
	private Integer id;

	private Integer anoMesRreferenciaFaturamento;

	private String descricaoContaMensagem01;

	private String descricaoContaMensagem02;

	private String descricaoContaMensagem03;

	private Date ultimaAlteracao;
        	
	private FaturamentoGrupo faturamentoGrupo;
	
	private GerenciaRegional gerenciaRegional;
	
	private Localidade localidade;
	
	private SetorComercial setorComercial;
	
	private Quadra quadra;

	public ContaMensagem(Integer id, Integer anoMesRreferenciaFaturamento, String descricaoContaMensagem, Date ultimaAlteracao, FaturamentoGrupo faturamentoGrupo, GerenciaRegional gerenciaRegional, Localidade localidade, SetorComercial setorComercial) {
		super();
		
		this.id = id;
		this.anoMesRreferenciaFaturamento = anoMesRreferenciaFaturamento;
		this.descricaoContaMensagem01 = descricaoContaMensagem;
		this.ultimaAlteracao = ultimaAlteracao;
		this.faturamentoGrupo = faturamentoGrupo;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
	}

	public ContaMensagem(Integer id, Integer anoMesRreferenciaFaturamento, String descricaoContaMensagem, Date ultimaAlteracao, FaturamentoGrupo faturamentoGrupo, GerenciaRegional gerenciaRegional, Localidade localidade, SetorComercial setorComercial, String descricaoContaMensagem02, String descricaoContaMensagem03 ) {
		super();
		
		this.id = id;
		this.anoMesRreferenciaFaturamento = anoMesRreferenciaFaturamento;
		this.descricaoContaMensagem01 = descricaoContaMensagem;
		this.ultimaAlteracao = ultimaAlteracao;
		this.faturamentoGrupo = faturamentoGrupo;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.descricaoContaMensagem02 = descricaoContaMensagem02;
		this.descricaoContaMensagem03 = descricaoContaMensagem03;
	}

	public ContaMensagem () {
		
	}
	
	public ContaMensagem (Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo anoMesRreferenciaFaturamento.
	 */
	public Integer getAnoMesRreferenciaFaturamento() {
		return anoMesRreferenciaFaturamento;
	}

	/**
	 * @param anoMesRreferenciaFaturamento O anoMesRreferenciaFaturamento a ser setado.
	 */
	public void setAnoMesRreferenciaFaturamento(Integer anoMesRreferenciaFaturamento) {
		this.anoMesRreferenciaFaturamento = anoMesRreferenciaFaturamento;
	}


	/**
	 * @return Retorna o campo descricaoContaMensagem01.
	 */
	public String getDescricaoContaMensagem01() {
		return descricaoContaMensagem01;
	}

	/**
	 * @param descricaoContaMensagem01 O descricaoContaMensagem01 a ser setado.
	 */
	public void setDescricaoContaMensagem01(String descricaoContaMensagem01) {
		this.descricaoContaMensagem01 = descricaoContaMensagem01;
	}

	/**
	 * @return Retorna o campo descricaoContaMensagem02.
	 */
	public String getDescricaoContaMensagem02() {
		return descricaoContaMensagem02;
	}

	/**
	 * @param descricaoContaMensagem02 O descricaoContaMensagem02 a ser setado.
	 */
	public void setDescricaoContaMensagem02(String descricaoContaMensagem02) {
		this.descricaoContaMensagem02 = descricaoContaMensagem02;
	}

	/**
	 * @return Retorna o campo descricaoContaMensagem03.
	 */
	public String getDescricaoContaMensagem03() {
		return descricaoContaMensagem03;
	}

	/**
	 * @param descricaoContaMensagem03 O descricaoContaMensagem03 a ser setado.
	 */
	public void setDescricaoContaMensagem03(String descricaoContaMensagem03) {
		this.descricaoContaMensagem03 = descricaoContaMensagem03;
	}

	/**
	 * @return Retorna o campo faturamentoGrupo.
	 */
	public FaturamentoGrupo getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	/**
	 * @param faturamentoGrupo O faturamentoGrupo a ser setado.
	 */
	public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
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

	public Filtro retornaFiltro() {
		
		FiltroContaMensagem filtroContaMensagem = new FiltroContaMensagem();
		filtroContaMensagem.adicionarParametro(new ParametroSimples(FiltroContaMensagem.ID,this.getId()));
		filtroContaMensagem.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroContaMensagem.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroContaMensagem.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		filtroContaMensagem.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
		
		return filtroContaMensagem;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
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
}
