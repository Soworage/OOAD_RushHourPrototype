module RushHourPrototype {
	 requires javafx.base;
	    requires javafx.controls;
	    requires javafx.media;
	    requires javafx.fxml;
	    requires javafx.web;
	opens application to javafx.graphics, javafx.fxml;
}
