package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarConsumoAnormalidadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarConsumoAnormalidadeActionForm atualizarConsumoAnormalidadeActionForm = (AtualizarConsumoAnormalidadeActionForm) actionForm;

		ConsumoAnormalidade consumoAnormalidade = (ConsumoAnormalidade) sessao.getAttribute("atualizarConsumoAnormalidade");
		
		consumoAnormalidade.setDescricao(atualizarConsumoAnormalidadeActionForm.getDescricao());
		consumoAnormalidade.setDescricaoAbreviada(atualizarConsumoAnormalidadeActionForm.getDescricaoAbreviada());
		consumoAnormalidade.setMensagemConta(atualizarConsumoAnormalidadeActionForm.getMensagemConta());
		consumoAnormalidade.setIndicadorUso(new Short (atualizarConsumoAnormalidadeActionForm.getIndicadorUso()));
		consumoAnormalidade.setIndicadorRevisaoPermissaoEspecial(
				new Short(atualizarConsumoAnormalidadeActionForm.getIndicadorRevisaoComPermissaoEspecial()));
				
        String descricaoConsumoAnormalidade = atualizarConsumoAnormalidadeActionForm
        .getDescricao();
        String descricaoAbreviadaConsumoAnormalidade = atualizarConsumoAnormalidadeActionForm
        .getDescricaoAbreviada();    
        String mensagemConta = atualizarConsumoAnormalidadeActionForm.getMensagemConta();
        String indicadordeUso = atualizarConsumoAnormalidadeActionForm
        .getIndicadorUso();
		
        consumoAnormalidade.setDescricao(descricaoConsumoAnormalidade);
        consumoAnormalidade.setDescricaoAbreviada(descricaoAbreviadaConsumoAnormalidade);
        
        if(!atualizarConsumoAnormalidadeActionForm.getMensagemConta().trim().equals("") && atualizarConsumoAnormalidadeActionForm.getMensagemConta() != null){
        	consumoAnormalidade.setMensagemConta(mensagemConta);
        } else {
        	mensagemConta = null;
        	consumoAnormalidade.setMensagemConta(mensagemConta);
        }
        
        consumoAnormalidade.setUltimaAlteracao( new Date() );	
        consumoAnormalidade.setIndicadorUso( new Short(indicadordeUso));
		
		fachada.atualizar(consumoAnormalidade);

		montarPaginaSucesso(httpServletRequest, "Anormalidade de Consumo "
				+ atualizarConsumoAnormalidadeActionForm.getId().toString() + " atualizada com sucesso.",
				"Realizar outra Manutenção de Anormalidade de Consumo ",
				"exibirFiltrarConsumoAnormalidadeAction.do?menu=sim");        
        
		return retorno;
	}
}
