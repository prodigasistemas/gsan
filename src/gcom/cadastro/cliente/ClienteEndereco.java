package gcom.cadastro.cliente;

import gcom.atualizacaocadastral.IClienteEndereco;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.EnderecoTipo;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.FiltroLogradouroCep;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao
public class ClienteEndereco extends ObjetoTransacao implements IClienteEndereco {
	
	private static final long serialVersionUID = 1L;

	private Integer id;

	@ControleAlteracao(funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR,Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})
	private String numero;

	@ControleAlteracao(funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR, Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})
	private String complemento;

	@ControleAlteracao(funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR, Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})
	private Short indicadorEnderecoCorrespondencia;

	private Date ultimaAlteracao;

	@ControleAlteracao(value=FiltroClienteEndereco.ENDERECO_TIPO, funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR, 
			Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})
	private EnderecoTipo enderecoTipo;

	private gcom.cadastro.cliente.Cliente cliente;

	@ControleAlteracao(value=FiltroClienteEndereco.ENDERECO_REFERENCIA, funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR, Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})	
	private EnderecoReferencia enderecoReferencia;

	@ControleAlteracao(value=FiltroClienteEndereco.LOGRADOURO_CEP, funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR, Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})	
	private LogradouroCep logradouroCep;

	@ControleAlteracao(value=FiltroClienteEndereco.LOGRADOURO_BAIRRO,
			funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR, 
				Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})	
	private LogradouroBairro logradouroBairro;
	
	private Logradouro perimetroInicial;
    
    private Logradouro perimetroFinal;
	
	public final static Short INDICADOR_ENDERECO_CORRESPONDENCIA = new Short("1");

	public ClienteEndereco(String numero, String complemento,
			Short indicadorEnderecoCorrespondencia, Date ultimaAlteracao,
			EnderecoTipo enderecoTipo, gcom.cadastro.cliente.Cliente cliente,
			EnderecoReferencia enderecoReferencia, LogradouroCep logradouroCep,
			LogradouroBairro logradouroBairro) {
		this.numero = numero;
		this.complemento = complemento;
		this.indicadorEnderecoCorrespondencia = indicadorEnderecoCorrespondencia;
		this.ultimaAlteracao = ultimaAlteracao;
		this.enderecoTipo = enderecoTipo;
		this.cliente = cliente;
		this.enderecoReferencia = enderecoReferencia;
		this.logradouroCep = logradouroCep;
		this.logradouroBairro = logradouroBairro;
	}
	
	public ClienteEndereco(ClienteEndereco clienteEnderecoCadastrado) {
		this.numero = clienteEnderecoCadastrado.getNumero();
		this.complemento = clienteEnderecoCadastrado.getComplemento();
		this.indicadorEnderecoCorrespondencia = clienteEnderecoCadastrado.getIndicadorEnderecoCorrespondencia();
		this.ultimaAlteracao = clienteEnderecoCadastrado.getUltimaAlteracao();
		this.enderecoTipo = clienteEnderecoCadastrado.getEnderecoTipo();
		this.cliente = clienteEnderecoCadastrado.getCliente();
		this.enderecoReferencia = clienteEnderecoCadastrado.getEnderecoReferencia();
		this.logradouroCep = clienteEnderecoCadastrado.getLogradouroCep();
		this.logradouroBairro = clienteEnderecoCadastrado.getLogradouroBairro();
	}

	public ClienteEndereco() {
	}

	public ClienteEndereco(EnderecoTipo enderecoTipo,
			gcom.cadastro.cliente.Cliente cliente,
			EnderecoReferencia enderecoReferencia, LogradouroCep logradouroCep,
			LogradouroBairro logradouroBairro) {
		this.enderecoTipo = enderecoTipo;
		this.cliente = cliente;
		this.enderecoReferencia = enderecoReferencia;
		this.logradouroCep = logradouroCep;
		this.logradouroBairro = logradouroBairro;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Short getIndicadorEnderecoCorrespondencia() {
		return this.indicadorEnderecoCorrespondencia;
	}

	public void setIndicadorEnderecoCorrespondencia(
			Short indicadorEnderecoCorrespondencia) {
		this.indicadorEnderecoCorrespondencia = indicadorEnderecoCorrespondencia;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public EnderecoTipo getEnderecoTipo() {
		return this.enderecoTipo;
	}

	public void setEnderecoTipo(EnderecoTipo enderecoTipo) {
		this.enderecoTipo = enderecoTipo;
	}

	public gcom.cadastro.cliente.Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(gcom.cadastro.cliente.Cliente cliente) {
		this.cliente = cliente;
	}

	public EnderecoReferencia getEnderecoReferencia() {
		return this.enderecoReferencia;
	}

	public void setEnderecoReferencia(EnderecoReferencia enderecoReferencia) {
		this.enderecoReferencia = enderecoReferencia;
	}

	public String getEnderecoFormatado() {
		String endereco = null;

		if (this.getLogradouroCep() != null && this.getLogradouroCep().hasLogradouro()) {
			if (this.getLogradouroCep().hasLogradouroTipo()) {
				endereco = this.getLogradouroCep().getLogradouro().getLogradouroTipo().getDescricao().trim();
			}
			if (this.getLogradouroCep().hasTitulo()) {
				endereco += " " + this.getLogradouroCep().getLogradouro().getLogradouroTitulo().getDescricao().trim();
			}

			endereco += " " + this.getLogradouroCep().getLogradouro().getNome().trim();

			if (this.getEnderecoReferencia() != null && !this.getEnderecoReferencia().equals("") && this.getEnderecoReferencia().hasDescricao()) {
				endereco +=  " - " + this.getEnderecoReferencia().getDescricao().trim();
			}

			endereco = endereco + " - " + this.getNumero().trim();

			if (this.getComplemento() != null && !this.getComplemento().equalsIgnoreCase("")) {
				endereco = endereco + " - " + this.getComplemento().trim();
			}

			if (this.getLogradouroBairro() != null && this.getLogradouroBairro().hasBairro()) {
				endereco += " - " + this.getLogradouroBairro().getBairro().getNome().trim();

				if (this.getLogradouroBairro().hasMunicipio()) {
					endereco += " " + this.getLogradouroBairro().getBairro().getMunicipio().getNome().trim();
				}

				if (this.getLogradouroBairro().hasUnidadeFederacao()) {
					endereco += " " + this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao().getSigla().trim();
				}
			}

			if (this.getLogradouroCep() != null && this.getLogradouroCep().getCep() != null) {
				endereco += " " + this.getLogradouroCep().getCep().getCepFormatado().trim();
			}
			
			if (this.getPerimetroInicial() != null) {
				endereco += " ENTRE " + this.getPerimetroInicial().getDescricaoFormatada() + " E " + this.getPerimetroFinal().getDescricaoFormatada();
			}
		}

		return endereco;
	}

	public String getEnderecoFormatadoAbreviado() {
		String endereco = null;

		if (this.getLogradouroCep() != null && this.getLogradouroCep().hasLogradouro()) {
			if (this.getLogradouroCep().hasLogradouroTipo() && this.getLogradouroCep().hasTipoDescricaoAbreviada()) {
				endereco = this.getLogradouroCep().getLogradouro().getLogradouroTipo().getDescricaoAbreviada().trim();
			}
			if (this.getLogradouroCep().hasTitulo() && this.getLogradouroCep().hasDescricaoAbreviada()) {
				endereco += " " + this.getLogradouroCep().getLogradouro().getLogradouroTitulo().getDescricaoAbreviada().trim();
			}

			endereco += " " + this.getLogradouroCep().getLogradouro().getNome().trim();

			if (this.getEnderecoReferencia() != null && !this.getEnderecoReferencia().equals("") && this.getEnderecoReferencia().hasDescricaoAbreviada()) {
					endereco +=  ", " + this.getEnderecoReferencia().getDescricaoAbreviada().trim();
			}

			if (this.getNumero() != null && !this.getNumero().equals("")) {
				endereco += this.getNumero().trim();
			}

			if (this.getComplemento() != null && !this.getComplemento().equalsIgnoreCase("")) {
				endereco +=  " - " + this.getComplemento().trim();
			}

			if (this.getLogradouroBairro() != null && this.getLogradouroBairro().hasBairro()) {
				endereco +=  " - " + this.getLogradouroBairro().getBairro().getNome().trim();

				if (this.getLogradouroBairro().hasMunicipio()) {
					endereco += " " + this.getLogradouroBairro().getBairro().getMunicipio().getNome().trim();
					
					if (this.getLogradouroBairro().hasUnidadeFederacao()) {
						endereco += " " + this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao().getSigla().trim();
					}
				}
			}
			else if(this.getLogradouroCep() != null && this.getLogradouroCep().hasMunicipio()){
				endereco += " - " + this.getLogradouroCep().getLogradouro().getMunicipio().getNome().trim();
				
				if (this.getLogradouroCep().hasUnidadeFederacao()) {
					endereco += " " + this.getLogradouroCep().getLogradouro().getMunicipio().getUnidadeFederacao().getSigla().trim();
				}
				
			}

			if (this.getLogradouroCep() != null && this.getLogradouroCep().hasLogradouro() && this.getLogradouroCep().hasCep()) {
				endereco += " " + this.getLogradouroCep().getCep().getCepFormatado().trim();
			}
			
			if (this.getPerimetroInicial() != null && this.getPerimetroFinal() != null) {
				endereco += " ENTRE " + this.getPerimetroInicial().getDescricaoFormatada() + " E " + this.getPerimetroFinal().getDescricaoFormatada();
			}
		}

		return endereco;
	}

	public String getEnderecoTipoTituloLogradouro() {
		String endereco = null;

		if (this.getLogradouroCep() != null && this.getLogradouroCep().hasLogradouro()) {			

			if (this.getLogradouroCep().hasLogradouroTipo() && this.getLogradouroCep().hasTipoDescricao()) {
				endereco = this.getLogradouroCep().getLogradouro().getLogradouroTipo().getDescricao().trim();
			}

			if (this.getLogradouroCep().hasTitulo() && this.getLogradouroCep().hasTituloDescricao()) {
				endereco += " " + this.getLogradouroCep().getLogradouro().getLogradouroTitulo().getDescricao().trim();
			}

			endereco += " " + this.getLogradouroCep().getLogradouro().getNome().trim();
			
			if (this.getEnderecoReferencia() != null && !this.getEnderecoReferencia().equals("") && this.getEnderecoReferencia().hasDescricao()) {
				endereco += ", " + this.getEnderecoReferencia().getDescricao().trim();
			}
		}

		return endereco;
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof ClienteEndereco)) {
			return false;
		}
		ClienteEndereco castOther = (ClienteEndereco) other;

		if (this.getId() == null || castOther.getId() == null){
			return false;
		}
		
		return this.getId().intValue() == castOther.getId().intValue();
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public LogradouroBairro getLogradouroBairro() {
		return logradouroBairro;
	}

	public void setLogradouroBairro(LogradouroBairro logradouroBairro) {
		this.logradouroBairro = logradouroBairro;
	}

	public LogradouroCep getLogradouroCep() {
		return logradouroCep;
	}

	public void setLogradouroCep(LogradouroCep logradouroCep) {
		this.logradouroCep = logradouroCep;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
		filtroClienteEndereco.adicionarParametro(new ParametroSimples(
				FiltroClienteEndereco.ID, this.getId()));
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteEndereco.LOGRADOURO_CEP);
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteEndereco.LOGRADOURO_CEP 
				+ "." + FiltroLogradouroCep.LOGRADOURO);
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteEndereco.LOGRADOURO_CEP 
				+ "." + FiltroLogradouroCep.CEP);
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteEndereco.LOGRADOURO_BAIRRO);
		return filtroClienteEndereco;
	}

	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
		filtroClienteEndereco.adicionarParametro(new ParametroSimples(
				FiltroClienteEndereco.ID, this.getId()));
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(
				FiltroClienteEndereco.LOGRADOURO_CEP);
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(
				FiltroClienteEndereco.LOGRADOURO_CEP 
				+ "." + FiltroLogradouroCep.LOGRADOURO);
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(
				FiltroClienteEndereco.LOGRADOURO_CEP + "." 
				+ FiltroLogradouroCep.LOGRADOURO + "." 
				+ FiltroLogradouro.LOGRADOUROTIPO);
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(
				FiltroClienteEndereco.LOGRADOURO_CEP 
				+ "." + FiltroLogradouroCep.LOGRADOURO + "." 
				+ FiltroLogradouro.LOGRADOUROTITULO);				
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(
				FiltroClienteEndereco.LOGRADOURO_CEP + "." + FiltroLogradouroCep.CEP);
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(
				FiltroClienteEndereco.LOGRADOURO_BAIRRO);
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(
				FiltroClienteEndereco.ENDERECO_TIPO);
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(
				FiltroClienteEndereco.ENDERECO_REFERENCIA);
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(
				FiltroClienteEndereco.LOGRADOURO_MUNICIPIO);
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(
				FiltroClienteEndereco.BAIRRO_MUNICIPIO_UNIDADE_FEDERACAO);
		return filtroClienteEndereco;
	}	
	
	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	public Logradouro getPerimetroFinal() {
		return perimetroFinal;
	}

	public void setPerimetroFinal(Logradouro perimetroFinal) {
		this.perimetroFinal = perimetroFinal;
	}

	public Logradouro getPerimetroInicial() {
		return perimetroInicial;
	}

	public void setPerimetroInicial(Logradouro perimetroInicial) {
		this.perimetroInicial = perimetroInicial;
	}

	public String getNomeMunicipio() {
		try{
			return this.logradouroCep.getLogradouro().getMunicipio().getNome();
		} catch(NullPointerException e){
			return null;
		}
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		
	}

	public String getNomeBairro() {
		try{
			return this.logradouroBairro.getBairro().getNome();
		} catch(NullPointerException e){
			return null;
		}
	}

	public void setNomeBairro(String nomeBairro) {
		
	}

	public String getDescricaoLogradouro() {
		try{
			return this.logradouroCep.getLogradouro().getNome();
		} catch(NullPointerException e){
			return null;
		}
	}

	public void setDescricaoLogradouro(String descricaoLogradouro) {
		
	}
	
	public Integer getCodigoCep(){
		return this.logradouroCep.getCep().getCodigo();
	}
	
	public void setCodigoCep(Integer codigoCep){
		
	}

	public void getPerimetroInicial(Logradouro perimetroInicial) {
		
		
	}
}
