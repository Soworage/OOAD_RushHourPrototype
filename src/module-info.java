module RushHourPrototype {
	 requires javafx.base;
	    requires javafx.controls;
	    requires javafx.media;
	    requires javafx.fxml;
	    requires javafx.web;
	requires java.desktop;
	requires jdk.xml.dom;
	opens application to javafx.graphics, javafx.fxml;
}
