package gcom.financeiro;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class ResumoReceita implements Serializable {

	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;
    
    /** nullable persistent field */
    private Integer anoMesReferencia;
    
    private Date dataRealizada;

    /** nullable persistent field */
    private BigDecimal valorReceita;
    
    private Date ultimaAlteracao;
    
    private Banco banco;
    
    private Arrecadador arrecadador;
    
    private ContaContabil contaContabil;
    
    private GerenciaRegional gerenciaRegional;
    
    private Localidade localidade;
    
    private Categoria categoria;
    
    private ContaBancaria contaBancaria;
    
    private UnidadeNegocio unidadeNegocio;

   
	/** default constructor */
    public ResumoReceita() {
    }

 	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroResumoReceita filtroResumoReceita = new FiltroResumoReceita();
		
		filtroResumoReceita.adicionarCaminhoParaCarregamentoEntidade("banco");
		filtroResumoReceita.adicionarCaminhoParaCarregamentoEntidade("arrecadador");
		filtroResumoReceita.adicionarCaminhoParaCarregamentoEntidade("contaContabil");
		filtroResumoReceita.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		filtroResumoReceita.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroResumoReceita.adicionarCaminhoParaCarregamentoEntidade("categoria");
		filtroResumoReceita.adicionarCaminhoParaCarregamentoEntidade("contaBancaria");
		filtroResumoReceita.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
		filtroResumoReceita.adicionarParametro(
				new ParametroSimples(FiltroResumoReceita.ID, this.getId()));
		
		return filtroResumoReceita; 
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Arrecadador getArrecadador() {
		return arrecadador;
	}

	public void setArrecadador(Arrecadador arrecadador) {
		this.arrecadador = arrecadador;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public ContaContabil getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(ContaContabil contaContabil) {
		this.contaContabil = contaContabil;
	}

	public Date getDataRealizada() {
		return dataRealizada;
	}

	public void setDataRealizada(Date dataRealizada) {
		this.dataRealizada = dataRealizada;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorReceita() {
		return valorReceita;
	}

	public void setValorReceita(BigDecimal valorReceita) {
		this.valorReceita = valorReceita;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

}    


   
