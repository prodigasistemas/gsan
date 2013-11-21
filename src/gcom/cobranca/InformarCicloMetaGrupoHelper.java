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
package gcom.cobranca;

import java.math.BigDecimal;
import java.util.TreeMap;

/**
 * Classe Helper para auxiliar no gerenciamento dos dados 
 * da tela de Informar Ciclo Meta Grupo
 * 
 * @author Francisco do Nascimento
 * @since 23/04/2009
 */
public class InformarCicloMetaGrupoHelper implements Comparable{

	// P - Principal
	// G - Gerencia regional
	// U - Unidade de negocio
	// L - localidade
	private String tipoItem; 
	
	private Integer idItem;
	
	private String nomeItem;
	
	private Integer metaOriginal;
	
	private Integer metaAtual;
	
	private Integer qtdImoveisSituacao;
	
	//novo campo para o analisar metas do ciclo
	private Integer qtdTotalRealizada;
	
	//novo campo para o analisar metas do ciclo
	private BigDecimal valorTotalRealizada;
	
	//novo campo para o analisar metas do ciclo
	private Integer qtdTotalRestante;
	
	//novo campo para o analisar metas do ciclo
	private BigDecimal valorTotalRestante;
	

	private TreeMap<String, InformarCicloMetaGrupoHelper> subItens;

	/**
	 * @return Retorna o campo idItem.
	 */
	public Integer getIdItem() {
		return idItem;
	}

	/**
	 * @param idItem O idItem a ser setado.
	 */
	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	/**
	 * @return Retorna o campo metaAtual.
	 */
	public Integer getMetaAtual() {
		return metaAtual;
	}

	/**
	 * @param metaAtual O metaAtual a ser setado.
	 */
	public void setMetaAtual(Integer metaAtual) {
		this.metaAtual = metaAtual;
	}

	/**
	 * @return Retorna o campo metaOriginal.
	 */
	public Integer getMetaOriginal() {
		return metaOriginal;
	}

	/**
	 * @param metaOriginal O metaOriginal a ser setado.
	 */
	public void setMetaOriginal(Integer metaOriginal) {
		this.metaOriginal = metaOriginal;
	}

	/**
	 * @return Retorna o campo nomeItem.
	 */
	public String getNomeItem() {
		return nomeItem;
	}

	/**
	 * @param nomeItem O nomeItem a ser setado.
	 */
	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}

	/**
	 * @return Retorna o campo subItens.
	 */
	public TreeMap<String, InformarCicloMetaGrupoHelper> getSubItens() {
		return subItens;
	}

	/**
	 * @param subItens O subItens a ser setado.
	 */
	public void setSubItens(TreeMap<String, InformarCicloMetaGrupoHelper> subItens) {
		this.subItens = subItens;
	}

	/**
	 * @return Retorna o campo tipoItem.
	 */
	public String getTipoItem() {
		return tipoItem;
	}

	/**
	 * @param tipoItem O tipoItem a ser setado.
	 */
	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}

	public int compareTo(Object arg) {
		if (arg instanceof InformarCicloMetaGrupoHelper){
			InformarCicloMetaGrupoHelper obj = (InformarCicloMetaGrupoHelper) arg;
			return obj.getNomeItem().compareTo(this.nomeItem);	
		} 
		return 0;
	}

	/**
	 * @return Retorna o campo qtdImoveisSituacao.
	 */
	public Integer getQtdImoveisSituacao() {
		return qtdImoveisSituacao;
	}

	/**
	 * @param qtdImoveisSituacao O qtdImoveisSituacao a ser setado.
	 */
	public void setQtdImoveisSituacao(Integer qtdImoveisSituacao) {
		this.qtdImoveisSituacao = qtdImoveisSituacao;
	}

	/**
	 * @return Retorna o campo qtdTotalRealizada.
	 */
	public Integer getQtdTotalRealizada() {
		return qtdTotalRealizada;
	}

	/**
	 * @param qtdTotalRealizada O qtdTotalRealizada a ser setado.
	 */
	public void setQtdTotalRealizada(Integer qtdTotalRealizada) {
		this.qtdTotalRealizada = qtdTotalRealizada;
	}

	/**
	 * @return Retorna o campo qtdTotalRestante.
	 */
	public Integer getQtdTotalRestante() {
		return qtdTotalRestante;
	}

	/**
	 * @param qtdTotalRestante O qtdTotalRestante a ser setado.
	 */
	public void setQtdTotalRestante(Integer qtdTotalRestante) {
		this.qtdTotalRestante = qtdTotalRestante;
	}

	/**
	 * @return Retorna o campo valorTotalRestante.
	 */
	public BigDecimal getValorTotalRestante() {
		return valorTotalRestante;
	}

	/**
	 * @param valorTotalRestante O valorTotalRestante a ser setado.
	 */
	public void setValorTotalRestante(BigDecimal valorTotalRestante) {
		this.valorTotalRestante = valorTotalRestante;
	}

	public BigDecimal getValorTotalRealizada() {
		return valorTotalRealizada;
	}

	public void setValorTotalRealizada(BigDecimal valorTotalRealizada) {
		this.valorTotalRealizada = valorTotalRealizada;
	}
	
}
