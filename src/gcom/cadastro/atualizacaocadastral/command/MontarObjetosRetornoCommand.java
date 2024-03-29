package gcom.cadastro.atualizacaocadastral.command;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import gcom.atualizacaocadastral.ClienteEnderecoRetorno;
import gcom.atualizacaocadastral.ClienteFoneRetorno;
import gcom.atualizacaocadastral.ClienteImovelRetorno;
import gcom.atualizacaocadastral.ClienteRetorno;
import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.atualizacaocadastral.ImovelRamoAtividadeRetorno;
import gcom.atualizacaocadastral.ImovelRetorno;
import gcom.atualizacaocadastral.ImovelSubcategoriaRetorno;
import gcom.atualizacaocadastral.ImovelTipoOcupanteQuantidadeRetorno;
import gcom.atualizacaocadastral.Visita;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteBuilder;
import gcom.cadastro.cliente.ClienteFoneAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteProprietarioBuilder;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteResponsavelBuilder;
import gcom.cadastro.cliente.ClienteUsuarioBuilder;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.cliente.IClienteAtualizacaoCadastral;
import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.cliente.IRepositorioClienteImovel;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastralBuilder;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelTipoOcupante;
import gcom.cadastro.imovel.Subcategoria;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;

public class MontarObjetosRetornoCommand extends AbstractAtualizacaoCadastralCommand {
	
	private static Logger logger = Logger.getLogger(MontarObjetosRetornoCommand.class);
	
	private static final String MATRICULA_ZERO = "0";

	private AtualizacaoCadastral atualizacaoCadastral;
	private AtualizacaoCadastralImovel atualizacaoCadastralImovel;
	private int matriculaImovel;
	private int matriculaUsuario;
	private int matriculaResponsavel;
	private int matriculaProprietario;
	private Integer idImovelRetorno;

	private IRepositorioClienteImovel repositorioClienteImovel;

	private final String USUARIO_IGUAL_PROPRIETARIO = "1";
	private final String RESPONSAVEL_IGUAL_USUARIO = "0";
	private final String RESPONSAVEL_IGUAL_PROPRIETARIO = "1";


	public MontarObjetosRetornoCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil,
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, ControladorEnderecoLocal controladorEndereco,
			ControladorAtualizacaoCadastralLocal controladorAtualizacaoCadastral, ControladorClienteLocal controladorCliente, IRepositorioClienteImovel repositorioClienteImovel) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorEndereco, controladorAtualizacaoCadastral, controladorCliente);

		this.repositorioClienteImovel = repositorioClienteImovel;
	}

	public void execute(AtualizacaoCadastral atualizacaoCadastral) throws Exception {
		this.atualizacaoCadastral = atualizacaoCadastral;
		this.atualizacaoCadastralImovel = atualizacaoCadastral.getImovelAtual();
		this.matriculaImovel = atualizacaoCadastralImovel.getMatricula();
		this.matriculaUsuario = Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("matriculaUsuario"));
		this.matriculaResponsavel = Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("matriculaResponsavel"));
		this.matriculaProprietario = Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("matriculaProprietario"));

		salvarObjetosRetorno();
		atualizarImovelControle();
	}

	public void salvarObjetosRetorno() throws Exception {
		if (atualizacaoCadastral.getArquivoTexto().isArquivoRetornoTransmissao() || 
		   (atualizacaoCadastral.getArquivoTexto().isArquivoRetornoTodasSituacoes() && atualizacaoCadastralImovel.getImovelControle().isTransmitido()) ||
			atualizacaoCadastralImovel.isImovelNovo()) {
			
			salvarImovelRetorno();
			salvarClienteUsuario();
			salvarClienteProprietario();
			salvarClienteResponsavel();
			salvarImagens();
		} else {
			atualizarRetorno();
			atualizarValorIndicadorTransmissaoCpfCnpj();
		}
	}

	private void salvarImagens() throws Exception {
		for (String nomeImagem : atualizacaoCadastral.getImagens()) {
			String pasta = "/images/" + atualizacaoCadastral.getArquivoTexto().getDescricaoArquivo();

			if (nomeImagem.contains(String.valueOf(atualizacaoCadastralImovel.getMatricula()))) {
				controladorAtualizacaoCadastral.inserirImagemRetorno(atualizacaoCadastralImovel.getMatricula(), nomeImagem, pasta, idImovelRetorno);
			}
		}
	}
	
	private void inserirVisitaParaImovelControle(ImovelControleAtualizacaoCadastral controle) throws ControladorException {
		Visita visita = new Visita(controle, atualizacaoCadastralImovel);
		controladorUtil.inserir(visita);
	}

	private void salvarImovelRetorno() throws Exception {
		ImovelAtualizacaoCadastralBuilder builder = new ImovelAtualizacaoCadastralBuilder(atualizacaoCadastral, atualizacaoCadastralImovel);
		ImovelAtualizacaoCadastral imovelTxt = builder.getImovelAtualizacaoCadastral();

		salvarImovelRetorno(imovelTxt);
		salvarRamoAtividadeRetorno();
		salvarImovelSubcategoriaRetorno();
		salvarImovelQuantidadesOcupantes();
	}

	private void salvarRamoAtividadeRetorno() throws Exception {
		for (DadoAtualizacaoRamoAtividade ramo: atualizacaoCadastralImovel.getDadosRamoAtividade()){
			boolean existeRamoAtividadeAtualizacao = repositorioCadastro.existeImovelRamoAtividadeAtualizacaoCadastral(matriculaImovel, ramo.getId());

			if (!existeRamoAtividadeAtualizacao) {
				ImovelRamoAtividadeRetorno imovelRamoAtividadeRetorno = new ImovelRamoAtividadeRetorno();
				imovelRamoAtividadeRetorno.setImovel(new Imovel(matriculaImovel));
				imovelRamoAtividadeRetorno.setRamoAtividade(new RamoAtividade(ramo.getId()));
				imovelRamoAtividadeRetorno.setUltimaAlteracao(new Date());
				imovelRamoAtividadeRetorno.setIdImovelRetorno(idImovelRetorno);
				controladorUtil.inserir(imovelRamoAtividadeRetorno);
			}
		}
	}

	private void salvarImovelSubcategoriaRetorno() throws ControladorException {
		List<ImovelSubcategoriaAtualizacaoCadastral> subcategorias = getImovelSubcategorias();

		for (ImovelSubcategoriaAtualizacaoCadastral subcategoria : subcategorias) {
			salvarImovelSubcategoriaRetorno(subcategoria);
		}
	}

	private List<ImovelSubcategoriaAtualizacaoCadastral> getImovelSubcategorias() {
		List<ImovelSubcategoriaAtualizacaoCadastral> subcategorias = new ArrayList<ImovelSubcategoriaAtualizacaoCadastral>();
		subcategorias.addAll(buildImovelSubcategorias(TipoEconomia.RESIDENCIAL));
		subcategorias.addAll(buildImovelSubcategorias(TipoEconomia.COMERCIAL));
		subcategorias.addAll(buildImovelSubcategorias(TipoEconomia.INDUSTRIAL));
		subcategorias.addAll(buildImovelSubcategorias(TipoEconomia.PUBLICO));
		return subcategorias;
	}

	private List<ImovelSubcategoriaAtualizacaoCadastral> buildImovelSubcategorias(TipoEconomia tipoEconomia) {
		List<ImovelSubcategoriaAtualizacaoCadastral> subcategorias = new ArrayList<ImovelSubcategoriaAtualizacaoCadastral>();

		String descricaoSubcategoria = String.valueOf(tipoEconomia.getCodigo());

		String codigoSubcategoria = "";

		for (int j = 1; j < 5; j++) {
			codigoSubcategoria = descricaoSubcategoria + j;
			short qtdEconomias = Short.parseShort(atualizacaoCadastralImovel.getLinhaImovel("subcategoria" + codigoSubcategoria));
			if(qtdEconomias != 0){
				ImovelSubcategoriaAtualizacaoCadastral subcategoria = new ImovelSubcategoriaAtualizacaoCadastral();

				subcategoria.setImovel(new Imovel(matriculaImovel));
				subcategoria.setQuantidadeEconomias(qtdEconomias);
				subcategoria.setDescricaoSubcategoria(codigoSubcategoria);
				subcategoria.setDescricaoCategoria(tipoEconomia.getDescricao());

				TipoSubcategoria tipoSubcategoria = TipoSubcategoria.getByCodigo(codigoSubcategoria);
				subcategoria.setCategoria(new Categoria(tipoSubcategoria.getIdCategoria()));
				subcategoria.setSubcategoria(new Subcategoria(tipoSubcategoria.getIdSubcategoria()));

				subcategorias.add(subcategoria);
			}
		}

		return subcategorias;
	}


	private void salvarClienteUsuario() throws Exception {
		IClienteAtualizacaoCadastral clienteTxt = new ClienteUsuarioBuilder(atualizacaoCadastralImovel).buildCliente(ClienteRelacaoTipo.USUARIO);

		if (matriculaUsuario != 0 || StringUtils.isNotEmpty(atualizacaoCadastralImovel.getLinhaCliente("nomeUsuario"))) {
			salvarCliente(matriculaUsuario, clienteTxt, ClienteRelacaoTipo.USUARIO, ClienteBuilder.USUARIO);
		}
	}

	private void salvarClienteProprietario() throws Exception {
		IClienteAtualizacaoCadastral clienteTxt;

		if (atualizacaoCadastralImovel.getLinhaCliente("usuarioProprietario").equals(USUARIO_IGUAL_PROPRIETARIO)) {
			clienteTxt = new ClienteUsuarioBuilder(atualizacaoCadastralImovel).buildCliente(ClienteRelacaoTipo.PROPRIETARIO);
		} else {
			clienteTxt = new ClienteProprietarioBuilder(atualizacaoCadastralImovel).buildCliente(ClienteRelacaoTipo.PROPRIETARIO);
		}

		if (StringUtils.isNotEmpty(atualizacaoCadastralImovel.getLinhaCliente("nomeProprietario"))) {
			salvarCliente(matriculaProprietario, clienteTxt, ClienteRelacaoTipo.PROPRIETARIO, ClienteBuilder.PROPRIETARIO);
		}
	}

	private void salvarClienteResponsavel() throws Exception {
		IClienteAtualizacaoCadastral clienteTxt;
		String tipoResponsavel = atualizacaoCadastralImovel.getLinhaCliente("tipoResponsavel");
		String usuarioProprietario = atualizacaoCadastralImovel.getLinhaCliente("usuarioProprietario");

		if (tipoResponsavel.equals(RESPONSAVEL_IGUAL_USUARIO)) {
			clienteTxt = new ClienteUsuarioBuilder(atualizacaoCadastralImovel).buildCliente(ClienteRelacaoTipo.RESPONSAVEL);
		} else if (tipoResponsavel.equals(RESPONSAVEL_IGUAL_PROPRIETARIO)) {
			if (usuarioProprietario.equals(USUARIO_IGUAL_PROPRIETARIO)) {
				clienteTxt = new ClienteUsuarioBuilder(atualizacaoCadastralImovel).buildCliente(ClienteRelacaoTipo.RESPONSAVEL);
			} else {
				clienteTxt = new ClienteProprietarioBuilder(atualizacaoCadastralImovel).buildCliente(ClienteRelacaoTipo.RESPONSAVEL);
			}
		} else {
			clienteTxt = new ClienteResponsavelBuilder(atualizacaoCadastralImovel).buildCliente(ClienteRelacaoTipo.RESPONSAVEL);
		}

		if (StringUtils.isNotEmpty(atualizacaoCadastralImovel.getLinhaCliente("nomeResponsavel"))) {
			salvarCliente(matriculaResponsavel, clienteTxt, ClienteRelacaoTipo.RESPONSAVEL, ClienteBuilder.RESPONSAVEL);
		}
	}

	private void salvarCliente(int matricula, IClienteAtualizacaoCadastral clienteTxt, Short clienteRelacaoTipo, String tipoCliente) throws Exception{
		Integer tipoOperacaoCliente = getTipoOperacaoCliente(matricula, matriculaImovel, clienteTxt.getCpf(), clienteRelacaoTipo, repositorioClienteImovel);
		clienteTxt.setTipoOperacao(tipoOperacaoCliente);

		String dddTelefone = atualizacaoCadastralImovel.getLinhaCliente("dddTelefone" + tipoCliente);
		String telefone = atualizacaoCadastralImovel.getLinhaCliente("telefone" + tipoCliente);
		String dddCelular = atualizacaoCadastralImovel.getLinhaCliente("dddTelefone" + tipoCliente);
		String celular = atualizacaoCadastralImovel.getLinhaCliente("celular" + tipoCliente);

		salvarClienteRetorno(matricula, clienteRelacaoTipo, clienteTxt, dddTelefone, telefone, dddCelular, celular);
	}

	private void salvarClienteRetorno(int matricula, Short clienteRelacaoTipo, IClienteAtualizacaoCadastral clienteTxt, 
			String dddTelefone, String telefone, String dddCelular, String celular) throws ControladorException {
		Integer idclienteRetorno = salvarClienteRetorno(clienteTxt, clienteRelacaoTipo);
		salvarClienteImovelRetorno(clienteTxt, matriculaImovel, idclienteRetorno);

		salvarClienteFoneRetorno(dddTelefone, telefone, clienteRelacaoTipo, FoneTipo.RESIDENCIAL, matricula, idclienteRetorno);
		salvarClienteFoneRetorno(dddCelular, celular, clienteRelacaoTipo, FoneTipo.CELULAR, matricula, idclienteRetorno);
		salvarClienteEnderecoRetorno(matricula, clienteTxt, idclienteRetorno);
	}

	private void salvarClienteFoneRetorno(String ddd, String tipoClientFone, Short clienteRelacaoTipo, Integer foneTipo, int matriculaCliente, Integer idClienteRetorno) throws ControladorException {
		if (!tipoClientFone.trim().equals("")) {
			ClienteFoneAtualizacaoCadastral clienteFone = getClienteFoneAtualizacaoCadastral(ddd, tipoClientFone, foneTipo, matriculaCliente);

			ClienteFoneRetorno clienteFoneRetorno = new ClienteFoneRetorno(clienteFone);
			clienteFoneRetorno.setUltimaAlteracao(new Date());
			clienteFoneRetorno.setIdClienteRetorno(idClienteRetorno);
			controladorUtil.inserir(clienteFoneRetorno);
		}
	}

	private ClienteFoneAtualizacaoCadastral getClienteFoneAtualizacaoCadastral(String ddd, String tipoClientFone, Integer foneTipo, int matriculaCliente) {
		ClienteFoneAtualizacaoCadastral clienteFone = new ClienteFoneAtualizacaoCadastral();

		if (tipoClientFone.length() >= (IClienteFone.TAMANHO_TELEFONE_COM_DDD - 1)) {
			clienteFone.setDdd(tipoClientFone.substring(0, 2));
			clienteFone.setTelefone(tipoClientFone.substring(2));
		} else {
			clienteFone.setDdd(ddd);
			clienteFone.setTelefone(tipoClientFone);
		}
		clienteFone.setIdFoneTipo(foneTipo);
		clienteFone.setIdCliente(matriculaCliente);
		return clienteFone;
	}

	private void salvarClienteEnderecoRetorno(Integer matriculaCliente, IClienteAtualizacaoCadastral clienteAtualizacaoCadastral, Integer idClienteRetorno) throws ControladorException {
		ClienteEnderecoRetorno clienteEnderecoRetorno = new ClienteEnderecoRetorno(matriculaCliente, clienteAtualizacaoCadastral);
		clienteEnderecoRetorno.setUltimaAlteracao(new Date());
		clienteEnderecoRetorno.setIdClienteRetorno(idClienteRetorno);
		controladorUtil.inserir(clienteEnderecoRetorno);
	}

	private void salvarImovelRetorno(ImovelAtualizacaoCadastral imovelTxt) throws ControladorException {
		ImovelRetorno imovelRetorno = new ImovelRetorno(imovelTxt);
		imovelRetorno.setUltimaAlteracao(new Date());
		idImovelRetorno = (Integer) controladorUtil.inserir(imovelRetorno);
	}

	private void salvarImovelSubcategoriaRetorno(ImovelSubcategoriaAtualizacaoCadastral imovelSubcategoriaTxt) throws ControladorException {
		ImovelSubcategoriaRetorno imovelSubcategoriaRetorno = new ImovelSubcategoriaRetorno();
		imovelSubcategoriaRetorno.setImovel(imovelSubcategoriaTxt.getImovel());
		imovelSubcategoriaRetorno.setSubcategoria(imovelSubcategoriaTxt.getSubcategoria());
		imovelSubcategoriaRetorno.setQuantidadeEconomias(imovelSubcategoriaTxt.getQuantidadeEconomias());
		imovelSubcategoriaRetorno.setUltimaAlteracao(new Date());
		imovelSubcategoriaRetorno.setIdImovelRetorno(idImovelRetorno);
		controladorUtil.inserir(imovelSubcategoriaRetorno);
	}

    @SuppressWarnings("unchecked")
	private void salvarImovelQuantidadesOcupantes() throws ControladorException {
        Collection<ImovelTipoOcupante> todosTipos = controladorUtil.listar(ImovelTipoOcupante.class);

        for (ImovelTipoOcupante tipo : todosTipos) {
            ImovelTipoOcupanteQuantidadeRetorno retorno = new ImovelTipoOcupanteQuantidadeRetorno();
            retorno.setIdImovelRetorno(idImovelRetorno);
            retorno.setImovel(new Imovel(matriculaImovel));
            retorno.setTipoOcupante(tipo);
            Integer qtd = Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("tipoOcupante" + tipo.getDescricaoSemCaracteresEspeciais()));
            retorno.setQuantidade(qtd);

            controladorUtil.inserir(retorno);
        }
    }

	private Integer salvarClienteRetorno(IClienteAtualizacaoCadastral clienteTxt, Short clienteRelacaoTipo) throws ControladorException {
		ClienteRetorno clienteRetorno = new ClienteRetorno(clienteTxt);
		clienteRetorno.setUltimaAlteracao(new Date());
		
		definirValorIndicadorTransmissaoCpfCnpj(clienteRetorno, clienteRelacaoTipo);
		return (Integer) controladorUtil.inserir(clienteRetorno);

	}

	private void salvarClienteImovelRetorno(IClienteAtualizacaoCadastral clienteTxt, int matriculaImovel, Integer idClienteRetorno) throws ControladorException {
		ClienteImovelRetorno clienteImovelRetorno = new ClienteImovelRetorno(clienteTxt, matriculaImovel);
		clienteImovelRetorno.setUltimaAlteracao(new Date());
		clienteImovelRetorno.setIdClienteRetorno(idClienteRetorno);
		clienteImovelRetorno.setIdImovelRetorno(idImovelRetorno);
		controladorUtil.inserir(clienteImovelRetorno);
	}

	private void atualizarImovelControle() throws ControladorException {
		ImovelControleAtualizacaoCadastral controle = null;
		if (atualizacaoCadastralImovel.isImovelNovo()) {
			controle = atualizacaoCadastralImovel.getImovelControle();
		} else {
			controle = controladorAtualizacaoCadastral.obterImovelControle(atualizacaoCadastralImovel.getMatricula());
		}

		if (controle != null) {
		    if (controle.isTransmitido() || controle.isRevisita()) {
		        inserirVisitaParaImovelControle(controle);
		    }
		    
		    // Apenas no retorno da fiscalizacao
		    if (atualizacaoCadastral.getArquivoTexto().isArquivoRetornoFiscalizacao()) {
		    	controle.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(SituacaoAtualizacaoCadastral.AGUARDANDO_ANALISE));
		    }
		    
			controle.setImovelRetorno(new ImovelRetorno(idImovelRetorno));
			controladorUtil.atualizar(controle);
		}
	}

	private void atualizarRetorno() throws ControladorException {
		ImovelControleAtualizacaoCadastral controle = controladorAtualizacaoCadastral.obterImovelControle(atualizacaoCadastralImovel.getMatricula());
		idImovelRetorno = controle.getImovelRetorno() == null ? null : controle.getImovelRetorno().getId();
		
		atualizarImovelSubcategoriaRetorno();
		
		if (atualizacaoCadastral.getArquivoTexto().isArquivoRetornoFiscalizacao())
			controladorAtualizacaoCadastral.atualizarSubcategoriaAoFiscalizar(atualizacaoCadastralImovel.getMatricula());
		
		controladorAtualizacaoCadastral.atualizarRetornoPreAprovado(controle);
	}
	
	private void atualizarImovelSubcategoriaRetorno() throws ControladorException{
			List<ImovelSubcategoriaAtualizacaoCadastral> subcategorias = getImovelSubcategorias();
			for (ImovelSubcategoriaAtualizacaoCadastral subcategoria : subcategorias) {
				boolean existeSubcategoria = controladorAtualizacaoCadastral.existeSubcategoriaRetorno(subcategoria.getImovel().getId(), subcategoria.getSubcategoria().getId());
				
				if (!existeSubcategoria) {
					salvarImovelSubcategoriaRetorno(subcategoria);
				}
			}
	}
	
	private void definirValorIndicadorTransmissaoCpfCnpj(ClienteRetorno clienteRetorno, Short clienteRelacaoTipo) {
		String indicadorTransmissao = atualizacaoCadastralImovel
				.getLinhaCliente(String.format("%s%s", ParseClienteCommand.INDICADOR_TRANSMISSAO_CPF_CNPJ,
						ClienteRelacaoTipo.converterRelacaoTipo(clienteRelacaoTipo.intValue())));
		boolean indicadorTransmissaoCpfCnpj = Boolean.parseBoolean(indicadorTransmissao);
		if (indicadorTransmissaoCpfCnpj) {
			clienteRetorno.setIndicadorTransmissaoCpfCnpj(ConstantesSistema.SIM);
		} else {
			clienteRetorno.setIndicadorTransmissaoCpfCnpj(ConstantesSistema.NAO);
		}
	}
	
	private void atualizarValorIndicadorTransmissaoCpfCnpj() {
		for (String tipoCliente : ParseClienteCommand.TIPOS_CLIENTE) {
			boolean indicador = Boolean.parseBoolean(this.atualizacaoCadastralImovel.getLinhaCliente(
					String.format("%s%s", ParseClienteCommand.INDICADOR_TRANSMISSAO_CPF_CNPJ, tipoCliente)));
			String matriculaCliente = atualizacaoCadastralImovel.getLinhaCliente(String.format("matricula%s", tipoCliente));
			if (StringUtils.isNotEmpty(matriculaCliente) && !MATRICULA_ZERO.equalsIgnoreCase(matriculaCliente)) {
				try {
					ClienteRetorno clienteRetorno = controladorAtualizacaoCadastral
							.pesquisarClienteRetornoPorMatriculaClienteETipoRelacao(
									Integer.parseInt(matriculaCliente),
									ClienteRelacaoTipo.converterRelacaoTipo(tipoCliente));
					
					if (clienteRetorno != null) {
						// Pela regra, deve-se alterar apenas para verdadeiro se houver transmissao
						if (indicador) {
							clienteRetorno.setIndicadorTransmissaoCpfCnpj(ConstantesSistema.SIM);
						} else if (clienteRetorno.getIndicadorTransmissaoCpfCnpj() == null) { // Preenche como nao apenas se for null
							clienteRetorno.setIndicadorTransmissaoCpfCnpj(ConstantesSistema.NAO);
						}
						
						controladorUtil.atualizar(clienteRetorno);
					}
				} catch (ControladorException e) {
					logger.error("Ocorreu algo inexperado ao definir indicador de transmissao cpf/cnpj", e);
				}
			}
		}
	}
	
}
