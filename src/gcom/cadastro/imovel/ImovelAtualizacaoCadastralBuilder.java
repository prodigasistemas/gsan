package gcom.cadastro.imovel;

import java.math.BigDecimal;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.endereco.FiltroLogradouroTipo;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.fachada.Fachada;
import gcom.util.filtro.ParametroSimples;

public class ImovelAtualizacaoCadastralBuilder {

	private int matricula;
	private ImovelAtualizacaoCadastral imovelAtualizacaoCadastral;

	private AtualizacaoCadastralImovel atualizacaoCadastralImovel;
	
	public ImovelAtualizacaoCadastralBuilder(int matricula, AtualizacaoCadastralImovel atualizacaoCadastralImovel){
		this.matricula = matricula;
		this.imovelAtualizacaoCadastral = new ImovelAtualizacaoCadastral();
		this.atualizacaoCadastralImovel = atualizacaoCadastralImovel;
		
		buildImovel();
	}

	public ImovelAtualizacaoCadastral getImovelAtualizacaoCadastral() {
		return imovelAtualizacaoCadastral;
	}
	
	public void buildImovel(){
		// Linha 2
		this.getImovelAtualizacaoCadastral().setIdImovel(matricula);
		this.getImovelAtualizacaoCadastral().setNumeroImovel(atualizacaoCadastralImovel.getLinhaImovel("numeroImovel"));
		this.getImovelAtualizacaoCadastral().setComplementoEndereco(atualizacaoCadastralImovel.getLinhaImovel("complementoImovel"));
		this.getImovelAtualizacaoCadastral().setIdFonteAbastecimento(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("fonteAbastecimento")));
		this.getImovelAtualizacaoCadastral().setNumeroIptu(atualizacaoCadastralImovel.getLinhaImovel("numeroIPTU") == null ? null : new BigDecimal(atualizacaoCadastralImovel.getLinhaImovel("numeroIPTU")));
		this.getImovelAtualizacaoCadastral().setNumeroContratoEnergia(atualizacaoCadastralImovel.getLinhaImovel("numeroCelpa").equals("") ? null : Long.parseLong(atualizacaoCadastralImovel.getLinhaImovel("numeroCelpa")));
		this.getImovelAtualizacaoCadastral().setIdLogradouroTipo(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("idTipoLogradouroImovel")));
		this.getImovelAtualizacaoCadastral().setDsLogradouroTipo(getDescricaoLogradouro(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("idTipoLogradouroImovel"))));
		this.getImovelAtualizacaoCadastral().setDescricaoLogradouro(atualizacaoCadastralImovel.getLinhaImovel("logradouroImovel"));
		this.getImovelAtualizacaoCadastral().setCodigoCep(Integer.parseInt(atualizacaoCadastralImovel.getLinhaImovel("cep")));
		this.getImovelAtualizacaoCadastral().setNomeBairro(atualizacaoCadastralImovel.getLinhaImovel("bairro"));
		this.getImovelAtualizacaoCadastral().setNomeMunicipio(atualizacaoCadastralImovel.getLinhaImovel("municipio"));
		this.getImovelAtualizacaoCadastral().setNumeroPontosUtilizacao(Short.parseShort(atualizacaoCadastralImovel.getLinhaImovel("numeroPontosUteis")));
		this.getImovelAtualizacaoCadastral().setNumeroMorador(Short.parseShort(atualizacaoCadastralImovel.getLinhaImovel("numeroOcupantes")));

		// Linha 4
		this.getImovelAtualizacaoCadastral().setIdLigacaoAguaSituacao(Integer.parseInt(atualizacaoCadastralImovel.getLinhaServicos("ligacaoAguaSituacao")));
		this.getImovelAtualizacaoCadastral().setIdLigacaoEsgotoSituacao(Integer.parseInt(atualizacaoCadastralImovel.getLinhaServicos("ligacaoEsgotoSituacao")));
		this.getImovelAtualizacaoCadastral().setIdLocalInstalacaoRamal(atualizacaoCadastralImovel.getLinhaServicos("localInstalacaoRamal").equals("") ? null : Integer.parseInt(atualizacaoCadastralImovel.getLinhaServicos("localInstalacaoRamal")));

		// Linha 5
		if (atualizacaoCadastralImovel.getLinhaMedidor().size() > 0) {
			this.getImovelAtualizacaoCadastral().setNumeroHidrometro(atualizacaoCadastralImovel.getLinhaMedidor("numeroHidrometro"));
			this.getImovelAtualizacaoCadastral().setIdMarcaHidrometro(atualizacaoCadastralImovel.getLinhaMedidor("marcaHidrometro").equals("") ? 0 : Integer.parseInt(atualizacaoCadastralImovel.getLinhaMedidor("marcaHidrometro")));
			this.getImovelAtualizacaoCadastral().setIdProtecaoHidrometro(atualizacaoCadastralImovel.getLinhaMedidor("tipoCaixaProtecaoHidrometro").equals("") ? 0 : Integer.parseInt(atualizacaoCadastralImovel.getLinhaMedidor("tipoCaixaProtecaoHidrometro")));
			this.getImovelAtualizacaoCadastral().setIdCapacidadeHidrometro(atualizacaoCadastralImovel.getLinhaMedidor("capacidadeHidrometro").equals("") ? 0 : Integer.parseInt(atualizacaoCadastralImovel.getLinhaMedidor("capacidadeHidrometro")));
		}

		// Linha 6
		this.getImovelAtualizacaoCadastral().setIdCadastroOcorrencia(Integer.parseInt(atualizacaoCadastralImovel.getLinhaAnormalidade("codigoAnormalidade")));
		this.getImovelAtualizacaoCadastral().setDescricaoOutrasInformacoes(atualizacaoCadastralImovel.getLinhaAnormalidade("comentario").trim());
		this.getImovelAtualizacaoCadastral().setCoordenadaY(atualizacaoCadastralImovel.getLinhaAnormalidade("latitude"));
		this.getImovelAtualizacaoCadastral().setCoordenadaX(atualizacaoCadastralImovel.getLinhaAnormalidade("longitude"));
	}
	
	public String getDescricaoLogradouro(int idTipoLogradouro) {
		FiltroLogradouroTipo filtro = new FiltroLogradouroTipo();
		filtro.adicionarParametro(new ParametroSimples(FiltroLogradouroTipo.ID, idTipoLogradouro));
		LogradouroTipo logradouroTipo = (LogradouroTipo) (Fachada.getInstancia().pesquisar(filtro, LogradouroTipo.class.getName()).iterator().next());

		return logradouroTipo.getDescricao();
	}
}
