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

import java.util.Date;


/**
 * [UC0753] Manter Ordem de Servico Concluida
 * 
 * @author Ivan Sérgio
 * @created 01/04/2008
 * 
 */
public class ManterOrdemServicoConcluidaHelper {
	private Integer idOrdemServico;
	private Date dataEmissao;
	private Date dataEncerramento;
	private Integer idImovel;
	private Short codigoFiscalizacao;
	private Date dataFiscalizacao1;
	private Date dataFiscalizacao2;
	private Date dataFiscalizacao3;
	private Integer idUsuario;
	private Integer idFuncionario;
	private Short indicadorTrocaProtecao;
	private Short indicadorTrocaRegistro;
	private String descricaoHidrometroLocalInstalacao;
	private Date dataEncerramentoBoletim;
	private Date dataUltimaAlteracao;	
	
	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}
	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	public Date getDataEncerramentoBoletim() {
		return dataEncerramentoBoletim;
	}
	public void setDataEncerramentoBoletim(Date dataEncerramentoBoletim) {
		this.dataEncerramentoBoletim = dataEncerramentoBoletim;
	}
	public Short getCodigoFiscalizacao() {
		return codigoFiscalizacao;
	}
	public void setCodigoFiscalizacao(Short codigoFiscalizacao) {
		this.codigoFiscalizacao = codigoFiscalizacao;
	}
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public Date getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	public Date getDataFiscalizacao1() {
		return dataFiscalizacao1;
	}
	public void setDataFiscalizacao1(Date dataFiscalizacao1) {
		this.dataFiscalizacao1 = dataFiscalizacao1;
	}
	public Date getDataFiscalizacao2() {
		return dataFiscalizacao2;
	}
	public void setDataFiscalizacao2(Date dataFiscalizacao2) {
		this.dataFiscalizacao2 = dataFiscalizacao2;
	}
	public Date getDataFiscalizacao3() {
		return dataFiscalizacao3;
	}
	public void setDataFiscalizacao3(Date dataFiscalizacao3) {
		this.dataFiscalizacao3 = dataFiscalizacao3;
	}
	public String getDescricaoHidrometroLocalInstalacao() {
		return descricaoHidrometroLocalInstalacao;
	}
	public void setDescricaoHidrometroLocalInstalacao(
			String descricaoHidrometroLocalInstalacao) {
		this.descricaoHidrometroLocalInstalacao = descricaoHidrometroLocalInstalacao;
	}
	public Integer getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Short getIndicadorTrocaProtecao() {
		return indicadorTrocaProtecao;
	}
	public void setIndicadorTrocaProtecao(Short indicadorTrocaProtecao) {
		this.indicadorTrocaProtecao = indicadorTrocaProtecao;
	}
	public Short getIndicadorTrocaRegistro() {
		return indicadorTrocaRegistro;
	}
	public void setIndicadorTrocaRegistro(Short indicadorTrocaRegistro) {
		this.indicadorTrocaRegistro = indicadorTrocaRegistro;
	}
	public Integer getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
}