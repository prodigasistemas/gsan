package gcom.cadastro.atualizacaocadastral.command;

import gcom.atualizacaocadastral.ClienteFoneRetorno;
import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.atualizacaocadastral.ImovelRamoAtividadeRetorno;
import gcom.atualizacaocadastral.ImovelRetorno;
import gcom.atualizacaocadastral.ImovelSubcategoriaRetorno;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteFoneAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteProprietarioAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteResponsavelAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteUsuarioAtualizacaoCadastral;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.cliente.IClienteAtualizacaoCadastral;
import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastralBuilder;
import gcom.cadastro.imovel.ImovelRamoAtividadeAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelRamoAtividadePK;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoriaPK;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class MontarObjetosAtualizacaoCadastralCommand extends AbstractAtualizacaoCadastralCommand {
	
	private AtualizacaoCadastral atualizacaoCadastral;
	private AtualizacaoCadastralImovel atualizacaoCadastralImovel;
	private int matriculaImovel;
	private int matriculaUsuario;
	private int matriculaResponsavel;
	private int matriculaProprietario;
	private int tipoOperacao;

	public MontarObjetosAtualizacaoCadastralCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil,
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, ControladorImovelLocal controladorImovel,
			ControladorClienteLocal controladorCliente) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorImovel, controladorCliente);
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
		salvarRamoAtividade();
		salvarClienteUsuario();
		salvarClienteResponsavel();
		salvarClienteProprietario();
		salvarImovel();
		salvarImovelSubcategoria();
		
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
				controladorUtil.inserir(imovelRamoAtividadeRetorno);
			}
		}
	}

	private void salvarImovel() throws ControladorException {
		ImovelAtualizacaoCadastralBuilder builder = new ImovelAtualizacaoCadastralBuilder(matriculaImovel, atualizacaoCadastralImovel, tipoOperacao);
		ImovelAtualizacaoCadastral imovelTxt = builder.getImovelAtualizacaoCadastral();
		
		ImovelAtualizacaoCadastral imovelAtualizacaoCadastralBase = controladorImovel.pesquisarImovelAtualizacaoCadastral(matriculaImovel);

		salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, imovelAtualizacaoCadastralBase, imovelTxt, matriculaImovel, tipoOperacao);
		salvarImovelRetorno(imovelTxt);
	}


	@SuppressWarnings("rawtypes")
	private void salvarImovelSubcategoria() throws ControladorException {
		List<ImovelSubcategoriaAtualizacaoCadastral> subcategorias = new ArrayList<ImovelSubcategoriaAtualizacaoCadastral>();
		subcategorias.addAll(buildImovelSubcategorias(TipoEconomia.RESIDENCIAL));
		subcategorias.addAll(buildImovelSubcategorias(TipoEconomia.COMERCIAL));
		subcategorias.addAll(buildImovelSubcategorias(TipoEconomia.INDUSTRIAL));
		subcategorias.addAll(buildImovelSubcategorias(TipoEconomia.PUBLICO));
		
		for (ImovelSubcategoriaAtualizacaoCadastral subcategoria : subcategorias) {
			Collection imovelSubcategorias = controladorImovel.pesquisarImovelSubcategoriaAtualizacaoCadastral(matriculaImovel, subcategoria.getIdSubcategoria(), null);
			ImovelSubcategoriaAtualizacaoCadastral imovelSubcategoriaAtualizacaoCadastral = null;
			if (imovelSubcategorias.isEmpty()) {
				imovelSubcategoriaAtualizacaoCadastral = new ImovelSubcategoriaAtualizacaoCadastral();
			} else {
				imovelSubcategoriaAtualizacaoCadastral = (ImovelSubcategoriaAtualizacaoCadastral) imovelSubcategorias.iterator().next();
			}

			salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, imovelSubcategoriaAtualizacaoCadastral, subcategoria, matriculaImovel, tipoOperacao);
			salvarImovelSubcategoriaRetorno(subcategoria);
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
	

	private void salvarClienteProprietario() throws ControladorException {
		if (matriculaProprietario != 0) {
			IClienteAtualizacaoCadastral clienteTxt = new ClienteProprietarioAtualizacaoCadastral(atualizacaoCadastralImovel);

			salvarCliente(matriculaProprietario, ClienteRelacaoTipo.PROPRIETARIO, clienteTxt, 
					atualizacaoCadastralImovel.getLinhaCliente("telefoneProprietario"), atualizacaoCadastralImovel.getLinhaCliente("celularProprietario"));
		}
	}

	private void salvarClienteResponsavel() throws ControladorException {
		if (matriculaResponsavel != 0) {
			IClienteAtualizacaoCadastral clienteTxt = new ClienteResponsavelAtualizacaoCadastral(atualizacaoCadastralImovel);

			salvarCliente(matriculaResponsavel, ClienteRelacaoTipo.RESPONSAVEL, clienteTxt, 
					atualizacaoCadastralImovel.getLinhaCliente("telefoneResponsavel"), atualizacaoCadastralImovel.getLinhaCliente("celularResponsavel"));
		}
	}
	
	private void salvarClienteUsuario() throws ControladorException {
		if (matriculaUsuario != 0) {
			IClienteAtualizacaoCadastral clienteTxt = new ClienteUsuarioAtualizacaoCadastral(atualizacaoCadastralImovel);

			salvarCliente(matriculaUsuario, ClienteRelacaoTipo.USUARIO, clienteTxt, 
							atualizacaoCadastralImovel.getLinhaCliente("telefoneUsuario"), atualizacaoCadastralImovel.getLinhaCliente("celularUsuario"));
		}		
	}

	private void salvarCliente(int matricula, Short clienteRelacaoTipo, IClienteAtualizacaoCadastral clienteTxt, String telefone, String celular) throws ControladorException {
		ArrayList<ClienteFoneAtualizacaoCadastral> clientesFone = new ArrayList<ClienteFoneAtualizacaoCadastral>();
		salvarClienteFoneAtualizacaoCadastral(telefone, clienteRelacaoTipo, FoneTipo.RESIDENCIAL, matricula, clientesFone);
		salvarClienteFoneAtualizacaoCadastral(celular, clienteRelacaoTipo, FoneTipo.CELULAR, matricula, clientesFone);
		
		IClienteAtualizacaoCadastral clienteAtualizacaoCadastralBase = controladorCliente.pesquisarClienteAtualizacaoCadastral(matricula, 
																										matriculaImovel, new Integer(clienteRelacaoTipo));
		
		salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, clienteAtualizacaoCadastralBase, clienteTxt, matriculaImovel, tipoOperacao);
	}

	private void salvarClienteFoneAtualizacaoCadastral(String tipoClientFone, Short clienteRelacaoTipo, Integer foneTipo, int matriculaCliente, ArrayList<ClienteFoneAtualizacaoCadastral> clientesFone) {
		if (!tipoClientFone.trim().equals("")) {
			ClienteFoneAtualizacaoCadastral clienteFone = new ClienteFoneAtualizacaoCadastral();

			clienteFone.setDdd(tipoClientFone.substring(0, 2));
			clienteFone.setTelefone(tipoClientFone.substring(2));
			clienteFone.setIdFoneTipo(foneTipo);
			clienteFone.setIdCliente(matriculaCliente);

			clientesFone.add(clienteFone);

			try {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = controladorCliente
						.pesquisarClienteFoneAtualizacaoCadastral(Integer.valueOf(matriculaCliente), Integer.valueOf(matriculaImovel), foneTipo,
								Integer.valueOf(clienteRelacaoTipo), null).iterator().next();

				salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, clienteFoneAtualizacaoCadastral, clienteFone, matriculaImovel, tipoOperacao);
				salvarClienteFoneRetorno(clienteFone);
			} catch (NoSuchElementException e) {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = new ClienteFoneAtualizacaoCadastral();
				try {
					salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, clienteFoneAtualizacaoCadastral, clienteFone, matriculaImovel, tipoOperacao);
					salvarClienteFoneRetorno(clienteFone);
				} catch (ControladorException e1) {
					e1.printStackTrace();
				}
			} catch (ControladorException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void salvarClienteFoneRetorno(IClienteFone clienteFone) throws ControladorException {
		ClienteFoneRetorno clienteFoneRetorno = new ClienteFoneRetorno(clienteFone);
		clienteFoneRetorno.setUltimaAlteracao(new Date());
		controladorUtil.inserir(clienteFoneRetorno);
	}

	private void salvarImovelRetorno(ImovelAtualizacaoCadastral imovelTxt) throws ControladorException {
		ImovelRetorno imovelRetorno = new ImovelRetorno(imovelTxt);
		imovelRetorno.setUltimaAlteracao(new Date());
		controladorUtil.inserir(imovelRetorno);
	}
	
	private void salvarImovelSubcategoriaRetorno(ImovelSubcategoriaAtualizacaoCadastral imovelSubcategoriaTxt) throws ControladorException {
		ImovelSubcategoriaRetorno imovelSubcategoriaRetorno = new ImovelSubcategoriaRetorno(imovelSubcategoriaTxt);
		imovelSubcategoriaRetorno.setUltimaAlteracao(new Date());
		controladorUtil.inserir(imovelSubcategoriaRetorno);
	}
	
	private void atualizarSituacaoControleImovelAtualizacaoCadastral(Integer situacao) throws Exception {
		ImovelControleAtualizacaoCadastral imovelControleAtualizacaoCadastral = repositorioImovel.pesquisarImovelControleAtualizacaoCadastral(matriculaImovel);
		imovelControleAtualizacaoCadastral.setDataRetorno(new Date());
		imovelControleAtualizacaoCadastral.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(situacao));

		controladorUtil.atualizar(imovelControleAtualizacaoCadastral);
	}	
}
