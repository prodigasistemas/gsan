package gcom.cadastro.atualizacaocadastral.command;

import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.cadastro.imovel.ImovelControleAtualizacaoCadastral;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;
import gcom.util.Util;
import gcom.util.exception.MatriculaProprietarioException;
import gcom.util.exception.MatriculaResponsavelException;
import gcom.util.exception.MatriculaUsuarioException;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

public class ParseClienteCommand extends AbstractAtualizacaoCadastralCommand {
	
	private static Logger logger = Logger.getLogger(ParseClienteCommand.class);
	
	public ParseClienteCommand() {
		super();
	}

	public ParseClienteCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil, 
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, 
			ControladorImovelLocal controladorImovel, ControladorClienteLocal controladorCliente) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorImovel, controladorCliente);
	}

	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		Map<String, String> linha = atualizacao.getImovelAtual().getLinhaCliente();
		
		String matriculaImovelCliente = parser.obterDadoParser(9).trim();
		linha.put("matriculaImovelCliente", matriculaImovelCliente);
		
		atualizacao.getImovelAtual().setMatricula(Integer.valueOf(matriculaImovelCliente));
		
		logger.info("Carregando Imóvel: " + Integer.parseInt(matriculaImovelCliente));
		
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
		
		String rgUsuario = parser.obterDadoParser(9).trim();
		linha.put("rgUsuario", rgUsuario);
		
		String ufRgUsuario = parser.obterDadoParser(2).trim();
		linha.put("ufRgUsuario", ufRgUsuario);
		
		String sexoUsuario = parser.obterDadoParser(1).trim();
		linha.put("sexoUsuario", sexoUsuario);
		
		String telefoneUsuario = parser.obterDadoParser(10).trim();
		linha.put("telefoneUsuario", telefoneUsuario);
		
		String celularUsuario = parser.obterDadoParser(10).trim();
		linha.put("celularUsuario", celularUsuario);
		
		String emailUsuario = parser.obterDadoParser(30).trim();
		linha.put("emailUsuario", emailUsuario);
		
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
		
		String rgProprietario = parser.obterDadoParser(9).trim();
		linha.put("rgProprietario", rgProprietario);
		
		String ufRgProprietario = parser.obterDadoParser(2).trim();
		linha.put("ufRgProprietario", ufRgProprietario);
		
		String sexoProprietario = parser.obterDadoParser(1).trim();
		linha.put("sexoProprietario", sexoProprietario);
		
		String telefoneProprietario = parser.obterDadoParser(10).trim();
		linha.put("telefoneProprietario", telefoneProprietario);
		
		String celularProprietario = parser.obterDadoParser(10).trim();
		linha.put("celularProprietario", celularProprietario);
		
		String emailProprietario = parser.obterDadoParser(30).trim();
		linha.put("emailProprietario", emailProprietario);
		
		String tipoLogradouroProprietario = parser.obterDadoParser(2).trim();
		linha.put("idTipoLogradouroProprietario", tipoLogradouroProprietario);
		
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
		
		String rgResponsavel = parser.obterDadoParser(9).trim();
		linha.put("rgResponsavel", rgResponsavel);
		
		String ufRgResponsavel = parser.obterDadoParser(2).trim();
		linha.put("ufRgResponsavel", ufRgResponsavel);
		
		String sexoResponsavel = parser.obterDadoParser(1).trim();
		linha.put("sexoResponsavel", sexoResponsavel);
		
		String telefoneResponsavel = parser.obterDadoParser(10).trim();
		linha.put("telefoneResponsavel", telefoneResponsavel);
		
		String celularResponsavel = parser.obterDadoParser(10).trim();
		linha.put("celularResponsavel", celularResponsavel);
		
		String emailResponsavel = parser.obterDadoParser(30).trim();
		linha.put("emailResponsavel", emailResponsavel);
		
		String tipoLogradouroResponsavel = parser.obterDadoParser(2).trim();
		linha.put("idTipoLogradouroResponsavel", tipoLogradouroResponsavel);
		
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
			
		String latitude = parser.obterDadoParser(20).trim();
		linha.put("latitude", latitude);
		
		String longitude = parser.obterDadoParser(20).trim();
		linha.put("longitude", longitude);
		
		String data = parser.obterDadoParser(26).trim();
		linha.put("data", data);
		
		validaCampos(atualizacao);		
	}
	
	private void validaCampos(AtualizacaoCadastral atualizacao) throws Exception {
		Map<String, String> linha = atualizacao.getImovelAtual().getLinhaCliente();
		
		ImovelControleAtualizacaoCadastral imovelControleAtualizacaoCadastral = repositorioImovel.pesquisarImovelControleAtualizacaoCadastral(atualizacao.getImovelAtual().getMatricula());
		
		if (imovelControleAtualizacaoCadastral != null && imovelControleAtualizacaoCadastral.getSituacaoAtualizacaoCadastral().getId() == SituacaoAtualizacaoCadastral.APROVADO){
			atualizacao.getImovelAtual().addMensagemErro("Imóvel com situação 'APROVADO'");
			atualizacao.getImovelAtual().setImovelAprovado(true);
		}

		if (Util.nomeInvalido(linha.get("nomeUsuario"))){
			atualizacao.getImovelAtual().addMensagemErro("Nome de usuário inválido.");
		}
		
		if (Util.nomeInvalido(linha.get("nomeResponsavel"))){
			atualizacao.getImovelAtual().addMensagemErro("Nome de responsável inválido.");
		}

		if (Util.nomeInvalido(linha.get("nomeProprietario"))){
			atualizacao.getImovelAtual().addMensagemErro("Nome de proprietário inválido.");
		}
		
		if (Util.cpfCnpjInvalido(linha.get("cnpjCpfUsuario"))){
			atualizacao.getImovelAtual().addMensagemErro("CPF/CNPJ de usuário inválido.");
			limparDadosUsuario(linha);
		}
		
		if (Util.cpfCnpjInvalido(linha.get("cnpjCpfProprietario"))){
			atualizacao.getImovelAtual().addMensagemErro("CPF/CNPJ de proprietário inválido.");
		}

		if (Util.cpfCnpjInvalido(linha.get("cnpjCpfResponsavel"))){
			atualizacao.getImovelAtual().addMensagemErro("CPF/CNPJ de responsável inválido.");
		}
		
		if (StringUtils.isEmpty(linha.get("latitude"))){
			atualizacao.getImovelAtual().addMensagemErro("Latitude nao informada");
		}
		
		if (StringUtils.isEmpty(linha.get("longitude"))){
			atualizacao.getImovelAtual().addMensagemErro("Longitude nao informada");
		}
	}
	
	private void limparDadosUsuario(Map<String, String> linha) {
		linha.put("matriculaUsuario", "");
		linha.put("nomeUsuario", "");
		linha.put("tipoPessoaUsuario", "");
		linha.put("cnpjCpfUsuario", "");
		linha.put("rgUsuario", "");
		linha.put("ufRgUsuario", "");
		linha.put("sexoUsuario", "");
		linha.put("telefoneUsuario", "");
		linha.put("celularUsuario", "");
		linha.put("emailUsuario", "");
	}
}
