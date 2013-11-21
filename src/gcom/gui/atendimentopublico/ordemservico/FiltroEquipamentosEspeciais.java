package gcom.gui.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroEquipamentosEspeciais extends Filtro implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public FiltroEquipamentosEspeciais () {
		

	}

	public FiltroEquipamentosEspeciais (String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	 public final static String ID = "id"; 
	 public final static String DESCRICAO = "descricao";
	 public final static String DESCRICAOABREVIADA = "descricaoAbreviada";
	 public final static String INDICADORUSO = "indicadorUso";	  
	
}
