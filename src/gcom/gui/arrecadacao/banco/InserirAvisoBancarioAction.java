package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.aviso.AvisoDeducoes;
import gcom.arrecadacao.aviso.AvisoDeducoesPK;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
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

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirAvisoBancarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		InserirAvisoBancarioActionForm form = (InserirAvisoBancarioActionForm) actionForm;
		Arrecadador arrecadadorSessao = (Arrecadador) sessao
				.getAttribute("arrecadador");
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataLancamento = null;
		try {
			dataLancamento = formato.parse(form.getDataLancamento());
		} catch (ParseException e) {
		}

		if (arrecadadorSessao == null || arrecadadorSessao.equals("")) {
			// O Arrecadador eh obrigatório
			throw new ActionServletException("atencao.informe_campo", null,
					"Arrecadador");
		}

		if (dataLancamento == null || dataLancamento.equals("")) {
			// O Arrecadador eh obrigatório
			throw new ActionServletException("atencao.informe_campo", null,
					"Data do Lançamento");
		}

		if (form.getNumeroDocumento() == null
				|| form.getNumeroDocumento().equals("")) {
			// O Arrecadador eh obrigatório
			throw new ActionServletException("atencao.informe_campo", null,
					"Número do Documento");
		}
		
		if (form.getIdFormaArrecadacao() == null || 
				form.getIdFormaArrecadacao().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			// Forma de Arrecadacao eh obrigatorio
			throw new ActionServletException("atencao.informe.campo", null,
					"Forma de Arrecadação");
		}

		if (form.getNumeroSequencial() == null
				|| form.getNumeroSequencial().equals("")) {
			// O Arrecadador eh obrigatório
			throw new ActionServletException("atencao.informe_campo", null,
					"Número do Sequencial");
		}
		
		System.out.println("idArrecadacaoFOrma: " + form.getIdFormaArrecadacao());
		
		ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
		arrecadacaoForma.setId(new Integer(form.getIdFormaArrecadacao()));

		Double numeroSequencial = new Double(form.getNumeroSequencial());

		BigDecimal valorAviso = new BigDecimal("0");

		String valorArrecadacaoFormatado = null;

		String valorDevolucaoFormatado = null;

		// Início do cálculo do valor do aviso
		if (form.getValorArrecadacao() != null
				&& !form.getValorArrecadacao().equals("")) {

			valorArrecadacaoFormatado = form.getValorArrecadacao().replace(".",
					"").replace(",", ".");

			valorAviso = valorAviso.add(new BigDecimal(
					valorArrecadacaoFormatado));

		}

		if (form.getValorDevolucao() != null
				&& !form.getValorDevolucao().equals("")) {

			valorDevolucaoFormatado = form.getValorDevolucao().replace(".", "")
					.replace(",", ".");

			valorAviso = valorAviso.subtract(new BigDecimal(
					valorDevolucaoFormatado));

		}

		if (sessao.getAttribute("colecaoAvisoDeducao") != null
				&& !((Collection) sessao.getAttribute("colecaoAvisoDeducao"))
						.isEmpty()) {
			Collection colecaoAvisoDeducao = (Collection) sessao
					.getAttribute("colecaoAvisoDeducao");
			Iterator colecaoAvisoDeducaoIterator = colecaoAvisoDeducao
					.iterator();

			while (colecaoAvisoDeducaoIterator.hasNext()) {
				AvisoDeducoes avisoDeducoes = (AvisoDeducoes) colecaoAvisoDeducaoIterator
						.next();
				valorAviso = valorAviso.subtract(avisoDeducoes
						.getValorDeducao());
			}

			if (form.getAvisoBancario() != null) {

				if (fachada.pesquisarDeducoesAvisoBancario(arrecadadorSessao
						.getCodigoAgente().toString(), dataLancamento, ""
						+ numeroSequencial.shortValue()) != null) {
					Double valorDeducoes = fachada
							.pesquisarDeducoesAvisoBancario(arrecadadorSessao
									.getCodigoAgente().toString(),
									dataLancamento, ""
											+ numeroSequencial.shortValue());
					valorAviso = valorAviso.subtract(new BigDecimal(
							valorDeducoes.toString()));
				}
			}

		}

		// Fim do cálculo do valor do aviso

		if (form.getTipoAviso().equals("" + AvisoBancario.INDICADOR_CREDITO)
				|| form.getTipoAviso().equalsIgnoreCase("credito")) {
			if (valorAviso.compareTo(new BigDecimal("0.00")) < 0) {

				throw new ActionServletException(
						"atencao.valor_aviso.nao_credito", null, Util
								.formatarMoedaReal(valorAviso));
			}

		} else {
			if (valorAviso.compareTo(new BigDecimal("0.00")) > 0) {
				throw new ActionServletException(
						"atencao.valor_aviso.nao_debito", null, Util
								.formatarMoedaReal(valorAviso));
			}
		}

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_AVISO_BANCARIO_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		AvisoBancario avisoBancario = new AvisoBancario();

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_AVISO_BANCARIO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		registradorOperacao.registrarOperacao(avisoBancario);

		Date dataRealizacao = null;

		if (form.getDataRealizacao() != null
				&& !form.getDataRealizacao().equals("")) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			try {
				dataRealizacao = format.parse(form.getDataRealizacao());
			} catch (ParseException ex) {
				throw new ActionServletException("erro.sistema");
			}

		}

		BigDecimal valorArrecadacao = new BigDecimal("0.00");

		if (form.getValorArrecadacao() != null
				&& !form.getValorArrecadacao().equals("")) {
			valorArrecadacao = new BigDecimal(valorArrecadacaoFormatado);
		}

		BigDecimal valorDevolucao = new BigDecimal("0.00");

		if (form.getValorDevolucao() != null
				&& !form.getValorDevolucao().equals("")) {
			valorDevolucao = new BigDecimal(valorDevolucaoFormatado);
		}

		ContaBancaria contaBancaria = new ContaBancaria();
		
		if (form.getIdContaBancaria() != null 
				&& !form.getIdContaBancaria().equals("")){
				contaBancaria.setId(new Integer(form.getIdContaBancaria()));
		}else{
			throw new ActionServletException("atencao.informe_campo", null,
			"Conta Bancária");
		}
		

		// Verifica se o usuário selecionou um aviso bancário se não tiver
		// selecionado insere
		if (form.getAvisoBancario() == null) {

			Short tipoAviso = null;

			if (form.getTipoAviso().equalsIgnoreCase("credito")
					|| form.getTipoAviso().equals(
							"" + AvisoBancario.INDICADOR_CREDITO)) {
				tipoAviso = new Short("1");
			} else {
				tipoAviso = new Short("2");
			}

			FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();

			Collection colecaoSistemaParametros = fachada.pesquisar(
					filtroSistemaParametro, SistemaParametro.class.getName());

			SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametros
					.iterator().next();

//			Arrecadador arrecadador = new Arrecadador();
//
//			arrecadador.setId(new Integer(form.getCodigoArrecadador()));

			avisoBancario.setDataLancamento(dataLancamento);
			avisoBancario.setNumeroSequencial(numeroSequencial.shortValue());
			avisoBancario.setDataPrevista(null);
			avisoBancario.setDataRealizada(dataRealizacao);
			// avisoBancario.setValorPrevisto(null);
			avisoBancario.setValorRealizado(valorAviso);
			avisoBancario.setValorArrecadacaoCalculado(new BigDecimal("0.00"));
			avisoBancario.setValorDevolucaoCalculado(new BigDecimal("0.00"));
			avisoBancario.setValorArrecadacaoInformado(valorArrecadacao);
			avisoBancario.setValorDevolucaoInformado(valorDevolucao);
			avisoBancario.setValorContabilizado(new BigDecimal("0.00"));
			// Alterado por Sávio Luiz data:22/03/2007
			// pegar o maior anoMes entre o anoMes da dataLancamento e o
			// anoMesArrecadacao
			Integer anoMesDataLancamento = Util
					.recuperaAnoMesDaData(dataLancamento);
			if (anoMesDataLancamento > sistemaParametro.getAnoMesArrecadacao()) {
				avisoBancario
						.setAnoMesReferenciaArrecadacao(anoMesDataLancamento);
			} else {
				avisoBancario.setAnoMesReferenciaArrecadacao(sistemaParametro
						.getAnoMesArrecadacao());
			}

			avisoBancario.setIndicadorCreditoDebito(new Short(tipoAviso));
			avisoBancario.setNumeroDocumento(new Integer(form
					.getNumeroDocumento()).intValue());
			avisoBancario.setArrecadacaoForma(arrecadacaoForma);
			avisoBancario.setArrecadador(arrecadadorSessao);
			avisoBancario.setContaBancaria(contaBancaria);
			avisoBancario.setArrecadadorMovimento(null);
			avisoBancario.setUltimaAlteracao(new Date());

			// ------------ REGISTRAR TRANSAÇÃO ----------------
			avisoBancario.setOperacaoEfetuada(operacaoEfetuada);
			avisoBancario.adicionarUsuario(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(avisoBancario);
			// ------------ REGISTRAR TRANSAÇÃO ----------------

			fachada.inserir(avisoBancario);

		} else {
			// Caso tenha selecionado atualiza
			avisoBancario = (AvisoBancario) sessao
					.getAttribute("avisoBancario");
			avisoBancario.setDataRealizada(dataRealizacao);
			avisoBancario.setValorRealizado(valorAviso);
			/*
			 * avisoBancario.setValorArrecadacaoCalculado(new
			 * BigDecimal("0.00")); avisoBancario.setValorDevolucaoCalculado(new
			 * BigDecimal("0.00"));
			 */
			avisoBancario.setValorArrecadacaoInformado(valorArrecadacao);
			avisoBancario.setValorDevolucaoInformado(valorDevolucao);
			avisoBancario.setNumeroDocumento(new Integer(form
					.getNumeroDocumento()).intValue());
			avisoBancario.setContaBancaria(contaBancaria);
			avisoBancario.setUltimaAlteracao(new Date());

			// ------------ REGISTRAR TRANSAÇÃO ----------------

			RegistradorOperacao registradorOperacaoAtualizar = new RegistradorOperacao(
					Operacao.OPERACAO_AVISO_BANCARIO_ATUALIZAR,
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			Operacao operacaoAtualizar = new Operacao();
			operacaoAtualizar.setId(Operacao.OPERACAO_AVISO_BANCARIO_ATUALIZAR);

			OperacaoEfetuada operacaoEfetuadaAtualizar = new OperacaoEfetuada();
			operacaoEfetuadaAtualizar.setOperacao(operacaoAtualizar);

			avisoBancario.setOperacaoEfetuada(operacaoEfetuadaAtualizar);
			avisoBancario.adicionarUsuario(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacaoAtualizar.registrarOperacao(avisoBancario);
			// ------------ REGISTRAR TRANSAÇÃO ----------------

			fachada.atualizar(avisoBancario);
		}

		if (sessao.getAttribute("colecaoAvisoDeducao") != null
				&& !((Collection) sessao.getAttribute("colecaoAvisoDeducao"))
						.isEmpty()) {
			Collection colecaoAvisoDeducao = (Collection) sessao
					.getAttribute("colecaoAvisoDeducao");
			Iterator colecaoAvisoDeducaoIterator = colecaoAvisoDeducao
					.iterator();
			while (colecaoAvisoDeducaoIterator.hasNext()) {
				AvisoDeducoes avisoDeducoes = (AvisoDeducoes) colecaoAvisoDeducaoIterator
						.next();
				AvisoDeducoesPK avisoDeducoesPK = new AvisoDeducoesPK();
				avisoDeducoes.setAvisoBancario(avisoBancario);
				avisoDeducoesPK.setAvisoBancarioId(avisoBancario.getId());
				avisoDeducoesPK.setDeducaoTipoId(avisoDeducoes.getDeducaoTipo()
						.getId());
				registradorOperacao.registrarOperacao(avisoDeducoes);
				avisoDeducoes.setComp_id(avisoDeducoesPK);

				fachada.inserirAvisosDeducoes(avisoDeducoes, avisoBancario);

			}
		}

		montarPaginaSucesso(httpServletRequest, "Aviso Bancário de código "
				+ avisoBancario.getId() + " com Data de Lançamento "
				+ form.getDataLancamento() + " e seqüencial "
				+ form.getNumeroSequencial() + " do Arrecadador "
				+ form.getNomeArrecadador() + " inserido com sucesso.",
				"Inserir outro Aviso Bancário",
				"exibirInserirAvisoBancarioAction.do?menu=sim",
				"ExibirAtualizarAvisoBancarioAction.do?idAvisoBancario="
						+ avisoBancario.getId(),
				"Atualizar Aviso Bancário Inserido");

		return retorno;
	}
}
