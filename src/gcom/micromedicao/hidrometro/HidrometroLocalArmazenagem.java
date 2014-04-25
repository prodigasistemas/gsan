package gcom.micromedicao.hidrometro;

import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;

import java.util.Set;

/**
 * @author Hibernate CodeGenerator
 */
public class HidrometroLocalArmazenagem extends TabelaAuxiliarAbreviada {
	private static final long serialVersionUID = 1L;
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	private Set hidrometroLocalArmazenagemOrigem;

	private Set hidrometroLocalArmazenagemDestino;

	private Set hidrometros;
	
	private Short indicadorOficina;
	
    /**
     * Description of the Field
     */

    public final static Short INDICADOR_OFICINA = new Short("1");

	public Set getHidrometroLocalArmazenagemDestino() {
		return hidrometroLocalArmazenagemDestino;
	}

	public void setHidrometroLocalArmazenagemDestino(
			Set hidrometroLocalArmazenagemDestino) {
		this.hidrometroLocalArmazenagemDestino = hidrometroLocalArmazenagemDestino;
	}

	public Set getHidrometroLocalArmazenagemOrigem() {
		return hidrometroLocalArmazenagemOrigem;
	}

	public void setHidrometroLocalArmazenagemOrigem(
			Set hidrometroLocalArmazenagemOrigem) {
		this.hidrometroLocalArmazenagemOrigem = hidrometroLocalArmazenagemOrigem;
	}

	public Set getHidrometros() {
		return hidrometros;
	}

	public void setHidrometros(Set hidrometros) {
		this.hidrometros = hidrometros;
	}

	public Filtro retornaFiltro(){
		FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();
		
//		filtroHidrometroLocalArmazenagem.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalArmazenagemDestino");
//		filtroHidrometroLocalArmazenagem.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalArmazenagemOrigem");
//		filtroHidrometroLocalArmazenagem.adicionarCaminhoParaCarregamentoEntidade("hidrometros");
		filtroHidrometroLocalArmazenagem.adicionarParametro(new ParametroSimples(FiltroHidrometroLocalArmazenagem.ID, this.getId()));
		return filtroHidrometroLocalArmazenagem; 
	}

	public Short getIndicadorOficina() {
		return indicadorOficina;
	}

	public void setIndicadorOficina(Short indicadorOficina) {
		this.indicadorOficina = indicadorOficina;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
}
