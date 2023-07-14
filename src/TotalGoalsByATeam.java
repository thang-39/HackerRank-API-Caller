import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TotalGoalsByATeam {

    public static void main(String[] args) {
        System.out.println(getTotalGoals("Barcelona",2011));
    }
    public static int getTotalGoals(String team, int year) {

        try {
            return getTotalGoalsHomeTeam(team,year) + getTotalGoalsVisitedTeam(team, year);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static int getTotalGoalsHomeTeam(String team, int year) throws IOException {
        int totalGoalsHome = 0;
        int startPage = 1;
        int totalPages = Integer.MAX_VALUE;

        String response;

        String team1goals = "team1goals";

        while (startPage <= totalPages) {
            URL url = new URL("https://jsonmock.hackerrank.com/api/football_matches?year="+ year +"&team1=" + team + "&page=" + startPage);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader dataIn = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((response = dataIn.readLine()) != null) {
                totalPages = Integer.parseInt(response.substring(48,49));
                System.out.println(totalPages);

                int index = 0;
                while (index != -1) {
                    index = response.indexOf(team1goals,index);
                    if (index != -1) {
                        int comma = response.indexOf(",",index);
//                        System.out.println(response.substring(index + team1goals.length() + 3, comma-1));
                        totalGoalsHome += Integer.parseInt(response.substring(index + team1goals.length() + 3, comma-1));
                        index++;
                    }
                }
            }
            dataIn.close();
            startPage++;
        }
        return totalGoalsHome;
    }

    public static int getTotalGoalsVisitedTeam(String team, int year) throws IOException {
        int totalGoalsHome = 0;
        int startPage = 1;
        int totalPages = Integer.MAX_VALUE;

        String response;

        String team2goals = "team2goals";

        while (startPage <= totalPages) {
            URL url = new URL("https://jsonmock.hackerrank.com/api/football_matches?year="+ year +"&team2=" + team + "&page=" + startPage);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader dataIn = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((response = dataIn.readLine()) != null) {
                totalPages = Integer.parseInt(response.substring(48,49));
                System.out.println(totalPages);

                int index = 0;
                while (index != -1) {
                    index = response.indexOf(team2goals,index);
                    if (index != -1) {
                        int comma = response.indexOf("}",index);
//                        System.out.println(response.substring(index + team2goals.length() + 3, comma-1));
                        totalGoalsHome += Integer.parseInt(response.substring(index + team2goals.length() + 3, comma-1));
                        index++;
                    }
                }
            }
            dataIn.close();
            startPage++;
        }
        return totalGoalsHome;
    }

}
