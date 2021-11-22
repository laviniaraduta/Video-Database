package entertainment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Serial extends Video {
    private int numberOfSeasons;
    private Double rating;
    private ArrayList<String> cast;
    private ArrayList<Season> seasons;
    private Map<Integer, Double> ratingPerSeason = new HashMap<>();
    private int totalDuration;

    public Serial(String name, int year, ArrayList<String> cast, ArrayList<String> genres, int numberOfSeasons, ArrayList<Season> seasons) {
        super(name, year, cast, genres);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }


    public Double getRating() {
        return this.rating;
    }

    // seteaza ratingul total al serialului si calculeaza si ratingul/sezon
    public void setRating() {
        Double totalSum = 0d;
        int index = 1;
        for (Season s : this.seasons) {
            Double seasonSum = 0d;
            for (Double r : s.getRatings()) {
                seasonSum = seasonSum + r;
            }
            this.ratingPerSeason.put(index, (Double) (seasonSum / s.getRatings().size()));
            totalSum = totalSum + seasonSum;
            index++;
        }
        this.rating = (Double) (totalSum / this.numberOfSeasons);
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public Map<Integer, Double> getRatingPerSeason() {
        return ratingPerSeason;
    }
    public void setTotalDuration() {
        int duration = 0;
        for (Season s : this.seasons) {
            duration += s.getDuration();
        }
        this.totalDuration = duration;
    }
    public int getTotalDuration() {
        return this.totalDuration;
    }
}
