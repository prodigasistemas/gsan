package gcom.gui.arrecadacao;

import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.debitoautomatico.FiltroDebitoAutomatico;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoverDebitoAutomaticoAction extends GcomAction {
	
	private Fachada fachada = Fachada.getInstancia();
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

        String[] idsDebitoAutomatico = manutencaoRegistroActionForm.getIdRegistrosRemocao();

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        if (idsDebitoAutomatico == null || idsDebitoAutomatico.length == 0) {
            throw new ActionServletException("atencao.registros.nao_selecionados");
        } else {
        	for (int i = 0; i < idsDebitoAutomatico.length; i++) {
        		this.atualizarDebitoAutomatico(Integer.valueOf(idsDebitoAutomatico[i]));
			}
        }
        
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest, idsDebitoAutomatico.length + " Débito(s) Automático(s) removidos com sucesso.",
                    "Realizar outra Manutenção de Débito Automático", "exibirFiltrarDebitoAutomaticoAction.do?menu=sim");
        }

        return retorno;
	}

	@SuppressWarnings("unchecked")
	private void atualizarDebitoAutomatico(Integer idDebitoAutomatico) throws NumberFormatException {
		FiltroDebitoAutomatico filtroDebitoAutomatico = new FiltroDebitoAutomatico();
		filtroDebitoAutomatico.adicionarParametro(new ParametroSimples(FiltroDebitoAutomatico.ID, idDebitoAutomatico));
		filtroDebitoAutomatico.adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoAutomatico.IMOVEL);

		Collection colecaoDebitoAutomatico = fachada.pesquisar(filtroDebitoAutomatico, DebitoAutomatico.class.getName());
		DebitoAutomatico debitoAutomatico = (DebitoAutomatico) Util.retonarObjetoDeColecao(colecaoDebitoAutomatico);
		debitoAutomatico.setDataExclusao(new Date());
		debitoAutomatico.setUltimaAlteracao(new Date());
		
		fachada.atualizar(debitoAutomatico);
		fachada.atualizarIndicadorDebitoAutomaticoComDataExclusao(debitoAutomatico.getImovel().getId());
	}
}
