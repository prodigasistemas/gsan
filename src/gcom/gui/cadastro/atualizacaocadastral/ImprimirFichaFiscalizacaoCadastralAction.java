package gcom.gui.cadastro.atualizacaocadastral;

import java.util.Collection;

import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarRegistroAtendimentoHelper;
import gcom.atualizacaocadastral.FiltroImagemRetorno;
import gcom.atualizacaocadastral.ImovelRetorno;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.fachada.Fachada;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.atualizacaocadastral.RelatorioFichaFiscalizacaoCadastral;
import gcom.relatorio.cadastro.atualizacaocadastral.RelatorioFichaFiscalizacaoCadastralBO;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ImprimirFichaFiscalizacaoCadastralAction extends ExibidorProcessamentoTarefaRelatorio {
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = null;
		
		try {
			boolean dadosOriginais = true;
			
			String numeroRA = httpServletRequest.getParameter("numeroRA");
			if (numeroRA != null) {
				setarDadosHelperSessao(numeroRA, httpServletRequest);
				dadosOriginais= false;
			} 
			
			RelatorioFichaFiscalizacaoCadastralBO relatorioBO = new RelatorioFichaFiscalizacaoCadastralBO(httpServletRequest, dadosOriginais);

			RelatorioFichaFiscalizacaoCadastral relatorio = relatorioBO.getRelatorioFichaFiscalizacaoCadastral();

			httpServletRequest.setAttribute("telaSucessoRelatorio",true);
			
			retorno = processarExibicaoRelatorio(relatorio, TarefaRelatorio.TIPO_PDF,
					httpServletRequest, httpServletResponse, actionMapping);
		} catch (SistemaException ex) {
			reportarErros(httpServletRequest, "erro.sistema");
			retorno = actionMapping.findForward("telaErroPopup");
		} catch (RelatorioVazioException ex1) {
			reportarErros(httpServletRequest, "erro.relatorio.vazio");
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}
		
		return retorno;
	}
	
	private void setarDadosHelperSessao(String numeroRA, HttpServletRequest httpServletRequest) {
		HttpSession sessao = httpServletRequest.getSession(false);
		
		
		
		sessao.setAttribute("colecaoConsultarMovimentoAtualizacaoCadastralHelper", obterDadosAtualizacaoCadastral(numeroRA));
	}
	
	@SuppressWarnings("unchecked")
	private RegistroAtendimento pesquisarRA(String numeroRA) {
		FiltroRegistroAtendimento filtro = new FiltroRegistroAtendimento();
		filtro.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, numeroRA));
		
		Collection<RegistroAtendimento> ras = Fachada.getInstancia().pesquisar(filtro, RegistroAtendimento.class.getName());
		
		return ras.iterator().next();
	}
	
	private Collection<ConsultarMovimentoAtualizacaoCadastralHelper> obterDadosAtualizacaoCadastral(String numeroRA) {
		RegistroAtendimento ra = pesquisarRA(numeroRA);
		String idImovelRetorno = ra.obterNumeroImovelRetorno();
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro = new FiltrarAlteracaoAtualizacaoCadastralActionHelper();
		
		filtro.setSituacaoImoveis(SituacaoAtualizacaoCadastral.ATUALIZADO);
		filtro.setIdImovelRetorno(new Integer(idImovelRetorno));
		return Fachada.getInstancia().pesquisarMovimentoAtualizacaoCadastral(filtro);
	}
	
	private FiltrarAlteracaoAtualizacaoCadastralActionHelper obterFiltro(String numeroRA) {
		RegistroAtendimento ra = pesquisarRA(numeroRA);
		String idImovelRetorno = ra.obterNumeroImovelRetorno();
		
		ImovelRetorno retorno = Fachada.getInstancia().pesquisarImovelRetornoPorIdRetorno(new Integer(idImovelRetorno));
		
		
		
	//	filtro.set
		return null;
		
	}
	
}
