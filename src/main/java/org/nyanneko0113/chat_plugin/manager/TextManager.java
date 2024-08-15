package org.nyanneko0113.chat_plugin.manager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.ibm.icu.text.Transliterator;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TextManager {

    //テキストを漢字に変換
    public static String textTOkanji(String text) {
        try {
            text = text.replaceAll("nn", "n");
            Transliterator transliterator = Transliterator.getInstance("Latin-Hiragana");

            String text_before = transliterator.transliterate(text);
            String text_after = text_before.replaceAll("　", "").replaceAll(" ", "") + ",";

            JsonArray json = getJson(text_after);
            return json.get(0).getAsJsonArray().get(1).getAsJsonArray().get(0).getAsString() + ")";
        }
        catch (IOException ex) {
            Bukkit.getLogger().warning("変換中にエラーが発生しました：" + ex);
            return text;
        }
    }

    private static JsonArray getJson(String text) throws IOException {
        URL url = new URL("http://www.google.com/transliterate?langpair=ja-Hira|ja&text=" + text);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder content = new StringBuilder();

        String inputLine;
        while((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        con.disconnect();
        return  (new Gson()).fromJson(content.toString(), JsonArray.class);
    }
}
