package databases;

import entertainment.Movie;
import entertainment.Serial;

import java.util.HashMap;
import java.util.Map;

public class VideoDatabase {
    Map<String, Double> videoAndRating = new HashMap<>();
    Map<String, Integer> videoAndDuration = new HashMap<>();

    public VideoDatabase(MovieDatabase md, SerialDatabase sd) {
        for (Movie m : md.getMovies()) {
            this.videoAndRating.put(m.getName(), m.getRating());
            this.videoAndDuration.put(m.getName(), m.getDuration());
        }
        for (Serial s : sd.getSerials()) {
            this.videoAndRating.put(s.getName(), s.getRating());
            this.videoAndDuration.put(s.getName(), s.getTotalDuration());
        }
    }
}
