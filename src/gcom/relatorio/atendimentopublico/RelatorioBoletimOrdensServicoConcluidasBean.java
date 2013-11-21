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
package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0766] - Relatorio Boletim de Ordens de Servico Concluidas
 * 
 * @author Ivan Sérgio
 * @date 07/05/2008
 * 
 */
public class RelatorioBoletimOrdensServicoConcluidasBean implements RelatorioBean {

	private String referenciaEncerramentoMes;
	private String referenciaEncerramentoAno;
	private String situacao;
	private String idLocalidade;
	private String nomeLocalidade;
	private String idLocalInstalacao;
	private String descricaoLocalInstalacao;
	private String nomeAbreviadoFirma;
	private String inscricao;
	private String idOrdemServico;
	private String tipoServico;
	private String trocaProtecao;
	private String trocaRegistro;
	private String dataGeracaoOrdemServico;
	private String dataEncerramentoOrdemServico;
	private String dataFiscalizacao1;
	private String dataFiscalizacao2;
	private String dataFiscalizacao3;
	private String paga;
	private String idImovel;
	private String codigoSetorComercial;
	private String idSetorComercial;
	
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public RelatorioBoletimOrdensServicoConcluidasBean () {}

	public String getDataEncerramentoOrdemServico() {
		return dataEncerramentoOrdemServico;
	}

	public void setDataEncerramentoOrdemServico(String dataEncerramentoOrdemServico) {
		this.dataEncerramentoOrdemServico = dataEncerramentoOrdemServico;
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

	public String getDataGeracaoOrdemServico() {
		return dataGeracaoOrdemServico;
	}

	public void setDataGeracaoOrdemServico(String dataGeracaoOrdemServico) {
		this.dataGeracaoOrdemServico = dataGeracaoOrdemServico;
	}

	public String getDescricaoLocalInstalacao() {
		return descricaoLocalInstalacao;
	}

	public void setDescricaoLocalInstalacao(String descricaoLocalInstalacao) {
		this.descricaoLocalInstalacao = descricaoLocalInstalacao;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdLocalInstalacao() {
		return idLocalInstalacao;
	}

	public void setIdLocalInstalacao(String idLocalInstalacao) {
		this.idLocalInstalacao = idLocalInstalacao;
	}

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getNomeAbreviadoFirma() {
		return nomeAbreviadoFirma;
	}

	public void setNomeAbreviadoFirma(String nomeAbreviadoFirma) {
		this.nomeAbreviadoFirma = nomeAbreviadoFirma;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getPaga() {
		return paga;
	}

	public void setPaga(String paga) {
		this.paga = paga;
	}

	public String getReferenciaEncerramentoAno() {
		return referenciaEncerramentoAno;
	}

	public void setReferenciaEncerramentoAno(String referenciaEncerramentoAno) {
		this.referenciaEncerramentoAno = referenciaEncerramentoAno;
	}

	public String getReferenciaEncerramentoMes() {
		return referenciaEncerramentoMes;
	}

	public void setReferenciaEncerramentoMes(String referenciaEncerramentoMes) {
		this.referenciaEncerramentoMes = referenciaEncerramentoMes;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getTrocaProtecao() {
		return trocaProtecao;
	}

	public void setTrocaProtecao(String trocaProtecao) {
		this.trocaProtecao = trocaProtecao;
	}

	public String getTrocaRegistro() {
		return trocaRegistro;
	}

	public void setTrocaRegistro(String trocaRegistro) {
		this.trocaRegistro = trocaRegistro;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
}
