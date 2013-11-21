package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelProgramaEspecial;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarImovelProgramaEspecialAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterImovelProgramaEspecialAction");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		boolean peloMenosUmParamentroInformado = false;
		
		FiltrarImovelProgramaEspecialActionForm filtrarImovelProgramaEspecialActionForm = (FiltrarImovelProgramaEspecialActionForm) actionForm;
		
		FiltroImovelProgramaEspecial filtroImovelProgramaEspecial = new FiltroImovelProgramaEspecial();
		
		filtroImovelProgramaEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelProgramaEspecial.IMOVEL);
		filtroImovelProgramaEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelProgramaEspecial.LOCALIDADE);
		filtroImovelProgramaEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelProgramaEspecial.SETOR_COMERCIAL);
		filtroImovelProgramaEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelProgramaEspecial.QUADRA);
		
		if(filtrarImovelProgramaEspecialActionForm.getMatriculaImovel()!=null
				&& !filtrarImovelProgramaEspecialActionForm.getMatriculaImovel().equals("")){
	
			filtroImovelProgramaEspecial.adicionarParametro(
				new ParametroSimples(FiltroImovelProgramaEspecial.IMOVEL_ID, filtrarImovelProgramaEspecialActionForm.getMatriculaImovel()));			
			
			peloMenosUmParamentroInformado = true;
			
		}
		
		if(filtrarImovelProgramaEspecialActionForm.getDataApresentacaoDocumentosInicial()!=null
				&& !filtrarImovelProgramaEspecialActionForm.getDataApresentacaoDocumentosInicial().equals("")
					&& filtrarImovelProgramaEspecialActionForm.getDataApresentacaoDocumentosFinal()!=null
						&& !filtrarImovelProgramaEspecialActionForm.getDataApresentacaoDocumentosFinal().equals("")){
			
			Date dataInicio = Util.converteStringParaDate(filtrarImovelProgramaEspecialActionForm.getDataApresentacaoDocumentosInicial());
			Date dataFim = Util.converteStringParaDate(filtrarImovelProgramaEspecialActionForm.getDataApresentacaoDocumentosFinal());
			
			filtroImovelProgramaEspecial.adicionarParametro(
					new MaiorQue(FiltroImovelProgramaEspecial.DATA_APRESENTACAO_DOCUMENTOS, dataInicio,ParametroSimples.CONECTOR_AND));
			filtroImovelProgramaEspecial.adicionarParametro(
					new MenorQue(FiltroImovelProgramaEspecial.DATA_APRESENTACAO_DOCUMENTOS,dataFim));
			
			peloMenosUmParamentroInformado = true;
		}
		if(filtrarImovelProgramaEspecialActionForm.getMesAnoReferenciaEntradaPrograma()!=null
				&& !filtrarImovelProgramaEspecialActionForm.getMesAnoReferenciaEntradaPrograma().equals("")){
			Integer mesAno = Util.formatarMesAnoComBarraParaAnoMes(filtrarImovelProgramaEspecialActionForm.getMesAnoReferenciaEntradaPrograma());
			filtroImovelProgramaEspecial.adicionarParametro(new ParametroSimples(FiltroImovelProgramaEspecial.DATA_INICIO, mesAno));
			peloMenosUmParamentroInformado = true;
		}
		if(filtrarImovelProgramaEspecialActionForm.getMesAnoReferenciaSaidaPrograma()!=null
				&& !filtrarImovelProgramaEspecialActionForm.getMesAnoReferenciaSaidaPrograma().equals("")){
			Integer mesAno = Util.formatarMesAnoComBarraParaAnoMes(filtrarImovelProgramaEspecialActionForm.getMesAnoReferenciaSaidaPrograma());
			filtroImovelProgramaEspecial.adicionarParametro(new ParametroSimples(FiltroImovelProgramaEspecial.DATA_SAIDA, mesAno));
			peloMenosUmParamentroInformado = true;
		}
		
		
		if(!peloMenosUmParamentroInformado){
			
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
			
		}
		
		
		//Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pela sessão uma variável para o
		// ExibirManterEquipeAction e nele verificar se irá para o
		// atualizar ou para o manter, caso o checkbox esteja desmarcado remove
		// da sessão
		String indicadorAtualizar = httpServletRequest
				.getParameter("atualizar");

		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("atualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("atualizar");
		}
		
		
		sessao.setAttribute("filtroImovelProgramaEspecial", filtroImovelProgramaEspecial);
		
		return retorno;

	}
}
