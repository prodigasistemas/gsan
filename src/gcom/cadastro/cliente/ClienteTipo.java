package gcom.cadastro.cliente;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao
public class ClienteTipo extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private Short indicadorPessoaFisicaJuridica;
    private Short indicadorUso;
    private Date ultimaAlteracao;
    private String descricaoComId;

    private EsferaPoder esferaPoder;

    public final static Short INDICADOR_PESSOA_FISICA = new Short("1");
    public final static Short INDICADOR_PESSOA_JURIDICA = new Short("2");

    public ClienteTipo(String descricao, Short indicadorPessoaFisicaJuridica, Short indicadorUso, Date ultimaAlteracao, EsferaPoder esferaPoder) {
        this.descricao = descricao;
        this.indicadorPessoaFisicaJuridica = indicadorPessoaFisicaJuridica;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.esferaPoder = esferaPoder;
    }

    public ClienteTipo() {
    }
    
    public ClienteTipo(Integer id) {
    	this.id = id;
    }

    public ClienteTipo(gcom.cadastro.cliente.EsferaPoder esferaPoder) {
        this.esferaPoder = esferaPoder;
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

    public Short getIndicadorPessoaFisicaJuridica() {
        return this.indicadorPessoaFisicaJuridica;
    }

    public void setIndicadorPessoaFisicaJuridica(
            Short indicadorPessoaFisicaJuridica) {
        this.indicadorPessoaFisicaJuridica = indicadorPessoaFisicaJuridica;
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

    public gcom.cadastro.cliente.EsferaPoder getEsferaPoder() {
        return this.esferaPoder;
    }

    public void setEsferaPoder(gcom.cadastro.cliente.EsferaPoder esferaPoder) {
        this.esferaPoder = esferaPoder;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
        
    public String[] retornaCamposChavePrimaria() {
    	String[] retorno = {"id"};
    	return retorno;
    }
        
    public Filtro retornaFiltro(){
        FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();
        filtroClienteTipo.adicionarParametro(new ParametroSimples(FiltroClienteTipo.ID,this.getId()));
        return filtroClienteTipo;
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
		
		String descricao = getDescricao();
		
		if (getDescricao() != null){
			if (indicadorPessoaFisicaJuridica.shortValue() == INDICADOR_PESSOA_FISICA.shortValue()){
				descricao += " (PF)";
			} else {
				descricao += " (PJ)";
			}				
		}
			return descricao;
	}
}
