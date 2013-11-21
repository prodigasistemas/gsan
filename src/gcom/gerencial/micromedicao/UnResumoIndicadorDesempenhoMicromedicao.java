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
* Ivan Sérgio Virginio da Silva Júnior
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
package gcom.gerencial.micromedicao;

import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.cadastro.cliente.GClienteTipo;
import gcom.gerencial.cadastro.cliente.GEsferaPoder;
import gcom.gerencial.cadastro.imovel.GCategoria;
import gcom.gerencial.cadastro.imovel.GImovelPerfil;
import gcom.gerencial.cadastro.imovel.GSubcategoria;
import gcom.gerencial.cadastro.localidade.GGerenciaRegional;
import gcom.gerencial.cadastro.localidade.GLocalidade;
import gcom.gerencial.cadastro.localidade.GQuadra;
import gcom.gerencial.cadastro.localidade.GSetorComercial;
import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoIndicadorDesempenhoMicromedicao implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** persistent field */
    private int anoReferencia;

    /** persistent field */
    private String mesReferencia;

    /** persistent field */
    private int codigoSetorComercial;

    /** persistent field */
    private int numeroQuadra;

    /** persistent field */
    private int quantidadeLigacoesAtivas;

    /** persistent field */
    private int quantidadeLigacoesComHidrometro;

    /** persistent field */
    private int quantidadeLigacoesComMedicaoReal;

    /** persistent field */
    private int quantidadeLigacoesComHidrometroEMedicaoEstimada;

    /** persistent field */
    private int quantidadeEconomiasAtivas;

    /** persistent field */
    private int quantidadeEconomiasComHidrometro;

    /** persistent field */
    private int quantidadeEconomiasComMedicaoReal;

    /** persistent field */
    private int quantidadeEconomiasComHidrometroEMedicaoEstimada;

    /** persistent field */
    private int consumoAguaAtivas;

    /** persistent field */
    private int consumoAguaComHidrometro;

    /** persistent field */
    private int consumoAguaComMedicaoReal;

    /** persistent field */
    private int consumoAguaComHidrometroEMedicaoEstimada;

    /** persistent field */
    private int volumeFaturadoAgua;

    /** persistent field */
    private int volumeFaturadoAguaMedido;

    /** persistent field */
    private int volumeFaturadoAguaNaoMedido;

    /** persistent field */
    private int quantidadeLigacoes;

    /** persistent field */
    private int quantidadeEconomias;

    /** persistent field */
    private int volumeEsgoto;

    /** persistent field */
    private int volumeFaturadoEsgoto;

    /** persistent field */
    private int volumeFaturadoEsgotoMedido;

    /** persistent field */
    private int volumeFaturadoEsgotoNaoMedido;

    /** persistent field */
    private int quantidadeVisitasRealizadas;

    /** persistent field */
    private int quantidadeLeiturasEfetuadas;

    /** persistent field */
    private int quantidadeLeiturasComAnormalidadeHidrometro;

    /** persistent field */
    private int quantidadeHidrometroInstaladoRamal;

    /** persistent field */
    private int quantidadeHidrometroSubstituidoRamal;

    /** persistent field */
    private int quantidadeHidrometroRemanejadoRamal;

    /** persistent field */
    private int quantidadeHidrometroRetiradoRamal;

    /** persistent field */
    private int quantidadeHidrometrosAtualInstaladosRamal;

    /** persistent field */
    private int quantidadeHidrometroInstaladoPoco;

    /** persistent field */
    private int quantidadeHidrometroSubstituidoPoco;

    /** persistent field */
    private int quantidadeHidrometroRemanejadoPoco;

    /** persistent field */
    private int quantidadeHidrometroRetiradoPoco;

    /** persistent field */
    private int quantidadeHidrometrosAtualInstaladosPoco;

    /** persistent field */
    private int quantidadeHidrometroDadosAtualizados;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private GUnidadeNegocio gerUnidadeNegocio;

    /** nullable persistent field */
    private GSetorComercial gerSetorComercial;

    /** persistent field */
    private GSubcategoria gerSubcategoria;

    /** nullable persistent field */
    private GLigacaoAguaSituacao gerLigacaoAguaSituacao;

    /** persistent field */
    private GLigacaoAguaPerfil gerLigacaoAguaPerfil;

    /** persistent field */
    private GCategoria gerCategoria;

    /** persistent field */
    private GGerenciaRegional gerGerenciaRegional;

    /** nullable persistent field */
    private gcom.gerencial.micromedicao.GRota gerRota;

    /** persistent field */
    private GLocalidade gerLocalidadeElo;

    /** persistent field */
    private GLocalidade gerLocalidade;

    /** persistent field */
    private GClienteTipo gerClienteTipo;

    /** nullable persistent field */
    private GImovelPerfil gerImovelPerfil;

    /** persistent field */
    private GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;

    /** nullable persistent field */
    private GEsferaPoder gerEsferaPoder;

    /** nullable persistent field */
    private GQuadra gerQuadra;

    /** nullable persistent field */
    private GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao;

    /** full constructor */
    public UnResumoIndicadorDesempenhoMicromedicao(int anoMesReferencia, int anoReferencia, String mesReferencia, int codigoSetorComercial, int numeroQuadra, int quantidadeLigacoesAtivas, int quantidadeLigacoesComHidrometro, int quantidadeLigacoesComMedicaoReal, int quantidadeLigacoesComHidrometroEMedicaoEstimada, int quantidadeEconomiasAtivas, int quantidadeEconomiasComHidrometro, int quantidadeEconomiasComMedicaoReal, int quantidadeEconomiasComHidrometroEMedicaoEstimada, int consumoAguaAtivas, int consumoAguaComHidrometro, int consumoAguaComMedicaoReal, int consumoAguaComHidrometroEMedicaoEstimada, int volumeFaturadoAgua, int volumeFaturadoAguaMedido, int volumeFaturadoAguaNaoMedido, int quantidadeLigacoes, int quantidadeEconomias, int volumeEsgoto, int volumeFaturadoEsgoto, int volumeFaturadoEsgotoMedido, int volumeFaturadoEsgotoNaoMedido, int quantidadeVisitasRealizadas, int quantidadeLeiturasEfetuadas, int quantidadeLeiturasComAnormalidadeHidrometro, int quantidadeHidrometroInstaladoRamal, int quantidadeHidrometroSubstituidoRamal, int quantidadeHidrometroRemanejadoRamal, int quantidadeHidrometroRetiradoRamal, int quantidadeHidrometrosAtualInstaladosRamal, int quantidadeHidrometroInstaladoPoco, int quantidadeHidrometroSubstituidoPoco, int quantidadeHidrometroRemanejadoPoco, int quantidadeHidrometroRetiradoPoco, int quantidadeHidrometrosAtualInstaladosPoco, int quantidadeHidrometroDadosAtualizados, Date ultimaAlteracao, GUnidadeNegocio gerUnidadeNegocio, GSetorComercial gerSetorComercial, GSubcategoria gerSubcategoria, GLigacaoAguaSituacao gerLigacaoAguaSituacao, GLigacaoAguaPerfil gerLigacaoAguaPerfil, GCategoria gerCategoria, GGerenciaRegional gerGerenciaRegional, gcom.gerencial.micromedicao.GRota gerRota, GLocalidade gerLocalidadeElo, GLocalidade gerLocalidade, GClienteTipo gerClienteTipo, GImovelPerfil gerImovelPerfil, GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil, GEsferaPoder gerEsferaPoder, GQuadra gerQuadra, GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao) {
        this.anoMesReferencia = anoMesReferencia;
        this.anoReferencia = anoReferencia;
        this.mesReferencia = mesReferencia;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.quantidadeLigacoesAtivas = quantidadeLigacoesAtivas;
        this.quantidadeLigacoesComHidrometro = quantidadeLigacoesComHidrometro;
        this.quantidadeLigacoesComMedicaoReal = quantidadeLigacoesComMedicaoReal;
        this.quantidadeLigacoesComHidrometroEMedicaoEstimada = quantidadeLigacoesComHidrometroEMedicaoEstimada;
        this.quantidadeEconomiasAtivas = quantidadeEconomiasAtivas;
        this.quantidadeEconomiasComHidrometro = quantidadeEconomiasComHidrometro;
        this.quantidadeEconomiasComMedicaoReal = quantidadeEconomiasComMedicaoReal;
        this.quantidadeEconomiasComHidrometroEMedicaoEstimada = quantidadeEconomiasComHidrometroEMedicaoEstimada;
        this.consumoAguaAtivas = consumoAguaAtivas;
        this.consumoAguaComHidrometro = consumoAguaComHidrometro;
        this.consumoAguaComMedicaoReal = consumoAguaComMedicaoReal;
        this.consumoAguaComHidrometroEMedicaoEstimada = consumoAguaComHidrometroEMedicaoEstimada;
        this.volumeFaturadoAgua = volumeFaturadoAgua;
        this.volumeFaturadoAguaMedido = volumeFaturadoAguaMedido;
        this.volumeFaturadoAguaNaoMedido = volumeFaturadoAguaNaoMedido;
        this.quantidadeLigacoes = quantidadeLigacoes;
        this.quantidadeEconomias = quantidadeEconomias;
        this.volumeEsgoto = volumeEsgoto;
        this.volumeFaturadoEsgoto = volumeFaturadoEsgoto;
        this.volumeFaturadoEsgotoMedido = volumeFaturadoEsgotoMedido;
        this.volumeFaturadoEsgotoNaoMedido = volumeFaturadoEsgotoNaoMedido;
        this.quantidadeVisitasRealizadas = quantidadeVisitasRealizadas;
        this.quantidadeLeiturasEfetuadas = quantidadeLeiturasEfetuadas;
        this.quantidadeLeiturasComAnormalidadeHidrometro = quantidadeLeiturasComAnormalidadeHidrometro;
        this.quantidadeHidrometroInstaladoRamal = quantidadeHidrometroInstaladoRamal;
        this.quantidadeHidrometroSubstituidoRamal = quantidadeHidrometroSubstituidoRamal;
        this.quantidadeHidrometroRemanejadoRamal = quantidadeHidrometroRemanejadoRamal;
        this.quantidadeHidrometroRetiradoRamal = quantidadeHidrometroRetiradoRamal;
        this.quantidadeHidrometrosAtualInstaladosRamal = quantidadeHidrometrosAtualInstaladosRamal;
        this.quantidadeHidrometroInstaladoPoco = quantidadeHidrometroInstaladoPoco;
        this.quantidadeHidrometroSubstituidoPoco = quantidadeHidrometroSubstituidoPoco;
        this.quantidadeHidrometroRemanejadoPoco = quantidadeHidrometroRemanejadoPoco;
        this.quantidadeHidrometroRetiradoPoco = quantidadeHidrometroRetiradoPoco;
        this.quantidadeHidrometrosAtualInstaladosPoco = quantidadeHidrometrosAtualInstaladosPoco;
        this.quantidadeHidrometroDadosAtualizados = quantidadeHidrometroDadosAtualizados;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerUnidadeNegocio = gerUnidadeNegocio;
        this.gerSetorComercial = gerSetorComercial;
        this.gerSubcategoria = gerSubcategoria;
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
        this.gerCategoria = gerCategoria;
        this.gerGerenciaRegional = gerGerenciaRegional;
        this.gerRota = gerRota;
        this.gerLocalidadeElo = gerLocalidadeElo;
        this.gerLocalidade = gerLocalidade;
        this.gerClienteTipo = gerClienteTipo;
        this.gerImovelPerfil = gerImovelPerfil;
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
        this.gerEsferaPoder = gerEsferaPoder;
        this.gerQuadra = gerQuadra;
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
    }

    /** default constructor */
    public UnResumoIndicadorDesempenhoMicromedicao() {
    }

    /** minimal constructor */
    public UnResumoIndicadorDesempenhoMicromedicao(int anoMesReferencia, int anoReferencia, String mesReferencia, int codigoSetorComercial, int numeroQuadra, int quantidadeLigacoesAtivas, int quantidadeLigacoesComHidrometro, int quantidadeLigacoesComMedicaoReal, int quantidadeLigacoesComHidrometroEMedicaoEstimada, int quantidadeEconomiasAtivas, int quantidadeEconomiasComHidrometro, int quantidadeEconomiasComMedicaoReal, int quantidadeEconomiasComHidrometroEMedicaoEstimada, int consumoAguaAtivas, int consumoAguaComHidrometro, int consumoAguaComMedicaoReal, int consumoAguaComHidrometroEMedicaoEstimada, int volumeFaturadoAgua, int volumeFaturadoAguaMedido, int volumeFaturadoAguaNaoMedido, int quantidadeLigacoes, int quantidadeEconomias, int volumeEsgoto, int volumeFaturadoEsgoto, int volumeFaturadoEsgotoMedido, int volumeFaturadoEsgotoNaoMedido, int quantidadeVisitasRealizadas, int quantidadeLeiturasEfetuadas, int quantidadeLeiturasComAnormalidadeHidrometro, int quantidadeHidrometroInstaladoRamal, int quantidadeHidrometroSubstituidoRamal, int quantidadeHidrometroRemanejadoRamal, int quantidadeHidrometroRetiradoRamal, int quantidadeHidrometrosAtualInstaladosRamal, int quantidadeHidrometroInstaladoPoco, int quantidadeHidrometroSubstituidoPoco, int quantidadeHidrometroRemanejadoPoco, int quantidadeHidrometroRetiradoPoco, int quantidadeHidrometrosAtualInstaladosPoco, int quantidadeHidrometroDadosAtualizados, Date ultimaAlteracao, GUnidadeNegocio gerUnidadeNegocio, GSubcategoria gerSubcategoria, GLigacaoAguaPerfil gerLigacaoAguaPerfil, GCategoria gerCategoria, GGerenciaRegional gerGerenciaRegional, GLocalidade gerLocalidadeElo, GLocalidade gerLocalidade, GClienteTipo gerClienteTipo, GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        this.anoMesReferencia = anoMesReferencia;
        this.anoReferencia = anoReferencia;
        this.mesReferencia = mesReferencia;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.quantidadeLigacoesAtivas = quantidadeLigacoesAtivas;
        this.quantidadeLigacoesComHidrometro = quantidadeLigacoesComHidrometro;
        this.quantidadeLigacoesComMedicaoReal = quantidadeLigacoesComMedicaoReal;
        this.quantidadeLigacoesComHidrometroEMedicaoEstimada = quantidadeLigacoesComHidrometroEMedicaoEstimada;
        this.quantidadeEconomiasAtivas = quantidadeEconomiasAtivas;
        this.quantidadeEconomiasComHidrometro = quantidadeEconomiasComHidrometro;
        this.quantidadeEconomiasComMedicaoReal = quantidadeEconomiasComMedicaoReal;
        this.quantidadeEconomiasComHidrometroEMedicaoEstimada = quantidadeEconomiasComHidrometroEMedicaoEstimada;
        this.consumoAguaAtivas = consumoAguaAtivas;
        this.consumoAguaComHidrometro = consumoAguaComHidrometro;
        this.consumoAguaComMedicaoReal = consumoAguaComMedicaoReal;
        this.consumoAguaComHidrometroEMedicaoEstimada = consumoAguaComHidrometroEMedicaoEstimada;
        this.volumeFaturadoAgua = volumeFaturadoAgua;
        this.volumeFaturadoAguaMedido = volumeFaturadoAguaMedido;
        this.volumeFaturadoAguaNaoMedido = volumeFaturadoAguaNaoMedido;
        this.quantidadeLigacoes = quantidadeLigacoes;
        this.quantidadeEconomias = quantidadeEconomias;
        this.volumeEsgoto = volumeEsgoto;
        this.volumeFaturadoEsgoto = volumeFaturadoEsgoto;
        this.volumeFaturadoEsgotoMedido = volumeFaturadoEsgotoMedido;
        this.volumeFaturadoEsgotoNaoMedido = volumeFaturadoEsgotoNaoMedido;
        this.quantidadeVisitasRealizadas = quantidadeVisitasRealizadas;
        this.quantidadeLeiturasEfetuadas = quantidadeLeiturasEfetuadas;
        this.quantidadeLeiturasComAnormalidadeHidrometro = quantidadeLeiturasComAnormalidadeHidrometro;
        this.quantidadeHidrometroInstaladoRamal = quantidadeHidrometroInstaladoRamal;
        this.quantidadeHidrometroSubstituidoRamal = quantidadeHidrometroSubstituidoRamal;
        this.quantidadeHidrometroRemanejadoRamal = quantidadeHidrometroRemanejadoRamal;
        this.quantidadeHidrometroRetiradoRamal = quantidadeHidrometroRetiradoRamal;
        this.quantidadeHidrometrosAtualInstaladosRamal = quantidadeHidrometrosAtualInstaladosRamal;
        this.quantidadeHidrometroInstaladoPoco = quantidadeHidrometroInstaladoPoco;
        this.quantidadeHidrometroSubstituidoPoco = quantidadeHidrometroSubstituidoPoco;
        this.quantidadeHidrometroRemanejadoPoco = quantidadeHidrometroRemanejadoPoco;
        this.quantidadeHidrometroRetiradoPoco = quantidadeHidrometroRetiradoPoco;
        this.quantidadeHidrometrosAtualInstaladosPoco = quantidadeHidrometrosAtualInstaladosPoco;
        this.quantidadeHidrometroDadosAtualizados = quantidadeHidrometroDadosAtualizados;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerUnidadeNegocio = gerUnidadeNegocio;
        this.gerSubcategoria = gerSubcategoria;
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
        this.gerCategoria = gerCategoria;
        this.gerGerenciaRegional = gerGerenciaRegional;
        this.gerLocalidadeElo = gerLocalidadeElo;
        this.gerLocalidade = gerLocalidade;
        this.gerClienteTipo = gerClienteTipo;
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(int anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public int getAnoReferencia() {
        return this.anoReferencia;
    }

    public void setAnoReferencia(int anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    public String getMesReferencia() {
        return this.mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public int getCodigoSetorComercial() {
        return this.codigoSetorComercial;
    }

    public void setCodigoSetorComercial(int codigoSetorComercial) {
        this.codigoSetorComercial = codigoSetorComercial;
    }

    public int getNumeroQuadra() {
        return this.numeroQuadra;
    }

    public void setNumeroQuadra(int numeroQuadra) {
        this.numeroQuadra = numeroQuadra;
    }

    public int getQuantidadeLigacoesAtivas() {
        return this.quantidadeLigacoesAtivas;
    }

    public void setQuantidadeLigacoesAtivas(int quantidadeLigacoesAtivas) {
        this.quantidadeLigacoesAtivas = quantidadeLigacoesAtivas;
    }

    public int getQuantidadeLigacoesComHidrometro() {
        return this.quantidadeLigacoesComHidrometro;
    }

    public void setQuantidadeLigacoesComHidrometro(int quantidadeLigacoesComHidrometro) {
        this.quantidadeLigacoesComHidrometro = quantidadeLigacoesComHidrometro;
    }

    public int getQuantidadeLigacoesComMedicaoReal() {
        return this.quantidadeLigacoesComMedicaoReal;
    }

    public void setQuantidadeLigacoesComMedicaoReal(int quantidadeLigacoesComMedicaoReal) {
        this.quantidadeLigacoesComMedicaoReal = quantidadeLigacoesComMedicaoReal;
    }

    public int getQuantidadeLigacoesComHidrometroEMedicaoEstimada() {
        return this.quantidadeLigacoesComHidrometroEMedicaoEstimada;
    }

    public void setQuantidadeLigacoesComHidrometroEMedicaoEstimada(int quantidadeLigacoesComHidrometroEMedicaoEstimada) {
        this.quantidadeLigacoesComHidrometroEMedicaoEstimada = quantidadeLigacoesComHidrometroEMedicaoEstimada;
    }

    public int getQuantidadeEconomiasAtivas() {
        return this.quantidadeEconomiasAtivas;
    }

    public void setQuantidadeEconomiasAtivas(int quantidadeEconomiasAtivas) {
        this.quantidadeEconomiasAtivas = quantidadeEconomiasAtivas;
    }

    public int getQuantidadeEconomiasComHidrometro() {
        return this.quantidadeEconomiasComHidrometro;
    }

    public void setQuantidadeEconomiasComHidrometro(int quantidadeEconomiasComHidrometro) {
        this.quantidadeEconomiasComHidrometro = quantidadeEconomiasComHidrometro;
    }

    public int getQuantidadeEconomiasComMedicaoReal() {
        return this.quantidadeEconomiasComMedicaoReal;
    }

    public void setQuantidadeEconomiasComMedicaoReal(int quantidadeEconomiasComMedicaoReal) {
        this.quantidadeEconomiasComMedicaoReal = quantidadeEconomiasComMedicaoReal;
    }

    public int getQuantidadeEconomiasComHidrometroEMedicaoEstimada() {
        return this.quantidadeEconomiasComHidrometroEMedicaoEstimada;
    }

    public void setQuantidadeEconomiasComHidrometroEMedicaoEstimada(int quantidadeEconomiasComHidrometroEMedicaoEstimada) {
        this.quantidadeEconomiasComHidrometroEMedicaoEstimada = quantidadeEconomiasComHidrometroEMedicaoEstimada;
    }

    public int getConsumoAguaAtivas() {
        return this.consumoAguaAtivas;
    }

    public void setConsumoAguaAtivas(int consumoAguaAtivas) {
        this.consumoAguaAtivas = consumoAguaAtivas;
    }

    public int getConsumoAguaComHidrometro() {
        return this.consumoAguaComHidrometro;
    }

    public void setConsumoAguaComHidrometro(int consumoAguaComHidrometro) {
        this.consumoAguaComHidrometro = consumoAguaComHidrometro;
    }

    public int getConsumoAguaComMedicaoReal() {
        return this.consumoAguaComMedicaoReal;
    }

    public void setConsumoAguaComMedicaoReal(int consumoAguaComMedicaoReal) {
        this.consumoAguaComMedicaoReal = consumoAguaComMedicaoReal;
    }

    public int getConsumoAguaComHidrometroEMedicaoEstimada() {
        return this.consumoAguaComHidrometroEMedicaoEstimada;
    }

    public void setConsumoAguaComHidrometroEMedicaoEstimada(int consumoAguaComHidrometroEMedicaoEstimada) {
        this.consumoAguaComHidrometroEMedicaoEstimada = consumoAguaComHidrometroEMedicaoEstimada;
    }

    public int getVolumeFaturadoAgua() {
        return this.volumeFaturadoAgua;
    }

    public void setVolumeFaturadoAgua(int volumeFaturadoAgua) {
        this.volumeFaturadoAgua = volumeFaturadoAgua;
    }

    public int getVolumeFaturadoAguaMedido() {
        return this.volumeFaturadoAguaMedido;
    }

    public void setVolumeFaturadoAguaMedido(int volumeFaturadoAguaMedido) {
        this.volumeFaturadoAguaMedido = volumeFaturadoAguaMedido;
    }

    public int getVolumeFaturadoAguaNaoMedido() {
        return this.volumeFaturadoAguaNaoMedido;
    }

    public void setVolumeFaturadoAguaNaoMedido(int volumeFaturadoAguaNaoMedido) {
        this.volumeFaturadoAguaNaoMedido = volumeFaturadoAguaNaoMedido;
    }

    public int getQuantidadeLigacoes() {
        return this.quantidadeLigacoes;
    }

    public void setQuantidadeLigacoes(int quantidadeLigacoes) {
        this.quantidadeLigacoes = quantidadeLigacoes;
    }

    public int getQuantidadeEconomias() {
        return this.quantidadeEconomias;
    }

    public void setQuantidadeEconomias(int quantidadeEconomias) {
        this.quantidadeEconomias = quantidadeEconomias;
    }

    public int getVolumeEsgoto() {
        return this.volumeEsgoto;
    }

    public void setVolumeEsgoto(int volumeEsgoto) {
        this.volumeEsgoto = volumeEsgoto;
    }

    public int getVolumeFaturadoEsgoto() {
        return this.volumeFaturadoEsgoto;
    }

    public void setVolumeFaturadoEsgoto(int volumeFaturadoEsgoto) {
        this.volumeFaturadoEsgoto = volumeFaturadoEsgoto;
    }

    public int getVolumeFaturadoEsgotoMedido() {
        return this.volumeFaturadoEsgotoMedido;
    }

    public void setVolumeFaturadoEsgotoMedido(int volumeFaturadoEsgotoMedido) {
        this.volumeFaturadoEsgotoMedido = volumeFaturadoEsgotoMedido;
    }

    public int getVolumeFaturadoEsgotoNaoMedido() {
        return this.volumeFaturadoEsgotoNaoMedido;
    }

    public void setVolumeFaturadoEsgotoNaoMedido(int volumeFaturadoEsgotoNaoMedido) {
        this.volumeFaturadoEsgotoNaoMedido = volumeFaturadoEsgotoNaoMedido;
    }

    public int getQuantidadeVisitasRealizadas() {
        return this.quantidadeVisitasRealizadas;
    }

    public void setQuantidadeVisitasRealizadas(int quantidadeVisitasRealizadas) {
        this.quantidadeVisitasRealizadas = quantidadeVisitasRealizadas;
    }

    public int getQuantidadeLeiturasEfetuadas() {
        return this.quantidadeLeiturasEfetuadas;
    }

    public void setQuantidadeLeiturasEfetuadas(int quantidadeLeiturasEfetuadas) {
        this.quantidadeLeiturasEfetuadas = quantidadeLeiturasEfetuadas;
    }

    public int getQuantidadeLeiturasComAnormalidadeHidrometro() {
        return this.quantidadeLeiturasComAnormalidadeHidrometro;
    }

    public void setQuantidadeLeiturasComAnormalidadeHidrometro(int quantidadeLeiturasComAnormalidadeHidrometro) {
        this.quantidadeLeiturasComAnormalidadeHidrometro = quantidadeLeiturasComAnormalidadeHidrometro;
    }

    public int getQuantidadeHidrometroInstaladoRamal() {
        return this.quantidadeHidrometroInstaladoRamal;
    }

    public void setQuantidadeHidrometroInstaladoRamal(int quantidadeHidrometroInstaladoRamal) {
        this.quantidadeHidrometroInstaladoRamal = quantidadeHidrometroInstaladoRamal;
    }

    public int getQuantidadeHidrometroSubstituidoRamal() {
        return this.quantidadeHidrometroSubstituidoRamal;
    }

    public void setQuantidadeHidrometroSubstituidoRamal(int quantidadeHidrometroSubstituidoRamal) {
        this.quantidadeHidrometroSubstituidoRamal = quantidadeHidrometroSubstituidoRamal;
    }

    public int getQuantidadeHidrometroRemanejadoRamal() {
        return this.quantidadeHidrometroRemanejadoRamal;
    }

    public void setQuantidadeHidrometroRemanejadoRamal(int quantidadeHidrometroRemanejadoRamal) {
        this.quantidadeHidrometroRemanejadoRamal = quantidadeHidrometroRemanejadoRamal;
    }

    public int getQuantidadeHidrometroRetiradoRamal() {
        return this.quantidadeHidrometroRetiradoRamal;
    }

    public void setQuantidadeHidrometroRetiradoRamal(int quantidadeHidrometroRetiradoRamal) {
        this.quantidadeHidrometroRetiradoRamal = quantidadeHidrometroRetiradoRamal;
    }

    public int getQuantidadeHidrometrosAtualInstaladosRamal() {
        return this.quantidadeHidrometrosAtualInstaladosRamal;
    }

    public void setQuantidadeHidrometrosAtualInstaladosRamal(int quantidadeHidrometrosAtualInstaladosRamal) {
        this.quantidadeHidrometrosAtualInstaladosRamal = quantidadeHidrometrosAtualInstaladosRamal;
    }

    public int getQuantidadeHidrometroInstaladoPoco() {
        return this.quantidadeHidrometroInstaladoPoco;
    }

    public void setQuantidadeHidrometroInstaladoPoco(int quantidadeHidrometroInstaladoPoco) {
        this.quantidadeHidrometroInstaladoPoco = quantidadeHidrometroInstaladoPoco;
    }

    public int getQuantidadeHidrometroSubstituidoPoco() {
        return this.quantidadeHidrometroSubstituidoPoco;
    }

    public void setQuantidadeHidrometroSubstituidoPoco(int quantidadeHidrometroSubstituidoPoco) {
        this.quantidadeHidrometroSubstituidoPoco = quantidadeHidrometroSubstituidoPoco;
    }

    public int getQuantidadeHidrometroRemanejadoPoco() {
        return this.quantidadeHidrometroRemanejadoPoco;
    }

    public void setQuantidadeHidrometroRemanejadoPoco(int quantidadeHidrometroRemanejadoPoco) {
        this.quantidadeHidrometroRemanejadoPoco = quantidadeHidrometroRemanejadoPoco;
    }

    public int getQuantidadeHidrometroRetiradoPoco() {
        return this.quantidadeHidrometroRetiradoPoco;
    }

    public void setQuantidadeHidrometroRetiradoPoco(int quantidadeHidrometroRetiradoPoco) {
        this.quantidadeHidrometroRetiradoPoco = quantidadeHidrometroRetiradoPoco;
    }

    public int getQuantidadeHidrometrosAtualInstaladosPoco() {
        return this.quantidadeHidrometrosAtualInstaladosPoco;
    }

    public void setQuantidadeHidrometrosAtualInstaladosPoco(int quantidadeHidrometrosAtualInstaladosPoco) {
        this.quantidadeHidrometrosAtualInstaladosPoco = quantidadeHidrometrosAtualInstaladosPoco;
    }

    public int getQuantidadeHidrometroDadosAtualizados() {
        return this.quantidadeHidrometroDadosAtualizados;
    }

    public void setQuantidadeHidrometroDadosAtualizados(int quantidadeHidrometroDadosAtualizados) {
        this.quantidadeHidrometroDadosAtualizados = quantidadeHidrometroDadosAtualizados;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public GUnidadeNegocio getGerUnidadeNegocio() {
        return this.gerUnidadeNegocio;
    }

    public void setGerUnidadeNegocio(GUnidadeNegocio gerUnidadeNegocio) {
        this.gerUnidadeNegocio = gerUnidadeNegocio;
    }

    public GSetorComercial getGerSetorComercial() {
        return this.gerSetorComercial;
    }

    public void setGerSetorComercial(GSetorComercial gerSetorComercial) {
        this.gerSetorComercial = gerSetorComercial;
    }

    public GSubcategoria getGerSubcategoria() {
        return this.gerSubcategoria;
    }

    public void setGerSubcategoria(GSubcategoria gerSubcategoria) {
        this.gerSubcategoria = gerSubcategoria;
    }

    public GLigacaoAguaSituacao getGerLigacaoAguaSituacao() {
        return this.gerLigacaoAguaSituacao;
    }

    public void setGerLigacaoAguaSituacao(GLigacaoAguaSituacao gerLigacaoAguaSituacao) {
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
    }

    public GLigacaoAguaPerfil getGerLigacaoAguaPerfil() {
        return this.gerLigacaoAguaPerfil;
    }

    public void setGerLigacaoAguaPerfil(GLigacaoAguaPerfil gerLigacaoAguaPerfil) {
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
    }

    public GCategoria getGerCategoria() {
        return this.gerCategoria;
    }

    public void setGerCategoria(GCategoria gerCategoria) {
        this.gerCategoria = gerCategoria;
    }

    public GGerenciaRegional getGerGerenciaRegional() {
        return this.gerGerenciaRegional;
    }

    public void setGerGerenciaRegional(GGerenciaRegional gerGerenciaRegional) {
        this.gerGerenciaRegional = gerGerenciaRegional;
    }

    public gcom.gerencial.micromedicao.GRota getGerRota() {
        return this.gerRota;
    }

    public void setGerRota(gcom.gerencial.micromedicao.GRota gerRota) {
        this.gerRota = gerRota;
    }

    public GLocalidade getGerLocalidadeElo() {
        return this.gerLocalidadeElo;
    }

    public void setGerLocalidadeElo(GLocalidade gerLocalidadeElo) {
        this.gerLocalidadeElo = gerLocalidadeElo;
    }

    public GLocalidade getGerLocalidade() {
        return this.gerLocalidade;
    }

    public void setGerLocalidade(GLocalidade gerLocalidade) {
        this.gerLocalidade = gerLocalidade;
    }

    public GClienteTipo getGerClienteTipo() {
        return this.gerClienteTipo;
    }

    public void setGerClienteTipo(GClienteTipo gerClienteTipo) {
        this.gerClienteTipo = gerClienteTipo;
    }

    public GImovelPerfil getGerImovelPerfil() {
        return this.gerImovelPerfil;
    }

    public void setGerImovelPerfil(GImovelPerfil gerImovelPerfil) {
        this.gerImovelPerfil = gerImovelPerfil;
    }

    public GLigacaoEsgotoPerfil getGerLigacaoEsgotoPerfil() {
        return this.gerLigacaoEsgotoPerfil;
    }

    public void setGerLigacaoEsgotoPerfil(GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    }

    public GEsferaPoder getGerEsferaPoder() {
        return this.gerEsferaPoder;
    }

    public void setGerEsferaPoder(GEsferaPoder gerEsferaPoder) {
        this.gerEsferaPoder = gerEsferaPoder;
    }

    public GQuadra getGerQuadra() {
        return this.gerQuadra;
    }

    public void setGerQuadra(GQuadra gerQuadra) {
        this.gerQuadra = gerQuadra;
    }

    public GLigacaoEsgotoSituacao getGerLigacaoEsgotoSituacao() {
        return this.gerLigacaoEsgotoSituacao;
    }

    public void setGerLigacaoEsgotoSituacao(GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao) {
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
