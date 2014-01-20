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
		montarObjetosAtualizacaoCadastral(atualizacao.getArquivoTexto(), atualizacao.getLinhaCliente(), atualizacao.getLinhaImovel(),
				atualizacao.getLinhaRamoAtividade(), atualizacao.getLinhaServicos(), atualizacao.getLinhaMedidor(), atualizacao.getLinhaAnormalidade());
	}
	
	public void montarObjetosAtualizacaoCadastral(ArquivoTextoAtualizacaoCadastral arquivoTexto, Map<String, String>... maps) throws Exception {

		ClienteAtualizacaoCadastral clienteUsuarioTxt = null;
		ClienteAtualizacaoCadastral clienteResponsavelTxt = null;
		ClienteAtualizacaoCadastral clienteProprietarioTxt = null;
		ImovelAtualizacaoCadastral imovelTxt = null;

		Interceptador interceptador = Interceptador.getInstancia();

		int matriculaImovel = Integer.parseInt(maps[1].get("matricula"));

		// Linha 1
		int matriculaUsuario = Integer.parseInt(maps[0].get("matriculaUsuario"));
		int matriculaResponsavel = Integer.parseInt(maps[0].get("matriculaResponsavel"));
		int matriculaProprietario = Integer.parseInt(maps[0].get("matriculaProprietario"));

		ArrayList<ClienteFoneAtualizacaoCadastral> clientesFone = new ArrayList<ClienteFoneAtualizacaoCadastral>();

		if (matriculaUsuario != 0) {

			clienteUsuarioTxt = new ClienteAtualizacaoCadastral();

			clienteUsuarioTxt.setNomeCliente(maps[0].get("nomeUsuario"));
			clienteUsuarioTxt.setCpfCnpj(maps[0].get("cnpjCpfUsuario"));
			clienteUsuarioTxt.setRg(maps[0].get("rgUsuario"));
			clienteUsuarioTxt.setDsUFSiglaOrgaoExpedidorRg(maps[0].get("ufRgUsuario"));
			clienteUsuarioTxt.setIdPessoaSexo(maps[0].get("sexoUsuario").equals("0") ? null : Integer.parseInt(maps[0].get("sexoUsuario")));
			clienteUsuarioTxt.setEmail(maps[0].get("emailUsuario"));
			clienteUsuarioTxt.setIdClienteRelacaoTipo(new Integer(ClienteRelacaoTipo.USUARIO));
			clienteUsuarioTxt.setIdImovel(Integer.parseInt(maps[0].get("matriculaImovelCliente")));

			if (matriculaUsuario == matriculaResponsavel) {
				clienteUsuarioTxt.setIdLogradouroTipo(Integer.parseInt(maps[0].get("idTipoLogradouroResponsavel")));
				clienteUsuarioTxt.setDescricaoLogradouro(maps[0].get("logradouroResponsavel"));
				clienteUsuarioTxt.setDsLogradouroTipo(getDescricaoLogradouro(Integer.parseInt(maps[0].get("idTipoLogradouroResponsavel"))));
				clienteUsuarioTxt.setNumeroImovel(maps[0].get("numeroResponsavel"));
				clienteUsuarioTxt.setComplementoEndereco(maps[0].get("complementoResponsavel"));
				clienteUsuarioTxt.setNomeBairro(maps[0].get("bairroResponsavel"));
				clienteUsuarioTxt.setCodigoCep(Integer.parseInt(maps[0].get("cepResponsavel")));
				clienteUsuarioTxt.setNomeMunicipio(maps[0].get("municipioResponsavel"));

			} else if (matriculaUsuario == matriculaProprietario) {
				clienteUsuarioTxt.setIdLogradouroTipo(Integer.parseInt(maps[0].get("idTipoLogradouroProprietario")));
				clienteUsuarioTxt.setDescricaoLogradouro(maps[0].get("logradouroProprietario"));
				clienteUsuarioTxt.setDsLogradouroTipo(getDescricaoLogradouro(Integer.parseInt(maps[0].get("idTipoLogradouroProprietario"))));
				clienteUsuarioTxt.setNumeroImovel(maps[0].get("numeroProprietario"));
				clienteUsuarioTxt.setComplementoEndereco(maps[0].get("complementoProprietario"));
				clienteUsuarioTxt.setNomeBairro(maps[0].get("bairroProprietario"));
				clienteUsuarioTxt.setCodigoCep(maps[0].get("cepProprietario").equals("") ? null : Integer.parseInt(maps[0].get("cepProprietario")));
				clienteUsuarioTxt.setNomeMunicipio(maps[0].get("municipioProprietario"));

			} else {
				clienteUsuarioTxt.setIdLogradouroTipo(Integer.parseInt(maps[1].get("idTipoLogradouroImovel")));
				clienteUsuarioTxt.setDescricaoLogradouro(maps[1].get("logradouroImovel"));
				clienteUsuarioTxt.setDsLogradouroTipo(getDescricaoLogradouro(Integer.parseInt(maps[1].get("idTipoLogradouroImovel"))));
				clienteUsuarioTxt.setNumeroImovel(maps[1].get("numeroImovel"));
				clienteUsuarioTxt.setComplementoEndereco(maps[1].get("complementoImovel"));
				clienteUsuarioTxt.setNomeBairro(maps[1].get("bairro"));
				clienteUsuarioTxt.setCodigoCep(Integer.parseInt(maps[1].get("cep")));
				clienteUsuarioTxt.setNomeMunicipio(maps[1].get("municipio"));

			}

			if (!maps[0].get("telefoneUsuario").trim().equals("")) {
				ClienteFoneAtualizacaoCadastral clienteFone = new ClienteFoneAtualizacaoCadastral();

				// clienteFone.setIdCliente(id);
				clienteFone.setDdd(maps[0].get("telefoneUsuario").substring(0, 2));
				clienteFone.setTelefone(maps[0].get("telefoneUsuario").substring(2));
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

			if (!maps[0].get("celularUsuario").trim().equals("")) {
				ClienteFoneAtualizacaoCadastral clienteFone = new ClienteFoneAtualizacaoCadastral();

				// clienteFone.setIdCliente(id);
				clienteFone.setDdd(maps[0].get("celularUsuario").substring(0, 2));
				clienteFone.setTelefone(maps[0].get("celularUsuario").substring(2));
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
		}

		if (matriculaResponsavel != 0) {

			clienteResponsavelTxt = new ClienteAtualizacaoCadastral();

			clienteResponsavelTxt.setNomeCliente(maps[0].get("nomeResponsavel"));
			clienteResponsavelTxt.setCpfCnpj(maps[0].get("cnpjCpfResponsavel"));
			clienteResponsavelTxt.setRg(maps[0].get("rgResponsavel"));
			clienteResponsavelTxt.setDsUFSiglaOrgaoExpedidorRg(maps[0].get("ufRgResponsavel"));
			clienteResponsavelTxt.setIdPessoaSexo(maps[0].get("sexoResponsavel").equals("") ? null : Integer.parseInt(maps[0].get("sexoResponsavel")));
			clienteResponsavelTxt.setEmail(maps[0].get("emailResponsavel"));
			clienteResponsavelTxt.setIdLogradouroTipo(Integer.parseInt(maps[0].get("idTipoLogradouroResponsavel")));
			clienteResponsavelTxt.setDescricaoLogradouro(maps[0].get("logradouroResponsavel"));
			clienteResponsavelTxt.setNumeroImovel(maps[0].get("numeroResponsavel"));
			clienteResponsavelTxt.setComplementoEndereco(maps[0].get("complementoResponsavel"));
			clienteResponsavelTxt.setNomeBairro(maps[0].get("bairroResponsavel"));
			clienteResponsavelTxt.setCodigoCep(Integer.parseInt(maps[0].get("cepResponsavel")));
			clienteResponsavelTxt.setNomeMunicipio(maps[0].get("municipioResponsavel"));
			clienteResponsavelTxt.setIdClienteRelacaoTipo(new Integer(ClienteRelacaoTipo.RESPONSAVEL));
			clienteResponsavelTxt.setIdImovel(Integer.parseInt(maps[0].get("matriculaImovelCliente")));

			if (!maps[0].get("telefoneResponsavel").trim().equals("")) {
				ClienteFoneAtualizacaoCadastral clienteFone = new ClienteFoneAtualizacaoCadastral();

				// clienteFone.setIdCliente(id);
				clienteFone.setDdd(maps[0].get("telefoneResponsavel").substring(0, 2));
				clienteFone.setTelefone(maps[0].get("telefoneResponsavel").substring(2));
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

			if (!maps[0].get("celularResponsavel").trim().equals("")) {
				ClienteFoneAtualizacaoCadastral clienteFone = new ClienteFoneAtualizacaoCadastral();

				// clienteFone.setIdCliente(id);
				clienteFone.setDdd(maps[0].get("celularResponsavel").substring(0, 2));
				clienteFone.setTelefone(maps[0].get("celularResponsavel").substring(2));
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

		}

		if (matriculaProprietario != 0) {

			clienteProprietarioTxt = new ClienteAtualizacaoCadastral();

			clienteProprietarioTxt.setNomeCliente(maps[0].get("nomeProprietario"));
			clienteProprietarioTxt.setCpfCnpj(maps[0].get("cnpjCpfProprietario"));
			clienteProprietarioTxt.setRg(maps[0].get("rgProprietario"));
			clienteProprietarioTxt.setDsUFSiglaOrgaoExpedidorRg(maps[0].get("ufRgProprietario"));
			clienteProprietarioTxt.setIdPessoaSexo(Integer.parseInt(maps[0].get("sexoProprietario")));
			clienteProprietarioTxt.setEmail(maps[0].get("emailProprietario"));
			clienteProprietarioTxt.setIdLogradouroTipo(Integer.parseInt(maps[0].get("idTipoLogradouroProprietario")));
			clienteProprietarioTxt.setDescricaoLogradouro(maps[0].get("logradouroProprietario"));
			clienteProprietarioTxt.setNumeroImovel(maps[0].get("numeroProprietario"));
			clienteProprietarioTxt.setComplementoEndereco(maps[0].get("complementoProprietario"));
			clienteProprietarioTxt.setNomeBairro(maps[0].get("bairroProprietario"));
			clienteProprietarioTxt.setCodigoCep(maps[0].get("cepProprietario").equals("") ? null : Integer.parseInt(maps[0].get("cepProprietario")));
			clienteProprietarioTxt.setNomeMunicipio(maps[0].get("municipioProprietario"));
			clienteProprietarioTxt.setIdClienteRelacaoTipo(new Integer(ClienteRelacaoTipo.PROPRIETARIO));
			clienteProprietarioTxt.setIdImovel(Integer.parseInt(maps[0].get("matriculaImovelCliente")));

			if (!maps[0].get("telefoneProprietario").trim().equals("")) {
				ClienteFoneAtualizacaoCadastral clienteFone = new ClienteFoneAtualizacaoCadastral();

				// clienteFone.setIdCliente(id);
				clienteFone.setDdd(maps[0].get("telefoneProprietario").substring(0, 2));
				clienteFone.setTelefone(maps[0].get("telefoneProprietario").substring(2));
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

			if (!maps[0].get("celularProprietario").trim().equals("")) {
				ClienteFoneAtualizacaoCadastral clienteFone = new ClienteFoneAtualizacaoCadastral();

				clienteFone.setDdd(maps[0].get("celularProprietario").substring(0, 2));
				clienteFone.setTelefone(maps[0].get("celularProprietario").substring(2));
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

		}

		// Linha 2
		imovelTxt = new ImovelAtualizacaoCadastral();

		imovelTxt.setIdImovel(matriculaImovel);
		imovelTxt.setNumeroImovel(maps[1].get("numeroImovel"));
		imovelTxt.setComplementoEndereco(maps[1].get("complementoImovel"));
		imovelTxt.setIdFonteAbastecimento(Integer.parseInt(maps[1].get("fonteAbastecimento")));
		imovelTxt.setNumeroIptu(maps[1].get("numeroIPTU") == null ? null : new BigDecimal(maps[1].get("numeroIPTU")));
		imovelTxt.setNumeroContratoEnergia(maps[1].get("numeroCelpa").equals("") ? null : Long.parseLong(maps[1].get("numeroCelpa")));
		imovelTxt.setIdLogradouroTipo(Integer.parseInt(maps[1].get("idTipoLogradouroImovel")));
		imovelTxt.setDsLogradouroTipo(getDescricaoLogradouro(Integer.parseInt(maps[1].get("idTipoLogradouroImovel"))));
		imovelTxt.setDescricaoLogradouro(maps[1].get("logradouroImovel"));
		imovelTxt.setCodigoCep(Integer.parseInt(maps[1].get("cep")));
		imovelTxt.setNomeBairro(maps[1].get("bairro"));
		imovelTxt.setNomeMunicipio(maps[1].get("municipio"));
		imovelTxt.setNumeroPontosUtilizacao(Short.parseShort(maps[1].get("numeroPontosUteis")));
		imovelTxt.setNumeroMorador(Short.parseShort(maps[1].get("numeroOcupantes")));

		// Linha 4
		imovelTxt.setIdLigacaoAguaSituacao(Integer.parseInt(maps[3].get("ligacaoAguaSituacao")));
		imovelTxt.setIdLigacaoEsgotoSituacao(Integer.parseInt(maps[3].get("ligacaoEsgotoSituacao")));
		imovelTxt.setIdLocalInstalacaoRamal(maps[3].get("localInstalacaoRamal").equals("") ? null : Integer.parseInt(maps[3].get("localInstalacaoRamal")));

		// Linha 5
		if (maps[4].size() > 0) {
			imovelTxt.setNumeroHidrometro(maps[4].get("numeroHidrometro"));
			imovelTxt.setIdMarcaHidrometro(maps[4].get("marcaHidrometro").equals("") ? 0 : Integer.parseInt(maps[4].get("marcaHidrometro")));
			imovelTxt.setIdProtecaoHidrometro(maps[4].get("tipoCaixaProtecaoHidrometro").equals("") ? 0 : Integer.parseInt(maps[4]
					.get("tipoCaixaProtecaoHidrometro")));
			imovelTxt.setIdCapacidadeHidrometro(maps[4].get("capacidadeHidrometro").equals("") ? 0 : Integer.parseInt(maps[4].get("capacidadeHidrometro")));
		}

		// Linha 6
		imovelTxt.setIdCadastroOcorrencia(Integer.parseInt(maps[5].get("codigoAnormalidade")));
		imovelTxt.setDescricaoOutrasInformacoes(maps[5].get("comentario").trim());
		imovelTxt.setCoordenadaY(maps[5].get("latitude"));
		imovelTxt.setCoordenadaX(maps[5].get("longitude"));

		// Imovel Subcategoria
		short qtdEconomias = 0;
		String descricaoSubcategoria = "";
		String descricaoCategoria = "";
		int idCategoria = 0;
		for (int i = 0; i < 16; i++) {
			if (i <= 3) {
				qtdEconomias = Short.parseShort(maps[1].get("subcategoriaR" + (i + 1)));
				idCategoria = 1;
				descricaoSubcategoria = "R" + (i + 1);
				descricaoCategoria = "RESIDENCIAL";
			} else if (i >= 4 && i < 8) {
				qtdEconomias = Short.parseShort(maps[1].get("subcategoriaC" + (i - 3)));
				idCategoria = 2;
				descricaoSubcategoria = "C" + (i - 3);
				descricaoCategoria = "COMERCIAL";
			} else if (i >= 8 && i < 12) {
				qtdEconomias = Short.parseShort(maps[1].get("subcategoriaI" + (i - 7)));
				idCategoria = 3;
				descricaoSubcategoria = "I" + (i - 7);
				descricaoCategoria = "INDUSTRIAL";
			} else {
				qtdEconomias = Short.parseShort(maps[1].get("subcategoriaP" + (i - 11)));
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

		ImovelAtualizacaoCadastral imovelAtualizacaoCadastralBase = controladorImovel.pesquisarImovelAtualizacaoCadastral(matriculaImovel);
		salvarTabelaColunaAtualizacaoCadastral(imovelAtualizacaoCadastralBase, imovelTxt, arquivoTexto, interceptador, matriculaImovel);

		salvarImovelRetorno(imovelTxt);
		
		atualizarSituacaoControleImovelAtualizacaoCadastral(matriculaImovel, SituacaoAtualizacaoCadastral.TRANSMITIDO);
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
