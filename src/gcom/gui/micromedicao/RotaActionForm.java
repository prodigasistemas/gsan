package gcom.gui.micromedicao;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @author Vivianne Sousa
 * @created 28/03/2006
 */
public class RotaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String indicadorAtualizar;
	private String id;
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
	private String cobrancaCriterio;
	private String dataAjusteLeitura;
	private String indicadorAjusteConsumo;
	private String indicadorFiscalizarCortado;			
	private String indicadorFiscalizarSuprimido;			
	private String indicadorGerarFalsaFaixa;			
	private String percentualGeracaoFaixaFalsa;			
	private String indicadorGerarFiscalizacao;		
	private String indicadorRotaAlternativa;
	private String percentualGeracaoFiscalizacao;
	private String indicadorUso;
	private String empresaEntregaContas;
	private String indicadorTransmissaoOffline;
	private String indicadorImpressaoTermicaFinalGrupo;
	
	public String getCobrancaCriterio() {
		return cobrancaCriterio;
	}
	public void setCobrancaCriterio(String cobrancaCriterio) {
		this.cobrancaCriterio = cobrancaCriterio;
	}
	public String getCobrancaGrupo() {
		return cobrancaGrupo;
	}
	public void setCobrancaGrupo(String cobrancaGrupo) {
		this.cobrancaGrupo = cobrancaGrupo;
	}
	public String getCodigoRota() {
		return codigoRota;
	}
	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public String getDataAjusteLeitura() {
		return dataAjusteLeitura;
	}
	public void setDataAjusteLeitura(String dataAjusteLeitura) {
		this.dataAjusteLeitura = dataAjusteLeitura;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getIndicadorAjusteConsumo() {
		return indicadorAjusteConsumo;
	}
	public void setIndicadorAjusteConsumo(String indicadorAjusteConsumo) {
		this.indicadorAjusteConsumo = indicadorAjusteConsumo;
	}
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
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
	public void setIndicadorFiscalizarSuprimido(String indicadorFiscalizarSuprimido) {
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
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
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
	public void setPercentualGeracaoFaixaFalsa(String percentualGeracaoFaixaFalsa) {
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
	/**
	 * @return Retorna o campo empresaCobranca.
	 */
	public String getEmpresaCobranca() {
		return empresaCobranca;
	}
	/**
	 * @param empresaCobranca O empresaCobranca a ser setado.
	 */
	public void setEmpresaCobranca(String empresaCobranca) {
		this.empresaCobranca = empresaCobranca;
	}
	public String getEmpresaEntregaContas() {
		return empresaEntregaContas;
	}
	public void setEmpresaEntregaContas(String empresaEntregaContas) {
		this.empresaEntregaContas = empresaEntregaContas;
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
	public void setIndicadorTransmissaoOffline(String indicadorTransmissaoOffline) {
		this.indicadorTransmissaoOffline = indicadorTransmissaoOffline;
	}
	public String getIndicadorImpressaoTermicaFinalGrupo() {
		return indicadorImpressaoTermicaFinalGrupo;
	}
	public void setIndicadorImpressaoTermicaFinalGrupo(String indicadorImpressaoTermicaFinalGrupo) {
		this.indicadorImpressaoTermicaFinalGrupo = indicadorImpressaoTermicaFinalGrupo;
	}
	
	
	
}
 
