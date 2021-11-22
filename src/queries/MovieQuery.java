package queries;

import databases.ActorDatabase;
import databases.MovieDatabase;
import databases.SerialDatabase;
import databases.UserDatabase;
import entertainment.Movie;

import java.util.*;

public class MovieQuery extends Query {
    private List<List<String>> filters;
    public MovieQuery(int actionId, String actionType, String objectType,
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
    @Override
    public String queryMethod(ActorDatabase ad, UserDatabase ud,
                              MovieDatabase md, SerialDatabase sd) {
        String message = "Query result: ";
        if (this.getCriteria().equals("ratings")) {
            Map<String, Double> moviesWithRatings = new HashMap<>();
            Integer year;
            boolean condition;
            List<String> genres = this.filters.get(1);
            for (Movie m : md.getMovies()) {
                m.setRating();
                condition = m.getRating() != 0;
                if (this.filters.get(0).get(0) != null) {
                    year = Integer.valueOf(this.filters.get(0).get(0));
                    condition = condition && (m.getYear() == year);
                }
                if (this.filters.get(1).get(0) != null) {
                    condition = condition && m.getGenres().containsAll(this.filters.get(1));
                }
                if (condition) {
                    moviesWithRatings.put(m.getName(), m.getRating());
                }
            }
            List<String> names = new ArrayList<>();// asc = true, desc = false
            if (this.getSortType().equals("asc")) {
                List<Map.Entry<String, Double>> sortedRatings = sortByComparator(moviesWithRatings, true);
                names = getNames(sortedRatings);
            } else if (this.getSortType().equals("desc")) {
                List<Map.Entry<String, Double>> sortedRatings = sortByComparator(moviesWithRatings, false);
                names = getNames(sortedRatings);
            }
            message = message + names;
//            System.out.println(message);

        } else if (this.getCriteria().equals("favorite")) {

        } else if (this.getCriteria().equals("longest")) {

        } else if (this.getCriteria().equals("most_viewed")) {

        }
        return message;
    }
}
