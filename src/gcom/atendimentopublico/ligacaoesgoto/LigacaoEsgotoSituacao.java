package gcom.atendimentopublico.ligacaoesgoto;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class LigacaoEsgotoSituacao extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String descricao;
	private String descricaoAbreviado;
	private Short indicadorUso;
	private Date ultimaAlteracao;
	private Short indicadorFaturamentoSituacao;
	private Integer volumeMinimoFaturamento;
	private Short indicadorExistenciaRede;
	private Short indicadorExistenciaLigacao;
	private String descricaoComId;

	public final static Integer POTENCIAL = new Integer(1);
	public final static Integer FACTIVEL = new Integer(2);
	public final static Integer LIGADO = new Integer(3);
	public final static Integer EM_FISCALIZACAO = new Integer(4);
	public final static Integer LIG_FORA_DE_USO = new Integer(5);
	public final static Integer TAMPONADO = new Integer(6);

	public final static String SITUACAO_LIGADO_FORA_DE_USO = new String("LIGADO FORA DE USO");
	public final static String SITUACAO_LIGADO = new String("LIGADO");

	public final static Integer SITUACAO_TAMPONADO = new Integer(1);
	public final static Integer SITUACAO_DESATIVACAO = new Integer(2);
	public final static Integer SITUACAO_RESTABELECIMENTO = new Integer(3);
	public final static Integer SITUACAO_REATIVACAO = new Integer(4);
	
	public final static Short FATURAMENTO_ATIVO = new Short("1");
	
	public final static Short INDICADOR_EXISTENCIA_REDE_SIM = new Short("1");
    public final static Short INDICADOR_EXISTENCIA_REDE_NAO = new Short("2");
    
    public final static Short INDICADOR_EXISTENCIA_LIGACAO_SIM = new Short("1");
    public final static Short INDICADOR_EXISTENCIA_LIGACAO_NAO = new Short("2");

	public LigacaoEsgotoSituacao(String descricao, Short indicadorUso,
			Date ultimaAlteracao, Short indicadorFaturamentoSituacao, Integer volumeMinimoFaturamento) {
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorFaturamentoSituacao = indicadorFaturamentoSituacao;
		this.volumeMinimoFaturamento = volumeMinimoFaturamento;
	}

	public LigacaoEsgotoSituacao(String descricao, String descricaoAbreviado,
			Short indicadorUso, Date ultimaAlteracao, Short indicadorFaturamentoSituacao, Integer volumeMinimoFaturamento) {
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.descricaoAbreviado = descricaoAbreviado;
		this.indicadorFaturamentoSituacao = indicadorFaturamentoSituacao;
		this.volumeMinimoFaturamento = volumeMinimoFaturamento;
	}

	public LigacaoEsgotoSituacao() {
	}
	
	public LigacaoEsgotoSituacao(Integer id) {
		this.id = id;
	}

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

	public Short getIndicadorExistenciaLigacao() {
		return indicadorExistenciaLigacao;
	}

	public void setIndicadorExistenciaLigacao(Short indicadorExistenciaLigacao) {
		this.indicadorExistenciaLigacao = indicadorExistenciaLigacao;
	}

	public Short getIndicadorExistenciaRede() {
		return indicadorExistenciaRede;
	}

	public void setIndicadorExistenciaRede(Short indicadorExistenciaRede) {
		this.indicadorExistenciaRede = indicadorExistenciaRede;
	}
	
	public boolean possuiFaturamentoAtivo() {
		return this.indicadorFaturamentoSituacao.intValue() == LigacaoEsgotoSituacao.FATURAMENTO_ATIVO.intValue();
	}
}
