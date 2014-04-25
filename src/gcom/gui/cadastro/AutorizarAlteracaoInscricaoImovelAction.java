package gcom.gui.cadastro;

import gcom.cadastro.ImovelInscricaoAlteradaHelper;
import gcom.cadastro.imovel.FiltroImovelInscricaoAlterada;
import gcom.cadastro.imovel.ImovelInscricaoAlterada;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AutorizarAlteracaoInscricaoImovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
			ImovelInscricaoAlteradaHelper helper = (ImovelInscricaoAlteradaHelper) 
				sessao.getAttribute("imovelInscricaoAlterada");
		
			
	        String ids = (String) httpServletRequest.getParameter("indicadorAutorizar");

	        String[] idsQuadrasArray = ids.split(",");

	         //mensagem de erro quando o usuário tenta autorizar sem ter selecionado
	         //nenhum registro
	        if (idsQuadrasArray == null || idsQuadrasArray.length == 0) {
	            throw new ActionServletException("atencao.registros.nao_selecionados");
	        }

	        Collection colecaoImovelInscricaoAlterada = new ArrayList();
	        
	        for(int x=0; x<idsQuadrasArray.length; x++){
	        
	        FiltroImovelInscricaoAlterada filtroImovelInscricaoAlterada = new FiltroImovelInscricaoAlterada();
	        
	        filtroImovelInscricaoAlterada.adicionarParametro(
	        		new ParametroSimples(
	        				FiltroImovelInscricaoAlterada.SETOR_COMERCIAL_ATUAL_CODIGO, helper.getCodigoSetorComercial()));
	        filtroImovelInscricaoAlterada.adicionarParametro(
	        		new ParametroSimples(
	        				FiltroImovelInscricaoAlterada.LOCALIDADE_ATUAL, helper.getIdLocalidade()));
	        filtroImovelInscricaoAlterada.adicionarParametro(
	        		new ParametroSimples(
	        				FiltroImovelInscricaoAlterada.QUADRA_ATUAL, idsQuadrasArray[x]));
	        filtroImovelInscricaoAlterada.adicionarParametro(
	        		new ParametroSimples(
	        				FiltroImovelInscricaoAlterada.INDICADOR_ALTERACAO_EXCLUIDA, ConstantesSistema.NAO));
	        filtroImovelInscricaoAlterada.adicionarParametro(
	        		new ParametroSimples(
	        				FiltroImovelInscricaoAlterada.INDICADOR_ATUALIZADO, ConstantesSistema.NAO));
	        filtroImovelInscricaoAlterada.adicionarParametro(
	        		new ParametroNulo(
	        				FiltroImovelInscricaoAlterada.INDICADOR_ERRO_ALTERACAO));
	        filtroImovelInscricaoAlterada.adicionarParametro(
	        		new ParametroSimples(
	        				FiltroImovelInscricaoAlterada.INDICADOR_AUTORIZADO, ConstantesSistema.NAO));
	
	        
	        colecaoImovelInscricaoAlterada = fachada.pesquisar(filtroImovelInscricaoAlterada, ImovelInscricaoAlterada.class.getName());
	        
	        Iterator iColecaoImovelInscricaoAlterada = colecaoImovelInscricaoAlterada.iterator();
	        
	        if (colecaoImovelInscricaoAlterada != null && !colecaoImovelInscricaoAlterada.isEmpty()){
	        	
	        	while (iColecaoImovelInscricaoAlterada.hasNext()){
	        		
	        	ImovelInscricaoAlterada imovelInscricaoAlterada = (ImovelInscricaoAlterada) iColecaoImovelInscricaoAlterada.next();
	        	
	        	imovelInscricaoAlterada.setIndicadorAutorizado(ConstantesSistema.SIM);
	        	imovelInscricaoAlterada.setUsuarioAutorizacao(usuario);
	        	
	        	//------------ REGISTRAR TRANSAÇÃO ----------------
//	    		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//	    			Operacao.OPERACAO_AUTORIZAR_ALTERACAO_INSCRICAO_IMOVEL,
//	    			ImovelInscricaoAlterada.getId(), ImovelInscricaoAlterada.getId(),
//	    			new UsuarioAcaoUsuarioHelper(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//	    			
//	    		registradorOperacao.registrarOperacao(ImovelInscricaoAlterada);
	    		
	    		//------------ REGISTRAR TRANSAÇÃO ----------------
	        		
	        	fachada.atualizar(imovelInscricaoAlterada);
	        	
	        	}
	        }
	        
	        }
	        
	        
			montarPaginaSucesso(httpServletRequest, "Alteração da inscrição do(s) imovel(s) autorizado(s) com sucesso.",
					"Realizar outra Autorização de Alteração de Inscrição de Imovel(s) com  ",
					"exibirFiltrarAlteracaoInscricaoImovelAction.do?menu=sim");
			
		
		
        
		// devolve o mapeamento de retorno
		return retorno;
	}

		
}
