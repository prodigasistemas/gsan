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
package gcom.gui.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.Despejo;
import gcom.cadastro.imovel.FiltroAreaConstruidaFaixa;
import gcom.cadastro.imovel.FiltroDespejo;
import gcom.cadastro.imovel.FiltroFonteAbastecimento;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroImovelTipoCobertura;
import gcom.cadastro.imovel.FiltroImovelTipoConstrucao;
import gcom.cadastro.imovel.FiltroImovelTipoHabitacao;
import gcom.cadastro.imovel.FiltroImovelTipoPropriedade;
import gcom.cadastro.imovel.FiltroPavimentoCalcada;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.FiltroPiscinaVolumeFaixa;
import gcom.cadastro.imovel.FiltroPocoTipo;
import gcom.cadastro.imovel.FiltroReservatorioVolumeFaixa;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelTipoCobertura;
import gcom.cadastro.imovel.ImovelTipoConstrucao;
import gcom.cadastro.imovel.ImovelTipoHabitacao;
import gcom.cadastro.imovel.ImovelTipoPropriedade;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.imovel.PiscinaVolumeFaixa;
import gcom.cadastro.imovel.PocoTipo;
import gcom.cadastro.imovel.ReservatorioVolumeFaixa;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroQuadraFace;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.bean.IntegracaoQuadraFaceHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirInserirImovelCaracteristicasAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
		ActionForm actionForm, HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse) {

        ActionForward retorno = 
        	actionMapping.findForward("inserirImovelCaracteristicas");

        //Obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm inserirImovelClienteActionForm = 
        	(DynaValidatorForm) sessao.getAttribute("InserirImovelActionForm");

        //Cria filtros
        FiltroAreaConstruidaFaixa filtroAreaConstruida = new FiltroAreaConstruidaFaixa();
        FiltroReservatorioVolumeFaixa filtroReservatorioVolumeFaixa = new FiltroReservatorioVolumeFaixa();
        FiltroPiscinaVolumeFaixa filtroPiscinaVolumeFaixa = new FiltroPiscinaVolumeFaixa();
        FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada();
        FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
        FiltroFonteAbastecimento filtroFonteAbastecimento = new FiltroFonteAbastecimento();
        FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
        FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
        FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
        FiltroPocoTipo filtroPocoTipo = new FiltroPocoTipo();
        FiltroDespejo filtroDespejo = new FiltroDespejo();
        
        FiltroImovelTipoHabitacao filtroImovelTipoHabitacao = new FiltroImovelTipoHabitacao();
        FiltroImovelTipoPropriedade filtroImovelTipoPropriedade = new FiltroImovelTipoPropriedade();
        FiltroImovelTipoConstrucao filtroImovelTipoConstrucao = new FiltroImovelTipoConstrucao();
        FiltroImovelTipoCobertura filtroImovelTipoCobertura = new FiltroImovelTipoCobertura();
        
        FiltroLigacaoEsgotoEsgotamento filtroLigacaoEsgotoEsgotamento = new FiltroLigacaoEsgotoEsgotamento();
       
        //Cria Coleçao
        Collection<AreaConstruidaFaixa> areaContruidaFaixas = null;
        Collection<ReservatorioVolumeFaixa> reservatorioVolumeFaixas = null;
        Collection<PiscinaVolumeFaixa> piscinaVolumeFaixas = null;
        Collection<PavimentoCalcada> pavimetoCalcadas = null;
        Collection<PavimentoRua> pavimentoRuas= null;
        Collection<FonteAbastecimento> fonteAbastecimentos = null;
        Collection<LigacaoEsgotoSituacao> ligacaoEsgotoSituacaos = null;
        Collection<LigacaoAguaSituacao> ligacaoAguaSituacaos = null;
        Collection<PocoTipo> pocoTipos = null;
        Collection<ImovelPerfil> imovelPerfis = null;
        Collection<Despejo> despejos = null;
        Collection<ImovelTipoHabitacao> tipoHabitacao = new ArrayList<ImovelTipoHabitacao>();
        Collection<ImovelTipoPropriedade> tipoPropriedade = new ArrayList<ImovelTipoPropriedade>();
        Collection<ImovelTipoConstrucao> tipoConstrucao = new ArrayList<ImovelTipoConstrucao>();
        Collection<ImovelTipoCobertura> tipoCobertura = new ArrayList<ImovelTipoCobertura>();
        
        Collection<LigacaoEsgotoEsgotamento> colecaoLigacaoEsgotoEsgotamento = new ArrayList<LigacaoEsgotoEsgotamento>();
        
        //Obtém a instância da Fachada
        Fachada fachada = Fachada.getInstancia();
        
        sessao.removeAttribute("gis");

        //Faz as pesuisas e testa se as colecoes estao vazias antes de jogalas
        // na sessao
        
        //Filtro Esgotamento
        filtroLigacaoEsgotoEsgotamento.adicionarParametro(
        new ParametroSimples(FiltroLigacaoEsgotoEsgotamento.INDICADOR_USO,
        ConstantesSistema.INDICADOR_USO_ATIVO));
        
        filtroLigacaoEsgotoEsgotamento.setCampoOrderBy(FiltroLigacaoEsgotoEsgotamento.DESCRICAO);
        
        colecaoLigacaoEsgotoEsgotamento = fachada.pesquisar(filtroLigacaoEsgotoEsgotamento,
        LigacaoEsgotoEsgotamento.class.getName());
        
        if ( Util.isVazioOrNulo(colecaoLigacaoEsgotoEsgotamento)) {
            throw new ActionServletException("atencao.naocadastrado", null,"Esgotamento");
        }
        
        filtroAreaConstruida.adicionarParametro(
        	new ParametroSimples(FiltroAreaConstruidaFaixa.INDICADOR_USO,
        		ConstantesSistema.INDICADOR_USO_ATIVO));
        
        areaContruidaFaixas = fachada.pesquisar(filtroAreaConstruida,AreaConstruidaFaixa.class.getName());
        
        if (Util.isVazioOrNulo(areaContruidaFaixas)) {
            throw new ActionServletException("atencao.naocadastrado", null,"Area Construida Faixa");
        }
        
        filtroReservatorioVolumeFaixa.adicionarParametro(
        	new ParametroSimples(FiltroReservatorioVolumeFaixa.INDICADOR_USO,
        		ConstantesSistema.INDICADOR_USO_ATIVO));
        
        reservatorioVolumeFaixas = 
        	fachada.pesquisar(filtroReservatorioVolumeFaixa, ReservatorioVolumeFaixa.class.getName());
        
        if (Util.isVazioOrNulo(reservatorioVolumeFaixas)) {
            throw new ActionServletException("atencao.naocadastrado", null,"Reservatorio Volume Faixa");
        }
        
        filtroPiscinaVolumeFaixa.adicionarParametro(
        	new ParametroSimples(FiltroPiscinaVolumeFaixa.INDICADOR_USO,
        		ConstantesSistema.INDICADOR_USO_ATIVO));
        
        piscinaVolumeFaixas = 
        	fachada.pesquisar(filtroPiscinaVolumeFaixa,PiscinaVolumeFaixa.class.getName());
        
        if (Util.isVazioOrNulo(piscinaVolumeFaixas)) {
            throw new ActionServletException("atencao.naocadastrado", null,"Piscina Volume Faixa");
        }
        
        //jardim
        if(inserirImovelClienteActionForm.get("jardim") != null && 
        	inserirImovelClienteActionForm.get("jardim").equals("")){
        	
        	inserirImovelClienteActionForm.set("jardim","2");
        }
        
        //filtro pavimento calçada
        filtroPavimentoCalcada.adicionarParametro(
        	new ParametroSimples(FiltroPavimentoCalcada.INDICADOR_USO,
        		ConstantesSistema.INDICADOR_USO_ATIVO));
        
        filtroPavimentoCalcada.setCampoOrderBy(FiltroPavimentoCalcada.DESCRICAO);
        
        pavimetoCalcadas = 
        	fachada.pesquisar(filtroPavimentoCalcada,PavimentoCalcada.class.getName());
        
        if (Util.isVazioOrNulo(pavimetoCalcadas)) {
            throw new ActionServletException("atencao.naocadastrado",null, "Paimento Calçada");
        }
        //filtro pavimento rua
        filtroPavimentoRua.adicionarParametro(
        	new ParametroSimples(FiltroPavimentoRua.INDICADOR_USO,
        		ConstantesSistema.INDICADOR_USO_ATIVO));
        
        filtroPavimentoRua.setCampoOrderBy(FiltroPavimentoRua.DESCRICAO);
        
        pavimentoRuas = 
        	fachada.pesquisar(filtroPavimentoRua,PavimentoRua.class.getName());
        
        if (Util.isVazioOrNulo(pavimentoRuas)) {
            throw new ActionServletException("atencao.naocadastrado",null, "Pavimento Rua");
        }
        
        //filtro fonte abastecimento
        filtroFonteAbastecimento.adicionarParametro(
        	new ParametroSimples(FiltroFonteAbastecimento.INDICADOR_USO,
        		ConstantesSistema.INDICADOR_USO_ATIVO));
        
        filtroFonteAbastecimento.setCampoOrderBy(FiltroFonteAbastecimento.DESCRICAO);
        
        fonteAbastecimentos = 
        	fachada.pesquisar(filtroFonteAbastecimento,FonteAbastecimento.class.getName());
        
        if (Util.isVazioOrNulo(fonteAbastecimentos)) {
            throw new ActionServletException("atencao.naocadastrado", null,"Fonte Abastecimento");
        }

        //TESTE PARA SABER SE A QUADRA TEM OU NÃO REDE DE AGUA
        Quadra quadraSessao = (Quadra) sessao.getAttribute("quadraCaracteristicas");
        String idQuadraFace = (String) inserirImovelClienteActionForm.get("idQuadraFace");
        
        /*
		 * Integração com o conceito de face da quadra
		 * 
		 * Caso a empresa utilize o conceito de face da quadra (PARM_ICQUADRAFACE = 1 da 
		 * tabela SISTEMA_PARAMETROS); os campos de INDICADOR_REDE_AGUA, INDICADOR_REDE_ESGOTO
		 * DISTRITO_OPERACIONAL e BACIA serão obtidos a partir da face.
		 */
		IntegracaoQuadraFaceHelper integracaoQuadraFace = null;
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
    	if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.SIM)){
    		
    		FiltroQuadraFace filtroQuadraFace = new FiltroQuadraFace();
    		filtroQuadraFace.adicionarParametro(new ParametroSimples(FiltroQuadraFace.ID, new Integer(idQuadraFace)));
    		
    		Collection colecaoQuadraFace = fachada.pesquisar(filtroQuadraFace, QuadraFace.class.getName());
    		
    		if (colecaoQuadraFace != null && !colecaoQuadraFace.isEmpty()) {
    			
    			QuadraFace quadraFaceNaBase = ((QuadraFace) ((List) colecaoQuadraFace).get(0));
    			
    			integracaoQuadraFace = new IntegracaoQuadraFaceHelper();
    			
    			integracaoQuadraFace.setIndicadorRedeAgua(quadraFaceNaBase.getIndicadorRedeAgua());
    			integracaoQuadraFace.setIndicadorRedeEsgoto(quadraFaceNaBase.getIndicadorRedeEsgoto());
    		}
    	}
    	else{
    		
    		FiltroQuadra filtroQuadra = new FiltroQuadra();
//    		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidade)));
//    		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, new Integer(idSetorComercial)));
    		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID, quadraSessao.getId()));
    		
    		Collection colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
    		
    		if (colecaoQuadra != null && !colecaoQuadra.isEmpty()) {
    			
    			Quadra quadraNaBase = ((Quadra) ((List) colecaoQuadra).get(0));
    			
    			integracaoQuadraFace = new IntegracaoQuadraFaceHelper();
    			
    			integracaoQuadraFace.setIndicadorRedeAgua(quadraNaBase.getIndicadorRedeAgua());
    			integracaoQuadraFace.setIndicadorRedeEsgoto(quadraNaBase.getIndicadorRedeEsgoto());
    		}
    	}
        
        if (integracaoQuadraFace.getIndicadorRedeAgua() != null && 
        	integracaoQuadraFace.getIndicadorRedeAgua().equals(Quadra.SEM_REDE)) {
            
        	filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE,
					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE_NAO));
        	
			filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO,
					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO_NAO));
        }
        
        if (integracaoQuadraFace.getIndicadorRedeAgua() != null && 
        		integracaoQuadraFace.getIndicadorRedeAgua().equals(Quadra.COM_REDE)) {
            
        	filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE,
					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE_SIM));
        	
			filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO,
					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO_NAO));
        	
        }
        
        if (integracaoQuadraFace.getIndicadorRedeAgua() != null && 
        		integracaoQuadraFace.getIndicadorRedeAgua().equals(Quadra.REDE_PARCIAL)) {
        	
        	filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO,
					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO_NAO));
        }
        
        filtroLigacaoAguaSituacao.adicionarParametro(
        	new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO,
        		ConstantesSistema.INDICADOR_USO_ATIVO));
        
        filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
        
        ligacaoAguaSituacaos = 
        	fachada.pesquisar(filtroLigacaoAguaSituacao,LigacaoAguaSituacao.class.getName());
        
        if (Util.isVazioOrNulo(ligacaoAguaSituacaos)) {
            throw new ActionServletException("atencao.naocadastrado");
        }
        
        //TESTE PARA SABER SE A QUADRA TEM OU NÃO REDE DE ESGOTO
        if (integracaoQuadraFace.getIndicadorRedeEsgoto() != null && 
        		integracaoQuadraFace.getIndicadorRedeEsgoto().equals(Quadra.SEM_REDE)) {
        	
        	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE,
					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE_NAO));
        	
        	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO,
					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO_NAO));
        	
        }
        
        if (integracaoQuadraFace.getIndicadorRedeEsgoto() != null && 
        	integracaoQuadraFace.getIndicadorRedeEsgoto().equals(Quadra.COM_REDE)) {
        	
        	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE,
					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE_SIM));
        	
        	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO,
					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO_NAO));
        	
        }
        
        if (integracaoQuadraFace.getIndicadorRedeEsgoto() != null && 
            integracaoQuadraFace.getIndicadorRedeEsgoto().equals(Quadra.REDE_PARCIAL)) {
        	
        	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO,
					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO_NAO));
        	
        }
        
        filtroLigacaoEsgotoSituacao.adicionarParametro(
        	new ParametroSimples(FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
        		ConstantesSistema.INDICADOR_USO_ATIVO));
        
        filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);
        ligacaoEsgotoSituacaos = 
        	fachada.pesquisar(filtroLigacaoEsgotoSituacao,LigacaoEsgotoSituacao.class.getName());
        
        if (Util.isVazioOrNulo(ligacaoEsgotoSituacaos)) {
            throw new ActionServletException("atencao.naocadastrado", null,"Ligacao Esgoto Situação");
        }

        filtroPocoTipo.adicionarParametro(
        	new ParametroSimples(
        		FiltroPocoTipo.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        
        filtroPocoTipo.setCampoOrderBy(FiltroPocoTipo.DESCRICAO);
        
        pocoTipos = 
        	fachada.pesquisar(filtroPocoTipo, PocoTipo.class.getName());
        
        if (Util.isVazioOrNulo(pocoTipos)) {
            throw new ActionServletException("atencao.naocadastrado", null,"Poço Tipo");
        }
        // AQUI 
        
        
        //-----------------------------------------------------------------------------------
        //Vivianne Sousa 23/05/2008
        //analista : Adriano
        //Verifica se o usuário tem permissão para incluir o perfil do imóvel corporativo
        
        //Ana Maria 10/09/2008
        //analista : Fabiola
        //Verifica se o usuário tem permissão para incluir o perfil do imóvel grande telemedido e corporativo telemed.
        
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
        boolean temPermissaoInserirImovelComPerfilCorporativo = 
            fachada.verificarPermissaoInserirImovelComPerfilCorporativo(usuario);
        
        if (temPermissaoInserirImovelComPerfilCorporativo){
            filtroImovelPerfil.adicionarParametro(
                    new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));
                
                filtroImovelPerfil.adicionarParametro(
                    new ParametroSimples(FiltroImovelPerfil.INDICADOR_GERACAO_AUTOMATICA,
                        ImovelPerfil.NAO));
                
                filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
                
                imovelPerfis = 
                    fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
        }else{
            imovelPerfis = 
                fachada.pesquisarImovelPerfilDiferenteCorporativo();
        }
        
        if (Util.isVazioOrNulo(imovelPerfis)) {
            throw new ActionServletException("atencao.naocadastrado", null,"Imovel Perfil");
        }
        // Indicador Nivel Instalação Esgoto, como default.
        if(inserirImovelClienteActionForm.get("indicadorNivelInstalacaoEsgoto") != null &&
        		inserirImovelClienteActionForm.get("indicadorNivelInstalacaoEsgoto").equals("")){

        		inserirImovelClienteActionForm.set("indicadorNivelInstalacaoEsgoto", "2");
        		}

        
        //----------------------------------------------------------------------------------
        
        filtroDespejo.adicionarParametro(
        	new ParametroSimples(FiltroDespejo.INDICADOR_USO,
        		ConstantesSistema.INDICADOR_USO_ATIVO));
        
        filtroDespejo.setCampoOrderBy(FiltroDespejo.DESCRICAO);
        
        despejos = fachada.pesquisar(filtroDespejo, Despejo.class.getName());
        if (Util.isVazioOrNulo(despejos)) {
            throw new ActionServletException("atencao.naocadastrado", null,"Despejo");
        }

        //Imovel Tipo Habitacao
        filtroImovelTipoHabitacao.adicionarParametro(
           	new ParametroSimples(FiltroImovelTipoHabitacao.INDICADOR_USO,
           		ConstantesSistema.INDICADOR_USO_ATIVO));
            
        filtroImovelTipoHabitacao.setCampoOrderBy(FiltroImovelTipoHabitacao.DESCRICAO);
        
        tipoHabitacao = 
        	fachada.pesquisar(filtroImovelTipoHabitacao, ImovelTipoHabitacao.class.getName());

        //Imovel Tipo Propriedade
        filtroImovelTipoPropriedade.adicionarParametro(
               	new ParametroSimples(FiltroImovelTipoPropriedade.INDICADOR_USO,
               		ConstantesSistema.INDICADOR_USO_ATIVO));
                
        filtroImovelTipoPropriedade.setCampoOrderBy(FiltroImovelTipoPropriedade.DESCRICAO);
        
        tipoPropriedade = 
        	fachada.pesquisar(filtroImovelTipoPropriedade, ImovelTipoPropriedade.class.getName());
        
        //Imovel Tipo Construcao
        filtroImovelTipoConstrucao.adicionarParametro(
               	new ParametroSimples(FiltroImovelTipoConstrucao.INDICADOR_USO,
               		ConstantesSistema.INDICADOR_USO_ATIVO));
                
        filtroImovelTipoConstrucao.setCampoOrderBy(FiltroImovelTipoConstrucao.DESCRICAO);
        
        tipoConstrucao = 
        	fachada.pesquisar(filtroImovelTipoConstrucao, ImovelTipoConstrucao.class.getName());

        //Imovel Tipo Cobertura
        filtroImovelTipoCobertura.adicionarParametro(
               	new ParametroSimples(FiltroImovelTipoCobertura.INDICADOR_USO,
               		ConstantesSistema.INDICADOR_USO_ATIVO));
                
        filtroImovelTipoCobertura.setCampoOrderBy(FiltroImovelTipoCobertura.DESCRICAO);
        
        tipoCobertura = 
        	fachada.pesquisar(filtroImovelTipoCobertura, ImovelTipoCobertura.class.getName());
        
        httpServletRequest.setAttribute("areaContruidaFaixas",areaContruidaFaixas);
        httpServletRequest.setAttribute("reservatorioVolumeFaixas",reservatorioVolumeFaixas);
        httpServletRequest.setAttribute("piscinaVolumeFaixas",piscinaVolumeFaixas);
        httpServletRequest.setAttribute("pavimetoCalcadas", pavimetoCalcadas);
        httpServletRequest.setAttribute("pavimentoRuas", pavimentoRuas);
        httpServletRequest.setAttribute("fonteAbastecimentos",fonteAbastecimentos);
        httpServletRequest.setAttribute("ligacaoAguaSituacaos",ligacaoAguaSituacaos);
        httpServletRequest.setAttribute("ligacaoEsgotoSituacaos",ligacaoEsgotoSituacaos);
        httpServletRequest.setAttribute("perfilImoveis", imovelPerfis);
        httpServletRequest.setAttribute("pocoTipos", pocoTipos);
        httpServletRequest.setAttribute("tipoDespejos", despejos);

        httpServletRequest.setAttribute("tipoHabitacao",tipoHabitacao);
        httpServletRequest.setAttribute("tipoPropriedade", tipoPropriedade);
        httpServletRequest.setAttribute("tipoConstrucao", tipoConstrucao);
        httpServletRequest.setAttribute("tipoCobertura", tipoCobertura);
        
        httpServletRequest.setAttribute("colecaoLigacaoEsgotoEsgotamento", colecaoLigacaoEsgotoEsgotamento);
        
        
        // Nathalia Santos 12/07/2011
        // Analista: Romulo 
        // Verifica se a empresa é a CAER, caso seja seta o atributo, caso não remove o atributo.
      
        if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
			SistemaParametro.EMPRESA_CAER)){
			httpServletRequest.setAttribute("apresentarIndicadorNivelInstalacaoEsgoto", true);
		} else { 
			httpServletRequest.removeAttribute("apresentarIndicadorNivelInstalacaoEsgoto");	
		}
        
        return retorno;
	}
}	