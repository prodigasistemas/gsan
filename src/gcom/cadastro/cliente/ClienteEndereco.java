/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.cadastro.cliente;

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
public class ClienteEndereco extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR, 
			Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})
	private String numero;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR, 
			Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})
	private String complemento;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR, 
			Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})
	private Short indicadorEnderecoCorrespondencia;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	@ControleAlteracao(value=FiltroClienteEndereco.ENDERECO_TIPO,
		funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR, 
			Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})
	private EnderecoTipo enderecoTipo;

	/** persistent field */
	private gcom.cadastro.cliente.Cliente cliente;

	/** persistent field */
	@ControleAlteracao(value=FiltroClienteEndereco.ENDERECO_REFERENCIA,
			funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR, 
				Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})	
	private EnderecoReferencia enderecoReferencia;

	@ControleAlteracao(value=FiltroClienteEndereco.LOGRADOURO_CEP,
			funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR, 
				Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})	
	private LogradouroCep logradouroCep;

	@ControleAlteracao(value=FiltroClienteEndereco.LOGRADOURO_BAIRRO,
			funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR, 
				Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})	
	private LogradouroBairro logradouroBairro;
	
	private Logradouro perimetroInicial;
    
    private Logradouro perimetroFinal;
	
	public final static Short INDICADOR_ENDERECO_CORRESPONDENCIA = new Short("1");

	/** full constructor */
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
	
	/** full constructor para atualização cadastral */
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

	/** default constructor */
	public ClienteEndereco() {
	}

	/** minimal constructor */
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

	/**
	 * < * Retorna o valor de enderecoFormatado < * < *
	 * 
	 * @return O valor de enderecoFormatado <
	 */
	public String getEnderecoFormatado() {
		String endereco = null;

		// verifica se o logradouro do imovel é diferente de null
		if (this.getLogradouroCep() != null
				&& this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(
						new Integer("0"))) {

			// verifica se o logradouro tipo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().equals("")) {
				// concatena o logradouro tipo do imovel
				endereco = this.getLogradouroCep().getLogradouro()
						.getLogradouroTipo().getDescricao().trim();
			}
			// verifica se o logradouro titulo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTitulo().equals("")) {
				// concatena o logradouro titulo do imovel
				endereco = endereco
						+ " "
						+ this.getLogradouroCep().getLogradouro()
								.getLogradouroTitulo().getDescricao().trim();
			}

			// concatena o logradouro do imovel
			endereco = endereco + " "
					+ this.getLogradouroCep().getLogradouro().getNome().trim();

			if (this.getEnderecoReferencia() != null
					&& !this.getEnderecoReferencia().equals("")) {
				if (this.getEnderecoReferencia().getDescricao() != null
						&& !this.getEnderecoReferencia().getDescricao().equals(
								"")) {
					endereco = endereco + " - "
							+ this.getEnderecoReferencia().getDescricao().trim();
				}
			}

			// concate o numero do imovel
			endereco = endereco + " - " + this.getNumero().trim();

			if (this.getComplemento() != null
					&& !this.getComplemento().equalsIgnoreCase("")) {
				endereco = endereco + " - " + this.getComplemento().trim();
			}

			if (this.getLogradouroBairro() != null
					&& this.getLogradouroBairro().getBairro() != null
					&& this.getLogradouroBairro().getBairro().getId()
							.intValue() != 0) {
				endereco = endereco + " - "
						+ this.getLogradouroBairro().getBairro().getNome().trim();

				if (this.getLogradouroBairro().getBairro().getMunicipio() != null
						&& this.getLogradouroBairro().getBairro()
								.getMunicipio().getId().intValue() != 0) {
					endereco = endereco
							+ " "
							+ this.getLogradouroBairro().getBairro()
									.getMunicipio().getNome().trim();
				}

				if (this.getLogradouroBairro().getBairro().getMunicipio()
						.getUnidadeFederacao() != null
						&& this.getLogradouroBairro().getBairro()
								.getMunicipio().getUnidadeFederacao().getId()
								.intValue() != 0) {
					endereco = endereco
							+ " "
							+ this.getLogradouroBairro().getBairro()
									.getMunicipio().getUnidadeFederacao()
									.getSigla().trim();
				}
			}

			if (this.getLogradouroCep() != null
					&& this.getLogradouroCep().getCep() != null) {
				// concatena o cep formatado do imóvel
				endereco = endereco + " "
						+ this.getLogradouroCep().getCep().getCepFormatado().trim();
			}
			
			if (this.getPerimetroInicial() != null) {
				endereco = endereco + " ENTRE " + this.getPerimetroInicial().getDescricaoFormatada() + " E " 
						+ this.getPerimetroFinal().getDescricaoFormatada();
			}

		}

		return endereco;
	}

	/**
	 * < * Retorna o valor de enderecoFormatado < * < *
	 * 
	 * @return O valor de enderecoFormatado <
	 */
	public String getEnderecoFormatadoAbreviado() {
		String endereco = null;

		// verifica se o logradouro do imovel é diferente de null
		if (this.getLogradouroCep() != null
				&& this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(
						new Integer("0"))) {
			
			

			// verifica se o logradouro tipo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().equals("")) {
				if (this.getLogradouroCep().getLogradouro().getLogradouroTipo()
						.getDescricaoAbreviada() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTipo().getDescricaoAbreviada()
								.equals("")) {
					// concatena o logradouro tipo do imovel
					endereco = this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().getDescricaoAbreviada().trim();
				}
			}
			// verifica se o logradouro titulo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTitulo().equals("")) {
				if (this.getLogradouroCep().getLogradouro()
						.getLogradouroTitulo().getDescricaoAbreviada() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTitulo().getDescricaoAbreviada()
								.equals("")) {
					// concatena o logradouro titulo do imovel
					endereco = endereco
							+ " "
							+ this.getLogradouroCep().getLogradouro()
									.getLogradouroTitulo()
									.getDescricaoAbreviada().trim();
				}
			}

			// concatena o logradouro do imovel
			endereco = endereco + " "
					+ this.getLogradouroCep().getLogradouro().getNome().trim();

			if (this.getEnderecoReferencia() != null
					&& !this.getEnderecoReferencia().equals("")) {
				if (this.getEnderecoReferencia().getDescricaoAbreviada() != null
						&& !this.getEnderecoReferencia().getDescricaoAbreviada().equals(
								"")) {
					endereco = endereco + ", "
							+ this.getEnderecoReferencia().getDescricaoAbreviada().trim();
				}
			}

			if (this.getNumero() != null && !this.getNumero().equals("")) {

				// concate o numero do imovel
				endereco = endereco + this.getNumero().trim();
			}

			if (this.getComplemento() != null
					&& !this.getComplemento().equalsIgnoreCase("")) {
				endereco = endereco + " - " + this.getComplemento().trim();
			}
			

			if (this.getLogradouroBairro() != null
					&& this.getLogradouroBairro().getBairro() != null
					&& this.getLogradouroBairro().getBairro().getId()
							.intValue() != 0) {
				endereco = endereco + " - "
						+ this.getLogradouroBairro().getBairro().getNome().trim();

				if (this.getLogradouroBairro().getBairro().getMunicipio() != null
						&& this.getLogradouroBairro().getBairro()
								.getMunicipio().getId().intValue() != 0) {
					endereco = endereco
							+ " "
							+ this.getLogradouroBairro().getBairro()
									.getMunicipio().getNome().trim();
					
					if (this.getLogradouroBairro().getBairro().getMunicipio()
							.getUnidadeFederacao() != null
							&& this.getLogradouroBairro().getBairro()
									.getMunicipio().getUnidadeFederacao().getId()
									.intValue() != 0) {
						endereco = endereco
								+ " "
								+ this.getLogradouroBairro().getBairro()
										.getMunicipio().getUnidadeFederacao()
										.getSigla().trim();
					}
				}

				
			}
			else if(this.getLogradouroCep().getLogradouro().getMunicipio()!= null){
				endereco = endereco
				+ " - "
				+ this.getLogradouroCep().getLogradouro().getMunicipio().getNome().trim();
				if (this.getLogradouroCep().getLogradouro().getMunicipio()
						.getUnidadeFederacao() != null
						&& this.getLogradouroCep().getLogradouro().getMunicipio().getUnidadeFederacao().getId()
								.intValue() != 0) {
					endereco = endereco
							+ " "
							+ this.getLogradouroCep().getLogradouro()
									.getMunicipio().getUnidadeFederacao()
									.getSigla().trim();
				}
				
			}

			if (this.getLogradouroCep() != null
					&& this.getLogradouroCep().getCep() != null) {
				// concatena o cep formatado do imóvel
				endereco = endereco + " "
						+ this.getLogradouroCep().getCep().getCepFormatado().trim();
			}
			
			if (this.getPerimetroInicial() != null) {
				endereco = endereco + " ENTRE " + this.getPerimetroInicial().getDescricaoFormatada() + " E " 
						+ this.getPerimetroFinal().getDescricaoFormatada();
			}

		}

		return endereco;
	}
	
	public String getEnderecoTipoTituloLogradouro() {
		String endereco = null;

		// verifica se o logradouro do imovel é diferente de null
		if (this.getLogradouroCep() != null
				&& this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(
						new Integer("0"))) {			

			// verifica se o logradouro tipo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().equals("")) {
				if (this.getLogradouroCep().getLogradouro().getLogradouroTipo()
						.getDescricao() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTipo().getDescricao()
								.equals("")) {
					// concatena o logradouro tipo do imovel
					endereco = this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().getDescricao().trim();
				}
			}
			// verifica se o logradouro titulo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTitulo().equals("")) {
				if (this.getLogradouroCep().getLogradouro()
						.getLogradouroTitulo().getDescricao() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTitulo().getDescricao()
								.equals("")) {
					// concatena o logradouro titulo do imovel
					endereco = endereco
							+ " "
							+ this.getLogradouroCep().getLogradouro()
									.getLogradouroTitulo()
									.getDescricao().trim();
				}
			}

			// concatena o logradouro do imovel
				endereco = endereco + " "
						+ this.getLogradouroCep().getLogradouro().getNome().trim();
			
			if (this.getEnderecoReferencia() != null
					&& !this.getEnderecoReferencia().equals("")) {
				if (this.getEnderecoReferencia().getDescricaoAbreviada() != null
						&& !this.getEnderecoReferencia().getDescricao().equals(
								"")) {
					endereco = endereco + ", "
							+ this.getEnderecoReferencia().getDescricao().trim();
				}
			}

		}

		return endereco;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param other
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
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
		
//		return new EqualsBuilder().append(this.getCliente().getId(),
//				castOther.getCliente().getId()).append(
//				this.getLogradouroBairro() != null ? this.getLogradouroBairro().getBairro().getId() : null,
//				castOther.getLogradouroBairro() != null ? castOther.getLogradouroBairro().getBairro().getId() : null)
//				.append(this.getLogradouroCep() != null? this.getLogradouroCep().getCep().getCepId() : null,
//				castOther.getLogradouroCep() != null? castOther.getLogradouroCep().getCep().getCepId() : null)
//				.append(this.getComplemento(), castOther.getComplemento())
//				.append(this.getUltimaAlteracao().getTime(), castOther.getUltimaAlteracao().getTime())
//				.isEquals();
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
//		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(
//				FiltroClienteEndereco.CEP_MUNICIPIO);
		filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade(
				FiltroClienteEndereco.BAIRRO_MUNICIPIO_UNIDADE_FEDERACAO);
		return filtroClienteEndereco;
	}	
	
	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	/**
	 * @return Retorna o campo perimetroFinal.
	 */
	public Logradouro getPerimetroFinal() {
		return perimetroFinal;
	}

	/**
	 * @param perimetroFinal O perimetroFinal a ser setado.
	 */
	public void setPerimetroFinal(Logradouro perimetroFinal) {
		this.perimetroFinal = perimetroFinal;
	}

	/**
	 * @return Retorna o campo perimetroInicial.
	 */
	public Logradouro getPerimetroInicial() {
		return perimetroInicial;
	}

	/**
	 * @param perimetroInicial O perimetroInicial a ser setado.
	 */
	public void setPerimetroInicial(Logradouro perimetroInicial) {
		this.perimetroInicial = perimetroInicial;
	}

//	@Override
//	public String getDescricaoParaRegistroTransacao() {
//		return ""; //getLogradouroCep().getDescricaoParaRegistroTransacao() + " - " + this.numero;
//	}

}
