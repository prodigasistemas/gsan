package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.bean.InserirQuadraHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/***
 * @author Administrador, Ivan Sergio
 * @date 16/02/2009
 * @alteracao 16/02/2009 - CRC1178 - Adicionado o Indicador de Incremento do Lote
 */
public class AtualizarQuadraAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        AtualizarQuadraActionForm atualizarQuadraActionForm = (AtualizarQuadraActionForm) actionForm;
        
        Quadra quadraParaManter = (Quadra) sessao.getAttribute("quadraManter");
        
        //CARREGANDO O OBJETO INSERIR_QUADRA_HELPER
		InserirQuadraHelper helper = this.carregarInserirQuadraHelper(atualizarQuadraActionForm);
		
        //VALIDANDO OS DADOS DA QUADRA
		Quadra quadraAtualizar = fachada.validarQuadra(helper);
		quadraAtualizar.setId(quadraParaManter.getId());
		quadraAtualizar.setUltimaAlteracao(quadraParaManter.getUltimaAlteracao());
		quadraAtualizar.setIndicadorBloqueio(new Short(atualizarQuadraActionForm.getIndicadorBloqueio()));
		
		//OBTENDO AS FACES DA QUADRA
		Collection colecaoQuadraFace = (Collection) sessao.getAttribute("colecaoQuadraFace");

        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        if(quadraAtualizar.getRota() != null){
        	if(quadraAtualizar.getRota().getIndicadorRotaAlternativa().shortValue() == 2){
            	fachada.atualizarQuadra(quadraAtualizar, usuarioLogado, colecaoQuadraFace);	
            }else{
            	throw new ActionServletException(
				"atencao.rota_alternativa_nao_pode_associar_quadra");
            }
        }

        montarPaginaSucesso(httpServletRequest,
                "Quadra de número  " + helper.getQuadraNM() +
                " do setor comercial " + helper.getSetorComercialCD() + 
                "-" + quadraAtualizar.getSetorComercial().getDescricao() +
                " da localidade " + helper.getLocalidadeID() + 
                "-" + quadraAtualizar.getSetorComercial().getLocalidade().getDescricao() +
                " atualizada com sucesso.",
                "Realizar outra Manutenção de Quadra", "exibirFiltrarQuadraAction.do?desfazer=S");
        
        sessao.removeAttribute("quadraManter");
        sessao.removeAttribute("colecaoPerfilQuadra");
        sessao.removeAttribute("colecaoSistemaEsgoto");
        sessao.removeAttribute("colecaoZeis");
        sessao.removeAttribute("colecaoBacia");
        sessao.removeAttribute("colecaoQuadraFace");

        return retorno;
    }
    
    private InserirQuadraHelper carregarInserirQuadraHelper(AtualizarQuadraActionForm atualizarQuadraActionForm){
		
		InserirQuadraHelper helper = new InserirQuadraHelper();
		
		helper.setQuadraId(atualizarQuadraActionForm.getQuadraID());
		helper.setIndicadorUso(atualizarQuadraActionForm.getIndicadorUso());
		
		helper.setLocalidadeID(atualizarQuadraActionForm.getLocalidadeID());
		helper.setSetorComercialCD(atualizarQuadraActionForm.getSetorComercialCD());
		helper.setQuadraNM(atualizarQuadraActionForm.getQuadraNM());
		helper.setPerfilQuadraID(atualizarQuadraActionForm.getPerfilQuadra());
		helper.setAreaTipoID(atualizarQuadraActionForm.getAreaTipoID());
		helper.setIndicadorRedeAgua(atualizarQuadraActionForm.getIndicadorRedeAguaAux());
		helper.setIndicadorRedeEsgoto(atualizarQuadraActionForm.getIndicadorRedeEsgotoAux());
		helper.setSistemaEsgotoID(atualizarQuadraActionForm.getSistemaEsgotoID());
		helper.setBaciaID(atualizarQuadraActionForm.getBaciaID());
		helper.setDistritoOperacionalID(atualizarQuadraActionForm.getDistritoOperacionalID());
		helper.setSetorCensitarioID(atualizarQuadraActionForm.getSetorCensitarioID());
		helper.setZeisID(atualizarQuadraActionForm.getZeisID());
		helper.setRotaCD(atualizarQuadraActionForm.getCodigoRota());
		/*
         * Mantis 536 - Felipe Santos - 08/03/2012
         * 
         * Adição do id da rota no helper para pesquisa
         */
		helper.setRotaID(atualizarQuadraActionForm.getRotaID());
		// fim da alteração
		helper.setIndicadorIncrementoLote(atualizarQuadraActionForm.getIndicadorIncrementoLote());
		
		helper.setBairroCD(atualizarQuadraActionForm.getBairroID());
		helper.setMunicipioID(atualizarQuadraActionForm.getMunicipioID());
		
		return helper;
	}

}
