package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelDoacao;
import gcom.cadastro.imovel.ImovelDoacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0389] - Inserir Imóvel Doação Action responsável pela pre-exibição da
 * pagina de inserir ImovelDoacao
 * 
 * @author César Araújo
 * @created 22 de agosto de 2006
 */
public class CancelarImovelDoacaoAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		/*** Declara e inicializa variáveis ***/
		Fachada fachada                                           = null;
		HttpSession sessao                                        = null;
		ActionForward retorno                                     = null;
		String[] idsCancelamento                                  = null;
		ImovelDoacao imovelDoacao                                 = null;
		Usuario usuarioCancelamento                               = null;
		FiltroImovelDoacao filtroImovelDoacao                     = null;
		Integer contadorImovelDoacaoCancelados                    = null;          
		ManutencaoRegistroActionForm manutencaoRegistroActionForm = null;

		/*** Procedimentos básicos para execução do método ***/
		retorno = actionMapping.findForward("telaSucesso");
		manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;
		fachada = Fachada.getInstancia();
		sessao = httpServletRequest.getSession(false);
		
		/*** Obtém os ids para cancelamento ***/
		idsCancelamento = manutencaoRegistroActionForm.getIdRegistrosRemocao();
		
		/*** Avalia se existem ids de imovel doacao válidos ***/
		if (idsCancelamento.length == 0) {
			throw new ActionServletException("atencao.manter_imovel_doacao_nenhuma_entidade_beneficente_selecionada");		
		}
		
		/*** Cria filtro imovel doacao***/
		filtroImovelDoacao = new FiltroImovelDoacao();
		contadorImovelDoacaoCancelados = new Integer(0);

        /** alterado por pedro alexandre dia 17/11/2006 
         * Recupera o usuário logado para passar no metódo de cancelar 
         * para verificar se o usuário tem abrangência para cancelar a doação
         */
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        
		/*** Manipula cada um dos ids de imovel doacao para cancelamento ***/
		for (String idCancelamento: idsCancelamento) {
			/*** Prepara o filtro para pesquisar o respectivo imovel doacao na base ***/
			filtroImovelDoacao.limparListaParametros();
    		filtroImovelDoacao.adicionarParametro(new ParametroSimples(FiltroImovelDoacao.ID, idCancelamento));
    		filtroImovelDoacao.adicionarCaminhoParaCarregamentoEntidade("imovel");
    		imovelDoacao = (ImovelDoacao)fachada.pesquisar(filtroImovelDoacao, ImovelDoacao.class.getName()).iterator().next();

    		/*** Cria e atribui o usuário de cancelamento ***/
    		usuarioCancelamento = new Usuario();
    		usuarioCancelamento.setId(((Usuario)sessao.getAttribute("usuarioLogado")).getId());
    		
    		/*** Atribui os dados que serão atualizados para o imovel doacao ***/
        	imovelDoacao.setDataCancelamento(new Date());
        	imovelDoacao.setUsuarioCancelamento(usuarioCancelamento);
        
        	fachada.atualizarImovelDoacao(imovelDoacao, usuarioLogado);
        	
        	contadorImovelDoacaoCancelados += 1;  
		}
		
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_CATEGORIA_INSERIR);
        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSAÇÃO ----------------//

        /*** Monta tela de sucesso ***/
		montarPaginaSucesso(httpServletRequest,
				            contadorImovelDoacaoCancelados+" Autorização(ões) para Doação Mensal do Imóvel "+imovelDoacao.getImovel().getId().toString()+" cancelada(s) com sucesso.",
				            "Cancelar outra Autorização para Doação Mensal", 
				            "exibirManterImovelDoacaoAction.do");

		return retorno;
	}
}
