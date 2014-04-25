package gcom.gui.arrecadacao;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.FiltroDevolucao;
import gcom.arrecadacao.GuiaDevolucao;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Fernanda Karla
 * @created 09 de Março de 2006
 */
public class AtualizarDevolucaoAction extends GcomAction {
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
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		ManterDevolucaoActionForm manterDevolucaoActionForm = (ManterDevolucaoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_DEVOLUCOES_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
        
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_DEVOLUCOES_ATUALIZAR);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSAÇÃO ----------------

		String codigoDevolucao = manterDevolucaoActionForm.getCodigoDevolucao();
		String valorDevolucaoAntes = manterDevolucaoActionForm.getValorDevolucao().toString().replace(".","");
		String valorDevolucao = valorDevolucaoAntes.replace(",",".");
		String dataDevolucao = manterDevolucaoActionForm.getDataDevolucao();
		String confirmado = httpServletRequest.getParameter("confirmado");

		Fachada fachada = Fachada.getInstancia();

		// Criando o objeto Devolucao
		Devolucao devolucao = new Devolucao();

		FiltroDevolucao filtroDevolucao = new FiltroDevolucao();

		filtroDevolucao.adicionarParametro(new ParametroSimples(
				FiltroDevolucao.ID, codigoDevolucao));

		Collection devolucoes = fachada.pesquisar(filtroDevolucao,
				Devolucao.class.getName());

		if (devolucoes != null && !devolucoes.isEmpty()) {

			Devolucao dadosDevolucao = (Devolucao) ((List) devolucoes).get(0);

			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

			devolucao.setId(new Integer(codigoDevolucao));
			
			if(dadosDevolucao.getValorDevolucao() != (new BigDecimal(valorDevolucao)))
			{
				FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();

				filtroAvisoBancario.adicionarParametro(new ParametroSimples(
						FiltroAvisoBancario.ID, dadosDevolucao.getAvisoBancario().getId()));

				Collection avisoBancario = fachada.pesquisar(filtroAvisoBancario,
						AvisoBancario.class.getName());
				
				BigDecimal valorFinal = null;

				if (avisoBancario != null && !avisoBancario.isEmpty()) {

					AvisoBancario dadosAvisoBancario = (AvisoBancario) ((List) avisoBancario).get(0);
					
					BigDecimal valorDevolucao2 = dadosAvisoBancario.getValorDevolucaoCalculado();
					
					BigDecimal valorSubtracao = (valorDevolucao2.subtract(dadosDevolucao.getValorDevolucao()));
					
					valorFinal = (new BigDecimal(valorDevolucao).add(valorSubtracao));

				}
				fachada.atualizaValorArrecadacaoAvisoBancaraio(valorFinal, dadosDevolucao.getAvisoBancario().getId());
			}

			devolucao.setValorDevolucao(new BigDecimal(valorDevolucao));

			int mesAnoArrecadacao = dadosDevolucao
					.getAnoMesReferenciaArrecadacao();
			devolucao.setAnoMesReferenciaArrecadacao(new Integer(
					mesAnoArrecadacao));

			Date dataDevolucoesFormatada = null;

			try {
				dataDevolucoesFormatada = dataFormatada.parse(dataDevolucao);
			} catch (ParseException ex) {
				throw new ActionServletException("erro.sistema");
			}

			devolucao.setDataDevolucao(dataDevolucoesFormatada);

			// Criando o objeto Referencia Devolucao
			if (manterDevolucaoActionForm.getReferenciaDevolucaoClone() != null
					&& !manterDevolucaoActionForm.getReferenciaDevolucaoClone()
							.equals("")) {
				String referenciaDevolucao = manterDevolucaoActionForm
						.getReferenciaDevolucaoClone();
				String mes = referenciaDevolucao.substring(0, 2);
				String ano = referenciaDevolucao.substring(3, 7);
				String mesAno = ano + mes;

				devolucao.setAnoMesReferenciaDevolucao(new Integer(mesAno));
			} else {
				devolucao.setAnoMesReferenciaDevolucao(null);
			}

			// Criando o objeto Cliente
			if (manterDevolucaoActionForm.getCodigoClienteClone() != null
					&& !manterDevolucaoActionForm.getCodigoClienteClone().equals("")) {
				Integer idCliente = new Integer(manterDevolucaoActionForm
						.getCodigoClienteClone());
				Cliente cliente = new Cliente();
				cliente.setId(idCliente);
				devolucao.setCliente(cliente);
			}else {
				devolucao.setCliente(null);
			}

			devolucao.setDevolucaoSituacaoAnterior(dadosDevolucao
					.getDevolucaoSituacaoAnterior());
			devolucao.setDevolucaoSituacaoAtual(dadosDevolucao
					.getDevolucaoSituacaoAtual());

			FiltroImovel filtroImovel = new FiltroImovel();

			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("localidade");
			// Criando o objeto Imovel
			if (manterDevolucaoActionForm.getCodigoImovelClone() != null
					&& !manterDevolucaoActionForm.getCodigoImovelClone().equals("")) {
				
				Integer idImovel = new Integer(manterDevolucaoActionForm
						.getCodigoImovelClone());
				Imovel imovel = new Imovel();
				imovel.setId(idImovel);
				devolucao.setImovel(imovel);
				
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, manterDevolucaoActionForm.getCodigoImovelClone()));
				
				Collection imovelEncontrado = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());
				if (imovelEncontrado != null && !imovelEncontrado.isEmpty()) 
				{
					String codigoDigitadoLocalidadeEnter = ((Imovel) ((List) imovelEncontrado).get(0)).getLocalidade().getId().toString();
					if(manterDevolucaoActionForm.getLocalidade() != null && !manterDevolucaoActionForm.getLocalidade().equals("") && !manterDevolucaoActionForm.getLocalidadeClone().equals(codigoDigitadoLocalidadeEnter))
					{
						throw new ActionServletException(
								"atencao.pesquisa.localidade.imovel.diferente", ""
										+ codigoDigitadoLocalidadeEnter, manterDevolucaoActionForm.getLocalidade());
					}
				}
			} else {
				devolucao.setImovel(null);
			}

			// Criando o objeto Localidade
			if (manterDevolucaoActionForm.getLocalidadeClone() != null
					&& !manterDevolucaoActionForm.getLocalidadeClone().equals("")) {
				Integer idLocalidade = new Integer(manterDevolucaoActionForm
						.getLocalidadeClone());
				Localidade localidade = new Localidade();
				localidade.setId(idLocalidade);
				devolucao.setLocalidade(localidade);
			}else {
				devolucao.setLocalidade(dadosDevolucao.getLocalidade());
			}
			// Criando o objeto Guia Devolucao
			if (manterDevolucaoActionForm.getGuiaDevolucaoClone() != null
					&& !manterDevolucaoActionForm.getGuiaDevolucaoClone().equals("")) {
				Integer idGuiaDevolucao = new Integer(manterDevolucaoActionForm
						.getGuiaDevolucaoClone());
				GuiaDevolucao guiaDevolucao = new GuiaDevolucao();
				guiaDevolucao.setId(idGuiaDevolucao);
				devolucao.setGuiaDevolucao(guiaDevolucao);
			} else {
				devolucao.setGuiaDevolucao(null);
			}

			devolucao.setAvisoBancario(dadosDevolucao.getAvisoBancario());

			// Criando o objeto TipoDebito
			if (manterDevolucaoActionForm.getTipoDebitoClone() != null
					&& !manterDevolucaoActionForm.getTipoDebitoClone().equals("")) {
				Integer idTipoDebito = new Integer(manterDevolucaoActionForm
						.getTipoDebitoClone());
				DebitoTipo tipoDebito = new DebitoTipo();
				tipoDebito.setId(idTipoDebito);
				devolucao.setDebitoTipo(tipoDebito);
			}else{
				devolucao.setDebitoTipo(null);
			}
			// Última Alteração
			devolucao.setUltimaAlteracao(new Date());
		}
		
		if(devolucao.getImovel() != null)
		{
			FiltroImovel filtroImovel = new FiltroImovel();

			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("localidade");
			
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, devolucao.getImovel().getId()));
			
			Collection imovelEncontrado = fachada.pesquisar(filtroImovel,
					Imovel.class.getName());
			if (imovelEncontrado != null && !imovelEncontrado.isEmpty()) 
			{
				String codigoDigitadoLocalidadeEnter = ((Imovel) ((List) imovelEncontrado).get(0)).getLocalidade().getId().toString();
				if(manterDevolucaoActionForm.getLocalidade() != null && !manterDevolucaoActionForm.getLocalidade().equals("") && !manterDevolucaoActionForm.getLocalidade().equals(codigoDigitadoLocalidadeEnter))
				{
					throw new ActionServletException(
							"atencao.pesquisa.localidade.imovel.diferente", ""
									+ codigoDigitadoLocalidadeEnter, manterDevolucaoActionForm.getLocalidade());
				}
			}
		}
		Devolucao dadosDevolucao = (Devolucao)sessao.getAttribute("dadosDevolucao");
		// verifica se o valor da devolucao é maior que o valor da guia da devolucao
		if(manterDevolucaoActionForm.getValorGuiaDevolucao() != null && !manterDevolucaoActionForm.getValorGuiaDevolucao().equalsIgnoreCase("") 
				&& Util.formatarMoedaRealparaBigDecimal(manterDevolucaoActionForm.getValorDevolucao()).compareTo(Util.formatarMoedaRealparaBigDecimal(manterDevolucaoActionForm.getValorGuiaDevolucao())) == 1) 
		{
			if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")){
				httpServletRequest.setAttribute("caminhoActionConclusao",
						"/gsan/atualizarDevolucaoAction.do");
				// Monta a página de confirmação para perguntar se o usuário
				// quer inserir
				// a devolução mesmo com o valor da devolução sendo superior ao da guia da devolução
				
				// coloca o objeto devolucao na sessao caso ele seja diferente de null
				if(dadosDevolucao != null){
					sessao.setAttribute("dadosDevolucao",dadosDevolucao);
				}
				return montarPaginaConfirmacao(
						"atencao.valor_devolucao_maior_valor_guia_devolucao.confirmacao",
						httpServletRequest, actionMapping, manterDevolucaoActionForm.getValorDevolucao(),manterDevolucaoActionForm.getValorGuiaDevolucao());
			}
		}

		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
        devolucao.setOperacaoEfetuada(operacaoEfetuada);
        devolucao.adicionarUsuario(usuarioLogado, 
        		UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
        registradorOperacao.registrarOperacao(devolucao);
        //------------ REGISTRAR TRANSAÇÃO ----------------
        
        
        
        // Limpa os parametros do filtro para fazer uma nova pesquisar e verificar a ultima alteracao na base
		filtroDevolucao.limparListaParametros(); 

		// Parte de Validação com Timestamp

		// Seta no filtro o código da devolucao que está sendo atualizada
		filtroDevolucao.adicionarParametro(new ParametroSimples(
				FiltroDevolucao.ID, devolucao.getId()));

		// Procura a devolucao na base
		Devolucao devolucaoNaBase = (Devolucao) ((List) (fachada
				.pesquisar(filtroDevolucao, Devolucao.class.getName()))).get(0);

		// Atualização realizada por outro usuário
		// Caso o usuário esteja tentando atualizar uma devolucao e o mesmo já
		// tenha
		// sido atualizado durante a manutenção corrente
		if (devolucaoNaBase.getUltimaAlteracao().after(
				dadosDevolucao.getUltimaAlteracao())) {
			throw new ActionServletException("atencao.atualizacao.timestamp");
		}

		// Atualiza a Devolucao
		fachada.atualizar(devolucao);

		montarPaginaSucesso(httpServletRequest, "Devolução de código "
				+ devolucao.getId() + " atualizada com sucesso.",
				"Realizar outra Manutenção de Devolucao",
				"exibirFiltrarDevolucaoAction.do?tela=manterDevolucao&menu=sim");
		return retorno;
	}
}
