package gcom.micromedicao.leitura;

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao
public class LeituraAnormalidade extends ObjetoTransacao {


	private static final long serialVersionUID = 1L;

	private Integer id;
	private String descricao;
	private String descricaoAbreviada;
	private short indicadorRelativoHidrometro;
	private Short indicadorImovelSemHidrometro;
	private Short indicadorSistema;
	private short indicadorEmissaoOrdemServico;
	private Short indicadorUso;
	private Short indicadorPerdaTarifaSocial;
	private Date ultimaAlteracao;
	private BigDecimal numeroFatorSemLeitura;
	private BigDecimal numeroFatorComLeitura;
	private Short indicadorLeitura;
	private Short indicadorImpressaoSimultanea;
	private Integer numeroMesesLeituraSuspensa;
	private Integer numeroVezesSuspendeLeitura;

	private LeituraAnormalidadeLeitura leituraAnormalidadeLeituraComleitura;
	private LeituraAnormalidadeLeitura leituraAnormalidadeLeituraSemleitura;
	private LeituraAnormalidadeConsumo leituraAnormalidadeConsumoComleitura;
	private LeituraAnormalidadeConsumo leituraAnormalidadeConsumoSemleitura;
	private ServicoTipo servicoTipo;

	public final static Integer HIDROMETRO_PARADO = new Integer(30);
	public final static Integer HIDROMETRO_SUBSTITUIDO = new Integer(32);
	public final static Integer HIDROMETRO_PARADO_SEM_CONSUMO = new Integer(38);
	public final static Integer HIDROMETRO_RETIRADO  = new Integer(2);

	public final static Integer INDICADOR_PERDA_TARIFA_SOCIAL = new Integer(1);
	public final static Integer INDICADOR_NAO_PERDA_TARIFA_SOCIAL = new Integer(2);
	
	public final static Short INDICADOR_IMOVEL_SEM_HIDROMETRO = new Short("2");
	public final static Integer INDICADOR_LIGADO_CLANDESTINO_AGUA = new Integer("81");
	public final static Integer INDICADOR_LIGADO_CLANDESTINO_ESGOTO = new Integer("82");
	public final static Integer INDICADOR_LIGADO_CLANDESTINO_AGUA_ESGOTO = new Integer("83");
	
	public static final int ALTERAR_DADOS_DO_FATURAMENTO = 436;

	public LeituraAnormalidade(
			String descricao, String descricaoAbreviada, short indicadorRelativoHidrometro,
			Short indicadorImovelSemHidrometro, Short indicadorSistema, short indicadorEmissaoOrdemServico,
			Short indicadorUso,
			Short indicadorPerdaTarifaSocial, Date ultimaAlteracao,
			LeituraAnormalidadeLeitura leituraAnormalidadeLeituraComleitura,
			LeituraAnormalidadeLeitura leituraAnormalidadeLeituraSemleitura,
			LeituraAnormalidadeConsumo leituraAnormalidadeConsumoComleitura,
			LeituraAnormalidadeConsumo leituraAnormalidadeConsumoSemleitura,
			ServicoTipo servicoTipo) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorRelativoHidrometro = indicadorRelativoHidrometro;
		this.indicadorImovelSemHidrometro = indicadorImovelSemHidrometro;
		this.indicadorSistema = indicadorSistema;
		this.indicadorEmissaoOrdemServico = indicadorEmissaoOrdemServico;
		this.indicadorUso = indicadorUso;
		this.indicadorPerdaTarifaSocial = indicadorPerdaTarifaSocial;
		this.ultimaAlteracao = ultimaAlteracao;
		this.leituraAnormalidadeLeituraComleitura = leituraAnormalidadeLeituraComleitura;
		this.leituraAnormalidadeLeituraSemleitura = leituraAnormalidadeLeituraSemleitura;
		this.leituraAnormalidadeConsumoComleitura = leituraAnormalidadeConsumoComleitura;
		this.leituraAnormalidadeConsumoSemleitura = leituraAnormalidadeConsumoSemleitura;
		this.servicoTipo = servicoTipo;
	}


	public LeituraAnormalidade() {
	}

	public LeituraAnormalidade(
			String descricao, String descricaoAbreviada, short indicadorRelativoHidrometro,
			short indicadorEmissaoOrdemServico,
			LeituraAnormalidadeLeitura leituraAnormalidadeLeituraComleitura,
			LeituraAnormalidadeLeitura leituraAnormalidadeLeituraSemleitura,
			LeituraAnormalidadeConsumo leituraAnormalidadeConsumoComleitura,
			LeituraAnormalidadeConsumo leituraAnormalidadeConsumoSemleitura) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorRelativoHidrometro = indicadorRelativoHidrometro;
		this.indicadorEmissaoOrdemServico = indicadorEmissaoOrdemServico;
		this.leituraAnormalidadeLeituraComleitura = leituraAnormalidadeLeituraComleitura;
		this.leituraAnormalidadeLeituraSemleitura = leituraAnormalidadeLeituraSemleitura;
		this.leituraAnormalidadeConsumoComleitura = leituraAnormalidadeConsumoComleitura;
		this.leituraAnormalidadeConsumoSemleitura = leituraAnormalidadeConsumoSemleitura;
	}

	public ServicoTipo getServicoTipo() {
		return servicoTipo;
	}
	
	public void setServicoTipo(ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	public LeituraAnormalidade(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public short getIndicadorRelativoHidrometro() {
		return this.indicadorRelativoHidrometro;
	}

	public void setIndicadorRelativoHidrometro(short indicadorRelativoHidrometro) {
		this.indicadorRelativoHidrometro = indicadorRelativoHidrometro;
	}

	public Short getIndicadorImovelSemHidrometro() {
		return this.indicadorImovelSemHidrometro;
	}

	public void setIndicadorImovelSemHidrometro(Short indicadorImovelSemHidrometro) {
		this.indicadorImovelSemHidrometro = indicadorImovelSemHidrometro;
	}

	public Short getIndicadorSistema() {
		return this.indicadorSistema;
	}

	public void setIndicadorSistema(Short indicadorSistema) {
		this.indicadorSistema = indicadorSistema;
	}

	public short getIndicadorEmissaoOrdemServico() {
		return this.indicadorEmissaoOrdemServico;
	}

	public void setIndicadorEmissaoOrdemServico(short indicadorEmissaoOrdemServico) {
		this.indicadorEmissaoOrdemServico = indicadorEmissaoOrdemServico;
	}

	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Short getIndicadorPerdaTarifaSocial() {
		return this.indicadorPerdaTarifaSocial;
	}

	public void setIndicadorPerdaTarifaSocial(Short indicadorPerdaTarifaSocial) {
		this.indicadorPerdaTarifaSocial = indicadorPerdaTarifaSocial;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public LeituraAnormalidadeLeitura getLeituraAnormalidadeLeituraComleitura() {
		return this.leituraAnormalidadeLeituraComleitura;
	}

	public void setLeituraAnormalidadeLeituraComleitura(LeituraAnormalidadeLeitura leituraAnormalidadeLeituraComleitura) {
		this.leituraAnormalidadeLeituraComleitura = leituraAnormalidadeLeituraComleitura;
	}

	public LeituraAnormalidadeLeitura getLeituraAnormalidadeLeituraSemleitura() {
		return this.leituraAnormalidadeLeituraSemleitura;
	}

	public void setLeituraAnormalidadeLeituraSemleitura(LeituraAnormalidadeLeitura leituraAnormalidadeLeituraSemleitura) {
		this.leituraAnormalidadeLeituraSemleitura = leituraAnormalidadeLeituraSemleitura;
	}

	public LeituraAnormalidadeConsumo getLeituraAnormalidadeConsumoComleitura() {
		return this.leituraAnormalidadeConsumoComleitura;
	}

	public void setLeituraAnormalidadeConsumoComleitura(LeituraAnormalidadeConsumo leituraAnormalidadeConsumoComleitura) {
		this.leituraAnormalidadeConsumoComleitura = leituraAnormalidadeConsumoComleitura;
	}

	public LeituraAnormalidadeConsumo getLeituraAnormalidadeConsumoSemleitura() {
		return this.leituraAnormalidadeConsumoSemleitura;
	}

	public void setLeituraAnormalidadeConsumoSemleitura(LeituraAnormalidadeConsumo leituraAnormalidadeConsumoSemleitura) {
		this.leituraAnormalidadeConsumoSemleitura = leituraAnormalidadeConsumoSemleitura;
	}

	public Short getIndicadorLeitura() {
		return indicadorLeitura;
	}

	public void setIndicadorLeitura(Short indicadorLeitura) {
		this.indicadorLeitura = indicadorLeitura;
	}

	public BigDecimal getNumeroFatorComLeitura() {
		return numeroFatorComLeitura;
	}

	public void setNumeroFatorComLeitura(BigDecimal numeroFatorComLeitura) {
		this.numeroFatorComLeitura = numeroFatorComLeitura;
	}

	public BigDecimal getNumeroFatorSemLeitura() {
		return numeroFatorSemLeitura;
	}

	public void setNumeroFatorSemLeitura(BigDecimal numeroFatorSemLeitura) {
		this.numeroFatorSemLeitura = numeroFatorSemLeitura;
	}

	public Integer getNumeroMesesLeituraSuspensa() {
		return numeroMesesLeituraSuspensa;
	}

	public void setNumeroMesesLeituraSuspensa(Integer numeroMesesLeituraSuspensa) {
		this.numeroMesesLeituraSuspensa = numeroMesesLeituraSuspensa;
	}

	public Integer getNumeroVezesSuspendeLeitura() {
		return numeroVezesSuspendeLeitura;
	}

	public void setNumeroVezesSuspendeLeitura(Integer numeroVezesSuspendeLeitura) {
		this.numeroVezesSuspendeLeitura = numeroVezesSuspendeLeitura;
	}

	public short getIndicadorImpressaoSimultanea() {
		return this.indicadorImpressaoSimultanea;
	}
	
	public void setIndicadorImpressaoSimultanea(short indicadorImpressaoSimultanea) {
		this.indicadorImpressaoSimultanea = indicadorImpressaoSimultanea;
	}
	
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();

		filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(	FiltroLeituraAnormalidade.ID, this.getId()));
		filtroLeituraAnormalidade.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");
		filtroLeituraAnormalidade.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeConsumoSemleitura");
		filtroLeituraAnormalidade.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeConsumoComleitura");
		filtroLeituraAnormalidade.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeLeituraSemleitura");
		filtroLeituraAnormalidade.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeLeituraComleitura");

		return filtroLeituraAnormalidade;
	}
}
