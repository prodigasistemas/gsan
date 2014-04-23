package gcom.gui.faturamento.credito;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelCobrancaSituacao;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cobranca.CobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoARealizar;
import gcom.faturamento.credito.FiltroCreditoOrigem;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0195] Manter Crédito a Realizar Permite cancelar um ou mais créditos a
 * realizar de um determinado imóvel
 * 
 * @author Roberta Costa
 * @since 12/01/2006
 */
public class ExibirManterCreditoARealizarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterCreditoARealizar");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// Coleção de Tipo de Crédito
		FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
		Collection<CreditoTipo> collectionCreditoTipo = fachada.pesquisar(
				filtroCreditoTipo, CreditoTipo.class.getName());

		httpServletRequest.setAttribute("collectionCreditoTipo",
				collectionCreditoTipo);

		// Coleção de Origem do Crédito
		FiltroCreditoOrigem filtroCreditoOrigem = new FiltroCreditoOrigem();
		Collection<CreditoOrigem> collectionCreditoOrigem = fachada.pesquisar(
				filtroCreditoOrigem, CreditoOrigem.class.getName());

		httpServletRequest.setAttribute("collectionCreditoOrigem",
				collectionCreditoOrigem);

		// Validações do Formulário
		ManterCreditoARealizarActionForm manterCreditoARealizarActionForm = (ManterCreditoARealizarActionForm) actionForm;

		String limparForm = (String) httpServletRequest
				.getParameter("limparForm");
		String codigoImovel = manterCreditoARealizarActionForm
				.getMatriculaImovel();
		
		if(httpServletRequest.getParameter("menu") != null){
			codigoImovel = null;
			manterCreditoARealizarActionForm.setMatriculaImovel("");
			manterCreditoARealizarActionForm.setInscricaoImovel("");
			manterCreditoARealizarActionForm.setNomeCliente("");
			manterCreditoARealizarActionForm.setSituacaoAgua("");
			manterCreditoARealizarActionForm.setSituacaoEsgoto("");
		}
		
		if (codigoImovel == null || codigoImovel.trim().equals("")){
			if (httpServletRequest.getParameter("codigoImovel") != null){
				codigoImovel = httpServletRequest.getParameter("codigoImovel").toString();
			}
		}
		
		if (codigoImovel != null && !codigoImovel.trim().equals("")) {
			// Pesquisa o imovel na base
			FiltroImovel filtroImovel = new FiltroImovel();

			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, codigoImovel));
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("quadra");
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
            
            /*filtroImovel
            .adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao.id");
            filtroImovel
            .adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao.id");
    */
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");

			Collection<Imovel> imovelPesquisado = fachada.pesquisar(
					filtroImovel, Imovel.class.getName());

			// [FS0001 - Verificar existêncioa da matrícula do imóvel] Imovel
			// Inxistente
			if (imovelPesquisado != null && imovelPesquisado.isEmpty()) {
				httpServletRequest.setAttribute("corImovel","exception");
           		manterCreditoARealizarActionForm
           			.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
           		manterCreditoARealizarActionForm
       				.setMatriculaImovel("");
           		manterCreditoARealizarActionForm
   					.setNomeCliente("");
           		manterCreditoARealizarActionForm
   					.setSituacaoAgua("");
           		manterCreditoARealizarActionForm
   					.setSituacaoEsgoto("");
			}

			// [FS0001 - Verificar existêncioa da matrícula do imóvel] Imovel
			// Excluido
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				Imovel imovel = imovelPesquisado.iterator().next();
				if (imovel.getIndicadorExclusao() == Imovel.IMOVEL_EXCLUIDO) {
					throw new ActionServletException(
							"atencao.pesquisa.imovel.excluido");
				}
			}

			// [FS0002 - Verificar usuário com débito em cobrança administrativa
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				Imovel imovel = imovelPesquisado.iterator().next();
				FiltroImovelCobrancaSituacao filtroImovelCobrancaSituacao = new FiltroImovelCobrancaSituacao();

				filtroImovelCobrancaSituacao
						.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacao");
				filtroImovelCobrancaSituacao
						.adicionarParametro(new ParametroSimples(
								FiltroImovelCobrancaSituacao.IMOVEL_ID, imovel
										.getId()));

				Collection imovelCobrancaSituacaoEncontrada = fachada
						.pesquisar(filtroImovelCobrancaSituacao,
								ImovelCobrancaSituacao.class.getName());

				// Verifica se o imóvel tem débito em cobrança administrativa
				if (imovelCobrancaSituacaoEncontrada != null
						&& !imovelCobrancaSituacaoEncontrada.isEmpty()) {

					if (((ImovelCobrancaSituacao) ((List) imovelCobrancaSituacaoEncontrada)
							.get(0)).getCobrancaSituacao() != null) {

						if (((ImovelCobrancaSituacao) ((List) imovelCobrancaSituacaoEncontrada)
								.get(0)).getCobrancaSituacao().getId().equals(
								CobrancaSituacao.COBRANCA_ADMINISTRATIVA)
								&& ((ImovelCobrancaSituacao) ((List) imovelCobrancaSituacaoEncontrada)
										.get(0)).getDataRetiradaCobranca() == null) {

							throw new ActionServletException(
									"atencao.pesquisa.imovel.cobranca_administrativa");
						}
					}
				}
			}

			// [FS0003 - Verificar situação ligação de ágiua e esgoto]
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				Imovel imovel = imovelPesquisado.iterator().next();
				if ((imovel.getLigacaoAguaSituacao() != null)
						&& ((imovel.getLigacaoAguaSituacao().getId() == LigacaoAguaSituacao.POTENCIAL) || (imovel
								.getLigacaoEsgotoSituacao().getId() == LigacaoAguaSituacao.FACTIVEL))
						&& (imovel.getLigacaoEsgotoSituacao().getId() != LigacaoEsgotoSituacao.LIGADO)) {
					throw new ActionServletException(
							"atencao.pesquisa.imovel.inativo");
				}
			}

			// Obtem o cliente imovel do imovel pesquisado
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {

				Imovel imovel = imovelPesquisado.iterator().next();

				sessao.setAttribute("imovelPesquisado", imovel);

				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("cliente");
				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.IMOVEL_ID, codigoImovel));
				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
						ClienteRelacaoTipo.USUARIO));

				Collection<ClienteImovel> clienteImovelPesquisado = fachada
						.pesquisar(filtroClienteImovel, ClienteImovel.class
								.getName());

				if (clienteImovelPesquisado != null
						&& !clienteImovelPesquisado.isEmpty()) {
					ClienteImovel clienteImovel = clienteImovelPesquisado
							.iterator().next();
					if (clienteImovel.getCliente() != null) {
						manterCreditoARealizarActionForm
								.setNomeCliente(clienteImovel.getCliente()
										.getNome());
					}
				}
				if (imovel.getLigacaoAguaSituacao() != null) {
					manterCreditoARealizarActionForm.setSituacaoAgua(imovel
							.getLigacaoAguaSituacao().getDescricao());
				}

				if (imovel.getLigacaoEsgotoSituacao() != null) {
					manterCreditoARealizarActionForm.setSituacaoEsgoto(imovel
							.getLigacaoEsgotoSituacao().getDescricao());
				}
				manterCreditoARealizarActionForm
						.setMatriculaImovel(codigoImovel);

				manterCreditoARealizarActionForm.setInscricaoImovel(imovel
						.getInscricaoFormatada());
			}
			if(imovelPesquisado != null && !imovelPesquisado.isEmpty())
			{
				// Pesquisando os Crédito a Realizar do Imóvel
				// CreditoARealizar creditoARealizar = new CreditoARealizar();
	
				FiltroCreditoARealizar filtroCreditoARealizar = new FiltroCreditoARealizar();
	
				filtroCreditoARealizar.adicionarParametro(new ParametroSimples(
						FiltroCreditoARealizar.IMOVEL_ID, codigoImovel));
				filtroCreditoARealizar.adicionarParametro(new ParametroSimples(
						FiltroCreditoARealizar.DEBITO_CREDITO_SITUACAO_ATUAL,
						DebitoCreditoSituacao.NORMAL));
				/*filtroCreditoARealizar
						.adicionarCaminhoParaCarregamentoEntidade("creditoTipo.descricaoAbreviada");
				filtroCreditoARealizar
						.adicionarCaminhoParaCarregamentoEntidade("creditoOrigem.descricaoAbreviada");
*/                
                filtroCreditoARealizar.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");
                filtroCreditoARealizar.adicionarCaminhoParaCarregamentoEntidade("creditoOrigem");
	
				Collection<CreditoARealizar> collectionCreditoARealizar = fachada
						.pesquisar(filtroCreditoARealizar, CreditoARealizar.class
								.getName());
	
				if (collectionCreditoARealizar == null
						|| collectionCreditoARealizar.isEmpty()) {
					// Nenhum Credito a Realizar cadastrado
					throw new ActionServletException(
							"atencao.credito_a_realizar_inexistente",null,manterCreditoARealizarActionForm.getMatriculaImovel());
				}
	
				httpServletRequest.setAttribute("collectionCreditoARealizar",
						collectionCreditoARealizar);
			}
		}

		if (limparForm != null && !limparForm.trim().equalsIgnoreCase("")) {

			manterCreditoARealizarActionForm.reset(actionMapping,
					httpServletRequest);

			if (sessao.getAttribute("imovelPesquisado") != null) {
				sessao.removeAttribute("imovelPesquisado");
			}
		}

		return retorno;
	}
}
