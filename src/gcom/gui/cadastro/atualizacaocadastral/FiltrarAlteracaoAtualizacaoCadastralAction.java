package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		Fachada fachada = Fachada.getInstancia();

		ActionForward retorno = actionMapping.findForward("filtrarAlteracaoAtualizacaoCadastral");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarAlteracaoAtualizacaoCadastralActionForm form = (FiltrarAlteracaoAtualizacaoCadastralActionForm) actionForm;
        
		if (!form.existeParametroInformado()) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		if(form.getNomeLocalidadeInicial().trim().equalsIgnoreCase("") && !form.getIdLocalidadeInicial().trim().equalsIgnoreCase("")) {
			form.setNomeLocalidadeInicial(this.pesquisarNomeLocalidade(form.getIdLocalidadeInicial(), fachada));
		}
		
		if(form.getNomeLocalidadeFinal().trim().equalsIgnoreCase("") && !form.getIdLocalidadeFinal().trim().equalsIgnoreCase("")) {
			form.setNomeLocalidadeFinal(this.pesquisarNomeLocalidade(form.getIdLocalidadeFinal(), fachada));
		}
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro = new FiltrarAlteracaoAtualizacaoCadastralActionHelper(form);
		filtro.setNomeLocalidadeFinal(form.getNomeLocalidadeFinal());
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> helper = fachada.pesquisarMovimentoAtualizacaoCadastral(filtro);
		
		filtro.setTotalImoveis(helper.size());
        
        if( helper.isEmpty()){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", "exibirFiltrarAlteracaoAtualizacaoCadastralAction.do", null, new String[] {});
        }
        sessao.setAttribute("colecaoConsultarMovimentoAtualizacaoCadastralHelper",helper);
        sessao.setAttribute("filtroMovimentoAtualizacaoCadastral", filtro);
        sessao.setAttribute("aprovacaoEmLote", filtro.isAprovacaoEmLote());
        
        if ((filtro.isAlteracaoHidrometro() != null && filtro.isAlteracaoHidrometro()) 
        		|| (filtro.isAlteracaoSituacaoAgua() != null && filtro.isAlteracaoSituacaoAgua())
        		|| (filtro.isAlteracaoSituacaoEsgoto() != null && filtro.isAlteracaoSituacaoEsgoto())
        		|| (filtro.isAlteracaoCategoria() != null && filtro.isAlteracaoCategoria())) {
        	sessao.setAttribute("relatorio", true);
        }

		return retorno;
	}
	
	private String pesquisarNomeLocalidade(String idLocalidade, Fachada fachada) {
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade(FiltroLocalidade.DESCRICAO);
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, new Integer(idLocalidade)));
		Collection localidades = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		String nomeLocalidade = "";
		if (localidades != null && !localidades.isEmpty()) {
			nomeLocalidade = (((List<Localidade>) localidades).get(0)).getDescricao();
		}
		
		return nomeLocalidade.trim();
	}
}
