import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MusicPlayer {
    private ArrayList<Song> songs;
    private int currentSongIndex;
    private boolean repeat;
    private Playlist playlist;
    private HashMap<String, Lyric> lyrics;

    public MusicPlayer() {
        // Constructor for MusicPlayer, initializes the list of songs
        songs = new ArrayList<Song>();
        currentSongIndex = 0;
        repeat = false;
        playlist = new Playlist();
        lyrics = new HashMap<String, Lyric>();
        //loadLyrics();
    }

    

   public void loadLyrics() {
        try {
            Lyric firstDayOut = new Lyric("First Day Out", "lyrics/firstdayout.txt");
            lyrics.put(firstDayOut.getTitle().toLowerCase(), firstDayOut);

            Lyric flawsAndSin = new Lyric("Flaws and Sin", "lyrics/flawsandsin.txt");
            lyrics.put(flawsAndSin.getTitle().toLowerCase(), flawsAndSin);

            Lyric freddyKrueger = new Lyric("Freddy Krueger", "lyrics/freddy.txt");
            lyrics.put(freddyKrueger.getTitle().toLowerCase(), freddyKrueger);

            Lyric middleChild = new Lyric("Middle Child", "lyrics/middlechild.txt");
            lyrics.put(middleChild.getTitle().toLowerCase(), middleChild);

            Lyric mySide = new Lyric("My Side", "lyrics/myside.txt");
            lyrics.put(mySide.getTitle().toLowerCase(), mySide);

            Lyric reallyReally = new Lyric("Really Really", "lyrics/reallyreally.txt");
            lyrics.put(reallyReally.getTitle().toLowerCase(), reallyReally);

            Lyric thugCry = new Lyric("Thug Cry", "lyrics/thugcry.txt");
            lyrics.put(thugCry.getTitle().toLowerCase(), thugCry);

            Lyric timing = new Lyric("Timing", "lyrics/timing.txt");
            lyrics.put(timing.getTitle().toLowerCase(), timing);

            Lyric toxicWaste = new Lyric("Toxic Waste", "lyrics/toxicwaste.txt");
            lyrics.put(toxicWaste.getTitle().toLowerCase(), toxicWaste);

            Lyric two = new Lyric("Two", "lyrics/two.txt");
            lyrics.put(two.getTitle().toLowerCase(), two);
    
        } catch (Exception e) {
            System.err.println("Error loading the lyrics: ");
        }
    }
    
    public String getLyrics(String title) {
        String lyricsFilePath = "lyrics/" + title.toLowerCase().replaceAll("\\s", "") + ".txt";
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                try (BufferedReader br = new BufferedReader(new FileReader(lyricsFilePath))) {
                    StringBuilder lyricsBuilder = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        lyricsBuilder.append(line);
                        lyricsBuilder.append("\n");
                    }
                    return lyricsBuilder.toString();
                } catch (IOException e) {
                    System.err.println("Error reading the lyrics for " + title + ": " + e.getMessage());
                }
            }
        }
        return null;
    }

    public void printLyrics(String songTitle)   {
        Lyric songLyrics = lyrics.get(songTitle);
        if (songLyrics != null) {
            System.out.println("Lyrics for " + songLyrics.getTitle() + ": ");
            songLyrics.printLyrics();
        } else{
            System.out.println("Lyrics not found for song: " + songTitle);
        }
    }


    public void addSong(Song song) {
        // Add a new song to the list
        songs.add(song);
    }

    public void addToPlaylist(Song song)    {
        // Add a song to the playlist
        playlist.addSong(song);
    }

    public void playPlaylist()  {
        // Play the songs in the playlist
        for (Song song : playlist.getSongs())   {
            System.out.println("Now playing: " + song.getTitle() + " by " + song.getArtist());
        }
    }

    public void searchMusic(String searchTerm) {
        // Search for a song by title or artist
        ArrayList<Song> results = new ArrayList<Song>();
        for (Song song : songs) {
            if (song.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    song.getArtist().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(song);
            }
        }

        // Display search results
        if (results.size() > 0) {
            System.out.println("Search results:");
            for (Song song : results) {
                System.out.println(song.getTitle() + " by " + song.getArtist());
            }
        } else {
            System.out.println("No results found for \"" + searchTerm + "\"");
        }
    }

    public void nextSong() {
        if (songs.isEmpty()) {
            System.out.println("No songs in the player.");
            return;
        }

        currentSongIndex = (currentSongIndex + 1) % songs.size();
        Song currentSong = songs.get(currentSongIndex);
        System.out.println("Now playing: " + currentSong.getTitle() + " by " + currentSong.getArtist());
    }

    public void listSongs() {
        if (songs.isEmpty()) {
            System.out.println("No songs in the player.");
            return;
        }

        System.out.println("List of songs: ");
        for (Song song : songs) {
            System.out.println(song.getTitle() + " by " + song.getArtist());
        }
    }

    public void previousSong() {
        if (songs.isEmpty()) {
            System.out.println("No songs in the player.");
            return;
        }

        currentSongIndex = (currentSongIndex - 1 + songs.size()) % songs.size();
        Song currentSong = songs.get(currentSongIndex);
        System.out.println("Now playing: " + currentSong.getTitle() + " by " + currentSong.getArtist());
    }

    public void toggleRepeat() {
        repeat = !repeat;
        System.out.println("Repeat is now " + (repeat ? "on" : "off") + ".");
    }
}

