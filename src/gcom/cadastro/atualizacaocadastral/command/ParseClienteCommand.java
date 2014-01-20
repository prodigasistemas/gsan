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

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ParseClienteCommand extends AbstractAtualizacaoCadastralCommand {

	public ParseClienteCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil, 
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, 
			ControladorImovelLocal controladorImovel, ControladorClienteLocal controladorCliente) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorImovel, controladorCliente);
	}

	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		Map<String, String> linha = atualizacao.getLinhaCliente();
		
		String matriculaImovelCliente = parser.obterDadoParser(9).trim();
		linha.put("matriculaImovelCliente", matriculaImovelCliente);
		
		atualizacao.setMatricula(matriculaImovelCliente);
		
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
		
		int matriculaUsuario = Integer.parseInt(parser.obterDadoParser(9));
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
		
		int matriculaProprietario = Integer.parseInt(parser.obterDadoParser(9));;
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
			
		int matriculaResponsavel = Integer.parseInt(parser.obterDadoParser(9));;
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
		Map<String, String> linha = atualizacao.getLinhaCliente();
		
		ImovelControleAtualizacaoCadastral imovelControleAtualizacaoCadastral = repositorioImovel.pesquisarImovelControleAtualizacaoCadastral(Integer.valueOf(atualizacao.getMatricula()));
		
		if (imovelControleAtualizacaoCadastral.getSituacaoAtualizacaoCadastral().getId() == SituacaoAtualizacaoCadastral.APROVADO){
			atualizacao.addMensagemErro("Imóvel com situação 'APROVADO'");
			atualizacao.setImovelAprovado(true);
		}

		if (nomeInvalido(linha.get("nomeUsuario"))){
			atualizacao.addMensagemErro("Nome de usuário inválido.");
		}
		
		if (nomeInvalido(linha.get("nomeResponsavel"))){
			atualizacao.addMensagemErro("Nome de responsável inválido.");
		}

		if (nomeInvalido(linha.get("nomeProprietario"))){
			atualizacao.addMensagemErro("Nome de proprietário inválido.");
		}
	}
	
	private boolean nomeInvalido(String nome){
		String regexNome = "[a-zA-Z\\s]*";
		if (StringUtils.isNotBlank(nome) && !nome.matches(regexNome)){
			return true;
		}
		return false;
		
	}
}
