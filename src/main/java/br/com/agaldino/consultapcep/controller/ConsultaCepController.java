package br.com.agaldino.consultapcep.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Controller
public class ConsultaCepController {

	private static final String URL = "https://viacep.com.br/ws/";
	private static final String FORMAT_JSON = "/json";

	@GetMapping("/api/consulta/{cep}")
	public HttpEntity<String> consultar(@PathVariable("cep") String cep) {

		RestTemplate restTemplate = new RestTemplate();
		final String url = URL + cep + FORMAT_JSON;

		try {
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			return response;
		} catch (HttpClientErrorException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>(e.getResponseBodyAsString(), e.getStatusCode());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}