package gcom.atendimentopublico.ligacaoesgoto;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LigacaoEsgotoSituacao extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private String descricaoAbreviado;

	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	/** nullable persistent field */
	private Short indicadorFaturamentoSituacao;
	
	/** nullable persistent field */
	private Integer volumeMinimoFaturamento;
	
	private Short indicadorExistenciaRede;
	
	private Short indicadorExistenciaLigacao;
	
	/**
	 * @since 19/09/2007
	 */
	private String descricaoComId;

	// ---CONSTANTES SISTEMA

	public final static Integer POTENCIAL = new Integer(1);

	public final static Integer FACTIVEL = new Integer(2);

	public final static Integer LIGADO = new Integer(3);

	public final static Integer EM_FISCALIZACAO = new Integer(4);

	public final static Integer LIG_FORA_DE_USO = new Integer(5);

	public final static Integer TAMPONADO = new Integer(6);

	// Descrição Tipo de Serviços

	public final static String SITUACAO_LIGADO_FORA_DE_USO = new String(
			"LIGADO FORA DE USO");

	public final static String SITUACAO_LIGADO = new String("LIGADO");

	// Descrição Tipo de Serviços
	public final static Integer SITUACAO_TAMPONADO = new Integer(1);

	public final static Integer SITUACAO_DESATIVACAO = new Integer(2);

	public final static Integer SITUACAO_RESTABELECIMENTO = new Integer(3);

	public final static Integer SITUACAO_REATIVACAO = new Integer(4);
	
	public final static Short FATURAMENTO_ATIVO = new Short("1");
	
	public final static Short INDICADOR_EXISTENCIA_REDE_SIM = new Short("1");
    
    public final static Short INDICADOR_EXISTENCIA_REDE_NAO = new Short("2");
    
    public final static Short INDICADOR_EXISTENCIA_LIGACAO_SIM = new Short("1");
    
    public final static Short INDICADOR_EXISTENCIA_LIGACAO_NAO = new Short("2");

	/** full constructor */
	public LigacaoEsgotoSituacao(String descricao, Short indicadorUso,
			Date ultimaAlteracao, Short indicadorFaturamentoSituacao, Integer volumeMinimoFaturamento) {
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorFaturamentoSituacao = indicadorFaturamentoSituacao;
		this.volumeMinimoFaturamento = volumeMinimoFaturamento;
	}

	/** full constructor */
	public LigacaoEsgotoSituacao(String descricao, String descricaoAbreviado,
			Short indicadorUso, Date ultimaAlteracao, Short indicadorFaturamentoSituacao, Integer volumeMinimoFaturamento) {
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.descricaoAbreviado = descricaoAbreviado;
		this.indicadorFaturamentoSituacao = indicadorFaturamentoSituacao;
		this.volumeMinimoFaturamento = volumeMinimoFaturamento;
	}

	/** default constructor */
	public LigacaoEsgotoSituacao() {
	}

	/**
	 * @return Retorna o campo descricaoAbreviado.
	 */
	public String getDescricaoAbreviado() {
		return descricaoAbreviado;
	}

	public Short getIndicadorFaturamentoSituacao() {
		return indicadorFaturamentoSituacao;
	}

	public void setIndicadorFaturamentoSituacao(Short indicadorFaturamentoSituacao) {
		this.indicadorFaturamentoSituacao = indicadorFaturamentoSituacao;
	}

	public Integer getVolumeMinimoFaturamento() {
		return volumeMinimoFaturamento;
	}

	public void setVolumeMinimoFaturamento(Integer volumeMinimoFaturamento) {
		this.volumeMinimoFaturamento = volumeMinimoFaturamento;
	}

	/**
	 * @param descricaoAbreviado
	 *            O descricaoAbreviado a ser setado.
	 */
	public void setDescricaoAbreviado(String descricaoAbreviado) {
		this.descricaoAbreviado = descricaoAbreviado;
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
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroLigacaoEsgotoSituacao filtro = new FiltroLigacaoEsgotoSituacao();

		filtro.adicionarParametro(new ParametroSimples(
				FiltroLigacaoEsgoto.ID, this.getId()));
		return filtro;
	}
	
	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 19/09/2007
	 *
	 * @return
	 */
	public String getDescricaoComId() {
		
		if(this.getId().compareTo(10) == -1){
			descricaoComId = "0" + getId()+ " - " + getDescricao();
		}else{
			descricaoComId = getId()+ " - " + getDescricao();
		}
		
		return descricaoComId;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getDescricao();
	}
	
	@Override
	public void initializeLazy() {
		getDescricaoParaRegistroTransacao();
	}

	/**
	 * @return Retorna o campo indicadorExistenciaLigacao.
	 */
	public Short getIndicadorExistenciaLigacao() {
		return indicadorExistenciaLigacao;
	}

	/**
	 * @param indicadorExistenciaLigacao O indicadorExistenciaLigacao a ser setado.
	 */
	public void setIndicadorExistenciaLigacao(Short indicadorExistenciaLigacao) {
		this.indicadorExistenciaLigacao = indicadorExistenciaLigacao;
	}

	/**
	 * @return Retorna o campo indicadorExistenciaRede.
	 */
	public Short getIndicadorExistenciaRede() {
		return indicadorExistenciaRede;
	}

	/**
	 * @param indicadorExistenciaRede O indicadorExistenciaRede a ser setado.
	 */
	public void setIndicadorExistenciaRede(Short indicadorExistenciaRede) {
		this.indicadorExistenciaRede = indicadorExistenciaRede;
	}
}
