 package gcom.gui.relatorio.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.DadosLeiturista;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarRelatorioAcompanhamentoLeituristaAction extends
		GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarRelatorioAcompanhamentoLeituristaForm filtrarRelatorioAcompanhamentoLeituristaForm = (FiltrarRelatorioAcompanhamentoLeituristaForm) actionForm;

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirFiltrarRelatorioAcompanhamentoLeituristaAction");
		
		SortedSet colecaoLeiturista = new TreeSet();
		

		// Parte que passa as coleções da Empresa necessárias no jsp
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.ID);
		Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
				Empresa.class.getName());

		if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Empresa");
		}

		// Parte que passa as coleções da Grupo de Faturamento necessárias no
		// jsp
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
		Collection colecaoFaturamentoGrupo = fachada.pesquisar(
				filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

		if (colecaoFaturamentoGrupo != null
				&& !colecaoFaturamentoGrupo.isEmpty()) {
			sessao.setAttribute("colecaoFaturamentoGrupo",
					colecaoFaturamentoGrupo);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Grupo de Faturamento");
		}
		
		// Pesquisa Rotas
		if(filtrarRelatorioAcompanhamentoLeituristaForm.getIdGrupoFaturamento()!=null
				&& !filtrarRelatorioAcompanhamentoLeituristaForm.getIdGrupoFaturamento().equals("-1")
					&& !filtrarRelatorioAcompanhamentoLeituristaForm.getIdGrupoFaturamento().equals("")){
			FiltroRota filtroRota = new FiltroRota();
			
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.FATURAMENTO_GRUPO_ID,filtrarRelatorioAcompanhamentoLeituristaForm.getIdGrupoFaturamento()));
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial");

			String[] ordenacao = {FiltroRota.SETOR_COMERCIAL_CODIGO,FiltroRota.CODIGO_ROTA};
			
			filtroRota.setCampoOrderBy(ordenacao);
			
			Collection<Rota> colecaoRotas = fachada.pesquisar(filtroRota,Rota.class.getName());
			
	     	Iterator iteraRotas =	colecaoRotas.iterator();
	     	
	     	List colecaoIdsCodigos = new ArrayList(colecaoRotas.size());
	     	
	     	while(iteraRotas.hasNext()){
	     		
	     		Rota rota = (Rota) iteraRotas.next();
	     		
	     		FiltrarRelatorioAcompanhamentoLeituristaHelper helper = 
	     			new FiltrarRelatorioAcompanhamentoLeituristaHelper(
	     					rota.getId(),
	     					rota.getCodigo(),
	     					"Setor "+rota.getSetorComercial().getCodigo()+" Rota "+rota.getCodigo());
	     		
	     		colecaoIdsCodigos.add(helper);
	     		
	     		
	     	}
	     	

			sessao.setAttribute("colecaoRotas", colecaoIdsCodigos);
			
		}
			
		
		// Leiturista da Empresa
		if (filtrarRelatorioAcompanhamentoLeituristaForm.getIdEmpresa() != null
				&& !filtrarRelatorioAcompanhamentoLeituristaForm.getIdEmpresa()
						.equals("-1")
				&& !filtrarRelatorioAcompanhamentoLeituristaForm.getIdEmpresa()
						.equals("")) {

			FiltroLeiturista filtroLeiturista = new FiltroLeiturista(
					FiltroLeiturista.ID);
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.EMPRESA_ID,
					filtrarRelatorioAcompanhamentoLeituristaForm.getIdEmpresa()));
			filtroLeiturista
					.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista
					.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
			

			Collection colecao = fachada.pesquisar(filtroLeiturista,
					Leiturista.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				Iterator it = colecao.iterator();
				while (it.hasNext()) {
					Leiturista leitu = (Leiturista) it.next();
					DadosLeiturista dadosLeiu = null;
					if (leitu.getFuncionario() != null) {
						dadosLeiu = new DadosLeiturista(leitu.getId(), leitu
								.getFuncionario().getNome());
					} else {
						dadosLeiu = new DadosLeiturista(leitu.getId(), leitu
								.getCliente().getNome());
					}
					colecaoLeiturista.add(dadosLeiu);
					
					
				}
			}

		}
		
		sessao.setAttribute("colecaoLeiturista", colecaoLeiturista);

		return retorno;

	}

}
