package gcom.cadastro.atualizacaocadastral.command;

import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;

import java.util.Map;

public class ParseMedidorCommand extends AbstractAtualizacaoCadastralCommand {

	public ParseMedidorCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil, 
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, 
			ControladorImovelLocal controladorImovel, ControladorClienteLocal controladorCliente) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorImovel, controladorCliente);
	}

	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		Map<String, String> linha = atualizacao.getImovelAtual().getLinhaMedidor();

		String matriculaImovelMedidor = parser.obterDadoParser(9);
		
		String icImovelPossuiMedidor = parser.obterDadoParser(1);
		
		String numeroHidrometro = null;
		String marcaHidrometro = null;
		String capacidadeHidrometro = null;
		String tipoCaixaProtecaoHidrometro = null;
		
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
		}else{
			parser.obterDadoParser(16).trim();
		}
	}
}
