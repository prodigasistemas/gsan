package gcom.cadastro.cliente;

import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;

import java.io.Serializable;
import java.util.Date;

/**
 * bean simplificado responsável para trazer só o necessário
 * 
 * @author Sávio Luiz
 * @created 17 de Maio de 2004
 */

public class ClienteImovelSimplificado implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * persistent field
     */
    private gcom.cadastro.imovel.Imovel imovel;

    /**
     * persistent field
     */
    private gcom.cadastro.cliente.Cliente cliente;

    /**
     * persistent field
     */   
    private Date dataFimRelacao;

    /**
     * Constructor for the ClienteImovelSimplificado object
     * 
     * @param idImovel
     *            Description of the Parameter
     * @param numeroImovel
     *            Description of the Parameter
     * @param cep
     *            Description of the Parameter
     * @param logradouro
     *            Description of the Parameter
     * @param nomeCliente
     *            Description of the Parameter
     */
	public ClienteImovelSimplificado(Integer idImovel, String numeroImovel, LogradouroCep logradouroCep, 
			                         LogradouroBairro logradouroBairro, Quadra quadra, EnderecoReferencia enderecoReferencia, 
			                         String complementoEndereco, String nomeCliente, Integer idCliente, 
			                         SetorComercial setorComercial,Localidade localidade, Date dataFimRelacao) {
		this.imovel = new Imovel(idImovel, numeroImovel, logradouroCep, 
				                 logradouroBairro, quadra, enderecoReferencia, 
				                 complementoEndereco, setorComercial, localidade);
		this.cliente = new Cliente(nomeCliente, idCliente);
		this.dataFimRelacao = dataFimRelacao; 
	}

    /**
     * Constructor for the ClienteImovelSimplificado object
     * 
     * @param idImovel
     *            Description of the Parameter
     * @param numeroImovel
     *            Description of the Parameter
     * @param cep
     *            Description of the Parameter
     * @param logradouro
     *            Description of the Parameter
     * @param nomeCliente
     *            Description of the Parameter
     */
	public ClienteImovelSimplificado(Integer idImovel, String numeroImovel,
			LogradouroCep logradouroCep, LogradouroBairro logradouroBairro, Quadra quadra,
			EnderecoReferencia enderecoReferencia, String complementoEndereco,
			String nomeCliente, Integer idCliente, SetorComercial setorComercial,Localidade localidade, Date dataFimRelacao, Date ultimaAlteracao) {
		this.imovel = new Imovel(idImovel, numeroImovel, logradouroCep, logradouroBairro, quadra,
				enderecoReferencia, complementoEndereco, setorComercial,localidade,ultimaAlteracao);
		this.cliente = new Cliente(nomeCliente, idCliente);
		this.dataFimRelacao = dataFimRelacao; 
	}	
	
	/**
     * Gets the cliente attribute of the ClienteImovelSimplificado object
     * 
     * @return The cliente value
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Sets the cliente attribute of the ClienteImovelSimplificado object
     * 
     * @param cliente
     *            The new cliente value
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Gets the imovel attribute of the ClienteImovelSimplificado object
     * 
     * @return The imovel value
     */
    public Imovel getImovel() {
        return imovel;
    }

    /**
     * Sets the imovel attribute of the ClienteImovelSimplificado object
     * 
     * @param imovel
     *            The new imovel value
     */
    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }
    
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof ClienteImovelSimplificado)) {
            return false;
        }
        ClienteImovelSimplificado castOther = (ClienteImovelSimplificado) other;

        return (this.getImovel().getId().equals(castOther.getImovel().getId()));
    }

	public Date getDataFimRelacao() {
		return dataFimRelacao;
	}

	public void setDataFimRelacao(Date dataFimRelacao) {
		this.dataFimRelacao = dataFimRelacao;
	}


}
