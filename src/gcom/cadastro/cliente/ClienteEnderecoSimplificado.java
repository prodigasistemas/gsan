package gcom.cadastro.cliente;

/**
 * bean simplificado responsável para trazer só o necessário
 * 
 * @author Sávio Luiz
 * @created 17 de Maio de 2004
 */

public class ClienteEnderecoSimplificado {

    private gcom.cadastro.cliente.Cliente cliente;

    /**
     * Constructor for the ClienteEnderecoSimplificado object
     * 
     * @param id
     *            Description of the Parameter
     * @param nome
     *            Description of the Parameter
     * @param clienteTipo
     *            Description of the Parameter
     * @param cpf
     *            Description of the Parameter
     * @param cnpj
     *            Description of the Parameter
     */
    public ClienteEnderecoSimplificado(Integer id, String nome,
            gcom.cadastro.cliente.ClienteTipo clienteTipo, String cpf,
            String cnpj) {
        this.cliente = new Cliente(id, nome, clienteTipo, cpf, cnpj);
    }

    /**
     * Gets the cliente attribute of the ClienteEnderecoSimplificado object
     * 
     * @return The cliente value
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Sets the cliente attribute of the ClienteEnderecoSimplificado object
     * 
     * @param cliente
     *            The new cliente value
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
