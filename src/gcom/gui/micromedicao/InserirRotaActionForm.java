package gcom.gui.micromedicao;

import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.*;

/**
 * Form utilizado no Inserir Rota e no Atualizar Rota
 * 
 * @author Vivianne Sousa
 * @created 21/03/2006
 */
public class InserirRotaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idLocalidade;
	private String localidadeDescricao;
	private String codigoSetorComercial;
	private String setorComercialDescricao;
	private String codigoRota;
	private String cobrancaGrupo;
	private String faturamentoGrupo;
	private String leituraTipo;
	private String empresaLeituristica;
	private String empresaCobranca;
	private String empresaEntregaContas;
	private String cobrancaCriterio;
	private String dataAjusteLeitura;
	private String indicadorAjusteConsumo;
	private String numeroDiasConsumoAjuste;
	private String indicadorFiscalizarCortado;
	private String indicadorFiscalizarSuprimido;
	private String indicadorGerarFalsaFaixa;
	private String percentualGeracaoFaixaFalsa;
	private String indicadorGerarFiscalizacao;
	private String percentualGeracaoFiscalizacao;
	private String indicadorUso;
	private String ultimaAlteracao;

	private String cobrancaAcao;
	private String idCobrancaCriterio;
	private String descricaoCobrancaCriterio;
	private String idLeiturista;
	private String nomeLeiturista;
	private String indicadorRotaAlternativa;
	private String indicadorTransmissaoOffline;
	private String indicadorSequencialLeitura;
	private String indicadorImpressaoTermicaFinalGrupo;

	private String numeroLimiteImoveis;

	public String getNomeLeiturista() {
		return nomeLeiturista;
	}

	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
	}

	public String getCobrancaAcao() {
		return cobrancaAcao;
	}

	public void setCobrancaAcao(String cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}

	public String getDescricaoCobrancaCriterio() {
		return descricaoCobrancaCriterio;
	}

	public void setDescricaoCobrancaCriterio(String descricaoCobrancaCriterio) {
		this.descricaoCobrancaCriterio = descricaoCobrancaCriterio;
	}

	public String getIdCobrancaCriterio() {
		return idCobrancaCriterio;
	}

	public void setIdCobrancaCriterio(String idCobrancaCriterio) {
		this.idCobrancaCriterio = idCobrancaCriterio;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getCobrancaGrupo() {
		return cobrancaGrupo;
	}

	public void setCobrancaGrupo(String cobrancaGrupo) {
		this.cobrancaGrupo = cobrancaGrupo;
	}

	public String getEmpresaLeituristica() {
		return empresaLeituristica;
	}

	public void setEmpresaLeituristica(String empresaLeituristica) {
		this.empresaLeituristica = empresaLeituristica;
	}

	public String getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	public void setFaturamentoGrupo(String faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getIndicadorFiscalizarCortado() {
		return indicadorFiscalizarCortado;
	}

	public void setIndicadorFiscalizarCortado(String indicadorFiscalizarCortado) {
		this.indicadorFiscalizarCortado = indicadorFiscalizarCortado;
	}

	public String getIndicadorFiscalizarSuprimido() {
		return indicadorFiscalizarSuprimido;
	}

	public void setIndicadorFiscalizarSuprimido(
			String indicadorFiscalizarSuprimido) {
		this.indicadorFiscalizarSuprimido = indicadorFiscalizarSuprimido;
	}

	public String getIndicadorGerarFalsaFaixa() {
		return indicadorGerarFalsaFaixa;
	}

	public void setIndicadorGerarFalsaFaixa(String indicadorGerarFalsaFaixa) {
		this.indicadorGerarFalsaFaixa = indicadorGerarFalsaFaixa;
	}

	public String getIndicadorGerarFiscalizacao() {
		return indicadorGerarFiscalizacao;
	}

	public void setIndicadorGerarFiscalizacao(String indicadorGerarFiscalizacao) {
		this.indicadorGerarFiscalizacao = indicadorGerarFiscalizacao;
	}

	public String getLeituraTipo() {
		return leituraTipo;
	}

	public void setLeituraTipo(String leituraTipo) {
		this.leituraTipo = leituraTipo;
	}

	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}

	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}

	public String getPercentualGeracaoFaixaFalsa() {
		return percentualGeracaoFaixaFalsa;
	}

	public void setPercentualGeracaoFaixaFalsa(
			String percentualGeracaoFaixaFalsa) {
		this.percentualGeracaoFaixaFalsa = percentualGeracaoFaixaFalsa;
	}

	public String getPercentualGeracaoFiscalizacao() {
		return percentualGeracaoFiscalizacao;
	}

	public void setPercentualGeracaoFiscalizacao(
			String percentualGeracaoFiscalizacao) {
		this.percentualGeracaoFiscalizacao = percentualGeracaoFiscalizacao;
	}

	public String getSetorComercialDescricao() {
		return setorComercialDescricao;
	}

	public void setSetorComercialDescricao(String setorComercialDescricao) {
		this.setorComercialDescricao = setorComercialDescricao;
	}

	public String getCobrancaCriterio() {
		return cobrancaCriterio;
	}

	public void setCobrancaCriterio(String cobrancaCriterio) {
		this.cobrancaCriterio = cobrancaCriterio;
	}

	public String getDataAjusteLeitura() {
		return dataAjusteLeitura;
	}

	public void setDataAjusteLeitura(String dataAjusteLeitura) {
		this.dataAjusteLeitura = dataAjusteLeitura;
	}

	public String getIndicadorAjusteConsumo() {
		return indicadorAjusteConsumo;
	}

	public void setIndicadorAjusteConsumo(String indicadorAjusteConsumo) {
		this.indicadorAjusteConsumo = indicadorAjusteConsumo;
	}

	public String getNumeroDiasConsumoAjuste() {
		return numeroDiasConsumoAjuste;
	}

	public void setNumeroDiasConsumoAjuste(String numeroDiasConsumoAjuste) {
		this.numeroDiasConsumoAjuste = numeroDiasConsumoAjuste;
	}

	public String getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}

	public String getIndicadorRotaAlternativa() {
		return indicadorRotaAlternativa;
	}

	public void setIndicadorRotaAlternativa(String indicadorRotaAlternativa) {
		this.indicadorRotaAlternativa = indicadorRotaAlternativa;
	}

	public String getIndicadorTransmissaoOffline() {
		return indicadorTransmissaoOffline;
	}

	public void setIndicadorTransmissaoOffline(
			String indicadorTransmissaoOffline) {
		this.indicadorTransmissaoOffline = indicadorTransmissaoOffline;
	}

	public String getIndicadorImpressaoTermicaFinalGrupo() {
		return indicadorImpressaoTermicaFinalGrupo;
	}

	public void setIndicadorImpressaoTermicaFinalGrupo(
			String indicadorImpressaoTermicaFinalGrupo) {
		this.indicadorImpressaoTermicaFinalGrupo = indicadorImpressaoTermicaFinalGrupo;
	}

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 */
	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		idLocalidade = null;
		localidadeDescricao = null;
		codigoSetorComercial = null;
		setorComercialDescricao = null;
		cobrancaGrupo = null;
		faturamentoGrupo = null;
		leituraTipo = null;
		empresaLeituristica = null;
		cobrancaCriterio = null;
		dataAjusteLeitura = null;
		indicadorAjusteConsumo = null;
		numeroDiasConsumoAjuste = null;
		indicadorFiscalizarCortado = null;
		indicadorFiscalizarSuprimido = null;
		indicadorGerarFalsaFaixa = null;
		percentualGeracaoFaixaFalsa = null;
		indicadorGerarFiscalizacao = null;
		indicadorTransmissaoOffline = null;
		percentualGeracaoFiscalizacao = null;
		indicadorUso = ConstantesSistema.INDICADOR_USO_ATIVO.toString();
		ultimaAlteracao = null;
	}

	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo empresaCobranca.
	 */
	public String getEmpresaCobranca() {
		return empresaCobranca;
	}

	/**
	 * @param empresaCobranca
	 *            O empresaCobranca a ser setado.
	 */
	public void setEmpresaCobranca(String empresaCobranca) {
		this.empresaCobranca = empresaCobranca;
	}

	public String getIdLeiturista() {
		return idLeiturista;
	}

	public void setIdLeiturista(String idLeiturista) {
		this.idLeiturista = idLeiturista;
	}

	public String getEmpresaEntregaContas() {
		return empresaEntregaContas;
	}

	public void setEmpresaEntregaContas(String empresaEntregaContas) {
		this.empresaEntregaContas = empresaEntregaContas;
	}

	public String getNumeroLimiteImoveis() {
		return numeroLimiteImoveis;
	}

	public void setNumeroLimiteImoveis(String numeroLimiteImoveis) {
		this.numeroLimiteImoveis = numeroLimiteImoveis;
	}

	public String getIndicadorSequencialLeitura() {
		return indicadorSequencialLeitura;
	}

	public void setIndicadorSequencialLeitura(String indicadorSequencialLeitura) {
		this.indicadorSequencialLeitura = indicadorSequencialLeitura;
	}

}
