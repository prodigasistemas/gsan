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
* Yara Taciane de Souza
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
package gcom.gui.atendimentopublico.ordemservico;

import javax.servlet.ServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Yara Taciane de Souza
 * @created 29/05/2008
 */

public class AtualizarOrdemProcessoRepavimentacaoPopUpActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idOrdemServico;
	private String idOrdemServicoPavimento;
	private String dataExecucao;	
	private String idPavimentoCalcadaRet;
	private String descricaoPavimentoCalcadaRet;
	private String idPavimentoRuaRet;	
	private String descricaoPavimentoRuaRet;
	private String areaPavimentoCalcadaRet;
	private String areaPavimentoRuaRet;
	private String manterPaginaAux;
	private String nomeEmpresa;
	private String observacao;
	
	private String idMotivoRejeicao;
	private String dataRejeicao;
	private String descricaoRejeicao;
	
	private String dataAtual;
	
	public void reset(ActionMapping arg0, ServletRequest arg1) {
		// TODO Auto-generated method stub
		super.reset(arg0, arg1);
		this.idOrdemServico = "";	
		this.idOrdemServicoPavimento = "";	
		this.dataExecucao = "";	
		this.idPavimentoCalcadaRet= "";
		this. descricaoPavimentoCalcadaRet= "";
		this.idPavimentoRuaRet= "";	
		this.descricaoPavimentoRuaRet= "";		
		this.areaPavimentoCalcadaRet ="";
		this.areaPavimentoRuaRet = "";
		this.idMotivoRejeicao = "";
		this.dataRejeicao = "";
		this.descricaoRejeicao = "";
		this.dataAtual = "";
	}

	/**
	 * @return Retorna o campo dataExecucao.
	 */
	public String getDataExecucao() {
		return dataExecucao;
	}

	/**
	 * @param dataExecucao O dataExecucao a ser setado.
	 */
	public void setDataExecucao(String dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	/**
	 * @return Retorna o campo descricaoPavimentoCalcadaRet.
	 */
	public String getDescricaoPavimentoCalcadaRet() {
		return descricaoPavimentoCalcadaRet;
	}

	/**
	 * @param descricaoPavimentoCalcadaRet O descricaoPavimentoCalcadaRet a ser setado.
	 */
	public void setDescricaoPavimentoCalcadaRet(String descricaoPavimentoCalcadaRet) {
		this.descricaoPavimentoCalcadaRet = descricaoPavimentoCalcadaRet;
	}

	/**
	 * @return Retorna o campo descricaoPavimentoRuaRet.
	 */
	public String getDescricaoPavimentoRuaRet() {
		return descricaoPavimentoRuaRet;
	}

	/**
	 * @param descricaoPavimentoRuaRet O descricaoPavimentoRuaRet a ser setado.
	 */
	public void setDescricaoPavimentoRuaRet(String descricaoPavimentoRuaRet) {
		this.descricaoPavimentoRuaRet = descricaoPavimentoRuaRet;
	}

	/**
	 * @return Retorna o campo idOrdemServico.
	 */
	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	/**
	 * @param idOrdemServico O idOrdemServico a ser setado.
	 */
	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	/**
	 * @return Retorna o campo idOrdemServicoPavimento.
	 */
	public String getIdOrdemServicoPavimento() {
		return idOrdemServicoPavimento;
	}

	/**
	 * @param idOrdemServicoPavimento O idOrdemServicoPavimento a ser setado.
	 */
	public void setIdOrdemServicoPavimento(String idOrdemServicoPavimento) {
		this.idOrdemServicoPavimento = idOrdemServicoPavimento;
	}

	/**
	 * @return Retorna o campo idPavimentoCalcadaRet.
	 */
	public String getIdPavimentoCalcadaRet() {
		return idPavimentoCalcadaRet;
	}

	/**
	 * @param idPavimentoCalcadaRet O idPavimentoCalcadaRet a ser setado.
	 */
	public void setIdPavimentoCalcadaRet(String idPavimentoCalcadaRet) {
		this.idPavimentoCalcadaRet = idPavimentoCalcadaRet;
	}

	/**
	 * @return Retorna o campo idPavimentoRuaRet.
	 */
	public String getIdPavimentoRuaRet() {
		return idPavimentoRuaRet;
	}

	/**
	 * @param idPavimentoRuaRet O idPavimentoRuaRet a ser setado.
	 */
	public void setIdPavimentoRuaRet(String idPavimentoRuaRet) {
		this.idPavimentoRuaRet = idPavimentoRuaRet;
	}

	/**
	 * @return Retorna o campo areaPavimentoCalcadaRet.
	 */
	public String getAreaPavimentoCalcadaRet() {
		return areaPavimentoCalcadaRet;
	}

	/**
	 * @param areaPavimentoCalcadaRet O areaPavimentoCalcadaRet a ser setado.
	 */
	public void setAreaPavimentoCalcadaRet(String areaPavimentoCalcadaRet) {
		this.areaPavimentoCalcadaRet = areaPavimentoCalcadaRet;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	/**
	 * @return Retorna o campo areaPavimentoRuaRet.
	 */
	public String getAreaPavimentoRuaRet() {
		return areaPavimentoRuaRet;
	}

	/**
	 * @param areaPavimentoRuaRet O areaPavimentoRuaRet a ser setado.
	 */
	public void setAreaPavimentoRuaRet(String areaPavimentoRuaRet) {
		this.areaPavimentoRuaRet = areaPavimentoRuaRet;
	}

	/**
	 * @return Returns the manterPaginaAux.
	 */
	public String getManterPaginaAux() {
		return manterPaginaAux;
	}

	/**
	 * @param manterPaginaAux The manterPaginaAux to set.
	 */
	public void setManterPaginaAux(String manterPaginaAux) {
		this.manterPaginaAux = manterPaginaAux;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDataRejeicao() {
		return dataRejeicao;
	}

	public void setDataRejeicao(String dataRejeicao) {
		this.dataRejeicao = dataRejeicao;
	}

	public String getDescricaoRejeicao() {
		return descricaoRejeicao;
	}

	public void setDescricaoRejeicao(String descricaoRejeicao) {
		this.descricaoRejeicao = descricaoRejeicao;
	}

	public String getIdMotivoRejeicao() {
		return idMotivoRejeicao;
	}

	public void setIdMotivoRejeicao(String idMotivoRejeicao) {
		this.idMotivoRejeicao = idMotivoRejeicao;
	}

	public String getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}

}

