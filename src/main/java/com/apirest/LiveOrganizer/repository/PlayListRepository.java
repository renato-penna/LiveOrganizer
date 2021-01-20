package com.apirest.LiveOrganizer.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.apirest.LiveOrganizer.document.PlayList;

public interface PlayListRepository extends ReactiveMongoRepository<PlayList, String>{

}
