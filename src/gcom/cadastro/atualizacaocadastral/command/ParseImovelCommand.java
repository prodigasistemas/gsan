package gcom.cadastro.atualizacaocadastral.command;

import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.cadastro.imovel.ImagemAtualizacaoCadastral;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ErroRepositorioException;
import gcom.util.ParserUtil;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;

public class ParseImovelCommand extends AbstractAtualizacaoCadastralCommand {

	public ParseImovelCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil, 
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, 
			ControladorImovelLocal controladorImovel, ControladorClienteLocal controladorCliente) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorImovel, controladorCliente);
	}

	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		Map<String, String> linha = atualizacao.getImovelAtual().getLinhaImovel();
		
		Integer matricula = Integer.parseInt(parser.obterDadoParser(9).trim());
		linha.put("matricula", "" + matricula);
		
		String tipoOperacao = parser.obterDadoParser(1);
		linha.put("tipoOperacao", "" + tipoOperacao);

		inserirImagem(atualizacao, matricula);

		String codigoCliente = parser.obterDadoParser(30).trim();
		linha.put("codigoCliente", codigoCliente);

		String inscricao = parser.obterDadoParser(17).trim();
		linha.put("inscricao", inscricao);

		String rota = parser.obterDadoParser(2).trim();
		linha.put("rota", rota);

		String face = parser.obterDadoParser(2).trim();
		linha.put("face", face);

		String codigoMunicipio = parser.obterDadoParser(8).trim();
		linha.put("codigoMunicipio", codigoMunicipio);

		String numeroIPTU = parser.obterDadoParser(31).trim();
		linha.put("numeroIPTU", numeroIPTU.trim().equals("") ? null : numeroIPTU);

		String numeroCelpa = parser.obterDadoParser(20).trim();
		linha.put("numeroCelpa", numeroCelpa);

		String numeroPontosUteis = parser.obterDadoParser(5).trim();
		linha.put("numeroPontosUteis", numeroPontosUteis);

		String numeroOcupantes = parser.obterDadoParser(5).trim();
		linha.put("numeroOcupantes", numeroOcupantes);

		String tipoLogradouroImovel = parser.obterDadoParser(2).trim();
		linha.put("idTipoLogradouroImovel", tipoLogradouroImovel);

		String logradouroImovel = parser.obterDadoParser(40).trim();
		linha.put("logradouroImovel", logradouroImovel);

		String numeroImovel = parser.obterDadoParser(5).trim();
		linha.put("numeroImovel", numeroImovel);

		String complementoImovel = parser.obterDadoParser(25).trim();
		linha.put("complementoImovel", complementoImovel);

		String bairro = parser.obterDadoParser(20).trim();
		linha.put("bairro", bairro);

		String cep = parser.obterDadoParser(8).trim();
		linha.put("cep", cep);

		String municipio = parser.obterDadoParser(15).trim();
		linha.put("municipio", municipio);

		String codigoLogradouro = parser.obterDadoParser(9).trim();
		linha.put("codigoLogradouro", codigoLogradouro);

		String subcategoriaR1 = parser.obterDadoParser(3).trim();
		linha.put("subcategoriaR1", subcategoriaR1);

		String subcategoriaR2 = parser.obterDadoParser(3).trim();
		linha.put("subcategoriaR2", subcategoriaR2);

		String subcategoriaR3 = parser.obterDadoParser(3).trim();
		linha.put("subcategoriaR3", subcategoriaR3);

		String subcategoriaR4 = parser.obterDadoParser(3).trim();
		linha.put("subcategoriaR4", subcategoriaR4);

		String subcategoriaC1 = parser.obterDadoParser(3).trim();
		linha.put("subcategoriaC1", subcategoriaC1);

		String subcategoriaC2 = parser.obterDadoParser(3).trim();
		linha.put("subcategoriaC2", subcategoriaC2);

		String subcategoriaC3 = parser.obterDadoParser(3).trim();
		linha.put("subcategoriaC3", subcategoriaC3);

		String subcategoriaC4 = parser.obterDadoParser(3).trim();
		linha.put("subcategoriaC4", subcategoriaC4);

		String subcategoriaP1 = parser.obterDadoParser(3).trim();
		linha.put("subcategoriaP1", subcategoriaP1);

		String subcategoriaP2 = parser.obterDadoParser(3).trim();
		linha.put("subcategoriaP2", subcategoriaP2);

		String subcategoriaP3 = parser.obterDadoParser(3).trim();
		linha.put("subcategoriaP3", subcategoriaP3);

		String subcategoriaP4 = parser.obterDadoParser(3).trim();
		linha.put("subcategoriaP4", subcategoriaP4);

		String subcategoriaI1 = parser.obterDadoParser(3).trim();
		linha.put("subcategoriaI1", subcategoriaI1);

		String subcategoriaI2 = parser.obterDadoParser(3).trim();
		linha.put("subcategoriaI2", subcategoriaI2);

		String subcategoriaI3 = parser.obterDadoParser(3).trim();
		linha.put("subcategoriaI3", subcategoriaI3);

		String subcategoriaI4 = parser.obterDadoParser(3).trim();
		linha.put("subcategoriaI4", subcategoriaI4);

		String fonteAbastecimento = parser.obterDadoParser(2).trim();
		linha.put("fonteAbastecimento", fonteAbastecimento);

		String latitude = parser.obterDadoParser(20).trim();
		linha.put("latitude", latitude);

		String longitude = parser.obterDadoParser(20).trim();
		linha.put("longitude", longitude);

		String data = parser.obterDadoParser(26).trim();
		linha.put("data", data);
		
		validaCamposImovel(atualizacao);
	}

	private void inserirImagem(AtualizacaoCadastral atualizacao,
			Integer matricula) throws IOException, ControladorException {
		for (String nomeImagem : atualizacao.getImovelAtual().getNomesImagens()) {
			if (nomeImagem.contains("" + matricula)) {
				File file = new File(nomeImagem);

				BufferedImage image = ImageIO.read(file);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(image, nomeImagem.split("[.]")[1], baos);
				byte[] imagem = baos.toByteArray();

				ImagemAtualizacaoCadastral imagemAtualizacaoCadastral = new ImagemAtualizacaoCadastral();
				imagemAtualizacaoCadastral.setIdImovel(matricula);
				imagemAtualizacaoCadastral.setImagem(imagem);
				imagemAtualizacaoCadastral.setUltimaAlteracao(new Date());

				controladorUtil.inserir(imagemAtualizacaoCadastral);

				baos.close();
				file.delete();
			}
		}
	}

	private void validaCamposImovel(AtualizacaoCadastral atualizacao) throws Exception {
		AtualizacaoCadastralImovel imovel = atualizacao.getImovelAtual(); 
		Map<String, String> linha = imovel.getLinhaImovel();
		
		validarTipoLogradouro(imovel, linha);
		validarTipoOperacao(imovel, linha);
		validarCoordenadas(imovel, linha);
		validarEconomias(imovel, linha);
	}

	private void validarTipoOperacao(AtualizacaoCadastralImovel imovel, Map<String, String> linha) throws ErroRepositorioException {
		String tipoOperacao = linha.get("tipoOperacao"); 
		if (campoNumericoInvalido(tipoOperacao)){
			imovel.addMensagemErro("Tipo da operação é inválido");
		}else{
			Integer tipo = Integer.valueOf(tipoOperacao);
			if (tipo == AlteracaoTipo.INCLUSAO){
				String codigoLogradouro = linha.get("codigoLogradouro");
				String codigoMunicipio = linha.get("codigoMunicipio");
				String nomeBairro       = linha.get("bairro");
				String municipio        = linha.get("municipio");
				String cep              = linha.get("cep");
				
				boolean codigosInvalidos = false;
				if (campoNumericoInvalido(codigoLogradouro)){
					imovel.addMensagemErro("Código do logradouro inválido");
					codigosInvalidos = true;
				}
				if (campoNumericoInvalido(codigoMunicipio)){
					imovel.addMensagemErro("Código do município inválido");
					codigosInvalidos = true;
				}
				
				if (campoNumericoInvalido(cep)){
					imovel.addMensagemErro("CEP inválido");
					codigosInvalidos = true;
				}
				
				if (!codigosInvalidos){
					Integer cod = Integer.valueOf(codigoLogradouro);
					Logradouro logradouro = repositorioImovel.pesquisarLogradouro(cod);
					boolean bairroInvalido = true;
					for(LogradouroBairro logrBairro: logradouro.getLogradouroBairros()){
						if (logrBairro.getBairro().getNome().equalsIgnoreCase(nomeBairro)){
							bairroInvalido = false;
						}
					}
					
					if (bairroInvalido){
						imovel.addMensagemErro("Bairro inválido");
					}
					
					if (!logradouro.getMunicipio().getNome().equalsIgnoreCase(municipio)){
						imovel.addMensagemErro("Município inválido");
					}
					
					boolean cepInvalido = true;
					for(LogradouroCep logrCep: logradouro.getLogradouroCeps()){
						if (logrCep.getCep().getCodigo() == Integer.valueOf(cep)){
							cepInvalido = false;
						}
					}
					
					if (cepInvalido){
						imovel.addMensagemErro("CEP inválido");
					}
				}
				
				String inscricao = linha.get("inscricao");
				String lote      = inscricao.substring(10, 14);
				String sublote   = inscricao.substring(14);
				
				if (campoNumericoInvalido(lote)){
					imovel.addMensagemErro("Lote inválido");
				}
				if (campoNumericoInvalido(sublote)){
					imovel.addMensagemErro("Sublote inválido");
				}
				
				int qtdInscricao = 0;
				for (AtualizacaoCadastralImovel itemAtualizacao: imovel.getAtualizacaoArquivo().getAtualizacoesImovel()){
					if (itemAtualizacao.getLinhaImovel("inscricao").equals(inscricao)){
						qtdInscricao++;
					}
				}
				
				if (qtdInscricao > 1){
					imovel.addMensagemErro("Número de inscrição repetido");
				}
				
				imovel.limparDadosProprietario();
				imovel.limparDadosResponsavel();
			}
		}
	}

	private void validarTipoLogradouro(AtualizacaoCadastralImovel imovel, Map<String, String> linha) throws ErroRepositorioException {
		String tipoLogradouro = linha.get("idTipoLogradouroImovel");
		
		if (StringUtils.isEmpty(tipoLogradouro)){
			imovel.addMensagemErro("Tipo do logradouro do imóvel inválido.");
		}else{
			LogradouroTipo tipo = repositorioImovel.pesquisarTipoLogradouro(Integer.valueOf(tipoLogradouro));
			if (tipo == null){
				imovel.addMensagemErro("Tipo do logradouro do imóvel inexistente.");
			}
		}
	}

	private void validarEconomias(AtualizacaoCadastralImovel imovel, Map<String, String> linha) {
		boolean existeEconomia = false;
		for(String key: linha.keySet()){
			if (key.contains("subcategoria")){
				String valor =  linha.get(key).trim();
				if (StringUtils.isNotEmpty(valor) && !StringUtils.containsOnly(valor.trim(), new char[]{'0'})){
					existeEconomia = true;
					
					char codigo = key.replace("subcategoria", "").charAt(0);
					TipoEconomia tipo = TipoEconomia.getByCodigo(codigo);
					if (!imovel.getDadosImovel().contemTipoEconomia(tipo)){
						imovel.getDadosImovel().addTipoEconomia(tipo);
					}
				}
			}
		}
		
		if (!existeEconomia){
			imovel.addMensagemErro("Imóvel deve possuir ao menos uma economia.");
		}
	}

	private void validarCoordenadas(AtualizacaoCadastralImovel imovel, Map<String, String> linha) {
		if (StringUtils.isEmpty(linha.get("latitude"))){
			imovel.addMensagemErro("Latitude inválida");
		}else{
			if (StringUtils.containsOnly(linha.get("latitude").trim(), new char[]{'0'})){
				imovel.addMensagemErro("Latitude inválida");
			}
		}
		
		if (StringUtils.isEmpty(linha.get("longitude"))){
			imovel.addMensagemErro("Longitude inválida");
		}
		else{
			if (StringUtils.containsOnly(linha.get("longitude").trim(), new char[]{'0'})){
				imovel.addMensagemErro("Longitude inválida");
			}
		}
	}

	private boolean campoNumericoInvalido(String tipoOperacao) {
		return StringUtils.isEmpty(tipoOperacao) || !StringUtils.isNumeric(tipoOperacao) || StringUtils.containsOnly(tipoOperacao, new char[]{'0'}) ;
	}
	
}
