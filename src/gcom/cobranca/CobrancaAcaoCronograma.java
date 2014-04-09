package gcom.cobranca;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CobrancaAcaoCronograma extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private gcom.cobranca.CobrancaAcao cobrancaAcao;

	/** persistent field */
	private gcom.cobranca.CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMes;
	
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroCobrancaAcaoCronograma filtroCobrancaAcaoCronograma = new FiltroCobrancaAcaoCronograma();
		filtroCobrancaAcaoCronograma. adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao");
		filtroCobrancaAcaoCronograma. adicionarCaminhoParaCarregamentoEntidade("cobrancaGrupoCronogramaMes");
		filtroCobrancaAcaoCronograma. adicionarParametro(
		new ParametroSimples(FiltroCobrancaAcaoCronograma.ID, this.getId()));
		
		return filtroCobrancaAcaoCronograma; 
	}

	/** full constructor */
	public CobrancaAcaoCronograma(Date ultimaAlteracao,
			gcom.cobranca.CobrancaAcao cobrancaAcao,
			gcom.cobranca.CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMes) {
		this.ultimaAlteracao = ultimaAlteracao;
		this.cobrancaAcao = cobrancaAcao;
		this.cobrancaGrupoCronogramaMes = cobrancaGrupoCronogramaMes;
	}

	/** default constructor */
	public CobrancaAcaoCronograma() {
	}

	/** minimal constructor */
	public CobrancaAcaoCronograma(gcom.cobranca.CobrancaAcao cobrancaAcao,
			gcom.cobranca.CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMes) {
		this.cobrancaAcao = cobrancaAcao;
		this.cobrancaGrupoCronogramaMes = cobrancaGrupoCronogramaMes;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.cobranca.CobrancaAcao getCobrancaAcao() {
		return this.cobrancaAcao;
	}

	public void setCobrancaAcao(gcom.cobranca.CobrancaAcao cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}

	public gcom.cobranca.CobrancaGrupoCronogramaMes getCobrancaGrupoCronogramaMes() {
		return this.cobrancaGrupoCronogramaMes;
	}

	public void setCobrancaGrupoCronogramaMes(
			gcom.cobranca.CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMes) {
		this.cobrancaGrupoCronogramaMes = cobrancaGrupoCronogramaMes;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
	
	@Override
	public void initializeLazy() {
		getCobrancaGrupoCronogramaMes();
		getCobrancaAcao();
	}
}
