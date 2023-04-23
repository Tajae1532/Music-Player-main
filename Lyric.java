import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Lyric {
    private String title;
    private String lyrics;
    private List<Song> songs;

    public Lyric(String title, String lyricFilePath) {
        this.title = title;
        this.lyrics = readLyricsFromFile(lyricFilePath);
    }

    public String getTitle()    {
        return this.title;
    }

    public void printLyrics()   {
        System.out.println("Lyrics for " + title + ": ");
        System.out.println(lyrics);
    }

    private String readLyricsFromFile(String lyricFilePath)   {
        StringBuilder lyricsBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(lyricFilePath))) {
            String line;
            while ((line = br.readLine()) != null)  {
                lyricsBuilder.append(line);
                lyricsBuilder.append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading the lyrics: " + e.getMessage());
        }
        return lyricsBuilder.toString();
    }

    public String getLyrics(String title) {
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                // Create a new Lyric object with the title and the path to the lyrics file
                Lyric lyric = new Lyric(title, "lyrics/" + title + ".txt");
                // Return the lyrics as a String
                return lyric.getLyrics(title);
            }
        }
        // If the title was not found, return null
        return null;
    }
}
