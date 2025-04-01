package usantatecla.movies.v22;

public class MovieBuilder {

	private String title;
	private MovieType movieType;
	
	private enum MovieType {
		REGULAR, CHILDREN, NEW_RELEASE
	}
	
	public MovieBuilder() {
		title = "movieName";
		movieType = MovieType.REGULAR;
	}
	
	public MovieBuilder title(String title) {
		this.title = title;
		return this;
	}
	
	public MovieBuilder childrens() {
		this.movieType = MovieType.CHILDREN;
		return this;
	}
	
	public MovieBuilder regular() {
		this.movieType = MovieType.REGULAR;
		return this;
	}
	
	public MovieBuilder newRelease() {
		this.movieType = MovieType.NEW_RELEASE;
		return this;
	}
	
	public Movie build() {
		switch (movieType) {
			case CHILDREN:
				return new ChildrenMovie(title);
			case NEW_RELEASE:
				return new NewReleaseMovie(title);
			case REGULAR:
			default:
				return new RegularMovie(title);
		}
	}
}
