package br.com.caelum.ingresso.rest;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.mysql.jdbc.log.LogFactory;

import br.com.caelum.ingresso.model.DetalhesFilme;
import br.com.caelum.ingresso.model.Filme;

@Component
public class OmdbClient {
	
	public Optional<DetalhesFilme> request(Filme filme) {
		
		RestTemplate rest = new RestTemplate();
		
		String param = filme.getNome().replace(" ", "+");
		
		String url = String.format("https://omdb-fj22.herokuapp.com/movie?title=%s",param);
		
		try{
		DetalhesFilme dt = rest.getForObject(url, DetalhesFilme.class);
		return Optional.ofNullable(dt);
		}catch(RestClientException e){
			return Optional.empty();
		}
		
	}
	
	
}
