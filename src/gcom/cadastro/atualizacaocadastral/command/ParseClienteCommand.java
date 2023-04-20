package gcom.cadastro.atualizacaocadastral.command;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import gcom.cadastro.atualizacaocadastral.validador.ValidadorTamanhoLinhaClienteCommand;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.cliente.RepositorioClienteHBM;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.util.ControladorUtilLocal;
import gcom.util.ErroRepositorioException;
import gcom.util.ParserUtil;
import gcom.util.Util;
import gcom.util.exception.MatriculaProprietarioException;
import gcom.util.exception.MatriculaResponsavelException;
import gcom.util.exception.MatriculaUsuarioException;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

public class ParseClienteCommand extends AbstractAtualizacaoCadastralCommand {
	
	private static Logger logger = Logger.getLogger(ParseClienteCommand.class);
	
	public static final String INDICADOR_TRANSMISSAO_CPF_CNPJ = "indicadorTransmissaoCpfCnpj";
	
	public static final String[] TIPOS_CLIENTE = { "Proprietario", "Usuario", "Responsavel" };
	
	public ParseClienteCommand(ParserUtil parser, ControladorUtilLocal controladorUtil) {
		super(parser);

		this.controladorUtil = controladorUtil;
	}
	
	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		Map<String, String> linha = atualizacao.getImovelAtual().getLinhaCliente();
		AtualizacaoCadastralImovel imovelAtual = atualizacao.getImovelAtual();
		
		new ValidadorTamanhoLinhaClienteCommand(parser, imovelAtual, linha).execute();
		
		String matriculaImovelCliente = parser.obterDadoParser(9).trim();
		atualizacao.getImovelAtual().setMatricula(Integer.valueOf(matriculaImovelCliente));
		
		if(!imovelAtual.isErroLayout()) {
			logger.info("Carregando Imovel: " + matriculaImovelCliente);

			linha.put("matriculaImovelCliente", matriculaImovelCliente);
			
			String gerencia = parser.obterDadoParser(25).trim();
			linha.put("gerencia", gerencia);

			String tipoEnderecoProprientario = parser.obterDadoParser(1).trim();
			linha.put("tipoEnderecoProprietario", tipoEnderecoProprientario);

			String tipoEnderecoResponsavel = parser.obterDadoParser(1).trim();
			linha.put("tipoEnderecoResponsavel", tipoEnderecoResponsavel);

			String usuarioProprietario = parser.obterDadoParser(1).trim();
			linha.put("usuarioProprietario", usuarioProprietario);

			String tipoResponsavel = parser.obterDadoParser(1).trim();
			linha.put("tipoResponsavel", tipoResponsavel);

			int matriculaUsuario = 0;
			try {
				matriculaUsuario = Integer.parseInt(parser.obterDadoParser(9));
			} catch (Exception e) {
				throw new MatriculaUsuarioException(e, String.valueOf(atualizacao.getImovelAtual().getMatricula())); 
			}
			linha.put("matriculaUsuario", ""+matriculaUsuario);

			String nomeUsuario = parser.obterDadoParser(50).trim();
			linha.put("nomeUsuario", nomeUsuario.toUpperCase());

			String tipoPessoaUsuario = parser.obterDadoParser(1).trim();
			linha.put("tipoPessoaUsuario", tipoPessoaUsuario);

			String cnpjCpfUsuario = parser.obterDadoParser(14).trim();
			linha.put("cnpjCpfUsuario", cnpjCpfUsuario);

			String rgUsuario = parser.obterDadoParser(13).trim();
			linha.put("rgUsuario", rgUsuario);

			String ufRgUsuario = parser.obterDadoParser(2).trim();
			linha.put("ufRgUsuario", ufRgUsuario);

			String sexoUsuario = parser.obterDadoParser(1).trim();
			linha.put("sexoUsuario", getSexoTipo(matriculaUsuario, sexoUsuario));
			
			String dddTelefoneUsuario =  parser.obterDadoParser(IClienteFone.TAMANHO_DDD).trim();
			linha.put("dddTelefoneUsuario", dddTelefoneUsuario);
			
			String telefoneUsuario = parser.obterDadoParser(IClienteFone.TAMANHO_TELEFONE).trim();
			linha.put("telefoneUsuario", telefoneUsuario);
			
			String dddCelularUsuario =  parser.obterDadoParser(IClienteFone.TAMANHO_DDD).trim();
			linha.put("dddCelularUsuario", dddCelularUsuario);
			
			String celularUsuario = parser.obterDadoParser(IClienteFone.TAMANHO_TELEFONE).trim();
			linha.put("celularUsuario", celularUsuario);				
								
			String emailUsuario = parser.obterDadoParser(30).trim();
			linha.put("emailUsuario", emailUsuario);
			
			String numeroNisUsuario = parser.obterDadoParser(11).trim();
			linha.put("numeroNisUsuario", numeroNisUsuario);

			int matriculaProprietario = 0;
			try {
				matriculaProprietario = Integer.parseInt(parser.obterDadoParser(9));
			} catch (Exception e) {
				throw new MatriculaProprietarioException(e, String.valueOf(atualizacao.getImovelAtual().getMatricula()));
			}

			linha.put("matriculaProprietario", ""+matriculaProprietario);

			String nomeProprietario = parser.obterDadoParser(50).trim();
			linha.put("nomeProprietario", nomeProprietario.toUpperCase());

			String tipoPessoaProprietario = parser.obterDadoParser(1).trim();
			linha.put("tipoPessoaProprietario", tipoPessoaProprietario);

			String cnpjCpfProprietario = parser.obterDadoParser(14).trim();
			linha.put("cnpjCpfProprietario", cnpjCpfProprietario);

			String rgProprietario = parser.obterDadoParser(13).trim();
			linha.put("rgProprietario", rgProprietario);

			String ufRgProprietario = parser.obterDadoParser(2).trim();
			linha.put("ufRgProprietario", ufRgProprietario);

			String sexoProprietario = parser.obterDadoParser(1).trim();
			linha.put("sexoProprietario", getSexoTipo(matriculaProprietario, sexoProprietario));
			
			String dddTelefoneProprietario =  parser.obterDadoParser(IClienteFone.TAMANHO_DDD).trim();
			linha.put("dddTelefoneProprietario", dddTelefoneProprietario);
		
			String telefoneProprietario = parser.obterDadoParser(IClienteFone.TAMANHO_TELEFONE).trim();
			linha.put("telefoneProprietario", telefoneProprietario);
			
			String dddCelularProprietario =  parser.obterDadoParser(IClienteFone.TAMANHO_DDD).trim();
			linha.put("dddCelularProprietario", dddCelularProprietario);
				
			String celularProprietario = parser.obterDadoParser(IClienteFone.TAMANHO_TELEFONE).trim();
			linha.put("celularProprietario", celularProprietario);				
					
			String emailProprietario = parser.obterDadoParser(30).trim();
			linha.put("emailProprietario", emailProprietario);

			String tipoLogradouroProprietario = parser.obterDadoParser(2).trim();
			linha.put("idTipoLogradouroProprietario", tipoLogradouroProprietario);
			
			if (StringUtils.isNotEmpty(tipoLogradouroProprietario) && StringUtils.isNumeric(tipoLogradouroProprietario) && Integer.valueOf(tipoLogradouroProprietario) > 0){
				LogradouroTipo tipo = (LogradouroTipo) controladorUtil.obterPorId(LogradouroTipo.class, Integer.valueOf(tipoLogradouroProprietario));
				linha.put("dsTipoLogradouroProprietario", tipo.getDescricao());
			}

			String logradouroProprietario = parser.obterDadoParser(40).trim();
			linha.put("logradouroProprietario", logradouroProprietario);

			String numeroProprietario = parser.obterDadoParser(5).trim();
			linha.put("numeroProprietario", numeroProprietario);

			String complementoProprietario = parser.obterDadoParser(25).trim();
			linha.put("complementoProprietario", complementoProprietario);

			String bairroProprietario = parser.obterDadoParser(20).trim();
			linha.put("bairroProprietario", bairroProprietario);

			String cepProprietario = parser.obterDadoParser(8).trim();
			linha.put("cepProprietario", cepProprietario);

			String municipioProprietario = parser.obterDadoParser(15).trim();
			linha.put("municipioProprietario", municipioProprietario);
			
			String numeroNisProprietario = parser.obterDadoParser(11).trim();
			linha.put("numeroNisProprietario", numeroNisProprietario);

			int matriculaResponsavel = 0;
			try {
				matriculaResponsavel = Integer.parseInt(parser.obterDadoParser(9));
			} catch (Exception e) {
				throw new MatriculaResponsavelException(e, String.valueOf(atualizacao.getImovelAtual().getMatricula()));
			}
			linha.put("matriculaResponsavel", ""+matriculaResponsavel);

			String nomeResponsavel = parser.obterDadoParser(50).trim();
			linha.put("nomeResponsavel", nomeResponsavel.toUpperCase());

			String tipoPessoaResponsavel = parser.obterDadoParser(1).trim();
			linha.put("tipoPessoaResponsavel", tipoPessoaResponsavel);

			String cnpjCpfResponsavel = parser.obterDadoParser(14).trim();
			linha.put("cnpjCpfResponsavel", cnpjCpfResponsavel);

			String rgResponsavel = parser.obterDadoParser(13).trim();
			linha.put("rgResponsavel", rgResponsavel);

			String ufRgResponsavel = parser.obterDadoParser(2).trim();
			linha.put("ufRgResponsavel", ufRgResponsavel);

			String sexoResponsavel = parser.obterDadoParser(1).trim();
			linha.put("sexoResponsavel", getSexoTipo(matriculaResponsavel, sexoResponsavel));
			
			String dddTelefoneResponsavel =  parser.obterDadoParser(IClienteFone.TAMANHO_DDD).trim();
			linha.put("dddTelefoneResponsavel", dddTelefoneResponsavel);
			
			String telefoneResponsavel = parser.obterDadoParser(IClienteFone.TAMANHO_TELEFONE).trim();
			linha.put("telefoneResponsavel", telefoneResponsavel);
			
			String dddCelularResponsavel =  parser.obterDadoParser(IClienteFone.TAMANHO_DDD).trim();
			linha.put("dddCelularResponsavel", dddCelularResponsavel);
				
			String celularResponsavel = parser.obterDadoParser(IClienteFone.TAMANHO_TELEFONE).trim();
			linha.put("celularResponsavel", celularResponsavel);				
				
			String emailResponsavel = parser.obterDadoParser(30).trim();
			linha.put("emailResponsavel", emailResponsavel);

			String tipoLogradouroResponsavel = parser.obterDadoParser(2).trim();
			linha.put("idTipoLogradouroResponsavel", tipoLogradouroResponsavel);

			if (StringUtils.isNotEmpty(tipoLogradouroResponsavel) && StringUtils.isNumeric(tipoLogradouroResponsavel) && Integer.valueOf(tipoLogradouroResponsavel) > 0){
				LogradouroTipo tipo = (LogradouroTipo) controladorUtil.obterPorId(LogradouroTipo.class, Integer.valueOf(tipoLogradouroResponsavel));
				linha.put("dsTipoLogradouroResponsavel", tipo.getDescricao());
			}
			
			String logradouroResponsavel = parser.obterDadoParser(40).trim();
			linha.put("logradouroResponsavel", logradouroResponsavel);

			String numeroResponsavel = parser.obterDadoParser(5).trim();
			linha.put("numeroResponsavel", numeroResponsavel);

			String complementoResponsavel = parser.obterDadoParser(25).trim();
			linha.put("complementoResponsavel", complementoResponsavel);

			String bairroResponsavel = parser.obterDadoParser(20).trim();
			linha.put("bairroResponsavel", bairroResponsavel);

			String cepResponsavel = parser.obterDadoParser(8).trim();
			linha.put("cepResponsavel", cepResponsavel);

			String municipioResponsavel = parser.obterDadoParser(15).trim();
			linha.put("municipioResponsavel", municipioResponsavel);
			
			String numeroNisResponsavel = parser.obterDadoParser(11).trim();
			linha.put("numeroNisResponsavel", numeroNisResponsavel);

			String latitude = parser.obterDadoParser(20).trim();
			linha.put("latitude", latitude);

			String longitude = parser.obterDadoParser(20).trim();
			linha.put("longitude", longitude);

			String data = parser.obterDadoParser(26).trim();
			linha.put("data", data);
			
			// Tratamento para CPF ou CNPJ parcial vindo do arquivo, 
			// ou seja, com 7 DIGITOS
			tratarCpfCnpjParcial(linha);
		}
	}
	
	@SuppressWarnings("unchecked")
	private String getSexoTipo(int matricula, String sexoTipo) throws Exception {
		if (sexoTipo.trim().equals("")) {
			Filtro filtro = new FiltroCliente();
			filtro.adicionarParametro(new ParametroSimples(FiltroCliente.ID, matricula));
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.SEXO);

			Cliente cliente = (Cliente) Util.retonarObjetoDeColecao((List<Cliente>) controladorUtil.pesquisar(filtro, Cliente.class.getName()));

			if (cliente != null && cliente.getPessoaSexo() != null) {
				sexoTipo = cliente.getPessoaSexo().getId().toString();
			} else {
				sexoTipo = "1";
			}
		}

		return sexoTipo;
	}
	
	private void tratarCpfCnpjParcial(Map<String, String> linhaCliente) {
		for (String tipoCliente : TIPOS_CLIENTE) {
			
			String cpfCnpj = linhaCliente.get(String.format("cnpjCpf%s", tipoCliente));
			
			if (StringUtils.isNotEmpty(cpfCnpj)) {
				
				if (cpfCnpj.length() == 7) { // Significa que houve transmissao parcial
					
					String matriculaCliente = linhaCliente.get(String.format("matricula%s", tipoCliente));
					
					if (StringUtils.isNotEmpty(matriculaCliente)) {
						try {
							Cliente cliente = RepositorioClienteHBM.getInstancia()
									.pesquisarCliente(Integer.parseInt(matriculaCliente));
							
							if (cliente != null) {
								if (cliente.getClienteTipo().ehPessoaFisica()) {
									if (StringUtils.isNotEmpty(cliente.getCpf()) && cliente.getCpf().startsWith(cpfCnpj)) {
										linhaCliente.put(String.format("cnpjCpf%s", tipoCliente), cliente.getCpf());
									}
								} else {
									if (StringUtils.isNotEmpty(cliente.getCnpj())
											&& cliente.getCnpj().startsWith(cpfCnpj)) {
										linhaCliente.put(String.format("cnpjCpf%s", tipoCliente), cliente.getCnpj());
									}
								}
							}

							linhaCliente.put(String.format("%s%s", INDICADOR_TRANSMISSAO_CPF_CNPJ, tipoCliente), "false");
						} catch (ErroRepositorioException e) {
							logger.error("Erro ao tentar buscar cpf/cnpf do cliente para comparar com informacao parcial da transmissao", e);
						}
					}
				} else {
					// Significa que foi transmitido um CPF/CNPJ com mais de 7 digitos
					linhaCliente.put(String.format("%s%s", INDICADOR_TRANSMISSAO_CPF_CNPJ, tipoCliente), "true");
				}
			} else {
				// Significa que NAO foi transmitido CPF/CNPJ
				linhaCliente.put(String.format("%s%s", INDICADOR_TRANSMISSAO_CPF_CNPJ, tipoCliente), "false");
			}
		}
	}
}
