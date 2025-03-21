package MediaPlayer;
import javax.swing.*;
import javax.swing.table.*;


/**
 * Controller class that manages the playlist using a LinkedList data structure.
 * Handles operations like adding, removing, reordering, and sorting songs.
 */
public class PlaylistController {

    // Data structure - Customized LinkedList to store playlist songs
    private BetterLinkedList<Song> playlist;

    // UI Model
    private DefaultTableModel playlistModel;

    // Parent frame for dialogs (not currently used)
    @SuppressWarnings("unused")
		private JFrame parentFrame;

    /**
     * Creates a new PlaylistController.
     *
     * @param tableModel The table model to update with playlist data
     * @param parentFrame The parent frame for displaying dialogs
     */
    public PlaylistController(DefaultTableModel tableModel, JFrame parentFrame) {
        this.playlistModel = tableModel;
        this.parentFrame = parentFrame;
        this.playlist = new BetterLinkedList<>();
    }

    /**
     * Adds a song to the end of the playlist.
     *
     * @param song The song to add to the playlist
     */
    public void addSong(Song song) {
			// todo: Implement this method
    }

    /**
     * Removes a song from the playlist at the given index.
     *
     * @param index The index of the song to remove
     * @return true if removal was successful, false otherwise
     */
    public boolean removeSong(int index) {
			// todo: Implement this method
			return false; // placeholder
    }

    /**
     * Moves a song up one position in the playlist.
     *
     * @param index The index of the song to move
     * @return The new index of the song, or -1 if move failed
     */
    public int moveSongUp(int index) {
			// todo: Implement this method using 'swap' method
			return 1; // placeholder
    }

    /**
     * Moves a song down one position in the playlist.
     *
     * @param index The index of the song to move
     * @return The new index of the song, or -1 if move failed
     */
    public int moveSongDown(int index) {
			// todo: Implement this method using 'swap' method
			return 1; // placeholder
    }

    /**
     * Sorts the playlist alphabetically by song title.
     */
    public void sortByTitle() {
			// todo: Implement this method AFTER implementing sort in the BetterLinkedList class
    }


    /**
     * Gets the current size of the playlist.
     *
     * @return The number of songs in the playlist
     */
    public int getSize() {
        // todo: Implement this method
				return 1;
    }

    /**
     * Gets a song from the playlist at a specific index.
     *
     * @param index The index of the song to retrieve
     * @return The song at the specified index, or null if index is invalid
     */
    public Song getSongAt(int index) {
				// todo: Implement this method
        return null;
    }

    /**
     * Gets all songs in the playlist.
     *
     * @return A list of all songs in the playlist
     */
    public LinkedList<Song> getAllSongs() {
        return playlist;
    }

    /**
     * Clears all songs from the playlist.
     */
    public void clearPlaylist() {
				// todo: Implement this method
    }

    /**
     * Updates the playlist table to reflect the current playlist.
     * This must be called after any modification to the playlist.
     */
    private void updatePlaylistTable() {
        playlistModel.setRowCount(0);
        int position = 1;
				for (int i = 0; i < playlist.size(); i++) {
					Song song = playlist.get(i);
					playlistModel.addRow(song.toPlaylistRow(position++));
				}
    }
}