package gcom.cadastro.atualizacaocadastral.command;

import gcom.atualizacaocadastral.ClienteEnderecoRetorno;
import gcom.atualizacaocadastral.ClienteFoneRetorno;
import gcom.atualizacaocadastral.ClienteImovelRetorno;
import gcom.atualizacaocadastral.ClienteRetorno;
import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.atualizacaocadastral.ImagemRetorno;
import gcom.atualizacaocadastral.ImovelRamoAtividadeRetorno;
import gcom.atualizacaocadastral.ImovelRetorno;
import gcom.atualizacaocadastral.ImovelSubcategoriaRetorno;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.cliente.ClienteBuilder;
import gcom.cadastro.cliente.ClienteFoneAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteProprietarioBuilder;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteResponsavelBuilder;
import gcom.cadastro.cliente.ClienteUsuarioBuilder;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.cliente.IClienteAtualizacaoCadastral;
import gcom.cadastro.cliente.IRepositorioClienteImovel;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastralBuilder;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.Subcategoria;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class MontarObjetosRetornoCommand extends AbstractAtualizacaoCadastralCommand {
	
	private AtualizacaoCadastral atualizacaoCadastral;
	private AtualizacaoCadastralImovel atualizacaoCadastralImovel;
	private int matriculaImovel;
	private int matriculaUsuario;
	private int matriculaResponsavel;
	private int matriculaProprietario;
	private int tipoOperacao;
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
		this.matriculaImovel = Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("matricula"));
		this.matriculaUsuario = Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("matriculaUsuario"));
		this.matriculaResponsavel = Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("matriculaResponsavel"));
		this.matriculaProprietario = Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("matriculaProprietario"));
		this.tipoOperacao = Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("tipoOperacao"));
		
		salvarObjetosRetorno();
	}
	
	public void salvarObjetosRetorno() throws Exception {
		salvarImovelRetorno();
		
		salvarClienteUsuario();
		salvarClienteProprietario();
		salvarClienteResponsavel();
		
		salvarImagens();
	}

	private void salvarImovelRetorno() throws Exception {
		ImovelAtualizacaoCadastralBuilder builder = new ImovelAtualizacaoCadastralBuilder(matriculaImovel, atualizacaoCadastral, atualizacaoCadastralImovel, tipoOperacao);
		ImovelAtualizacaoCadastral imovelTxt = builder.getImovelAtualizacaoCadastral();
		
		salvarImovelRetorno(imovelTxt);
		salvarRamoAtividadeRetorno();
		salvarImovelSubcategoriaRetorno();
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
			salvarImovelSubcategoriaRetorno(subcategoria, idImovelRetorno);
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
		
		if (matriculaUsuario != 0) {
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
		
		String telefone = atualizacaoCadastralImovel.getLinhaCliente("telefone" + tipoCliente);
		String celular = atualizacaoCadastralImovel.getLinhaCliente("celular" + tipoCliente);
		
		salvarClienteRetorno(matricula, clienteRelacaoTipo, clienteTxt, telefone, celular);
	}

	private void salvarClienteRetorno(int matricula, Short clienteRelacaoTipo, IClienteAtualizacaoCadastral clienteTxt, String telefone, String celular) throws ControladorException {
		Integer idclienteRetorno = salvarClienteRetorno(clienteTxt);
		salvarClienteImovelRetorno(clienteTxt, matriculaImovel, idclienteRetorno);
		
		salvarClienteFoneRetorno(telefone, clienteRelacaoTipo, FoneTipo.RESIDENCIAL, matricula, idclienteRetorno);
		salvarClienteFoneRetorno(celular, clienteRelacaoTipo, FoneTipo.CELULAR, matricula, idclienteRetorno);
		salvarClienteEnderecoRetorno(matricula, clienteTxt, idclienteRetorno);
	}
	
	private void salvarClienteFoneRetorno(String tipoClientFone, Short clienteRelacaoTipo, Integer foneTipo, int matriculaCliente, Integer idClienteRetorno) throws ControladorException {
		if (!tipoClientFone.trim().equals("")) {
			ClienteFoneAtualizacaoCadastral clienteFone = getClienteFoneAtualizacaoCadastral(tipoClientFone, foneTipo, matriculaCliente);
			
			ClienteFoneRetorno clienteFoneRetorno = new ClienteFoneRetorno(clienteFone);
			clienteFoneRetorno.setUltimaAlteracao(new Date());
			clienteFoneRetorno.setIdClienteRetorno(idClienteRetorno);
			controladorUtil.inserir(clienteFoneRetorno);
		}
	}

	private ClienteFoneAtualizacaoCadastral getClienteFoneAtualizacaoCadastral(String tipoClientFone, Integer foneTipo, int matriculaCliente) {
		ClienteFoneAtualizacaoCadastral clienteFone = new ClienteFoneAtualizacaoCadastral();

		if (tipoClientFone.length() > 2) {
			clienteFone.setDdd(tipoClientFone.substring(0, 2));
			clienteFone.setTelefone(tipoClientFone.substring(2));
		} else {
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
	
	private void salvarImovelSubcategoriaRetorno(ImovelSubcategoriaAtualizacaoCadastral imovelSubcategoriaTxt, Integer idImovelRetorno) throws ControladorException {
		ImovelSubcategoriaRetorno imovelSubcategoriaRetorno = new ImovelSubcategoriaRetorno();
		imovelSubcategoriaRetorno.setImovel(imovelSubcategoriaTxt.getImovel());
		imovelSubcategoriaRetorno.setSubcategoria(imovelSubcategoriaTxt.getSubcategoria());
		imovelSubcategoriaRetorno.setQuantidadeEconomias(imovelSubcategoriaTxt.getQuantidadeEconomias());
		imovelSubcategoriaRetorno.setUltimaAlteracao(new Date());
		imovelSubcategoriaRetorno.setIdImovelRetorno(idImovelRetorno);
		controladorUtil.inserir(imovelSubcategoriaRetorno);
	}
	
	private Integer salvarClienteRetorno(IClienteAtualizacaoCadastral clienteTxt) throws ControladorException {
		ClienteRetorno clienteRetorno = new ClienteRetorno(clienteTxt);
		clienteRetorno.setUltimaAlteracao(new Date());
		return (Integer)controladorUtil.inserir(clienteRetorno);
		
	}
	
	private void salvarClienteImovelRetorno(IClienteAtualizacaoCadastral clienteTxt, int matriculaImovel, Integer idClienteRetorno) throws ControladorException {
		ClienteImovelRetorno clienteImovelRetorno = new ClienteImovelRetorno(clienteTxt, matriculaImovel);
		clienteImovelRetorno.setUltimaAlteracao(new Date());
		clienteImovelRetorno.setIdClienteRetorno(idClienteRetorno);
		clienteImovelRetorno.setIdImovelRetorno(idImovelRetorno);
		controladorUtil.inserir(clienteImovelRetorno);
	}
	
	private void salvarImagens() throws Exception {
		for (String nomeImagem : atualizacaoCadastral.getImagens()) {

			String caminhoJboss = System.getProperty("jboss.server.home.dir");
			String pasta = "/images/cadastro/" + atualizacaoCadastral.getArquivoTexto().getDescricaoArquivo();
			
			if (nomeImagem.contains(Integer.toString(atualizacaoCadastralImovel.getMatricula()))) {
				inserirImagemImovel(atualizacaoCadastralImovel.getMatricula(), nomeImagem, caminhoJboss, pasta);
			}
		}
	}

	private void inserirImagemImovel(Integer matricula, String nomeImagem, String caminhoJboss, String pasta) {
		
		try {
			File imagem = new File(caminhoJboss + pasta, nomeImagem);
			
			ImagemRetorno imagemRetorno = new ImagemRetorno();
			imagemRetorno.setIdImovelRetorno(idImovelRetorno);
			
			if (matricula > 0) {
				imagemRetorno.setIdImovel(matricula);
			}
			
			imagemRetorno.setNomeImagem(imagem.getName());
			imagemRetorno.setPathImagem(imagem.getAbsolutePath());
			imagemRetorno.setUltimaAlteracao(new Date());

			controladorUtil.inserir(imagemRetorno);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}