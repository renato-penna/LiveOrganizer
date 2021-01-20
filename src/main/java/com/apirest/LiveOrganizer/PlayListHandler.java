package com.apirest.LiveOrganizer;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.apirest.LiveOrganizer.document.PlayList;
import com.apirest.LiveOrganizer.services.PlayListService;

import reactor.core.publisher.Mono;

//@Component
public class PlayListHandler {
	
	@Autowired
	private PlayListService pls;
	
	public Mono<ServerResponse> findAll(ServerRequest request){
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(pls.findAll(),PlayList.class);
	}
	
	public Mono<ServerResponse> findById(ServerRequest request){
		String id = request.pathVariable("id");
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(pls.findById(id), PlayList.class);
	}
	
	public Mono<ServerResponse> save(ServerRequest request){
		final Mono<PlayList> playList = request.bodyToMono(PlayList.class);
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(fromPublisher(playList.flatMap(pls::save), PlayList.class));
	}
}
