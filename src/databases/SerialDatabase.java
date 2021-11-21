package databases;

import entertainment.Season;
import entertainment.Serial;
import fileio.SerialInputData;

import java.util.ArrayList;

public class SerialDatabase {
    private ArrayList<Serial> serials = new ArrayList<Serial>();

    public void addSerials(ArrayList<SerialInputData> serials) {
        for (SerialInputData s : serials) {
            String title = s.getTitle();
            int year = s.getYear();
            ArrayList<String> cast = s.getCast();
            ArrayList<String> genres = s.getGenres();
            int numseasons = s.getNumberSeason();
            ArrayList<Season> seasons = s.getSeasons();
            this.serials.add(new Serial(title, year, cast, genres, numseasons, seasons));
        }
    }

    public ArrayList<Serial> getSerials() {
        return serials;
    }

    public void setSerials(ArrayList<Serial> serials) {
        this.serials = serials;
    }

    public Serial getSerialByTitle(String title) {
        for(Serial s : this.serials) {
            if(s.getName().equals(title)) {
                return s;
            }
        }
        return null;
    }
}
