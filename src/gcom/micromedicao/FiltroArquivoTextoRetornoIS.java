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
 * Alteração realizada pela COSANPA
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

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroArquivoTextoRetornoIS extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final String ID_ARQUIVO_TEXTO_RETORNO_IS = "id";
	
	public static final String ANO_MES_REFERENCIA = "anoMesReferencia";
	
	public static final String CODIGO_SETOR = "codigoSetorComercial";
	
	public static final String CODIGO_ROTA = "codigo";
	
	public static final String NOME_ARQUIVO = "nomeArquivo";
	
	public static final String TEMPO_RETORNO_ARQUIVO = "tempoRetornoArquivo";
	
	public static final String TEMPO_FINALIZACAO = "tempoFinalizacao";
	
    public static final String FATURAMENTO_GRUPO_ID = "faturamentoGrupo.id";
    
    public static final String LOCALIDADE_ID = "localidade.id";

}
