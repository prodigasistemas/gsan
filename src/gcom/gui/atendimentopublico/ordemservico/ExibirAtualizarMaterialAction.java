package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroMaterial;
import gcom.atendimentopublico.ordemservico.FiltroMaterialUnidade;
import gcom.atendimentopublico.ordemservico.Material;
import gcom.atendimentopublico.ordemservico.MaterialUnidade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
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
 * [SB0001]Atualizar Material
 *
 * @author Kássia Albuquerque
 * @date 20/11/2006
 */

public class ExibirAtualizarMaterialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarMaterial");
		AtualizarMaterialActionForm atualizarMaterialActionForm = (AtualizarMaterialActionForm)actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();

		FiltroMaterialUnidade filtroMaterialUnidade = new FiltroMaterialUnidade();
		Collection<MaterialUnidade> colecaoMaterialUnidade = fachada.pesquisar(filtroMaterialUnidade, MaterialUnidade.class.getName());
		httpServletRequest.setAttribute("colecaoMaterialUnidade", colecaoMaterialUnidade);

		Material material = null;
		String idMaterial = null;

		if (httpServletRequest.getParameter("idMaterial") != null) {
			//tela do manter
			idMaterial = (String) httpServletRequest.getParameter("idMaterial");
			sessao.setAttribute("idMaterial", idMaterial);
		} else if (sessao.getAttribute("idMaterial") != null) {
			//tela do filtrar
			idMaterial = (String) sessao.getAttribute("idMaterial");
		}else if(httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null){
			//link na tela de sucesso do inserir material
			idMaterial = (String)httpServletRequest.getParameter("idRegistroInseridoAtualizar");
			sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarMaterialAction.do?menu=sim");
		}

		if (idMaterial == null){
			if (httpServletRequest.getAttribute("idRegistroAtualizar") == null){
				material = (Material) sessao.getAttribute("materialAtualizar");
			}else{
				idMaterial = (String) httpServletRequest.getAttribute("idRegistroAtualizar").toString();
			}
		}

		//------Inicio da parte que verifica se vem da página de
		// 		material_manter.jsp
		if (material == null){

			if (idMaterial != null && !idMaterial.equals("")) {
				FiltroMaterial filtroMaterial = new FiltroMaterial();
				filtroMaterial.adicionarCaminhoParaCarregamentoEntidade("materialUnidade");
				filtroMaterial.adicionarParametro(new ParametroSimples(FiltroMaterialUnidade.ID, idMaterial));
				Collection<Material> colecaoMaterial = fachada.pesquisar(filtroMaterial, Material.class.getName());

				if (colecaoMaterial != null && !colecaoMaterial.isEmpty()) {
					material = (Material) Util.retonarObjetoDeColecao(colecaoMaterial);
				}
			}
		}

		//  ------  O material foi encontrado
		atualizarMaterialActionForm.setId(String.valueOf(material.getId()));
		if(material.getCodigo() == null){
			atualizarMaterialActionForm.setCodigo("");
		}else{
			atualizarMaterialActionForm.setCodigo(String.valueOf(material.getCodigo()));
		}
		atualizarMaterialActionForm.setDescricaoMaterial(material.getDescricao());
		atualizarMaterialActionForm.setAbrevMaterial(material.getDescricaoAbreviada());
		atualizarMaterialActionForm.setUnidadeMaterial(material.getMaterialUnidade().getId().toString());
		atualizarMaterialActionForm.setIndicadorUso(String.valueOf(material.getIndicadorUso()));
		sessao.setAttribute("material", material);
		// ------ Fim da parte que verifica se vem da página de material_manter.jsp

		return retorno;
	}
}
