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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.EmpresaContratoCobranca;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Arthur Carvalho
 * @date 17/04/2008
 */
public class InserirEmpresaAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma Empresa
	 * 
	 * [UC0782] Inserir Empresa
	 * 
	 * 
	 * @author Arthur Carvalho
	 * @date 14/05/2008
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirEmpresaActionForm inserirEmpresaActionForm = (InserirEmpresaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		Fachada fachada = Fachada.getInstancia();

		String descricao = inserirEmpresaActionForm.getDescricao();

		Empresa empresa = new Empresa();

		// Nome
		if (!"".equals(inserirEmpresaActionForm.getDescricao())) {
			empresa.setDescricao(inserirEmpresaActionForm.getDescricao());
		}
		// Nome Abreviado
		if (!"".equals(inserirEmpresaActionForm.getDescricaoAbreviada())) {
			empresa.setDescricaoAbreviada(inserirEmpresaActionForm
					.getDescricaoAbreviada());
		}
		// E-mail
		if (!"".equals(inserirEmpresaActionForm.getEmail())) {
			empresa.setEmail(inserirEmpresaActionForm.getEmail());
		}
		// Empresa Principal
		if (inserirEmpresaActionForm.getIndicadorEmpresaPrincipal() != null
				&& !"".equals(inserirEmpresaActionForm
						.getIndicadorEmpresaPrincipal())) {
			empresa.setIndicadorEmpresaPrincipal(inserirEmpresaActionForm
					.getIndicadorEmpresaPrincipal());
		}

		// Indicador Empresa Contratada Cobranca
		if (inserirEmpresaActionForm.getIndicadorEmpresaCobranca() != null
				&& !"".equals(inserirEmpresaActionForm
						.getIndicadorEmpresaCobranca())) {
			empresa.setIndicadorEmpresaContratadaCobranca(new Integer(
					inserirEmpresaActionForm.getIndicadorEmpresaCobranca())
					.shortValue());
		}
		
		if ( inserirEmpresaActionForm.getIndicadorLeitura() != null
				&& ! "".equals(inserirEmpresaActionForm.getIndicadorLeitura())){
			
			empresa.setIndicadorLeitura( inserirEmpresaActionForm.getIndicadorLeitura() );
			
		}
		
		// Indicador de Uso
		Short iu = ConstantesSistema.INDICADOR_USO_ATIVO;

		empresa.setIndicadorUso(iu);

		EmpresaContratoCobranca empresaCobranca = null;
		
		// Verifica se a empresa de cobranca
		if (inserirEmpresaActionForm.getIndicadorEmpresaCobranca() != null
				&& inserirEmpresaActionForm.getIndicadorEmpresaCobranca()
						.equals("" + ConstantesSistema.INDICADOR_USO_ATIVO)) {

			empresaCobranca = new EmpresaContratoCobranca();

			// validar Data Inicio Contrato de Cobrança
			if (inserirEmpresaActionForm.getDataInicioContratoCobranca() != null
					&& !inserirEmpresaActionForm
							.getDataInicioContratoCobranca().equals("")) {

				Date data = Util
						.converteStringParaDate(inserirEmpresaActionForm
								.getDataInicioContratoCobranca());
				empresaCobranca.setDataInicioContrato(data);
			} else {
				throw new ActionServletException("atencao.informe_campo", null,
						" Data do Início do Contrato");
			}

			// Percentual de Cobranca
			if (inserirEmpresaActionForm.getPercentualPagamento() != null
					&& !inserirEmpresaActionForm.getPercentualPagamento()
							.equals("")
					&& (inserirEmpresaActionForm.getPercentualDaFaixaInformado() == null
							|| !inserirEmpresaActionForm.getPercentualDaFaixaInformado().equalsIgnoreCase("sim"))) {
				
				BigDecimal percentualPagamentoAtual = null;

				String percentualPagamento = inserirEmpresaActionForm
						.getPercentualPagamento().toString().replace(".", "");

				percentualPagamento = percentualPagamento.replace(",", ".");
				percentualPagamentoAtual = new BigDecimal(percentualPagamento);

				empresaCobranca
						.setPercentualContratoCobranca(percentualPagamentoAtual);
				empresaCobranca
						.setCodigoLayoutTxt(ConstantesSistema.SIM);

			}

		}
		
		List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa = null;
		
		if (sessao.getAttribute("colecaoEmpresaCobrancaFaixa") != null
				&& !sessao.getAttribute("colecaoEmpresaCobrancaFaixa").equals("")){

			colecaoEmpresaCobrancaFaixa = (List<EmpresaCobrancaFaixa>) sessao.getAttribute("colecaoEmpresaCobrancaFaixa");

			empresaCobranca.setCodigoLayoutTxt(ConstantesSistema.NAO);
			
		}

		Integer idEmpresa = (Integer) fachada.inserirEmpresa(empresa,
				empresaCobranca, usuarioLogado, colecaoEmpresaCobrancaFaixa);

		montarPaginaSucesso(httpServletRequest, "Empresa " + descricao
				+ " inserido com sucesso.", "Inserir outra Empresa",
				"exibirInserirEmpresaAction.do?menu=sim",
				"exibirAtualizarEmpresaAction.do?idRegistroAtualizacao="
						+ idEmpresa, "Atualizar Empresa Inserida");

		sessao.removeAttribute("InserirEmpresaActionForm");

		return retorno;

	}
}
