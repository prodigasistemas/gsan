package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.interceptor.RegistradorOperacao;
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
 * Remover Imóvel Perfil 
 * @author Wallace Thierre
 * Data: 01/10/2010
 *
 */
public class RemoverImovelPerfilAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping, 
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		//Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();		


		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");       


		Fachada fachada = Fachada.getInstancia();

		// mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException("atencao.registros.nao_selecionados");
		}

		FiltroImovelPerfil filtroImovelPerfil = 
			new FiltroImovelPerfil();

		Collection idsImovelPerfil = new ArrayList(ids.length);

		for (int i = 0; i < ids.length; i++) {
			idsImovelPerfil.add(new Integer(ids[i]));
		}

		filtroImovelPerfil.adicionarParametro(new ParametroSimplesIn(FiltroImovelPerfil.ID,idsImovelPerfil));

		Collection coletionImovelPerfil = fachada.pesquisar(filtroImovelPerfil,
				ImovelPerfil.class.getName());

		Iterator itera = coletionImovelPerfil.iterator();

		while(itera.hasNext()){

			ImovelPerfil imovelPerfil = (ImovelPerfil) itera.next();

			// ------------ REGISTRAR TRANSAÇÃO ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_IMOVEL_PERFIL_REMOVER, imovelPerfil.getId(),
					imovelPerfil.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			// ------------ REGISTRAR TRANSAÇÃO ----------------

			registradorOperacao.registrarOperacao(imovelPerfil);

			fachada.remover(imovelPerfil);

		}

		Integer idQt = ids.length;

		montarPaginaSucesso(httpServletRequest, idQt.toString()
				+ " Imovel Perfil removido(s) com sucesso.",
				"Realizar outra Manutenção Imovel Perfil",
		"exibirFiltrarImovelPerfilAction.do?menu=sim");		
		return retorno;
		
	}

}
