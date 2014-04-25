package gcom.relatorio.cadastro;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1076] - Gerar Relatorio Atualizacao Cadastral Via Internet.
 * @author Daniel Alves
 * @date 29/09/2010 
 */
public class RelatorioImoveisDoacoesEntidadeBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String entidade;
	private String imovel;
	private String cliente;
	private String dataAdesao;
	private String dataCancelamento;
	private String usuarioAdesao;
	private String usuarioCancelamento;
	private String mesAnoInicio;
	private String mesAnoFinal;
	private BigDecimal valor;
	
	public RelatorioImoveisDoacoesEntidadeBean(){}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDataAdesao() {
		return dataAdesao;
	}

	public void setDataAdesao(String dataAdesao) {
		this.dataAdesao = dataAdesao;
	}

	public String getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(String dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public String getEntidade() {
		return entidade;
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	public String getImovel() {
		return imovel;
	}

	public void setImovel(String imovel) {
		this.imovel = imovel;
	}

	public String getMesAnoFinal() {
		return mesAnoFinal;
	}

	public void setMesAnoFinal(String mesAnoFinal) {
		this.mesAnoFinal = mesAnoFinal;
	}

	public String getMesAnoInicio() {
		return mesAnoInicio;
	}

	public void setMesAnoInicio(String mesAnoInicio) {
		this.mesAnoInicio = mesAnoInicio;
	}

	public String getUsuarioAdesao() {
		return usuarioAdesao;
	}

	public void setUsuarioAdesao(String usuarioAdesao) {
		this.usuarioAdesao = usuarioAdesao;
	}

	public String getUsuarioCancelamento() {
		return usuarioCancelamento;
	}

	public void setUsuarioCancelamento(String usuarioCancelamento) {
		this.usuarioCancelamento = usuarioCancelamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	
}
