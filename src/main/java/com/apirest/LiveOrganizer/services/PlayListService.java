package com.apirest.LiveOrganizer.services;

import com.apirest.LiveOrganizer.document.PlayList;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayListService {
	Flux<PlayList> findAll();
	
	Mono<PlayList> findById(String id);
	
	Mono<PlayList> save(PlayList playList);
}
