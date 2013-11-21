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
package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioDadosComplementaresImovelBean implements RelatorioBean {
	
	private String matriculaImovel;

	private String inscricaoImovel;

	private String situacaoAguaImovel;

	private String situacaoEsgotoImovel;

	private String tarifaConsumo;

	private String quantidadeRetificacoes;

	private String quantidadeParcelamento;

	private String quantidadeReparcelamento;

	private String quantidadeReparcelamentosConsecutivos;

	private String situacaoCobranca;

	private String funcionarioResponsavel;

	private String informacoesComplementares;
	
	private JRBeanCollectionDataSource colecaoVencimentosAlternativos;

	private JRBeanCollectionDataSource colecaoDebitosAutomaticos;

	private JRBeanCollectionDataSource colecaoOcorrenciaCadastro;

	private JRBeanCollectionDataSource colecaoAnormalidadesLocalidadePolo;

	private JRBeanCollectionDataSource colecaoSituacoesEspeciaisFaturamento;

	private JRBeanCollectionDataSource colecaoSituacoesEspeciaisCobranca;

	private JRBeanCollectionDataSource colecaoRamoAtividade;
	
	private JRBeanCollectionDataSource colecaoSituacoesCobranca;

	public RelatorioDadosComplementaresImovelBean() {
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String id) {
		this.inscricaoImovel = id;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String codigo) {
		this.matriculaImovel = codigo;
	}

	public String getSituacaoAguaImovel() {
		return situacaoAguaImovel;
	}

	public void setSituacaoAguaImovel(String codAgencia) {
		this.situacaoAguaImovel = codAgencia;
	}

	public String getSituacaoEsgotoImovel() {
		return situacaoEsgotoImovel;
	}

	public void setSituacaoEsgotoImovel(String nomeBanco) {
		this.situacaoEsgotoImovel = nomeBanco;
	}

	public String getTarifaConsumo() {
		return tarifaConsumo;
	}

	public void setTarifaConsumo(String perfilImovel) {
		this.tarifaConsumo = perfilImovel;
	}

	public String getQuantidadeRetificacoes() {
		return quantidadeRetificacoes;
	}

	public void setQuantidadeRetificacoes(String tipoDespejo) {
		this.quantidadeRetificacoes = tipoDespejo;
	}

	public String getQuantidadeParcelamento() {
		return quantidadeParcelamento;
	}

	public void setQuantidadeParcelamento(String areaConstruida) {
		this.quantidadeParcelamento = areaConstruida;
	}

	public String getQuantidadeReparcelamento() {
		return quantidadeReparcelamento;
	}

	public void setQuantidadeReparcelamento(String testadaLote) {
		this.quantidadeReparcelamento = testadaLote;
	}

	public String getQuantidadeReparcelamentosConsecutivos() {
		return quantidadeReparcelamentosConsecutivos;
	}

	public void setQuantidadeReparcelamentosConsecutivos(String volumeInferiorReservatorio) {
		this.quantidadeReparcelamentosConsecutivos = volumeInferiorReservatorio;
	}

	public String getSituacaoCobranca() {
		return situacaoCobranca;
	}

	public void setSituacaoCobranca(String volumeSuperiorReservatorio) {
		this.situacaoCobranca = volumeSuperiorReservatorio;
	}

	public String getFuncionarioResponsavel() {
		return funcionarioResponsavel;
	}

	public void setFuncionarioResponsavel(String volumePiscina) {
		this.funcionarioResponsavel = volumePiscina;
	}

	public String getInformacoesComplementares() {
		return informacoesComplementares;
	}

	public void setInformacoesComplementares(String fonteAbastecimento) {
		this.informacoesComplementares = fonteAbastecimento;
	}

	public JRBeanCollectionDataSource getColecaoVencimentosAlternativos() {
		return colecaoVencimentosAlternativos;
	}

	public void setColecaoVencimentosAlternativos(
			JRBeanCollectionDataSource colecaoClienteImovelHelper) {
		this.colecaoVencimentosAlternativos = colecaoClienteImovelHelper;
	}

	public JRBeanCollectionDataSource getColecaoOcorrenciaCadastro() {
		return colecaoOcorrenciaCadastro;
	}

	public void setColecaoOcorrenciaCadastro(
			JRBeanCollectionDataSource colecaoOcorrenciaCadastro) {
		this.colecaoOcorrenciaCadastro = colecaoOcorrenciaCadastro;
	}

	public JRBeanCollectionDataSource getColecaoAnormalidadesLocalidadePolo() {
		return colecaoAnormalidadesLocalidadePolo;
	}

	public void setColecaoAnormalidadesLocalidadePolo(
			JRBeanCollectionDataSource colecaoAnormalidadesLocalidadePolo) {
		this.colecaoAnormalidadesLocalidadePolo = colecaoAnormalidadesLocalidadePolo;
	}

	public JRBeanCollectionDataSource getColecaoSituacoesEspeciaisFaturamento() {
		return colecaoSituacoesEspeciaisFaturamento;
	}

	public void setColecaoSituacoesEspeciaisFaturamento(
			JRBeanCollectionDataSource colecaoSituacoesEspeciaisFaturamento) {
		this.colecaoSituacoesEspeciaisFaturamento = colecaoSituacoesEspeciaisFaturamento;
	}

	public JRBeanCollectionDataSource getColecaoSituacoesEspeciaisCobranca() {
		return colecaoSituacoesEspeciaisCobranca;
	}

	public void setColecaoSituacoesEspeciaisCobranca(
			JRBeanCollectionDataSource colecaoSituacoesEspeciaisCobranca) {
		this.colecaoSituacoesEspeciaisCobranca = colecaoSituacoesEspeciaisCobranca;
	}
	
	public JRBeanCollectionDataSource getColecaoDebitosAutomaticos() {
		return colecaoDebitosAutomaticos;
	}

	public void setColecaoDebitosAutomaticos(
			JRBeanCollectionDataSource coelcaoClienteImovelEconomia) {
		
		this.colecaoDebitosAutomaticos = coelcaoClienteImovelEconomia;
	}

	public JRBeanCollectionDataSource getColecaoRamoAtividade() {
		return colecaoRamoAtividade;
	}

	public void setColecaoRamoAtividade(
			JRBeanCollectionDataSource colecaoRamoAtividade) {
		this.colecaoRamoAtividade = colecaoRamoAtividade;
	}

	public JRBeanCollectionDataSource getColecaoSituacoesCobranca() {
		return colecaoSituacoesCobranca;
	}

	public void setColecaoSituacoesCobranca(
			JRBeanCollectionDataSource colecaoSituacoesCobranca) {
		this.colecaoSituacoesCobranca = colecaoSituacoesCobranca;
	}

}