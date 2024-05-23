package br.com.usinasantafe.pom.util.conHttp;

import br.com.usinasantafe.pom.POMContext;

public class UrlsConexaoHttp {

    public static String versao = "versao_" + POMContext.versaoWS.replace(".", "_");

//    public static String url = "https://www.usinasantafe.com.br/pomdev/view/";
//    public static String url = "https://www.usinasantafe.com.br/pomqa/view/";
    public static String url = "https://www.usinasantafe.com.br/pomprod/" + versao + "/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pom.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pom.util.conHttp.UrlsConexaoHttp";

    public static String AtividadeBean = url + "atividade.php";
    public static String ComponenteBean = url + "componente.php";
    public static String FuncBean = url + "funcionario.php";
    public static String ItemCheckListBean = url + "itemchecklist.php";
    public static String ItemOSMecanBean = url + "itemosmecan.php";
    public static String OSBean = url + "os.php";
    public static String ROSAtivBean = url + "rosativ.php";
    public static String ServicoBean = url + "servico.php";
    public static String TurnoBean = url + "turno.php";

    public UrlsConexaoHttp() {
    }

    public String getsInserirCheckList() {
        return url + "inserirchecklist.php";
    }

    public String getsInsertBolAbertoMMFert() {
        return url + "inserirbolabertommfert.php";
    }

    public String getsInsertBolFechadoMMFert() {
        return url + "inserirbolfechadommfert.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        switch (classe) {
            case "Equip":
                retorno = url + "equip.php";
                break;
            case "Atualiza":
                retorno = url + "atualaplic.php";
                break;
            case "Atividade":
                retorno = url + "pesqativ.php";
                break;
            case "CheckList":
                retorno = url + "atualchecklist.php";
                break;
            case "OS":
                retorno = url + "pesqos.php";
                break;
            case "OSMecan":
                retorno = url + "pesqosmecan.php";
                break;
        }
        return retorno;
    }

}
