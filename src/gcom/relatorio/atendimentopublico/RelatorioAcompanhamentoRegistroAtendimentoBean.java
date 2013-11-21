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
package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Hugo Leonardo
 * @version 1.0
 */

public class RelatorioAcompanhamentoRegistroAtendimentoBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String idRA;
	private String tipoSolicitacao;
	private String dataAbertura;
	private String dataRecebimento;
	private String dataEncerramento;
	private String motivoEncerramento;
	private String quantidade;
	private String idUnidadeAtendimento;
	private String descricaoUnidadeAtendimento;
	private String idMunicipio;
	private String descricaoMunicipio;
	private Integer quantidadeTotal;
	private String situacaoRA;
	private String totalGeral;
	
	public RelatorioAcompanhamentoRegistroAtendimentoBean(){
		
	}
	
	// Síntetico
	public RelatorioAcompanhamentoRegistroAtendimentoBean(String idRA, String tipoSolicitacao, 
			String dataAbertura, String dataEncerramento,
			String motivoEncerramento, String idUnidadeAtendimento,
			String descricaoUnidadeAtendimento,
			String idMunicipio, String descricaoMunicipio) {
		
		this.idRA = idRA;
		this.tipoSolicitacao = tipoSolicitacao;
		this.dataAbertura = dataAbertura;
		this.dataEncerramento = dataEncerramento;
		this.motivoEncerramento = motivoEncerramento;
		this.idUnidadeAtendimento = idUnidadeAtendimento;
		this.descricaoUnidadeAtendimento = descricaoUnidadeAtendimento;
		this.idMunicipio = idMunicipio;
		this.descricaoMunicipio = descricaoMunicipio;
	}
	
	public RelatorioAcompanhamentoRegistroAtendimentoBean(String idUnidadeAtendimento, 
			String motivoEncerramento, String quantidade, String idMunicipio){
		
		this.idUnidadeAtendimento = idUnidadeAtendimento;
		this.idMunicipio = idMunicipio;
		this.quantidade = quantidade;
		this.motivoEncerramento = motivoEncerramento;
	}

	public String getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(String dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public String getIdRA() {
		return idRA;
	}

	public void setIdRA(String idRA) {
		this.idRA = idRA;
	}

	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}

	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}

	public String getTipoSolicitacao() {
		return tipoSolicitacao;
	}

	public void setTipoSolicitacao(String tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}

	public String getIdUnidadeAtendimento() {
		return idUnidadeAtendimento;
	}

	public void setIdUnidadeAtendimento(String idUnidadeAtendimento) {
		this.idUnidadeAtendimento = idUnidadeAtendimento;
	}

	public String getDescricaoUnidadeAtendimento() {
		return descricaoUnidadeAtendimento;
	}

	public void setDescricaoUnidadeAtendimento(String descricaoUnidadeAtendimento) {
		this.descricaoUnidadeAtendimento = descricaoUnidadeAtendimento;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(Integer quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}

	public String getSituacaoRA() {
		return situacaoRA;
	}

	public void setSituacaoRA(String situacaoRA) {
		this.situacaoRA = situacaoRA;
	}

	public String getTotalGeral() {
		return totalGeral;
	}

	public void setTotalGeral(String totalGeral) {
		this.totalGeral = totalGeral;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}

	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}	
}
