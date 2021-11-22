package queries;

import actor.Actor;
import databases.ActorDatabase;
import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;

import java.util.*;

import static utils.Utils.*;

public class ActorQuery extends Query {
    private List<List<String>> filters;
    public ActorQuery(int actionId, String actionType, String objectType,
                      int number, String username, String sortType,
                      String criteria, String genre, List<List<String>> filters) {
        super(actionId, actionType, objectType, number, username, sortType, criteria);
        this.filters = filters;
    }

    public List<List<String>> getFilters() {
        return filters;
    }

    public void setFilters(List<List<String>> filters) {
        this.filters = filters;
    }

    @Override
    public String queryMethod(ActorDatabase ad, UserDatabase ud,
                              MovieDatabase md, SerialDatabase sd) {
        String message = "Query result: ";
        if (this.getCriteria().equals("average")) {
            Map<String, Double> actorsRatings = new HashMap<>();
            for (Actor a : ad.getActors()) {
                Double rating = a.getTotalRating(md, sd);
                if (rating != 0) {
                    actorsRatings.put(a.getName(), rating);
                }
            }
            List<String> names = formNameList(this.getSortType(), actorsRatings, this.getNumber());
            message = message + names;

        } else if (this.getCriteria().equals("awards")) {
            List<String> mentionedAwards = this.filters.get(3);
            Map<String, Integer> actorsAwards = new HashMap<>();
            for (Actor a : ad.getActors()) {
                int numAwards = a.getMentionedAwards(mentionedAwards);
                if (numAwards != 0) {
                    actorsAwards.put(a.getName(), numAwards);
                }
            }
            List<String> names = formNameListInteger(this.getSortType(), actorsAwards, this.getNumber());
            message = message + names;

        } else if (this.getCriteria().equals("filter_description")) {
            List<String> words = this.filters.get(2);
            List<String> actors = new ArrayList<String>();
            for (Actor a : ad.getActors()) {
                if (a.hasWords(words)) {
                    actors.add(a.getName());
                }
            }
            if (this.getSortType().equals("asc")) {
                actors.sort(Comparator.naturalOrder());
            } else if (this.getSortType().equals("desc")) {
                actors.sort(Comparator.reverseOrder());
            }
            message = message + actors;
        }
        return message;
    }
}
