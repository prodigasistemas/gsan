package gcom.gui.cadastro.imovel;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 28 de Junho de 2004
 */
public class ManterImovelActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;


	private String idLocalidade;
	private String localidadeDescricao;
		
	private String idSetorComercial;
		
	private String idImovel;
	private String setorComercialDescricao;
		
	private String idQuadra;
	private String quadraDescricao;
		
	private String descricaoImovel;
		
	private String lote;
	private String subLote;
	private String testadaLote;
	private String idCliente;
	private String nomeCliente;
	private String tipoCliente;
	private String tipoClienteImovel;
		
	private String tipoClienteImovelEconomia;

	private String codigoClientes;

	private String areaConstruida;
		
	private String faixaAreaConstruida;
		
	private String reservatorioInferior;
		
	private String faixaReservatorioInferior;
		
	private String reservatorioSuperior;
		
	private String faixaResevatorioSuperior;
		
	private String piscina;
	private String faixaPiscina;
	private String pavimentoCalcada;
		
	private String pavimentoRua;
	private String fonteAbastecimento;
		
	private String situacaoLigacaoAgua;
		
	private String situacaoLigacaoEsgoto;
		
	private String perfilImovel;
	private String poco;
	private String numeroPontos;
	private String numeroMoradores;
		
	private String numeroIptu;
	private String numeroContratoCelpe;
		
	private String envioConta;
	private String extratoResponsavel;
		
	private String tipoOcupacao;
	private String cordenadasUtmX;
		
	private String cordenadasUtmY;
		
	private String idClienteImovelUsuario;
		
	private String idClienteImovelResponsavel;
		
	private String idSubCategoriaImovel;
		
	private String idClienteImovelResponsavelEconomias;
		
	private String textoSelecionado;
		
	private String textoSelecionadoCategoria;
		
	private String textoSelecionadoSubCategoria;
		
	private String idCategoria;
	private String idSubCategoria;
		
	private String quantidadeEconomia;
		
	private String complementoEndereco;
		
	private String numeroPontoUtilizacao;
		
	private String numeroContratoEnergia;
		
	private String idAreaConstruidaFaixa;
		
	private String idRegistrosRemocaoEconomia;
		
	private String tipoDespejo;
	private String botaoAdicionar;
		
	private String botaoClicado;
	private String botaoRemover;
	private String numero;
	private String idBairro;
	private String bairro;
	private String 	idMunicipio;
	private String municipio;
	private String tipoLogradouro;
		
	private String tituloLogradouro;
		
	private String logradouro;
	private String cep;
	private String indicadorUso;
	private String matricula;
	private String idLogradouro;
	private String dataInicioClienteImovelRelacao;
	
	private String informacoesComplementares;
	
	private String indicadorImovelSubcategoriaNaoPodeSerAtualizado;
	

	/**TODO: COSANPA
	 * Mantis 647 - Disponibilizar o campo nome do Imóvel no cadastro do Imóvel
	 * 
	 * @author Wellington Rocha
	 * @date 14/11/2012*/
	private String nomeDoImovel;
	
	
	public String getNomeDoImovel() {
		return nomeDoImovel;
	}
	public void setNomeDoImovel(String nomeDoImovel) {
		this.nomeDoImovel = nomeDoImovel;
	}
	/**
	 * @return Retorna o campo areaConstruida.
	 */
	public String getAreaConstruida() {
		return areaConstruida;
	}
	/**
	 * @param areaConstruida O areaConstruida a ser setado.
	 */
	public void setAreaConstruida(String areaConstruida) {
		this.areaConstruida = areaConstruida;
	}
	/**
	 * @return Retorna o campo bairro.
	 */
	public String getBairro() {
		return bairro;
	}
	/**
	 * @param bairro O bairro a ser setado.
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	/**
	 * @return Retorna o campo botaoAdicionar.
	 */
	public String getBotaoAdicionar() {
		return botaoAdicionar;
	}
	/**
	 * @param botaoAdicionar O botaoAdicionar a ser setado.
	 */
	public void setBotaoAdicionar(String botaoAdicionar) {
		this.botaoAdicionar = botaoAdicionar;
	}
	/**
	 * @return Retorna o campo botaoClicado.
	 */
	public String getBotaoClicado() {
		return botaoClicado;
	}
	/**
	 * @param botaoClicado O botaoClicado a ser setado.
	 */
	public void setBotaoClicado(String botaoClicado) {
		this.botaoClicado = botaoClicado;
	}
	/**
	 * @return Retorna o campo botaoRemover.
	 */
	public String getBotaoRemover() {
		return botaoRemover;
	}
	/**
	 * @param botaoRemover O botaoRemover a ser setado.
	 */
	public void setBotaoRemover(String botaoRemover) {
		this.botaoRemover = botaoRemover;
	}
	/**
	 * @return Retorna o campo cep.
	 */
	public String getCep() {
		return cep;
	}
	/**
	 * @param cep O cep a ser setado.
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}
	/**
	 * @return Retorna o campo codigoClientes.
	 */
	public String getCodigoClientes() {
		return codigoClientes;
	}
	/**
	 * @param codigoClientes O codigoClientes a ser setado.
	 */
	public void setCodigoClientes(String codigoClientes) {
		this.codigoClientes = codigoClientes;
	}
	/**
	 * @return Retorna o campo complementoEndereco.
	 */
	public String getComplementoEndereco() {
		return complementoEndereco;
	}
	/**
	 * @param complementoEndereco O complementoEndereco a ser setado.
	 */
	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}
	/**
	 * @return Retorna o campo cordenadasUtmX.
	 */
	public String getCordenadasUtmX() {
		return cordenadasUtmX;
	}
	/**
	 * @param cordenadasUtmX O cordenadasUtmX a ser setado.
	 */
	public void setCordenadasUtmX(String cordenadasUtmX) {
		this.cordenadasUtmX = cordenadasUtmX;
	}
	/**
	 * @return Retorna o campo cordenadasUtmY.
	 */
	public String getCordenadasUtmY() {
		return cordenadasUtmY;
	}
	/**
	 * @param cordenadasUtmY O cordenadasUtmY a ser setado.
	 */
	public void setCordenadasUtmY(String cordenadasUtmY) {
		this.cordenadasUtmY = cordenadasUtmY;
	}
	/**
	 * @return Retorna o campo dataInicioClienteImovelRelacao.
	 */
	public String getDataInicioClienteImovelRelacao() {
		return dataInicioClienteImovelRelacao;
	}
	/**
	 * @param dataInicioClienteImovelRelacao O dataInicioClienteImovelRelacao a ser setado.
	 */
	public void setDataInicioClienteImovelRelacao(
			String dataInicioClienteImovelRelacao) {
		this.dataInicioClienteImovelRelacao = dataInicioClienteImovelRelacao;
	}
	/**
	 * @return Retorna o campo descricaoImovel.
	 */
	public String getDescricaoImovel() {
		return descricaoImovel;
	}
	/**
	 * @param descricaoImovel O descricaoImovel a ser setado.
	 */
	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}
	/**
	 * @return Retorna o campo envioConta.
	 */
	public String getEnvioConta() {
		return envioConta;
	}
	/**
	 * @param envioConta O envioConta a ser setado.
	 */
	public void setEnvioConta(String envioConta) {
		this.envioConta = envioConta;
	}
	/**
	 * @return Retorna o campo extratoResponsavel.
	 */
	public String getExtratoResponsavel() {
		return extratoResponsavel;
	}
	/**
	 * @param extratoResponsavel O extratoResponsavel a ser setado.
	 */
	public void setExtratoResponsavel(String extratoResponsavel) {
		this.extratoResponsavel = extratoResponsavel;
	}
	/**
	 * @return Retorna o campo faixaAreaConstruida.
	 */
	public String getFaixaAreaConstruida() {
		return faixaAreaConstruida;
	}
	/**
	 * @param faixaAreaConstruida O faixaAreaConstruida a ser setado.
	 */
	public void setFaixaAreaConstruida(String faixaAreaConstruida) {
		this.faixaAreaConstruida = faixaAreaConstruida;
	}
	/**
	 * @return Retorna o campo faixaPiscina.
	 */
	public String getFaixaPiscina() {
		return faixaPiscina;
	}
	/**
	 * @param faixaPiscina O faixaPiscina a ser setado.
	 */
	public void setFaixaPiscina(String faixaPiscina) {
		this.faixaPiscina = faixaPiscina;
	}
	/**
	 * @return Retorna o campo faixaReservatorioInferior.
	 */
	public String getFaixaReservatorioInferior() {
		return faixaReservatorioInferior;
	}
	/**
	 * @param faixaReservatorioInferior O faixaReservatorioInferior a ser setado.
	 */
	public void setFaixaReservatorioInferior(String faixaReservatorioInferior) {
		this.faixaReservatorioInferior = faixaReservatorioInferior;
	}
	/**
	 * @return Retorna o campo faixaResevatorioSuperior.
	 */
	public String getFaixaResevatorioSuperior() {
		return faixaResevatorioSuperior;
	}
	/**
	 * @param faixaResevatorioSuperior O faixaResevatorioSuperior a ser setado.
	 */
	public void setFaixaResevatorioSuperior(String faixaResevatorioSuperior) {
		this.faixaResevatorioSuperior = faixaResevatorioSuperior;
	}
	/**
	 * @return Retorna o campo fonteAbastecimento.
	 */
	public String getFonteAbastecimento() {
		return fonteAbastecimento;
	}
	/**
	 * @param fonteAbastecimento O fonteAbastecimento a ser setado.
	 */
	public void setFonteAbastecimento(String fonteAbastecimento) {
		this.fonteAbastecimento = fonteAbastecimento;
	}
	/**
	 * @return Retorna o campo idAreaConstruidaFaixa.
	 */
	public String getIdAreaConstruidaFaixa() {
		return idAreaConstruidaFaixa;
	}
	/**
	 * @param idAreaConstruidaFaixa O idAreaConstruidaFaixa a ser setado.
	 */
	public void setIdAreaConstruidaFaixa(String idAreaConstruidaFaixa) {
		this.idAreaConstruidaFaixa = idAreaConstruidaFaixa;
	}
	/**
	 * @return Retorna o campo idBairro.
	 */
	public String getIdBairro() {
		return idBairro;
	}
	/**
	 * @param idBairro O idBairro a ser setado.
	 */
	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
	}
	/**
	 * @return Retorna o campo idCategoria.
	 */
	public String getIdCategoria() {
		return idCategoria;
	}
	/**
	 * @param idCategoria O idCategoria a ser setado.
	 */
	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}
	/**
	 * @return Retorna o campo idCliente.
	 */
	public String getIdCliente() {
		return idCliente;
	}
	/**
	 * @param idCliente O idCliente a ser setado.
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	/**
	 * @return Retorna o campo idClienteImovelResponsavel.
	 */
	public String getIdClienteImovelResponsavel() {
		return idClienteImovelResponsavel;
	}
	/**
	 * @param idClienteImovelResponsavel O idClienteImovelResponsavel a ser setado.
	 */
	public void setIdClienteImovelResponsavel(String idClienteImovelResponsavel) {
		this.idClienteImovelResponsavel = idClienteImovelResponsavel;
	}
	/**
	 * @return Retorna o campo idClienteImovelResponsavelEconomias.
	 */
	public String getIdClienteImovelResponsavelEconomias() {
		return idClienteImovelResponsavelEconomias;
	}
	/**
	 * @param idClienteImovelResponsavelEconomias O idClienteImovelResponsavelEconomias a ser setado.
	 */
	public void setIdClienteImovelResponsavelEconomias(
			String idClienteImovelResponsavelEconomias) {
		this.idClienteImovelResponsavelEconomias = idClienteImovelResponsavelEconomias;
	}
	/**
	 * @return Retorna o campo idClienteImovelUsuario.
	 */
	public String getIdClienteImovelUsuario() {
		return idClienteImovelUsuario;
	}
	/**
	 * @param idClienteImovelUsuario O idClienteImovelUsuario a ser setado.
	 */
	public void setIdClienteImovelUsuario(String idClienteImovelUsuario) {
		this.idClienteImovelUsuario = idClienteImovelUsuario;
	}
	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}
	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}
	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	/**
	 * @return Retorna o campo idLogradouro.
	 */
	public String getIdLogradouro() {
		return idLogradouro;
	}
	/**
	 * @param idLogradouro O idLogradouro a ser setado.
	 */
	public void setIdLogradouro(String idLogradouro) {
		this.idLogradouro = idLogradouro;
	}
	/**
	 * @return Retorna o campo idMunicipio.
	 */
	public String getIdMunicipio() {
		return idMunicipio;
	}
	/**
	 * @param idMunicipio O idMunicipio a ser setado.
	 */
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	/**
	 * @return Retorna o campo idQuadra.
	 */
	public String getIdQuadra() {
		return idQuadra;
	}
	/**
	 * @param idQuadra O idQuadra a ser setado.
	 */
	public void setIdQuadra(String idQuadra) {
		this.idQuadra = idQuadra;
	}
	/**
	 * @return Retorna o campo idRegistrosRemocaoEconomia.
	 */
	public String getIdRegistrosRemocaoEconomia() {
		return idRegistrosRemocaoEconomia;
	}
	/**
	 * @param idRegistrosRemocaoEconomia O idRegistrosRemocaoEconomia a ser setado.
	 */
	public void setIdRegistrosRemocaoEconomia(String idRegistrosRemocaoEconomia) {
		this.idRegistrosRemocaoEconomia = idRegistrosRemocaoEconomia;
	}
	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	/**
	 * @return Retorna o campo idSubCategoria.
	 */
	public String getIdSubCategoria() {
		return idSubCategoria;
	}
	/**
	 * @param idSubCategoria O idSubCategoria a ser setado.
	 */
	public void setIdSubCategoria(String idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}
	/**
	 * @return Retorna o campo idSubCategoriaImovel.
	 */
	public String getIdSubCategoriaImovel() {
		return idSubCategoriaImovel;
	}
	/**
	 * @param idSubCategoriaImovel O idSubCategoriaImovel a ser setado.
	 */
	public void setIdSubCategoriaImovel(String idSubCategoriaImovel) {
		this.idSubCategoriaImovel = idSubCategoriaImovel;
	}
	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}
	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	/**
	 * @return Retorna o campo localidadeDescricao.
	 */
	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}
	/**
	 * @param localidadeDescricao O localidadeDescricao a ser setado.
	 */
	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}
	/**
	 * @return Retorna o campo logradouro.
	 */
	public String getLogradouro() {
		return logradouro;
	}
	/**
	 * @param logradouro O logradouro a ser setado.
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	/**
	 * @return Retorna o campo lote.
	 */
	public String getLote() {
		return lote;
	}
	/**
	 * @param lote O lote a ser setado.
	 */
	public void setLote(String lote) {
		this.lote = lote;
	}
	/**
	 * @return Retorna o campo matricula.
	 */
	public String getMatricula() {
		return matricula;
	}
	/**
	 * @param matricula O matricula a ser setado.
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	/**
	 * @return Retorna o campo municipio.
	 */
	public String getMunicipio() {
		return municipio;
	}
	/**
	 * @param municipio O municipio a ser setado.
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}
	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	/**
	 * @return Retorna o campo numero.
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * @param numero O numero a ser setado.
	 */
	public void setNumero(String numero) {
		this.numero = numero;
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
	 * @return Retorna o campo numeroContratoEnergia.
	 */
	public String getNumeroContratoEnergia() {
		return numeroContratoEnergia;
	}
	/**
	 * @param numeroContratoEnergia O numeroContratoEnergia a ser setado.
	 */
	public void setNumeroContratoEnergia(String numeroContratoEnergia) {
		this.numeroContratoEnergia = numeroContratoEnergia;
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
	 * @return Retorna o campo numeroMoradores.
	 */
	public String getNumeroMoradores() {
		return numeroMoradores;
	}
	/**
	 * @param numeroMoradores O numeroMoradores a ser setado.
	 */
	public void setNumeroMoradores(String numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}
	/**
	 * @return Retorna o campo numeroPontos.
	 */
	public String getNumeroPontos() {
		return numeroPontos;
	}
	/**
	 * @param numeroPontos O numeroPontos a ser setado.
	 */
	public void setNumeroPontos(String numeroPontos) {
		this.numeroPontos = numeroPontos;
	}
	/**
	 * @return Retorna o campo numeroPontoUtilizacao.
	 */
	public String getNumeroPontoUtilizacao() {
		return numeroPontoUtilizacao;
	}
	/**
	 * @param numeroPontoUtilizacao O numeroPontoUtilizacao a ser setado.
	 */
	public void setNumeroPontoUtilizacao(String numeroPontoUtilizacao) {
		this.numeroPontoUtilizacao = numeroPontoUtilizacao;
	}
	/**
	 * @return Retorna o campo pavimentoCalcada.
	 */
	public String getPavimentoCalcada() {
		return pavimentoCalcada;
	}
	/**
	 * @param pavimentoCalcada O pavimentoCalcada a ser setado.
	 */
	public void setPavimentoCalcada(String pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}
	/**
	 * @return Retorna o campo pavimentoRua.
	 */
	public String getPavimentoRua() {
		return pavimentoRua;
	}
	/**
	 * @param pavimentoRua O pavimentoRua a ser setado.
	 */
	public void setPavimentoRua(String pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}
	/**
	 * @return Retorna o campo perfilImovel.
	 */
	public String getPerfilImovel() {
		return perfilImovel;
	}
	/**
	 * @param perfilImovel O perfilImovel a ser setado.
	 */
	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
	/**
	 * @return Retorna o campo piscina.
	 */
	public String getPiscina() {
		return piscina;
	}
	/**
	 * @param piscina O piscina a ser setado.
	 */
	public void setPiscina(String piscina) {
		this.piscina = piscina;
	}
	/**
	 * @return Retorna o campo poco.
	 */
	public String getPoco() {
		return poco;
	}
	/**
	 * @param poco O poco a ser setado.
	 */
	public void setPoco(String poco) {
		this.poco = poco;
	}
	/**
	 * @return Retorna o campo quadraDescricao.
	 */
	public String getQuadraDescricao() {
		return quadraDescricao;
	}
	/**
	 * @param quadraDescricao O quadraDescricao a ser setado.
	 */
	public void setQuadraDescricao(String quadraDescricao) {
		this.quadraDescricao = quadraDescricao;
	}
	/**
	 * @return Retorna o campo quantidadeEconomia.
	 */
	public String getQuantidadeEconomia() {
		return quantidadeEconomia;
	}
	/**
	 * @param quantidadeEconomia O quantidadeEconomia a ser setado.
	 */
	public void setQuantidadeEconomia(String quantidadeEconomia) {
		this.quantidadeEconomia = quantidadeEconomia;
	}
	/**
	 * @return Retorna o campo reservatorioInferior.
	 */
	public String getReservatorioInferior() {
		return reservatorioInferior;
	}
	/**
	 * @param reservatorioInferior O reservatorioInferior a ser setado.
	 */
	public void setReservatorioInferior(String reservatorioInferior) {
		this.reservatorioInferior = reservatorioInferior;
	}
	/**
	 * @return Retorna o campo reservatorioSuperior.
	 */
	public String getReservatorioSuperior() {
		return reservatorioSuperior;
	}
	/**
	 * @param reservatorioSuperior O reservatorioSuperior a ser setado.
	 */
	public void setReservatorioSuperior(String reservatorioSuperior) {
		this.reservatorioSuperior = reservatorioSuperior;
	}
	/**
	 * @return Retorna o campo setorComercialDescricao.
	 */
	public String getSetorComercialDescricao() {
		return setorComercialDescricao;
	}
	/**
	 * @param setorComercialDescricao O setorComercialDescricao a ser setado.
	 */
	public void setSetorComercialDescricao(String setorComercialDescricao) {
		this.setorComercialDescricao = setorComercialDescricao;
	}
	/**
	 * @return Retorna o campo situacaoLigacaoAgua.
	 */
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	/**
	 * @param situacaoLigacaoAgua O situacaoLigacaoAgua a ser setado.
	 */
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	/**
	 * @return Retorna o campo situacaoLigacaoEsgoto.
	 */
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	/**
	 * @param situacaoLigacaoEsgoto O situacaoLigacaoEsgoto a ser setado.
	 */
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}
	/**
	 * @return Retorna o campo subLote.
	 */
	public String getSubLote() {
		return subLote;
	}
	/**
	 * @param subLote O subLote a ser setado.
	 */
	public void setSubLote(String subLote) {
		this.subLote = subLote;
	}
	/**
	 * @return Retorna o campo testadaLote.
	 */
	public String getTestadaLote() {
		return testadaLote;
	}
	/**
	 * @param testadaLote O testadaLote a ser setado.
	 */
	public void setTestadaLote(String testadaLote) {
		this.testadaLote = testadaLote;
	}
	/**
	 * @return Retorna o campo textoSelecionado.
	 */
	public String getTextoSelecionado() {
		return textoSelecionado;
	}
	/**
	 * @param textoSelecionado O textoSelecionado a ser setado.
	 */
	public void setTextoSelecionado(String textoSelecionado) {
		this.textoSelecionado = textoSelecionado;
	}
	/**
	 * @return Retorna o campo textoSelecionadoCategoria.
	 */
	public String getTextoSelecionadoCategoria() {
		return textoSelecionadoCategoria;
	}
	/**
	 * @param textoSelecionadoCategoria O textoSelecionadoCategoria a ser setado.
	 */
	public void setTextoSelecionadoCategoria(String textoSelecionadoCategoria) {
		this.textoSelecionadoCategoria = textoSelecionadoCategoria;
	}
	/**
	 * @return Retorna o campo textoSelecionadoSubCategoria.
	 */
	public String getTextoSelecionadoSubCategoria() {
		return textoSelecionadoSubCategoria;
	}
	/**
	 * @param textoSelecionadoSubCategoria O textoSelecionadoSubCategoria a ser setado.
	 */
	public void setTextoSelecionadoSubCategoria(String textoSelecionadoSubCategoria) {
		this.textoSelecionadoSubCategoria = textoSelecionadoSubCategoria;
	}
	/**
	 * @return Retorna o campo tipoCliente.
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}
	/**
	 * @param tipoCliente O tipoCliente a ser setado.
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	/**
	 * @return Retorna o campo tipoClienteImovel.
	 */
	public String getTipoClienteImovel() {
		return tipoClienteImovel;
	}
	/**
	 * @param tipoClienteImovel O tipoClienteImovel a ser setado.
	 */
	public void setTipoClienteImovel(String tipoClienteImovel) {
		this.tipoClienteImovel = tipoClienteImovel;
	}
	/**
	 * @return Retorna o campo tipoClienteImovelEconomia.
	 */
	public String getTipoClienteImovelEconomia() {
		return tipoClienteImovelEconomia;
	}
	/**
	 * @param tipoClienteImovelEconomia O tipoClienteImovelEconomia a ser setado.
	 */
	public void setTipoClienteImovelEconomia(String tipoClienteImovelEconomia) {
		this.tipoClienteImovelEconomia = tipoClienteImovelEconomia;
	}
	/**
	 * @return Retorna o campo tipoDespejo.
	 */
	public String getTipoDespejo() {
		return tipoDespejo;
	}
	/**
	 * @param tipoDespejo O tipoDespejo a ser setado.
	 */
	public void setTipoDespejo(String tipoDespejo) {
		this.tipoDespejo = tipoDespejo;
	}
	/**
	 * @return Retorna o campo tipoLogradouro.
	 */
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}
	/**
	 * @param tipoLogradouro O tipoLogradouro a ser setado.
	 */
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	/**
	 * @return Retorna o campo tipoOcupacao.
	 */
	public String getTipoOcupacao() {
		return tipoOcupacao;
	}
	/**
	 * @param tipoOcupacao O tipoOcupacao a ser setado.
	 */
	public void setTipoOcupacao(String tipoOcupacao) {
		this.tipoOcupacao = tipoOcupacao;
	}
	/**
	 * @return Retorna o campo tituloLogradouro.
	 */
	public String getTituloLogradouro() {
		return tituloLogradouro;
	}
	/**
	 * @param tituloLogradouro O tituloLogradouro a ser setado.
	 */
	public void setTituloLogradouro(String tituloLogradouro) {
		this.tituloLogradouro = tituloLogradouro;
	}
		
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		System.out.println("");
	}
	public String getInformacoesComplementares() {
		return informacoesComplementares;
	}
	public void setInformacoesComplementares(String informacoesComplementares) {
		this.informacoesComplementares = informacoesComplementares;
	}
	/**
	 * @return Returns the indicadorImovelSubcategoriaNaoPodeSerAtualizado.
	 */
	public String getIndicadorImovelSubcategoriaNaoPodeSerAtualizado() {
		return indicadorImovelSubcategoriaNaoPodeSerAtualizado;
	}
	/**
	 * @param indicadorImovelSubcategoriaNaoPodeSerAtualizado The indicadorImovelSubcategoriaNaoPodeSerAtualizado to set.
	 */
	public void setIndicadorImovelSubcategoriaNaoPodeSerAtualizado(
			String indicadorImovelSubcategoriaNaoPodeSerAtualizado) {
		this.indicadorImovelSubcategoriaNaoPodeSerAtualizado = indicadorImovelSubcategoriaNaoPodeSerAtualizado;
	}
}
