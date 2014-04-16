package gcom.cadastro.cliente;

import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class ClienteImovelEconomia extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	public Filtro retornaFiltro() {

		FiltroClienteImovelEconomia filtroClienteImovelEconomia = new FiltroClienteImovelEconomia();
		filtroClienteImovelEconomia.adicionarParametro(new ParametroSimples(
				FiltroClienteImovelEconomia.ID, this.getId()));
		filtroClienteImovelEconomia
				.adicionarCaminhoParaCarregamentoEntidade("clienteImovelFimRelacaoMotivo");
		filtroClienteImovelEconomia
				.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroClienteImovelEconomia
				.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
		filtroClienteImovelEconomia
				.adicionarCaminhoParaCarregamentoEntidade("imovelEconomia");
		return filtroClienteImovelEconomia;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	/** identifier field */
	private Integer id;

	/** persistent field */
	@ControleAlteracao(funcionalidade={TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})
	private Date dataInicioRelacao;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})
	private Date dataFimRelacao;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	@ControleAlteracao(value=FiltroClienteImovel.CLIENTE_IMOVEL_FIM_RELACAO_MOTIVO,
			funcionalidade={TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})
	private gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo;

	/** persistent field */
	private gcom.cadastro.cliente.Cliente cliente;

	/** persistent field */
	private gcom.cadastro.cliente.ClienteRelacaoTipo clienteRelacaoTipo;

	/** persistent field */
	private ImovelEconomia imovelEconomia;

	/** full constructor */
	public ClienteImovelEconomia(
			Date dataInicioRelacao,
			Date dataFimRelacao,
			Date ultimaAlteracao,
			gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo,
			gcom.cadastro.cliente.Cliente cliente,
			gcom.cadastro.cliente.ClienteRelacaoTipo clienteRelacaoTipo,
			ImovelEconomia imovelEconomia) {
		this.dataInicioRelacao = dataInicioRelacao;
		this.dataFimRelacao = dataFimRelacao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.clienteImovelFimRelacaoMotivo = clienteImovelFimRelacaoMotivo;
		this.cliente = cliente;
		this.clienteRelacaoTipo = clienteRelacaoTipo;
		this.imovelEconomia = imovelEconomia;
	}

	/** default constructor */
	public ClienteImovelEconomia() {
	}

	/** minimal constructor */
	public ClienteImovelEconomia(
			Date dataInicioRelacao,
			gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo,
			gcom.cadastro.cliente.Cliente cliente,
			gcom.cadastro.cliente.ClienteRelacaoTipo clienteRelacaoTipo,
			ImovelEconomia imovelEconomia) {
		this.dataInicioRelacao = dataInicioRelacao;
		this.clienteImovelFimRelacaoMotivo = clienteImovelFimRelacaoMotivo;
		this.cliente = cliente;
		this.clienteRelacaoTipo = clienteRelacaoTipo;
		this.imovelEconomia = imovelEconomia;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataInicioRelacao() {
		return this.dataInicioRelacao;
	}

	public void setDataInicioRelacao(Date dataInicioRelacao) {
		this.dataInicioRelacao = dataInicioRelacao;
	}

	public Date getDataFimRelacao() {
		return this.dataFimRelacao;
	}

	public void setDataFimRelacao(Date dataFimRelacao) {
		this.dataFimRelacao = dataFimRelacao;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo getClienteImovelFimRelacaoMotivo() {
		return this.clienteImovelFimRelacaoMotivo;
	}

	public void setClienteImovelFimRelacaoMotivo(
			gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo) {
		this.clienteImovelFimRelacaoMotivo = clienteImovelFimRelacaoMotivo;
	}

	public gcom.cadastro.cliente.Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(gcom.cadastro.cliente.Cliente cliente) {
		this.cliente = cliente;
	}

	public gcom.cadastro.cliente.ClienteRelacaoTipo getClienteRelacaoTipo() {
		return this.clienteRelacaoTipo;
	}

	public void setClienteRelacaoTipo(
			gcom.cadastro.cliente.ClienteRelacaoTipo clienteRelacaoTipo) {
		this.clienteRelacaoTipo = clienteRelacaoTipo;
	}

	public ImovelEconomia getImovelEconomia() {
		return this.imovelEconomia;
	}

	public void setImovelEconomia(ImovelEconomia imovelEconomia) {
		this.imovelEconomia = imovelEconomia;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param other
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof ClienteImovelEconomia)) {
			return false;
		}
		ClienteImovelEconomia castOther = (ClienteImovelEconomia) other;

		return ((this.getCliente().getId().equals(castOther.getCliente()
				.getId())) && (this.getClienteRelacaoTipo().getId()
				.equals(castOther.getClienteRelacaoTipo().getId())));
	}

	/**
	 * Description of the Method
	 * 
	 * @return Description of the Return Value
	 */
	public int hashCode() {
		if (this.ultimaAlteracao != null){
			return this.ultimaAlteracao.hashCode();
		}
		return super.hashCode();
	}
	
	public String getDescricao(){
		String ret = "";
		if (getCliente() != null){
			ret = getCliente().getNome();
		}
		return ret;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getDescricao() + " (" + this.getClienteRelacaoTipo().getDescricao() + ")";
	}

	@Override
	public void initializeLazy() {
		getCliente();
		if (getClienteRelacaoTipo() != null) {
			getClienteRelacaoTipo().initializeLazy();
		}
		if (getClienteImovelFimRelacaoMotivo() != null){
			getClienteImovelFimRelacaoMotivo().initializeLazy();
		}
	}
	
}
