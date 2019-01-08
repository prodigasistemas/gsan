package gcom.seguranca.transacao;

import gcom.cadastro.atualizacaocadastral.bean.ColunaAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.gui.cadastro.atualizacaocadastral.AlteracaoImovelRelatorioAtualizacaoCadastral;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionHelper;
import gcom.gui.cadastro.atualizacaocadastral.ImovelRelatorioAtualizacaoCadastral;

public class AdicionaAlteracaoSituacaoEsgoto extends RelatorioConsultaAtualizacaoCadastralCallBack{
	public AdicionaAlteracaoSituacaoEsgoto(ImovelRelatorioAtualizacaoCadastral relatorio, FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro, ConsultarMovimentoAtualizacaoCadastralHelper imovel) {
		super(relatorio, filtro, imovel);
	}

	public void executa() {
		if (filtro.isAlteracaoSituacaoEsgoto() != null && filtro.isAlteracaoSituacaoEsgoto()){
			for (ColunaAtualizacaoCadastral coluna : imovel.getColunasAtualizacao()) {
				if (coluna.getNomeColuna().contains(TabelaColuna.NOME_COLUNA_ESGOTO)){
					AlteracaoImovelRelatorioAtualizacaoCadastral alteracao = 
							new AlteracaoImovelRelatorioAtualizacaoCadastral("Alteração de Situação de Esgoto", coluna.getValorAnterior(), coluna.getValorTransmitido());
					relatorio.addAlteracao(alteracao);
					break;
				}
			}
		}
	}
}
