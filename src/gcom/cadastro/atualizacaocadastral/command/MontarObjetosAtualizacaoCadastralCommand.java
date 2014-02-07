package gcom.cadastro.atualizacaocadastral.command;

import gcom.atualizacaocadastral.ClienteEnderecoRetorno;
import gcom.atualizacaocadastral.ClienteFoneRetorno;
import gcom.atualizacaocadastral.ClienteImovelRetorno;
import gcom.atualizacaocadastral.ClienteRetorno;
import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.atualizacaocadastral.ImovelRamoAtividadeRetorno;
import gcom.atualizacaocadastral.ImovelRetorno;
import gcom.atualizacaocadastral.ImovelSubcategoriaRetorno;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteAtualizacaoCadastral;
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
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastralBuilder;
import gcom.cadastro.imovel.ImovelRamoAtividadeAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelRamoAtividadePK;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoriaPK;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang.StringUtils;

public class MontarObjetosAtualizacaoCadastralCommand extends AbstractAtualizacaoCadastralCommand {
	
	private AtualizacaoCadastral atualizacaoCadastral;
	private AtualizacaoCadastralImovel atualizacaoCadastralImovel;
	private int matriculaImovel;
	private int matriculaUsuario;
	private int matriculaResponsavel;
	private int matriculaProprietario;
	private int tipoOperacao;
	private Integer idImovelRetorno;
	private Integer idImovelCliente;
	
	private IRepositorioClienteImovel repositorioClienteImovel = null;
	

	public MontarObjetosAtualizacaoCadastralCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil,
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
		
		salvarObjetosAtualizacaoCadastral();
	}
	
	public void salvarObjetosAtualizacaoCadastral() throws Exception {
		salvarImovel();
		salvarClienteUsuario();
		salvarClienteResponsavel();
		salvarClienteProprietario();
		
		atualizarSituacaoControleImovelAtualizacaoCadastral(SituacaoAtualizacaoCadastral.TRANSMITIDO);
	}
	
	private void salvarRamoAtividade() throws Exception {
		for (DadoAtualizacaoRamoAtividade ramo: atualizacaoCadastralImovel.getDadosRamoAtividade()){
			boolean existeRamoAtividadeAtualizacao = repositorioCadastro.existeImovelRamoAtividadeAtualizacaoCadastral(matriculaImovel, ramo.getId());
			
			if (!existeRamoAtividadeAtualizacao) {
				ImovelRamoAtividadeAtualizacaoCadastral ramoAtividadeTxt = new ImovelRamoAtividadeAtualizacaoCadastral();
				ramoAtividadeTxt.setIdImovel(matriculaImovel);
				ramoAtividadeTxt.setIdRamoAtividade(ramo.getId());
				
				ImovelRamoAtividadePK pk = new ImovelRamoAtividadePK(matriculaImovel, ramo.getId());
				ramoAtividadeTxt.setComp_id(pk);
				
				int tipoOperacao = Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("tipoOperacao"));
				
				salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, new ImovelRamoAtividadeAtualizacaoCadastral(),
						ramoAtividadeTxt, matriculaImovel, tipoOperacao);
				
				ImovelRamoAtividadeRetorno imovelRamoAtividadeRetorno = new ImovelRamoAtividadeRetorno(pk);
				imovelRamoAtividadeRetorno.setUltimaAlteracao(new Date());
				imovelRamoAtividadeRetorno.setIdImovelRetorno(idImovelRetorno);
				controladorUtil.inserir(imovelRamoAtividadeRetorno);
			}
		}
	}

	private void salvarImovel() throws Exception {
		ImovelAtualizacaoCadastralBuilder builder = new ImovelAtualizacaoCadastralBuilder(matriculaImovel, atualizacaoCadastralImovel, tipoOperacao);
		ImovelAtualizacaoCadastral imovelTxt = builder.getImovelAtualizacaoCadastral();
		
		ImovelAtualizacaoCadastral imovelAtualizacaoCadastralBase = controladorAtualizacaoCadastral.pesquisarImovelAtualizacaoCadastral(matriculaImovel);

		salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, imovelAtualizacaoCadastralBase, imovelTxt, matriculaImovel, tipoOperacao);
		salvarImovelRetorno(imovelTxt);
		
		salvarRamoAtividade();
		salvarImovelSubcategoria();
	}


	@SuppressWarnings("rawtypes")
	private void salvarImovelSubcategoria() throws ControladorException {
		List<ImovelSubcategoriaAtualizacaoCadastral> subcategorias = new ArrayList<ImovelSubcategoriaAtualizacaoCadastral>();
		subcategorias.addAll(buildImovelSubcategorias(TipoEconomia.RESIDENCIAL));
		subcategorias.addAll(buildImovelSubcategorias(TipoEconomia.COMERCIAL));
		subcategorias.addAll(buildImovelSubcategorias(TipoEconomia.INDUSTRIAL));
		subcategorias.addAll(buildImovelSubcategorias(TipoEconomia.PUBLICO));
		
		for (ImovelSubcategoriaAtualizacaoCadastral subcategoria : subcategorias) {
			Collection imovelSubcategorias = controladorAtualizacaoCadastral.pesquisarImovelSubcategoriaAtualizacaoCadastral(matriculaImovel, subcategoria.getIdSubcategoria(), null);
			ImovelSubcategoriaAtualizacaoCadastral imovelSubcategoriaAtualizacaoCadastral = null;
			if (imovelSubcategorias.isEmpty()) {
				imovelSubcategoriaAtualizacaoCadastral = new ImovelSubcategoriaAtualizacaoCadastral();
			} else {
				imovelSubcategoriaAtualizacaoCadastral = (ImovelSubcategoriaAtualizacaoCadastral) imovelSubcategorias.iterator().next();
			}

			salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, imovelSubcategoriaAtualizacaoCadastral, subcategoria, matriculaImovel, tipoOperacao);
			salvarImovelSubcategoriaRetorno(subcategoria, idImovelRetorno);
		}
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
				
				subcategoria.setIdImovel(matriculaImovel);
				subcategoria.setQuantidadeEconomias(qtdEconomias);
				subcategoria.setDescricaoSubcategoria(codigoSubcategoria);
				subcategoria.setDescricaoCategoria(tipoEconomia.getDescricao());

				TipoSubcategoria tipoSubcategoria = TipoSubcategoria.getByCodigo(codigoSubcategoria);
				subcategoria.setIdCategoria(tipoSubcategoria.getIdCategoria());
				subcategoria.setIdSubcategoria(tipoSubcategoria.getIdSubcategoria());
				
				ImovelSubcategoriaPK pk = new ImovelSubcategoriaPK(matriculaImovel,tipoSubcategoria.getIdSubcategoria());
				subcategoria.setComp_id(pk);
				
				subcategorias.add(subcategoria);
			}
		}
		
		return subcategorias;
	}
	

	private void salvarClienteProprietario() throws Exception {
		IClienteAtualizacaoCadastral clienteTxt = new ClienteProprietarioBuilder(atualizacaoCadastralImovel).getClienteTxt();
		
		if (matriculaProprietario != 0 || StringUtils.isNotEmpty(clienteTxt.getCpf())) {
	        boolean existeCliente = repositorioClienteImovel.existeClienteImovelTipo(matriculaProprietario
	                , matriculaImovel
	                , (int) ClienteRelacaoTipo.PROPRIETARIO
	                , clienteTxt.getCpf());

	        salvarCliente(matriculaProprietario, ClienteRelacaoTipo.PROPRIETARIO, clienteTxt 
        			, atualizacaoCadastralImovel.getLinhaCliente("telefoneProprietario")
        			, atualizacaoCadastralImovel.getLinhaCliente("celularProprietario")
        			, existeCliente);
		}
	}

	private void salvarClienteResponsavel() throws Exception {
		IClienteAtualizacaoCadastral clienteTxt = new ClienteResponsavelBuilder(atualizacaoCadastralImovel).getClienteTxt();
		
		if (matriculaResponsavel != 0 || StringUtils.isNotEmpty(clienteTxt.getCpf())) {
	        boolean existeCliente = repositorioClienteImovel.existeClienteImovelTipo(matriculaResponsavel
	                , matriculaImovel
	                , (int) ClienteRelacaoTipo.RESPONSAVEL
	                , clienteTxt.getCpf());
	        
			salvarCliente(matriculaResponsavel, ClienteRelacaoTipo.RESPONSAVEL, clienteTxt
					, atualizacaoCadastralImovel.getLinhaCliente("telefoneResponsavel")
					, atualizacaoCadastralImovel.getLinhaCliente("celularResponsavel")
					, existeCliente);

		}
	}
	
	private void salvarClienteUsuario() throws ControladorException {
		if (matriculaUsuario != 0) {
			IClienteAtualizacaoCadastral clienteTxt = new ClienteUsuarioBuilder(atualizacaoCadastralImovel).getClienteTxt();

			salvarCliente(matriculaUsuario, ClienteRelacaoTipo.USUARIO, clienteTxt
					,atualizacaoCadastralImovel.getLinhaCliente("telefoneUsuario")
					, atualizacaoCadastralImovel.getLinhaCliente("celularUsuario")
					, true);

		}		
	}

	private void salvarCliente(int matricula, Short clienteRelacaoTipo, IClienteAtualizacaoCadastral clienteTxt, String telefone, String celular, boolean existeCliente) throws ControladorException {
		Integer idclienteRetorno = salvarClienteRetorno(clienteTxt);
		salvarClienteImovelRetorno(clienteTxt, matriculaImovel, idclienteRetorno);
		
		ArrayList<ClienteFoneAtualizacaoCadastral> clientesFone = new ArrayList<ClienteFoneAtualizacaoCadastral>();
		salvarClienteFoneAtualizacaoCadastral(telefone, clienteRelacaoTipo, FoneTipo.RESIDENCIAL, matricula, clientesFone, idclienteRetorno);
		salvarClienteFoneAtualizacaoCadastral(celular, clienteRelacaoTipo, FoneTipo.CELULAR, matricula, clientesFone, idclienteRetorno);
		
		salvarClienteEnderecoRetorno(matricula, clienteTxt, idclienteRetorno);
		
		IClienteAtualizacaoCadastral clienteAtualizacaoCadastralBase = null;
		if (existeCliente){
			clienteAtualizacaoCadastralBase = controladorCliente.pesquisarClienteAtualizacaoCadastral(matricula, matriculaImovel, new Integer(clienteRelacaoTipo));			
		}else{
			tipoOperacao = AlteracaoTipo.INCLUSAO;
			clienteAtualizacaoCadastralBase = new ClienteAtualizacaoCadastral();
			
		}
		
		salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, clienteAtualizacaoCadastralBase, clienteTxt, matriculaImovel, tipoOperacao);
	}


	private void salvarClienteFoneAtualizacaoCadastral(String tipoClientFone, Short clienteRelacaoTipo, Integer foneTipo, int matriculaCliente, ArrayList<ClienteFoneAtualizacaoCadastral> clientesFone, Integer idClienteRetorno) {
		if (!tipoClientFone.trim().equals("")) {
			ClienteFoneAtualizacaoCadastral clienteFone = new ClienteFoneAtualizacaoCadastral();

			if (tipoClientFone.length() > 2) {
				clienteFone.setDdd(tipoClientFone.substring(0, 2));
				clienteFone.setTelefone(tipoClientFone.substring(2));
			} else {
				clienteFone.setTelefone(tipoClientFone);
			}
			clienteFone.setIdFoneTipo(foneTipo);
			clienteFone.setIdCliente(matriculaCliente);

			clientesFone.add(clienteFone);

			try {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = controladorCliente
						.pesquisarClienteFoneAtualizacaoCadastral(Integer.valueOf(matriculaCliente), Integer.valueOf(matriculaImovel), foneTipo,
								Integer.valueOf(clienteRelacaoTipo), null).iterator().next();

				salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, clienteFoneAtualizacaoCadastral, clienteFone, matriculaImovel, tipoOperacao);
				salvarClienteFoneRetorno(clienteFone, idClienteRetorno);
			} catch (NoSuchElementException e) {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = new ClienteFoneAtualizacaoCadastral();
				try {
					salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, clienteFoneAtualizacaoCadastral, clienteFone, matriculaImovel, tipoOperacao);
					salvarClienteFoneRetorno(clienteFone, idClienteRetorno);
				} catch (ControladorException e1) {
					e1.printStackTrace();
				}
			} catch (ControladorException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void salvarClienteEnderecoRetorno(Integer matriculaCliente, IClienteAtualizacaoCadastral clienteAtualizacaoCadastral, 
			Integer idClienteRetorno) throws ControladorException {
		ClienteEnderecoRetorno clienteEnderecoRetorno = new ClienteEnderecoRetorno(matriculaCliente, clienteAtualizacaoCadastral);
		clienteEnderecoRetorno.setUltimaAlteracao(new Date());
		clienteEnderecoRetorno.setIdClienteRetorno(idClienteRetorno);
		controladorUtil.inserir(clienteEnderecoRetorno);
	}

	private void salvarClienteFoneRetorno(IClienteFone clienteFone, Integer idClienteRetorno) throws ControladorException {
		ClienteFoneRetorno clienteFoneRetorno = new ClienteFoneRetorno(clienteFone);
		clienteFoneRetorno.setUltimaAlteracao(new Date());
		clienteFoneRetorno.setIdClienteRetorno(idClienteRetorno);
		controladorUtil.inserir(clienteFoneRetorno);
	}

	private void salvarImovelRetorno(ImovelAtualizacaoCadastral imovelTxt) throws ControladorException {
		ImovelRetorno imovelRetorno = new ImovelRetorno(imovelTxt);
		imovelRetorno.setUltimaAlteracao(new Date());
		idImovelRetorno = (Integer)controladorUtil.inserir(imovelRetorno);
	}
	
	private void salvarImovelSubcategoriaRetorno(ImovelSubcategoriaAtualizacaoCadastral imovelSubcategoriaTxt, Integer idImovelRetorno) throws ControladorException {
		ImovelSubcategoriaRetorno imovelSubcategoriaRetorno = new ImovelSubcategoriaRetorno(imovelSubcategoriaTxt);
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
	
	private void atualizarSituacaoControleImovelAtualizacaoCadastral(Integer situacao) throws Exception {
		ImovelControleAtualizacaoCadastral imovelControleAtualizacaoCadastral = repositorioImovel.pesquisarImovelControleAtualizacaoCadastral(matriculaImovel);
		imovelControleAtualizacaoCadastral.setDataRetorno(new Date());
		imovelControleAtualizacaoCadastral.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(situacao));

		controladorUtil.atualizar(imovelControleAtualizacaoCadastral);
	}	
}
