package queries;

import actor.Actor;
import common.Constants;
import databases.ActorDatabase;
import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

import static utils.Utils.formNameList;
import static utils.Utils.formNameListInteger;

public final class ActorQuery extends Query {
    private List<List<String>> filters;
    public ActorQuery(final int actionId, final String actionType, final String objectType,
                      final int number, final String username, final String sortType,
                      final String criteria, final String genre, final List<List<String>> filters) {
        super(actionId, actionType, objectType, number, username, sortType, criteria);
        this.filters = filters;
    }

    public List<List<String>> getFilters() {
        return filters;
    }

    public void setFilters(final List<List<String>> filters) {
        this.filters = filters;
    }

    @Override
    public String queryMethod(final ActorDatabase ad, final UserDatabase ud,
                              final MovieDatabase md, final SerialDatabase sd) {
        String message = "Query result: ";
        if (this.getCriteria().equals(Constants.AVERAGE)) {
            Map<String, Double> actorsRatings = new HashMap<>();
            for (Actor a : ad.getActors()) {
                Double rating = a.getTotalRating(md, sd);
                if (rating != 0) {
                    actorsRatings.put(a.getName(), rating);
                }
            }
            List<String> names = formNameList(this.getSortType(), actorsRatings, this.getNumber());
            message = message + names;

        } else if (this.getCriteria().equals(Constants.AWARDS)) {
            List<String> mentionedAwards = this.filters.get(Constants.AWARDS_POS);
            Map<String, Integer> actorsAwards = new HashMap<>();
            for (Actor a : ad.getActors()) {
                int numAwards = a.getMentionedAwards(mentionedAwards);
                if (numAwards != 0) {
                    actorsAwards.put(a.getName(), numAwards);
                }
            }
            List<String> names = formNameListInteger(this.getSortType(),
                    actorsAwards, this.getNumber());
            message = message + names;

        } else if (this.getCriteria().equals(Constants.FILTER_DESCRIPTIONS)) {
            List<String> words = this.filters.get(2);
            List<String> actors = new ArrayList<String>();
            for (Actor a : ad.getActors()) {
                if (a.hasWords(words)) {
                    actors.add(a.getName());
                }
            }
            if (this.getSortType().equals(Constants.ASCENDING)) {
                actors.sort(Comparator.naturalOrder());
            } else if (this.getSortType().equals(Constants.DESCENDING)) {
                actors.sort(Comparator.reverseOrder());
            }
            message = message + actors;
        }
        return message;
    }
}
