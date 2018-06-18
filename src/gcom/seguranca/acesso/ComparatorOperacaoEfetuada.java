package gcom.seguranca.acesso;

import java.util.Comparator;

public class ComparatorOperacaoEfetuada  implements Comparator<OperacaoEfetuada>{

	public int compare(OperacaoEfetuada op1, OperacaoEfetuada op2) {
		return op1.getUltimaAlteracao().before(op2.getUltimaAlteracao()) ? 
				-1 : (op1.getUltimaAlteracao().after(op2.getUltimaAlteracao()) ? +1 : 0);
	}

}
