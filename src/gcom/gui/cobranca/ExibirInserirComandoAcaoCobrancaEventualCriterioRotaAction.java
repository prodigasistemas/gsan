
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
 * Anderson Italo felinto de Lima
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
package gcom.gui.cobranca;

import gcom.atendimentopublico.ordemservico.FiltroFiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeComando;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0243] Inserir Comando de Ação de Conbrança - Tipo de Comando Cronograma
 * 
 * @author Rafael Santos
 * @since 24/01/2006
 */
public class ExibirInserirComandoAcaoCobrancaEventualCriterioRotaAction extends
		GcomAction {

	private Collection colecaoPesquisa = null;

	private String localidadeID = null;

	// private String gerenciaRegionalID = null;

	private String setorComercialCD = null;

	private HttpSession sessao;

	/**
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInserirComandoAcaoCobrancaEventualCriterioRota");

		// Mudar isso quando implementar a parte de segurança
		sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		sessao.removeAttribute("colecaoCobrancaGrupo");
		sessao.removeAttribute("colecaoCobrancaAtividade");
		sessao.removeAttribute("colecaoCobrancaAcao");

		String limparForm = (String) httpServletRequest
				.getParameter("limparForm");

		String validarCriterio = (String) httpServletRequest
				.getParameter("validarCriterio");

		String validar = (String) httpServletRequest.getParameter("validar");
		
		InserirComandoAcaoCobrancaEventualCriterioRotaActionForm inserirComandoAcaoCobrancaEventualCriterioRotaActionForm = (InserirComandoAcaoCobrancaEventualCriterioRotaActionForm) actionForm;
		
		if(sessao.getAttribute("inserirComandoAcaoCobrancaEventualCriterioComandoActionForm") != null){
			InserirComandoAcaoCobrancaEventualCriterioComandoActionForm formSessao = (InserirComandoAcaoCobrancaEventualCriterioComandoActionForm)
				sessao.getAttribute("inserirComandoAcaoCobrancaEventualCriterioComandoActionForm");
			
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setRotaInicial(formSessao.getRotaInicial());
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setRotaFinal(formSessao.getRotaFinal());
			
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setLocalidadeOrigemID(formSessao.getLocalidadeOrigemID());
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setLocalidadeDestinoID(formSessao.getLocalidadeDestinoID());
			
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialDestinoCD(formSessao.getSetorComercialDestinoCD());
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialDestinoID(formSessao.getSetorComercialDestinoID());
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setNomeSetorComercialDestino(formSessao.getNomeSetorComercialDestino());
			
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialOrigemCD(formSessao.getSetorComercialOrigemCD());
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setSetorComercialOrigemID(formSessao.getSetorComercialOrigemID());
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setNomeSetorComercialOrigem(formSessao.getNomeSetorComercialOrigem());
			
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setCodigoClienteSuperior(formSessao.getCodigoClienteSuperior());
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setNomeClienteSuperior(formSessao.getNomeClienteSuperior());
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setNomeCliente(formSessao.getNomeCliente());
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setClienteRelacaoTipo(formSessao.getClienteRelacaoTipo());
			
			
			
			sessao.removeAttribute("inserirComandoAcaoCobrancaEventualCriterioComandoActionForm");
		}

		if (limparForm != null && !limparForm.trim().equalsIgnoreCase("")) {
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.reset(
					actionMapping, httpServletRequest);
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
					.setIndicadorGerarBoletimCadastro("2");
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
					.setIndicadorImoveisDebito("1");
			if (sessao.getAttribute("colecaoRota") != null) {
				sessao.removeAttribute("colecaoRota");
			}

		}

		String rota = (String) httpServletRequest.getParameter("rota");

		// carregar as rotas
		if (rota != null && !rota.trim().equalsIgnoreCase("")) {

			// carregarRota(inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,fachada,
			// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getSetorComercialOrigemCD(),inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getLocalidadeOrigemID());
		}

		String limparRota = (String) httpServletRequest
				.getParameter("limparRota");

		// limpar as rotas
		if (limparRota != null && !limparRota.trim().equalsIgnoreCase("")) {
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
					.setRotaInicial("");
			sessao.setAttribute("colecaoRota", null);
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
					.setRotaInicial(null);
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
					.setRotaFinal("");
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
					.setRotaFinal(null);
		}

		String idCobrancaAcaoAtividadeComando = (String) httpServletRequest
				.getParameter("idCobrancaAcaoAtividadeComando");

		if (idCobrancaAcaoAtividadeComando != null
				&& !idCobrancaAcaoAtividadeComando.equals("")) {

			FiltroCobrancaAcaoAtividadeComando filtroCobrancaAcaoAtividadeComando = new FiltroCobrancaAcaoAtividadeComando();
			filtroCobrancaAcaoAtividadeComando
					.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.COBRANCA_ACAO);
			filtroCobrancaAcaoAtividadeComando
					.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.COBRANCA_GRUPO);
			filtroCobrancaAcaoAtividadeComando
					.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.COBRANCA_ATIVIDADE);
			filtroCobrancaAcaoAtividadeComando
					.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.USUARIO);
			filtroCobrancaAcaoAtividadeComando
					.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.GERENCIAL_REGIONAL);
			filtroCobrancaAcaoAtividadeComando
					.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.LOCALIDADE_INICIAL);
			filtroCobrancaAcaoAtividadeComando
					.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.ROTA_INICIAL);
			filtroCobrancaAcaoAtividadeComando
			.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.ROTA_FINAL);
			filtroCobrancaAcaoAtividadeComando
					.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.CLIENTE);
			filtroCobrancaAcaoAtividadeComando
					.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.CLIENTE_RELACAO_TIPO);
			filtroCobrancaAcaoAtividadeComando
					.adicionarParametro(new ParametroSimples(
							FiltroCobrancaAcaoAtividadeComando.ID,
							idCobrancaAcaoAtividadeComando));

			Collection colecaoCobrancaAcaoAtividadeComando = fachada.pesquisar(
					filtroCobrancaAcaoAtividadeComando,
					CobrancaAcaoAtividadeComando.class.getName());
			if (colecaoCobrancaAcaoAtividadeComando != null
					&& !colecaoCobrancaAcaoAtividadeComando.isEmpty()) {
				CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando = (CobrancaAcaoAtividadeComando) colecaoCobrancaAcaoAtividadeComando
						.iterator().next();

				String[] acaoCobranca = { cobrancaAcaoAtividadeComando
						.getCobrancaAcao().getId().toString() };
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.setCobrancaAcao(acaoCobranca);
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.setCobrancaAtividade(cobrancaAcaoAtividadeComando
								.getCobrancaAtividade().getId().toString());
				/*
				 * if(inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getCobrancaAtividade() !=
				 * null &&
				 * !inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getCobrancaAtividade().equals("")){
				 * FiltroCobrancaAtividade filtroCobrancaAtividade = new
				 * FiltroCobrancaAtividade();
				 * filtroCobrancaAtividade.adicionarParametro(new
				 * ParametroSimples(FiltroCobrancaAtividade.ID,inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getCobrancaAtividade()));
				 * Collection colecaoCobrancaAtividade =
				 * fachada.pesquisar(filtroCobrancaAtividade,CobrancaAtividade.class.getName());
				 * if(colecaoCobrancaAtividade != null &&
				 * !colecaoCobrancaAtividade.isEmpty()){ CobrancaAtividade
				 * cobrancaAtividade = (CobrancaAtividade)
				 * colecaoCobrancaAtividade.iterator().next(); String indicador =
				 * null; if(cobrancaAtividade.getIndicadorExecucao() != null){
				 * indicador =
				 * cobrancaAtividade.getIndicadorExecucao().toString(); }else{
				 * indicador = ""; }
				 * inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setCobrancaAtividadeIndicadorExecucao(indicador);
				 * //httpServletRequest.setAttribute("cobrancaAtividadeIndicadorExecucao",cobrancaAtividade.getIndicadorExecucao().toString());
				 * }else{
				 * inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setCobrancaAtividadeIndicadorExecucao("");
				 * //httpServletRequest.setAttribute("cobrancaAtividadeIndicadorExecucao",""); } }
				 */
				// cobranca grupo
				if (cobrancaAcaoAtividadeComando.getCobrancaGrupo() != null) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setCobrancaGrupo(cobrancaAcaoAtividadeComando
									.getCobrancaGrupo().getId().toString());
				} else {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setCobrancaGrupo("");
				}
				// gerencia regional
				if (cobrancaAcaoAtividadeComando.getGerenciaRegional() != null) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setGerenciaRegional(cobrancaAcaoAtividadeComando
									.getGerenciaRegional().getId().toString());
				} else {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setGerenciaRegional("");
				}
				// unidade negocio
				if (cobrancaAcaoAtividadeComando.getUnidadeNegocio() != null) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setUnidadeNegocio(cobrancaAcaoAtividadeComando
									.getUnidadeNegocio().getId().toString());
				} else {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setUnidadeNegocio("");
				}
				// localidade inicial
				if (cobrancaAcaoAtividadeComando.getLocalidadeInicial() != null) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setLocalidadeOrigemID(cobrancaAcaoAtividadeComando
									.getLocalidadeInicial().getId().toString());
					FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
					filtroLocalidade.adicionarParametro(new ParametroSimples(
							FiltroLocalidade.ID, cobrancaAcaoAtividadeComando
									.getLocalidadeInicial().getId()));
					Collection colecaoLocalidadesIniciais = fachada.pesquisar(
							filtroLocalidade, Localidade.class.getName());

					Localidade localidadeInicial = (Localidade) colecaoLocalidadesIniciais
							.iterator().next();
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeLocalidadeOrigem(localidadeInicial
									.getDescricao());
				} else {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setLocalidadeOrigemID("");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeLocalidadeOrigem("");
				}
				// localidade final
				if (cobrancaAcaoAtividadeComando.getLocalidadeFinal() != null) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setLocalidadeDestinoID(cobrancaAcaoAtividadeComando
									.getLocalidadeFinal().getId().toString());
					FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
					filtroLocalidade.adicionarParametro(new ParametroSimples(
							FiltroLocalidade.ID, cobrancaAcaoAtividadeComando
									.getLocalidadeFinal().getId()));
					Collection colecaoLocalidadesFinais = fachada.pesquisar(
							filtroLocalidade, Localidade.class.getName());

					Localidade localidadeFinal = (Localidade) colecaoLocalidadesFinais
							.iterator().next();
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeLocalidadeDestino(localidadeFinal
									.getDescricao());
				} else {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setLocalidadeDestinoID("");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeLocalidadeDestino("");
				}
				if (cobrancaAcaoAtividadeComando
						.getCodigoSetorComercialInicial() != null) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setSetorComercialOrigemCD(cobrancaAcaoAtividadeComando
									.getCodigoSetorComercialInicial()
									.toString());
					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					filtroSetorComercial
							.adicionarParametro(new ParametroSimples(
									FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
									cobrancaAcaoAtividadeComando
											.getCodigoSetorComercialInicial()
											.toString()));
					Collection colecaoSetorComercialIniciais = fachada
							.pesquisar(filtroSetorComercial,
									SetorComercial.class.getName());

					SetorComercial setorComercialInicial = (SetorComercial) colecaoSetorComercialIniciais
							.iterator().next();
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeSetorComercialOrigem(setorComercialInicial
									.getDescricao());
				} else {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setSetorComercialOrigemCD("");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeSetorComercialOrigem("");
				}
				if (cobrancaAcaoAtividadeComando.getCodigoSetorComercialFinal() != null) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setSetorComercialDestinoCD(cobrancaAcaoAtividadeComando
									.getCodigoSetorComercialFinal().toString());
					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					filtroSetorComercial
							.adicionarParametro(new ParametroSimples(
									FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
									cobrancaAcaoAtividadeComando
											.getCodigoSetorComercialFinal()
											.toString()));
					Collection colecaoSetorComercialFinais = fachada.pesquisar(
							filtroSetorComercial, SetorComercial.class
									.getName());

					SetorComercial setorComercialFinal = (SetorComercial) colecaoSetorComercialFinais
							.iterator().next();
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeSetorComercialOrigem(setorComercialFinal
									.getDescricao());
				} else {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setSetorComercialDestinoCD("");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeSetorComercialOrigem("");
				}
				boolean carregou = false;
				// rota inicial
				if (cobrancaAcaoAtividadeComando.getRotaInicial() != null) {
					// carregarRota(inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,fachada,cobrancaAcaoAtividadeComando.getCodigoSetorComercialInicial().toString(),inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getLocalidadeOrigemID());
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setRotaInicial(cobrancaAcaoAtividadeComando
									.getRotaInicial().getCodigo().toString());
					carregou = true;
				} else {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setRotaInicial("");
					sessao.setAttribute("colecaoRota", null);
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setRotaInicial(null);
				}
				// rota final
				if (cobrancaAcaoAtividadeComando.getRotaFinal() != null) {
					if (!carregou) {
						// carregarRota(inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,fachada,cobrancaAcaoAtividadeComando.getCodigoSetorComercialFinal().toString(),inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getLocalidadeOrigemID());
					}

					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setRotaFinal(cobrancaAcaoAtividadeComando
									.getRotaFinal().getCodigo().toString());
				} else {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setRotaFinal("");
					sessao.setAttribute("colecaoRota", null);
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setRotaFinal(null);
				}

				if (cobrancaAcaoAtividadeComando.getCliente() != null) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setIdCliente(cobrancaAcaoAtividadeComando
									.getCliente().getId().toString());
					FiltroCliente filtroCliente = new FiltroCliente();
					filtroCliente.adicionarParametro(new ParametroSimples(
							FiltroCliente.ID, cobrancaAcaoAtividadeComando
									.getCliente().getId().toString()));
					Collection colecaoCliente = fachada.pesquisar(
							filtroCliente, Cliente.class.getName());
					Cliente cliente = (Cliente) colecaoCliente.iterator()
							.next();
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeCliente(cliente.getNome());

				} else {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeCliente("");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setIdCliente("");
				}

				if (cobrancaAcaoAtividadeComando.getClienteRelacaoTipo() != null) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setClienteRelacaoTipo(cobrancaAcaoAtividadeComando
									.getClienteRelacaoTipo().getId().toString());
				} else {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setClienteRelacaoTipo("");
				}

				if (cobrancaAcaoAtividadeComando
						.getAnoMesReferenciaContaInicial() != null) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setPeriodoInicialConta(Util
									.formatarAnoMesParaMesAno(Util
											.adicionarZerosEsquedaNumero(
													6,
													cobrancaAcaoAtividadeComando
															.getAnoMesReferenciaContaInicial()
															.toString())
											+ ""));
				} else {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setPeriodoInicialConta("");
				}

				if (cobrancaAcaoAtividadeComando
						.getAnoMesReferenciaContaFinal() != null) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setPeriodoFinalConta(Util
									.formatarAnoMesParaMesAno(Util
											.adicionarZerosEsquedaNumero(
													6,
													cobrancaAcaoAtividadeComando
															.getAnoMesReferenciaContaFinal()
															.toString())
											+ ""));
				} else {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setPeriodoFinalConta("");
				}

				if (cobrancaAcaoAtividadeComando
						.getDataVencimentoContaInicial() != null) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setPeriodoVencimentoContaInicial(Util
									.formatarData(cobrancaAcaoAtividadeComando
											.getDataVencimentoContaInicial()));
				} else {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setPeriodoVencimentoContaInicial("");
				}

				if (cobrancaAcaoAtividadeComando.getDataVencimentoContaFinal() != null) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setPeriodoVencimentoContaFinal(Util
									.formatarData(cobrancaAcaoAtividadeComando
											.getDataVencimentoContaFinal()));
				} else {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setPeriodoVencimentoContaFinal("");
				}

				// id cobrança criterio
				if (cobrancaAcaoAtividadeComando != null
						&& cobrancaAcaoAtividadeComando.getCobrancaCriterio() != null) {
					sessao.setAttribute("idCobrancaCriterio",
							cobrancaAcaoAtividadeComando.getCobrancaCriterio()
									.getId().toString());
				}

				// indicador de criterio
				if (cobrancaAcaoAtividadeComando != null
						&& cobrancaAcaoAtividadeComando.getIndicadorCriterio() != null) {
					if (cobrancaAcaoAtividadeComando.getIndicadorCriterio()
							.shortValue() == 1) {
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
								.setCobrancaAtividadeIndicadorExecucao("1");
						// manterComandoAcaoCobrancaDetalhesActionForm.setIndicador("Rota");
					} else if (cobrancaAcaoAtividadeComando
							.getIndicadorCriterio().shortValue() == 2) {
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
								.setCobrancaAtividadeIndicadorExecucao("2");
						// manterComandoAcaoCobrancaDetalhesActionForm.setIndicador("Comando");
					}
				}

				if (cobrancaAcaoAtividadeComando.getDescricaoTitulo() != null) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setTitulo(cobrancaAcaoAtividadeComando
									.getDescricaoTitulo());
				}

				if (cobrancaAcaoAtividadeComando.getDescricaoSolicitacao() != null) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setDescricaoSolicitacao(cobrancaAcaoAtividadeComando
									.getDescricaoSolicitacao());
				}

				if (cobrancaAcaoAtividadeComando.getDataEncerramentoPrevista() != null) {
					if (cobrancaAcaoAtividadeComando.getComando() != null) {
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
								.setPrazoExecucao(""
										+ Util
												.obterQuantidadeDiasEntreDuasDatas(
														cobrancaAcaoAtividadeComando
																.getComando(),
														cobrancaAcaoAtividadeComando
																.getDataEncerramentoPrevista()));
					}
				}

				if (cobrancaAcaoAtividadeComando
						.getQuantidadeMaximaDocumentos() != null) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setQuantidadeMaximaDocumentos(""
									+ cobrancaAcaoAtividadeComando
											.getQuantidadeMaximaDocumentos());
				}
				if (cobrancaAcaoAtividadeComando
						.getValorLimiteObrigatoria() != null) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setValorLimiteObrigatoria(""
									+ cobrancaAcaoAtividadeComando
											.getValorLimiteObrigatoria());
				}
				if (cobrancaAcaoAtividadeComando.getIndicadorBoletim() != null) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setIndicadorGerarBoletimCadastro(""
									+ cobrancaAcaoAtividadeComando
											.getIndicadorBoletim());
				}
				if (cobrancaAcaoAtividadeComando.getIndicadorDebito() != null) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setIndicadorImoveisDebito(""
									+ cobrancaAcaoAtividadeComando
											.getIndicadorDebito());
				}

			}

		}// fim do comando ação cobrança pelo request

		// valdiar os criteorios de rota e comando para o usuário selecionar
		if (validarCriterio != null && !validarCriterio.equals("")) {

			if (validar != null && validar.equals("Atividade")) {// validar a
				// atividade
				// selecionada

				if (inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.getCobrancaAtividade() != null
						&& !inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
								.getCobrancaAtividade().equals("")) {
					FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
					filtroCobrancaAtividade
							.adicionarParametro(new ParametroSimples(
									FiltroCobrancaAtividade.ID,
									inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
											.getCobrancaAtividade()));
					Collection colecaoCobrancaAtividade = fachada.pesquisar(
							filtroCobrancaAtividade, CobrancaAtividade.class
									.getName());
					if (colecaoCobrancaAtividade != null
							&& !colecaoCobrancaAtividade.isEmpty()) {
						CobrancaAtividade cobrancaAtividade = (CobrancaAtividade) colecaoCobrancaAtividade
								.iterator().next();
						String indicador = null;
						if (cobrancaAtividade.getIndicadorExecucao() != null) {
							indicador = cobrancaAtividade
									.getIndicadorExecucao().toString();
						} else {
							indicador = "";
						}
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
								.setCobrancaAtividadeIndicadorExecucao(indicador);
						// httpServletRequest.setAttribute("cobrancaAtividadeIndicadorExecucao",cobrancaAtividade.getIndicadorExecucao().toString());
					} else {
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
								.setCobrancaAtividadeIndicadorExecucao("");
						// httpServletRequest.setAttribute("cobrancaAtividadeIndicadorExecucao","");
					}
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setCobrancaAtividade(inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
									.getCobrancaAtividade());
				}
			}
		}

		// CARREGAR AS COBRANÇAS GRUPO - INICIO
		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
		if (sessao.getAttribute("colecaoCobrancaGrupo") == null) {
			Collection colecaoCobrancaGrupo = (Collection) fachada.pesquisar(
					filtroCobrancaGrupo, CobrancaGrupo.class.getName());
			filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);
			if (colecaoCobrancaGrupo != null && !colecaoCobrancaGrupo.isEmpty()) {
				// carregar grupo de cobrança
				sessao.setAttribute("colecaoCobrancaGrupo",
						colecaoCobrancaGrupo);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa.nenhum_registro_tabela", null,
						"Tabela Cobrança Grupo");
			}
		}
		// FIM COBRANÇA GRUPO
		
		//CLIENTE
		if(inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getIdCliente() != null && !inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.equals("")){
			pesquisarCliente(inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getInscricaoTipo(), 
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm, fachada, httpServletRequest);	
		}
		
		

		// CARREGAR AS COBRANÇAS ATIVIDADE - INICIO

		FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
		if (sessao.getAttribute("colecaoCobrancaAtividade") == null) {
			filtroCobrancaAtividade
					.setCampoOrderBy(FiltroCobrancaAtividade.DESCRICAO);
			filtroCobrancaAtividade
					.adicionarParametro(new ParametroSimplesDiferenteDe(
							FiltroCobrancaAtividade.ID,
							CobrancaAtividade.ENCERRAR));
			Collection colecaoCobrancaAtividade = (Collection) fachada
					.pesquisar(filtroCobrancaAtividade, CobrancaAtividade.class
							.getName());
			if (colecaoCobrancaAtividade != null
					&& !colecaoCobrancaAtividade.isEmpty()) {
				// carregar atividade de cobrança
				sessao.setAttribute("colecaoCobrancaAtividade",
						colecaoCobrancaAtividade);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa.nenhum_registro_tabela", null,
						"Tabela Cobrança Atividade");
			}
		}

		// FIM COBRANÇA ATIVIDADE

		// CARREGAR AS COBRANÇAS ACAO - INICIO

		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.INDICADOR_USO,ConstantesSistema.SIM));
		
		if (sessao.getAttribute("colecaoCobrancaAcao") == null) {
			filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);
			Collection colecaoCobrancaAcao = (Collection) fachada.pesquisar(
					filtroCobrancaAcao, CobrancaAcao.class.getName());
			if (colecaoCobrancaAcao != null && !colecaoCobrancaAcao.isEmpty()) {
				// carregar ação de cobrança
				sessao.setAttribute("colecaoCobrancaAcao", colecaoCobrancaAcao);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa.nenhum_registro_tabela", null,
						"Tabela Cobrança Ação");
			}
		}

		// FIM COBRANÇA Ação
		
		if (sessao.getAttribute("colecaoFiscalizacaoSituacao") == null) {
			
			FiltroFiscalizacaoSituacao filtroFiscalizacaoSituacao = new FiltroFiscalizacaoSituacao();
			
			filtroFiscalizacaoSituacao.setCampoOrderBy(FiltroFiscalizacaoSituacao.DESCRICAO);
			
			Collection colecaoFiscalizacaoSituacao = (Collection) fachada.pesquisar(
					filtroFiscalizacaoSituacao, FiscalizacaoSituacao.class.getName());
			if (colecaoFiscalizacaoSituacao != null && !colecaoFiscalizacaoSituacao.isEmpty()) {
				// carregar ação de cobrança
				sessao.setAttribute("colecaoFiscalizacaoSituacao", colecaoFiscalizacaoSituacao);
			}
		}

		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO,ConstantesSistema.SIM));

		filtroGerenciaRegional
				.setCampoOrderBy(FiltroGerenciaRegional.NOME_ABREVIADO);
		Collection colecaoGerenciaRegional = (Collection) fachada.pesquisar(
				filtroGerenciaRegional, GerenciaRegional.class.getName());
		if (colecaoGerenciaRegional != null
				&& !colecaoGerenciaRegional.isEmpty()) {
			// carregar gerencia regional
			sessao.setAttribute("colecaoGerenciaRegional",
					colecaoGerenciaRegional);
		} else {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Tabela Gerência Regional");
		}

		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO,ConstantesSistema.SIM));

		filtroUnidadeNegocio
				.setCampoOrderBy(FiltroUnidadeNegocio.NOME_ABREVIADO);
		Collection colecaoUnidadeNegocio = (Collection) fachada.pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class.getName());
		if (colecaoUnidadeNegocio != null && !colecaoUnidadeNegocio.isEmpty()) {
			// carregar gerencia regional
			sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		} else {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Tabela Unidade Negócio");
		}

		FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
		filtroClienteRelacaoTipo
				.setCampoOrderBy(FiltroClienteRelacaoTipo.DESCRICAO);
		// carrega os cliente relação tipo
		Collection colecaoClienteRelacaoTipo = (Collection) fachada.pesquisar(
				filtroClienteRelacaoTipo, ClienteRelacaoTipo.class.getName());
		if (colecaoClienteRelacaoTipo != null
				&& !colecaoClienteRelacaoTipo.isEmpty()) {
			// carregar cliente relação tipo
			sessao.setAttribute("colecaoClienteRelacaoTipo",
					colecaoClienteRelacaoTipo);
		} else {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Tabela Cliente Relação Tipo");
		}

		String periodoFinalConta = fachada.pesquisarParametrosDoSistema()
				.getAnoMesArrecadacao()
				+ "";

		if ((inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
				.getPeriodoFinalConta() != null && inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
				.getPeriodoFinalConta().equals(""))
				| inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.getPeriodoFinalConta() == null) {

			String ano = periodoFinalConta.substring(0, 4);
			String mes = periodoFinalConta.substring(4, 6);
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
					.setPeriodoFinalConta(mes + "/" + ano);
		}

		if ((inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
				.getPeriodoVencimentoContaFinal() != null && inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
				.getPeriodoVencimentoContaFinal().equals(""))
				| inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.getPeriodoVencimentoContaFinal() == null) {
			Calendar calendarNova = Calendar.getInstance();

			calendarNova.add(Calendar.MONTH, -1);

			String dataNova = "";
			dataNova = calendarNova.getActualMaximum(Calendar.DAY_OF_MONTH)
					+ "";

			if (calendarNova.get(Calendar.MONTH) < 9) {
				dataNova = dataNova + "/0"
						+ (calendarNova.get(Calendar.MONTH) + 1);
			} else {
				dataNova = dataNova + "/"
						+ (calendarNova.get(Calendar.MONTH) + 1);
			}
			dataNova = dataNova + "/" + calendarNova.get(Calendar.YEAR);

			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
					.setPeriodoVencimentoContaFinal(dataNova);
		}

		String objetoConsulta = (String) httpServletRequest
				.getParameter("objetoConsulta");

		String inscricaoTipo = (String) httpServletRequest
				.getParameter("inscricaoTipo");

		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")
				&& inscricaoTipo != null
				&& !inscricaoTipo.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {
			// Localidade
			case 1:

				pesquisarLocalidade(
						inscricaoTipo,
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
						fachada, httpServletRequest);

				break;
			// Setor Comercial
			case 2:

				pesquisarLocalidade(
						inscricaoTipo,
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
						fachada, httpServletRequest);

				pesquisarSetorComercial(
						inscricaoTipo,
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
						fachada, httpServletRequest);

				break;
			case 3:
				pesquisarCliente(
						inscricaoTipo,
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
						fachada, httpServletRequest);
				break;
			case 4:
				pesquisarLogradouro(
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
						fachada, httpServletRequest);
				break;	
			default:
				break;
			}
		}

		return retorno;
	}

	/**
	 * Pesquisa a Localidade
	 * 
	 * @param inscricaoTipo
	 * @param imovelOutrosCriteriosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarLocalidade(
			String inscricaoTipo,
			InserirComandoAcaoCobrancaEventualCriterioRotaActionForm inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
					.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
					.getLocalidadeOrigemID();

			// / gerenciaRegionalID = (String)
			// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
			// .getGerenciaRegional();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			// / filtroLocalidade.adicionarParametro(new ParametroSimples(
			// // FiltroLocalidade.ID_GERENCIA, gerenciaRegionalID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formulário
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.setLocalidadeOrigemID("");
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.setNomeLocalidadeOrigem("Localidade Inexistente");
				httpServletRequest.setAttribute("corLocalidadeOrigem",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeOrigemID");

			} else {
				Localidade objetoLocalidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.setLocalidadeOrigemID(String.valueOf(objetoLocalidade
								.getId()));
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.setNomeLocalidadeOrigem(objetoLocalidade
								.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");

				String localidadeDestinoID = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.getLocalidadeDestinoID();
				// verifica o valor das localidades, origem e final
				if (localidadeDestinoID != null) {

					if (localidadeDestinoID.equals("")) {
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
								.setLocalidadeDestinoID(String
										.valueOf(objetoLocalidade.getId()));
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
								.setNomeLocalidadeDestino(objetoLocalidade
										.getDescricao());
					} else {
						int localidadeDestino = new Integer(localidadeDestinoID)
								.intValue();
						int localidadeOrigem = objetoLocalidade.getId()
								.intValue();
						if (localidadeOrigem > localidadeDestino) {
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
									.setLocalidadeDestinoID(String
											.valueOf(objetoLocalidade.getId()));
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
									.setNomeLocalidadeDestino(objetoLocalidade
											.getDescricao());
						}
					}
				}
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeDestinoID");

			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
					.getLocalidadeDestinoID();

			// / gerenciaRegionalID = (String)
			// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
			// .getGerenciaRegional();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			// / filtroLocalidade.adicionarParametro(new ParametroSimples(
			// FiltroLocalidade.ID_GERENCIA, gerenciaRegionalID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
					.setInscricaoTipo("destino");

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formulário
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.setLocalidadeDestinoID("");
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.setNomeLocalidadeDestino("Localidade inexistente");
				httpServletRequest.setAttribute("corLocalidadeDestino",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeDestinoID");

			} else {
				Localidade objetoLocalidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisa);

				int localidadeDestino = objetoLocalidade.getId().intValue();

				String localidade = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.getLocalidadeOrigemID();

				if (localidade != null && !localidade.equals("")) {

					int localidadeOrigem = new Integer(localidade).intValue();
					if (localidadeDestino < localidadeOrigem) {
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
								.setLocalidadeDestinoID("");
						// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						// .setNomeLocalidadeDestino("Loc. Final maior que a
						// Inicial");
						httpServletRequest.setAttribute("mensagem",
								"Localidae Final menor que o Inicial");
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
								.setNomeLocalidadeDestino("");
						httpServletRequest.setAttribute("corLocalidadeDestino",
								"valor");

						httpServletRequest.setAttribute("nomeCampo",
								"localidadeDestinoID");

					} else {
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
								.setLocalidadeDestinoID(String
										.valueOf(objetoLocalidade.getId()));
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
								.setNomeLocalidadeDestino(objetoLocalidade
										.getDescricao());
						httpServletRequest.setAttribute("corLocalidadeDestino",
								"valor");
						httpServletRequest.setAttribute("nomeCampo",
								"setorComercialOrigemCD");

					}
				} else {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setLocalidadeDestinoID(String
									.valueOf(objetoLocalidade.getId()));
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeLocalidadeDestino(objetoLocalidade
									.getDescricao());
					httpServletRequest.setAttribute("corLocalidadeDestino",
							"valor");
					httpServletRequest.setAttribute("nomeCampo",
							"setorComercialOrigemCD");

				}
			}
		}

	}

	/**
	 * Pesquisa o Setor Comercial
	 * 
	 * @param inscricaoTipo
	 * @param imovelOutrosCriteriosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarSetorComercial(
			String inscricaoTipo,
			InserirComandoAcaoCobrancaEventualCriterioRotaActionForm inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
					.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
					.getLocalidadeOrigemID();

			// O campo localidadeOrigemID será obrigatório
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.getSetorComercialOrigemCD();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formulário
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setSetorComercialOrigemCD("");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setSetorComercialOrigemID("");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeSetorComercialOrigem("Setor Comercial Inexistente");
					httpServletRequest.setAttribute("corSetorComercialOrigem",
							"exception");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setRotaInicial(null);
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setRotaFinal(null);

					httpServletRequest.setAttribute("nomeCampo",
							"setorComercialOrigemCD");

				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					// setorComercialID =
					// objetoSetorComercial.getId().toString();
					// setorComercialOrigem
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setSetorComercialOrigemCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setSetorComercialOrigemID(String
									.valueOf(objetoSetorComercial.getId()));
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeSetorComercialOrigem(objetoSetorComercial
									.getDescricao());
					httpServletRequest.setAttribute("corSetorComercialOrigem",
							"valor");

					String setorComercialDestinoCD = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.getSetorComercialDestinoCD();

					// verifica o valor dos setores comerciais, origem e final
					if (setorComercialDestinoCD != null) {

						if (setorComercialDestinoCD.equals("")) {

							// setorComercialDestino
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
									.setSetorComercialDestinoCD(String
											.valueOf(objetoSetorComercial
													.getCodigo()));
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
									.setSetorComercialDestinoID(String
											.valueOf(objetoSetorComercial
													.getId()));
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
									.setNomeSetorComercialDestino(objetoSetorComercial
											.getDescricao());

							// carregarRota(
							// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
							// fachada,
							// objetoSetorComercial.getCodigo()+"",localidadeID);

						} else {

							int setorDestino = new Integer(
									setorComercialDestinoCD).intValue();
							int setorOrigem = objetoSetorComercial.getCodigo();
							if (setorOrigem > setorDestino) {

								// setorComercialDestino
								inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
										.setSetorComercialDestinoCD(String
												.valueOf(objetoSetorComercial
														.getCodigo()));
								inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
										.setSetorComercialDestinoID(String
												.valueOf(objetoSetorComercial
														.getId()));
								inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
										.setNomeSetorComercialDestino(objetoSetorComercial
												.getDescricao());

								// carregarRota(
								// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
								// fachada,
								// objetoSetorComercial.getCodigo()+"",localidadeID);
							}
						}
						httpServletRequest.setAttribute("nomeCampo",
								"setorComercialDestinoCD");
					}
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.setSetorComercialOrigemCD("");
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.setNomeSetorComercialOrigem("Informe a localidade da inscrição de origem.");
				httpServletRequest.setAttribute("corSetorComercialOrigem",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialOrigemCD");
			}
		} else {

			inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
					.setInscricaoTipo("destino");

			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
					.getLocalidadeDestinoID();

			// O campo localidadeOrigem será obrigatório
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.getSetorComercialDestinoCD();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID do
					// formulário
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setSetorComercialDestinoCD("");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setSetorComercialDestinoID("");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeSetorComercialDestino("Setor Comercial Inexistente");
					httpServletRequest.setAttribute("corSetorComercialDestino",
							"exception");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setRotaInicial(null);
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setRotaFinal(null);
					httpServletRequest.setAttribute("nomeCampo",
							"setorComercialDestinoCD");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);

					int setorDestino = objetoSetorComercial.getCodigo();

					String setor = (String) inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.getSetorComercialOrigemCD();

					if (setor != null && !setor.equals("")) {

						int setorOrigem = new Integer(setor).intValue();
						if (setorDestino < setorOrigem) {

							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
									.setSetorComercialDestinoCD("");
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
									.setSetorComercialDestinoID("");
							// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							// .setNomeSetorComercialDestino("Setor Final maior
							// que Inicial");
							httpServletRequest
									.setAttribute("mensagem",
											"Setor Comercial Final menor que o Inicial");
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
									.setNomeSetorComercialDestino("");
							httpServletRequest.setAttribute(
									"corSetorComercialDestino", "valor");

							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
									.setRotaInicial(null);
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
									.setRotaFinal(null);
							httpServletRequest.setAttribute("nomeCampo",
									"setorComercialDestinoCD");

						} else {
							// rota
							// carregarRota(
							// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
							// fachada,
							// objetoSetorComercial.getCodigo()+"",localidadeID
							// );

							// setor comercial destino
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
									.setSetorComercialDestinoCD(String
											.valueOf(objetoSetorComercial
													.getCodigo()));
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
									.setSetorComercialDestinoID(String
											.valueOf(objetoSetorComercial
													.getId()));
							inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
									.setNomeSetorComercialDestino(objetoSetorComercial
											.getDescricao());
							httpServletRequest.setAttribute(
									"corSetorComercialDestino", "valor");
							httpServletRequest.setAttribute("nomeCampo",
									"rotaFinal");
						}
					} else {

						// carregarRota(
						// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
						// fachada,
						// objetoSetorComercial.getCodigo()+"",localidadeID);

						// setor comercial destino
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
								.setSetorComercialDestinoCD(String
										.valueOf(objetoSetorComercial
												.getCodigo()));
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
								.setSetorComercialDestinoID(String
										.valueOf(objetoSetorComercial.getId()));
						inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
								.setNomeSetorComercialDestino(objetoSetorComercial
										.getDescricao());
						httpServletRequest.setAttribute(
								"corSetorComercialDestino", "valor");
						httpServletRequest.setAttribute("nomeCampo",
								"rotaFinal");

					}
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formulário
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.setSetorComercialDestinoCD("");
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						.setNomeSetorComercialDestino("Informe a localidade da inscrição de destino.");
				httpServletRequest.setAttribute("corSetorComercialDestino",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialDestinoCD");

			}
		}

	}

	/**
	 * Inicializa a Rota
	 * 
	 * @param inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
	 * @param fachada
	 * @param objetoSetorComercial
	 */
	public void carregarRota(
			InserirComandoAcaoCobrancaEventualCriterioRotaActionForm inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
			Fachada fachada, String codigoSetorComercial, String idLocalidade) {

		FiltroRota filtroRota = new FiltroRota();
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroRota.adicionarParametro(new ParametroSimples(
				FiltroRota.LOCALIDADE_ID, idLocalidade));
		filtroRota.adicionarParametro(new ParametroSimples(
				FiltroRota.SETOR_COMERCIAL_CODIGO, codigoSetorComercial));
		Collection colecaoRota = (Collection) fachada.pesquisar(filtroRota,
				Rota.class.getName());
		sessao.setAttribute("colecaoRota", colecaoRota);
		inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
				.setRotaInicial("rota");
		inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
				.setRotaFinal("rota");

	}

	/**
	 * Pesquisa o Setor Comercial
	 * 
	 * @param inscricaoTipo
	 * @param imovelOutrosCriteriosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarCliente(
			String inscricaoTipo,
			InserirComandoAcaoCobrancaEventualCriterioRotaActionForm inserirComandoAcaoCobrancaEventualCriterioRotaActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		String idCliente = null;
		if (inscricaoTipo != null && inscricaoTipo.equals("superior")) {
			idCliente = inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
					.getCodigoClienteSuperior();
		} else {
			idCliente = inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
					.getIdCliente();
		}

		// -------Parte que trata do código quando o usuário tecla enter
		// se o id do cliente for diferente de nulo
		if (idCliente != null
				&& !idCliente.toString().trim().equalsIgnoreCase("")) {

			FiltroCliente filtroCliente = new FiltroCliente();
			Collection clientes = null;
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, new Integer(idCliente)));
			clientes = fachada
					.pesquisar(filtroCliente, Cliente.class.getName());
			if (clientes != null && !clientes.isEmpty()) {
				// O cliente foi encontrado

				if (inscricaoTipo != null && inscricaoTipo.equals("superior")) {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setCodigoClienteSuperior(((Cliente) ((List) clientes)
									.get(0)).getId().toString());
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeClienteSuperior(((Cliente) ((List) clientes)
									.get(0)).getNome());
				} else {
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setIdCliente(((Cliente) ((List) clientes).get(0))
									.getId().toString());
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeCliente(((Cliente) ((List) clientes).get(0))
									.getNome());
				}

				Cliente cliente = new Cliente();

				cliente = (Cliente) clientes.iterator().next();
				sessao.setAttribute("clienteObj", cliente);
			} else {
				if (inscricaoTipo != null && inscricaoTipo.equals("superior")) {
					httpServletRequest.setAttribute(
							"codigoClienteSuperiorNaoEncontrado", "true");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeClienteSuperior("");
					httpServletRequest.setAttribute("nomeCampo",
							"codigoClienteSuperior");
				} else {
					httpServletRequest.setAttribute(
							"codigoClienteNaoEncontrado", "true");
					inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							.setNomeCliente("");
					httpServletRequest.setAttribute("nomeCampo", "idCliente");
				}
				
			}

		}
	
	}
	private void pesquisarLogradouro(InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		
		
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0011] Valida Logradouro

			// Filtra Logradouro
			FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
			filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, 
					form.getLogradouroId()));
			
			// Recupera Logradouro
			Collection<Logradouro> colecaoLogradouro = fachada.pesquisar(filtroLogradouro, Logradouro.class.getName());
			
			if (colecaoLogradouro != null && !colecaoLogradouro.isEmpty()) {
				sessao.setAttribute("logradouroEncontrada", "true");
				Logradouro logradouro = colecaoLogradouro.iterator().next();
				form.setLogradouroDescricao(logradouro.getNome());
				form.setLogradouroId(logradouro.getId().toString());
			} else {
				sessao.removeAttribute("logradouroEncontrada");
				form.setLogradouroId("");
				form.setLogradouroDescricao("Logradouro inexistente");
			}

	}
	
}
