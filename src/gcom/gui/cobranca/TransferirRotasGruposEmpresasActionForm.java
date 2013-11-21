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
* Anderson Italo Felinto de Lima
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

package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

public class TransferirRotasGruposEmpresasActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String[] idGrupoFaturamentoFiltro;
	private String[] idGrupoCobrancaFiltro;
	private String[] idEmpresaFaturamentoFiltro;
	private String[] idEmpresaCobrancaFiltro;
	private String[] idGerenciaRegional;
	private String[] idUnidadeNegocio;
	private String idLocalidadeInicial;
	private String descricaoLocalidadeInicial;
	private String idSetorComercialInicial;
	private String descricaoSetorComercialInicial;
	private String idRotaInicial;
	private String idLocalidadeFinal;
	private String descricaoLocalidadeFinal;
	private String idSetorComercialFinal;
	private String descricaoSetorComercialFinal;
	private String idRotaFinal;
	private String quantidadeRotas;
	private String[] idGrupoFaturamentoSelecao;
	private String[] idGrupoCobrancaSelecao;
	private String[] idEmpresaFaturamentoSelecao;
	private String[] idEmpresaCobrancaSelecao;
	private String idGrupoFaturamentoDestino;
	private String idGrupoCobrancaDestino;
	private String idEmpresaFaturamentoDestino;
	private String idEmpresaCobrancaDestino;
	private String idTipoLeitura;
	private String idLeiturista;
	private String nomeLeiturista;
	
	
	public String getIdLeiturista() {
		return idLeiturista;
	}
	public void setIdLeiturista(String idLeiturista) {
		this.idLeiturista = idLeiturista;
	}
	public String getNomeLeiturista() {
		return nomeLeiturista;
	}
	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
	}
	public String getIdTipoLeitura() {
		return idTipoLeitura;
	}
	public void setIdTipoLeitura(String idTipoLeitura) {
		this.idTipoLeitura = idTipoLeitura;
	}
	public String getIdEmpresaCobrancaDestino() {
		return idEmpresaCobrancaDestino;
	}
	public void setIdEmpresaCobrancaDestino(String idEmpresaCobrancaDestino) {
		this.idEmpresaCobrancaDestino = idEmpresaCobrancaDestino;
	}
	public String[] getIdEmpresaCobrancaFiltro() {
		return idEmpresaCobrancaFiltro;
	}
	public void setIdEmpresaCobrancaFiltro(String[] idEmpresaCobrancaFiltro) {
		this.idEmpresaCobrancaFiltro = idEmpresaCobrancaFiltro;
	}
	public String[] getIdEmpresaCobrancaSelecao() {
		return idEmpresaCobrancaSelecao;
	}
	public void setIdEmpresaCobrancaSelecao(String[] idEmpresaCobrancaSelecao) {
		this.idEmpresaCobrancaSelecao = idEmpresaCobrancaSelecao;
	}
	public String getIdEmpresaFaturamentoDestino() {
		return idEmpresaFaturamentoDestino;
	}
	public void setIdEmpresaFaturamentoDestino(String idEmpresaFaturamentoDestino) {
		this.idEmpresaFaturamentoDestino = idEmpresaFaturamentoDestino;
	}
	public String[] getIdEmpresaFaturamentoFiltro() {
		return idEmpresaFaturamentoFiltro;
	}
	public void setIdEmpresaFaturamentoFiltro(String[] idEmpresaFaturamentoFiltro) {
		this.idEmpresaFaturamentoFiltro = idEmpresaFaturamentoFiltro;
	}
	public String[] getIdEmpresaFaturamentoSelecao() {
		return idEmpresaFaturamentoSelecao;
	}
	public void setIdEmpresaFaturamentoSelecao(String[] idEmpresaFaturamentoSelecao) {
		this.idEmpresaFaturamentoSelecao = idEmpresaFaturamentoSelecao;
	}
	public String[] getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(String[] idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public String getIdGrupoCobrancaDestino() {
		return idGrupoCobrancaDestino;
	}
	public void setIdGrupoCobrancaDestino(String idGrupoCobrancaDestino) {
		this.idGrupoCobrancaDestino = idGrupoCobrancaDestino;
	}
	public String[] getIdGrupoCobrancaFiltro() {
		return idGrupoCobrancaFiltro;
	}
	public void setIdGrupoCobrancaFiltro(String[] idGrupoCobrancaFiltro) {
		this.idGrupoCobrancaFiltro = idGrupoCobrancaFiltro;
	}
	public String[] getIdGrupoCobrancaSelecao() {
		return idGrupoCobrancaSelecao;
	}
	public void setIdGrupoCobrancaSelecao(String[] idGrupoCobrancaSelecao) {
		this.idGrupoCobrancaSelecao = idGrupoCobrancaSelecao;
	}
	public String getIdGrupoFaturamentoDestino() {
		return idGrupoFaturamentoDestino;
	}
	public void setIdGrupoFaturamentoDestino(String idGrupoFaturamentoDestino) {
		this.idGrupoFaturamentoDestino = idGrupoFaturamentoDestino;
	}
	public String[] getIdGrupoFaturamentoFiltro() {
		return idGrupoFaturamentoFiltro;
	}
	public void setIdGrupoFaturamentoFiltro(String[] idGrupoFaturamentoFiltro) {
		this.idGrupoFaturamentoFiltro = idGrupoFaturamentoFiltro;
	}
	public String[] getIdGrupoFaturamentoSelecao() {
		return idGrupoFaturamentoSelecao;
	}
	public void setIdGrupoFaturamentoSelecao(String[] idGrupoFaturamentoSelecao) {
		this.idGrupoFaturamentoSelecao = idGrupoFaturamentoSelecao;
	}
	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}
	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}
	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}
	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}
	public String getIdRotaFinal() {
		return idRotaFinal;
	}
	public void setIdRotaFinal(String idRotaFinal) {
		this.idRotaFinal = idRotaFinal;
	}
	public String getIdRotaInicial() {
		return idRotaInicial;
	}
	public void setIdRotaInicial(String idRotaInicial) {
		this.idRotaInicial = idRotaInicial;
	}
	public String getIdSetorComercialFinal() {
		return idSetorComercialFinal;
	}
	public void setIdSetorComercialFinal(String idSetorComercialFinal) {
		this.idSetorComercialFinal = idSetorComercialFinal;
	}
	public String getIdSetorComercialInicial() {
		return idSetorComercialInicial;
	}
	public void setIdSetorComercialInicial(String idSetorComercialInicial) {
		this.idSetorComercialInicial = idSetorComercialInicial;
	}
	public String[] getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(String[] idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public String getQuantidadeRotas() {
		return quantidadeRotas;
	}
	public void setQuantidadeRotas(String quantidadeRotas) {
		this.quantidadeRotas = quantidadeRotas;
	}
	public String getDescricaoLocalidadeFinal() {
		return descricaoLocalidadeFinal;
	}
	public void setDescricaoLocalidadeFinal(String descricaoLocalidadeFinal) {
		this.descricaoLocalidadeFinal = descricaoLocalidadeFinal;
	}
	public String getDescricaoLocalidadeInicial() {
		return descricaoLocalidadeInicial;
	}
	public void setDescricaoLocalidadeInicial(String descricaoLocalidadeInicial) {
		this.descricaoLocalidadeInicial = descricaoLocalidadeInicial;
	}
	public String getDescricaoSetorComercialFinal() {
		return descricaoSetorComercialFinal;
	}
	public void setDescricaoSetorComercialFinal(String descricaoSetorComercialFinal) {
		this.descricaoSetorComercialFinal = descricaoSetorComercialFinal;
	}
	public String getDescricaoSetorComercialInicial() {
		return descricaoSetorComercialInicial;
	}
	public void setDescricaoSetorComercialInicial(
			String descricaoSetorComercialInicial) {
		this.descricaoSetorComercialInicial = descricaoSetorComercialInicial;
	}

}
