/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.relatorio.RelatorioBean;

/**
 * classe responsável por criar o relatório de religação de clientes inadimplentes
 * 
 * [UC1120] Gerar Relatório de religação de clientes inadimplentes
 * 
 * @author Hugo Leonardo
 *
 * @date 25/01/2011
 */
public class RelatorioReligacaoClientesInadiplentesBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String matricula;
	private String endereco;
	private String perfil;
	private String numeroOS;
	private String usuarioAberturaOS;
	private String nomeUsuarioAberturaOS;
	private String dataEncerramento;
	private String usuarioEncerramentoOS;
	private String nomeUsuarioEncerramentoOS;
	private String valorDebito;
	
	private String nomeCliente;
	private String cpf;
	private String tipoRelacao;

    public RelatorioReligacaoClientesInadiplentesBean(){
    	
    }
    
	public RelatorioReligacaoClientesInadiplentesBean(String matricula, String endereco, String perfil, 
			String numeroOS, String usuarioAberturaOS, String nomeUsuarioAberturaOS, String dataEncerramento, 
			String usuarioEncerramentoOS, String nomeUsuarioEncerramentoOS, String valorDebito){
		
		this.matricula = matricula;
		this.endereco = endereco;
		this.perfil = perfil;
		this.numeroOS = numeroOS;
		this.usuarioAberturaOS = usuarioAberturaOS;
		this.dataEncerramento = dataEncerramento;
		this.usuarioEncerramentoOS = usuarioEncerramentoOS;
		this.valorDebito = valorDebito;
		this.nomeUsuarioAberturaOS = nomeUsuarioAberturaOS;
		this.nomeUsuarioEncerramentoOS = nomeUsuarioEncerramentoOS;
	}
	
	public RelatorioReligacaoClientesInadiplentesBean(
			String nomeCliente, String cpf, String tipoRelacao, 
			String matricula, String endereco, String perfil, 
			String numeroOS, String usuarioAberturaOS, String nomeUsuarioAberturaOS, String dataEncerramento, 
			String usuarioEncerramentoOS, String nomeUsuarioEncerramentoOS, String valorDebito){
		
		this.nomeCliente = nomeCliente;
		this.cpf = cpf;
		this.tipoRelacao = tipoRelacao;
		this.matricula = matricula;
		this.endereco = endereco;
		this.perfil = perfil;
		this.numeroOS = numeroOS;
		this.usuarioAberturaOS = usuarioAberturaOS;
		this.dataEncerramento = dataEncerramento;
		this.usuarioEncerramentoOS = usuarioEncerramentoOS;
		this.valorDebito = valorDebito;
		this.nomeUsuarioAberturaOS = nomeUsuarioAberturaOS;
		this.nomeUsuarioEncerramentoOS = nomeUsuarioEncerramentoOS;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getUsuarioAberturaOS() {
		return usuarioAberturaOS;
	}

	public void setUsuarioAberturaOS(String usuarioAberturaOS) {
		this.usuarioAberturaOS = usuarioAberturaOS;
	}

	public String getUsuarioEncerramentoOS() {
		return usuarioEncerramentoOS;
	}

	public void setUsuarioEncerramentoOS(String usuarioEncerramentoOS) {
		this.usuarioEncerramentoOS = usuarioEncerramentoOS;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	public String getNomeUsuarioAberturaOS() {
		return nomeUsuarioAberturaOS;
	}

	public void setNomeUsuarioAberturaOS(String nomeUsuarioAberturaOS) {
		this.nomeUsuarioAberturaOS = nomeUsuarioAberturaOS;
	}

	public String getNomeUsuarioEncerramentoOS() {
		return nomeUsuarioEncerramentoOS;
	}

	public void setNomeUsuarioEncerramentoOS(String nomeUsuarioEncerramentoOS) {
		this.nomeUsuarioEncerramentoOS = nomeUsuarioEncerramentoOS;
	}

}
