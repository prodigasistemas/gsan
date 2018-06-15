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
import gcom.faturamento.bean.ContaSegundaViaHelper;
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
import java.util.List;

import javax.ejb.CreateException;

public class ContaSegundaViaBO {

	private Fachada fachada;
	private ControladorFaturamentoLocal controlador;
	private IRepositorioFaturamento repositorio;

	private Collection<EmitirContaHelper> contas;
	private EmitirContaHelper contaHelper;

	public ContaSegundaViaBO(Integer idContaHistorico, Collection<?> idsConta, boolean cobrarTaxaEmissaoConta, Short contaSemCodigoBarras) {
		super();

		fachada = Fachada.getInstancia();
		repositorio = RepositorioFaturamentoHBM.getInstancia();
		controlador = getControlador();
		contas = pesquisarContas(idContaHistorico, idsConta, cobrarTaxaEmissaoConta, contaSemCodigoBarras);
	}

	public ContaSegundaViaHelper criar(Imovel imovel, Usuario usuario, String situacaoConta) {
		ContaSegundaViaHelper helper = null;
		
		if (contas != null && !contas.isEmpty()) {
			List<ContaSegundaViaDTO> listaDTO = new ArrayList<ContaSegundaViaDTO>();
			SistemaParametro parametros = fachada.pesquisarParametrosDoSistema();
			
			for (EmitirContaHelper conta : contas) {
				contaHelper = conta;
				
				try {
					setMensagens();
					
					ContaSegundaViaDTO dto = new ContaSegundaViaDTO(
							contaHelper, 
							parametros, 
							getNomeResponsavel(), 
							getEconomias(), 
							getHidrometro(imovel), 
							getEmissao(usuario), 
							situacaoConta);
					
					listaDTO.add(dto);
				} catch (ControladorException e) {
					e.printStackTrace();
				}
			}
			
			helper = new ContaSegundaViaHelper(listaDTO);
		}

		return helper;
	}

	private ControladorFaturamentoLocal getControlador() {
		try {
			ControladorFaturamentoLocalHome localHome = (ControladorFaturamentoLocalHome) ServiceLocator.getInstancia().getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	private String getNomeResponsavel() {
		String nome = null;
		if (contaHelper.getIdClienteResponsavel() != null && !contaHelper.getIdClienteResponsavel().trim().equals("")) {
			FiltroCliente filtro = new FiltroCliente();
			filtro.adicionarParametro(new ParametroSimples(FiltroCliente.ID, contaHelper.getIdClienteResponsavel()));
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
		Collection categorias = fachada.obterQuantidadeEconomiasCategoria(new Imovel(contaHelper.getIdImovel()));
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
	private Collection<EmitirContaHelper> pesquisarContas(Integer idContaHistorico, Collection idsConta, boolean cobrarTaxaEmissaoConta, Short contaSemCodigoBarras) {
		if (idContaHistorico == null) {
			return fachada.emitir2ViaContas(idsConta, cobrarTaxaEmissaoConta, contaSemCodigoBarras);
		} else {
			return fachada.emitir2ViaContasHistorico(idsConta, cobrarTaxaEmissaoConta, contaSemCodigoBarras);
		}
	}

	private void setMensagens() throws ControladorException {
		try {
			contaHelper.setMensagensFixas(repositorio.pesquisarContaMensagemFixa());

			Object[] mensagens = obterMensagens();
			contaHelper.setPrimeiraParte((String) mensagens[0]);
			contaHelper.setSegundaParte((String) mensagens[1]);
			contaHelper.setTerceiraParte((String) mensagens[2]);

			String[] mensagensAnormalidade = controlador.obterMensagemAnormalidadeConsumo(contaHelper);
			if (mensagensAnormalidade != null)
				contaHelper.setMensagemAnormalidade(mensagensAnormalidade[0] + mensagensAnormalidade[1]);

			contaHelper.setMensagemDebitos(obterMensagemDebitos());

			contaHelper.setMensagemQuitacao(controlador.obterMsgQuitacaoDebitos(new Imovel(contaHelper.getIdImovel()), contaHelper.getAmReferencia()));
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		} catch (ControladorException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	private Object[] obterMensagens() throws ControladorException {
		try {
			Object[] mensagens = repositorio.pesquisarParmsContaMensagem(contaHelper, null, contaHelper.getIdGerenciaRegional(), contaHelper.getIdLocalidade(), contaHelper.getIdSetorComercial());

			if (mensagens == null)
				mensagens = repositorio.pesquisarParmsContaMensagem(contaHelper, null, contaHelper.getIdGerenciaRegional(), contaHelper.getIdLocalidade(), null);

			if (mensagens == null)
				mensagens = repositorio.pesquisarParmsContaMensagem(contaHelper, null, contaHelper.getIdGerenciaRegional(), null, null);

			if (mensagens == null)
				mensagens = repositorio.pesquisarParmsContaMensagem(contaHelper, contaHelper.getIdFaturamentoGrupo(), null, null, null);

			if (mensagens == null)
				mensagens = repositorio.pesquisarParmsContaMensagem(contaHelper, null, null, null, null);

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

		ObterDebitoImovelOuClienteHelper debitoImovelClienteHelper = fachada.obterDebitoImovelOuCliente(1, contaHelper.getIdImovel().toString(), null, null, 
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
