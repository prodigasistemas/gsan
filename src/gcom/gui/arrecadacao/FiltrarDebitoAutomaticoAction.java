package gcom.gui.arrecadacao;

import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.FiltroAgencia;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.debitoautomatico.FiltroDebitoAutomatico;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0802] - Filtrar Debito Automático
 * 
 * @author Bruno Barros
 * @date 11/06/2008
 */
public class FiltrarDebitoAutomaticoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("exibirManterDebitoAutomatico");

		FiltrarDebitoAutomaticoActionForm form = (FiltrarDebitoAutomaticoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// Iniciamos a montagem do filtro
		FiltroDebitoAutomatico filtroDebitoAutomatico = new FiltroDebitoAutomatico();
		// Trazemos apenas os debitos com data de exclusão nula
		filtroDebitoAutomatico.adicionarParametro( new ParametroNulo( FiltroDebitoAutomatico.DATA_EXCLUSAO  ) );
		
		// Verificamos se algum parametro foi informado, e em caso positivo
		// adicionamos ao filtro, caso contrario, informamos que ao menos 1
		// parametro precisa ser informado
		boolean bInformouParametro = false;
		
		// Informou matricula ????
		if ( form.getMatricula() != null && !form.getMatricula().equals( "" ) ){
			bInformouParametro = true;
			
			// Uma vez que a matricula foi informada, verificamos se a mesma é valida
			// FS0001 - Verificar existencia de débito automático para o imovel
			if ( !verificaExistenciaDebitoAutomatico( form.getMatricula() ) ){
				throw new ActionServletException( "atencao.imovel_sem_debito_automatico_cadastrado" );
			}
			
			filtroDebitoAutomatico.adicionarParametro( new ParametroSimples( FiltroDebitoAutomatico.IMOVEL_MATRICULA, form.getMatricula() ) );			
		} else {		
			// Informou agencia ????
			if ( form.getAgenciaCodigo() != null && !form.getAgenciaCodigo().equals( "" ) ){
				bInformouParametro = true;
				
				// Uma vez que a agencia foi informada, verificamos se a mesma é válida
				// FS0003 - Verificar existencia da agência
				if ( !verificaCodigoAgenciaValido( form.getAgenciaCodigo() ) ){
					throw new ActionServletException( "atencao.agenciaBancaria.inexistente" );
				}			
				
				filtroDebitoAutomatico.adicionarCaminhoParaCarregamentoEntidade( "agencia" );
				filtroDebitoAutomatico.adicionarParametro( new ParametroSimples( FiltroDebitoAutomatico.AGENCIA_CODIGO, form.getAgenciaCodigo() ) );			
			}
			
			// Informou banco ????
			if ( form.getBancoID() != null && !form.getBancoID().equals( "" ) ){
				bInformouParametro = true;
				
				// uma vez que o banco foi informado, verificamos se a mesma é valida
				// FS0002 - Verificar existência do banco
				if ( !verificaExistenciaBancoId( form.getBancoID() ) ){
					throw new ActionServletException( "atencao.banco.inexistente" );
				}						
				filtroDebitoAutomatico.adicionarParametro( new ParametroSimples( FiltroDebitoAutomatico.BANCO_ID, form.getBancoID() ) );			
			}
		}
		
		// Verificamos se ao menos 1 parametro foi informado
		// FS0004 - Verificar Preenchimento dos campos
		if ( !bInformouParametro ){
			throw new ActionServletException( "atencao.filtro.nenhum_parametro_informado" );
		}		

		// Verificamos se a agencia informada pertence ao banco informado
		if ( form.getAgenciaCodigo() != null && !form.getAgenciaCodigo().equals( "" ) ){
			verificaAgenciaPertenceAoBanco( form.getBancoID(), form.getAgenciaCodigo() );
		}
		
		// Verificamos se a pesquisar irá retornar algum resultado
		// FS0005 - Nenhum resultado encontrado
		if ( verificarRegistrosGeradosZerados( filtroDebitoAutomatico ) ){
			throw new ActionServletException( "atencao.pesquisa.nenhumresultado" );
		}		

		// FS0006 - Verica se, informada a agencia, tambem informou o banco
		// Manda o filtro pelo request para o ExibirManterDebitoAutomatico
		sessao.setAttribute("filtroDebitoAutomatico", filtroDebitoAutomatico);

		return retorno;
	}
	
	// FS0001 - Verificar existencia de débito automático para o imovel 
	private boolean verificaExistenciaDebitoAutomatico( String matricula ){
		// Montamos o filtro para verificar se a matricula do imovel
		// possue algum registro na tabela de debito automatico com
		// data de exclusão diferente de nulo
		FiltroDebitoAutomatico filtro = new FiltroDebitoAutomatico();
		filtro.adicionarParametro( new ParametroSimples( FiltroDebitoAutomatico.IMOVEL_MATRICULA, matricula ) );
		filtro.adicionarParametro( new ParametroNulo( FiltroDebitoAutomatico.DATA_EXCLUSAO ) );		
		Collection<DebitoAutomatico> colDebitos = Fachada.getInstancia().pesquisar( filtro, DebitoAutomatico.class.getName() );
		
		return colDebitos != null && colDebitos.size() > 0;
	}
	
	// FS0002 - Verificar existencia do banco 
	private boolean verificaExistenciaBancoId( String bancoId ){
		// Montamos o filtro para verificar se o banco existe
		FiltroBanco filtro = new FiltroBanco();
		filtro.adicionarParametro( new ParametroSimples( FiltroBanco.ID, bancoId ) );		
		Collection<Banco> colBancos = Fachada.getInstancia().pesquisar( filtro, Banco.class.getName() );
		
		return colBancos != null && colBancos.size() > 0;
	}	
	
	// FS0003 - Verificar existencia da agencia bancaria 
	private boolean verificaCodigoAgenciaValido( String agenciaCodigo ){
		// Montamos o filtro para verificar se a agencia bancária existe
		FiltroAgencia filtro = new FiltroAgencia();
		filtro.adicionarParametro( new ParametroSimples( FiltroAgencia.CODIGO_AGENCIA, agenciaCodigo ) );		
		Collection<Agencia> colAgencias = Fachada.getInstancia().pesquisar( filtro, Agencia.class.getName() );
		
		return colAgencias != null && colAgencias.size() > 0;
	}
	
	// FS0005 - Nenhum registro encontrado
	private boolean verificarRegistrosGeradosZerados( FiltroDebitoAutomatico filtro ){
		// Pesquisamos
		Collection<DebitoAutomatico> colDebito =  Fachada.getInstancia().pesquisar( filtro, DebitoAutomatico.class.getName() );
		return colDebito == null || colDebito.size() == 0;
	}
	
	// FS0006 - Verica se, informada a agencia, tambem informou o banco
	// e se a agencia pertence aquele banco
	private void verificaAgenciaPertenceAoBanco( String bancoId, String agenciaCodigo ) throws ActionServletException{
		// Caso banco seja nulo, informar que ele e obrigatorio com a agencie
		if ( bancoId == null || bancoId.equals( "" ) ){
			throw new ActionServletException( "atencao.agencia.sem_banco" );
		}
		
		// Verificamo se a agencia pertence ao banco informado
		FiltroAgencia filtro = new FiltroAgencia();
		filtro.adicionarParametro( new ParametroSimples( FiltroAgencia.CODIGO_AGENCIA, agenciaCodigo ) ); 
		filtro.adicionarParametro( new ParametroSimples( FiltroAgencia.BANCO_ID, bancoId ) );
		
		Collection<DebitoAutomatico> colDebitos = Fachada.getInstancia().pesquisar( filtro, Agencia.class.getName() ); 
		
		if ( colDebitos == null || colDebitos.size() == 0 ){
			throw new ActionServletException( "atencao.agencia.banco_errado" );
		}
	}
	
}
