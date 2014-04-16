package gcom.atendimentopublico.ligacaoesgoto;

import gcom.atendimentopublico.LigacaoOrigem;
import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao
public class LigacaoEsgoto extends ObjetoTransacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_ATUALIZAR_VOLUME_MINIMO = 277; 
		// Operacao.OPERACAO_VOLUME_MINIMO_LIGACAO_ESGOTO_ATUALIZAR
	public static final int ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO = 272;
		// Operacao.OPERACAO_LIGACAO_ESGOTO_EFETUAR 
	public static final int ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO_SEM_RA = 1002;
		// Operacao.OPERACAO_LIGACAO_ESGOTO__SEM_RA_EFETUAR
	public static final int ATRIBUTOS_EFETUAR_MUDANCA_SITUACAO_FATURAMENTO_LIGACAO_ESGOTO = 247;
		// Operacao.OPERACAO_MUDANCA_SITUACAO_FATURAMENTO_LIGACAO_ESGOTO
	public static final int ATRIBUTOS_ATUALIZAR_LIGACAO_ESGOTO = 430;
		// Operação.OPERACAO_LIGACAO_ESGOTO_ATUALIZAR
	
	public Filtro retornaFiltro() {
		FiltroLigacaoEsgoto filtroLigacaoEsgoto = new FiltroLigacaoEsgoto();

		filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(
				FiltroUsuario.ID, this.getId()));
		filtroLigacaoEsgoto
				.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoPerfil");
		filtroLigacaoEsgoto
				.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoDiametro");
		filtroLigacaoEsgoto
				.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoMaterial");
		filtroLigacaoEsgoto
		.adicionarCaminhoParaCarregamentoEntidade("imovel");

		return filtroLigacaoEsgoto;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO, ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO_SEM_RA,ATRIBUTOS_ATUALIZAR_LIGACAO_ESGOTO})
	private Date dataLigacao;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade=ATRIBUTOS_ATUALIZAR_VOLUME_MINIMO)
	private Integer consumoMinimo;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO, ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO_SEM_RA,ATRIBUTOS_ATUALIZAR_LIGACAO_ESGOTO})
	private BigDecimal percentual;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO, ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO_SEM_RA,ATRIBUTOS_ATUALIZAR_LIGACAO_ESGOTO})	
	private BigDecimal percentualAguaConsumidaColetada;

	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO, ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO_SEM_RA,ATRIBUTOS_ATUALIZAR_LIGACAO_ESGOTO})
	private Short indicadorCaixaGordura;
    
	private Short indicadorLigacaoEsgoto;
	
    private BigDecimal percentualAlternativo;
    
    private Integer numeroConsumoPercentualAlternativo;

	/** nullable persistent field */
	private Imovel imovel;

	/** persistent field */
	@ControleAlteracao(value=FiltroLigacaoEsgoto.LIGACAO_ESGOTO_PERFIL,
			funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO, ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO_SEM_RA,ATRIBUTOS_ATUALIZAR_LIGACAO_ESGOTO})	
	private gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil ligacaoEsgotoPerfil;

	/** persistent field */
	@ControleAlteracao(value=FiltroLigacaoEsgoto.LIGACAO_ESGOTO_DIAMETRO,
			funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO, ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO_SEM_RA,ATRIBUTOS_ATUALIZAR_LIGACAO_ESGOTO})	
	private gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro ligacaoEsgotoDiametro;

	/** persistent field */
	@ControleAlteracao(value=FiltroLigacaoEsgoto.LIGACAO_ESGOTO_MATERIAL,
			funcionalidade={ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO, ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO_SEM_RA,ATRIBUTOS_ATUALIZAR_LIGACAO_ESGOTO})
	private gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial ligacaoEsgotoMaterial;
	
	private LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento;
	
	private LigacaoEsgotoDestinoDejetos ligacaoEsgotoDestinoDejetos;
	
	private LigacaoEsgotoCaixaInspecao ligacaoEsgotoCaixaInspecao;
	
	private LigacaoEsgotoDestinoAguasPluviais ligacaoEsgotoDestinoAguasPluviais;
	
	/** nullable persistent field */
	private LigacaoOrigem ligacaoOrigem;
	
	public final static Short INDICADOR_COM_CAIXA_GORDURA = new Short("1");
	
	public final static Short INDICADOR_SEM_CAIXA_GORDURA = new Short("2");

	/** full constructor */
	public LigacaoEsgoto(
			Date dataLigacao,
			Integer consumoMinimo,
			BigDecimal percentual,
			BigDecimal percentualAguaConsumidaColetada,
			Date ultimaAlteracao,
			Short indicadorCaixaGordura,
			Short indicadorLigacaoEsgoto,
			Imovel imovel,
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil ligacaoEsgotoPerfil,
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro ligacaoEsgotoDiametro,
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial ligacaoEsgotoMaterial) {
		this.dataLigacao = dataLigacao;
		this.consumoMinimo = consumoMinimo;
		this.percentual = percentual;
		this.percentualAguaConsumidaColetada = percentualAguaConsumidaColetada;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorCaixaGordura = indicadorCaixaGordura;
		this.indicadorLigacaoEsgoto = indicadorLigacaoEsgoto;
		this.imovel = imovel;
		this.ligacaoEsgotoPerfil = ligacaoEsgotoPerfil;
		this.ligacaoEsgotoDiametro = ligacaoEsgotoDiametro;
		this.ligacaoEsgotoMaterial = ligacaoEsgotoMaterial;
	}

	/** default constructor */
	public LigacaoEsgoto() {
	}

	/** minimal constructor */
	public LigacaoEsgoto(
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil ligacaoEsgotoPerfil,
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro ligacaoEsgotoDiametro,
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial ligacaoEsgotoMaterial) {
		this.ligacaoEsgotoPerfil = ligacaoEsgotoPerfil;
		this.ligacaoEsgotoDiametro = ligacaoEsgotoDiametro;
		this.ligacaoEsgotoMaterial = ligacaoEsgotoMaterial;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataLigacao() {
		return this.dataLigacao;
	}

	public void setDataLigacao(Date dataLigacao) {
		this.dataLigacao = dataLigacao;
	}

	public Integer getConsumoMinimo() {
		return this.consumoMinimo;
	}

	public void setConsumoMinimo(Integer consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}

	public BigDecimal getPercentual() {
		return this.percentual;
	}

	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}

	public BigDecimal getPercentualAguaConsumidaColetada() {
		return this.percentualAguaConsumidaColetada;
	}

	public void setPercentualAguaConsumidaColetada(
			BigDecimal percentualAguaConsumidaColetada) {
		this.percentualAguaConsumidaColetada = percentualAguaConsumidaColetada;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil getLigacaoEsgotoPerfil() {
		return this.ligacaoEsgotoPerfil;
	}

	public void setLigacaoEsgotoPerfil(
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil ligacaoEsgotoPerfil) {
		this.ligacaoEsgotoPerfil = ligacaoEsgotoPerfil;
	}

	public gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro getLigacaoEsgotoDiametro() {
		return this.ligacaoEsgotoDiametro;
	}

	public void setLigacaoEsgotoDiametro(
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro ligacaoEsgotoDiametro) {
		this.ligacaoEsgotoDiametro = ligacaoEsgotoDiametro;
	}

	public gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial getLigacaoEsgotoMaterial() {
		return this.ligacaoEsgotoMaterial;
	}

	public void setLigacaoEsgotoMaterial(
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial ligacaoEsgotoMaterial) {
		this.ligacaoEsgotoMaterial = ligacaoEsgotoMaterial;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * @return Retorna o campo indicadorCaixaGordura.
	 */
	public Short getIndicadorCaixaGordura() {
		return indicadorCaixaGordura;
	}

	/**
	 * @param indicadorCaixaGordura O indicadorCaixaGordura a ser setado.
	 */
	public void setIndicadorCaixaGordura(Short indicadorCaixaGordura) {
		this.indicadorCaixaGordura = indicadorCaixaGordura;
	}

    public Integer getNumeroConsumoPercentualAlternativo() {
        return numeroConsumoPercentualAlternativo;
    }

    public void setNumeroConsumoPercentualAlternativo(Integer numeroConsumoPercentualAlternativo) {
        this.numeroConsumoPercentualAlternativo = numeroConsumoPercentualAlternativo;
    }

    public BigDecimal getPercentualAlternativo() {
        return percentualAlternativo;
    }

    public void setPercentualAlternativo(BigDecimal percentualAlternativo) {
        this.percentualAlternativo = percentualAlternativo;
    }

	
	public LigacaoEsgotoDestinoAguasPluviais getLigacaoEsgotoDestinoAguasPluviais() {
	
		return ligacaoEsgotoDestinoAguasPluviais;
	}

	
	public void setLigacaoEsgotoDestinoAguasPluviais(
			LigacaoEsgotoDestinoAguasPluviais ligacaoEsgotoDestinoAguasPluviais) {
	
		this.ligacaoEsgotoDestinoAguasPluviais = ligacaoEsgotoDestinoAguasPluviais;
	}

	
	public LigacaoEsgotoCaixaInspecao getLigacaoEsgotoCaixaInspecao() {
	
		return ligacaoEsgotoCaixaInspecao;
	}

	
	public void setLigacaoEsgotoCaixaInspecao(
			LigacaoEsgotoCaixaInspecao ligacaoEsgotoCaixaInspecao) {
	
		this.ligacaoEsgotoCaixaInspecao = ligacaoEsgotoCaixaInspecao;
	}

	
	public LigacaoEsgotoDestinoDejetos getLigacaoEsgotoDestinoDejetos() {
	
		return ligacaoEsgotoDestinoDejetos;
	}

	
	public void setLigacaoEsgotoDestinoDejetos(
			LigacaoEsgotoDestinoDejetos ligacaoEsgotoDestinoDejetos) {
	
		this.ligacaoEsgotoDestinoDejetos = ligacaoEsgotoDestinoDejetos;
	}

	
	public LigacaoEsgotoEsgotamento getLigacaoEsgotoEsgotamento() {
	
		return ligacaoEsgotoEsgotamento;
	}

	
	public void setLigacaoEsgotoEsgotamento(
			LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento) {
	
		this.ligacaoEsgotoEsgotamento = ligacaoEsgotoEsgotamento;
	}

	public LigacaoOrigem getLigacaoOrigem() {
		return ligacaoOrigem;
	}

	public void setLigacaoOrigem(LigacaoOrigem ligacaoOrigem) {
		this.ligacaoOrigem = ligacaoOrigem;
	}

	public Short getIndicadorLigacaoEsgoto() {
		return indicadorLigacaoEsgoto;
	}

	public void setIndicadorLigacaoEsgoto(Short indicadorLigacaoEsgoto) {
		this.indicadorLigacaoEsgoto = indicadorLigacaoEsgoto;
	}
}
