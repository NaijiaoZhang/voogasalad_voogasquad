package statswrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class Wrapper {
	
	private final String baseURL = "http://voogasquad.herokuapp.com/";
	private String username;
	private static final Wrapper instance = new Wrapper();

	
	public static Wrapper getInstance() {
		return instance;
	}
	
	public void addStates(String endpoint) {
		Client client = ClientBuilder.newClient();
		Response response = client.target(baseURL + endpoint)
				.request(MediaType.TEXT_PLAIN_TYPE).get();
	}
	
	public String login(String username, String password) throws IOException{
		this.username = username;
		String endpoint = "login";
		URL url = new URL(baseURL + endpoint);
		String json = "{\"username\": \"" + username + "\",\"password\":\""+password+"\"}";
		String response = executeRequest(url, json, true);
		return response;
	}
	
	public String createAccount(String username, String password) throws IOException{
		String endpoint = "createaccount";
		URL url = new URL(baseURL + endpoint);
		String json = "{\"username\": \"" + username + "\",\"password\":\""+password+"\"}";
		String response = executeRequest(url, json, true);
		return response;
	}
	
	public void createGame(String xmlData) throws IOException{
		String endpoint = "creategame";
		//String test = "TEST GAME";
		URL url = new URL(baseURL + endpoint);
		String newXML = xmlData.replaceAll("\"", "");
		String json = "{\"type\":\"game\",\"game\": \"" + newXML + "\"}";
		String response = executeRequest(url, json, true);
	}
	
	/*
	 * Record for every new level
	 */
	public void recordGameScores(String gold, String lives, String level) throws IOException{
		String endpoint = "recordscore";
		URL url = new URL(baseURL + endpoint);
		String json = "{\"gold\": \"" + gold + "\",\"lives\":\""+lives+"\",\"level\":\""+level+"\"}";
		String response = executeRequest(url, json, true);
	}
	
	/*
	 * Record for every new level
	 */
	public void logScore(String score) throws IOException{
		String endpoint = "logScore";
		URL url = new URL(baseURL + endpoint);
		String json = "{\"score\": \"" + score + "\"}";
		String response = executeRequest(url, json, true);
	}
	
	/*
	 * Updated game score within the level
	 */
	public void updateGameScores(String field, String level, String value) throws IOException{
		//System.out.println("Update game scores: "+field+": "+value);
		String endpoint = "updatescore";
		URL url = new URL(baseURL + endpoint);
		String json = "{\"updated_field\": \"" + field + "\",\"value\":\""+value+"\",\"level\":[\""+level+"\"]}";
		//System.out.println("UPDATE JSON: "+json);
		String response = executeRequest(url, json, true);
		//System.out.println(response);
	}
	
	public void logEndScore(String gold, String lives, String level) throws IOException{
		String endpoint = "log_end_score";
		URL url = new URL(baseURL + endpoint);
		String json = "{\"gold\": \"" + gold + "\",\"lives\":\""+lives+"\",\"level\":\""+level+"\"}";
		String response = executeRequest(url, json, true);
	}
	
	private String executeRequest(URL url, String json, boolean post) throws IOException{
		URLConnection urlConnection = url.openConnection();
		urlConnection.setDoOutput(post); //false if post
		urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		urlConnection.connect();
		OutputStream outputStream = urlConnection.getOutputStream();
		outputStream.write((json).getBytes("UTF-8"));
		outputStream.flush();
		//InputStream inputStream = urlConnection.getInputStream();
		
		//Get Response
        InputStream in = urlConnection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(in));
        String line;
        StringBuffer response = new StringBuffer();
        while((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        ////System.out.println("Read attempt: " + response.toString());
        return response.toString();
	}
}
