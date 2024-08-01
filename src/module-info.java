module RushHourPrototype {
	 requires javafx.base;
	    requires javafx.controls;
	    requires javafx.media;
	    requires javafx.fxml;
	    requires javafx.web;
	requires java.desktop;
	requires jdk.xml.dom;
	opens application to javafx.graphics, javafx.fxml, javafx.base;
    opens application.controller to javafx.fxml, javafx.graphics;
	opens application.model to javafx.fxml, javafx.graphics, javafx.base;
}
