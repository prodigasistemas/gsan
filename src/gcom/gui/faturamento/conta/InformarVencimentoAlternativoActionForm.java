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
package gcom.gui.faturamento.conta;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InformarVencimentoAlternativoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idImovel;

	private String numeroImovel;

	private String inscricaoImovel;

	private String mesAnoConta;

	private String novoDiaVencimento;

	private String nomeClienteUsuario;

	private String percentualEsgoto;

	private String situacaoAguaConta;

	private String situacaoAguaImovel;

	private String situacaoEsgotoConta;

	private String situacaoEsgotoImovel;

	private String diaVencimentoGrupo;

	private String diaVencimentoAtual;

	private String dataAlteracaoVencimento;
	
	private String diaInicial; 

	private String indicadorVencimentoMesSeguinte;
	
	public String getIndicadorVencimentoMesSeguinte() {
		return indicadorVencimentoMesSeguinte;
	}

	public void setIndicadorVencimentoMesSeguinte(
			String indicadorVencimentoMesSeguinte) {
		this.indicadorVencimentoMesSeguinte = indicadorVencimentoMesSeguinte;
	}

	public String getDiaInicial() {
		return diaInicial;
	}

	public void setDiaInicial(String diaInicial) {
		this.diaInicial = diaInicial;
	}

	public String getDataAlteracaoVencimento() {
		return dataAlteracaoVencimento;
	}

	public void setDataAlteracaoVencimento(String dataAlteracaoVencimento) {
		this.dataAlteracaoVencimento = dataAlteracaoVencimento;
	}

	public String getDiaVencimentoAtual() {
		return diaVencimentoAtual;
	}

	public void setDiaVencimentoAtual(String diaVencimentoAtual) {
		this.diaVencimentoAtual = diaVencimentoAtual;
	}

	public String getDiaVencimentoGrupo() {
		return diaVencimentoGrupo;
	}

	public void setDiaVencimentoGrupo(String diaVencimentoGrupo) {
		this.diaVencimentoGrupo = diaVencimentoGrupo;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getMesAnoConta() {
		return mesAnoConta;
	}

	public void setMesAnoConta(String mesAnoConta) {
		this.mesAnoConta = mesAnoConta;
	}

	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	public String getNovoDiaVencimento() {
		return novoDiaVencimento;
	}

	public void setNovoDiaVencimento(String novoDiaVencimento) {
		this.novoDiaVencimento = novoDiaVencimento;
	}

	public String getPercentualEsgoto() {
		return percentualEsgoto;
	}

	public void setPercentualEsgoto(String percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}

	public String getSituacaoAguaConta() {
		return situacaoAguaConta;
	}

	public void setSituacaoAguaConta(String situacaoAguaConta) {
		this.situacaoAguaConta = situacaoAguaConta;
	}

	public String getSituacaoAguaImovel() {
		return situacaoAguaImovel;
	}

	public void setSituacaoAguaImovel(String situacaoAguaImovel) {
		this.situacaoAguaImovel = situacaoAguaImovel;
	}

	public String getSituacaoEsgotoConta() {
		return situacaoEsgotoConta;
	}

	public void setSituacaoEsgotoConta(String situacaoEsgotoConta) {
		this.situacaoEsgotoConta = situacaoEsgotoConta;
	}

	public String getSituacaoEsgotoImovel() {
		return situacaoEsgotoImovel;
	}

	public void setSituacaoEsgotoImovel(String situacaoEsgotoImovel) {
		this.situacaoEsgotoImovel = situacaoEsgotoImovel;
	}

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		this.idImovel = "";

		this.inscricaoImovel = "";

		this.mesAnoConta = "";

		this.novoDiaVencimento = "";

		this.nomeClienteUsuario = "";

		this.percentualEsgoto = "";

		this.situacaoAguaConta = "";

		this.situacaoAguaImovel = "";

		this.situacaoEsgotoConta = "";

		this.situacaoEsgotoImovel = "";

		this.diaVencimentoGrupo = "";

		this.diaVencimentoAtual = "";

		this.dataAlteracaoVencimento = "";

		this.numeroImovel = "";
		
		this.diaInicial = "";
	}

	public String getNumeroImovel() {
		return numeroImovel;
	}

	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}
}
