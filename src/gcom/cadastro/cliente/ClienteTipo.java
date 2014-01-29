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

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao
public class ClienteTipo extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private Short indicadorPessoaFisicaJuridica;
    private Short indicadorUso;
    private Date ultimaAlteracao;
    private String descricaoComId;

    private EsferaPoder esferaPoder;

    public final static Short INDICADOR_PESSOA_FISICA = new Short("1");
    public final static Short INDICADOR_PESSOA_JURIDICA = new Short("2");

    public ClienteTipo(String descricao, Short indicadorPessoaFisicaJuridica, Short indicadorUso, Date ultimaAlteracao, EsferaPoder esferaPoder) {
        this.descricao = descricao;
        this.indicadorPessoaFisicaJuridica = indicadorPessoaFisicaJuridica;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.esferaPoder = esferaPoder;
    }

    public ClienteTipo() {
    }
    
    public ClienteTipo(Integer id) {
    	this.id = id;
    }

    public ClienteTipo(gcom.cadastro.cliente.EsferaPoder esferaPoder) {
        this.esferaPoder = esferaPoder;
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

    public Short getIndicadorPessoaFisicaJuridica() {
        return this.indicadorPessoaFisicaJuridica;
    }

    public void setIndicadorPessoaFisicaJuridica(
            Short indicadorPessoaFisicaJuridica) {
        this.indicadorPessoaFisicaJuridica = indicadorPessoaFisicaJuridica;
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

    public gcom.cadastro.cliente.EsferaPoder getEsferaPoder() {
        return this.esferaPoder;
    }

    public void setEsferaPoder(gcom.cadastro.cliente.EsferaPoder esferaPoder) {
        this.esferaPoder = esferaPoder;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
        
    public String[] retornaCamposChavePrimaria() {
    	String[] retorno = {"id"};
    	return retorno;
    }
        
    public Filtro retornaFiltro(){
        FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();
        filtroClienteTipo.adicionarParametro(new ParametroSimples(FiltroClienteTipo.ID,this.getId()));
        return filtroClienteTipo;
    }

	public String getDescricaoComId() {
		
		if(this.getId().compareTo(10) == -1){
			descricaoComId = "0" + getId()+ " - " + getDescricao();
		}else{
			descricaoComId = getId()+ " - " + getDescricao();
		}
		
		return descricaoComId;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		
		String descricao = getDescricao();
		
		if (getDescricao() != null){
			if (indicadorPessoaFisicaJuridica.shortValue() == INDICADOR_PESSOA_FISICA.shortValue()){
				descricao += " (PF)";
			} else {
				descricao += " (PJ)";
			}				
		}
			return descricao;
	}
}
