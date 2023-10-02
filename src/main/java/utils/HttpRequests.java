package utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHeaders;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import constants.Proplist;

public class HttpRequests{

	public String getAuthHeader() {
		Properties props = ConfigReader.loadProps();
		final String auth = props.getProperty(Proplist.ADO_USERNAME) + ":" + props.getProperty(Proplist.ADO_TOKEN);
		final byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
		return "Basic " + new String(encodedAuth);
	}

	public String get(URI uri) {
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
					throw new HttpException("Error response in GET request: " + response.getStatusLine().getStatusCode());
				}
			}
		} catch (IOException | ParseException | HttpException e) {
			e.printStackTrace();
		}
		return resultContent;
	}
	
	public String post(URI uri, String payload) {
		String resultContent = null;
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setHeader(HttpHeaders.AUTHORIZATION, getAuthHeader());
		httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-type", "application/json");
	    try {
			httpPost.setEntity(new StringEntity(payload));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					resultContent = EntityUtils.toString(entity);
				}
				else {
					throw new HttpException("Error response in GET request: " + response.getStatusLine().getStatusCode());
				}
			}
		} catch (IOException | ParseException | HttpException e) {
			e.printStackTrace();
		}
		return resultContent;
	}

}
