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
package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.micromedicao.Rota;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroMarca;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ComandoOrdemSeletiva implements Serializable {
	
	private static final long serialVersionUID = 1L;


	private Integer	id;
	private Short situacaoComando;
	private String descricaoComando;
	private Date dataGeracao;
	private Date dataEncerramento;
	private Integer quantidadeOrdemServico;
	private Integer quantidadeMaximaOrdemServico;
	private Integer sequencialRotaInicial;
	private Integer sequencialRotaFinal;
	private Integer quantidadeConsecutivaAnormalidade;
	private Integer anoMesHidrometroInstInicial;
	private Integer anoMesHidrometroInstFinal;
	private Integer quantidadeEconomiaInicial;
	private Integer quantidadeEconomiaFinal;
	private Integer quantidadeDocumentoInicial;
	private Integer quantidadeDocumentoFinal;
	private Integer quantidadeMoradoresInicial;
	private Integer quantidadeMoradoresFinal;
	private BigDecimal areaConstruidaInicial;
	private BigDecimal areaConstruidaFinal;
	private Short indicadorImovelCondominio;
	private Integer mediaConsumo;
	private Integer consumoEconomiaInicial;
	private Integer consumoEconomiaFinal;
	private Date ultimaAlteracao;
	private Short indicadorGeracaoTxt;
	
	private ServicoTipo servicoTipo;
	private ImovelPerfil imovelPerfil;
	private Localidade localidadeInicial;
	private Localidade localidadeFinal;
	private Quadra quadraInicial;
	private Quadra quadraFinal;
	private SetorComercial setorComercialInicial;
	private SetorComercial setorComercialFinal;
	private Rota rotaInicial;
	private Rota rotaFinal;
	private Imovel imovel;
	private GerenciaRegional gerenciaRegional;
	private UnidadeNegocio unidadeNegocio;
	private Empresa empresa;
	private Localidade localidadePolo;
	private Logradouro logradouro;
	private HidrometroLocalInstalacao hidrometroLocalInstalacao;
	private HidrometroMarca hidrometroMarca;
	private Categoria categoria;
	private Subcategoria subcategoria;
	
	public ComandoOrdemSeletiva(Integer id, Short situacaoComando, String descricaoComando, Date dataGeracao, Date dataEncerramento, Integer quantidadeOrdemServico, Integer quantidadeMaximaOrdemServico, Integer sequencialRotaInicial, Integer sequencialRotaFinal, Integer quantidadeConsecutivaAnormalidade, Integer anoMesHidrometroInstInicial, Integer anoMesHidrometroInstFinal, Integer quantidadeEconomiaInicial, Integer quantidadeEconomiaFinal, Integer quantidadeDocumentoInicial, Integer quantidadeDocumentoFinal, Integer quantidadeMoradoresInicial, Integer quantidadeMoradoresFinal, BigDecimal areaConstruidaInicial, BigDecimal areaConstruidaFinal, Short indicadorImovelCondominio, Integer mediaConsumo, Integer consumoEconomiaInicial, Integer consumoEconomiaFinal, Date ultimaAlteracao, ServicoTipo servicoTipo, ImovelPerfil imovelPerfil, Localidade localidadeInicial, Localidade localidadeFinal, Quadra quadraInicial, Quadra quadraFinal, SetorComercial setorComercialInicial, SetorComercial setorComercialFinal, Rota rotaInicial, Rota rotaFinal, Imovel imovel, GerenciaRegional gerenciaRegional, UnidadeNegocio unidadeNegocio, Empresa empresa, Localidade localidadePolo, Logradouro logradouro, HidrometroLocalInstalacao hidrometroLocalInstalacao, HidrometroMarca hidrometroMarca, Categoria categoria, Subcategoria subcategoria) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.situacaoComando = situacaoComando;
		this.descricaoComando = descricaoComando;
		this.dataGeracao = dataGeracao;
		this.dataEncerramento = dataEncerramento;
		this.quantidadeOrdemServico = quantidadeOrdemServico;
		this.quantidadeMaximaOrdemServico = quantidadeMaximaOrdemServico;
		this.sequencialRotaInicial = sequencialRotaInicial;
		this.sequencialRotaFinal = sequencialRotaFinal;
		this.quantidadeConsecutivaAnormalidade = quantidadeConsecutivaAnormalidade;
		this.anoMesHidrometroInstInicial = anoMesHidrometroInstInicial;
		this.anoMesHidrometroInstFinal = anoMesHidrometroInstFinal;
		this.quantidadeEconomiaInicial = quantidadeEconomiaInicial;
		this.quantidadeEconomiaFinal = quantidadeEconomiaFinal;
		this.quantidadeDocumentoInicial = quantidadeDocumentoInicial;
		this.quantidadeDocumentoFinal = quantidadeDocumentoFinal;
		this.quantidadeMoradoresInicial = quantidadeMoradoresInicial;
		this.quantidadeMoradoresFinal = quantidadeMoradoresFinal;
		this.areaConstruidaInicial = areaConstruidaInicial;
		this.areaConstruidaFinal = areaConstruidaFinal;
		this.indicadorImovelCondominio = indicadorImovelCondominio;
		this.mediaConsumo = mediaConsumo;
		this.consumoEconomiaInicial = consumoEconomiaInicial;
		this.consumoEconomiaFinal = consumoEconomiaFinal;
		this.ultimaAlteracao = ultimaAlteracao;
		this.servicoTipo = servicoTipo;
		this.imovelPerfil = imovelPerfil;
		this.localidadeInicial = localidadeInicial;
		this.localidadeFinal = localidadeFinal;
		this.quadraInicial = quadraInicial;
		this.quadraFinal = quadraFinal;
		this.setorComercialInicial = setorComercialInicial;
		this.setorComercialFinal = setorComercialFinal;
		this.rotaInicial = rotaInicial;
		this.rotaFinal = rotaFinal;
		this.imovel = imovel;
		this.gerenciaRegional = gerenciaRegional;
		this.unidadeNegocio = unidadeNegocio;
		this.empresa = empresa;
		this.localidadePolo = localidadePolo;
		this.logradouro = logradouro;
		this.hidrometroLocalInstalacao = hidrometroLocalInstalacao;
		this.hidrometroMarca = hidrometroMarca;
		this.categoria = categoria;
		this.subcategoria = subcategoria;
	}
	public ComandoOrdemSeletiva() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getAnoMesHidrometroInstFinal() {
		return anoMesHidrometroInstFinal;
	}
	public void setAnoMesHidrometroInstFinal(Integer anoMesHidrometroInstFinal) {
		this.anoMesHidrometroInstFinal = anoMesHidrometroInstFinal;
	}
	public Integer getAnoMesHidrometroInstInicial() {
		return anoMesHidrometroInstInicial;
	}
	public void setAnoMesHidrometroInstInicial(Integer anoMesHidrometroInstInicial) {
		this.anoMesHidrometroInstInicial = anoMesHidrometroInstInicial;
	}
	public BigDecimal getAreaConstruidaFinal() {
		return areaConstruidaFinal;
	}
	public void setAreaConstruidaFinal(BigDecimal areaConstruidaFinal) {
		this.areaConstruidaFinal = areaConstruidaFinal;
	}
	public BigDecimal getAreaConstruidaInicial() {
		return areaConstruidaInicial;
	}
	public void setAreaConstruidaInicial(BigDecimal areaConstruidaInicial) {
		this.areaConstruidaInicial = areaConstruidaInicial;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Integer getConsumoEconomiaFinal() {
		return consumoEconomiaFinal;
	}
	public void setConsumoEconomiaFinal(Integer consumoEconomiaFinal) {
		this.consumoEconomiaFinal = consumoEconomiaFinal;
	}
	public Integer getConsumoEconomiaInicial() {
		return consumoEconomiaInicial;
	}
	public void setConsumoEconomiaInicial(Integer consumoEconomiaInicial) {
		this.consumoEconomiaInicial = consumoEconomiaInicial;
	}
	public Date getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	public Date getDataGeracao() {
		return dataGeracao;
	}
	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	public String getDescricaoComando() {
		return descricaoComando;
	}
	public void setDescricaoComando(String descricaoComando) {
		this.descricaoComando = descricaoComando;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public HidrometroLocalInstalacao getHidrometroLocalInstalacao() {
		return hidrometroLocalInstalacao;
	}
	public void setHidrometroLocalInstalacao(
			HidrometroLocalInstalacao hidrometroLocalInstalacao) {
		this.hidrometroLocalInstalacao = hidrometroLocalInstalacao;
	}
	public HidrometroMarca getHidrometroMarca() {
		return hidrometroMarca;
	}
	public void setHidrometroMarca(HidrometroMarca hidrometroMarca) {
		this.hidrometroMarca = hidrometroMarca;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}
	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}
	public Short getIndicadorImovelCondominio() {
		return indicadorImovelCondominio;
	}
	public void setIndicadorImovelCondominio(Short indicadorImovelCondominio) {
		this.indicadorImovelCondominio = indicadorImovelCondominio;
	}
	public Localidade getLocalidadeFinal() {
		return localidadeFinal;
	}
	public void setLocalidadeFinal(Localidade localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}
	public Localidade getLocalidadeInicial() {
		return localidadeInicial;
	}
	public void setLocalidadeInicial(Localidade localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}
	public Localidade getLocalidadePolo() {
		return localidadePolo;
	}
	public void setLocalidadePolo(Localidade localidadePolo) {
		this.localidadePolo = localidadePolo;
	}
	public Logradouro getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}
	public Integer getMediaConsumo() {
		return mediaConsumo;
	}
	public void setMediaConsumo(Integer mediaConsumo) {
		this.mediaConsumo = mediaConsumo;
	}
	public Quadra getQuadraFinal() {
		return quadraFinal;
	}
	public void setQuadraFinal(Quadra quadraFinal) {
		this.quadraFinal = quadraFinal;
	}
	public Quadra getQuadraInicial() {
		return quadraInicial;
	}
	public void setQuadraInicial(Quadra quadraInicial) {
		this.quadraInicial = quadraInicial;
	}
	public Integer getQuantidadeConsecutivaAnormalidade() {
		return quantidadeConsecutivaAnormalidade;
	}
	public void setQuantidadeConsecutivaAnormalidade(
			Integer quantidadeConsecutivaAnormalidade) {
		this.quantidadeConsecutivaAnormalidade = quantidadeConsecutivaAnormalidade;
	}
	public Integer getQuantidadeDocumentoFinal() {
		return quantidadeDocumentoFinal;
	}
	public void setQuantidadeDocumentoFinal(Integer quantidadeDocumentoFinal) {
		this.quantidadeDocumentoFinal = quantidadeDocumentoFinal;
	}
	public Integer getQuantidadeDocumentoInicial() {
		return quantidadeDocumentoInicial;
	}
	public void setQuantidadeDocumentoInicial(Integer quantidadeDocumentoInicial) {
		this.quantidadeDocumentoInicial = quantidadeDocumentoInicial;
	}
	public Integer getQuantidadeEconomiaFinal() {
		return quantidadeEconomiaFinal;
	}
	public void setQuantidadeEconomiaFinal(Integer quantidadeEconomiaFinal) {
		this.quantidadeEconomiaFinal = quantidadeEconomiaFinal;
	}
	public Integer getQuantidadeEconomiaInicial() {
		return quantidadeEconomiaInicial;
	}
	public void setQuantidadeEconomiaInicial(Integer quantidadeEconomiaInicial) {
		this.quantidadeEconomiaInicial = quantidadeEconomiaInicial;
	}
	public Integer getQuantidadeMaximaOrdemServico() {
		return quantidadeMaximaOrdemServico;
	}
	public void setQuantidadeMaximaOrdemServico(Integer quantidadeMaximaOrdemServico) {
		this.quantidadeMaximaOrdemServico = quantidadeMaximaOrdemServico;
	}
	public Integer getQuantidadeMoradoresFinal() {
		return quantidadeMoradoresFinal;
	}
	public void setQuantidadeMoradoresFinal(Integer quantidadeMoradoresFinal) {
		this.quantidadeMoradoresFinal = quantidadeMoradoresFinal;
	}
	public Integer getQuantidadeMoradoresInicial() {
		return quantidadeMoradoresInicial;
	}
	public void setQuantidadeMoradoresInicial(Integer quantidadeMoradoresInicial) {
		this.quantidadeMoradoresInicial = quantidadeMoradoresInicial;
	}
	public Integer getQuantidadeOrdemServico() {
		return quantidadeOrdemServico;
	}
	public void setQuantidadeOrdemServico(Integer quantidadeOrdemServico) {
		this.quantidadeOrdemServico = quantidadeOrdemServico;
	}
	public Rota getRotaFinal() {
		return rotaFinal;
	}
	public void setRotaFinal(Rota rotaFinal) {
		this.rotaFinal = rotaFinal;
	}
	public Rota getRotaInicial() {
		return rotaInicial;
	}
	public void setRotaInicial(Rota rotaInicial) {
		this.rotaInicial = rotaInicial;
	}
	public Integer getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}
	public void setSequencialRotaFinal(Integer sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}
	public Integer getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}
	public void setSequencialRotaInicial(Integer sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}
	public ServicoTipo getServicoTipo() {
		return servicoTipo;
	}
	public void setServicoTipo(ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}
	public SetorComercial getSetorComercialFinal() {
		return setorComercialFinal;
	}
	public void setSetorComercialFinal(SetorComercial setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}
	public SetorComercial getSetorComercialInicial() {
		return setorComercialInicial;
	}
	public void setSetorComercialInicial(SetorComercial setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}
	public Short getSituacaoComando() {
		return situacaoComando;
	}
	public void setSituacaoComando(Short situacaoComando) {
		this.situacaoComando = situacaoComando;
	}
	public Subcategoria getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public Short getIndicadorGeracaoTxt() {
		return indicadorGeracaoTxt;
	}
	public void setIndicadorGeracaoTxt(Short indicadorGeracaoTxt) {
		this.indicadorGeracaoTxt = indicadorGeracaoTxt;
	}
	
}
