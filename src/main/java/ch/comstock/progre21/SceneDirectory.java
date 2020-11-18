package ch.comstock.progre21;

public enum SceneDirectory {
	HOME("views/Home.fxml"),
	PTDB("views/PTDB.fxml"),
	MS_VF("views/MS_VF.fxml"),
	MS_VR("views/MS_VR.fxml"),
	MS_ARC("views/MS_ARC.fxml"),
	MS_ARCR("views/MS_ARCR.fxml"),
	ARTYOBS("views/ARTYOBS.fxml"),
	CALC_UPN("views/CALC_UPN.fxml"),
	CALC_NORM("views/CALC_NORM.fxml"),
	SETTINGS("views/Settings.fxml");
	private String fxmlPath;

	SceneDirectory(String fxml) {
		this.fxmlPath = fxml;
	}

	public String getFXMLPath() {
		return this.fxmlPath;
	}
}
