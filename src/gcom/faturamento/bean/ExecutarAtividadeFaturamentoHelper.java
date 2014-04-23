package gcom.faturamento.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe que irá auxiliar no formato do retorno da pesquisa das atividades de faturamento do cronograma
 * que foram comandadas 
 *
 * @author Raphael Rossiter
 * @date 29/03/2006
 */
public class ExecutarAtividadeFaturamentoHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idFaturamentoAtividadeCronograma;
	private Integer idGrupoFaturamento;
	private Integer anoMesFaturamento;
	private String descricaoAtividade;
	private Date dataPrevista;
	private Date comando;
	
	public ExecutarAtividadeFaturamentoHelper(){}
	
	
	public ExecutarAtividadeFaturamentoHelper(Integer idFaturamentoAtividadeCronograma, Integer idGrupoFaturamento,
			Integer anoMesFaturamento, String descricaoAtividade, Date dataPrevista, Date comando){
		
		this.idFaturamentoAtividadeCronograma = idFaturamentoAtividadeCronograma;
		this.idGrupoFaturamento = idGrupoFaturamento;
		this.anoMesFaturamento = anoMesFaturamento;
		this.descricaoAtividade = descricaoAtividade;
		this.dataPrevista = dataPrevista;
		this.comando = comando; 
	}


	public Integer getAnoMesFaturamento() {
		return anoMesFaturamento;
	}


	public void setAnoMesFaturamento(Integer anoMesFaturamento) {
		this.anoMesFaturamento = anoMesFaturamento;
	}


	public Date getComando() {
		return comando;
	}


	public void setComando(Date comando) {
		this.comando = comando;
	}


	public Date getDataPrevista() {
		return dataPrevista;
	}


	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}


	public String getDescricaoAtividade() {
		return descricaoAtividade;
	}


	public void setDescricaoAtividade(String descricaoAtividade) {
		this.descricaoAtividade = descricaoAtividade;
	}


	public Integer getIdFaturamentoAtividadeCronograma() {
		return idFaturamentoAtividadeCronograma;
	}


	public void setIdFaturamentoAtividadeCronograma(
			Integer idFaturamentoAtividadeCronograma) {
		this.idFaturamentoAtividadeCronograma = idFaturamentoAtividadeCronograma;
	}


	public Integer getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}


	public void setIdGrupoFaturamento(Integer idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}
	
	
	
}
