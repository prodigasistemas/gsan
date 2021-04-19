package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


public class FiltroOrdemServicoFoto extends Filtro implements Serializable {
		
		private static final long serialVersionUID = 1L;

		public FiltroOrdemServicoFoto() {
		}
		
		public FiltroOrdemServicoFoto(String campoOrderBy) {
			this.campoOrderBy = campoOrderBy;
		}
		
		public final static String ID = "id";
		public final static String ORDEM_SERVICO = "ordemServico";
		public final static String ORDEM_SERVICO_ID = "idOrdemServico";
	}
