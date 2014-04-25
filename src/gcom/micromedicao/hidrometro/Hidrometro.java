package gcom.micromedicao.hidrometro;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao
public class Hidrometro extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
	/** identifier field */
	private Integer id;

	/** persistent field */
	@ControleAlteracao(funcionalidade={LigacaoAgua.ATRIBUTOS_EFETUAR_LIGACAO_AGUA_INSTALACAO_HIDROMETRO,
			HidrometroInstalacaoHistorico.ATRIBUTOS_EFETUAR_INSTALACAO_HIDROMETRO,
			HidrometroInstalacaoHistorico.ATRIBUTOS_EFETUAR_SUBSTITUICAO_HIDROMETRO})
	private String numero;

	/** persistent field */
	private Date dataAquisicao;

	/** persistent field */
	private Short anoFabricacao;

	/** nullable persistent field */
	private Short indicadorMacromedidor;

	/** nullable persistent field */
	private Date dataUltimaRevisao;

	/** nullable persistent field */
	private Date dataBaixa;

	/** persistent field */
	private Integer numeroLeituraAcumulada;

	/** nullable persistent field */
	private Short numeroDigitosLeitura;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private gcom.micromedicao.hidrometro.HidrometroTipo hidrometroTipo;

	/** persistent field */
	@ControleAlteracao(value=FiltroHidrometro.HIDROMETRO_SITUACAO,
			funcionalidade=HidrometroInstalacaoHistorico.ATRIBUTOS_EFETUAR_SUBSTITUICAO_HIDROMETRO)
	private gcom.micromedicao.hidrometro.HidrometroSituacao hidrometroSituacao;

	/** persistent field */
	private gcom.micromedicao.hidrometro.HidrometroMarca hidrometroMarca;

	/** persistent field */
	private gcom.micromedicao.hidrometro.HidrometroCapacidade hidrometroCapacidade;

	/** persistent field */
	private gcom.micromedicao.hidrometro.HidrometroMotivoBaixa hidrometroMotivoBaixa;

	/** persistent field */
	@ControleAlteracao(value=FiltroHidrometro.HIDROMETRO_LOCAL_ARMAZENAGEM,
			funcionalidade=HidrometroInstalacaoHistorico.ATRIBUTOS_EFETUAR_SUBSTITUICAO_HIDROMETRO)	
	private gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagem;

	/** persistent field */
	private gcom.micromedicao.hidrometro.HidrometroClasseMetrologica hidrometroClasseMetrologica;

	/** persistent field */
	private gcom.micromedicao.hidrometro.HidrometroDiametro hidrometroDiametro;
	
	
	private gcom.micromedicao.hidrometro.HidrometroRelojoaria hidrometroRelojoaria;
	
	private BigDecimal vazaoTransicao;
	
	private BigDecimal vazaoNominal;
	
	private BigDecimal vazaoMinima;
	
	private Integer notaFiscal;
	
	private Short tempoGarantiaAnos;

	/** persistent field */
	private Set hidrometroMovimentados;

	public final static Integer INDICADOR_COMERCIAL = new Integer("2");

	public final static Integer INDICADOR_OPERACIONAL = new Integer("1");
	
	public final static Integer SITUACAO_INSTALADO = new Integer("1");
	
	public final static Integer SITUACAO_SUBSTITUIDO = new Integer("2");

	/** full constructor */
	public Hidrometro(
			String numero,
			Date dataAquisicao,
			Short anoFabricacao,
			Short indicadorMacromedidor,
			Date dataUltimaRevisao,
			Date dataBaixa,
			Integer numeroLeituraAcumulada,
			Short numeroDigitosLeitura,
			Date ultimaAlteracao,
			gcom.micromedicao.hidrometro.HidrometroTipo hidrometroTipo,
			gcom.micromedicao.hidrometro.HidrometroSituacao hidrometroSituacao,
			gcom.micromedicao.hidrometro.HidrometroMarca hidrometroMarca,
			gcom.micromedicao.hidrometro.HidrometroCapacidade hidrometroCapacidade,
			gcom.micromedicao.hidrometro.HidrometroMotivoBaixa hidrometroMotivoBaixa,
			gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagem,
			gcom.micromedicao.hidrometro.HidrometroClasseMetrologica hidrometroClasseMetrologica,
			gcom.micromedicao.hidrometro.HidrometroDiametro hidrometroDiametro,
			Set hidrometroMovimentados) {
		this.numero = numero;
		this.dataAquisicao = dataAquisicao;
		this.anoFabricacao = anoFabricacao;
		this.indicadorMacromedidor = indicadorMacromedidor;
		this.dataUltimaRevisao = dataUltimaRevisao;
		this.dataBaixa = dataBaixa;
		this.numeroLeituraAcumulada = numeroLeituraAcumulada;
		this.numeroDigitosLeitura = numeroDigitosLeitura;
		this.ultimaAlteracao = ultimaAlteracao;
		this.hidrometroTipo = hidrometroTipo;
		this.hidrometroSituacao = hidrometroSituacao;
		this.hidrometroMarca = hidrometroMarca;
		this.hidrometroCapacidade = hidrometroCapacidade;
		this.hidrometroMotivoBaixa = hidrometroMotivoBaixa;
		this.hidrometroLocalArmazenagem = hidrometroLocalArmazenagem;
		this.hidrometroClasseMetrologica = hidrometroClasseMetrologica;
		this.hidrometroDiametro = hidrometroDiametro;
		this.hidrometroMovimentados = hidrometroMovimentados;
	}

	/** default constructor */
	public Hidrometro() {
	}

	/** minimal constructor */
	public Hidrometro(
			String numero,
			Date dataAquisicao,
			Short anoFabricacao,
			Integer numeroLeituraAcumulada,
			gcom.micromedicao.hidrometro.HidrometroTipo hidrometroTipo,
			gcom.micromedicao.hidrometro.HidrometroSituacao hidrometroSituacao,
			gcom.micromedicao.hidrometro.HidrometroMarca hidrometroMarca,
			gcom.micromedicao.hidrometro.HidrometroCapacidade hidrometroCapacidade,
			gcom.micromedicao.hidrometro.HidrometroMotivoBaixa hidrometroMotivoBaixa,
			gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagem,
			gcom.micromedicao.hidrometro.HidrometroClasseMetrologica hidrometroClasseMetrologica,
			gcom.micromedicao.hidrometro.HidrometroDiametro hidrometroDiametro,
			Set hidrometroMovimentados) {
		this.numero = numero;
		this.dataAquisicao = dataAquisicao;
		this.anoFabricacao = anoFabricacao;
		this.numeroLeituraAcumulada = numeroLeituraAcumulada;
		this.hidrometroTipo = hidrometroTipo;
		this.hidrometroSituacao = hidrometroSituacao;
		this.hidrometroMarca = hidrometroMarca;
		this.hidrometroCapacidade = hidrometroCapacidade;
		this.hidrometroMotivoBaixa = hidrometroMotivoBaixa;
		this.hidrometroLocalArmazenagem = hidrometroLocalArmazenagem;
		this.hidrometroClasseMetrologica = hidrometroClasseMetrologica;
		this.hidrometroDiametro = hidrometroDiametro;
		this.hidrometroMovimentados = hidrometroMovimentados;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDataAquisicao() {
		return this.dataAquisicao;
	}

	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public Short getAnoFabricacao() {
		return this.anoFabricacao;
	}

	public void setAnoFabricacao(Short anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public Short getIndicadorMacromedidor() {
		return this.indicadorMacromedidor;
	}

	public void setIndicadorMacromedidor(Short indicadorMacromedidor) {
		this.indicadorMacromedidor = indicadorMacromedidor;
	}

	public Date getDataUltimaRevisao() {
		return this.dataUltimaRevisao;
	}

	public void setDataUltimaRevisao(Date dataUltimaRevisao) {
		this.dataUltimaRevisao = dataUltimaRevisao;
	}

	public Date getDataBaixa() {
		return this.dataBaixa;
	}

	public void setDataBaixa(Date dataBaixa) {
		this.dataBaixa = dataBaixa;
	}

	public Integer getNumeroLeituraAcumulada() {
		return this.numeroLeituraAcumulada;
	}

	public void setNumeroLeituraAcumulada(Integer numeroLeituraAcumulada) {
		this.numeroLeituraAcumulada = numeroLeituraAcumulada;
	}

	public Short getNumeroDigitosLeitura() {
		return this.numeroDigitosLeitura;
	}

	public void setNumeroDigitosLeitura(Short numeroDigitosLeitura) {
		this.numeroDigitosLeitura = numeroDigitosLeitura;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.micromedicao.hidrometro.HidrometroTipo getHidrometroTipo() {
		return this.hidrometroTipo;
	}

	public void setHidrometroTipo(
			gcom.micromedicao.hidrometro.HidrometroTipo hidrometroTipo) {
		this.hidrometroTipo = hidrometroTipo;
	}

	public gcom.micromedicao.hidrometro.HidrometroSituacao getHidrometroSituacao() {
		return this.hidrometroSituacao;
	}

	public void setHidrometroSituacao(
			gcom.micromedicao.hidrometro.HidrometroSituacao hidrometroSituacao) {
		this.hidrometroSituacao = hidrometroSituacao;
	}

	public gcom.micromedicao.hidrometro.HidrometroMarca getHidrometroMarca() {
		return this.hidrometroMarca;
	}

	public void setHidrometroMarca(
			gcom.micromedicao.hidrometro.HidrometroMarca hidrometroMarca) {
		this.hidrometroMarca = hidrometroMarca;
	}

	public gcom.micromedicao.hidrometro.HidrometroCapacidade getHidrometroCapacidade() {
		return this.hidrometroCapacidade;
	}

	public void setHidrometroCapacidade(
			gcom.micromedicao.hidrometro.HidrometroCapacidade hidrometroCapacidade) {
		this.hidrometroCapacidade = hidrometroCapacidade;
	}

	public gcom.micromedicao.hidrometro.HidrometroMotivoBaixa getHidrometroMotivoBaixa() {
		return this.hidrometroMotivoBaixa;
	}

	public void setHidrometroMotivoBaixa(
			gcom.micromedicao.hidrometro.HidrometroMotivoBaixa hidrometroMotivoBaixa) {
		this.hidrometroMotivoBaixa = hidrometroMotivoBaixa;
	}

	public gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem getHidrometroLocalArmazenagem() {
		return this.hidrometroLocalArmazenagem;
	}

	public void setHidrometroLocalArmazenagem(
			gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagem) {
		this.hidrometroLocalArmazenagem = hidrometroLocalArmazenagem;
	}

	public gcom.micromedicao.hidrometro.HidrometroClasseMetrologica getHidrometroClasseMetrologica() {
		return this.hidrometroClasseMetrologica;
	}

	public void setHidrometroClasseMetrologica(
			gcom.micromedicao.hidrometro.HidrometroClasseMetrologica hidrometroClasseMetrologica) {
		this.hidrometroClasseMetrologica = hidrometroClasseMetrologica;
	}

	public gcom.micromedicao.hidrometro.HidrometroDiametro getHidrometroDiametro() {
		return this.hidrometroDiametro;
	}

	public void setHidrometroDiametro(
			gcom.micromedicao.hidrometro.HidrometroDiametro hidrometroDiametro) {
		this.hidrometroDiametro = hidrometroDiametro;
	}

	public Set getHidrometroMovimentados() {
		return this.hidrometroMovimentados;
	}

	public void setHidrometroMovimentados(Set hidrometroMovimentados) {
		this.hidrometroMovimentados = hidrometroMovimentados;
	}
	
	

	public gcom.micromedicao.hidrometro.HidrometroRelojoaria getHidrometroRelojoaria() {
		return hidrometroRelojoaria;
	}

	public void setHidrometroRelojoaria(
			gcom.micromedicao.hidrometro.HidrometroRelojoaria hidrometroRelojoaria) {
		this.hidrometroRelojoaria = hidrometroRelojoaria;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
	
	public Integer getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(Integer notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Short getTempoGarantiaAnos() {
		return tempoGarantiaAnos;
	}

	public void setTempoGarantiaAnos(Short tempoGarantiaAnos) {
		this.tempoGarantiaAnos = tempoGarantiaAnos;
	}

	public BigDecimal getVazaoMinima() {
		return vazaoMinima;
	}

	public void setVazaoMinima(BigDecimal vazaoMinima) {
		this.vazaoMinima = vazaoMinima;
	}

	public BigDecimal getVazaoNominal() {
		return vazaoNominal;
	}

	public void setVazaoNominal(BigDecimal vazaoNominal) {
		this.vazaoNominal = vazaoNominal;
	}

	public BigDecimal getVazaoTransicao() {
		return vazaoTransicao;
	}

	public void setVazaoTransicao(BigDecimal vazaoTransicao) {
		this.vazaoTransicao = vazaoTransicao;
	}

	/*
	 * Constutores Anteriores ao Mapeamento da Iteração
	 */
	/** full constructor */
	public Hidrometro(
			String numero,
			Date dataAquisicao,
			Short anoFabricacao,
			Short indicadorMacromedidor,
			Date dataUltimaRevisao,
			Date dataBaixa,
			Integer numeroLeituraAcumulada,
			Short numeroDigitosLeitura,
			Date ultimaAlteracao,
			gcom.micromedicao.hidrometro.HidrometroTipo hidrometroTipo,
			gcom.micromedicao.hidrometro.HidrometroSituacao hidrometroSituacao,
			gcom.micromedicao.hidrometro.HidrometroMarca hidrometroMarca,
			gcom.micromedicao.hidrometro.HidrometroCapacidade hidrometroCapacidade,
			gcom.micromedicao.hidrometro.HidrometroMotivoBaixa hidrometroMotivoBaixa,
			gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagem,
			gcom.micromedicao.hidrometro.HidrometroClasseMetrologica hidrometroClasseMetrologica,
			gcom.micromedicao.hidrometro.HidrometroDiametro hidrometroDiametro) {
		this.numero = numero;
		this.dataAquisicao = dataAquisicao;
		this.anoFabricacao = anoFabricacao;
		this.indicadorMacromedidor = indicadorMacromedidor;
		this.dataUltimaRevisao = dataUltimaRevisao;
		this.dataBaixa = dataBaixa;
		this.numeroLeituraAcumulada = numeroLeituraAcumulada;
		this.numeroDigitosLeitura = numeroDigitosLeitura;
		this.ultimaAlteracao = ultimaAlteracao;
		this.hidrometroTipo = hidrometroTipo;
		this.hidrometroSituacao = hidrometroSituacao;
		this.hidrometroMarca = hidrometroMarca;
		this.hidrometroCapacidade = hidrometroCapacidade;
		this.hidrometroMotivoBaixa = hidrometroMotivoBaixa;
		this.hidrometroLocalArmazenagem = hidrometroLocalArmazenagem;
		this.hidrometroClasseMetrologica = hidrometroClasseMetrologica;
		this.hidrometroDiametro = hidrometroDiametro;
	}
	
	
	/*
	 * Constutores Anteriores ao Mapeamento da Iteração
	 */
	/** full constructor */
	public Hidrometro(
			String numero,
			Date dataAquisicao,
			Short anoFabricacao,
			Short indicadorMacromedidor,
			Date dataUltimaRevisao,
			Date dataBaixa,
			Integer numeroLeituraAcumulada,
			Short numeroDigitosLeitura,
			Date ultimaAlteracao,
			gcom.micromedicao.hidrometro.HidrometroTipo hidrometroTipo,
			gcom.micromedicao.hidrometro.HidrometroSituacao hidrometroSituacao,
			gcom.micromedicao.hidrometro.HidrometroMarca hidrometroMarca,
			gcom.micromedicao.hidrometro.HidrometroCapacidade hidrometroCapacidade,
			gcom.micromedicao.hidrometro.HidrometroMotivoBaixa hidrometroMotivoBaixa,
			gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagem,
			gcom.micromedicao.hidrometro.HidrometroClasseMetrologica hidrometroClasseMetrologica,
			gcom.micromedicao.hidrometro.HidrometroDiametro hidrometroDiametro,
			HidrometroRelojoaria hidrometroRelojoaria,  BigDecimal vazaoTransicao,
			BigDecimal vazaoNominal, BigDecimal vazaoMinima, Integer notaFiscal,
			Short tempoGarantiaAnos ) {
		this.numero = numero;
		this.dataAquisicao = dataAquisicao;
		this.anoFabricacao = anoFabricacao;
		this.indicadorMacromedidor = indicadorMacromedidor;
		this.dataUltimaRevisao = dataUltimaRevisao;
		this.dataBaixa = dataBaixa;
		this.numeroLeituraAcumulada = numeroLeituraAcumulada;
		this.numeroDigitosLeitura = numeroDigitosLeitura;
		this.ultimaAlteracao = ultimaAlteracao;
		this.hidrometroTipo = hidrometroTipo;
		this.hidrometroSituacao = hidrometroSituacao;
		this.hidrometroMarca = hidrometroMarca;
		this.hidrometroCapacidade = hidrometroCapacidade;
		this.hidrometroMotivoBaixa = hidrometroMotivoBaixa;
		this.hidrometroLocalArmazenagem = hidrometroLocalArmazenagem;
		this.hidrometroClasseMetrologica = hidrometroClasseMetrologica;
		this.hidrometroDiametro = hidrometroDiametro;
		this.hidrometroRelojoaria = hidrometroRelojoaria;
		this.vazaoTransicao = vazaoTransicao;
		this.vazaoNominal = vazaoNominal;
		this.vazaoMinima = vazaoMinima;
		this.notaFiscal = notaFiscal;
		this.tempoGarantiaAnos = tempoGarantiaAnos;
		
	}

	/** minimal constructor */
	public Hidrometro(
			String numero,
			Date dataAquisicao,
			Short anoFabricacao,
			Date dataUltimaRevisao,
			Integer numeroLeituraAcumulada,
			gcom.micromedicao.hidrometro.HidrometroTipo hidrometroTipo,
			gcom.micromedicao.hidrometro.HidrometroSituacao hidrometroSituacao,
			gcom.micromedicao.hidrometro.HidrometroMarca hidrometroMarca,
			gcom.micromedicao.hidrometro.HidrometroCapacidade hidrometroCapacidade,
			gcom.micromedicao.hidrometro.HidrometroMotivoBaixa hidrometroMotivoBaixa,
			gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagem,
			gcom.micromedicao.hidrometro.HidrometroClasseMetrologica hidrometroClasseMetrologica,
			gcom.micromedicao.hidrometro.HidrometroDiametro hidrometroDiametro) {
		this.numero = numero;
		this.dataAquisicao = dataAquisicao;
		this.anoFabricacao = anoFabricacao;
		this.dataUltimaRevisao = dataUltimaRevisao;
		this.numeroLeituraAcumulada = numeroLeituraAcumulada;
		this.hidrometroTipo = hidrometroTipo;
		this.hidrometroSituacao = hidrometroSituacao;
		this.hidrometroMarca = hidrometroMarca;
		this.hidrometroCapacidade = hidrometroCapacidade;
		this.hidrometroMotivoBaixa = hidrometroMotivoBaixa;
		this.hidrometroLocalArmazenagem = hidrometroLocalArmazenagem;
		this.hidrometroClasseMetrologica = hidrometroClasseMetrologica;
		this.hidrometroDiametro = hidrometroDiametro;
	}

	/**
	 * Verifica a igualdade dos hidrometros para poder atualizar um conjunto de
	 * hidrometro
	 * 
	 * @param other
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public boolean equalsHidrometro(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof Hidrometro)) {
			return false;
		}
		Hidrometro castOther = (Hidrometro) other;
		
		//Verifica se o tipo de relojoaria é o mesmo 
		//Autor:Sávio Luiz Data:25/04/2008
		boolean mesmoTipoRolojoaria = false;
		if((this.getHidrometroRelojoaria()!= null && !this.getHidrometroRelojoaria().equals(""))&& 
		   (castOther.getHidrometroRelojoaria() != null && !castOther.getHidrometroRelojoaria().equals(""))){
			if(this.getHidrometroRelojoaria().getId().equals(castOther.getHidrometroRelojoaria().getId())){
				mesmoTipoRolojoaria = true;
			}
		}else{
			if((this.getHidrometroRelojoaria() == null ||  this.getHidrometroRelojoaria().equals("")) &&
			   (castOther.getHidrometroRelojoaria() == null ||  castOther.getHidrometroRelojoaria().equals(""))){
				mesmoTipoRolojoaria = true;
			}
		}

		return ((this.getDataAquisicao().equals(castOther.getDataAquisicao()))
				&& (this.getAnoFabricacao()
						.equals(castOther.getAnoFabricacao()))
				&& (this.getIndicadorMacromedidor().equals(castOther
						.getIndicadorMacromedidor()))
				&& (this.getHidrometroClasseMetrologica().getId()
						.equals(castOther.getHidrometroClasseMetrologica()
								.getId()))
				&& (this.getHidrometroMarca().getId().equals(castOther
						.getHidrometroMarca().getId()))
				&& (this.getHidrometroDiametro().getId().equals(castOther
						.getHidrometroDiametro().getId()))
				&& (this.getHidrometroCapacidade().getId().equals(castOther
						.getHidrometroCapacidade().getId())) 
				&& (mesmoTipoRolojoaria)		
				&& (this
				.getHidrometroTipo().getId().equals(castOther
				.getHidrometroTipo().getId())));
	}
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
		
		filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade(FiltroHidrometro.HIDROMETRO_TIPO);
		filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade(FiltroHidrometro.HIDROMETRO_SITUACAO);
		filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade(FiltroHidrometro.HIDROMETRO_MARCA);
		filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade(FiltroHidrometro.HIDROMETRO_CAPACIDADE);
		filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade(FiltroHidrometro.HIDROMETRO_DIAMETRO);
		filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade(FiltroHidrometro.HIDROMETRO_MOTIVO_BAIXA);
		filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade(FiltroHidrometro.HIDROMETRO_LOCAL_ARMAZENAGEM);
		filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade(FiltroHidrometro.HIDROMETRO_CLASSE_METROLOGICA);
		
		filtroHidrometro.adicionarParametro(
				new ParametroSimples(FiltroHidrometro.ID, this.getId()));
		return filtroHidrometro; 
	}
	
//	@Override
//	public String getDescricaoParaRegistroTransacao() {
//		if (this.getNumero() != null){
//			return this.getNumero();	
//		} 
//		return "";		
//	}
}
