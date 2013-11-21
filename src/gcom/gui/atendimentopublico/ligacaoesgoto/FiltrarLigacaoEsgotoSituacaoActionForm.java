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
package gcom.gui.atendimentopublico.ligacaoesgoto;


import gcom.util.ConstantesSistema;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC00789] Filtrar Situação de Ligação de Esgoto
 * 
 * @author Bruno Barros
 *
 * @date 14/05/2008
 */
public class FiltrarLigacaoEsgotoSituacaoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String descricao;
	private String tipoPesquisa = ConstantesSistema.TIPO_PESQUISA_INICIAL.toString();
	private String descricaoAbreviada;
	private String consumoMinimoFaturamento;
	private String indicadorFaturamento = ConstantesSistema.TODOS.toString();
	private String indicadorExistenciaRede = ConstantesSistema.TODOS.toString();
	private String indicadorExistenciaLigacao = ConstantesSistema.TODOS.toString();
	private String indicadorUso = ConstantesSistema.TODOS.toString();
	private String indicadorAtualizar;
	
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}
	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}
	public String getIndicadorExistenciaLigacao() {
		return indicadorExistenciaLigacao;
	}
	public void setIndicadorExistenciaLigacao(String indicadorExistenciaLigacao) {
		this.indicadorExistenciaLigacao = indicadorExistenciaLigacao;
	}
	public String getIndicadorExistenciaRede() {
		return indicadorExistenciaRede;
	}
	public void setIndicadorExistenciaRede(String indicadorExistenciaRede) {
		this.indicadorExistenciaRede = indicadorExistenciaRede;
	}
	public String getIndicadorFaturamento() {
		return indicadorFaturamento;
	}
	public void setIndicadorFaturamento(String indicadorFaturamento) {
		this.indicadorFaturamento = indicadorFaturamento;
	}
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	
	public void reset(){
		this.codigo = "";
		this.descricao = "";
		this.tipoPesquisa = "";
		this.descricaoAbreviada = "";
		this.consumoMinimoFaturamento = "";
		this.tipoPesquisa = ConstantesSistema.TIPO_PESQUISA_INICIAL.toString();
		this.indicadorExistenciaLigacao = ConstantesSistema.TODOS.toString();
		this.indicadorExistenciaRede = ConstantesSistema.TODOS.toString();
		this.indicadorFaturamento = ConstantesSistema.TODOS.toString();
		this.indicadorUso = ConstantesSistema.TODOS.toString();
	}
	public String getConsumoMinimoFaturamento() {
		return consumoMinimoFaturamento;
	}
	public void setConsumoMinimoFaturamento(String consumoMinimoFaturamento) {
		this.consumoMinimoFaturamento = consumoMinimoFaturamento;
	}
}