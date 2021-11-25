package entertainment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Serial extends Video {
    private int numberOfSeasons;
    private ArrayList<Season> seasons;
    private Map<Integer, Double> ratingPerSeason = new HashMap<>();
    private int totalDuration;

    public Serial(final String name, final int year, final ArrayList<String> cast,
                  final ArrayList<String> genres, final int numberOfSeasons,
                  final ArrayList<Season> seasons) {
        super(name, year, cast, genres);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
    }

    public Serial(final Serial s) {
        super(s.getName(), s.getYear(), s.getCast(), s.getGenres());
        this.setRating(s.getRating());
        this.numberOfSeasons = s.getNumberOfSeasons();
        this.seasons = s.seasons;
        this.ratingPerSeason = s.getRatingPerSeason();
        this.totalDuration = s.getTotalDuration();
    }
    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(final int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }


    /**
     *
     */
    // seteaza ratingul total al serialului si calculeaza si ratingul/sezon
    public void setRating() {
        Double totalSum = 0d;
        int index = 1;
        for (Season s : this.seasons) {
            Double seasonSum = 0d;
            for (Double r : s.getRatings()) {
                seasonSum = seasonSum + r;
            }
            if (s.getRatings().size() == 0) {
                this.ratingPerSeason.put(index, 0d);
            } else {
                this.ratingPerSeason.put(index, (Double) (seasonSum / s.getRatings().size()));
            }
            totalSum = totalSum + seasonSum;
            index++;
        }
        if (totalSum.equals(0d)) {
            this.setRating(0d);
        } else {
            this.setRating((Double) (totalSum / this.numberOfSeasons));
        }
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(final ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public Map<Integer, Double> getRatingPerSeason() {
        return ratingPerSeason;
    }

    /**
     *
     */
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
