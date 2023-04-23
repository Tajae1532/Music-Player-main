import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();
        Playlist playlist = new Playlist();

        //Loading song from songs.txt
        try (BufferedReader br = new BufferedReader(new FileReader("songs.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] songData = line.split(",");
                if (songData.length == 2) {
                    Song song = new Song(songData[0].trim(), songData[1].trim());
                    player.addSong(song);
                    playlist.addSong(song);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the songs.txt file: " + e.getMessage());
        }

        System.out.println("Welcome to the Music Player");

        //Had to add try in font of the scanner since it saying it was never closed after adding the playlist line
        try (Scanner scanner = new Scanner(System.in))  {

        while (true) {
            // Prompt the user for a command
            System.out.print("Enter a command: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("search")) {
                // Search for a song by title or artist
                System.out.print("Enter a search term: ");
                String searchTerm = scanner.nextLine();
                player.searchMusic(searchTerm);
            }
            else if (input.equalsIgnoreCase("list")) {
                player.listSongs();
            }
            else if (input.equalsIgnoreCase("repeat")) {
                player.toggleRepeat();
            }
            else if (input.equalsIgnoreCase("next")) {
                player.nextSong();
            } else if (input.equalsIgnoreCase("previous")) {
                player.previousSong();
            } else if (input.equalsIgnoreCase("playlist"))  {
                System.out.println("Current playlist: ");
                for (Song song : playlist.getSongs())   {
                    System.out.println(song.getTitle() + " by " + song.getArtist());
                }
            } else if (input.equalsIgnoreCase("add")) {
                // Add a song to the playlist
                    System.out.print("Enter the song title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter the artist name: ");
                    String artist = scanner.nextLine();
                    playlist.addSong(title, artist);
                    System.out.println(title + " by " + artist + " has been added to the playlist");
                } else if (input.equalsIgnoreCase("remove")) {
                    System.out.print("Enter the song title: ");
                    String title = scanner.nextLine();
                    playlist.removeSong(title);
            // should show the lyrics when typing the title of the song
            } else if (input.equalsIgnoreCase("lyrics")) {
                System.out.print("Enter the song title: ");
                String title = scanner.nextLine();
                String lyrics = player.getLyrics(title);
                if (lyrics != null) {
                    System.out.println(lyrics);
                } else {
                    System.out.println("Sorry, the lyrics for " + title + " could not be found.");
                }
            }
            else if (input.equalsIgnoreCase("exit")) {
                // Exit the program
                System.out.println("Goodbye!");
                break;
            } else {
                // Invalid command
                System.out.println("Invalid command. Try again.");
            }
        }
    }
    catch (Exception e) {
        System.err.println("Error: " + e.getMessage());
    }
}
}

