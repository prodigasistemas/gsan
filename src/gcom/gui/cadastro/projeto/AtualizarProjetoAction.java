package gcom.gui.cadastro.projeto;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.projeto.Projeto;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarProjetoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta Retorno (Forward = Sucesso)
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Form
		AtualizarProjetoForm form = (AtualizarProjetoForm) actionForm;

		// Fachada
		Fachada fachada = Fachada.getInstancia();

		// Sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if(form.getObservacao() != null && 
			form.getObservacao().length() > 300){
			
			String[] msg = new String[2];
			msg[0]="Observação";
			msg[1]="300";
			
			throw new ActionServletException("atencao.execedeu_limit_observacao",null,msg);
		}
		

		Date inicioProjeto = Util.converteStringParaDate(form.getDataInicio());

		Projeto projeto  = new Projeto(form.getNome(), inicioProjeto);
		
		Integer idProjeto = (Integer) sessao.getAttribute("idProjeto");
		
		projeto.setId(idProjeto);

		if (form.getDataFim() != null && !form.getDataFim().equals("")) {

			Date fimProjeto = Util.converteStringParaDate(form.getDataFim());
			projeto.setDataFim(fimProjeto);
			
		}
		if(form.getNomeAbreviado()!=null 
				&& !form.getNomeAbreviado().equals("")){
			projeto.setNomeAbreviado(form.getNomeAbreviado());
		}
		if(form.getIdOrgaoFinanciador()!=null 
				&& !form.getIdOrgaoFinanciador().equals("")){
			//CRIA OBJETO DO CLIENTE ORGAO FINANCIADOR
			Cliente orgaoFinanciador = new Cliente();
			orgaoFinanciador.setId(new Integer(form.getIdOrgaoFinanciador()));
			
			projeto.setOrgaoFinanciador(orgaoFinanciador);
		}
		if(form.getValorFinanciamento()!=null 
				&& !form.getValorFinanciamento().equals("")){
			BigDecimal valorFinanciamento = new BigDecimal(
					form.getValorFinanciamento().replace(".","").replace(",","."));
			projeto.setValorFinanciamento(valorFinanciamento);
		}
		if(form.getObservacao()!=null 
				&& !form.getObservacao().equals("")){
			projeto.setObservacao(form.getObservacao());
		}
		
		projeto.setUltimaAlteracao(new Date());
		
		fachada.atualizar(projeto);

		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Projeto " + projeto.getNome()
				+ " atualizado com sucesso.",
				"Realizar outra manutenção de Projeto.",
				"exibirFiltrarProjetoAction.do?menu=sim");

		return retorno;
	}

}
