package service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import domain.DraftPick;

@Stateless
@LocalBean
public class DraftHTTPImport {

	private String accessToken = "e4f2b521-8ad1-4eff-a754-e357fd9adb12";

	public List<DraftPick> importDraftClass(int importYear) {
		String url = "https://erikberg.com/nba/draft.json?season=" + importYear;
		String charset = StandardCharsets.UTF_8.name();
		String userAgent = "NBAStats/1.0 (http://nbastats.com/contact.html)";

		List<DraftPick> pickList = new ArrayList<DraftPick>();

		URLConnection connection;
		try {
			connection = new URL(url).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Authorization", "Bearer "
					+ accessToken);
			connection.setRequestProperty("User-agent", userAgent);

			InputStream response = connection.getInputStream();

			try (Scanner scanner = new Scanner(response)) {
				String responseBody = scanner.useDelimiter("\\A").next();

				JsonParser jsonParser = new JsonParser();
				JsonElement ja = jsonParser.parse(responseBody);

				for (JsonElement je : ja.getAsJsonArray()) {
					JsonObject jo = je.getAsJsonObject();

					JsonElement season = jo.get("season");
					JsonElement round = jo.get("round");
					JsonElement pick = jo.get("pick");

					JsonObject player = jo.getAsJsonObject("player");
					JsonElement playerName = player.get("display_name");

					JsonObject team = jo.getAsJsonObject("team");
					JsonElement teamName = team.get("full_name");

					DraftPick draftPick = new DraftPick(season.getAsInt(),
							round.getAsInt(), pick.getAsInt(),
							teamName.getAsString(), playerName.getAsString());

					pickList.add(draftPick);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pickList;
	}

}
