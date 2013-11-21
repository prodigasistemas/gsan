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
package gcom.gui.micromedicao;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLigacaoTipo;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Atualizar Tipo de Rateio
 * 
 * @author Rafael Santos, Pedro Alexandre
 * @since 11/01/2006, 23/01/2008
 */
public class ExibirConsultarHistoricoMedicaoIndividualizadaAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirConsultarHistoricoMedicaoIndividualizada");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarHistoricoMedicaoIndividualizadaActionForm consultarHistoricoMedicaoIndividualizadaActionForm = (ConsultarHistoricoMedicaoIndividualizadaActionForm) actionForm;

		String codigoImovel = consultarHistoricoMedicaoIndividualizadaActionForm.getCodigoImovel();

		// limpa para pesquisar outro imovel
		if (sessao.getAttribute("colecaoConsultarHistoricoMedicaoIndividualizada") != null) {
			sessao.removeAttribute("colecaoConsultarHistoricoMedicaoIndividualizada");
		}

		// limpa para pesquisar outro imovel
		if (sessao.getAttribute("dadosImovel") != null) {
			sessao.removeAttribute("dadosImovel");
		}

		httpServletRequest.getParameter("limparColecoes");
		httpServletRequest.getAttribute("limparColecoes");

		if (httpServletRequest.getParameter("limparColecoes") != null) {
			sessao.removeAttribute("colecaoConsultarHistoricoMedicaoIndividualizada");
			sessao.removeAttribute("dadosImovel");

		} else if (codigoImovel != null	&& !codigoImovel.trim().equalsIgnoreCase("")) {

			if (codigoImovel != null && !codigoImovel.trim().equals("")) {
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, codigoImovel));
				filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO,Imovel.IMOVEL_EXCLUIDO));
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua.hidrometroInstalacaoHistorico.rateioTipo");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota.faturamentoGrupo");

				Collection<Imovel> imovelPesquisado = fachada.pesquisar(filtroImovel, Imovel.class.getName());

				// [FS0002 - Verificar existência da matrícula do imóvel
				if (imovelPesquisado != null && imovelPesquisado.isEmpty()) {
					consultarHistoricoMedicaoIndividualizadaActionForm.setCodigoImovel("");
					consultarHistoricoMedicaoIndividualizadaActionForm.setDescricaoImovel("MATRÍCULA INEXISTENTE");
					httpServletRequest.setAttribute("matriculaInexistente",true);
				} else {
					Imovel imovelCondominio = imovelPesquisado.iterator().next();
					consultarHistoricoMedicaoIndividualizadaActionForm.setDescricaoImovel(imovelCondominio.getInscricaoFormatada());
					
					// [FS0001] - Verificar se o imóvel é um condomínio
					if (imovelCondominio.getIndicadorImovelCondominio() != null	&& (imovelCondominio.getIndicadorImovelCondominio().shortValue() == Imovel.IMOVEL_NAO_CONDOMINIO.shortValue())) {
						throw new ActionServletException("atencao.imovel.nao_condominio");
					}
					
					// [FS0001] - Verificar se o imóvel é um condomínio
					if (imovelCondominio.getIndicadorImovelCondominio() == null) {
						throw new ActionServletException("atencao.imovel.nao_condominio");
					}

					if (httpServletRequest.getParameter("pesquisaImovel") == null || httpServletRequest.getParameter("pesquisaImovel").equals("")) {

						String idTipoLigacao = consultarHistoricoMedicaoIndividualizadaActionForm.getIdTipoLigacao();
						LigacaoTipo tipoLigacao = new LigacaoTipo();
						tipoLigacao.setId(new Integer(idTipoLigacao));
						
						String anoMes = consultarHistoricoMedicaoIndividualizadaActionForm.getMesAno();

						
						boolean valida = Util.validarAnoMes(anoMes);
						if (valida) {
							throw new ActionServletException("atencao.invalid",	null, "Mês/Ano do Faturamento");
						}

						// [FS0007] Compara Ano Mês Referência com Ano Mes Atual
						if (anoMes != null && !anoMes.equalsIgnoreCase("")) {
							String mesReferencia = anoMes.substring(0, 2);
							String anoReferencia = anoMes.substring(3, 7);

							Calendar calendarAtual = new GregorianCalendar();

							String mesAtual = (calendarAtual.get(Calendar.MONTH) + 1) + "";
							String anoAtual = calendarAtual.get(Calendar.YEAR) + "";
							if (mesAtual.length() == 1) {
								mesAtual = "0" + mesAtual;
							}

							boolean maior = Util.compararAnoMesReferencia(new Integer(anoReferencia + "" + mesReferencia), new Integer(anoAtual + "" + mesAtual), ">");
							if (maior) {
								throw new ActionServletException("atencao.ano_mes.maior.ano_mes_atual");
							}
						}

						// [FS0003] Validar mês e ano referência
						if (anoMes != null && !anoMes.equalsIgnoreCase("")) {
							String mes = anoMes.substring(0, 2);
							if (new Integer(mes).intValue() > 12) {
								throw new ActionServletException("atencao.ano_mes.invalido", null,codigoImovel);
							}
						}
						
						String anoMesFaturamentoInformado = null;
						// [FS0004] - Verifica ano e mês do faturamento
						if (anoMes != null && !anoMes.equalsIgnoreCase("")) {
							String mes = anoMes.substring(0, 2);
							String ano = anoMes.substring(3, 7);

							anoMesFaturamentoInformado = ano + mes;

							Calendar anoMesInformado = Calendar.getInstance();
							anoMesInformado.set(Calendar.YEAR, new Integer(ano));
							anoMesInformado.set(Calendar.MONTH,new Integer(mes) - 1);
							anoMesInformado.set(Calendar.DATE, 1);

							anoMes = imovelCondominio.getQuadra().getRota().getFaturamentoGrupo().getAnoMesReferencia()	+ "";

							Calendar anoMesReferencia = Calendar.getInstance();
							anoMesReferencia.set(Calendar.YEAR, new Integer(anoMes.substring(0, 4)).intValue());
							anoMesReferencia.set(Calendar.MONTH, new Integer(anoMes.substring(4, 6)).intValue() - 1);
							anoMesReferencia.set(Calendar.DATE, 1);

							if (anoMesInformado.getTime().compareTo(anoMesReferencia.getTime()) > 0) {
								throw new ActionServletException("atencao.ano_mes.referencia.superior",	null, codigoImovel);
							}
						}

						Collection colecaoConsultarHistoricoMedicaoIndividualizada = fachada.consultarHistoricoMedicaoIndividualizada(imovelCondominio, anoMesFaturamentoInformado, tipoLigacao);

						if (colecaoConsultarHistoricoMedicaoIndividualizada != null	&& !colecaoConsultarHistoricoMedicaoIndividualizada.isEmpty()) {
							int quantidade = (colecaoConsultarHistoricoMedicaoIndividualizada.size() - 1);
							consultarHistoricoMedicaoIndividualizadaActionForm.setQuantidadeImoveisVinculados(quantidade + "");
						} else {
							consultarHistoricoMedicaoIndividualizadaActionForm.setQuantidadeImoveisVinculados("0");
						}

						Collection dadosImovel = new ArrayList();
						dadosImovel.add(imovelCondominio);

						sessao.setAttribute("colecaoConsultarHistoricoMedicaoIndividualizada", colecaoConsultarHistoricoMedicaoIndividualizada);
						sessao.setAttribute("dadosImovel", dadosImovel);
					}
				}
			}
		} else {
			
			FiltroLigacaoTipo filtroLigacaoTipo = new FiltroLigacaoTipo();
			Collection colecaoLigacaoTipo = fachada.pesquisar(filtroLigacaoTipo,LigacaoTipo.class.getName());
			sessao.setAttribute("colecaoLigacaoTipo",colecaoLigacaoTipo);
			
			consultarHistoricoMedicaoIndividualizadaActionForm.reset(actionMapping, httpServletRequest);
			consultarHistoricoMedicaoIndividualizadaActionForm.setIdTipoLigacao(ConstantesSistema.NUMERO_NAO_INFORMADO + "");

			if (sessao.getAttribute("colecaoConsultarHistoricoMedicaoIndividualizada") != null) {
				sessao.removeAttribute("colecaoConsultarHistoricoMedicaoIndividualizada");
			}

			if (sessao.getAttribute("dadosImovel") != null) {
				sessao.removeAttribute("dadosImovel");
			}
		}
		
		return retorno;
	}
}
