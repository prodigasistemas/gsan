package gcom.gui.faturamento.debito;

import gcom.faturamento.debito.DebitoTipoVigencia;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

public class ReplicarDebitoTipoVigenciaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String debitoTipo;
	private String nomeDebitoTipo;
	private String valorServico;
	private String dataVigenciaInicial;
	private String dataVigenciaFinal;
	private String novaDataVigenciaInicial;
	private String novaDataVigenciaFinal;
	private String indiceParaCorrecao;
	private String[] idRegistrosSelecionados;
	private boolean mensagem;
	
	private Collection<DebitoTipoVigencia> collDebitoTipoVigencia;
	
	public ReplicarDebitoTipoVigenciaActionForm(){
		
		this.collDebitoTipoVigencia = new ArrayList<DebitoTipoVigencia>();
		
	}

	public Collection<DebitoTipoVigencia> getCollDebitoTipoVigencia() {
		return collDebitoTipoVigencia;
	}

	public void setCollDebitoTipoVigencia(
			Collection<DebitoTipoVigencia> collDebitoTipoVigencia) {
		this.collDebitoTipoVigencia = collDebitoTipoVigencia;
	}

	public String getDataVigenciaFinal() {
		return dataVigenciaFinal;
	}

	public void setDataVigenciaFinal(String dataVigenciaFinal) {
		this.dataVigenciaFinal = dataVigenciaFinal;
	}

	public String getDataVigenciaInicial() {
		return dataVigenciaInicial;
	}

	public void setDataVigenciaInicial(String dataVigenciaInicial) {
		this.dataVigenciaInicial = dataVigenciaInicial;
	}

	public String getDebitoTipo() {
		return debitoTipo;
	}

	public void setDebitoTipo(String debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	public String getIndiceParaCorrecao() {
		return indiceParaCorrecao;
	}

	public void setIndiceParaCorrecao(String indiceParaCorrecao) {
		this.indiceParaCorrecao = indiceParaCorrecao;
	}

	public boolean isMensagem() {
		return mensagem;
	}

	public void setMensagem(boolean mensagem) {
		this.mensagem = mensagem;
	}

	public String getNomeDebitoTipo() {
		return nomeDebitoTipo;
	}

	public void setNomeDebitoTipo(String nomeDebitoTipo) {
		this.nomeDebitoTipo = nomeDebitoTipo;
	}

	public String getNovaDataVigenciaFinal() {
		return novaDataVigenciaFinal;
	}

	public void setNovaDataVigenciaFinal(String novaDataVigenciaFinal) {
		this.novaDataVigenciaFinal = novaDataVigenciaFinal;
	}

	public String getNovaDataVigenciaInicial() {
		return novaDataVigenciaInicial;
	}

	public void setNovaDataVigenciaInicial(String novaDataVigenciaInicial) {
		this.novaDataVigenciaInicial = novaDataVigenciaInicial;
	}

	public String getValorServico() {
		return valorServico;
	}

	public void setValorServico(String valorServico) {
		this.valorServico = valorServico;
	}

	public String[] getIdRegistrosSelecionados() {
		return idRegistrosSelecionados;
	}

	public void setIdRegistrosSelecionados(String[] idRegistrosSelecionados) {
		this.idRegistrosSelecionados = idRegistrosSelecionados;
	}
	
	
	
}
