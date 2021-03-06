package actor;

import databases.MovieDatabase;
import databases.SerialDatabase;
import entertainment.Movie;
import entertainment.Serial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Actor {
    private String name;
    private String careerDescription;
    private ArrayList<String> filmography;
    private Map<ActorsAwards, Integer> awards;
    private Map<String, Double> videoRatings = new HashMap<>();
    private Integer totalAwards;

    public Actor(final String name, final String careerDescription,
                 final ArrayList<String> filmography, final Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
        this.totalAwards = awards.values().stream().mapToInt(i -> i).sum();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public void setCareerDescription(final String careerDescription) {
        this.careerDescription = careerDescription;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }

    public void setFilmography(final ArrayList<String> filmography) {
        this.filmography = filmography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public void setAwards(final Map<ActorsAwards, Integer> awards) {
        this.awards = awards;
    }

    /**
     * Computes the average rating for the actor's total videos
     * @param md the database containing all the movies
     * @param sd the database containing all the shows
     * @return double value representing the rating
     */
    public Double getTotalRating(final MovieDatabase md, final SerialDatabase sd) {
        Double ratingSum = 0d;
        int number = 0;
        for (String title : this.filmography) {
            Movie movie = md.getMovieByTitle(title);
            if (movie != null) {
                movie.setRating();
                if (!movie.getRating().equals(0d)) {
                    ratingSum += movie.getRating();
                    number++;
                }
            } else {
                Serial serial = sd.getSerialByTitle(title);
                if (serial != null) {
                    serial.setRating();
                    if (!serial.getRating().equals(0d)) {
                        ratingSum += serial.getRating();
                        number++;
                    }
                }
            }
        }
        if (ratingSum == 0 || number == 0) {
            return 0d;
        } else {
            return (Double) (ratingSum / number);
        }
    }

    public Integer getTotalAwards() {
        return totalAwards;
    }

    public void setTotalAwards(final Integer totalAwards) {
        this.totalAwards = totalAwards;
    }

    public Map<String, Double> getVideoRatings() {
        return videoRatings;
    }


    /**
     * Verifies if the actor has achieved all the mentioned awards
     * @param mentionedAwards list of the awards we search
     * @return the total number of awards of the actor
     */
    public int getMentionedAwards(final List<String> mentionedAwards) {
        for (String award : mentionedAwards) {
            ActorsAwards a = ActorsAwards.valueOf(award);
            if (!this.getAwards().containsKey(a)) {
                return 0;
            }
        }
        return this.totalAwards;
    }

    /**
     * Checks if the career description of the actor contains all the keywords
     * @param words the list of words we search
     * @return boolean that shows if the description contains all the words
     */
    public boolean hasWords(final List<String> words) {
        for (String w : words) {
            String patternString = "[ -]" + w + "[ ,.]";
            Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(this.careerDescription);
            boolean found = matcher.find();
            if (!found) {
                return false;
            }
        }
        return true;
    }
}
