package gcom.cadastro.atualizacaocadastral.command;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.micromedicao.hidrometro.FiltroHidrometroProtecao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ParseMedidorCommand extends AbstractAtualizacaoCadastralCommand {

	public ParseMedidorCommand(ParserUtil parser, IRepositorioCadastro repositorioCadastro, ControladorUtilLocal controladorUtil, 
			ControladorTransacaoLocal controladorTransacao, IRepositorioImovel repositorioImovel, ControladorEnderecoLocal controladorEndereco,
			ControladorAtualizacaoCadastralLocal controladorAtualizacaoCadastral, ControladorClienteLocal controladorCliente) {
		super(parser, repositorioCadastro, controladorUtil, controladorTransacao, repositorioImovel, controladorEndereco,
				controladorAtualizacaoCadastral, controladorCliente);
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

			validarCampos(atualizacao);
		}else{
			parser.obterDadoParser(16).trim();
		}
	}
	
	private void validarCampos(AtualizacaoCadastral atualizacao) {
		AtualizacaoCadastralImovel imovel = atualizacao.getImovelAtual();
		Map<String, String> linha = imovel.getLinhaMedidor();
		
		validarValorNumeroHidrometro(imovel, linha);		
		validarTipoCaixaProtecaoHidrometro(imovel, linha);
	}

	private void validarValorNumeroHidrometro(AtualizacaoCadastralImovel imovel, Map<String, String> linha) {
		String numeroHidrometro = linha.get("numeroHidrometro");
		if(StringUtils.isEmpty(numeroHidrometro)) {
			imovel.addMensagemErro("Número do hidrômetro não está preenchido");
		}
	}

	private void validarTipoCaixaProtecaoHidrometro(AtualizacaoCadastralImovel imovel, Map<String, String> linha) {
		String tipoCaixaProtecao = linha.get("tipoCaixaProtecaoHidrometro");
		if(StringUtils.isEmpty(tipoCaixaProtecao)){
			imovel.addMensagemErro("Tipo de caixa de proteção inválida");
		} else {
			FiltroHidrometroProtecao filtro = new FiltroHidrometroProtecao();
			filtro.adicionarParametro(new ParametroSimples(FiltroHidrometroProtecao.ID, Integer.parseInt(tipoCaixaProtecao)));
			try {
				Collection<HidrometroProtecao> colecaohidrometroProtecao = controladorUtil.pesquisar(filtro, HidrometroProtecao.class.getName());
				if(colecaohidrometroProtecao.isEmpty())
					imovel.addMensagemErro("Tipo de caixa de proteção inexistente");
			} catch (ControladorException e) {
				e.printStackTrace();
			}
		}
	}
}
