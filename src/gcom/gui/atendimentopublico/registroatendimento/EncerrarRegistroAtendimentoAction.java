package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0435] Encerrar Registro Atendimento
 * 
 * @author Leonardo Regis
 * @created 24 de Julho de 2006
 */
public class EncerrarRegistroAtendimentoAction extends GcomAction {

	// Fachada
	Fachada fachada = Fachada.getInstancia();
	
	/**
	 * [UC0435] Encerrar Registro Atendimento
	 * 
	 * [UC0107] Registrar Transação
	 * 
	 * @author Leonardo Regis
	 * @date   18/08/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta Retorno (Forward = Sucesso)
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Form
		EncerrarRegistroAtendimentoActionForm encerrarRegistroAtendimentoActionForm = (EncerrarRegistroAtendimentoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
        
		// [UC0107] Registrar Transação
		RegistradorOperacao registradorOperacao = 
			new RegistradorOperacao(
				Operacao.OPERACAO_REGISTRO_ATENDIMENTO_ENCERRAR,
				new UsuarioAcaoUsuarioHelper(usuario,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_REGISTRO_ATENDIMENTO_ENCERRAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		
		// Tramite
		RegistroAtendimento registroAtendimento = null;
		RegistroAtendimentoUnidade registroAtendimentoUnidade = null;

		if (new Integer(encerrarRegistroAtendimentoActionForm.getMotivoEncerramentoId()).intValue() != 
			ConstantesSistema.NUMERO_NAO_INFORMADO) {
		
			// Recupera informações do trâmite
			registroAtendimento = getDadosEncerramento(encerrarRegistroAtendimentoActionForm);
            
            // Validamos se possivel criar um novo RA
            Object[] arrayValidaGeracaoNovoRA = validarGeracaoNovoRA( 
                    registroAtendimento,
                    httpServletRequest,
                    actionMapping );
            
            if ( ( Boolean ) arrayValidaGeracaoNovoRA[2] ){
                return ( ActionForward ) arrayValidaGeracaoNovoRA[0];
            }
            
            Boolean confirmadoGeracaoNovoRA = ( Boolean )arrayValidaGeracaoNovoRA[1];
            
			registroAtendimentoUnidade = getDadosRegistroAtendimentoUnidade(usuario, registroAtendimento, encerrarRegistroAtendimentoActionForm);
			
			// Faz as validações de tramitação
			fachada.validarEncerramentoRA(registroAtendimento);
            
            // Caso exista a possibilidade de geração de um novo registro de 
            // atendimento
			
			
			Integer idDebitoTipo = null;
			BigDecimal valorDebito = null;
			Integer qtdParcelas = null;
			String percentualCobranca = null;
			
			if (encerrarRegistroAtendimentoActionForm.getMotivoEncerramentoId() != null &&
				encerrarRegistroAtendimentoActionForm.getMotivoEncerramentoId().equals(String.valueOf(AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO)) &&
				encerrarRegistroAtendimentoActionForm.getIdTipoDebito() != null &&
				!encerrarRegistroAtendimentoActionForm.getIdTipoDebito().equals("")){
				
				
				if(encerrarRegistroAtendimentoActionForm.getMotivoNaoCobranca() == null || 
				encerrarRegistroAtendimentoActionForm.getMotivoNaoCobranca().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ){

					idDebitoTipo = 
						new Integer(encerrarRegistroAtendimentoActionForm.getIdTipoDebito());
					
					valorDebito = 
						Util.formatarMoedaRealparaBigDecimal(encerrarRegistroAtendimentoActionForm.getValorDebito());
					
					qtdParcelas = 
						new Integer(encerrarRegistroAtendimentoActionForm.getQuantidadeParcelas());
					
					percentualCobranca = encerrarRegistroAtendimentoActionForm.getPercentualCobranca();
					
				}
				else{
					ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
					servicoNaoCobrancaMotivo.setId(
							new Integer(encerrarRegistroAtendimentoActionForm.getMotivoNaoCobranca()));
					
					registroAtendimento.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);
				}
				
			}
			
			// Encerrar RA
			fachada.encerrarRegistroAtendimento(registroAtendimento, 
				registroAtendimentoUnidade,
				usuario,
				idDebitoTipo, 
				valorDebito, 
				qtdParcelas, 
				percentualCobranca,
                confirmadoGeracaoNovoRA,null,false);
			
			// Setando Operação
			registroAtendimento.setOperacaoEfetuada(operacaoEfetuada);
			registroAtendimento.adicionarUsuario(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(registroAtendimento);
			
			// Setando Operação
			registroAtendimentoUnidade.setOperacaoEfetuada(operacaoEfetuada);
			registroAtendimentoUnidade.adicionarUsuario(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(registroAtendimentoUnidade);			
			
			// [FS008] Monta a página de sucesso
			montarPaginaSucesso(httpServletRequest,	
				"Registro de Atendimento "+registroAtendimento.getId()+" encerrado com sucesso!", 
				"Manter Registro de Atendimento",
				"exibirFiltrarRegistroAtendimentoAction.do?menu=sim",
				"exibirConsultarRegistroAtendimentoAction.do?numeroRA="+ registroAtendimento.getId().toString(), 
				"Voltar");
		}

		return retorno;
	}

	/**
	 * Carrega Trâmite com informações vindas da tela 
	 *
	 * @author Leonardo Regis
	 * @date 18/08/2006
	 *
	 * @param form
	 */
	private RegistroAtendimento getDadosEncerramento(EncerrarRegistroAtendimentoActionForm form) {
		RegistroAtendimento registroAtendimento = new RegistroAtendimento();
		
		// Registro de Atendimento
		registroAtendimento.setId(new Integer(form.getNumeroRA()));
		registroAtendimento.setRegistroAtendimento(
			Util.converteStringParaDateHora(form.getDataAtendimento()+" "+form.getHoraAtendimento()));
		
		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = 
			this.consultaAtendimentoMotivoEncerramento(new Integer(form.getMotivoEncerramentoId()));

		registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
		
		RegistroAtendimento registroAtendimentoDuplicidade = null;
		if (form.getNumeroRAReferencia() != null && !form.getNumeroRAReferencia().equals("")) {
			registroAtendimentoDuplicidade = new RegistroAtendimento();
			registroAtendimentoDuplicidade.setId(new Integer(form.getNumeroRAReferencia()));
		}
		
		registroAtendimento.setRegistroAtendimentoDuplicidade(registroAtendimentoDuplicidade);
		registroAtendimento.setDataEncerramento(Util.converteStringParaDateHora(form.getDataEncerramento()+" "+form.getHoraEncerramento()+":00"));
		
		if(form.getParecerEncerramento() != null && 
			!form.getParecerEncerramento().equals("") && 
			form.getParecerEncerramento().length() > 400){
			
			String[] msg = new String[2];
			msg[0]="Parecer";
			msg[1]="400";
			
			throw new ActionServletException("atencao.execedeu_limit_observacao",null,msg);
			
		}else if (form.getParecerEncerramento() != null && 
			!form.getParecerEncerramento().equals("")) {
			
			registroAtendimento.setParecerEncerramento(form.getParecerEncerramento().toUpperCase());	
		}
		
		registroAtendimento.setUltimaAlteracao(new Date());
        registroAtendimento.setSolicitacaoTipoEspecificacao( new SolicitacaoTipoEspecificacao() );
        registroAtendimento.getSolicitacaoTipoEspecificacao().setId( new Integer ( form.getEspecificacaoId() ) );
		
		return registroAtendimento;
	}

	/**
	 * Carrega Registro Atendimento Unidade a partir do usuário logado. 
	 *
	 * @author Leonardo Regis
	 * @date 26/08/2006
	 *
	 * @param usuario
	 * @param registroAtendimento
	 */
	private RegistroAtendimentoUnidade getDadosRegistroAtendimentoUnidade(Usuario usuario, RegistroAtendimento registroAtendimento, EncerrarRegistroAtendimentoActionForm form) {
		RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
		
		registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);
		registroAtendimentoUnidade.setUnidadeOrganizacional(usuario.getUnidadeOrganizacional());
		registroAtendimentoUnidade.setUsuario(usuario);
		AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
		atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
		registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
		registroAtendimentoUnidade.setUltimaAlteracao(form.getDataConcorrenciaRA());

		return registroAtendimentoUnidade;
	}

	/**
	 * Consulta Atendimento Motivo Encerramento 
	 *
	 * @author Rafael Pinto
	 * @date 18/10/2006
	 *
	 * @param id do Atendimento Motivo Encerramento
	 */
	private AtendimentoMotivoEncerramento consultaAtendimentoMotivoEncerramento(Integer id){
		
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = 
			new FiltroAtendimentoMotivoEncerramento();

		filtroAtendimentoMotivoEncerramento.adicionarParametro(
			new ParametroSimples(FiltroAtendimentoMotivoEncerramento.ID,id));

		Collection colecao = 
			Fachada.getInstancia().pesquisar(filtroAtendimentoMotivoEncerramento, 
					AtendimentoMotivoEncerramento.class.getName());
		
		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento =
			(AtendimentoMotivoEncerramento) Util.retonarObjetoDeColecao(colecao);
		
		return atendimentoMotivoEncerramento;
	}
    
    /**
     * 
     * [UC0435] - Encerrar Registro de Atendimento
     * 
     * Método verifica se existe a possibilidade de ser gerada um novo RA.
     * Caso positivo, retorna uma tela de pergunta ao usuário para verificar
     * se ele deseja gerar esse novo RA ou não. Caso não seja necessária a pegun-
     * ta, retorna com a tela de sucesso normal ao fluxo.
     *
     * @author bruno
     * @date 15/04/2009
     *
     * @param registroAtendimento: Registro do atendimento.
     * @param request: Onde se serão informados os parametros para geração da página
     * @param actionMapping: Necessário para geração da página
     * 
     * @return Object[3]
     * 
     *      Object[0]: ActionFoward com a tela a ser mostrada
     *      Object[1]: Se o usuário confimou ou não a inserção do novo ra
     *      Object[2]: Se será redirecionado ao usuário perguntando se será
     *                 inserido ou não o novo RA.
     */
    private Object[] validarGeracaoNovoRA( 
            RegistroAtendimento registroAtendimento, 
            HttpServletRequest request,
            ActionMapping actionMapping ){
        
        Object[] retorno = new Object[3];
        
        // Verificamos se ja foi confimado...
        retorno[1] = 
            ( request.getParameter("confirmado") != null ? 
                    request.getParameter("confirmado").equals("ok") : 
                        null );
        
        retorno[2] = new Boolean( Boolean.FALSE );
        
        if ( retorno[1] == null ){
            Fachada fachada = Fachada.getInstancia();
            
            FiltroSolicitacaoTipoEspecificacao filtro = 
                new FiltroSolicitacaoTipoEspecificacao();
            
            filtro.adicionarParametro( new ParametroSimples( 
                    FiltroSolicitacaoTipoEspecificacao.ID, 
                    registroAtendimento.
                        getSolicitacaoTipoEspecificacao().getId() ) );
            
            filtro.adicionarCaminhoParaCarregamentoEntidade( 
                    "solicitacaoTipoEspecificacaoNovoRA" );
            Collection<SolicitacaoTipoEspecificacao> 
                colSolicitacaoTipoEspecificacao = 
                    fachada.pesquisar( 
                            filtro, 
                            SolicitacaoTipoEspecificacao.class.getName() );
            
            SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = 
                ( SolicitacaoTipoEspecificacao ) 
                    Util.retonarObjetoDeColecao( 
                            colSolicitacaoTipoEspecificacao );
            
            if ( solicitacaoTipoEspecificacao.
                    getSolicitacaoTipoEspecificacaoNovoRA()!= null ){
                retorno[2] = new Boolean( Boolean.TRUE );
                
                registroAtendimento.setSolicitacaoTipoEspecificacao( 
                        solicitacaoTipoEspecificacao );
                
                request.setAttribute("caminhoActionConclusao",
                        "/gsan/encerrarRegistroAtendimentoAction.do");
                request.setAttribute("cancelamento", "TRUE");
                request.setAttribute("nomeBotao1", "Sim");
                request.setAttribute("nomeBotao2", "Não");
    
                retorno[0] = montarPaginaConfirmacao(
                        "atencao.encerrar_ra_confirma_geracao_novo_ra",
                        request, 
                        actionMapping, 
                        solicitacaoTipoEspecificacao.
                            getSolicitacaoTipoEspecificacaoNovoRA().
                                getDescricao() );
                return retorno;
            }
        }       
        
        retorno[0] = actionMapping.findForward("telaSucesso");
        return retorno;
    }	
    
}
