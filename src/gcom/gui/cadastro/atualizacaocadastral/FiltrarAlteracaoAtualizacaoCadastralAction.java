package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.leitura.FiltroLeiturista;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarAlteracaoAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = mapping.findForward("filtrarAlteracaoAtualizacaoCadastral");
		
		HttpSession sessao = request.getSession(false);
		
		FiltrarAlteracaoAtualizacaoCadastralActionForm form = (FiltrarAlteracaoAtualizacaoCadastralActionForm) actionForm;
        
		if (!form.existeParametroInformado()) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		setForm(form);
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro = new FiltrarAlteracaoAtualizacaoCadastralActionHelper(form);
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> helper = getFachada().pesquisarMovimentoAtualizacaoCadastral(filtro);
		
		filtro.setTotalImoveis(helper.size());
        
        if( helper.isEmpty()){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", "exibirFiltrarAlteracaoAtualizacaoCadastralAction.do", null, new String[] {});
        }
        
        sessao.setAttribute("colecaoConsultarMovimentoAtualizacaoCadastralHelper",helper);
        sessao.setAttribute("filtroMovimentoAtualizacaoCadastral", filtro);
        sessao.setAttribute("aprovacaoEmLote", filtro.isAprovacaoEmLote());
        sessao.setAttribute("loteInformado", filtro.isLoteInformado());
        
        if ((filtro.isAlteracaoHidrometro() != null && filtro.isAlteracaoHidrometro()) 
        	|| (filtro.isAlteracaoSituacaoAgua() != null && filtro.isAlteracaoSituacaoAgua())
        	|| (filtro.isAlteracaoSituacaoEsgoto() != null && filtro.isAlteracaoSituacaoEsgoto())
        	|| (filtro.isAlteracaoCategoria() != null && filtro.isAlteracaoCategoria())) {
        	
        	sessao.setAttribute("relatorio", true);
        }

		return retorno;
	}

	private void setForm(FiltrarAlteracaoAtualizacaoCadastralActionForm form) {
		form.setNomeLeiturista(this.pesquisarNomeLeiturista(form.getIdLeiturista()));
		form.setNomeLocalidadeInicial(this.pesquisarNomeLocalidade(form.getIdLocalidadeInicial()));
		form.setNomeLocalidadeFinal(this.pesquisarNomeLocalidade(form.getIdLocalidadeFinal()));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String pesquisarNomeLeiturista(String idLeiturista) {
		if (idLeiturista != null && !idLeiturista.trim().equals("")) {
			Filtro filtro = new FiltroLeiturista();
			filtro.adicionarParametro(new ParametroSimples(FiltroLeiturista.ID, idLeiturista));
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
			
			Collection pesquisa = getFachada().pesquisar(filtro, Leiturista.class.getName());
			
			String nome = "";
			if (pesquisa != null && !pesquisa.isEmpty()) {
				Leiturista leiturista = (Leiturista) Util.retonarObjetoDeColecao(pesquisa);
				
				if (leiturista.getFuncionario() != null) {
					nome = leiturista.getFuncionario().getNome();
				}
			}
			
			return nome.trim();
		} else {
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String pesquisarNomeLocalidade(String idLocalidade) {
		if(idLocalidade.trim().equalsIgnoreCase("") && !idLocalidade.trim().equalsIgnoreCase("")) {
			Filtro filtro = new FiltroLocalidade(FiltroLocalidade.DESCRICAO);
			filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			
			Collection pesquisa = getFachada().pesquisar(filtro, Localidade.class.getName());
			
			String nomeLocalidade = "";
			if (pesquisa != null && !pesquisa.isEmpty()) {
				nomeLocalidade = (((List<Localidade>) pesquisa).get(0)).getDescricao();
			}
			
			return nomeLocalidade.trim();
		} else {
			return null;
		}
	}
}
