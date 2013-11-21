/**
 * 
 */
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
 * Rômulo Aurélio de Melo Souza Filho
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
package gcom.gui.relatorio.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * [UC0868]-Gerar Relatório de Pagamentos das Contas em Cobranca por EmpresaS
 * 
 * @author Rômulo Aurélio
 * @date 08/01/2009
 */
public class GerarRelatorioPagamentosContasCobrancaEmpresaActionForm extends
		ActionForm {

	private static final long serialVersionUID = 1L;

	private String idEmpresa;

	private String nomeEmpresa;

	private String periodoComandoInicial;

	private String periodoComandoFinal;

	private String opcaoRelatorio;

	private String gerenciaRegionalId;

	private String gerenciaRegionalporLocalidadeId;

	private String codigoLocalidade;

	private String unidadeNegocioId;

	private String unidadeNegocioGerenciaRegionalId;
	
	private String opcaoTotalizacao;

	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public String getUnidadeNegocioGerenciaRegionalId() {
		return unidadeNegocioGerenciaRegionalId;
	}

	public void setUnidadeNegocioGerenciaRegionalId(
			String unidadeNegocioGerenciaRegionalId) {
		this.unidadeNegocioGerenciaRegionalId = unidadeNegocioGerenciaRegionalId;
	}

	public String getUnidadeNegocioId() {
		return unidadeNegocioId;
	}

	public void setUnidadeNegocioId(String unidadeNegocioId) {
		this.unidadeNegocioId = unidadeNegocioId;
	}

	public String getCodigoLocalidade() {
		return codigoLocalidade;
	}

	public void setCodigoLocalidade(String codigoLocalidade) {
		this.codigoLocalidade = codigoLocalidade;
	}

	public String getGerenciaRegionalId() {
		return gerenciaRegionalId;
	}

	public void setGerenciaRegionalId(String gerenciaRegionalId) {
		this.gerenciaRegionalId = gerenciaRegionalId;
	}

	public String getGerenciaRegionalporLocalidadeId() {
		return gerenciaRegionalporLocalidadeId;
	}

	public void setGerenciaRegionalporLocalidadeId(
			String gerenciaRegionalporLocalidadeId) {
		this.gerenciaRegionalporLocalidadeId = gerenciaRegionalporLocalidadeId;
	}

	public String getOpcaoRelatorio() {
		return opcaoRelatorio;
	}

	public void setOpcaoRelatorio(String opcaoRelatorio) {
		this.opcaoRelatorio = opcaoRelatorio;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getPeriodoComandoInicial() {
		return periodoComandoInicial;
	}

	public void setPeriodoComandoInicial(String periodoComandoInicial) {
		this.periodoComandoInicial = periodoComandoInicial;
	}

	public String getPeriodoComandoFinal() {
		return periodoComandoFinal;
	}

	public void setPeriodoComandoFinal(String periodoComandoFinal) {
		this.periodoComandoFinal = periodoComandoFinal;
	}

}
