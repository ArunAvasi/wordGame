import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {
        try {
            String randomWord = getRandomWord();
            System.out.println("Random word: " + randomWord);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
    private static String getRandomWord() throws IOException, JSONException {
        String api_key = "169c08ab92msh0b7fe475c8aa506p19466fjsn1bbfbe410e1a";
        String host = "wordsapiv1.p.rapidapi.com";
        URL url = new URL("https://wordsapiv1.p.rapidapi.com/words/?random=true");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("X-RapidAPI-Key", api_key);
        urlConnection.setRequestProperty("X-RapidAPI-Host", host);

        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject obj = new JSONObject(response.toString());
        String word = obj.getString("word");
        String[] words = word.split(" ");
        return words[0];

    }


}
