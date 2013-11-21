package gcom.cobranca.contratoparcelamento;

import java.util.Comparator;

public class ComparatorPrestacaoContratoParcelamento implements Comparator<PrestacaoContratoParcelamento> {

	public int compare(PrestacaoContratoParcelamento p1, PrestacaoContratoParcelamento p2) {
		return  p1.getNumero() < p2.getNumero() ? -1: ( p1.getNumero() > p2.getNumero() ? +1 : 0);
	}

}
