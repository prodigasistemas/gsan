package gcom.gui.cobranca.contratoparcelamento;

import gcom.cobranca.contratoparcelamento.ContratoParcelamentoCliente;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoCliente;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RetornarContratoParcelamentoPesquisarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Inicializacoes de variaveis
		ActionForward retorno = actionMapping
				.findForward("retornoPesquisa");
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarContratoParcelamentoActionForm form = (PesquisarContratoParcelamentoActionForm) actionForm;
		
		String numeroContrato = form.getNumeroContrato();
		String dataContrato = form.getDataContrato();
		String loginUsuario = form.getLoginUsuario();
		String clienteAutocomplete = form.getAutocompleteCliente();
		String indicadorSituacao = form.getIndicadorSituacao();
		
		FiltroContratoParcelamentoCliente filtro = new FiltroContratoParcelamentoCliente();	
		filtro.adicionarParametro(new ParametroNulo(FiltroContratoParcelamentoCliente.ID_CLIENTE_SUPERIOR));
		
		boolean peloMenosUm = false;
		
		if(numeroContrato != null && !numeroContrato.equals("")){
			peloMenosUm = true;
				filtro.adicionarParametro(new ComparacaoTexto(
						FiltroContratoParcelamentoCliente.NUMERO_CONTRATO, numeroContrato));
		}
		
		if(dataContrato != null && !dataContrato.equals("")){
			peloMenosUm = true;
				filtro.adicionarParametro(new ParametroSimples(
						FiltroContratoParcelamentoCliente.DATA_CONTRATO, dataContrato));
		}
		
		if(indicadorSituacao != null && !indicadorSituacao.equals("")){
			peloMenosUm = true;
			
			//Encerrados
			if(indicadorSituacao.equals("2")){
				filtro.adicionarParametro(new ParametroSimplesDiferenteDe(
						FiltroContratoParcelamentoCliente.PARCEL_SITUACAO_ID, ParcelamentoSituacao.NORMAL + ""));
			//Não Encerrados
			}else if(indicadorSituacao.equals("1")){
				filtro.adicionarParametro(new ParametroSimples(
						FiltroContratoParcelamentoCliente.PARCEL_SITUACAO_ID, indicadorSituacao));
			}
		}
		
		if (loginUsuario != null && !loginUsuario.trim().equals("")){
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getLoginUsuario()));
			Collection colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				
				peloMenosUm = true;
				filtro.adicionarParametro(new ParametroSimples(
						FiltroContratoParcelamentoCliente.USUARIO_RESPONSAVEL_ID, usuario.getId()));
			}
		}else{
			sessao.removeAttribute("usuarioResponsavel");
		}
		
		if (clienteAutocomplete != null && !"".equals(clienteAutocomplete)
				&& clienteAutocomplete.contains("-")){
			int id = Integer.parseInt(clienteAutocomplete.split(" - ")[0].trim());
			peloMenosUm = true;
			filtro.adicionarParametro(new ComparacaoTexto(
					FiltroContratoParcelamentoCliente.ID_CLIENTE, id+""));
		}else{
			sessao.removeAttribute("cliente");
		}
		
		if (!peloMenosUm){
			ActionServletException ex = new ActionServletException("atencao.filtro.nenhum_parametro_informado");
			ex.setUrlBotaoVoltar("/gsan/exibirContratoParcelamentoPesquisar.do?indicadorUsoTodos=1&tipoConsulta=contratoAnterior&indicadorPesquisaApenasContEncerrados=1");
			throw ex;
		}
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoCliente.CONTRATO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoCliente.CLIENTE);
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtro, ContratoParcelamentoCliente.class.getName());
		Collection collContratoParcelamento = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		//Validações 
		if (collContratoParcelamento == null || collContratoParcelamento.isEmpty()) {
			ActionServletException ex = new ActionServletException("atencao.pesquisa.nenhumresultado", null, "contratoParcelamento");
			ex.setUrlBotaoVoltar("/gsan/exibirContratoParcelamentoPesquisar.do?indicadorUsoTodos=1&tipoConsulta=contratoAnterior&indicadorPesquisaApenasContEncerrados=1");
			throw ex;
		} else {
			sessao.setAttribute("collContratoParcelamento", collContratoParcelamento);

		}		
		
		String popup = (String) sessao.getAttribute("popup");
		if (popup != null && popup.equals("2")) {
			sessao.setAttribute("popup", popup);
		} else {
			sessao.removeAttribute("popup");
		}
		
		return retorno;
	}

}
