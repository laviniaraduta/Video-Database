package databases;

import entertainment.Season;
import entertainment.Serial;
import fileio.SerialInputData;

import java.util.ArrayList;
import java.util.List;

public final class SerialDatabase {
    private ArrayList<Serial> serials = new ArrayList<Serial>();

    /**
     * @param serialsList
     */
    public void addSerials(final List<SerialInputData> serialsList) {
        for (SerialInputData s : serialsList) {
            String title = s.getTitle();
            int year = s.getYear();
            ArrayList<String> cast = s.getCast();
            ArrayList<String> genres = s.getGenres();
            int numSeasons = s.getNumberSeason();
            ArrayList<Season> seasons = s.getSeasons();
            this.serials.add(new Serial(title, year, cast, genres, numSeasons, seasons));
        }
    }

    public ArrayList<Serial> getSerials() {
        return serials;
    }

    public void setSerials(final ArrayList<Serial> serials) {
        this.serials = serials;
    }

    /**
     * Returns the serial with the title given from the database
     * @param title the title of the serial
     * @return the serial found, null otherwise
     */
    public Serial getSerialByTitle(final String title) {
        for (Serial s : this.serials) {
            if (s.getName().equals(title)) {
                return s;
            }
        }
        return null;
    }
}
