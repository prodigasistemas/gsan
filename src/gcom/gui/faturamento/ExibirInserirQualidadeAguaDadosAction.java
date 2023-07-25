package gcom.gui.faturamento;

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

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroQualidadeAgua;
import gcom.faturamento.FiltroQualidadeAguaPadrao;
import gcom.faturamento.QualidadeAgua;
import gcom.faturamento.QualidadeAguaPadrao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroFonteCaptacao;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.FonteCaptacao;
import gcom.operacional.SistemaAbastecimento;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Kássia Albuquerque, Rômulo Aurélio [Alteracao para abas]
 * @date 24/07/2007, 16/09/2008
 */

public class ExibirInserirQualidadeAguaDadosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirInserirQualidadeAguaDadosAction");

		InserirQualidadeAguaActionForm form = (InserirQualidadeAguaActionForm) actionForm;

		HttpSession sessao = this.getSessao(httpServletRequest);

		// SETANDO FOCO INICIAL
		if (httpServletRequest.getParameter("menu") != null && 
			!httpServletRequest.getParameter("menu").equals("")) {

			httpServletRequest.setAttribute("nomeCampo", "referencia");

			sessao.removeAttribute("inserirTodosAtivado");

			// [SB0001 - EXIBIÇÃO DOS PARAMETROS DE QUALIDADE DA ÁGUA]
			FiltroQualidadeAguaPadrao filtroQualidadeAguaPadrao = new FiltroQualidadeAguaPadrao();
			Collection colecaoQualidadeAguaPadrao = 
				this.getFachada().pesquisar(filtroQualidadeAguaPadrao,QualidadeAguaPadrao.class.getName());

			if (colecaoQualidadeAguaPadrao != null && 
				!colecaoQualidadeAguaPadrao.isEmpty()) {

				QualidadeAguaPadrao qualidadeAguaPadrao = 
					(QualidadeAguaPadrao) colecaoQualidadeAguaPadrao.iterator().next();

				sessao.setAttribute("qualidadeAguaPadraoId",qualidadeAguaPadrao.getId());

				if (qualidadeAguaPadrao.getDescricaoPadraoTurbidez() != null && 
					!qualidadeAguaPadrao.getDescricaoPadraoTurbidez().equals("")) {

					form.setPadraoTurbidez(qualidadeAguaPadrao.getDescricaoPadraoTurbidez());

				} else {
					form.setPadraoTurbidez("");
				}

				if (qualidadeAguaPadrao.getDescricaoPadraoCloro() != null && 
					!qualidadeAguaPadrao.getDescricaoPadraoCloro().equals("")) {

					form.setPadraoCloroResidual(qualidadeAguaPadrao.getDescricaoPadraoCloro());
				} else {
					form.setPadraoCloroResidual("");
				}

				if (qualidadeAguaPadrao.getDescricaoPadraoPh() != null && 
					!qualidadeAguaPadrao.getDescricaoPadraoPh().equals("")) {

					form.setPadraoPH(qualidadeAguaPadrao.getDescricaoPadraoPh());
				} else {
					form.setPadraoPH("");
				}

				if (qualidadeAguaPadrao.getDescricaoPadraoCor() != null && 
					!qualidadeAguaPadrao.getDescricaoPadraoCor().equals("")) {

					form.setPadraoCor(qualidadeAguaPadrao.getDescricaoPadraoCor());
				} else {
					form.setPadraoCor("");
				}

				if (qualidadeAguaPadrao.getDescricaoPadraoFluor() != null && 
					!qualidadeAguaPadrao.getDescricaoPadraoFluor().equals("")) {

					form.setPadraoFluor(qualidadeAguaPadrao.getDescricaoPadraoFluor());
				} else {
					form.setPadraoFluor("");
				}

				if (qualidadeAguaPadrao.getDescricaoPadraoFerro() != null && 
					!qualidadeAguaPadrao.getDescricaoPadraoFerro().equals("")) {

					form.setPadraoFerro(qualidadeAguaPadrao.getDescricaoPadraoFerro());
				} else {
					form.setPadraoFerro("");
				}

				if (qualidadeAguaPadrao.getDescricaoPadraoColiformesTotais() != null && 
					!qualidadeAguaPadrao.getDescricaoPadraoColiformesTotais().equals("")) {

					form.setPadraoColiformesTotais(qualidadeAguaPadrao.getDescricaoPadraoColiformesTotais());
				} else {
					form.setPadraoColiformesTotais("");
				}

				if (qualidadeAguaPadrao.getDescricaoPadraoColiformesFecais() != null && 
					!qualidadeAguaPadrao.getDescricaoPadraoColiformesFecais().equals("")) {

					form.setPadraoColiformesFecais(qualidadeAguaPadrao.getDescricaoPadraoColiformesFecais());
				} else {
					form.setPadraoColiformesFecais("");
				}

				if (qualidadeAguaPadrao.getDescricaoNitrato() != null && 
					!qualidadeAguaPadrao.getDescricaoNitrato().equals("")) {

					form.setPadraoNitrato(qualidadeAguaPadrao.getDescricaoNitrato());
				} else {
					form.setPadraoNitrato("");
				}

				if (qualidadeAguaPadrao.getDescricaoPadraoColiformesTermotolerantes() != null && 
					!qualidadeAguaPadrao.getDescricaoPadraoColiformesTermotolerantes().equals("")) {

					form.setPadraoColiformesTermotolerantes(qualidadeAguaPadrao.getDescricaoPadraoColiformesTermotolerantes());
				} else {
					form.setPadraoColiformesTermotolerantes("");
				}
				
				if(qualidadeAguaPadrao.getDescricaoPadraoAlcalinidade() != null 
						&& !qualidadeAguaPadrao.getDescricaoPadraoAlcalinidade().equals("")){
					
					form.setPadraoAlcalinidade(qualidadeAguaPadrao.getDescricaoPadraoAlcalinidade());
					
				}else{
					form.setPadraoAlcalinidade("");
				}

			} else {

				form.setPadraoTurbidez("");
				form.setPadraoCloroResidual("");
				form.setPadraoPH("");
				form.setPadraoCor("");
				form.setPadraoFluor("");
				form.setPadraoFerro("");
				form.setPadraoColiformesTotais("");
				form.setPadraoColiformesFecais("");
				form.setPadraoNitrato("");
				form.setPadraoColiformesTermotolerantes("");
				form.setPadraoAlcalinidade("");

			}

		}

		String indicadorInserirTodos = form.getIncluirTodos();

		// [FS0001 - VALIDAR REFERÊNCIA]
		if ((form.getReferencia() != null && !form.getReferencia().equals(""))
				&& (indicadorInserirTodos != null && !indicadorInserirTodos
						.equals(""))) {

			String anoMesReferencia = Util
					.formatarMesAnoParaAnoMesSemBarra(form.getReferencia());

			SistemaParametro sistemaParametro = this.getFachada()
					.pesquisarParametrosDoSistema();
			String anoMesFuturo = ""
					+ Util.somaUmMesAnoMesReferencia(sistemaParametro
							.getAnoMesFaturamento());

			if (Util.compararAnoMesReferencia(anoMesReferencia, anoMesFuturo,
					">")) {
				httpServletRequest.setAttribute("nomeCampo", "referencia");
				throw new ActionServletException("atencao.mes_ano_menor");
			}

			// [SB0002] INCLUSAO DOS PARAMETROS DA QUALIDADE AGUA
			httpServletRequest.setAttribute("inserirTodosAtivado", "1");

			sessao.setAttribute("inserirTodosAtivado", "1");

			FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();
			FiltroQualidadeAgua filtroQualidadeAguaRepetida = new FiltroQualidadeAgua();

			// [FS0009 - VERIFICAR EXISTENCIA MES ANTERIOR]
			String anoMesReferenciaAnterior = ""
					+ Util
							.subtrairMesDoAnoMes(new Integer(anoMesReferencia),
									1);

			filtroQualidadeAgua
					.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroQualidadeAgua
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");

			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
					FiltroQualidadeAgua.ANO_MES_REFERENCIA,
					anoMesReferenciaAnterior));

			Collection colecaoQualidadeAgua = this.getFachada().pesquisar(
					filtroQualidadeAgua, QualidadeAgua.class.getName());

			// ------------[VERIFICANDO A EXISTENCIA DE QUALIDADE AGUA PARA MÊS
			// ATUAL]---------

			filtroQualidadeAguaRepetida
					.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroQualidadeAguaRepetida
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroQualidadeAguaRepetida
					.adicionarParametro(new ParametroSimples(
							FiltroQualidadeAgua.ANO_MES_REFERENCIA,
							anoMesReferencia));

			Collection colecaoQualidadeAguaRepetida = this.getFachada()
					.pesquisar(filtroQualidadeAguaRepetida,
							QualidadeAgua.class.getName());

			if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {
				form.setIncluirTodos("");
				sessao.removeAttribute("inserirTodosAtivado");
				throw new ActionServletException(
						"atencao.mes_anterior_inexistente");
			} else if (colecaoQualidadeAguaRepetida != null
					&& !colecaoQualidadeAguaRepetida.isEmpty()) {
				throw new ActionServletException(
						"atencao.faturamento.qualidade_agua_existente_colecao",
						null, form.getReferencia());
			} else {

				Iterator iteratorColecaoQualidadeAgua = colecaoQualidadeAgua
						.iterator();

				while (iteratorColecaoQualidadeAgua.hasNext()) {

					QualidadeAgua qualidadeAgua = (QualidadeAgua) iteratorColecaoQualidadeAgua
							.next();

					qualidadeAgua.setId(null);
					qualidadeAgua.setUltimaAlteracao(new Date());
					qualidadeAgua.setAnoMesReferencia(new Integer(
							anoMesReferencia));
				}

				sessao.setAttribute("colecaoQualidadeAgua",
						colecaoQualidadeAgua);
			}

		} else if ((indicadorInserirTodos != null && !indicadorInserirTodos
				.equals(""))
				&& (form.getReferencia() == null || form.getReferencia()
						.equals(""))) {

			throw new ActionServletException("atencao.informar_referencia");
		} else {

			sessao.removeAttribute("inserirTodosAtivado");

			// [FS0001 - VALIDAR REFERÊNCIA]
			if ((form.getReferencia() != null && !form.getReferencia().equals(
					""))) {

				String anoMesReferencia = Util
						.formatarMesAnoParaAnoMesSemBarra(form.getReferencia());

				SistemaParametro sistemaParametro = this.getFachada()
						.pesquisarParametrosDoSistema();
				String anoMesFuturo = ""
						+ Util.somaUmMesAnoMesReferencia(sistemaParametro
								.getAnoMesFaturamento());

				if (Util.compararAnoMesReferencia(anoMesReferencia,
						anoMesFuturo, ">")) {

					httpServletRequest.setAttribute("nomeCampo", "referencia");
					throw new ActionServletException("atencao.mes_ano_menor");
				}
			}

			// [FS0002 - VERIFICAR EXISTENCIA DA LOCALIDADE]
			if ((form.getIdLocalidade() != null && !form.getIdLocalidade()
					.trim().equals(""))) {

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.limparListaParametros();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID,
						new Integer(form.getIdLocalidade())));

				Collection colecaoLocalidade = this.getFachada().pesquisar(
						filtroLocalidade, Localidade.class.getName());

				if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

					Localidade localidade = (Localidade) colecaoLocalidade
							.iterator().next();
					form.setIdLocalidade("" + localidade.getId());
					form.setLocalidadeDescricao(localidade.getDescricao());
					httpServletRequest.setAttribute("localidadeEncontrada",
							"true");
					httpServletRequest.setAttribute("nomeCampo",
							"idSetorComercial");

				} else {
					httpServletRequest.setAttribute("localidadeEncontrada",
							"exception");
					form.setIdLocalidade("");
					form.setLocalidadeDescricao("LOCALIDADE INEXISTENTE");
					form.setIdSetorComercial("");
					form.setSetorComercialDescricao("");
					httpServletRequest
							.setAttribute("nomeCampo", "idLocalidade");
				}

			}

			// [FS0003 - VERIFICAR EXISTENCIA DO SETOR COMERCIAL]
			if (form.getIdSetorComercial() != null
					&& !form.getIdSetorComercial().toString().trim()
							.equalsIgnoreCase("")) {

				if (form.getIdLocalidade() != null
						&& !form.getIdLocalidade().toString().trim()
								.equalsIgnoreCase("")) {

					Collection colecaoSetoresComerciais = this
							.pesquisarSetorComercial(new Integer(form
									.getIdLocalidade()), new Integer(form
									.getIdSetorComercial()));

					if (colecaoSetoresComerciais != null
							&& !colecaoSetoresComerciais.isEmpty()) {

						SetorComercial setorComercial = (SetorComercial) colecaoSetoresComerciais
								.iterator().next();

						form.setIdSetorComercial(""
								+ setorComercial.getCodigo());
						form.setSetorComercialDescricao(setorComercial
								.getDescricao());

						httpServletRequest.setAttribute(
								"codigoSetorComercialEncontrado", "true");
						httpServletRequest.setAttribute("nomeCampo",
								"fonteCaptacao");

					} else {
						httpServletRequest.setAttribute(
								"codigoSetorComercialEncontrado", "exception");
						httpServletRequest.setAttribute("nomeCampo",
								"idSetorComercial");

						form.setIdSetorComercial("");
						form
								.setSetorComercialDescricao("SETOR COMERCIAL INEXISTENTE");

					}
				}

			} else {
				form.setIdSetorComercial("");
				form.setSetorComercialDescricao("");
			}

		}
		
		Fachada fachada = Fachada.getInstancia();

		FiltroSistemaAbastecimento filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<SistemaAbastecimento> colecaoSistemaAbastecimento = fachada.pesquisar(filtroSistemaAbastecimento,
				SistemaAbastecimento.class.getName());

		if (colecaoSistemaAbastecimento == null || colecaoSistemaAbastecimento.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Sistema Abastecimento");
		}
		
		//Manda valores do Sistema de Abastecimento para Sessão
		httpServletRequest.setAttribute("colecaoSistemaAbastecimento", colecaoSistemaAbastecimento);
		
		if((form.getIdLocalidade()!=null && !form.getIdLocalidade().equals(""))
				|| (form.getFonteCaptacao()!=null && !form.getFonteCaptacao().equals("-1"))
					|| (form.getIdSetorComercial()!=null && !form.getIdSetorComercial().equals(""))){
			httpServletRequest.setAttribute("validaCampos", "localidade");
		}

		if(form.getSistemaAbastecimento()!=null&& !form.getSistemaAbastecimento().equals("-1")){
			httpServletRequest.setAttribute("validaCampos", "sistema");
		}

		this.montaColecaoFonteCaptacao(form, httpServletRequest);

		return retorno;
	}

	/**
	 * Monta a colecao de Fontes de Captacao
	 * 
	 * @author Rafael Pinto
	 * @date 15/10/2008
	 */
	private void montaColecaoFonteCaptacao(InserirQualidadeAguaActionForm form,
			HttpServletRequest httpServletRequest) {

		String localidade = form.getIdLocalidade();
		String setorComercial = form.getIdSetorComercial();
		Collection<FonteCaptacao> colecaoFonteCaptacao = new ArrayList<FonteCaptacao>();

		// Pesquisa fontes de captacao por setor comercial
		if (localidade != null && !localidade.equals("")
				&& setorComercial != null && !setorComercial.equals("")) {

			Collection colecaoSetoresComerciais = this.pesquisarSetorComercial(
					new Integer(localidade), new Integer(setorComercial));

			if (colecaoSetoresComerciais != null
					&& !colecaoSetoresComerciais.isEmpty()) {

				SetorComercial setor = (SetorComercial) colecaoSetoresComerciais
						.iterator().next();

				Collection colecaoSetor = new ArrayList();
				colecaoSetor.add(setor);

				colecaoFonteCaptacao = this.getFachada()
						.pesquisarFonteCaptacao(colecaoSetor);
			}

		} else if (localidade != null && !localidade.equals("")) {

			Collection colecaoSetoresComerciais = this.pesquisarSetorComercial(
					new Integer(localidade), null);

			colecaoFonteCaptacao = this.getFachada().pesquisarFonteCaptacao(
					colecaoSetoresComerciais);
		} else {

			FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();

			filtroFonteCaptacao.limparListaParametros();
			filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
					FiltroFonteCaptacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoFonteCaptacao = this.getFachada().pesquisar(
					filtroFonteCaptacao, FonteCaptacao.class.getName());
		}

		if (colecaoFonteCaptacao != null && !colecaoFonteCaptacao.isEmpty()) {
			
			if(colecaoFonteCaptacao.size() == 1){
				
				FonteCaptacao fonte = (FonteCaptacao) Util.retonarObjetoDeColecao(colecaoFonteCaptacao);
				form.setFonteCaptacao(fonte.getId().toString());
			}
			
			httpServletRequest.setAttribute("colecaoFonteCaptacao",colecaoFonteCaptacao);
		}

	}

	/**
	 * Pesquisa o SetorComercial
	 * 
	 * @author Rafael Pinto
	 * @date 15/10/2008
	 */
	private Collection<SetorComercial> pesquisarSetorComercial(
			Integer localidade, Integer setorComercial) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		filtroSetorComercial.limparListaParametros();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.ID_LOCALIDADE, localidade));

		if (setorComercial != null) {

			filtroSetorComercial
					.adicionarParametro(new ParametroSimples(
							FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
							setorComercial));
		}

		return this.getFachada().pesquisar(filtroSetorComercial,
				SetorComercial.class.getName());
	}

}
