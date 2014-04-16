package gcom.seguranca.transacao;

import gcom.cadastro.atualizacaocadastral.bean.ColunaAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.gui.cadastro.atualizacaocadastral.AlteracaoImovelRelatorioAtualizacaoCadastral;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionHelper;
import gcom.gui.cadastro.atualizacaocadastral.ImovelRelatorioAtualizacaoCadastral;

public class AdicionaAlteracaoSubCategoria extends RelatorioConsultaAtualizacaoCadastralCallBack{
	public AdicionaAlteracaoSubCategoria(ImovelRelatorioAtualizacaoCadastral relatorio, FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro, ConsultarMovimentoAtualizacaoCadastralHelper imovel) {
		super(relatorio, filtro, imovel);
	}

	public void executa() {
		if (filtro.isAlteracaoCategoria() != null && filtro.isAlteracaoCategoria()){
			for (ColunaAtualizacaoCadastral coluna : imovel.getColunasAtualizacao()) {
				if (coluna.getNomeColuna().contains(TabelaColuna.NOME_COLUNA_ECONOMIAS)){
					AlteracaoImovelRelatorioAtualizacaoCadastral alteracao = 
							new AlteracaoImovelRelatorioAtualizacaoCadastral("Quantidade de economias - " + coluna.getComplemento(), coluna.getValorAnterior(), coluna.getValorAtual());
					relatorio.addAlteracao(alteracao);
				}
			}
		}
	}
}
