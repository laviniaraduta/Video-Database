package queries;

import actor.Actor;
import databases.ActorDatabase;
import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;

import java.util.*;

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

    private static List<Map.Entry<String, Double>> sortByComparator (Map<String, Double> unsorted, boolean order) {
        List<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(unsorted.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                if (order) {
                    if (Double.compare(o1.getValue(), o2.getValue()) == 0) {
                        return o1.getKey().compareTo(o2.getKey());
                    } else {
                        return Double.compare(o1.getValue(), o2.getValue());
                    }
                } else {
                    if (Double.compare(o2.getValue(), o1.getValue()) == 0) {
                        return o2.getKey().compareTo(o1.getKey());
                    } else {
                        return Double.compare(o2.getValue(), o1.getValue());
                    }
                }
            }
        });
//        Map<String, Double> sorted = new HashMap<String, Double>();
//        for (Map.Entry<String, Double> entry : list) {
//            sorted.put(entry.getKey(), entry.getValue());
//        }
        return list;
    }

    private List<String> getNames(List<Map.Entry<String, Double>> map) {
        List<String> names = new ArrayList<String>();
        int index = 0;
        for (Map.Entry<String, Double> entry : map) {
            if (index < this.getNumber()) {
                names.add(entry.getKey());
                index++;
            } else {
                break;
            }
        }
        return names;
    }

    private Map.Entry<String, Double> minEntry (Map<String, Double> map) {
        Map.Entry<String, Double> min = null;
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (min == null || Double.compare(min.getValue(), entry.getValue()) > 0) {
                min = entry;
            }
        }
        return min;
    }

    private Map.Entry<String, Double> maxEntry (Map<String, Double> map) {
        Map.Entry<String, Double> max = null;
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (max == null || Double.compare(max.getValue(), entry.getValue()) < 0) {
                max = entry;
            }
        }
        return max;
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
            List<String> names = null;// asc = true, desc = false
            if (this.getSortType().equals("asc")) {
                List<Map.Entry<String, Double>> sortedRatings = sortByComparator(actorsRatings, true);
                names = getNames(sortedRatings);
            } else if (this.getSortType().equals("desc")) {
                List<Map.Entry<String, Double>> sortedRatings = sortByComparator(actorsRatings, false);
                names = getNames(sortedRatings);
            }
            message = message + names;

        } else if (this.getCriteria().equals("awards")) {
            Map<String, Double> actorsAwards = new HashMap<>();
            for (Actor a : ad.getActors()) {
//                int numAwards = a.getMentionedAwards()
            }

        } else if (this.getCriteria().equals("filter_description")) {

        }
//        System.out.println(message);
        return message;
    }
}
