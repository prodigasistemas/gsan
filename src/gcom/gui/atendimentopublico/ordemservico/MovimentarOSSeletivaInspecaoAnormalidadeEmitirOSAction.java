package gcom.gui.atendimentopublico.ordemservico;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioOrdemServico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável por validar os dados informados na página de Emitir OS do processo 
 * de movimentar ordem de serviço.
 *
 * @author Vivianne Sousa
 * @date 14/07/2011
 */
public class MovimentarOSSeletivaInspecaoAnormalidadeEmitirOSAction extends ExibidorProcessamentoTarefaRelatorio {
   
    /**
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        //Cria uma instância da sessão 
//        HttpSession sessao = httpServletRequest.getSession(false);
		
        MovimentarOSSeletivaInspecaoAnormalidadeActionForm form = (MovimentarOSSeletivaInspecaoAnormalidadeActionForm) actionForm;
        
       if (httpServletRequest.getParameter("concluir") != null
				&& (httpServletRequest.getParameter("concluir")).toString().equalsIgnoreCase("true")) {
			//Se clicou no botão de "Emitir"
			
//        	if () {
	        	
				String mensagem = validarFormEmitirOSRA(form);
				StringTokenizer idsOrdemServico = null;
				if (mensagem != null && !mensagem.equals("")) {
					throw new ActionServletException(mensagem);
				}else{
					
					if (form.getNumerosOSRegistroAtendimentoEmitir() != null
				    	&& form.getNumerosOSRegistroAtendimentoEmitir().length > 0){
						
						String[] idsOS = form.getNumerosOSRegistroAtendimentoEmitir();
						
						String chave = "";
						for (int j=0; j< idsOS.length; j++) {
							chave = chave+"$"+idsOS[j];
						}
						idsOrdemServico = new StringTokenizer(chave, "$");
						
					}else{
						List<Integer> numerosOSPesquisar = new ArrayList();
						
						String chave = "";
						for (int i = 0; i < form.getNumerosOSEmitir().length; i++) {
							
							if (form.getNumerosOSEmitir()[i] != null
									&& !form.getNumerosOSEmitir()[i].equals("")) {
								
								numerosOSPesquisar.add(new Integer((form.getNumerosOSEmitir()[i]).trim()));
								
								chave = chave+"$"+(form.getNumerosOSEmitir()[i]).trim();
							}
						}
						idsOrdemServico = new StringTokenizer(chave, "$");
						
						verificaSeOSFazParteComando(numerosOSPesquisar,form.getIdComando());
					}
					
					String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

					RelatorioOrdemServico relatorioOrdemServico = new RelatorioOrdemServico(
							(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
					relatorioOrdemServico.addParametro("idsOrdemServico", idsOrdemServico);
					
					if (tipoRelatorio == null) {
						tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
					}

					relatorioOrdemServico.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
					retorno = processarExibicaoRelatorio(relatorioOrdemServico,
							tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);

					// devolve o mapeamento contido na variável retorno
					return retorno;
					
					
				}

				
				
//			} else {
//				throw new ActionServletException(
//						"atencao.selecione.um_tipo.emissao.ordem_servico");
//			}
        }

        // Monta página de sucesso
    	montarPaginaSucesso(httpServletRequest, "Ordem(ns) de Serviço emitida(s) com sucesso!",
			"Voltar",
			"exibirMovimentarOSSeletivaInspecaoAnormalidadeAction.do?comando=" + form.getIdComando());
        
        //Retorna o mapemaneto na variável "retorno"
        return retorno;
    }


    public String validarFormEmitirOSRA(MovimentarOSSeletivaInspecaoAnormalidadeActionForm form) {

    	if ((form.getNumerosOSRegistroAtendimentoEmitir() == null
    		|| form.getNumerosOSRegistroAtendimentoEmitir().length == 0)&&
    	 (!(form.getNumerosOSEmitir() != null && form.getNumerosOSEmitir().length != 0
			|| (form.getNumerosOSEmitir()[0] != null && !form.getNumerosOSEmitir()[0].equals(""))
			|| (form.getNumerosOSEmitir()[1] != null && !form.getNumerosOSEmitir()[1].equals(""))
			|| (form.getNumerosOSEmitir()[2] != null && !form.getNumerosOSEmitir()[2].equals(""))
			|| (form.getNumerosOSEmitir()[3] != null && !form.getNumerosOSEmitir()[3].equals(""))
			|| (form.getNumerosOSEmitir()[4] != null && !form.getNumerosOSEmitir()[4].equals(""))
			|| (form.getNumerosOSEmitir()[5] != null && !form.getNumerosOSEmitir()[5].equals(""))
			|| (form.getNumerosOSEmitir()[6] != null && !form.getNumerosOSEmitir()[6].equals(""))
			|| (form.getNumerosOSEmitir()[7] != null && !form.getNumerosOSEmitir()[7].equals(""))
			|| (form.getNumerosOSEmitir()[8] != null && !form.getNumerosOSEmitir()[8].equals(""))
			|| (form.getNumerosOSEmitir()[9] != null && !form.getNumerosOSEmitir()[9].equals("")))))  {
    		return "atencao.selecione.alguma.ordens_servico";
    	}
    	
		return null;
    }
    // [FS0001] - Verificar se ordem de serviço faz parte do comando
    public String verificaSeOSFazParteComando(List<Integer> numerosOSPesquisar,String idComandoOrdemSeletiva) {
    	String retorno = null;
    			
    	String OsNaoFazParteComando = Fachada.getInstancia().retornaOsNaoFazParteComando(
    			new Integer (idComandoOrdemSeletiva),numerosOSPesquisar);
    	
    	if(!OsNaoFazParteComando.equalsIgnoreCase("")){
    		throw new ActionServletException("atencao.os.nao_contida_comando",null,OsNaoFazParteComando);
    	}
    	
    	return retorno;
    }
    

}
