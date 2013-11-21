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
package gcom.cobranca;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;

public class CobrancaSituacaoComando implements Serializable {
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
    
    private Integer quantidadeImoveis;
    
    private Short indicadorComando;
    
    private Integer anoMesInicialSituacaoCobranca;
    
    private Integer anoMesFinalSituacaoCobranca;
    
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
    
  
    public static Integer COMANDO_INSERIR = 1;
    public static Integer COMANDO_RETIRAR = 2;
    
    /** persistent field */
    private gcom.cobranca.CobrancaSituacaoTipo cobrancaSituacaoTipo;
    
    private gcom.cobranca.CobrancaSituacaoMotivo cobrancaSituacaoMotivo;

	public Integer getAnoMesFinalSituacaoCobranca() {
		return anoMesFinalSituacaoCobranca;
	}

	public void setAnoMesFinalSituacaoCobranca(Integer anoMesFinalSituacaoCobranca) {
		this.anoMesFinalSituacaoCobranca = anoMesFinalSituacaoCobranca;
	}

	public Integer getAnoMesInicialSituacaoCobranca() {
		return anoMesInicialSituacaoCobranca;
	}

	public void setAnoMesInicialSituacaoCobranca(
			Integer anoMesInicialSituacaoCobranca) {
		this.anoMesInicialSituacaoCobranca = anoMesInicialSituacaoCobranca;
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

	public gcom.cobranca.CobrancaSituacaoMotivo getCobrancaSituacaoMotivo() {
		return cobrancaSituacaoMotivo;
	}

	public void setCobrancaSituacaoMotivo(
			gcom.cobranca.CobrancaSituacaoMotivo cobrancaSituacaoMotivo) {
		this.cobrancaSituacaoMotivo = cobrancaSituacaoMotivo;
	}

	public gcom.cobranca.CobrancaSituacaoTipo getCobrancaSituacaoTipo() {
		return cobrancaSituacaoTipo;
	}

	public void setCobrancaSituacaoTipo(
			gcom.cobranca.CobrancaSituacaoTipo cobrancaSituacaoTipo) {
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
	}
    

}
