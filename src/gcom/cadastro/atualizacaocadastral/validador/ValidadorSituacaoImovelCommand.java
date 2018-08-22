package gcom.cadastro.atualizacaocadastral.validador;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.atualizacaocadastral.IRepositorioAtualizacaoCadastral;
import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

public class ValidadorSituacaoImovelCommand extends ValidadorCommand {
	
	private ControladorAtualizacaoCadastralLocal controlador;
	
	public ValidadorSituacaoImovelCommand(AtualizacaoCadastralImovel cadastroImovel,
			ControladorAtualizacaoCadastralLocal controlador) {
		super(cadastroImovel);
		
		this.controlador = controlador;
	}

	@Override
	public void execute() throws Exception {

		ImovelControleAtualizacaoCadastral imovelControle = controlador.obterImovelControle(cadastroImovel.getMatricula());
		
		if (cadastroImovel.getAtualizacaoArquivo().getArquivoTexto().isArquivoRetornoTransmissao() 
				&& !imovelValidoTransmissao(imovelControle)) {
			cadastroImovel.addMensagemErro("Tipo de retorno inválido. Imóvel não está transmitido.");
		}
		
		
		if (cadastroImovel.getAtualizacaoArquivo().getArquivoTexto().isArquivoRetornoRevisao() 
				&& !imovelValidoRevisao(imovelControle)) {
			cadastroImovel.addMensagemErro("Tipo de retorno inválido. Imóvel não está em revisão.");
		}
		
		if (cadastroImovel.getAtualizacaoArquivo().getArquivoTexto().isArquivoRetornoFiscalizacao() 
				&& !imovelValidoFiscalizacao(imovelControle)) {
			cadastroImovel.addMensagemErro("Tipo de retorno inválido. Imóvel não está em fiscalização.");
		}
	}
	
	private boolean imovelValidoTransmissao(ImovelControleAtualizacaoCadastral imovelControle) {
		return (cadastroImovel.getAtualizacaoArquivo().getArquivoTexto().isArquivoRetornoTransmissao()
				&& imovelControle.getSituacaoAtualizacaoCadastral().getId()
						.equals(SituacaoAtualizacaoCadastral.TRANSMITIDO));
	}
	
	private boolean imovelValidoRevisao(ImovelControleAtualizacaoCadastral imovelControle) {
		return (cadastroImovel.getAtualizacaoArquivo().getArquivoTexto().isArquivoRetornoRevisao()
				&& imovelControle.getSituacaoAtualizacaoCadastral().getId()
						.equals(SituacaoAtualizacaoCadastral.EM_REVISAO));
	}
	
	private boolean imovelValidoFiscalizacao(ImovelControleAtualizacaoCadastral imovelControle) {
		System.out.println("--> " + imovelControle.getId());
		return (cadastroImovel.getAtualizacaoArquivo().getArquivoTexto().isArquivoRetornoFiscalizacao()
				&& imovelControle.getSituacaoAtualizacaoCadastral().getId()
						.equals(SituacaoAtualizacaoCadastral.EM_FISCALIZACAO));
	}
}
