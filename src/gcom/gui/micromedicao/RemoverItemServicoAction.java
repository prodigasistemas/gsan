package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.FiltroDebitoTipoVigencia;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.FiltroItemServico;
import gcom.micromedicao.ItemServico;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author Rodrigo Cabral
 * @date 04/08/2010
 */



public class RemoverItemServicoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

        //Obtém os ids de remoção
        String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();
         
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        Fachada fachada = Fachada.getInstancia();
        
        HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
        
        // mensagem de erro quando o usuário tenta excluir sem ter selecionado
        // nenhum registro
        if (ids == null || ids.length == 0) {
            throw new ActionServletException("atencao.registros.nao_selecionados");
        }
        
        FiltroItemServico filtroItemServico = 
			new FiltroItemServico();
 
        Collection idsItemServico = new ArrayList(ids.length);
        
        for (int i = 0; i < ids.length; i++) {
			idsItemServico.add(new Integer(ids[i]));
		}
		
		filtroItemServico.adicionarParametro(new ParametroSimplesIn(FiltroDebitoTipoVigencia.ID,idsItemServico));

		Collection coletionItemServico = fachada.pesquisar(filtroItemServico,
				ItemServico.class.getName());
		
		Iterator itera = coletionItemServico.iterator();
		
		while(itera.hasNext()){
			
			ItemServico itemServico = (ItemServico) itera.next();
			
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_REMOVER_ITEM_SERVICO, itemServico.getId(), itemServico.getId(),
				    new UsuarioAcaoUsuarioHelper(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			
			
			registradorOperacao.registrarOperacao(itemServico);
			
			fachada.remover(itemServico);
			
		}

    
        //Verificar Sucesso de Transação
        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest,
            		ids.length + " Item(s) de Contrato removido(s) com sucesso.",
                    "Realizar outra Manutenção de Item de Contrato",
                    "exibirFiltrarItemServicoAction.do?menu=sim");
        }

        return retorno;

	}
}
