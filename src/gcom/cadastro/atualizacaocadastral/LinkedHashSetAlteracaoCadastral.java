package gcom.cadastro.atualizacaocadastral;

import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.seguranca.transacao.AlteracaoTipo;

import java.util.LinkedHashMap;

public class LinkedHashSetAlteracaoCadastral extends LinkedHashMap<Integer, ConsultarMovimentoAtualizacaoCadastralHelper> {
	private static final long serialVersionUID = 2718222905198299673L;

	public ConsultarMovimentoAtualizacaoCadastralHelper put(Integer idImovel, ConsultarMovimentoAtualizacaoCadastralHelper value) {
		if (this.containsKey(idImovel)){
			ConsultarMovimentoAtualizacaoCadastralHelper current = this.get(idImovel);
			
			if (current.getIdTipoAlteracao().intValue() != value.getIdTipoAlteracao().intValue()){
				current.setIdTipoAlteracao(precedenciaAlteracoes(current.getIdTipoAlteracao(), value.getIdTipoAlteracao()));
			}
			
			return current;
		} else{
			return super.put(idImovel, value);
		}
	}

	private Integer precedenciaAlteracoes(int atual, int nova) {
		if (nova == AlteracaoTipo.ALTERACAO)
			return AlteracaoTipo.ALTERACAO;
		
		if (atual == AlteracaoTipo.ALTERACAO)
			return AlteracaoTipo.ALTERACAO;
		
		if (nova == AlteracaoTipo.EXCLUSAO)
			return AlteracaoTipo.EXCLUSAO;

		if (atual == AlteracaoTipo.EXCLUSAO)
			return AlteracaoTipo.EXCLUSAO;
		
		return AlteracaoTipo.INCLUSAO;
	}
}
