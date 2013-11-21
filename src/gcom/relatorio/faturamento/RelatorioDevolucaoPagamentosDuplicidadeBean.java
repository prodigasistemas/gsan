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
package gcom.relatorio.faturamento;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * classe responsável por criar o Relatório Devolução dos Pagamentos em Duplicidade.
 * 
 * [UC1129] Gerar Relatório Devolução dos Pagamentos em Duplicidade
 * 
 * @author Hugo Leonardo
 *
 * @date 10/03/2011
 */
public class RelatorioDevolucaoPagamentosDuplicidadeBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String localidade;
	private String nomeLocalidade;
	private String numeroRA;
	private String matricula;
	private String mesAnoPagamentoDuplicidade;
	private BigDecimal valorPagamentoDuplicidade;
	private String mesAnoConta;
	private BigDecimal valorConta;
	private BigDecimal creditoRealizado;
	private BigDecimal creditoARealizar;
	private String dataAtualizacao;
	private String gerencia;
	private String nomeGerencia;
	private String unidade;
	private String nomeUnidade;

	public RelatorioDevolucaoPagamentosDuplicidadeBean(){
		
	}
	
	public RelatorioDevolucaoPagamentosDuplicidadeBean(String gerencia, String nomeGerencia, 
			String unidade, String nomeUnidade, 
			String localidade, String nomeLocalidade, 
			String numeroRA, String matricula, String mesAnoPagamentoDuplicidade, 
			BigDecimal valorPagamentoDuplicidade, String mesAnoConta, BigDecimal valorConta,
			BigDecimal creditoRealizado, BigDecimal creditoARealizar, String dataAtualizacao) {
		
		this.gerencia = gerencia;
		this.nomeGerencia = nomeGerencia;
		this.unidade = unidade;
		this.nomeUnidade = nomeUnidade;
		this.localidade = localidade;
		this.nomeLocalidade = nomeLocalidade;
		this.numeroRA = numeroRA;
		this.matricula = matricula;
		this.mesAnoPagamentoDuplicidade = mesAnoPagamentoDuplicidade;
		this.valorPagamentoDuplicidade = valorPagamentoDuplicidade;
		this.mesAnoConta = mesAnoConta;
		this.valorConta = valorConta;
		this.creditoRealizado = creditoRealizado;
		this.creditoARealizar = creditoARealizar;
		this.dataAtualizacao = dataAtualizacao;
	}

	public BigDecimal getCreditoARealizar() {
		return creditoARealizar;
	}

	public void setCreditoARealizar(BigDecimal creditoARealizar) {
		this.creditoARealizar = creditoARealizar;
	}

	public BigDecimal getCreditoRealizado() {
		return creditoRealizado;
	}

	public void setCreditoRealizado(BigDecimal creditoRealizado) {
		this.creditoRealizado = creditoRealizado;
	}

	public String getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(String dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMesAnoConta() {
		return mesAnoConta;
	}

	public void setMesAnoConta(String mesAnoConta) {
		this.mesAnoConta = mesAnoConta;
	}

	public String getMesAnoPagamentoDuplicidade() {
		return mesAnoPagamentoDuplicidade;
	}

	public void setMesAnoPagamentoDuplicidade(String mesAnoPagamentoDuplicidade) {
		this.mesAnoPagamentoDuplicidade = mesAnoPagamentoDuplicidade;
	}

	public String getNumeroRA() {
		return numeroRA;
	}

	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}

	public BigDecimal getValorConta() {
		return valorConta;
	}

	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}

	public BigDecimal getValorPagamentoDuplicidade() {
		return valorPagamentoDuplicidade;
	}

	public void setValorPagamentoDuplicidade(BigDecimal valorPagamentoDuplicidade) {
		this.valorPagamentoDuplicidade = valorPagamentoDuplicidade;
	}

	public String getGerencia() {
		return gerencia;
	}

	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}

	public String getNomeGerencia() {
		return nomeGerencia;
	}

	public void setNomeGerencia(String nomeGerencia) {
		this.nomeGerencia = nomeGerencia;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	
}
