package gcom.gui.faturamento.conta;

import gcom.atendimentopublico.registroatendimento.EspecificacaoTipoValidacao;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class RetirarRevisaoContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirManterConta");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        Fachada fachada = Fachada.getInstancia();
        
        //Carregando o identificador das contas selecionadas
        String identificadoresConta = httpServletRequest.getParameter("conta");
        
        String[] arrayIdentificadores = identificadoresConta.split(",");
        
        int flag = 0;
		Integer contaNaBase = null; 
		
		Collection idsConta = new ArrayList();
		String idImovel = httpServletRequest.getParameter("idImovel");
		
		for (int i = 0; i < arrayIdentificadores.length; i++) {
			// Carregando a conta que está na base
			String dadosConta = arrayIdentificadores[i];
			String[] idUltimaAlteracao = dadosConta.split("-");
			
			Integer idConta = new Integer (idUltimaAlteracao[0]);
			idsConta.add(idConta);
			
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			
			Calendar data = new GregorianCalendar();
			data.setTimeInMillis(new Long(idUltimaAlteracao[1].trim())
					.longValue());
			
			String time = formatter.format(data.getTime());
			
			// alterado para fazer a pesquisa por Hql e nao com filtro como estava sendo feita antes - Fernanda Paiva - 23/08/2006
			contaNaBase = contaNaBase = fachada.pesquisarExistenciaContaParaConcorrencia(idUltimaAlteracao[0],time);
			
			// Verificar atualização realizada antes por outro usuário
			
			if (contaNaBase == null || contaNaBase.equals("")) {
				httpServletRequest.setAttribute("reloadPage", "OK");
				flag = 1;
				sessao.setAttribute("erroConcorrencia","erroConcorrencia");
			}
		}

		
		//[FS0017] Verificar ocorrência de conta(s) em revisão por ação do usuário
		//Vivianne Sousa 14/05/2007
        Collection contasRevisaoAcaoUsuario = fachada.obterContasEmRevisaoPorAcaoUsuario(idsConta);
        
        if (contasRevisaoAcaoUsuario != null && !contasRevisaoAcaoUsuario.isEmpty()){

            //[FS0001] - Verificar Existência de RA
            fachada.verificarExistenciaRegistroAtendimento(new Integer(idImovel), "atencao.conta_existencia_registro_atendimento",EspecificacaoTipoValidacao.ALTERACAO_CONTA); 
	        	
        }
		
		
		
        if (sessao.getAttribute("colecaoContaImovel") != null && (identificadoresConta != null &&
            	!identificadoresConta.equalsIgnoreCase("")) && flag == 0){
            
            	Collection<Conta> colecaoContaImovel = (Collection) sessao.getAttribute("colecaoContaImovel");
                
            	//Retirando de revisão uma ou mais contas
            	fachada.retirarRevisaoConta(colecaoContaImovel, identificadoresConta, usuarioLogado, null);
            	
        }
        
        
        return retorno;
    }

}

