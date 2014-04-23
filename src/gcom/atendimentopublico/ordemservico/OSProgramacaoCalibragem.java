package gcom.atendimentopublico.ordemservico;


import gcom.cadastro.endereco.FiltroOSProgramaCalibragem;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * [UC1185] Informar Calibragem
 * 
 * @author Thúlio Araújo
 *
 * @date 20/06/2011
 */
@ControleAlteracao()
public class OSProgramacaoCalibragem extends ObjetoTransacao {
	public static final int ATUALIZAR_CALIBRAGEM = 1813;
	private static final long serialVersionUID = 1L;
	
	//public static final int ATUALIZAR_IMPORTANCIA_LOGRADOURO = 1816;
	/** identifier field */
	
	
	private Integer id;
	private OSPriorizacaoTipo priorizacaoTipo;
	private Integer grauImportancia;
	private Integer faixaInicial;
	private Integer faixaFinal;
	
	@ControleAlteracao(funcionalidade={ATUALIZAR_CALIBRAGEM})
	private Integer peso;
	@ControleAlteracao(funcionalidade={ATUALIZAR_CALIBRAGEM})
	private Integer fator;
	private Date ultimaAlteracao;
	
	public OSProgramacaoCalibragem(){
	}

	public Integer getFaixaFinal() {
		return faixaFinal;
	}

	public void setFaixaFinal(Integer faixaFinal) {
		this.faixaFinal = faixaFinal;
	}

	public Integer getFaixaInicial() {
		return faixaInicial;
	}

	public void setFaixaInicial(Integer faixaInicial) {
		this.faixaInicial = faixaInicial;
	}

	public Integer getFator() {
		return fator;
	}

	public void setFator(Integer fator) {
		this.fator = fator;
	}

	public Integer getGrauImportancia() {
		return grauImportancia;
	}

	public void setGrauImportancia(Integer grauImportancia) {
		this.grauImportancia = grauImportancia;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

	@Override
	public Filtro retornaFiltro() {
		// TODO Auto-generated method stub
		FiltroOSProgramaCalibragem filtroOSProgramacaoCalibragem = new FiltroOSProgramaCalibragem();
		
		filtroOSProgramacaoCalibragem.adicionarCaminhoParaCarregamentoEntidade("priorizacaoTipo");
		filtroOSProgramacaoCalibragem.adicionarParametro(
				new ParametroSimples(FiltroOSProgramaCalibragem.ID, this.getId()));
		return filtroOSProgramacaoCalibragem; 
	}
	
	public OSPriorizacaoTipo getPriorizacaoTipo() {
		return priorizacaoTipo;
	}

	public void setPriorizacaoTipo(OSPriorizacaoTipo priorizacaoTipo) {
		this.priorizacaoTipo = priorizacaoTipo;
	}

	
	public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
		
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroOSProgramaCalibragem.ID,this.getId()));
		
		return filtro;
	}
		
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}
	
	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
	}
}
