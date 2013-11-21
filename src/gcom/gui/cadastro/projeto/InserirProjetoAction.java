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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirProjetoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirProjetoForm form = (InserirProjetoForm) actionForm;

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		if(form.getObservacao() != null && 
			form.getObservacao().length() > 300){

			String[] msg = new String[2];
			msg[0]="Observação";
			msg[1]="300";
			
			throw new ActionServletException("atencao.execedeu_limit_observacao",null,msg);
		}
		
		Date inicioProjeto = Util.converteStringParaDate(form.getDataInicio());
		
		Date fimProjeto = null;
		
		if(form.getDataFim()!=null && !form.getDataFim().equals("")){
			
			fimProjeto = Util.converteStringParaDate(form.getDataFim());
			
			if(Util.compararDiaMesAno(fimProjeto,inicioProjeto)==-1){
				throw new ActionServletException("atencao.datainicio_maior_datafim");
			}
			
		}
		
		
		
		Projeto projeto = new Projeto(form.getNome(),inicioProjeto);
		
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
		if(form.getDataFim()!=null 
				&& !form.getDataFim().equals("")){
			
			projeto.setDataFim(fimProjeto);
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
		
		
		Integer idProjeto = (Integer) fachada.inserir(projeto);
		
		montarPaginaSucesso(httpServletRequest,
				"Projeto " + projeto.getNome() + " inserido com sucesso.",
				"Atualizar o Projeto incluído",
				"exibirAtualizarProjetoAction.do?projetoID="+idProjeto);
		
		
		return retorno;
	}
}
