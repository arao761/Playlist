package MediaPlayer;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.util.*;

/**
 * Controller class that manages the song database and related table model.
 * Handles loading songs from CSV and updating the UI.
 */
public class SongDatabaseController {

    // Data
    private ArrayList<Song> songDatabase;
    // UI Model
    private DefaultTableModel songDatabaseModel;
    // Parent frame for dialogs
    private JFrame parentFrame;

    // Default CSV filename
    private static final String CSV_FILENAME = "songs.csv";

    /**
     * Creates a new SongDatabaseController.
     *
     * @param tableModel The table model to update with song data
     * @param parentFrame The parent frame for displaying dialogs
     */
    public SongDatabaseController(DefaultTableModel tableModel, JFrame parentFrame) {
        this.songDatabaseModel = tableModel;
        this.parentFrame = parentFrame;
        this.songDatabase = new ArrayList<>();

        // Ensure the CSV file exists
        createSongsCsvIfNotExists();
    }

    /**
     * Loads the song database from the CSV file.
     */
    public void loadSongDatabase() {
        try {
            // Clear existing data
            songDatabaseModel.setRowCount(0);
            songDatabase.clear();

            // Try to load from songs.csv
            BufferedReader reader = new BufferedReader(new FileReader(CSV_FILENAME));
            String line;

            // Skip header line
            reader.readLine();

            // Read song data
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    Song song = new Song(data[0], data[1], data[2], data[3]);
                    songDatabase.add(song);
                    songDatabaseModel.addRow(song.toTableRow());
                }
            }

            reader.close();

            // Show success message
            JOptionPane.showMessageDialog(parentFrame,
                "Loaded " + songDatabase.size() + " songs from database.",
                "Database Loaded", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(parentFrame,
                "Error loading song database: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();

            // If file loading fails, add some default songs
            addDefaultSongs();
        }
    }

    /**
     * Adds some default songs if CSV loading fails.
     */
    private void addDefaultSongs() {
        songDatabase.add(new Song("Default Song 1", "Artist 1", "Album 1", "3:30"));
        songDatabase.add(new Song("Default Song 2", "Artist 2", "Album 2", "4:15"));
        songDatabase.add(new Song("Default Song 3", "Artist 3", "Album 3", "2:55"));

        // Update the table
        updateTableModel();
    }

    /**
     * Updates the table model with the current contents of the song database.
     */
    private void updateTableModel() {
        songDatabaseModel.setRowCount(0);
        for (Song song : songDatabase) {
            songDatabaseModel.addRow(song.toTableRow());
        }
    }

    /**
     * Searches the song database for songs matching the given query.
     *
     * @param query The search term to match against song title, artist, or album
     */
    public void searchSongs(String query) {
        if (query == null || query.trim().isEmpty()) {
            // If query is empty, show all songs
            updateTableModel();
            return;
        }

        // Clear the table
        songDatabaseModel.setRowCount(0);

        // Convert query to lowercase for case-insensitive search
        String lowerQuery = query.toLowerCase().trim();

        // Add songs that match the query
        for (Song song : songDatabase) {
            if (song.getTitle().toLowerCase().contains(lowerQuery) ||
                song.getArtist().toLowerCase().contains(lowerQuery) ||
                song.getAlbum().toLowerCase().contains(lowerQuery)) {
                songDatabaseModel.addRow(song.toTableRow());
            }
        }
    }

    /**
     * Get a song from the database by its index.
     *
     * @param index The index of the song in the database
     * @return The song at the specified index or null if out of bounds
     */
    public Song getSongAt(int index) {
        if (index >= 0 && index < songDatabase.size()) {
            return songDatabase.get(index);
        }
        return null;
    }

    /**
     * Get all songs in the database.
     *
     * @return An unmodifiable list of all songs
     */
    public List<Song> getAllSongs() {
        return Collections.unmodifiableList(songDatabase);
    }

    /**
     * Get the number of songs in the database.
     *
     * @return The number of songs
     */
    public int getSize() {
        return songDatabase.size();
    }

    /**
     * Creates the songs CSV file if it doesn't exist.
     */
    private void createSongsCsvIfNotExists() {
        File file = new File(CSV_FILENAME);
        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.println("Title,Artist,Album,Duration");
                writer.println("Midnight Serenade,Luna Rivers,Moonlit Dreams,3:42");
                writer.println("Electric Dreams,Neon Pulse,Synthetic Memories,4:15");
                writer.println("Ocean's Call,Coral Tides,Deep Blue,3:56");
                writer.println("Mountain Echo,Alpine Sounds,Higher Ground,5:21");
                writer.println("Urban Symphony,City Noise,Concrete Jungle,4:03");
                writer.println("Desert Wind,Sandy Dunes,Mirage,3:38");
                writer.println("Forest Whispers,Green Canopy,Ancient Trees,4:47");
                writer.println("Starlight Journey,Cosmic Travelers,Galactic Voyage,6:12");
                writer.println("Rainy Day Blues,Cloudy Skies,Storm Front,3:29");
                writer.println("Sunshine Melody,Bright Rays,Summer Days,2:58");
                writer.println("Autumn Leaves,Falling Foliage,Seasonal Changes,4:34");
                writer.println("Winter's Tale,Frosty Winds,Ice Kingdom,5:07");
                writer.println("Spring Awakening,New Beginnings,Rebirth,3:49");
                writer.println("Summer Groove,Beach Vibes,Hot Sand,3:22");
                writer.println("Twilight Zone,Purple Horizon,Dusk Till Dawn,4:51");
                writer.println("Morning Coffee,Caffeine Rush,Wake Up Call,2:46");
                writer.println("Nightfall,Dark Shadows,Midnight Hour,5:33");
                writer.println("Highway Drive,Road Warriors,Long Journey,4:09");
                writer.println("Lost in Thought,Mind Wanderers,Introspection,6:27");
                writer.println("Dance Floor,Rhythm Kings,Move Your Body,3:12");
                writer.println("Peaceful Moments,Calm Waters,Tranquility,4:38");
                writer.println("Heavy Metal,Steel Forge,Iron Will,5:55");
                writer.println("Jazz Fusion,Smooth Saxes,Improvisation,7:23");
                writer.println("Classical Beauty,String Quartet,Timeless Pieces,8:11");
                writer.println("Rock Anthem,Guitar Heroes,Epic Solos,4:29");
                writer.println("Pop Sensation,Chart Toppers,Hit Machine,3:17");
                writer.println("Country Roads,Western Stars,Open Plains,4:02");
                writer.println("Hip Hop Beat,Urban Poets,Word Play,3:45");
                writer.println("Electronic Pulse,Digital Waves,Future Sounds,5:18");
                writer.println("Reggae Rhythm,Island Grooves,Beach Party,4:43");
                System.out.println("Created songs.csv file");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Song> getRandomSongs(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRandomSongs'");
    }
}