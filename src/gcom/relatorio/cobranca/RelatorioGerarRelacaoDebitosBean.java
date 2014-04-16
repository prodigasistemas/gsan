package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * Bean do [UC0227] Gerar Relação de Débitos 
 *
 * @author Rafael Santos
 * @date 25/05/2006
 */
public class RelatorioGerarRelacaoDebitosBean implements RelatorioBean {
	
	private String gerenciaRegional;
	private String localidade; 
	private String idGerenciaRegional;
	private String nomeGerenciaRegional;
	private String idLocalidade;
	private String nomeLocalidade;
	private String matriculaImovel;
	private String inscricaoImovel;
	private String nomeClienteUsuario;
	private String nomeClienteResponsavel;
	private String endereco;
	private String quantidadeEconomias;
	private String descricaoAbreviadaCategoria;
	private String subcategoriaPrincipal;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	private String percentualEsgoto;
	private String dataCorte;
	private String mediaConsumoImovel;
	private String countContasLocalidade;
	private String countImovelLocalidade;
	private String countDebitosLocalidade;
	private String countContasGerencia;			
	private String countImovelGerencia;
	private String countDebitosGerencia;
	private String countContasTotal;			
	private String countImovelTotal;
	private String countDebitosTotal;
	private String totalDebitosLocalidade;
	private String totalDebitosGerencia;
	private String totalDebitosTotal;
	private String rota;
	private String sequencialRota;
	private String mensagemNaoExisteDados;
	
	private BigDecimal totalContasLocalidade;	
	private BigDecimal totalContasGerencia;
	private BigDecimal totalContasTotal;
	private BigDecimal totalDebitosACobrarLocalidade;
	private BigDecimal totalDebitosACobrarGerencia;
	private BigDecimal totalDebitosACobrarTotal;
	private BigDecimal totalGuiasPagamentoLocalidade;
	private BigDecimal totalGuiasPagamentoGerencia;
	private BigDecimal totalGuiasPagamentoTotal;
	private BigDecimal totalAcrescimosLocalidade;
	private BigDecimal totalAcrescimosGerencia;
	private BigDecimal totalAcrescimosTotal;
	
	
	private JRBeanCollectionDataSource jrColecaoRelatorioGerarRelacaoDebitosContasBean;
	
	private ArrayList arrayRelatorioGerarRelacaoDebitosContasBean;

	
	private JRBeanCollectionDataSource jrColecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean;
	
	private ArrayList arrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean;
	
	
	private JRBeanCollectionDataSource jrColecaoRelatorioGerarRelacaoDebitosTotaisImovelBean;
	
	private ArrayList arrayRelatorioGerarRelacaoDebitosTotaisImovelBean;

	private JRBeanCollectionDataSource jrColecaoRelatorioGerarRelacaoDebitosGuiasBean;
	
	private ArrayList arrayRelatorioGerarRelacaoDebitosGuiasBean;
	
	
	/**
	 * Construtor de RelatorioGerarRelacaoDebitosBean 
	 * 
	 * @param idGerenciaRegional
	 * @param nomeGerenciaRegional
	 * @param idLocalidade
	 * @param nomeLocalidade
	 * @param matriculaImovel
	 * @param inscricaoImovel
	 * @param nomeClienteUsuario
	 * @param nomeClienteResponsavel
	 * @param endereco
	 * @param quantidadeEconomias
	 * @param descricaoAbreviadaCategoria
	 * @param situacaoLigacaoAgua
	 * @param situacaoLigacaoEsgoto
	 * @param percentualEsgoto
	 * @param dataCorte
	 * @param mediaConsumoImovel
	 * @param jrColecaoRelatorioGerarRelacaoDebitosContasBean
	 * @param colecaoRelatorioGerarRelacaoDebitosContasBean
	 * @param jrColecaoRelatorioGerarRelacaoDebitosTotaisImovelBean
	 * @param colecaoRelatorioGerarRelacaoDebitosTotaisImovelBean
	 */
	public RelatorioGerarRelacaoDebitosBean(String idGerenciaRegional, String nomeGerenciaRegional, String idLocalidade, String nomeLocalidade, String matriculaImovel, String inscricaoImovel, String nomeClienteUsuario, String nomeClienteResponsavel, String endereco, String quantidadeEconomias, String descricaoAbreviadaCategoria, String situacaoLigacaoAgua, String situacaoLigacaoEsgoto, String percentualEsgoto, String dataCorte, String mediaConsumoImovel, Collection colecaoRelatorioGerarRelacaoDebitosContasBean, Collection colecaoRelatorioGerarRelacaoDebitosTotaisImovelBean,Collection colecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean,
			String gerenciaRegional, String localidade,String countContasLocalidade,String countImovelLocalidade,String countDebitosLocalidade,String countContasGerencia,String countImovelGerencia,String countDebitosGerencia,String countContasTotal,String countImovelTotal,String countDebitosTotal,String totalDebitosLocalidade,String totalDebitosGerencia,String totalDebitosTotal,
			BigDecimal totalContasLocalidade,	
			BigDecimal totalGuiasPagamentoLocalidade,
			BigDecimal totalDebitosACobrarLocalidade,
			BigDecimal totalAcrescimosLocalidade,
			BigDecimal totalContasGerencia,
			BigDecimal totalGuiasPagamentoGerencia,
			BigDecimal totalDebitosACobrarGerencia,
			BigDecimal totalAcrescimosGerencia,
			BigDecimal totalContasTotal,
			BigDecimal totalGuiasPagamentoTotal,
			BigDecimal totalDebitosACobrarTotal,
			BigDecimal totalAcrescimosTotal) {
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLocalidade = idLocalidade;
		this.nomeLocalidade = nomeLocalidade;
		this.matriculaImovel = matriculaImovel;
		this.inscricaoImovel = inscricaoImovel;
		this.nomeClienteUsuario = nomeClienteUsuario;
		this.nomeClienteResponsavel = nomeClienteResponsavel;
		this.endereco = endereco;
		this.quantidadeEconomias = quantidadeEconomias;
		this.descricaoAbreviadaCategoria = descricaoAbreviadaCategoria;
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
		this.percentualEsgoto = percentualEsgoto;
		this.dataCorte = dataCorte;
		this.mediaConsumoImovel = mediaConsumoImovel;
		
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade; 
		
		this.countContasLocalidade = countContasLocalidade;
		this.countImovelLocalidade = countImovelLocalidade;
		this.countDebitosLocalidade = countDebitosLocalidade;
		this.countContasGerencia = countContasGerencia;			
		this.countImovelGerencia = countImovelGerencia;
		this.countDebitosGerencia = countDebitosGerencia;
		this.countContasTotal = countContasTotal;			
		this.countImovelTotal = countImovelTotal;
		this.countDebitosTotal = countDebitosTotal;
		this.totalDebitosLocalidade = totalDebitosLocalidade;
		this.totalDebitosGerencia = totalDebitosGerencia;
		this.totalDebitosTotal = totalDebitosTotal;
		
		this.arrayRelatorioGerarRelacaoDebitosContasBean = new ArrayList();
		this.arrayRelatorioGerarRelacaoDebitosTotaisImovelBean = new ArrayList();
		this.arrayRelatorioGerarRelacaoDebitosContasBean.addAll(colecaoRelatorioGerarRelacaoDebitosContasBean);
		this.arrayRelatorioGerarRelacaoDebitosTotaisImovelBean.addAll(colecaoRelatorioGerarRelacaoDebitosTotaisImovelBean);
		
		this.jrColecaoRelatorioGerarRelacaoDebitosContasBean = new JRBeanCollectionDataSource(this.arrayRelatorioGerarRelacaoDebitosContasBean);
		
		this.jrColecaoRelatorioGerarRelacaoDebitosTotaisImovelBean = new JRBeanCollectionDataSource(this.arrayRelatorioGerarRelacaoDebitosTotaisImovelBean);
		
		this.arrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean = new ArrayList();
		this.arrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean.addAll(colecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean);
		this.jrColecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean = new JRBeanCollectionDataSource(this.arrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean);
		
		this.totalContasLocalidade = totalContasLocalidade;
		this.totalContasGerencia = totalContasGerencia;
		this.totalContasTotal = totalContasTotal;
		this.totalDebitosACobrarLocalidade = totalDebitosACobrarLocalidade;
		this.totalDebitosACobrarGerencia = totalDebitosACobrarGerencia;
		this.totalDebitosACobrarTotal = totalDebitosACobrarTotal;
		this.totalGuiasPagamentoLocalidade = totalGuiasPagamentoLocalidade;
		this.totalGuiasPagamentoGerencia = totalGuiasPagamentoGerencia;
		this.totalGuiasPagamentoTotal = totalGuiasPagamentoTotal;
		this.totalAcrescimosLocalidade = totalAcrescimosLocalidade;
		this.totalAcrescimosGerencia = totalAcrescimosGerencia;
		this.totalAcrescimosTotal = totalAcrescimosTotal;
	} 
	
	/**
	 * Construtor de RelatorioGerarRelacaoDebitosBean 
	 * 
	 * @param idGerenciaRegional
	 * @param nomeGerenciaRegional
	 * @param idLocalidade
	 * @param nomeLocalidade
	 * @param matriculaImovel
	 * @param inscricaoImovel
	 * @param nomeClienteUsuario
	 * @param nomeClienteResponsavel
	 * @param endereco
	 * @param quantidadeEconomias
	 * @param descricaoAbreviadaCategoria
	 * @param situacaoLigacaoAgua
	 * @param situacaoLigacaoEsgoto
	 * @param percentualEsgoto
	 * @param dataCorte
	 * @param mediaConsumoImovel
	 * @param jrColecaoRelatorioGerarRelacaoDebitosContasBean
	 * @param colecaoRelatorioGerarRelacaoDebitosContasBean
	 * @param jrColecaoRelatorioGerarRelacaoDebitosTotaisImovelBean
	 * @param colecaoRelatorioGerarRelacaoDebitosTotaisImovelBean
	 */
	public RelatorioGerarRelacaoDebitosBean(String matriculaImovel, String inscricaoImovel, String nomeClienteUsuario, String nomeClienteResponsavel, String endereco, String quantidadeEconomias, String descricaoAbreviadaCategoria, String subcategoriaPrincipal, String situacaoLigacaoAgua, String situacaoLigacaoEsgoto, String percentualEsgoto, String dataCorte, String mediaConsumoImovel,
			String gerenciaRegional,
			String localidade,
			String rota, 
			String sequencialRota, 
			String countContasLocalidade,String countImovelLocalidade,String countDebitosLocalidade,String countContasGerencia,String countImovelGerencia,String countDebitosGerencia,String countContasTotal,String countImovelTotal,String countDebitosTotal,String totalDebitosLocalidade,String totalDebitosGerencia,String totalDebitosTotal,Collection colecaoRelatorioGerarRelacaoDebitosContasBean,Collection colecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean,
			Collection colecaoRelatorioGerarRelacaoDebitosTotaisImovelBean,
			Collection colecaoRelatorioGerarRelacaoDebitosGuiasBean,
			BigDecimal totalContasLocalidade,	
			BigDecimal totalGuiasPagamentoLocalidade,
			BigDecimal totalDebitosACobrarLocalidade,
			BigDecimal totalAcrescimosLocalidade,
			BigDecimal totalContasGerencia,
			BigDecimal totalGuiasPagamentoGerencia,
			BigDecimal totalDebitosACobrarGerencia,
			BigDecimal totalAcrescimosGerencia,
			BigDecimal totalContasTotal,
			BigDecimal totalGuiasPagamentoTotal,
			BigDecimal totalDebitosACobrarTotal,
			BigDecimal totalAcrescimosTotal) {
		this.matriculaImovel = matriculaImovel;
		this.inscricaoImovel = inscricaoImovel;
		this.nomeClienteUsuario = nomeClienteUsuario;
		this.nomeClienteResponsavel = nomeClienteResponsavel;
		this.endereco = endereco;
		this.quantidadeEconomias = quantidadeEconomias;
		this.descricaoAbreviadaCategoria = descricaoAbreviadaCategoria;
		this.subcategoriaPrincipal = subcategoriaPrincipal;
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
		this.percentualEsgoto = percentualEsgoto;
		this.dataCorte = dataCorte;
		this.mediaConsumoImovel = mediaConsumoImovel;
		
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.rota = rota;
		this.sequencialRota = sequencialRota;
		
		this.countContasLocalidade = countContasLocalidade;
		this.countImovelLocalidade = countImovelLocalidade;
		this.countDebitosLocalidade = countDebitosLocalidade;
		this.countContasGerencia = countContasGerencia;			
		this.countImovelGerencia = countImovelGerencia;
		this.countDebitosGerencia = countDebitosGerencia;
		this.countContasTotal = countContasTotal;			
		this.countImovelTotal = countImovelTotal;
		this.countDebitosTotal = countDebitosTotal;
		this.totalDebitosLocalidade = totalDebitosLocalidade;
		this.totalDebitosGerencia = totalDebitosGerencia;
		this.totalDebitosTotal = totalDebitosTotal;
		
		this.arrayRelatorioGerarRelacaoDebitosContasBean = new ArrayList();
		this.arrayRelatorioGerarRelacaoDebitosContasBean.addAll(colecaoRelatorioGerarRelacaoDebitosContasBean);
		this.jrColecaoRelatorioGerarRelacaoDebitosContasBean = new JRBeanCollectionDataSource(this.arrayRelatorioGerarRelacaoDebitosContasBean);
		
		this.arrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean = new ArrayList();
		this.arrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean.addAll(colecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean);
		this.jrColecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean = new JRBeanCollectionDataSource(this.arrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean);
		
		this.arrayRelatorioGerarRelacaoDebitosTotaisImovelBean = new ArrayList();
		this.arrayRelatorioGerarRelacaoDebitosTotaisImovelBean.addAll(colecaoRelatorioGerarRelacaoDebitosTotaisImovelBean);
		this.jrColecaoRelatorioGerarRelacaoDebitosTotaisImovelBean = new JRBeanCollectionDataSource(this.arrayRelatorioGerarRelacaoDebitosTotaisImovelBean);

		
		this.arrayRelatorioGerarRelacaoDebitosGuiasBean = new ArrayList();
		this.arrayRelatorioGerarRelacaoDebitosGuiasBean.addAll(colecaoRelatorioGerarRelacaoDebitosGuiasBean);
		this.jrColecaoRelatorioGerarRelacaoDebitosGuiasBean = new JRBeanCollectionDataSource(this.arrayRelatorioGerarRelacaoDebitosGuiasBean);
		
		this.totalContasLocalidade = totalContasLocalidade;
		this.totalContasGerencia = totalContasGerencia;
		this.totalContasTotal = totalContasTotal;
		this.totalDebitosACobrarLocalidade = totalDebitosACobrarLocalidade;
		this.totalDebitosACobrarGerencia = totalDebitosACobrarGerencia;
		this.totalDebitosACobrarTotal = totalDebitosACobrarTotal;
		this.totalGuiasPagamentoLocalidade = totalGuiasPagamentoLocalidade;
		this.totalGuiasPagamentoGerencia = totalGuiasPagamentoGerencia;
		this.totalGuiasPagamentoTotal = totalGuiasPagamentoTotal;
		this.totalAcrescimosLocalidade = totalAcrescimosLocalidade;
		this.totalAcrescimosGerencia = totalAcrescimosGerencia;
		this.totalAcrescimosTotal = totalAcrescimosTotal;
	} 
	
	public RelatorioGerarRelacaoDebitosBean() {
	}

	/**
	 * @return Retorna o campo colecaoRelatorioGerarRelacaoDebitosContasBean.
	 */
	public ArrayList getArrayRelatorioGerarRelacaoDebitosContasBean() {
		return arrayRelatorioGerarRelacaoDebitosContasBean;
	}

	/**
	 * @param colecaoRelatorioGerarRelacaoDebitosContasBean O colecaoRelatorioGerarRelacaoDebitosContasBean a ser setado.
	 */
	public void setArrayRelatorioGerarRelacaoDebitosContasBean(
			ArrayList colecaoRelatorioGerarRelacaoDebitosContasBean) {
		this.arrayRelatorioGerarRelacaoDebitosContasBean = colecaoRelatorioGerarRelacaoDebitosContasBean;
	}

	/**
	 * @return Retorna o campo colecaoRelatorioGerarRelacaoDebitosTotaisImovelBean.
	 */
	public ArrayList getArrayRelatorioGerarRelacaoDebitosTotaisImovelBean() {
		return arrayRelatorioGerarRelacaoDebitosTotaisImovelBean;
	}

	/**
	 * @param colecaoRelatorioGerarRelacaoDebitosTotaisImovelBean O colecaoRelatorioGerarRelacaoDebitosTotaisImovelBean a ser setado.
	 */
	public void setArrayRelatorioGerarRelacaoDebitosTotaisImovelBean(
			ArrayList colecaoRelatorioGerarRelacaoDebitosTotaisImovelBean) {
		this.arrayRelatorioGerarRelacaoDebitosTotaisImovelBean = colecaoRelatorioGerarRelacaoDebitosTotaisImovelBean;
	}

	/**
	 * @return Retorna o campo dataCorte.
	 */
	public String getDataCorte() {
		return dataCorte;
	}

	/**
	 * @param dataCorte O dataCorte a ser setado.
	 */
	public void setDataCorte(String dataCorte) {
		this.dataCorte = dataCorte;
	}

	/**
	 * @return Retorna o campo descricaoAbreviadaCategoria.
	 */
	public String getDescricaoAbreviadaCategoria() {
		return descricaoAbreviadaCategoria;
	}

	/**
	 * @param descricaoAbreviadaCategoria O descricaoAbreviadaCategoria a ser setado.
	 */
	public void setDescricaoAbreviadaCategoria(String descricaoAbreviadaCategoria) {
		this.descricaoAbreviadaCategoria = descricaoAbreviadaCategoria;
	}

	/**
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	/**
	 * @param inscricaoImovel O inscricaoImovel a ser setado.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Retorna o campo jrColecaoRelatorioGerarRelacaoDebitosContasBean.
	 */
	public JRBeanCollectionDataSource getJrColecaoRelatorioGerarRelacaoDebitosContasBean() {
		return jrColecaoRelatorioGerarRelacaoDebitosContasBean;
	}

	/**
	 * @param jrColecaoRelatorioGerarRelacaoDebitosContasBean O jrColecaoRelatorioGerarRelacaoDebitosContasBean a ser setado.
	 */
	public void setJrColecaoRelatorioGerarRelacaoDebitosContasBean(
			JRBeanCollectionDataSource jrColecaoRelatorioGerarRelacaoDebitosContasBean) {
		this.jrColecaoRelatorioGerarRelacaoDebitosContasBean = jrColecaoRelatorioGerarRelacaoDebitosContasBean;
	}

	/**
	 * @return Retorna o campo jrColecaoRelatorioGerarRelacaoDebitosTotaisImovelBean.
	 */
	public JRBeanCollectionDataSource getJrColecaoRelatorioGerarRelacaoDebitosTotaisImovelBean() {
		return jrColecaoRelatorioGerarRelacaoDebitosTotaisImovelBean;
	}

	/**
	 * @param jrColecaoRelatorioGerarRelacaoDebitosTotaisImovelBean O jrColecaoRelatorioGerarRelacaoDebitosTotaisImovelBean a ser setado.
	 */
	public void setJrColecaoRelatorioGerarRelacaoDebitosTotaisImovelBean(
			JRBeanCollectionDataSource jrColecaoRelatorioGerarRelacaoDebitosTotaisImovelBean) {
		this.jrColecaoRelatorioGerarRelacaoDebitosTotaisImovelBean = jrColecaoRelatorioGerarRelacaoDebitosTotaisImovelBean;
	}

	/**
	 * @return Retorna o campo matriculaImovel.
	 */
	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	/**
	 * @param matriculaImovel O matriculaImovel a ser setado.
	 */
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	/**
	 * @return Retorna o campo mediaConsumoImovel.
	 */
	public String getMediaConsumoImovel() {
		return mediaConsumoImovel;
	}

	/**
	 * @param mediaConsumoImovel O mediaConsumoImovel a ser setado.
	 */
	public void setMediaConsumoImovel(String mediaConsumoImovel) {
		this.mediaConsumoImovel = mediaConsumoImovel;
	}

	/**
	 * @return Retorna o campo nomeClienteResponsavel.
	 */
	public String getNomeClienteResponsavel() {
		return nomeClienteResponsavel;
	}

	/**
	 * @param nomeClienteResponsavel O nomeClienteResponsavel a ser setado.
	 */
	public void setNomeClienteResponsavel(String nomeClienteResponsavel) {
		this.nomeClienteResponsavel = nomeClienteResponsavel;
	}

	/**
	 * @return Retorna o campo nomeClienteUsuario.
	 */
	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	/**
	 * @param nomeClienteUsuario O nomeClienteUsuario a ser setado.
	 */
	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	/**
	 * @return Retorna o campo nomeGerenciaRegional.
	 */
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	/**
	 * @param nomeGerenciaRegional O nomeGerenciaRegional a ser setado.
	 */
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	/**
	 * @return Retorna o campo nomeLocalidade.
	 */
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	/**
	 * @param nomeLocalidade O nomeLocalidade a ser setado.
	 */
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	/**
	 * @return Retorna o campo percentualEsgoto.
	 */
	public String getPercentualEsgoto() {
		return percentualEsgoto;
	}

	/**
	 * @param percentualEsgoto O percentualEsgoto a ser setado.
	 */
	public void setPercentualEsgoto(String percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}

	/**
	 * @return Retorna o campo quantidadeEconomias.
	 */
	public String getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	/**
	 * @param quantidadeEconomias O quantidadeEconomias a ser setado.
	 */
	public void setQuantidadeEconomias(String quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	/**
	 * @return Retorna o campo situacaoLigacaoAgua.
	 */
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	/**
	 * @param situacaoLigacaoAgua O situacaoLigacaoAgua a ser setado.
	 */
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	/**
	 * @return Retorna o campo situacaoLigacaoEsgoto.
	 */
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	/**
	 * @param situacaoLigacaoEsgoto O situacaoLigacaoEsgoto a ser setado.
	 */
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}


	/**
	 * @return Retorna o campo arrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean.
	 */
	public ArrayList getArrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean() {
		return arrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean;
	}


	/**
	 * @param arrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean O arrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean a ser setado.
	 */
	public void setArrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean(
			ArrayList arrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean) {
		this.arrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean = arrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean;
	}


	/**
	 * @return Retorna o campo jrColecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean.
	 */
	public JRBeanCollectionDataSource getJrColecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean() {
		return jrColecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean;
	}


	/**
	 * @param jrColecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean O jrColecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean a ser setado.
	 */
	public void setJrColecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean(
			JRBeanCollectionDataSource jrColecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean) {
		this.jrColecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean = jrColecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean;
	}


	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}


	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}


	/**
	 * @return Retorna o campo localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}


	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo arrayRelatorioGerarRelacaoDebitosGuiasBean.
	 */
	public ArrayList getArrayRelatorioGerarRelacaoDebitosGuiasBean() {
		return arrayRelatorioGerarRelacaoDebitosGuiasBean;
	}

	/**
	 * @param arrayRelatorioGerarRelacaoDebitosGuiasBean O arrayRelatorioGerarRelacaoDebitosGuiasBean a ser setado.
	 */
	public void setArrayRelatorioGerarRelacaoDebitosGuiasBean(
			ArrayList arrayRelatorioGerarRelacaoDebitosGuiasBean) {
		this.arrayRelatorioGerarRelacaoDebitosGuiasBean = arrayRelatorioGerarRelacaoDebitosGuiasBean;
	}

	/**
	 * @return Retorna o campo jrColecaoRelatorioGerarRelacaoDebitosGuiasBean.
	 */
	public JRBeanCollectionDataSource getJrColecaoRelatorioGerarRelacaoDebitosGuiasBean() {
		return jrColecaoRelatorioGerarRelacaoDebitosGuiasBean;
	}

	/**
	 * @param jrColecaoRelatorioGerarRelacaoDebitosGuiasBean O jrColecaoRelatorioGerarRelacaoDebitosGuiasBean a ser setado.
	 */
	public void setJrColecaoRelatorioGerarRelacaoDebitosGuiasBean(
			JRBeanCollectionDataSource jrColecaoRelatorioGerarRelacaoDebitosGuiasBean) {
		this.jrColecaoRelatorioGerarRelacaoDebitosGuiasBean = jrColecaoRelatorioGerarRelacaoDebitosGuiasBean;
	}

	/**
	 * @return Retorna o campo countContasGerencia.
	 */
	public String getCountContasGerencia() {
		return countContasGerencia;
	}

	/**
	 * @param countContasGerencia O countContasGerencia a ser setado.
	 */
	public void setCountContasGerencia(String countContasGerencia) {
		this.countContasGerencia = countContasGerencia;
	}

	/**
	 * @return Retorna o campo countContasLocalidade.
	 */
	public String getCountContasLocalidade() {
		return countContasLocalidade;
	}

	/**
	 * @param countContasLocalidade O countContasLocalidade a ser setado.
	 */
	public void setCountContasLocalidade(String countContasLocalidade) {
		this.countContasLocalidade = countContasLocalidade;
	}

	/**
	 * @return Retorna o campo countContasTotal.
	 */
	public String getCountContasTotal() {
		return countContasTotal;
	}

	/**
	 * @param countContasTotal O countContasTotal a ser setado.
	 */
	public void setCountContasTotal(String countContasTotal) {
		this.countContasTotal = countContasTotal;
	}

	/**
	 * @return Retorna o campo countDebitosGerencia.
	 */
	public String getCountDebitosGerencia() {
		return countDebitosGerencia;
	}

	/**
	 * @param countDebitosGerencia O countDebitosGerencia a ser setado.
	 */
	public void setCountDebitosGerencia(String countDebitosGerencia) {
		this.countDebitosGerencia = countDebitosGerencia;
	}

	/**
	 * @return Retorna o campo countDebitosLocalidade.
	 */
	public String getCountDebitosLocalidade() {
		return countDebitosLocalidade;
	}

	/**
	 * @param countDebitosLocalidade O countDebitosLocalidade a ser setado.
	 */
	public void setCountDebitosLocalidade(String countDebitosLocalidade) {
		this.countDebitosLocalidade = countDebitosLocalidade;
	}

	/**
	 * @return Retorna o campo countDebitosTotal.
	 */
	public String getCountDebitosTotal() {
		return countDebitosTotal;
	}

	/**
	 * @param countDebitosTotal O countDebitosTotal a ser setado.
	 */
	public void setCountDebitosTotal(String countDebitosTotal) {
		this.countDebitosTotal = countDebitosTotal;
	}

	/**
	 * @return Retorna o campo countGuiasGerencia.
	 */
	public String getCountImovelGerencia() {
		return countImovelGerencia;
	}

	/**
	 * @param countGuiasGerencia O countGuiasGerencia a ser setado.
	 */
	public void setCountImovelGerencia(String countImovelGerencia) {
		this.countImovelGerencia = countImovelGerencia;
	}

	/**
	 * @return Retorna o campo countGuiasLocalidade.
	 */
	public String getCountImovelLocalidade() {
		return countImovelLocalidade;
	}

	/**
	 * @param countGuiasLocalidade O countGuiasLocalidade a ser setado.
	 */
	public void setCountImovelLocalidade(String countImovelLocalidade) {
		this.countImovelLocalidade = countImovelLocalidade;
	}

	/**
	 * @return Retorna o campo countGuiasTotal.
	 */
	public String getCountImovelTotal() {
		return countImovelTotal;
	}

	/**
	 * @param countGuiasTotal O countGuiasTotal a ser setado.
	 */
	public void setCountImovelTotal(String countImovelTotal) {
		this.countImovelTotal = countImovelTotal;
	}

	/**
	 * @return Retorna o campo totalDebitosGerencia.
	 */
	public String getTotalDebitosGerencia() {
		return totalDebitosGerencia;
	}

	/**
	 * @param totalDebitosGerencia O totalDebitosGerencia a ser setado.
	 */
	public void setTotalDebitosGerencia(String totalDebitosGerencia) {
		this.totalDebitosGerencia = totalDebitosGerencia;
	}

	/**
	 * @return Retorna o campo totalDebitosLocalidade.
	 */
	public String getTotalDebitosLocalidade() {
		return totalDebitosLocalidade;
	}

	/**
	 * @param totalDebitosLocalidade O totalDebitosLocalidade a ser setado.
	 */
	public void setTotalDebitosLocalidade(String totalDebitosLocalidade) {
		this.totalDebitosLocalidade = totalDebitosLocalidade;
	}

	/**
	 * @return Retorna o campo totalDebitosTotal.
	 */
	public String getTotalDebitosTotal() {
		return totalDebitosTotal;
	}

	/**
	 * @param totalDebitosTotal O totalDebitosTotal a ser setado.
	 */
	public void setTotalDebitosTotal(String totalDebitosTotal) {
		this.totalDebitosTotal = totalDebitosTotal;
	}

	/**
	 * @return Retorna o campo totalAcrescimosGerencia.
	 */
	public BigDecimal getTotalAcrescimosGerencia() {
		return totalAcrescimosGerencia;
	}

	/**
	 * @param totalAcrescimosGerencia O totalAcrescimosGerencia a ser setado.
	 */
	public void setTotalAcrescimosGerencia(BigDecimal totalAcrescimosGerencia) {
		this.totalAcrescimosGerencia = totalAcrescimosGerencia;
	}

	/**
	 * @return Retorna o campo totalAcrescimosLocalidade.
	 */
	public BigDecimal getTotalAcrescimosLocalidade() {
		return totalAcrescimosLocalidade;
	}

	/**
	 * @param totalAcrescimosLocalidade O totalAcrescimosLocalidade a ser setado.
	 */
	public void setTotalAcrescimosLocalidade(BigDecimal totalAcrescimosLocalidade) {
		this.totalAcrescimosLocalidade = totalAcrescimosLocalidade;
	}

	/**
	 * @return Retorna o campo totalAcrescimosTotal.
	 */
	public BigDecimal getTotalAcrescimosTotal() {
		return totalAcrescimosTotal;
	}

	/**
	 * @param totalAcrescimosTotal O totalAcrescimosTotal a ser setado.
	 */
	public void setTotalAcrescimosTotal(BigDecimal totalAcrescimosTotal) {
		this.totalAcrescimosTotal = totalAcrescimosTotal;
	}

	/**
	 * @return Retorna o campo totalContasGerencia.
	 */
	public BigDecimal getTotalContasGerencia() {
		return totalContasGerencia;
	}

	/**
	 * @param totalContasGerencia O totalContasGerencia a ser setado.
	 */
	public void setTotalContasGerencia(BigDecimal totalContasGerencia) {
		this.totalContasGerencia = totalContasGerencia;
	}

	/**
	 * @return Retorna o campo totalContasLocalidade.
	 */
	public BigDecimal getTotalContasLocalidade() {
		return totalContasLocalidade;
	}

	/**
	 * @param totalContasLocalidade O totalContasLocalidade a ser setado.
	 */
	public void setTotalContasLocalidade(BigDecimal totalContasLocalidade) {
		this.totalContasLocalidade = totalContasLocalidade;
	}

	/**
	 * @return Retorna o campo totalContasTotal.
	 */
	public BigDecimal getTotalContasTotal() {
		return totalContasTotal;
	}

	/**
	 * @param totalContasTotal O totalContasTotal a ser setado.
	 */
	public void setTotalContasTotal(BigDecimal totalContasTotal) {
		this.totalContasTotal = totalContasTotal;
	}

	/**
	 * @return Retorna o campo totalDebitosACobrarGerencia.
	 */
	public BigDecimal getTotalDebitosACobrarGerencia() {
		return totalDebitosACobrarGerencia;
	}

	/**
	 * @param totalDebitosACobrarGerencia O totalDebitosACobrarGerencia a ser setado.
	 */
	public void setTotalDebitosACobrarGerencia(
			BigDecimal totalDebitosACobrarGerencia) {
		this.totalDebitosACobrarGerencia = totalDebitosACobrarGerencia;
	}

	/**
	 * @return Retorna o campo totalDebitosACobrarLocalidade.
	 */
	public BigDecimal getTotalDebitosACobrarLocalidade() {
		return totalDebitosACobrarLocalidade;
	}

	/**
	 * @param totalDebitosACobrarLocalidade O totalDebitosACobrarLocalidade a ser setado.
	 */
	public void setTotalDebitosACobrarLocalidade(
			BigDecimal totalDebitosACobrarLocalidade) {
		this.totalDebitosACobrarLocalidade = totalDebitosACobrarLocalidade;
	}

	/**
	 * @return Retorna o campo totalDebitosACobrarTotal.
	 */
	public BigDecimal getTotalDebitosACobrarTotal() {
		return totalDebitosACobrarTotal;
	}

	/**
	 * @param totalDebitosACobrarTotal O totalDebitosACobrarTotal a ser setado.
	 */
	public void setTotalDebitosACobrarTotal(BigDecimal totalDebitosACobrarTotal) {
		this.totalDebitosACobrarTotal = totalDebitosACobrarTotal;
	}

	/**
	 * @return Retorna o campo totalGuiasPagamentoGerencia.
	 */
	public BigDecimal getTotalGuiasPagamentoGerencia() {
		return totalGuiasPagamentoGerencia;
	}

	/**
	 * @param totalGuiasPagamentoGerencia O totalGuiasPagamentoGerencia a ser setado.
	 */
	public void setTotalGuiasPagamentoGerencia(
			BigDecimal totalGuiasPagamentoGerencia) {
		this.totalGuiasPagamentoGerencia = totalGuiasPagamentoGerencia;
	}

	/**
	 * @return Retorna o campo totalGuiasPagamentoLocalidade.
	 */
	public BigDecimal getTotalGuiasPagamentoLocalidade() {
		return totalGuiasPagamentoLocalidade;
	}

	/**
	 * @param totalGuiasPagamentoLocalidade O totalGuiasPagamentoLocalidade a ser setado.
	 */
	public void setTotalGuiasPagamentoLocalidade(
			BigDecimal totalGuiasPagamentoLocalidade) {
		this.totalGuiasPagamentoLocalidade = totalGuiasPagamentoLocalidade;
	}

	/**
	 * @return Retorna o campo totalGuiasPagamentoTotal.
	 */
	public BigDecimal getTotalGuiasPagamentoTotal() {
		return totalGuiasPagamentoTotal;
	}

	/**
	 * @param totalGuiasPagamentoTotal O totalGuiasPagamentoTotal a ser setado.
	 */
	public void setTotalGuiasPagamentoTotal(BigDecimal totalGuiasPagamentoTotal) {
		this.totalGuiasPagamentoTotal = totalGuiasPagamentoTotal;
	}

	/**
	 * @return Retorna o campo subcategoriaPrincipal.
	 */
	public String getSubcategoriaPrincipal() {
		return subcategoriaPrincipal;
	}

	/**
	 * @param subcategoriaPrincipal O subcategoriaPrincipal a ser setado.
	 */
	public void setSubcategoriaPrincipal(String subcategoriaPrincipal) {
		this.subcategoriaPrincipal = subcategoriaPrincipal;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String getSequencialRota() {
		return sequencialRota;
	}

	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}

	public String getMensagemNaoExisteDados() {
		return mensagemNaoExisteDados;
	}

	public void setMensagemNaoExisteDados(String mensagemNaoExisteDados) {
		this.mensagemNaoExisteDados = mensagemNaoExisteDados;
	}
	
}
