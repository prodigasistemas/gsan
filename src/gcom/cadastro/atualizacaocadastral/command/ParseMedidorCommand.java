package gcom.cadastro.atualizacaocadastral.command;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorTamanhoLinhaMedidorCommand;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;

public class ParseMedidorCommand extends AbstractAtualizacaoCadastralCommand {

	public ParseMedidorCommand(ParserUtil parser,
			IRepositorioCadastro repositorioCadastro,
			ControladorUtilLocal controladorUtil,
			ControladorTransacaoLocal controladorTransacao,
			IRepositorioImovel repositorioImovel,
			ControladorEnderecoLocal controladorEndereco,
			ControladorAtualizacaoCadastralLocal controladorAtualizacaoCadastral,
			ControladorClienteLocal controladorCliente) {
		
		super(parser,
			  repositorioCadastro,
			  controladorUtil,
			  controladorTransacao,
			  repositorioImovel,
			  controladorEndereco,
			  controladorAtualizacaoCadastral,
			  controladorCliente);
	}

	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		
		Map<String, String> linha = atualizacao.getImovelAtual().getLinhaMedidor();
		AtualizacaoCadastralImovel imovel = atualizacao.getImovelAtual();

		new ValidadorTamanhoLinhaMedidorCommand(parser, imovel, linha).execute();

		if (!imovel.isErroLayout()) {

			String matriculaImovel = parser.obterDadoParser(9).trim();
			linha.put("matriculaImovel", matriculaImovel);

			short indicadorImovelPossuiMedidor = Short.valueOf(parser.obterDadoParser(1));
			
			String numeroHidrometro = parser.obterDadoParser(10).trim();
			linha.put("numeroHidrometro", numeroHidrometro);

			if (indicadorImovelPossuiMedidor == ConstantesSistema.SIM && StringUtils.isNotEmpty(numeroHidrometro)) {

				String marcaHidrometro = parser.obterDadoParser(2).trim();
				linha.put("marcaHidrometro", marcaHidrometro);

				String capacidadeHidrometro = parser.obterDadoParser(2).trim();
				linha.put("capacidadeHidrometro", capacidadeHidrometro);

				String tipoCaixaProtecaoHidrometro = parser.obterDadoParser(2).trim();
				linha.put("tipoCaixaProtecaoHidrometro", tipoCaixaProtecaoHidrometro);

				String latitude = parser.obterDadoParser(20).trim();
				linha.put("latitude", latitude);

				String longitude = parser.obterDadoParser(20).trim();
				linha.put("longitude", longitude);

				String dataServico = parser.obterDadoParser(26).trim();
				linha.put("dataServico", dataServico);
				
				String leituraHidrometro = parser.obterDadoParser(10).trim();
				linha.put("leituraHidrometro", leituraHidrometro);
			} else {
				indicadorImovelPossuiMedidor = ConstantesSistema.NAO;
				parser.obterDadoParser(16).trim();
			}
			
			atualizacao.getImovelAtual().setExisteMedidor(indicadorImovelPossuiMedidor == ConstantesSistema.SIM ? true : false);
		}
	}
}
