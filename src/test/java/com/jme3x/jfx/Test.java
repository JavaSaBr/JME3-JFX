package com.jme3x.jfx;

import org.lwjgl.opengl.Display;

import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;

public class Test extends SimpleApplication {

	public static void main(final String[] args) {
		new Test().start();
	}

	@Override
	public void simpleInitApp() {
		this.setPauseOnLostFocus(false);
		this.flyCam.setDragToRotate(true);
		this.viewPort.setBackgroundColor(ColorRGBA.Red);

		final GuiManager testguiManager = new GuiManager(this.guiNode, this.assetManager, this, false,
				new ExampleCursorProvider(this, this.assetManager, this.inputManager));
		/**
		 * 2d gui, use the default input provider
		 */
		this.inputManager.addRawInputListener(testguiManager.getInputRedirector());

		final TestHud testhud = new TestHud();
		testhud.initialize();
		testguiManager.attachHudAsync(testhud);

		final TestWindow testwindow = new TestWindow();
		testwindow.initialize();
		testwindow.setTitleAsync("TestTitle");
		testguiManager.attachHudAsync(testwindow);

		Display.setResizable(true);
	}

	@Override
	public void simpleUpdate(final float tpf) {
		if (Display.wasResized()) {
			// keep settings in sync with the actual Display
			int w = Display.getWidth();
			int h = Display.getHeight();
			if (w < 2) {
				w = 2;
			}
			if (h < 2) {
				h = 2;
			}
			this.settings.setWidth(Display.getWidth());
			this.settings.setHeight(Display.getHeight());
			this.reshape(this.settings.getWidth(), this.settings.getHeight());
		}
	}
}