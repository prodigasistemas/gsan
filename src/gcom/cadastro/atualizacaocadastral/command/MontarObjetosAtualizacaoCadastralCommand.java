package gcom.cadastro.atualizacaocadastral.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.commons.lang.StringUtils;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteAtualizacaoCadastral;
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
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastralBuilder;
import gcom.cadastro.imovel.ImovelRamoAtividadeAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelTipoOcupante;
import gcom.cadastro.imovel.ImovelTipoOcupanteQuantidadeAtualizacaoCadastral;
import gcom.cadastro.imovel.Subcategoria;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;

public class MontarObjetosAtualizacaoCadastralCommand extends AbstractAtualizacaoCadastralCommand {
	
	private AtualizacaoCadastral atualizacaoCadastral;
	private AtualizacaoCadastralImovel atualizacaoCadastralImovel;
	private int matriculaImovel;
	private int matriculaUsuario;
	private int matriculaResponsavel;
	private int matriculaProprietario;
	private int tipoOperacao;
	
	private IRepositorioClienteImovel repositorioClienteImovel;
	
	private final String USUARIO_IGUAL_PROPRIETARIO = "1";
	private final String RESPONSAVEL_IGUAL_USUARIO = "0";
	private final String RESPONSAVEL_IGUAL_PROPRIETARIO = "1";
	

	public MontarObjetosAtualizacaoCadastralCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil,
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
		this.tipoOperacao = Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("tipoOperacao"));
		
		salvarObjetosAtualizacaoCadastral();
	}
	
	public void salvarObjetosAtualizacaoCadastral() throws Exception {
		verificarControle();
		
		salvarImovel();
		salvarClienteUsuario();
		salvarClienteProprietario();
		salvarClienteResponsavel();
	}

	private void salvarImovel() throws Exception {
		ImovelAtualizacaoCadastralBuilder builder = new ImovelAtualizacaoCadastralBuilder(atualizacaoCadastral, atualizacaoCadastralImovel);
		ImovelAtualizacaoCadastral imovelTxt = builder.getImovelAtualizacaoCadastral();
		
		ImovelAtualizacaoCadastral imovelAtualizacaoCadastralBase = controladorAtualizacaoCadastral.pesquisarImovelAtualizacaoCadastral(matriculaImovel);
		
		if (imovelAtualizacaoCadastralBase == null) {
			imovelAtualizacaoCadastralBase = new ImovelAtualizacaoCadastral(ConstantesSistema.ZERO.intValue());
			tipoOperacao = AlteracaoTipo.INCLUSAO;
		}
		
		salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, imovelAtualizacaoCadastralBase, imovelTxt, 
				matriculaImovel, tipoOperacao, atualizacaoCadastralImovel.getImovelControle().getId());
		
		salvarRamoAtividade();
		salvarImovelSubcategoria();
		salvarImovelQuantidadesOcupantes();
	}
	
	private void salvarRamoAtividade() throws Exception {
		for (DadoAtualizacaoRamoAtividade ramo: atualizacaoCadastralImovel.getDadosRamoAtividade()){
			boolean existeRamoAtividadeAtualizacao = repositorioCadastro.existeImovelRamoAtividadeAtualizacaoCadastral(matriculaImovel, ramo.getId());
			
			if (!existeRamoAtividadeAtualizacao) {
				ImovelRamoAtividadeAtualizacaoCadastral ramoAtividadeTxt = new ImovelRamoAtividadeAtualizacaoCadastral();
				ramoAtividadeTxt.setImovel(new Imovel(matriculaImovel));
				ramoAtividadeTxt.setRamoAtividade(new RamoAtividade(ramo.getId()));
				
				int tipoOperacao = Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("tipoOperacao"));
				
				salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, new ImovelRamoAtividadeAtualizacaoCadastral(),
						ramoAtividadeTxt, matriculaImovel, tipoOperacao, atualizacaoCadastralImovel.getImovelControle().getId());
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void salvarImovelSubcategoria() throws ControladorException {
		List<ImovelSubcategoriaAtualizacaoCadastral> subcategorias = getImovelSubcategorias();
		
		for (ImovelSubcategoriaAtualizacaoCadastral subcategoria : subcategorias) {
			Collection imovelSubcategorias = controladorAtualizacaoCadastral.pesquisarImovelSubcategoriaAtualizacaoCadastral(matriculaImovel, subcategoria.getSubcategoria().getId(), null);
			ImovelSubcategoriaAtualizacaoCadastral imovelSubcategoriaAtualizacaoCadastral = null;
			if (imovelSubcategorias.isEmpty()) {
				imovelSubcategoriaAtualizacaoCadastral = new ImovelSubcategoriaAtualizacaoCadastral();
			} else {
				imovelSubcategoriaAtualizacaoCadastral = (ImovelSubcategoriaAtualizacaoCadastral) imovelSubcategorias.iterator().next();
			}

			salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, imovelSubcategoriaAtualizacaoCadastral, subcategoria, 
					matriculaImovel, tipoOperacao, atualizacaoCadastralImovel.getImovelControle().getId());
		}
	}
	
	@SuppressWarnings("unchecked")
	private void salvarImovelQuantidadesOcupantes() throws ControladorException {
	    Collection<ImovelTipoOcupanteQuantidadeAtualizacaoCadastral> tiposImovel = controladorAtualizacaoCadastral.pesquisarOcupantesAtualizacaoCadastral(matriculaImovel);
	    
	    Map<Integer, ImovelTipoOcupanteQuantidadeAtualizacaoCadastral> mapTiposImovel = new HashMap<Integer, ImovelTipoOcupanteQuantidadeAtualizacaoCadastral>();
	    
	    for(ImovelTipoOcupanteQuantidadeAtualizacaoCadastral tipo : tiposImovel){
	        mapTiposImovel.put(tipo.getTipoOcupante().getId(), tipo);
	    }
	    
	    Collection<ImovelTipoOcupante> todosTipos = controladorUtil.listar(ImovelTipoOcupante.class);
	    for (ImovelTipoOcupante tipo : todosTipos) {
	        Integer qtd = Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("tipoOcupante" + tipo.getDescricaoSemCaracteresEspeciais()));
	        
	        ImovelTipoOcupanteQuantidadeAtualizacaoCadastral valorBase = mapTiposImovel.get(tipo.getId());
	        if (valorBase == null){
	            valorBase = new ImovelTipoOcupanteQuantidadeAtualizacaoCadastral(0);
	        }
	        ImovelTipoOcupanteQuantidadeAtualizacaoCadastral valorTxt  = new ImovelTipoOcupanteQuantidadeAtualizacaoCadastral();
	        valorTxt.setQuantidade(qtd);
	        valorTxt.setTipoOcupante(tipo);
	        
	        salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, valorBase, valorTxt, matriculaImovel, 
	        		tipoOperacao, atualizacaoCadastralImovel.getImovelControle().getId());
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
		
		if(atualizacaoCadastralImovel.getLinhaCliente("usuarioProprietario").equals(USUARIO_IGUAL_PROPRIETARIO)){
			clienteTxt = new ClienteUsuarioBuilder(atualizacaoCadastralImovel).buildCliente(ClienteRelacaoTipo.PROPRIETARIO);
		}else{
			clienteTxt = new ClienteProprietarioBuilder(atualizacaoCadastralImovel).buildCliente(ClienteRelacaoTipo.PROPRIETARIO);
		}
		
		if (StringUtils.isNotEmpty(atualizacaoCadastralImovel.getLinhaCliente("nomeProprietario"))) {
			salvarCliente(matriculaProprietario, clienteTxt, ClienteRelacaoTipo.PROPRIETARIO, ClienteBuilder.PROPRIETARIO);
		}
	}

	private void salvarClienteResponsavel() throws Exception {
		IClienteAtualizacaoCadastral clienteTxt;
		
		if(atualizacaoCadastralImovel.getLinhaCliente("tipoResponsavel").equals(RESPONSAVEL_IGUAL_USUARIO)){
			clienteTxt = new ClienteUsuarioBuilder(atualizacaoCadastralImovel).buildCliente(ClienteRelacaoTipo.RESPONSAVEL);
		}else if(atualizacaoCadastralImovel.getLinhaCliente("tipoResponsavel").equals(RESPONSAVEL_IGUAL_PROPRIETARIO)){
			clienteTxt = new ClienteProprietarioBuilder(atualizacaoCadastralImovel).buildCliente(ClienteRelacaoTipo.RESPONSAVEL);
		}else{
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
		
		salvarClienteAtualizacaoCadastral(matricula, clienteRelacaoTipo, clienteTxt, dddTelefone, telefone, dddCelular, celular);
	}

	private void salvarClienteAtualizacaoCadastral(int matricula, Short clienteRelacaoTipo, IClienteAtualizacaoCadastral clienteTxt, String dddTelefone, String telefone, String dddCelular, String celular) throws ControladorException {

		salvarClienteFoneAtualizacaoCadastral(dddTelefone, telefone, clienteRelacaoTipo, FoneTipo.RESIDENCIAL, matricula);
		salvarClienteFoneAtualizacaoCadastral(dddCelular, celular, clienteRelacaoTipo, FoneTipo.CELULAR, matricula);
		
		IClienteAtualizacaoCadastral clienteAtualizacaoCadastralBase = null;
		if (clienteTxt.getTipoOperacao() != AlteracaoTipo.INCLUSAO) {
			clienteAtualizacaoCadastralBase = controladorCliente.pesquisarClienteAtualizacaoCadastral(
					matricula, matriculaImovel, new Integer(clienteRelacaoTipo));			
		}else{
			clienteAtualizacaoCadastralBase = new ClienteAtualizacaoCadastral();
		}
		
		salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, clienteAtualizacaoCadastralBase, clienteTxt, 
				matriculaImovel, tipoOperacao, atualizacaoCadastralImovel.getImovelControle().getId());
	}

	private void salvarClienteFoneAtualizacaoCadastral(String ddd, String telefone, Short clienteRelacaoTipo, Integer foneTipo, int matriculaCliente) {
		
		if (!telefone.trim().equals("")) {
			ClienteFoneAtualizacaoCadastral clienteFone = getClienteFoneAtualizacaoCadastral(ddd, telefone, foneTipo, matriculaCliente);

			try {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = controladorCliente
						.pesquisarClienteFoneAtualizacaoCadastral(Integer.valueOf(matriculaCliente), Integer.valueOf(matriculaImovel), foneTipo,
								Integer.valueOf(clienteRelacaoTipo), null).iterator().next();

				salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, clienteFoneAtualizacaoCadastral, clienteFone, 
						matriculaImovel, tipoOperacao, atualizacaoCadastralImovel.getImovelControle().getId());
			} catch (NoSuchElementException e) {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = new ClienteFoneAtualizacaoCadastral();
				try {
					salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, clienteFoneAtualizacaoCadastral, clienteFone, 
							matriculaImovel, tipoOperacao, atualizacaoCadastralImovel.getImovelControle().getId());
				} catch (ControladorException e1) {
					e1.printStackTrace();
				}
			} catch (ControladorException e) {
				e.printStackTrace();
			}
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
	
	private void verificarControle() throws Exception {
		if (atualizacaoCadastralImovel.isImovelNovo())
			inserirControleImovelNovo();
		else
			inserirOuAtualizarControleImovel();
	}

	private void inserirOuAtualizarControleImovel() throws ControladorException {
		ImovelControleAtualizacaoCadastral controle = controladorAtualizacaoCadastral.pesquisarImovelControleAtualizacao(matriculaImovel);
		
		if (controle == null) {
			controle = new ImovelControleAtualizacaoCadastral();
			controle.setImovel(new Imovel(matriculaImovel));
			controle.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(SituacaoAtualizacaoCadastral.TRANSMITIDO));
		}
		
		if (atualizacaoCadastral.getArquivoTexto().isArquivoRetornoTransmissao())
			controle.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(SituacaoAtualizacaoCadastral.TRANSMITIDO));
		
		controle.setDataRetorno(new Date());
		controle.setCadastroOcorrencia(new CadastroOcorrencia(atualizacaoCadastralImovel.getCadastroOcorrencia().getId()));
		
		definirSeImovelFoiAlteradoPelaLojaDuranteRecadastramento(controle);
		
		controladorUtil.inserirOuAtualizar(controle);
		atualizacaoCadastralImovel.setImovelControle(controle);
	}

	private void inserirControleImovelNovo() throws ControladorException {
		ImovelControleAtualizacaoCadastral controle = new ImovelControleAtualizacaoCadastral();
		controle.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(SituacaoAtualizacaoCadastral.TRANSMITIDO));
		controle.setDataGeracao(new Date());
		controle.setDataRetorno(new Date());
		controle.setCadastroOcorrencia(new CadastroOcorrencia(atualizacaoCadastralImovel.getCadastroOcorrencia().getId()));
		
		controladorUtil.inserir(controle);
		atualizacaoCadastralImovel.setImovelControle(controle);
	}
	
	private void definirSeImovelFoiAlteradoPelaLojaDuranteRecadastramento(ImovelControleAtualizacaoCadastral imovelControle) throws ControladorException {
		if (imovelControle == null || imovelControle.getImovel() == null || imovelControle.getImovel().getId() == null) {
			return;
		}
		if (controladorAtualizacaoCadastral.verificarSeHouveAlteracaoNoImovelRelevanteParaAtualizacaoCadastral(imovelControle.getImovel().getId())) {
			imovelControle.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(SituacaoAtualizacaoCadastral.ATUALIZADO_LOJA));
		}
	}
}
