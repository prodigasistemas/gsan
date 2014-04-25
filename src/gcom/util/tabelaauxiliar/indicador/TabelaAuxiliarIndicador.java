package gcom.util.tabelaauxiliar.indicador;

import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.TabelaAuxiliar;

import java.util.Date;

/**
 * @author gcom
 *
 */
public class TabelaAuxiliarIndicador extends TabelaAuxiliar{

	
	private static final long serialVersionUID = 1L;
	private Short indicadorBaixaRenda;
	
	
	public TabelaAuxiliarIndicador(Integer id, String descricao, Short indicadorUso, Date ultimaAlteracao, Short indicadorBaixaRenda) {
		super(id, descricao, indicadorUso, ultimaAlteracao);
		this.indicadorBaixaRenda = indicadorBaixaRenda;
	}

	 /**
     * default constructor
     */
    public TabelaAuxiliarIndicador() {
    }


	@Override
	public Filtro retornaFiltro() {
		FiltroTabelaAuxiliarIndicador filtroTabelaAuxiliarIndicador = new FiltroTabelaAuxiliarIndicador();

		filtroTabelaAuxiliarIndicador.adicionarParametro(new ParametroSimples(
				FiltroTabelaAuxiliarIndicador.ID, this.getId()));
		return filtroTabelaAuxiliarIndicador;
	}


	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	/**
	 * @return Returns the indicadorBaixaRenda.
	 */
	public Short getIndicadorBaixaRenda() {
		return indicadorBaixaRenda;
	}

	/**
	 * @param indicadorBaixaRenda The indicadorBaixaRenda to set.
	 */
	public void setIndicadorBaixaRenda(Short indicadorBaixaRenda) {
		this.indicadorBaixaRenda = indicadorBaixaRenda;
	}



}
