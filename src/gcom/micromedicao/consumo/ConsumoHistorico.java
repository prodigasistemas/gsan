package gcom.micromedicao.consumo;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PocoTipo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.RateioTipo;
import gcom.micromedicao.Rota;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class ConsumoHistorico extends ObjetoTransacao {
	
	public static final int ATUALIZA_LEITURA_CONSUMO_EXCECOES = 353;
	
	private static final long serialVersionUID = 1L;
	
	/** identifier field */
	private Integer id;

	/** persistent field */
	private int referenciaFaturamento;

	/** nullable persistent field */
	private Short indicadorAlteracaoUltimosConsumos;

	/** nullable persistent field */
	private Short indicadorAjuste;

	/** nullable persistent field */
	private Integer numeroConsumoFaturadoMes;

	/** nullable persistent field */
	private Integer consumoRateio;

	/** nullable persistent field */
	private Short indicadorImovelCondominio;

	/** nullable persistent field */
	private Integer consumoMedio;

	/** nullable persistent field */
	private Integer consumoMinimo;

	/** nullable persistent field */
	private Short indicadorFaturamento;

	/** nullable persistent field */
	private BigDecimal percentualColeta;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATUALIZA_LEITURA_CONSUMO_EXCECOES})
	private Date ultimaAlteracao;

	/** nullable persistent field */
	private Integer consumoImovelCondominio;
	
	/** nullable persistent field */
	private Integer consumoImovelVinculadosCondominio;
	
	/** nullable persistent field */
	private Integer numeroConsumoCalculoMedia;

	
	private Rota rota;

	/**
	 * A constante INDICADOR_FATURAMENTO_FATURAR_ESGOTO é usada pelo caso de uso
	 * [UC0153] - Apresentar Dados Para Análise da Medição e Consumo e contém o
	 * id do tipo de ligação Água(lgtp_id = 1 - ÁGUA - da tabela ligacaoTipo) Só
	 * mudar a constante se o id de ÁGUA(lgtp_id) mudar.
	 */
	public final static Short INDICADOR_FATURAMENTO_FATURAR_AGUA = new Short(
			"1");

	/**
	 * A constante INDICADOR_FATURAMENTO_FATURAR_ESGOTO é usada pelo caso de uso
	 * [UC0153] - Apresentar Dados Para Análise da Medição e Consumo e contém o
	 * id do tipo de ligação Esgoto(lgtp_id = 2 - ESGOTO - da tabela
	 * ligacaoTipo) Só mudar a constante se o id de Esgoto(lgtp_id) mudar.
	 */
	public final static Short INDICADOR_FATURAMENTO_FATURAR_ESGOTO = new Short(
			"2");

	public final static Short FATURAR_AGUA = new Short("1");

	public final static Short FATURAR_ESGOTO = new Short("1");

	/**
	 * Description of the Field
	 */
	public final static Integer NUMERO_CONSUMO_FATURADO_MES_PADRAO = new Integer(
			0);

	/** persistent field */
	private gcom.micromedicao.consumo.ConsumoHistorico consumoHistoricoCondominio;

	/** persistent field */
	private gcom.micromedicao.consumo.ConsumoTipo consumoTipo;

	/** persistent field */
	private RateioTipo rateioTipo;

	/** persistent field */
	private gcom.micromedicao.consumo.LigacaoTipo ligacaoTipo;

	/** persistent field */
	private gcom.micromedicao.consumo.ConsumoAnormalidade consumoAnormalidade;

	/** persistent field */
	private PocoTipo pocoTipo;

	/** persistent field */
	private FaturamentoSituacaoTipo faturamentoSituacaoTipo;

	/** persistent field */
	private Imovel imovel;

	/** full constructor */
	public ConsumoHistorico(
			int referenciaFaturamento,
			Short indicadorAlteracaoUltimosConsumos,
			Short indicadorAjuste,
			Integer numeroConsumoFaturadoMes,
			Integer consumoRateio,
			Short indicadorImovelCondominio,
			Integer consumoMedio,
			Integer consumoMinimo,
			Short indicadorFaturamento,
			BigDecimal percentualColeta,
			Date ultimaAlteracao,
			Integer consumoImovelCondominio,
			Integer consumoImovelVinculadosCondominio,
			gcom.micromedicao.consumo.ConsumoHistorico consumoHistoricoCondominio,
			gcom.micromedicao.consumo.ConsumoTipo consumoTipo,
			RateioTipo rateioTipo,
			gcom.micromedicao.consumo.LigacaoTipo ligacaoTipo,
			gcom.micromedicao.consumo.ConsumoAnormalidade consumoAnormalidade,
			PocoTipo pocoTipo, FaturamentoSituacaoTipo faturamentoSituacaoTipo,
			Imovel imovel) {
		this.referenciaFaturamento = referenciaFaturamento;
		this.indicadorAlteracaoUltimosConsumos = indicadorAlteracaoUltimosConsumos;
		this.indicadorAjuste = indicadorAjuste;
		this.numeroConsumoFaturadoMes = numeroConsumoFaturadoMes;
		this.consumoRateio = consumoRateio;
		this.indicadorImovelCondominio = indicadorImovelCondominio;
		this.consumoMedio = consumoMedio;
		this.consumoMinimo = consumoMinimo;
		this.indicadorFaturamento = indicadorFaturamento;
		this.percentualColeta = percentualColeta;
		this.ultimaAlteracao = ultimaAlteracao;
		this.consumoImovelCondominio = consumoImovelCondominio;
		this.consumoImovelVinculadosCondominio = consumoImovelVinculadosCondominio;
		this.consumoHistoricoCondominio = consumoHistoricoCondominio;
		this.consumoTipo = consumoTipo;
		this.rateioTipo = rateioTipo;
		this.ligacaoTipo = ligacaoTipo;
		this.consumoAnormalidade = consumoAnormalidade;
		this.pocoTipo = pocoTipo;
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
		this.imovel = imovel;
	}
	
	public ConsumoHistorico(ConsumoHistorico consumoHistorico) {
		this.referenciaFaturamento = consumoHistorico.getReferenciaFaturamento();
		this.indicadorAlteracaoUltimosConsumos = consumoHistorico.getIndicadorAlteracaoUltimosConsumos();
		this.indicadorAjuste = consumoHistorico.getIndicadorAjuste();
		this.numeroConsumoFaturadoMes = consumoHistorico.getNumeroConsumoFaturadoMes();
		this.consumoRateio = consumoHistorico.getConsumoRateio();
		this.indicadorImovelCondominio = consumoHistorico.getIndicadorImovelCondominio();
		this.consumoMedio = consumoHistorico.getConsumoMedio();
		this.consumoMinimo = consumoHistorico.getConsumoMinimo();
		this.indicadorFaturamento = consumoHistorico.getIndicadorFaturamento();
		this.percentualColeta = consumoHistorico.getPercentualColeta();
		this.ultimaAlteracao = consumoHistorico.getUltimaAlteracao();
		this.consumoImovelCondominio = consumoHistorico.getConsumoImovelCondominio();
		this.consumoImovelVinculadosCondominio = consumoHistorico.getConsumoImovelVinculadosCondominio();
		this.consumoHistoricoCondominio = consumoHistorico.getConsumoHistoricoCondominio();
		this.consumoTipo = consumoHistorico.getConsumoTipo();
		this.rateioTipo = consumoHistorico.getRateioTipo();
		this.ligacaoTipo = consumoHistorico.getLigacaoTipo();
		this.consumoAnormalidade = consumoHistorico.getConsumoAnormalidade();
		this.pocoTipo = consumoHistorico.getPocoTipo();
		this.faturamentoSituacaoTipo = consumoHistorico.getFaturamentoSituacaoTipo();
		this.imovel = consumoHistorico.getImovel();
		this.rota = consumoHistorico.getRota();
		this.numeroConsumoCalculoMedia = consumoHistorico.getNumeroConsumoCalculoMedia();
	}

	/** default constructor */
	public ConsumoHistorico() {
	}

	/** minimal constructor */
	public ConsumoHistorico(
			int referenciaFaturamento,
			gcom.micromedicao.consumo.ConsumoHistorico consumoHistoricoCondominio,
			gcom.micromedicao.consumo.ConsumoTipo consumoTipo,
			RateioTipo rateioTipo,
			gcom.micromedicao.consumo.LigacaoTipo ligacaoTipo,
			gcom.micromedicao.consumo.ConsumoAnormalidade consumoAnormalidade,
			PocoTipo pocoTipo, FaturamentoSituacaoTipo faturamentoSituacaoTipo,
			Imovel imovel) {
		this.referenciaFaturamento = referenciaFaturamento;
		this.consumoHistoricoCondominio = consumoHistoricoCondominio;
		this.consumoTipo = consumoTipo;
		this.rateioTipo = rateioTipo;
		this.ligacaoTipo = ligacaoTipo;
		this.consumoAnormalidade = consumoAnormalidade;
		this.pocoTipo = pocoTipo;
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
		this.imovel = imovel;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getReferenciaFaturamento() {
		return this.referenciaFaturamento;
	}
	
	public String getReferenciaFaturamentoFormatado() {
		return Util.formatarAnoMesParaMesAno(  this.referenciaFaturamento );
	}
	

	public void setReferenciaFaturamento(int referenciaFaturamento) {
		this.referenciaFaturamento = referenciaFaturamento;
	}

	public Short getIndicadorAlteracaoUltimosConsumos() {
		return this.indicadorAlteracaoUltimosConsumos;
	}

	public void setIndicadorAlteracaoUltimosConsumos(
			Short indicadorAlteracaoUltimosConsumos) {
		this.indicadorAlteracaoUltimosConsumos = indicadorAlteracaoUltimosConsumos;
	}

	public Short getIndicadorAjuste() {
		return this.indicadorAjuste;
	}

	public void setIndicadorAjuste(Short indicadorAjuste) {
		this.indicadorAjuste = indicadorAjuste;
	}

	public Integer getNumeroConsumoFaturadoMes() {
		return this.numeroConsumoFaturadoMes;
	}

	public void setNumeroConsumoFaturadoMes(Integer numeroConsumoFaturadoMes) {
		this.numeroConsumoFaturadoMes = numeroConsumoFaturadoMes;
	}

	public Integer getConsumoRateio() {
		return this.consumoRateio;
	}

	public void setConsumoRateio(Integer consumoRateio) {
		this.consumoRateio = consumoRateio;
	}

	public Short getIndicadorImovelCondominio() {
		return this.indicadorImovelCondominio;
	}

	public void setIndicadorImovelCondominio(Short indicadorImovelCondominio) {
		this.indicadorImovelCondominio = indicadorImovelCondominio;
	}

	public Integer getConsumoMedio() {
		return this.consumoMedio;
	}

	public void setConsumoMedio(Integer consumoMedio) {
		this.consumoMedio = consumoMedio;
	}

	public Integer getConsumoMinimo() {
		return this.consumoMinimo;
	}

	public void setConsumoMinimo(Integer consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}

	public Short getIndicadorFaturamento() {
		return this.indicadorFaturamento;
	}

	public void setIndicadorFaturamento(Short indicadorFaturamento) {
		this.indicadorFaturamento = indicadorFaturamento;
	}

	public BigDecimal getPercentualColeta() {
		return this.percentualColeta;
	}

	public void setPercentualColeta(BigDecimal percentualColeta) {
		this.percentualColeta = percentualColeta;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getConsumoImovelCondominio() {
		return this.consumoImovelCondominio;
	}

	public void setConsumoImovelCondominio(Integer consumoImovelCondominio) {
		this.consumoImovelCondominio = consumoImovelCondominio;
	}

	public gcom.micromedicao.consumo.ConsumoHistorico getConsumoHistoricoCondominio() {
		return this.consumoHistoricoCondominio;
	}

	public void setConsumoHistoricoCondominio(
			gcom.micromedicao.consumo.ConsumoHistorico consumoHistoricoCondominio) {
		this.consumoHistoricoCondominio = consumoHistoricoCondominio;
	}

	public gcom.micromedicao.consumo.ConsumoTipo getConsumoTipo() {
		return this.consumoTipo;
	}

	public void setConsumoTipo(gcom.micromedicao.consumo.ConsumoTipo consumoTipo) {
		this.consumoTipo = consumoTipo;
	}

	public RateioTipo getRateioTipo() {
		return this.rateioTipo;
	}

	public void setRateioTipo(RateioTipo rateioTipo) {
		this.rateioTipo = rateioTipo;
	}

	public gcom.micromedicao.consumo.LigacaoTipo getLigacaoTipo() {
		return this.ligacaoTipo;
	}

	public void setLigacaoTipo(gcom.micromedicao.consumo.LigacaoTipo ligacaoTipo) {
		this.ligacaoTipo = ligacaoTipo;
	}

	public gcom.micromedicao.consumo.ConsumoAnormalidade getConsumoAnormalidade() {
		return this.consumoAnormalidade;
	}

	public void setConsumoAnormalidade(
			gcom.micromedicao.consumo.ConsumoAnormalidade consumoAnormalidade) {
		this.consumoAnormalidade = consumoAnormalidade;
	}

	public PocoTipo getPocoTipo() {
		return this.pocoTipo;
	}

	public void setPocoTipo(PocoTipo pocoTipo) {
		this.pocoTipo = pocoTipo;
	}

	public FaturamentoSituacaoTipo getFaturamentoSituacaoTipo() {
		return this.faturamentoSituacaoTipo;
	}

	public void setFaturamentoSituacaoTipo(
			FaturamentoSituacaoTipo faturamentoSituacaoTipo) {
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
	}

	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * Retorna o valor de mesAno
	 * 
	 * @return O valor de mesAno
	 */
	public String getMesAno() {
		// o metodo serve para transformar o AnoMesReferencia do banco
		// em mes/Ano para demonstraçao para o usuario.
		// Ex.: 200508 para 08/2005
		String mesAno = null;

		String mes = null;
		String ano = null;

		if (this.referenciaFaturamento != 0) {
			String anoMes = "" + this.referenciaFaturamento;

			mes = anoMes.substring(4, 6);
			ano = anoMes.substring(0, 4);
			mesAno = mes + "/" + ano;
		}
		return mesAno;
	}

	/**
	 * @return Retorna o campo rota.
	 */
	public Rota getRota() {
		return rota;
	}

	/**
	 * @param rota
	 *            O rota a ser setado.
	 */
	public void setRota(Rota rota) {
		this.rota = rota;
	}

	/**
	 * @return Retorna o campo consumoImovelVinculadosCondominio.
	 */
	public Integer getConsumoImovelVinculadosCondominio() {
		return consumoImovelVinculadosCondominio;
	}

	/**
	 * @param consumoImovelVinculadosCondominio O consumoImovelVinculadosCondominio a ser setado.
	 */
	public void setConsumoImovelVinculadosCondominio(
			Integer consumoImovelVinculadosCondominio) {
		this.consumoImovelVinculadosCondominio = consumoImovelVinculadosCondominio;
	}

	/**
	 * @return Retorna o campo numeroConsumoCalculoMedia.
	 */
	public Integer getNumeroConsumoCalculoMedia() {
		return numeroConsumoCalculoMedia;
	}

	/**
	 * @param numeroConsumoCalculoMedia O numeroConsumoCalculoMedia a ser setado.
	 */
	public void setNumeroConsumoCalculoMedia(Integer numeroConsumoCalculoMedia) {
		this.numeroConsumoCalculoMedia = numeroConsumoCalculoMedia;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	@Override
	public Filtro retornaFiltro() {
		FiltroConsumoHistorico filtroConsumoHistorico = new FiltroConsumoHistorico();

		filtroConsumoHistorico.adicionarParametro(new ParametroSimples(FiltroConsumoHistorico.ID,
				this.getId()));
		filtroConsumoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel");
		
		return filtroConsumoHistorico;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"ultimaAlteracao"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Data"};
		return labels;		
	}

}
