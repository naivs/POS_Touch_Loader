package experiments;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 *
 * @author Ivan
 */
public class GitHubQuery {

    public static void main(String[] args) throws MalformedURLException, ProtocolException, IOException {

        ArrayList<Project> projects = new ArrayList();

        String[] query = {"https://api.github.com/search/repositories?q=tetris&page=1&per_page=100",
            "https://api.github.com/search/repositories?q=tetris&page=2&per_page=100",
            "https://api.github.com/search/repositories?q=tetris&page=3&per_page=100",
            "https://api.github.com/search/repositories?q=tetris&page=4&per_page=100"};

        JsonParser parser = new JsonParser();

        for (String qu : query) {

            String line = readData(connectURL(qu));
            JsonObject root = parser.parse(line).getAsJsonObject();
            JsonArray items = root.getAsJsonArray("items");

            for (JsonElement element : items) {

                if(projects.size() == 320) break;
                
                JsonObject obj = element.getAsJsonObject();

                String name = obj.getAsJsonPrimitive("name").getAsString();

                String lang = "null";
                try {
                    lang = obj.getAsJsonPrimitive("language").getAsString();
                } catch (ClassCastException e) {
                    //System.err.println("project " + name + " has bad language...");
                }

                projects.add(new Project(
                        name,
                        obj.getAsJsonPrimitive("forks_count").getAsInt(),
                        obj.getAsJsonPrimitive("watchers_count").getAsInt(),
                        obj.getAsJsonPrimitive("open_issues_count").getAsInt(),
                        obj.getAsJsonPrimitive("id").getAsInt(),
                        obj.getAsJsonPrimitive("size").getAsInt(),
                        lang,
                        obj.getAsJsonPrimitive("updated_at").getAsString()));
            }
        }

        System.out.println("Projects count: " + projects.size());
        
        for(int i = 0; i < projects.size(); i++) {
            for(int j = projects.size() - 1; j > i; j--) {
                if(projects.get(i).rating < projects.get(j).rating) {
                    Collections.swap(projects, i, j);
                }
            }
        }
        
        for (int i = 0; i < 10; i++) {
            System.out.println(projects.get(i));
        }
    }

    private static InputStream connectURL(String url) throws MalformedURLException, IOException {

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");

        return conn.getInputStream();
    }

    private static String readData(InputStream inputStream) {

        try (BufferedInputStream bis = new BufferedInputStream(inputStream); ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = bis.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {

        } catch (IOException e) {

        }

        return null;
    }

    private static class Project {

        String name;
        float rating = 0f;

        int forks, watchers, openIssues, id, size;
        String language;
        long updatedAt;

        public Project(String name,
                int forks,
                int watchers,
                int openIssues,
                int id,
                int size,
                String language,
                String updatedAt) {

            this.name = name;
            this.forks = forks;
            this.id = id;
            this.language = language;
            this.watchers = watchers;
            this.openIssues = openIssues;
            this.size = size;

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                Date date = sdf.parse(updatedAt);
                this.updatedAt = date.getTime();
            } catch (ParseException e) {
                this.updatedAt = 0L;
                System.err.println("Exception while Data parsing. " + e.getMessage());
            }

            calcRating();
        }

        private void calcRating() {

            /*
            * 1. Количество форков (forks: +3 за каждый)
            * 2. Количество людей следящих за проектом (watchers: +1 за каждого)
            * 3. Количество открытых дефектов (open_issues: -1 за каждый)
            * 4. ID владельца проекта - нечётный (id: -30)
            * 5. Размер репозитория (size: +0.1 за каждый мегабайт)
            * 6. Язык программирования Java (language: +5)
            7. Не обновлялся больше двух лет (updated_at: -20)
             */
            rating = forks * 3 + watchers - openIssues - (id & 1 * 30);

            if (language.equals("\"Java\"")) {
                rating += 5;
            }

            rating += size / 1024;

            Calendar projectTime = Calendar.getInstance();
            projectTime.setTimeInMillis(updatedAt);
            projectTime.add(Calendar.YEAR, -1);

            if (Calendar.getInstance().getTimeInMillis() - updatedAt < projectTime.getTimeInMillis()) {
                rating -= 20;
            }
        }

        public float getRating() {
            return rating;
        }

        @Override
        public String toString() {
            return name + " (" + size + " kb)";
        }
    }
}
