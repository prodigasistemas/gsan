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

@ControleAlteracao()
public class ConsumoHistorico extends ObjetoTransacao {
	
	public static final int ATUALIZA_LEITURA_CONSUMO_EXCECOES = 353;
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private int referenciaFaturamento;
	private Short indicadorAlteracaoUltimosConsumos;
	private Short indicadorAjuste;
	private Integer numeroConsumoFaturadoMes;
	private Integer consumoRateio;
	private Short indicadorImovelCondominio;
	private Integer consumoMedio;
	private Integer consumoMinimo;
	private Short indicadorFaturamento;
	private BigDecimal percentualColeta;

	@ControleAlteracao(funcionalidade={ATUALIZA_LEITURA_CONSUMO_EXCECOES})
	private Date ultimaAlteracao;

	private Integer consumoImovelCondominio;
	private Integer consumoImovelVinculadosCondominio;
	private Integer numeroConsumoCalculoMedia;
	
	private Rota rota;

	public final static Short INDICADOR_FATURAMENTO_FATURAR_AGUA = new Short("1");
	public final static Short INDICADOR_FATURAMENTO_FATURAR_ESGOTO = new Short("2");

	public final static Short FATURAR_AGUA = new Short("1");
	public final static Short FATURAR_ESGOTO = new Short("1");

	public final static Integer NUMERO_CONSUMO_FATURADO_MES_PADRAO = new Integer(0);

	private ConsumoHistorico consumoHistoricoCondominio;
	private ConsumoTipo consumoTipo;
	private RateioTipo rateioTipo;
	private LigacaoTipo ligacaoTipo;
	private ConsumoAnormalidade consumoAnormalidade;
	private PocoTipo pocoTipo;
	private FaturamentoSituacaoTipo faturamentoSituacaoTipo;
	private Imovel imovel;

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

	public ConsumoHistorico() {
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

	public String getMesAno() {
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

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public Integer getConsumoImovelVinculadosCondominio() {
		return consumoImovelVinculadosCondominio;
	}

	public void setConsumoImovelVinculadosCondominio(Integer consumoImovelVinculadosCondominio) {
		this.consumoImovelVinculadosCondominio = consumoImovelVinculadosCondominio;
	}

	public Integer getNumeroConsumoCalculoMedia() {
		return numeroConsumoCalculoMedia;
	}

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
