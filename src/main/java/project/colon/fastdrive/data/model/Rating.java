package project.colon.fastdrive.data.model;

public enum Rating {

    BAD(1),
    GOOD(2),
    SATISFACTORY(3),
    EXCELLENT(4);

        private int rating;

        Rating(int rating){
            this.rating = rating;
        }

    public int getRating() {
        return rating;
    }
}
