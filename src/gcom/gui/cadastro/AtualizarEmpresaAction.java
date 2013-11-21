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
package gcom.gui.cadastro;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.EmpresaContratoCobranca;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarEmpresaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		AtualizarEmpresaActionForm atualizarEmpresaActionForm = (AtualizarEmpresaActionForm) actionForm;

		Empresa empresa = (Empresa) sessao.getAttribute("atualizarEmpresa");

		empresa.setId(new Integer(atualizarEmpresaActionForm.getId()));
		empresa.setDescricao(atualizarEmpresaActionForm.getDescricao());
		empresa.setDescricaoAbreviada(atualizarEmpresaActionForm
				.getDescricaoAbreviada());
		empresa.setEmail(atualizarEmpresaActionForm.getEmail());
		empresa.setIndicadorEmpresaPrincipal(new Short(
				atualizarEmpresaActionForm.getIndicadorEmpresaPrincipal()));
		empresa.setIndicadorUso(new Short(atualizarEmpresaActionForm
				.getIndicadorUso()));

		String idEmpresa = atualizarEmpresaActionForm.getId();
		String descricaoEmpresa = atualizarEmpresaActionForm.getDescricao();
		String descricaoAbreviadaEmpresa = atualizarEmpresaActionForm
				.getDescricaoAbreviada();
		String emailEmpresa = atualizarEmpresaActionForm.getEmail();
		Short indicadorEmpresaPrincipal = atualizarEmpresaActionForm
				.getIndicadorEmpresaPrincipal();
		Short indicadorUsoEmpresa = atualizarEmpresaActionForm
				.getIndicadorUso();

		empresa.setDescricao(descricaoEmpresa);

		empresa.setId(new Integer(idEmpresa));

		if (!atualizarEmpresaActionForm.getEmail().trim().equals("")
				&& atualizarEmpresaActionForm.getEmail() != null) {
			empresa.setEmail(emailEmpresa);

		} else {
			emailEmpresa = null;
			empresa.setEmail(emailEmpresa);
		}

		if (!atualizarEmpresaActionForm.getDescricaoAbreviada().trim().equals(
				"")
				&& atualizarEmpresaActionForm.getDescricaoAbreviada() != null) {
			empresa.setDescricaoAbreviada(descricaoAbreviadaEmpresa);
		} else {
			descricaoAbreviadaEmpresa = null;
			empresa.setDescricaoAbreviada(descricaoAbreviadaEmpresa);
		}

		EmpresaContratoCobranca empresaCobranca = null;

		empresa.setIndicadorEmpresaContratadaCobranca(new Integer(
				atualizarEmpresaActionForm.getIndicadorEmpresaCobranca())
				.shortValue());
		
		empresa.setIndicadorLeitura( atualizarEmpresaActionForm.getIndicadorLeitura());

		// Verifica se a empresa de cobranca
		if (atualizarEmpresaActionForm.getIndicadorEmpresaCobranca() != null
				&& atualizarEmpresaActionForm.getIndicadorEmpresaCobranca()
						.equals("" + ConstantesSistema.INDICADOR_USO_ATIVO)) {

			empresaCobranca = new EmpresaContratoCobranca();

			empresaCobranca.setEmpresa(empresa);

			// validar Data Inicio Contrato de Cobrança
			if (atualizarEmpresaActionForm.getDataInicioContratoCobranca() != null
					&& !atualizarEmpresaActionForm
							.getDataInicioContratoCobranca().equals("")) {

				Date data = Util
						.converteStringParaDate(atualizarEmpresaActionForm
								.getDataInicioContratoCobranca());
				empresaCobranca.setDataInicioContrato(data);
			} else {
				throw new ActionServletException("atencao.informe_campo", null,
						" Data do Início do Contrato");
			}

			// validar Data Final Contrato de Cobrança
			if (atualizarEmpresaActionForm.getDataFimContratoCobranca() != null
					&& !atualizarEmpresaActionForm.getDataFimContratoCobranca()
							.equals("")) {

				Date data = Util
						.converteStringParaDate(atualizarEmpresaActionForm
								.getDataFimContratoCobranca());
				empresaCobranca.setDataFinalContrato(data);
			} 

			// Percentual de Cobranca
			if (atualizarEmpresaActionForm.getPercentualPagamento() != null
					&& !atualizarEmpresaActionForm.getPercentualPagamento()
							.equals("")
					&& (atualizarEmpresaActionForm.getPercentualDaFaixaInformado() == null
							|| !atualizarEmpresaActionForm.getPercentualDaFaixaInformado().equalsIgnoreCase("sim"))) {

				BigDecimal percentualPagamentoAtual = null;

				String percentualPagamento = atualizarEmpresaActionForm
						.getPercentualPagamento().toString().replace(".", "");

				percentualPagamento = percentualPagamento.replace(",", ".");
				percentualPagamentoAtual = new BigDecimal(percentualPagamento);

				empresaCobranca
						.setPercentualContratoCobranca(percentualPagamentoAtual);
				empresaCobranca
						.setCodigoLayoutTxt(ConstantesSistema.SIM);

			}

		}

		empresa.setUltimaAlteracao(new Date());
		empresa.setIndicadorUso(new Short(indicadorUsoEmpresa));
		empresa.setIndicadorEmpresaPrincipal(new Short(
				indicadorEmpresaPrincipal));
		
		List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa = null;
		
		if (sessao.getAttribute("colecaoEmpresaCobrancaFaixa") != null
				&& !sessao.getAttribute("colecaoEmpresaCobrancaFaixa").equals("")){

			colecaoEmpresaCobrancaFaixa = (List<EmpresaCobrancaFaixa>) sessao.getAttribute("colecaoEmpresaCobrancaFaixa");

			empresaCobranca.setCodigoLayoutTxt(ConstantesSistema.NAO);
		}
		
		List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixaRemover = null;
		
		if (sessao.getAttribute("colecaoEmpresaCobrancaFaixaRemover") != null
				&& !sessao.getAttribute("colecaoEmpresaCobrancaFaixaRemover").equals("")){

			colecaoEmpresaCobrancaFaixaRemover = (List<EmpresaCobrancaFaixa>) sessao.getAttribute("colecaoEmpresaCobrancaFaixaRemover");
			
		}
		
		fachada.atualizarEmpresa(empresa, empresaCobranca, usuarioLogado,
				colecaoEmpresaCobrancaFaixa, colecaoEmpresaCobrancaFaixaRemover);

		sessao.removeAttribute("colecaoEmpresaCobrancaFaixaRemover");
		sessao.removeAttribute("colecaoEmpresaCobrancaFaixa");
		
		montarPaginaSucesso(httpServletRequest, "Empresa "
				+ atualizarEmpresaActionForm.getId().toString()
				+ " atualizado com sucesso.",
				"Realizar outra Manutenção de Empresa ",
				"exibirFiltrarEmpresaAction.do?menu=sim");

		return retorno;
	}
}
