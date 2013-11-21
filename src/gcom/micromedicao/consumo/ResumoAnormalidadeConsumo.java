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
package gcom.micromedicao.consumo;

import java.util.Date;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.micromedicao.Rota;

/**
 * Classe Basica
 * 
 * 
 * @author Thiago Toscano
 * @date 12/05/2006
 */
public class ResumoAnormalidadeConsumo {

	private Integer id;

	private Integer anoMesReferencia;

	private Integer codigoSetorComercial;

	private Integer numeroQuadra;

	private Integer quantidadeLigacoes;

	private GerenciaRegional gerenciaRegional;

	private Localidade localidade;

	private SetorComercial setorComercial;

	private Rota rota;

	private Quadra quadra;

	private ImovelPerfil imovelPerfil;

	private LigacaoAguaSituacao ligacaoAguaSituacao;

	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

	private Categoria categoria;

	private EsferaPoder esferaPoder;

	private ConsumoAnormalidade consumoAnormalidade;

	private LigacaoTipo ligacaoTipo;
	
	/** nullable persistent field */
    private Date ultimaAlteracao;

	public ResumoAnormalidadeConsumo(Integer id, Integer anoMesReferencia, Integer codigoSetorComercial, Integer numeroQuadra, Integer quantidadeLigacoes, GerenciaRegional gerenciaRegional, Localidade localidade, SetorComercial setorComercial, Rota rota, Quadra quadra, ImovelPerfil imovelPerfil, LigacaoAguaSituacao ligacaoAguaSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Categoria categoria, EsferaPoder esferaPoder, ConsumoAnormalidade consumoAnormalidade, LigacaoTipo ligacaoTipo) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.anoMesReferencia = anoMesReferencia;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.quantidadeLigacoes = quantidadeLigacoes;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.rota = rota;
		this.quadra = quadra;
		this.imovelPerfil = imovelPerfil;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.categoria = categoria;
		this.esferaPoder = esferaPoder;
		this.consumoAnormalidade = consumoAnormalidade;
		this.ligacaoTipo = ligacaoTipo;
	}
	
	public ResumoAnormalidadeConsumo(Integer anoMesReferencia, Integer codigoSetorComercial, Integer numeroQuadra, Integer quantidadeLigacoes, GerenciaRegional gerenciaRegional, Localidade localidade, SetorComercial setorComercial, Rota rota, Quadra quadra, ImovelPerfil imovelPerfil, LigacaoAguaSituacao ligacaoAguaSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Categoria categoria, EsferaPoder esferaPoder, ConsumoAnormalidade consumoAnormalidade, LigacaoTipo ligacaoTipo) {
		super();
		this.anoMesReferencia = anoMesReferencia;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.quantidadeLigacoes = quantidadeLigacoes;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.rota = rota;
		this.quadra = quadra;
		this.imovelPerfil = imovelPerfil;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.categoria = categoria;
		this.esferaPoder = esferaPoder;
		this.consumoAnormalidade = consumoAnormalidade;
		this.ligacaoTipo = ligacaoTipo;
	}
	
	public ResumoAnormalidadeConsumo(){
		
	}

	/**
	 * @return Retorna o campo anoMesRreferencia.
	 */
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	/**
	 * @param anoMesRreferencia O anoMesRreferencia a ser setado.
	 */
	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	/**
	 * @return Retorna o campo categoria.
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	/**
	 * @return Retorna o campo consumoAnormalidade.
	 */
	public ConsumoAnormalidade getConsumoAnormalidade() {
		return consumoAnormalidade;
	}

	/**
	 * @param consumoAnormalidade O consumoAnormalidade a ser setado.
	 */
	public void setConsumoAnormalidade(ConsumoAnormalidade consumoAnormalidade) {
		this.consumoAnormalidade = consumoAnormalidade;
	}

	/**
	 * @return Retorna o campo esferaPoder.
	 */
	public EsferaPoder getEsferaPoder() {
		return esferaPoder;
	}

	/**
	 * @param esferaPoder O esferaPoder a ser setado.
	 */
	public void setEsferaPoder(EsferaPoder esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo imovelPerfil.
	 */
	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	/**
	 * @param imovelPerfil O imovelPerfil a ser setado.
	 */
	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	/**
	 * @return Retorna o campo ligacaoAguaSituacao.
	 */
	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	/**
	 * @param ligacaoAguaSituacao O ligacaoAguaSituacao a ser setado.
	 */
	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	/**
	 * @return Retorna o campo ligacaoEsgotoSituacao.
	 */
	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	/**
	 * @param ligacaoEsgotoSituacao O ligacaoEsgotoSituacao a ser setado.
	 */
	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	/**
	 * @return Retorna o campo ligacaoTipo.
	 */
	public LigacaoTipo getLigacaoTipo() {
		return ligacaoTipo;
	}

	/**
	 * @param ligacaoTipo O ligacaoTipo a ser setado.
	 */
	public void setLigacaoTipo(LigacaoTipo ligacaoTipo) {
		this.ligacaoTipo = ligacaoTipo;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public Localidade getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo numeroQuadra.
	 */
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	/**
	 * @param numeroQuadra O numeroQuadra a ser setado.
	 */
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	/**
	 * @return Retorna o campo quadra.
	 */
	public Quadra getQuadra() {
		return quadra;
	}

	/**
	 * @param quadra O quadra a ser setado.
	 */
	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	/**
	 * @return Retorna o campo quantidadeLigacoes.
	 */
	public Integer getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}

	/**
	 * @param quantidadeLigacoes O quantidadeLigacoes a ser setado.
	 */
	public void setQuantidadeLigacoes(Integer quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}

	/**
	 * @return Retorna o campo rota.
	 */
	public Rota getRota() {
		return rota;
	}

	/**
	 * @param rota O rota a ser setado.
	 */
	public void setRota(Rota rota) {
		this.rota = rota;
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

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}