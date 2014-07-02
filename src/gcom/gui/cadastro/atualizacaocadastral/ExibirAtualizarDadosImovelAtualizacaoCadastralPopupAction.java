package gcom.gui.cadastro.atualizacaocadastral;

import gcom.atualizacaocadastral.ImagemRetorno;
import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.SituacaoAguaHelper;
import gcom.cadastro.atualizacaocadastral.SituacaoEsgotoHelper;
import gcom.cadastro.atualizacaocadastral.SituacaoSubcategoriaHelper;
import gcom.cadastro.atualizacaocadastral.bean.DadosTabelaAtualizacaoCadastralHelper;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.AtualizacaoCadastralUtil;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarDadosImovelAtualizacaoCadastralPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		ActionForward retorno = actionMapping.findForward("exibirAtualizarDadosImovelAtualizacaoCadastralPopup");
		
		HttpSession sessao = request.getSession(false);
		
		ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm form = (ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		String idImovel = (String) request.getParameter("idImovel");

		try {
			Collection<DadosTabelaAtualizacaoCadastralHelper> resumoImovel = new LinkedList<DadosTabelaAtualizacaoCadastralHelper>();

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
					
					form.setDescricaoImovel(imovel.getId().toString());
					form.setIdLocalidade(localidade.getId().toString());
					form.setDescricaoLocalidade(localidade.getDescricao());
					form.setIdSetorComercial(setorComercial.getId().toString());
					form.setCodigoSetorComercial("" + setorComercial.getCodigo());
					form.setDescricaoSetorComercial(setorComercial.getDescricao());
					form.setIdQuadra(quadra.getId().toString());
					form.setNumeroQuadra("" + quadra.getNumeroQuadra());
					resumoImovel.add(new SituacaoAguaHelper(imovel.getLigacaoAguaSituacao().getDescricao()));
					resumoImovel.add(new SituacaoEsgotoHelper(imovel.getLigacaoEsgotoSituacao().getDescricao()));
					
					Collection<ImovelSubcategoriaAtualizacaoCadastral> subcategorias = fachada.pesquisarSubCategoriasAtualizacaoCadastral(imovel.getId());
					for (ImovelSubcategoriaAtualizacaoCadastral economia : subcategorias) {
						String subcategoria = economia.getDescricaoCategoria() + " - " + economia.getDescricaoSubcategoria();
						resumoImovel.add(new SituacaoSubcategoriaHelper(String.valueOf(economia.getQuantidadeEconomias()), subcategoria));
					}
				}else{
					form.limparCampos();
				}
			}
			
			Collection<ImagemRetorno> colecaoImagens = fachada.pesquisarImagensRetornoPorIdImovel(Integer.parseInt(idImovel));
			if (colecaoImagens != null && !colecaoImagens.isEmpty()) {
				sessao.setAttribute("colecaoImagens", colecaoImagens);
			} else {
				sessao.setAttribute("colecaoImagens", null);
			}
			
			Map<String, List<DadosTabelaAtualizacaoCadastralHelper>> map = fachada.consultarDadosTabelaColunaAtualizacaoCadastral(
					null, null, Integer.valueOf(idImovel), null, null);
			
			Collection<DadosTabelaAtualizacaoCadastralHelper> atualizacoes = new AtualizacaoCadastralUtil().linhasAtualizacaoCadastral(resumoImovel, map);
			
			if (atualizacoes != null && !atualizacoes.isEmpty()) {
				sessao.setAttribute("colecaoDadosTabelaAtualizacaoCadastral", atualizacoes);
			}		
			
			ImovelControleAtualizacaoCadastral controle = fachada.pesquisarImovelControleAtualizacao(Integer.valueOf(idImovel));
			
			boolean fiscalizado = false;
			
			if (controle != null) {
				if (controle.getSituacaoAtualizacaoCadastral().getId().equals(SituacaoAtualizacaoCadastral.EM_FISCALIZACAO)) {
					fiscalizado = true;
				}
			}
			
			sessao.setAttribute("fiscalizado", fiscalizado);
		} catch (Exception e) {
			throw new ActionServletException("erro.exibir.dados.atualizacao", e, "Dados do Imovel e Cliente");
		}

		return retorno;
	}
}
