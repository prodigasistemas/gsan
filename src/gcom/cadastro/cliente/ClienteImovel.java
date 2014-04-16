package gcom.cadastro.cliente;

import gcom.atualizacaocadastral.IClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class ClienteImovel extends ObjetoTransacao implements IClienteImovel {
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** persistent field */
	@ControleAlteracao(funcionalidade={Imovel.ATRIBUTOS_IMOVEL_ATUALIZAR,Imovel.ATRIBUTOS_IMOVEL_REMOVER,
			TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL, Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR,Cliente.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private Date dataInicioRelacao;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={Imovel.ATRIBUTOS_IMOVEL_ATUALIZAR,Imovel.ATRIBUTOS_IMOVEL_REMOVER,
			TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL, Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR,Cliente.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private Date dataFimRelacao;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private Imovel imovel;

	/**
	 * persistent field
	 */
	@ControleAlteracao(funcionalidade={Imovel.ATRIBUTOS_IMOVEL_ATUALIZAR,Imovel.ATRIBUTOS_IMOVEL_REMOVER, Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR,Cliente.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private Short indicadorNomeConta;

	/** persistent field */
	@ControleAlteracao(value=FiltroClienteImovel.CLIENTE_IMOVEL_FIM_RELACAO_MOTIVO,
			funcionalidade={Imovel.ATRIBUTOS_IMOVEL_ATUALIZAR,Imovel.ATRIBUTOS_IMOVEL_REMOVER,
			TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL, Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR,Cliente.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo;

	/** persistent field */
	private gcom.cadastro.cliente.Cliente cliente;

	/** persistent field */
	private gcom.cadastro.cliente.ClienteRelacaoTipo clienteRelacaoTipo;

	/** full constructor */
	public ClienteImovel(
			Date dataInicioRelacao,
			Date dataFimRelacao,
			Date ultimaAlteracao,
			Imovel imovel,
			gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo,
			gcom.cadastro.cliente.Cliente cliente,
			gcom.cadastro.cliente.ClienteRelacaoTipo clienteRelacaoTipo) {
		this.dataInicioRelacao = dataInicioRelacao;
		this.dataFimRelacao = dataFimRelacao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.imovel = imovel;
		this.clienteImovelFimRelacaoMotivo = clienteImovelFimRelacaoMotivo;
		this.cliente = cliente;
		this.clienteRelacaoTipo = clienteRelacaoTipo;
	}

	/** default constructor */
	public ClienteImovel() {
	}

	/** minimal constructor */
	public ClienteImovel(
			Date dataInicioRelacao,
			Imovel imovel,
			gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo,
			gcom.cadastro.cliente.Cliente cliente,
			gcom.cadastro.cliente.ClienteRelacaoTipo clienteRelacaoTipo) {
		this.dataInicioRelacao = dataInicioRelacao;
		this.imovel = imovel;
		this.clienteImovelFimRelacaoMotivo = clienteImovelFimRelacaoMotivo;
		this.cliente = cliente;
		this.clienteRelacaoTipo = clienteRelacaoTipo;
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

	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
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

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof ClienteImovel)) {
			return false;
		}
		ClienteImovel castOther = (ClienteImovel) other;

		return ((this.getCliente().getId().equals(castOther.getCliente()
				.getId()))
				&& (this.getClienteRelacaoTipo().getId().equals(castOther
						.getClienteRelacaoTipo().getId())) && (this.getImovel() != null ? this
				.getImovel().getId().equals(castOther.getImovel().getId())
				: true) && (this.getDataInicioRelacao().equals(castOther.getDataInicioRelacao())));
	}

	/**
	 * Description of the Method
	 * 
	 * @return Description of the Return Value
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getCliente()).append(
				getClienteRelacaoTipo()).append(getDataInicioRelacao()).append(
				getUltimaAlteracao()).toHashCode();
	}

	public Short getIndicadorNomeConta() {
		return indicadorNomeConta;
	}

	public void setIndicadorNomeConta(Short indicadorNomeConta) {
		this.indicadorNomeConta = indicadorNomeConta;
	}

	public Filtro retornaFiltro() {
		
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.ID, this.getId()));
		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade("clienteImovelFimRelacaoMotivo");

		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");

		filtroClienteImovel
				.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

		return filtroClienteImovel;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
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
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = super.retornaFiltroRegistroOperacao();
		filtro
			.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.IMOVEL);
		filtro
			.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE_IMOVEL_FIM_RELACAO_MOTIVO);
		filtro.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
		return filtro;
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
}
