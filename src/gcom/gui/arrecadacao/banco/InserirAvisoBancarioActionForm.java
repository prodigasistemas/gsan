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
package gcom.gui.arrecadacao.banco;

import org.apache.struts.action.ActionForm;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 2 de Junho de 2004
 */
public class InserirAvisoBancarioActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String numeroSequencial;

	private String codigoArrecadador;
		
	private String nomeArrecadador;
	
	private String dataLancamento;

	private String[] avisoBancario;

	private String tipoAviso;
	
	private String numeroDocumento;
	
	private String dataRealizacao;
	
	private String valorArrecadacao;
	
	private String valorDevolucao;
	
	private String numeroConta;
	
	private String idFormaArrecadacao;
	
	private String numeroBanco;
	
	private String numeroAgencia; 
	
	private String idContaBancaria;
	
	private String valorDeducoes;
	
	private String valorAviso;
	
	private String codigoConvenio;

	public String getIdContaBancaria() {
		return idContaBancaria;
	}

	public void setIdContaBancaria(String idContaBancaria) {
		this.idContaBancaria = idContaBancaria;
	}

	public String getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(String numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	public String getNumeroBanco() {
		return numeroBanco;
	}

	public void setNumeroBanco(String numeroBanco) {
		this.numeroBanco = numeroBanco;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(String dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getTipoAviso() {
		return tipoAviso;
	}

	public void setTipoAviso(String tipoAviso) {
		this.tipoAviso = tipoAviso;
	}

	public String getValorArrecadacao() {
		return valorArrecadacao;
	}

	public void setValorArrecadacao(String valorArrecadacao) {
		this.valorArrecadacao = valorArrecadacao;
	}

	public String getValorDevolucao() {
		return valorDevolucao;
	}

	public void setValorDevolucao(String valorDevolucao) {
		this.valorDevolucao = valorDevolucao;
	}

	public String[] getAvisoBancario() {
		return avisoBancario;
	}

	public void setAvisoBancario(String[] avisoBancario) {
		this.avisoBancario = avisoBancario;
	}

	public String getCodigoArrecadador() {
		return codigoArrecadador;
	}

	public void setCodigoArrecadador(String codigoArrecadador) {
		this.codigoArrecadador = codigoArrecadador;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getNomeArrecadador() {
		return nomeArrecadador;
	}

	public void setNomeArrecadador(String nomeArrecadador) {
		this.nomeArrecadador = nomeArrecadador;
	}

	public String getNumeroSequencial() {
		return numeroSequencial;
	}

	public void setNumeroSequencial(String numeroSequencial) {
		this.numeroSequencial = numeroSequencial;
	}

	public String getValorAviso() {
		return valorAviso;
	}

	public void setValorAviso(String valorAviso) {
		this.valorAviso = valorAviso;
	}

	public String getValorDeducoes() {
		return valorDeducoes;
	}

	public void setValorDeducoes(String valorDeducoes) {
		this.valorDeducoes = valorDeducoes;
	}

	public String getIdFormaArrecadacao() {
		return idFormaArrecadacao;
	}

	public void setIdFormaArrecadacao(String idFormaArrecadacao) {
		this.idFormaArrecadacao = idFormaArrecadacao;
	}

	public String getCodigoConvenio() {
		return codigoConvenio;
	}

	public void setCodigoConvenio(String codigoConvenio) {
		this.codigoConvenio = codigoConvenio;
	}

}

