package gcom.gui.faturamento.conta;

import gcom.atendimentopublico.registroatendimento.EspecificacaoTipoValidacao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDocumentoItem;
import gcom.cobranca.FiltroCobrancaDocumentoItem;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaMotivoCancelamento;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.conta.FiltroMotivoCancelamentoConta;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirCancelarContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirCancelarConta");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Fachada fachada = Fachada.getInstancia();
        
        //Carregando o identificador das contas selecionadas
        String contaSelected = httpServletRequest.getParameter("conta");
        
    	String idImovel = httpServletRequest.getParameter("idImovel");
    	
		/*
		 * Colocado por Ana Maria em 22/04/2009				
		 */
		if (!fachada.verificarPermissaoRetificarContaImovelPefilBloqueado((Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO))){
			Boolean imovelBloqueado = fachada.verificarImovelPerfilBloqueado(new Integer(httpServletRequest.getParameter("idImovel")));
			if(imovelBloqueado){						
                throw new ActionServletException(
                        "atencao.perfil_imovel_nao_permite_operacao");
			}
		}
		
        //Instância do formulário que está sendo utilizado
        CancelarContaActionForm cancelarContaActionForm = (CancelarContaActionForm) actionForm;
        
        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
        StringTokenizer idsContas = new StringTokenizer(contaSelected, ",");
        
        while (idsContas.hasMoreElements()) {
        	String dadosConta = (String) idsContas.nextElement();
        	String idConta = dadosConta.split("-")[0];
        	
        	FiltroConta filtroConta = new FiltroConta();
        	filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.ID, Integer.valueOf(idConta)));
        	
        	Collection colecaoConta = fachada.pesquisar(filtroConta, Conta.class.getName());
        	
        	if (colecaoConta != null && !colecaoConta.isEmpty()) {
        		Conta conta = (Conta) Util.retonarObjetoDeColecao(colecaoConta);
        		
        		if (conta.getDebitoCreditoSituacaoAtual().getId().equals(DebitoCreditoSituacao.RETIFICADA) || conta.getDebitoCreditoSituacaoAtual().getId().equals(DebitoCreditoSituacao.INCLUIDA)) {
        			
        			FiltroCobrancaDocumentoItem filtroCobrancaDocumentoItem = new FiltroCobrancaDocumentoItem();
        			filtroCobrancaDocumentoItem.adicionarParametro(new ParametroSimples(FiltroCobrancaDocumentoItem.CONTA_GERAL_ID, Integer.valueOf(idConta)));
        			
        			Collection colecaoCobrancaDocumentoItem = fachada.pesquisar(filtroCobrancaDocumentoItem, CobrancaDocumentoItem.class.getName());
        			
        			if (conta.getReferenciaContabil().intValue() >= sistemaParametro.getAnoMesFaturamento().intValue() && colecaoCobrancaDocumentoItem != null && !colecaoCobrancaDocumentoItem.isEmpty()) {
        				String contasComExtrato = "";
        				
        				if (cancelarContaActionForm.getContasEmExtratoDebito() != null) {
        					contasComExtrato = cancelarContaActionForm.getContasEmExtratoDebito() + ", ";
        				}
        				
        				contasComExtrato = contasComExtrato + Util.formatarAnoMesParaMesAno(conta.getReferencia());
        				cancelarContaActionForm.setContasEmExtratoDebito(contasComExtrato);
        				
        			}
        		}
        	}
        }
    	
        /******************************************************************
         * Verifica se o Usuário tem Permissao Especial para
         * Cancelar Conta sem RA
         * 
         * Ivan Sérgio
         * 28/01/2008
         ******************************************************************/
    	Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		boolean usuarioPermissaoCancelarSemRA = fachada.verificarPermissaoCancelarContaSemRA(usuario);
		
		if (!usuarioPermissaoCancelarSemRA) {
			// [FS0001] - Verificar Existência de RA
	        fachada.verificarExistenciaRegistroAtendimento(new Integer(idImovel), 
	        		"atencao.conta_existencia_registro_atendimento", EspecificacaoTipoValidacao.ALTERACAO_CONTA);
		}
    	
        //Carregar: Lista dos motivos de cancelamento da conta 
        if (sessao.getAttribute("colecaoMotivoCancelamentoConta") == null){
        
        	FiltroMotivoCancelamentoConta filtroMotivoCancelamentoConta = 
        	new FiltroMotivoCancelamentoConta(FiltroMotivoCancelamentoConta.DESCRICAO_MOTIVO_CANCELAMENTO_CONTA);
        	
        	filtroMotivoCancelamentoConta.adicionarParametro(
        	new ParametroSimples(FiltroMotivoCancelamentoConta.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        
        	Collection colecaoMotivoCancelamentoConta = fachada.pesquisar(filtroMotivoCancelamentoConta,
        			ContaMotivoCancelamento.class.getName());
        
        	if (colecaoMotivoCancelamentoConta == null || colecaoMotivoCancelamentoConta.isEmpty()){
        	throw new ActionServletException(
                    "atencao.pesquisa.nenhum_registro_tabela", null,
                    "MOTIVO_CANCELAMENTO_CONTA");
        	}
        
        	// Disponibiliza a coleção pela sessão
        	sessao.setAttribute("colecaoMotivoCancelamentoConta", colecaoMotivoCancelamentoConta);
        
        }
        
        cancelarContaActionForm.setContaSelected(contaSelected);
        cancelarContaActionForm.setMotivoCancelamentoContaID( "" );

        return retorno;
    }

}
