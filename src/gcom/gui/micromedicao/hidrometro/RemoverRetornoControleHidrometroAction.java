package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;


import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.hidrometro.FiltroRetornoControleHidrometro;
import gcom.micromedicao.hidrometro.RetornoControleHidrometro;
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
 * @author Wallace Thierre
 * @date 04/08/2010
 */

public class RemoverRetornoControleHidrometroAction extends GcomAction{

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

		FiltroRetornoControleHidrometro filtroRetornoControleHidrometro = 
			new FiltroRetornoControleHidrometro();

		Collection idsRetornoControleHidrometro = new ArrayList(ids.length);

		for (int i = 0; i < ids.length; i++) {
			idsRetornoControleHidrometro.add(new Integer(ids[i]));
		}

		filtroRetornoControleHidrometro.adicionarParametro(new ParametroSimplesIn(FiltroRetornoControleHidrometro.ID,idsRetornoControleHidrometro));

		Collection coletionRetornoControleHidrometro = fachada.pesquisar(filtroRetornoControleHidrometro,
				RetornoControleHidrometro.class.getName());

		Iterator itera = coletionRetornoControleHidrometro.iterator();

		while(itera.hasNext()){

			RetornoControleHidrometro retornoControleHidrometro = (RetornoControleHidrometro) itera.next();

			// ------------ REGISTRAR TRANSAÇÃO ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_RETORNO_CONTROLE_HIDROMETRO_REMOVER, retornoControleHidrometro.getId(),
					retornoControleHidrometro.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			// ------------ REGISTRAR TRANSAÇÃO ----------------

			//debitoTipoVigencia.setDebitoTipo(debitoTipo);

			registradorOperacao.registrarOperacao(retornoControleHidrometro);

			fachada.remover(retornoControleHidrometro);

		}

		Integer idQt = ids.length;

		//fachada.remover(ids, RetornoControleHidrometro.class.getName(),
		//	null, null);

		montarPaginaSucesso(httpServletRequest, idQt.toString()
				+ " Retorno de Controle de Hidrômetro removido(s) com sucesso.",
				"Realizar outra Manutenção Retorno de Controle de Hidrômetro",
		"exibirFiltrarRetornoControleHidrometroAction.do?menu=sim");		
		return retorno;

		/*	
		if(retorno.getName().equalsIgnoreCase("telaSucesso")){

			montarPaginaSucesso(httpServletRequest, 
					ids.length + " Retorno Controle Hidrômetro removido(s) com sucesso.",
					"Realizar outra Manutenção de Retorno Controle Hidrômetro",
			"exibirFiltrarRetornoControleHidrometroAction.do?menu=sim");		
		}

		return retorno;
		 */
	}

}
