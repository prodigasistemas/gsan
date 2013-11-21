package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EmitirOrdemServicoFiscalizacaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("telaSucesso");

		EmitirOrdemFiscalizacaoForm emitirOrdemdeFiscalizacaoForm =
			(EmitirOrdemFiscalizacaoForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		OrdemServico ordemServicoFiscalizacao = new OrdemServico();
		
		if(emitirOrdemdeFiscalizacaoForm.getMatriculaImovel()!=null
				&& !emitirOrdemdeFiscalizacaoForm.getMatriculaImovel().equals("")){
			
			FiltroImovel filtro = new FiltroImovel();
			
			filtro.adicionarParametro(
					new ParametroSimples(
							FiltroImovel.ID, 
							emitirOrdemdeFiscalizacaoForm.getMatriculaImovel()));
			
			Collection colecaoimovel = 
				fachada.pesquisar(filtro, Imovel.class.getName());
			
			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoimovel);

			ordemServicoFiscalizacao.setImovel(imovel);
		}
			
	    Integer idOsGerada = fachada.gerarOrdemServicoFiscalizacao(ordemServicoFiscalizacao, usuario);
		
	    emitirOrdemdeFiscalizacaoForm.setIndicadorPermitirEmitir("sim");
	    emitirOrdemdeFiscalizacaoForm.setIndicadorPermitirGerarOs("nao");
	    emitirOrdemdeFiscalizacaoForm.setOrdemServico(idOsGerada.toString());
	    
		montarPaginaSucesso(httpServletRequest,
				"Ordem de Serviço de Fiscalização " +idOsGerada+ " gerada com sucesso.",
				"Emitir Ordem de Fiscalização",
				"exibirEmitirOrdemFiscalizacaoAction.do");
		
		return retorno;
	}
}
