package gcom.cadastro.atualizacaocadastral.command;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorTamanhoLinhaRamoAtividadeCommand;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;

import java.util.Map;

public class ParseRamoAtividadeCommand extends AbstractAtualizacaoCadastralCommand {

	public ParseRamoAtividadeCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil, 
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, ControladorEnderecoLocal controladorEndereco,
			ControladorAtualizacaoCadastralLocal controladorImovel, ControladorClienteLocal controladorCliente) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorEndereco, controladorImovel, controladorCliente);
	}

	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		Map<String, String> linha = atualizacao.getImovelAtual().getLinhaRamoAtividade();
		AtualizacaoCadastralImovel imovel = atualizacao.getImovelAtual();
		
		new ValidadorTamanhoLinhaRamoAtividadeCommand(parser, imovel, linha).execute();
		if(!imovel.isErroLayout()) {

			String matriculaImovelRamoAtividade = parser.obterDadoParser(9).trim();
			linha.put("matriculaImovelRamoAtividade", matriculaImovelRamoAtividade);

			String ramoAtividade = parser.obterDadoParser(3).trim();
			linha.put("ramoAtividade", ramoAtividade);
			
			adicionarRamoAtividade(linha, imovel);
		}
	}

	private void adicionarRamoAtividade(Map<String, String> linha,
			AtualizacaoCadastralImovel imovel) throws NumberFormatException {
		
		int idRamoAtividade = Integer.parseInt(linha.get("ramoAtividade"));
		
		if (idRamoAtividade >= 0) {
			DadoAtualizacaoRamoAtividade ramo = new DadoAtualizacaoRamoAtividade();
			ramo.setId(idRamoAtividade);
			imovel.addDadoRamoAtividade(ramo);
		}
	}
}
