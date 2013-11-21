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
package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

public class ConsultarDebitoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoImovel;
	
	private String inscricaoImovel;
	
	private String codigoImovelClone;

	private String codigoCliente;
	
	private String nomeCliente;
	
	private String codigoClienteClone;
	
	private String tipoRelacao;
	
	private String codigoClienteSuperior;
	
	private String nomeClienteSuperior;
	
	private String codigoClienteSuperiorClone;

	private String referenciaInicial;

	private String referenciaFinal;

	private String dataVencimentoInicial;

	private String dataVencimentoFinal;
	
	private String refInicial;
	
	private String refFinal;
	
	private String dtInicial;
	
	private String dtFinal;
	
	private String responsavel;
	
	
	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoImovel() {
		return codigoImovel;
	}

	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	public String getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}

	public void setDataVencimentoFinal(String dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}

	public String getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}

	public void setDataVencimentoInicial(String dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}

	public String getReferenciaFinal() {
		return referenciaFinal;
	}

	public void setReferenciaFinal(String referenciaFinal) {
		this.referenciaFinal = referenciaFinal;
	}

	public String getReferenciaInicial() {
		return referenciaInicial;
	}

	public void setReferenciaInicial(String referenciaInicial) {
		this.referenciaInicial = referenciaInicial;
	}

	public String getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getCodigoClienteClone() {
		return codigoClienteClone;
	}

	public void setCodigoClienteClone(String codigoClienteClone) {
		this.codigoClienteClone = codigoClienteClone;
	}

	public String getCodigoImovelClone() {
		return codigoImovelClone;
	}

	public void setCodigoImovelClone(String codigoImovelClone) {
		this.codigoImovelClone = codigoImovelClone;
	}

	public String getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(String dtFinal) {
		this.dtFinal = dtFinal;
	}

	public String getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(String dtInicial) {
		this.dtInicial = dtInicial;
	}

	public String getRefFinal() {
		return refFinal;
	}

	public void setRefFinal(String refFinal) {
		this.refFinal = refFinal;
	}

	public String getRefInicial() {
		return refInicial;
	}

	public void setRefInicial(String refInicial) {
		this.refInicial = refInicial;
	}

	/**
	 * @return Retorna o campo responsavel.
	 */
	public String getResponsavel() {
		return responsavel;
	}

	/**
	 * @param responsavel O responsavel a ser setado.
	 */
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	/**
	 * @return Retorna o campo codigoClienteSuperior.
	 */
	public String getCodigoClienteSuperior() {
		return codigoClienteSuperior;
	}

	/**
	 * @param codigoClienteSuperior O codigoClienteSuperior a ser setado.
	 */
	public void setCodigoClienteSuperior(String codigoClienteSuperior) {
		this.codigoClienteSuperior = codigoClienteSuperior;
	}

	/**
	 * @return Retorna o campo codigoClienteSuperiorClone.
	 */
	public String getCodigoClienteSuperiorClone() {
		return codigoClienteSuperiorClone;
	}

	/**
	 * @param codigoClienteSuperiorClone O codigoClienteSuperiorClone a ser setado.
	 */
	public void setCodigoClienteSuperiorClone(String codigoClienteSuperiorClone) {
		this.codigoClienteSuperiorClone = codigoClienteSuperiorClone;
	}

	/**
	 * @return Retorna o campo nomeClienteSuperior.
	 */
	public String getNomeClienteSuperior() {
		return nomeClienteSuperior;
	}

	/**
	 * @param nomeClienteSuperior O nomeClienteSuperior a ser setado.
	 */
	public void setNomeClienteSuperior(String nomeClienteSuperior) {
		this.nomeClienteSuperior = nomeClienteSuperior;
	}
	
}
