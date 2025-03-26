package MediaPlayer;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Main application class for the Music Playlist Manager.
 * Provides a GUI for managing a song database and playlists.
 */
public class MusicPlaylistManager extends JFrame {

    // Main panels
    private JPanel mainPanel;
    private JPanel songDatabasePanel;
    private JPanel playlistPanel;

    // Database components
    private JTable songDatabaseTable;
    private DefaultTableModel songDatabaseModel;
    private JScrollPane songDatabaseScrollPane;
    private JTextField searchField;
    private JButton searchButton;

    // Playlist components
    private JTable playlistTable;
    private DefaultTableModel playlistModel;
    private JScrollPane playlistScrollPane;
    private JButton addSongButton;
    private JButton removeSongButton;
    private JButton moveUpButton;
    private JButton moveDownButton;
    private JButton sortButton;
    private JButton addRandomSongsButton;

    // Playback control components
    private JPanel playbackPanel;
    private JButton previousButton;
    private JButton playPauseButton;
    private JButton nextButton;
    private JSlider volumeSlider;
    private JLabel currentSongLabel;
    private JProgressBar songProgressBar;

    // Controllers
    private SongDatabaseController songDbController;
    private PlaylistController playlistController;

    /**
     * Creates a new Music Playlist Manager application.
     */
    public MusicPlaylistManager() {
        // Setup the JFrame
        setTitle("Music Playlist Manager");
        setSize(1250, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();
        setupLayout();
        setupEventHandlers();

        // Load songs from database
        songDbController.loadSongDatabase();

        setVisible(true);
    }

    /**
     * Initializes the GUI components.
     */
    private void initializeComponents() {
        // Initialize main panels
        mainPanel = new JPanel(new BorderLayout());
        songDatabasePanel = new JPanel(new BorderLayout());
        playlistPanel = new JPanel(new BorderLayout());

        // Setup song database panel
        songDatabasePanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Song Database"));

        // Create song database table
        String[] databaseColumns = {"Title", "Artist", "Album", "Duration"};
        songDatabaseModel = new DefaultTableModel(databaseColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        songDatabaseTable = new JTable(songDatabaseModel);
        songDatabaseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        songDatabaseTable.setDragEnabled(true);
				songDatabaseTable.setRowHeight(25);  // Set explicit row height
				songDatabaseTable.setFont(new Font(songDatabaseTable.getFont().getName(), Font.PLAIN, 18)); // Larger font
        songDatabaseScrollPane = new JScrollPane(songDatabaseTable);

        // Initialize the song database controller
        songDbController = new SongDatabaseController(songDatabaseModel, this);

        // Create search components
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        searchButton = new JButton("Search");
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        songDatabasePanel.add(searchPanel, BorderLayout.NORTH);
        songDatabasePanel.add(songDatabaseScrollPane, BorderLayout.CENTER);

        // Setup playlist panel
        playlistPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Current Playlist"));

        // Create playlist table
        String[] playlistColumns = {"#", "Title", "Artist", "Duration"};
        playlistModel = new DefaultTableModel(playlistColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        playlistTable = new JTable(playlistModel);
        playlistTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playlistTable.setDragEnabled(true);
				playlistTable.setRowHeight(25);  // Set explicit row height
				playlistTable.setFont(new Font(songDatabaseTable.getFont().getName(), Font.PLAIN, 18)); // Larger font
        playlistScrollPane = new JScrollPane(playlistTable);

        // Initialize the playlist controller with a LinkedList
        playlistController = new PlaylistController(playlistModel, this);

        // Create playlist control buttons
        JPanel playlistControlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addSongButton = new JButton("Add Song");
        removeSongButton = new JButton("Remove");
        moveUpButton = new JButton("Move Up");
        moveDownButton = new JButton("Move Down");
        sortButton = new JButton("Sort");
        addRandomSongsButton = new JButton("Add 5 Random Songs");

        playlistControlPanel.add(addSongButton);
        playlistControlPanel.add(removeSongButton);
        playlistControlPanel.add(moveUpButton);
        playlistControlPanel.add(moveDownButton);
        playlistControlPanel.add(sortButton);
        playlistControlPanel.add(addRandomSongsButton);


        playlistPanel.add(playlistControlPanel, BorderLayout.NORTH);
        playlistPanel.add(playlistScrollPane, BorderLayout.CENTER);

        // Setup playback control panel
        playbackPanel = new JPanel(new BorderLayout());
        playbackPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel transportPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				previousButton = new JButton(new ImageIcon("media/resized/previous_24x24.png"));
				playPauseButton = new JButton(new ImageIcon("media/resized/play_24x24.png"));
				nextButton = new JButton(new ImageIcon("media/resized/next_24x24.png"));
				transportPanel.add(previousButton);
        transportPanel.add(playPauseButton);
        transportPanel.add(nextButton);

        JPanel infoPanel = new JPanel(new BorderLayout());
        currentSongLabel = new JLabel("No song playing");
        songProgressBar = new JProgressBar(0, 100);
        songProgressBar.setValue(0);
        infoPanel.add(currentSongLabel, BorderLayout.NORTH);
        infoPanel.add(songProgressBar, BorderLayout.CENTER);

        JPanel volumePanel = new JPanel(new BorderLayout());
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        volumeSlider.setMajorTickSpacing(25);
        volumeSlider.setPaintTicks(true);
        JLabel volumeLabel = new JLabel("Volume:");
        volumePanel.add(volumeLabel, BorderLayout.WEST);
        volumePanel.add(volumeSlider, BorderLayout.CENTER);

        playbackPanel.add(transportPanel, BorderLayout.NORTH);
        playbackPanel.add(infoPanel, BorderLayout.CENTER);
        playbackPanel.add(volumePanel, BorderLayout.SOUTH);
    }

    /**
     * Sets up the layout of the application.
     */
    private void setupLayout() {
        // Create a split pane for the two lists
        JSplitPane splitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            songDatabasePanel,
            playlistPanel
        );
        splitPane.setDividerLocation(450);

        // Add components to the main panel
        mainPanel.add(splitPane, BorderLayout.CENTER);
        mainPanel.add(playbackPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);
    }

    /**
     * Sets up event handlers for UI components.
     */
    private void setupEventHandlers() {
        // Search button functionality
        searchButton.addActionListener(e -> {
            String query = searchField.getText();
            songDbController.searchSongs(query);
        });

        // Search field: also trigger search on Enter key
        searchField.addActionListener(e -> {
            searchButton.doClick();
        });

				// Add song button functionality
        addSongButton.addActionListener(e -> {
            int selectedRow = songDatabaseTable.getSelectedRow();
            if (selectedRow >= 0) {
                // Get the song from the model (could be filtered)
                String title = (String) songDatabaseModel.getValueAt(selectedRow, 0);
                String artist = (String) songDatabaseModel.getValueAt(selectedRow, 1);
                String album = (String) songDatabaseModel.getValueAt(selectedRow, 2);
                String duration = (String) songDatabaseModel.getValueAt(selectedRow, 3);

                // Create a new song and add to playlist
                Song song = new Song(title, artist, album, duration);
                playlistController.addSong(song);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Please select a song to add to playlist.",
                    "No Song Selected", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Remove song button functionality
        removeSongButton.addActionListener(e -> {
            int selectedRow = playlistTable.getSelectedRow();
            if (selectedRow >= 0) {
                if (!playlistController.removeSong(selectedRow)) {
                    JOptionPane.showMessageDialog(this,
                        "Could not remove the selected song.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                    "Please select a song to remove from playlist.",
                    "No Song Selected", JOptionPane.WARNING_MESSAGE);
            }
        });



        // Move up button functionality
        moveUpButton.addActionListener(e -> {
            int selectedRow = playlistTable.getSelectedRow();
            if (selectedRow > 0) {
                int newIndex = playlistController.moveSongUp(selectedRow);
                if (newIndex >= 0) {
                    // Select the moved song
                    playlistTable.setRowSelectionInterval(newIndex, newIndex);
                }
            }
        });

        // Move down button functionality
        moveDownButton.addActionListener(e -> {
            int selectedRow = playlistTable.getSelectedRow();
            if (selectedRow >= 0 && selectedRow < playlistController.getSize() - 1) {
                int newIndex = playlistController.moveSongDown(selectedRow);
                if (newIndex >= 0) {
                    // Select the moved song
                    playlistTable.setRowSelectionInterval(newIndex, newIndex);
                }
            }
        });

        // Sort button functionality - by default sort by title
        sortButton.addActionListener(e -> { playlistController.sortByTitle();});

          //add random 5 song functionality
          addRandomSongsButton.addActionListener(e -> {
            List<Song> randomSongs = songDbController.getRandomSongs(5);
            if (randomSongs != null && !randomSongs.isEmpty()) {
                for (Song song : randomSongs) {
                    playlistController.addSong(song);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                    "No songs available to add.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add right-click menu for sort options
        JPopupMenu sortMenu = new JPopupMenu();
        JMenuItem sortByTitleItem = new JMenuItem("Sort by Title");
        JMenuItem sortByArtistItem = new JMenuItem("Sort by Artist");
        JMenuItem sortByDurationItem = new JMenuItem("Sort by Duration");

        sortByTitleItem.addActionListener(e -> playlistController.sortByTitle());
        // sortByArtistItem.addActionListener(e -> playlistController.sortByArtist());
        // sortByDurationItem.addActionListener(e -> playlistController.sortByDuration());

        sortMenu.add(sortByTitleItem);
        sortMenu.add(sortByArtistItem);
        sortMenu.add(sortByDurationItem);

        sortButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
                    sortMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
                    sortMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    /**
     * Main method to run the application.
     */
    public static void main(String[] args) {

        // Use the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new MusicPlaylistManager();
        });
    }
}