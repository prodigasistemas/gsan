package gcom.cobranca.contratoparcelamento;

import gcom.cadastro.cliente.Cliente;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

public class ContratoParcelamentoCliente  extends ObjetoTransacao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private ContratoParcelamento contrato;
	
	private Cliente cliente;
	
	private ContratoParcelamentoCliente clienteSuperior;
	
	private Short indicadorClienteSuperior;
	
	private Date ultimaAlteracao;
	
	public ContratoParcelamentoCliente(){}
	
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroContratoParcelamentoCliente filtro = new FiltroContratoParcelamentoCliente();

		filtro.adicionarParametro(
			new ParametroSimples(FiltroContratoParcelamentoCliente.ID, this.getId()));
		return filtro;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ContratoParcelamentoCliente getClienteSuperior() {
		return clienteSuperior;
	}

	public void setClienteSuperior(ContratoParcelamentoCliente clienteSuperior) {
		this.clienteSuperior = clienteSuperior;
	}

	public ContratoParcelamento getContrato() {
		return contrato;
	}

	public void setContrato(ContratoParcelamento contrato) {
		this.contrato = contrato;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorClienteSuperior() {
		return indicadorClienteSuperior;
	}

	public void setIndicadorClienteSuperior(Short indicadorClienteSuperior) {
		this.indicadorClienteSuperior = indicadorClienteSuperior;
	}

}
