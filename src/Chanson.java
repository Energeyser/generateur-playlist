import java.util.Date;

/**
 * Created by axel- on 30/08/2016.
 */
public class Chanson {
    private int trackID;
    private int size;
    private int totalTime;
    private int trackNumber;
    private int year;
    private Date dateModified;
    private Date dateAdded;
    private int bitRate;
    private int sampleRate;
    private int skipCount;
    private Date skipDate;
    private int artworkCount;
    private String persistantID;
    private String trackType;
    private int fileFolderCount;
    private int libraryFolderCount;
    private String name;
    private String artist;
    private String albumArtist;
    private String composer;
    private String album;
    private String genre;
    private String kind;
    private String location;

    public Chanson(){

    }


    /* GETTERS */

    public int getTotalTime() {
        return totalTime;
    }

    public String getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public Date getSkipDate() {
        return skipDate;
    }

    public int getBitRate() {
        return bitRate;
    }

    public int getArtworkCount() {
        return artworkCount;
    }

    public int getFileFolderCount() {
        return fileFolderCount;
    }

    public int getLibraryFolderCount() {
        return libraryFolderCount;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public int getSize() {
        return size;
    }

    public int getSkipCount() {
        return skipCount;
    }

    public int getTrackID() {
        return trackID;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public int getYear() {
        return year;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public String getPersistantID() {
        return persistantID;
    }

    public String getTrackType() {
        return trackType;
    }

    public String getAlbum() {
        return album;
    }

    public String getComposer() {
        return composer;
    }

    public String getGenre() {
        return genre;
    }

    public String getKind() {
        return kind;
    }

    public String getLocation() {
        return location;
    }

    /*SETTERS*/

    public void setBitRate(int bitRate) {
        this.bitRate = bitRate;
    }

    public void setArtworkCount(int artworkCount) {
        this.artworkCount = artworkCount;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public void setFileFolderCount(int fileFolderCount) {
        this.fileFolderCount = fileFolderCount;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public void setPersistantID(String persistantID) {
        this.persistantID = persistantID;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setLibraryFolderCount(int libraryFolderCount) {
        this.libraryFolderCount = libraryFolderCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }

    public void setSkipCount(int skipCount) {
        this.skipCount = skipCount;
    }

    public void setSkipDate(Date skipDate) {
        this.skipDate = skipDate;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public void setTrackID(int trackID) {
        this.trackID = trackID;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public void setTrackType(String trackType) {
        this.trackType = trackType;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
