package gcom.gui.cadastro.imovel;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroQuadraFace;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Classe responável pelo recebimento dos dados que formarão a inscrição do imóvel 
 *
 * @author Raphael Rossiter
 * @date 12/05/2009
 */
public class ExibirAtualizarImovelLocalidadeAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarImovelLocalidade");

		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm atualizarImovelLocalidadeActionForm = (DynaValidatorForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		sessao.removeAttribute("gis");
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		((DynaValidatorForm) actionForm).set("usaRota", sistemaParametro.getIndicadorUsaRota().toString()); 
		if (sistemaParametro.getIndicadorUsaRota().toString().equals("1") ) {
			sessao.setAttribute("usaRota", sistemaParametro.getIndicadorUsaRota().toString());	
		} else {
			sessao.setAttribute("usaRota", null);
		}
		
		String idLocalidade = null;
		String idSetorComercial = null;
		String idQuadra = null;
		String idQuadraFace = null;
		
		// LOCALIDADE
		if (atualizarImovelLocalidadeActionForm.get("idLocalidade") != null && 
			!atualizarImovelLocalidadeActionForm.get("idLocalidade").toString().trim().equalsIgnoreCase("")) {
			
			idLocalidade = (String) atualizarImovelLocalidadeActionForm.get("idLocalidade");
		}
		
		//SETOR COMERCIAL
		if (atualizarImovelLocalidadeActionForm.get("idSetorComercial") != null && 
			!atualizarImovelLocalidadeActionForm.get("idSetorComercial").toString().trim().equalsIgnoreCase("")) {
			
			idSetorComercial = (String) atualizarImovelLocalidadeActionForm.get("idSetorComercial");
		}
		
		//QUADRA
		if (atualizarImovelLocalidadeActionForm.get("idQuadra") != null && 
			!atualizarImovelLocalidadeActionForm.get("idQuadra").toString().trim().equalsIgnoreCase("")) {
			
			idQuadra = (String) atualizarImovelLocalidadeActionForm.get("idQuadra");
		}
		
		/*
		 * Caso a empresa utilize o conceito de face da quadra (PARM_ICQUADRAFACE = 1 da tabela SISTEMA_PARAMETROS) 
		 * O campo face da quadra ficará disponível para atualização;
		 * 
		 * QUADRA_FACE
		 */
        if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.SIM) && 
        	atualizarImovelLocalidadeActionForm.get("idQuadraFace") != null && 
    		!atualizarImovelLocalidadeActionForm.get("idQuadraFace").toString().trim().equalsIgnoreCase("")){
        	
        	idQuadraFace = (String) atualizarImovelLocalidadeActionForm.get("idQuadraFace");
        }

		//FILTROS E COLEÇÕES
        FiltroQuadra filtroQuadra = new FiltroQuadra();
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		FiltroQuadraFace filtroQuadraFace = new FiltroQuadraFace();

		Collection localidades = new HashSet();
		Collection setorComerciais = new HashSet();
		Collection quadras = new HashSet();
		Collection facesQuadra = new HashSet();

		//PESQUISANDO LOCALIDADE...
		if (idLocalidade != null && !idLocalidade.toString().trim().equalsIgnoreCase("")) {
			
			filtroLocalidade.limparListaParametros();
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
			FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
			FiltroLocalidade.ID, new Integer(idLocalidade)));
			
			localidades = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (localidades != null && !localidades.isEmpty()) {
				
				// LOCALIDADE ENCONTRADA
				atualizarImovelLocalidadeActionForm.set("idLocalidade", Util
				.adicionarZerosEsquedaNumero(3, new Integer(((Localidade) ((List) localidades).get(0))
				.getId().toString()).toString()));
				
				atualizarImovelLocalidadeActionForm.set("localidadeDescricao",
				((Localidade) ((List) localidades).get(0)).getDescricao());

				httpServletRequest.setAttribute("nomeCampo", "idSetorComercial");

			} 
			else {
				
				httpServletRequest.setAttribute("codigoLocalidadeNaoEncontrada", "true");
				
				atualizarImovelLocalidadeActionForm.set("localidadeDescricao","LOCALIDADE INEXISTENTE");
				atualizarImovelLocalidadeActionForm.set("idLocalidade", "");
				atualizarImovelLocalidadeActionForm.set("idSetorComercial", "");
				atualizarImovelLocalidadeActionForm.set("setorComercialDescricao", "");
				atualizarImovelLocalidadeActionForm.set("idQuadra", "");

				httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
			}
		}

		//PESQUISANDO SETOR COMERCIAL...
		if (idSetorComercial != null && !idSetorComercial.toString().trim().equalsIgnoreCase("")) {
			
			if (idLocalidade != null && !idLocalidade.toString().trim().equalsIgnoreCase("")) {
				
				filtroSetorComercial.limparListaParametros();
				
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.ID_LOCALIDADE, new Integer(idLocalidade)));
				
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(idSetorComercial)));
				
				setorComerciais = fachada.pesquisar(filtroSetorComercial,SetorComercial.class.getName());
				
				if (setorComerciais != null && !setorComerciais.isEmpty()) {
					
					// SETOR COMERCIAL ENCONTRADO
					atualizarImovelLocalidadeActionForm.set("idSetorComercial",""
					+ Util.adicionarZerosEsquedaNumero(3, new Integer(
					((SetorComercial) ((List) setorComerciais).get(0)).getCodigo()).toString()));
					
					atualizarImovelLocalidadeActionForm.set("setorComercialDescricao",
					((SetorComercial) ((List) setorComerciais).get(0)).getDescricao());

					httpServletRequest.setAttribute("nomeCampo", "idQuadra");
					
				} 
				else {
					
					atualizarImovelLocalidadeActionForm.set("idSetorComercial","");
					atualizarImovelLocalidadeActionForm.set("setorComercialDescricao", "SETOR COMERCIAL INEXISTENTE");
					atualizarImovelLocalidadeActionForm.set("idQuadra", "");
					
					httpServletRequest.setAttribute("codigoSetorComercialNaoEncontrada", "true");
					httpServletRequest.setAttribute("nomeCampo","idSetorComercial");
				}
			}
		} 
		else {
			
			atualizarImovelLocalidadeActionForm.set("idSetorComercial", "");
			atualizarImovelLocalidadeActionForm.set("setorComercialDescricao", "");
		}

		//PESQUISANDO QUADRA...
		if (idQuadra != null && !idQuadra.toString().trim().equalsIgnoreCase("")) {
			
			if (idSetorComercial != null && !idSetorComercial.toString().trim().equalsIgnoreCase("")) {
				
				filtroQuadra.limparListaParametros();

				filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");

				filtroQuadra.adicionarParametro(new ParametroSimples(
				FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidade)));

				filtroQuadra.adicionarParametro(new ParametroSimples(
				FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				filtroQuadra.adicionarParametro(new ParametroSimples(
				FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(idSetorComercial)));
				
				filtroQuadra.adicionarParametro(new ParametroSimples(
				FiltroQuadra.NUMERO_QUADRA, new Integer(idQuadra)));
				
				quadras = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
				
				if (quadras != null && !quadras.isEmpty()) {
					
					// QUADRA ENCONTRADA
					Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(quadras);

					atualizarImovelLocalidadeActionForm.set("idQuadra", ""
					+ Util.adicionarZerosEsquedaNumero(3, "" + quadra.getNumeroQuadra()));
					
					sessao.setAttribute("idQuadraInicial", quadra.getId());
					
					sessao.setAttribute("quadraCaracteristicas", quadras.iterator().next());
					
					//ROTA QUE ESTÁ ASSOCIADA COM A QUADRA
					String msg = "Rota:" + quadra.getRota().getCodigo().toString();

					httpServletRequest.setAttribute("msgQuadra", msg);

					//CARREGANDO AS FACES ASSOCIADAS A QUADRA SELECIONADA
					if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.SIM)){
						
						filtroQuadraFace.limparListaParametros();

						filtroQuadraFace.adicionarParametro(new ParametroSimples(
						FiltroQuadraFace.ID_QUADRA, quadra.getId()));
						
						facesQuadra = fachada.pesquisar(filtroQuadraFace, QuadraFace.class.getName());
						
						if (facesQuadra != null && !facesQuadra.isEmpty()) {
							
							if (idQuadraFace != null && !idQuadraFace.toString().trim().equalsIgnoreCase("")) {
								
								atualizarImovelLocalidadeActionForm.set("idQuadraFace", idQuadraFace);
							}
							
							//FACE(S) DA QUADRA ENCONTRADA(S)
							httpServletRequest.setAttribute("facesQuadra", facesQuadra);
						}
						else{
							
							//FACE(S) DA QUADRA NÃO ENCONTRADA(S)
							httpServletRequest.setAttribute("msgQuadraFace","Face(s) da Quadra inexistente(s)");
						}
						
						httpServletRequest.setAttribute("nomeCampo", "idQuadraFace");
					}
					else{
						
						httpServletRequest.setAttribute("nomeCampo", "lote");
					}
				} 
				else {
					
					atualizarImovelLocalidadeActionForm.set("idQuadra", "");
					atualizarImovelLocalidadeActionForm.set("idQuadraFace", "");
					
					httpServletRequest.setAttribute("codigoQuadraNaoEncontrada", "true");
					httpServletRequest.setAttribute("msgQuadra", "QUADRA INEXISTENTE");
					httpServletRequest.setAttribute("nomeCampo", "idQuadra");
				}
			}
		} 
		else {
			
			atualizarImovelLocalidadeActionForm.set("idQuadra", "");
			atualizarImovelLocalidadeActionForm.set("idQuadraFace", "");
		}
		
		//VERIFICAÇÃO PARA DEFINIR SE O CAMPO DE FACE DA QUADRA FICARÁ DISPONÍVEL
		if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.SIM)){
			httpServletRequest.setAttribute("exibirQuadraFace", "SIM");
		}

		return retorno;
	}
}
