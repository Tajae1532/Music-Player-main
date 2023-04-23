import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Playlist{
    private ArrayList<Song> songs;
    private ArrayList<String> allowedSongs;

    public ArrayList<Song> getSongs()   {
        return songs;
    }

    public Playlist()   {
        songs = new ArrayList<>();
        allowedSongs = new ArrayList<>();
        readAllowedSongsFromFile("songs.txt");
    }

    private void readAllowedSongsFromFile(String filename)  {
        try (BufferedReader br = new BufferedReader(new FileReader(filename)))  {
            String line;
            while ((line = br.readLine()) != null)  {
                allowedSongs.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading " + filename + ": " + e.getMessage());
        }
    }

    public void addSong(Song song)  {
        if (allowedSongs.contains(song.getTitle())) {
            songs.add(song);
            System.out.println(song.getTitle() + " added to playlist");
        } 
    }

    public void addSong(String title, String artist)    {
        Song song = new Song(title, artist);
        songs.add(song);
    }

    public boolean removeSong(String title) {
        for (Song song : songs) {
            if (song.getTitle().equals(title)) {
                songs.remove(song);
                System.out.println(title + " has been removed from the playlist");
                return true;
            }
        }
        System.out.println("Song not in the playlist.");
        return false;
    }
}
