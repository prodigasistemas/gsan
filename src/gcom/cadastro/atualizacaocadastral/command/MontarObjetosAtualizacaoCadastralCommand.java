package gcom.cadastro.atualizacaocadastral.command;

import gcom.atualizacaocadastral.ImovelRetorno;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteFoneAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.endereco.FiltroLogradouroTipo;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelControleAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.fachada.Fachada;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.NoSuchElementException;

public class MontarObjetosAtualizacaoCadastralCommand extends AbstractAtualizacaoCadastralCommand {
	
	private AtualizacaoCadastral atualizacaoCadastral;

	private AtualizacaoCadastralImovel atualizacaoCadastralImovel;

	public MontarObjetosAtualizacaoCadastralCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil,
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, ControladorImovelLocal controladorImovel,
			ControladorClienteLocal controladorCliente) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorImovel, controladorCliente);
	}

	public void execute(AtualizacaoCadastral atualizacaoCadastral) throws Exception {
		this.atualizacaoCadastral = atualizacaoCadastral;
		this.atualizacaoCadastralImovel = atualizacaoCadastral.getImovelAtual(); 
		salvarObjetosAtualizacaoCadastral();
	}
	
	public void salvarObjetosAtualizacaoCadastral() throws Exception {
		int matriculaImovel = Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("matricula"));

		salvarImovelSubcategoriaAtualizacaoCadastral(matriculaImovel);

		int matriculaUsuario = Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("matriculaUsuario"));
		int matriculaResponsavel = Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("matriculaResponsavel"));
		int matriculaProprietario = Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("matriculaProprietario"));

		salvarClienteUsuario(matriculaUsuario, matriculaImovel, matriculaResponsavel, matriculaProprietario);
		salvarClienteResponsavel(matriculaImovel, matriculaResponsavel);
		salvarClienteProprietario(matriculaImovel, matriculaProprietario);
		salvarImovel(matriculaImovel);
		
		atualizarSituacaoControleImovelAtualizacaoCadastral(matriculaImovel, SituacaoAtualizacaoCadastral.TRANSMITIDO);
	}

	private void salvarImovel(int matriculaImovel) throws ControladorException {
		ImovelAtualizacaoCadastral imovelTxt = buildImovelTxt(matriculaImovel);
		ImovelAtualizacaoCadastral imovelAtualizacaoCadastralBase = controladorImovel.pesquisarImovelAtualizacaoCadastral(matriculaImovel);

		salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, imovelAtualizacaoCadastralBase, imovelTxt, matriculaImovel);
		salvarImovelRetorno(imovelTxt);
	}

	private void salvarClienteProprietario(int matriculaImovel, int matriculaProprietario) throws ControladorException {
		if (matriculaProprietario != 0) {
			ClienteAtualizacaoCadastral clienteProprietarioTxt = buildClienteProprietario(matriculaImovel, matriculaProprietario);
			ClienteAtualizacaoCadastral clienteAtualizacaoCadastralBase = controladorCliente.pesquisarClienteAtualizacaoCadastral(matriculaProprietario,
					matriculaImovel, new Integer(ClienteRelacaoTipo.PROPRIETARIO));

			salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, clienteAtualizacaoCadastralBase, clienteProprietarioTxt, matriculaImovel);
		}
	}

	private void salvarClienteResponsavel(int matriculaImovel, int matriculaResponsavel) throws ControladorException {
		if (matriculaResponsavel != 0) {
			ClienteAtualizacaoCadastral clienteResponsavelTxt = buildClienteResponsavel(matriculaImovel, matriculaResponsavel);
			ClienteAtualizacaoCadastral clienteAtualizacaoCadastralBase = controladorCliente.pesquisarClienteAtualizacaoCadastral(matriculaResponsavel,
					matriculaImovel, new Integer(ClienteRelacaoTipo.RESPONSAVEL));

			salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, clienteAtualizacaoCadastralBase, clienteResponsavelTxt, matriculaImovel);
		}
	}

	private void salvarClienteUsuario(int matriculaUsuario, int matriculaImovel, int matriculaResponsavel, int matriculaProprietario) throws ControladorException {
		if (matriculaUsuario != 0) {
			ClienteAtualizacaoCadastral clienteUsuarioTxt = buildClienteUsuario(matriculaImovel, matriculaUsuario, matriculaResponsavel, matriculaProprietario);
			ClienteAtualizacaoCadastral clienteAtualizacaoCadastralBase = controladorCliente.pesquisarClienteAtualizacaoCadastral(matriculaUsuario,
					matriculaImovel, new Integer(ClienteRelacaoTipo.USUARIO));

			salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, clienteAtualizacaoCadastralBase, clienteUsuarioTxt, matriculaImovel);
		}		
	}

	private void salvarImovelSubcategoriaAtualizacaoCadastral(int matriculaImovel) throws ControladorException {
		
		// Imovel Subcategoria
		short qtdEconomias = 0;
		String descricaoSubcategoria = "";
		String descricaoCategoria = "";
		int idCategoria = 0;
		for (int i = 0; i < 16; i++) {
			if (i <= 3) {
				qtdEconomias = Short.parseShort(atualizacaoCadastralImovel.getLinhaImovel("subcategoriaR" + (i + 1)));
				idCategoria = 1;
				descricaoSubcategoria = "R" + (i + 1);
				descricaoCategoria = "RESIDENCIAL";
			} else if (i >= 4 && i < 8) {
				qtdEconomias = Short.parseShort(atualizacaoCadastralImovel.getLinhaImovel("subcategoriaC" + (i - 3)));
				idCategoria = 2;
				descricaoSubcategoria = "C" + (i - 3);
				descricaoCategoria = "COMERCIAL";
			} else if (i >= 8 && i < 12) {
				qtdEconomias = Short.parseShort(atualizacaoCadastralImovel.getLinhaImovel("subcategoriaI" + (i - 7)));
				idCategoria = 3;
				descricaoSubcategoria = "I" + (i - 7);
				descricaoCategoria = "INDUSTRIAL";
			} else {
				qtdEconomias = Short.parseShort(atualizacaoCadastralImovel.getLinhaImovel("subcategoriaP" + (i - 11)));
				idCategoria = 4;
				descricaoSubcategoria = "P" + (i - 11);
				descricaoCategoria = "PUBLICO";
			}

			if (qtdEconomias != 0) {
				ImovelSubcategoriaAtualizacaoCadastral subcategoria = new ImovelSubcategoriaAtualizacaoCadastral();

				subcategoria.setIdImovel(matriculaImovel);
				subcategoria.setQuantidadeEconomias(qtdEconomias);
				subcategoria.setIdSubcategoria(i + 1);
				subcategoria.setIdCategoria(idCategoria);
				subcategoria.setDescricaoSubcategoria(descricaoSubcategoria);
				subcategoria.setDescricaoCategoria(descricaoCategoria);

				Collection subCategoria = controladorImovel.pesquisarImovelSubcategoriaAtualizacaoCadastral(matriculaImovel, subcategoria.getIdSubcategoria(),
						null);
				ImovelSubcategoriaAtualizacaoCadastral imovelSubcategoriaAtualizacaoCadastral = null;
				if (subCategoria.isEmpty()) {
					imovelSubcategoriaAtualizacaoCadastral = new ImovelSubcategoriaAtualizacaoCadastral();
				} else {
					imovelSubcategoriaAtualizacaoCadastral = (ImovelSubcategoriaAtualizacaoCadastral) subCategoria.iterator().next();
				}

				salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, imovelSubcategoriaAtualizacaoCadastral, subcategoria, matriculaImovel);
			}

		}
	}

	private ImovelAtualizacaoCadastral buildImovelTxt(int matriculaImovel) {
		ImovelAtualizacaoCadastral imovelTxt;
		imovelTxt = new ImovelAtualizacaoCadastral();

		// Linha 2
		imovelTxt.setIdImovel(matriculaImovel);
		imovelTxt.setNumeroImovel(atualizacaoCadastralImovel.getLinhaImovel("numeroImovel"));
		imovelTxt.setComplementoEndereco(atualizacaoCadastralImovel.getLinhaImovel("complementoImovel"));
		imovelTxt.setIdFonteAbastecimento(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("fonteAbastecimento")));
		imovelTxt.setNumeroIptu(atualizacaoCadastralImovel.getLinhaImovel("numeroIPTU") == null ? null : new BigDecimal(atualizacaoCadastralImovel.getLinhaImovel("numeroIPTU")));
		imovelTxt.setNumeroContratoEnergia(atualizacaoCadastralImovel.getLinhaImovel("numeroCelpa").equals("") ? null : Long.parseLong(atualizacaoCadastralImovel.getLinhaImovel("numeroCelpa")));
		imovelTxt.setIdLogradouroTipo(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("idTipoLogradouroImovel")));
		imovelTxt.setDsLogradouroTipo(getDescricaoLogradouro(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("idTipoLogradouroImovel"))));
		imovelTxt.setDescricaoLogradouro(atualizacaoCadastralImovel.getLinhaImovel("logradouroImovel"));
		imovelTxt.setCodigoCep(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("cep")));
		imovelTxt.setNomeBairro(atualizacaoCadastralImovel.getLinhaImovel("bairro"));
		imovelTxt.setNomeMunicipio(atualizacaoCadastralImovel.getLinhaImovel("municipio"));
		imovelTxt.setNumeroPontosUtilizacao(Short.parseShort(atualizacaoCadastralImovel.getLinhaImovel("numeroPontosUteis")));
		imovelTxt.setNumeroMorador(Short.parseShort(atualizacaoCadastralImovel.getLinhaImovel("numeroOcupantes")));

		// Linha 4
		imovelTxt.setIdLigacaoAguaSituacao(Integer.parseInt(atualizacaoCadastralImovel.getLinhaServicos("ligacaoAguaSituacao")));
		imovelTxt.setIdLigacaoEsgotoSituacao(Integer.parseInt(atualizacaoCadastralImovel.getLinhaServicos("ligacaoEsgotoSituacao")));
		imovelTxt.setIdLocalInstalacaoRamal(atualizacaoCadastralImovel.getLinhaServicos("localInstalacaoRamal").equals("") ? null : Integer.parseInt(atualizacaoCadastralImovel.getLinhaServicos("localInstalacaoRamal")));

		// Linha 5
		if (atualizacaoCadastralImovel.getLinhaMedidor().size() > 0) {
			imovelTxt.setNumeroHidrometro(atualizacaoCadastralImovel.getLinhaMedidor("numeroHidrometro"));
			imovelTxt.setIdMarcaHidrometro(atualizacaoCadastralImovel.getLinhaMedidor("marcaHidrometro").equals("") ? 0 : Integer.parseInt(atualizacaoCadastralImovel.getLinhaMedidor("marcaHidrometro")));
			imovelTxt.setIdProtecaoHidrometro(atualizacaoCadastralImovel.getLinhaMedidor("tipoCaixaProtecaoHidrometro").equals("") ? 0 : Integer.parseInt(atualizacaoCadastralImovel.getLinhaMedidor("tipoCaixaProtecaoHidrometro")));
			imovelTxt.setIdCapacidadeHidrometro(atualizacaoCadastralImovel.getLinhaMedidor("capacidadeHidrometro").equals("") ? 0 : Integer.parseInt(atualizacaoCadastralImovel.getLinhaMedidor("capacidadeHidrometro")));
		}

		// Linha 6
		imovelTxt.setIdCadastroOcorrencia(Integer.parseInt(atualizacaoCadastralImovel.getLinhaAnormalidade("codigoAnormalidade")));
		imovelTxt.setDescricaoOutrasInformacoes(atualizacaoCadastralImovel.getLinhaAnormalidade("comentario").trim());
		imovelTxt.setCoordenadaY(atualizacaoCadastralImovel.getLinhaAnormalidade("latitude"));
		imovelTxt.setCoordenadaX(atualizacaoCadastralImovel.getLinhaAnormalidade("longitude"));
		
		return imovelTxt;
	}

	private ClienteAtualizacaoCadastral buildClienteProprietario(int matriculaImovel, int matriculaProprietario) {
		
		if (matriculaProprietario == 0) {
			return null;
		}
		
		ClienteAtualizacaoCadastral clienteProprietarioTxt;
		clienteProprietarioTxt = new ClienteAtualizacaoCadastral();

		clienteProprietarioTxt.setNomeCliente(atualizacaoCadastralImovel.getLinhaCliente("nomeProprietario"));
		clienteProprietarioTxt.setCpfCnpj(atualizacaoCadastralImovel.getLinhaCliente("cnpjCpfProprietario"));
		clienteProprietarioTxt.setRg(atualizacaoCadastralImovel.getLinhaCliente("rgProprietario"));
		clienteProprietarioTxt.setDsUFSiglaOrgaoExpedidorRg(atualizacaoCadastralImovel.getLinhaCliente("ufRgProprietario"));
		clienteProprietarioTxt.setIdPessoaSexo(Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("sexoProprietario")));
		clienteProprietarioTxt.setEmail(atualizacaoCadastralImovel.getLinhaCliente("emailProprietario"));
		clienteProprietarioTxt.setIdLogradouroTipo(Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("idTipoLogradouroProprietario")));
		clienteProprietarioTxt.setDescricaoLogradouro(atualizacaoCadastralImovel.getLinhaCliente("logradouroProprietario"));
		clienteProprietarioTxt.setNumeroImovel(atualizacaoCadastralImovel.getLinhaCliente("numeroProprietario"));
		clienteProprietarioTxt.setComplementoEndereco(atualizacaoCadastralImovel.getLinhaCliente("complementoProprietario"));
		clienteProprietarioTxt.setNomeBairro(atualizacaoCadastralImovel.getLinhaCliente("bairroProprietario"));
		clienteProprietarioTxt.setCodigoCep(atualizacaoCadastralImovel.getLinhaCliente("cepProprietario").equals("") ? null : Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("cepProprietario")));
		clienteProprietarioTxt.setNomeMunicipio(atualizacaoCadastralImovel.getLinhaCliente("municipioProprietario"));
		clienteProprietarioTxt.setIdClienteRelacaoTipo(new Integer(ClienteRelacaoTipo.PROPRIETARIO));
		clienteProprietarioTxt.setIdImovel(Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("matriculaImovelCliente")));

		ArrayList<ClienteFoneAtualizacaoCadastral> clientesFone = new ArrayList<ClienteFoneAtualizacaoCadastral>();
		
		salvarClienteFoneAtualizacaoCadastral(atualizacaoCadastralImovel.getLinhaCliente("telefoneProprietario"), FoneTipo.RESIDENCIAL,
												matriculaImovel, matriculaProprietario, clientesFone);
		salvarClienteFoneAtualizacaoCadastral(atualizacaoCadastralImovel.getLinhaCliente("celularProprietario"), FoneTipo.CELULAR,
												matriculaImovel, matriculaProprietario, clientesFone);
		
		return clienteProprietarioTxt;
	}

	private ClienteAtualizacaoCadastral buildClienteResponsavel(int matriculaImovel, int matriculaResponsavel) {
		
		if (matriculaResponsavel == 0) {
			return null;
		}
		
		ClienteAtualizacaoCadastral clienteResponsavelTxt;
		clienteResponsavelTxt = new ClienteAtualizacaoCadastral();

		clienteResponsavelTxt.setNomeCliente(atualizacaoCadastralImovel.getLinhaCliente("nomeResponsavel"));
		clienteResponsavelTxt.setCpfCnpj(atualizacaoCadastralImovel.getLinhaCliente("cnpjCpfResponsavel"));
		clienteResponsavelTxt.setRg(atualizacaoCadastralImovel.getLinhaCliente("rgResponsavel"));
		clienteResponsavelTxt.setDsUFSiglaOrgaoExpedidorRg(atualizacaoCadastralImovel.getLinhaCliente("ufRgResponsavel"));
		clienteResponsavelTxt.setIdPessoaSexo(atualizacaoCadastralImovel.getLinhaCliente("sexoResponsavel").equals("") ? null : Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("sexoResponsavel")));
		clienteResponsavelTxt.setEmail(atualizacaoCadastralImovel.getLinhaCliente("emailResponsavel"));
		clienteResponsavelTxt.setIdLogradouroTipo(Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("idTipoLogradouroResponsavel")));
		clienteResponsavelTxt.setDescricaoLogradouro(atualizacaoCadastralImovel.getLinhaCliente("logradouroResponsavel"));
		clienteResponsavelTxt.setNumeroImovel(atualizacaoCadastralImovel.getLinhaCliente("numeroResponsavel"));
		clienteResponsavelTxt.setComplementoEndereco(atualizacaoCadastralImovel.getLinhaCliente("complementoResponsavel"));
		clienteResponsavelTxt.setNomeBairro(atualizacaoCadastralImovel.getLinhaCliente("bairroResponsavel"));
		clienteResponsavelTxt.setCodigoCep(Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("cepResponsavel")));
		clienteResponsavelTxt.setNomeMunicipio(atualizacaoCadastralImovel.getLinhaCliente("municipioResponsavel"));
		clienteResponsavelTxt.setIdClienteRelacaoTipo(new Integer(ClienteRelacaoTipo.RESPONSAVEL));
		clienteResponsavelTxt.setIdImovel(Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("matriculaImovelCliente")));

		ArrayList<ClienteFoneAtualizacaoCadastral> clientesFone = new ArrayList<ClienteFoneAtualizacaoCadastral>();
		
		salvarClienteFoneAtualizacaoCadastral(atualizacaoCadastralImovel.getLinhaCliente("telefoneResponsavel"), FoneTipo.RESIDENCIAL,
												matriculaImovel, matriculaResponsavel, clientesFone);
		salvarClienteFoneAtualizacaoCadastral(atualizacaoCadastralImovel.getLinhaCliente("celularResponsavel"), FoneTipo.CELULAR,
												matriculaImovel, matriculaResponsavel, clientesFone);
		
		return clienteResponsavelTxt;
	}

	private ClienteAtualizacaoCadastral buildClienteUsuario(int matriculaImovel, int matriculaUsuario, int matriculaResponsavel, int matriculaProprietario) {
		
		if (matriculaUsuario == 0) {
			return null;
		}
		
		ClienteAtualizacaoCadastral clienteUsuarioTxt;
		clienteUsuarioTxt = new ClienteAtualizacaoCadastral();

		clienteUsuarioTxt.setNomeCliente(atualizacaoCadastralImovel.getLinhaCliente("nomeUsuario"));
		clienteUsuarioTxt.setCpfCnpj(atualizacaoCadastralImovel.getLinhaCliente("cnpjCpfUsuario"));
		clienteUsuarioTxt.setRg(atualizacaoCadastralImovel.getLinhaCliente("rgUsuario"));
		clienteUsuarioTxt.setDsUFSiglaOrgaoExpedidorRg(atualizacaoCadastralImovel.getLinhaCliente("ufRgUsuario"));
		clienteUsuarioTxt.setIdPessoaSexo(atualizacaoCadastralImovel.getLinhaCliente("sexoUsuario").equals("0") ? null : Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("sexoUsuario")));
		clienteUsuarioTxt.setEmail(atualizacaoCadastralImovel.getLinhaCliente("emailUsuario"));
		clienteUsuarioTxt.setIdClienteRelacaoTipo(new Integer(ClienteRelacaoTipo.USUARIO));
		clienteUsuarioTxt.setIdImovel(Integer.parseInt(atualizacaoCadastralImovel.getLinhaCliente("matriculaImovelCliente")));
		clienteUsuarioTxt.setIdLogradouroTipo(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("idTipoLogradouroImovel")));
		clienteUsuarioTxt.setDescricaoLogradouro(atualizacaoCadastralImovel.getLinhaImovel("logradouroImovel"));
		clienteUsuarioTxt.setDsLogradouroTipo(getDescricaoLogradouro(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("idTipoLogradouroImovel"))));
		clienteUsuarioTxt.setNumeroImovel(atualizacaoCadastralImovel.getLinhaImovel("numeroImovel"));
		clienteUsuarioTxt.setComplementoEndereco(atualizacaoCadastralImovel.getLinhaImovel("complementoImovel"));
		clienteUsuarioTxt.setNomeBairro(atualizacaoCadastralImovel.getLinhaImovel("bairro"));
		clienteUsuarioTxt.setCodigoCep(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("cep")));
		clienteUsuarioTxt.setNomeMunicipio(atualizacaoCadastralImovel.getLinhaImovel("municipio"));


		ArrayList<ClienteFoneAtualizacaoCadastral> clientesFone = new ArrayList<ClienteFoneAtualizacaoCadastral>();
		
		salvarClienteFoneAtualizacaoCadastral(atualizacaoCadastralImovel.getLinhaCliente("telefoneUsuario"), FoneTipo.RESIDENCIAL,
												matriculaImovel, matriculaUsuario, clientesFone);
		salvarClienteFoneAtualizacaoCadastral(atualizacaoCadastralImovel.getLinhaCliente("celularUsuario"), FoneTipo.CELULAR, 
												matriculaImovel, matriculaUsuario, clientesFone);
		
		return clienteUsuarioTxt;
	}

	private void salvarClienteFoneAtualizacaoCadastral(String tipoClientFone, Integer foneTipo, int matriculaImovel, int matriculaCliente, 
														ArrayList<ClienteFoneAtualizacaoCadastral> clientesFone) {
		if (!tipoClientFone.trim().equals("")) {
			ClienteFoneAtualizacaoCadastral clienteFone = new ClienteFoneAtualizacaoCadastral();

			clienteFone.setDdd(tipoClientFone.substring(0, 2));
			clienteFone.setTelefone(tipoClientFone.substring(2));
			clienteFone.setIdFoneTipo(foneTipo);
			clienteFone.setIdCliente(matriculaCliente);

			clientesFone.add(clienteFone);

			try {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = controladorCliente
						.pesquisarClienteFoneAtualizacaoCadastral(Integer.valueOf(matriculaCliente), Integer.valueOf(matriculaImovel), FoneTipo.CELULAR,
								Integer.valueOf(ClienteRelacaoTipo.USUARIO), null).iterator().next();

				salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, clienteFoneAtualizacaoCadastral, clienteFone, matriculaImovel);
			} catch (NoSuchElementException e) {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = new ClienteFoneAtualizacaoCadastral();
				try {
					salvarTabelaColunaAtualizacaoCadastral(atualizacaoCadastral, clienteFoneAtualizacaoCadastral, clienteFone, matriculaImovel);
				} catch (ControladorException e1) {
					e1.printStackTrace();
				}
			} catch (ControladorException e) {
				e.printStackTrace();
			}
		}
	}

	private void salvarImovelRetorno(ImovelAtualizacaoCadastral imovelTxt) throws ControladorException {
		ImovelRetorno imovelRetorno = new ImovelRetorno(imovelTxt);
		imovelRetorno.setUltimaAlteracao(new Date());
		controladorUtil.inserir(imovelRetorno);
	}

	public String getDescricaoLogradouro(int idTipoLogradouro) {
		FiltroLogradouroTipo filtro = new FiltroLogradouroTipo();
		filtro.adicionarParametro(new ParametroSimples(FiltroLogradouroTipo.ID, idTipoLogradouro));
		LogradouroTipo logradouroTipo = (LogradouroTipo) (Fachada.getInstancia().pesquisar(filtro, LogradouroTipo.class.getName()).iterator().next());

		return logradouroTipo.getDescricao();
	}

	private void atualizarSituacaoControleImovelAtualizacaoCadastral(int matriculaImovel, Integer situacao) throws Exception {
		ImovelControleAtualizacaoCadastral imovelControleAtualizacaoCadastral = repositorioImovel.pesquisarImovelControleAtualizacaoCadastral(matriculaImovel);
		imovelControleAtualizacaoCadastral.setDataRetorno(new Date());
		imovelControleAtualizacaoCadastral.setSituacaoAtualizacaoCadastral(new SituacaoAtualizacaoCadastral(situacao));

		controladorUtil.atualizar(imovelControleAtualizacaoCadastral);
	}	
}
