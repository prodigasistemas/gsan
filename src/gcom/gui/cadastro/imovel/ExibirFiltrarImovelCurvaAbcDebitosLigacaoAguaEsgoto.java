package gcom.gui.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.medicao.FiltroMedicaoTipo;
import gcom.micromedicao.medicao.MedicaoTipo;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarImovelCurvaAbcDebitosLigacaoAguaEsgoto extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarImovelCurvaAbcDebitosLigacaoAguaEsgoto");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		ImovelCurvaAbcDebitosActionForm imovelCurvaAbcDebitosActionForm = (ImovelCurvaAbcDebitosActionForm) actionForm;
		
		//Ligacao Agua Situacao
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
		filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
		
		Collection<LigacaoAguaSituacao> collectionsLigacaoAguaSituacao = fachada
			.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName() );
		
		if(collectionsLigacaoAguaSituacao == null || collectionsLigacaoAguaSituacao.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Situação da Ligação de Água");			
		}		 
		 
		//Ligacao Esgoto Situacao
		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
		filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);
		Collection<LigacaoEsgotoSituacao> collectionLigacaoEsgotoSituacao = fachada
			.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName() );
		
		if(collectionLigacaoEsgotoSituacao == null || collectionLigacaoEsgotoSituacao.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Situação da Ligação de Esgoto");			
		}		 
		 
		//Medicao Tipo
		FiltroMedicaoTipo filtroMedicaoTipo = new FiltroMedicaoTipo();
		filtroMedicaoTipo.setCampoOrderBy(FiltroMedicaoTipo.DESCRICAO);
		Collection<FiltroMedicaoTipo> collectionFiltroMedicaoTipo = fachada
			.pesquisar(filtroMedicaoTipo, MedicaoTipo.class.getName());
		
	 	if(collectionFiltroMedicaoTipo == null || collectionFiltroMedicaoTipo.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Indicador de Medição");			
		}		 
		 
		httpServletRequest.setAttribute("collectionFiltroMedicaoTipo", collectionFiltroMedicaoTipo );
		httpServletRequest.setAttribute("collectionsLigacaoAguaSituacao", collectionsLigacaoAguaSituacao);		 
		httpServletRequest.setAttribute("collectionLigacaoEsgotoSituacao", collectionLigacaoEsgotoSituacao);
		
		// Usado para fazer o controle de navegacao por conta da Aba Local 
		sessao.setAttribute("abaAtual", "LIGACAOAGUAESGOTO");
		
		if (imovelCurvaAbcDebitosActionForm.getClassificacao() != null) {
			httpServletRequest.setAttribute("classificacao", imovelCurvaAbcDebitosActionForm.getClassificacao());
		}
		
		return retorno;
	}
}
