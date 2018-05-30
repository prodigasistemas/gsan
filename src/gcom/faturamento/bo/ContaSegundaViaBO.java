package gcom.faturamento.bo;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.RepositorioFaturamentoHBM;
import gcom.faturamento.bean.ContaSegundaViaDTO;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.ejb.CreateException;

public class ContaSegundaViaBO {

	private Fachada fachada;
	private ControladorFaturamentoLocal controlador;
	private IRepositorioFaturamento repositorio;

	private EmitirContaHelper helper;

	public ContaSegundaViaBO(Integer idContaHistorico, Collection<?> idsConta, boolean cobrarTaxaEmissaoConta, Short contaSemCodigoBarras) {
		super();

		this.fachada = Fachada.getInstancia();
		this.setControlador();
		this.repositorio = RepositorioFaturamentoHBM.getInstancia();
		this.criarHelper(idContaHistorico, idsConta, cobrarTaxaEmissaoConta, contaSemCodigoBarras);
	}

	public ContaSegundaViaDTO criar(Imovel imovel, Usuario usuario, String situacaoConta) {
		return new ContaSegundaViaDTO(helper, 
				fachada.pesquisarParametrosDoSistema(), 
				getNomeResponsavel(), 
				getEconomias(), 
				getHidrometro(imovel), 
				getEmissao(usuario),
				situacaoConta);
	}

	private void setControlador() {
		try {
			ControladorFaturamentoLocalHome localHome = (ControladorFaturamentoLocalHome) ServiceLocator.getInstancia().getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			controlador = localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	private String getNomeResponsavel() {
		String nome = null;
		if (helper.getIdClienteResponsavel() != null && !helper.getIdClienteResponsavel().trim().equals("")) {
			FiltroCliente filtro = new FiltroCliente();
			filtro.adicionarParametro(new ParametroSimples(FiltroCliente.ID, helper.getIdClienteResponsavel()));
			Collection colecao = fachada.pesquisarCliente(filtro);

			if (!colecao.isEmpty()) {
				Cliente cliente = (Cliente) colecao.iterator().next();
				nome = cliente.getNome();
			}
		}

		return nome;
	}

	private String getHidrometro(Imovel imovel) {
		String numero = null;
		try {
			Object[] dadosLigacao = fachada.pesquisarDadosHidrometroTipoLigacaoAgua(imovel);
			numero = (String) dadosLigacao[4];
		} catch (ControladorException e) {
			e.printStackTrace();
		}

		return numero != null && !numero.equals("") ? numero : "-";
	}

	private String getEmissao(Usuario usuario) {
		if (usuario != null) {
			return "por " + usuario.getNomeUsuario();
		} else {
			return "pela INTERNET";
		}
	}

	@SuppressWarnings("rawtypes")
	private String getEconomias() {
		String economias = "";
		Collection categorias = fachada.obterQuantidadeEconomiasCategoria(new Imovel(helper.getIdImovel()));
		for (Iterator iterator = categorias.iterator(); iterator.hasNext();) {
			Categoria categoria = (Categoria) iterator.next();

			if (categoria.getId().equals(Categoria.RESIDENCIAL)) {
				economias += categoria.getQuantidadeEconomiasCategoria() + " RES";
			} else if (categoria.getId().equals(Categoria.COMERCIAL)) {
				economias += economias.equals("") ? "" : " + ";
				economias += categoria.getQuantidadeEconomiasCategoria() + " COM";
			} else if (categoria.getId().equals(Categoria.INDUSTRIAL)) {
				economias += economias.equals("") ? "" : " + ";
				economias += categoria.getQuantidadeEconomiasCategoria() + " IND";
			} else if (categoria.getId().equals(Categoria.PUBLICO)) {
				economias += economias.equals("") ? "" : " + ";
				economias += categoria.getQuantidadeEconomiasCategoria() + " PUB";
			}
		}
		return economias;
	}

	@SuppressWarnings("rawtypes")
	private void criarHelper(Integer idContaHistorico, Collection idsConta, boolean cobrarTaxaEmissaoConta, Short contaSemCodigoBarras) {
		Collection<EmitirContaHelper> colecao = new ArrayList<EmitirContaHelper>();
		if (idContaHistorico == null) {
			colecao = fachada.emitir2ViaContas(idsConta, cobrarTaxaEmissaoConta, contaSemCodigoBarras);
		} else {
			colecao = fachada.emitir2ViaContasHistorico(idsConta, cobrarTaxaEmissaoConta, contaSemCodigoBarras);
		}

		if (!colecao.isEmpty()) {
			helper = colecao.iterator().next();

			try {
				setMensagens();
			} catch (ControladorException e) {
				e.printStackTrace();
			}

		}
	}

	private void setMensagens() throws ControladorException {
		try {
			helper.setMensagensFixas(repositorio.pesquisarContaMensagemFixa());

			Object[] mensagens = obterMensagens();
			helper.setPrimeiraParte((String) mensagens[0]);
			helper.setSegundaParte((String) mensagens[1]);
			helper.setTerceiraParte((String) mensagens[2]);

			String[] mensagensAnormalidade = controlador.obterMensagemAnormalidadeConsumo(helper);
			if (mensagensAnormalidade != null)
				helper.setMensagemAnormalidade(mensagensAnormalidade[0] + mensagensAnormalidade[1]);

			helper.setMensagemDebitos(obterMensagemDebitos());

			helper.setMensagemQuitacao(controlador.obterMsgQuitacaoDebitos(new Imovel(helper.getIdImovel()), helper.getAmReferencia()));
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		} catch (ControladorException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	private Object[] obterMensagens() throws ControladorException {
		try {
			Object[] mensagens = repositorio.pesquisarParmsContaMensagem(helper, null, helper.getIdGerenciaRegional(), helper.getIdLocalidade(), helper.getIdSetorComercial());

			if (mensagens == null)
				mensagens = repositorio.pesquisarParmsContaMensagem(helper, null, helper.getIdGerenciaRegional(), helper.getIdLocalidade(), null);

			if (mensagens == null)
				mensagens = repositorio.pesquisarParmsContaMensagem(helper, null, helper.getIdGerenciaRegional(), null, null);

			if (mensagens == null)
				mensagens = repositorio.pesquisarParmsContaMensagem(helper, helper.getIdFaturamentoGrupo(), null, null, null);

			if (mensagens == null)
				mensagens = repositorio.pesquisarParmsContaMensagem(helper, null, null, null, null);

			if (mensagens == null) {
				mensagens = new Object[3];
				mensagens[0] = "";
				mensagens[1] = "";
				mensagens[2] = "";
			}

			return mensagens;
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	private String obterMensagemDebitos() throws ControladorException {
		SistemaParametro parametros = fachada.pesquisarParametrosDoSistema();
		
		Date dataVencimentoFinal = getDataVencimentoFinal(parametros);

		ObterDebitoImovelOuClienteHelper debitoImovelClienteHelper = fachada.obterDebitoImovelOuCliente(1, helper.getIdImovel().toString(), null, null, 
				"190001", Util.subtrairMesDoAnoMes(parametros.getAnoMesFaturamento(), 1) + "", 
				Util.converteStringParaDate("01/01/1900"), dataVencimentoFinal, 1, 2, 2, 2, 2, 1, 2, null);

		if (debitoImovelClienteHelper != null
				&& ((debitoImovelClienteHelper.getColecaoGuiasPagamentoValores() != null && !debitoImovelClienteHelper.getColecaoGuiasPagamentoValores().isEmpty()) 
					|| (debitoImovelClienteHelper.getColecaoContasValores() != null && !debitoImovelClienteHelper.getColecaoContasValores().isEmpty()))) {
			
			String dataVencimentoFinalString = Util.formatarData(dataVencimentoFinal);
			
			return "Sr(a) cliente, em " + dataVencimentoFinalString + ", registramos que V.SA. estava em débito com a " + parametros.getNomeAbreviadoEmpresa().toUpperCase() + ". " 
					+ "Compareça a um dos nossos postos de atendimento para regularizar sua situação. Evite o corte. "
					+ "Caso o débito tenha sido pago após a data indicada, desconsidere este aviso.";
		} else {
			return "A " + parametros.getNomeAbreviadoEmpresa().toUpperCase() + " agradece sua pontualidade.";
		}
	}

	private Date getDataVencimentoFinal(SistemaParametro parametros) {
		String anoMes = "" + Util.subtrairMesDoAnoMes(parametros.getAnoMesArrecadacao(), 1);
		int ano = Integer.parseInt(anoMes.substring(0, 4));
		int mes = Integer.parseInt(anoMes.substring(4, 6));
		
		Calendar data = GregorianCalendar.getInstance();
		data.set(Calendar.YEAR, ano);
		data.set(Calendar.MONTH, (mes - 1));
		data.set(Calendar.DAY_OF_MONTH,data.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		return data.getTime();
	}
}
