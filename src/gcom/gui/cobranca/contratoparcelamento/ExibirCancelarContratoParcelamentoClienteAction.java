package gcom.gui.cobranca.contratoparcelamento;

import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cobranca.contratoparcelamento.ContratoParcelamento;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoCliente;
import gcom.cobranca.parcelamento.FiltroParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamento.ParcelamentoMotivoDesfazer;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * [UC1140] Cancelar Contrato de Parcelamento por Cliente
 * 
 * @author Rômulo Aurélio
 * @date 12/05/2011
 */
public class ExibirCancelarContratoParcelamentoClienteAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirCancelarContratoParcelamentoClienteAction");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		CancelarContratoParcelamentoClienteActionForm form = (CancelarContratoParcelamentoClienteActionForm) actionForm;

		if (sessao.getAttribute("fecharPopupCancelarContrato") != null
				&& !sessao.getAttribute("fecharPopupCancelarContrato").equals("")) {
			sessao.setAttribute("idContrato", form.getIdClienteContrato());
		} else {

			String numeroContratoParcelamento = httpServletRequest
					.getParameter("numeroParcelamento");
			String idClienteContrato = httpServletRequest
				.getParameter("idClienteContrato");
			form.setIdClienteContrato(idClienteContrato);
	
			ContratoParcelamento contratoParcelamento = fachada
					.pesquisarContratoParcelamento(numeroContratoParcelamento);
	
			// [FS0003] – Verificar existência de contrato com o número recebido
			if (contratoParcelamento == null) {
				throw new ActionServletException(
						"atencao.numero_contrato_parcelamento_inexistente", null,
						"" + numeroContratoParcelamento);
			}
	
			// [FS0008] - Verificar possibilidade de cancelamento do contrato de
			// parcelamento por cliente
	
			fachada.verificarPossibilidadeCancelamentoContratoParcelamento(contratoParcelamento);
	
			//2.0
			
			this.montarDadosExibicao(form, contratoParcelamento, fachada, sessao);
			
			sessao.setAttribute("contratoParcelamento", contratoParcelamento);
		}
		
		return retorno;
		

	}

	//2
	public void montarDadosExibicao(CancelarContratoParcelamentoClienteActionForm form,
			ContratoParcelamento contratoParcelamento, Fachada fachada, HttpSession sessao){

		//2.1
		form.setNumeroContrato(contratoParcelamento.getNumero());
		
		//2.1.2
		this.montarDadosCliente(form, contratoParcelamento, fachada);

		//2.1.3
		form.setDataImplantacaoContrato(Util.formatarData(contratoParcelamento.getDataImplantacao()));

		//2.2.1
		form.setDataCancelamento(Util.formatarData(new Date()));
		
		
		//2.2.2
		FiltroParcelamentoMotivoDesfazer filtroParcelamentoMotivoDesfazer = new FiltroParcelamentoMotivoDesfazer();
		filtroParcelamentoMotivoDesfazer.adicionarParametro(new ParametroSimples(
				FiltroParcelamentoMotivoDesfazer.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroParcelamentoMotivoDesfazer.setCampoOrderBy(FiltroParcelamentoMotivoDesfazer.DESCRICAO);
		
		Collection colecaoParcelamentoMotivoDesfazer = fachada.pesquisar(filtroParcelamentoMotivoDesfazer, ParcelamentoMotivoDesfazer.class.getName());
		if (colecaoParcelamentoMotivoDesfazer == null
				|| colecaoParcelamentoMotivoDesfazer.isEmpty()) {
			throw new ActionServletException("atencao.sem_dados_para_selecao", null, "cobranca.parcel_motivo_desfazer");
		}
			sessao.setAttribute("colecaoParcelamentoMotivoDesfazer",colecaoParcelamentoMotivoDesfazer);		
	}
	
	public void montarDadosCliente(CancelarContratoParcelamentoClienteActionForm form,
			ContratoParcelamento contratoParcelamento, Fachada fachada) {

		// 2.1.2 Dados Cliente

		ContratoParcelamentoCliente contratoParcelamentoCliente = fachada.pesquisarClienteContrato(contratoParcelamento.getId());

		// 2.1.2
		form.setIdCliente("" + contratoParcelamentoCliente.getCliente().getId());
		form.setNomeCliente(contratoParcelamentoCliente.getCliente().getNome());
		form.setCnpjCliente(contratoParcelamentoCliente.getCliente().getCnpjFormatado());

		if (contratoParcelamento.getRelacaoCliente() != null) {

			FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
			filtroClienteRelacaoTipo.adicionarParametro(
					new ParametroSimples(FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID,
							contratoParcelamento.getRelacaoCliente().getId()));

			Collection colecaoClienteRelacaoTipo = fachada.pesquisar(filtroClienteRelacaoTipo,ClienteRelacaoTipo.class.getName());

			ClienteRelacaoTipo clienteRelacaoTipo = (ClienteRelacaoTipo) colecaoClienteRelacaoTipo
					.iterator().next();

			form.setDescricaoClienteRelacaoTipo(clienteRelacaoTipo.getDescricao());

		}


	}

}
