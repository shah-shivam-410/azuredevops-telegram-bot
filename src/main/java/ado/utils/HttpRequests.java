package ado.utils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpRequests {

	public static final String BASE_URI = "https://dev.azure.com";

	public String getAuthHeader() throws IOException {
		Properties props = ConfigReader.loadProps();
		final String auth = props.getProperty("USERNAME") + ":" + props.getProperty("TOKEN");
		final byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
		final String authHeader = "Basic " + new String(encodedAuth);
		return authHeader;
	}

	public String get(URI uri) throws Exception {
		String resultContent = null;
		HttpGet httpGet = new HttpGet(uri);
		httpGet.setHeader(HttpHeaders.AUTHORIZATION, getAuthHeader());

		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					resultContent = EntityUtils.toString(entity);
				}
				else {
					throw new Exception("Error response in GET request: " + response.getStatusLine().getStatusCode());
				}
				// System.out.println(resultContent);
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return resultContent;
	}

}
