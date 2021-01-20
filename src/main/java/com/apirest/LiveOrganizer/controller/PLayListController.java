package com.apirest.LiveOrganizer.controller;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.LiveOrganizer.document.PlayList;
import com.apirest.LiveOrganizer.services.PlayListService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
public class PLayListController {
	
	@Autowired 
	private PlayListService pls;
	
	@GetMapping(value="/playlist")
	public Flux<PlayList> getPlayList(){
		return pls.findAll();
	}
	
	@GetMapping(value="/playlist/{id}")
	public Mono<PlayList> getPlayListId(@PathVariable (value="id") String id ){
		return pls.findById(id);
	}
	
	@PostMapping(value="/playlist")
	public Mono<PlayList> savePlayList(@RequestBody PlayList playList){
		return pls.save(playList);
	}
	
	@GetMapping(value="/playlist/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, PlayList>> getPlaylistByEvents(){

		Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        Flux<PlayList> playlistFlux = pls.findAll();
        System.out.println("---Start get Playlists by WEBFLUX--- " + LocalDateTime.now());
        return Flux.zip(interval, playlistFlux);
        
	}
}
