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
package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1198] Gerar Relatóo de Arrecadação das Multas de Autos de Infração Pendentes 
 * @author Hugo Azevedo
 * @date 12/07/2011
 */
public class RelatorioMultasAutosInfracaoPendentesBean implements RelatorioBean {
	
	private String idGrupoFaturamento;
	private String grupoFaturamento;
	private String idFuncionario;
	private String nomeFuncionario;
	private String idLocalidade;
	private String nomeLocalidade;
	private String rota;
	private String matriculaImovel;
	private String nomeCliente;
	private String endereco;
	private String autoInfracao;
	private String descricaoServico;
	private String dataAutuacao;

	
	public RelatorioMultasAutosInfracaoPendentesBean(){}

	
	public String getIdGrupoFaturamento(){
		return idGrupoFaturamento;
	}
	
	
	public void setIdGrupoFaturamento(String idGrupoFaturamento){
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}


	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}


	public String getIdFuncionario() {
		return idFuncionario;
	}


	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}


	public String getNomeFuncionario() {
		return nomeFuncionario;
	}


	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}


	public String getIdLocalidade() {
		return idLocalidade;
	}


	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}


	public String getNomeLocalidade() {
		return nomeLocalidade;
	}


	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}


	public String getRota() {
		return rota;
	}


	public void setRota(String rota) {
		this.rota = rota;
	}


	public String getMatriculaImovel() {
		return matriculaImovel;
	}


	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}


	public String getNomeCliente() {
		return nomeCliente;
	}


	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getAutoInfracao() {
		return autoInfracao;
	}


	public void setAutoInfracao(String autoInfracao) {
		this.autoInfracao = autoInfracao;
	}


	public String getDescricaoServico() {
		return descricaoServico;
	}


	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}


	public String getDataAutuacao() {
		return dataAutuacao;
	}


	public void setDataAutuacao(String dataAutuacao) {
		this.dataAutuacao = dataAutuacao;
	}
	
	
	
	
}
