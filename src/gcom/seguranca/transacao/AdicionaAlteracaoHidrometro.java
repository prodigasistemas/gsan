package gcom.seguranca.transacao;

import gcom.cadastro.atualizacaocadastral.bean.ColunaAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.gui.cadastro.atualizacaocadastral.AlteracaoImovelRelatorioAtualizacaoCadastral;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionHelper;
import gcom.gui.cadastro.atualizacaocadastral.ImovelRelatorioAtualizacaoCadastral;

public class AdicionaAlteracaoHidrometro extends RelatorioConsultaAtualizacaoCadastralCallBack{
	public AdicionaAlteracaoHidrometro(ImovelRelatorioAtualizacaoCadastral relatorio, FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro, ConsultarMovimentoAtualizacaoCadastralHelper imovel) {
		super(relatorio, filtro, imovel);
	}

	public void executa() {
		if (filtro.isAlteracaoHidrometro() != null && filtro.isAlteracaoHidrometro()){
			for (ColunaAtualizacaoCadastral coluna : imovel.getColunasAtualizacao()) {
				if (coluna.getNomeColuna().contains(TabelaColuna.NOME_COLUNA_HIDROMETRO)){
					AlteracaoImovelRelatorioAtualizacaoCadastral alteracao = 
							new AlteracaoImovelRelatorioAtualizacaoCadastral("Alteração de Hidrômetro", coluna.getValorAnterior(), coluna.getValorAtual());
					relatorio.addAlteracao(alteracao);
					break;
				}
			}
		}
	}
}
