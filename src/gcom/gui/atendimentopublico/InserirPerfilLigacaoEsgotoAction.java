package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Arthur Carvalho
 * @date 16/10/2008
 */
public class InserirPerfilLigacaoEsgotoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um Perfil da ligação de esgoto
	 * 
	 * [UC0861] Inserir Perfil da ligação Esgoto
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirPerfilLigacaoEsgotoActionForm inserirPerfilLigacaoEsgotoActionForm = (InserirPerfilLigacaoEsgotoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		Fachada fachada = Fachada.getInstancia();

		String descricao = inserirPerfilLigacaoEsgotoActionForm.getDescricao();

		LigacaoEsgotoPerfil ligacaoEsgotoPerfil = new LigacaoEsgotoPerfil();

		// Nome
		if (!"".equals(inserirPerfilLigacaoEsgotoActionForm.getDescricao())) {
			ligacaoEsgotoPerfil.setDescricao(inserirPerfilLigacaoEsgotoActionForm.getDescricao());
		}
		// Percentual de Esgoto
		if (!"".equals(inserirPerfilLigacaoEsgotoActionForm.getPercentualEsgotoConsumidaColetada())) {
			
			BigDecimal percentualEsgotoConsumidaColetada = null;

			String percentual = inserirPerfilLigacaoEsgotoActionForm
					.getPercentualEsgotoConsumidaColetada().toString().replace(".", "");

			percentual = percentual.replace(",", ".");
			
			percentualEsgotoConsumidaColetada = new BigDecimal(percentual);

			ligacaoEsgotoPerfil.setPercentualEsgotoConsumidaColetada(percentualEsgotoConsumidaColetada);
		}
		
		// Indicador de Uso
		Short iu = ConstantesSistema.INDICADOR_USO_ATIVO;

		ligacaoEsgotoPerfil.setIndicadorUso(iu);

		Short io = ConstantesSistema.NAO;
		ligacaoEsgotoPerfil.setIndicadorPrincipal(io);

		Integer idPerfilLigacaoEsgoto = (Integer) fachada.inserirPerfilLigacaoEsgoto(ligacaoEsgotoPerfil,
				 usuarioLogado);

		montarPaginaSucesso(httpServletRequest, "Perfil da Ligação de Esgoto " + descricao
				+ " inserido com sucesso.", "Inserir outro Perfil da Ligação de Esgoto",
				"exibirInserirPerfilLigacaoEsgotoAction.do?menu=sim",
				"exibirAtualizarPerfilLigacaoEsgotoAction.do?idRegistroAtualizacao="
						+ idPerfilLigacaoEsgoto, "Atualizar Perfil da Ligação de Esgoto Inserida");

		sessao.removeAttribute("InserirPerfilLigacaoEsgotoActionForm");

		return retorno;

	}
}
