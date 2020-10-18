package ch.comstock.progre21;

public enum SceneDirectory {
	HOME("views/Home.fxml"),
	PTDB_NEW("views/Coord.fxml");

	private String fxmlPath;

	SceneDirectory(String fxml) {
		this.fxmlPath = fxml;
	}

	public String getFXMLPath() {
		return this.fxmlPath;
	}
}
