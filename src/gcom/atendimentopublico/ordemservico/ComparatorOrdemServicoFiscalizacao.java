package gcom.atendimentopublico.ordemservico;

import java.util.Comparator;

public class ComparatorOrdemServicoFiscalizacao implements Comparator<OrdemServico>{

	public int compare(OrdemServico o1, OrdemServico o2) {
		
		if(o1.getDataGeracao().before(o2.getDataGeracao())){
			return -1;
		}else{
			return +1;
		}
		
	}

}
