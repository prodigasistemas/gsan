package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OSProgramacaoCalibragem;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarInformarCalibragemAction extends GcomAction {

	private String peso;
	private String fator;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		// Carrega as calibragens de acordo com os objetos da sessão
		Collection colecaoProgramaCalibragem = new Vector();
		if (sessao.getAttribute("colecaoProgramaCalibragem") != null) {
			colecaoProgramaCalibragem = (Collection) sessao
					.getAttribute("colecaoProgramaCalibragem");
		}
		
		Iterator colecaoProgramaCalibragemIt = colecaoProgramaCalibragem.iterator();
		Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
		OSProgramacaoCalibragem osProgramaCalibragem;
		
		while (colecaoProgramaCalibragemIt.hasNext()) {
			osProgramaCalibragem = (OSProgramacaoCalibragem) colecaoProgramaCalibragemIt.next();
			
			if (requestMap.get("peso_" + osProgramaCalibragem.getId().intValue()) != null) {
				peso = (requestMap.get("peso_" + osProgramaCalibragem.getId().intValue()))[0];
			}
			
			if (peso == null
					|| peso.equalsIgnoreCase("")) {

				throw new ActionServletException(
						"atencao.campo_texto.obrigatorio", null,
						"Peso");
			}
			
			if (requestMap.get("fator_" + osProgramaCalibragem.getId().intValue()) != null) {
				fator = (requestMap.get("fator_" + osProgramaCalibragem.getId().intValue()))[0];
			}
			
			if (fator == null
					|| fator.equalsIgnoreCase("")) {

				throw new ActionServletException(
						"atencao.campo_texto.obrigatorio", null,
						"Fator");
			}
			
			osProgramaCalibragem.setFator(Integer.valueOf(fator));
			osProgramaCalibragem.setPeso(Integer.valueOf(peso));
			fachada.atualizarInformarCalibragem(osProgramaCalibragem, usuarioLogado);
		}
		
        httpServletRequest.setAttribute("caminhoFuncionalidade","exibirInformarCalibragemAction.do?menu=sim");
        httpServletRequest.setAttribute("labelPaginaSucesso","Realizar outra Manutenção na Programação Calibragem");
        httpServletRequest.setAttribute("mensagemPaginaSucesso","Dados da(s) calibragem(ens) atualizado(s) com sucesso.");		
		
		return retorno;
	}
}
