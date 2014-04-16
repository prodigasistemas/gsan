package gcom.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class ImovelEconomia extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	public Filtro retornaFiltro() {

		FiltroImovelEconomia filtro = new FiltroImovelEconomia();

		filtro.adicionarParametro(new ParametroSimples(
				FiltroImovelEconomia.ID, this.getId()));

		
		filtro.adicionarCaminhoParaCarregamentoEntidade("areaConstruidaFaixa");

		filtro.adicionarCaminhoParaCarregamentoEntidade("imovelSubcategoria");
		return filtro;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	
	
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String complementoEndereco;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,
    		TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})
    private Short numeroMorador;

    /** nullable persistent field */
    private Short numeroPontosUtilizacao;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,
    		TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})    
    private String numeroIptu;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,
    		TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})    
    private Long numeroCelpe;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,
    		TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})    
    private BigDecimal areaConstruida;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    @ControleAlteracao(value=FiltroImovelEconomia.AREA_CONSTRUIDA_FAIXA, 
    		funcionalidade={TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,
    		TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})    
    private gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa;

    /** persistent field */
    private gcom.cadastro.imovel.ImovelSubcategoria imovelSubcategoria;

    /** persistent field */
    private Set tarifaSocialDadoEconomias;

    /** persistent field */
    @ControleAlteracao(value=FiltroImovelEconomia.CLIENTE_IMOVEL_ECONOMIA, 
    		funcionalidade={TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,
    		TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})    
    private Set clienteImovelEconomias;
    
    private int codigoModificado;

    /** full constructor */
    public ImovelEconomia(String complementoEndereco, Short numeroMorador, Short numeroPontosUtilizacao, 
    		String numeroIptu, Long numeroCelpe, BigDecimal areaConstruida, Date ultimaAlteracao, gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa, gcom.cadastro.imovel.ImovelSubcategoria imovelSubcategoria, Set tarifaSocialDadoEconomias, Set clienteImovelEconomias) {
        this.complementoEndereco = complementoEndereco;
        this.numeroMorador = numeroMorador;
        this.numeroPontosUtilizacao = numeroPontosUtilizacao;
        this.numeroIptu = numeroIptu;
        this.numeroCelpe = numeroCelpe;
        this.areaConstruida = areaConstruida;
        this.ultimaAlteracao = ultimaAlteracao;
        this.areaConstruidaFaixa = areaConstruidaFaixa;
        this.imovelSubcategoria = imovelSubcategoria;
        this.tarifaSocialDadoEconomias = tarifaSocialDadoEconomias;
        this.clienteImovelEconomias = clienteImovelEconomias;
    }

    /** default constructor */
    public ImovelEconomia() {
    }

    /** minimal constructor */
    public ImovelEconomia(gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa, gcom.cadastro.imovel.ImovelSubcategoria imovelSubcategoria, Set tarifaSocialDadoEconomias, Set clienteImovelEconomias) {
        this.areaConstruidaFaixa = areaConstruidaFaixa;
        this.imovelSubcategoria = imovelSubcategoria;
        this.tarifaSocialDadoEconomias = tarifaSocialDadoEconomias;
        this.clienteImovelEconomias = clienteImovelEconomias;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComplementoEndereco() {
        return this.complementoEndereco;
    }

    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
    }

    public Short getNumeroMorador() {
        return this.numeroMorador;
    }

    public void setNumeroMorador(Short numeroMorador) {
        this.numeroMorador = numeroMorador;
    }

    public Short getNumeroPontosUtilizacao() {
        return this.numeroPontosUtilizacao;
    }

    public void setNumeroPontosUtilizacao(Short numeroPontosUtilizacao) {
        this.numeroPontosUtilizacao = numeroPontosUtilizacao;
    }

    public String getNumeroIptu() {
        return this.numeroIptu;
    }

    public void setNumeroIptu(String numeroIptu) {
        this.numeroIptu = numeroIptu;
    }

    public Long getNumeroCelpe() {
        return this.numeroCelpe;
    }

    public void setNumeroCelpe(Long numeroCelpe) {
        this.numeroCelpe = numeroCelpe;
    }

    public BigDecimal getAreaConstruida() {
        return this.areaConstruida;
    }

    public void setAreaConstruida(BigDecimal areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cadastro.imovel.AreaConstruidaFaixa getAreaConstruidaFaixa() {
        return this.areaConstruidaFaixa;
    }

    public void setAreaConstruidaFaixa(gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa) {
        this.areaConstruidaFaixa = areaConstruidaFaixa;
    }

    public gcom.cadastro.imovel.ImovelSubcategoria getImovelSubcategoria() {
        return this.imovelSubcategoria;
    }

    public void setImovelSubcategoria(gcom.cadastro.imovel.ImovelSubcategoria imovelSubcategoria) {
        this.imovelSubcategoria = imovelSubcategoria;
    }

    public Set getTarifaSocialDadoEconomias() {
        return this.tarifaSocialDadoEconomias;
    }

    public void setTarifaSocialDadoEconomias(Set tarifaSocialDadoEconomias) {
        this.tarifaSocialDadoEconomias = tarifaSocialDadoEconomias;
    }

    public Set getClienteImovelEconomias() {
        return this.clienteImovelEconomias;
    }

    public void setClienteImovelEconomias(Set clienteImovelEconomias) {
        this.clienteImovelEconomias = clienteImovelEconomias;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    /**
      * Gets the hashCode attribute of the ImovelSubcategoria object
      * 
      * @return The hashCode value
      */
     public int getHashCode() {
         return this.hashCode();
     }

    /**
      * Description of the Method
      * 
      * @return Description of the Return Value
      */
     public int hashCode() {
         return new HashCodeBuilder().append(getId()).append(
                 getComplementoEndereco()).append(getNumeroMorador()).append(
                 getNumeroPontosUtilizacao()).append(getNumeroIptu()).append(
                 getNumeroCelpe()).append(getAreaConstruida()).append(
                 getUltimaAlteracao()).append(getAreaConstruidaFaixa()).append(
                 getImovelSubcategoria()).toHashCode();
     }
 
     /**
      * Description of the Method
      * 
      * @param other
      *            Description of the Parameter
      * @return Description of the Return Value
      */
     public boolean equals(Object other) {
         if ((this == other)) {
             return true;
         }
         if (!(other instanceof ImovelEconomia)) {
             return false;
         }
         ImovelEconomia castOther = (ImovelEconomia) other;
 
         return new EqualsBuilder().append(this.getComplementoEndereco(),
                 castOther.getComplementoEndereco()).append(
                 this.getNumeroMorador(), castOther.getNumeroMorador()).append(
                 this.getNumeroPontosUtilizacao(),
                 castOther.getNumeroPontosUtilizacao()).append(
                 this.getNumeroIptu(), castOther.getNumeroIptu()).append(
                 this.getNumeroCelpe(), castOther.getNumeroCelpe()).append(
                 this.getAreaConstruida(), castOther.getAreaConstruida())
                 .append(this.getAreaConstruidaFaixa(),
                         castOther.getAreaConstruidaFaixa()).append(
                         this.getImovelSubcategoria(),
                         castOther.getImovelSubcategoria()).isEquals();
     }

     /**
       * Gets the codigoModificado attribute of the ImovelEconomia object
       * 
       * @return The codigoModificado value
       */
      public int getCodigoModificado() {
          return codigoModificado;
      }
  
      /**
       * Sets the codigoModificado attribute of the ImovelEconomia object
       * 
       * @param codigoModificado
       *            The new codigoModificado value
       */
      public void setCodigoModificado(int codigoModificado) {
          this.codigoModificado = codigoModificado;
      }

     
    /*
	 * Construtor Anterior ao Mapeamento da Iteração 5 
	 */
    
     public ImovelEconomia(String complementoEndereco, Short numeroMorador,
             Short numeroPontosUtilizacao, String numeroIptu,
             Long numeroCelpe, BigDecimal areaConstruida, Date ultimaAlteracao,
             gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa,
             gcom.cadastro.imovel.ImovelSubcategoria imovelSubcategoria,
             Set clienteImovelEconomias) {
         this.complementoEndereco = complementoEndereco;
         this.numeroMorador = numeroMorador;
         this.numeroPontosUtilizacao = numeroPontosUtilizacao;
         this.numeroIptu = numeroIptu;
         this.numeroCelpe = numeroCelpe;
         this.areaConstruida = areaConstruida;
         this.ultimaAlteracao = ultimaAlteracao;
         this.areaConstruidaFaixa = areaConstruidaFaixa;
         this.imovelSubcategoria = imovelSubcategoria;
         this.clienteImovelEconomias = clienteImovelEconomias;
     }
     
     @Override
    public String getDescricaoParaRegistroTransacao() {
    	 Cliente cliente = getClienteUsuario();
    	 if (cliente != null){
    		return cliente.getDescricaoParaRegistroTransacao(); 
    	 }
    	 if (getImovelSubcategoria() != null){
    		 return getImovelSubcategoria().getDescricaoParaRegistroTransacao();	 
    	 }
    	 return "";    	 
    }
     
     @Override
    public void initializeLazy() {
    	 if (getImovelSubcategoria() != null){
    		 getImovelSubcategoria().initializeLazy();
    	 }
    	 if (getAreaConstruidaFaixa() != null){
    		 getAreaConstruidaFaixa().initializeLazy();
    	 }
    	 getDescricaoParaRegistroTransacao();
    	 initilizarCollectionLazy(clienteImovelEconomias);
    }
     
    public Cliente getClienteUsuario(){
    	if (clienteImovelEconomias != null){
    		for (Iterator iterator = clienteImovelEconomias.iterator(); iterator.hasNext();) {
				ClienteImovelEconomia clienteImovel = (ClienteImovelEconomia) iterator.next();
				if (clienteImovel != null && 
					clienteImovel.getClienteRelacaoTipo().getId().shortValue() == ClienteRelacaoTipo.USUARIO.shortValue()
					&& clienteImovel.getDataFimRelacao() == null){
					return clienteImovel.getCliente(); 
				}				
			} 
    	}
    	return null;
    }
}
