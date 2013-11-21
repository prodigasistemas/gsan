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

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.util.ConstantesInterfaceGSAN;
import gcom.util.ConstantesSistema;
import gcom.util.Internacionalizador;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Pedro Alexandre
 * @date 21/05/2007
 */
public class ExibirGerarRelatorioAnormalidadeLeituraPeriodoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm = (GerarRelatorioAnormalidadeLeituraPeriodoActionForm) actionForm;

		if (httpServletRequest.getParameter("menu") != null) {
			relatorioForm.limparForm();
			carregarColecoes(httpServletRequest.getSession(false));
		}

		consultarLocalidadeInicial(relatorioForm);

		consultarLocalidadeFinal(relatorioForm);

		consultarSetorComercialInicial(relatorioForm, httpServletRequest);

		consultarSetorComercialFinal(relatorioForm, httpServletRequest);

		return actionMapping.findForward("exibirGerarRelatorioAnormalidadeLeituraPeriodo");
	}

	/**
	 * Esse método consulta a localidade final, caso o usuário a tenha informado.
	 * Se por acaso ela não existir, é enviada a mensagem informando isso.
	 *
	 *@since 02/10/2009
	 *@author Marlon Patrick
	 */
	private void consultarLocalidadeFinal(GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm) {

		if ( Util.verificarNaoVazio(relatorioForm.getIdLocalidadeFinal())) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,relatorioForm.getIdLocalidadeFinal()));
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO
					,ConstantesSistema.INDICADOR_USO_ATIVO));

			boolean isUnidadeNegocioInformado = false;

			if(Util.isCampoComboboxInformado(relatorioForm.getIdUnidadeNegocio())){
				isUnidadeNegocioInformado = true;

				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.UNIDADE_NEGOCIO_ID,relatorioForm.getIdUnidadeNegocio()));
			}

			Collection<Localidade> colecaoLocalidade = 
				this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

			if ( Util.isVazioOrNulo(colecaoLocalidade)) {
				relatorioForm.setIdLocalidadeFinal("");

				if(isUnidadeNegocioInformado){
					relatorioForm.setNomeLocalidadeFinal(
							Internacionalizador.getMensagem(ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO1_INEXISTENTE_NA_CAMPO2_INFORMADA,
									new String[]{ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE,ConstantesInterfaceGSAN.LABEL_GSAN_UNIDADE_NEGOCIO}));
				}else{
					relatorioForm.setNomeLocalidadeFinal(Internacionalizador.getMensagem(
							ConstantesInterfaceGSAN.ATENCAO_PESQUISA_INEXISTENTE, ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE));
				}

				return;
			}

			Localidade localidade = colecaoLocalidade.iterator().next();

			relatorioForm.setIdLocalidadeFinal(localidade.getId().toString());
			relatorioForm.setNomeLocalidadeFinal(localidade.getDescricao());
		}
	}

	/**
	 * Esse método consulta a localidade inicial, caso o usuário a tenha informado.
	 * Se por acaso ela não existir, é enviada a mensagem informando isso.
	 *
	 *@since 02/10/2009
	 *@author Marlon Patrick
	 */
	private void consultarLocalidadeInicial(GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm) {

		if ( Util.verificarNaoVazio(relatorioForm.getIdLocalidadeInicial())) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,relatorioForm.getIdLocalidadeInicial()));
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO
					,ConstantesSistema.INDICADOR_USO_ATIVO));

			boolean isUnidadeNegocioInformado = false;

			if(Util.isCampoComboboxInformado(relatorioForm.getIdUnidadeNegocio())){
				isUnidadeNegocioInformado = true;

				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.UNIDADE_NEGOCIO_ID,relatorioForm.getIdUnidadeNegocio()));
			}

			Collection<Localidade> colecaoLocalidade = 
				this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

			if ( Util.isVazioOrNulo(colecaoLocalidade)) {
				relatorioForm.setIdLocalidadeInicial("");

				if(isUnidadeNegocioInformado){
					relatorioForm.setNomeLocalidadeFinal(
							Internacionalizador.getMensagem(ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO1_INEXISTENTE_NA_CAMPO2_INFORMADA,
									new String[]{ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE,ConstantesInterfaceGSAN.LABEL_GSAN_UNIDADE_NEGOCIO}));
				}else{
					relatorioForm.setNomeLocalidadeFinal(Internacionalizador.getMensagem(
							ConstantesInterfaceGSAN.ATENCAO_PESQUISA_INEXISTENTE, ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE));
				}

				return;
			}

			Localidade localidade = colecaoLocalidade.iterator().next();

			relatorioForm.setIdLocalidadeInicial(localidade.getId().toString());
			relatorioForm.setNomeLocalidadeInicial(localidade.getDescricao());
		}
	}

	/**
	 * Esse método consulta o setor inicial, caso o usuário o tenha informado.
	 * Se por acaso ele não existir, é enviada a mensagem informando isso.
	 *
	 *@since 02/10/2009
	 *@author Marlon Patrick
	 */
	private void consultarSetorComercialInicial(GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm,
			HttpServletRequest httpServletRequest) {

		if ( Util.verificarNaoVazio(relatorioForm.getCodigoSetorComercialInicial())) {

			Integer codigoSetorInicial = new Integer(relatorioForm.getCodigoSetorComercialInicial());

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,codigoSetorInicial));
			filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID,new Integer(relatorioForm.getIdLocalidadeInicial())));

			Collection<SetorComercial> colecaoSetorComercial = 
				this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if ( Util.isVazioOrNulo(colecaoSetorComercial)) {

				relatorioForm.setNomeSetorComercialInicial(Internacionalizador.getMensagem(ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO1_INEXISTENTE_NA_CAMPO2_INFORMADA,
						new String[]{ConstantesInterfaceGSAN.LABEL_GSAN_SETOR_COMERCIAL,ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE}));

				httpServletRequest.setAttribute("setorComercialInicialInexistente",true);

			} else {
				SetorComercial setor = colecaoSetorComercial.iterator().next();
				relatorioForm.setNomeSetorComercialInicial(setor.getDescricao());
			}

		} else {
			relatorioForm.setNomeSetorComercialInicial("");
		}
	}

	/**
	 * Esse método consulta o setor final, caso o usuário o tenha informado.
	 * Se por acaso ele não existir, é enviada a mensagem informando isso.
	 *
	 *@since 02/10/2009
	 *@author Marlon Patrick
	 */
	private void consultarSetorComercialFinal(GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm,
			HttpServletRequest httpServletRequest) {

		if ( Util.verificarNaoVazio(relatorioForm.getCodigoSetorComercialFinal())) {

			Integer codigoSetorFinal = new Integer(relatorioForm.getCodigoSetorComercialFinal());

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,codigoSetorFinal));
			filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID,new Integer(relatorioForm.getIdLocalidadeFinal())));

			Collection<SetorComercial> colecaoSetorComercial = 
				this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if ( Util.isVazioOrNulo(colecaoSetorComercial)) {
				relatorioForm.setNomeSetorComercialFinal(Internacionalizador.getMensagem(ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO1_INEXISTENTE_NA_CAMPO2_INFORMADA,
						new String[]{ConstantesInterfaceGSAN.LABEL_GSAN_SETOR_COMERCIAL,ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE}));

				httpServletRequest.setAttribute("setorComercialFinalInexistente",true);

			} else {
				SetorComercial setor = colecaoSetorComercial.iterator().next();
				relatorioForm
				.setNomeSetorComercialFinal(setor.getDescricao());
			}

		} else {
			relatorioForm.setNomeSetorComercialFinal("");
		}
	}

	/**
	 * Esse método carrega todas as coleções a serem exibidas na tela
	 * na sessão. (Anormalidades de Leitura e Grupos de Faturamento)
	 *
	 *@since 29/10/2009
	 *@author Marlon Patrick
	 */
	private void carregarColecoes(HttpSession sessao) {

		Fachada fachada = Fachada.getInstancia();

		FiltroLeituraAnormalidade filtroConsumoAnormalidade = new FiltroLeituraAnormalidade(FiltroLeituraAnormalidade.DESCRICAO);				
		filtroConsumoAnormalidade.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<LeituraAnormalidade> colecaoAnormalidadeleitura = fachada.pesquisar(filtroConsumoAnormalidade, LeituraAnormalidade.class.getName());

		if(Util.isVazioOrNulo(colecaoAnormalidadeleitura)){
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_ENTIDADE_SEM_DADOS_PARA_SELECAO,
					ConstantesInterfaceGSAN.LABEL_GSAN_ANORMALIDADE_LEITURA);
		}

		sessao.setAttribute("colecaoAnormalidadesLeitura",colecaoAnormalidadeleitura);

		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo(FiltroFaturamentoGrupo.DESCRICAO);
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<FaturamentoGrupo> colecaoFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

		if(Util.isVazioOrNulo(colecaoFaturamentoGrupo)){
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_ENTIDADE_SEM_DADOS_PARA_SELECAO,
					ConstantesInterfaceGSAN.LABEL_GSAN_GRUPO_FATURAMENTO);
		}

		sessao.setAttribute("colecaoGruposFaturamento",colecaoFaturamentoGrupo);

		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio(FiltroUnidadeNegocio.NOME);
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<UnidadeNegocio> colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		if(Util.isVazioOrNulo(colecaoUnidadeNegocio)){
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_ENTIDADE_SEM_DADOS_PARA_SELECAO,
					ConstantesInterfaceGSAN.LABEL_GSAN_GRUPO_FATURAMENTO);
		}

		sessao.setAttribute("colecaoUnidadeNegocio", 
				fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName()));
	}
}