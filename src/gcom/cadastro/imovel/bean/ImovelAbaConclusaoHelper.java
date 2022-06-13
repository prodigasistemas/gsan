package gcom.cadastro.imovel.bean;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.localidade.SetorComercial;

import java.util.Collection;

public class ImovelAbaConclusaoHelper {

	private String numeroIptu;
	private String numeroContratoCelpe;
	private String idQuadraImovel;
	private String idImovelPrincipal;
	private String idFuncionario;
	private String coordenadasUtmX;
	private String coordenadasUtmY;
	private String sequencialRotaEntrega;
	private String idRotaEntrega;
	private String idRotaAlternativa;
	private Collection<ClienteImovel> imoveisClientes;
	private SetorComercial setorComercial;
	private Integer idImovelAtualizar;
	private String numeroMedidorEnergia;
	private String informacoesComplementares;
	private String numeroQuadraEntrega;
	private String indicadorEnvioContaFisica;

	public String getNumeroMedidorEnergia() {
		return numeroMedidorEnergia;
	}

	public void setNumeroMedidorEnergia(String numeroMedidorEnergia) {
		this.numeroMedidorEnergia = numeroMedidorEnergia;
	}

	/**
	 * @return Retorna o campo idRotaAlternativa
	 */
	public String getIdRotaAlternativa() {
		return idRotaAlternativa;
	}

	/**
	 * @param idRotaAlternativa - O campo idRotaAlternativa
	 */
	public void setIdRotaAlternativa(String idRotaAlternativa) {
		this.idRotaAlternativa = idRotaAlternativa;
	}

	/**
	 * @return Retorna o campo setorComercial.
	 */
	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	/**
	 * @param setorComercial O setorComercial a ser setado.
	 */
	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	/**
	 * @return Retorna o campo numeroContratoCelpe.
	 */
	public String getNumeroContratoCelpe() {
		return numeroContratoCelpe;
	}

	/**
	 * @param numeroContratoCelpe O numeroContratoCelpe a ser setado.
	 */
	public void setNumeroContratoCelpe(String numeroContratoCelpe) {
		this.numeroContratoCelpe = numeroContratoCelpe;
	}

	/**
	 * @return Retorna o campo numeroIptu.
	 */
	public String getNumeroIptu() {
		return numeroIptu;
	}

	/**
	 * @param numeroIptu O numeroIptu a ser setado.
	 */
	public void setNumeroIptu(String numeroIptu) {
		this.numeroIptu = numeroIptu;
	}

	/**
	 * @return Retorna o campo idQuadraImovel.
	 */
	public String getIdQuadraImovel() {
		return idQuadraImovel;
	}

	/**
	 * @param idQuadraImovel O idQuadraImovel a ser setado.
	 */
	public void setIdQuadraImovel(String idQuadraImovel) {
		this.idQuadraImovel = idQuadraImovel;
	}

	/**
	 * @return Retorna o campo idImovelPrincipal.
	 */
	public String getIdImovelPrincipal() {
		return idImovelPrincipal;
	}

	/**
	 * @param idImovelPrincipal O idImovelPrincipal a ser setado.
	 */
	public void setIdImovelPrincipal(String idImovelPrincipal) {
		this.idImovelPrincipal = idImovelPrincipal;
	}

	/**
	 * @return Retorna o campo idFuncionario.
	 */
	public String getIdFuncionario() {
		return idFuncionario;
	}

	/**
	 * @param idFuncionario O idFuncionario a ser setado.
	 */
	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	/**
	 * @return Retorna o campo coordenadasUtmX.
	 */
	public String getCoordenadasUtmX() {
		return coordenadasUtmX;
	}

	/**
	 * @param coordenadasUtmX O coordenadasUtmX a ser setado.
	 */
	public void setCoordenadasUtmX(String coordenadasUtmX) {
		this.coordenadasUtmX = coordenadasUtmX;
	}

	/**
	 * @return Retorna o campo coordenadasUtmY.
	 */
	public String getCoordenadasUtmY() {
		return coordenadasUtmY;
	}

	/**
	 * @param coordenadasUtmY O coordenadasUtmY a ser setado.
	 */
	public void setCoordenadasUtmY(String coordenadasUtmY) {
		this.coordenadasUtmY = coordenadasUtmY;
	}

	/**
	 * @return Retorna o campo imoveisClientes.
	 */
	public Collection<ClienteImovel> getImoveisClientes() {
		return imoveisClientes;
	}

	/**
	 * @param imoveisClientes O imoveisClientes a ser setado.
	 */
	public void setImoveisClientes(Collection<ClienteImovel> imoveisClientes) {
		this.imoveisClientes = imoveisClientes;
	}

	/**
	 * @return Retorna o campo idRotaEntrega.
	 */
	public String getIdRotaEntrega() {
		return idRotaEntrega;
	}

	/**
	 * @param idRotaEntrega O idRotaEntrega a ser setado.
	 */
	public void setIdRotaEntrega(String idRotaEntrega) {
		this.idRotaEntrega = idRotaEntrega;
	}

	/**
	 * @return Retorna o campo sequencialRotaEntrega.
	 */
	public String getSequencialRotaEntrega() {
		return sequencialRotaEntrega;
	}

	/**
	 * @param sequencialRotaEntrega O sequencialRotaEntrega a ser setado.
	 */
	public void setSequencialRotaEntrega(String sequencialRotaEntrega) {
		this.sequencialRotaEntrega = sequencialRotaEntrega;
	}

	public Integer getIdImovelAtualizar() {
		return idImovelAtualizar;
	}

	public void setIdImovelAtualizar(Integer idImovelAtualizar) {
		this.idImovelAtualizar = idImovelAtualizar;
	}

	public String getInformacoesComplementares() {
		return informacoesComplementares;
	}

	public void setInformacoesComplementares(String informacoesComplementares) {
		this.informacoesComplementares = informacoesComplementares;
	}

	public String getNumeroQuadraEntrega() {
		return numeroQuadraEntrega;
	}

	public void setNumeroQuadraEntrega(String numeroQuadraEntrega) {
		this.numeroQuadraEntrega = numeroQuadraEntrega;
	}

	public String getIndicadorEnvioContaFisica() {
		return indicadorEnvioContaFisica;
	}

	public void setIndicadorEnvioContaFisica(String indicadorEnvioContaFisica) {
		this.indicadorEnvioContaFisica = indicadorEnvioContaFisica;
	}

}
