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
package gcom.gui.cadastro.atualizacaocadastral;

import org.apache.struts.action.ActionForm;

public class FiltrarAlteracaoAtualizacaoCadastralActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

    private String idEmpresa;
    
    private String nomeEmpresa;
    
    private String idLeiturista;
    
    private String nomeLeiturista;
    
    private String periodoRealizacaoInicial;
    
    private String periodoRealizacaoFinal;
    
	private String idRegistrosNaoAutorizados;
	
	private String idRegistrosAutorizados;
	
	private String exibirCampos;
	
	private String[] colunaImoveisSelecionados;
	
	private String idLocalidadeInicial;	

	private String idLocalidadeFinal;

	private String nomeLocalidadeInicial;	

	private String nomeLocalidadeFinal;
	
	private String cdSetorComercialInicial;	
	
	private String cdSetorComercialFinal;
	
	private String nomeSetorComercialInicial;	
	
	private String nomeSetorComercialFinal;
	
	private String cdRotaInicial;

	private String cdRotaFinal;

	public String getIdRegistrosAutorizados() {
		return idRegistrosAutorizados;
	}

	public void setIdRegistrosAutorizados(String idRegistrosAutorizados) {
		this.idRegistrosAutorizados = idRegistrosAutorizados;
	}

	public String getIdRegistrosNaoAutorizados() {
		return idRegistrosNaoAutorizados;
	}

	public void setIdRegistrosNaoAutorizados(String idRegistrosNaoAutorizados) {
		this.idRegistrosNaoAutorizados = idRegistrosNaoAutorizados;
	}

	public String getPeriodoRealizacaoFinal() {
		return periodoRealizacaoFinal;
	}

	public void setPeriodoRealizacaoFinal(String periodoRealizacaoFinal) {
		this.periodoRealizacaoFinal = periodoRealizacaoFinal;
	}

	public String getPeriodoRealizacaoInicial() {
		return periodoRealizacaoInicial;
	}

	public void setPeriodoRealizacaoInicial(String periodoRealizacaoInicial) {
		this.periodoRealizacaoInicial = periodoRealizacaoInicial;
	}

	public String getNomeLeiturista() {
		return nomeLeiturista;
	}

	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
	}

	public String getIdLeiturista() {
		return idLeiturista;
	}

	public void setIdLeiturista(String idLeiturista) {
		this.idLeiturista = idLeiturista;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String[] getColunaImoveisSelecionados() {
		return colunaImoveisSelecionados;
	}

	public void setColunaImoveisSelecionados(String[] colunaImoveisSelecionados) {
		this.colunaImoveisSelecionados = colunaImoveisSelecionados;
	}

	public String getExibirCampos() {
		return exibirCampos;
	}

	public void setExibirCampos(String exibirCampos) {
		this.exibirCampos = exibirCampos;
	}

	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}

	public String getCdSetorComercialInicial() {
		return cdSetorComercialInicial;
	}

	public void setCdSetorComercialInicial(String setorInicial) {
		this.cdSetorComercialInicial = setorInicial;
	}

	public String getCdSetorComercialFinal() {
		return cdSetorComercialFinal;
	}

	public void setCdSetorComercialFinal(String setorFinal) {
		this.cdSetorComercialFinal = setorFinal;
	}

	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}

	public void setNomeSetorComercialInicial(String nomeSetorInicial) {
		this.nomeSetorComercialInicial = nomeSetorInicial;
	}

	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}

	public void setNomeSetorComercialFinal(String nomeSetorFinal) {
		this.nomeSetorComercialFinal = nomeSetorFinal;
	}

	public String getCdRotaInicial() {
		return cdRotaInicial;
	}

	public void setCdRotaInicial(String codRotaInicial) {
		this.cdRotaInicial = codRotaInicial;
	}

	public String getCdRotaFinal() {
		return cdRotaFinal;
	}

	public void setCdRotaFinal(String codRotaFinal) {
		this.cdRotaFinal = codRotaFinal;
	}
}