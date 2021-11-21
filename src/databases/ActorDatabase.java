package databases;

import actor.Actor;
import actor.ActorsAwards;
import fileio.ActorInputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActorDatabase {
    private ArrayList<Actor> actors = new ArrayList<Actor>();
    public void addActors(List<ActorInputData> actors) {
        for (ActorInputData a : actors) {
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

    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }
}
