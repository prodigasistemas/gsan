package gcom.gui.micromedicao;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.gui.GcomAction;
import gcom.micromedicao.bean.LigacaoMedicaoIndividualizadaHelper;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * [UC0383] MANTER TIPO MATERIAL
 * [SB0001] Atualizar Material
 *
 * @author Kássia Albuquerque
 * @date 17/11/2006
 */


public class AtualizarLigacoesMedicaoIndividualizadaAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		

		ActionForward retorno = actionMapping.findForward("ligacoesMedicaoIndividualizadaAtualizado");
		
//		LigacoesMedicaoIndividualizadaActionForm form = (LigacoesMedicaoIndividualizadaActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Collection colecaoLigacoesMedicaoSessao = (Collection) sessao.getAttribute("colecaoLigacoesMedicaoIndividualizada");
		Iterator iterator = colecaoLigacoesMedicaoSessao.iterator();
		
		Collection colecaoLigacoesAtualizacao = new ArrayList();
		LigacaoMedicaoIndividualizadaHelper ligacaoMedicaoIndividualizadaHelperSessao = null;
		LigacaoMedicaoIndividualizadaHelper ligacaoMedicaoAtualizacao = null;
		
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) sessao.getAttribute("faturamentoGrupo");
		
		String leituraAnterior = null;
		String dataLeituraAnterior = null;
		String leituraAtual = null;
		String dataLeituraAtual = null;
		String anormalidadeLeitura = null;
		String consumoInformado = null;
		
		while(iterator.hasNext()){
			ligacaoMedicaoIndividualizadaHelperSessao = (LigacaoMedicaoIndividualizadaHelper) iterator.next();
			ligacaoMedicaoAtualizacao = new LigacaoMedicaoIndividualizadaHelper();
			
			
			ligacaoMedicaoAtualizacao.setIdImovel(ligacaoMedicaoIndividualizadaHelperSessao.getIdImovel());
			
			leituraAnterior = (String)httpServletRequest.getParameter("leituraAnterior&"+ ligacaoMedicaoIndividualizadaHelperSessao.getIdImovel());
			dataLeituraAnterior = (String)httpServletRequest.getParameter("dataLeituraAnterior&"+ ligacaoMedicaoIndividualizadaHelperSessao.getIdImovel());
			leituraAtual = (String)httpServletRequest.getParameter("leituraAtual&"+ ligacaoMedicaoIndividualizadaHelperSessao.getIdImovel());
			dataLeituraAtual = (String)httpServletRequest.getParameter("dataLeituraAtual&"+ ligacaoMedicaoIndividualizadaHelperSessao.getIdImovel());
			anormalidadeLeitura = (String)httpServletRequest.getParameter("idLeituraAnormalidade&"+ ligacaoMedicaoIndividualizadaHelperSessao.getIdImovel());
			consumoInformado = (String)httpServletRequest.getParameter("consumoInformado&"+ ligacaoMedicaoIndividualizadaHelperSessao.getIdImovel());

			if(leituraAnterior != null && !leituraAnterior.trim().equals("")){
				ligacaoMedicaoAtualizacao.setLeituraAnterior(new Integer(leituraAnterior));
			}
			
			ligacaoMedicaoAtualizacao.setDataLeituraAnterior(dataLeituraAnterior);
						
			if(leituraAtual != null && !leituraAtual.trim().equals("")){
				ligacaoMedicaoAtualizacao.setLeituraAtual(new Integer(leituraAtual));
			}
			
			ligacaoMedicaoAtualizacao.setDataLeituraAtual(dataLeituraAtual);
			
			if(anormalidadeLeitura != null && !anormalidadeLeitura.trim().equals("")){
				ligacaoMedicaoAtualizacao.setIdLeituraAnormalidade(new Integer(anormalidadeLeitura));
			}
			
			if(consumoInformado != null && !consumoInformado.trim().equals("")){
				ligacaoMedicaoAtualizacao.setConsumoInformado(new Integer(consumoInformado));
			}
			
			ligacaoMedicaoAtualizacao.setIdMedicaoHistorico(ligacaoMedicaoIndividualizadaHelperSessao.getIdMedicaoHistorico());
			ligacaoMedicaoAtualizacao.setIdConsumoHistorico(ligacaoMedicaoIndividualizadaHelperSessao.getIdConsumoHistorico());
			
			colecaoLigacoesAtualizacao.add(ligacaoMedicaoAtualizacao);
			
		}
		
		fachada.atualizarLigacoesMedicaoIndividualizada(colecaoLigacoesAtualizacao, usuarioLogado, faturamentoGrupo.getAnoMesReferencia());
		
		httpServletRequest.setAttribute("atulizado", "S");
		
		return retorno;
	}
	
}	      
    



