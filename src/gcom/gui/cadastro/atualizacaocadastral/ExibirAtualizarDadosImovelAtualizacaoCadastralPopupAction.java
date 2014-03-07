package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.atualizacaocadastral.bean.DadosTabelaAtualizacaoCadastralHelper;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
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

public class ExibirAtualizarDadosImovelAtualizacaoCadastralPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping.findForward("exibirAtualizarDadosImovelAtualizacaoCadastralPopup");
		
		// Cria a sessão
		HttpSession sessao = request.getSession(false);
		
		// Obtém o action form
		ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm form = (ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm) actionForm;

		// Obtém a fachada
		Fachada fachada = Fachada.getInstancia();
		
		String idImovel = (String) request.getParameter("idImovel");

		try {
			// Realiza o Filtro para o Imovel
			if ( (idImovel != null && !idImovel.equals(""))) {
				FiltroImovel filtro = new FiltroImovel();
				
				filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
				
				filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);
				filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
				filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
				filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);
				filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);
				
				Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, Imovel.class.getName()));
				
				form.setIdImovel(idImovel);
				form.setDescricaoImovel("NOVO");
				
				if (imovel != null) {
					Localidade localidade = (Localidade) imovel.getLocalidade();
					SetorComercial setorComercial = (SetorComercial) imovel.getSetorComercial();
					Quadra quadra = (Quadra) imovel.getQuadra();
					
					// Imovel
					form.setDescricaoImovel(imovel.getId().toString());
					// Localidade
					form.setIdLocalidade(localidade.getId().toString());
					form.setDescricaoLocalidade(localidade.getDescricao());
					// Setor Comercial
					form.setIdSetorComercial(setorComercial.getId().toString());
					form.setCodigoSetorComercial("" + setorComercial.getCodigo());
					form.setDescricaoSetorComercial(setorComercial.getDescricao());
					// Quadra
					form.setIdQuadra(quadra.getId().toString());
					form.setNumeroQuadra("" + quadra.getNumeroQuadra());
					form.setSituacaoLigacaoAgua(imovel.getLigacaoAguaSituacao().getDescricao());
					form.setSituacaoLigacaoEsgoto(imovel.getLigacaoEsgotoSituacao().getDescricao());
					request.setAttribute("imovelSubcategorias",fachada.pesquisarSubCategoriasAtualizacaoCadastral(imovel.getId()));
				}else{
					form.limparCampos();
				}
			}
			
			Collection<DadosTabelaAtualizacaoCadastralHelper> colecaoDadosTabelaAtualizacaoCadastral = 
					fachada.consultarDadosTabelaColunaAtualizacaoCadastral(null, null, new Integer(idImovel), null, null);
			
			if (!colecaoDadosTabelaAtualizacaoCadastral.isEmpty()) {
				sessao.setAttribute("colecaoDadosTabelaAtualizacaoCadastral", colecaoDadosTabelaAtualizacaoCadastral);
			}
		} catch (Exception e) {
			throw new ActionServletException("erro.exibir.dados.atualizacao", e, "Dados do Imovel e Cliente");
		}

		return retorno;
	}
}
