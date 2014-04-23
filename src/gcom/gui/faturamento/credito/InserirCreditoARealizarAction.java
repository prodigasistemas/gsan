package gcom.gui.faturamento.credito;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
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
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0194] Crédito a Realizar Permite inserir um crédito a realizar
 * 
 * @author Roberta Costa
 * @since 11/01/2006
 */
public class InserirCreditoARealizarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Pega uma instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		// Instância do formulário que está sendo utilizado
		InserirCreditoARealizarActionForm inserirCreditoARealizarActionForm = (InserirCreditoARealizarActionForm) actionForm;

		// Campos do Formulário
		Integer tipoCredito = new Integer(inserirCreditoARealizarActionForm
				.getTipoCredito());
		Integer origemCredito = new Integer(inserirCreditoARealizarActionForm
				.getOrigemCredito());
		Short numeroPrestacoes = new Short(inserirCreditoARealizarActionForm
				.getNumeroPrestacoes());
		String valorCreditoAntes = inserirCreditoARealizarActionForm
				.getValorCredito().toString().replace(".", "");
		String valorCredito = valorCreditoAntes.replace(',', '.');
		String registroAtendimentoForm = inserirCreditoARealizarActionForm
				.getRegistroAtendimento();
		String matriculaImovel = inserirCreditoARealizarActionForm
				.getMatriculaImovel();
		String referencia = inserirCreditoARealizarActionForm.getReferencia();
		String confirmado = httpServletRequest.getParameter("confirmado");

		// Criando o objeto creditoARealizar
		CreditoARealizar creditoARealizar = new CreditoARealizar();

		// Criando o objeto Crédito Tipo
		CreditoTipo creditoTipo = new CreditoTipo();
		creditoTipo.setId(tipoCredito);

		creditoARealizar.setCreditoTipo(creditoTipo);

		// Criando o objeto Crédito Origem
		CreditoOrigem creditoOrigem = new CreditoOrigem();
		creditoOrigem.setId(origemCredito);

		creditoARealizar.setCreditoOrigem(creditoOrigem);

		// Criando o objeto Registro Atendimento
		RegistroAtendimento registroAtendimento = new RegistroAtendimento();

		if (registroAtendimentoForm != null
				&& !registroAtendimentoForm.equals("")) {
			registroAtendimento.setId(new Integer(registroAtendimentoForm));

			creditoARealizar.setRegistroAtendimento(registroAtendimento);
		} else {
			// inseri o valor 1 temporariamente
			RegistroAtendimento registroAtendimentoConstante = new RegistroAtendimento();
			registroAtendimentoConstante.setId(RegistroAtendimento.CONSTANTE);
			creditoARealizar
					.setRegistroAtendimento(registroAtendimentoConstante);
		}

		if (inserirCreditoARealizarActionForm.getOrdemServico() != null
				&& !inserirCreditoARealizarActionForm.getOrdemServico().equals(
						"")) {
			Integer ordemServicoForm = new Integer(
					inserirCreditoARealizarActionForm.getOrdemServico());

			// Criando o objeto Ordem de Serviço
			OrdemServico ordemServico = new OrdemServico();
			ordemServico.setId(ordemServicoForm);

			creditoARealizar.setOrdemServico(ordemServico);
		} else {
			creditoARealizar.setOrdemServico(null);
		}
		
		//[FS0006] Verificar existência de credito a realizar para o registro de atendimento
		if((inserirCreditoARealizarActionForm.getTipoCredito() != null && 
				!inserirCreditoARealizarActionForm.getTipoCredito().equals("")) 
		    &&
		    (inserirCreditoARealizarActionForm.getRegistroAtendimento() != null
	    		&& !inserirCreditoARealizarActionForm.getRegistroAtendimento().equals(""))
		){
			FiltroCreditoARealizar filtroCreditoARelizar = new FiltroCreditoARealizar();
			filtroCreditoARelizar.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.ID_REGISTRO_ATENDIMENTO,inserirCreditoARealizarActionForm.getRegistroAtendimento()));
			filtroCreditoARelizar.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.ID_CREDITO_TIPO,inserirCreditoARealizarActionForm.getTipoCredito()));
			filtroCreditoARelizar.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroCreditoARealizar.DEBITO_CREDITO_SITUACAO_ATUAL,DebitoCreditoSituacao.CANCELADA));

			Collection colecaoCreditoARealizar = fachada.pesquisar(
					filtroCreditoARelizar, CreditoARealizar.class
							.getName());
			
			if (colecaoCreditoARealizar != null
					&& !colecaoCreditoARealizar.isEmpty()) {
				throw new ActionServletException(
				"atencao.existe.credito_a_realizar.para.registro_atendimento");
			}
		}
		
		

		// Pega o Imóvel da sessão
		Imovel imovel = null;
		if (sessao.getAttribute("imovelPesquisado") != null) {
			imovel = (Imovel) sessao.getAttribute("imovelPesquisado");
		}else{
			if (matriculaImovel != null && !matriculaImovel.trim().equals("")) {
				// Pesquisa o imovel na base
				FiltroImovel filtroImovel = new FiltroImovel();

				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, matriculaImovel));

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
                
                filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
                filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
        
				/*filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao.id");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao.id");*/
				

				Collection<Imovel> imovelPesquisado = fachada.pesquisar(
						filtroImovel, Imovel.class.getName());

				// [FS0001 - Verificar existêncioa da matrícula do imóvel] Imovel
				// Inxistente
				if (imovelPesquisado != null && imovelPesquisado.isEmpty()) {
					throw new ActionServletException("atencao.imovel.inexistente");
				}

				// [FS0001 - Verificar existêncioa da matrícula do imóvel] Imovel
				// Excluido
				if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
					imovel = imovelPesquisado.iterator().next();
					if (imovel.getIndicadorExclusao() == Imovel.IMOVEL_EXCLUIDO) {
						throw new ActionServletException(
								"atencao.pesquisa.imovel.excluido");
					}
				}

				// [FS0002 - Verificar usuário com débito em cobrança administrativa
				if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
					imovel = imovelPesquisado.iterator().next();
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
					imovel = imovelPesquisado.iterator().next();
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

					imovel = imovelPesquisado.iterator().next();
					
					sessao.setAttribute("imovelPesquisado", imovel);
				}
			}
		}
		
		if (referencia != null && !referencia.equals("")) {
			Integer referenciaCredito = Util.formatarMesAnoComBarraParaAnoMes(referencia);
			
			Integer qtdContas = fachada.pesquisarQuantidadeContasEContasHistorico(imovel.getId(), referenciaCredito);
			
			// verifica se o valor da devolucao é maior que o valor da guia da devolucao
			if (qtdContas == null || qtdContas == 0) {
			
				if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")){
					httpServletRequest.setAttribute("caminhoActionConclusao",
							"/gsan/inserirCreditoARealizarAction.do");
					// Monta a página de confirmação para perguntar se o usuário
					// quer inserir
					// a devolução mesmo com o valor da devolução sendo superior ao da guia da devolução
					return montarPaginaConfirmacao(
							"atencao.referencia.conta.inexistente",
							httpServletRequest, actionMapping);
				}
			}
			
			creditoARealizar.setAnoMesReferenciaCredito(referenciaCredito);
		}

		// Setando as informações do Imóvel
		creditoARealizar.setImovel(imovel);
		creditoARealizar.setCodigoSetorComercial(imovel.getSetorComercial()
				.getCodigo());
		creditoARealizar.setNumeroQuadra(new Integer(imovel.getQuadra()
				.getNumeroQuadra()));
		creditoARealizar.setNumeroLote(imovel.getLote());
		creditoARealizar.setNumeroSubLote(imovel.getSubLote());
		creditoARealizar.setQuadra(imovel.getQuadra());
		creditoARealizar.setLocalidade(imovel.getLocalidade());

		creditoARealizar.setNumeroPrestacaoCredito(numeroPrestacoes);
		creditoARealizar.setValorCredito(new BigDecimal(valorCredito));

		fachada.inserirCreditoARealizar(imovel, creditoARealizar,usuarioLogado);
		
		sessao.removeAttribute("imovelPesquisado");

		montarPaginaSucesso(httpServletRequest, "Crédito a Realizar do Imóvel "
				+ imovel.getId() + " inserido com sucesso.",
				"Inserir outro Crédito a Realizar",
				"exibirInserirCreditoARealizarAction.do?menu=sim",
				"exibirManterCreditoARealizarAction.do?codigoImovel="
						+ imovel.getId(),
				"Cancelar Crédito(s) a Realizar do Imóvel " + imovel.getId());
		
		return retorno;
	}
}
