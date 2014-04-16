package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

public class ReplicarValorCobrancaServicoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String tipoServico;
	private String nomeTipoServico;
	private String subCategoria;
	private String nomeSubCategoria;
	private String perfilImovel;
	private String indicadorMedido;
	private String capacidadeHidrometro;
	private String valorServico;
	private String dataVigenciaInicial;
	private String dataVigenciaFinal;
	private String indicadorTipoServicoEconomia;
	private String indicadorFaixaEconomiaInicial;
	private String indicadorFaixaEconomiaFinal;
	private String novaDataVigenciaInicial;
	private String novaDataVigenciaFinal;
	private String indiceParaCorrecao;
	private String[] idRegistrosSelecionados;
	private boolean mensagem;
	
	private Collection<ServicoCobrancaValor> collServicoCobrancaValor;
	
	public ReplicarValorCobrancaServicoActionForm(){
		
		this.collServicoCobrancaValor = new ArrayList<ServicoCobrancaValor>();
		
	}
	
	public String getValorServico() {
		return valorServico;
	}
	public void setValorServico(String valorServico) {
		this.valorServico = valorServico;
	}
	public String getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}
	public void setCapacidadeHidrometro(String capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}
	public String getIndicadorMedido() {
		return indicadorMedido;
	}
	public void setIndicadorMedido(String indicadorMedido) {
		this.indicadorMedido = indicadorMedido;
	}
	public String getPerfilImovel() {
		return perfilImovel;
	}
	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
	public String getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}
	public String getNomeTipoServico() {
		return nomeTipoServico;
	}
	public void setNomeTipoServico(String nomeTipoServico) {
		this.nomeTipoServico = nomeTipoServico;
	}
	public String getNomeSubCategoria() {
		return nomeSubCategoria;
	}
	public void setNomeSubCategoria(String nomeSubCategoria) {
		this.nomeSubCategoria = nomeSubCategoria;
	}
	public String getSubCategoria() {
		return subCategoria;
	}
	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
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
	public String getIndicadorFaixaEconomiaFinal() {
		return indicadorFaixaEconomiaFinal;
	}
	public void setIndicadorFaixaEconomiaFinal(String indicadorFaixaEconomiaFinal) {
		this.indicadorFaixaEconomiaFinal = indicadorFaixaEconomiaFinal;
	}
	public String getIndicadorFaixaEconomiaInicial() {
		return indicadorFaixaEconomiaInicial;
	}
	public void setIndicadorFaixaEconomiaInicial(
			String indicadorFaixaEconomiaInicial) {
		this.indicadorFaixaEconomiaInicial = indicadorFaixaEconomiaInicial;
	}
	public String getIndicadorTipoServicoEconomia() {
		return indicadorTipoServicoEconomia;
	}
	public void setIndicadorTipoServicoEconomia(String indicadorTipoServicoEconomia) {
		this.indicadorTipoServicoEconomia = indicadorTipoServicoEconomia;
	}

	public Collection<ServicoCobrancaValor> getCollServicoCobrancaValor() {
		return collServicoCobrancaValor;
	}

	public void setCollServicoCobrancaValor(
			Collection<ServicoCobrancaValor> collServicoCobrancaValor) {
		this.collServicoCobrancaValor = collServicoCobrancaValor;
	}

	public boolean getMensagem() {
		return mensagem;
	}

	public void setMensagem(boolean mensagem) {
		this.mensagem = mensagem;
	}

	public String getIndiceParaCorrecao() {
		return indiceParaCorrecao;
	}

	public void setIndiceParaCorrecao(String indiceParaCorrecao) {
		this.indiceParaCorrecao = indiceParaCorrecao;
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
	
	public String[] getIdRegistrosSelecionados() {
		return idRegistrosSelecionados;
	}

	public void setIdRegistrosSelecionados(String[] idRegistrosSelecionados) {
		this.idRegistrosSelecionados = idRegistrosSelecionados;
	}
	
}
