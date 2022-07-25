package gcom.faturamento.bo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;

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
import gcom.faturamento.bean.EntradaParcelamentoDTO;
import gcom.faturamento.bean.EntradaParcelamentoHelper;
import gcom.faturamento.conta.ComunicadoEmitirConta;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

public class EntradaParcelamentoBO {

	private Fachada fachada;
	private ControladorFaturamentoLocal controlador;
	private IRepositorioFaturamento repositorio;

	private Collection<EmitirContaHelper> guiaPagamentos;
	private EmitirContaHelper contaHelper;

	public EntradaParcelamentoBO(Integer idParcelamento) {
		super();

		fachada = Fachada.getInstancia();
		repositorio = RepositorioFaturamentoHBM.getInstancia();
		controlador = getControlador();
		guiaPagamentos = pesquisarGuiaPagamento(idParcelamento);
	}

	public EntradaParcelamentoHelper criar(Imovel imovel, Usuario usuario, String situacaoConta) {
		EntradaParcelamentoHelper helper = null;
		
		if (guiaPagamentos != null && !guiaPagamentos.isEmpty()) {
			List<EntradaParcelamentoDTO> listaDTO = new ArrayList<EntradaParcelamentoDTO>();
			SistemaParametro parametros = fachada.pesquisarParametrosDoSistema();
			
			for (EmitirContaHelper guiaPagamento : guiaPagamentos) {
				contaHelper = guiaPagamento;
				
				try {
					
					StringBuilder nossoNumero = null;
					String nossoNumeroSemDV = null;
					String numeroCarteira = null;
					Integer tipoDocumento = null;
					String banco = null;
					String numeroReferencia = null;
					if((guiaPagamento.getCpf() != null && guiaPagamento.getCodigoConvenio() != null ) || (guiaPagamento.getCpf() != null && guiaPagamento.getCodigoConvenio() != null ) ) {
						nossoNumero = this.controlador.obterNossoNumeroFichaCompensacao("1", guiaPagamento.getIdConta().toString(), guiaPagamento.getCodigoConvenio());
						nossoNumeroSemDV = nossoNumero.toString().substring(3, 20);
					}	
					
					if(nossoNumeroSemDV != null) {
						
						banco = "Banco do Brasil";
						numeroCarteira = ConstantesSistema.CARTEIRA_CONTA;
						tipoDocumento = 1;
						numeroReferencia = String.valueOf(guiaPagamento.getAmReferencia()) + guiaPagamento.getIdConta().toString();
					} else {
						tipoDocumento = 2;
					}
					
					
					EntradaParcelamentoDTO dto = new EntradaParcelamentoDTO(
							helper,
							parametros, 
							getNomeResponsavel(), 
							getEmissao(usuario), 
							nossoNumeroSemDV,
							numeroCarteira,
							banco,
							numeroReferencia);
					
					listaDTO.add(dto);
				} catch (ControladorException e) {
					e.printStackTrace();
				}
			}
			
			helper = new EntradaParcelamentoHelper(listaDTO);
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

	private String getEmissao(Usuario usuario) {
		if (usuario != null) {
			return "por " + usuario.getNomeUsuario();
		} else {
			return "pela INTERNET";
		}
	}
	

	@SuppressWarnings("rawtypes")
	private Collection<EmitirContaHelper> pesquisarGuiaPagamento(Integer idParcelamento) {
			return fachada.emitirGuiaPagamento(idParcelamento);
	}


}
