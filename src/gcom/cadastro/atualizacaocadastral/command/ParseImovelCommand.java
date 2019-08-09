package gcom.cadastro.atualizacaocadastral.command;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import gcom.atualizacaocadastral.IControladorAtualizacaoCadastral;
import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorTamanhoLinhaImovelCommand;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.util.CollectionUtil;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;
import gcom.util.Util;

public class ParseImovelCommand extends AbstractAtualizacaoCadastralCommand {

	private IControladorAtualizacaoCadastral controladorAtualizacaoCadastral;
	
	public ParseImovelCommand(
			ParserUtil parser, 
			ControladorUtilLocal controladorUtil, 
			IControladorAtualizacaoCadastral controladorAtualizacaoCadastral) {
		super(parser);
		this.controladorUtil = controladorUtil;
		this.controladorAtualizacaoCadastral = controladorAtualizacaoCadastral;
	}
	
	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		Map<String, String> linha = atualizacao.getImovelAtual().getLinhaImovel();
		AtualizacaoCadastralImovel imovel = atualizacao.getImovelAtual(); 
		
		new ValidadorTamanhoLinhaImovelCommand(parser, imovel, linha).execute();
		
		if(!imovel.isErroLayout()) {
			linha.put("matricula", parser.obterDadoParserTrim(9));

			String tipoOperacao = parser.obterDadoParserTrim(1);
			linha.put("tipoOperacao", tipoOperacao);
			
			if (!isImovelNovo(atualizacao.getImovelAtual(), tipoOperacao)) {
				verificarImovel(atualizacao);
			}
			
			linha.put("codigoCliente",   parser.obterDadoParser(30).trim());
			linha.put("inscricao",       parser.obterDadoParser(17).trim());
			linha.put("rota",            parser.obterDadoParser(2).trim());
			linha.put("face",            parser.obterDadoParser(2).trim());
			linha.put("codigoMunicipio", parser.obterDadoParser(8).trim());

			String numeroIPTU = parser.obterDadoParser(31).trim();
			linha.put("numeroIPTU", numeroIPTU.trim().equals("") ? null : numeroIPTU);
			
			linha.put("numeroCelpa", 		parser.obterDadoParser(20).trim());
			linha.put("numeroPontosUteis",	parser.obterDadoParser(5).trim());
			linha.put("numeroOcupantes",	parser.obterDadoParser(5).trim());
			
			String idTipoLogradouroImovel = parser.obterDadoParser(2).trim();
			
			linha.put("idTipoLogradouroImovel",        idTipoLogradouroImovel);
			
			if (StringUtils.isNotEmpty(idTipoLogradouroImovel) && StringUtils.isNumeric(idTipoLogradouroImovel) && Integer.valueOf(idTipoLogradouroImovel) > 0){
				LogradouroTipo tipo = (LogradouroTipo) controladorUtil.obterPorId(LogradouroTipo.class, Integer.valueOf(idTipoLogradouroImovel));
				linha.put("dsTipoLogradouroImovel", tipo.getDescricao());
			}
			
			linha.put("logradouroImovel",              parser.obterDadoParser(40).trim());
			linha.put("numeroImovel",                  parser.obterDadoParser(5).trim());
			linha.put("complementoImovel",             parser.obterDadoParser(25).trim());
			linha.put("bairro",                        parser.obterDadoParser(20).trim());
			linha.put("cep",                           parser.obterDadoParser(8).trim());
			linha.put("municipio",                     parser.obterDadoParser(15).trim());
			linha.put("codigoLogradouro",              parser.obterDadoParser(9).trim());
			linha.put("subcategoriaR1",                parser.obterDadoParser(3).trim());
			linha.put("subcategoriaR2",                parser.obterDadoParser(3).trim());
			linha.put("subcategoriaR3",                parser.obterDadoParser(3).trim());
			linha.put("subcategoriaR4",                parser.obterDadoParser(3).trim());
			linha.put("subcategoriaC1",                parser.obterDadoParser(3).trim());
			linha.put("subcategoriaC2",                parser.obterDadoParser(3).trim());
			linha.put("subcategoriaC3",                parser.obterDadoParser(3).trim());
			linha.put("subcategoriaC4",                parser.obterDadoParser(3).trim());
			linha.put("subcategoriaP1",                parser.obterDadoParser(3).trim());
			linha.put("subcategoriaP2",                parser.obterDadoParser(3).trim());
			linha.put("subcategoriaP3",                parser.obterDadoParser(3).trim());
			linha.put("subcategoriaP4",                parser.obterDadoParser(3).trim());
			linha.put("subcategoriaI1",                parser.obterDadoParser(3).trim());
			linha.put("subcategoriaI2",                parser.obterDadoParser(3).trim());
			linha.put("subcategoriaI3",                parser.obterDadoParser(3).trim());
			linha.put("subcategoriaI4",                parser.obterDadoParser(3).trim());
			linha.put("fonteAbastecimento",            parser.obterDadoParser(2).trim());
			linha.put("areaConstruida",                parser.obterDadoParserTrim(10));
			linha.put("classeSocial",                  parser.obterDadoParserTrim(1));
			linha.put("quantidadeAnimaisDomesticos",   parser.obterDadoParserTrim(4));
			linha.put("volCisterna",                   parser.obterDadoParserTrim(7));
			linha.put("volPiscina",                    parser.obterDadoParserTrim(7));
			linha.put("volCxDagua",                    parser.obterDadoParser(7));
			linha.put("tipoUso",                       parser.obterDadoParserTrim(1));
			linha.put("acessoHidrometro",              parser.obterDadoParserTrim(1));
			linha.put("tipoOcupanteCriancas",          parser.obterDadoParserTrim(4));
			linha.put("tipoOcupanteAdultos",           parser.obterDadoParserTrim(4));
			linha.put("tipoOcupanteIdosos",            parser.obterDadoParserTrim(4));
			linha.put("tipoOcupanteEmpregados",        parser.obterDadoParserTrim(4));
			linha.put("tipoOcupanteAlunos",            parser.obterDadoParserTrim(4));
			linha.put("tipoOcupanteCaes",              parser.obterDadoParserTrim(4));
			linha.put("tipoOcupanteOutros",            parser.obterDadoParserTrim(4));
			linha.put("quantidadeEconomiasSocial",     parser.obterDadoParserTrim(3));
			linha.put("quantidadeEconomiasOutros",     parser.obterDadoParserTrim(3));
			linha.put("percentualAbastecimento",       parser.obterDadoParserTrim(3));
			linha.put("latitude",                      parser.obterDadoParser(20).trim());
			linha.put("longitude",                     parser.obterDadoParser(20).trim());
			linha.put("data",                          parser.obterDadoParser(26).trim());
			
			if(parser.getFonte().length() == 545)
				linha.put("obsersacaoCategoria", 	   parser.obterDadoParserTrim(100));
		}
	}

	private boolean isImovelNovo(AtualizacaoCadastralImovel imovelAtual, String tipoOperacao) {
		if (isInclusao(tipoOperacao)) {
			imovelAtual.setImovelNovo(true);
			imovelAtual.setIdentificacaoImovelNovo(imovelAtual.getMatricula());
			imovelAtual.setMatricula(ConstantesSistema.ZERO);
			return true;
		} else {
			imovelAtual.setImovelNovo(false);
			return false;
		}
	}

	private boolean isInclusao(String tipoOperacao) {
		return Integer.valueOf(tipoOperacao) == AlteracaoTipo.INCLUSAO.intValue();
	}

	private void verificarImovel(AtualizacaoCadastral atualizacao) throws Exception {
		ImovelControleAtualizacaoCadastral controle = 
				controladorAtualizacaoCadastral.pesquisarImovelControleAtualizacao(atualizacao.getImovelAtual().getMatricula());

		if (controle != null && (controle.isPreAprovado() || controle.isAprovado() || controle.isAtualizado())) {
			atualizacao.getImovelAtual().addMensagemErro("Imóvel com situação 'PRE APROVADO', 'APROVADO' ou 'ATUALIZADO'");
			atualizacao.getImovelAtual().setImovelAprovado(true);
		}
	}
}
