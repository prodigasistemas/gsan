package gcom.gui.cadastro.imovel;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ImovelEconomiaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String[] idRegistrosRemocao;

    private String areaConstruida;

    private String codigoCliente;

    private String complementoEndereco;

    private String nomeCliente;

    private String numeroContratoEnergia;

    private String numeroIptu;

    private String numeroMorador;

    private String numeroPontoUtilizacao;

    private String quantidadeEconomia;

    private String idCategoria;

    private String idSubCategoria;

    private String idAreaConstruidaFaixa;

    private String idClienteTipo;

    private String botaoAdicionar;

    private String botaoClicado;

    private String botaoRemover;

    /**
     * Retorna o valor de areaConstruida
     * 
     * @return O valor de areaConstruida
     */
    public String getAreaConstruida() {
        return areaConstruida;
    }

    /**
     * Seta o valor de areaConstruida
     * 
     * @param areaConstruida
     *            O novo valor de areaConstruida
     */
    public void setAreaConstruida(String areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    /**
     * Retorna o valor de codigoCliente
     * 
     * @return O valor de codigoCliente
     */
    public String getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * Seta o valor de codigoCliente
     * 
     * @param codigoCliente
     *            O novo valor de codigoCliente
     */
    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    /**
     * Retorna o valor de complementoEndereco
     * 
     * @return O valor de complementoEndereco
     */
    public String getComplementoEndereco() {
        return complementoEndereco;
    }

    /**
     * Seta o valor de complementoEndereco
     * 
     * @param complementoEndereco
     *            O novo valor de complementoEndereco
     */
    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
    }

    /**
     * Retorna o valor de nomeCliente
     * 
     * @return O valor de nomeCliente
     */
    public String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * Seta o valor de nomeCliente
     * 
     * @param nomeCliente
     *            O novo valor de nomeCliente
     */
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    /**
     * Retorna o valor de numeroContratoEnergia
     * 
     * @return O valor de numeroContratoEnergia
     */
    public String getNumeroContratoEnergia() {
        return numeroContratoEnergia;
    }

    /**
     * Seta o valor de numeroContratoEnergia
     * 
     * @param numeroContratoEnergia
     *            O novo valor de numeroContratoEnergia
     */
    public void setNumeroContratoEnergia(String numeroContratoEnergia) {
        this.numeroContratoEnergia = numeroContratoEnergia;
    }

    /**
     * Retorna o valor de numeroIptu
     * 
     * @return O valor de numeroIptu
     */
    public String getNumeroIptu() {
        return numeroIptu;
    }

    /**
     * Seta o valor de numeroIptu
     * 
     * @param numeroIptu
     *            O novo valor de numeroIptu
     */
    public void setNumeroIptu(String numeroIptu) {
        this.numeroIptu = numeroIptu;
    }

    /**
     * Retorna o valor de numeroMorador
     * 
     * @return O valor de numeroMorador
     */
    public String getNumeroMorador() {
        return numeroMorador;
    }

    /**
     * Seta o valor de numeroMorador
     * 
     * @param numeroMorador
     *            O novo valor de numeroMorador
     */
    public void setNumeroMorador(String numeroMorador) {
        this.numeroMorador = numeroMorador;
    }

    /**
     * Retorna o valor de numeroPontoUtilizacao
     * 
     * @return O valor de numeroPontoUtilizacao
     */
    public String getNumeroPontoUtilizacao() {
        return numeroPontoUtilizacao;
    }

    /**
     * Seta o valor de numeroPontoUtilizacao
     * 
     * @param numeroPontoUtilizacao
     *            O novo valor de numeroPontoUtilizacao
     */
    public void setNumeroPontoUtilizacao(String numeroPontoUtilizacao) {
        this.numeroPontoUtilizacao = numeroPontoUtilizacao;
    }

    /**
     * Retorna o valor de quantidadeEconomia
     * 
     * @return O valor de quantidadeEconomia
     */
    public String getQuantidadeEconomia() {
        return quantidadeEconomia;
    }

    /**
     * Seta o valor de quantidadeEconomia
     * 
     * @param quantidadeEconomia
     *            O novo valor de quantidadeEconomia
     */
    public void setQuantidadeEconomia(String quantidadeEconomia) {
        this.quantidadeEconomia = quantidadeEconomia;
    }

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    }

    /**
     * Retorna o valor de idCategoria
     * 
     * @return O valor de idCategoria
     */
    public String getIdCategoria() {
        return idCategoria;
    }

    /**
     * Retorna o valor de idSubcategoria
     */

    /**
     * Seta o valor de idCategoria
     * 
     * @param idCategoria
     *            O novo valor de idCategoria
     */
    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * Seta o valor de idSubcategoria
     * 
     * @return O valor de idAreaConstruidaFaixa
     */
    public String getIdAreaConstruidaFaixa() {
        return idAreaConstruidaFaixa;
    }

    /**
     * Seta o valor de idAreaConstruidaFaixa
     * 
     * @param idAreaConstruidaFaixa
     *            O novo valor de idAreaConstruidaFaixa
     */
    public void setIdAreaConstruidaFaixa(String idAreaConstruidaFaixa) {
        this.idAreaConstruidaFaixa = idAreaConstruidaFaixa;
    }

    /**
     * Seta o valor de idTipoCliente
     * 
     * @return O valor de idSubCategoria
     */

    public String getIdSubCategoria() {
        return idSubCategoria;
    }

    /**
     * Seta o valor de idSubCategoria
     * 
     * @param idSubCategoria
     *            O novo valor de idSubCategoria
     */
    public void setIdSubCategoria(String idSubCategoria) {
        this.idSubCategoria = idSubCategoria;
    }

    /**
     * Retorna o valor de idClienteTipo
     * 
     * @return O valor de idClienteTipo
     */
    public String getIdClienteTipo() {
        return idClienteTipo;
    }

    /**
     * Seta o valor de idClienteTipo
     * 
     * @param idClienteTipo
     *            O novo valor de idClienteTipo
     */
    public void setIdClienteTipo(String idClienteTipo) {
        this.idClienteTipo = idClienteTipo;
    }

    /**
     * Retorna o valor de botaoAdicionar
     * 
     * @return O valor de botaoAdicionar
     */
    public String getBotaoAdicionar() {
        return botaoAdicionar;
    }

    /**
     * Seta o valor de botaoAdicionar
     * 
     * @param botaoAdicionar
     *            O novo valor de botaoAdicionar
     */
    public void setBotaoAdicionar(String botaoAdicionar) {
        this.botaoAdicionar = botaoAdicionar;
    }

    /**
     * Retorna o valor de botaoClicado
     * 
     * @return O valor de botaoClicado
     */
    public String getBotaoClicado() {
        return botaoClicado;
    }

    /**
     * Seta o valor de botaoClicado
     * 
     * @param botaoClicado
     *            O novo valor de botaoClicado
     */
    public void setBotaoClicado(String botaoClicado) {
        this.botaoClicado = botaoClicado;
    }

    /**
     * Retorna o valor de idRegistrosRemocao
     * 
     * @return O valor de idRegistrosRemocao
     */
    public String[] getIdRegistrosRemocao() {
        return idRegistrosRemocao;
    }

    /**
     * Seta o valor de idRegistrosRemocao
     * 
     * @param idRegistrosRemocao
     *            O novo valor de idRegistrosRemocao
     */
    public void setIdRegistrosRemocao(String[] idRegistrosRemocao) {
        this.idRegistrosRemocao = idRegistrosRemocao;
    }

    public String getBotaoRemover() {
        return botaoRemover;
    }

    public void setBotaoRemover(String botaoRemover) {
        this.botaoRemover = botaoRemover;
    }
}
