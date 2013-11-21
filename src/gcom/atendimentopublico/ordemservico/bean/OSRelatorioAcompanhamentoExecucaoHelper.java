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
package gcom.atendimentopublico.ordemservico.bean;

import java.io.Serializable;
import java.util.Date;


/**
 * [UC0450] Filtrar Registro de Atendimento
 * 
 * Classe facilitadora para o retorno do filtro a ser usado no manter.
 * 
 * @author Rafael Pinto
 * @date 18/08/2006
 */
public class OSRelatorioAcompanhamentoExecucaoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer idOrdemServico;
	private String situacaoOS;
	private Integer idServicoTipo;
	private String descricaoServicoTipo;
	private Integer idRegistroAtendimento;
	private Date dataSolicitacao;
	private Date dataEncerramento;
	private Date dataProgramacao;
	private Integer idUnidadeAtendimento;
	private String nomeUnidadeAtendimento;
	private String nomeUnidadeAtual;
	private String endereco;
	private String nomeEquipe;
	private Date dataEncerramentoRA;
	private Integer diasPrazo;
	
	/**
	 * @return Retorna o campo diasAtraso.
	 */
	public Integer getDiasPrazo() {
		return diasPrazo;
	}

	/**
	 * @param diasAtraso O diasAtraso a ser setado.
	 */
	public void setDiasPrazo(Integer diasPrazo) {
		this.diasPrazo = diasPrazo;
	}

	/**
	 * @return Retorna o campo nomeEquipe.
	 */
	public String getNomeEquipe() {
		return nomeEquipe;
	}

	/**
	 * @param nomeEquipe O nomeEquipe a ser setado.
	 */
	public void setNomeEquipe(String nomeEquipe) {
		this.nomeEquipe = nomeEquipe;
	}

	public OSRelatorioAcompanhamentoExecucaoHelper(){}

	/**
	 * @return Retorna o campo dataEncerramento.
	 */
	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	/**
	 * @param dataEncerramento O dataEncerramento a ser setado.
	 */
	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	/**
	 * @return Retorna o campo dataProgramacao.
	 */
	public Date getDataProgramacao() {
		return dataProgramacao;
	}

	/**
	 * @param dataProgramacao O dataProgramacao a ser setado.
	 */
	public void setDataProgramacao(Date dataProgramacao) {
		this.dataProgramacao = dataProgramacao;
	}

	/**
	 * @return Retorna o campo dataSolicitacao.
	 */
	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	/**
	 * @param dataSolicitacao O dataSolicitacao a ser setado.
	 */
	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	/**
	 * @return Retorna o campo descricaoServicoTipo.
	 */
	public String getDescricaoServicoTipo() {
		return descricaoServicoTipo;
	}

	/**
	 * @param descricaoServicoTipo O descricaoServicoTipo a ser setado.
	 */
	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		this.descricaoServicoTipo = descricaoServicoTipo;
	}

	/**
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return Retorna o campo idOrdemServico.
	 */
	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}

	/**
	 * @param idOrdemServico O idOrdemServico a ser setado.
	 */
	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	/**
	 * @return Retorna o campo idRegistroAtendimento.
	 */
	public Integer getIdRegistroAtendimento() {
		return idRegistroAtendimento;
	}

	/**
	 * @param idRegistroAtendimento O idRegistroAtendimento a ser setado.
	 */
	public void setIdRegistroAtendimento(Integer idRegistroAtendimento) {
		this.idRegistroAtendimento = idRegistroAtendimento;
	}

	/**
	 * @return Retorna o campo idServicoTipo.
	 */
	public Integer getIdServicoTipo() {
		return idServicoTipo;
	}

	/**
	 * @param idServicoTipo O idServicoTipo a ser setado.
	 */
	public void setIdServicoTipo(Integer idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}

	/**
	 * @return Retorna o campo idUnidadeAtendimento.
	 */
	public Integer getIdUnidadeAtendimento() {
		return idUnidadeAtendimento;
	}

	/**
	 * @param idUnidadeAtendimento O idUnidadeAtendimento a ser setado.
	 */
	public void setIdUnidadeAtendimento(Integer idUnidadeAtendimento) {
		this.idUnidadeAtendimento = idUnidadeAtendimento;
	}

	/**
	 * @return Retorna o campo nomeUnidadeAtendimento.
	 */
	public String getNomeUnidadeAtendimento() {
		return nomeUnidadeAtendimento;
	}

	/**
	 * @param nomeUnidadeAtendimento O nomeUnidadeAtendimento a ser setado.
	 */
	public void setNomeUnidadeAtendimento(String nomeUnidadeAtendimento) {
		this.nomeUnidadeAtendimento = nomeUnidadeAtendimento;
	}

	/**
	 * @return Retorna o campo situacaoOS.
	 */
	public String getSituacaoOS() {
		return situacaoOS;
	}

	/**
	 * @param situacaoOS O situacaoOS a ser setado.
	 */
	public void setSituacaoOS(String situacaoOS) {
		this.situacaoOS = situacaoOS;
	}

	/**
	 * @return Retorna o campo nomeUnidadeAtual.
	 */
	public String getNomeUnidadeAtual() {
		return nomeUnidadeAtual;
	}

	/**
	 * @param nomeUnidadeAtual O nomeUnidadeAtual a ser setado.
	 */
	public void setNomeUnidadeAtual(String nomeUnidadeAtual) {
		this.nomeUnidadeAtual = nomeUnidadeAtual;
	}

	/**
	 * @return Retorna o campo dataEncerramentoRA.
	 */
	public Date getDataEncerramentoRA() {
		return dataEncerramentoRA;
	}

	/**
	 * @param dataEncerramentoRA O dataEncerramentoRA a ser setado.
	 */
	public void setDataEncerramentoRA(Date dataEncerramentoRA) {
		this.dataEncerramentoRA = dataEncerramentoRA;
	}
	
	

}
