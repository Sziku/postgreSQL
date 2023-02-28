package org.example.controller;

import org.example.model.Actor.Actor;
import org.example.service.ActorService;

import java.util.List;

public class ActorController {
    private final ActorService service;

    public ActorController(ActorService service) {
        this.service = service;
    }

    public void listAllActors() {
        service.getAllActors().forEach(System.out::println);
    }

    public void writeOutActorByName(String name) {
        System.out.println(service.getActorByName(name));
    }

    public void addActor(Actor actor) {
        service.addActor(actor);
    }

    public void updateActor(int id, Actor actor) {
        service.updateActor(id, actor);
    }

    public void deleteActorById(int id) {
        service.deleteActorById(id);
    }
}
