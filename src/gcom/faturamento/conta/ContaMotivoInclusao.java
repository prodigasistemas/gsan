package gcom.faturamento.conta;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ContaMotivoInclusao extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
	
	public final static Integer TRASFERENCIA_DE_DEBITO = new Integer(41);
	public final static Integer RECUPERACAO_DE_CREDITO = new Integer(46);
	
	private Integer id;
	private String descricaoMotivoInclusaoConta;
	private Short indicadorUso;
	private Date ultimaAlteracao;

	public ContaMotivoInclusao(String descricaoMotivoInclusaoConta,
			Short indicadorUso, Date ultimaAlteracao) {
		this.descricaoMotivoInclusaoConta = descricaoMotivoInclusaoConta;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ContaMotivoInclusao() {
	}
	
	public ContaMotivoInclusao(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoMotivoInclusaoConta() {
		return this.descricaoMotivoInclusaoConta;
	}

	public void setDescricaoMotivoInclusaoConta(
			String descricaoMotivoInclusaoConta) {
		this.descricaoMotivoInclusaoConta = descricaoMotivoInclusaoConta;
	}

	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("cmicId", getId()).toString();
	}
	
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		Filtro filtro = new FiltroContaMotivoInclusao();
		
		filtro.adicionarParametro(
				new ParametroSimples(FiltroContaMotivoInclusao.ID, this.id));
		
		return filtro; 
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricaoMotivoInclusaoConta();
	}

}
