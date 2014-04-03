package gcom.cadastro.atualizacaocadastral.command;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorTamanhoLinhaMedidorCommand;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;

import java.util.Map;

public class ParseMedidorCommand extends AbstractAtualizacaoCadastralCommand {

	public ParseMedidorCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil, 
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, ControladorEnderecoLocal controladorEndereco,
			ControladorAtualizacaoCadastralLocal controladorAtualizacaoCadastral, ControladorClienteLocal controladorCliente) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorEndereco,
				controladorAtualizacaoCadastral, controladorCliente);
	}

	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		Map<String, String> linha = atualizacao.getImovelAtual().getLinhaMedidor();
		AtualizacaoCadastralImovel imovel = atualizacao.getImovelAtual();

		String icImovelPossuiMedidor = parser.obterDadoParser(1);
		
		String numeroHidrometro = null;
		String marcaHidrometro = null;
		String capacidadeHidrometro = null;
		String tipoCaixaProtecaoHidrometro = null;
		
		new ValidadorTamanhoLinhaMedidorCommand(parser, imovel, linha).execute();
		
		if (!imovel.isErroLayout()){
			atualizacao.getImovelAtual().setExisteMedidor(icImovelPossuiMedidor.equals("1") ? true : false);
			
			if(icImovelPossuiMedidor.equals("1")){
				numeroHidrometro = parser.obterDadoParser(10).trim();
				linha.put("numeroHidrometro", numeroHidrometro);
				
				marcaHidrometro = parser.obterDadoParser(2).trim();
				linha.put("marcaHidrometro", marcaHidrometro);
				
				capacidadeHidrometro = parser.obterDadoParser(2).trim();
				linha.put("capacidadeHidrometro", capacidadeHidrometro);
				
				tipoCaixaProtecaoHidrometro = parser.obterDadoParser(2).trim();
				linha.put("tipoCaixaProtecaoHidrometro", tipoCaixaProtecaoHidrometro);
				
				String latitude = parser.obterDadoParser(20).trim();
				linha.put("latitude", latitude);
				
				String longitude = parser.obterDadoParser(20).trim();
				linha.put("longitude", longitude);
				
				String dataServico = parser.obterDadoParser(26).trim();
				linha.put("dataServico", dataServico);
			} else{
				parser.obterDadoParser(16).trim();
			}
		}
	}
}
