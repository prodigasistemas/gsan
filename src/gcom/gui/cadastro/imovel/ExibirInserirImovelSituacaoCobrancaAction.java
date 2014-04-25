package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirInserirImovelSituacaoCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirInserirImovelSituacaoCobranca");

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
				
		// Mudar isso quando tiver esquema de segurança
		// HttpSession sessao = httpServletRequest.getSession(false);
		
		InserirImovelSituacaoCobrancaActionForm form = (InserirImovelSituacaoCobrancaActionForm) actionForm;
		
		String idImovel = httpServletRequest.getParameter("idImovel");
		
		if (idImovel == null || idImovel.equalsIgnoreCase("")){
			idImovel = form.getCodigoImovel();
		}
		
		form.setCodigoImovel(idImovel);
		form.setDataImplantacao(Util.formatarData(new Date()));
		String matriculaImovel = fachada.pesquisarInscricaoImovel(new Integer(idImovel));
		
		if (matriculaImovel != null && !matriculaImovel.equalsIgnoreCase("")){
			form.setMatriculaImovel(matriculaImovel);
		} else {
			throw new ActionServletException(
				"atencao.imovel.inexistente");
		}
		
		if (httpServletRequest.getParameter("objetoConsulta") != null){
			
			//	VERIFICANDO ESCRITORIO (Kassia Albuquerque)
			if ((form.getIdEscritorio() != null && !form.getIdEscritorio().equals(""))) {

				FiltroCliente filtroCliente = new FiltroCliente();
				filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, form.getIdEscritorio()));
				Collection colecaoEscritorio = fachada.pesquisar(filtroCliente,Cliente.class.getName());
				
				if (colecaoEscritorio != null && !colecaoEscritorio.isEmpty()) {
					
					Cliente escritorios = (Cliente) colecaoEscritorio.iterator().next();
					form.setNomeEscritorio(escritorios.getNome());
					
				} else {
					httpServletRequest.setAttribute("escritorioEncontrado", "exception");
					form.setIdEscritorio("");
					form.setNomeEscritorio("ESCRITÓRIO DE ADVOCACIA INEXISTENTE");
				}

			}
			
			//	VERIFICANDO ADVOGADO	(Kassia Albuquerque)
			if ((form.getIdAdvogado() != null && !form.getIdAdvogado().equals(""))) {

				FiltroCliente filtroCliente = new FiltroCliente();
				filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, form.getIdAdvogado()));
				Collection colecaoAdvogado = fachada.pesquisar(filtroCliente,Cliente.class.getName());
				
				if (colecaoAdvogado != null && !colecaoAdvogado.isEmpty()) {
					
					Cliente advogados = (Cliente) colecaoAdvogado.iterator().next();
					form.setNomeAdvogado(advogados.getNome());
					
				} else {
					httpServletRequest.setAttribute("advogadoEncontrado", "exception");
					form.setIdAdvogado("");
					form.setNomeAdvogado("ADVOGADO INEXISTENTE");
				}

			}
			
			// VERIFICANDO CLIENTE ALVO
			if (form.getIdClienteAlvo() != null && !form.getIdClienteAlvo().equalsIgnoreCase("")){
				String idCliente = form.getIdClienteAlvo();
				
				Cliente cliente = fachada.pesquisarClienteDigitado(new Integer(idCliente));
				if (cliente != null){
					FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
					filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_ID, idCliente));
					filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
					Collection colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
					
					if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()){
						form.setNomeClienteAlvo(cliente.getNome());
					} else {
						throw new ActionServletException("atencao.cliente_informado_nao_corresponde_imovel");
					}
					
				} else{
					throw new ActionServletException("atencao.cliente.inexistente");
				}
			}
			
			// SITUACAO COBRANCA
			if (form.getSituacaoCobranca() != null && !form.getSituacaoCobranca().equalsIgnoreCase("")){
				String idSituacaoCobranca = form.getSituacaoCobranca();
				FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
				filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(FiltroCobrancaSituacao.ID, idSituacaoCobranca));
				filtroCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("contaMotivoRevisao");
				
				Collection colecaoCobrancaSituacao = fachada.pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class.getName());
				CobrancaSituacao cobrancaSituacao = (CobrancaSituacao) colecaoCobrancaSituacao.iterator().next();
				
				if (cobrancaSituacao.getContaMotivoRevisao() == null){
					httpServletRequest.setAttribute("bloqueiaAnoMes", "sim");
				}
				
				// HABILITANDO OS CAMPOS ESCRITORIO E ADVOGADO (Kassia Albuquerque) 
				if (cobrancaSituacao.getIndicadorExigenciaAdvogado() != null && cobrancaSituacao.getIndicadorExigenciaAdvogado().equals(ConstantesSistema.INDICADOR_USO_ATIVO)){
					httpServletRequest.setAttribute("ativo", "ativo");
				}else{
					form.setIdAdvogado("");
					form.setNomeAdvogado("");
					form.setIdEscritorio("");
					form.setNomeEscritorio("");
				}
			}
		} else {
			Cliente cliente = fachada.pesquisarClienteUsuarioImovel (new Integer(form.getCodigoImovel()));
			
			form.setIdClienteAlvo(cliente.getId().toString());
			form.setNomeClienteAlvo(cliente.getNome());
		}

		this.pesquisarCobrancaSituacao(httpServletRequest, fachada, usuarioLogado, new Integer(idImovel));
		
		return retorno;

	}
	
	/**
	 * 
	 * [SB0004]  Selecionar Situações de Cobrança
     *
	 * @author Hugo Amorim
	 * @date 29/01/2010
	 * @param httpServletRequest
	 * @param fachada
	 * @param usuarioLogado
	 */
	private void pesquisarCobrancaSituacao(
			HttpServletRequest httpServletRequest, Fachada fachada, 
			Usuario usuarioLogado, Integer idImovel){
		
		boolean possuiPermissaoinformarQualquerSituacaoCobranca =
			fachada.verificarPermissaoEspecialAtiva(
					PermissaoEspecial.INFORMAR_QUALQUER_SITUACAO_DE_COBRANCA, usuarioLogado);
		//CRC3323 - comentado por Vivianne Sousa - analista:Fatima Sampaio - 12/05/2010
//		FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
//		/*
//		 * Caso o usuario não tenha permissão especial para informar qualquer situação de cobranca,
//		 *  o sistema retira da lista de situações de cobrança selecionadas as situações com indicação 
//		 *  de serem selecionadas apenas pelos usuários com permissão especial.
//		 */
//		if(!possuiPermissaoinformarQualquerSituacaoCobranca){
//			filtroCobrancaSituacao.adicionarParametro(
//					new ParametroSimples(FiltroCobrancaSituacao.INDICADOR_SELECAO_APENAS_COM_PERMISSAO,
//							ConstantesSistema.NAO));
//		}
//		
//		filtroCobrancaSituacao.adicionarParametro(
//				new ParametroSimples(FiltroCobrancaSituacao.INDICADOR_BLOQUEIO_INCLUSAO,
//						ConstantesSistema.NAO));
//		filtroCobrancaSituacao.adicionarParametro(
//				new ParametroSimples(FiltroCobrancaSituacao.INDICADOR_USO,
//						ConstantesSistema.SIM));
//		
//		filtroCobrancaSituacao.setCampoOrderBy(FiltroCobrancaSituacao.DESCRICAO);
		
//		Collection colecaoSituacaoCobranca = fachada.pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class.getName());
		
		
		Collection colecaoSituacaoCobranca  = fachada.pesquisarCobrancaSituacao(
				idImovel,possuiPermissaoinformarQualquerSituacaoCobranca);
		
		if(colecaoSituacaoCobranca == null || colecaoSituacaoCobranca.isEmpty()){
			//[FS0007] – Verificar lista de situações de cobrança para inclusão
			throw new ActionServletException("atencao.nao_e_possivel_novas_situacoes_cobranca");
		}else{
			httpServletRequest.setAttribute("colecaoSituacaoCobranca", colecaoSituacaoCobranca);
		}
	}

}
