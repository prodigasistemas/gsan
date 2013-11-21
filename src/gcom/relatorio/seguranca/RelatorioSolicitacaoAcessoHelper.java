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
package gcom.relatorio.seguranca;

import java.io.Serializable;

/**
 * [UC1093] Gerar Relatório Solicitação Acesso
 * 
 * @author Hugo Leonardo
 *
 * @date 23/11/2010
 */
public class RelatorioSolicitacaoAcessoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String matricula; 
	private String idFuncionarioSolicitante; 
	private String nomeFuncionarioSolicitante;
	private String idFuncionarioSuperior; 
	private String nomeFuncionarioSuperior;
	private String nomeUsuario; 
	private String cpf; 
	private String idLotacao; 
	private String nomeLotacao; 
	private String dataInicial; 
	private String dataFinal; 
	private String dataSolicitacao;
	private String dataAutorizacao;
	private String situacaoAcesso;
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataAutorizacao() {
		return dataAutorizacao;
	}

	public void setDataAutorizacao(String dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(String dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public String getIdFuncionarioSolicitante() {
		return idFuncionarioSolicitante;
	}

	public void setIdFuncionarioSolicitante(String idFuncionarioSolicitante) {
		this.idFuncionarioSolicitante = idFuncionarioSolicitante;
	}

	public String getIdFuncionarioSuperior() {
		return idFuncionarioSuperior;
	}

	public void setIdFuncionarioSuperior(String idFuncionarioSuperior) {
		this.idFuncionarioSuperior = idFuncionarioSuperior;
	}

	public String getIdLotacao() {
		return idLotacao;
	}

	public void setIdLotacao(String idLotacao) {
		this.idLotacao = idLotacao;
	}

	public String getNomeFuncionarioSolicitante() {
		return nomeFuncionarioSolicitante;
	}

	public void setNomeFuncionarioSolicitante(String nomeFuncionarioSolicitante) {
		this.nomeFuncionarioSolicitante = nomeFuncionarioSolicitante;
	}

	public String getNomeFuncionarioSuperior() {
		return nomeFuncionarioSuperior;
	}

	public void setNomeFuncionarioSuperior(String nomeFuncionarioSuperior) {
		this.nomeFuncionarioSuperior = nomeFuncionarioSuperior;
	}

	public String getNomeLotacao() {
		return nomeLotacao;
	}

	public void setNomeLotacao(String nomeLotacao) {
		this.nomeLotacao = nomeLotacao;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSituacaoAcesso() {
		return situacaoAcesso;
	}

	public void setSituacaoAcesso(String situacaoAcesso) {
		this.situacaoAcesso = situacaoAcesso;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
}