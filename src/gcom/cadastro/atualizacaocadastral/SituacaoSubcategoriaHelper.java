package gcom.cadastro.atualizacaocadastral;

import gcom.cadastro.atualizacaocadastral.bean.DadosTabelaAtualizacaoCadastralHelper;
import gcom.seguranca.transacao.TabelaColuna;

public class SituacaoSubcategoriaHelper extends DadosTabelaAtualizacaoCadastralHelper{
	private static final long serialVersionUID = -8744374677845523310L;

	public SituacaoSubcategoriaHelper(String situacao, String subcategoria) {
		super.setColunaValorAnterior(situacao);
		//super.setColunaValorAtual(situacao);
		super.setDescricaoTabela("Subcategoria " + subcategoria);
		super.setDescricaoColuna("Quantidade de economias");
		super.setComplemento(subcategoria);
		super.setNomeColuna(TabelaColuna.NOME_COLUNA_ECONOMIAS);
		super.setInformativo(true);
		super.setPosicao((short) 2);		
	}
}
