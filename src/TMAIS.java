import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TMAIS {

    public static void main(String[] args) throws IOException {

        //Criar URL
        URL url = new URL ("https://www.google.com");
        //Ligar ao URL
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        //Definir Pedido
        con.setRequestMethod("GET");

        //Definir TimeOuts
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        //Obter a Resposta
        int status = con.getResponseCode();
        System.out.println("Response Code: "+ status);

        //Ler e Interpretar a Resposta
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null){
            content.append(inputLine);
        }
        in.close();

        //Escrever a resposta
        System.out.println(content.toString());

        //Desligar a Ligação
        con.disconnect();

    }

}
