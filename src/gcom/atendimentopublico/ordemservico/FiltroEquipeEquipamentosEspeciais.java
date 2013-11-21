package gcom.atendimentopublico.ordemservico;
	
	import java.io.Serializable;

	import gcom.util.filtro.Filtro;

	/**
	 * Classe que representa o filtro de equipe equipamentos especiais
	 * 
	 * @author Nathalia Santos
	 * @date 20/06/2011
	 */
	public class FiltroEquipeEquipamentosEspeciais extends Filtro implements Serializable {
		
		private static final long serialVersionUID = 1L;

		/**
		 * Construtor Default
		 */
		public FiltroEquipeEquipamentosEspeciais () {
		}

		/**
		 * Construtor passando order by
		 * 
		 * @param campoOrderBy
		 */
		public FiltroEquipeEquipamentosEspeciais (String campoOrderBy) {
			this.campoOrderBy = campoOrderBy;
		}

		/**
		 * Id da equipe
		 */
		public final static String ID = "id";
		
		public final static String ID_EQUIPE_EQUIPAMENTO_ESPECIAIS = "equipeEquipamentosEspeciais.id";
		
		public final static String QUANTIDADE = "quantidade";
		
		public final static String TULTIMAALTERACAO = "equipe.dataHora";
		
		public final static String ID_EQUIPE = "equipe.id";
		

	}

