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
package gcom.gui.atendimentopublico.ordemservico;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
 * @author Vivianne Sousa
 * @date 20/05/2011
 */
public class GerarOSSeletivaFiscalizacaoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idLocalidadeInicial;
	private String descricaoLocalidadeInicial;
	private String idLocalidadeFinal;
	private String descricaoLocalidadeFinal;
	private String idServicoTipo;
	private String descricaoServicoTipo;
	private String grupoCobranca;
	private String gerenciaRegional;
	private String unidadeNegocio;
	private String qtdeDiasEncerramentoOS;
	private String qtdeOSEncerradasComConclusao;
	private String qtdeOSEncerradasSemConclusao;
	private String percentualOSgeradas;
	private String idOrdemServico;
	private String nomeOrdemServico;
	
	public String getDescricaoLocalidadeFinal() {
		return descricaoLocalidadeFinal;
	}
	public void setDescricaoLocalidadeFinal(String descricaoLocalidadeFinal) {
		this.descricaoLocalidadeFinal = descricaoLocalidadeFinal;
	}
	public String getDescricaoLocalidadeInicial() {
		return descricaoLocalidadeInicial;
	}
	public void setDescricaoLocalidadeInicial(String descricaoLocalidadeInicial) {
		this.descricaoLocalidadeInicial = descricaoLocalidadeInicial;
	}
	public String getDescricaoServicoTipo() {
		return descricaoServicoTipo;
	}
	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		this.descricaoServicoTipo = descricaoServicoTipo;
	}
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public String getGrupoCobranca() {
		return grupoCobranca;
	}
	public void setGrupoCobranca(String grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}
	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}
	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}
	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}
	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}
	public String getIdServicoTipo() {
		return idServicoTipo;
	}
	public void setIdServicoTipo(String idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}
	public String getPercentualOSgeradas() {
		return percentualOSgeradas;
	}
	public void setPercentualOSgeradas(String percentualOSgeradas) {
		this.percentualOSgeradas = percentualOSgeradas;
	}
	public String getQtdeDiasEncerramentoOS() {
		return qtdeDiasEncerramentoOS;
	}
	public void setQtdeDiasEncerramentoOS(String qtdeDiasEncerramentoOS) {
		this.qtdeDiasEncerramentoOS = qtdeDiasEncerramentoOS;
	}
	public String getQtdeOSEncerradasComConclusao() {
		return qtdeOSEncerradasComConclusao;
	}
	public void setQtdeOSEncerradasComConclusao(String qtdeOSEncerradasComConclusao) {
		this.qtdeOSEncerradasComConclusao = qtdeOSEncerradasComConclusao;
	}
	public String getQtdeOSEncerradasSemConclusao() {
		return qtdeOSEncerradasSemConclusao;
	}
	public void setQtdeOSEncerradasSemConclusao(String qtdeOSEncerradasSemConclusao) {
		this.qtdeOSEncerradasSemConclusao = qtdeOSEncerradasSemConclusao;
	}
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public String getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public String getNomeOrdemServico() {
		return nomeOrdemServico;
	}
	public void setNomeOrdemServico(String nomeOrdemServico) {
		this.nomeOrdemServico = nomeOrdemServico;
	}
	
	
}