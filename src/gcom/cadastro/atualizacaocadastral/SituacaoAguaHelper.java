package gcom.cadastro.atualizacaocadastral;

import gcom.cadastro.atualizacaocadastral.bean.DadosTabelaAtualizacaoCadastralHelper;
import gcom.seguranca.transacao.TabelaColuna;

public class SituacaoAguaHelper extends DadosTabelaAtualizacaoCadastralHelper{
	private static final long serialVersionUID = -8744374677845523310L;

	public SituacaoAguaHelper(String situacao) {
		super.setColunaValorAnterior(situacao);
		super.setColunaValorAtual(situacao);
		super.setDescricaoTabela("Imovel");
		super.setDescricaoColuna("Situacao Ligacao Agua");
		super.setNomeColuna(TabelaColuna.NOME_COLUNA_AGUA);
		super.setInformativo(true);
		super.setPosicao((short) 0);
	}
}
