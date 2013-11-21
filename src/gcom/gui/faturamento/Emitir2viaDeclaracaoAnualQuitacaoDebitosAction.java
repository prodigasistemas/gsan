package gcom.gui.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioDeclaracaoAnualQuitacaoDebitos;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Emitir2viaDeclaracaoAnualQuitacaoDebitosAction extends ExibidorProcessamentoTarefaRelatorio {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		Fachada fachada = Fachada.getInstancia();
		
		//httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		Emitir2viaDeclaracaoAnualQuitacaoDebitosActionForm form = 
			(Emitir2viaDeclaracaoAnualQuitacaoDebitosActionForm) actionForm;
		
		//caso o ano não tenha sido selecionado. Mostra tela de erro.
		if(form.getAno().equals("-1")){
			throw new ActionServletException("atencao.ano.nao_informado");
		}
		
		if(!fachada.verificarQuitacaoContas(new Integer(form.getMatriculaImovel()),new Integer(form.getAno()))){
			throw new ActionServletException("atencao.imovel_nao_possui_extrato", form.getMatriculaImovel().toString());
		}
		
		SistemaParametro sistemaParametro
			= fachada.pesquisarParametrosDoSistema();
		
		String nomeEmpresa = sistemaParametro.getNomeAbreviadoEmpresa();
		
		RelatorioDeclaracaoAnualQuitacaoDebitos relatorio = null;
		
		if(nomeEmpresa.equalsIgnoreCase("CAERN")){
				
			relatorio
				= new RelatorioDeclaracaoAnualQuitacaoDebitos(
						usuario,ConstantesRelatorios.RELATORIO_DECLARACAO_ANUAL_QUITACAO_DEBITOS_CAERN);
			
		}else if(nomeEmpresa.equalsIgnoreCase("CAEMA")){
				
			relatorio
				= new RelatorioDeclaracaoAnualQuitacaoDebitos(
						usuario,ConstantesRelatorios.RELATORIO_DECLARACAO_ANUAL_QUITACAO_DEBITOS_CAEMA);
			
		}else{
			
			relatorio
				= new RelatorioDeclaracaoAnualQuitacaoDebitos(
					usuario,ConstantesRelatorios.RELATORIO_DECLARACAO_ANUAL_QUITACAO_DEBITOS);
		}
		
		relatorio.addParametro("matricula", form.getMatriculaImovel());
		relatorio.addParametro("ano", form.getAno());
		
		
		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		relatorio.addParametro("tipoFormatoRelatorio", new Integer(tipoRelatorio));
		
		retorno = processarExibicaoRelatorio(relatorio,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);	
		
		
		return retorno;
	}
}
