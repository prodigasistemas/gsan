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
package gcom.cadastro;

import gcom.cadastro.cliente.Cliente;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

/**
 * @author Fernando Fontelles
 * @created 09/07/2010
 */
@ControleAlteracao()
public class EmailClienteAlterado extends ObjetoTransacao {
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Cliente idCliente;
	private String nomeClienteAnterior;
	private String cpfAnterior;
	private String cnpjAnterior;
	private String emailAnterior;
	
	private String nomeSolicitante;
	private String cpfSolicitante;
	
	private String nomeClienteAtual;
	private String cpfAtual;
	private String cnpjAtual;
	private String emailAtual;
	
	private Date confirmacaoOnline;
	private Date solicitacaoOnline;

	private Date ultimaAlteracao;
	
	private Integer telefoneContato;
	
	public EmailClienteAlterado(){
		
	}

	public EmailClienteAlterado(Cliente idCliente, String nomeClienteAnterior, 
			String cpfAnterior, String cnpjAnterior, String emailAnterior, 
			String nomeSolicitante, String cpfSolicitante, String nomeClienteAtual, 
			String cpfAtual, String cnpjAtual, String emailAtual, Date solicitacaoOnline) {
		super();
		// TODO Auto-generated constructor stub
		this.idCliente = idCliente;
		this.nomeClienteAnterior = nomeClienteAnterior;
		this.cpfAnterior = cpfAnterior;
		this.cnpjAnterior = cnpjAnterior;
		this.emailAnterior = emailAnterior;
		this.nomeSolicitante = nomeSolicitante;
		this.cpfSolicitante = cpfSolicitante;
		this.nomeClienteAtual = nomeClienteAtual;
		this.cpfAtual = cpfAtual;
		this.cnpjAtual = cnpjAtual;
		this.emailAtual = emailAtual;
		this.solicitacaoOnline = solicitacaoOnline;
		
	}

	public Date getConfirmacaoOnline() {
		return confirmacaoOnline;
	}

	public void setConfirmacaoOnline(Date confirmacaoOnline) {
		this.confirmacaoOnline = confirmacaoOnline;
	}

	public Date getSolicitacaoOnline() {
		return solicitacaoOnline;
	}

	public void setSolicitacaoOnline(Date solicitacaoOnline) {
		this.solicitacaoOnline = solicitacaoOnline;
	}

	public String getCnpjAnterior() {
		return cnpjAnterior;
	}

	public void setCnpjAnterior(String cnpjAnterior) {
		this.cnpjAnterior = cnpjAnterior;
	}

	public String getCnpjAtual() {
		return cnpjAtual;
	}

	public void setCnpjAtual(String cnpjAtual) {
		this.cnpjAtual = cnpjAtual;
	}

	public String getCpfAnterior() {
		return cpfAnterior;
	}

	public void setCpfAnterior(String cpfAnterior) {
		this.cpfAnterior = cpfAnterior;
	}

	public String getCpfAtual() {
		return cpfAtual;
	}

	public void setCpfAtual(String cpfAtual) {
		this.cpfAtual = cpfAtual;
	}

	public String getCpfSolicitante() {
		return cpfSolicitante;
	}

	public void setCpfSolicitante(String cpfSolicitante) {
		this.cpfSolicitante = cpfSolicitante;
	}

	public String getEmailAnterior() {
		return emailAnterior;
	}

	public void setEmailAnterior(String emailAnterior) {
		this.emailAnterior = emailAnterior;
	}

	public String getEmailAtual() {
		return emailAtual;
	}

	public void setEmailAtual(String emailAtual) {
		this.emailAtual = emailAtual;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeClienteAnterior() {
		return nomeClienteAnterior;
	}

	public void setNomeClienteAnterior(String nomeClienteAnterior) {
		this.nomeClienteAnterior = nomeClienteAnterior;
	}

	public String getNomeClienteAtual() {
		return nomeClienteAtual;
	}

	public void setNomeClienteAtual(String nomeClienteAtual) {
		this.nomeClienteAtual = nomeClienteAtual;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	@Override
	public Date getUltimaAlteracao() {
		// TODO Auto-generated method stub
		return this.ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		// TODO Auto-generated method stub
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(Integer telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

}