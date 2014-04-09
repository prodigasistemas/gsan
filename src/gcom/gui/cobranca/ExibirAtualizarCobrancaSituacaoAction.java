package gcom.gui.cobranca;


import gcom.cadastro.cliente.FiltroProfissao;
import gcom.cadastro.cliente.FiltroRamoAtividade;
import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.FiltroContaMotivoRevisao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
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
 * 
 * @author Arthur Carvalho
 * @date 05/09/2008
 */
public class ExibirAtualizarCobrancaSituacaoAction extends GcomAction {

	/**
	 * Método responsavel por responder a requisicao
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("cobrancaSituacaoAtualizar");

		AtualizarCobrancaSituacaoActionForm atualizarCobrancaSituacaoActionForm = (AtualizarCobrancaSituacaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();		

		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoPesquisa = null;
		
		String id = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null){
			id = httpServletRequest.getParameter("idRegistroAtualizacao");
		}
		else{
			id = ((CobrancaSituacao) sessao.getAttribute("cobrancaSituacao")).getId().toString();
		}
		

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}

		CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
		
		
		if (id != null && !id.trim().equals("")) {

			FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
			filtroCobrancaSituacao.adicionarParametro(
				new ParametroSimples(FiltroCobrancaSituacao.ID, id));
			
			filtroCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("contaMotivoRevisao");
			
			Collection colecaoCobrancaSituacao = fachada.pesquisar(
					filtroCobrancaSituacao, CobrancaSituacao.class.getName());
			
			if (colecaoCobrancaSituacao != null && !colecaoCobrancaSituacao.isEmpty()) {
				cobrancaSituacao = (CobrancaSituacao) Util.retonarObjetoDeColecao(colecaoCobrancaSituacao);
			}

			
			if (id != null && !id.trim().equals("")) {

				atualizarCobrancaSituacaoActionForm
					.setCodigo(cobrancaSituacao
						.getId().toString());

				atualizarCobrancaSituacaoActionForm
					.setDescricao(cobrancaSituacao
						.getDescricao());
				
				if (cobrancaSituacao.getContaMotivoRevisao() != null){
					
					atualizarCobrancaSituacaoActionForm
					.setContaMotivoRevisao(cobrancaSituacao
							.getContaMotivoRevisao().getId().toString());
				} 
				
				if (cobrancaSituacao.getProfissao() != null){
					
					atualizarCobrancaSituacaoActionForm
					.setProfissao(cobrancaSituacao
							.getProfissao().getId().toString());
				} 
				
				if (cobrancaSituacao.getRamoAtividade() != null){
					
					atualizarCobrancaSituacaoActionForm
					.setRamoAtividade(cobrancaSituacao
							.getRamoAtividade().getId().toString());
				} 
				
				atualizarCobrancaSituacaoActionForm
					.setIndicadorExigenciaAdvogado(cobrancaSituacao
							.getIndicadorExigenciaAdvogado());
				
				atualizarCobrancaSituacaoActionForm
					.setIndicadorUso(cobrancaSituacao
						.getIndicadorUso());
				
				if (cobrancaSituacao.getIndicadorBloqueioParcelamento() != null){
				
					atualizarCobrancaSituacaoActionForm
						.setIndicadorBloqueioParcelamento(cobrancaSituacao
							.getIndicadorBloqueioParcelamento());
				}
				if (cobrancaSituacao.getIndicadorBloqueioRetirada() != null){
					
					atualizarCobrancaSituacaoActionForm
						.setIndicadorBloqueioRetirada(cobrancaSituacao
							.getIndicadorBloqueioRetirada());
				}
				
				if (cobrancaSituacao.getIndicadorBloqueioInclusao() != null){
					
					atualizarCobrancaSituacaoActionForm
						.setIndicadorBloqueioInclusao(cobrancaSituacao
							.getIndicadorBloqueioInclusao());
				}
				if(cobrancaSituacao.getIndicadorSelecaoApenasComPermissao() != null){
					atualizarCobrancaSituacaoActionForm
						.setIndicadorSelecaoApenasComPermissao(
								cobrancaSituacao.getIndicadorSelecaoApenasComPermissao());
				}
				
				if(cobrancaSituacao.getIndicadorPrescricaoImoveisParticulares() != null){
					atualizarCobrancaSituacaoActionForm.setIndicadorPrescricaoImoveisParticulares(
							cobrancaSituacao.getIndicadorPrescricaoImoveisParticulares());
					
				}
				
				if(cobrancaSituacao.getIndicadorNaoIncluirImoveisEmCobrancaResultado() != null){
					atualizarCobrancaSituacaoActionForm.setIndicadorNaoIncluirImoveisEmCobrancaResultado(
							cobrancaSituacao.getIndicadorNaoIncluirImoveisEmCobrancaResultado());
					
				}
				//Motivo de revisao da conta
					FiltroContaMotivoRevisao filtroContaMotivoRevisao = new FiltroContaMotivoRevisao();
		        
		        	filtroContaMotivoRevisao.setCampoOrderBy(FiltroContaMotivoRevisao.ID);
		        
		        	filtroContaMotivoRevisao.adicionarParametro(new ParametroSimples(
		        			FiltroContaMotivoRevisao.INDICADOR_USO,
		        			ConstantesSistema.INDICADOR_USO_ATIVO));
				
		        	//Retorna motivo de revisao da conta
		        	colecaoPesquisa = this.getFachada().pesquisar(filtroContaMotivoRevisao,
		        			ContaMotivoRevisao.class.getName());
		        
	        	if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		            	//Nenhum registro na tabela foi encontrado
		            	throw new ActionServletException(
		                    	"atencao.pesquisa.nenhum_registro_tabela", null,
		            			"Situação de Cobrança");
		        	} else {
		            	httpServletRequest.setAttribute("colecaoContaMotivoRevisao", colecaoPesquisa);
		        	}
				
		        
				}
			
			//Profissao
	        FiltroProfissao filtroProfissao = new FiltroProfissao();
	        
	        filtroProfissao.setCampoOrderBy(FiltroProfissao.ID);
	        
	        filtroProfissao.adicionarParametro(new ParametroSimples(
	        		FiltroProfissao.INDICADOR_USO,
	        		ConstantesSistema.INDICADOR_USO_ATIVO));
	       
	        Collection colecaoProfissao = null;
	        //Retorna Profissao
	        colecaoProfissao = this.getFachada().pesquisar(filtroProfissao,
	        		Profissao.class.getName());
	        
	        if (colecaoProfissao == null || colecaoProfissao.isEmpty()) {
	            //Nenhum registro na tabela foi encontrado
	            throw new ActionServletException(
	                    "atencao.pesquisa.nenhum_registro_tabela", null,
	                    "Profissao");
	        } else {
	            httpServletRequest.setAttribute("colecaoProfissao", colecaoProfissao);
	        }
	        
	        //Ramo Atividade
	        FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade();
	        
	        filtroRamoAtividade.setCampoOrderBy(FiltroRamoAtividade.ID);
	        
	        filtroRamoAtividade.adicionarParametro(new ParametroSimples(
	        		FiltroRamoAtividade.INDICADOR_USO,
	        		ConstantesSistema.INDICADOR_USO_ATIVO));
	       
	        Collection colecaoRamoAtividade = null;
	        //Retorna Ramo de Atividade
	        colecaoRamoAtividade = this.getFachada().pesquisar(filtroRamoAtividade,
	        		RamoAtividade.class.getName());
	        
	        if (colecaoRamoAtividade == null || colecaoRamoAtividade.isEmpty()) {
	            //Nenhum registro na tabela foi encontrado
	            throw new ActionServletException(
	                    "atencao.pesquisa.nenhum_registro_tabela", null,
	                    "Ramo Atvidade");
	        } else {
	            httpServletRequest.setAttribute("colecaoRamoAtividade", colecaoRamoAtividade);
	        }
			
			sessao.setAttribute("atualizarCobrancaSituacao", cobrancaSituacao);

			if (sessao.getAttribute("colecaoCobrancaSituacao") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarCobrancaSituacaoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarCobrancaSituacaoAction.do");
			}

		}
		

		return retorno;
	}
	
	
}
