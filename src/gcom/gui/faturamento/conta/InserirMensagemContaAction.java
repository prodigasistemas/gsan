package gcom.gui.faturamento.conta;


import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.conta.ContaMensagem;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class InserirMensagemContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {


		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirMensagemContaActionForm inserirMensagemContaActionForm = (InserirMensagemContaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		//HttpSession sessao = httpServletRequest.getSession(false);
		// Variavel para testar se o campo naum obrigatorio esta vazio
		String referenciaFaturamento = inserirMensagemContaActionForm.getReferenciaFaturamento();
		String mensagemConta01 = inserirMensagemContaActionForm.getMensagemConta01();
		String mensagemConta02 = inserirMensagemContaActionForm.getMensagemConta02();
		String mensagemConta03 = inserirMensagemContaActionForm.getMensagemConta03();
		String grupoFaturamento = inserirMensagemContaActionForm.getGrupoFaturamento();
		String gerenciaRegional = inserirMensagemContaActionForm.getGerenciaRegional();
		String localidade = inserirMensagemContaActionForm.getLocalidade();
		String[] setorComercial = inserirMensagemContaActionForm.getSetorComercial();
		String[] quadra = inserirMensagemContaActionForm.getQuadra();
		
		ContaMensagem contaMensagem = new ContaMensagem();
		
		HttpSession sessao = httpServletRequest.getSession(false);				
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		/*
		 * [UC0107] Registrar Transação
		 * 
		 */

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_CONTA_MENSAGEM_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_CONTA_MENSAGEM_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		// [UC0107] -Fim- Registrar Transação
		
		if (Util.validarAnoMes(referenciaFaturamento)){
			throw new ActionServletException(
				"atencao.ano_mes_invalido");
		}else{
			Integer mes = new Integer (referenciaFaturamento.substring(0, 2));
			Integer ano = new Integer (referenciaFaturamento.substring(3, 7));
			
			if (mes <= 0 || mes > 12){
				throw new ActionServletException(
					"atencao.ano_mes_invalido");
			}
			
			if (ano < 1900){
				throw new ActionServletException(
					"atencao.ano_mes_invalido");
			}
			
			if (referenciaFaturamento != null && !referenciaFaturamento.equalsIgnoreCase("")){
				Integer referenciaFaturametoTratado = new Integer(Util.formatarMesAnoParaAnoMesSemBarra(referenciaFaturamento));
				
					contaMensagem.setAnoMesRreferenciaFaturamento(referenciaFaturametoTratado);
			}
		}
		
		if (referenciaFaturamento != null && !referenciaFaturamento.trim().equals("")) {
			if (Util.validarAnoMes(referenciaFaturamento)) {
				new ActionServletException(
						"atencao.ano_mes_invalido",
						null, referenciaFaturamento);
			}
		}
		
		if (mensagemConta01 != null && !mensagemConta01.equalsIgnoreCase("")){
			contaMensagem.setDescricaoContaMensagem01(mensagemConta01);
		}
		if (mensagemConta02 != null && !mensagemConta02.equalsIgnoreCase("")){
			contaMensagem.setDescricaoContaMensagem02(mensagemConta02);
		}
		if (mensagemConta03 != null && !mensagemConta03.equalsIgnoreCase("")){
			contaMensagem.setDescricaoContaMensagem03(mensagemConta03);
		}
		
		if (grupoFaturamento != null && !grupoFaturamento.equalsIgnoreCase("")){
			FaturamentoGrupo fg = new FaturamentoGrupo();
			fg.setId(new Integer(grupoFaturamento));
			contaMensagem.setFaturamentoGrupo(fg);
		}
		
		if (gerenciaRegional != null && !gerenciaRegional.equalsIgnoreCase("")){
			GerenciaRegional gr = new GerenciaRegional();
			gr.setId(new Integer(gerenciaRegional));
			contaMensagem.setGerenciaRegional(gr);
		}
		
		if (localidade != null && !localidade.equalsIgnoreCase("")){
			Localidade loc = new Localidade();
			loc.setId(new Integer(localidade));
			contaMensagem.setLocalidade(loc);
		}
		
//		if (setorComercial != null && !setorComercial.equalsIgnoreCase("")){
//			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
//			
//			filtroSetorComercial.adicionarParametro(
//					new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidade));
//			filtroSetorComercial.adicionarParametro(
//					new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercial));
//			filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade");
//			Collection colecaoSC =  fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
//			SetorComercial sc = (SetorComercial) colecaoSC.iterator().next();
//			
//			/*SetorComercial sc = new SetorComercial();
//			sc.setId(new Integer(setorComercial));*/
//			contaMensagem.setSetorComercial(sc);
//			
//		}
		
		contaMensagem.setUltimaAlteracao(new java.util.Date());
		
//		 regitrando operacao
		contaMensagem.setOperacaoEfetuada(operacaoEfetuada);
		contaMensagem.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(contaMensagem);

//		Integer i = 0;
		
//		if (contaMensagem.getSetorComercial() == null){
//			fachada.inserirMensagemConta(contaMensagem, null);
//		} else {
			fachada.inserirMensagemConta(contaMensagem, setorComercial, quadra);
//	    }
		
		//Integer i = (Integer) fachada.inserirMensagemConta(contaMensagem, contaMensagem.getSetorComercial().getId());
		
		montarPaginaSucesso(httpServletRequest, "Mensagem da Conta com referência " + referenciaFaturamento +" incluída com sucesso.",
				"Inserir outra Mensagem de Conta",
				"exibirInserirMensagemContaAction.do?menu=sim" );
		
		
//		montarPaginaSucesso(httpServletRequest, "Mensagem da Conta com referência " + referenciaFaturamento +" incluída com sucesso.",
//				"Inserir outra Mensagem de Conta",
//				"exibirInserirMensagemContaAction.do?menu=sim",
//				"exibirAtualizarMensagemContaAction.do?idMensagemConta="+ i,
//				"Atualizar Mensagem da Conta Inserida");
		
		
		return retorno;
	}
}
