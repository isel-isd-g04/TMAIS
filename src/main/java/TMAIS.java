import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

class Schedule extends TimerTask{
    @Override
    public void run() {
        System.out.println("\nTask Started");
        try {
            execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String cmioIP;
    private static String cmspIP;

    private void execute() throws IOException {
        IESDConfig cfg = new IESDConfig();
        cmioIP   = cfg.getProperty("cmioIP");
        cmspIP   = cfg.getProperty("cmspIP");

        if(cmioIP == null || cmspIP == null){
            cmioIP = "40.115.103.115:9090";
            cmspIP = "13.80.133.138:9090/api/v1/user";
        }

        System.out.println("CMIO IP: " + cmioIP);
        CMOIS();

        System.out.println("\nCMSP IP: "+cmspIP);
        CMPIS();
    }

    private static void CMPIS() throws IOException {

        //Define url
        String ip = "http://" + cmspIP;
        URL cmpisIP = new URL(ip);
        //Establish Connection
        HttpURLConnection con = (HttpURLConnection) cmpisIP.openConnection();
        //Method used
        con.setRequestMethod("GET");
        //Define TimeOuts
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        //Connection Status
        int status = con.getResponseCode();
        System.out.println("CMPIS Response Code: " + status + "\n");
        //Read content
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null){
            content.append(inputLine);
        }
        in.close();
        //Create JSONArray
        JSONArray jsonArray = new JSONArray(content.toString());
        //Create array of objects
        CMPISObject[] cmpisObject = new CMPISObject[jsonArray.length()];
        //Create each object
        for (int i = 0; i < jsonArray.length(); i++) {
            //Create JSONObject
            JSONObject cmpisData = jsonArray.getJSONObject(i);
            //Create Object
            cmpisObject[i] = new CMPISObject();
            //CMIO Data
            //cmpisObject[i].setCmioIBAN((cmpisData.getString("cmioIBAN")));
            //cmpisObject[i].setCmioNIF((cmpisData.getString("cmioNIF")));
            //cmpisObject[i].setCmioValue((cmpisData.getFloat("cmioValue")));
            //User data
            cmpisObject[i].setUserIBAN(cmpisData.getString("IBAN"));//userIBAN
            cmpisObject[i].setUserNIF(cmpisData.getString("Nif"));//userNIF
            cmpisObject[i].setUserAuthorized(cmpisData.getBoolean("Result"));//userAuthorized
            cmpisObject[i].setUserValue(cmpisData.getFloat("Value"));//userValue
            //Print CMIO Data
            //System.out.println("CMIO IBAN: " + cmpisObject[i].getCmioIBAN());
            //System.out.println("CMIO NIF: " + cmpisObject[i].getCmioNIF());
            //System.out.println("CMIO Value: " + cmpisObject[i].getCmioValue());
            //Print User Data
            System.out.println("User IBAN: " + cmpisObject[i].getUserIBAN());
            System.out.println("User NIF: " + cmpisObject[i].getUserNIF());
            System.out.println("User Authorized: " + cmpisObject[i].getUserAuthorized());
            System.out.println("User Value: " + cmpisObject[i].getUserValue()+"\n");
        }
        //Disconnect
        con.disconnect();
    }

    private static void CMOIS() throws IOException {
        //Define url
        String ip = "http://" + cmioIP;
        URL cmois = new URL(ip);
        //Establish Connection
        HttpURLConnection con = (HttpURLConnection) cmois.openConnection();
        //Method used
        con.setRequestMethod("GET");
        //Define TimeOuts
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        //Connection Status
        int status = con.getResponseCode();
        System.out.println("CMOIS Response Code: " + status);
        //Read content
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null){
            content.append(inputLine);
        }
        in.close();
        //Create JSONArray
        JSONArray jsonArray = new JSONArray(content.toString());
        //Create array of objects
        CMOISObject[] cmoisObject = new CMOISObject[jsonArray.length()];
        //Create each object
        for (int i = 0; i < jsonArray.length(); i++) {
            //Create JSONObject
            JSONObject cmoisData = jsonArray.getJSONObject(i);
            //Create Object
            cmoisObject[i] = new CMOISObject();
            //DATA
            cmoisObject[i].setCMSP(cmoisData.getString("IBAN"));
            cmoisObject[i].setUserNIF(cmoisData.getString("Nif"));
            cmoisObject[i].setUserAuthorized(cmoisData.getBoolean("Result"));
            cmoisObject[i].setUserValue(cmoisData.getFloat("Value"));
            //Print Data
            System.out.println("CMSP IBAN: " + cmoisObject[i].getCMSP());
            System.out.println("User NIF: " + cmoisObject[i].getUserNIF());
            System.out.println("User Authorized: " + cmoisObject[i].getUserAuthorized());
            System.out.println("User Value: " + cmoisObject[i].getUserValue()+"\n");
        }

        //Disconnect
        con.disconnect();
    }
}

public class TMAIS {

    public static void main(String[] args)
    {
        Schedule schedule = new Schedule();
        Timer timer = new Timer();
        timer.schedule(schedule,0, 20000);

    }

}