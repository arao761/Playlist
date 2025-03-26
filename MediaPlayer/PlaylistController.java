package MediaPlayer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import org.w3c.dom.Node;


/**
 * Controller class that manages the playlist using a LinkedList data structure.
 * Handles operations like adding, removing, reordering, and sorting songs.
 */
public class PlaylistController{

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
      playlist.addLast(song);
      updatePlaylistTable();
    }

    /**
     * Removes a song from the playlist at the given index.
     *
     * @param index The index of the song to remove
     * @return true if removal was successful, false otherwise
     */
    public boolean removeSong(int index) {
			 
			if(index < 0 || index > playlist.size() - 1) {
        return false; 
      }

      playlist.remove(index);
      updatePlaylistTable();

      return true; 

    }

    /**
     * Moves a song up one position in the playlist.
     *
     * @param index The index of the song to move
     * @return The new index of the song, or -1 if move failed
     */
    public int moveSongUp(int index) {

      if(index < 0 || index >= playlist.size()){
        return -1; 
      }

			playlist.swapWithNext(index - 1); 
      updatePlaylistTable();

      return index - 1; 

    }

    /**
     * Moves a song down one position in the playlist.
     *
     * @param index The index of the song to move
     * @return The new index of the song, or -1 if move failed
     */
    public int moveSongDown(int index) {
			
      if(index < 0 || index >= playlist.size() - 1){
        return -1; 
      }

			playlist.swapWithNext(index); 
      updatePlaylistTable();

      return index + 1;
    }

    /**
     * Sorts the playlist alphabetically by song title.
     */
    public void sortByTitle() {

      playlist.sort(); 
      updatePlaylistTable();  

    }


    /**
     * Gets the current size of the playlist.
     *
     * @return The number of songs in the playlist
     */
    public int getSize() {
        
      return playlist.size(); 
    }

    /**
     * Gets a song from the playlist at a specific index.
     *
     * @param index The index of the song to retrieve
     * @return The song at the specified index, or null if index is invalid
     */
    public Song getSongAt(int index) {
				
      return (Song) playlist.get(index); 

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
				playlist.clear(); 
    }

    public List<Song> getRandomSongs(int count) {
      List<Song> allSongs = new ArrayList<>();
      
      for (int i = 0; i < count; i++) {
          allSongs.add(playlist.get(i));
      }
  
      if (allSongs.size() <= count) {
          return new ArrayList<>(allSongs);
      }

      Collections.shuffle(allSongs);

      updatePlaylistTable();
  
      return allSongs.subList(0, count);
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