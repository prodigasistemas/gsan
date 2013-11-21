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
package gcom.gui.arrecadacao.aviso;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0611] Movimentar Pagamentos/ Devoluções entre Avisos Bancários 
 * 
 * @author Ana Maria
 *
 * @date 07/06/2007
 */
public class MovimentarPagamentosDevolucoesAvisoBancarioActionForm extends ValidatorActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String arrecadacaoInformadoAntesOrigem;
	private String arrecadacaoCalculadoAntesOrigem;
	private String devolucaoInformadoAntesOrigem;
	private String devolucaoCalculadoAntesOrigem;
	private String arrecadacaoInformadoDepoisOrigem;
	private String arrecadacaoCalculadoDepoisOrigem;
	private String devolucaoInformadoDepoisOrigem;
	private String devolucaoCalculadoDepoisOrigem;
	
	private String arrecadacaoInformadoAntesDestino;
	private String arrecadacaoCalculadoAntesDestino;
	private String devolucaoInformadoAntesDestino;
	private String devolucaoCalculadoAntesDestino;
	private String arrecadacaoInformadoDepoisDestino;
	private String arrecadacaoCalculadoDepoisDestino;
	private String devolucaoInformadoDepoisDestino;
	private String devolucaoCalculadoDepoisDestino;
	
    private String[] idRegistrosRemocaoPagamento;	
    private String[] idRegistrosRemocaoDevolucao;    
	/**
	 * @return Retorna o campo arrecadacaoCalculadoAntesDestino.
	 */
	public String getArrecadacaoCalculadoAntesDestino() {
		return arrecadacaoCalculadoAntesDestino;
	}
	/**
	 * @param arrecadacaoCalculadoAntesDestino O arrecadacaoCalculadoAntesDestino a ser setado.
	 */
	public void setArrecadacaoCalculadoAntesDestino(
			String arrecadacaoCalculadoAntesDestino) {
		this.arrecadacaoCalculadoAntesDestino = arrecadacaoCalculadoAntesDestino;
	}
	/**
	 * @return Retorna o campo arrecadacaoCalculadoAntesOrigem.
	 */
	public String getArrecadacaoCalculadoAntesOrigem() {
		return arrecadacaoCalculadoAntesOrigem;
	}
	/**
	 * @param arrecadacaoCalculadoAntesOrigem O arrecadacaoCalculadoAntesOrigem a ser setado.
	 */
	public void setArrecadacaoCalculadoAntesOrigem(
			String arrecadacaoCalculadoAntesOrigem) {
		this.arrecadacaoCalculadoAntesOrigem = arrecadacaoCalculadoAntesOrigem;
	}
	/**
	 * @return Retorna o campo arrecadacaoCalculadoDepoisDestino.
	 */
	public String getArrecadacaoCalculadoDepoisDestino() {
		return arrecadacaoCalculadoDepoisDestino;
	}
	/**
	 * @param arrecadacaoCalculadoDepoisDestino O arrecadacaoCalculadoDepoisDestino a ser setado.
	 */
	public void setArrecadacaoCalculadoDepoisDestino(
			String arrecadacaoCalculadoDepoisDestino) {
		this.arrecadacaoCalculadoDepoisDestino = arrecadacaoCalculadoDepoisDestino;
	}
	/**
	 * @return Retorna o campo arrecadacaoCalculadoDepoisOrigem.
	 */
	public String getArrecadacaoCalculadoDepoisOrigem() {
		return arrecadacaoCalculadoDepoisOrigem;
	}
	/**
	 * @param arrecadacaoCalculadoDepoisOrigem O arrecadacaoCalculadoDepoisOrigem a ser setado.
	 */
	public void setArrecadacaoCalculadoDepoisOrigem(
			String arrecadacaoCalculadoDepoisOrigem) {
		this.arrecadacaoCalculadoDepoisOrigem = arrecadacaoCalculadoDepoisOrigem;
	}
	/**
	 * @return Retorna o campo arrecadacaoInformadoAntesDestino.
	 */
	public String getArrecadacaoInformadoAntesDestino() {
		return arrecadacaoInformadoAntesDestino;
	}
	/**
	 * @param arrecadacaoInformadoAntesDestino O arrecadacaoInformadoAntesDestino a ser setado.
	 */
	public void setArrecadacaoInformadoAntesDestino(
			String arrecadacaoInformadoAntesDestino) {
		this.arrecadacaoInformadoAntesDestino = arrecadacaoInformadoAntesDestino;
	}
	/**
	 * @return Retorna o campo arrecadacaoInformadoAntesOrigem.
	 */
	public String getArrecadacaoInformadoAntesOrigem() {
		return arrecadacaoInformadoAntesOrigem;
	}
	/**
	 * @param arrecadacaoInformadoAntesOrigem O arrecadacaoInformadoAntesOrigem a ser setado.
	 */
	public void setArrecadacaoInformadoAntesOrigem(
			String arrecadacaoInformadoAntesOrigem) {
		this.arrecadacaoInformadoAntesOrigem = arrecadacaoInformadoAntesOrigem;
	}
	/**
	 * @return Retorna o campo arrecadacaoInformadoDepoisDestino.
	 */
	public String getArrecadacaoInformadoDepoisDestino() {
		return arrecadacaoInformadoDepoisDestino;
	}
	/**
	 * @param arrecadacaoInformadoDepoisDestino O arrecadacaoInformadoDepoisDestino a ser setado.
	 */
	public void setArrecadacaoInformadoDepoisDestino(
			String arrecadacaoInformadoDepoisDestino) {
		this.arrecadacaoInformadoDepoisDestino = arrecadacaoInformadoDepoisDestino;
	}
	/**
	 * @return Retorna o campo arrecadacaoInformadoDepoisOrigem.
	 */
	public String getArrecadacaoInformadoDepoisOrigem() {
		return arrecadacaoInformadoDepoisOrigem;
	}
	/**
	 * @param arrecadacaoInformadoDepoisOrigem O arrecadacaoInformadoDepoisOrigem a ser setado.
	 */
	public void setArrecadacaoInformadoDepoisOrigem(
			String arrecadacaoInformadoDepoisOrigem) {
		this.arrecadacaoInformadoDepoisOrigem = arrecadacaoInformadoDepoisOrigem;
	}
	/**
	 * @return Retorna o campo devolucaoCalculadoAntesDestino.
	 */
	public String getDevolucaoCalculadoAntesDestino() {
		return devolucaoCalculadoAntesDestino;
	}
	/**
	 * @param devolucaoCalculadoAntesDestino O devolucaoCalculadoAntesDestino a ser setado.
	 */
	public void setDevolucaoCalculadoAntesDestino(
			String devolucaoCalculadoAntesDestino) {
		this.devolucaoCalculadoAntesDestino = devolucaoCalculadoAntesDestino;
	}
	/**
	 * @return Retorna o campo devolucaoCalculadoAntesOrigem.
	 */
	public String getDevolucaoCalculadoAntesOrigem() {
		return devolucaoCalculadoAntesOrigem;
	}
	/**
	 * @param devolucaoCalculadoAntesOrigem O devolucaoCalculadoAntesOrigem a ser setado.
	 */
	public void setDevolucaoCalculadoAntesOrigem(
			String devolucaoCalculadoAntesOrigem) {
		this.devolucaoCalculadoAntesOrigem = devolucaoCalculadoAntesOrigem;
	}
	/**
	 * @return Retorna o campo devolucaoCalculadoDepoisDestino.
	 */
	public String getDevolucaoCalculadoDepoisDestino() {
		return devolucaoCalculadoDepoisDestino;
	}
	/**
	 * @param devolucaoCalculadoDepoisDestino O devolucaoCalculadoDepoisDestino a ser setado.
	 */
	public void setDevolucaoCalculadoDepoisDestino(
			String devolucaoCalculadoDepoisDestino) {
		this.devolucaoCalculadoDepoisDestino = devolucaoCalculadoDepoisDestino;
	}
	/**
	 * @return Retorna o campo devolucaoCalculadoDepoisOrigem.
	 */
	public String getDevolucaoCalculadoDepoisOrigem() {
		return devolucaoCalculadoDepoisOrigem;
	}
	/**
	 * @param devolucaoCalculadoDepoisOrigem O devolucaoCalculadoDepoisOrigem a ser setado.
	 */
	public void setDevolucaoCalculadoDepoisOrigem(
			String devolucaoCalculadoDepoisOrigem) {
		this.devolucaoCalculadoDepoisOrigem = devolucaoCalculadoDepoisOrigem;
	}
	/**
	 * @return Retorna o campo devolucaoInformadoAntesDestino.
	 */
	public String getDevolucaoInformadoAntesDestino() {
		return devolucaoInformadoAntesDestino;
	}
	/**
	 * @param devolucaoInformadoAntesDestino O devolucaoInformadoAntesDestino a ser setado.
	 */
	public void setDevolucaoInformadoAntesDestino(
			String devolucaoInformadoAntesDestino) {
		this.devolucaoInformadoAntesDestino = devolucaoInformadoAntesDestino;
	}
	/**
	 * @return Retorna o campo devolucaoInformadoAntesOrigem.
	 */
	public String getDevolucaoInformadoAntesOrigem() {
		return devolucaoInformadoAntesOrigem;
	}
	/**
	 * @param devolucaoInformadoAntesOrigem O devolucaoInformadoAntesOrigem a ser setado.
	 */
	public void setDevolucaoInformadoAntesOrigem(
			String devolucaoInformadoAntesOrigem) {
		this.devolucaoInformadoAntesOrigem = devolucaoInformadoAntesOrigem;
	}
	/**
	 * @return Retorna o campo devolucaoInformadoDepoisDestino.
	 */
	public String getDevolucaoInformadoDepoisDestino() {
		return devolucaoInformadoDepoisDestino;
	}
	/**
	 * @param devolucaoInformadoDepoisDestino O devolucaoInformadoDepoisDestino a ser setado.
	 */
	public void setDevolucaoInformadoDepoisDestino(
			String devolucaoInformadoDepoisDestino) {
		this.devolucaoInformadoDepoisDestino = devolucaoInformadoDepoisDestino;
	}
	/**
	 * @return Retorna o campo devolucaoInformadoDepoisOrigem.
	 */
	public String getDevolucaoInformadoDepoisOrigem() {
		return devolucaoInformadoDepoisOrigem;
	}
	/**
	 * @param devolucaoInformadoDepoisOrigem O devolucaoInformadoDepoisOrigem a ser setado.
	 */
	public void setDevolucaoInformadoDepoisOrigem(
			String devolucaoInformadoDepoisOrigem) {
		this.devolucaoInformadoDepoisOrigem = devolucaoInformadoDepoisOrigem;
	}
	/**
	 * @return Retorna o campo idRegistrosRemocaoDevolucao.
	 */
	public String[] getIdRegistrosRemocaoDevolucao() {
		return idRegistrosRemocaoDevolucao;
	}
	/**
	 * @param idRegistrosRemocaoDevolucao O idRegistrosRemocaoDevolucao a ser setado.
	 */
	public void setIdRegistrosRemocaoDevolucao(String[] idRegistrosRemocaoDevolucao) {
		this.idRegistrosRemocaoDevolucao = idRegistrosRemocaoDevolucao;
	}
	/**
	 * @return Retorna o campo idRegistrosRemocaoPagamento.
	 */
	public String[] getIdRegistrosRemocaoPagamento() {
		return idRegistrosRemocaoPagamento;
	}
	/**
	 * @param idRegistrosRemocaoPagamento O idRegistrosRemocaoPagamento a ser setado.
	 */
	public void setIdRegistrosRemocaoPagamento(String[] idRegistrosRemocaoPagamento) {
		this.idRegistrosRemocaoPagamento = idRegistrosRemocaoPagamento;
	}
	
}