package gcom.cobranca.contratoparcelamento;

import java.util.Comparator;

public class ComparatorParcela implements Comparator<QuantidadePrestacoes> {

	public int compare(QuantidadePrestacoes p1, QuantidadePrestacoes p2) {
		return  p1.getQtdFaturasParceladas() < p2.getQtdFaturasParceladas() ? -1: ( p1.getQtdFaturasParceladas() > p2.getQtdFaturasParceladas() ? +1 : 0);
	}

}
