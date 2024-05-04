package assignment8SW;

import java.util.ArrayList;
import java.util.List;

interface Song {
    String getTitle();
    String getArtist();
}

interface Iterator {
    boolean hasNext();
    Song next();
}

interface Playlist {
    Iterator createIterator();
    void addSong(Song song);
}

class SongImpl implements Song {
    private String title;
    private String artist;

    public SongImpl(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getArtist() {
        return artist;
    }
}

class PlaylistImpl implements Playlist {
    private List<Song> songs;

    public PlaylistImpl() {
        this.songs = new ArrayList<>();
    }

    @Override
    public Iterator createIterator() {
        return new SongIterator();
    }

    @Override
    public void addSong(Song song) {
        songs.add(song);
    }

    private class SongIterator implements Iterator {
        private int index;

        @Override
        public boolean hasNext() {
            return index < songs.size();
        }

        @Override
        public Song next() {
            if (hasNext()) {
                return songs.get(index++);
            } else {
                throw new IndexOutOfBoundsException("No more songs in the playlist");
            }
        }
    }
}

public class Assignment8SWEx2 {
    public static void main(String[] args) {
        Playlist playlist = new PlaylistImpl();
        playlist.addSong(new SongImpl("Song 1", "Artist 1"));
        playlist.addSong(new SongImpl("Song 2", "Artist 2"));
        playlist.addSong(new SongImpl("Song 3", "Artist 3"));

        Iterator iterator = playlist.createIterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            System.out.println("Now playing: " + song.getTitle() + " by " + song.getArtist());
        }
    }
}
