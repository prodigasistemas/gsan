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
package gcom.gui.arrecadacao;

import java.util.Collection;

import org.apache.struts.action.ActionForm;

/**
 * @author Vivianne Sousa
 * @date 05/12/2006
 */
public class PesquisarItensMovimentoArrecadadorActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	
	private String matriculaImovel;
	
	private String inscricaoImovel;
	
	private String descricaoOcorrencia;
	
	private String indicadorAceitacao;
	
	private String idArrecadadorMovimento;
	
	private String descricaoFormaArrecadacao;
	
	private String formaArrecadacao;
	
	private String indicadorDiferencaValorMovimentoValorPagamento;
	
	private Collection colecaoArrecadadorMovimentoItem;
	
	private String valorMovimento;
	
	private String valorPagamento;
	
	private String nomeBanco;
	
	private String nomeAgencia;

	public String getValorMovimento() {
		return valorMovimento;
	}

	public void setValorMovimento(String valorMovimento) {
		this.valorMovimento = valorMovimento;
	}

	public String getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(String valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public String getIndicadorDiferencaValorMovimentoValorPagamento() {
		return indicadorDiferencaValorMovimentoValorPagamento;
	}

	public void setIndicadorDiferencaValorMovimentoValorPagamento(
			String indicadorDiferencaValorMovimentoValorPagamento) {
		this.indicadorDiferencaValorMovimentoValorPagamento = indicadorDiferencaValorMovimentoValorPagamento;
	}

	public String getFormaArrecadacao() {
		return formaArrecadacao;
	}

	public void setFormaArrecadacao(String formaArrecadacao) {
		this.formaArrecadacao = formaArrecadacao;
	}

	public String getIdArrecadadorMovimento() {
		return idArrecadadorMovimento;
	}

	public void setIdArrecadadorMovimento(String idArrecadadorMovimento) {
		this.idArrecadadorMovimento = idArrecadadorMovimento;
	}

	public String getDescricaoOcorrencia() {
		return descricaoOcorrencia;
	}

	public void setDescricaoOcorrencia(String descricaoOcorrencia) {
		this.descricaoOcorrencia = descricaoOcorrencia;
	}

	public String getIndicadorAceitacao() {
		return indicadorAceitacao;
	}

	public void setIndicadorAceitacao(String indicadorAceitacao) {
		this.indicadorAceitacao = indicadorAceitacao;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public Collection getColecaoArrecadadorMovimentoItem() {
		return colecaoArrecadadorMovimentoItem;
	}

	public void setColecaoArrecadadorMovimentoItem(
			Collection colecaoArrecadadorMovimentoItem) {
		this.colecaoArrecadadorMovimentoItem = colecaoArrecadadorMovimentoItem;
	}

	public String getDescricaoFormaArrecadacao() {
		return descricaoFormaArrecadacao;
	}

	public void setDescricaoFormaArrecadacao(String descricaoFormaArrecadacao) {
		this.descricaoFormaArrecadacao = descricaoFormaArrecadacao;
	}

	public String getNomeAgencia() {
		return nomeAgencia;
	}

	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}
	

	
}
