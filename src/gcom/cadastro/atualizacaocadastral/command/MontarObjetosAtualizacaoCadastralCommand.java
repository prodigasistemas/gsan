package gcom.cadastro.atualizacaocadastral.command;

import gcom.atualizacaocadastral.ImovelRetorno;
import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
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
import gcom.interceptor.Interceptador;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.NoSuchElementException;

public class MontarObjetosAtualizacaoCadastralCommand extends AbstractAtualizacaoCadastralCommand {

	public MontarObjetosAtualizacaoCadastralCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil,
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, ControladorImovelLocal controladorImovel,
			ControladorClienteLocal controladorCliente) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorImovel, controladorCliente);
	}

	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		montarObjetosAtualizacaoCadastral(atualizacao);
	}
	
	public void montarObjetosAtualizacaoCadastral(AtualizacaoCadastral atualizacaoCadastral) throws Exception {

		ArquivoTextoAtualizacaoCadastral arquivoTexto = atualizacaoCadastral.getArquivoTexto();
		
		Map<String, String> linhaCliente = atualizacaoCadastral.getLinhaCliente();
		Map<String, String> linhaImovel = atualizacaoCadastral.getLinhaImovel();
		Map<String, String> linhaRamoAtividade = atualizacaoCadastral.getLinhaRamoAtividade(); // NAO USADO
		Map<String, String> linhaServicos = atualizacaoCadastral.getLinhaServicos();
		Map<String, String> linhaMedidor = atualizacaoCadastral.getLinhaMedidor();
		Map<String, String> linhaAnormalidade = atualizacaoCadastral.getLinhaAnormalidade();

		Interceptador interceptador = Interceptador.getInstancia();

		int matriculaImovel = Integer.parseInt(linhaImovel.get("matricula"));
		salvarImovelSubcategoriaAtualizacaoCadastral(arquivoTexto, linhaImovel, interceptador, matriculaImovel);

		// Linha 1
		int matriculaUsuario = Integer.parseInt(linhaCliente.get("matriculaUsuario"));
		int matriculaResponsavel = Integer.parseInt(linhaCliente.get("matriculaResponsavel"));
		int matriculaProprietario = Integer.parseInt(linhaCliente.get("matriculaProprietario"));

		ClienteAtualizacaoCadastral clienteUsuarioTxt = buildClienteUsuario(arquivoTexto, linhaCliente, linhaImovel, interceptador, matriculaImovel, matriculaUsuario,
													matriculaResponsavel, matriculaProprietario);
		ClienteAtualizacaoCadastral clienteResponsavelTxt = buildClienteResponsavel(arquivoTexto, linhaCliente, interceptador, matriculaImovel, matriculaResponsavel);
		ClienteAtualizacaoCadastral clienteProprietarioTxt = buildClienteProprietario(arquivoTexto, linhaCliente, interceptador, matriculaImovel, matriculaProprietario);

		if (matriculaUsuario != 0) {
			ClienteAtualizacaoCadastral clienteAtualizacaoCadastralBase = controladorCliente.pesquisarClienteAtualizacaoCadastral(matriculaUsuario,
					matriculaImovel, new Integer(ClienteRelacaoTipo.USUARIO));

			salvarTabelaColunaAtualizacaoCadastral(clienteAtualizacaoCadastralBase, clienteUsuarioTxt, arquivoTexto, interceptador, matriculaImovel);

		} else if (matriculaResponsavel != 0) {
			ClienteAtualizacaoCadastral clienteAtualizacaoCadastralBase = controladorCliente.pesquisarClienteAtualizacaoCadastral(matriculaResponsavel,
					matriculaImovel, new Integer(ClienteRelacaoTipo.RESPONSAVEL));

			salvarTabelaColunaAtualizacaoCadastral(clienteAtualizacaoCadastralBase, clienteResponsavelTxt, arquivoTexto, interceptador, matriculaImovel);

		} else if (matriculaProprietario != 0) {
			ClienteAtualizacaoCadastral clienteAtualizacaoCadastralBase = controladorCliente.pesquisarClienteAtualizacaoCadastral(matriculaProprietario,
					matriculaImovel, new Integer(ClienteRelacaoTipo.PROPRIETARIO));

			salvarTabelaColunaAtualizacaoCadastral(clienteAtualizacaoCadastralBase, clienteProprietarioTxt, arquivoTexto, interceptador, matriculaImovel);
		}

		ImovelAtualizacaoCadastral imovelTxt = buildImovelTxt(linhaImovel, linhaServicos, linhaMedidor, linhaAnormalidade, matriculaImovel);
		ImovelAtualizacaoCadastral imovelAtualizacaoCadastralBase = controladorImovel.pesquisarImovelAtualizacaoCadastral(matriculaImovel);
		
		salvarTabelaColunaAtualizacaoCadastral(imovelAtualizacaoCadastralBase, imovelTxt, arquivoTexto, interceptador, matriculaImovel);

		salvarImovelRetorno(imovelTxt);
		
		atualizarSituacaoControleImovelAtualizacaoCadastral(matriculaImovel, SituacaoAtualizacaoCadastral.TRANSMITIDO);
	}

	private void salvarImovelSubcategoriaAtualizacaoCadastral(ArquivoTextoAtualizacaoCadastral arquivoTexto, Map<String, String> linhaImovel,
			Interceptador interceptador, int matriculaImovel) throws ControladorException {
		// Imovel Subcategoria
		short qtdEconomias = 0;
		String descricaoSubcategoria = "";
		String descricaoCategoria = "";
		int idCategoria = 0;
		for (int i = 0; i < 16; i++) {
			if (i <= 3) {
				qtdEconomias = Short.parseShort(linhaImovel.get("subcategoriaR" + (i + 1)));
				idCategoria = 1;
				descricaoSubcategoria = "R" + (i + 1);
				descricaoCategoria = "RESIDENCIAL";
			} else if (i >= 4 && i < 8) {
				qtdEconomias = Short.parseShort(linhaImovel.get("subcategoriaC" + (i - 3)));
				idCategoria = 2;
				descricaoSubcategoria = "C" + (i - 3);
				descricaoCategoria = "COMERCIAL";
			} else if (i >= 8 && i < 12) {
				qtdEconomias = Short.parseShort(linhaImovel.get("subcategoriaI" + (i - 7)));
				idCategoria = 3;
				descricaoSubcategoria = "I" + (i - 7);
				descricaoCategoria = "INDUSTRIAL";
			} else {
				qtdEconomias = Short.parseShort(linhaImovel.get("subcategoriaP" + (i - 11)));
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

				salvarTabelaColunaAtualizacaoCadastral(imovelSubcategoriaAtualizacaoCadastral, subcategoria, arquivoTexto, interceptador, matriculaImovel);
			}

		}
	}

	private ImovelAtualizacaoCadastral buildImovelTxt(Map<String, String> linhaImovel, Map<String, String> linhaServicos,
			Map<String, String> linhaMedidor, Map<String, String> linhaAnormalidade, int matriculaImovel) {
		ImovelAtualizacaoCadastral imovelTxt;
		imovelTxt = new ImovelAtualizacaoCadastral();

		// Linha 2
		imovelTxt.setIdImovel(matriculaImovel);
		imovelTxt.setNumeroImovel(linhaImovel.get("numeroImovel"));
		imovelTxt.setComplementoEndereco(linhaImovel.get("complementoImovel"));
		imovelTxt.setIdFonteAbastecimento(Integer.parseInt(linhaImovel.get("fonteAbastecimento")));
		imovelTxt.setNumeroIptu(linhaImovel.get("numeroIPTU") == null ? null : new BigDecimal(linhaImovel.get("numeroIPTU")));
		imovelTxt.setNumeroContratoEnergia(linhaImovel.get("numeroCelpa").equals("") ? null : Long.parseLong(linhaImovel.get("numeroCelpa")));
		imovelTxt.setIdLogradouroTipo(Integer.parseInt(linhaImovel.get("idTipoLogradouroImovel")));
		imovelTxt.setDsLogradouroTipo(getDescricaoLogradouro(Integer.parseInt(linhaImovel.get("idTipoLogradouroImovel"))));
		imovelTxt.setDescricaoLogradouro(linhaImovel.get("logradouroImovel"));
		imovelTxt.setCodigoCep(Integer.parseInt(linhaImovel.get("cep")));
		imovelTxt.setNomeBairro(linhaImovel.get("bairro"));
		imovelTxt.setNomeMunicipio(linhaImovel.get("municipio"));
		imovelTxt.setNumeroPontosUtilizacao(Short.parseShort(linhaImovel.get("numeroPontosUteis")));
		imovelTxt.setNumeroMorador(Short.parseShort(linhaImovel.get("numeroOcupantes")));

		// Linha 4
		imovelTxt.setIdLigacaoAguaSituacao(Integer.parseInt(linhaServicos.get("ligacaoAguaSituacao")));
		imovelTxt.setIdLigacaoEsgotoSituacao(Integer.parseInt(linhaServicos.get("ligacaoEsgotoSituacao")));
		imovelTxt.setIdLocalInstalacaoRamal(linhaServicos.get("localInstalacaoRamal").equals("") ? null : Integer.parseInt(linhaServicos.get("localInstalacaoRamal")));

		// Linha 5
		if (linhaMedidor.size() > 0) {
			imovelTxt.setNumeroHidrometro(linhaMedidor.get("numeroHidrometro"));
			imovelTxt.setIdMarcaHidrometro(linhaMedidor.get("marcaHidrometro").equals("") ? 0 : Integer.parseInt(linhaMedidor.get("marcaHidrometro")));
			imovelTxt.setIdProtecaoHidrometro(linhaMedidor.get("tipoCaixaProtecaoHidrometro").equals("") ? 0 : Integer.parseInt(linhaMedidor
					.get("tipoCaixaProtecaoHidrometro")));
			imovelTxt.setIdCapacidadeHidrometro(linhaMedidor.get("capacidadeHidrometro").equals("") ? 0 : Integer.parseInt(linhaMedidor.get("capacidadeHidrometro")));
		}

		// Linha 6
		imovelTxt.setIdCadastroOcorrencia(Integer.parseInt(linhaAnormalidade.get("codigoAnormalidade")));
		imovelTxt.setDescricaoOutrasInformacoes(linhaAnormalidade.get("comentario").trim());
		imovelTxt.setCoordenadaY(linhaAnormalidade.get("latitude"));
		imovelTxt.setCoordenadaX(linhaAnormalidade.get("longitude"));
		return imovelTxt;
	}

	private ClienteAtualizacaoCadastral buildClienteProprietario(ArquivoTextoAtualizacaoCadastral arquivoTexto, Map<String, String> linhaCliente,
			Interceptador interceptador, int matriculaImovel, int matriculaProprietario) {
		
		if (matriculaProprietario == 0) {
			return null;
		}
		
		ArrayList<ClienteFoneAtualizacaoCadastral> clientesFone = new ArrayList<ClienteFoneAtualizacaoCadastral>();
		
		ClienteAtualizacaoCadastral clienteProprietarioTxt;
		clienteProprietarioTxt = new ClienteAtualizacaoCadastral();

		clienteProprietarioTxt.setNomeCliente(linhaCliente.get("nomeProprietario"));
		clienteProprietarioTxt.setCpfCnpj(linhaCliente.get("cnpjCpfProprietario"));
		clienteProprietarioTxt.setRg(linhaCliente.get("rgProprietario"));
		clienteProprietarioTxt.setDsUFSiglaOrgaoExpedidorRg(linhaCliente.get("ufRgProprietario"));
		clienteProprietarioTxt.setIdPessoaSexo(Integer.parseInt(linhaCliente.get("sexoProprietario")));
		clienteProprietarioTxt.setEmail(linhaCliente.get("emailProprietario"));
		clienteProprietarioTxt.setIdLogradouroTipo(Integer.parseInt(linhaCliente.get("idTipoLogradouroProprietario")));
		clienteProprietarioTxt.setDescricaoLogradouro(linhaCliente.get("logradouroProprietario"));
		clienteProprietarioTxt.setNumeroImovel(linhaCliente.get("numeroProprietario"));
		clienteProprietarioTxt.setComplementoEndereco(linhaCliente.get("complementoProprietario"));
		clienteProprietarioTxt.setNomeBairro(linhaCliente.get("bairroProprietario"));
		clienteProprietarioTxt.setCodigoCep(linhaCliente.get("cepProprietario").equals("") ? null : Integer.parseInt(linhaCliente.get("cepProprietario")));
		clienteProprietarioTxt.setNomeMunicipio(linhaCliente.get("municipioProprietario"));
		clienteProprietarioTxt.setIdClienteRelacaoTipo(new Integer(ClienteRelacaoTipo.PROPRIETARIO));
		clienteProprietarioTxt.setIdImovel(Integer.parseInt(linhaCliente.get("matriculaImovelCliente")));

		if (!linhaCliente.get("telefoneProprietario").trim().equals("")) {
			ClienteFoneAtualizacaoCadastral clienteFone = new ClienteFoneAtualizacaoCadastral();

			// clienteFone.setIdCliente(id);
			clienteFone.setDdd(linhaCliente.get("telefoneProprietario").substring(0, 2));
			clienteFone.setTelefone(linhaCliente.get("telefoneProprietario").substring(2));
			clienteFone.setIdFoneTipo(FoneTipo.RESIDENCIAL);
			clienteFone.setIdCliente(matriculaProprietario);

			clientesFone.add(clienteFone);

			try {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = controladorCliente
						.pesquisarClienteFoneAtualizacaoCadastral(Integer.valueOf(matriculaProprietario), Integer.valueOf(matriculaImovel),
								FoneTipo.RESIDENCIAL, Integer.valueOf(ClienteRelacaoTipo.PROPRIETARIO), null).iterator().next();

				salvarTabelaColunaAtualizacaoCadastral(clienteFoneAtualizacaoCadastral, clienteFone, arquivoTexto, interceptador, matriculaImovel);
			} catch (NoSuchElementException e) {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = new ClienteFoneAtualizacaoCadastral();
				try {
					salvarTabelaColunaAtualizacaoCadastral(clienteFoneAtualizacaoCadastral, clienteFone, arquivoTexto, interceptador, matriculaImovel);
				} catch (ControladorException e1) {
					e1.printStackTrace();
				}
			} catch (ControladorException e) {
				e.printStackTrace();
			}
		}

		if (!linhaCliente.get("celularProprietario").trim().equals("")) {
			ClienteFoneAtualizacaoCadastral clienteFone = new ClienteFoneAtualizacaoCadastral();

			clienteFone.setDdd(linhaCliente.get("celularProprietario").substring(0, 2));
			clienteFone.setTelefone(linhaCliente.get("celularProprietario").substring(2));
			clienteFone.setIdFoneTipo(FoneTipo.CELULAR);
			clienteFone.setIdCliente(matriculaProprietario);

			clientesFone.add(clienteFone);

			try {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = controladorCliente
						.pesquisarClienteFoneAtualizacaoCadastral(Integer.valueOf(matriculaProprietario), Integer.valueOf(matriculaImovel),
								FoneTipo.CELULAR, Integer.valueOf(ClienteRelacaoTipo.PROPRIETARIO), null).iterator().next();
			} catch (NoSuchElementException e) {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = new ClienteFoneAtualizacaoCadastral();
				try {
					salvarTabelaColunaAtualizacaoCadastral(clienteFoneAtualizacaoCadastral, clienteFone, arquivoTexto, interceptador, matriculaImovel);
				} catch (ControladorException e1) {
					e1.printStackTrace();
				}
			} catch (ControladorException e) {
				e.printStackTrace();
			}
		}
		return clienteProprietarioTxt;
	}

	private ClienteAtualizacaoCadastral buildClienteResponsavel(ArquivoTextoAtualizacaoCadastral arquivoTexto, Map<String, String> linhaCliente,
			Interceptador interceptador, int matriculaImovel, int matriculaResponsavel) {
		
		if (matriculaResponsavel == 0) {
			return null;
		}
		
		ArrayList<ClienteFoneAtualizacaoCadastral> clientesFone = new ArrayList<ClienteFoneAtualizacaoCadastral>();
		
		ClienteAtualizacaoCadastral clienteResponsavelTxt;
		clienteResponsavelTxt = new ClienteAtualizacaoCadastral();

		clienteResponsavelTxt.setNomeCliente(linhaCliente.get("nomeResponsavel"));
		clienteResponsavelTxt.setCpfCnpj(linhaCliente.get("cnpjCpfResponsavel"));
		clienteResponsavelTxt.setRg(linhaCliente.get("rgResponsavel"));
		clienteResponsavelTxt.setDsUFSiglaOrgaoExpedidorRg(linhaCliente.get("ufRgResponsavel"));
		clienteResponsavelTxt.setIdPessoaSexo(linhaCliente.get("sexoResponsavel").equals("") ? null : Integer.parseInt(linhaCliente.get("sexoResponsavel")));
		clienteResponsavelTxt.setEmail(linhaCliente.get("emailResponsavel"));
		clienteResponsavelTxt.setIdLogradouroTipo(Integer.parseInt(linhaCliente.get("idTipoLogradouroResponsavel")));
		clienteResponsavelTxt.setDescricaoLogradouro(linhaCliente.get("logradouroResponsavel"));
		clienteResponsavelTxt.setNumeroImovel(linhaCliente.get("numeroResponsavel"));
		clienteResponsavelTxt.setComplementoEndereco(linhaCliente.get("complementoResponsavel"));
		clienteResponsavelTxt.setNomeBairro(linhaCliente.get("bairroResponsavel"));
		clienteResponsavelTxt.setCodigoCep(Integer.parseInt(linhaCliente.get("cepResponsavel")));
		clienteResponsavelTxt.setNomeMunicipio(linhaCliente.get("municipioResponsavel"));
		clienteResponsavelTxt.setIdClienteRelacaoTipo(new Integer(ClienteRelacaoTipo.RESPONSAVEL));
		clienteResponsavelTxt.setIdImovel(Integer.parseInt(linhaCliente.get("matriculaImovelCliente")));

		if (!linhaCliente.get("telefoneResponsavel").trim().equals("")) {
			ClienteFoneAtualizacaoCadastral clienteFone = new ClienteFoneAtualizacaoCadastral();

			// clienteFone.setIdCliente(id);
			clienteFone.setDdd(linhaCliente.get("telefoneResponsavel").substring(0, 2));
			clienteFone.setTelefone(linhaCliente.get("telefoneResponsavel").substring(2));
			clienteFone.setIdFoneTipo(FoneTipo.RESIDENCIAL);
			clienteFone.setIdCliente(matriculaResponsavel);

			clientesFone.add(clienteFone);

			try {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = controladorCliente
						.pesquisarClienteFoneAtualizacaoCadastral(Integer.valueOf(matriculaResponsavel), Integer.valueOf(matriculaImovel),
								FoneTipo.RESIDENCIAL, Integer.valueOf(ClienteRelacaoTipo.RESPONSAVEL), null).iterator().next();

				salvarTabelaColunaAtualizacaoCadastral(clienteFoneAtualizacaoCadastral, clienteFone, arquivoTexto, interceptador, matriculaImovel);
			} catch (NoSuchElementException e) {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = new ClienteFoneAtualizacaoCadastral();
				try {
					salvarTabelaColunaAtualizacaoCadastral(clienteFoneAtualizacaoCadastral, clienteFone, arquivoTexto, interceptador, matriculaImovel);
				} catch (ControladorException e1) {
					e1.printStackTrace();
				}
			} catch (ControladorException e) {
				e.printStackTrace();
			}
		}

		if (!linhaCliente.get("celularResponsavel").trim().equals("")) {
			ClienteFoneAtualizacaoCadastral clienteFone = new ClienteFoneAtualizacaoCadastral();

			// clienteFone.setIdCliente(id);
			clienteFone.setDdd(linhaCliente.get("celularResponsavel").substring(0, 2));
			clienteFone.setTelefone(linhaCliente.get("celularResponsavel").substring(2));
			clienteFone.setIdFoneTipo(FoneTipo.CELULAR);
			clienteFone.setIdCliente(matriculaResponsavel);

			clientesFone.add(clienteFone);

			try {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = controladorCliente
						.pesquisarClienteFoneAtualizacaoCadastral(Integer.valueOf(matriculaResponsavel), Integer.valueOf(matriculaImovel),
								FoneTipo.CELULAR, Integer.valueOf(ClienteRelacaoTipo.RESPONSAVEL), null).iterator().next();

				salvarTabelaColunaAtualizacaoCadastral(clienteFoneAtualizacaoCadastral, clienteFone, arquivoTexto, interceptador, matriculaImovel);
			} catch (NoSuchElementException e) {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = new ClienteFoneAtualizacaoCadastral();
				try {
					salvarTabelaColunaAtualizacaoCadastral(clienteFoneAtualizacaoCadastral, clienteFone, arquivoTexto, interceptador, matriculaImovel);
				} catch (ControladorException e1) {
					e1.printStackTrace();
				}
			} catch (ControladorException e) {
				e.printStackTrace();
			}
		}
		return clienteResponsavelTxt;
	}

	private ClienteAtualizacaoCadastral buildClienteUsuario(ArquivoTextoAtualizacaoCadastral arquivoTexto, Map<String, String> linhaCliente,
			Map<String, String> linhaImovel, Interceptador interceptador, int matriculaImovel, int matriculaUsuario, int matriculaResponsavel,
			int matriculaProprietario) {
		
		if (matriculaUsuario == 0) {
			return null;
		}
		
		ArrayList<ClienteFoneAtualizacaoCadastral> clientesFone = new ArrayList<ClienteFoneAtualizacaoCadastral>();
		
		ClienteAtualizacaoCadastral clienteUsuarioTxt;
		clienteUsuarioTxt = new ClienteAtualizacaoCadastral();

		clienteUsuarioTxt.setNomeCliente(linhaCliente.get("nomeUsuario"));
		clienteUsuarioTxt.setCpfCnpj(linhaCliente.get("cnpjCpfUsuario"));
		clienteUsuarioTxt.setRg(linhaCliente.get("rgUsuario"));
		clienteUsuarioTxt.setDsUFSiglaOrgaoExpedidorRg(linhaCliente.get("ufRgUsuario"));
		clienteUsuarioTxt.setIdPessoaSexo(linhaCliente.get("sexoUsuario").equals("0") ? null : Integer.parseInt(linhaCliente.get("sexoUsuario")));
		clienteUsuarioTxt.setEmail(linhaCliente.get("emailUsuario"));
		clienteUsuarioTxt.setIdClienteRelacaoTipo(new Integer(ClienteRelacaoTipo.USUARIO));
		clienteUsuarioTxt.setIdImovel(Integer.parseInt(linhaCliente.get("matriculaImovelCliente")));

		if (matriculaUsuario == matriculaResponsavel) {
			clienteUsuarioTxt.setIdLogradouroTipo(Integer.parseInt(linhaCliente.get("idTipoLogradouroResponsavel")));
			clienteUsuarioTxt.setDescricaoLogradouro(linhaCliente.get("logradouroResponsavel"));
			clienteUsuarioTxt.setDsLogradouroTipo(getDescricaoLogradouro(Integer.parseInt(linhaCliente.get("idTipoLogradouroResponsavel"))));
			clienteUsuarioTxt.setNumeroImovel(linhaCliente.get("numeroResponsavel"));
			clienteUsuarioTxt.setComplementoEndereco(linhaCliente.get("complementoResponsavel"));
			clienteUsuarioTxt.setNomeBairro(linhaCliente.get("bairroResponsavel"));
			clienteUsuarioTxt.setCodigoCep(Integer.parseInt(linhaCliente.get("cepResponsavel")));
			clienteUsuarioTxt.setNomeMunicipio(linhaCliente.get("municipioResponsavel"));

		} else if (matriculaUsuario == matriculaProprietario) {
			clienteUsuarioTxt.setIdLogradouroTipo(Integer.parseInt(linhaCliente.get("idTipoLogradouroProprietario")));
			clienteUsuarioTxt.setDescricaoLogradouro(linhaCliente.get("logradouroProprietario"));
			clienteUsuarioTxt.setDsLogradouroTipo(getDescricaoLogradouro(Integer.parseInt(linhaCliente.get("idTipoLogradouroProprietario"))));
			clienteUsuarioTxt.setNumeroImovel(linhaCliente.get("numeroProprietario"));
			clienteUsuarioTxt.setComplementoEndereco(linhaCliente.get("complementoProprietario"));
			clienteUsuarioTxt.setNomeBairro(linhaCliente.get("bairroProprietario"));
			clienteUsuarioTxt.setCodigoCep(linhaCliente.get("cepProprietario").equals("") ? null : Integer.parseInt(linhaCliente.get("cepProprietario")));
			clienteUsuarioTxt.setNomeMunicipio(linhaCliente.get("municipioProprietario"));

		} else {
			clienteUsuarioTxt.setIdLogradouroTipo(Integer.parseInt(linhaImovel.get("idTipoLogradouroImovel")));
			clienteUsuarioTxt.setDescricaoLogradouro(linhaImovel.get("logradouroImovel"));
			clienteUsuarioTxt.setDsLogradouroTipo(getDescricaoLogradouro(Integer.parseInt(linhaImovel.get("idTipoLogradouroImovel"))));
			clienteUsuarioTxt.setNumeroImovel(linhaImovel.get("numeroImovel"));
			clienteUsuarioTxt.setComplementoEndereco(linhaImovel.get("complementoImovel"));
			clienteUsuarioTxt.setNomeBairro(linhaImovel.get("bairro"));
			clienteUsuarioTxt.setCodigoCep(Integer.parseInt(linhaImovel.get("cep")));
			clienteUsuarioTxt.setNomeMunicipio(linhaImovel.get("municipio"));

		}

		if (!linhaCliente.get("telefoneUsuario").trim().equals("")) {
			ClienteFoneAtualizacaoCadastral clienteFone = new ClienteFoneAtualizacaoCadastral();

			// clienteFone.setIdCliente(id);
			clienteFone.setDdd(linhaCliente.get("telefoneUsuario").substring(0, 2));
			clienteFone.setTelefone(linhaCliente.get("telefoneUsuario").substring(2));
			clienteFone.setIdFoneTipo(FoneTipo.RESIDENCIAL);
			clienteFone.setIdCliente(matriculaUsuario);

			clientesFone.add(clienteFone);

			try {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = controladorCliente
						.pesquisarClienteFoneAtualizacaoCadastral(Integer.valueOf(matriculaUsuario), Integer.valueOf(matriculaImovel),
								FoneTipo.RESIDENCIAL, Integer.valueOf(ClienteRelacaoTipo.USUARIO), null).iterator().next();

				salvarTabelaColunaAtualizacaoCadastral(clienteFoneAtualizacaoCadastral, clienteFone, arquivoTexto, interceptador, matriculaImovel);
			} catch (NoSuchElementException e) {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = new ClienteFoneAtualizacaoCadastral();
				try {
					salvarTabelaColunaAtualizacaoCadastral(clienteFoneAtualizacaoCadastral, clienteFone, arquivoTexto, interceptador, matriculaImovel);
				} catch (ControladorException e1) {
					e1.printStackTrace();
				}
			} catch (ControladorException e) {
				e.printStackTrace();
			}
		}

		if (!linhaCliente.get("celularUsuario").trim().equals("")) {
			ClienteFoneAtualizacaoCadastral clienteFone = new ClienteFoneAtualizacaoCadastral();

			// clienteFone.setIdCliente(id);
			clienteFone.setDdd(linhaCliente.get("celularUsuario").substring(0, 2));
			clienteFone.setTelefone(linhaCliente.get("celularUsuario").substring(2));
			clienteFone.setIdFoneTipo(FoneTipo.CELULAR);
			clienteFone.setIdCliente(matriculaUsuario);

			clientesFone.add(clienteFone);

			try {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = controladorCliente
						.pesquisarClienteFoneAtualizacaoCadastral(Integer.valueOf(matriculaUsuario), Integer.valueOf(matriculaImovel), FoneTipo.CELULAR,
								Integer.valueOf(ClienteRelacaoTipo.USUARIO), null).iterator().next();

				salvarTabelaColunaAtualizacaoCadastral(clienteFoneAtualizacaoCadastral, clienteFone, arquivoTexto, interceptador, matriculaImovel);
			} catch (NoSuchElementException e) {
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = new ClienteFoneAtualizacaoCadastral();
				try {
					salvarTabelaColunaAtualizacaoCadastral(clienteFoneAtualizacaoCadastral, clienteFone, arquivoTexto, interceptador, matriculaImovel);
				} catch (ControladorException e1) {
					e1.printStackTrace();
				}
			} catch (ControladorException e) {
				e.printStackTrace();
			}
		}
		return clienteUsuarioTxt;
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
