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

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao
public class ClienteFone extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String ddd;

	private String telefone;

	private String ramal;
	
	private String contato;

	private Short indicadorTelefonePadrao;

	private Date ultimaAlteracao;

	private gcom.cadastro.cliente.FoneTipo foneTipo;

	private gcom.cadastro.cliente.Cliente cliente;
	
	public static final Short INDICADOR_FONE_PADRAO = new Short("1");
	
	private Integer idTabelaAtualizacaoCadastralAux;

	public ClienteFone(String ddd, String telefone, String ramal, String contato,
			Short indicadorTelefonePadrao, Date ultimaAlteracao,
			gcom.cadastro.cliente.FoneTipo foneTipo,
			gcom.cadastro.cliente.Cliente cliente) {
		this.ddd = ddd;
		this.telefone = telefone;
		this.ramal = ramal;
		this.contato = contato;
		this.indicadorTelefonePadrao = indicadorTelefonePadrao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.foneTipo = foneTipo;
		this.cliente = cliente;
	}

	public ClienteFone() {
	}

	public ClienteFone(gcom.cadastro.cliente.FoneTipo foneTipo,
			gcom.cadastro.cliente.Cliente cliente) {
		this.foneTipo = foneTipo;
		this.cliente = cliente;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDdd() {
		return this.ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRamal() {
		return this.ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	
	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Short getIndicadorTelefonePadrao() {
		return this.indicadorTelefonePadrao;
	}

	public void setIndicadorTelefonePadrao(Short indicadorTelefonePadrao) {
		this.indicadorTelefonePadrao = indicadorTelefonePadrao;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.cadastro.cliente.FoneTipo getFoneTipo() {
		return this.foneTipo;
	}

	public void setFoneTipo(gcom.cadastro.cliente.FoneTipo foneTipo) {
		this.foneTipo = foneTipo;
	}

	public gcom.cadastro.cliente.Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(gcom.cadastro.cliente.Cliente cliente) {
		this.cliente = cliente;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String getDddTelefone() {
		
		if (this.ddd != null){
			return "(" + this.ddd + ")" + this.telefone;
		}
		return this.telefone;
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof ClienteFone)) {
			return false;
		}
		ClienteFone castOther = (ClienteFone) other;

		return new EqualsBuilder().append(this.getDdd(), castOther.getDdd())
				.append(this.getTelefone(), castOther.getTelefone()).append(
						this.getRamal(), castOther.getRamal()).isEquals();

	}

	@Override
	public Filtro retornaFiltro() {
		Filtro filtro = new FiltroClienteFone();
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteFone.FONE_TIPO);
		filtro.adicionarParametro(
				new ParametroSimples(FiltroClienteFone.ID, this.getId()));
		return filtro; 		
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDddTelefone();
	}

	public Integer getIdTabelaAtualizacaoCadastralAux() {
		return idTabelaAtualizacaoCadastralAux;
	}

	public void setIdTabelaAtualizacaoCadastralAux(
			Integer idTabelaAtualizacaoCadastralAux) {
		this.idTabelaAtualizacaoCadastralAux = idTabelaAtualizacaoCadastralAux;
	}
	
	

}
