import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetMostActiveUsers {

    public static List<String> getUsernames(int threshold) throws IOException {

        List<String> result = new ArrayList<>();
        String response;
        int startPage = 1;
        int totalPages = Integer.MAX_VALUE;
        String subCount = "submission_count";
        String user = "username";
        int currentUserData;
        while (startPage <= totalPages){

            URL url = new URL("https://jsonmock.hackerrank.com/api/article_users?page="+startPage);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((response = in.readLine()) != null) {
                totalPages = Integer.parseInt(response.substring(49,50));
//                System.out.println(totalPages);
                List<String> userTitles = getUserTitles(response, user);
                int count = 0;

                int index = 0;
                while(index != -1){
                    index = response.indexOf(subCount, index);
                    if (index != -1) {
                        int cc = response.indexOf(",",index);
                        currentUserData = Integer.parseInt(response.substring(index + subCount.length() + 2, cc));
                        if (currentUserData >= threshold){
                            result.add(userTitles.get(count));
                        }
                        index++;
                        count++;
                    }
                }

            }
            in.close();
            startPage++;
        }

        return result;

    }

    public static List<String> getUserTitles(String response, String user){
        List<String> result = new ArrayList<>();
        int index = 0;
        while(index != -1){
            index = response.indexOf(user, index);
            if (index != -1) {
                int cc = response.indexOf(",",index);
                result.add(response.substring(index + user.length() + 2, cc));
                index++;
            }
        }
        return result;
    }

    private static void printActiveUsers(List<String> activeUserList){
        for (String title: activeUserList){
            System.out.println(title);
        }
    }
    public static void main(String[] args) throws IOException {
        printActiveUsers(getUsernames(10));
    }
}