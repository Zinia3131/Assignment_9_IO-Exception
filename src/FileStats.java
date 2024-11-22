class FileStats {
    private int lines;
    private int words;
    private int characters;

    public FileStats(int lines, int words, int characters) {
        this.lines = lines;
        this.words = words;
        this.characters = characters;
    }

    public int getLines() {
        return lines;
    }

    public int getWords() {
        return words;
    }

    public int getCharacters() {
        return characters;
    }
}