package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.bean.InserirQuadraHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe responsável pela inserção dos dados de uma quadra 
 *
 * @author Ivan Sérgio, Raphael Rossiter
 * @date 10/02/2009, 06/04/2009
 */
public class InserirQuadraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		InserirQuadraActionForm inserirQuadraActionForm = (InserirQuadraActionForm) actionForm;
		
		
		//Verificar a existencia de Setor alternativo
		String setorComercialId = inserirQuadraActionForm.getSetorComercialID();
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
    	filtroSetorComercial.adicionarParametro( new ParametroSimples ( FiltroSetorComercial.ID,
    			setorComercialId ) );
    	
    	Collection setorComercial = this.getFachada()
    			.pesquisar( filtroSetorComercial, SetorComercial.class.getName() );

    	Iterator iteratorSetorComercial = setorComercial.iterator();
    	SetorComercial setor = null;
    
    	while ( iteratorSetorComercial.hasNext() ) {
    	
    		setor = (SetorComercial) iteratorSetorComercial.next();
            
    		if ( setor.getIndicadorSetorAlternativo().equals( ConstantesSistema.INDICADOR_USO_ATIVO ) ) {
    			
    			throw new ActionServletException("atencao.setor_comercial_alternativo");
    		}
    	}
		//CARREGANDO O OBJETO INSERIR_QUADRA_HELPER
		InserirQuadraHelper helper = this.carregarInserirQuadraHelper(inserirQuadraActionForm);
		
		//VALIDANDO OS DADOS DA QUADRA
		Quadra quadraInserir = fachada.validarQuadra(helper);
		
		//OBTENDO AS FACES DA QUADRA
		Collection colecaoQuadraFace = (Collection) sessao.getAttribute("colecaoQuadraFace");

		Integer idQuadra = null;
		
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		idQuadra = fachada.inserirQuadra(quadraInserir, colecaoQuadraFace, usuarioLogado);

		montarPaginaSucesso(httpServletRequest, "Quadra de número "
				+ quadraInserir.getNumeroQuadra() + " do setor comercial "
				+ helper.getSetorComercialCD() + "-" + quadraInserir.getSetorComercial().getDescricao()
				+ " da localidade " + helper.getLocalidadeID() + "-"
				+ quadraInserir.getSetorComercial().getLocalidade().getDescricao() + " inserida com sucesso.",
				"Inserir outra Quadra", "exibirInserirQuadraAction.do",
				"exibirAtualizarQuadraAction.do?idRegistroInseridoAtualizar="
				+ idQuadra, "Atualizar Quadra Inserida");

		sessao.removeAttribute("InserirQuadraActionForm");
		sessao.removeAttribute("colecaoPerfilQuadra");
		sessao.removeAttribute("colecaoSistemaEsgoto");
		sessao.removeAttribute("colecaoZeis");
		sessao.removeAttribute("colecaoBacia");
		sessao.removeAttribute("colecaoQuadraFace");

		// devolve o mapeamento de retorno
		return retorno;
	}
	
	
	private InserirQuadraHelper carregarInserirQuadraHelper(InserirQuadraActionForm inserirQuadraActionForm){
		
		InserirQuadraHelper helper = new InserirQuadraHelper();
		
		helper.setLocalidadeID(inserirQuadraActionForm.getLocalidadeID());
		helper.setSetorComercialCD(inserirQuadraActionForm.getSetorComercialCD());
		helper.setQuadraNM(inserirQuadraActionForm.getQuadraNM());
		helper.setPerfilQuadraID(inserirQuadraActionForm.getPerfilQuadra());
		helper.setAreaTipoID(inserirQuadraActionForm.getAreaTipoID());
		helper.setIndicadorRedeAgua(inserirQuadraActionForm.getIndicadorRedeAguaAux());
		helper.setIndicadorRedeEsgoto(inserirQuadraActionForm.getIndicadorRedeEsgotoAux());
		helper.setSistemaEsgotoID(inserirQuadraActionForm.getSistemaEsgotoID());
		helper.setBaciaID(inserirQuadraActionForm.getBaciaID());
		helper.setDistritoOperacionalID(inserirQuadraActionForm.getDistritoOperacionalID());
		helper.setSetorCensitarioID(inserirQuadraActionForm.getSetorCensitarioID());
		helper.setZeisID(inserirQuadraActionForm.getZeisID());
		helper.setRotaCD(inserirQuadraActionForm.getCodigoRota());
		/*
         * TODO - COSANPA - Mantis 536 - Felipe Santos - 14/03/2012
         * 
         * Adição do id da rota no helper para pesquisa
         */
		helper.setRotaID(inserirQuadraActionForm.getRotaID());
		// fim da alteração
		helper.setIndicadorIncrementoLote(inserirQuadraActionForm.getIndicadorIncrementoLote());
		helper.setBairroCD(inserirQuadraActionForm.getBairroID());
		helper.setMunicipioID(inserirQuadraActionForm.getMunicipioID());
		
		return helper;
	}

}
