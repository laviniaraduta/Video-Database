package databases;

import entertainment.Movie;
import entertainment.Serial;
import entertainment.Video;

import javax.print.DocFlavor;
import java.util.*;

import static utils.Utils.formNameList;
import static utils.Utils.formNameListInteger;

public class VideoDatabase {
    private List<Video> videoList;
    private List<Video> videosByRating = new ArrayList<>();
    private List<Video> videosByViews = new ArrayList<>();
    private List<Video> videosByLikes = new ArrayList<>();
    private List<Video> videosByRatingByName = new ArrayList<>();

    public VideoDatabase(final MovieDatabase md, final SerialDatabase sd, final UserDatabase ud) {
        this.videoList = new ArrayList<Video>();
        for (Movie m : md.getMovies()) {
            m.setRating();
            m.setViews(ud);
            m.setLikes(ud);
            this.videoList.add(m);
            m.setIndexInVideoList(this.videoList.size());
            this.videosByRating.add(m);
            this.videosByViews.add(m);
            this.videosByLikes.add(m);
            this.videosByRatingByName.add(m);
        }
        for (Serial s : sd.getSerials()) {
            s.setRating();
            s.setViews(ud);
            s.setLikes(ud);
            this.videoList.add(s);
            s.setIndexInVideoList(this.videoList.size());
            this.videosByRating.add(s);
            this.videosByViews.add(s);
            this.videosByLikes.add(s);
            this.videosByRatingByName.add(s);

        }
        Collections.sort(this.videosByRating, new Comparator<Video>() {
            @Override
            public int compare(Video o1, Video o2) {
                if (Double.compare(o2.getRating(), o1.getRating()) == 0) {
                    return o1.getIndexInVideoList() - o2.getIndexInVideoList();
                } else {
                    return Double.compare(o2.getRating(), o1.getRating());
                }
            }
        });
        Collections.sort(this.videosByViews, new Comparator<Video>() {
            @Override
            public int compare(Video o1, Video o2) {
                if (Integer.compare(o2.getViews(), o1.getViews()) == 0) {
                    return o1.getIndexInVideoList() - o2.getIndexInVideoList();
                } else {
                    return Integer.compare(o2.getViews(), o1.getViews());
                }
            }
        });
        Collections.sort(this.videosByLikes, new Comparator<Video>() {
            @Override
            public int compare(Video o1, Video o2) {
                if (Integer.compare(o2.getLikes(), o1.getLikes()) == 0) {
                    return o1.getIndexInVideoList() - o2.getIndexInVideoList();
                } else {
                    return Integer.compare(o2.getLikes(), o1.getLikes());
                }
            }
        });
        Collections.sort(this.videosByRatingByName, new Comparator<Video>() {
            @Override
            public int compare(Video o1, Video o2) {
                if (Double.compare(o1.getRating(), o2.getRating()) == 0) {
                    return o1.getName().compareTo(o2.getName());
                } else {
                    return Double.compare(o1.getRating(), o2.getRating());
                }
            }
        });

    }


    public List<Video> getVideoList() {
        return videoList;
    }

    public List<Video> getVideosByRating() {
        return videosByRating;
    }

    public void setVideosByRating(List<Video> videosByRating) {
        this.videosByRating = videosByRating;
    }

    public List<Video> getVideosByViews() {
        return videosByViews;
    }

    public void setVideosByViews(List<Video> videosByViews) {
        this.videosByViews = videosByViews;
    }

    public List<Video> getVideosByLikes() {
        return videosByLikes;
    }

    public void setVideosByLikes(List<Video> videosByLikes) {
        this.videosByLikes = videosByLikes;
    }

    public List<Video> getVideosByRatingByName() {
        return videosByRatingByName;
    }

    public void setVideosByRatingByName(List<Video> videosByRatingByName) {
        this.videosByRatingByName = videosByRatingByName;
    }
}
