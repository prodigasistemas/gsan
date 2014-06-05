package gcom.gui.cadastro.atualizacaocadastral;

import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarDadosImovelAtualizacaoCadastralAction extends GcomAction {

	private Fachada fachada = Fachada.getInstancia();
	
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
    	ActionForward retorno =	actionMapping.findForward("filtrarAlteracaoAtualizacaoCadastral");
        
		ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm form = (ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm) actionForm;

        HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		CadastroOcorrencia cadastroOcorrencia = this.pesquisarCadastroOcorrencia(Integer.parseInt(form.getIdImovel()));
		
		if (cadastroOcorrencia != null) {
			if (cadastroOcorrencia.getIndicadorValidacao().equals(ConstantesSistema.SIM)) {
				
				if (!form.getIdRegistrosAutorizados().equals("")) {
					String registrosAutorizados = form.getIdRegistrosAutorizados();
					
					String[] listaIdRegistrosSim = registrosAutorizados.split(",");
					
					if (listaIdRegistrosSim != null && !listaIdRegistrosSim.equals("")) {
						fachada.atualizarIndicadorAutorizacaoColunaAtualizacaoCadastral(Integer.valueOf(form.getIdImovel()),
								listaIdRegistrosSim, ConstantesSistema.SIM, usuario);
					}
				}
				
				httpServletRequest.setAttribute("reload", true);
			} else {
				throw new ActionServletException("atencao.cadastro_ocorrencia_invalido", cadastroOcorrencia.getDescricao());
			}
		} else {
			throw new ActionServletException("atencao.cadastro_ocorrencia_invalido", "NULO. Carregue o imóvel novamente");
		}
		
        return retorno;
    }

	private CadastroOcorrencia pesquisarCadastroOcorrencia(Integer idImovel) {
		ImovelControleAtualizacaoCadastral imovelControleAtualizacaoCadastral = fachada.pesquisarImovelControleAtualizacaoCadastral(idImovel);
		
		return imovelControleAtualizacaoCadastral.getCadastroOcorrencia();
	}
}
