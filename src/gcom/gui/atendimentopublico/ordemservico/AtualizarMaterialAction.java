package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Material;
import gcom.atendimentopublico.ordemservico.MaterialUnidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * [UC0383] MANTER TIPO MATERIAL
 * [SB0001] Atualizar Material
 *
 * @author Kássia Albuquerque
 * @date 17/11/2006
 */
public class AtualizarMaterialAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, 
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		AtualizarMaterialActionForm atualizarMaterialActionForm = (AtualizarMaterialActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		if(validarCampos(atualizarMaterialActionForm)){
			Material material = new Material();
			material.setId(Integer.valueOf(atualizarMaterialActionForm.getId()));
			
			if(Util.verificarIdNaoVazio(atualizarMaterialActionForm.getCodigo())){
				try{
					material.setCodigo(Integer.valueOf(atualizarMaterialActionForm.getCodigo()));
				}catch (NumberFormatException e) {
					throw new ActionServletException("atencao.codigo_formato_invalido");
				}
			}
			
			material.setId(new Integer(atualizarMaterialActionForm.getId()));
			material.setDescricao(atualizarMaterialActionForm.getDescricaoMaterial());
			material.setDescricaoAbreviada(atualizarMaterialActionForm.getAbrevMaterial());
			MaterialUnidade materialUnidade = new MaterialUnidade();
			materialUnidade.setId(Integer.valueOf(atualizarMaterialActionForm.getUnidadeMaterial()));
			material.setMaterialUnidade(materialUnidade);
			material.setIndicadorUso(new Short(atualizarMaterialActionForm.getIndicadorUso()));
			
			//atualiza na base de dados Material
			fachada.atualizarMaterial(material, usuarioLogado);
		}else{
			throw new ActionServletException("atencao.parametros.obrigatorios.nao.selecionados");
		}
		
		
		//[FS0004]Verificar Sucesso da Atualização
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Material "+ atualizarMaterialActionForm.getDescricaoMaterial() +
				" atualizado com sucesso.", "Realizar outra Manutenção do Material",
				"exibirFiltrarMaterialAction.do?menu=sim");
		
		return retorno;
	}
	
	//[FS0003] Verificar Preenchimento dos campos
	private boolean validarCampos(AtualizarMaterialActionForm form){
		boolean valido = false;
		
		if(Util.verificarIdNaoVazio(form.getId()) && Util.verificarNaoVazio(form.getDescricaoMaterial()) && 
				Util.verificarNaoVazio(form.getIndicadorUso()) && Util.verificarIdNaoVazio(form.getUnidadeMaterial())){
			valido = true;
		}
		return valido;
	}	
}	      
    



