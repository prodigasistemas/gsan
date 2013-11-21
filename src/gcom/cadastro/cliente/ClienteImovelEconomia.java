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
