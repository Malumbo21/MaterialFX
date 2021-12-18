package io.github.palexdev.materialfx.css;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.layout.Region;

/**
 * Helper class which is responsible for parsing the stylesheets for a given {@link Parent}.
 * <p>
 * The list is automatically rebuilt if the parent's stylesheets change.
 * <p>
 * This approach is very simple yet effective (ffs even a baby could make it), but it has a limitation at the moment.
 * The stylesheets parsing is limited to the specified parent, it won't scan the entire scenegraph, I could also implement something like
 * that, it's simple, it's just needed to mimic the behavior of the JavaFX' StyleManager, but at the moment I don't think
 * it's really useful, so... we'll see in future if needed.
 */
public class MFXCSSBridge {
	//================================================================================
	// Properties
	//================================================================================
	private Parent parent;
	private final ObservableList<String> stylesheets = FXCollections.observableArrayList();
	private final InvalidationListener stylesheetsChanged = invalidated -> initializeStylesheets();

	//================================================================================
	// Constructors
	//================================================================================
	public MFXCSSBridge(Parent parent) {
		this.parent = parent;
		initializeStylesheets();
		addListeners();
	}

	//================================================================================
	// Methods
	//================================================================================

	/**
	 * Called by the constructor the first time.
	 * <p>
	 * Responsible for parsing and building the stylesheets list.
	 */
	public void initializeStylesheets() {
		stylesheets.clear();
		if (parent == null) return;

		if (parent instanceof Region) {
			Region region = (Region) parent;
			if (region.getUserAgentStylesheet() != null && !region.getUserAgentStylesheet().isEmpty()) {
				stylesheets.add(region.getUserAgentStylesheet());
			}
		}
		stylesheets.addAll(parent.getStylesheets());
	}

	/**
	 * Adds the listener responsible for updating the stylesheets list
	 * to the parent's stylesheets observable list.
	 */
	public void addListeners() {
		if (parent == null) return;
		parent.getStylesheets().addListener(stylesheetsChanged);
	}

	/**
	 * Disposes the MFXCSSBridge by removing the stylesheetsChanged listener.
	 */
	public void dispose() {
		if (parent != null) {
			parent.getStylesheets().removeListener(stylesheetsChanged);
		}
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	/**
	 * @return the parsed stylesheets list
	 */
	public ObservableList<String> getStylesheets() {
		return stylesheets;
	}
}