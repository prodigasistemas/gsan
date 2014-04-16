package gcom.cobranca.parcelamento;

import java.io.Serializable;
import java.util.Collection;


/** @author Hibernate CodeGenerator */
public class ParcelamentoQuantidadePrestacaoHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private ParcelamentoQuantidadePrestacao parcelamentoQuantidadePrestacao;
    
    private Collection collectionParcelamentoFaixaValor;

    /** full constructor */
    public ParcelamentoQuantidadePrestacaoHelper(
    		ParcelamentoQuantidadePrestacao parcelamentoQuantidadePrestacao,
    		Collection collectionParcelamentoFaixaValor) {
        this.parcelamentoQuantidadePrestacao = parcelamentoQuantidadePrestacao;
        this.collectionParcelamentoFaixaValor = collectionParcelamentoFaixaValor;
        
    }

    /** default constructor */
    public ParcelamentoQuantidadePrestacaoHelper() {
    }

	public Collection getCollectionParcelamentoFaixaValor() {
		return collectionParcelamentoFaixaValor;
	}

	public void setCollectionParcelamentoFaixaValor(
			Collection collectionParcelamentoFaixaValor) {
		this.collectionParcelamentoFaixaValor = collectionParcelamentoFaixaValor;
	}

	public ParcelamentoQuantidadePrestacao getParcelamentoQuantidadePrestacao() {
		return parcelamentoQuantidadePrestacao;
	}

	public void setParcelamentoQuantidadePrestacao(
			ParcelamentoQuantidadePrestacao parcelamentoQuantidadePrestacao) {
		this.parcelamentoQuantidadePrestacao = parcelamentoQuantidadePrestacao;
	}

    
  
}
