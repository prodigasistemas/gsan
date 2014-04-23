package gcom.gui.faturamento.conta;

import gcom.atendimentopublico.registroatendimento.EspecificacaoTipoValidacao;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.FiltroMotivoRevisaoConta;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirColocarRevisaoContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirColocarRevisaoConta");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Instância do formulário que está sendo utilizado
        ColocarRevisaoContaActionForm colocarRevisaoContaActionForm = (ColocarRevisaoContaActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
        //Carregando o identificador das contas selecionadas
        String contaSelected = httpServletRequest.getParameter("conta");
        
    	//FiltroConta filtroConta = new FiltroConta();
    	//filtroConta.adicionarCaminhoParaCarregamentoEntidade("imovel.id");
    	//String idConta  = contaSelected.split("-")[0];
    	//filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.ID, idConta));
    	//filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.IMOVEL_ID);
    	//Collection colecaoConta = fachada.pesquisar(filtroConta, Conta.class.getName());
    	
    	//Conta contaSelecao = (Conta) colecaoConta.iterator().next();
    	//Conta contaSelecao = fachada.pesquisarContaRetificacao(new Integer(idConta));
    	String idImovel = httpServletRequest.getParameter("idImovel");
    	
        //[FS0001] - Verificar Existência de RA
        fachada.verificarExistenciaRegistroAtendimento(new Integer(idImovel), "atencao.conta_existencia_registro_atendimento",EspecificacaoTipoValidacao.ALTERACAO_CONTA); 
        
        //Carregar: Lista dos motivos de cancelamento da conta 
        if (sessao.getAttribute("colecaoMotivoRevisaoConta") == null){
        
        	FiltroMotivoRevisaoConta filtroMotivoRevisaoConta = 
        	new FiltroMotivoRevisaoConta(FiltroMotivoRevisaoConta.DESCRICAO_MOTIVO_REVISAO_CONTA);
        	
        	filtroMotivoRevisaoConta.adicionarParametro(
        	new ParametroSimples(FiltroMotivoRevisaoConta.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        
        	Collection colecaoMotivoRevisaoConta = fachada.pesquisar(filtroMotivoRevisaoConta,
        			ContaMotivoRevisao.class.getName());
        
        	if (colecaoMotivoRevisaoConta == null || colecaoMotivoRevisaoConta.isEmpty()){
        	throw new ActionServletException(
                    "atencao.pesquisa.nenhum_registro_tabela", null,
                    "MOTIVO_REVISAO_CONTA");
        	}
        
        	// Disponibiliza a coleção pela sessão
        	sessao.setAttribute("colecaoMotivoRevisaoConta", colecaoMotivoRevisaoConta);
        
        }
        
        colocarRevisaoContaActionForm.setContaSelected(contaSelected);
        
        return retorno;
    }

}
