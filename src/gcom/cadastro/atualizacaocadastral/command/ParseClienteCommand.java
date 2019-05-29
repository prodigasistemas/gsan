package gcom.cadastro.atualizacaocadastral.command;

import gcom.atualizacaocadastral.IControladorAtualizacaoCadastral;
import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.validador.ValidadorTamanhoLinhaClienteCommand;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.util.ControladorUtilLocal;
import gcom.util.ParserUtil;
import gcom.util.Util;
import gcom.util.exception.MatriculaProprietarioException;
import gcom.util.exception.MatriculaResponsavelException;
import gcom.util.exception.MatriculaUsuarioException;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

public class ParseClienteCommand extends AbstractAtualizacaoCadastralCommand {
	
	private static Logger logger = Logger.getLogger(ParseClienteCommand.class);
	
	private IControladorAtualizacaoCadastral controladorAtualizacaoCadastral;
	
	public ParseClienteCommand(ParserUtil parser,
			ControladorUtilLocal controladorUtil,
			IRepositorioImovel repositorioImovel,
			IControladorAtualizacaoCadastral controladorAtualizacaoCadastral) {

		super(parser);

		this.controladorUtil = controladorUtil;
		this.repositorioImovel = repositorioImovel;
		this.controladorAtualizacaoCadastral = controladorAtualizacaoCadastral;
	}

	public void execute(AtualizacaoCadastral atualizacao) throws Exception {
		Map<String, String> linha = atualizacao.getImovelAtual().getLinhaCliente();
		AtualizacaoCadastralImovel imovelAtual = atualizacao.getImovelAtual();
		
		new ValidadorTamanhoLinhaClienteCommand(parser, imovelAtual, linha).execute();;
		String matriculaImovelCliente = parser.obterDadoParser(9).trim();
		atualizacao.getImovelAtual().setMatricula(Integer.valueOf(matriculaImovelCliente));
		
		if(!imovelAtual.isErroLayout()) {
			logger.info("Carregando Imovel: " + Integer.parseInt(matriculaImovelCliente));

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
			
			String telefoneUsuario = parser.obterDadoParser(IClienteFone.TAMANHO_TELEFONE).trim();
			linha.put("telefoneUsuario", telefoneUsuario);
			
			String celularUsuario = parser.obterDadoParser(IClienteFone.TAMANHO_TELEFONE).trim();
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

			String rgProprietario = parser.obterDadoParser(13).trim();
			linha.put("rgProprietario", rgProprietario);

			String ufRgProprietario = parser.obterDadoParser(2).trim();
			linha.put("ufRgProprietario", ufRgProprietario);

			String sexoProprietario = parser.obterDadoParser(1).trim();
			linha.put("sexoProprietario", getSexoTipo(matriculaProprietario, sexoProprietario));
		
			String telefoneProprietario = parser.obterDadoParser(IClienteFone.TAMANHO_TELEFONE).trim();
			linha.put("telefoneProprietario", telefoneProprietario);				
				
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
			
			String telefoneResponsavel = parser.obterDadoParser(IClienteFone.TAMANHO_TELEFONE).trim();
			linha.put("telefoneResponsavel", telefoneResponsavel);				
				
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

			String latitude = parser.obterDadoParser(20).trim();
			linha.put("latitude", latitude);

			String longitude = parser.obterDadoParser(20).trim();
			linha.put("longitude", longitude);

			String data = parser.obterDadoParser(26).trim();
			linha.put("data", data);

			verificarImovel(atualizacao, imovelAtual);
		}
	}
	
	private void verificarImovel(AtualizacaoCadastral atualizacao, AtualizacaoCadastralImovel imovelAtual) throws Exception {
		ImovelControleAtualizacaoCadastral imovelControleAtualizacaoCadastral = controladorAtualizacaoCadastral.pesquisarImovelControleAtualizacao(atualizacao.getImovelAtual().getMatricula());

		if (imovelControleAtualizacaoCadastral != null) {

			if (imovelControleAtualizacaoCadastral.isPreAprovado() 
					|| imovelControleAtualizacaoCadastral.isAprovado() 
					|| imovelControleAtualizacaoCadastral.isAtualizado()) {

				atualizacao.getImovelAtual().addMensagemErro("Imóvel com situação 'PRE APROVADO', 'APROVADO' ou 'ATUALIZADO'");
				atualizacao.getImovelAtual().setImovelAprovado(true);
			}
			
			if (atualizacao.getArquivoTexto().isArquivoRetornoTransmissao()) {
				controladorAtualizacaoCadastral.apagarInformacoesRetornoImovelAtualizacaoCadastral(atualizacao.getImovelAtual().getMatricula());
			}
			
		} else {
			if (atualizacao.getImovelAtual().getMatricula() > 0 && atualizacao.getArquivoTexto().isArquivoRetornoTransmissao()) {
				controladorAtualizacaoCadastral.apagarInformacoesRetornoImovelAtualizacaoCadastral(atualizacao.getImovelAtual().getMatricula());
			}
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
}
