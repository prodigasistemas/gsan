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
package gcom.micromedicao;

import gcom.micromedicao.SituacaoTransmissaoLeitura;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ArquivoTextoRoteiroEmpresaDivisao implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa;

    /** persistent field */
    private Integer quantidadeImovel;
    
    /** persistent field */
    private byte[] arquivoTexto;
    
    /** persistent field */
    private String nomeArquivo;
    
    /** persistent field */
    private Integer numeroSequenciaArquivo;
    
    /** persistent field */
    private SituacaoTransmissaoLeitura situacaoTransmissaoLeitura;
    
    /** persistent field */
    private Long numeroImei;
    
    /** persistent field */
    private gcom.micromedicao.Leiturista leiturista;

    /** persistent field */
    private Date ultimaAlteracao;
    
    /**
     * Description of the Field
     */
    public final static int DISPONIVEL = 1;
    public final static int ARQUIVO_TEXTO_LIBERADO = 2;
    public final static int ARQUIVO_TEXTO_EM_CAMPO = 3;
    public final static int ARQUIVO_TEXTO_FINALIZADO = 4;
    
    public ArquivoTextoRoteiroEmpresaDivisao(Integer id, SituacaoTransmissaoLeitura situacaoTransmissaoLeitura) {
    	this.id = id;
    	this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
    }

    /** full constructor */
    public ArquivoTextoRoteiroEmpresaDivisao(Integer id, 
    										 ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa, 
    										 Integer quantidadeImovel,  
    										 byte[] arquivoTexto, 
    										 String nomeArquivo, 
    										 Integer numeroSequenciaArquivo,
    										 SituacaoTransmissaoLeitura situacaoTransmissaoLeitura,
    										 Date ultimaAlteracao) {
        this.id = id;
        this.arquivoTextoRoteiroEmpresa = arquivoTextoRoteiroEmpresa;
        this.quantidadeImovel = quantidadeImovel;
        this.arquivoTexto = arquivoTexto;
        this.nomeArquivo = nomeArquivo;
        this.numeroSequenciaArquivo = numeroSequenciaArquivo;
        this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public ArquivoTextoRoteiroEmpresaDivisao() {
    }

	public byte[] getArquivoTexto() {
		return arquivoTexto;
	}

	public void setArquivoTexto(byte[] arquivoTexto) {
		this.arquivoTexto = arquivoTexto;
	}

	public ArquivoTextoRoteiroEmpresa getArquivoTextoRoteiroEmpresa() {
		return arquivoTextoRoteiroEmpresa;
	}

	public void setArquivoTextoRoteiroEmpresa(
			ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa) {
		this.arquivoTextoRoteiroEmpresa = arquivoTextoRoteiroEmpresa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Integer getNumeroSequenciaArquivo() {
		return numeroSequenciaArquivo;
	}

	public void setNumeroSequenciaArquivo(Integer numeroSequenciaArquivo) {
		this.numeroSequenciaArquivo = numeroSequenciaArquivo;
	}

	public Integer getQuantidadeImovel() {
		return quantidadeImovel;
	}

	public void setQuantidadeImovel(Integer quantidadeImovel) {
		this.quantidadeImovel = quantidadeImovel;
	}

	public SituacaoTransmissaoLeitura getSituacaoTransmissaoLeitura() {
		return situacaoTransmissaoLeitura;
	}

	public void setSituacaoTransmissaoLeitura(
			SituacaoTransmissaoLeitura situacaoTransmissaoLeitura) {
		this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public gcom.micromedicao.Leiturista getLeiturista() {
		return leiturista;
	}

	public void setLeiturista(gcom.micromedicao.Leiturista leiturista) {
		this.leiturista = leiturista;
	}

	public Long getNumeroImei() {
		return numeroImei;
	}

	public void setNumeroImei(Long numeroImei) {
		this.numeroImei = numeroImei;
	}
    
    
    
}
