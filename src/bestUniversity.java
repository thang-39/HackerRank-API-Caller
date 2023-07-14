import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class bestUniversity {

    public static void main(String[] args) throws IOException {
        System.out.println(bestUniversityByCountry("India"));
    }
    public static String bestUniversityByCountry(String country) throws IOException {
        List<String> rank = RankList();
        List<String> university = universityList();
        List<String> countries = countryList();

        return bestUniversity(country, rank, university,countries);



    }
    public static String bestUniversity(String country, List<String> rank, List<String> university,
                                        List<String> countries) {
        String bestUniversity = "";
        int maxRank = Integer.MAX_VALUE;

        for (int i = 0; i < rank.size(); i++) {
            if (countries.get(i).equalsIgnoreCase(country)) {
                int currentRank = Integer.parseInt(rank.get(i));
                if (currentRank < maxRank) {
                    maxRank = currentRank;
                    bestUniversity = university.get(i);
                }
            }
        }

        return bestUniversity;
    }

    public static List<String> RankList() throws IOException {

        int startPage = 1;
        int totalPages = 20;

        String response;

        String rank_display = "rank_display";

        List<String> universityList = new ArrayList<>();

        while (startPage <= totalPages) {
            URL url = new URL("https://jsonmock.hackerrank.com/api/universities?page=" + startPage);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader dataIn = new BufferedReader(new InputStreamReader(connection.getInputStream()));


            while ((response = dataIn.readLine()) != null) {

                int index = 0;
                while (index != -1) {
                    index = response.indexOf(rank_display,index);
                    if (index != -1) {
                        int comma = response.indexOf(",",index);
                        universityList.add(response.substring(index + rank_display.length() + 3, comma-1));
                        index++;
                    }
                }
            }
            dataIn.close();
            startPage++;
        }
        return universityList;

    }

    public static List<String> universityList() throws IOException {

        int startPage = 1;
        int totalPages = 20;

        String response;

        String university = "university";

        List<String> universityList = new ArrayList<>();

        while (startPage <= totalPages) {
            URL url = new URL("https://jsonmock.hackerrank.com/api/universities?page=" + startPage);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader dataIn = new BufferedReader(new InputStreamReader(connection.getInputStream()));


            while ((response = dataIn.readLine()) != null) {

                int index = 0;
                while (index != -1) {
                    index = response.indexOf(university,index);
                    if (index != -1) {
                        int comma = response.indexOf(",",index);
                        universityList.add(response.substring(index + university.length() + 3, comma-1));
                        index++;
                    }
                }
            }
            dataIn.close();
            startPage++;
        }
        return universityList;

    }
    public static List<String> countryList() throws IOException {

        int startPage = 1;
        int totalPages = 20;

        String response;

        String country = "country";

        List<String> universityList = new ArrayList<>();

        while (startPage <= totalPages) {
            URL url = new URL("https://jsonmock.hackerrank.com/api/universities?page=" + startPage);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader dataIn = new BufferedReader(new InputStreamReader(connection.getInputStream()));


            while ((response = dataIn.readLine()) != null) {

                int index = 0;
                while (index != -1) {
                    index = response.indexOf(country,index);
                    if (index != -1) {
                        int comma = response.indexOf(",",index);
                        universityList.add(response.substring(index + country.length() + 3, comma-1));
                        index++;
                    }
                }
            }
            dataIn.close();
            startPage++;
        }
        return universityList;

    }
}
