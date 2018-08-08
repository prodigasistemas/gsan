package gcom.cadastro.atualizacaocadastral;

import gcom.cadastro.atualizacaocadastral.bean.DadosTabelaAtualizacaoCadastralHelper;
import gcom.seguranca.transacao.TabelaColuna;

public class SituacaoEsgotoHelper extends DadosTabelaAtualizacaoCadastralHelper{
	private static final long serialVersionUID = -8744374677845523310L;

	public SituacaoEsgotoHelper(String situacao) {
		super.setColunaValorAnterior(situacao);
		//super.setColunaValorAtual(situacao);
		super.setDescricaoTabela("Imovel");
		super.setDescricaoColuna("Situacao Ligacao Esgoto");
		super.setNomeColuna(TabelaColuna.NOME_COLUNA_ESGOTO);
		super.setInformativo(true);
		super.setPosicao((short) 1);		
	}
}
