package databases;

import actor.Actor;
import actor.ActorsAwards;
import fileio.ActorInputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ActorDatabase {
    private ArrayList<Actor> actors = new ArrayList<Actor>();

    /**
     * Populates the actor database with actors read from a file
     * @param actorsList list of actors read from a file
     */
    public void addActors(final List<ActorInputData> actorsList) {
        for (ActorInputData a : actorsList) {
            String name = a.getName();
            String career = a.getCareerDescription();
            ArrayList<String> filmography = a.getFilmography();
            Map<ActorsAwards, Integer> awards = a.getAwards();
            this.actors.add(new Actor(name, career, filmography, awards));
        }
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void setActors(final ArrayList<Actor> actors) {
        this.actors = actors;
    }
}
