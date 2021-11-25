package utils;

import actor.ActorsAwards;
import common.Constants;
import entertainment.Genre;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

/**
 * The class contains static methods that helps with parsing.
 *
 * We suggest you add your static methods here or in a similar class.
 */
public final class Utils {
    /**
     * for coding style
     */
    private Utils() {
    }

    /**
     * Transforms a string into an enum
     * @param genre of video
     * @return an Genre Enum
     */
    public static Genre stringToGenre(final String genre) {
        return switch (genre) {
            case "Action" -> Genre.ACTION;
            case "Action & Adventure" -> Genre.ACTION_ADVENTURE;
            case "Adventure" -> Genre.ADVENTURE;
            case "Animation" -> Genre.ANIMATION;
            case "Comedy" -> Genre.COMEDY;
            case "Crime" -> Genre.CRIME;
            case "Drama" -> Genre.DRAMA;
            case "Family" -> Genre.FAMILY;
            case "Fantasy" -> Genre.FANTASY;
            case "History" -> Genre.HISTORY;
            case "Horror" -> Genre.HORROR;
            case "Kids" -> Genre.KIDS;
            case "Mystery" -> Genre.MYSTERY;
            case "Romance" -> Genre.ROMANCE;
            case "Sci-Fi & Fantasy" -> Genre.SCI_FI_FANTASY;
            case "Science Fiction" -> Genre.SCIENCE_FICTION;
            case "Thriller" -> Genre.THRILLER;
            case "TV Movie" -> Genre.TV_MOVIE;
            case "War" -> Genre.WAR;
            case "Western" -> Genre.WESTERN;
            default -> null;
        };
    }
    /**
     * Transforms a string into an enum
     * @param award for actors
     * @return an ActorsAwards Enum
     */
    public static ActorsAwards stringToAwards(final String award) {
        return switch (award) {
            case "BEST_SCREENPLAY" -> ActorsAwards.BEST_SCREENPLAY;
            case "BEST_SUPPORTING_ACTOR" -> ActorsAwards.BEST_SUPPORTING_ACTOR;
            case "BEST_DIRECTOR" -> ActorsAwards.BEST_DIRECTOR;
            case "BEST_PERFORMANCE" -> ActorsAwards.BEST_PERFORMANCE;
            case "PEOPLE_CHOICE_AWARD" -> ActorsAwards.PEOPLE_CHOICE_AWARD;
            default -> null;
        };
    }


    /**
     * Transforms an array of JSON's into an array of strings
     * @param array of JSONs
     * @return a list of strings
     */
    public static ArrayList<String> convertJSONArray(final JSONArray array) {
        if (array != null) {
            ArrayList<String> finalArray = new ArrayList<>();
            for (Object object : array) {
                finalArray.add((String) object);
            }
            return finalArray;
        } else {
            return null;
        }
    }

    /**
     * Transforms an array of JSON's into a map
     * @param jsonActors array of JSONs
     * @return a map with ActorsAwardsa as key and Integer as value
     */
    public static Map<ActorsAwards, Integer> convertAwards(final JSONArray jsonActors) {
        Map<ActorsAwards, Integer> awards = new LinkedHashMap<>();

        for (Object iterator : jsonActors) {
            awards.put(stringToAwards((String) ((JSONObject) iterator).get(Constants.AWARD_TYPE)),
                    Integer.parseInt(((JSONObject) iterator).get(Constants.NUMBER_OF_AWARDS)
                            .toString()));
        }

        return awards;
    }

    /**
     * Transforms an array of JSON's into a map
     * @param movies array of JSONs
     * @return a map with String as key and Integer as value
     */
    public static Map<String, Integer> watchedMovie(final JSONArray movies) {
        Map<String, Integer> mapVideos = new LinkedHashMap<>();

        if (movies != null) {
            for (Object movie : movies) {
                mapVideos.put((String) ((JSONObject) movie).get(Constants.NAME),
                        Integer.parseInt(((JSONObject) movie).get(Constants.NUMBER_VIEWS)
                                .toString()));
            }
        } else {
            System.out.println("NU ESTE VIZIONAT NICIUN FILM");
        }

        return mapVideos;
    }

    /**
     * Sorts a map containing entries of form String -> Double
     * @param unsorted the map that needs to be sorted
     * @param order true for ascending order, false for descending
     * @return a sorted list of map entries
     */
    public static List<Map.Entry<String, Double>> sortByComparator(
            final Map<String, Double> unsorted,
            final boolean order) {
        List<Map.Entry<String, Double>> list =
                new ArrayList<Map.Entry<String, Double>>(unsorted.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(final Map.Entry<String, Double> o1,
                               final Map.Entry<String, Double> o2) {
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

    /**
     * Sorts a map containing entries of form Genre -> Integer
     * @param unsorted the map that needs to be sorted
     * @param order true for ascending order, false for descending
     * @return a sorted list of map entries
     */
    public static List<Map.Entry<Genre, Integer>> sortByComparatorGenres(
            final Map<Genre, Integer> unsorted,
            final boolean order) {
        List<Map.Entry<Genre, Integer>> list =
                new ArrayList<Map.Entry<Genre, Integer>>(unsorted.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Genre, Integer>>() {
            @Override
            public int compare(final Map.Entry<Genre, Integer> o1,
                               final Map.Entry<Genre, Integer> o2) {
                if (order) {
                    if (Integer.compare(o1.getValue(), o2.getValue()) == 0) {
                        return o1.getKey().compareTo(o2.getKey());
                    } else {
                        return Double.compare(o1.getValue(), o2.getValue());
                    }
                } else {
                    if (Integer.compare(o2.getValue(), o1.getValue()) == 0) {
                        return o2.getKey().compareTo(o1.getKey());
                    } else {
                        return Double.compare(o2.getValue(), o1.getValue());
                    }
                }
            }
        });
        return list;
    }

    /**
     * Sorts a map containing entries of form String -> Integer
     * @param unsorted the map that needs to be sorted
     * @param order true for ascending order, false for descending
     * @return a sorted list of map entries
     */
    public static List<Map.Entry<String, Integer>> sortByComparatorInteger(
            final Map<String, Integer> unsorted,
            final boolean order) {
        List<Map.Entry<String, Integer>> list =
                new ArrayList<Map.Entry<String, Integer>>(unsorted.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(final Map.Entry<String, Integer> o1,
                               final Map.Entry<String, Integer> o2) {
                if (order) {
                    if (Integer.compare(o1.getValue(), o2.getValue()) == 0) {
                        return o1.getKey().compareTo(o2.getKey());
                    } else {
                        return Integer.compare(o1.getValue(), o2.getValue());
                    }
                } else {
                    if (Integer.compare(o2.getValue(), o1.getValue()) == 0) {
                        return o2.getKey().compareTo(o1.getKey());
                    } else {
                        return Integer.compare(o2.getValue(), o1.getValue());
                    }
                }
            }
        });
        return list;
    }


    /**
     * Gets the Strings list from the list of map entries
     * @param list list of map entries
     * @param number the number of names we need
     * @return
     */
    public static List<String> getNames(final List<Map.Entry<String, Double>> list,
                                        final int number) {
        List<String> names = new ArrayList<String>();
        int index = 0;
        for (Map.Entry<String, Double> entry : list) {
            if (index < number) {
                names.add(entry.getKey());
                index++;
            } else {
                break;
            }
        }
        return names;
    }

    /**
     * Gets the Strings list from the list of map entries
     * @param list list of map entries
     * @param number the number of names we need
     * @return
     */
    public static List<String> getNamesInteger(final List<Map.Entry<String, Integer>> list,
                                               final int number) {
        List<String> names = new ArrayList<String>();
        int index = 0;
        for (Map.Entry<String, Integer> entry : list) {
            if (index < number) {
                names.add(entry.getKey());
                index++;
            } else {
                break;
            }
        }
        return names;
    }

    /**
     * Sorts the map and returns th elist of names from it
     * @param type the type of sort
     * @param map the map that needs to be sorted
     * @param number the number of values we need
     * @return
     */
    public static List<String> formNameList(final String type,
                                            final Map<String, Double> map,
                                            final int number) {
        List<String> names = null; // asc = true, desc = false
        if (type.equals("asc")) {
            List<Map.Entry<String, Double>> sortedMap = sortByComparator(map, true);
            names = getNames(sortedMap, number);
        } else if (type.equals("desc")) {
            List<Map.Entry<String, Double>> sortedMap = sortByComparator(map, false);
            names = getNames(sortedMap, number);
        }
        return names;
    }

    /**
     * Sorts the map and returns th elist of names from it
     * @param type the type of sort
     * @param map the map that needs to be sorted
     * @param number the number of values we need
     * @return
     */
    public static List<String> formNameListInteger(final String type,
                                                   final Map<String, Integer> map,
                                                   final int number) {
        List<String> names = null;
        if (type.equals("asc")) {
            List<Map.Entry<String, Integer>> sortedMap = sortByComparatorInteger(map, true);
            names = getNamesInteger(sortedMap, number);
        } else if (type.equals("desc")) {
            List<Map.Entry<String, Integer>> sortedMap = sortByComparatorInteger(map, false);
            names = getNamesInteger(sortedMap, number);
        }
        return names;
    }

}
