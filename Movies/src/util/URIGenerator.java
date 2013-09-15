package util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import domain.Thing;

public class URIGenerator {
	
	public synchronized static <T extends Thing> URI generate(T resource) throws URISyntaxException{
		String uri = "http://is.fon.rs/movies/"+ UUID.randomUUID();
		return new URI(uri);
	}

}
