package gcom.cadastro.cliente;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.endereco.FiltroLogradouroTipo;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.fachada.Fachada;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;

public abstract class ClienteBuilder {

	protected AtualizacaoCadastralImovel atualizacaoCadastralImovel;
	protected IClienteAtualizacaoCadastral clienteTxt = null;
	
	public final static String USUARIO = "Usuario";
	public final static String PROPRIETARIO = "Proprietario";
	public final static String RESPONSAVEL = "Responsavel";
	
	public ClienteBuilder(AtualizacaoCadastralImovel atualizacaoCadastralImovel){
		this.atualizacaoCadastralImovel = atualizacaoCadastralImovel;
		clienteTxt = new ClienteAtualizacaoCadastral();
	}
	
	protected void buildCliente(String tipoCliente, Short clienteRelacaoTipo) {
		clienteTxt.setNome(atualizacaoCadastralImovel.getLinhaCliente("nome" + tipoCliente));
		
		String cnpjCpf = atualizacaoCadastralImovel.getLinhaCliente("cnpjCpf" + tipoCliente);
		if (cnpjCpf.length() == 11) {
			clienteTxt.setCpf(cnpjCpf);
		} else if (cnpjCpf.length() == 14) {
			clienteTxt.setCnpj(cnpjCpf);
		}
		
		clienteTxt.setRg(atualizacaoCadastralImovel.getLinhaCliente("rg" + tipoCliente));
		
		clienteTxt.setUnidadeFederacao(getUnidadeFederecao(atualizacaoCadastralImovel.getLinhaCliente("ufRg" + tipoCliente)));
		
		String campo = atualizacaoCadastralImovel.getLinhaCliente("sexo" + tipoCliente);
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			int idSexo = Integer.parseInt(campo);
			
			if (idSexo == 1 || idSexo == 2) {
				PessoaSexo sexo = new PessoaSexo(idSexo);
				clienteTxt.setPessoaSexo(sexo);
			}
		}
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("tipoPessoa" + tipoCliente);
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setIdClienteTipo(Integer.parseInt(campo));
		}
		
		clienteTxt.setEmail(atualizacaoCadastralImovel.getLinhaCliente("email" + tipoCliente));
		clienteTxt.setIdCliente(new Integer(atualizacaoCadastralImovel.getLinhaCliente("matricula" + tipoCliente)));
		
		clienteTxt.setIdClienteRelacaoTipo(new Integer(clienteRelacaoTipo));
		
		campo = atualizacaoCadastralImovel.getLinhaCliente("matriculaImovelCliente");
		if (StringUtils.isNotEmpty(campo) && StringUtils.isNumeric(campo)){
			clienteTxt.setIdImovel(Integer.parseInt(campo));
		}
	}
	
	private UnidadeFederacao getUnidadeFederecao(String uf) {
		UnidadeFederacao retorno = null;
		
		FiltroUnidadeFederacao filtroUF = new FiltroUnidadeFederacao();
		filtroUF.adicionarParametro(new ParametroSimples(FiltroUnidadeFederacao.SIGLA, uf));
		Collection pesquisa = Fachada.getInstancia().pesquisar(filtroUF, UnidadeFederacao.class.getName());
		if (pesquisa != null && !pesquisa.isEmpty()) {
			retorno = (UnidadeFederacao) Util.retonarObjetoDeColecao(pesquisa);
		}
		
		return retorno;
	}

	public abstract IClienteAtualizacaoCadastral buildCliente(Short clienteRelacaoTipo);
}
