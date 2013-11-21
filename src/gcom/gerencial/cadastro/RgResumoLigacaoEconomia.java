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
package gcom.gerencial.cadastro;

import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.cadastro.cliente.GClienteTipo;
import gcom.gerencial.cadastro.cliente.GEsferaPoder;
import gcom.gerencial.cadastro.geografico.GBairro;
import gcom.gerencial.cadastro.geografico.GMicrorregiao;
import gcom.gerencial.cadastro.geografico.GMunicipio;
import gcom.gerencial.cadastro.geografico.GRegiao;
import gcom.gerencial.cadastro.imovel.GCategoria;
import gcom.gerencial.cadastro.imovel.GImovelPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.cadastro.imovel.GSubcategoria;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RgResumoLigacaoEconomia implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int referencia;

    /** persistent field */
    private short indicadorHidrometro;

    /** persistent field */
    private short volumeFixadoAgua;

    /** persistent field */
    private short indicadorVolumeFixadoEsgoto;

    /** persistent field */
    private short indicadorPoco;

    /** persistent field */
    private int quantidadeLigacoes;

    /** persistent field */
    private int quantidadeEconomias;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private GMunicipio gerMunicipio;

    /** persistent field */
    private GSubcategoria gerSubcategoria;

    /** persistent field */
    private GClienteTipo gerClienteTipo;

    /** persistent field */
    private GLigacaoAguaSituacao gerLigacaoAguaSituacao;

    /** persistent field */
    private GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao;

    /** persistent field */
    private GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;

    /** persistent field */
    private GRegiao gerRegiao;

    /** persistent field */
    private GBairro gerBairro;

    /** persistent field */
    private GLigacaoAguaPerfil gerLigacaoAguaPerfil;

    /** persistent field */
    private GEsferaPoder gerEsferaPoder;

    /** persistent field */
    private GMicrorregiao gerMicrorregiao;

    /** persistent field */
    private GCategoria gerCategoria;

    /** persistent field */
    private GImovelPerfil gerImovelPerfil;

    /** full constructor */
    public RgResumoLigacaoEconomia(int referencia, short indicadorHidrometro, short volumeFixadoAgua, short indicadorVolumeFixadoEsgoto, short indicadorPoco, int quantidadeLigacoes, int quantidadeEconomias, Date ultimaAlteracao, GMunicipio gMunicipio, GSubcategoria gSubcategoria, GClienteTipo gClienteTipo, GLigacaoAguaSituacao gLigacaoAguaSituacao, GLigacaoEsgotoSituacao gLigacaoEsgotoSituacao, GLigacaoEsgotoPerfil gLigacaoEsgotoPerfil, GRegiao gRegiao, GBairro gBairro, GLigacaoAguaPerfil gLigacaoAguaPerfil, GEsferaPoder gEsferaPoder, GMicrorregiao gMicrorregiao, GCategoria gCategoria, GImovelPerfil gImovelPerfil) {
        this.referencia = referencia;
        this.indicadorHidrometro = indicadorHidrometro;
        this.volumeFixadoAgua = volumeFixadoAgua;
        this.indicadorVolumeFixadoEsgoto = indicadorVolumeFixadoEsgoto;
        this.indicadorPoco = indicadorPoco;
        this.quantidadeLigacoes = quantidadeLigacoes;
        this.quantidadeEconomias = quantidadeEconomias;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerMunicipio = gMunicipio;
        this.gerSubcategoria = gSubcategoria;
        this.gerClienteTipo = gClienteTipo;
        this.gerLigacaoAguaSituacao = gLigacaoAguaSituacao;
        this.gerLigacaoEsgotoSituacao = gLigacaoEsgotoSituacao;
        this.gerLigacaoEsgotoPerfil = gLigacaoEsgotoPerfil;
        this.gerRegiao = gRegiao;
        this.gerBairro = gBairro;
        this.gerLigacaoAguaPerfil = gLigacaoAguaPerfil;
        this.gerEsferaPoder = gEsferaPoder;
        this.gerMicrorregiao = gMicrorregiao;
        this.gerCategoria = gCategoria;
        this.gerImovelPerfil = gImovelPerfil;
    }

    /** default constructor */
    public RgResumoLigacaoEconomia() {
    }

    
  
    public GBairro getGerBairro() {
		return gerBairro;
	}

	public void setGerBairro(GBairro gerBairro) {
		this.gerBairro = gerBairro;
	}

	public GCategoria getGerCategoria() {
		return gerCategoria;
	}

	public void setGerCategoria(GCategoria gerCategoria) {
		this.gerCategoria = gerCategoria;
	}

	public GClienteTipo getGerClienteTipo() {
		return gerClienteTipo;
	}

	public void setGerClienteTipo(GClienteTipo gerClienteTipo) {
		this.gerClienteTipo = gerClienteTipo;
	}

	public GEsferaPoder getGerEsferaPoder() {
		return gerEsferaPoder;
	}

	public void setGerEsferaPoder(GEsferaPoder gerEsferaPoder) {
		this.gerEsferaPoder = gerEsferaPoder;
	}

	public GImovelPerfil getGerImovelPerfil() {
		return gerImovelPerfil;
	}

	public void setGerImovelPerfil(GImovelPerfil gerImovelPerfil) {
		this.gerImovelPerfil = gerImovelPerfil;
	}

	public GLigacaoAguaPerfil getGerLigacaoAguaPerfil() {
		return gerLigacaoAguaPerfil;
	}

	public void setGerLigacaoAguaPerfil(GLigacaoAguaPerfil gerLigacaoAguaPerfil) {
		this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
	}

	public GLigacaoAguaSituacao getGerLigacaoAguaSituacao() {
		return gerLigacaoAguaSituacao;
	}

	public void setGerLigacaoAguaSituacao(
			GLigacaoAguaSituacao gerLigacaoAguaSituacao) {
		this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
	}

	public GLigacaoEsgotoPerfil getGerLigacaoEsgotoPerfil() {
		return gerLigacaoEsgotoPerfil;
	}

	public void setGerLigacaoEsgotoPerfil(
			GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
		this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
	}

	public GLigacaoEsgotoSituacao getGerLigacaoEsgotoSituacao() {
		return gerLigacaoEsgotoSituacao;
	}

	public void setGerLigacaoEsgotoSituacao(
			GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao) {
		this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
	}

	public GMicrorregiao getGerMicrorregiao() {
		return gerMicrorregiao;
	}

	public void setGerMicrorregiao(GMicrorregiao gerMicrorregiao) {
		this.gerMicrorregiao = gerMicrorregiao;
	}

	public GMunicipio getGerMunicipio() {
		return gerMunicipio;
	}

	public void setGerMunicipio(GMunicipio gerMunicipio) {
		this.gerMunicipio = gerMunicipio;
	}

	public GRegiao getGerRegiao() {
		return gerRegiao;
	}

	public void setGerRegiao(GRegiao gerRegiao) {
		this.gerRegiao = gerRegiao;
	}

	public GSubcategoria getGerSubcategoria() {
		return gerSubcategoria;
	}

	public void setGerSubcategoria(GSubcategoria gerSubcategoria) {
		this.gerSubcategoria = gerSubcategoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public short getIndicadorHidrometro() {
		return indicadorHidrometro;
	}

	public void setIndicadorHidrometro(short indicadorHidrometro) {
		this.indicadorHidrometro = indicadorHidrometro;
	}

	public short getIndicadorPoco() {
		return indicadorPoco;
	}

	public void setIndicadorPoco(short indicadorPoco) {
		this.indicadorPoco = indicadorPoco;
	}

	public short getIndicadorVolumeFixadoEsgoto() {
		return indicadorVolumeFixadoEsgoto;
	}

	public void setIndicadorVolumeFixadoEsgoto(short indicadorVolumeFixadoEsgoto) {
		this.indicadorVolumeFixadoEsgoto = indicadorVolumeFixadoEsgoto;
	}

	public int getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(int quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public int getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}

	public void setQuantidadeLigacoes(int quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}

	public int getReferencia() {
		return referencia;
	}

	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public short getVolumeFixadoAgua() {
		return volumeFixadoAgua;
	}

	public void setVolumeFixadoAgua(short volumeFixadoAgua) {
		this.volumeFixadoAgua = volumeFixadoAgua;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
