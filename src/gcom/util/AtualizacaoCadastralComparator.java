package gcom.util;

import gcom.cadastro.atualizacaocadastral.bean.DadosTabelaAtualizacaoCadastralHelper;

import java.util.Comparator;

public class AtualizacaoCadastralComparator implements Comparator<DadosTabelaAtualizacaoCadastralHelper> {

	public int compare(DadosTabelaAtualizacaoCadastralHelper arg0, DadosTabelaAtualizacaoCadastralHelper arg1) {
		return arg0.getPosicao().compareTo(arg1.getPosicao());
	}
}
