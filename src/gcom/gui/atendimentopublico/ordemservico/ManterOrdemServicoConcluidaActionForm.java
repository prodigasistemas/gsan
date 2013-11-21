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
* Ivan Sérgio Virginio da Silva Júnior
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
 * [UC0753] Manter Ordem de Servico Concluida
 * 
 * @author Ivan Sérgio
 * @created 26/03/2008
 * 
 */
public class ManterOrdemServicoConcluidaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idOrdemServico;
	private String idOrdemServicoPesquisado;
	private String dataEmissao;
	private String dataEncerramento;
	private String idImovel;
	private String codigoFiscalizacao;
	private String codigoFiscalizacaoAnterior;
	private String dataFiscalizacao1;
	private String dataFiscalizacao2;
	private String dataFiscalizacao3;
	private String idUsuario;
	private String idFuncionario;
	private String indicadorTrocaProtecao;
	private String indicadorTrocaRegistro;
	private String descricaoHidrometroLocalInstalacao;
	private String dataEncerramentoBoletim;
	private String dataUltimaAlteracao;
	
	public String getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}
	public void setDataUltimaAlteracao(String dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	public String getDataEncerramentoBoletim() {
		return dataEncerramentoBoletim;
	}
	public void setDataEncerramentoBoletim(String dataEncerramentoBoletim) {
		this.dataEncerramentoBoletim = dataEncerramentoBoletim;
	}
	public String getCodigoFiscalizacao() {
		return codigoFiscalizacao;
	}
	public void setCodigoFiscalizacao(String codigoFiscalizacao) {
		this.codigoFiscalizacao = codigoFiscalizacao;
	}
	public String getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public String getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	public String getDataFiscalizacao1() {
		return dataFiscalizacao1;
	}
	public void setDataFiscalizacao1(String dataFiscalizacao1) {
		this.dataFiscalizacao1 = dataFiscalizacao1;
	}
	public String getDataFiscalizacao2() {
		return dataFiscalizacao2;
	}
	public void setDataFiscalizacao2(String dataFiscalizacao2) {
		this.dataFiscalizacao2 = dataFiscalizacao2;
	}
	public String getDataFiscalizacao3() {
		return dataFiscalizacao3;
	}
	public void setDataFiscalizacao3(String dataFiscalizacao3) {
		this.dataFiscalizacao3 = dataFiscalizacao3;
	}
	public String getDescricaoHidrometroLocalInstalacao() {
		return descricaoHidrometroLocalInstalacao;
	}
	public void setDescricaoHidrometroLocalInstalacao(
			String descricaoHidrometroLocalInstalacao) {
		this.descricaoHidrometroLocalInstalacao = descricaoHidrometroLocalInstalacao;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getIndicadorTrocaProtecao() {
		return indicadorTrocaProtecao;
	}
	public void setIndicadorTrocaProtecao(String indicadorTrocaProtecao) {
		this.indicadorTrocaProtecao = indicadorTrocaProtecao;
	}
	public String getIndicadorTrocaRegistro() {
		return indicadorTrocaRegistro;
	}
	public void setIndicadorTrocaRegistro(String indicadorTrocaRegistro) {
		this.indicadorTrocaRegistro = indicadorTrocaRegistro;
	}
	public String getIdOrdemServicoPesquisado() {
		return idOrdemServicoPesquisado;
	}
	public void setIdOrdemServicoPesquisado(String idOrdemServicoPesquisado) {
		this.idOrdemServicoPesquisado = idOrdemServicoPesquisado;
	}
	public String getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public String getCodigoFiscalizacaoAnterior() {
		return codigoFiscalizacaoAnterior;
	}
	public void setCodigoFiscalizacaoAnterior(String codigoFiscalizacaoAnterior) {
		this.codigoFiscalizacaoAnterior = codigoFiscalizacaoAnterior;
	}
	
	
}