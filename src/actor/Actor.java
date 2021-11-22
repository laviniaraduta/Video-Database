package actor;

import databases.MovieDatabase;
import databases.SerialDatabase;
import entertainment.Movie;
import entertainment.Serial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Actor {
    private String name;
    private String careerDescription;
    private ArrayList<String> filmography;
    private Map<ActorsAwards, Integer> awards;
    private Map<String, Double> videoRatings = new HashMap<>();

    public Actor(final String name, final String careerDescription,
                 final ArrayList<String> filmography, final Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
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

//    public void setVideoRatings(MovieDatabase md, SerialDatabase sd) {
//        for (String title : this.filmography) {
//            Movie movie = md.getMovieByTitle(title);
//            if (movie != null) {
//                if (movie.getRating() != null) {
//                    this.videoRatings.put(title, movie.getRating());
//                }
//            } else {
//                Serial serial = sd.getSerialByTitle(title);
//                if (serial != null) {
//                    if (serial.getRating() != null) {
//                        this.videoRatings.put(title, serial.getRating());
//                    }
//                }
//            }
//        }
//    }


    /**
     * @param md
     * @param sd
     * @return
     */
    public Double getTotalRating(final MovieDatabase md, final SerialDatabase sd) {
        Double ratingSum = 0d;
        int number = 0;
        for (String title : this.filmography) {
            Movie movie = md.getMovieByTitle(title);
            if (movie != null) {
                if (movie.getRating() != null) {
                    ratingSum += movie.getRating();
                    number++;
                }
            } else {
                Serial serial = sd.getSerialByTitle(title);
                if (serial != null) {
                    if (serial.getRating() != null) {
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

    public Map<String, Double> getVideoRatings() {
        return videoRatings;
    }

    /**
     * @param mentionedAwards
     * @return
     */
    public int getMentionedAwards(final List<String> mentionedAwards) {
        int total = 0, number = mentionedAwards.size();
        for (String award : mentionedAwards) {
            ActorsAwards a = ActorsAwards.valueOf(award);
            if (this.getAwards().containsKey(a)) {
                total += this.getAwards().get(a);
            } else {
                return 0;
            }
        }
        return total;
    }

    /**
     * @param words
     * @return
     */
    public boolean hasWords(final List<String> words) {
        for (String w : words) {
            if (!this.getCareerDescription().toLowerCase().contains(w)) {
                return false;
            }
        }
        return true;
    }
}
