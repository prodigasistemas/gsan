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
package gcom.batch.arrecadacao;

import gcom.cadastro.localidade.Localidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * @author COSANPA - Felipe Santos - Mantis 414
 * @date 25/10/2013
 */
public class TarefaBatchGerarDadosRelatorioBIG extends TarefaBatch {
	
	public TarefaBatchGerarDadosRelatorioBIG(Usuario usuario,
			int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchGerarDadosRelatorioBIG() {
		super(null, 0);
	}

	private static final long serialVersionUID = 1L;

	@Override
	protected Collection pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	protected Collection pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("GerarDadosRelatorioBIGBatch", this);
	}

	@Override
	public Object executar() throws TarefaException {
		
		Integer anoMesReferencia = (Integer) getParametro("anoMesReferencia");
		
		Collection<Localidade> colecaoLocalidades = (Collection<Localidade>) getParametro(
				ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 
		
		Iterator iterator = colecaoLocalidades.iterator();

		while (iterator.hasNext()) {

			Localidade localidade = (Localidade) iterator.next();
			
			enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_DADOS_RELATORIO_BIG_MDB,
					new Object[]{anoMesReferencia, localidade, this.getIdFuncionalidadeIniciada()});
			
		}
		
		return null;
	}
	
	

}
