/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.cadastro.cliente;

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
public class ClienteImovel extends ObjetoTransacao {
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
