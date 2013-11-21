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
package gcom.gui.cadastro.imovel;

import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter aviso bancario 
 *
 * @author thiago
 *
 * @date 10/03/2006
 */
public class ExibirConsultarRelacaoClienteImovelActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idImovel = ""; 
	private String nomeImovel = "";
	private String imovelNaoEncontrado = "false";

	private String idCliente = "";
	private String nomeCliente = "";	
	private String clienteNaoEncontrado = "false";

	private String idClienteRelacaoTipo = "";
	private Collection collClienteRelacaoTipo = new Vector();
	private String periodoInicialDataInicioRelacao = "";
	private String periodoFinalDataInicioRelacao = "";
	private String situacaoRelacao = "";
	private String periodoInicialDataFimRelacao = "";
	private String periodoFinalDataFimRelacao = "";
	private String idClienteImovelFimRelacaoMotivo = "";
	private Collection collClienteImovelFimRelacaoMotivo = new Vector();

	/**
	 * Método que limpa os atributos 
	 *
	 * @author Administrador
	 * @date 10/03/2006
	 *
	 * @param arg0 mapeamento
	 * @param arg1 request
	 */
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		// TODO Auto-generated method stub
		super.reset(arg0, arg1);
		
		this.idImovel = ""; 
		this.nomeImovel = "";
		this.imovelNaoEncontrado = "false";
		
		this.idCliente = "";
		this.nomeCliente = "";	
		this.clienteNaoEncontrado = "false";
		
		this.idClienteRelacaoTipo = "";
		this.situacaoRelacao = "";
		this.collClienteRelacaoTipo = new Vector();
		this.periodoInicialDataInicioRelacao = "";
		this.periodoFinalDataInicioRelacao = "";
		this.periodoInicialDataFimRelacao = "";
		this.periodoFinalDataFimRelacao = "";
		this.idClienteImovelFimRelacaoMotivo = "";
		this.collClienteImovelFimRelacaoMotivo = new Vector();
		
	}

	/**
	 * @return Retorna o campo clienteNaoEncontrado.
	 */
	public String getClienteNaoEncontrado() {
		return clienteNaoEncontrado;
	}

	/**
	 * @param clienteNaoEncontrado O clienteNaoEncontrado a ser setado.
	 */
	public void setClienteNaoEncontrado(String clienteNaoEncontrado) {
		this.clienteNaoEncontrado = clienteNaoEncontrado;
	}

	/**
	 * @return Retorna o campo collClienteImovelFimRelacaoMotivo.
	 */
	public Collection getCollClienteImovelFimRelacaoMotivo() {
		return collClienteImovelFimRelacaoMotivo;
	}

	/**
	 * @param collClienteImovelFimRelacaoMotivo O collClienteImovelFimRelacaoMotivo a ser setado.
	 */
	public void setCollClienteImovelFimRelacaoMotivo(
			Collection collClienteImovelFimRelacaoMotivo) {
		this.collClienteImovelFimRelacaoMotivo = collClienteImovelFimRelacaoMotivo;
	}

	/**
	 * @return Retorna o campo collClienteRelacaoTipo.
	 */
	public Collection getCollClienteRelacaoTipo() {
		return collClienteRelacaoTipo;
	}

	/**
	 * @param collClienteRelacaoTipo O collClienteRelacaoTipo a ser setado.
	 */
	public void setCollClienteRelacaoTipo(Collection collClienteRelacaoTipo) {
		this.collClienteRelacaoTipo = collClienteRelacaoTipo;
	}

	/**
	 * @return Retorna o campo periodoFinalDataFimRelacao.
	 */
	public String getPeriodoFinalDataFimRelacao() {
		return periodoFinalDataFimRelacao;
	}

	/**
	 * @param periodoFinalDataFimRelacao O periodoFinalDataFimRelacao a ser setado.
	 */
	public void setPeriodoFinalDataFimRelacao(String periodoFinalDataFimRelacao) {
		this.periodoFinalDataFimRelacao = periodoFinalDataFimRelacao;
	}

	/**
	 * @return Retorna o campo periodoFinalDataInicioRelacao.
	 */
	public String getPeriodoFinalDataInicioRelacao() {
		return periodoFinalDataInicioRelacao;
	}

	/**
	 * @param periodoFinalDataInicioRelacao O periodoFinalDataInicioRelacao a ser setado.
	 */
	public void setPeriodoFinalDataInicioRelacao(
			String periodoFinalDataInicioRelacao) {
		this.periodoFinalDataInicioRelacao = periodoFinalDataInicioRelacao;
	}

	/**
	 * @return Retorna o campo periodoInicialDataFimRelacao.
	 */
	public String getPeriodoInicialDataFimRelacao() {
		return periodoInicialDataFimRelacao;
	}

	/**
	 * @param periodoInicialDataFimRelacao O periodoInicialDataFimRelacao a ser setado.
	 */
	public void setPeriodoInicialDataFimRelacao(String periodoInicialDataFimRelacao) {
		this.periodoInicialDataFimRelacao = periodoInicialDataFimRelacao;
	}

	/**
	 * @return Retorna o campo periodoInicialDataInicioRelacao.
	 */
	public String getPeriodoInicialDataInicioRelacao() {
		return periodoInicialDataInicioRelacao;
	}

	/**
	 * @param periodoInicialDataInicioRelacao O periodoInicialDataInicioRelacao a ser setado.
	 */
	public void setPeriodoInicialDataInicioRelacao(
			String periodoInicialDataInicioRelacao) {
		this.periodoInicialDataInicioRelacao = periodoInicialDataInicioRelacao;
	}

	/**
	 * @return Retorna o campo idCliente.
	 */
	public String getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente O idCliente a ser setado.
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * @return Retorna o campo idClienteImovelFimRelacaoMotivo.
	 */
	public String getIdClienteImovelFimRelacaoMotivo() {
		return idClienteImovelFimRelacaoMotivo;
	}

	/**
	 * @param idClienteImovelFimRelacaoMotivo O idClienteImovelFimRelacaoMotivo a ser setado.
	 */
	public void setIdClienteImovelFimRelacaoMotivo(
			String idClienteImovelFimRelacaoMotivo) {
		this.idClienteImovelFimRelacaoMotivo = idClienteImovelFimRelacaoMotivo;
	}

	/**
	 * @return Retorna o campo idClienteRelacaoTipo.
	 */
	public String getIdClienteRelacaoTipo() {
		return idClienteRelacaoTipo;
	}

	/**
	 * @param idClienteRelacaoTipo O idClienteRelacaoTipo a ser setado.
	 */
	public void setIdClienteRelacaoTipo(String idClienteRelacaoTipo) {
		this.idClienteRelacaoTipo = idClienteRelacaoTipo;
	}

	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Retorna o campo imovelNaoEncontrado.
	 */
	public String getImovelNaoEncontrado() {
		return imovelNaoEncontrado;
	}

	/**
	 * @param imovelNaoEncontrado O imovelNaoEncontrado a ser setado.
	 */
	public void setImovelNaoEncontrado(String imovelNaoEncontrado) {
		this.imovelNaoEncontrado = imovelNaoEncontrado;
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
	 * @return Retorna o campo nomeImovel.
	 */
	public String getNomeImovel() {
		return nomeImovel;
	}

	/**
	 * @param nomeImovel O nomeImovel a ser setado.
	 */
	public void setNomeImovel(String nomeImovel) {
		this.nomeImovel = nomeImovel;
	}

	public String getSituacaoRelacao() {
		return situacaoRelacao;
	}

	public void setSituacaoRelacao(String situacaoRelacao) {
		this.situacaoRelacao = situacaoRelacao;
	}

	
}