package gcom.cadastro.localidade;

import gcom.cadastro.geografico.Municipio;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.DescriptorEntity;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class SetorComercial extends ObjetoTransacao implements Serializable, DescriptorEntity{
	
	private static final long serialVersionUID = 1L;
	
	public final static Short BLOQUEIO_INSERIR_IMOVEL_SIM = new Short("1");
	public final static Short BLOQUEIO_INSERIR_IMOVEL_NAO = new Short("2");

    private Integer id;
    private int codigo;
    private String descricao;
    private Short indicadorUso;
    private Date ultimaAlteracao;
    private Municipio municipio;
    private Localidade localidade;
    private Short indicadorSetorAlternativo;
    
    private Short indicadorBloqueio = BLOQUEIO_INSERIR_IMOVEL_NAO;

    public SetorComercial() {
    }
    
    public SetorComercial(Integer id) {
    	this.id = id;
    }
    
    public SetorComercial(int codigo, String descricao, Short indicadorUso,
            Date ultimaAlteracao, Municipio municipio,
            gcom.cadastro.localidade.Localidade localidade, Short indicadorSetorAlternativo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.municipio = municipio;
        this.localidade = localidade;
        this.indicadorSetorAlternativo = indicadorSetorAlternativo;
    }

    public SetorComercial(int codigo, String descricao, Municipio municipio,
            gcom.cadastro.localidade.Localidade localidade) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.municipio = municipio;
        this.localidade = localidade;
    }

    public SetorComercial(Integer id, int codigo, String descricao) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public Municipio getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public gcom.cadastro.localidade.Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(gcom.cadastro.localidade.Localidade localidade) {
        this.localidade = localidade;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
	
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Short getIndicadorSetorAlternativo() {
		return indicadorSetorAlternativo;
	}

	public void setIndicadorSetorAlternativo(Short indicadorSetorAlternativo) {
		this.indicadorSetorAlternativo = indicadorSetorAlternativo;
	}
	
	public Short getIndicadorBloqueio() {
		return indicadorBloqueio;
	}

	public void setIndicadorBloqueio(Short indicadorBloqueio) {
		this.indicadorBloqueio = indicadorBloqueio;
	}

	public Filtro retornaFiltro(){
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		
		filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade(FiltroSetorComercial.MUNICIPIO);
		filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade(FiltroSetorComercial.LOCALIDADE);
		filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.ID, this.getId()));
		return filtroSetorComercial; 
	}
	
	public String getDescricaoParaRegistroTransacao(){
		if (getDescricao() != null){
			return getCodigo() + " - " + getDescricao();
		}else{
			return null;
		}
	}
	
	@Override
	public void initializeLazy() {
		if (getLocalidade() != null){
			getLocalidade().getDescricao();
		}
	}
}
