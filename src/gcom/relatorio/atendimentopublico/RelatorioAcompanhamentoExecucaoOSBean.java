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
package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0492] - Gerar Relatório Acompanhamento de Execução de Ordem de Serviço
 * 
 * @author Rafael Corrêa
 * @date 07/11/2006
 */
public class RelatorioAcompanhamentoExecucaoOSBean implements RelatorioBean {

	private String idOS;

	private String unidadeAtual;

	private String situacao;

	private String tipoServico;

	private String idRA;

	private String endereco;

	private String origem;

	private String dataSolicitacao;

	private String dataProgramacao;

	private String dataEncerramento;

	private String equipe;

	private String diasAtraso;

	private String total;

	private String totalGeral;

	public RelatorioAcompanhamentoExecucaoOSBean(String idOS,
			String unidadeAtual, String situacao, String tipoServico,
			String idRA, String endereco, String origem,
			String dataSolicitacao, String dataProgramacao,
			String dataEncerramento, String equipe, String diasAtraso,
			String total, String totalGeral) {

		this.idOS = idOS;
		this.unidadeAtual = unidadeAtual;
		this.situacao = situacao;
		this.tipoServico = tipoServico;
		this.idRA = idRA;
		this.endereco = endereco;
		this.origem = origem;
		this.dataSolicitacao = dataSolicitacao;
		this.dataProgramacao = dataProgramacao;
		this.dataEncerramento = dataEncerramento;
		this.equipe = equipe;
		this.diasAtraso = diasAtraso;
		this.total = total;
		this.totalGeral = totalGeral;

	}

	/**
	 * @return Retorna o campo totalGeral.
	 */
	public String getTotalGeral() {
		return totalGeral;
	}

	/**
	 * @param totalGeral
	 *            O totalGeral a ser setado.
	 */
	public void setTotalGeral(String totalGeral) {
		this.totalGeral = totalGeral;
	}

	/**
	 * @return Retorna o campo dataEncerramento.
	 */
	public String getDataEncerramento() {
		return dataEncerramento;
	}

	/**
	 * @param dataEncerramento
	 *            O dataEncerramento a ser setado.
	 */
	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	/**
	 * @return Retorna o campo dataProgramacao.
	 */
	public String getDataProgramacao() {
		return dataProgramacao;
	}

	/**
	 * @param dataProgramacao
	 *            O dataProgramacao a ser setado.
	 */
	public void setDataProgramacao(String dataProgramacao) {
		this.dataProgramacao = dataProgramacao;
	}

	/**
	 * @return Retorna o campo dataSolicitacao.
	 */
	public String getDataSolicitacao() {
		return dataSolicitacao;
	}

	/**
	 * @param dataSolicitacao
	 *            O dataSolicitacao a ser setado.
	 */
	public void setDataSolicitacao(String dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	/**
	 * @return Retorna o campo diasAtraso.
	 */
	public String getDiasAtraso() {
		return diasAtraso;
	}

	/**
	 * @param diasAtraso
	 *            O diasAtraso a ser setado.
	 */
	public void setDiasAtraso(String diasAtraso) {
		this.diasAtraso = diasAtraso;
	}

	/**
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco
	 *            O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return Retorna o campo equipe.
	 */
	public String getEquipe() {
		return equipe;
	}

	/**
	 * @param equipe
	 *            O equipe a ser setado.
	 */
	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}

	/**
	 * @return Retorna o campo idOS.
	 */
	public String getIdOS() {
		return idOS;
	}

	/**
	 * @param idOS
	 *            O idOS a ser setado.
	 */
	public void setIdOS(String idOS) {
		this.idOS = idOS;
	}

	/**
	 * @return Retorna o campo idRA.
	 */
	public String getIdRA() {
		return idRA;
	}

	/**
	 * @param idRA
	 *            O idRA a ser setado.
	 */
	public void setIdRA(String idRA) {
		this.idRA = idRA;
	}

	/**
	 * @return Retorna o campo origem.
	 */
	public String getOrigem() {
		return origem;
	}

	/**
	 * @param origem
	 *            O origem a ser setado.
	 */
	public void setOrigem(String origem) {
		this.origem = origem;
	}

	/**
	 * @return Retorna o campo situacao.
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao
	 *            O situacao a ser setado.
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	/**
	 * @return Retorna o campo tipoServico.
	 */
	public String getTipoServico() {
		return tipoServico;
	}

	/**
	 * @param tipoServico
	 *            O tipoServico a ser setado.
	 */
	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	/**
	 * @return Retorna o campo unidadeAtual.
	 */
	public String getUnidadeAtual() {
		return unidadeAtual;
	}

	/**
	 * @param unidadeAtual
	 *            O unidadeAtual a ser setado.
	 */
	public void setUnidadeAtual(String unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
	}

	/**
	 * @return Retorna o campo total.
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            O total a ser setado.
	 */
	public void setTotal(String total) {
		this.total = total;
	}

}
