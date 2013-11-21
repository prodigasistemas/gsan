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
package gcom.faturamento.bean;

import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.faturamento.ConsumoFaixaLigacao;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.util.ConstantesSistema;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;


/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * de emitir histograma de esgoto
 *
 * @author Rafael Pinto
 * @date 05/11/2007
 */
public class FiltrarEmitirHistogramaEsgotoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int opcaoTotalizacao;
	private Integer mesAnoFaturamento;
	
	private GerenciaRegional gerenciaRegional = null;
	private UnidadeNegocio unidadeNegocio = null;
	private Localidade eloPolo = null;
	private Localidade localidade = null;
	private SetorComercial setorComercial = null;
	private Quadra quadra = null;
	private Integer indicadorTarifaCategoria = null;

	private CategoriaTipo tipoCategoria = null;
	private Categoria categoria = null;
	private Subcategoria subcategoria = null;
	private ConsumoTarifa tarifa = null;
	private ImovelPerfil perfilImovel = null;
	private EsferaPoder esferaPoder = null;
	private LigacaoEsgotoSituacao situacaoLigacaoEsgoto = null;
	private BigDecimal percentualLigacaoEsgoto = null;

	private Short consumo = null;
	private Short medicao = null;
	private Short poco = null;
	private Short volumoFixoEsgoto = null;
	
	private Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido = null;
	private Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido = null;
	
	private String tipoGroupBy = null;
	
	private ConsumoFaixaLigacao consumoFaixaLigacao = null;
	
	private ConsumoFaixaLigacao consumoFaixaLigacaoIntervaloMedido = null;
	private ConsumoFaixaLigacao consumoFaixaLigacaoIntervaloNaoMedido = null;
	
	private Collection<Integer> colecaoCategoria = null;
	private Collection<Integer> colecaoSubcategoria = null;
	private Collection<Integer> colecaoTarifa = null;
	private Collection<Integer> colecaoPerfilImovel = null;
	private Collection<Integer> colecaoEsferaPoder = null;
	private Collection<Integer> colecaoSituacaoLigacaoEsgoto = null;
	private Collection<BigDecimal> colecaoPercentualLigacaoEsgoto = null;
	
	private Integer codigoSetorComercial = null;
	private Integer numeroQuadra = null;
	
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public void setConsumoFaixaLigacaoIntervaloMedido(
			ConsumoFaixaLigacao consumoFaixaLigacaoIntervaloMedido) {
		this.consumoFaixaLigacaoIntervaloMedido = consumoFaixaLigacaoIntervaloMedido;
	}

	public void setConsumoFaixaLigacaoIntervaloNaoMedido(
			ConsumoFaixaLigacao consumoFaixaLigacaoIntervaloNaoMedido) {
		this.consumoFaixaLigacaoIntervaloNaoMedido = consumoFaixaLigacaoIntervaloNaoMedido;
	}

	public ConsumoFaixaLigacao getConsumoFaixaLigacaoIntervaloMedido() {
		return consumoFaixaLigacaoIntervaloMedido;
	}

	public ConsumoFaixaLigacao getConsumoFaixaLigacaoIntervaloNaoMedido() {
		return consumoFaixaLigacaoIntervaloNaoMedido;
	}


	public Collection<Integer> getColecaoCategoria() {
		return colecaoCategoria;
	}

	public void setColecaoCategoria(Collection<Integer> colecaoCategoria) {
		this.colecaoCategoria = colecaoCategoria;
	}

	public Collection<Integer> getColecaoEsferaPoder() {
		return colecaoEsferaPoder;
	}

	public void setColecaoEsferaPoder(Collection<Integer> colecaoEsferaPoder) {
		this.colecaoEsferaPoder = colecaoEsferaPoder;
	}

	public Collection<Integer> getColecaoPerfilImovel() {
		return colecaoPerfilImovel;
	}

	public void setColecaoPerfilImovel(Collection<Integer> colecaoPerfilImovel) {
		this.colecaoPerfilImovel = colecaoPerfilImovel;
	}

	public Collection<Integer> getColecaoSituacaoLigacaoEsgoto() {
		return colecaoSituacaoLigacaoEsgoto;
	}

	public void setColecaoSituacaoLigacaoEsgoto(
			Collection<Integer> colecaoSituacaoLigacaoEsgoto) {
		this.colecaoSituacaoLigacaoEsgoto = colecaoSituacaoLigacaoEsgoto;
	}

	public Collection<Integer> getColecaoTarifa() {
		return colecaoTarifa;
	}

	public void setColecaoTarifa(Collection<Integer> colecaoTarifa) {
		this.colecaoTarifa = colecaoTarifa;
	}

	public ConsumoFaixaLigacao getConsumoFaixaLigacao() {
		return consumoFaixaLigacao;
	}

	public void setConsumoFaixaLigacao(ConsumoFaixaLigacao consumoFaixaLigacao) {
		this.consumoFaixaLigacao = consumoFaixaLigacao;
	}

	public FiltrarEmitirHistogramaEsgotoHelper() { }
	
	public FiltrarEmitirHistogramaEsgotoHelper(FiltrarEmitirHistogramaEsgotoHelper filtro) { 
		
		this.opcaoTotalizacao = filtro.getOpcaoTotalizacao();
		this.mesAnoFaturamento = filtro.getMesAnoFaturamento();
		
		this.gerenciaRegional = filtro.getGerenciaRegional();
		this.unidadeNegocio = filtro.getUnidadeNegocio();
		this.eloPolo = filtro.getEloPolo();
		this.localidade = filtro.getLocalidade();
		this.setorComercial = filtro.getSetorComercial();
		this.quadra = filtro.getQuadra();

		this.tipoCategoria = filtro.getTipoCategoria();
		this.categoria = filtro.getCategoria();
		this.tarifa = filtro.getTarifa();
		this.perfilImovel = filtro.getPerfilImovel();
		this.esferaPoder = filtro.getEsferaPoder();
		this.situacaoLigacaoEsgoto = filtro.getSituacaoLigacaoEsgoto();

		this.consumo = filtro.getConsumo();
		this.medicao = filtro.getMedicao();
		this.poco = filtro.getPoco();
		this.volumoFixoEsgoto = filtro.getVolumoFixoEsgoto();
		
		this.colecaoConsumoFaixaLigacaoMedido = 
			filtro.getColecaoConsumoFaixaLigacaoMedido();
		this.colecaoConsumoFaixaLigacaoNaoMedido = 
			filtro.getColecaoConsumoFaixaLigacaoNaoMedido();
		
		this.tipoGroupBy = filtro.getTipoGroupBy();
		
		this.consumoFaixaLigacao = filtro.getConsumoFaixaLigacao();
		
		this.consumoFaixaLigacaoIntervaloMedido = filtro.getConsumoFaixaLigacaoIntervaloMedido();
		this.consumoFaixaLigacaoIntervaloNaoMedido = filtro.getConsumoFaixaLigacaoIntervaloNaoMedido();
		
		this.colecaoCategoria = filtro.getColecaoCategoria();
		this.colecaoTarifa = filtro.getColecaoTarifa();
		this.colecaoPerfilImovel = filtro.getColecaoPerfilImovel();
		this.colecaoEsferaPoder = filtro.getColecaoEsferaPoder();
		this.colecaoSituacaoLigacaoEsgoto = filtro.getColecaoSituacaoLigacaoEsgoto();
		this.indicadorTarifaCategoria = filtro.getIndicadorTarifaCategoria();
		
		
		
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Collection<ConsumoFaixaLigacao> getColecaoConsumoFaixaLigacaoMedido() {
		return colecaoConsumoFaixaLigacaoMedido;
	}

	public void setColecaoConsumoFaixaLigacaoMedido(
			Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoMedido) {
		this.colecaoConsumoFaixaLigacaoMedido = colecaoConsumoFaixaLigacaoMedido;
	}

	public Collection<ConsumoFaixaLigacao> getColecaoConsumoFaixaLigacaoNaoMedido() {
		return colecaoConsumoFaixaLigacaoNaoMedido;
	}

	public void setColecaoConsumoFaixaLigacaoNaoMedido(
			Collection<ConsumoFaixaLigacao> colecaoConsumoFaixaLigacaoNaoMedido) {
		this.colecaoConsumoFaixaLigacaoNaoMedido = colecaoConsumoFaixaLigacaoNaoMedido;
	}

	public Short getConsumo() {
		return consumo;
	}

	public void setConsumo(Short consumo) {
		this.consumo = consumo;
	}

	public Localidade getEloPolo() {
		return eloPolo;
	}

	public void setEloPolo(Localidade eloPolo) {
		this.eloPolo = eloPolo;
	}

	public EsferaPoder getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(EsferaPoder esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Short getMedicao() {
		return medicao;
	}

	public void setMedicao(Short medicao) {
		this.medicao = medicao;
	}

	public Integer getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}

	public void setMesAnoFaturamento(Integer mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}

	public int getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(int opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public ImovelPerfil getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(ImovelPerfil perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public Short getPoco() {
		return poco;
	}

	public void setPoco(Short poco) {
		this.poco = poco;
	}

	public Quadra getQuadra() {
		return quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public LigacaoEsgotoSituacao getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	public void setSituacaoLigacaoEsgoto(LigacaoEsgotoSituacao situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public ConsumoTarifa getTarifa() {
		return tarifa;
	}

	public void setTarifa(ConsumoTarifa tarifa) {
		this.tarifa = tarifa;
	}

	public CategoriaTipo getTipoCategoria() {
		return tipoCategoria;
	}

	public void setTipoCategoria(CategoriaTipo tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Short getVolumoFixoEsgoto() {
		return volumoFixoEsgoto;
	}

	public void setVolumoFixoEsgoto(Short volumoFixoEsgoto) {
		this.volumoFixoEsgoto = volumoFixoEsgoto;
	}

	public String getTipoGroupBy() {
		return tipoGroupBy;
	}

	public void setTipoGroupBy(String tipoGroupBy) {
		this.tipoGroupBy = tipoGroupBy;
	}
	
	public ConsumoFaixaLigacao retornaOLimiteConsultaTotal(short indicadorHidrometro){
		
		ConsumoFaixaLigacao consumoFaixa = null;
		if(indicadorHidrometro == ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()){
			
			if(this.colecaoConsumoFaixaLigacaoMedido != null && 
					!this.colecaoConsumoFaixaLigacaoMedido.isEmpty()){
						
					int size = this.colecaoConsumoFaixaLigacaoMedido.size();
					int contador = 0;
					
					int faixaInicio = 0;
					int faixaFim = 0;
					
					Iterator itera = this.colecaoConsumoFaixaLigacaoMedido.iterator();
					while (itera.hasNext()) {
						
						contador++;
						
						ConsumoFaixaLigacao element = (ConsumoFaixaLigacao) itera.next();
						
						if(contador == 1){
							faixaInicio = element.getNumeroFaixaInicio();
						}
						if(contador == size){
							faixaFim = element.getNumeroFaixaFim();
						}
					}
					
					consumoFaixa = new ConsumoFaixaLigacao();
					consumoFaixa.setNumeroFaixaInicio(faixaInicio);
					consumoFaixa.setNumeroFaixaFim(faixaFim);
				}
			
		}else{
			if(this.colecaoConsumoFaixaLigacaoNaoMedido != null && 
				!this.colecaoConsumoFaixaLigacaoNaoMedido.isEmpty()){
						
				int size = this.colecaoConsumoFaixaLigacaoNaoMedido.size();
				int contador = 0;
					
				int faixaInicio = 0;
				int faixaFim = 0;
					
				Iterator itera = this.colecaoConsumoFaixaLigacaoNaoMedido.iterator();
				while (itera.hasNext()) {
						
					contador++;
					
					ConsumoFaixaLigacao element = (ConsumoFaixaLigacao) itera.next();
						
					if(contador == 1){
						faixaInicio = element.getNumeroFaixaInicio();
					}
					if(contador == size){
						faixaFim = element.getNumeroFaixaFim();
					}
				}
					
				consumoFaixa = new ConsumoFaixaLigacao();
				consumoFaixa.setNumeroFaixaInicio(faixaInicio);
				consumoFaixa.setNumeroFaixaFim(faixaFim);
			}
		
		}

		return consumoFaixa;		
	}

	public Collection<BigDecimal> getColecaoPercentualLigacaoEsgoto() {
		return colecaoPercentualLigacaoEsgoto;
	}

	public void setColecaoPercentualLigacaoEsgoto(
			Collection<BigDecimal> colecaoPercentualLigacaoEsgoto) {
		this.colecaoPercentualLigacaoEsgoto = colecaoPercentualLigacaoEsgoto;
	}

	public BigDecimal getPercentualLigacaoEsgoto() {
		return percentualLigacaoEsgoto;
	}

	public void setPercentualLigacaoEsgoto(BigDecimal percentualLigacaoEsgoto) {
		this.percentualLigacaoEsgoto = percentualLigacaoEsgoto;
	}

	public Integer getIndicadorTarifaCategoria() {
		return indicadorTarifaCategoria;
	}

	public void setIndicadorTarifaCategoria(Integer indicadorTarifaCategoria) {
		this.indicadorTarifaCategoria = indicadorTarifaCategoria;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public Collection<Integer> getColecaoSubcategoria() {
		return colecaoSubcategoria;
	}

	public void setColecaoSubcategoria(Collection<Integer> colecaoSubcategoria) {
		this.colecaoSubcategoria = colecaoSubcategoria;
	}

}