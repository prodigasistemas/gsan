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
package gcom.gui.faturamento.credito;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class FiltrarTipoCreditoActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigo;

	private String descricao;

	private String abreviatura;

	private String tipoLancamentoContabil;

	private String indicativoUso;

	private String indicadorgeracaoAutomaica;

	private String valorLimiteCreditoInicial;

	private String valorLimiteCreditoFinal;
	
	private String atualizar;
	
	private String tipoPesquisa;

	/**
	 * @return Retorna o campo atualizar.
	 */
	public String getAtualizar() {
		return atualizar;
	}

	/**
	 * @param atualizar O atualizar a ser setado.
	 */
	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
	}

	/**
	 * @return Retorna o campo abreviatura.
	 */
	public String getAbreviatura() {
		return abreviatura;
	}

	/**
	 * @param abreviatura
	 *            O abreviatura a ser setado.
	 */
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Retorna o campo indicadorgeracaoAutomaica.
	 */
	public String getIndicadorgeracaoAutomaica() {
		return indicadorgeracaoAutomaica;
	}

	/**
	 * @param indicadorgeracaoAutomaica
	 *            O indicadorgeracaoAutomaica a ser setado.
	 */
	public void setIndicadorgeracaoAutomaica(String indicadorgeracaoAutomaica) {
		this.indicadorgeracaoAutomaica = indicadorgeracaoAutomaica;
	}

	/**
	 * @return Retorna o campo tipoLancamentoContabil.
	 */
	public String getTipoLancamentoContabil() {
		return tipoLancamentoContabil;
	}

	/**
	 * @param tipoLancamentoContabil
	 *            O tipoLancamentoContabil a ser setado.
	 */
	public void setTipoLancamentoContabil(String tipoLancamentoContabil) {
		this.tipoLancamentoContabil = tipoLancamentoContabil;
	}

	/**
	 * @return Retorna o campo valorLimiteCreditoFinal.
	 */
	public String getValorLimiteCreditoFinal() {
		return valorLimiteCreditoFinal;
	}

	/**
	 * @param valorLimiteCreditoFinal
	 *            O valorLimiteCreditoFinal a ser setado.
	 */
	public void setValorLimiteCreditoFinal(String valorLimiteCreditoFinal) {
		this.valorLimiteCreditoFinal = valorLimiteCreditoFinal;
	}

	/**
	 * @return Retorna o campo valorLimiteCreditoInicial.
	 */
	public String getValorLimiteCreditoInicial() {
		return valorLimiteCreditoInicial;
	}

	/**
	 * @param valorLimiteCreditoInicial
	 *            O valorLimiteCreditoInicial a ser setado.
	 */
	public void setValorLimiteCreditoInicial(String valorLimiteCreditoInicial) {
		this.valorLimiteCreditoInicial = valorLimiteCreditoInicial;
	}

	/**
	 * @return Retorna o campo codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            O codigo a ser setado.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Retorna o campo indicativoUso.
	 */
	public String getIndicativoUso() {
		return indicativoUso;
	}

	/**
	 * @param indicativoUso
	 *            O indicativoUso a ser setado.
	 */
	public void setIndicativoUso(String indicativoUso) {
		this.indicativoUso = indicativoUso;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

}
