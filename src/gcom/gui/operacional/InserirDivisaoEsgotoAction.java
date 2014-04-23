package gcom.gui.operacional;


import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.FiltroDivisaoEsgoto;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Este caso de uso permite a inclusão de uma Divisão de Esgoto
 * 
 * [UC0815] Inserir Divisao de Esgoto
 * 
 * 
 * @author Arthur Carvalho
 * @date 09/06/2008
 */
public class InserirDivisaoEsgotoAction extends GcomAction {

		public ActionForward execute(ActionMapping actionMapping,
				ActionForm actionForm, HttpServletRequest httpServletRequest,
				HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("telaSucesso");

			InserirDivisaoEsgotoActionForm inserirDivisaoEsgotoActionForm = (InserirDivisaoEsgotoActionForm) actionForm;

			HttpSession sessao = httpServletRequest.getSession(false);

			Fachada fachada = Fachada.getInstancia();

			String descricao = inserirDivisaoEsgotoActionForm.getDescricao();
			String idUnidadeOrganizacional= inserirDivisaoEsgotoActionForm.getUnidadeOrganizacionalId();
			
			
			
			DivisaoEsgoto divisaoEsgoto= new DivisaoEsgoto();
			Collection colecaoPesquisa = null;
			

			// Descricao
			if (!"".equals(inserirDivisaoEsgotoActionForm.getDescricao())) {
				divisaoEsgoto.setDescricao(inserirDivisaoEsgotoActionForm
						.getDescricao());
			} else {
				throw new ActionServletException("atencao.required", null,
						"descrição");
			}
			
			/*
			 * Unidade Organizacional é obrigatório 
			 */
			if (idUnidadeOrganizacional == null || idUnidadeOrganizacional.equalsIgnoreCase("")) {
				throw new ActionServletException("atencao.required", null,  "Unidade Organizacional");
			} else {
				FiltroUnidadeOrganizacional filtroUnidadeOrganizacional= new FiltroUnidadeOrganizacional();
	            
	            filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
	                    FiltroUnidadeOrganizacional.ID, idUnidadeOrganizacional));

				filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
						FiltroUnidadeOrganizacional.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna Unidade Organizacional
				colecaoPesquisa = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class
						.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.distrito_operacional_inexistente");
				} else {
					UnidadeOrganizacional objetoUnidadeOrganizacional = (UnidadeOrganizacional) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					divisaoEsgoto.setUnidadeOrganizacional(objetoUnidadeOrganizacional);
				}
			}
			
			Short iu = 1;
			divisaoEsgoto.setIndicadorUso(iu);
			
			// Ultima alteração
			divisaoEsgoto.setUltimaAlteracao(new Date());

			
			FiltroDivisaoEsgoto filtroDivisaoEsgoto = new FiltroDivisaoEsgoto();

			filtroDivisaoEsgoto.adicionarParametro(new ParametroSimples(
					FiltroDivisaoEsgoto.DESCRICAO, divisaoEsgoto.getDescricao()));

			
			colecaoPesquisa = (Collection) fachada.pesquisar(
					filtroDivisaoEsgoto, DivisaoEsgoto.class.getName());

			if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			
				throw new ActionServletException(
						"atencao.divisao_esgoto_ja_cadastrada", null,
						descricao);
				
			
			} else {
				divisaoEsgoto.setDescricao(descricao);
				

				Integer idDivisaoEsgoto = (Integer) fachada
						.inserir(divisaoEsgoto);

				montarPaginaSucesso(httpServletRequest,
						"Divisão de Esgoto " + descricao
								+ " inserido com sucesso.",
						"Inserir outra Divisão de Esgoto",
						"exibirInserirDivisaoEsgotoAction.do?menu=sim",
						"exibirAtualizarDivisaoEsgotoAction.do?idRegistroAtualizacao="
								+ idDivisaoEsgoto,
						"Atualizar Divisão de Esgoto Inserida");

				sessao.removeAttribute("InserirDivisaoEsgotoActionForm");

				return retorno;
			}

			}
		}

