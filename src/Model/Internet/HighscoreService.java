package Model.Internet;

import Model.UserScore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HighscoreService {

    private String url = "https://tetris-26817.firebaseio.com/users.json";
    private static ObjectMapper om = new ObjectMapper();

    public static List<UserScore> GetHighScores() {


        try {
            List<UserScore> scores = om.readValue(new URL("https://tetris-26817.firebaseio.com/users.json"), new TypeReference<List<UserScore>>() {
            });
            return scores == null ? new ArrayList<>() : scores;

        } catch (IOException e) {
            System.out.println("no connection");
        }

        return null;
    }

    public static boolean AddHighScore(UserScore score) {
        List<UserScore> scores = GetHighScores();

        if (scores == null)
            return false;


        scores.add(score);
        scores.removeIf(Objects::isNull);
        scores.sort((x, y) -> Integer.compare(y.score, x.score));

        return uploadScores(scores);
    }

    private static boolean uploadScores(List<UserScore> scores) {
        try {
            String json = om.writeValueAsString(scores);
            PutRest(json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean Purge(){

        List<UserScore> scores = new ArrayList<>();

        return uploadScores(scores);
    }

    private static void PutRest(String input) throws IOException {
        URL url = new URL("https://tetris-26817.firebaseio.com/users.json");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");


        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();

        if (conn.getResponseCode() != HttpsURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        conn.disconnect();

    }

}
