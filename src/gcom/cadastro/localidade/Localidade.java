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
package gcom.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.geografico.Municipio;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

import javax.jms.Session;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Localidade extends ObjetoTransacao {


	public final static int AURORA = 347;
    public final static int CABANGA = 339;
    public final static int ALTO_DO_CEU = 360;
    public final static int JENIPAPO = 735;
    
    public final static Short BLOQUEIO_INSERIR_IMOVEL_SIM = new Short("1");
	public final static Short BLOQUEIO_INSERIR_IMOVEL_NAO = new Short("2");
	
	/**
	 * 
	 */
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** persistent field */
	private String descricao;

	/** nullable persistent field */
	private String numeroImovel;

	/** nullable persistent field */
	private String complementoEndereco;

	/** persistent field */
	private String fone;

	/** persistent field */
	private String ramalfone;

	/** nullable persistent field */
	private String fax;

	/** nullable persistent field */
	private String email;

	/** persistent field */
	private int consumoGrandeUsuario;

	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private gcom.cadastro.localidade.LocalidadePorte localidadePorte;

	/** persistent field */
	private gcom.cadastro.localidade.Localidade localidade;

	/** persistent field */
	private gcom.cadastro.localidade.GerenciaRegional gerenciaRegional;

	/** persistent field */
	private EnderecoReferencia enderecoReferencia;

	/** persistent field */
	private gcom.cadastro.localidade.LocalidadeClasse localidadeClasse;

	private LogradouroCep logradouroCep;

	private LogradouroBairro logradouroBairro;

	private Integer codigoICMS;

	private String codigoCentroCusto;
	
	private Short indicadorLocalidadeInformatizada;
	
	private Cliente cliente;
	
	private HidrometroLocalArmazenagem hidrometroLocalArmazenagem;

	/** persistent field */
	private UnidadeNegocio unidadeNegocio;
	
	private Short indicadorBloqueio = BLOQUEIO_INSERIR_IMOVEL_NAO;

	private Short indicadorLocalidadeSede;

	private String codigoCentroCustoEsgoto;

	private Municipio municipio;
	
	public Short getIndicadorBloqueio() {
		return indicadorBloqueio;
	}

	public void setIndicadorBloqueio(Short indicadorBloqueio) {
		this.indicadorBloqueio = indicadorBloqueio;
	}

	public String getCodigoCentroCusto() {
		return codigoCentroCusto;
	}

	public void setCodigoCentroCusto(String codigoCentroCusto) {
		this.codigoCentroCusto = codigoCentroCusto;
	}

	public Integer getCodigoICMS() {
		return codigoICMS;
	}

	public void setCodigoICMS(Integer codigoICMS) {
		this.codigoICMS = codigoICMS;
	}

	/** full constructor */
	public Localidade(Integer id, String descricao, String numeroImovel,
			String complementoEndereco, String fone, String ramalfone,
			String fax, String email, int consumoGrandeUsuario,
			Short indicadorUso, Date ultimaAlteracao,
			gcom.cadastro.localidade.LocalidadePorte localidadePorte,
			gcom.cadastro.localidade.Localidade localidade,
			gcom.cadastro.localidade.GerenciaRegional gerenciaRegional,
			EnderecoReferencia enderecoReferencia,
			gcom.cadastro.localidade.LocalidadeClasse localidadeClasse,
			LogradouroCep logradouroCep, LogradouroBairro logradouroBairro) {
		this.id = id;
		this.descricao = descricao;
		this.numeroImovel = numeroImovel;
		this.complementoEndereco = complementoEndereco;
		this.fone = fone;
		this.ramalfone = ramalfone;
		this.fax = fax;
		this.email = email;
		this.consumoGrandeUsuario = consumoGrandeUsuario;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.localidadePorte = localidadePorte;
		this.localidade = localidade;
		this.gerenciaRegional = gerenciaRegional;
		this.enderecoReferencia = enderecoReferencia;
		this.localidadeClasse = localidadeClasse;
		this.logradouroCep = logradouroCep;
		this.logradouroBairro = logradouroBairro;
	}

	/** default constructor */
	public Localidade() {
	}

	// Construido por Sávio Luiz para setar o id no objeto
	public Localidade(Integer id) {
		this.id = id;
	}

	/** minimal constructor */
	public Localidade(Integer id, String descricao, String fone,
			String ramalfone, int consumoGrandeUsuario,
			gcom.cadastro.localidade.LocalidadePorte localidadePorte,
			gcom.cadastro.localidade.Localidade localidade,
			gcom.cadastro.localidade.GerenciaRegional gerenciaRegional,
			EnderecoReferencia enderecoReferencia,
			gcom.cadastro.localidade.LocalidadeClasse localidadeClasse,
			LogradouroCep logradouroCep, LogradouroBairro logradouroBairro) {
		this.id = id;
		this.descricao = descricao;
		this.fone = fone;
		this.ramalfone = ramalfone;
		this.consumoGrandeUsuario = consumoGrandeUsuario;
		this.localidadePorte = localidadePorte;
		this.localidade = localidade;
		this.gerenciaRegional = gerenciaRegional;
		this.enderecoReferencia = enderecoReferencia;
		this.localidadeClasse = localidadeClasse;
		this.logradouroCep = logradouroCep;
		this.logradouroBairro = logradouroBairro;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNumeroImovel() {
		return this.numeroImovel;
	}

	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}

	public String getComplementoEndereco() {
		return this.complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public String getFone() {
		return this.fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getRamalfone() {
		return this.ramalfone;
	}

	public void setRamalfone(String ramalfone) {
		this.ramalfone = ramalfone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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

	public gcom.cadastro.localidade.LocalidadePorte getLocalidadePorte() {
		return this.localidadePorte;
	}

	public void setLocalidadePorte(
			gcom.cadastro.localidade.LocalidadePorte localidadePorte) {
		this.localidadePorte = localidadePorte;
	}

	public gcom.cadastro.localidade.Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(gcom.cadastro.localidade.Localidade localidade) {
		this.localidade = localidade;
	}

	public gcom.cadastro.localidade.GerenciaRegional getGerenciaRegional() {
		return this.gerenciaRegional;
	}

	public void setGerenciaRegional(
			gcom.cadastro.localidade.GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public EnderecoReferencia getEnderecoReferencia() {
		return this.enderecoReferencia;
	}

	public void setEnderecoReferencia(EnderecoReferencia enderecoReferencia) {
		this.enderecoReferencia = enderecoReferencia;
	}

	public gcom.cadastro.localidade.LocalidadeClasse getLocalidadeClasse() {
		return this.localidadeClasse;
	}

	public void setLocalidadeClasse(
			gcom.cadastro.localidade.LocalidadeClasse localidadeClasse) {
		this.localidadeClasse = localidadeClasse;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param session
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public boolean onSave(Session session) {
		if (this.localidade == null) {
			Localidade localidadeElo = new Localidade();

			localidadeElo.setId(this.id);
			this.localidade = localidadeElo;
		}
		return false;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param session
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public boolean onUpdate(Session session) {
		return false;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param session
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public boolean onDelete(Session session) {
		return false;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param session
	 *            Descrição do parâmetro
	 * @param serializable
	 *            Descrição do parâmetro
	 */
	public void onLoad(Session session, Serializable serializable) {
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

	/**
	 * Gets the endereco attribute of the Imovel object
	 * 
	 * @return The endereco value
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
						.getLogradouroTipo().getDescricao();
			}
			// verifica se o logradouro titulo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTitulo().equals("")) {
				// concatena o logradouro titulo do imovel
				endereco = endereco
						+ " "
						+ this.getLogradouroCep().getLogradouro()
								.getLogradouroTitulo().getDescricao();
			}

			// concatena o logradouro do imovel
			endereco = endereco + " "
					+ this.getLogradouroCep().getLogradouro().getNome();

			if (this.getEnderecoReferencia() != null
					&& !this.getEnderecoReferencia().equals("")) {
				if (this.getEnderecoReferencia().getDescricao() != null
						&& !this.getEnderecoReferencia().getDescricao().equals(
								"")) {
					endereco = endereco + " - "
							+ this.getEnderecoReferencia().getDescricao();
				}
			}

			// concate o numero do imovel
			endereco = endereco + " - " + this.getNumeroImovel();

			if (this.getComplementoEndereco() != null
					&& !this.getComplementoEndereco().equalsIgnoreCase("")) {
				endereco = endereco + " - " + this.getComplementoEndereco();
			}

			if (this.getLogradouroBairro() != null
					&& this.getLogradouroBairro().getBairro() != null
					&& this.getLogradouroBairro().getBairro().getId()
							.intValue() != 0) {
				endereco = endereco + " - "
						+ this.getLogradouroBairro().getBairro().getNome();

				if (this.getLogradouroBairro().getBairro().getMunicipio() != null
						&& this.getLogradouroBairro().getBairro()
								.getMunicipio().getId().intValue() != 0) {
					endereco = endereco
							+ " "
							+ this.getLogradouroBairro().getBairro()
									.getMunicipio().getNome();
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
									.getSigla();
				}
			}

			if (this.getLogradouroCep() != null
					&& this.getLogradouroCep().getCep() != null) {
				// concatena o cep formatado do imóvel
				endereco = endereco + " "
						+ this.getLogradouroCep().getCep().getCepFormatado();
			}

		}

		return endereco;
	}
	
	public String getEnderecoFormatadoTituloAbreviado() {
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
						.getLogradouroTipo().getDescricao();
			}
			// verifica se o logradouro titulo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTitulo().equals("")) {
				// concatena o logradouro titulo do imovel
				endereco = endereco
						+ " "
						+ this.getLogradouroCep().getLogradouro()
								.getLogradouroTitulo().getDescricaoAbreviada();
			}

			// concatena o logradouro do imovel
			endereco = endereco + " "
					+ this.getLogradouroCep().getLogradouro().getNome();

			if (this.getEnderecoReferencia() != null
					&& !this.getEnderecoReferencia().equals("")) {
				if (this.getEnderecoReferencia().getDescricao() != null
						&& !this.getEnderecoReferencia().getDescricao().equals(
								"")) {
					endereco = endereco + " - "
							+ this.getEnderecoReferencia().getDescricao();
				}
			}

			// concate o numero do imovel
			endereco = endereco + " - " + this.getNumeroImovel();

			if (this.getComplementoEndereco() != null
					&& !this.getComplementoEndereco().equalsIgnoreCase("")) {
				endereco = endereco + " - " + this.getComplementoEndereco();
			}

			if (this.getLogradouroBairro() != null
					&& this.getLogradouroBairro().getBairro() != null
					&& this.getLogradouroBairro().getBairro().getId()
							.intValue() != 0) {
				endereco = endereco + " - "
						+ this.getLogradouroBairro().getBairro().getNome();

				if (this.getLogradouroBairro().getBairro().getMunicipio() != null
						&& this.getLogradouroBairro().getBairro()
								.getMunicipio().getId().intValue() != 0) {
					endereco = endereco
							+ " "
							+ this.getLogradouroBairro().getBairro()
									.getMunicipio().getNome();
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
									.getSigla();
				}
			}

			if (this.getLogradouroCep() != null
					&& this.getLogradouroCep().getCep() != null) {
				// concatena o cep formatado do imóvel
				endereco = endereco + " "
						+ this.getLogradouroCep().getCep().getCepFormatado();
			}

		}

		return endereco;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("localidadePorte");
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("localidadeClasse");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
		filtroLocalidade
				.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, this.getId()));
		return filtroLocalidade;
	}

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio
	 *            O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public int getConsumoGrandeUsuario() {
		return consumoGrandeUsuario;
	}

	public void setConsumoGrandeUsuario(int consumoGrandeUsuario) {
		this.consumoGrandeUsuario = consumoGrandeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Short getIndicadorLocalidadeInformatizada() {
		return indicadorLocalidadeInformatizada;
	}

	public void setIndicadorLocalidadeInformatizada(
			Short indicadorLocalidadeInformatizada) {
		this.indicadorLocalidadeInformatizada = indicadorLocalidadeInformatizada;
	}

	public HidrometroLocalArmazenagem getHidrometroLocalArmazenagem() {
		return hidrometroLocalArmazenagem;
	}

	public void setHidrometroLocalArmazenagem(
			HidrometroLocalArmazenagem hidrometroLocalArmazenagem) {
		this.hidrometroLocalArmazenagem = hidrometroLocalArmazenagem;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		if (getDescricao()!= null){
			return this.id + " - " + this.descricao;
		}else{
			return null;
		}
	}
	
	@Override
	public void initializeLazy() {
		getDescricao();
		retornaCamposChavePrimaria();
	}

	public Short getIndicadorLocalidadeSede() {
		return indicadorLocalidadeSede;
	}

	public void setIndicadorLocalidadeSede(Short indicadorLocalidadeSede) {
		this.indicadorLocalidadeSede = indicadorLocalidadeSede;
	}

	public String getCodigoCentroCustoEsgoto() {
		return codigoCentroCustoEsgoto;
	}

	public void setCodigoCentroCustoEsgoto(String codigoCentroCustoEsgoto) {
		this.codigoCentroCustoEsgoto = codigoCentroCustoEsgoto;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
}
