package gcom.cobranca;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * [RM 776] - Novo Processo de Cobrança - Automação das Penalidades - Parte 2
 * Classe básica Motivo não aceitação encerramento de OS
 * 
 * @author Diogo Peixoto
 *
 */
@ControleAlteracao()
public class MotivoNaoAceitacaoEncerramentoOS extends ObjetoTransacao{

	public static final int ATRIBUTOS_INSERIR_MOTIVO_NAO_ACEITACAO = 1799; // Operacao.OPERACAO_INSERIR_MOTIVO_NAO_ACEITACAO
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_MOTIVO_NAO_ACEITACAO})
	private String descricaoMotivoNaoAceitacaoEncerramentoOS;
	private Short multiplicadorValorServicoDescontarCorteSupressao;
	private Short multiplicadorValorServicoDescontarNaoExecutados;
	private BigDecimal percentualMultaAplicar;
	private Short indicadorUso;
	private Date ultimaAlteracao;
	private Short indicadorMotivoFiscalizacao;

	public MotivoNaoAceitacaoEncerramentoOS() {
		this.indicadorMotivoFiscalizacao = Short.valueOf("2");
		this.indicadorUso = Short.valueOf("1");
		this.ultimaAlteracao = new Date();
	}

	public MotivoNaoAceitacaoEncerramentoOS(Integer id, Short multiplicadorCorteSupressao, Short multiplicadorNaoExecutado, BigDecimal percentual) {
		this.id = id;
		this.multiplicadorValorServicoDescontarCorteSupressao = multiplicadorCorteSupressao;
		this.multiplicadorValorServicoDescontarNaoExecutados = multiplicadorNaoExecutado;
		this.percentualMultaAplicar = percentual;
	}

	public Filtro retornaFiltro(){
		FiltroMotivoNaoAceitacaoEncerramentoOS filtro = new FiltroMotivoNaoAceitacaoEncerramentoOS();
		filtro.adicionarParametro(new ParametroSimples(FiltroMotivoNaoAceitacaoEncerramentoOS.ID, this.getId()));
		return filtro;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.descricaoMotivoNaoAceitacaoEncerramentoOS;
	}

	
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	@Override
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoMotivoNaoAceitacaoEncerramentoOS() {
		return descricaoMotivoNaoAceitacaoEncerramentoOS;
	}

	public void setDescricaoMotivoNaoAceitacaoEncerramentoOS(
			String descricaoMotivoNaoAceitacaoEncerramentoOS) {
		this.descricaoMotivoNaoAceitacaoEncerramentoOS = descricaoMotivoNaoAceitacaoEncerramentoOS;
	}

	public Short getMultiplicadorValorServicoDescontarCorteSupressao() {
		return multiplicadorValorServicoDescontarCorteSupressao;
	}

	public void setMultiplicadorValorServicoDescontarCorteSupressao(
			Short multiplicadorValorServicoDescontarCorteSupressao) {
		this.multiplicadorValorServicoDescontarCorteSupressao = multiplicadorValorServicoDescontarCorteSupressao;
	}

	public Short getMultiplicadorValorServicoDescontarNaoExecutados() {
		return multiplicadorValorServicoDescontarNaoExecutados;
	}

	public void setMultiplicadorValorServicoDescontarNaoExecutados(
			Short multiplicadorValorServicoDescontarNaoExecutados) {
		this.multiplicadorValorServicoDescontarNaoExecutados = multiplicadorValorServicoDescontarNaoExecutados;
	}

	public BigDecimal getPercentualMultaAplicar() {
		return percentualMultaAplicar;
	}

	public void setPercentualMultaAplicar(BigDecimal percentualMultaAplicar) {
		this.percentualMultaAplicar = percentualMultaAplicar;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Short getIndicadorMotivoFiscalizacao() {
		return indicadorMotivoFiscalizacao;
	}

	public void setIndicadorMotivoFiscalizacao(Short indicadorMotivoFiscalizacao) {
		this.indicadorMotivoFiscalizacao = indicadorMotivoFiscalizacao;
	}
	
}
