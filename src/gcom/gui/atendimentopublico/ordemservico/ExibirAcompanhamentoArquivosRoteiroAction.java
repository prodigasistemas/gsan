package gcom.gui.atendimentopublico.ordemservico;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroSituacaoTransmissaoLeitura;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1199] – Acompanhar Arquivos de Roteiro
 * 
 * @author Thúlio Araújo
 *
 * @date 15/07/2011
 */
public class ExibirAcompanhamentoArquivosRoteiroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirAcompanhamentoArquivosRoteiro");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.ID);
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, 1));
		Collection<?> colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		
		sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		
		FiltroSituacaoTransmissaoLeitura filtroSituacaoTransmissaoLeitura = new FiltroSituacaoTransmissaoLeitura();
		filtroSituacaoTransmissaoLeitura.adicionarParametro(new ParametroSimples(FiltroSituacaoTransmissaoLeitura.INDICADOR_USO, 1));
		filtroSituacaoTransmissaoLeitura.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroSituacaoTransmissaoLeitura.ID, 1));
		filtroSituacaoTransmissaoLeitura.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroSituacaoTransmissaoLeitura.ID, 5));
		filtroSituacaoTransmissaoLeitura.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroSituacaoTransmissaoLeitura.ID, 6));
		filtroSituacaoTransmissaoLeitura.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroSituacaoTransmissaoLeitura.ID, 7));
		filtroSituacaoTransmissaoLeitura.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroSituacaoTransmissaoLeitura.ID, 8));
		Collection<?> colecaoSituacaoTransmissaoLeitura = fachada.pesquisar(filtroSituacaoTransmissaoLeitura, SituacaoTransmissaoLeitura.class.getName());
		
		sessao.setAttribute("colecaoSituacaoTransmissaoLeitura", colecaoSituacaoTransmissaoLeitura);
		
		return retorno;
	}
}
