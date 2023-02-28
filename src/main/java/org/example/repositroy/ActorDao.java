package org.example.repositroy;

import org.example.model.Actor.Actor;

import java.util.List;

public interface ActorDao {
    List<Actor> getAllActors();
    Actor getActorByName(String firstName, String lastName);
    void addActor(Actor actor);
    void updateActor(int id, Actor actor);
    void deleteActorById(int id);
}
