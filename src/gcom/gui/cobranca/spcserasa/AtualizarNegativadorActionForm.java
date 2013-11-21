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
* Thiago Vieira de Melo
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
package gcom.gui.cobranca.spcserasa;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AtualizarNegativadorActionForm extends ActionForm {
	
	private String codigoAgente;
	
	private String codigoCliente;
	
	private String codigoImovel;
	
	private String inscricaoEstadual;
	
	private String nomeCliente;
	
	private String inscricaoImovel;
	
	private String mensagemAgente;

	private String okAgente;
	
	private String okCliente;
	
	private String okImovel;
	
	private String ativo;
	
	private String inativo;
	
	private String idNegativador;
	
	private String time;
	
	private String indicadorUso;
	
	
	private static final long serialVersionUID = 1L;

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	
    	this.codigoAgente = "";
    	this.inscricaoEstadual = "";
    	this.codigoImovel = "";
    	this.codigoCliente = "";
    	this.nomeCliente = "";
    	this.inscricaoImovel = "";
    	this.mensagemAgente = "";
    	this.okAgente = "";
    	this.okCliente = "";
    	this.okImovel = "";
    	this.ativo = "";
    	this.inativo = "";
    	this.idNegativador = "";
    	this.time = "";
    	this.indicadorUso="";
    	
    }

	/**
	 * @return Retorna o campo codigoAgente.
	 */
	public String getCodigoAgente() {
		return codigoAgente;
	}

	/**
	 * @param codigoAgente O codigoAgente a ser setado.
	 */
	public void setCodigoAgente(String codigoAgente) {
		this.codigoAgente = codigoAgente;
	}

	/**
	 * @return Retorna o campo inscricaoEstadual.
	 */
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	/**
	 * @param inscricaoEstadual O inscricaoEstadual a ser setado.
	 */
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	/**
	 * @return Retorna o campo codigoImovel.
	 */
	public String getCodigoImovel() {
		return codigoImovel;
	}

	/**
	 * @param codigoImovel O codigoImovel a ser setado.
	 */
	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	/**
	 * @return Retorna o campo codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente O codigoCliente a ser setado.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return Retorna o campo inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	/**
	 * @param inscricaoImovel O inscricaoImovel a ser setado.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Retorna o campo mensagemAgente.
	 */
	public String getMensagemAgente() {
		return mensagemAgente;
	}

	/**
	 * @param mensagemAgente O mensagemAgente a ser setado.
	 */
	public void setMensagemAgente(String mensagemAgente) {
		this.mensagemAgente = mensagemAgente;
	}

	/**
	 * @return Retorna o campo okAgente.
	 */
	public String getOkAgente() {
		return okAgente;
	}

	/**
	 * @param okAgente O okAgente a ser setado.
	 */
	public void setOkAgente(String okAgente) {
		this.okAgente = okAgente;
	}

	/**
	 * @return Retorna o campo okCliente.
	 */
	public String getOkCliente() {
		return okCliente;
	}

	/**
	 * @param okCliente O okCliente a ser setado.
	 */
	public void setOkCliente(String okCliente) {
		this.okCliente = okCliente;
	}

	/**
	 * @return Retorna o campo okImovel.
	 */
	public String getOkImovel() {
		return okImovel;
	}

	/**
	 * @param okImovel O okImovel a ser setado.
	 */
	public void setOkImovel(String okImovel) {
		this.okImovel = okImovel;
	}

	/**
	 * @return Retorna o campo ativo.
	 */
	public String getAtivo() {
		return ativo;
	}

	/**
	 * @param ativo O ativo a ser setado.
	 */
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	/**
	 * @return Retorna o campo inativo.
	 */
	public String getInativo() {
		return inativo;
	}

	/**
	 * @param inativo O inativo a ser setado.
	 */
	public void setInativo(String inativo) {
		this.inativo = inativo;
	}

	/**
	 * @return Retorna o campo idNegativador.
	 */
	public String getIdNegativador() {
		return idNegativador;
	}

	/**
	 * @param idNegativador O idNegativador a ser setado.
	 */
	public void setIdNegativador(String idNegativador) {
		this.idNegativador = idNegativador;
	}

	/**
	 * @return Retorna o campo time.
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time O time a ser setado.
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	
	

}