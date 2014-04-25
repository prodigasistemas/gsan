package gcom.relatorio.cobranca.spcserasa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import gcom.relatorio.RelatorioBean;

/**
 * @author Ivan Sergio, Mariana Victor
 * @created 04/02/2011, 10/02/2011
 */

public class RelatorioAcompanhamentoClientesNegativadosSinteticoBean implements RelatorioBean {
	  
	private JRBeanCollectionDataSource arrayJRDadosParcelamento;
	private ArrayList arrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean;
	
	private JRBeanCollectionDataSource arrayJRParametrosSelecionados;
	private ArrayList arrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean;
	
	private String idNegativador;
	private String negativador;
	
    private String nomeCliente;
    private String matricula;
    private String cpfCnpj;
    private BigDecimal valorNegativado;
    private BigDecimal valorPago;
    private String situacao;
    
	private String periodoEnvioNegativacao;
    private String localidade;
    private String idLocalidade;
    private String gerenciaRegional;
    private String idGerenciaRegional;
    private String unidadeNegocio;
    private String idUnidadeNegocio;
    
    private Integer quantidadeEnviados;
    private BigDecimal valorDebitos;
    private Integer quantidadePagos;
    private BigDecimal valorPagoUnidade;
    private Integer quantidadeLocalidade;

	public RelatorioAcompanhamentoClientesNegativadosSinteticoBean(
			String nomeCliente, 
			String matricula, 
			String cpfCnpj, 
			BigDecimal valorNegativado, 
			BigDecimal valorPago,
			String situacao, 
			String periodoEnvioNegativacao, 
			String localidade, 
			String idLocalidade,
			String idNegativador, 
			String negativador, 
			Collection colecaoDadosParcelamento,
		    RelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean parametrosSelecionadosBean,
			Integer quantidadeLocalidade,
			Integer quantidadePagos) {
		super();
		this.nomeCliente = nomeCliente;
		this.matricula = matricula;
		this.cpfCnpj = cpfCnpj;
		this.valorNegativado = valorNegativado;
		this.valorPago = valorPago;
		this.situacao = situacao;
		this.periodoEnvioNegativacao = periodoEnvioNegativacao;
		this.localidade = localidade;
		this.idLocalidade = idLocalidade;
		this.idNegativador = idNegativador;
		this.negativador = negativador;
		
		this.arrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean = new ArrayList();
		this.arrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean.addAll(colecaoDadosParcelamento);
		this.arrayJRDadosParcelamento = new JRBeanCollectionDataSource(this.arrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean);
		
		this.arrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean = new ArrayList();
		this.arrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean.add(parametrosSelecionadosBean);
		this.arrayJRParametrosSelecionados = new JRBeanCollectionDataSource(this.arrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean);
				
		this.quantidadeLocalidade = quantidadeLocalidade;
		this.quantidadePagos = quantidadePagos;
	}

	/**
	 * @return Retorna o campo cpfCnpj.
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * @param cpfCnpj O cpfCnpj a ser setado.
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
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
	 * @return Retorna o campo matricula.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula O matricula a ser setado.
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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
	 * @return Retorna o campo periodoEnvioNegativacao.
	 */
	public String getPeriodoEnvioNegativacao() {
		return periodoEnvioNegativacao;
	}

	/**
	 * @param periodoEnvioNegativacao O periodoEnvioNegativacao a ser setado.
	 */
	public void setPeriodoEnvioNegativacao(String periodoEnvioNegativacao) {
		this.periodoEnvioNegativacao = periodoEnvioNegativacao;
	}

	/**
	 * @return Retorna o campo situacao.
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao O situacao a ser setado.
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	/**
	 * @return Retorna o campo valorNegativado.
	 */
	public BigDecimal getValorNegativado() {
		return valorNegativado;
	}

	/**
	 * @param valorNegativado O valorNegativado a ser setado.
	 */
	public void setValorNegativado(BigDecimal valorNegativado) {
		this.valorNegativado = valorNegativado;
	}

	/**
	 * @return Retorna o campo valorPago.
	 */
	public BigDecimal getValorPago() {
		return valorPago;
	}

	/**
	 * @param valorPago O valorPago a ser setado.
	 */
	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	/**
	 * @return Retorna o campo idNegativador.
	 */
	public String getIdNegativador() {
		return idNegativador;
	}

	/**
	 * @param idNegativador O idNegativador a ser setado.
	 */
	public void setIdNegativador(String idNegativador) {
		this.idNegativador = idNegativador;
	}

	/**
	 * @return Retorna o campo negativador.
	 */
	public String getNegativador() {
		return negativador;
	}

	/**
	 * @param negativador O negativador a ser setado.
	 */
	public void setNegativador(String negativador) {
		this.negativador = negativador;
	}

	public JRBeanCollectionDataSource getArrayJRDadosParcelamento() {
		return arrayJRDadosParcelamento;
	}

	public void setArrayJRDadosParcelamento(
			JRBeanCollectionDataSource arrayJRDadosParcelamento) {
		this.arrayJRDadosParcelamento = arrayJRDadosParcelamento;
	}

	public ArrayList getArrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean() {
		return arrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean;
	}

	public void setArrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean(
			ArrayList arrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean) {
		this.arrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean = arrayRelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean;
	}

	public JRBeanCollectionDataSource getArrayJRParametrosSelecionados() {
		return arrayJRParametrosSelecionados;
	}

	public void setArrayJRParametrosSelecionados(
			JRBeanCollectionDataSource arrayJRParametrosSelecionados) {
		this.arrayJRParametrosSelecionados = arrayJRParametrosSelecionados;
	}

	public ArrayList getArrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean() {
		return arrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean;
	}

	public void setArrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean(
			ArrayList arrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean) {
		this.arrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean = arrayRelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Integer getQuantidadeEnviados() {
		return quantidadeEnviados;
	}

	public void setQuantidadeEnviados(Integer quantidadeEnviados) {
		this.quantidadeEnviados = quantidadeEnviados;
	}

	public Integer getQuantidadePagos() {
		return quantidadePagos;
	}

	public void setQuantidadePagos(Integer quantidadePagos) {
		this.quantidadePagos = quantidadePagos;
	}

	public BigDecimal getValorDebitos() {
		return valorDebitos;
	}

	public void setValorDebitos(BigDecimal valorDebitos) {
		this.valorDebitos = valorDebitos;
	}

	public BigDecimal getValorPagoUnidade() {
		return valorPagoUnidade;
	}

	public void setValorPagoUnidade(BigDecimal valorPagoUnidade) {
		this.valorPagoUnidade = valorPagoUnidade;
	}

	public Integer getQuantidadeLocalidade() {
		return quantidadeLocalidade;
	}

	public void setQuantidadeLocalidade(Integer quantidadeLocalidade) {
		this.quantidadeLocalidade = quantidadeLocalidade;
	}
	
}
