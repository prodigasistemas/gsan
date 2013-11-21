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

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form responsável pelas propiedades do caso de uso de gerar movimento
 * de débito automático para o banco
 * 
 * @author Sávio Luiz
 * @date 17/04/2006
 */
public class GerarMovimentoDebitoAutomaticoBancoActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String opcaoMovimentoDebitoAutomatico;

	private String idGrupoFaturamento;
	
	private String idGrupoFaturamentoSelecionados;

	private String mesAnoFaturamento;

	private String opcaoEnvioParaBanco;
	
	private String idArrecadadorMovimento;

	private String codigoBancoMovimento;

	private String codigoRemessaMovimento;

	private String identificacaoServicoMovimento;

	private String numeroSequencialMovimento;
	
	private String[] idsCodigosBancos;
	
	private Date dataAtual;

	public String getCodigoBancoMovimento() {
		return codigoBancoMovimento;
	}

	public void setCodigoBancoMovimento(String codigoBancoMovimento) {
		this.codigoBancoMovimento = codigoBancoMovimento;
	}

	public String getCodigoRemessaMovimento() {
		return codigoRemessaMovimento;
	}

	public void setCodigoRemessaMovimento(String codigoRemessaMovimento) {
		this.codigoRemessaMovimento = codigoRemessaMovimento;
	}

	public String getIdentificacaoServicoMovimento() {
		return identificacaoServicoMovimento;
	}

	public void setIdentificacaoServicoMovimento(
			String identificacaoServicoMovimento) {
		this.identificacaoServicoMovimento = identificacaoServicoMovimento;
	}

	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public String getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}

	
	public String getNumeroSequencialMovimento() {
		return numeroSequencialMovimento;
	}

	public void setNumeroSequencialMovimento(String numeroSequencialMovimento) {
		this.numeroSequencialMovimento = numeroSequencialMovimento;
	}

	public String getOpcaoEnvioParaBanco() {
		return opcaoEnvioParaBanco;
	}

	public void setOpcaoEnvioParaBanco(String opcaoEnvioParaBanco) {
		this.opcaoEnvioParaBanco = opcaoEnvioParaBanco;
	}

	public String getOpcaoMovimentoDebitoAutomatico() {
		return opcaoMovimentoDebitoAutomatico;
	}

	public void setOpcaoMovimentoDebitoAutomatico(
			String opcaoMovimentoDebitoAutomatico) {
		this.opcaoMovimentoDebitoAutomatico = opcaoMovimentoDebitoAutomatico;
	}
	
	

	public String[] getIdsCodigosBancos() {
		return idsCodigosBancos;
	}

	public void setIdsCodigosBancos(String[] idsCodigosBancos) {
		this.idsCodigosBancos = idsCodigosBancos;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		opcaoMovimentoDebitoAutomatico = null;
		idGrupoFaturamento = "";
		mesAnoFaturamento = null;
		opcaoEnvioParaBanco = null;
		codigoBancoMovimento = null;
		codigoRemessaMovimento = null;
		identificacaoServicoMovimento = null;
		numeroSequencialMovimento = null;
		idsCodigosBancos = null;
	}

	public void setMesAnoFaturamento(String mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}

	public String getIdArrecadadorMovimento() {
		return idArrecadadorMovimento;
	}

	public void setIdArrecadadorMovimento(String idArrecadadorMovimento) {
		this.idArrecadadorMovimento = idArrecadadorMovimento;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public String getIdGrupoFaturamentoSelecionados() {
		return idGrupoFaturamentoSelecionados;
	}

	public void setIdGrupoFaturamentoSelecionados(
			String idGrupoFaturamentoSelecionados) {
		this.idGrupoFaturamentoSelecionados = idGrupoFaturamentoSelecionados;
	}
	
	

}