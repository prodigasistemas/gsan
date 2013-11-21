package gcom.atendimentopublico;

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.LocalOcorrencia;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class EspecificacaoPavimentacaoServicoTipo extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;
	private LocalOcorrencia localOcorrencia;
	private PavimentoCalcada pavimentoCalcada;
	private PavimentoRua pavimentoRua;
	private ServicoTipo servicoTipo;
	private Date ultimaAlteracao;
	
	
	@Override
	public boolean equals(Object obj) {
		boolean retorno = false;
		EspecificacaoPavimentacaoServicoTipo espPavSerTip = (EspecificacaoPavimentacaoServicoTipo) obj;
		
		if(espPavSerTip.getSolicitacaoTipoEspecificacao().getId().equals(this.solicitacaoTipoEspecificacao.getId()) 
				&& espPavSerTip.getLocalOcorrencia().getId().equals(this.localOcorrencia.getId()) 
				&& espPavSerTip.getPavimentoCalcada().getId().equals(this.pavimentoCalcada.getId())
				&& espPavSerTip.getPavimentoRua().getId().equals(this.pavimentoRua.getId()) 
				&& espPavSerTip.getServicoTipo().getId().equals(this.servicoTipo.getId())
				){
			retorno = true;
		}
		return retorno;
	}
	
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
	
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroEspecificacaoPavimentacaoServicoTipo filtroEspecificaoPavimentacaoServicoTipo = new FiltroEspecificacaoPavimentacaoServicoTipo();
		filtroEspecificaoPavimentacaoServicoTipo.adicionarCaminhoParaCarregamentoEntidade(FiltroEspecificacaoPavimentacaoServicoTipo.LOCALOCORRENCIA);
		filtroEspecificaoPavimentacaoServicoTipo.adicionarCaminhoParaCarregamentoEntidade(FiltroEspecificacaoPavimentacaoServicoTipo.PAVIMENTOCALCADA);
		filtroEspecificaoPavimentacaoServicoTipo.adicionarCaminhoParaCarregamentoEntidade(FiltroEspecificacaoPavimentacaoServicoTipo.PAVIMENTORUA);
		filtroEspecificaoPavimentacaoServicoTipo.adicionarCaminhoParaCarregamentoEntidade(FiltroEspecificacaoPavimentacaoServicoTipo.SERVICOTIPO);
		filtroEspecificaoPavimentacaoServicoTipo.adicionarCaminhoParaCarregamentoEntidade(FiltroEspecificacaoPavimentacaoServicoTipo.SOLICITACAOTIPOESPECIFICACAO);
		filtroEspecificaoPavimentacaoServicoTipo.adicionarParametro(new ParametroSimples(FiltroEspecificacaoPavimentacaoServicoTipo.ID, this.getId()));
		return filtroEspecificaoPavimentacaoServicoTipo; 
	}    
	
	//Getters and Setters
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public LocalOcorrencia getLocalOcorrencia() {
		return localOcorrencia;
	}
	
	public void setLocalOcorrencia(LocalOcorrencia localOcorrencia) {
		this.localOcorrencia = localOcorrencia;
	}
	
	public PavimentoCalcada getPavimentoCalcada() {
		return pavimentoCalcada;
	}
	
	public void setPavimentoCalcada(PavimentoCalcada pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}
	
	public PavimentoRua getPavimentoRua() {
		return pavimentoRua;
	}
	
	public void setPavimentoRua(PavimentoRua pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}
	
	public ServicoTipo getServicoTipo() {
		return servicoTipo;
	}
	
	public void setServicoTipo(ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}
	
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacao() {
		return solicitacaoTipoEspecificacao;
	}

	public void setSolicitacaoTipoEspecificacao(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
	}	
}