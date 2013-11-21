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
package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadadorContrato;
import gcom.arrecadacao.FiltroAvisoDeducoes;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.aviso.AvisoDeducoes;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirProcessoTresInserirAvisoBancarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirInserirAvisoBancarioProcessoTres");

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		InserirAvisoBancarioActionForm form = (InserirAvisoBancarioActionForm) actionForm;
		Arrecadador arrecadador = (Arrecadador) sessao
				.getAttribute("arrecadador");
		form.setNomeArrecadador(arrecadador.getCliente().getNome());
		Short numeroSequencial = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataLancamento = null;
		Collection colecaoAvisoDeducao = null;
		try {
			dataLancamento = formato.parse(form.getDataLancamento());
		} catch (ParseException e) {
		}

		if (form.getAvisoBancario() == null) {
			numeroSequencial = fachada.pesquisarValorMaximoNumeroSequencial(
					dataLancamento, arrecadador.getId().toString());
			if (numeroSequencial != null) {
				numeroSequencial = new Short(""
						+ (numeroSequencial.intValue() + 1));
			} else {
				numeroSequencial = 1;
			}
			form.setNumeroSequencial("" + numeroSequencial);
		} else {
			if (sessao.getAttribute("avisoBancario") != null) {
				AvisoBancario avisoBancarioTemp = (AvisoBancario) sessao
						.getAttribute("avisoBancario");
				form.setNumeroSequencial(""
						+ avisoBancarioTemp.getNumeroSequencial());
				form.setTipoAviso(""
						+ avisoBancarioTemp.getIndicadorCreditoDebito());
				httpServletRequest.setAttribute("avisoSelecionado", true);

				if (sessao.getAttribute("colecaoAvisoDeducao") == null) {

					FiltroAvisoDeducoes filtroAvisoDeducoes = new FiltroAvisoDeducoes();

					filtroAvisoDeducoes
							.adicionarCaminhoParaCarregamentoEntidade("deducaoTipo");

					filtroAvisoDeducoes
							.adicionarParametro(new ParametroSimples(
									FiltroAvisoDeducoes.AVISO_BANCARIO_ID,
									avisoBancarioTemp.getId()));

					colecaoAvisoDeducao = fachada.pesquisar(
							filtroAvisoDeducoes, AvisoDeducoes.class.getName());

					sessao.setAttribute("colecaoAvisoDeducao",
							colecaoAvisoDeducao);

				}

			} else {
				numeroSequencial = fachada
						.pesquisarValorMaximoNumeroSequencial(dataLancamento,
								arrecadador.getId().toString());
				if (numeroSequencial != null) {
					numeroSequencial = new Short(""
							+ (numeroSequencial.intValue() + 1));
				} else {
					numeroSequencial = 1;
				}
				form.setNumeroSequencial("" + numeroSequencial);
			}
		}

		sessao.setAttribute("idArrecadador", form.getCodigoArrecadador());

		if (form.getIdContaBancaria() == null || form.getIdContaBancaria().equals("")){
			FiltroArrecadadorContrato filtroArrecadadorContrato = new FiltroArrecadadorContrato();
			filtroArrecadadorContrato.adicionarParametro(new ParametroSimples(
					FiltroArrecadadorContrato.ARRECADADOR_CODIGO_AGENTE, form
							.getCodigoArrecadador()));
			filtroArrecadadorContrato.adicionarParametro(new ParametroNulo(
					FiltroArrecadadorContrato.DATA_CONTRATO_ENCERRAMENTO));
			filtroArrecadadorContrato.adicionarParametro(new ParametroSimples(
					FiltroArrecadadorContrato.CODIGO_CONVENIO, form.getCodigoConvenio()));
			filtroArrecadadorContrato
					.adicionarCaminhoParaCarregamentoEntidade("contaBancariaDepositoArrecadacao.agencia.banco");
	
			Collection colecaoArrecadadorContrato = fachada.pesquisar(
					filtroArrecadadorContrato, ArrecadadorContrato.class
							.getName());
	
			if (colecaoArrecadadorContrato != null
					&& !colecaoArrecadadorContrato.isEmpty()) {
				ArrecadadorContrato arrecadadorContrato = (ArrecadadorContrato) colecaoArrecadadorContrato
						.iterator().next();
				form.setIdContaBancaria(arrecadadorContrato
								.getContaBancariaDepositoArrecadacao().getId().toString());
	
				form
						.setNumeroConta(arrecadadorContrato
								.getContaBancariaDepositoArrecadacao()
								.getNumeroConta());
				form.setNumeroBanco(""
						+ arrecadadorContrato
								.getContaBancariaDepositoArrecadacao()
								.getAgencia().getBanco().getId());
				form.setNumeroAgencia(""
						+ arrecadadorContrato
								.getContaBancariaDepositoArrecadacao()
								.getAgencia().getCodigoAgencia());
			}
			else{
				throw new ActionServletException("atencao.arrecadador.sem.contrato");
			}
		}
		
		// Pesquisar ArrecadacaoForma
        FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
        filtroArrecadacaoForma.setConsultaSemLimites(true);
        filtroArrecadacaoForma.setCampoOrderBy(FiltroArrecadacaoForma.DESCRICAO);
        Collection collectionArrecadacaoForma = fachada.pesquisar(filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
        httpServletRequest.setAttribute("collectionArrecadacaoForma", collectionArrecadacaoForma);

		// Recupera da sessao os aviso deducao que vem do popup
		if (sessao.getAttribute("collectionAvisoDeducao") == null
				|| ((Collection) sessao.getAttribute("collectionAvisoDeducao"))
						.isEmpty()) {

			if (sessao.getAttribute("collectionAvisoDeducao") == null) {
				Collection<AvisoDeducoes> collectionAvisoDeducaoPopUp = new ArrayList();
				sessao.setAttribute("collectionAvisoDeducao",
						collectionAvisoDeducaoPopUp);
			}

		} else {
			Collection<AvisoDeducoes> collectionAvisoDeducaoPopUp = new ArrayList();
			collectionAvisoDeducaoPopUp = (ArrayList) sessao
					.getAttribute("collectionAvisoDeducao");
			AvisoDeducoes avisoDeducoesPopUp = ((AvisoDeducoes) ((List) collectionAvisoDeducaoPopUp)
					.get(0));
			colecaoAvisoDeducao = (Collection) sessao
					.getAttribute("colecaoAvisoDeducao");
			if (colecaoAvisoDeducao == null || colecaoAvisoDeducao.isEmpty()) {
				colecaoAvisoDeducao = new ArrayList();
				colecaoAvisoDeducao.add(((List) collectionAvisoDeducaoPopUp)
						.get(0));
				collectionAvisoDeducaoPopUp
						.remove(((List) collectionAvisoDeducaoPopUp).get(0));
				sessao.setAttribute("colecaoAvisoDeducao", colecaoAvisoDeducao);
			} else {
				Iterator colecaoAvisoDeducaoIterator = colecaoAvisoDeducao
						.iterator();
				while (colecaoAvisoDeducaoIterator.hasNext()) {
					AvisoDeducoes avisoDeducoes = (AvisoDeducoes) colecaoAvisoDeducaoIterator
							.next();
					if (avisoDeducoes.getDeducaoTipo().getDescricaoAbreviado()
							.equalsIgnoreCase(
									avisoDeducoesPopUp.getDeducaoTipo()
											.getDescricaoAbreviado())) {
						collectionAvisoDeducaoPopUp
								.remove(((List) collectionAvisoDeducaoPopUp)
										.get(0));
						throw new ActionServletException(
								"atencao.deducao_tipo.existente");
					}

				}

				/*
				 * FiltroAvisoDeducoes filtroAvisoDeducoes = new
				 * FiltroAvisoDeducoes();
				 * filtroAvisoDeducoes.adicionarParametro(new ParametroSimples(
				 * FiltroAvisoDeducoes.DEDUCAO_TIPO_ID, avisoDeducoesPopUp
				 * .getDeducaoTipo().getId()));
				 * filtroAvisoDeducoes.adicionarParametro(new ParametroSimples(
				 * FiltroAvisoDeducoes.AVISO_BANCARIO_ARRECADADOR_CODIGO,
				 * form.getCodigoArrecadador()));
				 * filtroAvisoDeducoes.adicionarParametro(new ParametroSimples(
				 * FiltroAvisoDeducoes.AVISO_BANCARIO_DATA_LANCAMENTO,
				 * Util.converteStringParaDate(form.getDataLancamento())));
				 * filtroAvisoDeducoes.adicionarParametro(new ParametroSimples(
				 * FiltroAvisoDeducoes.AVISO_BANCARIO_NUMERO_SEQUENCIAL,
				 * form.getNumeroSequencial()));
				 * 
				 * Collection colecaoAvisoDeducoesPesquisada =
				 * fachada.pesquisar( filtroAvisoDeducoes,
				 * AvisoDeducoes.class.getName());
				 * 
				 * if (colecaoAvisoDeducoesPesquisada != null &&
				 * !colecaoAvisoDeducoesPesquisada.isEmpty()) { throw new
				 * ActionServletException(
				 * "atencao.deducao_tipo.aviso_bancario.existente"); } else {
				 */
				colecaoAvisoDeducao.add(((List) collectionAvisoDeducaoPopUp)
						.get(0));
				collectionAvisoDeducaoPopUp
						.remove(((List) collectionAvisoDeducaoPopUp).get(0));
				sessao.setAttribute("colecaoAvisoDeducao", colecaoAvisoDeducao);

				// }
			}
		}
		// Recupera da sessao os aviso deducao que vem do popup

		// Remocao de Objeto da collection
		if (httpServletRequest.getParameter("timestamp") != null
				&& !httpServletRequest.getParameter("timestamp").equals("")) {

			colecaoAvisoDeducao = (Collection) sessao
					.getAttribute("colecaoAvisoDeducao");
			Iterator iterator = colecaoAvisoDeducao.iterator();
			long timestamp = new Long(httpServletRequest
					.getParameter("timestamp")).longValue();

			while (iterator.hasNext()) {
				AvisoDeducoes avisoDeducoes = (AvisoDeducoes) iterator.next();
				if (GcomAction.obterTimestampIdObjeto(avisoDeducoes) == timestamp) {
					iterator.remove();
				}
			}
		}

		BigDecimal valorDeducao = null;

		if (sessao.getAttribute("colecaoAvisoDeducao") != null) {

			colecaoAvisoDeducao = (Collection) sessao
					.getAttribute("colecaoAvisoDeducao");

			Iterator colecaoAvisoDeducaoIterator = colecaoAvisoDeducao
					.iterator();

			while (colecaoAvisoDeducaoIterator.hasNext()) {
				AvisoDeducoes avisoDeducoesCalculo = (AvisoDeducoes) colecaoAvisoDeducaoIterator
						.next();

				if (valorDeducao == null) {
					valorDeducao = new BigDecimal("0.00");
				}
				valorDeducao = valorDeducao.add(avisoDeducoesCalculo
						.getValorDeducao());
			}

			if (valorDeducao != null) {
				form.setValorDeducoes(Util.formatarMoedaReal(valorDeducao));
			} else {
				form.setValorDeducoes("");
			}

		}

		BigDecimal valorAviso = null;

		if (form.getValorArrecadacao() != null
				&& !form.getValorArrecadacao().equals("")) {
			valorAviso = new BigDecimal(form.getValorArrecadacao().replace(".",
					"").replace(",", "."));
		}

		if (form.getValorDevolucao() != null
				&& !form.getValorDevolucao().equals("")) {
			if (valorAviso == null) {
				valorAviso = new BigDecimal("0.00");
			}
			valorAviso = valorAviso.subtract(new BigDecimal(form
					.getValorDevolucao().replace(".", "").replace(",", ".")));
		}

		if (valorDeducao != null) {
			if (valorAviso == null) {
				valorAviso = new BigDecimal("0.00");
			}
			valorAviso = valorAviso.subtract(valorDeducao);
		}

		if (valorAviso != null) {
			form.setValorAviso(Util.formatarMoedaReal(valorAviso));
		} else {
			form.setValorAviso("");
		}

		// if (sessao.getAttribute())

		// Remocao de Objeto da collection
		httpServletRequest.setAttribute("nomeCampo","numeroDocumento");

		return retorno;
	}
}
