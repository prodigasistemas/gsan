package gcom.gui.cadastro.cliente;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import gcom.cadastro.descricaogenerica.DescricaoGenerica;
import gcom.cadastro.descricaogenerica.FiltroDescricaoGenerica;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1114] Informar descrição genérica
 *
 * @author Mariana Victor
 * @date 28/12/2010
 * 
 */
public class InformarDescricaoGenericaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		List<DescricaoGenerica> colecaoDescricaoGenerica = (List<DescricaoGenerica>) sessao.getAttribute("colecaoDescricaoGenerica");

		List<DescricaoGenerica> colecaoDescricoesExistentes = null;
		FiltroDescricaoGenerica filtroDescricaoGenerica = new FiltroDescricaoGenerica();
		colecaoDescricoesExistentes = (List<DescricaoGenerica>) fachada.pesquisar(filtroDescricaoGenerica, DescricaoGenerica.class.getName());
		
		Iterator iteratorDescricaoGenerica = colecaoDescricaoGenerica.iterator();
		
		while (iteratorDescricaoGenerica.hasNext()) {
			DescricaoGenerica descricaoGenerica = (DescricaoGenerica) iteratorDescricaoGenerica.next();
			
			if (descricaoGenerica.getDegeId() != null) { //Caso já exista
				descricaoGenerica.setUltimaAlteracao(new Date());
				
				Iterator iterator = colecaoDescricoesExistentes.iterator();
				while (iterator.hasNext()) {
					DescricaoGenerica descricaoExistente = (DescricaoGenerica) iterator.next();
					if (descricaoGenerica.getDegeId().equals(descricaoExistente.getDegeId())) {
						colecaoDescricoesExistentes.remove(descricaoExistente);
						break;
					}
				}
				
				fachada.atualizar(descricaoGenerica);
				
			} else { //Caso ainda não exista
				descricaoGenerica.setUltimaAlteracao(new Date());
				fachada.inserir(descricaoGenerica);
			}
		}
		
		Iterator iteratorDescricoesExistentes = colecaoDescricoesExistentes.iterator();
		while (iteratorDescricoesExistentes.hasNext()) {
			DescricaoGenerica descricaoExistente = (DescricaoGenerica) iteratorDescricoesExistentes.next();
			fachada.remover(descricaoExistente);
		}

		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Descrição(ões) Genérica(s) informadas com sucesso.",
				"Informar Outra Descrição Genérica",
				"exibirInformarDescricaoGenericaAction.do?menu=sim");
		
		return retorno;
	}
}
