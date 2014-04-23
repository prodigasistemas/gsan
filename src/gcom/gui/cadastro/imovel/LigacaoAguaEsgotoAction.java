package gcom.gui.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.medicao.FiltroMedicaoTipo;
import gcom.micromedicao.medicao.MedicaoTipo;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LigacaoAguaEsgotoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarLigacaoAguaEsgoto");

		
		 Fachada fachada = Fachada.getInstancia();
		 
		 FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();	 
		 Collection<LigacaoAguaSituacao> collectionsLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName() );
		 
		 FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
		 Collection<LigacaoEsgotoSituacao> collectionLigacaoEsgotoSituacao = fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName() );
		 
		 FiltroMedicaoTipo filtroMedicaoTipo = new FiltroMedicaoTipo();
		 Collection<FiltroMedicaoTipo> collectionFiltroMedicaoTipo =  fachada.pesquisar(filtroMedicaoTipo, MedicaoTipo.class.getName() );
		 
		 httpServletRequest.setAttribute("collectionFiltroMedicaoTipo", collectionFiltroMedicaoTipo );
		 httpServletRequest.setAttribute("collectionsLigacaoAguaSituacao", collectionsLigacaoAguaSituacao);		 
		 httpServletRequest.setAttribute("collectionLigacaoEsgotoSituacao", collectionLigacaoEsgotoSituacao);
		 
		return retorno;
	}

}
