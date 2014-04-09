package gcom.gui.relatorio.faturamento;

import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesInterfaceGSAN;
import gcom.util.ConstantesSistema;
import gcom.util.Internacionalizador;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *[UC0958] - Gerar Relatorio de Juros, Multas e Debitos Cancelados.
 *
 * @author Marlon Patrick
 * @since 07/10/2009
 */
public class ExibirGerarRelatorioJurosMultasDebitosCanceladosAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		GerarRelatorioJurosMultasDebitosCanceladosActionForm form = 
			(GerarRelatorioJurosMultasDebitosCanceladosActionForm) actionForm;
		
		if(Util.verificarNaoVazio(form.getIdLocalidade())){
			pesquisarLocalidade(form);			
		}
		
		if(Util.verificarNaoVazio(form.getIdUsuarioCancelamento())){
			pesquisarUsuarioCancelamento(form);
		}
		
		carregarComboboxEListbox(form);
		
		return actionMapping.findForward("exibirGerarRelatorioJurosMultasDebitosCancelados");
	}

	/**
	 * Este método faz a consulta para verificar a existencia da Localidade informada pelo usuário.
	 * 
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void pesquisarLocalidade(GerarRelatorioJurosMultasDebitosCanceladosActionForm form){

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,form.getIdLocalidade()));
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO
				,ConstantesSistema.INDICADOR_USO_ATIVO));
		
		boolean isUnidadeNegocioInformado = false;
		
		if(Util.isCampoComboboxInformado(form.getIdUnidadeNegocio())){
			isUnidadeNegocioInformado = true;
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.UNIDADE_NEGOCIO_ID,form.getIdUnidadeNegocio()));
		}
		
		Collection<Localidade> colecaoLocalidade = 
			this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoLocalidade)) {
				form.setIdLocalidade("");
				form.setLocalidadeExistente(false);

				if(isUnidadeNegocioInformado){
					form.setNomeLocalidade(
							Internacionalizador.getMensagem(ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO1_INEXISTENTE_NA_CAMPO2_INFORMADA,
								new String[]{ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE,ConstantesInterfaceGSAN.LABEL_GSAN_UNIDADE_NEGOCIO}));
				}else{
					form.setNomeLocalidade(Internacionalizador.getMensagem(
							ConstantesInterfaceGSAN.ATENCAO_PESQUISA_INEXISTENTE, ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE));
				}

				return;
		}
		
		Localidade localidade = colecaoLocalidade.iterator().next();
		
		form.setIdLocalidade(localidade.getId().toString());
		form.setNomeLocalidade(localidade.getDescricao());
		form.setLocalidadeExistente(true);

	}

	/**
	 * Este método faz a consulta para verificar a existencia do Usuário informado pelo usuário.
	 * 
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void pesquisarUsuarioCancelamento(GerarRelatorioJurosMultasDebitosCanceladosActionForm form){

		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID,
				form.getIdUsuarioCancelamento()));
		
		Collection<Usuario> colecaoUsuario = 
			this.getFachada().pesquisar(filtroUsuario, Usuario.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoUsuario)) {
				form.setIdUsuarioCancelamento("");
				form.setNomeUsuarioCancelamento(
						Internacionalizador.getMensagem(
								ConstantesInterfaceGSAN.ATENCAO_PESQUISA_INEXISTENTE, ConstantesInterfaceGSAN.LABEL_GSAN_USUARIO));
				
				form.setUsuarioExistente(false);
			
				return;
		}
		
		Usuario usuario = colecaoUsuario.iterator().next();
		
		form.setIdUsuarioCancelamento(usuario.getId().toString());
		form.setNomeUsuarioCancelamento(usuario.getNomeUsuario());
		form.setUsuarioExistente(true);
	}

	/**
	 * 
	 * Esse método invoca os métodos responsáveis por carregar as coleções de
	 * todos os combo boxes e list boxes a serem exibidos na tela.
	 *
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void carregarComboboxEListbox(GerarRelatorioJurosMultasDebitosCanceladosActionForm form) {
		carregarColecaoUnidadesNegocios(form);
		carregarColecaoTiposDebito(form);
		carregarColecaoCategorias(form);
		carregarColecaoPerfisImovel(form);
		carregarColecaoEsferasPoder(form);
	}
	
	/**
	 * Método consulta os perfis de imóvel ativos
	 * e seta essa coleção no FORM para que seja exibida na tela.
	 * 
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void carregarColecaoPerfisImovel(GerarRelatorioJurosMultasDebitosCanceladosActionForm form){
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.setConsultaSemLimites(true);
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);		
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(
				FiltroImovelPerfil.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<ImovelPerfil> colecaoImoveisPerfis = this.getFachada().pesquisar(
				filtroImovelPerfil, ImovelPerfil.class.getName());

		if ( Util.isVazioOrNulo(colecaoImoveisPerfis)) {
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_NAO_CADASTRADO, 
					ConstantesInterfaceGSAN.LABEL_GSAN_PERFIL_IMOVEL);
		}
		
		form.setColecaoPerfisImovel(colecaoImoveisPerfis);
	}

	/**
	 * Método consulta as Unidades de Negócio ativas
	 * e seta essa coleção no FORM para que seja exibida na tela.
	 * 
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void carregarColecaoTiposDebito(GerarRelatorioJurosMultasDebitosCanceladosActionForm form){
		
		FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
		filtroDebitoTipo.setConsultaSemLimites(true);
		filtroDebitoTipo.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);		
		filtroDebitoTipo.adicionarParametro(
				new ParametroSimples(FiltroDebitoTipo.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection<DebitoTipo> colecaoDebitoTipo = 
			this.getFachada().pesquisar(filtroDebitoTipo,DebitoTipo.class.getName());

		if ( Util.isVazioOrNulo(colecaoDebitoTipo)) {
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_NAO_CADASTRADO, 
					ConstantesInterfaceGSAN.LABEL_GSAN_TIPO_DEBITO);
		}
		
		form.setColecaoTiposDebito(colecaoDebitoTipo);
	}

	/**
	 * Método consulta as Unidades de Negócio ativas
	 * e seta essa coleção no FORM para que seja exibida na tela.
	 * 
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void carregarColecaoUnidadesNegocios(GerarRelatorioJurosMultasDebitosCanceladosActionForm form){
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);		
		filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection<UnidadeNegocio> colecaoUnidadeNegocio = 
			this.getFachada().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());

		if ( Util.isVazioOrNulo(colecaoUnidadeNegocio)) {
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_NAO_CADASTRADO, 
					ConstantesInterfaceGSAN.LABEL_GSAN_UNIDADE_NEGOCIO);
		}
		
		form.setColecaoUnidadesNegocios(colecaoUnidadeNegocio);
	}
	
	/**
	 * Método consulta as categorias ativas
	 * e seta essa coleção no FORM para que seja exibida na tela.
	 * 
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void carregarColecaoCategorias(GerarRelatorioJurosMultasDebitosCanceladosActionForm form){
		
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.setConsultaSemLimites(true);
		filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
		filtroCategoria.adicionarParametro(
				new ParametroSimples(FiltroCategoria.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<Categoria> colecaoCategoria = 
			this.getFachada().pesquisar(filtroCategoria, Categoria.class.getName());


		if ( Util.isVazioOrNulo(colecaoCategoria)) {
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_NAO_CADASTRADO, 
					ConstantesInterfaceGSAN.LABEL_GSAN_CATEGORIA);
		}
		
		form.setColecaoCategorias(colecaoCategoria);
	}
	
	/**
	 * Método consulta as esferas do poder ativas
	 * e seta essa coleção no FORM para que seja exibida na tela.
	 * 
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void carregarColecaoEsferasPoder(GerarRelatorioJurosMultasDebitosCanceladosActionForm form){
		
		FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
		filtroEsferaPoder.setConsultaSemLimites(true);
		filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);
		filtroEsferaPoder.adicionarParametro(
				new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<EsferaPoder> colecaoEsferaPoder = 
			this.getFachada().pesquisar(filtroEsferaPoder,EsferaPoder.class.getName());

		if ( Util.isVazioOrNulo(colecaoEsferaPoder)) {
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_NAO_CADASTRADO, 
					ConstantesInterfaceGSAN.LABEL_GSAN_ESFERA_PODER);
		}
		
		form.setColecaoEsferasPoder(colecaoEsferaPoder);
	}
}
