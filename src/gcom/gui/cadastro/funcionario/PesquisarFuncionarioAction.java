package gcom.gui.cadastro.funcionario;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de pesquisa de funcionário
 * 
 * @author Vivianne Sousa
 * @created 02/03/2006
 */

public class PesquisarFuncionarioAction extends GcomAction {
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
  
  //Inicializacoes de variaveis
  ActionForward retorno = actionMapping.findForward("retornoPesquisa");
  HttpSession sessao = httpServletRequest.getSession(false);
  //Fachada fachada = Fachada.getInstancia();  
  boolean peloMenosUmParametroInformado = false;
  PesquisarFuncionarioActionForm form = (PesquisarFuncionarioActionForm) actionForm;
  
  String id = form.getId();
  String nome = form.getNome();
  String idUnidadeEmpresa = form.getIdUnidadeEmpresa();
  String tipoPesquisa = form.getTipoPesquisa();
  
  //Matrícula mair q zero 
  if (id.equals("0")){
  	throw new ActionServletException("atencao.matricula_usuario_maior_zero");
  }
  
  FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
  filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
  filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("funcionarioCargo");
  
  
  
  
  
  
  
  //Pesquisa id  
  if(id != null && !id.equals("")){
   filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, id));
   peloMenosUmParametroInformado = true;
  }
  
  //Pesquisa nome  
  if(nome != null && !nome.equals("")){
	  
	  if (tipoPesquisa != null
				&& tipoPesquisa
						.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
								.toString())) {

			filtroFuncionario
					.adicionarParametro(new ComparacaoTextoCompleto(
							FiltroFuncionario.NOME, nome));
		} else {

			filtroFuncionario.adicionarParametro(new ComparacaoTexto(
					FiltroFuncionario.NOME, nome));
		}
   //filtroFuncionario.adicionarParametro(new ComparacaoTexto(FiltroFuncionario.NOME, nome));
   peloMenosUmParametroInformado = true;
  }
  
  //Pesquisa idUnidadeEmpresa
  if(idUnidadeEmpresa != null && !idUnidadeEmpresa.equals("")){
   filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.UNIDADE_ORGANIZACIONAL_ID, idUnidadeEmpresa));
   peloMenosUmParametroInformado = true;
  }
  
  
  
  
 
  // [FS0002] Verificar preenchimento dos campos
  if (!peloMenosUmParametroInformado) {
   throw new ActionServletException(
     "atencao.filtro.nenhum_parametro_informado");
	}
  
   //
 // Collection<Funcionario> collectionFuncionario = fachada.pesquisar(filtroFuncionario, Funcionario.class.getName());
  
  	filtroFuncionario.setCampoOrderBy(FiltroFuncionario.NOME, FiltroFuncionario.ID);
  
  	Map resultado = controlarPaginacao(httpServletRequest, retorno,
		  filtroFuncionario, Funcionario.class.getName());

	Collection collectionFuncionario = (Collection) resultado
			.get("colecaoRetorno");

	retorno = (ActionForward) resultado.get("destinoActionForward");
  
  
  
  //Validacoes 
  if (collectionFuncionario == null || collectionFuncionario.isEmpty()) {
  // [FS0004] Nenhum registro encontrado
   throw new ActionServletException(
     "atencao.pesquisa.nenhumresultado", null, "funcionario");
  } else if (collectionFuncionario.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
  // [FS0003] Muitos registros encontrados
   throw new ActionServletException("atencao.pesquisa.muitosregistros");
  } else {
   sessao.setAttribute("collectionFuncionario", collectionFuncionario);
  }    
  
  

  return retorno;
 }
}
