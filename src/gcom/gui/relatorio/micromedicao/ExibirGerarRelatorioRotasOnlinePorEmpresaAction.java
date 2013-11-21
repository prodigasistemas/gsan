package gcom.gui.relatorio.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.bean.PesquisarRelatorioRotasOnlinePorEmpresaHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0???] Gerar Relatorio Rotas Online por Empresa
 * 
 * @see gcom.gui.relatorio.micromedicao.ExibirGerarRelatorioRotasOnlinePorEmpresaAction
 * @see gcom.gui.relatorio.micromedicao.GerarRelatorioRotasOnlinePorEmpresaAction
 * @see gcom.relatorio.micromedicao.RelatorioRotasOnlinePorEmpresa
 * @see gcom.micromedicao.RepositorioMicromedicaoHBM#pesquisarRelatorioRotasOnlinePorEmpresa(PesquisarRelatorioRotasOnlinePorEmpresaHelper)
 * 
 * @author Victor Cisneiros
 * @date 28/10/2008
 */
public class ExibirGerarRelatorioRotasOnlinePorEmpresaAction extends GcomAction {

	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("exibirGerarRelatorioRotasOnlinePorEmpresaAction");
		HttpSession sessao = request.getSession();
		Fachada fachada = Fachada.getInstancia();

		FiltroFaturamentoGrupo filtroFaturamentroGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentroGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
		sessao.setAttribute("collectionFaturamentoGrupo", fachada.pesquisar(filtroFaturamentroGrupo, FaturamentoGrupo.class.getName()));

		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		sessao.setAttribute("collectionEmpresa", fachada.pesquisar(filtroEmpresa, Empresa.class.getName()));
		
		FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
		filtroLeiturista.setCampoOrderBy("cliente.nome");
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
		sessao.setAttribute("collectionLeiturista", fachada.pesquisar(filtroLeiturista, Leiturista.class.getName()));
		
		return retorno;
	}

}
