package gcom.gui.faturamento.conta;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Abrangencia;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirManterContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirManterConta");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Instância do formulário que está sendo utilizado
		ManterContaActionForm manterContaActionForm = (ManterContaActionForm) actionForm;
		String limparForm = httpServletRequest.getParameter("limparForm");

		// DEFINIÇÃO QUE IRÁ AUXILIAR O RETORNO DOS POPUPS
		sessao.setAttribute("UseCase", "MANTERCONTA");

		// Removendo coleções da sessão
		sessao.removeAttribute("contaID");
		if (limparForm != null && !limparForm.equalsIgnoreCase("")) {
			sessao.removeAttribute("colecaoContaImovel");
		}
		
		if (sessao.getAttribute("erroConcorrencia") != null && !sessao.getAttribute("erroConcorrencia").equals("")) {
			sessao.removeAttribute("erroConcorrencia");
			throw new ActionServletException(
                    "atencao.atualizacao.timestamp");
		}
		
		/*
		 * Pesquisar o imóvel a partir da matrícula do imóvel
		 * ======================================================================
		 */
		String idImovel = manterContaActionForm.getIdImovel();
		String idImovelRequest = httpServletRequest.getParameter("idImovelRequest");

		if ((idImovel != null && !idImovel.equalsIgnoreCase("")) ||
			(idImovelRequest != null && !idImovelRequest.equalsIgnoreCase(""))) {

			/*FiltroImovel filtroImovel = new FiltroImovel();

			// Objetos que serão retornados pelo hobernate
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial.codigo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("quadra.numeroQuadra");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao.descricao");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao.descricao");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto.percentual");

			// Realizando a pesquisa do imóvel a partir da matrícula recebida
			if (idImovel != null && !idImovel.equalsIgnoreCase("")){
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, idImovel));
			}
			else{
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, idImovelRequest));
			}
			
			/*
        	 * Apenas imóveis que não foram excluídos poderão inserir conta
        	 * (IMOV_ICEXCLUSAO = 1)
        	 */
			/*
        	filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO, 
        	Imovel.IMOVEL_EXCLUIDO));
        	*/
             /** alterado por pedro alexandre dia 22/11/2006 
             * Recupera o usuário logado para passar no metódo de colocar conta em revisão
             * para verificar se o usuário tem abrangência para colocar uma conta em revisão
             * para o imóvel informado.
             */
            Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
            Collection colecaoImovel = new ArrayList();
			//Collection colecaoImovel = fachada.pesquisar(filtroImovel,Imovel.class.getName());
            //colecaoImovel.add(fachada.pesquisarImovelContaManter(filtroImovel,usuarioLogado));
            
            Imovel imovel = null;
            if (idImovel != null && !idImovel.equalsIgnoreCase("")){
                imovel = fachada.pesquisarImovelRegistroAtendimento(new Integer(idImovel));
            }
            else{
                imovel = fachada.pesquisarImovelRegistroAtendimento(new Integer(idImovelRequest));
            }
            
            colecaoImovel.add(imovel);
            
    		// ------------ CONTROLE DE ABRANGENCIA ----------------
    		Abrangencia abrangencia = new Abrangencia(usuarioLogado, imovel);

    		if (!fachada.verificarAcessoAbrangencia(abrangencia)) {
    			
    			throw new ActionServletException("atencao.acesso.negado.abrangencia");
    		}

    		// ------------ FIM CONTROLE DE ABRANGENCIA ------------
            
            /** fim alteração ***************************************************************/
            
			// [FS0002] - Verificar existência da matrícula do imóvel
			if (colecaoImovel == null || colecaoImovel.isEmpty()) {
				//throw new ActionServletException("atencao.imovel.inexistente");
				
				httpServletRequest.setAttribute("corInscricao", "exception");
				manterContaActionForm.setIdImovel("");
				manterContaActionForm.setInscricaoImovel("Matrícula Inexistente");
        		httpServletRequest.setAttribute("nomeCampo", "idImovel");
        		manterContaActionForm.setNomeClienteUsuario("");
        		manterContaActionForm.setSituacaoAguaImovel("");
        		manterContaActionForm.setSituacaoEsgotoImovel("");
        		sessao.removeAttribute("colecaoContaImovel");
			}
			else{

				Imovel objetoImovel = (Imovel) Util
						.retonarObjetoDeColecao(colecaoImovel);
	
				/*
				 * Pesquisar o cliente usuário do imóvel selecionado
				 * ======================================================================
				 */
				/*FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
	
				// Objetos que serão retornados pelo hibernate.
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("cliente.nome");
				
				
				
				
				
				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
						ClienteRelacaoTipo.USUARIO));
				filtroClienteImovel.adicionarParametro(new ParametroNulo(
						FiltroClienteImovel.FIM_RELACAO_MOTIVO));
	
				Collection colecaoClienteImovel = fachada.pesquisar(
						filtroClienteImovel, ClienteImovel.class.getName());
	*/
	
				//ClienteImovel objetoClienteImovel = (ClienteImovel) Util
					//	.retonarObjetoDeColecao(colecaoClienteImovel);
				String matriculaImovel = null;
				
				if (idImovel != null && !idImovel.equalsIgnoreCase("")){
					matriculaImovel = idImovel;
				}
				else{
					matriculaImovel = idImovelRequest;
				}
				
				Cliente cliente = fachada.pesquisarClienteUsuarioImovel(new Integer(matriculaImovel));

				// Verifica existência do cliente usuário
				if (cliente == null) {
					throw new ActionServletException("atencao.naocadastrado", null,
							"cliente do tipo usuário foi");
				}

				
				/*
				 * O sistema exibe uma lista das contas do imóvel com situação atual
				 * normal, retificada ou incluída
				 * ======================================================================
				 */
	
				Collection colecaoContaImovel = fachada.obterContasImovelManter(objetoImovel, DebitoCreditoSituacao.NORMAL,
						DebitoCreditoSituacao.INCLUIDA, DebitoCreditoSituacao.RETIFICADA);

				SistemaParametro sistemaParametro = fachada
					.pesquisarParametrosDoSistema();
				
				if (sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta() != null
						&& sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta().equals(ConstantesSistema.SIM)) {
					colecaoContaImovel = fachada.obterColecaoSemContasEmContratoParcelamento(
							colecaoContaImovel);
				}
				
				if (httpServletRequest.getParameter("limpaTela")!= null
						&& !httpServletRequest.getParameter("limpaTela").equals("")){
					//qd volta da msg de 
					//O imóvel de matrícula {} não possui nenhuma conta
					manterContaActionForm.setNomeClienteUsuario("");
		    		manterContaActionForm.setSituacaoAguaImovel("");
		    		manterContaActionForm.setSituacaoEsgotoImovel("");
		    		sessao.removeAttribute("colecaoContaImovel");
				}else if ((colecaoContaImovel == null || colecaoContaImovel.isEmpty()) && sessao.getAttribute("cancelar") == null) {
//					 [FS0003] - Verificar existência de alguma conta
					throw new ActionServletException(
						"atencao.pesquisa.nenhuma.conta_imovel", "exibirManterContaAction.do?limpaTela=1", new Exception(),
							manterContaActionForm.getIdImovel());
				}
	
				// Carregando as informações do imóvel no formulário de exibição.
				if (idImovel == null || idImovel.equalsIgnoreCase("")){
					manterContaActionForm.setIdImovel(idImovelRequest);
				}
				
				manterContaActionForm.setInscricaoImovel(objetoImovel.getInscricaoFormatada());
				manterContaActionForm.setNomeClienteUsuario(cliente.getNome());
				manterContaActionForm.setSituacaoAguaImovel(objetoImovel.getLigacaoAguaSituacao().getDescricao());
				manterContaActionForm.setSituacaoEsgotoImovel(objetoImovel.getLigacaoEsgotoSituacao().getDescricao());
				
				
				//Ordenando a coleção por mês/ano de referência
	
		        Collections.sort((List) colecaoContaImovel, new Comparator() {
		            public int compare(Object a, Object b) {
		                int retorno = 0;
		                
		            	String anoMesReferencia1 = String.valueOf(((Conta) a).getReferencia());
		                String anoMesReferencia2 = String.valueOf(((Conta) b).getReferencia());
	
		                Integer ano1 = new Integer(anoMesReferencia1.substring(0, 4));
		                Integer ano2 = new Integer(anoMesReferencia2.substring(0, 4));
		                Integer mes1 = new Integer(anoMesReferencia1.substring(4, 6));
		                Integer mes2 = new Integer(anoMesReferencia2.substring(4, 6));
		                
		                if (ano1 > ano2){
		                	retorno = 1;
		                }
		                else if (ano1 < ano2){
		                	retorno = -1;
		                }
		                else if (mes1 > mes2){
		                	retorno = 1;
		                }
		                else if (mes1 < mes2){
		                	retorno = -1;
		                }
		                
		                return retorno;
		            }
		        });
                
                // Coloca na sessão a coleção com as contas do imóvel selecionado
				sessao.setAttribute("colecaoContaImovel", colecaoContaImovel);
				
				if (idImovel != null && !idImovel.equalsIgnoreCase("")){
					sessao.setAttribute("imovel",idImovel);
					verificarSeContaRA(new Integer(idImovel),httpServletRequest,sessao,usuarioLogado);
				}
				else{
					sessao.setAttribute("imovel",idImovelRequest);
					verificarSeContaRA(new Integer(idImovelRequest),httpServletRequest,sessao,usuarioLogado);
				}
				
			}
		}
		
		/*
		 * Colocado por Raphael Rossiter em 03/11/2008
		 * Permite retificar um conjunto de contas a partir do manter conta.
		 */
		if (fachada.verificarPermissaoRetificarConjuntoConta((Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO))){
			httpServletRequest.setAttribute("retificarConjuntoConta", "OK");
		}
        
        /* Colocado por Bruno Barros em 05 de Janeiro de 2009
         * Verificamos se o usuário possue permissão especial para atualizar 
         * ou retificar contas pagas
         */
        
        boolean usuarioPodeAtualizarRetificarContasPagas =
            fachada.verificarPermissaoEspecial( 
                    PermissaoEspecial.ATUALIZAR_RETIFICAR_CONTAS_PAGAS, 
                    ( Usuario ) sessao.getAttribute(Usuario.USUARIO_LOGADO ) );
        
        httpServletRequest.setAttribute("usuarioPodeAtualizarRetificarContasPagas",
                    usuarioPodeAtualizarRetificarContasPagas );
        
        // *****************************************************************
		
		sessao.removeAttribute("cancelar");
		
		
		manterContaActionForm.setIdRA("1");
		
		return retorno;
	}
	
	/*[FS0040] – Verificar se a conta consta no Registro de Atendimento
	 * Vivianne Sousa - 09/02/2011
	 */
	public void verificarSeContaRA(Integer idImovel,
			HttpServletRequest httpServletRequest,HttpSession sessao,Usuario usuarioLogado){
		
		String habilitaRetificacaoContaRA = "2";
		Integer idRegistroAtendimento = null;
		SistemaParametro sistemaParametro = getFachada().pesquisarParametrosDoSistema();
		
		if(sistemaParametro.getIndicadorNormaRetificacao().equals(ConstantesSistema.SIM)){
			//Caso a Empresa esteja na Norma de Retificação de Conta 
			
			//Caso o usuário possua permissão especial habilitar esta conta para retificação
			boolean temPermissaoParaRetificarContaNorma = 
				getFachada().verificarPermissaoEspecial(PermissaoEspecial.RETIFICAR_CONTA_NORMA_REVISAO_FATURAMENTO, usuarioLogado);	
			
			if(temPermissaoParaRetificarContaNorma){
				habilitaRetificacaoContaRA = "1";
			}else{
				idRegistroAtendimento = getFachada().verificaSolicitacaoTipoEspecificacaoRA(idImovel);
				
				if(idRegistroAtendimento != null){
					habilitaRetificacaoContaRA = "3";
					//Caso exista a conta informada no Registro de Atendimento Conta 
					//(REGISTRO_ATENDIMENTO_CONTA com RGAT_ID = RGAT_ID do REGISTRO_ATENDIMENTO
					//e CNTA_ID = CNTA_ID da conta a ser retificada), habilitar esta conta para retificação
				}
			}			
		}else{
			habilitaRetificacaoContaRA = "1";
		}
		
		httpServletRequest.setAttribute("habilitaRetificacaoContaRA",habilitaRetificacaoContaRA);
		if(idRegistroAtendimento != null){
			httpServletRequest.setAttribute("idRA",idRegistroAtendimento.toString());
		}
		
		
	}

}
