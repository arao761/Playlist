package MediaPlayer;
/**
 * Represents a song with basic metadata information.
 */

 //todo Make this class Comparable for sorting

public class Song implements Comparable<Song> {
    private String title;
    private String artist;
    private String album;
    private String duration;

    /**
     * Creates a new Song with the given attributes.
     *
     * @param title The title of the song
     * @param artist The artist who performed the song
     * @param album The album the song belongs to
     * @param duration The duration of the song in MM:SS format
     */
    public Song(String title, String artist, String album, String duration) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    /**
     * @return The title of the song
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return The artist of the song
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @return The album of the song
     */
    public String getAlbum() {
        return album;
    }

    /**
     * @return The duration of the song
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Converts the song data to an array for database table display.
     *
     * @return Object array with [title, artist, album, duration]
     */
    public Object[] toTableRow() {
        return new Object[]{title, artist, album, duration};
    }

    /**
     * Converts the song data to an array for playlist table display.
     *
     * @param position The position of the song in the playlist
     * @return Object array with [position, title, artist, duration]
     */
    public Object[] toPlaylistRow(int position) {
        return new Object[]{position, title, artist, duration};
    }

    @Override
    public String toString() {
        return title + " - " + artist + " (" + duration + ")";
    }

    public int compareTo(Song song){
        return this.title.compareTo(song.title);
    }
    
}