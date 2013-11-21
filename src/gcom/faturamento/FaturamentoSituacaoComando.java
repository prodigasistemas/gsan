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
* Rômulo Aurélio de Melo Souza Filho
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
package gcom.faturamento;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@ControleAlteracao()
public class FaturamentoSituacaoComando extends ObjetoTransacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	/** identifier field */
    private Integer id;
    
    private Integer codigoSetorComercialInicial;
    
    private Integer codigoSetorComercialFinal;
    
    private Integer numeroQuadraInicial;
    
    private Integer numeroQuadraFinal;
    
    private Integer numeroLoteInicial;
    
    private Integer numeroLoteFinal;
    
    private Integer numeroSubLoteInicial;
    
    private Integer numeroSubLoteFinal;
    
    private Integer codigoRotaInicial;
    
    private Integer codigoRotaFinal;
    private Integer sequencialRotaInicial;
    
    private Integer sequencialRotaFinal;
    
    private Short indicadorConsumo;
    
    private Integer quantidadeImoveis;
    
    private Short indicadorComando;
    
    private Integer anoMesInicialSituacaoFaturamento;
    
    @ControleAlteracao(funcionalidade={Operacao.OPERACAO_ATUALIZAR_SITUACAO_ESPECIAL_FATURAMENTO})
    private Integer anoMesFinalSituacaoFaturamento;
    
    @ControleAlteracao(funcionalidade={Operacao.OPERACAO_ATUALIZAR_SITUACAO_ESPECIAL_FATURAMENTO})
    private String observacao;
    
    private Date ultimaAlteracao;
    
    private Imovel imovel;
    
    private Localidade localidadeInicial;
    
    private Localidade localidadeFinal;
    
    private Categoria categoria1;
    
    private Categoria categoria2;
    
    private Categoria categoria3;
    
    private Categoria categoria4;
    
    private Usuario usuario;
    
    
    /**
     * variavel usada como auxiliar para geração de consultas
     * ja que o indicadorConsumo pode ser filtrado como um
     * operador in no banco de dados
     * 
     */
    private Collection<Short> indicadoresConsumo;
    
    public static Integer COMANDO_INSERIR = 1;
    public static Integer COMANDO_RETIRAR = 2;
    
    /** persistent field */
    private gcom.faturamento.FaturamentoSituacaoTipo faturamentoSituacaoTipo;
    
    private gcom.faturamento.FaturamentoSituacaoMotivo faturamentoSituacaoMotivo;

	public Integer getAnoMesFinalSituacaoFaturamento() {
		return anoMesFinalSituacaoFaturamento;
	}

	public void setAnoMesFinalSituacaoFaturamento(
			Integer anoMesFinalSituacaoFaturamento) {
		this.anoMesFinalSituacaoFaturamento = anoMesFinalSituacaoFaturamento;
	}

	public Integer getAnoMesInicialSituacaoFaturamento() {
		return anoMesInicialSituacaoFaturamento;
	}

	public void setAnoMesInicialSituacaoFaturamento(
			Integer anoMesInicialSituacaoFaturamento) {
		this.anoMesInicialSituacaoFaturamento = anoMesInicialSituacaoFaturamento;
	}

	public Categoria getCategoria1() {
		return categoria1;
	}

	public void setCategoria1(Categoria categoria1) {
		this.categoria1 = categoria1;
	}

	public Categoria getCategoria2() {
		return categoria2;
	}

	public void setCategoria2(Categoria categoria2) {
		this.categoria2 = categoria2;
	}

	public Categoria getCategoria3() {
		return categoria3;
	}

	public void setCategoria3(Categoria categoria3) {
		this.categoria3 = categoria3;
	}

	public Categoria getCategoria4() {
		return categoria4;
	}

	public void setCategoria4(Categoria categoria4) {
		this.categoria4 = categoria4;
	}

	public Integer getCodigoRotaFinal() {
		return codigoRotaFinal;
	}

	public void setCodigoRotaFinal(Integer codigoRotaFinal) {
		this.codigoRotaFinal = codigoRotaFinal;
	}

	public Integer getCodigoRotaInicial() {
		return codigoRotaInicial;
	}

	public void setCodigoRotaInicial(Integer codigoRotaInicial) {
		this.codigoRotaInicial = codigoRotaInicial;
	}

	public Integer getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(Integer codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public Integer getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(Integer codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public gcom.faturamento.FaturamentoSituacaoMotivo getFaturamentoSituacaoMotivo() {
		return faturamentoSituacaoMotivo;
	}

	public void setFaturamentoSituacaoMotivo(
			gcom.faturamento.FaturamentoSituacaoMotivo faturamentoSituacaoMotivo) {
		this.faturamentoSituacaoMotivo = faturamentoSituacaoMotivo;
	}

	public gcom.faturamento.FaturamentoSituacaoTipo getFaturamentoSituacaoTipo() {
		return faturamentoSituacaoTipo;
	}

	public void setFaturamentoSituacaoTipo(
			gcom.faturamento.FaturamentoSituacaoTipo faturamentoSituacaoTipo) {
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
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

	public Short getIndicadorComando() {
		return indicadorComando;
	}

	public void setIndicadorComando(Short indicadorComando) {
		this.indicadorComando = indicadorComando;
	}

	public Short getIndicadorConsumo() {
		return indicadorConsumo;
	}

	public void setIndicadorConsumo(Short indicadorConsumo) {
		this.indicadorConsumo = indicadorConsumo;
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

	public Integer getNumeroLoteFinal() {
		return numeroLoteFinal;
	}

	public void setNumeroLoteFinal(Integer numeroLoteFinal) {
		this.numeroLoteFinal = numeroLoteFinal;
	}

	public Integer getNumeroLoteInicial() {
		return numeroLoteInicial;
	}

	public void setNumeroLoteInicial(Integer numeroLoteInicial) {
		this.numeroLoteInicial = numeroLoteInicial;
	}

	public Integer getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}

	public void setNumeroQuadraFinal(Integer numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}

	public Integer getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}

	public void setNumeroQuadraInicial(Integer numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}

	public Integer getNumeroSubLoteFinal() {
		return numeroSubLoteFinal;
	}

	public void setNumeroSubLoteFinal(Integer numeroSubLoteFinal) {
		this.numeroSubLoteFinal = numeroSubLoteFinal;
	}

	public Integer getNumeroSubLoteInicial() {
		return numeroSubLoteInicial;
	}

	public void setNumeroSubLoteInicial(Integer numeroSubLoteInicial) {
		this.numeroSubLoteInicial = numeroSubLoteInicial;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getQuantidadeImoveis() {
		return quantidadeImoveis;
	}

	public void setQuantidadeImoveis(Integer quantidadeImoveis) {
		this.quantidadeImoveis = quantidadeImoveis;
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

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Collection<Short> getIndicadoresConsumo() {
		return indicadoresConsumo;
	}

	public void setIndicadoresConsumo(Collection<Short> indicadoresConsumo) {
		this.indicadoresConsumo = indicadoresConsumo;
	}
	
	public String getAnoMesFinalSituacaoFaturamentoComoMesAno() {
		return Util.formatarAnoMesParaMesAno(this.anoMesFinalSituacaoFaturamento);
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroFaturamentoSituacaoComando filtro = new FiltroFaturamentoSituacaoComando();
		
		filtro.adicionarParametro(new ParametroSimples(FiltroFaturamentoSituacaoComando.ID,this.getId()));
		
//		filtro.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoMotivo");
//		filtro.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoTipo");
		
		return filtro;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"anoMesFinalSituacaoFaturamentoComoMesAno","observacao"};
		return labels;	
	}

	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Mes/Ano Referencia do Faturamento Final","Observacao"};
		return labels;		
	}
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		return filtro;
	}	

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getId().toString();
	}

}
