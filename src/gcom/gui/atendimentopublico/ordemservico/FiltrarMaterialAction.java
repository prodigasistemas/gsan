package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroMaterial;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * [UC0382]FILTRAR MATERIAL
 * 
 * @author Kássia Albuquerque
 * @date 14/11/2006
 */



public class FiltrarMaterialAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterMaterial");
		
		
		//		 Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarMaterialActionForm form = (FiltrarMaterialActionForm) actionForm;
		
		// Recupera todos os campos da página para ser colocada no filtro
		// posteriormente
	
		String descricaoMaterial = form.getDescricaoMaterial();
		String abrevMaterial = form.getAbrevMaterial();
		String unidadeMaterial = form.getUnidadeMaterial();
		String indicadorUso = form.getIndicadorUso();
		
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		
		
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("indicadorAtualizar");
		}

		boolean peloMenosUmParametroInformado = false;

		FiltroMaterial filtroMaterial = new FiltroMaterial(FiltroMaterial.DESCRICAO);
		
		filtroMaterial.adicionarCaminhoParaCarregamentoEntidade("materialUnidade");
		
		//		 Descrição Material
		if (descricaoMaterial != null && !descricaoMaterial.trim().equals("")){

			peloMenosUmParametroInformado = true;
			filtroMaterial.adicionarParametro(new ComparacaoTexto(FiltroMaterial.DESCRICAO, descricaoMaterial));

		}

		//   Descrição Abreviada
		if (abrevMaterial != null && !abrevMaterial.trim().equals("")) {

			peloMenosUmParametroInformado = true;
			filtroMaterial.adicionarParametro(new ComparacaoTexto(FiltroMaterial.DESCRICAO_ABREVIADA,abrevMaterial));

		}
		
		//	   Unidade Material
		if (unidadeMaterial != null && !unidadeMaterial.trim().equals("")&& !unidadeMaterial.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
		
			peloMenosUmParametroInformado = true;
			filtroMaterial.adicionarParametro(new ParametroSimples(FiltroMaterial.MATERIAL_UNIDADE_ID,unidadeMaterial));
		
		}


		// Indicador de Uso
		if (indicadorUso != null && !indicadorUso.equals(FiltrarTipoPerfilServicoAction.INDICADOR_TODOS)) {

			peloMenosUmParametroInformado = true;
			filtroMaterial.adicionarParametro(new ParametroSimples(FiltroMaterial.INDICADOR_USO, indicadorUso));

		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		// Manda o filtro pela sessao para o
		// ExibirManterMaterialAction
		sessao.setAttribute("filtroMaterial", filtroMaterial);


		
		return retorno;
	}
}
	
